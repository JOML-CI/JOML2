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
 * Generated implementation of {@link FloatQuat} backed by individual scalar fields.
 * <p>
 * Not part of the public API - obtain instances through the {@link Joml} factory methods.
 */
public final class FloatQuatImpl implements FloatQuat {
    public float x;
    public float y;
    public float z;
    public float w;

    /** Store/load dispatch targets, picked on the first store/load (see {@code Joml.storeLoadBackend()}). */
    private static final class StoreLoad {
        static final FloatQuatBbOps BB_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new FloatQuatBbOpsUnsafe()
            : new FloatQuatBbOpsApi();
        static final FloatQuatRawOps RAW_OPS = Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE
            ? new FloatQuatRawOpsUnsafe()
            : new FloatQuatRawOpsApi();
    }

    public FloatQuatImpl() {
        w = 1;
    }

    public FloatQuatImpl(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public FloatQuatImpl(FloatQuatR src) {
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
    public FloatQuat invert(@Mutated FloatQuat dest) {
        if (Math.useFma()) {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _t3_inv = 1.0f / java.lang.Math.fma(this.w, this.w, java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y)));
            d.x = -(this.x * _t3_inv);
            d.y = -(this.y * _t3_inv);
            d.z = -(this.z * _t3_inv);
            d.w = this.w * _t3_inv;
            return d;
        } else {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _t3_inv = 1.0f / ((this.w) * (this.w) + (((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))))));
            d.x = -(this.x * _t3_inv);
            d.y = -(this.y * _t3_inv);
            d.z = -(this.z * _t3_inv);
            d.w = this.w * _t3_inv;
            return d;
        }
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
        if (Math.useFma()) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            float _t3_inv = 1.0f / java.lang.Math.fma(this.w, this.w, java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y)));
            d.x = -(this.x * _t3_inv);
            d.y = -(this.y * _t3_inv);
            d.z = -(this.z * _t3_inv);
            d.w = this.w * _t3_inv;
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            float _t3_inv = 1.0f / ((this.w) * (this.w) + (((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))))));
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
    public FloatQuat invertProduct(FloatQuatR other, @Mutated FloatQuat dest) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        if (!Math.useFma()) return invertProduct_mulAdd(otherX, otherY, otherZ, otherW, dest);
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _t20 = java.lang.Math.fma(otherX, this.w, otherW * this.x) + java.lang.Math.fma(otherZ, this.y, -(otherY * this.z));
        float _t21 = java.lang.Math.fma(otherW, this.w, -otherX * this.x) - java.lang.Math.fma(otherY, this.y, otherZ * this.z);
        float _t22 = java.lang.Math.fma(otherY, this.x, otherZ * this.w) + java.lang.Math.fma(otherW, this.z, -otherX * this.y);
        float _t23 = java.lang.Math.fma(otherX, this.z, otherW * this.y) + java.lang.Math.fma(otherY, this.w, -(otherZ * this.x));
        float _t27_inv = 1.0f / java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t20 * _t20)));
        d.x = -(_t20 * _t27_inv);
        d.y = -(_t23 * _t27_inv);
        d.z = -(_t22 * _t27_inv);
        d.w = _t21 * _t27_inv;
        return d;
    }

    /**
     * Compute the inverse of the product of this quaternion and {@code other}, i.e.
     * {@code (this * other)^-1}, and store the result in {@code dest}.
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
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        if (!Math.useFma()) return invertProduct_mulAdd(otherX, otherY, otherZ, otherW, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _t20 = java.lang.Math.fma(otherX, this.w, otherW * this.x) + java.lang.Math.fma(otherZ, this.y, -(otherY * this.z));
        float _t21 = java.lang.Math.fma(otherW, this.w, -otherX * this.x) - java.lang.Math.fma(otherY, this.y, otherZ * this.z);
        float _t22 = java.lang.Math.fma(otherY, this.x, otherZ * this.w) + java.lang.Math.fma(otherW, this.z, -otherX * this.y);
        float _t23 = java.lang.Math.fma(otherX, this.z, otherW * this.y) + java.lang.Math.fma(otherY, this.w, -(otherZ * this.x));
        float _t27_inv = 1.0f / java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t20 * _t20)));
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
    public FloatQuat invertProduct(float otherX, float otherY, float otherZ, float otherW, @Mutated FloatQuat dest) {
        if (!Math.useFma()) return invertProduct_mulAdd(otherX, otherY, otherZ, otherW, dest);
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _t20 = java.lang.Math.fma(otherX, this.w, otherW * this.x) + java.lang.Math.fma(otherZ, this.y, -(otherY * this.z));
        float _t21 = java.lang.Math.fma(otherW, this.w, -otherX * this.x) - java.lang.Math.fma(otherY, this.y, otherZ * this.z);
        float _t22 = java.lang.Math.fma(otherY, this.x, otherZ * this.w) + java.lang.Math.fma(otherW, this.z, -otherX * this.y);
        float _t23 = java.lang.Math.fma(otherX, this.z, otherW * this.y) + java.lang.Math.fma(otherY, this.w, -(otherZ * this.x));
        float _t27_inv = 1.0f / java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t20 * _t20)));
        d.x = -(_t20 * _t27_inv);
        d.y = -(_t23 * _t27_inv);
        d.z = -(_t22 * _t27_inv);
        d.w = _t21 * _t27_inv;
        return d;
    }

    /** {@code invertProduct} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatQuat invertProduct_mulAdd(float otherX, float otherY, float otherZ, float otherW, @Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _t20 = ((otherX) * (this.w) + (otherW * this.x)) + ((otherZ) * (this.y) - (otherY * this.z));
        float _t21 = ((otherW) * (this.w) - (otherX * this.x)) - ((otherY) * (this.y) + (otherZ * this.z));
        float _t22 = ((otherY) * (this.x) + (otherZ * this.w)) + ((otherW) * (this.z) - (otherX * this.y));
        float _t23 = ((otherX) * (this.z) + (otherW * this.y)) + ((otherY) * (this.w) - (otherZ * this.x));
        float _t27_inv = 1.0f / ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t20 * _t20))))));
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
        if (!Math.useFma()) return invertProduct_mulAdd(otherX, otherY, otherZ, otherW, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _t20 = java.lang.Math.fma(otherX, this.w, otherW * this.x) + java.lang.Math.fma(otherZ, this.y, -(otherY * this.z));
        float _t21 = java.lang.Math.fma(otherW, this.w, -otherX * this.x) - java.lang.Math.fma(otherY, this.y, otherZ * this.z);
        float _t22 = java.lang.Math.fma(otherY, this.x, otherZ * this.w) + java.lang.Math.fma(otherW, this.z, -otherX * this.y);
        float _t23 = java.lang.Math.fma(otherX, this.z, otherW * this.y) + java.lang.Math.fma(otherY, this.w, -(otherZ * this.x));
        float _t27_inv = 1.0f / java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t20 * _t20)));
        d.x = -(_t20 * _t27_inv);
        d.y = -(_t23 * _t27_inv);
        d.z = -(_t22 * _t27_inv);
        d.w = _t21 * _t27_inv;
        return d;
    }

    /** {@code invertProduct} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat invertProduct_mulAdd(float otherX, float otherY, float otherZ, float otherW, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _t20 = ((otherX) * (this.w) + (otherW * this.x)) + ((otherZ) * (this.y) - (otherY * this.z));
        float _t21 = ((otherW) * (this.w) - (otherX * this.x)) - ((otherY) * (this.y) + (otherZ * this.z));
        float _t22 = ((otherY) * (this.x) + (otherZ * this.w)) + ((otherW) * (this.z) - (otherX * this.y));
        float _t23 = ((otherX) * (this.z) + (otherW * this.y)) + ((otherY) * (this.w) - (otherZ * this.x));
        float _t27_inv = 1.0f / ((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t20 * _t20))))));
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
    public FloatQuat add(FloatQuatR other, @Mutated FloatQuat dest) {
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        FloatQuatImpl d = (FloatQuatImpl) dest;
        d.x = other.x() + this.x;
        d.y = otherY + this.y;
        d.z = otherZ + this.z;
        d.w = otherW + this.w;
        return d;
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
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
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
    public FloatQuat add(float otherX, float otherY, float otherZ, float otherW, @Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
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
    public FloatQuat mul(float scalar, @Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        d.x = scalar * this.x;
        d.y = scalar * this.y;
        d.z = scalar * this.z;
        d.w = scalar * this.w;
        return d;
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
    public FloatQuat negate(@Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        d.x = -this.x;
        d.y = -this.y;
        d.z = -this.z;
        d.w = -this.w;
        return d;
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
    public FloatQuat sub(FloatQuatR other, @Mutated FloatQuat dest) {
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        FloatQuatImpl d = (FloatQuatImpl) dest;
        d.x = this.x - other.x();
        d.y = this.y - otherY;
        d.z = this.z - otherZ;
        d.w = this.w - otherW;
        return d;
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
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
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
    public FloatQuat sub(float otherX, float otherY, float otherZ, float otherW, @Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
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
    @Mutated public FloatQuat makeUniformRotation(float u1, float u2, float u3) {
        float _t0 = (float) java.lang.Math.sqrt(u1);
        float _t1 = u2 * 6.2831855f;
        float _t3 = u3 * 6.2831855f;
        float _t4 = Math.sin(_t1);
        float _t5 = (float) java.lang.Math.sqrt(1.0f - u1);
        float _t6 = Math.sin(_t3);
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
    @Mutated public FloatQuat set(FloatQuatR v) {
        float vY = v.y();
        float vZ = v.z();
        float vW = v.w();
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
    @Mutated public FloatQuat set(float vX, float vY, float vZ, float vW) {
        this.x = vX;
        this.y = vY;
        this.z = vZ;
        this.w = vW;
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
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        d.x = this.x;
        d.y = this.y;
        d.z = this.z;
        d.w = this.w;
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
    @Mutated public FloatQuat makeFromDualQuat(FloatDualQuatR dq) {
        float dqRY = dq.rY();
        float dqRZ = dq.rZ();
        float dqRW = dq.rW();
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
    @Mutated public FloatQuat makeFromDualQuat(float dqRX, float dqRY, float dqRZ, float dqRW, float dqDX, float dqDY, float dqDZ, float dqDW) {
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
    private void makeFromMatrix_s4190286d_c0(FloatQuatImpl _dst, float _t10, float _sp0, float _t1, float _r0, float _t2, float _t15, float _r1, float _r4, float _sp1, float _t4, float _sp2, float _t6, float _t7, float _sp3, float _t16, float _t8, float _t9, float _t17, float _t14) {
        _dst.x = _t10 > 0.0f ? _sp0 * _t1 : _r0 > _t2 ? 0.5f * (float) java.lang.Math.sqrt(_t15) : _r1 > _r4 ? _sp1 * _t4 : _sp2 * _t6;
        _dst.y = _t10 > 0.0f ? _sp0 * _t7 : _r0 > _t2 ? _sp3 * _t4 : _r1 > _r4 ? 0.5f * (float) java.lang.Math.sqrt(_t16) : _sp2 * _t8;
        _dst.z = _t10 > 0.0f ? _sp0 * _t9 : _r0 > _t2 ? _sp3 * _t6 : _r1 > _r4 ? _sp1 * _t8 : 0.5f * (float) java.lang.Math.sqrt(_t17);
        _dst.w = _t10 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t14) : _r0 > _t2 ? _sp3 * _t1 : _r1 > _r4 ? _sp1 * _t7 : _sp2 * _t9;
    }

    /**
     * Private tail of {@code makeFromMatrix}. Shared by 3 identical private paths of
     * {@code makeFromMatrix}; reached only through it.
     */
    private void makeFromMatrix_s4190286d_tail(FloatQuatImpl _dst, float _t15, float _t10, float _sp0, float _t1, float _r0, float _t2, float _r1, float _r4, float _sp1, float _t4, float _sp2, float _t6, float _t7, float _t16, float _t8, float _t9, float _t17, float _t14) {
        makeFromMatrix_s4190286d_c0(_dst, _t10, _sp0, _t1, _r0, _t2, _t15, _r1, _r4, _sp1, _t4, _sp2, _t6, _t7, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t15)), _t16, _t8, _t9, _t17, _t14);
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
        float _r0 = m.m00();
        float _r1 = m.m11();
        float _r2 = m.m21();
        float _r3 = m.m12();
        float _r4 = m.m22();
        float _r5 = m.m01();
        float _r6 = m.m10();
        float _r7 = m.m02();
        float _r8 = m.m20();
        float _t0 = _r0 + _r1;
        float _t10 = _r4 + _t0;
        float _t14 = 1.0f + _t10;
        float _t16 = 1.0f + (_r1 - (_r0 + _r4));
        float _t17 = 1.0f + (_r4 - _t0);
        makeFromMatrix_s4190286d_tail(this, 1.0f + (_r0 - (_r1 + _r4)), _t10, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t14)), _r2 - _r3, _r0, Math.max(_r1, _r4), _r1, _r4, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)), _r5 + _r6, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), _r7 + _r8, _r7 - _r8, _t16, _r3 + _r2, _r6 - _r5, _t17, _t14);
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
    @Mutated public FloatQuat makeFromMatrix(Float3x4R m) {
        float _r0 = m.m00();
        float _r1 = m.m11();
        float _r2 = m.m21();
        float _r3 = m.m12();
        float _r4 = m.m22();
        float _r5 = m.m01();
        float _r6 = m.m10();
        float _r7 = m.m02();
        float _r8 = m.m20();
        float _t0 = _r0 + _r1;
        float _t10 = _r4 + _t0;
        float _t14 = 1.0f + _t10;
        float _t16 = 1.0f + (_r1 - (_r0 + _r4));
        float _t17 = 1.0f + (_r4 - _t0);
        makeFromMatrix_s4190286d_tail(this, 1.0f + (_r0 - (_r1 + _r4)), _t10, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t14)), _r2 - _r3, _r0, Math.max(_r1, _r4), _r1, _r4, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)), _r5 + _r6, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), _r7 + _r8, _r7 - _r8, _t16, _r3 + _r2, _r6 - _r5, _t17, _t14);
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
    @Mutated public FloatQuat makeFromMatrix(Float4x4R m) {
        float _r0 = m.m00();
        float _r1 = m.m11();
        float _r2 = m.m21();
        float _r3 = m.m12();
        float _r4 = m.m22();
        float _r5 = m.m01();
        float _r6 = m.m10();
        float _r7 = m.m02();
        float _r8 = m.m20();
        float _t0 = _r0 + _r1;
        float _t10 = _r4 + _t0;
        float _t14 = 1.0f + _t10;
        float _t16 = 1.0f + (_r1 - (_r0 + _r4));
        float _t17 = 1.0f + (_r4 - _t0);
        makeFromMatrix_s4190286d_tail(this, 1.0f + (_r0 - (_r1 + _r4)), _t10, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t14)), _r2 - _r3, _r0, Math.max(_r1, _r4), _r1, _r4, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t16)), _r5 + _r6, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t17)), _r7 + _r8, _r7 - _r8, _t16, _r3 + _r2, _r6 - _r5, _t17, _t14);
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
    public FloatDualQuat toDualQuat(@Mutated FloatDualQuat dest) {
        FloatDualQuatImpl d = (FloatDualQuatImpl) dest;
        d.rX = this.x;
        d.rY = this.y;
        d.rZ = this.z;
        d.rW = this.w;
        d.dX = 0.0f;
        d.dY = 0.0f;
        d.dZ = 0.0f;
        d.dW = 0.0f;
        return d;
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
        DoubleDualQuatImpl d = (DoubleDualQuatImpl) dest;
        d.rX = this.x;
        d.rY = this.y;
        d.rZ = this.z;
        d.rW = this.w;
        d.dX = 0.0f;
        d.dY = 0.0f;
        d.dZ = 0.0f;
        d.dW = 0.0f;
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
    public Float4x4 toMatrix(@Mutated Float4x4 dest) {
        if (!Math.useFma()) return toMatrix_mulAdd(dest);
        Float4x4Impl d = (Float4x4Impl) dest;
        float _t0 = this.z * this.z;
        float _t1 = this.z * this.w;
        float _t2 = this.y * this.w;
        d.m00 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t0), 1.0f);
        d.m10 = 2.0f * java.lang.Math.fma(this.x, this.y, _t1);
        d.m20 = 2.0f * java.lang.Math.fma(this.x, this.z, -_t2);
        d.m30 = 0.0f;
        d.m01 = 2.0f * java.lang.Math.fma(this.x, this.y, -_t1);
        d.m11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t0), 1.0f);
        d.m21 = 2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        d.m31 = 0.0f;
        d.m02 = 2.0f * java.lang.Math.fma(this.x, this.z, _t2);
        d.m12 = 2.0f * java.lang.Math.fma(this.y, this.z, -(this.x * this.w));
        d.m22 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f);
        d.m32 = 0.0f;
        d.m03 = 0.0f;
        d.m13 = 0.0f;
        d.m23 = 0.0f;
        d.m33 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /** {@code toMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float4x4 toMatrix_mulAdd(@Mutated Float4x4 dest) {
        Float4x4Impl d = (Float4x4Impl) dest;
        float _t0 = this.z * this.z;
        float _t1 = this.z * this.w;
        float _t2 = this.y * this.w;
        d.m00 = ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t0))));
        d.m10 = 2.0f * ((this.x) * (this.y) + (_t1));
        d.m20 = 2.0f * ((this.x) * (this.z) - (_t2));
        d.m30 = 0.0f;
        d.m01 = 2.0f * ((this.x) * (this.y) - (_t1));
        d.m11 = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t0))));
        d.m21 = 2.0f * ((this.x) * (this.w) + (this.y * this.z));
        d.m31 = 0.0f;
        d.m02 = 2.0f * ((this.x) * (this.z) + (_t2));
        d.m12 = 2.0f * ((this.y) * (this.z) - (this.x * this.w));
        d.m22 = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y))));
        d.m32 = 0.0f;
        d.m03 = 0.0f;
        d.m13 = 0.0f;
        d.m23 = 0.0f;
        d.m33 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
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
        if (!Math.useFma()) return toMatrix_mulAdd(dest);
        Double4x4Impl d = (Double4x4Impl) dest;
        float _t0 = this.z * this.z;
        float _t1 = this.z * this.w;
        float _t2 = this.y * this.w;
        d.m00 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t0), 1.0f);
        d.m10 = 2.0f * java.lang.Math.fma(this.x, this.y, _t1);
        d.m20 = 2.0f * java.lang.Math.fma(this.x, this.z, -_t2);
        d.m30 = 0.0f;
        d.m01 = 2.0f * java.lang.Math.fma(this.x, this.y, -_t1);
        d.m11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t0), 1.0f);
        d.m21 = 2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        d.m31 = 0.0f;
        d.m02 = 2.0f * java.lang.Math.fma(this.x, this.z, _t2);
        d.m12 = 2.0f * java.lang.Math.fma(this.y, this.z, -(this.x * this.w));
        d.m22 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f);
        d.m32 = 0.0f;
        d.m03 = 0.0f;
        d.m13 = 0.0f;
        d.m23 = 0.0f;
        d.m33 = 1.0f;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /** {@code toMatrix} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double4x4 toMatrix_mulAdd(@Mutated Double4x4 dest) {
        Double4x4Impl d = (Double4x4Impl) dest;
        float _t0 = this.z * this.z;
        float _t1 = this.z * this.w;
        float _t2 = this.y * this.w;
        d.m00 = ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t0))));
        d.m10 = 2.0f * ((this.x) * (this.y) + (_t1));
        d.m20 = 2.0f * ((this.x) * (this.z) - (_t2));
        d.m30 = 0.0f;
        d.m01 = 2.0f * ((this.x) * (this.y) - (_t1));
        d.m11 = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t0))));
        d.m21 = 2.0f * ((this.x) * (this.w) + (this.y * this.z));
        d.m31 = 0.0f;
        d.m02 = 2.0f * ((this.x) * (this.z) + (_t2));
        d.m12 = 2.0f * ((this.y) * (this.z) - (this.x * this.w));
        d.m22 = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y))));
        d.m32 = 0.0f;
        d.m03 = 0.0f;
        d.m13 = 0.0f;
        d.m23 = 0.0f;
        d.m33 = 1.0f;
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
    public Float3x3 toMatrix3x3(@Mutated Float3x3 dest) {
        if (!Math.useFma()) return toMatrix3x3_mulAdd(dest);
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = this.z * this.z;
        float _t1 = this.z * this.w;
        float _t2 = this.y * this.w;
        d.m00 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t0), 1.0f);
        d.m10 = 2.0f * java.lang.Math.fma(this.x, this.y, _t1);
        d.m20 = 2.0f * java.lang.Math.fma(this.x, this.z, -_t2);
        d.m01 = 2.0f * java.lang.Math.fma(this.x, this.y, -_t1);
        d.m11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t0), 1.0f);
        d.m21 = 2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        d.m02 = 2.0f * java.lang.Math.fma(this.x, this.z, _t2);
        d.m12 = 2.0f * java.lang.Math.fma(this.y, this.z, -(this.x * this.w));
        d.m22 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f);
        d.properties = 0;
        return d;
    }

    /** {@code toMatrix3x3} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3x3 toMatrix3x3_mulAdd(@Mutated Float3x3 dest) {
        Float3x3Impl d = (Float3x3Impl) dest;
        float _t0 = this.z * this.z;
        float _t1 = this.z * this.w;
        float _t2 = this.y * this.w;
        d.m00 = ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t0))));
        d.m10 = 2.0f * ((this.x) * (this.y) + (_t1));
        d.m20 = 2.0f * ((this.x) * (this.z) - (_t2));
        d.m01 = 2.0f * ((this.x) * (this.y) - (_t1));
        d.m11 = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t0))));
        d.m21 = 2.0f * ((this.x) * (this.w) + (this.y * this.z));
        d.m02 = 2.0f * ((this.x) * (this.z) + (_t2));
        d.m12 = 2.0f * ((this.y) * (this.z) - (this.x * this.w));
        d.m22 = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y))));
        d.properties = 0;
        return d;
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
        if (!Math.useFma()) return toMatrix3x3_mulAdd(dest);
        Double3x3Impl d = (Double3x3Impl) dest;
        float _t0 = this.z * this.z;
        float _t1 = this.z * this.w;
        float _t2 = this.y * this.w;
        d.m00 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t0), 1.0f);
        d.m10 = 2.0f * java.lang.Math.fma(this.x, this.y, _t1);
        d.m20 = 2.0f * java.lang.Math.fma(this.x, this.z, -_t2);
        d.m01 = 2.0f * java.lang.Math.fma(this.x, this.y, -_t1);
        d.m11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t0), 1.0f);
        d.m21 = 2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        d.m02 = 2.0f * java.lang.Math.fma(this.x, this.z, _t2);
        d.m12 = 2.0f * java.lang.Math.fma(this.y, this.z, -(this.x * this.w));
        d.m22 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f);
        d.properties = 0;
        return d;
    }

    /** {@code toMatrix3x3} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x3 toMatrix3x3_mulAdd(@Mutated Double3x3 dest) {
        Double3x3Impl d = (Double3x3Impl) dest;
        float _t0 = this.z * this.z;
        float _t1 = this.z * this.w;
        float _t2 = this.y * this.w;
        d.m00 = ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t0))));
        d.m10 = 2.0f * ((this.x) * (this.y) + (_t1));
        d.m20 = 2.0f * ((this.x) * (this.z) - (_t2));
        d.m01 = 2.0f * ((this.x) * (this.y) - (_t1));
        d.m11 = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t0))));
        d.m21 = 2.0f * ((this.x) * (this.w) + (this.y * this.z));
        d.m02 = 2.0f * ((this.x) * (this.z) + (_t2));
        d.m12 = 2.0f * ((this.y) * (this.z) - (this.x * this.w));
        d.m22 = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y))));
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
    public Float3x4 toMatrix3x4(@Mutated Float3x4 dest) {
        if (!Math.useFma()) return toMatrix3x4_mulAdd(dest);
        Float3x4Impl d = (Float3x4Impl) dest;
        float _t0 = this.z * this.z;
        float _t1 = this.z * this.w;
        float _t2 = this.y * this.w;
        d.m00 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t0), 1.0f);
        d.m01 = 2.0f * java.lang.Math.fma(this.x, this.y, -_t1);
        d.m02 = 2.0f * java.lang.Math.fma(this.x, this.z, _t2);
        d.m03 = 0.0f;
        d.m10 = 2.0f * java.lang.Math.fma(this.x, this.y, _t1);
        d.m11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t0), 1.0f);
        d.m12 = 2.0f * java.lang.Math.fma(this.y, this.z, -(this.x * this.w));
        d.m13 = 0.0f;
        d.m20 = 2.0f * java.lang.Math.fma(this.x, this.z, -_t2);
        d.m21 = 2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        d.m22 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f);
        d.m23 = 0.0f;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /** {@code toMatrix3x4} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3x4 toMatrix3x4_mulAdd(@Mutated Float3x4 dest) {
        Float3x4Impl d = (Float3x4Impl) dest;
        float _t0 = this.z * this.z;
        float _t1 = this.z * this.w;
        float _t2 = this.y * this.w;
        d.m00 = ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t0))));
        d.m01 = 2.0f * ((this.x) * (this.y) - (_t1));
        d.m02 = 2.0f * ((this.x) * (this.z) + (_t2));
        d.m03 = 0.0f;
        d.m10 = 2.0f * ((this.x) * (this.y) + (_t1));
        d.m11 = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t0))));
        d.m12 = 2.0f * ((this.y) * (this.z) - (this.x * this.w));
        d.m13 = 0.0f;
        d.m20 = 2.0f * ((this.x) * (this.z) - (_t2));
        d.m21 = 2.0f * ((this.x) * (this.w) + (this.y * this.z));
        d.m22 = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y))));
        d.m23 = 0.0f;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
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
        if (!Math.useFma()) return toMatrix3x4_mulAdd(dest);
        Double3x4Impl d = (Double3x4Impl) dest;
        float _t0 = this.z * this.z;
        float _t1 = this.z * this.w;
        float _t2 = this.y * this.w;
        d.m00 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t0), 1.0f);
        d.m01 = 2.0f * java.lang.Math.fma(this.x, this.y, -_t1);
        d.m02 = 2.0f * java.lang.Math.fma(this.x, this.z, _t2);
        d.m03 = 0.0f;
        d.m10 = 2.0f * java.lang.Math.fma(this.x, this.y, _t1);
        d.m11 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t0), 1.0f);
        d.m12 = 2.0f * java.lang.Math.fma(this.y, this.z, -(this.x * this.w));
        d.m13 = 0.0f;
        d.m20 = 2.0f * java.lang.Math.fma(this.x, this.z, -_t2);
        d.m21 = 2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        d.m22 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f);
        d.m23 = 0.0f;
        d.properties = Joml.BIT_ORTHOGONAL;
        return d;
    }

    /** {@code toMatrix3x4} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3x4 toMatrix3x4_mulAdd(@Mutated Double3x4 dest) {
        Double3x4Impl d = (Double3x4Impl) dest;
        float _t0 = this.z * this.z;
        float _t1 = this.z * this.w;
        float _t2 = this.y * this.w;
        d.m00 = ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t0))));
        d.m01 = 2.0f * ((this.x) * (this.y) - (_t1));
        d.m02 = 2.0f * ((this.x) * (this.z) + (_t2));
        d.m03 = 0.0f;
        d.m10 = 2.0f * ((this.x) * (this.y) + (_t1));
        d.m11 = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t0))));
        d.m12 = 2.0f * ((this.y) * (this.z) - (this.x * this.w));
        d.m13 = 0.0f;
        d.m20 = 2.0f * ((this.x) * (this.z) - (_t2));
        d.m21 = 2.0f * ((this.x) * (this.w) + (this.y * this.z));
        d.m22 = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y))));
        d.m23 = 0.0f;
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
    public FloatQuat decomposeSwingTwist(Float3R axis, @Mutated FloatQuat swing, @Mutated FloatQuat twist) {
        return decomposeSwingTwist(axis.x(), axis.y(), axis.z(), swing, twist);
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
    public FloatQuat decomposeSwingTwist(float axisX, float axisY, float axisZ, @Mutated FloatQuat swing, @Mutated FloatQuat twist) {
        FloatQuatImpl d0 = (FloatQuatImpl) swing;
        FloatQuatImpl d1 = (FloatQuatImpl) twist;
        float _t2 = Math.fma(axisZ, this.z, Math.fma(axisX, this.x, axisY * this.y));
        float _t4 = Math.fma(this.w, this.w, _t2 * _t2);
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = this.w * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        float _rd0 = this.x;
        float _rd1 = this.y;
        float _rd2 = this.z;
        d0.x = Math.fma(_rd0, _t11, -this.w * _t12) + Math.fma(_rd2, _t13, -_rd1 * _t14);
        d0.y = Math.fma(_rd0, _t14, -this.w * _t13) + Math.fma(_rd1, _t11, -_rd2 * _t12);
        d0.z = Math.fma(_rd1, _t12, _rd2 * _t11) + Math.fma(-_rd0, _t13, -this.w * _t14);
        d0.w = Math.fma(_rd0, _t12, this.w * _t11) - Math.fma(-_rd2, _t14, -_rd1 * _t13);
        d1.x = _t12;
        d1.y = _t13;
        d1.z = _t14;
        d1.w = _t11;
        return this;
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
        DoubleQuatImpl d0 = (DoubleQuatImpl) swing;
        DoubleQuatImpl d1 = (DoubleQuatImpl) twist;
        float _t2 = Math.fma(axisZ, this.z, Math.fma(axisX, this.x, axisY * this.y));
        float _t4 = Math.fma(this.w, this.w, _t2 * _t2);
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = this.w * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        d0.x = Math.fma(this.x, _t11, -this.w * _t12) + Math.fma(this.z, _t13, -this.y * _t14);
        d0.y = Math.fma(this.x, _t14, -this.w * _t13) + Math.fma(this.y, _t11, -this.z * _t12);
        d0.z = Math.fma(this.y, _t12, this.z * _t11) + Math.fma(-this.x, _t13, -this.w * _t14);
        d0.w = Math.fma(this.x, _t12, this.w * _t11) - Math.fma(-this.z, _t14, -this.y * _t13);
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
    public FloatQuat getSwing(Float3R axis, @Mutated FloatQuat dest) {
        return getSwing(axis.x(), axis.y(), axis.z(), dest);
    }

    /**
     * Extract the swing component of this quaternion: the rotation perpendicular to {@code axis} in
     * the swing-twist decomposition, and store the result in {@code dest}.
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
    public FloatQuat getSwing(float axisX, float axisY, float axisZ, @Mutated FloatQuat dest) {
        if (!Math.useFma()) return getSwing_mulAdd(axisX, axisY, axisZ, dest);
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _t2 = java.lang.Math.fma(axisZ, this.z, java.lang.Math.fma(axisX, this.x, axisY * this.y));
        float _t4 = java.lang.Math.fma(this.w, this.w, _t2 * _t2);
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = this.w * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        float _rd0 = this.x;
        float _rd1 = this.y;
        float _rd2 = this.z;
        d.x = java.lang.Math.fma(_rd0, _t11, -this.w * _t12) + java.lang.Math.fma(_rd2, _t13, -_rd1 * _t14);
        d.y = java.lang.Math.fma(_rd0, _t14, -this.w * _t13) + java.lang.Math.fma(_rd1, _t11, -_rd2 * _t12);
        d.z = java.lang.Math.fma(_rd1, _t12, _rd2 * _t11) + java.lang.Math.fma(-_rd0, _t13, -this.w * _t14);
        d.w = java.lang.Math.fma(_rd0, _t12, this.w * _t11) - java.lang.Math.fma(-_rd2, _t14, -_rd1 * _t13);
        return d;
    }

    /** {@code getSwing} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatQuat getSwing_mulAdd(float axisX, float axisY, float axisZ, @Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _t2 = ((axisZ) * (this.z) + (((axisX) * (this.x) + (axisY * this.y))));
        float _t4 = ((this.w) * (this.w) + (_t2 * _t2));
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = this.w * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        float _rd0 = this.x;
        float _rd1 = this.y;
        float _rd2 = this.z;
        d.x = ((_rd0) * (_t11) - (this.w * _t12)) + ((_rd2) * (_t13) - (_rd1 * _t14));
        d.y = ((_rd0) * (_t14) - (this.w * _t13)) + ((_rd1) * (_t11) - (_rd2 * _t12));
        d.z = ((_rd1) * (_t12) + (_rd2 * _t11)) + ((-_rd0) * (_t13) - (this.w * _t14));
        d.w = ((_rd0) * (_t12) + (this.w * _t11)) - ((-_rd2) * (_t14) - (_rd1 * _t13));
        return d;
    }

    /**
     * Extract the swing component of this quaternion: the rotation perpendicular to ({@code axisX},
     * {@code axisY}, {@code axisZ}) in the swing-twist decomposition, and store the result in
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
        if (!Math.useFma()) return getSwing_mulAdd(axisX, axisY, axisZ, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _t2 = java.lang.Math.fma(axisZ, this.z, java.lang.Math.fma(axisX, this.x, axisY * this.y));
        float _t4 = java.lang.Math.fma(this.w, this.w, _t2 * _t2);
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = this.w * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        d.x = java.lang.Math.fma(this.x, _t11, -this.w * _t12) + java.lang.Math.fma(this.z, _t13, -this.y * _t14);
        d.y = java.lang.Math.fma(this.x, _t14, -this.w * _t13) + java.lang.Math.fma(this.y, _t11, -this.z * _t12);
        d.z = java.lang.Math.fma(this.y, _t12, this.z * _t11) + java.lang.Math.fma(-this.x, _t13, -this.w * _t14);
        d.w = java.lang.Math.fma(this.x, _t12, this.w * _t11) - java.lang.Math.fma(-this.z, _t14, -this.y * _t13);
        return d;
    }

    /** {@code getSwing} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat getSwing_mulAdd(float axisX, float axisY, float axisZ, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _t2 = ((axisZ) * (this.z) + (((axisX) * (this.x) + (axisY * this.y))));
        float _t4 = ((this.w) * (this.w) + (_t2 * _t2));
        float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t7 = _t2 * _t5;
        float _t11, _t12, _t13, _t14;
        if (_t4 > 1.0E-14f) {
            _t11 = this.w * _t5;
            _t12 = axisX * _t7;
            _t13 = axisY * _t7;
            _t14 = axisZ * _t7;
        } else {
            _t11 = 1.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
            _t14 = 0.0f;
        }
        d.x = ((this.x) * (_t11) - (this.w * _t12)) + ((this.z) * (_t13) - (this.y * _t14));
        d.y = ((this.x) * (_t14) - (this.w * _t13)) + ((this.y) * (_t11) - (this.z * _t12));
        d.z = ((this.y) * (_t12) + (this.z * _t11)) + ((-this.x) * (_t13) - (this.w * _t14));
        d.w = ((this.x) * (_t12) + (this.w * _t11)) - ((-this.z) * (_t14) - (this.y * _t13));
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
    public FloatQuat getTwist(Float3R axis, @Mutated FloatQuat dest) {
        return getTwist(axis.x(), axis.y(), axis.z(), dest);
    }

    /**
     * Extract the twist component of this quaternion: the rotation about {@code axis} in the
     * swing-twist decomposition, and store the result in {@code dest}.
     * <p>
     * The twist is the rotation about the axis alone; it is the identity rotation ({@code IDENTITY}
     * or its negation) when the rotation is a pure swing, and {@code IDENTITY} in the 180-degree
     * perpendicular case where the twist angle is undefined.
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
    public FloatQuat getTwist(float axisX, float axisY, float axisZ, @Mutated FloatQuat dest) {
        if (Math.useFma()) {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _t2 = java.lang.Math.fma(axisZ, this.z, java.lang.Math.fma(axisX, this.x, axisY * this.y));
            float _t4 = java.lang.Math.fma(this.w, this.w, _t2 * _t2);
            float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
            float _t6 = _t2 * _t5;
            if (_t4 > 1.0E-14f) {
                d.x = axisX * _t6;
                d.y = axisY * _t6;
                d.z = axisZ * _t6;
                d.w = this.w * _t5;
            } else {
                d.x = 0.0f;
                d.y = 0.0f;
                d.z = 0.0f;
                d.w = 1.0f;
            }
            return d;
        } else {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _t2 = ((axisZ) * (this.z) + (((axisX) * (this.x) + (axisY * this.y))));
            float _t4 = ((this.w) * (this.w) + (_t2 * _t2));
            float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
            float _t6 = _t2 * _t5;
            if (_t4 > 1.0E-14f) {
                d.x = axisX * _t6;
                d.y = axisY * _t6;
                d.z = axisZ * _t6;
                d.w = this.w * _t5;
            } else {
                d.x = 0.0f;
                d.y = 0.0f;
                d.z = 0.0f;
                d.w = 1.0f;
            }
            return d;
        }
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
        if (Math.useFma()) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            float _t2 = java.lang.Math.fma(axisZ, this.z, java.lang.Math.fma(axisX, this.x, axisY * this.y));
            float _t4 = java.lang.Math.fma(this.w, this.w, _t2 * _t2);
            float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
            float _t6 = _t2 * _t5;
            if (_t4 > 1.0E-14f) {
                d.x = axisX * _t6;
                d.y = axisY * _t6;
                d.z = axisZ * _t6;
                d.w = this.w * _t5;
            } else {
                d.x = 0.0f;
                d.y = 0.0f;
                d.z = 0.0f;
                d.w = 1.0f;
            }
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            float _t2 = ((axisZ) * (this.z) + (((axisX) * (this.x) + (axisY * this.y))));
            float _t4 = ((this.w) * (this.w) + (_t2 * _t2));
            float _t5 = (1.0f / (float) java.lang.Math.sqrt(_t4));
            float _t6 = _t2 * _t5;
            if (_t4 > 1.0E-14f) {
                d.x = axisX * _t6;
                d.y = axisY * _t6;
                d.z = axisZ * _t6;
                d.w = this.w * _t5;
            } else {
                d.x = 0.0f;
                d.y = 0.0f;
                d.z = 0.0f;
                d.w = 1.0f;
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
    @Mutated public FloatQuat makeIdentity() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        this.w = 1.0f;
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
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        this.w = 0.0f;
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
     * shows when this component is much larger in magnitude than the other one (1e8 towards 1 ends
     * at 0).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @param other the quaternion to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat lerp(FloatQuatR other, float t, @Mutated FloatQuat dest) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        if (Math.useFma()) {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            d.x = java.lang.Math.fma(t, otherX - this.x, this.x);
            d.y = java.lang.Math.fma(t, otherY - this.y, this.y);
            d.z = java.lang.Math.fma(t, otherZ - this.z, this.z);
            d.w = java.lang.Math.fma(t, otherW - this.w, this.w);
            return d;
        } else {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            d.x = ((t) * (otherX - this.x) + (this.x));
            d.y = ((t) * (otherY - this.y) + (this.y));
            d.z = ((t) * (otherZ - this.z) + (this.z));
            d.w = ((t) * (otherW - this.w) + (this.w));
            return d;
        }
    }

    /**
     * Linearly interpolate between this quaternion and {@code other} using the interpolation factor
     * {@code t} and store the result in {@code dest}.
     * <p>
     * The interpolation starts at this quaternion (interpolation factor {@code 0}) and ends at
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
     * @param other the quaternion to interpolate towards
     * @param t the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat lerp(FloatQuatR other, float t, @Mutated DoubleQuat dest) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
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
     * magnitude than the other one (1e8 towards 1 ends at 0).
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
        if (Math.useFma()) {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            d.x = java.lang.Math.fma(t, otherX - this.x, this.x);
            d.y = java.lang.Math.fma(t, otherY - this.y, this.y);
            d.z = java.lang.Math.fma(t, otherZ - this.z, this.z);
            d.w = java.lang.Math.fma(t, otherW - this.w, this.w);
            return d;
        } else {
            FloatQuatImpl d = (FloatQuatImpl) dest;
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
     * magnitude than the other one (1e8 towards 1 ends at 0).
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
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat nlerp(FloatQuatR target, float alpha, @Mutated FloatQuat dest) {
        float targetX = target.x();
        float targetY = target.y();
        float targetZ = target.z();
        float targetW = target.w();
        if (!Math.useFma()) return nlerp_mulAdd(targetX, targetY, targetZ, targetW, alpha, dest);
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _t4 = java.lang.Math.fma(alpha, targetW - this.w, this.w);
        float _t5 = java.lang.Math.fma(alpha, targetZ - this.z, this.z);
        float _t6 = java.lang.Math.fma(alpha, targetX - this.x, this.x);
        float _t7 = java.lang.Math.fma(alpha, targetY - this.y, this.y);
        float _t11 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, java.lang.Math.fma(_t6, _t6, _t7 * _t7)));
        float _t12 = (1.0f / (float) java.lang.Math.sqrt(_t11));
        if (_t11 != 0.0f) {
            d.x = _t6 * _t12;
            d.y = _t7 * _t12;
            d.z = _t5 * _t12;
            d.w = _t4 * _t12;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
            d.w = 0.0f;
        }
        return d;
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
        float targetX = target.x();
        float targetY = target.y();
        float targetZ = target.z();
        float targetW = target.w();
        if (!Math.useFma()) return nlerp_mulAdd(targetX, targetY, targetZ, targetW, alpha, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _t4 = java.lang.Math.fma(alpha, targetW - this.w, this.w);
        float _t5 = java.lang.Math.fma(alpha, targetZ - this.z, this.z);
        float _t6 = java.lang.Math.fma(alpha, targetX - this.x, this.x);
        float _t7 = java.lang.Math.fma(alpha, targetY - this.y, this.y);
        float _t11 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, java.lang.Math.fma(_t6, _t6, _t7 * _t7)));
        float _t12 = (1.0f / (float) java.lang.Math.sqrt(_t11));
        if (_t11 != 0.0f) {
            d.x = _t6 * _t12;
            d.y = _t7 * _t12;
            d.z = _t5 * _t12;
            d.w = _t4 * _t12;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
            d.w = 0.0f;
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
        if (!Math.useFma()) return nlerp_mulAdd(targetX, targetY, targetZ, targetW, alpha, dest);
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _t4 = java.lang.Math.fma(alpha, targetW - this.w, this.w);
        float _t5 = java.lang.Math.fma(alpha, targetZ - this.z, this.z);
        float _t6 = java.lang.Math.fma(alpha, targetX - this.x, this.x);
        float _t7 = java.lang.Math.fma(alpha, targetY - this.y, this.y);
        float _t11 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, java.lang.Math.fma(_t6, _t6, _t7 * _t7)));
        float _t12 = (1.0f / (float) java.lang.Math.sqrt(_t11));
        if (_t11 != 0.0f) {
            d.x = _t6 * _t12;
            d.y = _t7 * _t12;
            d.z = _t5 * _t12;
            d.w = _t4 * _t12;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
            d.w = 0.0f;
        }
        return d;
    }

    /** {@code nlerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatQuat nlerp_mulAdd(float targetX, float targetY, float targetZ, float targetW, float alpha, @Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _t4 = ((alpha) * (targetW - this.w) + (this.w));
        float _t5 = ((alpha) * (targetZ - this.z) + (this.z));
        float _t6 = ((alpha) * (targetX - this.x) + (this.x));
        float _t7 = ((alpha) * (targetY - this.y) + (this.y));
        float _t11 = ((_t4) * (_t4) + (((_t5) * (_t5) + (((_t6) * (_t6) + (_t7 * _t7))))));
        float _t12 = (1.0f / (float) java.lang.Math.sqrt(_t11));
        if (_t11 != 0.0f) {
            d.x = _t6 * _t12;
            d.y = _t7 * _t12;
            d.z = _t5 * _t12;
            d.w = _t4 * _t12;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
            d.w = 0.0f;
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
        if (!Math.useFma()) return nlerp_mulAdd(targetX, targetY, targetZ, targetW, alpha, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _t4 = java.lang.Math.fma(alpha, targetW - this.w, this.w);
        float _t5 = java.lang.Math.fma(alpha, targetZ - this.z, this.z);
        float _t6 = java.lang.Math.fma(alpha, targetX - this.x, this.x);
        float _t7 = java.lang.Math.fma(alpha, targetY - this.y, this.y);
        float _t11 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, java.lang.Math.fma(_t6, _t6, _t7 * _t7)));
        float _t12 = (1.0f / (float) java.lang.Math.sqrt(_t11));
        if (_t11 != 0.0f) {
            d.x = _t6 * _t12;
            d.y = _t7 * _t12;
            d.z = _t5 * _t12;
            d.w = _t4 * _t12;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
            d.w = 0.0f;
        }
        return d;
    }

    /** {@code nlerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat nlerp_mulAdd(float targetX, float targetY, float targetZ, float targetW, float alpha, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _t4 = ((alpha) * (targetW - this.w) + (this.w));
        float _t5 = ((alpha) * (targetZ - this.z) + (this.z));
        float _t6 = ((alpha) * (targetX - this.x) + (this.x));
        float _t7 = ((alpha) * (targetY - this.y) + (this.y));
        float _t11 = ((_t4) * (_t4) + (((_t5) * (_t5) + (((_t6) * (_t6) + (_t7 * _t7))))));
        float _t12 = (1.0f / (float) java.lang.Math.sqrt(_t11));
        if (_t11 != 0.0f) {
            d.x = _t6 * _t12;
            d.y = _t7 * _t12;
            d.z = _t5 * _t12;
            d.w = _t4 * _t12;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
            d.w = 0.0f;
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
     * {@code target} (interpolation factor {@code 1}), or at its negation - the same rotation -
     * when the two quaternions' dot product is negative.
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
     * {@code 1}), or at its negation - the same rotation - when the two quaternions' dot product is
     * negative.
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
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _t17, _t18, _t19, _t20;
        if (-Math.fma(this.w, targetW, Math.fma(this.z, targetZ, Math.fma(this.x, targetX, this.y * targetY))) > 0.0f) {
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
        float _t24 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        float _t25 = (1.0f / (float) java.lang.Math.sqrt(_t24));
        if (_t24 != 0.0f) {
            d.x = _t19 * _t25;
            d.y = _t20 * _t25;
            d.z = _t18 * _t25;
            d.w = _t17 * _t25;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
            d.w = 0.0f;
        }
        return d;
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
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _t17, _t18, _t19, _t20;
        if (-Math.fma(this.w, targetW, Math.fma(this.z, targetZ, Math.fma(this.x, targetX, this.y * targetY))) > 0.0f) {
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
        float _t24 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        float _t25 = (1.0f / (float) java.lang.Math.sqrt(_t24));
        if (_t24 != 0.0f) {
            d.x = _t19 * _t25;
            d.y = _t20 * _t25;
            d.z = _t18 * _t25;
            d.w = _t17 * _t25;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
            d.w = 0.0f;
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
        if (!Math.useFma()) return slerp_mulAdd(targetX, targetY, targetZ, targetW, alpha, dest);
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _t0 = 1.0f - alpha;
        float _t1 = this.w + targetW;
        float _t2 = this.z + targetZ;
        float _t3 = this.x + targetX;
        float _t4 = this.y + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, _t4 * _t4))));
        float _t12 = quatArcAngle(_t11);
        float _t13 = 4.0f - _t11;
        float _t19 = (float) java.lang.Math.sqrt(_t13 * _t11);
        float _t21 = 2.0f / _t19;
        float _t26, _t27;
        if (_t19 > 2.0E-6f) {
            _t26 = _t21 * Math.sin(alpha * _t12);
            _t27 = _t21 * Math.sin(_t0 * _t12);
        } else {
            if (_t11 > _t13) {
                _t26 = alpha;
                _t27 = _t0;
            } else {
                _t26 = 1.0f - _t5;
                _t27 = _t5;
            }
        }
        d.x = java.lang.Math.fma(this.x, _t27, targetX * _t26);
        d.y = java.lang.Math.fma(this.y, _t27, targetY * _t26);
        d.z = java.lang.Math.fma(this.z, _t27, targetZ * _t26);
        d.w = java.lang.Math.fma(this.w, _t27, targetW * _t26);
        return d;
    }

    /** {@code slerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatQuat slerp_mulAdd(float targetX, float targetY, float targetZ, float targetW, float alpha, @Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _t0 = 1.0f - alpha;
        float _t1 = this.w + targetW;
        float _t2 = this.z + targetZ;
        float _t3 = this.x + targetX;
        float _t4 = this.y + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, ((_t1) * (_t1) + (((_t2) * (_t2) + (((_t3) * (_t3) + (_t4 * _t4)))))));
        float _t12 = quatArcAngle(_t11);
        float _t13 = 4.0f - _t11;
        float _t19 = (float) java.lang.Math.sqrt(_t13 * _t11);
        float _t21 = 2.0f / _t19;
        float _t26, _t27;
        if (_t19 > 2.0E-6f) {
            _t26 = _t21 * Math.sin(alpha * _t12);
            _t27 = _t21 * Math.sin(_t0 * _t12);
        } else {
            if (_t11 > _t13) {
                _t26 = alpha;
                _t27 = _t0;
            } else {
                _t26 = 1.0f - _t5;
                _t27 = _t5;
            }
        }
        d.x = ((this.x) * (_t27) + (targetX * _t26));
        d.y = ((this.y) * (_t27) + (targetY * _t26));
        d.z = ((this.z) * (_t27) + (targetZ * _t26));
        d.w = ((this.w) * (_t27) + (targetW * _t26));
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
        if (!Math.useFma()) return slerp_mulAdd(targetX, targetY, targetZ, targetW, alpha, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _t0 = 1.0f - alpha;
        float _t1 = this.w + targetW;
        float _t2 = this.z + targetZ;
        float _t3 = this.x + targetX;
        float _t4 = this.y + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, _t4 * _t4))));
        float _t12 = quatArcAngle(_t11);
        float _t13 = 4.0f - _t11;
        float _t19 = (float) java.lang.Math.sqrt(_t13 * _t11);
        float _t21 = 2.0f / _t19;
        float _t26, _t27;
        if (_t19 > 2.0E-6f) {
            _t26 = _t21 * Math.sin(alpha * _t12);
            _t27 = _t21 * Math.sin(_t0 * _t12);
        } else {
            if (_t11 > _t13) {
                _t26 = alpha;
                _t27 = _t0;
            } else {
                _t26 = 1.0f - _t5;
                _t27 = _t5;
            }
        }
        d.x = java.lang.Math.fma(this.x, _t27, targetX * _t26);
        d.y = java.lang.Math.fma(this.y, _t27, targetY * _t26);
        d.z = java.lang.Math.fma(this.z, _t27, targetZ * _t26);
        d.w = java.lang.Math.fma(this.w, _t27, targetW * _t26);
        return d;
    }

    /** {@code slerp} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat slerp_mulAdd(float targetX, float targetY, float targetZ, float targetW, float alpha, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _t0 = 1.0f - alpha;
        float _t1 = this.w + targetW;
        float _t2 = this.z + targetZ;
        float _t3 = this.x + targetX;
        float _t4 = this.y + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, ((_t1) * (_t1) + (((_t2) * (_t2) + (((_t3) * (_t3) + (_t4 * _t4)))))));
        float _t12 = quatArcAngle(_t11);
        float _t13 = 4.0f - _t11;
        float _t19 = (float) java.lang.Math.sqrt(_t13 * _t11);
        float _t21 = 2.0f / _t19;
        float _t26, _t27;
        if (_t19 > 2.0E-6f) {
            _t26 = _t21 * Math.sin(alpha * _t12);
            _t27 = _t21 * Math.sin(_t0 * _t12);
        } else {
            if (_t11 > _t13) {
                _t26 = alpha;
                _t27 = _t0;
            } else {
                _t26 = 1.0f - _t5;
                _t27 = _t5;
            }
        }
        d.x = ((this.x) * (_t27) + (targetX * _t26));
        d.y = ((this.y) * (_t27) + (targetY * _t26));
        d.z = ((this.z) * (_t27) + (targetZ * _t26));
        d.w = ((this.w) * (_t27) + (targetW * _t26));
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
     * Valid input: {@code target} must have unit length; this quaternion must have unit length.
     *
     * @param target the target rotation
     * @param alpha the interpolation factor, typically within {@code [0, 1]}
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat slerpShortest(FloatQuatR target, float alpha, @Mutated FloatQuat dest) {
        float targetX = target.x();
        float targetY = target.y();
        float targetZ = target.z();
        float targetW = target.w();
        if (!Math.useFma()) return slerpShortest_mulAdd(targetX, targetY, targetZ, targetW, alpha, dest);
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _r0 = this.w;
        float _r1 = this.z;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t0 = 1.0f - alpha;
        float _t12 = java.lang.Math.fma(_r0, targetW, java.lang.Math.fma(_r1, targetZ, java.lang.Math.fma(_r2, targetX, _r3 * targetY)));
        float _t16 = Math.acos(Math.min(1.0f, java.lang.Math.abs(_t12)));
        float _t17 = Math.sin(_t16);
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
        slerpShortest_s43306241_tail_fma(d, _t17, _r0, Math.sin(_t0 * _t16), Math.sin(alpha * _t16), _t21, 1.0f / _t17, alpha, _t0, _r1, _t22, _r2, _t23, _r3, _t24);
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
        float targetX = target.x();
        float targetY = target.y();
        float targetZ = target.z();
        float targetW = target.w();
        if (!Math.useFma()) return slerpShortest_mulAdd(targetX, targetY, targetZ, targetW, alpha, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _r0 = this.w;
        float _r1 = this.z;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t0 = 1.0f - alpha;
        float _t12 = java.lang.Math.fma(_r0, targetW, java.lang.Math.fma(_r1, targetZ, java.lang.Math.fma(_r2, targetX, _r3 * targetY)));
        float _t16 = Math.acos(Math.min(1.0f, java.lang.Math.abs(_t12)));
        float _t17 = Math.sin(_t16);
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
        slerpShortest_s49327c8_tail_fma(d, _t17, _r0, Math.sin(_t0 * _t16), Math.sin(alpha * _t16), _t21, 1.0f / _t17, alpha, _t0, _r1, _t22, _r2, _t23, _r3, _t24);
        return d;
    }

    /** Private store group 0 of {@code slerpShortest}: computes and stores it; reached only through it. */
    private void slerpShortest_s43306241_c0(FloatQuatImpl _dst, float _t49, float _t50, float _t44, float _t45, float _t43, float _t42) {
        _dst.x = _t49 != 0.0f ? _t50 * _t44 : 0.0f;
        _dst.y = _t49 != 0.0f ? _t50 * _t45 : 0.0f;
        _dst.z = _t49 != 0.0f ? _t50 * _t43 : 0.0f;
        _dst.w = _t49 != 0.0f ? _t50 * _t42 : 0.0f;
    }

    /** Private tail of {@code slerpShortest}; reached only through it. */
    private void slerpShortest_s43306241_tail_fma(FloatQuatImpl _dst, float _t17, float _r0, float _t25, float _t19, float _t21, float _t17_inv, float alpha, float _t0, float _r1, float _t22, float _r2, float _t23, float _r3, float _t24) {
        float _t42, _t43, _t44, _t45;
        if (_t17 > 0.0f) {
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
        float _t49 = java.lang.Math.fma(_t42, _t42, java.lang.Math.fma(_t43, _t43, java.lang.Math.fma(_t44, _t44, _t45 * _t45)));
        slerpShortest_s43306241_c0(_dst, _t49, (1.0f / (float) java.lang.Math.sqrt(_t49)), _t44, _t45, _t43, _t42);
    }

    /** Private tail of {@code slerpShortest}; reached only through it. */
    private void slerpShortest_s43306241_tail_mulAdd(FloatQuatImpl _dst, float _t17, float _r0, float _t25, float _t19, float _t21, float _t17_inv, float alpha, float _t0, float _r1, float _t22, float _r2, float _t23, float _r3, float _t24) {
        float _t42, _t43, _t44, _t45;
        if (_t17 > 0.0f) {
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
        float _t49 = ((_t42) * (_t42) + (((_t43) * (_t43) + (((_t44) * (_t44) + (_t45 * _t45))))));
        slerpShortest_s43306241_c0(_dst, _t49, (1.0f / (float) java.lang.Math.sqrt(_t49)), _t44, _t45, _t43, _t42);
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
        if (!Math.useFma()) return slerpShortest_mulAdd(targetX, targetY, targetZ, targetW, alpha, dest);
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _r0 = this.w;
        float _r1 = this.z;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t0 = 1.0f - alpha;
        float _t12 = java.lang.Math.fma(_r0, targetW, java.lang.Math.fma(_r1, targetZ, java.lang.Math.fma(_r2, targetX, _r3 * targetY)));
        float _t16 = Math.acos(Math.min(1.0f, java.lang.Math.abs(_t12)));
        float _t17 = Math.sin(_t16);
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
        slerpShortest_s43306241_tail_fma(d, _t17, _r0, Math.sin(_t0 * _t16), Math.sin(alpha * _t16), _t21, 1.0f / _t17, alpha, _t0, _r1, _t22, _r2, _t23, _r3, _t24);
        return d;
    }

    /** {@code slerpShortest} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatQuat slerpShortest_mulAdd(float targetX, float targetY, float targetZ, float targetW, float alpha, @Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _r0 = this.w;
        float _r1 = this.z;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t0 = 1.0f - alpha;
        float _t12 = ((_r0) * (targetW) + (((_r1) * (targetZ) + (((_r2) * (targetX) + (_r3 * targetY))))));
        float _t16 = Math.acos(Math.min(1.0f, java.lang.Math.abs(_t12)));
        float _t17 = Math.sin(_t16);
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
        slerpShortest_s43306241_tail_mulAdd(d, _t17, _r0, Math.sin(_t0 * _t16), Math.sin(alpha * _t16), _t21, 1.0f / _t17, alpha, _t0, _r1, _t22, _r2, _t23, _r3, _t24);
        return d;
    }

    /** Private store group 0 of {@code slerpShortest}: computes and stores it; reached only through it. */
    private void slerpShortest_s49327c8_c0(DoubleQuatImpl _dst, float _t49, float _t50, float _t44, float _t45, float _t43, float _t42) {
        _dst.x = _t49 != 0.0f ? _t50 * _t44 : 0.0f;
        _dst.y = _t49 != 0.0f ? _t50 * _t45 : 0.0f;
        _dst.z = _t49 != 0.0f ? _t50 * _t43 : 0.0f;
        _dst.w = _t49 != 0.0f ? _t50 * _t42 : 0.0f;
    }

    /** Private tail of {@code slerpShortest}; reached only through it. */
    private void slerpShortest_s49327c8_tail_fma(DoubleQuatImpl _dst, float _t17, float _r0, float _t25, float _t19, float _t21, float _t17_inv, float alpha, float _t0, float _r1, float _t22, float _r2, float _t23, float _r3, float _t24) {
        float _t42, _t43, _t44, _t45;
        if (_t17 > 0.0f) {
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
        float _t49 = java.lang.Math.fma(_t42, _t42, java.lang.Math.fma(_t43, _t43, java.lang.Math.fma(_t44, _t44, _t45 * _t45)));
        slerpShortest_s49327c8_c0(_dst, _t49, (1.0f / (float) java.lang.Math.sqrt(_t49)), _t44, _t45, _t43, _t42);
    }

    /** Private tail of {@code slerpShortest}; reached only through it. */
    private void slerpShortest_s49327c8_tail_mulAdd(DoubleQuatImpl _dst, float _t17, float _r0, float _t25, float _t19, float _t21, float _t17_inv, float alpha, float _t0, float _r1, float _t22, float _r2, float _t23, float _r3, float _t24) {
        float _t42, _t43, _t44, _t45;
        if (_t17 > 0.0f) {
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
        float _t49 = ((_t42) * (_t42) + (((_t43) * (_t43) + (((_t44) * (_t44) + (_t45 * _t45))))));
        slerpShortest_s49327c8_c0(_dst, _t49, (1.0f / (float) java.lang.Math.sqrt(_t49)), _t44, _t45, _t43, _t42);
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
        if (!Math.useFma()) return slerpShortest_mulAdd(targetX, targetY, targetZ, targetW, alpha, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _r0 = this.w;
        float _r1 = this.z;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t0 = 1.0f - alpha;
        float _t12 = java.lang.Math.fma(_r0, targetW, java.lang.Math.fma(_r1, targetZ, java.lang.Math.fma(_r2, targetX, _r3 * targetY)));
        float _t16 = Math.acos(Math.min(1.0f, java.lang.Math.abs(_t12)));
        float _t17 = Math.sin(_t16);
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
        slerpShortest_s49327c8_tail_fma(d, _t17, _r0, Math.sin(_t0 * _t16), Math.sin(alpha * _t16), _t21, 1.0f / _t17, alpha, _t0, _r1, _t22, _r2, _t23, _r3, _t24);
        return d;
    }

    /** {@code slerpShortest} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat slerpShortest_mulAdd(float targetX, float targetY, float targetZ, float targetW, float alpha, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _r0 = this.w;
        float _r1 = this.z;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t0 = 1.0f - alpha;
        float _t12 = ((_r0) * (targetW) + (((_r1) * (targetZ) + (((_r2) * (targetX) + (_r3 * targetY))))));
        float _t16 = Math.acos(Math.min(1.0f, java.lang.Math.abs(_t12)));
        float _t17 = Math.sin(_t16);
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
        slerpShortest_s49327c8_tail_mulAdd(d, _t17, _r0, Math.sin(_t0 * _t16), Math.sin(alpha * _t16), _t21, 1.0f / _t17, alpha, _t0, _r1, _t22, _r2, _t23, _r3, _t24);
        return d;
    }

    /**
     * Perform spherical quadrangle interpolation (SQUAD) between this quaternion (the start
     * rotation) and the target rotation, shaped by the two inner control quaternions, and store the
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
     * rotation) and the target rotation, shaped by the two inner control quaternions, and store the
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
     * rotation) and the target rotation, shaped by the two inner control quaternions, and store the
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
        if (!Math.useFma()) return squad_mulAdd(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, dest);
        float _r0 = this.w;
        float _r1 = this.z;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t0 = 1.0f - t;
        float _t1 = t + t;
        float _t3 = control0W + control1W;
        float _t4 = control0Z + control1Z;
        float _t5 = control0X + control1X;
        float _t6 = control0Y + control1Y;
        float _t7 = t < 0.5f ? 1.0f : 0.0f;
        float _t8 = _r0 + targetW;
        float _t9 = _r1 + targetZ;
        float _t10 = _r2 + targetX;
        float _t11 = _r3 + targetY;
        float _t13 = _t0 * _t1;
        return squad_s6cc7e980_1_fma(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, (FloatQuatImpl) dest, _r0, _r1, _r2, _r3, _t0, _t7, 1.0f - _t7, _t13, java.lang.Math.fma(-_t0, _t1, 1.0f), _t13 < 0.5f ? 1.0f : 0.0f, Math.min(4.0f, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6)))), Math.min(4.0f, java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11)))));
    }

    /** {@code squad} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatQuat squad_mulAdd(float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t, @Mutated FloatQuat dest) {
        float _r0 = this.w;
        float _r1 = this.z;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t0 = 1.0f - t;
        float _t1 = t + t;
        float _t3 = control0W + control1W;
        float _t4 = control0Z + control1Z;
        float _t5 = control0X + control1X;
        float _t6 = control0Y + control1Y;
        float _t7 = t < 0.5f ? 1.0f : 0.0f;
        float _t8 = _r0 + targetW;
        float _t9 = _r1 + targetZ;
        float _t10 = _r2 + targetX;
        float _t11 = _r3 + targetY;
        float _t13 = _t0 * _t1;
        return squad_s6cc7e980_1_mulAdd(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, (FloatQuatImpl) dest, _r0, _r1, _r2, _r3, _t0, _t7, 1.0f - _t7, _t13, ((1.0f) - (_t0) * (_t1)), _t13 < 0.5f ? 1.0f : 0.0f, Math.min(4.0f, ((_t3) * (_t3) + (((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))))))), Math.min(4.0f, ((_t8) * (_t8) + (((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))))))));
    }

    /** Piece 2 of {@code squad}, split to fit the inline budget; reached only through it. */
    private FloatQuat squad_s6cc7e980_1_fma(float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t, FloatQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t0, float _t7, float _t12, float _t13, float _t14, float _t17, float _t25, float _t26) {
        float _t27 = quatArcAngle(_t25);
        float _t28 = quatArcAngle(_t26);
        float _t29 = 4.0f - _t25;
        float _t30 = 4.0f - _t26;
        float _t41 = (float) java.lang.Math.sqrt(_t29 * _t25);
        float _t43 = (float) java.lang.Math.sqrt(_t30 * _t26);
        float _t45 = 2.0f / _t41;
        float _t46 = 2.0f / _t43;
        float _t55, _t57;
        if (_t41 > 2.0E-6f) {
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
        float _t56, _t58;
        if (_t43 > 2.0E-6f) {
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
        return squad_s6cc7e980_2_fma(d, _t13, _t14, _t17, java.lang.Math.fma(control0X, _t57, control1X * _t55), java.lang.Math.fma(control0W, _t57, control1W * _t55), java.lang.Math.fma(_r0, _t58, targetW * _t56), java.lang.Math.fma(control0Z, _t57, control1Z * _t55), java.lang.Math.fma(_r1, _t58, targetZ * _t56), java.lang.Math.fma(_r2, _t58, targetX * _t56), java.lang.Math.fma(control0Y, _t57, control1Y * _t55), java.lang.Math.fma(_r3, _t58, targetY * _t56));
    }

    /** Piece 2 of {@code squad}, split to fit the inline budget; reached only through it. */
    private FloatQuat squad_s6cc7e980_1_mulAdd(float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t, FloatQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t0, float _t7, float _t12, float _t13, float _t14, float _t17, float _t25, float _t26) {
        float _t27 = quatArcAngle(_t25);
        float _t28 = quatArcAngle(_t26);
        float _t29 = 4.0f - _t25;
        float _t30 = 4.0f - _t26;
        float _t41 = (float) java.lang.Math.sqrt(_t29 * _t25);
        float _t43 = (float) java.lang.Math.sqrt(_t30 * _t26);
        float _t45 = 2.0f / _t41;
        float _t46 = 2.0f / _t43;
        float _t55, _t57;
        if (_t41 > 2.0E-6f) {
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
        float _t56, _t58;
        if (_t43 > 2.0E-6f) {
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
        return squad_s6cc7e980_2_mulAdd(d, _t13, _t14, _t17, ((control0X) * (_t57) + (control1X * _t55)), ((control0W) * (_t57) + (control1W * _t55)), ((_r0) * (_t58) + (targetW * _t56)), ((control0Z) * (_t57) + (control1Z * _t55)), ((_r1) * (_t58) + (targetZ * _t56)), ((_r2) * (_t58) + (targetX * _t56)), ((control0Y) * (_t57) + (control1Y * _t55)), ((_r3) * (_t58) + (targetY * _t56)));
    }

    /** Piece 3 of {@code squad}, split to fit the inline budget; reached only through it. */
    private FloatQuat squad_s6cc7e980_2_fma(FloatQuatImpl d, float _t13, float _t14, float _t17, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t73, float _t74) {
        float _t75 = _t68 + _t69;
        float _t76 = _t70 + _t71;
        float _t77 = _t67 + _t72;
        float _t78 = _t73 + _t74;
        float _t83 = Math.min(4.0f, java.lang.Math.fma(_t75, _t75, java.lang.Math.fma(_t76, _t76, java.lang.Math.fma(_t77, _t77, _t78 * _t78))));
        float _t84 = quatArcAngle(_t83);
        float _t85 = 4.0f - _t83;
        float _t91 = (float) java.lang.Math.sqrt(_t85 * _t83);
        float _t93 = 2.0f / _t91;
        float _t98, _t99;
        if (_t91 > 2.0E-6f) {
            _t98 = _t93 * Math.sin(_t13 * _t84);
            _t99 = _t93 * Math.sin(_t14 * _t84);
        } else {
            if (_t83 > _t85) {
                _t98 = _t13;
                _t99 = _t14;
            } else {
                _t98 = 1.0f - _t17;
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
    private FloatQuat squad_s6cc7e980_2_mulAdd(FloatQuatImpl d, float _t13, float _t14, float _t17, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t73, float _t74) {
        float _t75 = _t68 + _t69;
        float _t76 = _t70 + _t71;
        float _t77 = _t67 + _t72;
        float _t78 = _t73 + _t74;
        float _t83 = Math.min(4.0f, ((_t75) * (_t75) + (((_t76) * (_t76) + (((_t77) * (_t77) + (_t78 * _t78)))))));
        float _t84 = quatArcAngle(_t83);
        float _t85 = 4.0f - _t83;
        float _t91 = (float) java.lang.Math.sqrt(_t85 * _t83);
        float _t93 = 2.0f / _t91;
        float _t98, _t99;
        if (_t91 > 2.0E-6f) {
            _t98 = _t93 * Math.sin(_t13 * _t84);
            _t99 = _t93 * Math.sin(_t14 * _t84);
        } else {
            if (_t83 > _t85) {
                _t98 = _t13;
                _t99 = _t14;
            } else {
                _t98 = 1.0f - _t17;
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
     * Perform spherical quadrangle interpolation (SQUAD) between this quaternion (the start
     * rotation) and the target rotation, shaped by the two inner control quaternions, and store the
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
        if (!Math.useFma()) return squad_mulAdd(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, dest);
        float _r0 = this.w;
        float _r1 = this.z;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t0 = 1.0f - t;
        float _t1 = t + t;
        float _t3 = control0W + control1W;
        float _t4 = control0Z + control1Z;
        float _t5 = control0X + control1X;
        float _t6 = control0Y + control1Y;
        float _t7 = t < 0.5f ? 1.0f : 0.0f;
        float _t8 = _r0 + targetW;
        float _t9 = _r1 + targetZ;
        float _t10 = _r2 + targetX;
        float _t11 = _r3 + targetY;
        float _t13 = _t0 * _t1;
        return squad_s74b465c7_1_fma(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, (DoubleQuatImpl) dest, _r0, _r1, _r2, _r3, _t0, _t7, 1.0f - _t7, _t13, java.lang.Math.fma(-_t0, _t1, 1.0f), _t13 < 0.5f ? 1.0f : 0.0f, Math.min(4.0f, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6)))), Math.min(4.0f, java.lang.Math.fma(_t8, _t8, java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t10, _t10, _t11 * _t11)))));
    }

    /** {@code squad} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat squad_mulAdd(float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t, @Mutated DoubleQuat dest) {
        float _r0 = this.w;
        float _r1 = this.z;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t0 = 1.0f - t;
        float _t1 = t + t;
        float _t3 = control0W + control1W;
        float _t4 = control0Z + control1Z;
        float _t5 = control0X + control1X;
        float _t6 = control0Y + control1Y;
        float _t7 = t < 0.5f ? 1.0f : 0.0f;
        float _t8 = _r0 + targetW;
        float _t9 = _r1 + targetZ;
        float _t10 = _r2 + targetX;
        float _t11 = _r3 + targetY;
        float _t13 = _t0 * _t1;
        return squad_s74b465c7_1_mulAdd(control0X, control0Y, control0Z, control0W, control1X, control1Y, control1Z, control1W, targetX, targetY, targetZ, targetW, t, (DoubleQuatImpl) dest, _r0, _r1, _r2, _r3, _t0, _t7, 1.0f - _t7, _t13, ((1.0f) - (_t0) * (_t1)), _t13 < 0.5f ? 1.0f : 0.0f, Math.min(4.0f, ((_t3) * (_t3) + (((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))))))), Math.min(4.0f, ((_t8) * (_t8) + (((_t9) * (_t9) + (((_t10) * (_t10) + (_t11 * _t11))))))));
    }

    /** Piece 2 of {@code squad}, split to fit the inline budget; reached only through it. */
    private DoubleQuat squad_s74b465c7_1_fma(float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t, DoubleQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t0, float _t7, float _t12, float _t13, float _t14, float _t17, float _t25, float _t26) {
        float _t27 = quatArcAngle(_t25);
        float _t28 = quatArcAngle(_t26);
        float _t29 = 4.0f - _t25;
        float _t30 = 4.0f - _t26;
        float _t41 = (float) java.lang.Math.sqrt(_t29 * _t25);
        float _t43 = (float) java.lang.Math.sqrt(_t30 * _t26);
        float _t45 = 2.0f / _t41;
        float _t46 = 2.0f / _t43;
        float _t55, _t57;
        if (_t41 > 2.0E-6f) {
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
        float _t56, _t58;
        if (_t43 > 2.0E-6f) {
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
        return squad_s74b465c7_2_fma(d, _t13, _t14, _t17, java.lang.Math.fma(control0X, _t57, control1X * _t55), java.lang.Math.fma(control0W, _t57, control1W * _t55), java.lang.Math.fma(_r0, _t58, targetW * _t56), java.lang.Math.fma(control0Z, _t57, control1Z * _t55), java.lang.Math.fma(_r1, _t58, targetZ * _t56), java.lang.Math.fma(_r2, _t58, targetX * _t56), java.lang.Math.fma(control0Y, _t57, control1Y * _t55), java.lang.Math.fma(_r3, _t58, targetY * _t56));
    }

    /** Piece 2 of {@code squad}, split to fit the inline budget; reached only through it. */
    private DoubleQuat squad_s74b465c7_1_mulAdd(float control0X, float control0Y, float control0Z, float control0W, float control1X, float control1Y, float control1Z, float control1W, float targetX, float targetY, float targetZ, float targetW, float t, DoubleQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t0, float _t7, float _t12, float _t13, float _t14, float _t17, float _t25, float _t26) {
        float _t27 = quatArcAngle(_t25);
        float _t28 = quatArcAngle(_t26);
        float _t29 = 4.0f - _t25;
        float _t30 = 4.0f - _t26;
        float _t41 = (float) java.lang.Math.sqrt(_t29 * _t25);
        float _t43 = (float) java.lang.Math.sqrt(_t30 * _t26);
        float _t45 = 2.0f / _t41;
        float _t46 = 2.0f / _t43;
        float _t55, _t57;
        if (_t41 > 2.0E-6f) {
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
        float _t56, _t58;
        if (_t43 > 2.0E-6f) {
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
        return squad_s74b465c7_2_mulAdd(d, _t13, _t14, _t17, ((control0X) * (_t57) + (control1X * _t55)), ((control0W) * (_t57) + (control1W * _t55)), ((_r0) * (_t58) + (targetW * _t56)), ((control0Z) * (_t57) + (control1Z * _t55)), ((_r1) * (_t58) + (targetZ * _t56)), ((_r2) * (_t58) + (targetX * _t56)), ((control0Y) * (_t57) + (control1Y * _t55)), ((_r3) * (_t58) + (targetY * _t56)));
    }

    /** Piece 3 of {@code squad}, split to fit the inline budget; reached only through it. */
    private DoubleQuat squad_s74b465c7_2_fma(DoubleQuatImpl d, float _t13, float _t14, float _t17, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t73, float _t74) {
        float _t75 = _t68 + _t69;
        float _t76 = _t70 + _t71;
        float _t77 = _t67 + _t72;
        float _t78 = _t73 + _t74;
        float _t83 = Math.min(4.0f, java.lang.Math.fma(_t75, _t75, java.lang.Math.fma(_t76, _t76, java.lang.Math.fma(_t77, _t77, _t78 * _t78))));
        float _t84 = quatArcAngle(_t83);
        float _t85 = 4.0f - _t83;
        float _t91 = (float) java.lang.Math.sqrt(_t85 * _t83);
        float _t93 = 2.0f / _t91;
        float _t98, _t99;
        if (_t91 > 2.0E-6f) {
            _t98 = _t93 * Math.sin(_t13 * _t84);
            _t99 = _t93 * Math.sin(_t14 * _t84);
        } else {
            if (_t83 > _t85) {
                _t98 = _t13;
                _t99 = _t14;
            } else {
                _t98 = 1.0f - _t17;
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
    private DoubleQuat squad_s74b465c7_2_mulAdd(DoubleQuatImpl d, float _t13, float _t14, float _t17, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t73, float _t74) {
        float _t75 = _t68 + _t69;
        float _t76 = _t70 + _t71;
        float _t77 = _t67 + _t72;
        float _t78 = _t73 + _t74;
        float _t83 = Math.min(4.0f, ((_t75) * (_t75) + (((_t76) * (_t76) + (((_t77) * (_t77) + (_t78 * _t78)))))));
        float _t84 = quatArcAngle(_t83);
        float _t85 = 4.0f - _t83;
        float _t91 = (float) java.lang.Math.sqrt(_t85 * _t83);
        float _t93 = 2.0f / _t91;
        float _t98, _t99;
        if (_t91 > 2.0E-6f) {
            _t98 = _t93 * Math.sin(_t13 * _t84);
            _t99 = _t93 * Math.sin(_t14 * _t84);
        } else {
            if (_t83 > _t85) {
                _t98 = _t13;
                _t99 = _t14;
            } else {
                _t98 = 1.0f - _t17;
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
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        if (!Math.useFma()) return mul_mulAdd(otherX, otherY, otherZ, otherW, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        d.x = java.lang.Math.fma(otherX, this.w, otherW * this.x) + java.lang.Math.fma(otherZ, this.y, -(otherY * this.z));
        d.y = java.lang.Math.fma(otherX, this.z, otherW * this.y) + java.lang.Math.fma(otherY, this.w, otherZ * -this.x);
        d.z = java.lang.Math.fma(otherY, this.x, otherZ * this.w) + java.lang.Math.fma(otherW, this.z, -(otherX * this.y));
        d.w = java.lang.Math.fma(otherW, this.w, otherX * -this.x) - java.lang.Math.fma(otherY, this.y, otherZ * this.z);
        return d;
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
        if (Math.useFma()) {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _rd0 = this.x;
            float _rd1 = this.y;
            float _rd2 = this.z;
            d.x = java.lang.Math.fma(otherX, this.w, otherW * _rd0) + java.lang.Math.fma(otherZ, _rd1, -(otherY * _rd2));
            d.y = java.lang.Math.fma(otherX, _rd2, otherW * _rd1) + java.lang.Math.fma(otherY, this.w, otherZ * -_rd0);
            d.z = java.lang.Math.fma(otherY, _rd0, otherZ * this.w) + java.lang.Math.fma(otherW, _rd2, -(otherX * _rd1));
            d.w = java.lang.Math.fma(otherW, this.w, otherX * -_rd0) - java.lang.Math.fma(otherY, _rd1, otherZ * _rd2);
            return d;
        } else {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _rd0 = this.x;
            float _rd1 = this.y;
            float _rd2 = this.z;
            d.x = ((otherX) * (this.w) + (otherW * _rd0)) + ((otherZ) * (_rd1) - (otherY * _rd2));
            d.y = ((otherX) * (_rd2) + (otherW * _rd1)) + ((otherY) * (this.w) - (otherZ * _rd0));
            d.z = ((otherY) * (_rd0) + (otherZ * this.w)) + ((otherW) * (_rd2) - (otherX * _rd1));
            d.w = ((otherW) * (this.w) - (otherX * _rd0)) - ((otherY) * (_rd1) + (otherZ * _rd2));
            return d;
        }
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
        if (!Math.useFma()) return mul_mulAdd(otherX, otherY, otherZ, otherW, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        d.x = java.lang.Math.fma(otherX, this.w, otherW * this.x) + java.lang.Math.fma(otherZ, this.y, -(otherY * this.z));
        d.y = java.lang.Math.fma(otherX, this.z, otherW * this.y) + java.lang.Math.fma(otherY, this.w, otherZ * -this.x);
        d.z = java.lang.Math.fma(otherY, this.x, otherZ * this.w) + java.lang.Math.fma(otherW, this.z, -(otherX * this.y));
        d.w = java.lang.Math.fma(otherW, this.w, otherX * -this.x) - java.lang.Math.fma(otherY, this.y, otherZ * this.z);
        return d;
    }

    /** {@code mul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat mul_mulAdd(float otherX, float otherY, float otherZ, float otherW, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        d.x = ((otherX) * (this.w) + (otherW * this.x)) + ((otherZ) * (this.y) - (otherY * this.z));
        d.y = ((otherX) * (this.z) + (otherW * this.y)) + ((otherY) * (this.w) - (otherZ * this.x));
        d.z = ((otherY) * (this.x) + (otherZ * this.w)) + ((otherW) * (this.z) - (otherX * this.y));
        d.w = ((otherW) * (this.w) - (otherX * this.x)) - ((otherY) * (this.y) + (otherZ * this.z));
        return d;
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
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        if (!Math.useFma()) return preMul_mulAdd(otherX, otherY, otherZ, otherW, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        d.x = java.lang.Math.fma(otherX, this.w, otherW * this.x) + java.lang.Math.fma(otherY, this.z, -(otherZ * this.y));
        d.y = java.lang.Math.fma(otherY, this.w, otherZ * this.x) + java.lang.Math.fma(otherW, this.y, -otherX * this.z);
        d.z = java.lang.Math.fma(otherX, this.y, otherW * this.z) + java.lang.Math.fma(otherZ, this.w, -(otherY * this.x));
        d.w = java.lang.Math.fma(otherW, this.w, -otherX * this.x) - java.lang.Math.fma(otherY, this.y, otherZ * this.z);
        return d;
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
        if (Math.useFma()) {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _rd0 = this.x;
            float _rd1 = this.y;
            float _rd2 = this.z;
            d.x = java.lang.Math.fma(otherX, this.w, otherW * _rd0) + java.lang.Math.fma(otherY, _rd2, -(otherZ * _rd1));
            d.y = java.lang.Math.fma(otherY, this.w, otherZ * _rd0) + java.lang.Math.fma(otherW, _rd1, -otherX * _rd2);
            d.z = java.lang.Math.fma(otherX, _rd1, otherW * _rd2) + java.lang.Math.fma(otherZ, this.w, -(otherY * _rd0));
            d.w = java.lang.Math.fma(otherW, this.w, -otherX * _rd0) - java.lang.Math.fma(otherY, _rd1, otherZ * _rd2);
            return d;
        } else {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _rd0 = this.x;
            float _rd1 = this.y;
            float _rd2 = this.z;
            d.x = ((otherX) * (this.w) + (otherW * _rd0)) + ((otherY) * (_rd2) - (otherZ * _rd1));
            d.y = ((otherY) * (this.w) + (otherZ * _rd0)) + ((otherW) * (_rd1) - (otherX * _rd2));
            d.z = ((otherX) * (_rd1) + (otherW * _rd2)) + ((otherZ) * (this.w) - (otherY * _rd0));
            d.w = ((otherW) * (this.w) - (otherX * _rd0)) - ((otherY) * (_rd1) + (otherZ * _rd2));
            return d;
        }
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
        if (!Math.useFma()) return preMul_mulAdd(otherX, otherY, otherZ, otherW, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        d.x = java.lang.Math.fma(otherX, this.w, otherW * this.x) + java.lang.Math.fma(otherY, this.z, -(otherZ * this.y));
        d.y = java.lang.Math.fma(otherY, this.w, otherZ * this.x) + java.lang.Math.fma(otherW, this.y, -otherX * this.z);
        d.z = java.lang.Math.fma(otherX, this.y, otherW * this.z) + java.lang.Math.fma(otherZ, this.w, -(otherY * this.x));
        d.w = java.lang.Math.fma(otherW, this.w, -otherX * this.x) - java.lang.Math.fma(otherY, this.y, otherZ * this.z);
        return d;
    }

    /** {@code preMul} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat preMul_mulAdd(float otherX, float otherY, float otherZ, float otherW, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        d.x = ((otherX) * (this.w) + (otherW * this.x)) + ((otherY) * (this.z) - (otherZ * this.y));
        d.y = ((otherY) * (this.w) + (otherZ * this.x)) + ((otherW) * (this.y) - (otherX * this.z));
        d.z = ((otherX) * (this.y) + (otherW * this.z)) + ((otherZ) * (this.w) - (otherY * this.x));
        d.w = ((otherW) * (this.w) - (otherX * this.x)) - ((otherY) * (this.y) + (otherZ * this.z));
        return d;
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
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        if (Math.useFma()) {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            d.x = java.lang.Math.fma(weight, otherX, this.x);
            d.y = java.lang.Math.fma(weight, otherY, this.y);
            d.z = java.lang.Math.fma(weight, otherZ, this.z);
            d.w = java.lang.Math.fma(weight, otherW, this.w);
            return d;
        } else {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            d.x = ((weight) * (otherX) + (this.x));
            d.y = ((weight) * (otherY) + (this.y));
            d.z = ((weight) * (otherZ) + (this.z));
            d.w = ((weight) * (otherW) + (this.w));
            return d;
        }
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
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
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
    public FloatQuat addScaled(float otherX, float otherY, float otherZ, float otherW, float weight, @Mutated FloatQuat dest) {
        if (Math.useFma()) {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            d.x = java.lang.Math.fma(weight, otherX, this.x);
            d.y = java.lang.Math.fma(weight, otherY, this.y);
            d.z = java.lang.Math.fma(weight, otherZ, this.z);
            d.w = java.lang.Math.fma(weight, otherW, this.w);
            return d;
        } else {
            FloatQuatImpl d = (FloatQuatImpl) dest;
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
     * The angle is computed with {@code atan2}, so it keeps full {@code float} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles).
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the rotation angle in radians of this quaternion, within {@code [0, 2*PI]}
     */
    public float angle() {
        if (Math.useFma()) {
            return 2.0f * Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y))), this.w);
        } else {
            return 2.0f * Math.atan2((float) java.lang.Math.sqrt(((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))))), this.w);
        }
    }

    /**
     * Compute the angle in radians of the rotation that takes this quaternion to {@code other},
     * within {@code [0, PI]} (a quaternion and its negation, the same rotation, give 0).
     * <p>
     * The angle is computed with {@code atan2}, so it keeps full {@code float} resolution all the
     * way down to 0 (an {@code acos}-based form loses precision for small angles).
     * <p>
     * Valid input: this quaternion must have unit length; {@code other} must have unit length.
     *
     * @param other the quaternion to measure the angle to
     * @return the angle in radians of the rotation that takes this quaternion to {@code other},
     *        within {@code [0, PI]} (a quaternion and its negation, the same rotation, give 0)
     */
    public float angleTo(FloatQuatR other) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        if (!Math.useFma()) return angleTo_mulAdd(otherX, otherY, otherZ, otherW);
        float _t9, _t10, _t11, _t12;
        if (-java.lang.Math.fma(otherW, this.w, java.lang.Math.fma(otherZ, this.z, java.lang.Math.fma(otherX, this.x, otherY * this.y))) > 0.0f) {
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
        float _t13 = this.w - _t9;
        float _t14 = this.z - _t10;
        float _t15 = this.x - _t11;
        float _t16 = this.y - _t12;
        float _t17 = this.w + _t9;
        float _t18 = this.z + _t10;
        float _t19 = this.x + _t11;
        float _t20 = this.y + _t12;
        return 4.0f * Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16)))), (float) java.lang.Math.sqrt(java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)))));
    }

    /**
     * Compute the angle in radians of the rotation that takes this quaternion to ({@code otherX},
     * {@code otherY}, {@code otherZ}, {@code otherW}), within {@code [0, PI]} (a quaternion and its
     * negation, the same rotation, give 0).
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
     * @return the angle in radians of the rotation that takes this quaternion to ({@code otherX},
     *        {@code otherY}, {@code otherZ}, {@code otherW}), within {@code [0, PI]} (a quaternion
     *        and its negation, the same rotation, give 0)
     */
    public float angleTo(float otherX, float otherY, float otherZ, float otherW) {
        if (!Math.useFma()) return angleTo_mulAdd(otherX, otherY, otherZ, otherW);
        float _t9, _t10, _t11, _t12;
        if (-java.lang.Math.fma(otherW, this.w, java.lang.Math.fma(otherZ, this.z, java.lang.Math.fma(otherX, this.x, otherY * this.y))) > 0.0f) {
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
        float _t13 = this.w - _t9;
        float _t14 = this.z - _t10;
        float _t15 = this.x - _t11;
        float _t16 = this.y - _t12;
        float _t17 = this.w + _t9;
        float _t18 = this.z + _t10;
        float _t19 = this.x + _t11;
        float _t20 = this.y + _t12;
        return 4.0f * Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, java.lang.Math.fma(_t15, _t15, _t16 * _t16)))), (float) java.lang.Math.sqrt(java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)))));
    }

    /** {@code angleTo} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private float angleTo_mulAdd(float otherX, float otherY, float otherZ, float otherW) {
        float _t9, _t10, _t11, _t12;
        if (-((otherW) * (this.w) + (((otherZ) * (this.z) + (((otherX) * (this.x) + (otherY * this.y)))))) > 0.0f) {
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
        float _t13 = this.w - _t9;
        float _t14 = this.z - _t10;
        float _t15 = this.x - _t11;
        float _t16 = this.y - _t12;
        float _t17 = this.w + _t9;
        float _t18 = this.z + _t10;
        float _t19 = this.x + _t11;
        float _t20 = this.y + _t12;
        return 4.0f * Math.atan2((float) java.lang.Math.sqrt(((_t13) * (_t13) + (((_t14) * (_t14) + (((_t15) * (_t15) + (_t16 * _t16))))))), (float) java.lang.Math.sqrt(((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))))));
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
        if (Math.useFma()) {
            Float3Impl d = (Float3Impl) dest;
            float _t2 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y));
            float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
            if (_t2 > 0.0f) {
                d.x = this.x * _t3;
                d.y = this.y * _t3;
                d.z = this.z * _t3;
            } else {
                d.x = 0.0f;
                d.y = 0.0f;
                d.z = 0.0f;
            }
            return d;
        } else {
            Float3Impl d = (Float3Impl) dest;
            float _t2 = ((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))));
            float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
            if (_t2 > 0.0f) {
                d.x = this.x * _t3;
                d.y = this.y * _t3;
                d.z = this.z * _t3;
            } else {
                d.x = 0.0f;
                d.y = 0.0f;
                d.z = 0.0f;
            }
            return d;
        }
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            float _t2 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y));
            float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
            if (_t2 > 0.0f) {
                d.x = this.x * _t3;
                d.y = this.y * _t3;
                d.z = this.z * _t3;
            } else {
                d.x = 0.0f;
                d.y = 0.0f;
                d.z = 0.0f;
            }
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            float _t2 = ((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))));
            float _t3 = (1.0f / (float) java.lang.Math.sqrt(_t2));
            if (_t2 > 0.0f) {
                d.x = this.x * _t3;
                d.y = this.y * _t3;
                d.z = this.z * _t3;
            } else {
                d.x = 0.0f;
                d.y = 0.0f;
                d.z = 0.0f;
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
    public FloatQuat calculateW(@Mutated FloatQuat dest) {
        if (Math.useFma()) {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _rd0 = this.x;
            float _rd1 = this.y;
            float _rd2 = this.z;
            d.x = _rd0;
            d.y = _rd1;
            d.z = _rd2;
            d.w = (float) java.lang.Math.sqrt(Math.max(0.0f, java.lang.Math.fma(-_rd0, _rd0, java.lang.Math.fma(-_rd1, _rd1, java.lang.Math.fma(-_rd2, _rd2, 1.0f)))));
            return d;
        } else {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _rd0 = this.x;
            float _rd1 = this.y;
            float _rd2 = this.z;
            d.x = _rd0;
            d.y = _rd1;
            d.z = _rd2;
            d.w = (float) java.lang.Math.sqrt(Math.max(0.0f, ((((((1.0f) - (_rd2) * (_rd2))) - (_rd1) * (_rd1))) - (_rd0) * (_rd0))));
            return d;
        }
    }

    /**
     * Recompute the {@code w} component of this quaternion from {@code x}, {@code y} and {@code z},
     * assuming unit length (the positive square root is chosen), and store the result in
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
        if (Math.useFma()) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            d.x = this.x;
            d.y = this.y;
            d.z = this.z;
            d.w = (float) java.lang.Math.sqrt(Math.max(0.0f, java.lang.Math.fma(-this.x, this.x, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(-this.z, this.z, 1.0f)))));
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            d.x = this.x;
            d.y = this.y;
            d.z = this.z;
            d.w = (float) java.lang.Math.sqrt(Math.max(0.0f, ((((((1.0f) - (this.z) * (this.z))) - (this.y) * (this.y))) - (this.x) * (this.x))));
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
    public FloatQuat conjugate(@Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        d.x = -this.x;
        d.y = -this.y;
        d.z = -this.z;
        d.w = this.w;
        return d;
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
    public FloatQuat conjugateBy(FloatQuatR q, @Mutated FloatQuat dest) {
        return conjugateBy(q.x(), q.y(), q.z(), q.w(), dest);
    }

    /**
     * Conjugate this quaternion by {@code q}, i.e. compute {@code q * this * conj(q)} where
     * {@code q} is the given quaternion (equal to {@code q * this * q^-1} when it has unit length),
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
    public FloatQuat conjugateBy(float qX, float qY, float qZ, float qW, @Mutated FloatQuat dest) {
        if (!Math.useFma()) return conjugateBy_mulAdd(qX, qY, qZ, qW, dest);
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _t20 = java.lang.Math.fma(qX, this.y, qW * this.z) + java.lang.Math.fma(qZ, this.w, qY * -this.x);
        float _t21 = java.lang.Math.fma(qY, this.w, qZ * this.x) + java.lang.Math.fma(qW, this.y, -(qX * this.z));
        float _t22 = java.lang.Math.fma(qX, this.w, qW * this.x) + java.lang.Math.fma(qY, this.z, -(qZ * this.y));
        float _t23 = java.lang.Math.fma(qW, this.w, qX * -this.x) - java.lang.Math.fma(qY, this.y, qZ * this.z);
        d.x = java.lang.Math.fma(qY, _t20, -qZ * _t21) + java.lang.Math.fma(qW, _t22, -qX * _t23);
        d.y = java.lang.Math.fma(qZ, _t22, -qY * _t23) + java.lang.Math.fma(qW, _t21, -qX * _t20);
        d.z = java.lang.Math.fma(qX, _t21, qW * _t20) + java.lang.Math.fma(-qY, _t22, -qZ * _t23);
        d.w = java.lang.Math.fma(qX, _t22, qW * _t23) - java.lang.Math.fma(-qZ, _t20, -qY * _t21);
        return d;
    }

    /** {@code conjugateBy} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatQuat conjugateBy_mulAdd(float qX, float qY, float qZ, float qW, @Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _t20 = ((qX) * (this.y) + (qW * this.z)) + ((qZ) * (this.w) - (qY * this.x));
        float _t21 = ((qY) * (this.w) + (qZ * this.x)) + ((qW) * (this.y) - (qX * this.z));
        float _t22 = ((qX) * (this.w) + (qW * this.x)) + ((qY) * (this.z) - (qZ * this.y));
        float _t23 = ((qW) * (this.w) - (qX * this.x)) - ((qY) * (this.y) + (qZ * this.z));
        d.x = ((qY) * (_t20) - (qZ * _t21)) + ((qW) * (_t22) - (qX * _t23));
        d.y = ((qZ) * (_t22) - (qY * _t23)) + ((qW) * (_t21) - (qX * _t20));
        d.z = ((qX) * (_t21) + (qW * _t20)) + ((-qY) * (_t22) - (qZ * _t23));
        d.w = ((qX) * (_t22) + (qW * _t23)) - ((-qZ) * (_t20) - (qY * _t21));
        return d;
    }

    /**
     * Conjugate this quaternion by ({@code qX}, {@code qY}, {@code qZ}, {@code qW}), i.e. compute
     * {@code q * this * conj(q)} where {@code q} is the given quaternion (equal to
     * {@code q * this * q^-1} when it has unit length), and store the result in {@code dest}.
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
        if (!Math.useFma()) return conjugateBy_mulAdd(qX, qY, qZ, qW, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _t20 = java.lang.Math.fma(qX, this.y, qW * this.z) + java.lang.Math.fma(qZ, this.w, qY * -this.x);
        float _t21 = java.lang.Math.fma(qY, this.w, qZ * this.x) + java.lang.Math.fma(qW, this.y, -(qX * this.z));
        float _t22 = java.lang.Math.fma(qX, this.w, qW * this.x) + java.lang.Math.fma(qY, this.z, -(qZ * this.y));
        float _t23 = java.lang.Math.fma(qW, this.w, qX * -this.x) - java.lang.Math.fma(qY, this.y, qZ * this.z);
        d.x = java.lang.Math.fma(qY, _t20, -qZ * _t21) + java.lang.Math.fma(qW, _t22, -qX * _t23);
        d.y = java.lang.Math.fma(qZ, _t22, -qY * _t23) + java.lang.Math.fma(qW, _t21, -qX * _t20);
        d.z = java.lang.Math.fma(qX, _t21, qW * _t20) + java.lang.Math.fma(-qY, _t22, -qZ * _t23);
        d.w = java.lang.Math.fma(qX, _t22, qW * _t23) - java.lang.Math.fma(-qZ, _t20, -qY * _t21);
        return d;
    }

    /** {@code conjugateBy} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat conjugateBy_mulAdd(float qX, float qY, float qZ, float qW, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _t20 = ((qX) * (this.y) + (qW * this.z)) + ((qZ) * (this.w) - (qY * this.x));
        float _t21 = ((qY) * (this.w) + (qZ * this.x)) + ((qW) * (this.y) - (qX * this.z));
        float _t22 = ((qX) * (this.w) + (qW * this.x)) + ((qY) * (this.z) - (qZ * this.y));
        float _t23 = ((qW) * (this.w) - (qX * this.x)) - ((qY) * (this.y) + (qZ * this.z));
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
    public FloatQuat difference(FloatQuatR other, @Mutated FloatQuat dest) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        if (!Math.useFma()) return difference_mulAdd(otherX, otherY, otherZ, otherW, dest);
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _t3_inv = 1.0f / java.lang.Math.fma(this.w, this.w, java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y)));
        float _sp1 = _t3_inv * this.z;
        float _sp0 = this.y * _t3_inv;
        float _rd0 = this.x;
        float _rd1 = this.y;
        d.x = (java.lang.Math.fma(otherX, this.w, -otherW * _rd0) + java.lang.Math.fma(otherY, this.z, -otherZ * _rd1)) * _t3_inv;
        d.y = java.lang.Math.fma(java.lang.Math.fma(otherY, this.w, otherZ * _rd0), _t3_inv, java.lang.Math.fma(-otherX, _sp1, -otherW * _sp0));
        d.z = (java.lang.Math.fma(otherX, _rd1, -otherW * this.z) + java.lang.Math.fma(otherZ, this.w, -otherY * _rd0)) * _t3_inv;
        d.w = java.lang.Math.fma(java.lang.Math.fma(otherX, _rd0, otherW * this.w), _t3_inv, java.lang.Math.fma(otherZ, _sp1, otherY * _sp0));
        return d;
    }

    /**
     * Compute the difference between this quaternion and {@code other}, i.e. the rotation {@code D}
     * with {@code this * D = other}, that is {@code D = this^-1 * other}, and store the result in
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
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
        if (!Math.useFma()) return difference_mulAdd(otherX, otherY, otherZ, otherW, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _t3_inv = 1.0f / java.lang.Math.fma(this.w, this.w, java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y)));
        float _sp1 = _t3_inv * this.z;
        float _sp0 = this.y * _t3_inv;
        d.x = (java.lang.Math.fma(otherX, this.w, -otherW * this.x) + java.lang.Math.fma(otherY, this.z, -otherZ * this.y)) * _t3_inv;
        d.y = java.lang.Math.fma(java.lang.Math.fma(otherY, this.w, otherZ * this.x), _t3_inv, java.lang.Math.fma(-otherX, _sp1, -otherW * _sp0));
        d.z = (java.lang.Math.fma(otherX, this.y, -otherW * this.z) + java.lang.Math.fma(otherZ, this.w, -otherY * this.x)) * _t3_inv;
        d.w = java.lang.Math.fma(java.lang.Math.fma(otherX, this.x, otherW * this.w), _t3_inv, java.lang.Math.fma(otherZ, _sp1, otherY * _sp0));
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
    public FloatQuat difference(float otherX, float otherY, float otherZ, float otherW, @Mutated FloatQuat dest) {
        if (!Math.useFma()) return difference_mulAdd(otherX, otherY, otherZ, otherW, dest);
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _t3_inv = 1.0f / java.lang.Math.fma(this.w, this.w, java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y)));
        float _sp1 = _t3_inv * this.z;
        float _sp0 = this.y * _t3_inv;
        float _rd0 = this.x;
        float _rd1 = this.y;
        d.x = (java.lang.Math.fma(otherX, this.w, -otherW * _rd0) + java.lang.Math.fma(otherY, this.z, -otherZ * _rd1)) * _t3_inv;
        d.y = java.lang.Math.fma(java.lang.Math.fma(otherY, this.w, otherZ * _rd0), _t3_inv, java.lang.Math.fma(-otherX, _sp1, -otherW * _sp0));
        d.z = (java.lang.Math.fma(otherX, _rd1, -otherW * this.z) + java.lang.Math.fma(otherZ, this.w, -otherY * _rd0)) * _t3_inv;
        d.w = java.lang.Math.fma(java.lang.Math.fma(otherX, _rd0, otherW * this.w), _t3_inv, java.lang.Math.fma(otherZ, _sp1, otherY * _sp0));
        return d;
    }

    /** {@code difference} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatQuat difference_mulAdd(float otherX, float otherY, float otherZ, float otherW, @Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _t3_inv = 1.0f / ((this.w) * (this.w) + (((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))))));
        float _sp1 = _t3_inv * this.z;
        float _sp0 = this.y * _t3_inv;
        float _rd0 = this.x;
        float _rd1 = this.y;
        d.x = (((otherX) * (this.w) - (otherW * _rd0)) + ((otherY) * (this.z) - (otherZ * _rd1))) * _t3_inv;
        d.y = ((((otherY) * (this.w) + (otherZ * _rd0))) * (_t3_inv) + (((-otherX) * (_sp1) - (otherW * _sp0))));
        d.z = (((otherX) * (_rd1) - (otherW * this.z)) + ((otherZ) * (this.w) - (otherY * _rd0))) * _t3_inv;
        d.w = ((((otherX) * (_rd0) + (otherW * this.w))) * (_t3_inv) + (((otherZ) * (_sp1) + (otherY * _sp0))));
        return d;
    }

    /**
     * Compute the difference between this quaternion and ({@code otherX}, {@code otherY},
     * {@code otherZ}, {@code otherW}), i.e. the rotation {@code D} with
     * {@code this * D = (otherX, otherY, otherZ, otherW)}, that is
     * {@code D = this^-1 * (otherX, otherY, otherZ, otherW)}, and store the result in {@code dest}.
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
        if (!Math.useFma()) return difference_mulAdd(otherX, otherY, otherZ, otherW, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _t3_inv = 1.0f / java.lang.Math.fma(this.w, this.w, java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y)));
        float _sp1 = _t3_inv * this.z;
        float _sp0 = this.y * _t3_inv;
        d.x = (java.lang.Math.fma(otherX, this.w, -otherW * this.x) + java.lang.Math.fma(otherY, this.z, -otherZ * this.y)) * _t3_inv;
        d.y = java.lang.Math.fma(java.lang.Math.fma(otherY, this.w, otherZ * this.x), _t3_inv, java.lang.Math.fma(-otherX, _sp1, -otherW * _sp0));
        d.z = (java.lang.Math.fma(otherX, this.y, -otherW * this.z) + java.lang.Math.fma(otherZ, this.w, -otherY * this.x)) * _t3_inv;
        d.w = java.lang.Math.fma(java.lang.Math.fma(otherX, this.x, otherW * this.w), _t3_inv, java.lang.Math.fma(otherZ, _sp1, otherY * _sp0));
        return d;
    }

    /** {@code difference} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat difference_mulAdd(float otherX, float otherY, float otherZ, float otherW, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _t3_inv = 1.0f / ((this.w) * (this.w) + (((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))))));
        float _sp1 = _t3_inv * this.z;
        float _sp0 = this.y * _t3_inv;
        d.x = (((otherX) * (this.w) - (otherW * this.x)) + ((otherY) * (this.z) - (otherZ * this.y))) * _t3_inv;
        d.y = ((((otherY) * (this.w) + (otherZ * this.x))) * (_t3_inv) + (((-otherX) * (_sp1) - (otherW * _sp0))));
        d.z = (((otherX) * (this.y) - (otherW * this.z)) + ((otherZ) * (this.w) - (otherY * this.x))) * _t3_inv;
        d.w = ((((otherX) * (this.x) + (otherW * this.w))) * (_t3_inv) + (((otherZ) * (_sp1) + (otherY * _sp0))));
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
    public float dot(FloatQuatR other) {
        float otherX = other.x();
        float otherY = other.y();
        float otherZ = other.z();
        float otherW = other.w();
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
    public float dot(float otherX, float otherY, float otherZ, float otherW) {
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
    public FloatQuat exp(@Mutated FloatQuat dest) {
        if (Math.useFma()) {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _t2 = Math.min(Math.exp(this.w), 3.4028235E38f);
            float _t4 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y));
            float _t5 = (float) java.lang.Math.sqrt(_t4);
            float _t7 = Math.sin(_t5);
            float _t10 = _t2 * (_t7 / _t5);
            if (_t4 > 0.0f) {
                d.x = this.x * _t10;
                d.y = this.y * _t10;
                d.z = this.z * _t10;
            } else {
                d.x = 0.0f;
                d.y = 0.0f;
                d.z = 0.0f;
            }
            d.w = Math.cosFromSin(_t7, _t5) * _t2;
            return d;
        } else {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _t2 = Math.min(Math.exp(this.w), 3.4028235E38f);
            float _t4 = ((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))));
            float _t5 = (float) java.lang.Math.sqrt(_t4);
            float _t7 = Math.sin(_t5);
            float _t10 = _t2 * (_t7 / _t5);
            if (_t4 > 0.0f) {
                d.x = this.x * _t10;
                d.y = this.y * _t10;
                d.z = this.z * _t10;
            } else {
                d.x = 0.0f;
                d.y = 0.0f;
                d.z = 0.0f;
            }
            d.w = Math.cosFromSin(_t7, _t5) * _t2;
            return d;
        }
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
        if (Math.useFma()) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            float _t2 = Math.min(Math.exp(this.w), 3.4028235E38f);
            float _t4 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y));
            float _t5 = (float) java.lang.Math.sqrt(_t4);
            float _t7 = Math.sin(_t5);
            float _t10 = _t2 * (_t7 / _t5);
            if (_t4 > 0.0f) {
                d.x = this.x * _t10;
                d.y = this.y * _t10;
                d.z = this.z * _t10;
            } else {
                d.x = 0.0f;
                d.y = 0.0f;
                d.z = 0.0f;
            }
            d.w = Math.cosFromSin(_t7, _t5) * _t2;
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            float _t2 = Math.min(Math.exp(this.w), 3.4028235E38f);
            float _t4 = ((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))));
            float _t5 = (float) java.lang.Math.sqrt(_t4);
            float _t7 = Math.sin(_t5);
            float _t10 = _t2 * (_t7 / _t5);
            if (_t4 > 0.0f) {
                d.x = this.x * _t10;
                d.y = this.y * _t10;
                d.z = this.z * _t10;
            } else {
                d.x = 0.0f;
                d.y = 0.0f;
                d.z = 0.0f;
            }
            d.w = Math.cosFromSin(_t7, _t5) * _t2;
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesXYZ(@Mutated Float3 dest) {
        if (!Math.useFma()) return getEulerAnglesXYZ_mulAdd(dest);
        Float3Impl d = (Float3Impl) dest;
        float _t1 = this.y * this.z;
        float _t3 = this.z * this.z;
        float _t8 = 2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w);
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.w, -_t1);
        float _t10 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f);
        float _t12 = java.lang.Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < java.lang.Math.fma(_t8, _t8, _t12) * 1.0E-7f) {
            d.x = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.w, _t1), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t3), 1.0f));
            d.z = 0.0f;
        } else {
            d.x = Math.atan2(_t9, _t10);
            d.z = Math.atan2(2.0f * java.lang.Math.fma(this.z, this.w, -(this.x * this.y)), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t3), 1.0f));
        }
        d.y = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t12));
        return d;
    }

    /** {@code getEulerAnglesXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesXYZ_mulAdd(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t1 = this.y * this.z;
        float _t3 = this.z * this.z;
        float _t8 = 2.0f * ((this.x) * (this.z) + (this.y * this.w));
        float _t9 = 2.0f * ((this.x) * (this.w) - (_t1));
        float _t10 = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y))));
        float _t12 = ((_t10) * (_t10) + (_t9 * _t9));
        if (_t12 < ((_t8) * (_t8) + (_t12)) * 1.0E-7f) {
            d.x = Math.atan2(2.0f * ((this.x) * (this.w) + (_t1)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t3)))));
            d.z = 0.0f;
        } else {
            d.x = Math.atan2(_t9, _t10);
            d.z = Math.atan2(2.0f * ((this.z) * (this.w) - (this.x * this.y)), ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t3)))));
        }
        d.y = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t12));
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
        if (!Math.useFma()) return getEulerAnglesXYZ_mulAdd(dest);
        Double3Impl d = (Double3Impl) dest;
        float _t1 = this.y * this.z;
        float _t3 = this.z * this.z;
        float _t8 = 2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w);
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.w, -_t1);
        float _t10 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f);
        float _t12 = java.lang.Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < java.lang.Math.fma(_t8, _t8, _t12) * 1.0E-7f) {
            d.x = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.w, _t1), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t3), 1.0f));
            d.z = 0.0f;
        } else {
            d.x = Math.atan2(_t9, _t10);
            d.z = Math.atan2(2.0f * java.lang.Math.fma(this.z, this.w, -(this.x * this.y)), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t3), 1.0f));
        }
        d.y = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t12));
        return d;
    }

    /** {@code getEulerAnglesXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXYZ_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t1 = this.y * this.z;
        float _t3 = this.z * this.z;
        float _t8 = 2.0f * ((this.x) * (this.z) + (this.y * this.w));
        float _t9 = 2.0f * ((this.x) * (this.w) - (_t1));
        float _t10 = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y))));
        float _t12 = ((_t10) * (_t10) + (_t9 * _t9));
        if (_t12 < ((_t8) * (_t8) + (_t12)) * 1.0E-7f) {
            d.x = Math.atan2(2.0f * ((this.x) * (this.w) + (_t1)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t3)))));
            d.z = 0.0f;
        } else {
            d.x = Math.atan2(_t9, _t10);
            d.z = Math.atan2(2.0f * ((this.z) * (this.w) - (this.x * this.y)), ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t3)))));
        }
        d.y = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t12));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this quaternion must have unit length; {@code epsilon} must not be negative.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is below {@code epsilon} times the sum of the squares it is
     *        formed from (the first angle then carries the coupled rotation and the last is 0); the
     *        overload without it uses {@code 1e-7}
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesXYZ(float epsilon, @Mutated Float3 dest) {
        if (!Math.useFma()) return getEulerAnglesXYZ_mulAdd(epsilon, dest);
        Float3Impl d = (Float3Impl) dest;
        float _t1 = this.y * this.z;
        float _t3 = this.z * this.z;
        float _t8 = 2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w);
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.w, -_t1);
        float _t10 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f);
        float _t12 = java.lang.Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < epsilon * java.lang.Math.fma(_t8, _t8, _t12)) {
            d.x = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.w, _t1), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t3), 1.0f));
            d.z = 0.0f;
        } else {
            d.x = Math.atan2(_t9, _t10);
            d.z = Math.atan2(2.0f * java.lang.Math.fma(this.z, this.w, -(this.x * this.y)), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t3), 1.0f));
        }
        d.y = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t12));
        return d;
    }

    /** {@code getEulerAnglesXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesXYZ_mulAdd(float epsilon, @Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t1 = this.y * this.z;
        float _t3 = this.z * this.z;
        float _t8 = 2.0f * ((this.x) * (this.z) + (this.y * this.w));
        float _t9 = 2.0f * ((this.x) * (this.w) - (_t1));
        float _t10 = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y))));
        float _t12 = ((_t10) * (_t10) + (_t9 * _t9));
        if (_t12 < epsilon * ((_t8) * (_t8) + (_t12))) {
            d.x = Math.atan2(2.0f * ((this.x) * (this.w) + (_t1)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t3)))));
            d.z = 0.0f;
        } else {
            d.x = Math.atan2(_t9, _t10);
            d.z = Math.atan2(2.0f * ((this.z) * (this.w) - (this.x * this.y)), ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t3)))));
        }
        d.y = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t12));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length; {@code epsilon} must not be negative.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is below {@code epsilon} times the sum of the squares it is
     *        formed from (the first angle then carries the coupled rotation and the last is 0); the
     *        overload without it uses {@code 1e-7}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesXYZ(float epsilon, @Mutated Double3 dest) {
        if (!Math.useFma()) return getEulerAnglesXYZ_mulAdd(epsilon, dest);
        Double3Impl d = (Double3Impl) dest;
        float _t1 = this.y * this.z;
        float _t3 = this.z * this.z;
        float _t8 = 2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w);
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.w, -_t1);
        float _t10 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f);
        float _t12 = java.lang.Math.fma(_t10, _t10, _t9 * _t9);
        if (_t12 < epsilon * java.lang.Math.fma(_t8, _t8, _t12)) {
            d.x = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.w, _t1), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t3), 1.0f));
            d.z = 0.0f;
        } else {
            d.x = Math.atan2(_t9, _t10);
            d.z = Math.atan2(2.0f * java.lang.Math.fma(this.z, this.w, -(this.x * this.y)), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t3), 1.0f));
        }
        d.y = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t12));
        return d;
    }

    /** {@code getEulerAnglesXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXYZ_mulAdd(float epsilon, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t1 = this.y * this.z;
        float _t3 = this.z * this.z;
        float _t8 = 2.0f * ((this.x) * (this.z) + (this.y * this.w));
        float _t9 = 2.0f * ((this.x) * (this.w) - (_t1));
        float _t10 = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y))));
        float _t12 = ((_t10) * (_t10) + (_t9 * _t9));
        if (_t12 < epsilon * ((_t8) * (_t8) + (_t12))) {
            d.x = Math.atan2(2.0f * ((this.x) * (this.w) + (_t1)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t3)))));
            d.z = 0.0f;
        } else {
            d.x = Math.atan2(_t9, _t10);
            d.z = Math.atan2(2.0f * ((this.z) * (this.w) - (this.x * this.y)), ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t3)))));
        }
        d.y = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t12));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesXZY(@Mutated Float3 dest) {
        if (!Math.useFma()) return getEulerAnglesXZY_mulAdd(dest);
        Float3Impl d = (Float3Impl) dest;
        float _t0 = this.z * this.z;
        float _t1 = this.y * this.z;
        float _t7 = 2.0f * java.lang.Math.fma(this.x, this.w, _t1);
        float _t8 = 2.0f * java.lang.Math.fma(this.z, this.w, -(this.x * this.y));
        float _t9 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t0), 1.0f);
        float _t11 = java.lang.Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < java.lang.Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            d.x = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.w, -_t1), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f));
            d.y = 0.0f;
        } else {
            d.x = Math.atan2(_t7, _t9);
            d.y = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t0), 1.0f));
        }
        d.z = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11));
        return d;
    }

    /** {@code getEulerAnglesXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesXZY_mulAdd(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t0 = this.z * this.z;
        float _t1 = this.y * this.z;
        float _t7 = 2.0f * ((this.x) * (this.w) + (_t1));
        float _t8 = 2.0f * ((this.z) * (this.w) - (this.x * this.y));
        float _t9 = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t0))));
        float _t11 = ((_t9) * (_t9) + (_t7 * _t7));
        if (_t11 < ((_t8) * (_t8) + (_t11)) * 1.0E-7f) {
            d.x = Math.atan2(2.0f * ((this.x) * (this.w) - (_t1)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y)))));
            d.y = 0.0f;
        } else {
            d.x = Math.atan2(_t7, _t9);
            d.y = Math.atan2(2.0f * ((this.x) * (this.z) + (this.y * this.w)), ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t0)))));
        }
        d.z = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11));
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
        if (!Math.useFma()) return getEulerAnglesXZY_mulAdd(dest);
        Double3Impl d = (Double3Impl) dest;
        float _t0 = this.z * this.z;
        float _t1 = this.y * this.z;
        float _t7 = 2.0f * java.lang.Math.fma(this.x, this.w, _t1);
        float _t8 = 2.0f * java.lang.Math.fma(this.z, this.w, -(this.x * this.y));
        float _t9 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t0), 1.0f);
        float _t11 = java.lang.Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < java.lang.Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            d.x = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.w, -_t1), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f));
            d.y = 0.0f;
        } else {
            d.x = Math.atan2(_t7, _t9);
            d.y = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t0), 1.0f));
        }
        d.z = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11));
        return d;
    }

    /** {@code getEulerAnglesXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXZY_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t0 = this.z * this.z;
        float _t1 = this.y * this.z;
        float _t7 = 2.0f * ((this.x) * (this.w) + (_t1));
        float _t8 = 2.0f * ((this.z) * (this.w) - (this.x * this.y));
        float _t9 = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t0))));
        float _t11 = ((_t9) * (_t9) + (_t7 * _t7));
        if (_t11 < ((_t8) * (_t8) + (_t11)) * 1.0E-7f) {
            d.x = Math.atan2(2.0f * ((this.x) * (this.w) - (_t1)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y)))));
            d.y = 0.0f;
        } else {
            d.x = Math.atan2(_t7, _t9);
            d.y = Math.atan2(2.0f * ((this.x) * (this.z) + (this.y * this.w)), ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t0)))));
        }
        d.z = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this quaternion must have unit length; {@code epsilon} must not be negative.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is below {@code epsilon} times the sum of the squares it is
     *        formed from (the first angle then carries the coupled rotation and the last is 0); the
     *        overload without it uses {@code 1e-7}
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesXZY(float epsilon, @Mutated Float3 dest) {
        if (!Math.useFma()) return getEulerAnglesXZY_mulAdd(epsilon, dest);
        Float3Impl d = (Float3Impl) dest;
        float _t0 = this.z * this.z;
        float _t1 = this.y * this.z;
        float _t7 = 2.0f * java.lang.Math.fma(this.x, this.w, _t1);
        float _t8 = 2.0f * java.lang.Math.fma(this.z, this.w, -(this.x * this.y));
        float _t9 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t0), 1.0f);
        float _t11 = java.lang.Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < epsilon * java.lang.Math.fma(_t8, _t8, _t11)) {
            d.x = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.w, -_t1), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f));
            d.y = 0.0f;
        } else {
            d.x = Math.atan2(_t7, _t9);
            d.y = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t0), 1.0f));
        }
        d.z = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11));
        return d;
    }

    /** {@code getEulerAnglesXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesXZY_mulAdd(float epsilon, @Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t0 = this.z * this.z;
        float _t1 = this.y * this.z;
        float _t7 = 2.0f * ((this.x) * (this.w) + (_t1));
        float _t8 = 2.0f * ((this.z) * (this.w) - (this.x * this.y));
        float _t9 = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t0))));
        float _t11 = ((_t9) * (_t9) + (_t7 * _t7));
        if (_t11 < epsilon * ((_t8) * (_t8) + (_t11))) {
            d.x = Math.atan2(2.0f * ((this.x) * (this.w) - (_t1)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y)))));
            d.y = 0.0f;
        } else {
            d.x = Math.atan2(_t7, _t9);
            d.y = Math.atan2(2.0f * ((this.x) * (this.z) + (this.y * this.w)), ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t0)))));
        }
        d.z = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length; {@code epsilon} must not be negative.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is below {@code epsilon} times the sum of the squares it is
     *        formed from (the first angle then carries the coupled rotation and the last is 0); the
     *        overload without it uses {@code 1e-7}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesXZY(float epsilon, @Mutated Double3 dest) {
        if (!Math.useFma()) return getEulerAnglesXZY_mulAdd(epsilon, dest);
        Double3Impl d = (Double3Impl) dest;
        float _t0 = this.z * this.z;
        float _t1 = this.y * this.z;
        float _t7 = 2.0f * java.lang.Math.fma(this.x, this.w, _t1);
        float _t8 = 2.0f * java.lang.Math.fma(this.z, this.w, -(this.x * this.y));
        float _t9 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t0), 1.0f);
        float _t11 = java.lang.Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < epsilon * java.lang.Math.fma(_t8, _t8, _t11)) {
            d.x = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.w, -_t1), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f));
            d.y = 0.0f;
        } else {
            d.x = Math.atan2(_t7, _t9);
            d.y = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t0), 1.0f));
        }
        d.z = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11));
        return d;
    }

    /** {@code getEulerAnglesXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesXZY_mulAdd(float epsilon, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t0 = this.z * this.z;
        float _t1 = this.y * this.z;
        float _t7 = 2.0f * ((this.x) * (this.w) + (_t1));
        float _t8 = 2.0f * ((this.z) * (this.w) - (this.x * this.y));
        float _t9 = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t0))));
        float _t11 = ((_t9) * (_t9) + (_t7 * _t7));
        if (_t11 < epsilon * ((_t8) * (_t8) + (_t11))) {
            d.x = Math.atan2(2.0f * ((this.x) * (this.w) - (_t1)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y)))));
            d.y = 0.0f;
        } else {
            d.x = Math.atan2(_t7, _t9);
            d.y = Math.atan2(2.0f * ((this.x) * (this.z) + (this.y * this.w)), ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t0)))));
        }
        d.z = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesYXZ(@Mutated Float3 dest) {
        if (!Math.useFma()) return getEulerAnglesYXZ_mulAdd(dest);
        Float3Impl d = (Float3Impl) dest;
        float _t3 = this.z * this.z;
        float _t8 = 2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w);
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.w, this.y * -this.z);
        float _t10 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f);
        float _t12 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        d.x = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t12));
        if (_t12 < java.lang.Math.fma(_t9, _t9, _t12) * 1.0E-7f) {
            d.y = Math.atan2(2.0f * java.lang.Math.fma(this.y, this.w, this.x * -this.z), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t3), 1.0f));
            d.z = 0.0f;
        } else {
            d.y = Math.atan2(_t8, _t10);
            d.z = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t3), 1.0f));
        }
        return d;
    }

    /** {@code getEulerAnglesYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesYXZ_mulAdd(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t3 = this.z * this.z;
        float _t8 = 2.0f * ((this.x) * (this.z) + (this.y * this.w));
        float _t9 = 2.0f * ((this.x) * (this.w) - (this.y * this.z));
        float _t10 = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y))));
        float _t12 = ((_t10) * (_t10) + (_t8 * _t8));
        d.x = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t12));
        if (_t12 < ((_t9) * (_t9) + (_t12)) * 1.0E-7f) {
            d.y = Math.atan2(2.0f * ((this.y) * (this.w) - (this.x * this.z)), ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t3)))));
            d.z = 0.0f;
        } else {
            d.y = Math.atan2(_t8, _t10);
            d.z = Math.atan2(2.0f * ((this.x) * (this.y) + (this.z * this.w)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t3)))));
        }
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
        if (!Math.useFma()) return getEulerAnglesYXZ_mulAdd(dest);
        Double3Impl d = (Double3Impl) dest;
        float _t3 = this.z * this.z;
        float _t8 = 2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w);
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.w, this.y * -this.z);
        float _t10 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f);
        float _t12 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        d.x = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t12));
        if (_t12 < java.lang.Math.fma(_t9, _t9, _t12) * 1.0E-7f) {
            d.y = Math.atan2(2.0f * java.lang.Math.fma(this.y, this.w, this.x * -this.z), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t3), 1.0f));
            d.z = 0.0f;
        } else {
            d.y = Math.atan2(_t8, _t10);
            d.z = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t3), 1.0f));
        }
        return d;
    }

    /** {@code getEulerAnglesYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYXZ_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t3 = this.z * this.z;
        float _t8 = 2.0f * ((this.x) * (this.z) + (this.y * this.w));
        float _t9 = 2.0f * ((this.x) * (this.w) - (this.y * this.z));
        float _t10 = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y))));
        float _t12 = ((_t10) * (_t10) + (_t8 * _t8));
        d.x = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t12));
        if (_t12 < ((_t9) * (_t9) + (_t12)) * 1.0E-7f) {
            d.y = Math.atan2(2.0f * ((this.y) * (this.w) - (this.x * this.z)), ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t3)))));
            d.z = 0.0f;
        } else {
            d.y = Math.atan2(_t8, _t10);
            d.z = Math.atan2(2.0f * ((this.x) * (this.y) + (this.z * this.w)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t3)))));
        }
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this quaternion must have unit length; {@code epsilon} must not be negative.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is below {@code epsilon} times the sum of the squares it is
     *        formed from (the first angle then carries the coupled rotation and the last is 0); the
     *        overload without it uses {@code 1e-7}
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesYXZ(float epsilon, @Mutated Float3 dest) {
        if (!Math.useFma()) return getEulerAnglesYXZ_mulAdd(epsilon, dest);
        Float3Impl d = (Float3Impl) dest;
        float _t3 = this.z * this.z;
        float _t8 = 2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w);
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.w, this.y * -this.z);
        float _t10 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f);
        float _t12 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        d.x = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t12));
        if (_t12 < epsilon * java.lang.Math.fma(_t9, _t9, _t12)) {
            d.y = Math.atan2(2.0f * java.lang.Math.fma(this.y, this.w, this.x * -this.z), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t3), 1.0f));
            d.z = 0.0f;
        } else {
            d.y = Math.atan2(_t8, _t10);
            d.z = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t3), 1.0f));
        }
        return d;
    }

    /** {@code getEulerAnglesYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesYXZ_mulAdd(float epsilon, @Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t3 = this.z * this.z;
        float _t8 = 2.0f * ((this.x) * (this.z) + (this.y * this.w));
        float _t9 = 2.0f * ((this.x) * (this.w) - (this.y * this.z));
        float _t10 = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y))));
        float _t12 = ((_t10) * (_t10) + (_t8 * _t8));
        d.x = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t12));
        if (_t12 < epsilon * ((_t9) * (_t9) + (_t12))) {
            d.y = Math.atan2(2.0f * ((this.y) * (this.w) - (this.x * this.z)), ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t3)))));
            d.z = 0.0f;
        } else {
            d.y = Math.atan2(_t8, _t10);
            d.z = Math.atan2(2.0f * ((this.x) * (this.y) + (this.z * this.w)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t3)))));
        }
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length; {@code epsilon} must not be negative.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is below {@code epsilon} times the sum of the squares it is
     *        formed from (the first angle then carries the coupled rotation and the last is 0); the
     *        overload without it uses {@code 1e-7}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesYXZ(float epsilon, @Mutated Double3 dest) {
        if (!Math.useFma()) return getEulerAnglesYXZ_mulAdd(epsilon, dest);
        Double3Impl d = (Double3Impl) dest;
        float _t3 = this.z * this.z;
        float _t8 = 2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w);
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.w, this.y * -this.z);
        float _t10 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f);
        float _t12 = java.lang.Math.fma(_t10, _t10, _t8 * _t8);
        d.x = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t12));
        if (_t12 < epsilon * java.lang.Math.fma(_t9, _t9, _t12)) {
            d.y = Math.atan2(2.0f * java.lang.Math.fma(this.y, this.w, this.x * -this.z), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t3), 1.0f));
            d.z = 0.0f;
        } else {
            d.y = Math.atan2(_t8, _t10);
            d.z = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t3), 1.0f));
        }
        return d;
    }

    /** {@code getEulerAnglesYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYXZ_mulAdd(float epsilon, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t3 = this.z * this.z;
        float _t8 = 2.0f * ((this.x) * (this.z) + (this.y * this.w));
        float _t9 = 2.0f * ((this.x) * (this.w) - (this.y * this.z));
        float _t10 = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y))));
        float _t12 = ((_t10) * (_t10) + (_t8 * _t8));
        d.x = Math.atan2(_t9, (float) java.lang.Math.sqrt(_t12));
        if (_t12 < epsilon * ((_t9) * (_t9) + (_t12))) {
            d.y = Math.atan2(2.0f * ((this.y) * (this.w) - (this.x * this.z)), ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t3)))));
            d.z = 0.0f;
        } else {
            d.y = Math.atan2(_t8, _t10);
            d.z = Math.atan2(2.0f * ((this.x) * (this.y) + (this.z * this.w)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t3)))));
        }
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesYZX(@Mutated Float3 dest) {
        if (!Math.useFma()) return getEulerAnglesYZX_mulAdd(dest);
        Float3Impl d = (Float3Impl) dest;
        float _t0 = this.z * this.z;
        float _t7 = 2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w);
        float _t8 = 2.0f * java.lang.Math.fma(this.y, this.w, -(this.x * this.z));
        float _t9 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t0), 1.0f);
        float _t11 = java.lang.Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < java.lang.Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            d.x = 0.0f;
            d.y = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f));
        } else {
            d.x = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.w, -(this.y * this.z)), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t0), 1.0f));
            d.y = Math.atan2(_t8, _t9);
        }
        d.z = Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11));
        return d;
    }

    /** {@code getEulerAnglesYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesYZX_mulAdd(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t0 = this.z * this.z;
        float _t7 = 2.0f * ((this.x) * (this.y) + (this.z * this.w));
        float _t8 = 2.0f * ((this.y) * (this.w) - (this.x * this.z));
        float _t9 = ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t0))));
        float _t11 = ((_t9) * (_t9) + (_t8 * _t8));
        if (_t11 < ((_t7) * (_t7) + (_t11)) * 1.0E-7f) {
            d.x = 0.0f;
            d.y = Math.atan2(2.0f * ((this.x) * (this.z) + (this.y * this.w)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y)))));
        } else {
            d.x = Math.atan2(2.0f * ((this.x) * (this.w) - (this.y * this.z)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t0)))));
            d.y = Math.atan2(_t8, _t9);
        }
        d.z = Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11));
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
        if (!Math.useFma()) return getEulerAnglesYZX_mulAdd(dest);
        Double3Impl d = (Double3Impl) dest;
        float _t0 = this.z * this.z;
        float _t7 = 2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w);
        float _t8 = 2.0f * java.lang.Math.fma(this.y, this.w, -(this.x * this.z));
        float _t9 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t0), 1.0f);
        float _t11 = java.lang.Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < java.lang.Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            d.x = 0.0f;
            d.y = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f));
        } else {
            d.x = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.w, -(this.y * this.z)), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t0), 1.0f));
            d.y = Math.atan2(_t8, _t9);
        }
        d.z = Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11));
        return d;
    }

    /** {@code getEulerAnglesYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYZX_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t0 = this.z * this.z;
        float _t7 = 2.0f * ((this.x) * (this.y) + (this.z * this.w));
        float _t8 = 2.0f * ((this.y) * (this.w) - (this.x * this.z));
        float _t9 = ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t0))));
        float _t11 = ((_t9) * (_t9) + (_t8 * _t8));
        if (_t11 < ((_t7) * (_t7) + (_t11)) * 1.0E-7f) {
            d.x = 0.0f;
            d.y = Math.atan2(2.0f * ((this.x) * (this.z) + (this.y * this.w)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y)))));
        } else {
            d.x = Math.atan2(2.0f * ((this.x) * (this.w) - (this.y * this.z)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t0)))));
            d.y = Math.atan2(_t8, _t9);
        }
        d.z = Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this quaternion must have unit length; {@code epsilon} must not be negative.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is below {@code epsilon} times the sum of the squares it is
     *        formed from (the first angle then carries the coupled rotation and the last is 0); the
     *        overload without it uses {@code 1e-7}
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesYZX(float epsilon, @Mutated Float3 dest) {
        if (!Math.useFma()) return getEulerAnglesYZX_mulAdd(epsilon, dest);
        Float3Impl d = (Float3Impl) dest;
        float _t0 = this.z * this.z;
        float _t7 = 2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w);
        float _t8 = 2.0f * java.lang.Math.fma(this.y, this.w, -(this.x * this.z));
        float _t9 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t0), 1.0f);
        float _t11 = java.lang.Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < epsilon * java.lang.Math.fma(_t7, _t7, _t11)) {
            d.x = 0.0f;
            d.y = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f));
        } else {
            d.x = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.w, -(this.y * this.z)), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t0), 1.0f));
            d.y = Math.atan2(_t8, _t9);
        }
        d.z = Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11));
        return d;
    }

    /** {@code getEulerAnglesYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesYZX_mulAdd(float epsilon, @Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t0 = this.z * this.z;
        float _t7 = 2.0f * ((this.x) * (this.y) + (this.z * this.w));
        float _t8 = 2.0f * ((this.y) * (this.w) - (this.x * this.z));
        float _t9 = ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t0))));
        float _t11 = ((_t9) * (_t9) + (_t8 * _t8));
        if (_t11 < epsilon * ((_t7) * (_t7) + (_t11))) {
            d.x = 0.0f;
            d.y = Math.atan2(2.0f * ((this.x) * (this.z) + (this.y * this.w)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y)))));
        } else {
            d.x = Math.atan2(2.0f * ((this.x) * (this.w) - (this.y * this.z)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t0)))));
            d.y = Math.atan2(_t8, _t9);
        }
        d.z = Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length; {@code epsilon} must not be negative.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is below {@code epsilon} times the sum of the squares it is
     *        formed from (the first angle then carries the coupled rotation and the last is 0); the
     *        overload without it uses {@code 1e-7}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesYZX(float epsilon, @Mutated Double3 dest) {
        if (!Math.useFma()) return getEulerAnglesYZX_mulAdd(epsilon, dest);
        Double3Impl d = (Double3Impl) dest;
        float _t0 = this.z * this.z;
        float _t7 = 2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w);
        float _t8 = 2.0f * java.lang.Math.fma(this.y, this.w, -(this.x * this.z));
        float _t9 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t0), 1.0f);
        float _t11 = java.lang.Math.fma(_t9, _t9, _t8 * _t8);
        if (_t11 < epsilon * java.lang.Math.fma(_t7, _t7, _t11)) {
            d.x = 0.0f;
            d.y = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f));
        } else {
            d.x = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.w, -(this.y * this.z)), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t0), 1.0f));
            d.y = Math.atan2(_t8, _t9);
        }
        d.z = Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11));
        return d;
    }

    /** {@code getEulerAnglesYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesYZX_mulAdd(float epsilon, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t0 = this.z * this.z;
        float _t7 = 2.0f * ((this.x) * (this.y) + (this.z * this.w));
        float _t8 = 2.0f * ((this.y) * (this.w) - (this.x * this.z));
        float _t9 = ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t0))));
        float _t11 = ((_t9) * (_t9) + (_t8 * _t8));
        if (_t11 < epsilon * ((_t7) * (_t7) + (_t11))) {
            d.x = 0.0f;
            d.y = Math.atan2(2.0f * ((this.x) * (this.z) + (this.y * this.w)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y)))));
        } else {
            d.x = Math.atan2(2.0f * ((this.x) * (this.w) - (this.y * this.z)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t0)))));
            d.y = Math.atan2(_t8, _t9);
        }
        d.z = Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesZXY(@Mutated Float3 dest) {
        if (!Math.useFma()) return getEulerAnglesZXY_mulAdd(dest);
        Float3Impl d = (Float3Impl) dest;
        float _t1 = this.z * this.z;
        float _t7 = 2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        float _t8 = 2.0f * java.lang.Math.fma(this.z, this.w, -(this.x * this.y));
        float _t9 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t1), 1.0f);
        float _t11 = java.lang.Math.fma(_t9, _t9, _t8 * _t8);
        d.x = Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11));
        if (_t11 < java.lang.Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            d.y = 0.0f;
            d.z = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t1), 1.0f));
        } else {
            d.y = Math.atan2(2.0f * java.lang.Math.fma(this.y, this.w, -(this.x * this.z)), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f));
            d.z = Math.atan2(_t8, _t9);
        }
        return d;
    }

    /** {@code getEulerAnglesZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesZXY_mulAdd(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t1 = this.z * this.z;
        float _t7 = 2.0f * ((this.x) * (this.w) + (this.y * this.z));
        float _t8 = 2.0f * ((this.z) * (this.w) - (this.x * this.y));
        float _t9 = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t1))));
        float _t11 = ((_t9) * (_t9) + (_t8 * _t8));
        d.x = Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11));
        if (_t11 < ((_t7) * (_t7) + (_t11)) * 1.0E-7f) {
            d.y = 0.0f;
            d.z = Math.atan2(2.0f * ((this.x) * (this.y) + (this.z * this.w)), ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t1)))));
        } else {
            d.y = Math.atan2(2.0f * ((this.y) * (this.w) - (this.x * this.z)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y)))));
            d.z = Math.atan2(_t8, _t9);
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
        if (!Math.useFma()) return getEulerAnglesZXY_mulAdd(dest);
        Double3Impl d = (Double3Impl) dest;
        float _t1 = this.z * this.z;
        float _t7 = 2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        float _t8 = 2.0f * java.lang.Math.fma(this.z, this.w, -(this.x * this.y));
        float _t9 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t1), 1.0f);
        float _t11 = java.lang.Math.fma(_t9, _t9, _t8 * _t8);
        d.x = Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11));
        if (_t11 < java.lang.Math.fma(_t7, _t7, _t11) * 1.0E-7f) {
            d.y = 0.0f;
            d.z = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t1), 1.0f));
        } else {
            d.y = Math.atan2(2.0f * java.lang.Math.fma(this.y, this.w, -(this.x * this.z)), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f));
            d.z = Math.atan2(_t8, _t9);
        }
        return d;
    }

    /** {@code getEulerAnglesZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZXY_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t1 = this.z * this.z;
        float _t7 = 2.0f * ((this.x) * (this.w) + (this.y * this.z));
        float _t8 = 2.0f * ((this.z) * (this.w) - (this.x * this.y));
        float _t9 = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t1))));
        float _t11 = ((_t9) * (_t9) + (_t8 * _t8));
        d.x = Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11));
        if (_t11 < ((_t7) * (_t7) + (_t11)) * 1.0E-7f) {
            d.y = 0.0f;
            d.z = Math.atan2(2.0f * ((this.x) * (this.y) + (this.z * this.w)), ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t1)))));
        } else {
            d.y = Math.atan2(2.0f * ((this.y) * (this.w) - (this.x * this.z)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y)))));
            d.z = Math.atan2(_t8, _t9);
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this quaternion must have unit length; {@code epsilon} must not be negative.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is below {@code epsilon} times the sum of the squares it is
     *        formed from (the first angle then carries the coupled rotation and the last is 0); the
     *        overload without it uses {@code 1e-7}
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesZXY(float epsilon, @Mutated Float3 dest) {
        if (!Math.useFma()) return getEulerAnglesZXY_mulAdd(epsilon, dest);
        Float3Impl d = (Float3Impl) dest;
        float _t1 = this.z * this.z;
        float _t7 = 2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        float _t8 = 2.0f * java.lang.Math.fma(this.z, this.w, -(this.x * this.y));
        float _t9 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t1), 1.0f);
        float _t11 = java.lang.Math.fma(_t9, _t9, _t8 * _t8);
        d.x = Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11));
        if (_t11 < epsilon * java.lang.Math.fma(_t7, _t7, _t11)) {
            d.y = 0.0f;
            d.z = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t1), 1.0f));
        } else {
            d.y = Math.atan2(2.0f * java.lang.Math.fma(this.y, this.w, -(this.x * this.z)), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f));
            d.z = Math.atan2(_t8, _t9);
        }
        return d;
    }

    /** {@code getEulerAnglesZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesZXY_mulAdd(float epsilon, @Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t1 = this.z * this.z;
        float _t7 = 2.0f * ((this.x) * (this.w) + (this.y * this.z));
        float _t8 = 2.0f * ((this.z) * (this.w) - (this.x * this.y));
        float _t9 = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t1))));
        float _t11 = ((_t9) * (_t9) + (_t8 * _t8));
        d.x = Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11));
        if (_t11 < epsilon * ((_t7) * (_t7) + (_t11))) {
            d.y = 0.0f;
            d.z = Math.atan2(2.0f * ((this.x) * (this.y) + (this.z * this.w)), ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t1)))));
        } else {
            d.y = Math.atan2(2.0f * ((this.y) * (this.w) - (this.x * this.z)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y)))));
            d.z = Math.atan2(_t8, _t9);
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length; {@code epsilon} must not be negative.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is below {@code epsilon} times the sum of the squares it is
     *        formed from (the first angle then carries the coupled rotation and the last is 0); the
     *        overload without it uses {@code 1e-7}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesZXY(float epsilon, @Mutated Double3 dest) {
        if (!Math.useFma()) return getEulerAnglesZXY_mulAdd(epsilon, dest);
        Double3Impl d = (Double3Impl) dest;
        float _t1 = this.z * this.z;
        float _t7 = 2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        float _t8 = 2.0f * java.lang.Math.fma(this.z, this.w, -(this.x * this.y));
        float _t9 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t1), 1.0f);
        float _t11 = java.lang.Math.fma(_t9, _t9, _t8 * _t8);
        d.x = Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11));
        if (_t11 < epsilon * java.lang.Math.fma(_t7, _t7, _t11)) {
            d.y = 0.0f;
            d.z = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t1), 1.0f));
        } else {
            d.y = Math.atan2(2.0f * java.lang.Math.fma(this.y, this.w, -(this.x * this.z)), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f));
            d.z = Math.atan2(_t8, _t9);
        }
        return d;
    }

    /** {@code getEulerAnglesZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZXY_mulAdd(float epsilon, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t1 = this.z * this.z;
        float _t7 = 2.0f * ((this.x) * (this.w) + (this.y * this.z));
        float _t8 = 2.0f * ((this.z) * (this.w) - (this.x * this.y));
        float _t9 = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t1))));
        float _t11 = ((_t9) * (_t9) + (_t8 * _t8));
        d.x = Math.atan2(_t7, (float) java.lang.Math.sqrt(_t11));
        if (_t11 < epsilon * ((_t7) * (_t7) + (_t11))) {
            d.y = 0.0f;
            d.z = Math.atan2(2.0f * ((this.x) * (this.y) + (this.z * this.w)), ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t1)))));
        } else {
            d.y = Math.atan2(2.0f * ((this.y) * (this.w) - (this.x * this.z)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y)))));
            d.z = Math.atan2(_t8, _t9);
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this quaternion must have unit length.
     *
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesZYX(@Mutated Float3 dest) {
        if (!Math.useFma()) return getEulerAnglesZYX_mulAdd(dest);
        Float3Impl d = (Float3Impl) dest;
        float _t0 = this.z * this.z;
        float _t7 = 2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w);
        float _t8 = 2.0f * java.lang.Math.fma(this.y, this.w, -this.x * this.z);
        float _t9 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t0), 1.0f);
        float _t11 = java.lang.Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < java.lang.Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            d.x = 0.0f;
            d.z = Math.atan2(2.0f * java.lang.Math.fma(this.z, this.w, -this.x * this.y), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t0), 1.0f));
        } else {
            d.x = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f));
            d.z = Math.atan2(_t7, _t9);
        }
        d.y = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11));
        return d;
    }

    /** {@code getEulerAnglesZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesZYX_mulAdd(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t0 = this.z * this.z;
        float _t7 = 2.0f * ((this.x) * (this.y) + (this.z * this.w));
        float _t8 = 2.0f * ((this.y) * (this.w) - (this.x * this.z));
        float _t9 = ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t0))));
        float _t11 = ((_t9) * (_t9) + (_t7 * _t7));
        if (_t11 < ((_t8) * (_t8) + (_t11)) * 1.0E-7f) {
            d.x = 0.0f;
            d.z = Math.atan2(2.0f * ((this.z) * (this.w) - (this.x * this.y)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t0)))));
        } else {
            d.x = Math.atan2(2.0f * ((this.x) * (this.w) + (this.y * this.z)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y)))));
            d.z = Math.atan2(_t7, _t9);
        }
        d.y = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11));
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
        if (!Math.useFma()) return getEulerAnglesZYX_mulAdd(dest);
        Double3Impl d = (Double3Impl) dest;
        float _t0 = this.z * this.z;
        float _t7 = 2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w);
        float _t8 = 2.0f * java.lang.Math.fma(this.y, this.w, -this.x * this.z);
        float _t9 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t0), 1.0f);
        float _t11 = java.lang.Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < java.lang.Math.fma(_t8, _t8, _t11) * 1.0E-7f) {
            d.x = 0.0f;
            d.z = Math.atan2(2.0f * java.lang.Math.fma(this.z, this.w, -this.x * this.y), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t0), 1.0f));
        } else {
            d.x = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f));
            d.z = Math.atan2(_t7, _t9);
        }
        d.y = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11));
        return d;
    }

    /** {@code getEulerAnglesZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZYX_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t0 = this.z * this.z;
        float _t7 = 2.0f * ((this.x) * (this.y) + (this.z * this.w));
        float _t8 = 2.0f * ((this.y) * (this.w) - (this.x * this.z));
        float _t9 = ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t0))));
        float _t11 = ((_t9) * (_t9) + (_t7 * _t7));
        if (_t11 < ((_t8) * (_t8) + (_t11)) * 1.0E-7f) {
            d.x = 0.0f;
            d.z = Math.atan2(2.0f * ((this.z) * (this.w) - (this.x * this.y)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t0)))));
        } else {
            d.x = Math.atan2(2.0f * ((this.x) * (this.w) + (this.y * this.z)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y)))));
            d.z = Math.atan2(_t7, _t9);
        }
        d.y = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * Valid input: this quaternion must have unit length; {@code epsilon} must not be negative.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is below {@code epsilon} times the sum of the squares it is
     *        formed from (the first angle then carries the coupled rotation and the last is 0); the
     *        overload without it uses {@code 1e-7}
     * @param dest will hold the result
     * @return dest
     */
    public Float3 getEulerAnglesZYX(float epsilon, @Mutated Float3 dest) {
        if (!Math.useFma()) return getEulerAnglesZYX_mulAdd(epsilon, dest);
        Float3Impl d = (Float3Impl) dest;
        float _t0 = this.z * this.z;
        float _t7 = 2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w);
        float _t8 = 2.0f * java.lang.Math.fma(this.y, this.w, -this.x * this.z);
        float _t9 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t0), 1.0f);
        float _t11 = java.lang.Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < epsilon * java.lang.Math.fma(_t8, _t8, _t11)) {
            d.x = 0.0f;
            d.z = Math.atan2(2.0f * java.lang.Math.fma(this.z, this.w, -this.x * this.y), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t0), 1.0f));
        } else {
            d.x = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f));
            d.z = Math.atan2(_t7, _t9);
        }
        d.y = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11));
        return d;
    }

    /** {@code getEulerAnglesZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 getEulerAnglesZYX_mulAdd(float epsilon, @Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t0 = this.z * this.z;
        float _t7 = 2.0f * ((this.x) * (this.y) + (this.z * this.w));
        float _t8 = 2.0f * ((this.y) * (this.w) - (this.x * this.z));
        float _t9 = ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t0))));
        float _t11 = ((_t9) * (_t9) + (_t7 * _t7));
        if (_t11 < epsilon * ((_t8) * (_t8) + (_t11))) {
            d.x = 0.0f;
            d.z = Math.atan2(2.0f * ((this.z) * (this.w) - (this.x * this.y)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t0)))));
        } else {
            d.x = Math.atan2(2.0f * ((this.x) * (this.w) + (this.y * this.z)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y)))));
            d.z = Math.atan2(_t7, _t9);
        }
        d.y = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11));
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
     * {@code float} resolution over its whole range, down to 0.
     * <p>
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: this quaternion must have unit length; {@code epsilon} must not be negative.
     *
     * @param epsilon the gimbal-lock tolerance: the rotation counts as locked when the squared
     *        cosine of the middle angle is below {@code epsilon} times the sum of the squares it is
     *        formed from (the first angle then carries the coupled rotation and the last is 0); the
     *        overload without it uses {@code 1e-7}
     * @param dest will hold the result
     * @return dest
     */
    public Double3 getEulerAnglesZYX(float epsilon, @Mutated Double3 dest) {
        if (!Math.useFma()) return getEulerAnglesZYX_mulAdd(epsilon, dest);
        Double3Impl d = (Double3Impl) dest;
        float _t0 = this.z * this.z;
        float _t7 = 2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w);
        float _t8 = 2.0f * java.lang.Math.fma(this.y, this.w, -this.x * this.z);
        float _t9 = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, _t0), 1.0f);
        float _t11 = java.lang.Math.fma(_t9, _t9, _t7 * _t7);
        if (_t11 < epsilon * java.lang.Math.fma(_t8, _t8, _t11)) {
            d.x = 0.0f;
            d.z = Math.atan2(2.0f * java.lang.Math.fma(this.z, this.w, -this.x * this.y), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, _t0), 1.0f));
        } else {
            d.x = Math.atan2(2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z), java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f));
            d.z = Math.atan2(_t7, _t9);
        }
        d.y = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11));
        return d;
    }

    /** {@code getEulerAnglesZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 getEulerAnglesZYX_mulAdd(float epsilon, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t0 = this.z * this.z;
        float _t7 = 2.0f * ((this.x) * (this.y) + (this.z * this.w));
        float _t8 = 2.0f * ((this.y) * (this.w) - (this.x * this.z));
        float _t9 = ((1.0f) - (2.0f) * (((this.y) * (this.y) + (_t0))));
        float _t11 = ((_t9) * (_t9) + (_t7 * _t7));
        if (_t11 < epsilon * ((_t8) * (_t8) + (_t11))) {
            d.x = 0.0f;
            d.z = Math.atan2(2.0f * ((this.z) * (this.w) - (this.x * this.y)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (_t0)))));
        } else {
            d.x = Math.atan2(2.0f * ((this.x) * (this.w) + (this.y * this.z)), ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y)))));
            d.z = Math.atan2(_t7, _t9);
        }
        d.y = Math.atan2(_t8, (float) java.lang.Math.sqrt(_t11));
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
    public FloatQuat integrate(Float3R angularVel, float dt, @Mutated FloatQuat dest) {
        return integrate(angularVel.x(), angularVel.y(), angularVel.z(), dt, dest);
    }

    /**
     * Integrate the given angular velocity over the given time step and pre-multiply the resulting
     * rotation onto this quaternion ({@code dq * this}: the angular velocity is given in the
     * reference frame, not in this quaternion's local one) and store the result in {@code dest}.
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
    public FloatQuat integrate(float angularVelX, float angularVelY, float angularVelZ, float dt, @Mutated FloatQuat dest) {
        if (!Math.useFma()) return integrate_mulAdd(angularVelX, angularVelY, angularVelZ, dt, dest);
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _t0 = 0.5f * dt;
        float _t1 = angularVelZ * _t0;
        float _t2 = angularVelX * _t0;
        float _t3 = angularVelY * _t0;
        float _t6 = java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3));
        float _t7 = (float) java.lang.Math.sqrt(_t6);
        float _t9 = Math.sin(_t7);
        float _t10 = Math.cosFromSin(_t9, _t7);
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
        float _rd0 = this.x;
        float _rd1 = this.y;
        float _rd2 = this.z;
        d.x = java.lang.Math.fma(_rd0, _t10, this.w * _t15) + java.lang.Math.fma(_rd2, _t16, -(_rd1 * _t17));
        d.y = java.lang.Math.fma(_rd0, _t17, this.w * _t16) + java.lang.Math.fma(_rd1, _t10, -(_rd2 * _t15));
        d.z = java.lang.Math.fma(_rd1, _t15, _rd2 * _t10) + java.lang.Math.fma(this.w, _t17, -_rd0 * _t16);
        d.w = java.lang.Math.fma(this.w, _t10, -_rd0 * _t15) - java.lang.Math.fma(_rd1, _t16, _rd2 * _t17);
        return d;
    }

    /** {@code integrate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatQuat integrate_mulAdd(float angularVelX, float angularVelY, float angularVelZ, float dt, @Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _t0 = 0.5f * dt;
        float _t1 = angularVelZ * _t0;
        float _t2 = angularVelX * _t0;
        float _t3 = angularVelY * _t0;
        float _t6 = ((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3))));
        float _t7 = (float) java.lang.Math.sqrt(_t6);
        float _t9 = Math.sin(_t7);
        float _t10 = Math.cosFromSin(_t9, _t7);
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
        float _rd0 = this.x;
        float _rd1 = this.y;
        float _rd2 = this.z;
        d.x = ((_rd0) * (_t10) + (this.w * _t15)) + ((_rd2) * (_t16) - (_rd1 * _t17));
        d.y = ((_rd0) * (_t17) + (this.w * _t16)) + ((_rd1) * (_t10) - (_rd2 * _t15));
        d.z = ((_rd1) * (_t15) + (_rd2 * _t10)) + ((this.w) * (_t17) - (_rd0 * _t16));
        d.w = ((this.w) * (_t10) - (_rd0 * _t15)) - ((_rd1) * (_t16) + (_rd2 * _t17));
        return d;
    }

    /**
     * Integrate the given angular velocity over the given time step and pre-multiply the resulting
     * rotation onto this quaternion ({@code dq * this}: the angular velocity is given in the
     * reference frame, not in this quaternion's local one) and store the result in {@code dest}.
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
        if (!Math.useFma()) return integrate_mulAdd(angularVelX, angularVelY, angularVelZ, dt, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _t0 = 0.5f * dt;
        float _t1 = angularVelZ * _t0;
        float _t2 = angularVelX * _t0;
        float _t3 = angularVelY * _t0;
        float _t6 = java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, _t3 * _t3));
        float _t7 = (float) java.lang.Math.sqrt(_t6);
        float _t9 = Math.sin(_t7);
        float _t10 = Math.cosFromSin(_t9, _t7);
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
        d.x = java.lang.Math.fma(this.x, _t10, this.w * _t15) + java.lang.Math.fma(this.z, _t16, -(this.y * _t17));
        d.y = java.lang.Math.fma(this.x, _t17, this.w * _t16) + java.lang.Math.fma(this.y, _t10, -(this.z * _t15));
        d.z = java.lang.Math.fma(this.y, _t15, this.z * _t10) + java.lang.Math.fma(this.w, _t17, -this.x * _t16);
        d.w = java.lang.Math.fma(this.w, _t10, -this.x * _t15) - java.lang.Math.fma(this.y, _t16, this.z * _t17);
        return d;
    }

    /** {@code integrate} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat integrate_mulAdd(float angularVelX, float angularVelY, float angularVelZ, float dt, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _t0 = 0.5f * dt;
        float _t1 = angularVelZ * _t0;
        float _t2 = angularVelX * _t0;
        float _t3 = angularVelY * _t0;
        float _t6 = ((_t1) * (_t1) + (((_t2) * (_t2) + (_t3 * _t3))));
        float _t7 = (float) java.lang.Math.sqrt(_t6);
        float _t9 = Math.sin(_t7);
        float _t10 = Math.cosFromSin(_t9, _t7);
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
        d.x = ((this.x) * (_t10) + (this.w * _t15)) + ((this.z) * (_t16) - (this.y * _t17));
        d.y = ((this.x) * (_t17) + (this.w * _t16)) + ((this.y) * (_t10) - (this.z * _t15));
        d.z = ((this.y) * (_t15) + (this.z * _t10)) + ((this.w) * (_t17) - (this.x * _t16));
        d.w = ((this.w) * (_t10) - (this.x * _t15)) - ((this.y) * (_t16) + (this.z * _t17));
        return d;
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
        if (!Math.useFma()) return invNegativeX_mulAdd(dest);
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w);
        float _t10 = 2.0f * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
        float _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.x, this.x, this.w * this.w)));
        float _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = -(_t12 * _t16);
            d.y = -(_t10 * _t16);
            d.z = -(_t9 * _t16);
        } else {
            d.x = -0.0f;
            d.y = -0.0f;
            d.z = -0.0f;
        }
        return d;
    }

    /** {@code invNegativeX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 invNegativeX_mulAdd(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * ((this.x) * (this.z) + (this.y * this.w));
        float _t10 = 2.0f * ((this.x) * (this.y) - (this.z * this.w));
        float _t12 = ((((((this.x) * (this.x) + (this.w * this.w))) - (this.y) * (this.y))) - (this.z) * (this.z));
        float _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = -(_t12 * _t16);
            d.y = -(_t10 * _t16);
            d.z = -(_t9 * _t16);
        } else {
            d.x = -0.0f;
            d.y = -0.0f;
            d.z = -0.0f;
        }
        return d;
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
        if (!Math.useFma()) return invNegativeX_mulAdd(dest);
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w);
        float _t10 = 2.0f * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
        float _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.x, this.x, this.w * this.w)));
        float _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = -(_t12 * _t16);
            d.y = -(_t10 * _t16);
            d.z = -(_t9 * _t16);
        } else {
            d.x = -0.0f;
            d.y = -0.0f;
            d.z = -0.0f;
        }
        return d;
    }

    /** {@code invNegativeX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 invNegativeX_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * ((this.x) * (this.z) + (this.y * this.w));
        float _t10 = 2.0f * ((this.x) * (this.y) - (this.z * this.w));
        float _t12 = ((((((this.x) * (this.x) + (this.w * this.w))) - (this.y) * (this.y))) - (this.z) * (this.z));
        float _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = -(_t12 * _t16);
            d.y = -(_t10 * _t16);
            d.z = -(_t9 * _t16);
        } else {
            d.x = -0.0f;
            d.y = -0.0f;
            d.z = -0.0f;
        }
        return d;
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
        if (!Math.useFma()) return invNegativeY_mulAdd(dest);
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w);
        float _t10 = 2.0f * java.lang.Math.fma(this.y, this.z, -this.x * this.w);
        float _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
        float _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = -(_t9 * _t16);
            d.y = -(_t12 * _t16);
            d.z = -(_t10 * _t16);
        } else {
            d.x = -0.0f;
            d.y = -0.0f;
            d.z = -0.0f;
        }
        return d;
    }

    /** {@code invNegativeY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 invNegativeY_mulAdd(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * ((this.x) * (this.y) + (this.z * this.w));
        float _t10 = 2.0f * ((this.y) * (this.z) - (this.x * this.w));
        float _t12 = ((((this.y) * (this.y) + (((this.w) * (this.w) - (this.x * this.x))))) - (this.z) * (this.z));
        float _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = -(_t9 * _t16);
            d.y = -(_t12 * _t16);
            d.z = -(_t10 * _t16);
        } else {
            d.x = -0.0f;
            d.y = -0.0f;
            d.z = -0.0f;
        }
        return d;
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
        if (!Math.useFma()) return invNegativeY_mulAdd(dest);
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w);
        float _t10 = 2.0f * java.lang.Math.fma(this.y, this.z, -this.x * this.w);
        float _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
        float _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = -(_t9 * _t16);
            d.y = -(_t12 * _t16);
            d.z = -(_t10 * _t16);
        } else {
            d.x = -0.0f;
            d.y = -0.0f;
            d.z = -0.0f;
        }
        return d;
    }

    /** {@code invNegativeY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 invNegativeY_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * ((this.x) * (this.y) + (this.z * this.w));
        float _t10 = 2.0f * ((this.y) * (this.z) - (this.x * this.w));
        float _t12 = ((((this.y) * (this.y) + (((this.w) * (this.w) - (this.x * this.x))))) - (this.z) * (this.z));
        float _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = -(_t9 * _t16);
            d.y = -(_t12 * _t16);
            d.z = -(_t10 * _t16);
        } else {
            d.x = -0.0f;
            d.y = -0.0f;
            d.z = -0.0f;
        }
        return d;
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
        if (!Math.useFma()) return invNegativeZ_mulAdd(dest);
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        float _t10 = 2.0f * java.lang.Math.fma(this.x, this.z, -(this.y * this.w));
        float _t12 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
        float _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = -(_t10 * _t16);
            d.y = -(_t9 * _t16);
            d.z = -(_t12 * _t16);
        } else {
            d.x = -0.0f;
            d.y = -0.0f;
            d.z = -0.0f;
        }
        return d;
    }

    /** {@code invNegativeZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 invNegativeZ_mulAdd(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * ((this.x) * (this.w) + (this.y * this.z));
        float _t10 = 2.0f * ((this.x) * (this.z) - (this.y * this.w));
        float _t12 = ((this.z) * (this.z) + (((((this.w) * (this.w) - (this.x * this.x))) - (this.y) * (this.y))));
        float _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = -(_t10 * _t16);
            d.y = -(_t9 * _t16);
            d.z = -(_t12 * _t16);
        } else {
            d.x = -0.0f;
            d.y = -0.0f;
            d.z = -0.0f;
        }
        return d;
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
        if (!Math.useFma()) return invNegativeZ_mulAdd(dest);
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        float _t10 = 2.0f * java.lang.Math.fma(this.x, this.z, -(this.y * this.w));
        float _t12 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
        float _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = -(_t10 * _t16);
            d.y = -(_t9 * _t16);
            d.z = -(_t12 * _t16);
        } else {
            d.x = -0.0f;
            d.y = -0.0f;
            d.z = -0.0f;
        }
        return d;
    }

    /** {@code invNegativeZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 invNegativeZ_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * ((this.x) * (this.w) + (this.y * this.z));
        float _t10 = 2.0f * ((this.x) * (this.z) - (this.y * this.w));
        float _t12 = ((this.z) * (this.z) + (((((this.w) * (this.w) - (this.x * this.x))) - (this.y) * (this.y))));
        float _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = -(_t10 * _t16);
            d.y = -(_t9 * _t16);
            d.z = -(_t12 * _t16);
        } else {
            d.x = -0.0f;
            d.y = -0.0f;
            d.z = -0.0f;
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
    public Float3 invNormalizedNegativeX(@Mutated Float3 dest) {
        if (Math.useFma()) {
            Float3Impl d = (Float3Impl) dest;
            d.x = java.lang.Math.fma(2.0f, java.lang.Math.fma(this.y, this.y, this.z * this.z), -1.0f);
            d.y = -2.0f * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
            d.z = -2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w);
            return d;
        } else {
            Float3Impl d = (Float3Impl) dest;
            d.x = ((2.0f) * (((this.y) * (this.y) + (this.z * this.z))) - (1.0f));
            d.y = -2.0f * ((this.x) * (this.y) - (this.z * this.w));
            d.z = -2.0f * ((this.x) * (this.z) + (this.y * this.w));
            return d;
        }
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = java.lang.Math.fma(2.0f, java.lang.Math.fma(this.y, this.y, this.z * this.z), -1.0f);
            d.y = -2.0f * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
            d.z = -2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = ((2.0f) * (((this.y) * (this.y) + (this.z * this.z))) - (1.0f));
            d.y = -2.0f * ((this.x) * (this.y) - (this.z * this.w));
            d.z = -2.0f * ((this.x) * (this.z) + (this.y * this.w));
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
    public Float3 invNormalizedNegativeY(@Mutated Float3 dest) {
        if (Math.useFma()) {
            Float3Impl d = (Float3Impl) dest;
            d.x = -2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w);
            d.y = java.lang.Math.fma(2.0f, java.lang.Math.fma(this.x, this.x, this.z * this.z), -1.0f);
            d.z = -2.0f * java.lang.Math.fma(this.y, this.z, -(this.x * this.w));
            return d;
        } else {
            Float3Impl d = (Float3Impl) dest;
            d.x = -2.0f * ((this.x) * (this.y) + (this.z * this.w));
            d.y = ((2.0f) * (((this.x) * (this.x) + (this.z * this.z))) - (1.0f));
            d.z = -2.0f * ((this.y) * (this.z) - (this.x * this.w));
            return d;
        }
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = -2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w);
            d.y = java.lang.Math.fma(2.0f, java.lang.Math.fma(this.x, this.x, this.z * this.z), -1.0f);
            d.z = -2.0f * java.lang.Math.fma(this.y, this.z, -(this.x * this.w));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = -2.0f * ((this.x) * (this.y) + (this.z * this.w));
            d.y = ((2.0f) * (((this.x) * (this.x) + (this.z * this.z))) - (1.0f));
            d.z = -2.0f * ((this.y) * (this.z) - (this.x * this.w));
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
    public Float3 invNormalizedNegativeZ(@Mutated Float3 dest) {
        if (Math.useFma()) {
            Float3Impl d = (Float3Impl) dest;
            d.x = -2.0f * java.lang.Math.fma(this.x, this.z, -(this.y * this.w));
            d.y = -2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z);
            d.z = java.lang.Math.fma(2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), -1.0f);
            return d;
        } else {
            Float3Impl d = (Float3Impl) dest;
            d.x = -2.0f * ((this.x) * (this.z) - (this.y * this.w));
            d.y = -2.0f * ((this.x) * (this.w) + (this.y * this.z));
            d.z = ((2.0f) * (((this.x) * (this.x) + (this.y * this.y))) - (1.0f));
            return d;
        }
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = -2.0f * java.lang.Math.fma(this.x, this.z, -(this.y * this.w));
            d.y = -2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z);
            d.z = java.lang.Math.fma(2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), -1.0f);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = -2.0f * ((this.x) * (this.z) - (this.y * this.w));
            d.y = -2.0f * ((this.x) * (this.w) + (this.y * this.z));
            d.z = ((2.0f) * (((this.x) * (this.x) + (this.y * this.y))) - (1.0f));
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
    public Float3 invNormalizedPositiveX(@Mutated Float3 dest) {
        if (Math.useFma()) {
            Float3Impl d = (Float3Impl) dest;
            d.x = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, this.z * this.z), 1.0f);
            d.y = 2.0f * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
            d.z = 2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w);
            return d;
        } else {
            Float3Impl d = (Float3Impl) dest;
            d.x = ((1.0f) - (2.0f) * (((this.y) * (this.y) + (this.z * this.z))));
            d.y = 2.0f * ((this.x) * (this.y) - (this.z * this.w));
            d.z = 2.0f * ((this.x) * (this.z) + (this.y * this.w));
            return d;
        }
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, this.z * this.z), 1.0f);
            d.y = 2.0f * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
            d.z = 2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = ((1.0f) - (2.0f) * (((this.y) * (this.y) + (this.z * this.z))));
            d.y = 2.0f * ((this.x) * (this.y) - (this.z * this.w));
            d.z = 2.0f * ((this.x) * (this.z) + (this.y * this.w));
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
    public Float3 invNormalizedPositiveY(@Mutated Float3 dest) {
        if (Math.useFma()) {
            Float3Impl d = (Float3Impl) dest;
            d.x = 2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w);
            d.y = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.z * this.z), 1.0f);
            d.z = 2.0f * java.lang.Math.fma(this.y, this.z, -(this.x * this.w));
            return d;
        } else {
            Float3Impl d = (Float3Impl) dest;
            d.x = 2.0f * ((this.x) * (this.y) + (this.z * this.w));
            d.y = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.z * this.z))));
            d.z = 2.0f * ((this.y) * (this.z) - (this.x * this.w));
            return d;
        }
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = 2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w);
            d.y = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.z * this.z), 1.0f);
            d.z = 2.0f * java.lang.Math.fma(this.y, this.z, -(this.x * this.w));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = 2.0f * ((this.x) * (this.y) + (this.z * this.w));
            d.y = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.z * this.z))));
            d.z = 2.0f * ((this.y) * (this.z) - (this.x * this.w));
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
    public Float3 invNormalizedPositiveZ(@Mutated Float3 dest) {
        if (Math.useFma()) {
            Float3Impl d = (Float3Impl) dest;
            d.x = 2.0f * java.lang.Math.fma(this.x, this.z, -(this.y * this.w));
            d.y = 2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z);
            d.z = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f);
            return d;
        } else {
            Float3Impl d = (Float3Impl) dest;
            d.x = 2.0f * ((this.x) * (this.z) - (this.y * this.w));
            d.y = 2.0f * ((this.x) * (this.w) + (this.y * this.z));
            d.z = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y))));
            return d;
        }
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = 2.0f * java.lang.Math.fma(this.x, this.z, -(this.y * this.w));
            d.y = 2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z);
            d.z = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = 2.0f * ((this.x) * (this.z) - (this.y * this.w));
            d.y = 2.0f * ((this.x) * (this.w) + (this.y * this.z));
            d.z = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y))));
            return d;
        }
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
        if (!Math.useFma()) return invPositiveX_mulAdd(dest);
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w);
        float _t10 = 2.0f * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
        float _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.x, this.x, this.w * this.w)));
        float _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = _t12 * _t16;
            d.y = _t10 * _t16;
            d.z = _t9 * _t16;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
        }
        return d;
    }

    /** {@code invPositiveX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 invPositiveX_mulAdd(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * ((this.x) * (this.z) + (this.y * this.w));
        float _t10 = 2.0f * ((this.x) * (this.y) - (this.z * this.w));
        float _t12 = ((((((this.x) * (this.x) + (this.w * this.w))) - (this.y) * (this.y))) - (this.z) * (this.z));
        float _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = _t12 * _t16;
            d.y = _t10 * _t16;
            d.z = _t9 * _t16;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
        }
        return d;
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
        if (!Math.useFma()) return invPositiveX_mulAdd(dest);
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w);
        float _t10 = 2.0f * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
        float _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.x, this.x, this.w * this.w)));
        float _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = _t12 * _t16;
            d.y = _t10 * _t16;
            d.z = _t9 * _t16;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
        }
        return d;
    }

    /** {@code invPositiveX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 invPositiveX_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * ((this.x) * (this.z) + (this.y * this.w));
        float _t10 = 2.0f * ((this.x) * (this.y) - (this.z * this.w));
        float _t12 = ((((((this.x) * (this.x) + (this.w * this.w))) - (this.y) * (this.y))) - (this.z) * (this.z));
        float _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = _t12 * _t16;
            d.y = _t10 * _t16;
            d.z = _t9 * _t16;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
        }
        return d;
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
        if (!Math.useFma()) return invPositiveY_mulAdd(dest);
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w);
        float _t10 = 2.0f * java.lang.Math.fma(this.y, this.z, -this.x * this.w);
        float _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
        float _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = _t9 * _t16;
            d.y = _t12 * _t16;
            d.z = _t10 * _t16;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
        }
        return d;
    }

    /** {@code invPositiveY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 invPositiveY_mulAdd(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * ((this.x) * (this.y) + (this.z * this.w));
        float _t10 = 2.0f * ((this.y) * (this.z) - (this.x * this.w));
        float _t12 = ((((this.y) * (this.y) + (((this.w) * (this.w) - (this.x * this.x))))) - (this.z) * (this.z));
        float _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = _t9 * _t16;
            d.y = _t12 * _t16;
            d.z = _t10 * _t16;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
        }
        return d;
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
        if (!Math.useFma()) return invPositiveY_mulAdd(dest);
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w);
        float _t10 = 2.0f * java.lang.Math.fma(this.y, this.z, -this.x * this.w);
        float _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
        float _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = _t9 * _t16;
            d.y = _t12 * _t16;
            d.z = _t10 * _t16;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
        }
        return d;
    }

    /** {@code invPositiveY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 invPositiveY_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * ((this.x) * (this.y) + (this.z * this.w));
        float _t10 = 2.0f * ((this.y) * (this.z) - (this.x * this.w));
        float _t12 = ((((this.y) * (this.y) + (((this.w) * (this.w) - (this.x * this.x))))) - (this.z) * (this.z));
        float _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = _t9 * _t16;
            d.y = _t12 * _t16;
            d.z = _t10 * _t16;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
        }
        return d;
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
        if (!Math.useFma()) return invPositiveZ_mulAdd(dest);
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        float _t10 = 2.0f * java.lang.Math.fma(this.x, this.z, -(this.y * this.w));
        float _t12 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
        float _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = _t10 * _t16;
            d.y = _t9 * _t16;
            d.z = _t12 * _t16;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
        }
        return d;
    }

    /** {@code invPositiveZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 invPositiveZ_mulAdd(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * ((this.x) * (this.w) + (this.y * this.z));
        float _t10 = 2.0f * ((this.x) * (this.z) - (this.y * this.w));
        float _t12 = ((this.z) * (this.z) + (((((this.w) * (this.w) - (this.x * this.x))) - (this.y) * (this.y))));
        float _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = _t10 * _t16;
            d.y = _t9 * _t16;
            d.z = _t12 * _t16;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
        }
        return d;
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
        if (!Math.useFma()) return invPositiveZ_mulAdd(dest);
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        float _t10 = 2.0f * java.lang.Math.fma(this.x, this.z, -(this.y * this.w));
        float _t12 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
        float _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = _t10 * _t16;
            d.y = _t9 * _t16;
            d.z = _t12 * _t16;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
        }
        return d;
    }

    /** {@code invPositiveZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 invPositiveZ_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * ((this.x) * (this.w) + (this.y * this.z));
        float _t10 = 2.0f * ((this.x) * (this.z) - (this.y * this.w));
        float _t12 = ((this.z) * (this.z) + (((((this.w) * (this.w) - (this.x * this.x))) - (this.y) * (this.y))));
        float _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = _t10 * _t16;
            d.y = _t9 * _t16;
            d.z = _t12 * _t16;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
        }
        return d;
    }

    /**
     * Compute the length of this quaternion.
     * <p>
     * Valid input: nonzero magnitudes must lie between {@code 1e-19} and {@code 5e18}.
     *
     * @return the length of this quaternion
     */
    public float length() {
        if (Math.useFma()) {
            return (float) java.lang.Math.sqrt(java.lang.Math.fma(this.w, this.w, java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y))));
        } else {
            return (float) java.lang.Math.sqrt(((this.w) * (this.w) + (((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y)))))));
        }
    }

    /**
     * Compute the squared length of this quaternion.
     * <p>
     * Valid input: the default range of the package documentation.
     *
     * @return the squared length of this quaternion
     */
    public float lengthSquared() {
        if (Math.useFma()) {
            return java.lang.Math.fma(this.w, this.w, java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y)));
        } else {
            return ((this.w) * (this.w) + (((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))))));
        }
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
        if (Math.useFma()) {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _t2 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y));
            float _t6 = Math.atan2((float) java.lang.Math.sqrt(_t2), this.w) * (1.0f / (float) java.lang.Math.sqrt(_t2));
            if (_t2 > 0.0f) {
                d.x = this.x * _t6;
                d.y = this.y * _t6;
                d.z = this.z * _t6;
            } else {
                d.x = 0.0f;
                d.y = 0.0f;
                d.z = 0.0f;
            }
            d.w = Math.log((float) java.lang.Math.sqrt(java.lang.Math.fma(this.w, this.w, _t2)));
            return d;
        } else {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _t2 = ((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))));
            float _t6 = Math.atan2((float) java.lang.Math.sqrt(_t2), this.w) * (1.0f / (float) java.lang.Math.sqrt(_t2));
            if (_t2 > 0.0f) {
                d.x = this.x * _t6;
                d.y = this.y * _t6;
                d.z = this.z * _t6;
            } else {
                d.x = 0.0f;
                d.y = 0.0f;
                d.z = 0.0f;
            }
            d.w = Math.log((float) java.lang.Math.sqrt(((this.w) * (this.w) + (_t2))));
            return d;
        }
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
        if (Math.useFma()) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            float _t2 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y));
            float _t6 = Math.atan2((float) java.lang.Math.sqrt(_t2), this.w) * (1.0f / (float) java.lang.Math.sqrt(_t2));
            if (_t2 > 0.0f) {
                d.x = this.x * _t6;
                d.y = this.y * _t6;
                d.z = this.z * _t6;
            } else {
                d.x = 0.0f;
                d.y = 0.0f;
                d.z = 0.0f;
            }
            d.w = Math.log((float) java.lang.Math.sqrt(java.lang.Math.fma(this.w, this.w, _t2)));
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            float _t2 = ((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))));
            float _t6 = Math.atan2((float) java.lang.Math.sqrt(_t2), this.w) * (1.0f / (float) java.lang.Math.sqrt(_t2));
            if (_t2 > 0.0f) {
                d.x = this.x * _t6;
                d.y = this.y * _t6;
                d.z = this.z * _t6;
            } else {
                d.x = 0.0f;
                d.y = 0.0f;
                d.z = 0.0f;
            }
            d.w = Math.log((float) java.lang.Math.sqrt(((this.w) * (this.w) + (_t2))));
            return d;
        }
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
        if (!Math.useFma()) return negativeX_mulAdd(dest);
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w);
        float _t10 = 2.0f * java.lang.Math.fma(this.x, this.z, -this.y * this.w);
        float _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.x, this.x, this.w * this.w)));
        float _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = -(_t12 * _t16);
            d.y = -(_t9 * _t16);
            d.z = -(_t10 * _t16);
        } else {
            d.x = -0.0f;
            d.y = -0.0f;
            d.z = -0.0f;
        }
        return d;
    }

    /** {@code negativeX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 negativeX_mulAdd(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * ((this.x) * (this.y) + (this.z * this.w));
        float _t10 = 2.0f * ((this.x) * (this.z) - (this.y * this.w));
        float _t12 = ((((((this.x) * (this.x) + (this.w * this.w))) - (this.y) * (this.y))) - (this.z) * (this.z));
        float _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = -(_t12 * _t16);
            d.y = -(_t9 * _t16);
            d.z = -(_t10 * _t16);
        } else {
            d.x = -0.0f;
            d.y = -0.0f;
            d.z = -0.0f;
        }
        return d;
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
        if (!Math.useFma()) return negativeX_mulAdd(dest);
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w);
        float _t10 = 2.0f * java.lang.Math.fma(this.x, this.z, -this.y * this.w);
        float _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.x, this.x, this.w * this.w)));
        float _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = -(_t12 * _t16);
            d.y = -(_t9 * _t16);
            d.z = -(_t10 * _t16);
        } else {
            d.x = -0.0f;
            d.y = -0.0f;
            d.z = -0.0f;
        }
        return d;
    }

    /** {@code negativeX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 negativeX_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * ((this.x) * (this.y) + (this.z * this.w));
        float _t10 = 2.0f * ((this.x) * (this.z) - (this.y * this.w));
        float _t12 = ((((((this.x) * (this.x) + (this.w * this.w))) - (this.y) * (this.y))) - (this.z) * (this.z));
        float _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = -(_t12 * _t16);
            d.y = -(_t9 * _t16);
            d.z = -(_t10 * _t16);
        } else {
            d.x = -0.0f;
            d.y = -0.0f;
            d.z = -0.0f;
        }
        return d;
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
        if (!Math.useFma()) return negativeY_mulAdd(dest);
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        float _t10 = 2.0f * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
        float _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
        float _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = -(_t10 * _t16);
            d.y = -(_t12 * _t16);
            d.z = -(_t9 * _t16);
        } else {
            d.x = -0.0f;
            d.y = -0.0f;
            d.z = -0.0f;
        }
        return d;
    }

    /** {@code negativeY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 negativeY_mulAdd(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * ((this.x) * (this.w) + (this.y * this.z));
        float _t10 = 2.0f * ((this.x) * (this.y) - (this.z * this.w));
        float _t12 = ((((this.y) * (this.y) + (((this.w) * (this.w) - (this.x * this.x))))) - (this.z) * (this.z));
        float _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = -(_t10 * _t16);
            d.y = -(_t12 * _t16);
            d.z = -(_t9 * _t16);
        } else {
            d.x = -0.0f;
            d.y = -0.0f;
            d.z = -0.0f;
        }
        return d;
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
        if (!Math.useFma()) return negativeY_mulAdd(dest);
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        float _t10 = 2.0f * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
        float _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
        float _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = -(_t10 * _t16);
            d.y = -(_t12 * _t16);
            d.z = -(_t9 * _t16);
        } else {
            d.x = -0.0f;
            d.y = -0.0f;
            d.z = -0.0f;
        }
        return d;
    }

    /** {@code negativeY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 negativeY_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * ((this.x) * (this.w) + (this.y * this.z));
        float _t10 = 2.0f * ((this.x) * (this.y) - (this.z * this.w));
        float _t12 = ((((this.y) * (this.y) + (((this.w) * (this.w) - (this.x * this.x))))) - (this.z) * (this.z));
        float _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = -(_t10 * _t16);
            d.y = -(_t12 * _t16);
            d.z = -(_t9 * _t16);
        } else {
            d.x = -0.0f;
            d.y = -0.0f;
            d.z = -0.0f;
        }
        return d;
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
        if (!Math.useFma()) return negativeZ_mulAdd(dest);
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w);
        float _t10 = 2.0f * java.lang.Math.fma(this.y, this.z, -this.x * this.w);
        float _t12 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
        float _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = -(_t9 * _t16);
            d.y = -(_t10 * _t16);
            d.z = -(_t12 * _t16);
        } else {
            d.x = -0.0f;
            d.y = -0.0f;
            d.z = -0.0f;
        }
        return d;
    }

    /** {@code negativeZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 negativeZ_mulAdd(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * ((this.x) * (this.z) + (this.y * this.w));
        float _t10 = 2.0f * ((this.y) * (this.z) - (this.x * this.w));
        float _t12 = ((this.z) * (this.z) + (((((this.w) * (this.w) - (this.x * this.x))) - (this.y) * (this.y))));
        float _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = -(_t9 * _t16);
            d.y = -(_t10 * _t16);
            d.z = -(_t12 * _t16);
        } else {
            d.x = -0.0f;
            d.y = -0.0f;
            d.z = -0.0f;
        }
        return d;
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
        if (!Math.useFma()) return negativeZ_mulAdd(dest);
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w);
        float _t10 = 2.0f * java.lang.Math.fma(this.y, this.z, -this.x * this.w);
        float _t12 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
        float _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = -(_t9 * _t16);
            d.y = -(_t10 * _t16);
            d.z = -(_t12 * _t16);
        } else {
            d.x = -0.0f;
            d.y = -0.0f;
            d.z = -0.0f;
        }
        return d;
    }

    /** {@code negativeZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 negativeZ_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * ((this.x) * (this.z) + (this.y * this.w));
        float _t10 = 2.0f * ((this.y) * (this.z) - (this.x * this.w));
        float _t12 = ((this.z) * (this.z) + (((((this.w) * (this.w) - (this.x * this.x))) - (this.y) * (this.y))));
        float _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = -(_t9 * _t16);
            d.y = -(_t10 * _t16);
            d.z = -(_t12 * _t16);
        } else {
            d.x = -0.0f;
            d.y = -0.0f;
            d.z = -0.0f;
        }
        return d;
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
        if (Math.useFma()) {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _t3 = java.lang.Math.fma(this.w, this.w, java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y)));
            float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0f) {
                d.x = this.x * _t4;
                d.y = this.y * _t4;
                d.z = this.z * _t4;
                d.w = this.w * _t4;
            } else {
                d.x = 0.0f;
                d.y = 0.0f;
                d.z = 0.0f;
                d.w = 0.0f;
            }
            return d;
        } else {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _t3 = ((this.w) * (this.w) + (((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))))));
            float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0f) {
                d.x = this.x * _t4;
                d.y = this.y * _t4;
                d.z = this.z * _t4;
                d.w = this.w * _t4;
            } else {
                d.x = 0.0f;
                d.y = 0.0f;
                d.z = 0.0f;
                d.w = 0.0f;
            }
            return d;
        }
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
        if (Math.useFma()) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            float _t3 = java.lang.Math.fma(this.w, this.w, java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y)));
            float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0f) {
                d.x = this.x * _t4;
                d.y = this.y * _t4;
                d.z = this.z * _t4;
                d.w = this.w * _t4;
            } else {
                d.x = 0.0f;
                d.y = 0.0f;
                d.z = 0.0f;
                d.w = 0.0f;
            }
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            float _t3 = ((this.w) * (this.w) + (((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))))));
            float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
            if (_t3 != 0.0f) {
                d.x = this.x * _t4;
                d.y = this.y * _t4;
                d.z = this.z * _t4;
                d.w = this.w * _t4;
            } else {
                d.x = 0.0f;
                d.y = 0.0f;
                d.z = 0.0f;
                d.w = 0.0f;
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
    public Float3 normalizedNegativeX(@Mutated Float3 dest) {
        if (Math.useFma()) {
            Float3Impl d = (Float3Impl) dest;
            d.x = java.lang.Math.fma(2.0f, java.lang.Math.fma(this.y, this.y, this.z * this.z), -1.0f);
            d.y = -2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w);
            d.z = -2.0f * java.lang.Math.fma(this.x, this.z, -(this.y * this.w));
            return d;
        } else {
            Float3Impl d = (Float3Impl) dest;
            d.x = ((2.0f) * (((this.y) * (this.y) + (this.z * this.z))) - (1.0f));
            d.y = -2.0f * ((this.x) * (this.y) + (this.z * this.w));
            d.z = -2.0f * ((this.x) * (this.z) - (this.y * this.w));
            return d;
        }
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = java.lang.Math.fma(2.0f, java.lang.Math.fma(this.y, this.y, this.z * this.z), -1.0f);
            d.y = -2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w);
            d.z = -2.0f * java.lang.Math.fma(this.x, this.z, -(this.y * this.w));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = ((2.0f) * (((this.y) * (this.y) + (this.z * this.z))) - (1.0f));
            d.y = -2.0f * ((this.x) * (this.y) + (this.z * this.w));
            d.z = -2.0f * ((this.x) * (this.z) - (this.y * this.w));
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
    public Float3 normalizedNegativeY(@Mutated Float3 dest) {
        if (Math.useFma()) {
            Float3Impl d = (Float3Impl) dest;
            d.x = -2.0f * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
            d.y = java.lang.Math.fma(2.0f, java.lang.Math.fma(this.x, this.x, this.z * this.z), -1.0f);
            d.z = -2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z);
            return d;
        } else {
            Float3Impl d = (Float3Impl) dest;
            d.x = -2.0f * ((this.x) * (this.y) - (this.z * this.w));
            d.y = ((2.0f) * (((this.x) * (this.x) + (this.z * this.z))) - (1.0f));
            d.z = -2.0f * ((this.x) * (this.w) + (this.y * this.z));
            return d;
        }
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = -2.0f * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
            d.y = java.lang.Math.fma(2.0f, java.lang.Math.fma(this.x, this.x, this.z * this.z), -1.0f);
            d.z = -2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = -2.0f * ((this.x) * (this.y) - (this.z * this.w));
            d.y = ((2.0f) * (((this.x) * (this.x) + (this.z * this.z))) - (1.0f));
            d.z = -2.0f * ((this.x) * (this.w) + (this.y * this.z));
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
    public Float3 normalizedNegativeZ(@Mutated Float3 dest) {
        if (Math.useFma()) {
            Float3Impl d = (Float3Impl) dest;
            d.x = -2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w);
            d.y = -2.0f * java.lang.Math.fma(this.y, this.z, -(this.x * this.w));
            d.z = java.lang.Math.fma(2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), -1.0f);
            return d;
        } else {
            Float3Impl d = (Float3Impl) dest;
            d.x = -2.0f * ((this.x) * (this.z) + (this.y * this.w));
            d.y = -2.0f * ((this.y) * (this.z) - (this.x * this.w));
            d.z = ((2.0f) * (((this.x) * (this.x) + (this.y * this.y))) - (1.0f));
            return d;
        }
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = -2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w);
            d.y = -2.0f * java.lang.Math.fma(this.y, this.z, -(this.x * this.w));
            d.z = java.lang.Math.fma(2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), -1.0f);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = -2.0f * ((this.x) * (this.z) + (this.y * this.w));
            d.y = -2.0f * ((this.y) * (this.z) - (this.x * this.w));
            d.z = ((2.0f) * (((this.x) * (this.x) + (this.y * this.y))) - (1.0f));
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
    public Float3 normalizedPositiveX(@Mutated Float3 dest) {
        if (Math.useFma()) {
            Float3Impl d = (Float3Impl) dest;
            d.x = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, this.z * this.z), 1.0f);
            d.y = 2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w);
            d.z = 2.0f * java.lang.Math.fma(this.x, this.z, -(this.y * this.w));
            return d;
        } else {
            Float3Impl d = (Float3Impl) dest;
            d.x = ((1.0f) - (2.0f) * (((this.y) * (this.y) + (this.z * this.z))));
            d.y = 2.0f * ((this.x) * (this.y) + (this.z * this.w));
            d.z = 2.0f * ((this.x) * (this.z) - (this.y * this.w));
            return d;
        }
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.y, this.y, this.z * this.z), 1.0f);
            d.y = 2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w);
            d.z = 2.0f * java.lang.Math.fma(this.x, this.z, -(this.y * this.w));
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = ((1.0f) - (2.0f) * (((this.y) * (this.y) + (this.z * this.z))));
            d.y = 2.0f * ((this.x) * (this.y) + (this.z * this.w));
            d.z = 2.0f * ((this.x) * (this.z) - (this.y * this.w));
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
    public Float3 normalizedPositiveY(@Mutated Float3 dest) {
        if (Math.useFma()) {
            Float3Impl d = (Float3Impl) dest;
            d.x = 2.0f * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
            d.y = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.z * this.z), 1.0f);
            d.z = 2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z);
            return d;
        } else {
            Float3Impl d = (Float3Impl) dest;
            d.x = 2.0f * ((this.x) * (this.y) - (this.z * this.w));
            d.y = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.z * this.z))));
            d.z = 2.0f * ((this.x) * (this.w) + (this.y * this.z));
            return d;
        }
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = 2.0f * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
            d.y = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.z * this.z), 1.0f);
            d.z = 2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = 2.0f * ((this.x) * (this.y) - (this.z * this.w));
            d.y = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.z * this.z))));
            d.z = 2.0f * ((this.x) * (this.w) + (this.y * this.z));
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
    public Float3 normalizedPositiveZ(@Mutated Float3 dest) {
        if (Math.useFma()) {
            Float3Impl d = (Float3Impl) dest;
            d.x = 2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w);
            d.y = 2.0f * java.lang.Math.fma(this.y, this.z, -(this.x * this.w));
            d.z = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f);
            return d;
        } else {
            Float3Impl d = (Float3Impl) dest;
            d.x = 2.0f * ((this.x) * (this.z) + (this.y * this.w));
            d.y = 2.0f * ((this.y) * (this.z) - (this.x * this.w));
            d.z = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y))));
            return d;
        }
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
        if (Math.useFma()) {
            Double3Impl d = (Double3Impl) dest;
            d.x = 2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w);
            d.y = 2.0f * java.lang.Math.fma(this.y, this.z, -(this.x * this.w));
            d.z = java.lang.Math.fma(-2.0f, java.lang.Math.fma(this.x, this.x, this.y * this.y), 1.0f);
            return d;
        } else {
            Double3Impl d = (Double3Impl) dest;
            d.x = 2.0f * ((this.x) * (this.z) + (this.y * this.w));
            d.y = 2.0f * ((this.y) * (this.z) - (this.x * this.w));
            d.z = ((1.0f) - (2.0f) * (((this.x) * (this.x) + (this.y * this.y))));
            return d;
        }
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
        if (!Math.useFma()) return positiveX_mulAdd(dest);
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w);
        float _t10 = 2.0f * java.lang.Math.fma(this.x, this.z, -this.y * this.w);
        float _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.x, this.x, this.w * this.w)));
        float _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = _t12 * _t16;
            d.y = _t9 * _t16;
            d.z = _t10 * _t16;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
        }
        return d;
    }

    /** {@code positiveX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 positiveX_mulAdd(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * ((this.x) * (this.y) + (this.z * this.w));
        float _t10 = 2.0f * ((this.x) * (this.z) - (this.y * this.w));
        float _t12 = ((((((this.x) * (this.x) + (this.w * this.w))) - (this.y) * (this.y))) - (this.z) * (this.z));
        float _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = _t12 * _t16;
            d.y = _t9 * _t16;
            d.z = _t10 * _t16;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
        }
        return d;
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
        if (!Math.useFma()) return positiveX_mulAdd(dest);
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.y, this.z * this.w);
        float _t10 = 2.0f * java.lang.Math.fma(this.x, this.z, -this.y * this.w);
        float _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.x, this.x, this.w * this.w)));
        float _t15 = java.lang.Math.fma(_t10, _t10, java.lang.Math.fma(_t12, _t12, _t9 * _t9));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = _t12 * _t16;
            d.y = _t9 * _t16;
            d.z = _t10 * _t16;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
        }
        return d;
    }

    /** {@code positiveX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 positiveX_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * ((this.x) * (this.y) + (this.z * this.w));
        float _t10 = 2.0f * ((this.x) * (this.z) - (this.y * this.w));
        float _t12 = ((((((this.x) * (this.x) + (this.w * this.w))) - (this.y) * (this.y))) - (this.z) * (this.z));
        float _t15 = ((_t10) * (_t10) + (((_t12) * (_t12) + (_t9 * _t9))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = _t12 * _t16;
            d.y = _t9 * _t16;
            d.z = _t10 * _t16;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
        }
        return d;
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
        if (!Math.useFma()) return positiveY_mulAdd(dest);
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        float _t10 = 2.0f * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
        float _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
        float _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = _t10 * _t16;
            d.y = _t12 * _t16;
            d.z = _t9 * _t16;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
        }
        return d;
    }

    /** {@code positiveY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 positiveY_mulAdd(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * ((this.x) * (this.w) + (this.y * this.z));
        float _t10 = 2.0f * ((this.x) * (this.y) - (this.z * this.w));
        float _t12 = ((((this.y) * (this.y) + (((this.w) * (this.w) - (this.x * this.x))))) - (this.z) * (this.z));
        float _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = _t10 * _t16;
            d.y = _t12 * _t16;
            d.z = _t9 * _t16;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
        }
        return d;
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
        if (!Math.useFma()) return positiveY_mulAdd(dest);
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.w, this.y * this.z);
        float _t10 = 2.0f * java.lang.Math.fma(this.x, this.y, -(this.z * this.w));
        float _t12 = java.lang.Math.fma(-this.z, this.z, java.lang.Math.fma(this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
        float _t15 = java.lang.Math.fma(_t9, _t9, java.lang.Math.fma(_t12, _t12, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = _t10 * _t16;
            d.y = _t12 * _t16;
            d.z = _t9 * _t16;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
        }
        return d;
    }

    /** {@code positiveY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 positiveY_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * ((this.x) * (this.w) + (this.y * this.z));
        float _t10 = 2.0f * ((this.x) * (this.y) - (this.z * this.w));
        float _t12 = ((((this.y) * (this.y) + (((this.w) * (this.w) - (this.x * this.x))))) - (this.z) * (this.z));
        float _t15 = ((_t9) * (_t9) + (((_t12) * (_t12) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = _t10 * _t16;
            d.y = _t12 * _t16;
            d.z = _t9 * _t16;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
        }
        return d;
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
        if (!Math.useFma()) return positiveZ_mulAdd(dest);
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w);
        float _t10 = 2.0f * java.lang.Math.fma(this.y, this.z, -this.x * this.w);
        float _t12 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
        float _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = _t9 * _t16;
            d.y = _t10 * _t16;
            d.z = _t12 * _t16;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
        }
        return d;
    }

    /** {@code positiveZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 positiveZ_mulAdd(@Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * ((this.x) * (this.z) + (this.y * this.w));
        float _t10 = 2.0f * ((this.y) * (this.z) - (this.x * this.w));
        float _t12 = ((this.z) * (this.z) + (((((this.w) * (this.w) - (this.x * this.x))) - (this.y) * (this.y))));
        float _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = _t9 * _t16;
            d.y = _t10 * _t16;
            d.z = _t12 * _t16;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
        }
        return d;
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
        if (!Math.useFma()) return positiveZ_mulAdd(dest);
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, this.z, this.y * this.w);
        float _t10 = 2.0f * java.lang.Math.fma(this.y, this.z, -this.x * this.w);
        float _t12 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(-this.y, this.y, java.lang.Math.fma(this.w, this.w, -this.x * this.x)));
        float _t15 = java.lang.Math.fma(_t12, _t12, java.lang.Math.fma(_t9, _t9, _t10 * _t10));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = _t9 * _t16;
            d.y = _t10 * _t16;
            d.z = _t12 * _t16;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
        }
        return d;
    }

    /** {@code positiveZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 positiveZ_mulAdd(@Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * ((this.x) * (this.z) + (this.y * this.w));
        float _t10 = 2.0f * ((this.y) * (this.z) - (this.x * this.w));
        float _t12 = ((this.z) * (this.z) + (((((this.w) * (this.w) - (this.x * this.x))) - (this.y) * (this.y))));
        float _t15 = ((_t12) * (_t12) + (((_t9) * (_t9) + (_t10 * _t10))));
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t15));
        if (_t15 != 0.0f) {
            d.x = _t9 * _t16;
            d.y = _t10 * _t16;
            d.z = _t12 * _t16;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
        }
        return d;
    }

    /**
     * Raise this quaternion to the power of {@code t}, i.e. compute {@code exp(t * log(this))}, and
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
        if (!Math.useFma()) return pow_mulAdd(t, dest);
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _t2 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y));
        float _t12 = Math.min(Math.exp(t == 0.0f ? 0.0f : t * Math.log((float) java.lang.Math.sqrt(java.lang.Math.fma(this.w, this.w, _t2)))), 3.4028235E38f);
        float _t13 = Math.atan2((float) java.lang.Math.sqrt(_t2), this.w) * (1.0f / (float) java.lang.Math.sqrt(_t2));
        float _t20, _t21, _t22;
        if (_t2 > 0.0f) {
            _t20 = t * this.z * _t13;
            _t21 = t * this.x * _t13;
            _t22 = t * this.y * _t13;
        } else {
            _t20 = t * 0.0f;
            _t21 = t * 0.0f;
            _t22 = t * 0.0f;
        }
        float _t25 = java.lang.Math.fma(_t20, _t20, java.lang.Math.fma(_t21, _t21, _t22 * _t22));
        float _t26 = (float) java.lang.Math.sqrt(_t25);
        float _t28 = Math.sin(_t26);
        float _t31 = _t12 * (_t28 / _t26);
        if (_t25 > 0.0f) {
            d.x = _t21 * _t31;
            d.y = _t22 * _t31;
            d.z = _t20 * _t31;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
        }
        d.w = Math.cosFromSin(_t28, _t26) * _t12;
        return d;
    }

    /** {@code pow} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatQuat pow_mulAdd(float t, @Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _t2 = ((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))));
        float _t12 = Math.min(Math.exp(t == 0.0f ? 0.0f : t * Math.log((float) java.lang.Math.sqrt(((this.w) * (this.w) + (_t2))))), 3.4028235E38f);
        float _t13 = Math.atan2((float) java.lang.Math.sqrt(_t2), this.w) * (1.0f / (float) java.lang.Math.sqrt(_t2));
        float _t20, _t21, _t22;
        if (_t2 > 0.0f) {
            _t20 = t * this.z * _t13;
            _t21 = t * this.x * _t13;
            _t22 = t * this.y * _t13;
        } else {
            _t20 = t * 0.0f;
            _t21 = t * 0.0f;
            _t22 = t * 0.0f;
        }
        float _t25 = ((_t20) * (_t20) + (((_t21) * (_t21) + (_t22 * _t22))));
        float _t26 = (float) java.lang.Math.sqrt(_t25);
        float _t28 = Math.sin(_t26);
        float _t31 = _t12 * (_t28 / _t26);
        if (_t25 > 0.0f) {
            d.x = _t21 * _t31;
            d.y = _t22 * _t31;
            d.z = _t20 * _t31;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
        }
        d.w = Math.cosFromSin(_t28, _t26) * _t12;
        return d;
    }

    /**
     * Raise this quaternion to the power of {@code t}, i.e. compute {@code exp(t * log(this))}, and
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
        if (!Math.useFma()) return pow_mulAdd(t, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _t2 = java.lang.Math.fma(this.z, this.z, java.lang.Math.fma(this.x, this.x, this.y * this.y));
        float _t12 = Math.min(Math.exp(t == 0.0f ? 0.0f : t * Math.log((float) java.lang.Math.sqrt(java.lang.Math.fma(this.w, this.w, _t2)))), 3.4028235E38f);
        float _t13 = Math.atan2((float) java.lang.Math.sqrt(_t2), this.w) * (1.0f / (float) java.lang.Math.sqrt(_t2));
        float _t20, _t21, _t22;
        if (_t2 > 0.0f) {
            _t20 = t * this.z * _t13;
            _t21 = t * this.x * _t13;
            _t22 = t * this.y * _t13;
        } else {
            _t20 = t * 0.0f;
            _t21 = t * 0.0f;
            _t22 = t * 0.0f;
        }
        float _t25 = java.lang.Math.fma(_t20, _t20, java.lang.Math.fma(_t21, _t21, _t22 * _t22));
        float _t26 = (float) java.lang.Math.sqrt(_t25);
        float _t28 = Math.sin(_t26);
        float _t31 = _t12 * (_t28 / _t26);
        if (_t25 > 0.0f) {
            d.x = _t21 * _t31;
            d.y = _t22 * _t31;
            d.z = _t20 * _t31;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
        }
        d.w = Math.cosFromSin(_t28, _t26) * _t12;
        return d;
    }

    /** {@code pow} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat pow_mulAdd(float t, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _t2 = ((this.z) * (this.z) + (((this.x) * (this.x) + (this.y * this.y))));
        float _t12 = Math.min(Math.exp(t == 0.0f ? 0.0f : t * Math.log((float) java.lang.Math.sqrt(((this.w) * (this.w) + (_t2))))), 3.4028235E38f);
        float _t13 = Math.atan2((float) java.lang.Math.sqrt(_t2), this.w) * (1.0f / (float) java.lang.Math.sqrt(_t2));
        float _t20, _t21, _t22;
        if (_t2 > 0.0f) {
            _t20 = t * this.z * _t13;
            _t21 = t * this.x * _t13;
            _t22 = t * this.y * _t13;
        } else {
            _t20 = t * 0.0f;
            _t21 = t * 0.0f;
            _t22 = t * 0.0f;
        }
        float _t25 = ((_t20) * (_t20) + (((_t21) * (_t21) + (_t22 * _t22))));
        float _t26 = (float) java.lang.Math.sqrt(_t25);
        float _t28 = Math.sin(_t26);
        float _t31 = _t12 * (_t28 / _t26);
        if (_t25 > 0.0f) {
            d.x = _t21 * _t31;
            d.y = _t22 * _t31;
            d.z = _t20 * _t31;
        } else {
            d.x = 0.0f;
            d.y = 0.0f;
            d.z = 0.0f;
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
        if (Math.useFma()) {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _rd0 = this.x;
            float _rd1 = this.y;
            float _rd2 = this.z;
            d.x = java.lang.Math.fma(otherX, this.w, otherW * _rd0) + java.lang.Math.fma(otherY, _rd2, -(otherZ * _rd1));
            d.y = java.lang.Math.fma(otherY, this.w, otherZ * _rd0) + java.lang.Math.fma(otherW, _rd1, -otherX * _rd2);
            d.z = java.lang.Math.fma(otherX, _rd1, otherW * _rd2) + java.lang.Math.fma(otherZ, this.w, -(otherY * _rd0));
            d.w = java.lang.Math.fma(otherW, this.w, -otherX * _rd0) - java.lang.Math.fma(otherY, _rd1, otherZ * _rd2);
            return d;
        } else {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _rd0 = this.x;
            float _rd1 = this.y;
            float _rd2 = this.z;
            d.x = ((otherX) * (this.w) + (otherW * _rd0)) + ((otherY) * (_rd2) - (otherZ * _rd1));
            d.y = ((otherY) * (this.w) + (otherZ * _rd0)) + ((otherW) * (_rd1) - (otherX * _rd2));
            d.z = ((otherX) * (_rd1) + (otherW * _rd2)) + ((otherZ) * (this.w) - (otherY * _rd0));
            d.w = ((otherW) * (this.w) - (otherX * _rd0)) - ((otherY) * (_rd1) + (otherZ * _rd2));
            return d;
        }
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
        if (!Math.useFma()) return preMul_mulAdd(otherX, otherY, otherZ, otherW, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        d.x = java.lang.Math.fma(otherX, this.w, otherW * this.x) + java.lang.Math.fma(otherY, this.z, -(otherZ * this.y));
        d.y = java.lang.Math.fma(otherY, this.w, otherZ * this.x) + java.lang.Math.fma(otherW, this.y, -otherX * this.z);
        d.z = java.lang.Math.fma(otherX, this.y, otherW * this.z) + java.lang.Math.fma(otherZ, this.w, -(otherY * this.x));
        d.w = java.lang.Math.fma(otherW, this.w, -otherX * this.x) - java.lang.Math.fma(otherY, this.y, otherZ * this.z);
        return d;
    }

    /**
     * Rotate this quaternion towards {@code target}, by at most the given maximum angle, and store
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
        float targetX = target.x();
        float targetY = target.y();
        float targetZ = target.z();
        float targetW = target.w();
        if (Math.useFma()) return rotateTowards_fma(targetX, targetY, targetZ, targetW, step, dest);
        return rotateTowards_mulAdd(targetX, targetY, targetZ, targetW, step, dest);
    }

    /**
     * Rotate this quaternion towards {@code target}, by at most the given maximum angle, and store
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
        float targetX = target.x();
        float targetY = target.y();
        float targetZ = target.z();
        float targetW = target.w();
        if (Math.useFma()) return rotateTowards_fma(targetX, targetY, targetZ, targetW, step, dest);
        return rotateTowards_mulAdd(targetX, targetY, targetZ, targetW, step, dest);
    }

    /**
     * Rotate this quaternion towards ({@code targetX}, {@code targetY}, {@code targetZ},
     * {@code targetW}), by at most the given maximum angle, and store the result in {@code dest}.
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
        if (Math.useFma()) return rotateTowards_fma(targetX, targetY, targetZ, targetW, step, dest);
        return rotateTowards_mulAdd(targetX, targetY, targetZ, targetW, step, dest);
    }

    /** {@code rotateTowards} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatQuat rotateTowards_fma(float targetX, float targetY, float targetZ, float targetW, float step, @Mutated FloatQuat dest) {
        float _r0 = this.w;
        float _r1 = this.z;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t7 = java.lang.Math.fma(_r0, targetW, java.lang.Math.fma(_r1, targetZ, java.lang.Math.fma(_r2, targetX, _r3 * targetY)));
        float _t11 = Math.acos(Math.min(1.0f, java.lang.Math.abs(_t7)));
        float _t12 = Math.sin(_t11);
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
        float _t17 = _r0 - _t13;
        float _t18 = _r1 - _t14;
        float _t19 = _r2 - _t15;
        float _t20 = _r3 - _t16;
        float _t21 = _r0 + _t13;
        float _t22 = _r1 + _t14;
        float _t23 = _r2 + _t15;
        float _t24 = _r3 + _t16;
        float _t36 = 4.0f * Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)))), (float) java.lang.Math.sqrt(java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)))));
        float _t39 = _t36 > 0.0f ? Math.min(1.0f, step / _t36) : 0.0f;
        return rotateTowards_sa40886ef_1_fma((FloatQuatImpl) dest, _r0, _r1, _r2, _r3, _t11, _t12, 1.0f / _t12, _t13, _t14, _t15, _t16, _t39, 1.0f - _t39, Math.sin(_t11 * _t39));
    }

    /** {@code rotateTowards} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatQuat rotateTowards_mulAdd(float targetX, float targetY, float targetZ, float targetW, float step, @Mutated FloatQuat dest) {
        float _r0 = this.w;
        float _r1 = this.z;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t7 = ((_r0) * (targetW) + (((_r1) * (targetZ) + (((_r2) * (targetX) + (_r3 * targetY))))));
        float _t11 = Math.acos(Math.min(1.0f, java.lang.Math.abs(_t7)));
        float _t12 = Math.sin(_t11);
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
        float _t17 = _r0 - _t13;
        float _t18 = _r1 - _t14;
        float _t19 = _r2 - _t15;
        float _t20 = _r3 - _t16;
        float _t21 = _r0 + _t13;
        float _t22 = _r1 + _t14;
        float _t23 = _r2 + _t15;
        float _t24 = _r3 + _t16;
        float _t36 = 4.0f * Math.atan2((float) java.lang.Math.sqrt(((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))))), (float) java.lang.Math.sqrt(((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))))));
        float _t39 = _t36 > 0.0f ? Math.min(1.0f, step / _t36) : 0.0f;
        return rotateTowards_sa40886ef_1_mulAdd((FloatQuatImpl) dest, _r0, _r1, _r2, _r3, _t11, _t12, 1.0f / _t12, _t13, _t14, _t15, _t16, _t39, 1.0f - _t39, Math.sin(_t11 * _t39));
    }

    /** Piece 2 of {@code rotateTowards}, split to fit the inline budget; reached only through it. */
    private FloatQuat rotateTowards_sa40886ef_1_fma(FloatQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t11, float _t12, float _t12_inv, float _t13, float _t14, float _t15, float _t16, float _t39, float _t40, float _t42) {
        float _t44 = Math.sin(_t40 * _t11);
        float _t65, _t66, _t67, _t68;
        if (_t12 > 0.0f) {
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
        float _t72 = java.lang.Math.fma(_t65, _t65, java.lang.Math.fma(_t66, _t66, java.lang.Math.fma(_t67, _t67, _t68 * _t68)));
        float _t73 = (1.0f / (float) java.lang.Math.sqrt(_t72));
        d.x = _t72 != 0.0f ? _t73 * _t67 : 0.0f;
        d.y = _t72 != 0.0f ? _t73 * _t68 : 0.0f;
        d.z = _t72 != 0.0f ? _t73 * _t66 : 0.0f;
        d.w = _t72 != 0.0f ? _t73 * _t65 : 0.0f;
        return d;
    }

    /** Piece 2 of {@code rotateTowards}, split to fit the inline budget; reached only through it. */
    private FloatQuat rotateTowards_sa40886ef_1_mulAdd(FloatQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t11, float _t12, float _t12_inv, float _t13, float _t14, float _t15, float _t16, float _t39, float _t40, float _t42) {
        float _t44 = Math.sin(_t40 * _t11);
        float _t65, _t66, _t67, _t68;
        if (_t12 > 0.0f) {
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
        float _t72 = ((_t65) * (_t65) + (((_t66) * (_t66) + (((_t67) * (_t67) + (_t68 * _t68))))));
        float _t73 = (1.0f / (float) java.lang.Math.sqrt(_t72));
        d.x = _t72 != 0.0f ? _t73 * _t67 : 0.0f;
        d.y = _t72 != 0.0f ? _t73 * _t68 : 0.0f;
        d.z = _t72 != 0.0f ? _t73 * _t66 : 0.0f;
        d.w = _t72 != 0.0f ? _t73 * _t65 : 0.0f;
        return d;
    }

    /**
     * Rotate this quaternion towards ({@code targetX}, {@code targetY}, {@code targetZ},
     * {@code targetW}), by at most the given maximum angle, and store the result in {@code dest}.
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
        if (Math.useFma()) return rotateTowards_fma(targetX, targetY, targetZ, targetW, step, dest);
        return rotateTowards_mulAdd(targetX, targetY, targetZ, targetW, step, dest);
    }

    /** {@code rotateTowards} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateTowards_fma(float targetX, float targetY, float targetZ, float targetW, float step, @Mutated DoubleQuat dest) {
        float _r0 = this.w;
        float _r1 = this.z;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t7 = java.lang.Math.fma(_r0, targetW, java.lang.Math.fma(_r1, targetZ, java.lang.Math.fma(_r2, targetX, _r3 * targetY)));
        float _t11 = Math.acos(Math.min(1.0f, java.lang.Math.abs(_t7)));
        float _t12 = Math.sin(_t11);
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
        float _t17 = _r0 - _t13;
        float _t18 = _r1 - _t14;
        float _t19 = _r2 - _t15;
        float _t20 = _r3 - _t16;
        float _t21 = _r0 + _t13;
        float _t22 = _r1 + _t14;
        float _t23 = _r2 + _t15;
        float _t24 = _r3 + _t16;
        float _t36 = 4.0f * Math.atan2((float) java.lang.Math.sqrt(java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)))), (float) java.lang.Math.sqrt(java.lang.Math.fma(_t21, _t21, java.lang.Math.fma(_t22, _t22, java.lang.Math.fma(_t23, _t23, _t24 * _t24)))));
        float _t39 = _t36 > 0.0f ? Math.min(1.0f, step / _t36) : 0.0f;
        return rotateTowards_se7198d92_1_fma((DoubleQuatImpl) dest, _r0, _r1, _r2, _r3, _t11, _t12, 1.0f / _t12, _t13, _t14, _t15, _t16, _t39, 1.0f - _t39, Math.sin(_t11 * _t39));
    }

    /** {@code rotateTowards} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateTowards_mulAdd(float targetX, float targetY, float targetZ, float targetW, float step, @Mutated DoubleQuat dest) {
        float _r0 = this.w;
        float _r1 = this.z;
        float _r2 = this.x;
        float _r3 = this.y;
        float _t7 = ((_r0) * (targetW) + (((_r1) * (targetZ) + (((_r2) * (targetX) + (_r3 * targetY))))));
        float _t11 = Math.acos(Math.min(1.0f, java.lang.Math.abs(_t7)));
        float _t12 = Math.sin(_t11);
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
        float _t17 = _r0 - _t13;
        float _t18 = _r1 - _t14;
        float _t19 = _r2 - _t15;
        float _t20 = _r3 - _t16;
        float _t21 = _r0 + _t13;
        float _t22 = _r1 + _t14;
        float _t23 = _r2 + _t15;
        float _t24 = _r3 + _t16;
        float _t36 = 4.0f * Math.atan2((float) java.lang.Math.sqrt(((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))))), (float) java.lang.Math.sqrt(((_t21) * (_t21) + (((_t22) * (_t22) + (((_t23) * (_t23) + (_t24 * _t24))))))));
        float _t39 = _t36 > 0.0f ? Math.min(1.0f, step / _t36) : 0.0f;
        return rotateTowards_se7198d92_1_mulAdd((DoubleQuatImpl) dest, _r0, _r1, _r2, _r3, _t11, _t12, 1.0f / _t12, _t13, _t14, _t15, _t16, _t39, 1.0f - _t39, Math.sin(_t11 * _t39));
    }

    /** Piece 2 of {@code rotateTowards}, split to fit the inline budget; reached only through it. */
    private DoubleQuat rotateTowards_se7198d92_1_fma(DoubleQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t11, float _t12, float _t12_inv, float _t13, float _t14, float _t15, float _t16, float _t39, float _t40, float _t42) {
        float _t44 = Math.sin(_t40 * _t11);
        float _t65, _t66, _t67, _t68;
        if (_t12 > 0.0f) {
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
        float _t72 = java.lang.Math.fma(_t65, _t65, java.lang.Math.fma(_t66, _t66, java.lang.Math.fma(_t67, _t67, _t68 * _t68)));
        float _t73 = (1.0f / (float) java.lang.Math.sqrt(_t72));
        d.x = _t72 != 0.0f ? _t73 * _t67 : 0.0f;
        d.y = _t72 != 0.0f ? _t73 * _t68 : 0.0f;
        d.z = _t72 != 0.0f ? _t73 * _t66 : 0.0f;
        d.w = _t72 != 0.0f ? _t73 * _t65 : 0.0f;
        return d;
    }

    /** Piece 2 of {@code rotateTowards}, split to fit the inline budget; reached only through it. */
    private DoubleQuat rotateTowards_se7198d92_1_mulAdd(DoubleQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t11, float _t12, float _t12_inv, float _t13, float _t14, float _t15, float _t16, float _t39, float _t40, float _t42) {
        float _t44 = Math.sin(_t40 * _t11);
        float _t65, _t66, _t67, _t68;
        if (_t12 > 0.0f) {
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
        float _t72 = ((_t65) * (_t65) + (((_t66) * (_t66) + (((_t67) * (_t67) + (_t68 * _t68))))));
        float _t73 = (1.0f / (float) java.lang.Math.sqrt(_t72));
        d.x = _t72 != 0.0f ? _t73 * _t67 : 0.0f;
        d.y = _t72 != 0.0f ? _t73 * _t68 : 0.0f;
        d.z = _t72 != 0.0f ? _t73 * _t66 : 0.0f;
        d.w = _t72 != 0.0f ? _t73 * _t65 : 0.0f;
        return d;
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatQuat lookAlong_lh_fma(Float3R dir, Float3R up, @Mutated FloatQuat dest) {
        return lookAlong_lh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatQuat lookAlong_lh_mulAdd(Float3R dir, Float3R up, @Mutated FloatQuat dest) {
        return lookAlong_lh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_fma(Float3R dir, Float3R up, @Mutated DoubleQuat dest) {
        return lookAlong_lh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_mulAdd(Float3R dir, Float3R up, @Mutated DoubleQuat dest) {
        return lookAlong_lh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatQuat lookAlong_lh_fma(Float3R dir, Float3R up) {
        return lookAlong_lh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatQuat lookAlong_lh_mulAdd(Float3R dir, Float3R up) {
        return lookAlong_lh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatQuat lookAlong_lh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated FloatQuat dest) {
        float _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t27 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t28 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t29 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t36 = java.lang.Math.fma(dirZ, _t27, -dirY * _t28);
        float _t37 = java.lang.Math.fma(dirY, _t29, -(dirX * _t27));
        float _t38 = java.lang.Math.fma(dirX, _t28, -(dirZ * _t29));
        float _ct0 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36));
        if (!(_ct0 > java.lang.Math.fma(_t7, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        return lookAlong_lh_s36f6c823_1_fma(dirX, dirY, dirZ, (FloatQuatImpl) dest, _t15, _t36, _t37, _t38, (1.0f / (float) java.lang.Math.sqrt(_ct0)), this.x, this.w, this.y, this.z, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatQuat lookAlong_lh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated FloatQuat dest) {
        float _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t27 = ((upY) - (dirY) * (_sp0));
        float _t28 = ((upZ) - (dirZ) * (_sp0));
        float _t29 = ((upX) - (dirX) * (_sp0));
        float _t36 = ((dirZ) * (_t27) - (dirY * _t28));
        float _t37 = ((dirY) * (_t29) - (dirX * _t27));
        float _t38 = ((dirX) * (_t28) - (dirZ * _t29));
        float _ct0 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36))));
        if (!(_ct0 > ((_t7) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 1.4551915E-11f) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        return lookAlong_lh_s36f6c823_1_mulAdd(dirX, dirY, dirZ, (FloatQuatImpl) dest, _t15, _t36, _t37, _t38, (1.0f / (float) java.lang.Math.sqrt(_ct0)), this.x, this.w, this.y, this.z, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16);
    }

    /**
     * Piece 2 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private FloatQuat lookAlong_lh_s36f6c823_1_fma(float dirX, float dirY, float dirZ, FloatQuatImpl d, float _t15, float _t36, float _t37, float _t38, float _t45, float _r0, float _r1, float _r2, float _r3, float _t0, float _t16, float _t17, float _t18, float _t19, float _t21) {
        float _t46 = _t36 * _t45;
        float _t47 = _t37 * _t45;
        float _t48 = _t38 * _t45;
        float _t49 = java.lang.Math.fma(-_t36, _t45, 1.0f);
        float _t64 = java.lang.Math.fma(_t17, _t48, -(_t18 * _t46));
        float _t65 = java.lang.Math.fma(_t18, _t47, -(_t16 * _t48));
        float _t77 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(dirZ, _t15, 1.0f))));
        float _t79 = java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, java.lang.Math.fma(_t0, _t15, 1.0f))));
        float _t80 = java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, _t49)));
        return lookAlong_lh_s36f6c823_2_fma(dirZ, d, _t15, _t36, _t45, _r0, _r1, _r2, _r3, _t16, _t46, java.lang.Math.fma(dirX, _t15, _t47), java.lang.Math.fma(_t37, _t45, _t19), java.lang.Math.fma(_t16, _t46, -(_t17 * _t47)), java.lang.Math.fma(dirY, _t15, _t64), java.lang.Math.fma(dirY, _t15, -_t64), java.lang.Math.fma(_t38, _t45, _t65), java.lang.Math.fma(-_t38, _t45, _t65), _t77, _t79, _t80, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t77)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t0, _t15, _t49))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)));
    }

    /**
     * Piece 2 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private FloatQuat lookAlong_lh_s36f6c823_1_mulAdd(float dirX, float dirY, float dirZ, FloatQuatImpl d, float _t15, float _t36, float _t37, float _t38, float _t45, float _r0, float _r1, float _r2, float _r3, float _t0, float _t16, float _t17, float _t18, float _t19, float _t21) {
        float _t46 = _t36 * _t45;
        float _t47 = _t37 * _t45;
        float _t48 = _t38 * _t45;
        float _t49 = ((1.0f) - (_t36) * (_t45));
        float _t64 = ((_t17) * (_t48) - (_t18 * _t46));
        float _t65 = ((_t18) * (_t47) - (_t16 * _t48));
        float _t77 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t36) * (_t45) + (((dirZ) * (_t15) + (1.0f))))))));
        float _t79 = ((_t36) * (_t45) + (((_t21) * (_t46) + (((_t17) * (_t47) + (((_t0) * (_t15) + (1.0f))))))));
        float _t80 = ((dirZ) * (_t15) + (((_t21) * (_t46) + (((_t17) * (_t47) + (_t49))))));
        return lookAlong_lh_s36f6c823_2_mulAdd(dirZ, d, _t15, _t36, _t45, _r0, _r1, _r2, _r3, _t16, _t46, ((dirX) * (_t15) + (_t47)), ((_t37) * (_t45) + (_t19)), ((_t16) * (_t46) - (_t17 * _t47)), ((dirY) * (_t15) + (_t64)), ((dirY) * (_t15) - (_t64)), ((_t38) * (_t45) + (_t65)), ((_t65) - (_t38) * (_t45)), _t77, _t79, _t80, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t77)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t0) * (_t15) + (_t49)))))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)));
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private FloatQuat lookAlong_lh_s36f6c823_2_fma(float dirZ, FloatQuatImpl d, float _t15, float _t36, float _t45, float _r0, float _r1, float _r2, float _r3, float _t16, float _t46, float _t50, float _t54, float _t62, float _t68, float _t70, float _t73, float _t74, float _t77, float _t79, float _t80, float _sp1, float _sp4, float _t83, float _sp3) {
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
        d.x = java.lang.Math.fma(_r0, _t128, _r1 * _t125) + java.lang.Math.fma(_r2, _t126, -(_r3 * _t127));
        d.y = java.lang.Math.fma(_r2, _t128, _r3 * _t125) + java.lang.Math.fma(_r1, _t127, -_r0 * _t126);
        return lookAlong_lh_s36f6c823_3_fma(d, _r0, _r1, _r2, _r3, _t125, _t126, _t127, _t128);
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private FloatQuat lookAlong_lh_s36f6c823_2_mulAdd(float dirZ, FloatQuatImpl d, float _t15, float _t36, float _t45, float _r0, float _r1, float _r2, float _r3, float _t16, float _t46, float _t50, float _t54, float _t62, float _t68, float _t70, float _t73, float _t74, float _t77, float _t79, float _t80, float _sp1, float _sp4, float _t83, float _sp3) {
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
        d.x = ((_r0) * (_t128) + (_r1 * _t125)) + ((_r2) * (_t126) - (_r3 * _t127));
        d.y = ((_r2) * (_t128) + (_r3 * _t125)) + ((_r1) * (_t127) - (_r0 * _t126));
        return lookAlong_lh_s36f6c823_3_mulAdd(d, _r0, _r1, _r2, _r3, _t125, _t126, _t127, _t128);
    }

    /**
     * Piece 4 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private FloatQuat lookAlong_lh_s36f6c823_3_fma(FloatQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t125, float _t126, float _t127, float _t128) {
        d.z = java.lang.Math.fma(_r0, _t127, _r1 * _t126) + java.lang.Math.fma(_r3, _t128, -(_r2 * _t125));
        d.w = java.lang.Math.fma(_r1, _t128, -_r0 * _t125) - java.lang.Math.fma(_r2, _t127, _r3 * _t126);
        return d;
    }

    /**
     * Piece 4 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private FloatQuat lookAlong_lh_s36f6c823_3_mulAdd(FloatQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t125, float _t126, float _t127, float _t128) {
        d.z = ((_r0) * (_t127) + (_r1 * _t126)) + ((_r3) * (_t128) - (_r2 * _t125));
        d.w = ((_r1) * (_t128) - (_r0 * _t125)) - ((_r2) * (_t127) + (_r3 * _t126));
        return d;
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.LEFT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatQuat lookAlong_lh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        return lookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ, Joml.RETURN_NEW ? Joml.floatQuat() : this);
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.LEFT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatQuat lookAlong_lh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        return lookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, Joml.RETURN_NEW ? Joml.floatQuat() : this);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated DoubleQuat dest) {
        float _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t27 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t28 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t29 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t36 = java.lang.Math.fma(dirZ, _t27, -dirY * _t28);
        float _t37 = java.lang.Math.fma(dirY, _t29, -(dirX * _t27));
        float _t38 = java.lang.Math.fma(dirX, _t28, -(dirZ * _t29));
        float _ct0 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36));
        if (!(_ct0 > java.lang.Math.fma(_t7, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        return lookAlong_lh_sad3daae6_1_fma(dirX, dirY, dirZ, (DoubleQuatImpl) dest, _t15, _t36, _t37, _t38, (1.0f / (float) java.lang.Math.sqrt(_ct0)), this.x, this.w, this.y, this.z, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated DoubleQuat dest) {
        float _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t27 = ((upY) - (dirY) * (_sp0));
        float _t28 = ((upZ) - (dirZ) * (_sp0));
        float _t29 = ((upX) - (dirX) * (_sp0));
        float _t36 = ((dirZ) * (_t27) - (dirY * _t28));
        float _t37 = ((dirY) * (_t29) - (dirX * _t27));
        float _t38 = ((dirX) * (_t28) - (dirZ * _t29));
        float _ct0 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36))));
        if (!(_ct0 > ((_t7) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 1.4551915E-11f) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        return lookAlong_lh_sad3daae6_1_mulAdd(dirX, dirY, dirZ, (DoubleQuatImpl) dest, _t15, _t36, _t37, _t38, (1.0f / (float) java.lang.Math.sqrt(_ct0)), this.x, this.w, this.y, this.z, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16);
    }

    /**
     * Piece 2 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_sad3daae6_1_fma(float dirX, float dirY, float dirZ, DoubleQuatImpl d, float _t15, float _t36, float _t37, float _t38, float _t45, float _r0, float _r1, float _r2, float _r3, float _t0, float _t16, float _t17, float _t18, float _t19, float _t21) {
        float _t46 = _t36 * _t45;
        float _t47 = _t37 * _t45;
        float _t48 = _t38 * _t45;
        float _t49 = java.lang.Math.fma(-_t36, _t45, 1.0f);
        float _t64 = java.lang.Math.fma(_t17, _t48, -(_t18 * _t46));
        float _t65 = java.lang.Math.fma(_t18, _t47, -(_t16 * _t48));
        float _t77 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(dirZ, _t15, 1.0f))));
        float _t79 = java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, java.lang.Math.fma(_t0, _t15, 1.0f))));
        float _t80 = java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, _t49)));
        return lookAlong_lh_sad3daae6_2_fma(dirZ, d, _t15, _t36, _t45, _r0, _r1, _r2, _r3, _t16, _t46, java.lang.Math.fma(dirX, _t15, _t47), java.lang.Math.fma(_t37, _t45, _t19), java.lang.Math.fma(_t16, _t46, -(_t17 * _t47)), java.lang.Math.fma(dirY, _t15, _t64), java.lang.Math.fma(dirY, _t15, -_t64), java.lang.Math.fma(_t38, _t45, _t65), java.lang.Math.fma(-_t38, _t45, _t65), _t77, _t79, _t80, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t77)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t0, _t15, _t49))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)));
    }

    /**
     * Piece 2 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_sad3daae6_1_mulAdd(float dirX, float dirY, float dirZ, DoubleQuatImpl d, float _t15, float _t36, float _t37, float _t38, float _t45, float _r0, float _r1, float _r2, float _r3, float _t0, float _t16, float _t17, float _t18, float _t19, float _t21) {
        float _t46 = _t36 * _t45;
        float _t47 = _t37 * _t45;
        float _t48 = _t38 * _t45;
        float _t49 = ((1.0f) - (_t36) * (_t45));
        float _t64 = ((_t17) * (_t48) - (_t18 * _t46));
        float _t65 = ((_t18) * (_t47) - (_t16 * _t48));
        float _t77 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t36) * (_t45) + (((dirZ) * (_t15) + (1.0f))))))));
        float _t79 = ((_t36) * (_t45) + (((_t21) * (_t46) + (((_t17) * (_t47) + (((_t0) * (_t15) + (1.0f))))))));
        float _t80 = ((dirZ) * (_t15) + (((_t21) * (_t46) + (((_t17) * (_t47) + (_t49))))));
        return lookAlong_lh_sad3daae6_2_mulAdd(dirZ, d, _t15, _t36, _t45, _r0, _r1, _r2, _r3, _t16, _t46, ((dirX) * (_t15) + (_t47)), ((_t37) * (_t45) + (_t19)), ((_t16) * (_t46) - (_t17 * _t47)), ((dirY) * (_t15) + (_t64)), ((dirY) * (_t15) - (_t64)), ((_t38) * (_t45) + (_t65)), ((_t65) - (_t38) * (_t45)), _t77, _t79, _t80, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t77)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t0) * (_t15) + (_t49)))))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)));
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_sad3daae6_2_fma(float dirZ, DoubleQuatImpl d, float _t15, float _t36, float _t45, float _r0, float _r1, float _r2, float _r3, float _t16, float _t46, float _t50, float _t54, float _t62, float _t68, float _t70, float _t73, float _t74, float _t77, float _t79, float _t80, float _sp1, float _sp4, float _t83, float _sp3) {
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
        d.x = java.lang.Math.fma(_r0, _t128, _r1 * _t125) + java.lang.Math.fma(_r2, _t126, -(_r3 * _t127));
        d.y = java.lang.Math.fma(_r2, _t128, _r3 * _t125) + java.lang.Math.fma(_r1, _t127, -_r0 * _t126);
        return lookAlong_lh_sad3daae6_3_fma(d, _r0, _r1, _r2, _r3, _t125, _t126, _t127, _t128);
    }

    /**
     * Piece 3 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_sad3daae6_2_mulAdd(float dirZ, DoubleQuatImpl d, float _t15, float _t36, float _t45, float _r0, float _r1, float _r2, float _r3, float _t16, float _t46, float _t50, float _t54, float _t62, float _t68, float _t70, float _t73, float _t74, float _t77, float _t79, float _t80, float _sp1, float _sp4, float _t83, float _sp3) {
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
        d.x = ((_r0) * (_t128) + (_r1 * _t125)) + ((_r2) * (_t126) - (_r3 * _t127));
        d.y = ((_r2) * (_t128) + (_r3 * _t125)) + ((_r1) * (_t127) - (_r0 * _t126));
        return lookAlong_lh_sad3daae6_3_mulAdd(d, _r0, _r1, _r2, _r3, _t125, _t126, _t127, _t128);
    }

    /**
     * Piece 4 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_sad3daae6_3_fma(DoubleQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t125, float _t126, float _t127, float _t128) {
        d.z = java.lang.Math.fma(_r0, _t127, _r1 * _t126) + java.lang.Math.fma(_r3, _t128, -(_r2 * _t125));
        d.w = java.lang.Math.fma(_r1, _t128, -_r0 * _t125) - java.lang.Math.fma(_r2, _t127, _r3 * _t126);
        return d;
    }

    /**
     * Piece 4 of {@code lookAlong_lh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_sad3daae6_3_mulAdd(DoubleQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t125, float _t126, float _t127, float _t128) {
        d.z = ((_r0) * (_t127) + (_r1 * _t126)) + ((_r3) * (_t128) - (_r2 * _t125));
        d.w = ((_r1) * (_t128) - (_r0 * _t125)) - ((_r2) * (_t127) + (_r3 * _t126));
        return d;
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatQuat lookAlong_lh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated FloatQuat dest) {
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
        return lookAlong_lh_degenerate_sfa10eae0_1_fma((FloatQuatImpl) dest, this.x, this.w, this.y, this.z, _t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, _t45, _t46, java.lang.Math.fma(_t44, _t24, _t21), java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatQuat lookAlong_lh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated FloatQuat dest) {
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
        return lookAlong_lh_degenerate_sfa10eae0_1_mulAdd((FloatQuatImpl) dest, this.x, this.w, this.y, this.z, _t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, _t45, _t46, ((_t44) * (_t24) + (_t21)), ((_t45) * (_t26) - (_t46 * _t25)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatQuat lookAlong_lh_degenerate_sfa10eae0_1_fma(FloatQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47, float _t56) {
        float _t57 = java.lang.Math.fma(_t47, _t25, _t45 * -_t24);
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
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return lookAlong_lh_degenerate_sfa10eae0_2_fma(d, _r0, _r1, _r2, _r3, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatQuat lookAlong_lh_degenerate_sfa10eae0_1_mulAdd(FloatQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47, float _t56) {
        float _t57 = ((_t47) * (_t25) - (_t45 * _t24));
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
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return lookAlong_lh_degenerate_sfa10eae0_2_mulAdd(d, _r0, _r1, _r2, _r3, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))));
    }

    /**
     * Piece 3 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private FloatQuat lookAlong_lh_degenerate_sfa10eae0_2_fma(FloatQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t24, float _t25, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t78, float _t89, float _t93, float _t96, float _t97) {
        float _t99 = java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t32)));
        float _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, _t33)));
        float _t103 = java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t68, _t64, _t33)));
        float _t104 = java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t68, _t64, _t32)));
        return lookAlong_lh_degenerate_sfa10eae0_3_fma(d, _r0, _r1, _r2, _r3, _t24, _t25, _t64, _t67, _t70, _t71, _t74, _t78, _t89, _t96, _t97, _t99, _t100, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t104)), java.lang.Math.fma(_t67, _t65, _t93), java.lang.Math.fma(_t68, _t65, _t93));
    }

    /**
     * Piece 3 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private FloatQuat lookAlong_lh_degenerate_sfa10eae0_2_mulAdd(FloatQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t24, float _t25, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t78, float _t89, float _t93, float _t96, float _t97) {
        float _t99 = ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t32))))));
        float _t100 = ((_t67) * (_t64) + (((_t72) * (_t24) + (((_t69) * (_t25) + (_t33))))));
        float _t103 = ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t68) * (_t64) + (_t33))))));
        float _t104 = ((_t72) * (_t24) + (((_t69) * (_t25) + (((_t68) * (_t64) + (_t32))))));
        return lookAlong_lh_degenerate_sfa10eae0_3_mulAdd(d, _r0, _r1, _r2, _r3, _t24, _t25, _t64, _t67, _t70, _t71, _t74, _t78, _t89, _t96, _t97, _t99, _t100, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t104)), ((_t67) * (_t65) + (_t93)), ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Piece 4 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private FloatQuat lookAlong_lh_degenerate_sfa10eae0_3_fma(FloatQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t24, float _t25, float _t64, float _t67, float _t70, float _t71, float _t74, float _t78, float _t89, float _t96, float _t97, float _t99, float _t100, float _sp3, float _t103, float _t104, float _sp0, float _sp1, float _sp2, float _t115, float _t116) {
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
    private FloatQuat lookAlong_lh_degenerate_sfa10eae0_3_mulAdd(FloatQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t24, float _t25, float _t64, float _t67, float _t70, float _t71, float _t74, float _t78, float _t89, float _t96, float _t97, float _t99, float _t100, float _sp3, float _t103, float _t104, float _sp0, float _sp1, float _sp2, float _t115, float _t116) {
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
        d.x = ((_r0) * (_t152) + (_r1 * _t149)) + ((_r2) * (_t150) - (_r3 * _t151));
        d.y = ((_r2) * (_t152) + (_r3 * _t149)) + ((_r1) * (_t151) - (_r0 * _t150));
        d.z = ((_r0) * (_t151) + (_r1 * _t150)) + ((_r3) * (_t152) - (_r2 * _t149));
        d.w = ((_r1) * (_t152) - (_r0 * _t149)) - ((_r2) * (_t151) + (_r3 * _t150));
        return d;
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleQuat lookAlong_lh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated DoubleQuat dest) {
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
        return lookAlong_lh_degenerate_s447fcca7_1_fma((DoubleQuatImpl) dest, this.x, this.w, this.y, this.z, _t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, _t45, _t46, java.lang.Math.fma(_t44, _t24, _t21), java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleQuat lookAlong_lh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated DoubleQuat dest) {
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
        return lookAlong_lh_degenerate_s447fcca7_1_mulAdd((DoubleQuatImpl) dest, this.x, this.w, this.y, this.z, _t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, _t45, _t46, ((_t44) * (_t24) + (_t21)), ((_t45) * (_t26) - (_t46 * _t25)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_lh_degenerate_s447fcca7_1_fma(DoubleQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47, float _t56) {
        float _t57 = java.lang.Math.fma(_t47, _t25, _t45 * -_t24);
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
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return lookAlong_lh_degenerate_s447fcca7_2_fma(d, _r0, _r1, _r2, _r3, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_lh_degenerate_s447fcca7_1_mulAdd(DoubleQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47, float _t56) {
        float _t57 = ((_t47) * (_t25) - (_t45 * _t24));
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
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return lookAlong_lh_degenerate_s447fcca7_2_mulAdd(d, _r0, _r1, _r2, _r3, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))));
    }

    /**
     * Piece 3 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_degenerate_s447fcca7_2_fma(DoubleQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t24, float _t25, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t78, float _t89, float _t93, float _t96, float _t97) {
        float _t99 = java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t32)));
        float _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, _t33)));
        float _t103 = java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t68, _t64, _t33)));
        float _t104 = java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t68, _t64, _t32)));
        return lookAlong_lh_degenerate_s447fcca7_3_fma(d, _r0, _r1, _r2, _r3, _t24, _t25, _t64, _t67, _t70, _t71, _t74, _t78, _t89, _t96, _t97, _t99, _t100, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t104)), java.lang.Math.fma(_t67, _t65, _t93), java.lang.Math.fma(_t68, _t65, _t93));
    }

    /**
     * Piece 3 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_degenerate_s447fcca7_2_mulAdd(DoubleQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t24, float _t25, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t78, float _t89, float _t93, float _t96, float _t97) {
        float _t99 = ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t32))))));
        float _t100 = ((_t67) * (_t64) + (((_t72) * (_t24) + (((_t69) * (_t25) + (_t33))))));
        float _t103 = ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t68) * (_t64) + (_t33))))));
        float _t104 = ((_t72) * (_t24) + (((_t69) * (_t25) + (((_t68) * (_t64) + (_t32))))));
        return lookAlong_lh_degenerate_s447fcca7_3_mulAdd(d, _r0, _r1, _r2, _r3, _t24, _t25, _t64, _t67, _t70, _t71, _t74, _t78, _t89, _t96, _t97, _t99, _t100, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t104)), ((_t67) * (_t65) + (_t93)), ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Piece 4 of {@code lookAlong_lh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_lh_degenerate_s447fcca7_3_fma(DoubleQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t24, float _t25, float _t64, float _t67, float _t70, float _t71, float _t74, float _t78, float _t89, float _t96, float _t97, float _t99, float _t100, float _sp3, float _t103, float _t104, float _sp0, float _sp1, float _sp2, float _t115, float _t116) {
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
    private DoubleQuat lookAlong_lh_degenerate_s447fcca7_3_mulAdd(DoubleQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t24, float _t25, float _t64, float _t67, float _t70, float _t71, float _t74, float _t78, float _t89, float _t96, float _t97, float _t99, float _t100, float _sp3, float _t103, float _t104, float _sp0, float _sp1, float _sp2, float _t115, float _t116) {
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
    private FloatQuat lookAlong_rh_fma(Float3R dir, Float3R up, @Mutated FloatQuat dest) {
        return lookAlong_rh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatQuat lookAlong_rh_mulAdd(Float3R dir, Float3R up, @Mutated FloatQuat dest) {
        return lookAlong_rh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_fma(Float3R dir, Float3R up, @Mutated DoubleQuat dest) {
        return lookAlong_rh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_mulAdd(Float3R dir, Float3R up, @Mutated DoubleQuat dest) {
        return lookAlong_rh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatQuat lookAlong_rh_fma(Float3R dir, Float3R up) {
        return lookAlong_rh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatQuat lookAlong_rh_mulAdd(Float3R dir, Float3R up) {
        return lookAlong_rh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatQuat lookAlong_rh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated FloatQuat dest) {
        float _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t30 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t31 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t38 = java.lang.Math.fma(dirY, _t29, -(dirZ * _t30));
        float _t39 = java.lang.Math.fma(dirX, _t30, -(dirY * _t31));
        float _t40 = java.lang.Math.fma(dirZ, _t31, -dirX * _t29);
        float _ct0 = java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40));
        if (!(_ct0 > java.lang.Math.fma(_t8, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        return lookAlong_rh_s618fce05_1_fma(dirX, dirY, dirZ, (FloatQuatImpl) dest, _t16, _t38, _t39, _t40, (1.0f / (float) java.lang.Math.sqrt(_ct0)), this.x, this.w, this.y, this.z, -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatQuat lookAlong_rh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated FloatQuat dest) {
        float _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t29 = ((upZ) - (dirZ) * (_sp0));
        float _t30 = ((upY) - (dirY) * (_sp0));
        float _t31 = ((upX) - (dirX) * (_sp0));
        float _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        float _t39 = ((dirX) * (_t30) - (dirY * _t31));
        float _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        float _ct0 = ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40))));
        if (!(_ct0 > ((_t8) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 1.4551915E-11f) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        return lookAlong_rh_s618fce05_1_mulAdd(dirX, dirY, dirZ, (FloatQuatImpl) dest, _t16, _t38, _t39, _t40, (1.0f / (float) java.lang.Math.sqrt(_ct0)), this.x, this.w, this.y, this.z, -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17);
    }

    /**
     * Piece 2 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private FloatQuat lookAlong_rh_s618fce05_1_fma(float dirX, float dirY, float dirZ, FloatQuatImpl d, float _t16, float _t38, float _t39, float _t40, float _t47, float _r0, float _r1, float _r2, float _r3, float _t0, float _t17, float _t18, float _t19, float _t20, float _t22) {
        float _t23 = java.lang.Math.fma(dirZ, _t16, 1.0f);
        float _t41 = -_t38;
        float _t48 = _t39 * _t47;
        float _t49 = _t38 * _t47;
        float _t50 = _t40 * _t47;
        float _t65 = java.lang.Math.fma(_t19, _t49, -(_t17 * _t50));
        float _t66 = java.lang.Math.fma(_t18, _t50, -_t19 * _t48);
        float _t78 = java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, _t23)));
        float _t79 = java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t20, _t49, java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t0, _t16, 1.0f))));
        float _t81 = java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t20, _t49, java.lang.Math.fma(_t41, _t47, _t23)));
        return lookAlong_rh_s618fce05_2_fma(d, _t16, _t38, _t47, _r0, _r1, _r2, _r3, _t0, _t20, _t49, java.lang.Math.fma(dirX, _t16, _t48), java.lang.Math.fma(_t39, _t47, _t22), java.lang.Math.fma(_t17, _t48, -(_t18 * _t49)), java.lang.Math.fma(-dirY, _t16, _t65), -_t19 - _t65, java.lang.Math.fma(_t40, _t47, _t66), java.lang.Math.fma(-_t40, _t47, _t66), _t78, _t79, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), _t81, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)), java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, java.lang.Math.fma(_t41, _t47, 1.0f)))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)));
    }

    /**
     * Piece 2 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private FloatQuat lookAlong_rh_s618fce05_1_mulAdd(float dirX, float dirY, float dirZ, FloatQuatImpl d, float _t16, float _t38, float _t39, float _t40, float _t47, float _r0, float _r1, float _r2, float _r3, float _t0, float _t17, float _t18, float _t19, float _t20, float _t22) {
        float _t23 = ((dirZ) * (_t16) + (1.0f));
        float _t41 = -_t38;
        float _t48 = _t39 * _t47;
        float _t49 = _t38 * _t47;
        float _t50 = _t40 * _t47;
        float _t65 = ((_t19) * (_t49) - (_t17 * _t50));
        float _t66 = ((_t18) * (_t50) - (_t19 * _t48));
        float _t78 = ((_t38) * (_t47) + (((_t22) * (_t48) + (((_t18) * (_t49) + (_t23))))));
        float _t79 = ((_t17) * (_t48) + (((_t20) * (_t49) + (((_t38) * (_t47) + (((_t0) * (_t16) + (1.0f))))))));
        float _t81 = ((_t17) * (_t48) + (((_t20) * (_t49) + (((_t41) * (_t47) + (_t23))))));
        return lookAlong_rh_s618fce05_2_mulAdd(d, _t16, _t38, _t47, _r0, _r1, _r2, _r3, _t0, _t20, _t49, ((dirX) * (_t16) + (_t48)), ((_t39) * (_t47) + (_t22)), ((_t17) * (_t48) - (_t18 * _t49)), ((_t65) - (dirY) * (_t16)), -_t19 - _t65, ((_t40) * (_t47) + (_t66)), ((_t66) - (_t40) * (_t47)), _t78, _t79, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), _t81, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)), ((_t0) * (_t16) + (((_t22) * (_t48) + (((_t18) * (_t49) + (((_t41) * (_t47) + (1.0f)))))))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)));
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private FloatQuat lookAlong_rh_s618fce05_2_fma(FloatQuatImpl d, float _t16, float _t38, float _t47, float _r0, float _r1, float _r2, float _r3, float _t0, float _t20, float _t49, float _t52, float _t56, float _t64, float _t70, float _t72, float _t74, float _t75, float _t78, float _t79, float _sp2, float _t81, float _sp4, float _t85, float _sp1) {
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
        d.x = java.lang.Math.fma(_r0, _t127, _r1 * _t128) + java.lang.Math.fma(_r2, _t129, -(_r3 * _t126));
        d.y = java.lang.Math.fma(_r2, _t127, _r3 * _t128) + java.lang.Math.fma(_r1, _t126, -_r0 * _t129);
        return lookAlong_rh_s618fce05_3_fma(d, _r0, _r1, _r2, _r3, _t126, _t127, _t128, _t129);
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private FloatQuat lookAlong_rh_s618fce05_2_mulAdd(FloatQuatImpl d, float _t16, float _t38, float _t47, float _r0, float _r1, float _r2, float _r3, float _t0, float _t20, float _t49, float _t52, float _t56, float _t64, float _t70, float _t72, float _t74, float _t75, float _t78, float _t79, float _sp2, float _t81, float _sp4, float _t85, float _sp1) {
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
        d.x = ((_r0) * (_t127) + (_r1 * _t128)) + ((_r2) * (_t129) - (_r3 * _t126));
        d.y = ((_r2) * (_t127) + (_r3 * _t128)) + ((_r1) * (_t126) - (_r0 * _t129));
        return lookAlong_rh_s618fce05_3_mulAdd(d, _r0, _r1, _r2, _r3, _t126, _t127, _t128, _t129);
    }

    /**
     * Piece 4 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private FloatQuat lookAlong_rh_s618fce05_3_fma(FloatQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t126, float _t127, float _t128, float _t129) {
        d.z = java.lang.Math.fma(_r0, _t126, _r1 * _t129) + java.lang.Math.fma(_r3, _t127, -(_r2 * _t128));
        d.w = java.lang.Math.fma(_r1, _t127, -_r0 * _t128) - java.lang.Math.fma(_r2, _t126, _r3 * _t129);
        return d;
    }

    /**
     * Piece 4 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private FloatQuat lookAlong_rh_s618fce05_3_mulAdd(FloatQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t126, float _t127, float _t128, float _t129) {
        d.z = ((_r0) * (_t126) + (_r1 * _t129)) + ((_r3) * (_t127) - (_r2 * _t128));
        d.w = ((_r1) * (_t127) - (_r0 * _t128)) - ((_r2) * (_t126) + (_r3 * _t129));
        return d;
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatQuat lookAlong_rh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        return lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, Joml.RETURN_NEW ? Joml.floatQuat() : this);
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatQuat lookAlong_rh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        return lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, Joml.RETURN_NEW ? Joml.floatQuat() : this);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated DoubleQuat dest) {
        float _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t30 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t31 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t38 = java.lang.Math.fma(dirY, _t29, -(dirZ * _t30));
        float _t39 = java.lang.Math.fma(dirX, _t30, -(dirY * _t31));
        float _t40 = java.lang.Math.fma(dirZ, _t31, -dirX * _t29);
        float _ct0 = java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40));
        if (!(_ct0 > java.lang.Math.fma(_t8, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        return lookAlong_rh_s4d54d694_1_fma(dirX, dirY, dirZ, (DoubleQuatImpl) dest, _t16, _t38, _t39, _t40, (1.0f / (float) java.lang.Math.sqrt(_ct0)), this.x, this.w, this.y, this.z, -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated DoubleQuat dest) {
        float _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t29 = ((upZ) - (dirZ) * (_sp0));
        float _t30 = ((upY) - (dirY) * (_sp0));
        float _t31 = ((upX) - (dirX) * (_sp0));
        float _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        float _t39 = ((dirX) * (_t30) - (dirY * _t31));
        float _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        float _ct0 = ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40))));
        if (!(_ct0 > ((_t8) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 1.4551915E-11f) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, dest);
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        return lookAlong_rh_s4d54d694_1_mulAdd(dirX, dirY, dirZ, (DoubleQuatImpl) dest, _t16, _t38, _t39, _t40, (1.0f / (float) java.lang.Math.sqrt(_ct0)), this.x, this.w, this.y, this.z, -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17);
    }

    /**
     * Piece 2 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_s4d54d694_1_fma(float dirX, float dirY, float dirZ, DoubleQuatImpl d, float _t16, float _t38, float _t39, float _t40, float _t47, float _r0, float _r1, float _r2, float _r3, float _t0, float _t17, float _t18, float _t19, float _t20, float _t22) {
        float _t23 = java.lang.Math.fma(dirZ, _t16, 1.0f);
        float _t41 = -_t38;
        float _t48 = _t39 * _t47;
        float _t49 = _t38 * _t47;
        float _t50 = _t40 * _t47;
        float _t65 = java.lang.Math.fma(_t19, _t49, -(_t17 * _t50));
        float _t66 = java.lang.Math.fma(_t18, _t50, -_t19 * _t48);
        float _t78 = java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, _t23)));
        float _t79 = java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t20, _t49, java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t0, _t16, 1.0f))));
        float _t81 = java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t20, _t49, java.lang.Math.fma(_t41, _t47, _t23)));
        return lookAlong_rh_s4d54d694_2_fma(d, _t16, _t38, _t47, _r0, _r1, _r2, _r3, _t0, _t20, _t49, java.lang.Math.fma(dirX, _t16, _t48), java.lang.Math.fma(_t39, _t47, _t22), java.lang.Math.fma(_t17, _t48, -(_t18 * _t49)), java.lang.Math.fma(-dirY, _t16, _t65), -_t19 - _t65, java.lang.Math.fma(_t40, _t47, _t66), java.lang.Math.fma(-_t40, _t47, _t66), _t78, _t79, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), _t81, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)), java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, java.lang.Math.fma(_t41, _t47, 1.0f)))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)));
    }

    /**
     * Piece 2 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_s4d54d694_1_mulAdd(float dirX, float dirY, float dirZ, DoubleQuatImpl d, float _t16, float _t38, float _t39, float _t40, float _t47, float _r0, float _r1, float _r2, float _r3, float _t0, float _t17, float _t18, float _t19, float _t20, float _t22) {
        float _t23 = ((dirZ) * (_t16) + (1.0f));
        float _t41 = -_t38;
        float _t48 = _t39 * _t47;
        float _t49 = _t38 * _t47;
        float _t50 = _t40 * _t47;
        float _t65 = ((_t19) * (_t49) - (_t17 * _t50));
        float _t66 = ((_t18) * (_t50) - (_t19 * _t48));
        float _t78 = ((_t38) * (_t47) + (((_t22) * (_t48) + (((_t18) * (_t49) + (_t23))))));
        float _t79 = ((_t17) * (_t48) + (((_t20) * (_t49) + (((_t38) * (_t47) + (((_t0) * (_t16) + (1.0f))))))));
        float _t81 = ((_t17) * (_t48) + (((_t20) * (_t49) + (((_t41) * (_t47) + (_t23))))));
        return lookAlong_rh_s4d54d694_2_mulAdd(d, _t16, _t38, _t47, _r0, _r1, _r2, _r3, _t0, _t20, _t49, ((dirX) * (_t16) + (_t48)), ((_t39) * (_t47) + (_t22)), ((_t17) * (_t48) - (_t18 * _t49)), ((_t65) - (dirY) * (_t16)), -_t19 - _t65, ((_t40) * (_t47) + (_t66)), ((_t66) - (_t40) * (_t47)), _t78, _t79, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), _t81, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)), ((_t0) * (_t16) + (((_t22) * (_t48) + (((_t18) * (_t49) + (((_t41) * (_t47) + (1.0f)))))))), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)));
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_s4d54d694_2_fma(DoubleQuatImpl d, float _t16, float _t38, float _t47, float _r0, float _r1, float _r2, float _r3, float _t0, float _t20, float _t49, float _t52, float _t56, float _t64, float _t70, float _t72, float _t74, float _t75, float _t78, float _t79, float _sp2, float _t81, float _sp4, float _t85, float _sp1) {
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
        d.x = java.lang.Math.fma(_r0, _t127, _r1 * _t128) + java.lang.Math.fma(_r2, _t129, -(_r3 * _t126));
        d.y = java.lang.Math.fma(_r2, _t127, _r3 * _t128) + java.lang.Math.fma(_r1, _t126, -_r0 * _t129);
        return lookAlong_rh_s4d54d694_3_fma(d, _r0, _r1, _r2, _r3, _t126, _t127, _t128, _t129);
    }

    /**
     * Piece 3 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_s4d54d694_2_mulAdd(DoubleQuatImpl d, float _t16, float _t38, float _t47, float _r0, float _r1, float _r2, float _r3, float _t0, float _t20, float _t49, float _t52, float _t56, float _t64, float _t70, float _t72, float _t74, float _t75, float _t78, float _t79, float _sp2, float _t81, float _sp4, float _t85, float _sp1) {
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
        d.x = ((_r0) * (_t127) + (_r1 * _t128)) + ((_r2) * (_t129) - (_r3 * _t126));
        d.y = ((_r2) * (_t127) + (_r3 * _t128)) + ((_r1) * (_t126) - (_r0 * _t129));
        return lookAlong_rh_s4d54d694_3_mulAdd(d, _r0, _r1, _r2, _r3, _t126, _t127, _t128, _t129);
    }

    /**
     * Piece 4 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_s4d54d694_3_fma(DoubleQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t126, float _t127, float _t128, float _t129) {
        d.z = java.lang.Math.fma(_r0, _t126, _r1 * _t129) + java.lang.Math.fma(_r3, _t127, -(_r2 * _t128));
        d.w = java.lang.Math.fma(_r1, _t127, -_r0 * _t128) - java.lang.Math.fma(_r2, _t126, _r3 * _t129);
        return d;
    }

    /**
     * Piece 4 of {@code lookAlong_rh}, split to fit the inline budget. Shared by 2 identical
     * private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_s4d54d694_3_mulAdd(DoubleQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t126, float _t127, float _t128, float _t129) {
        d.z = ((_r0) * (_t126) + (_r1 * _t129)) + ((_r3) * (_t127) - (_r2 * _t128));
        d.w = ((_r1) * (_t127) - (_r0 * _t128)) - ((_r2) * (_t126) + (_r3 * _t129));
        return d;
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatQuat lookAlong_rh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated FloatQuat dest) {
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
        return lookAlong_rh_degenerate_s346b19b6_1_fma((FloatQuatImpl) dest, this.x, this.w, this.y, this.z, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, java.lang.Math.fma(_t44, _t26, _t23), java.lang.Math.fma(_t44, _t25, _t22), java.lang.Math.fma(_t44, _t24, _t21));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatQuat lookAlong_rh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated FloatQuat dest) {
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
        return lookAlong_rh_degenerate_s346b19b6_1_mulAdd((FloatQuatImpl) dest, this.x, this.w, this.y, this.z, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, ((_t44) * (_t26) + (_t23)), ((_t44) * (_t25) + (_t22)), ((_t44) * (_t24) + (_t21)));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatQuat lookAlong_rh_degenerate_s346b19b6_1_fma(FloatQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47) {
        float _t56 = java.lang.Math.fma(_t45, _t25, _t46 * -_t26);
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
        return lookAlong_rh_degenerate_s346b19b6_2_fma(d, _r0, _r1, _r2, _r3, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t69, _t25, -(_t70 * _t24)), java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)), java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatQuat lookAlong_rh_degenerate_s346b19b6_1_mulAdd(FloatQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47) {
        float _t56 = ((_t45) * (_t25) - (_t46 * _t26));
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
        return lookAlong_rh_degenerate_s346b19b6_2_mulAdd(d, _r0, _r1, _r2, _r3, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t69) * (_t25) - (_t70 * _t24)), ((_t73) * (_t24) - (_t69 * _t26)), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))), ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))));
    }

    /**
     * Piece 3 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private FloatQuat lookAlong_rh_degenerate_s346b19b6_2_fma(FloatQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t24, float _t25, float _t30, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t76, float _t88, float _t93, float _t96, float _t97) {
        float _t99 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t32)));
        float _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, _t33)));
        float _t103 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t68, _t64, _t33)));
        float _t104 = java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t68, _t64, _t32)));
        return lookAlong_rh_degenerate_s346b19b6_3_fma(d, _r0, _r1, _r2, _r3, _t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t104)), java.lang.Math.fma(_t67, _t65, _t93), java.lang.Math.fma(_t68, _t65, _t93));
    }

    /**
     * Piece 3 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private FloatQuat lookAlong_rh_degenerate_s346b19b6_2_mulAdd(FloatQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t24, float _t25, float _t30, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t76, float _t88, float _t93, float _t96, float _t97) {
        float _t99 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t32))))));
        float _t100 = ((_t67) * (_t64) + (((_t71) * (_t25) + (((_t70) * (_t24) + (_t33))))));
        float _t103 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t68) * (_t64) + (_t33))))));
        float _t104 = ((_t71) * (_t25) + (((_t70) * (_t24) + (((_t68) * (_t64) + (_t32))))));
        return lookAlong_rh_degenerate_s346b19b6_3_mulAdd(d, _r0, _r1, _r2, _r3, _t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t104)), ((_t67) * (_t65) + (_t93)), ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Piece 4 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private FloatQuat lookAlong_rh_degenerate_s346b19b6_3_fma(FloatQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t24, float _t25, float _t30, float _t64, float _t67, float _t69, float _t70, float _t72, float _t74, float _t76, float _t88, float _t96, float _t97, float _t99, float _t100, float _sp1, float _t103, float _t104, float _sp0, float _sp3, float _sp2, float _t115, float _t116) {
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
    private FloatQuat lookAlong_rh_degenerate_s346b19b6_3_mulAdd(FloatQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t24, float _t25, float _t30, float _t64, float _t67, float _t69, float _t70, float _t72, float _t74, float _t76, float _t88, float _t96, float _t97, float _t99, float _t100, float _sp1, float _t103, float _t104, float _sp0, float _sp3, float _sp2, float _t115, float _t116) {
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
        d.x = ((_r0) * (_t150) + (_r1 * _t151)) + ((_r2) * (_t152) - (_r3 * _t149));
        d.y = ((_r2) * (_t150) + (_r3 * _t151)) + ((_r1) * (_t149) - (_r0 * _t152));
        d.z = ((_r0) * (_t149) + (_r1 * _t152)) + ((_r3) * (_t150) - (_r2 * _t151));
        d.w = ((_r1) * (_t150) - (_r0 * _t151)) - ((_r2) * (_t149) + (_r3 * _t152));
        return d;
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleQuat lookAlong_rh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated DoubleQuat dest) {
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
        return lookAlong_rh_degenerate_s5e7bb289_1_fma((DoubleQuatImpl) dest, this.x, this.w, this.y, this.z, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, java.lang.Math.fma(_t44, _t26, _t23), java.lang.Math.fma(_t44, _t25, _t22), java.lang.Math.fma(_t44, _t24, _t21));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleQuat lookAlong_rh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated DoubleQuat dest) {
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
        return lookAlong_rh_degenerate_s5e7bb289_1_mulAdd((DoubleQuatImpl) dest, this.x, this.w, this.y, this.z, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, ((_t44) * (_t26) + (_t23)), ((_t44) * (_t25) + (_t22)), ((_t44) * (_t24) + (_t21)));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_rh_degenerate_s5e7bb289_1_fma(DoubleQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47) {
        float _t56 = java.lang.Math.fma(_t45, _t25, _t46 * -_t26);
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
        return lookAlong_rh_degenerate_s5e7bb289_2_fma(d, _r0, _r1, _r2, _r3, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t69, _t25, -(_t70 * _t24)), java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)), java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_rh_degenerate_s5e7bb289_1_mulAdd(DoubleQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47) {
        float _t56 = ((_t45) * (_t25) - (_t46 * _t26));
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
        return lookAlong_rh_degenerate_s5e7bb289_2_mulAdd(d, _r0, _r1, _r2, _r3, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t69) * (_t25) - (_t70 * _t24)), ((_t73) * (_t24) - (_t69 * _t26)), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))), ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))));
    }

    /**
     * Piece 3 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_degenerate_s5e7bb289_2_fma(DoubleQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t24, float _t25, float _t30, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t76, float _t88, float _t93, float _t96, float _t97) {
        float _t99 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t32)));
        float _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, _t33)));
        float _t103 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t68, _t64, _t33)));
        float _t104 = java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t68, _t64, _t32)));
        return lookAlong_rh_degenerate_s5e7bb289_3_fma(d, _r0, _r1, _r2, _r3, _t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t104)), java.lang.Math.fma(_t67, _t65, _t93), java.lang.Math.fma(_t68, _t65, _t93));
    }

    /**
     * Piece 3 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_degenerate_s5e7bb289_2_mulAdd(DoubleQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t24, float _t25, float _t30, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t76, float _t88, float _t93, float _t96, float _t97) {
        float _t99 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t32))))));
        float _t100 = ((_t67) * (_t64) + (((_t71) * (_t25) + (((_t70) * (_t24) + (_t33))))));
        float _t103 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t68) * (_t64) + (_t33))))));
        float _t104 = ((_t71) * (_t25) + (((_t70) * (_t24) + (((_t68) * (_t64) + (_t32))))));
        return lookAlong_rh_degenerate_s5e7bb289_3_mulAdd(d, _r0, _r1, _r2, _r3, _t24, _t25, _t30, _t64, _t67, _t69, _t70, _t72, _t74, _t76, _t88, _t96, _t97, _t99, _t100, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), _t103, _t104, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t104)), ((_t67) * (_t65) + (_t93)), ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Piece 4 of {@code lookAlong_rh_degenerate}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code lookAlong}; reached only through it.
     */
    private DoubleQuat lookAlong_rh_degenerate_s5e7bb289_3_fma(DoubleQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t24, float _t25, float _t30, float _t64, float _t67, float _t69, float _t70, float _t72, float _t74, float _t76, float _t88, float _t96, float _t97, float _t99, float _t100, float _sp1, float _t103, float _t104, float _sp0, float _sp3, float _sp2, float _t115, float _t116) {
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
    private DoubleQuat lookAlong_rh_degenerate_s5e7bb289_3_mulAdd(DoubleQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t24, float _t25, float _t30, float _t64, float _t67, float _t69, float _t70, float _t72, float _t74, float _t76, float _t88, float _t96, float _t97, float _t99, float _t100, float _sp1, float _t103, float _t104, float _sp0, float _sp3, float _sp2, float _t115, float _t116) {
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
    public FloatQuat lookAlong(Float3R dir, Float3R up, Handedness handedness, @Mutated FloatQuat dest) {
        if (Math.useFma()) {
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
    @Mutated public FloatQuat lookAlong(Float3R dir, Float3R up, Handedness handedness) {
        if (Math.useFma()) {
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
    public DoubleQuat lookAlong(Float3R dir, Float3R up, Handedness handedness, @Mutated DoubleQuat dest) {
        if (Math.useFma()) {
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
    public FloatQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, Handedness handedness, @Mutated FloatQuat dest) {
        if (Math.useFma()) {
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
    @Mutated public FloatQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, Handedness handedness) {
        if (Math.useFma()) {
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
    public DoubleQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, Handedness handedness, @Mutated DoubleQuat dest) {
        if (Math.useFma()) {
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
    public FloatQuat lookAlong(Float3R dir, Float3R up, @Mutated FloatQuat dest) { return lookAlong(dir, up, Handedness.RIGHT_HANDED, dest); }

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
    @Mutated public FloatQuat lookAlong(Float3R dir, Float3R up) { return lookAlong(dir, up, Handedness.RIGHT_HANDED); }

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
    public DoubleQuat lookAlong(Float3R dir, Float3R up, @Mutated DoubleQuat dest) { return lookAlong(dir, up, Handedness.RIGHT_HANDED, dest); }

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
    public FloatQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated FloatQuat dest) { return lookAlong(dirX, dirY, dirZ, upX, upY, upZ, Handedness.RIGHT_HANDED, dest); }

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
    @Mutated public FloatQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) { return lookAlong(dirX, dirY, dirZ, upX, upY, upZ, Handedness.RIGHT_HANDED); }

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
    public DoubleQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, @Mutated DoubleQuat dest) { return lookAlong(dirX, dirY, dirZ, upX, upY, upZ, Handedness.RIGHT_HANDED, dest); }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatQuat lookAlong_lh_fma(Float3R dir, Float3R up, float epsilon, @Mutated FloatQuat dest) {
        return lookAlong_lh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon, dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatQuat lookAlong_lh_mulAdd(Float3R dir, Float3R up, float epsilon, @Mutated FloatQuat dest) {
        return lookAlong_lh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon, dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_fma(Float3R dir, Float3R up, float epsilon, @Mutated DoubleQuat dest) {
        return lookAlong_lh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon, dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_mulAdd(Float3R dir, Float3R up, float epsilon, @Mutated DoubleQuat dest) {
        return lookAlong_lh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon, dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatQuat lookAlong_lh_fma(Float3R dir, Float3R up, float epsilon) {
        return lookAlong_lh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatQuat lookAlong_lh_mulAdd(Float3R dir, Float3R up, float epsilon) {
        return lookAlong_lh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatQuat lookAlong_lh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated FloatQuat dest) {
        float _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t27 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t28 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t29 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t36 = java.lang.Math.fma(dirZ, _t27, -dirY * _t28);
        float _t37 = java.lang.Math.fma(dirY, _t29, -(dirX * _t27));
        float _t38 = java.lang.Math.fma(dirX, _t28, -(dirZ * _t29));
        float _ct0 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36));
        if (!(_ct0 > java.lang.Math.fma(_t7, epsilon * java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        return lookAlong_lh_s36f6c823_1_fma(dirX, dirY, dirZ, (FloatQuatImpl) dest, _t15, _t36, _t37, _t38, (1.0f / (float) java.lang.Math.sqrt(_ct0)), this.x, this.w, this.y, this.z, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatQuat lookAlong_lh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated FloatQuat dest) {
        float _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t27 = ((upY) - (dirY) * (_sp0));
        float _t28 = ((upZ) - (dirZ) * (_sp0));
        float _t29 = ((upX) - (dirX) * (_sp0));
        float _t36 = ((dirZ) * (_t27) - (dirY * _t28));
        float _t37 = ((dirY) * (_t29) - (dirX * _t27));
        float _t38 = ((dirX) * (_t28) - (dirZ * _t29));
        float _ct0 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36))));
        if (!(_ct0 > ((_t7) * (epsilon * ((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        return lookAlong_lh_s36f6c823_1_mulAdd(dirX, dirY, dirZ, (FloatQuatImpl) dest, _t15, _t36, _t37, _t38, (1.0f / (float) java.lang.Math.sqrt(_ct0)), this.x, this.w, this.y, this.z, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16);
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.LEFT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatQuat lookAlong_lh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        return lookAlong_lh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, Joml.RETURN_NEW ? Joml.floatQuat() : this);
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.LEFT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatQuat lookAlong_lh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        return lookAlong_lh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, Joml.RETURN_NEW ? Joml.floatQuat() : this);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated DoubleQuat dest) {
        float _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t27 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t28 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t29 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t36 = java.lang.Math.fma(dirZ, _t27, -dirY * _t28);
        float _t37 = java.lang.Math.fma(dirY, _t29, -(dirX * _t27));
        float _t38 = java.lang.Math.fma(dirX, _t28, -(dirZ * _t29));
        float _ct0 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36));
        if (!(_ct0 > java.lang.Math.fma(_t7, epsilon * java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        return lookAlong_lh_sad3daae6_1_fma(dirX, dirY, dirZ, (DoubleQuatImpl) dest, _t15, _t36, _t37, _t38, (1.0f / (float) java.lang.Math.sqrt(_ct0)), this.x, this.w, this.y, this.z, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.LEFT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_lh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated DoubleQuat dest) {
        float _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t27 = ((upY) - (dirY) * (_sp0));
        float _t28 = ((upZ) - (dirZ) * (_sp0));
        float _t29 = ((upX) - (dirX) * (_sp0));
        float _t36 = ((dirZ) * (_t27) - (dirY * _t28));
        float _t37 = ((dirY) * (_t29) - (dirX * _t27));
        float _t38 = ((dirX) * (_t28) - (dirZ * _t29));
        float _ct0 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36))));
        if (!(_ct0 > ((_t7) * (epsilon * ((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        return lookAlong_lh_sad3daae6_1_mulAdd(dirX, dirY, dirZ, (DoubleQuatImpl) dest, _t15, _t36, _t37, _t38, (1.0f / (float) java.lang.Math.sqrt(_ct0)), this.x, this.w, this.y, this.z, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16);
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatQuat lookAlong_lh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated FloatQuat dest) {
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
        return lookAlong_lh_degenerate_s178e45ee_1_fma(epsilon, (FloatQuatImpl) dest, this.x, this.w, this.y, this.z, _t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, _t45, _t46, java.lang.Math.fma(_t44, _t24, _t21), java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatQuat lookAlong_lh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated FloatQuat dest) {
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
        return lookAlong_lh_degenerate_s178e45ee_1_mulAdd(epsilon, (FloatQuatImpl) dest, this.x, this.w, this.y, this.z, _t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, _t45, _t46, ((_t44) * (_t24) + (_t21)), ((_t45) * (_t26) - (_t46 * _t25)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatQuat lookAlong_lh_degenerate_s178e45ee_1_fma(float epsilon, FloatQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47, float _t56) {
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
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return lookAlong_lh_degenerate_sfa10eae0_2_fma(d, _r0, _r1, _r2, _r3, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatQuat lookAlong_lh_degenerate_s178e45ee_1_mulAdd(float epsilon, FloatQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47, float _t56) {
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
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return lookAlong_lh_degenerate_sfa10eae0_2_mulAdd(d, _r0, _r1, _r2, _r3, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleQuat lookAlong_lh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated DoubleQuat dest) {
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
        return lookAlong_lh_degenerate_sd19850b1_1_fma(epsilon, (DoubleQuatImpl) dest, this.x, this.w, this.y, this.z, _t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, _t45, _t46, java.lang.Math.fma(_t44, _t24, _t21), java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleQuat lookAlong_lh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated DoubleQuat dest) {
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
        return lookAlong_lh_degenerate_sd19850b1_1_mulAdd(epsilon, (DoubleQuatImpl) dest, this.x, this.w, this.y, this.z, _t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, _t45, _t46, ((_t44) * (_t24) + (_t21)), ((_t45) * (_t26) - (_t46 * _t25)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_lh_degenerate_sd19850b1_1_fma(float epsilon, DoubleQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47, float _t56) {
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
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return lookAlong_lh_degenerate_s447fcca7_2_fma(d, _r0, _r1, _r2, _r3, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t70, _t24, -(_t69 * _t25)), java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)));
    }

    /** Piece 2 of {@code lookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_lh_degenerate_sd19850b1_1_mulAdd(float epsilon, DoubleQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47, float _t56) {
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
        float _t72 = -_t70;
        float _t73 = _t67 * _t65;
        return lookAlong_lh_degenerate_s447fcca7_2_mulAdd(d, _r0, _r1, _r2, _r3, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t70) * (_t24) - (_t69 * _t25)), ((_t69) * (_t26) - (_t73 * _t24)), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))));
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatQuat lookAlong_rh_fma(Float3R dir, Float3R up, float epsilon, @Mutated FloatQuat dest) {
        return lookAlong_rh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon, dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatQuat lookAlong_rh_mulAdd(Float3R dir, Float3R up, float epsilon, @Mutated FloatQuat dest) {
        return lookAlong_rh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon, dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_fma(Float3R dir, Float3R up, float epsilon, @Mutated DoubleQuat dest) {
        return lookAlong_rh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon, dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_mulAdd(Float3R dir, Float3R up, float epsilon, @Mutated DoubleQuat dest) {
        return lookAlong_rh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon, dest);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatQuat lookAlong_rh_fma(Float3R dir, Float3R up, float epsilon) {
        return lookAlong_rh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatQuat lookAlong_rh_mulAdd(Float3R dir, Float3R up, float epsilon) {
        return lookAlong_rh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatQuat lookAlong_rh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated FloatQuat dest) {
        float _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t30 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t31 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t38 = java.lang.Math.fma(dirY, _t29, -(dirZ * _t30));
        float _t39 = java.lang.Math.fma(dirX, _t30, -(dirY * _t31));
        float _t40 = java.lang.Math.fma(dirZ, _t31, -dirX * _t29);
        float _ct0 = java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40));
        if (!(_ct0 > java.lang.Math.fma(_t8, epsilon * java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        return lookAlong_rh_s618fce05_1_fma(dirX, dirY, dirZ, (FloatQuatImpl) dest, _t16, _t38, _t39, _t40, (1.0f / (float) java.lang.Math.sqrt(_ct0)), this.x, this.w, this.y, this.z, -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private FloatQuat lookAlong_rh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated FloatQuat dest) {
        float _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t29 = ((upZ) - (dirZ) * (_sp0));
        float _t30 = ((upY) - (dirY) * (_sp0));
        float _t31 = ((upX) - (dirX) * (_sp0));
        float _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        float _t39 = ((dirX) * (_t30) - (dirY * _t31));
        float _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        float _ct0 = ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40))));
        if (!(_ct0 > ((_t8) * (epsilon * ((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        return lookAlong_rh_s618fce05_1_mulAdd(dirX, dirY, dirZ, (FloatQuatImpl) dest, _t16, _t38, _t39, _t40, (1.0f / (float) java.lang.Math.sqrt(_ct0)), this.x, this.w, this.y, this.z, -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17);
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatQuat lookAlong_rh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        return lookAlong_rh_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, Joml.RETURN_NEW ? Joml.floatQuat() : this);
    }

    /**
     * Private in-place self-form body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED};
     * reached only through the public {@code lookAlong} dispatcher.
     */
    @Mutated private FloatQuat lookAlong_rh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        return lookAlong_rh_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, Joml.RETURN_NEW ? Joml.floatQuat() : this);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated DoubleQuat dest) {
        float _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t30 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t31 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t38 = java.lang.Math.fma(dirY, _t29, -(dirZ * _t30));
        float _t39 = java.lang.Math.fma(dirX, _t30, -(dirY * _t31));
        float _t40 = java.lang.Math.fma(dirZ, _t31, -dirX * _t29);
        float _ct0 = java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40));
        if (!(_ct0 > java.lang.Math.fma(_t8, epsilon * java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        return lookAlong_rh_s4d54d694_1_fma(dirX, dirY, dirZ, (DoubleQuatImpl) dest, _t16, _t38, _t39, _t40, (1.0f / (float) java.lang.Math.sqrt(_ct0)), this.x, this.w, this.y, this.z, -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17);
    }

    /**
     * Private body of {@code lookAlong} for {@code Handedness.RIGHT_HANDED}; reached only through
     * the public {@code lookAlong} dispatcher.
     */
    private DoubleQuat lookAlong_rh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated DoubleQuat dest) {
        float _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t29 = ((upZ) - (dirZ) * (_sp0));
        float _t30 = ((upY) - (dirY) * (_sp0));
        float _t31 = ((upX) - (dirX) * (_sp0));
        float _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        float _t39 = ((dirX) * (_t30) - (dirY * _t31));
        float _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        float _ct0 = ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40))));
        if (!(_ct0 > ((_t8) * (epsilon * ((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return lookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon, dest);
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        return lookAlong_rh_s4d54d694_1_mulAdd(dirX, dirY, dirZ, (DoubleQuatImpl) dest, _t16, _t38, _t39, _t40, (1.0f / (float) java.lang.Math.sqrt(_ct0)), this.x, this.w, this.y, this.z, -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17);
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatQuat lookAlong_rh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated FloatQuat dest) {
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
        return lookAlong_rh_degenerate_s282ffa9c_1_fma(epsilon, (FloatQuatImpl) dest, this.x, this.w, this.y, this.z, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, java.lang.Math.fma(_t44, _t26, _t23), java.lang.Math.fma(_t44, _t25, _t22), java.lang.Math.fma(_t44, _t24, _t21));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private FloatQuat lookAlong_rh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated FloatQuat dest) {
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
        return lookAlong_rh_degenerate_s282ffa9c_1_mulAdd(epsilon, (FloatQuatImpl) dest, this.x, this.w, this.y, this.z, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, ((_t44) * (_t26) + (_t23)), ((_t44) * (_t25) + (_t22)), ((_t44) * (_t24) + (_t21)));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatQuat lookAlong_rh_degenerate_s282ffa9c_1_fma(float epsilon, FloatQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47) {
        float _t56 = java.lang.Math.fma(_t45, _t25, _t46 * -_t26);
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
        return lookAlong_rh_degenerate_s346b19b6_2_fma(d, _r0, _r1, _r2, _r3, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t69, _t25, -(_t70 * _t24)), java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)), java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatQuat lookAlong_rh_degenerate_s282ffa9c_1_mulAdd(float epsilon, FloatQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47) {
        float _t56 = ((_t45) * (_t25) - (_t46 * _t26));
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
        return lookAlong_rh_degenerate_s346b19b6_2_mulAdd(d, _r0, _r1, _r2, _r3, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t69) * (_t25) - (_t70 * _t24)), ((_t73) * (_t24) - (_t69 * _t26)), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))), ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleQuat lookAlong_rh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated DoubleQuat dest) {
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
        return lookAlong_rh_degenerate_s2c116793_1_fma(epsilon, (DoubleQuatImpl) dest, this.x, this.w, this.y, this.z, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, java.lang.Math.fma(_t44, _t26, _t23), java.lang.Math.fma(_t44, _t25, _t22), java.lang.Math.fma(_t44, _t24, _t21));
    }

    /**
     * Degenerate-input path of {@code lookAlong}: its methods leave here when their input spans no
     * proper basis (a zero direction, an up vector parallel to it or zero, NaN); reached only
     * through them.
     */
    private DoubleQuat lookAlong_rh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated DoubleQuat dest) {
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
        return lookAlong_rh_degenerate_s2c116793_1_mulAdd(epsilon, (DoubleQuatImpl) dest, this.x, this.w, this.y, this.z, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, ((_t44) * (_t26) + (_t23)), ((_t44) * (_t25) + (_t22)), ((_t44) * (_t24) + (_t21)));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_rh_degenerate_s2c116793_1_fma(float epsilon, DoubleQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47) {
        float _t56 = java.lang.Math.fma(_t45, _t25, _t46 * -_t26);
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
        return lookAlong_rh_degenerate_s5e7bb289_2_fma(d, _r0, _r1, _r2, _r3, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), java.lang.Math.fma(_t69, _t25, -(_t70 * _t24)), java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)), java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)));
    }

    /** Piece 2 of {@code lookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private DoubleQuat lookAlong_rh_degenerate_s2c116793_1_mulAdd(float epsilon, DoubleQuatImpl d, float _r0, float _r1, float _r2, float _r3, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47) {
        float _t56 = ((_t45) * (_t25) - (_t46 * _t26));
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
        return lookAlong_rh_degenerate_s5e7bb289_2_mulAdd(d, _r0, _r1, _r2, _r3, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), ((_t69) * (_t25) - (_t70 * _t24)), ((_t73) * (_t24) - (_t69 * _t26)), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))), ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))));
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
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @param handedness the handedness of the coordinate system to map into
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat lookAlong(Float3R dir, Float3R up, float epsilon, Handedness handedness, @Mutated FloatQuat dest) {
        if (Math.useFma()) {
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
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @param handedness the handedness of the coordinate system to map into
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public FloatQuat lookAlong(Float3R dir, Float3R up, float epsilon, Handedness handedness) {
        if (Math.useFma()) {
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
    public DoubleQuat lookAlong(Float3R dir, Float3R up, float epsilon, Handedness handedness, @Mutated DoubleQuat dest) {
        if (Math.useFma()) {
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
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @param handedness the handedness of the coordinate system to map into
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, Handedness handedness, @Mutated FloatQuat dest) {
        if (Math.useFma()) {
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
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @param handedness the handedness of the coordinate system to map into
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public FloatQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, Handedness handedness) {
        if (Math.useFma()) {
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
    public DoubleQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, Handedness handedness, @Mutated DoubleQuat dest) {
        if (Math.useFma()) {
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
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat lookAlong(Float3R dir, Float3R up, float epsilon, @Mutated FloatQuat dest) { return lookAlong(dir, up, epsilon, Handedness.RIGHT_HANDED, dest); }

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
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public FloatQuat lookAlong(Float3R dir, Float3R up, float epsilon) { return lookAlong(dir, up, epsilon, Handedness.RIGHT_HANDED); }

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
    public DoubleQuat lookAlong(Float3R dir, Float3R up, float epsilon, @Mutated DoubleQuat dest) { return lookAlong(dir, up, epsilon, Handedness.RIGHT_HANDED, dest); }

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
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated FloatQuat dest) { return lookAlong(dirX, dirY, dirZ, upX, upY, upZ, epsilon, Handedness.RIGHT_HANDED, dest); }

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
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @return this (a new instance when {@code Joml.RETURN_NEW} is enabled)
     */
    @Mutated public FloatQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) { return lookAlong(dirX, dirY, dirZ, upX, upY, upZ, epsilon, Handedness.RIGHT_HANDED); }

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
    public DoubleQuat lookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, @Mutated DoubleQuat dest) { return lookAlong(dirX, dirY, dirZ, upX, upY, upZ, epsilon, Handedness.RIGHT_HANDED, dest); }

    /**
     * Set this quaternion to a rotation of {@code angle} radians about the axis {@code axis}.
     * <p>
     * Valid input: {@code axis} must have unit length.
     *
     * @param angle the angle in radians
     * @param axis the rotation axis
     * @return this
     */
    @Mutated public FloatQuat makeRotationAxis(float angle, Float3R axis) {
        float axisX = axis.x();
        float axisY = axis.y();
        float axisZ = axis.z();
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
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
    @Mutated public FloatQuat makeRotationAxis(float angle, float axisX, float axisY, float axisZ) {
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return makeRotationX(axisX * angle);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return makeRotationY(axisY * angle);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return makeRotationZ(axisZ * angle);
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        this.x = axisX * _t1;
        this.y = axisY * _t1;
        this.z = axisZ * _t1;
        this.w = Math.cosFromSin(_t1, _t0);
        return this;
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatQuat makeRotationLookAlong_lh_fma(Float3R dir, Float3R up) {
        return makeRotationLookAlong_lh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatQuat makeRotationLookAlong_lh_mulAdd(Float3R dir, Float3R up) {
        return makeRotationLookAlong_lh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatQuat makeRotationLookAlong_lh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t27 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t28 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t29 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t36 = java.lang.Math.fma(dirZ, _t27, -dirY * _t28);
        float _t37 = java.lang.Math.fma(dirY, _t29, -(dirX * _t27));
        float _t38 = java.lang.Math.fma(dirX, _t28, -(dirZ * _t29));
        float _ct0 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36));
        if (!(_ct0 > java.lang.Math.fma(_t7, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        return makeRotationLookAlong_lh_s96c6b39a_1_fma(dirX, dirY, dirZ, this, _t15, _t36, _t37, _t38, _t45, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36 * _t45, _t37 * _t45, _t38 * _t45);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatQuat makeRotationLookAlong_lh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t27 = ((upY) - (dirY) * (_sp0));
        float _t28 = ((upZ) - (dirZ) * (_sp0));
        float _t29 = ((upX) - (dirX) * (_sp0));
        float _t36 = ((dirZ) * (_t27) - (dirY * _t28));
        float _t37 = ((dirY) * (_t29) - (dirX * _t27));
        float _t38 = ((dirX) * (_t28) - (dirZ * _t29));
        float _ct0 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36))));
        if (!(_ct0 > ((_t7) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 1.4551915E-11f) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        return makeRotationLookAlong_lh_s96c6b39a_1_mulAdd(dirX, dirY, dirZ, this, _t15, _t36, _t37, _t38, _t45, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36 * _t45, _t37 * _t45, _t38 * _t45);
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private FloatQuat makeRotationLookAlong_lh_s96c6b39a_1_fma(float dirX, float dirY, float dirZ, FloatQuatImpl d, float _t15, float _t36, float _t37, float _t38, float _t45, float _t0, float _t16, float _t17, float _t18, float _t19, float _t21, float _t46, float _t47, float _t48) {
        float _t49 = java.lang.Math.fma(-_t36, _t45, 1.0f);
        float _t62 = java.lang.Math.fma(_t16, _t46, -(_t17 * _t47));
        float _t63 = java.lang.Math.fma(_t17, _t48, -(_t18 * _t46));
        float _t65 = java.lang.Math.fma(_t18, _t47, -(_t16 * _t48));
        float _t77 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(dirZ, _t15, 1.0f))));
        float _t79 = java.lang.Math.fma(_t36, _t45, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, java.lang.Math.fma(_t0, _t15, 1.0f))));
        float _t80 = java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t21, _t46, java.lang.Math.fma(_t17, _t47, _t49)));
        float _t81 = java.lang.Math.fma(_t16, _t46, java.lang.Math.fma(_t19, _t47, java.lang.Math.fma(_t0, _t15, _t49)));
        return makeRotationLookAlong_lh_s96c6b39a_2(d, _t16, _t46, java.lang.Math.fma(dirX, _t15, _t47), java.lang.Math.fma(_t37, _t45, _t19), _t62, java.lang.Math.fma(dirY, _t15, _t63), java.lang.Math.fma(dirY, _t15, -_t63), Math.max(_t62, _t16), java.lang.Math.fma(_t38, _t45, _t65), java.lang.Math.fma(-_t38, _t45, _t65), java.lang.Math.fma(dirZ, _t15, java.lang.Math.fma(_t36, _t45, _t62)), _t77, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t77)), _t79, _t80, _t81, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)));
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private FloatQuat makeRotationLookAlong_lh_s96c6b39a_1_mulAdd(float dirX, float dirY, float dirZ, FloatQuatImpl d, float _t15, float _t36, float _t37, float _t38, float _t45, float _t0, float _t16, float _t17, float _t18, float _t19, float _t21, float _t46, float _t47, float _t48) {
        float _t49 = ((1.0f) - (_t36) * (_t45));
        float _t62 = ((_t16) * (_t46) - (_t17 * _t47));
        float _t63 = ((_t17) * (_t48) - (_t18 * _t46));
        float _t65 = ((_t18) * (_t47) - (_t16 * _t48));
        float _t77 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t36) * (_t45) + (((dirZ) * (_t15) + (1.0f))))))));
        float _t79 = ((_t36) * (_t45) + (((_t21) * (_t46) + (((_t17) * (_t47) + (((_t0) * (_t15) + (1.0f))))))));
        float _t80 = ((dirZ) * (_t15) + (((_t21) * (_t46) + (((_t17) * (_t47) + (_t49))))));
        float _t81 = ((_t16) * (_t46) + (((_t19) * (_t47) + (((_t0) * (_t15) + (_t49))))));
        return makeRotationLookAlong_lh_s96c6b39a_2(d, _t16, _t46, ((dirX) * (_t15) + (_t47)), ((_t37) * (_t45) + (_t19)), _t62, ((dirY) * (_t15) + (_t63)), ((dirY) * (_t15) - (_t63)), Math.max(_t62, _t16), ((_t38) * (_t45) + (_t65)), ((_t65) - (_t38) * (_t45)), ((dirZ) * (_t15) + (((_t36) * (_t45) + (_t62)))), _t77, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t77)), _t79, _t80, _t81, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t81)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private FloatQuat makeRotationLookAlong_lh_s96c6b39a_2(FloatQuatImpl d, float _t16, float _t46, float _t50, float _t55, float _t62, float _t68, float _t69, float _t70, float _t73, float _t74, float _t76, float _t77, float _sp1, float _t79, float _t80, float _t81, float _sp3, float _sp4, float _sp2) {
        d.x = _t76 > 0.0f ? _sp1 * _t69 : _t46 > _t70 ? 0.5f * (float) java.lang.Math.sqrt(_t79) : _t62 > _t16 ? _sp2 * _t73 : _sp3 * _t50;
        d.y = _t76 > 0.0f ? _sp1 * _t55 : _t46 > _t70 ? _sp4 * _t73 : _t62 > _t16 ? 0.5f * (float) java.lang.Math.sqrt(_t81) : _sp3 * _t68;
        d.z = _t76 > 0.0f ? _sp1 * _t74 : _t46 > _t70 ? _sp4 * _t50 : _t62 > _t16 ? _sp2 * _t68 : 0.5f * (float) java.lang.Math.sqrt(_t80);
        d.w = _t76 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t77) : _t46 > _t70 ? _sp4 * _t69 : _t62 > _t16 ? _sp2 * _t55 : _sp3 * _t74;
        return d;
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private FloatQuat makeRotationLookAlong_lh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        return makeRotationLookAlong_lh_degenerate_s2c1aa5ef_1_fma(this, _t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)), java.lang.Math.fma(_t47, _t25, _t45 * -_t24), java.lang.Math.fma(_t46, _t24, _t47 * -_t26));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private FloatQuat makeRotationLookAlong_lh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        return makeRotationLookAlong_lh_degenerate_s2c1aa5ef_1_mulAdd(this, _t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, ((_t45) * (_t26) - (_t46 * _t25)), ((_t47) * (_t25) - (_t45 * _t24)), ((_t46) * (_t24) - (_t47 * _t26)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatQuat makeRotationLookAlong_lh_degenerate_s2c1aa5ef_1_fma(FloatQuatImpl d, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t56, float _t57, float _t58) {
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
        float _t89 = java.lang.Math.fma(_t70, _t24, -(_t69 * _t25));
        return makeRotationLookAlong_lh_degenerate_s2c1aa5ef_2_fma(d, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), _t89, java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), Math.max(_t89, _t24), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)), java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t24))), java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t32))));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatQuat makeRotationLookAlong_lh_degenerate_s2c1aa5ef_1_mulAdd(FloatQuatImpl d, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t56, float _t57, float _t58) {
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
        float _t89 = ((_t70) * (_t24) - (_t69 * _t25));
        return makeRotationLookAlong_lh_degenerate_s2c1aa5ef_2_mulAdd(d, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), _t89, ((_t69) * (_t26) - (_t73 * _t24)), Math.max(_t89, _t24), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))), ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t24)))))), ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t32)))))));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private FloatQuat makeRotationLookAlong_lh_degenerate_s2c1aa5ef_2_fma(FloatQuatImpl d, float _t24, float _t25, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t78, float _t89, float _t93, float _t94, float _t96, float _t97, float _t98, float _t99) {
        float _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, _t33)));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99));
        float _t102 = java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t68, _t64, _t33)));
        float _t103 = java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t68, _t64, _t32)));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t102));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103));
        float _t107 = java.lang.Math.fma(_t67, _t65, _t93);
        d.x = _t98 > 0.0f ? _sp0 * _t96 : _t70 > _t94 ? 0.5f * (float) java.lang.Math.sqrt(_t100) : _t89 > _t24 ? _sp1 * _t107 : _sp2 * _t74;
        d.y = _t98 > 0.0f ? _sp0 * _t78 : _t70 > _t94 ? _sp3 * _t107 : _t89 > _t24 ? 0.5f * (float) java.lang.Math.sqrt(_t102) : _sp2 * _t97;
        return makeRotationLookAlong_lh_degenerate_s2c1aa5ef_3(d, _t24, _t70, _t74, _t78, _t89, _t94, _t96, _t97, _t98, _t99, _sp0, _t103, _sp3, _sp1, _sp2, java.lang.Math.fma(_t68, _t65, _t93));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private FloatQuat makeRotationLookAlong_lh_degenerate_s2c1aa5ef_2_mulAdd(FloatQuatImpl d, float _t24, float _t25, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t78, float _t89, float _t93, float _t94, float _t96, float _t97, float _t98, float _t99) {
        float _t100 = ((_t67) * (_t64) + (((_t72) * (_t24) + (((_t69) * (_t25) + (_t33))))));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99));
        float _t102 = ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t68) * (_t64) + (_t33))))));
        float _t103 = ((_t72) * (_t24) + (((_t69) * (_t25) + (((_t68) * (_t64) + (_t32))))));
        float _sp3 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t102));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103));
        float _t107 = ((_t67) * (_t65) + (_t93));
        d.x = _t98 > 0.0f ? _sp0 * _t96 : _t70 > _t94 ? 0.5f * (float) java.lang.Math.sqrt(_t100) : _t89 > _t24 ? _sp1 * _t107 : _sp2 * _t74;
        d.y = _t98 > 0.0f ? _sp0 * _t78 : _t70 > _t94 ? _sp3 * _t107 : _t89 > _t24 ? 0.5f * (float) java.lang.Math.sqrt(_t102) : _sp2 * _t97;
        return makeRotationLookAlong_lh_degenerate_s2c1aa5ef_3(d, _t24, _t70, _t74, _t78, _t89, _t94, _t96, _t97, _t98, _t99, _sp0, _t103, _sp3, _sp1, _sp2, ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Piece 4 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private FloatQuat makeRotationLookAlong_lh_degenerate_s2c1aa5ef_3(FloatQuatImpl d, float _t24, float _t70, float _t74, float _t78, float _t89, float _t94, float _t96, float _t97, float _t98, float _t99, float _sp0, float _t103, float _sp3, float _sp1, float _sp2, float _t108) {
        d.z = _t98 > 0.0f ? _sp0 * _t108 : _t70 > _t94 ? _sp3 * _t74 : _t89 > _t24 ? _sp1 * _t97 : 0.5f * (float) java.lang.Math.sqrt(_t103);
        d.w = _t98 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t99) : _t70 > _t94 ? _sp3 * _t96 : _t89 > _t24 ? _sp1 * _t78 : _sp2 * _t108;
        return d;
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatQuat makeRotationLookAlong_rh_fma(Float3R dir, Float3R up) {
        return makeRotationLookAlong_rh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatQuat makeRotationLookAlong_rh_mulAdd(Float3R dir, Float3R up) {
        return makeRotationLookAlong_rh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z());
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatQuat makeRotationLookAlong_rh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t30 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t31 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t38 = java.lang.Math.fma(dirY, _t29, -(dirZ * _t30));
        float _t39 = java.lang.Math.fma(dirX, _t30, -(dirY * _t31));
        float _t40 = java.lang.Math.fma(dirZ, _t31, -dirX * _t29);
        float _ct0 = java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40));
        if (!(_ct0 > java.lang.Math.fma(_t8, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)) * 1.4551915E-11f, 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ);
        float _t47 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        return makeRotationLookAlong_rh_s1465dddc_1_fma(dirX, dirY, this, _t16, _t38, _t39, _t40, _t47, -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, java.lang.Math.fma(dirZ, _t16, 1.0f), -_t38, _t39 * _t47, _t38 * _t47);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatQuat makeRotationLookAlong_rh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t29 = ((upZ) - (dirZ) * (_sp0));
        float _t30 = ((upY) - (dirY) * (_sp0));
        float _t31 = ((upX) - (dirX) * (_sp0));
        float _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        float _t39 = ((dirX) * (_t30) - (dirY * _t31));
        float _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        float _ct0 = ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40))));
        if (!(_ct0 > ((_t8) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY)))) * 1.4551915E-11f) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ);
        float _t47 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        return makeRotationLookAlong_rh_s1465dddc_1_mulAdd(dirX, dirY, this, _t16, _t38, _t39, _t40, _t47, -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, ((dirZ) * (_t16) + (1.0f)), -_t38, _t39 * _t47, _t38 * _t47);
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private FloatQuat makeRotationLookAlong_rh_s1465dddc_1_fma(float dirX, float dirY, FloatQuatImpl d, float _t16, float _t38, float _t39, float _t40, float _t47, float _t0, float _t17, float _t18, float _t19, float _t21, float _t22, float _t23, float _t41, float _t48, float _t49) {
        float _t50 = _t40 * _t47;
        float _t64 = java.lang.Math.fma(_t17, _t48, -(_t18 * _t49));
        float _t65 = java.lang.Math.fma(_t19, _t49, -(_t17 * _t50));
        float _t66 = java.lang.Math.fma(_t18, _t50, -_t19 * _t48);
        float _t78 = java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, _t23)));
        float _t79 = java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t21, _t49, java.lang.Math.fma(_t38, _t47, java.lang.Math.fma(_t0, _t16, 1.0f))));
        float _t80 = java.lang.Math.fma(_t17, _t48, java.lang.Math.fma(_t21, _t49, java.lang.Math.fma(_t41, _t47, _t23)));
        float _t84 = java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t22, _t48, java.lang.Math.fma(_t18, _t49, java.lang.Math.fma(_t41, _t47, 1.0f))));
        return makeRotationLookAlong_rh_s1465dddc_2(d, _t21, _t49, java.lang.Math.fma(dirX, _t16, _t48), java.lang.Math.fma(_t39, _t47, _t22), _t64, java.lang.Math.fma(-dirY, _t16, _t65), -_t19 - _t65, Math.max(_t64, _t21), java.lang.Math.fma(_t40, _t47, _t66), java.lang.Math.fma(-_t40, _t47, _t66), java.lang.Math.fma(_t0, _t16, java.lang.Math.fma(_t38, _t47, _t64)), _t78, _t79, _t80, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), _t84, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t84)));
    }

    /**
     * Piece 2 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private FloatQuat makeRotationLookAlong_rh_s1465dddc_1_mulAdd(float dirX, float dirY, FloatQuatImpl d, float _t16, float _t38, float _t39, float _t40, float _t47, float _t0, float _t17, float _t18, float _t19, float _t21, float _t22, float _t23, float _t41, float _t48, float _t49) {
        float _t50 = _t40 * _t47;
        float _t64 = ((_t17) * (_t48) - (_t18 * _t49));
        float _t65 = ((_t19) * (_t49) - (_t17 * _t50));
        float _t66 = ((_t18) * (_t50) - (_t19 * _t48));
        float _t78 = ((_t38) * (_t47) + (((_t22) * (_t48) + (((_t18) * (_t49) + (_t23))))));
        float _t79 = ((_t17) * (_t48) + (((_t21) * (_t49) + (((_t38) * (_t47) + (((_t0) * (_t16) + (1.0f))))))));
        float _t80 = ((_t17) * (_t48) + (((_t21) * (_t49) + (((_t41) * (_t47) + (_t23))))));
        float _t84 = ((_t0) * (_t16) + (((_t22) * (_t48) + (((_t18) * (_t49) + (((_t41) * (_t47) + (1.0f))))))));
        return makeRotationLookAlong_rh_s1465dddc_2(d, _t21, _t49, ((dirX) * (_t16) + (_t48)), ((_t39) * (_t47) + (_t22)), _t64, ((_t65) - (dirY) * (_t16)), -_t19 - _t65, Math.max(_t64, _t21), ((_t40) * (_t47) + (_t66)), ((_t66) - (_t40) * (_t47)), ((_t0) * (_t16) + (((_t38) * (_t47) + (_t64)))), _t78, _t79, _t80, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t78)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t79)), 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t80)), _t84, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t84)));
    }

    /**
     * Piece 3 of {@code makeRotationLookAlong_rh}, split to fit the inline budget. Shared by 2
     * identical private paths of {@code makeRotationLookAlong}; reached only through it.
     */
    private FloatQuat makeRotationLookAlong_rh_s1465dddc_2(FloatQuatImpl d, float _t21, float _t49, float _t52, float _t56, float _t64, float _t70, float _t71, float _t72, float _t74, float _t75, float _t77, float _t78, float _t79, float _t80, float _sp4, float _sp1, float _sp2, float _t84, float _sp3) {
        d.x = _t77 > 0.0f ? _sp1 * _t71 : _t49 > _t72 ? 0.5f * (float) java.lang.Math.sqrt(_t78) : _t64 > _t21 ? _sp2 * _t74 : _sp3 * _t56;
        d.y = _t77 > 0.0f ? _sp1 * _t52 : _t49 > _t72 ? _sp4 * _t74 : _t64 > _t21 ? 0.5f * (float) java.lang.Math.sqrt(_t80) : _sp3 * _t70;
        d.z = _t77 > 0.0f ? _sp1 * _t75 : _t49 > _t72 ? _sp4 * _t56 : _t64 > _t21 ? _sp2 * _t70 : 0.5f * (float) java.lang.Math.sqrt(_t84);
        d.w = _t77 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t79) : _t49 > _t72 ? _sp4 * _t71 : _t64 > _t21 ? _sp2 * _t52 : _sp3 * _t75;
        return d;
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private FloatQuat makeRotationLookAlong_rh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        float _t47 = java.lang.Math.fma(_t44, _t24, _t21);
        return makeRotationLookAlong_rh_degenerate_s57efa25_1_fma(this, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, _t45, _t47, java.lang.Math.fma(_t45, _t25, _t46 * -_t26), java.lang.Math.fma(_t46, _t24, -(_t47 * _t25)));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private FloatQuat makeRotationLookAlong_rh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
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
        float _t47 = ((_t44) * (_t24) + (_t21));
        return makeRotationLookAlong_rh_degenerate_s57efa25_1_mulAdd(this, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, _t45, _t47, ((_t45) * (_t25) - (_t46 * _t26)), ((_t46) * (_t24) - (_t47 * _t25)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatQuat makeRotationLookAlong_rh_degenerate_s57efa25_1_fma(FloatQuatImpl d, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t47, float _t56, float _t57) {
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
        float _t88 = java.lang.Math.fma(_t69, _t25, -(_t70 * _t24));
        return makeRotationLookAlong_rh_degenerate_s57efa25_2_fma(d, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), _t88, java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), Math.max(_t88, _t30), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)), java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)), java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t30))));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatQuat makeRotationLookAlong_rh_degenerate_s57efa25_1_mulAdd(FloatQuatImpl d, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t47, float _t56, float _t57) {
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
        float _t88 = ((_t69) * (_t25) - (_t70 * _t24));
        return makeRotationLookAlong_rh_degenerate_s57efa25_2_mulAdd(d, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), _t88, ((_t73) * (_t24) - (_t69 * _t26)), Math.max(_t88, _t30), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))), ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))), ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t30)))))));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatQuat makeRotationLookAlong_rh_degenerate_s57efa25_2_fma(FloatQuatImpl d, float _t24, float _t25, float _t30, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t76, float _t88, float _t93, float _t94, float _t96, float _t97, float _t98) {
        float _t99 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t32)));
        float _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, _t33)));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99));
        float _t102 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t68, _t64, _t33)));
        float _t103 = java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t68, _t64, _t32)));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t102));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103));
        float _t107 = java.lang.Math.fma(_t67, _t65, _t93);
        d.x = _t98 > 0.0f ? _sp0 * _t96 : _t70 > _t94 ? 0.5f * (float) java.lang.Math.sqrt(_t100) : _t88 > _t30 ? _sp1 * _t107 : _sp2 * _t76;
        return makeRotationLookAlong_rh_degenerate_s57efa25_3(d, _t30, _t70, _t74, _t76, _t88, _t94, _t96, _t97, _t98, _t99, _sp0, _t102, _t103, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), _sp1, _sp2, _t107, java.lang.Math.fma(_t68, _t65, _t93));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatQuat makeRotationLookAlong_rh_degenerate_s57efa25_2_mulAdd(FloatQuatImpl d, float _t24, float _t25, float _t30, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t76, float _t88, float _t93, float _t94, float _t96, float _t97, float _t98) {
        float _t99 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t32))))));
        float _t100 = ((_t67) * (_t64) + (((_t71) * (_t25) + (((_t70) * (_t24) + (_t33))))));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99));
        float _t102 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t68) * (_t64) + (_t33))))));
        float _t103 = ((_t71) * (_t25) + (((_t70) * (_t24) + (((_t68) * (_t64) + (_t32))))));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t102));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103));
        float _t107 = ((_t67) * (_t65) + (_t93));
        d.x = _t98 > 0.0f ? _sp0 * _t96 : _t70 > _t94 ? 0.5f * (float) java.lang.Math.sqrt(_t100) : _t88 > _t30 ? _sp1 * _t107 : _sp2 * _t76;
        return makeRotationLookAlong_rh_degenerate_s57efa25_3(d, _t30, _t70, _t74, _t76, _t88, _t94, _t96, _t97, _t98, _t99, _sp0, _t102, _t103, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), _sp1, _sp2, _t107, ((_t68) * (_t65) + (_t93)));
    }

    /**
     * Piece 4 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget.
     * Shared by 2 identical private paths of {@code makeRotationLookAlong}; reached only through
     * it.
     */
    private FloatQuat makeRotationLookAlong_rh_degenerate_s57efa25_3(FloatQuatImpl d, float _t30, float _t70, float _t74, float _t76, float _t88, float _t94, float _t96, float _t97, float _t98, float _t99, float _sp0, float _t102, float _t103, float _sp3, float _sp1, float _sp2, float _t107, float _t108) {
        d.y = _t98 > 0.0f ? _sp0 * _t74 : _t70 > _t94 ? _sp3 * _t107 : _t88 > _t30 ? 0.5f * (float) java.lang.Math.sqrt(_t102) : _sp2 * _t97;
        d.z = _t98 > 0.0f ? _sp0 * _t108 : _t70 > _t94 ? _sp3 * _t76 : _t88 > _t30 ? _sp1 * _t97 : 0.5f * (float) java.lang.Math.sqrt(_t103);
        d.w = _t98 > 0.0f ? 0.5f * (float) java.lang.Math.sqrt(_t99) : _t70 > _t94 ? _sp3 * _t96 : _t88 > _t30 ? _sp1 * _t74 : _sp2 * _t108;
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
    @Mutated public FloatQuat makeRotationLookAlong(Float3R dir, Float3R up, Handedness handedness) {
        if (Math.useFma()) {
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
    @Mutated public FloatQuat makeRotationLookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, Handedness handedness) {
        if (Math.useFma()) {
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
    @Mutated public FloatQuat makeRotationLookAlong(Float3R dir, Float3R up) { return makeRotationLookAlong(dir, up, Handedness.RIGHT_HANDED); }

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
    @Mutated public FloatQuat makeRotationLookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ) { return makeRotationLookAlong(dirX, dirY, dirZ, upX, upY, upZ, Handedness.RIGHT_HANDED); }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatQuat makeRotationLookAlong_lh_fma(Float3R dir, Float3R up, float epsilon) {
        return makeRotationLookAlong_lh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatQuat makeRotationLookAlong_lh_mulAdd(Float3R dir, Float3R up, float epsilon) {
        return makeRotationLookAlong_lh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatQuat makeRotationLookAlong_lh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t7 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t27 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t28 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t29 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t36 = java.lang.Math.fma(dirZ, _t27, -dirY * _t28);
        float _t37 = java.lang.Math.fma(dirY, _t29, -(dirX * _t27));
        float _t38 = java.lang.Math.fma(dirX, _t28, -(dirZ * _t29));
        float _ct0 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t36 * _t36));
        if (!(_ct0 > java.lang.Math.fma(_t7, epsilon * java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        return makeRotationLookAlong_lh_s96c6b39a_1_fma(dirX, dirY, dirZ, this, _t15, _t36, _t37, _t38, _t45, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36 * _t45, _t37 * _t45, _t38 * _t45);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.LEFT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatQuat makeRotationLookAlong_lh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t7 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t7;
        if (!(_t7 > 1.1754944E-38f && _t7 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t15 = (1.0f / (float) java.lang.Math.sqrt(_t7));
        float _t27 = ((upY) - (dirY) * (_sp0));
        float _t28 = ((upZ) - (dirZ) * (_sp0));
        float _t29 = ((upX) - (dirX) * (_sp0));
        float _t36 = ((dirZ) * (_t27) - (dirY * _t28));
        float _t37 = ((dirY) * (_t29) - (dirX * _t27));
        float _t38 = ((dirX) * (_t28) - (dirZ * _t29));
        float _ct0 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t36 * _t36))));
        if (!(_ct0 > ((_t7) * (epsilon * ((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_lh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t45 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t16 = dirZ * _t15;
        float _t17 = dirX * _t15;
        return makeRotationLookAlong_lh_s96c6b39a_1_mulAdd(dirX, dirY, dirZ, this, _t15, _t36, _t37, _t38, _t45, -dirZ, _t16, _t17, dirY * _t15, -_t17, -_t16, _t36 * _t45, _t37 * _t45, _t38 * _t45);
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private FloatQuat makeRotationLookAlong_lh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        return makeRotationLookAlong_lh_degenerate_sf46b18a5_1_fma(epsilon, this, _t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, java.lang.Math.fma(_t45, _t26, -(_t46 * _t25)), java.lang.Math.fma(_t47, _t25, _t45 * -_t24), java.lang.Math.fma(_t46, _t24, _t47 * -_t26));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private FloatQuat makeRotationLookAlong_lh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        return makeRotationLookAlong_lh_degenerate_sf46b18a5_1_mulAdd(epsilon, this, _t21, _t22, _t23, _t24, _t25, _t26, 1.0f + _t24, 1.0f - _t24, _t37, _t38, _t42, ((_t45) * (_t26) - (_t46 * _t25)), ((_t47) * (_t25) - (_t45 * _t24)), ((_t46) * (_t24) - (_t47 * _t26)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatQuat makeRotationLookAlong_lh_degenerate_sf46b18a5_1_fma(float epsilon, FloatQuatImpl d, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t56, float _t57, float _t58) {
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
        float _t89 = java.lang.Math.fma(_t70, _t24, -(_t69 * _t25));
        return makeRotationLookAlong_lh_degenerate_s2c1aa5ef_2_fma(d, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), _t89, java.lang.Math.fma(_t69, _t26, -(_t73 * _t24)), Math.max(_t89, _t24), java.lang.Math.fma(_t73, -_t25, java.lang.Math.fma(_t70, _t26, _t26)), java.lang.Math.fma(_t73, _t25, java.lang.Math.fma(_t72, _t26, _t26)), java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t24))), java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t67, _t64, _t32))));
    }

    /** Piece 2 of {@code makeRotationLookAlong_lh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatQuat makeRotationLookAlong_lh_degenerate_sf46b18a5_1_mulAdd(float epsilon, FloatQuatImpl d, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t32, float _t33, float _t37, float _t38, float _t42, float _t56, float _t57, float _t58) {
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
        float _t89 = ((_t70) * (_t24) - (_t69 * _t25));
        return makeRotationLookAlong_lh_degenerate_s2c1aa5ef_2_mulAdd(d, _t24, _t25, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, _t71, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), _t89, ((_t69) * (_t26) - (_t73 * _t24)), Math.max(_t89, _t24), ((((_t70) * (_t26) + (_t26))) - (_t73) * (_t25)), ((_t73) * (_t25) + (((_t72) * (_t26) + (_t26)))), ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t24)))))), ((_t70) * (_t24) + (((_t71) * (_t25) + (((_t67) * (_t64) + (_t32)))))));
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatQuat makeRotationLookAlong_rh_fma(Float3R dir, Float3R up, float epsilon) {
        return makeRotationLookAlong_rh_fma(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatQuat makeRotationLookAlong_rh_mulAdd(Float3R dir, Float3R up, float epsilon) {
        return makeRotationLookAlong_rh_mulAdd(dir.x(), dir.y(), dir.z(), up.x(), up.y(), up.z(), epsilon);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatQuat makeRotationLookAlong_rh_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _sp0 = java.lang.Math.fma(dirZ, upZ, java.lang.Math.fma(dirX, upX, dirY * upY)) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t29 = java.lang.Math.fma(-dirZ, _sp0, upZ);
        float _t30 = java.lang.Math.fma(-dirY, _sp0, upY);
        float _t31 = java.lang.Math.fma(-dirX, _sp0, upX);
        float _t38 = java.lang.Math.fma(dirY, _t29, -(dirZ * _t30));
        float _t39 = java.lang.Math.fma(dirX, _t30, -(dirY * _t31));
        float _t40 = java.lang.Math.fma(dirZ, _t31, -dirX * _t29);
        float _ct0 = java.lang.Math.fma(_t39, _t39, java.lang.Math.fma(_t38, _t38, _t40 * _t40));
        if (!(_ct0 > java.lang.Math.fma(_t8, epsilon * java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_fma(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t47 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        return makeRotationLookAlong_rh_s1465dddc_1_fma(dirX, dirY, this, _t16, _t38, _t39, _t40, _t47, -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, java.lang.Math.fma(dirZ, _t16, 1.0f), -_t38, _t39 * _t47, _t38 * _t47);
    }

    /**
     * Private body of {@code makeRotationLookAlong} for {@code Handedness.RIGHT_HANDED}; reached
     * only through the public {@code makeRotationLookAlong} dispatcher.
     */
    @Mutated private FloatQuat makeRotationLookAlong_rh_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _sp0 = ((dirZ) * (upZ) + (((dirX) * (upX) + (dirY * upY)))) / _t8;
        if (!(_t8 > 1.1754944E-38f && _t8 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t16 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t29 = ((upZ) - (dirZ) * (_sp0));
        float _t30 = ((upY) - (dirY) * (_sp0));
        float _t31 = ((upX) - (dirX) * (_sp0));
        float _t38 = ((dirY) * (_t29) - (dirZ * _t30));
        float _t39 = ((dirX) * (_t30) - (dirY * _t31));
        float _t40 = ((dirZ) * (_t31) - (dirX * _t29));
        float _ct0 = ((_t39) * (_t39) + (((_t38) * (_t38) + (_t40 * _t40))));
        if (!(_ct0 > ((_t8) * (epsilon * ((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f)) && _ct0 < Float.POSITIVE_INFINITY)) return makeRotationLookAlong_rh_degenerate_mulAdd(dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        float _t47 = (1.0f / (float) java.lang.Math.sqrt(_ct0));
        float _t17 = dirX * _t16;
        float _t18 = dirZ * _t16;
        return makeRotationLookAlong_rh_s1465dddc_1_mulAdd(dirX, dirY, this, _t16, _t38, _t39, _t40, _t47, -dirZ, _t17, _t18, dirY * _t16, -_t18, -_t17, ((dirZ) * (_t16) + (1.0f)), -_t38, _t39 * _t47, _t38 * _t47);
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private FloatQuat makeRotationLookAlong_rh_degenerate_fma(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        return makeRotationLookAlong_rh_degenerate_s65a2e42b_1_fma(epsilon, this, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, _t45, _t46, java.lang.Math.fma(_t44, _t24, _t21), java.lang.Math.fma(_t45, _t25, _t46 * -_t26));
    }

    /**
     * Degenerate-input path of {@code makeRotationLookAlong}: its methods leave here when their
     * input spans no proper basis (a zero direction, an up vector parallel to it or zero, NaN);
     * reached only through them.
     */
    @Mutated private FloatQuat makeRotationLookAlong_rh_degenerate_mulAdd(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
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
        return makeRotationLookAlong_rh_degenerate_s65a2e42b_1_mulAdd(epsilon, this, _t21, _t22, _t23, _t24, _t25, _t26, _t29, _t30, 1.0f - _t24, 1.0f + _t24, _t37, _t38, _t42, _t45, _t46, ((_t44) * (_t24) + (_t21)), ((_t45) * (_t25) - (_t46 * _t26)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatQuat makeRotationLookAlong_rh_degenerate_s65a2e42b_1_fma(float epsilon, FloatQuatImpl d, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47, float _t56) {
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
        float _t88 = java.lang.Math.fma(_t69, _t25, -(_t70 * _t24));
        return makeRotationLookAlong_rh_degenerate_s65a2e42b_2_fma(d, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, java.lang.Math.fma(_t67, _t63, _t25), java.lang.Math.fma(_t67, _t63, -_t25), _t88, java.lang.Math.fma(_t73, _t24, -(_t69 * _t26)), Math.max(_t88, _t30), java.lang.Math.fma(_t72, _t26, java.lang.Math.fma(_t73, _t25, _t29)), java.lang.Math.fma(_t70, _t26, java.lang.Math.fma(_t73, -_t25, _t29)));
    }

    /** Piece 2 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatQuat makeRotationLookAlong_rh_degenerate_s65a2e42b_1_mulAdd(float epsilon, FloatQuatImpl d, float _t21, float _t22, float _t23, float _t24, float _t25, float _t26, float _t29, float _t30, float _t32, float _t33, float _t37, float _t38, float _t42, float _t45, float _t46, float _t47, float _t56) {
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
        float _t88 = ((_t69) * (_t25) - (_t70 * _t24));
        return makeRotationLookAlong_rh_degenerate_s65a2e42b_2_mulAdd(d, _t24, _t25, _t30, _t32, _t33, _t64, _t65, _t67, -_t67, _t69, _t70, -_t69, _t72, ((_t67) * (_t63) + (_t25)), ((_t67) * (_t63) - (_t25)), _t88, ((_t73) * (_t24) - (_t69 * _t26)), Math.max(_t88, _t30), ((_t72) * (_t26) + (((_t73) * (_t25) + (_t29)))), ((_t70) * (_t26) + (((_t29) - (_t73) * (_t25)))));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatQuat makeRotationLookAlong_rh_degenerate_s65a2e42b_2_fma(FloatQuatImpl d, float _t24, float _t25, float _t30, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t76, float _t88, float _t93, float _t94, float _t96, float _t97) {
        float _t98 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t30)));
        float _t99 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t67, _t64, _t32)));
        float _t100 = java.lang.Math.fma(_t67, _t64, java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, _t33)));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99));
        float _t102 = java.lang.Math.fma(_t69, _t25, java.lang.Math.fma(_t72, _t24, java.lang.Math.fma(_t68, _t64, _t33)));
        float _t103 = java.lang.Math.fma(_t71, _t25, java.lang.Math.fma(_t70, _t24, java.lang.Math.fma(_t68, _t64, _t32)));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t102));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103));
        float _t107 = java.lang.Math.fma(_t67, _t65, _t93);
        d.x = _t98 > 0.0f ? _sp0 * _t96 : _t70 > _t94 ? 0.5f * (float) java.lang.Math.sqrt(_t100) : _t88 > _t30 ? _sp1 * _t107 : _sp2 * _t76;
        return makeRotationLookAlong_rh_degenerate_s57efa25_3(d, _t30, _t70, _t74, _t76, _t88, _t94, _t96, _t97, _t98, _t99, _sp0, _t102, _t103, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), _sp1, _sp2, _t107, java.lang.Math.fma(_t68, _t65, _t93));
    }

    /** Piece 3 of {@code makeRotationLookAlong_rh_degenerate}, split to fit the inline budget; reached only through it. */
    private FloatQuat makeRotationLookAlong_rh_degenerate_s65a2e42b_2_mulAdd(FloatQuatImpl d, float _t24, float _t25, float _t30, float _t32, float _t33, float _t64, float _t65, float _t67, float _t68, float _t69, float _t70, float _t71, float _t72, float _t74, float _t76, float _t88, float _t93, float _t94, float _t96, float _t97) {
        float _t98 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t30))))));
        float _t99 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t67) * (_t64) + (_t32))))));
        float _t100 = ((_t67) * (_t64) + (((_t71) * (_t25) + (((_t70) * (_t24) + (_t33))))));
        float _sp0 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t99));
        float _t102 = ((_t69) * (_t25) + (((_t72) * (_t24) + (((_t68) * (_t64) + (_t33))))));
        float _t103 = ((_t71) * (_t25) + (((_t70) * (_t24) + (((_t68) * (_t64) + (_t32))))));
        float _sp1 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t102));
        float _sp2 = 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t103));
        float _t107 = ((_t67) * (_t65) + (_t93));
        d.x = _t98 > 0.0f ? _sp0 * _t96 : _t70 > _t94 ? 0.5f * (float) java.lang.Math.sqrt(_t100) : _t88 > _t30 ? _sp1 * _t107 : _sp2 * _t76;
        return makeRotationLookAlong_rh_degenerate_s57efa25_3(d, _t30, _t70, _t74, _t76, _t88, _t94, _t96, _t97, _t98, _t99, _sp0, _t102, _t103, 0.5f * (1.0f / (float) java.lang.Math.sqrt(_t100)), _sp1, _sp2, _t107, ((_t68) * (_t65) + (_t93)));
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
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @param handedness the handedness of the coordinate system to map into
     * @return this
     */
    @Mutated public FloatQuat makeRotationLookAlong(Float3R dir, Float3R up, float epsilon, Handedness handedness) {
        if (Math.useFma()) {
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
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @param handedness the handedness of the coordinate system to map into
     * @return this
     */
    @Mutated public FloatQuat makeRotationLookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon, Handedness handedness) {
        if (Math.useFma()) {
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
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @return this
     */
    @Mutated public FloatQuat makeRotationLookAlong(Float3R dir, Float3R up, float epsilon) { return makeRotationLookAlong(dir, up, epsilon, Handedness.RIGHT_HANDED); }

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
     *        place of {@code up}); the overload without it uses {@code 2^-36}
     * @return this
     */
    @Mutated public FloatQuat makeRotationLookAlong(float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) { return makeRotationLookAlong(dirX, dirY, dirZ, upX, upY, upZ, epsilon, Handedness.RIGHT_HANDED); }

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
    @Mutated public FloatQuat makeRotationTo(Float3R fromDir, Float3R toDir) {
        float fromDirX = fromDir.x();
        float fromDirY = fromDir.y();
        float fromDirZ = fromDir.z();
        float toDirX = toDir.x();
        float toDirY = toDir.y();
        float toDirZ = toDir.z();
        if (!Math.useFma()) return makeRotationTo_mulAdd(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t15 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        float _t16 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        float _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        makeRotationTo_sb47fb53_c0(this, 0.5f * _t23, _t15, (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))), _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), _t13, _t17, _t19, _t16, _t18);
        return this;
    }

    /** Private store group 0 of {@code makeRotationTo}: computes and stores it; reached only through it. */
    private void makeRotationTo_sb47fb53_c0(FloatQuatImpl _dst, float _t24, float _t15, float _t32, float _t29, float _t30, float _t13, float _t17, float _t19, float _t16, float _t18) {
        _dst.x = _t24 > 6.0E-8f ? _t15 * _t32 : _t29 != 0.0f ? _t30 * _t13 : 0.0f;
        _dst.y = _t24 > 6.0E-8f ? _t17 * _t32 : _t29 != 0.0f ? _t30 * _t19 : 0.0f;
        _dst.z = _t24 > 6.0E-8f ? _t16 * _t32 : _t29 != 0.0f ? _t30 * _t18 : 0.0f;
        _dst.w = _t24 > 6.0E-8f ? _t24 * _t32 : 0.0f;
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
        if (!Math.useFma()) return makeRotationTo_mulAdd(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ);
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t15 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        float _t16 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        float _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        makeRotationTo_sb47fb53_c0(this, 0.5f * _t23, _t15, (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))), _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), _t13, _t17, _t19, _t16, _t18);
        return this;
    }

    /** {@code makeRotationTo} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatQuat makeRotationTo_mulAdd(float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ) {
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t15 = ((fromDirY) * (toDirZ) - (fromDirZ * toDirY));
        float _t16 = ((fromDirX) * (toDirY) - (fromDirY * toDirX));
        float _t17 = ((fromDirZ) * (toDirX) - (fromDirX * toDirZ));
        float _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        makeRotationTo_sb47fb53_c0(this, 0.5f * _t23, _t15, (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))), _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), _t13, _t17, _t19, _t16, _t18);
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
     *        without it uses {@code 6e-8}
     * @return this
     */
    @Mutated public FloatQuat makeRotationTo(Float3R fromDir, Float3R toDir, float epsilon) {
        float fromDirX = fromDir.x();
        float fromDirY = fromDir.y();
        float fromDirZ = fromDir.z();
        float toDirX = toDir.x();
        float toDirY = toDir.y();
        float toDirZ = toDir.z();
        if (!Math.useFma()) return makeRotationTo_mulAdd(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t15 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        float _t16 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        float _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        makeRotationTo_s27e2c0ff_c0(this, 0.5f * _t23, epsilon, _t15, (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))), _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), _t13, _t17, _t19, _t16, _t18);
        return this;
    }

    /** Private store group 0 of {@code makeRotationTo}: computes and stores it; reached only through it. */
    private void makeRotationTo_s27e2c0ff_c0(FloatQuatImpl _dst, float _t24, float epsilon, float _t15, float _t32, float _t29, float _t30, float _t13, float _t17, float _t19, float _t16, float _t18) {
        _dst.x = _t24 > epsilon ? _t15 * _t32 : _t29 != 0.0f ? _t30 * _t13 : 0.0f;
        _dst.y = _t24 > epsilon ? _t17 * _t32 : _t29 != 0.0f ? _t30 * _t19 : 0.0f;
        _dst.z = _t24 > epsilon ? _t16 * _t32 : _t29 != 0.0f ? _t30 * _t18 : 0.0f;
        _dst.w = _t24 > epsilon ? _t24 * _t32 : 0.0f;
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
     *        without it uses {@code 6e-8}
     * @return this
     */
    @Mutated public FloatQuat makeRotationTo(float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
        if (!Math.useFma()) return makeRotationTo_mulAdd(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon);
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t15 = java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY));
        float _t16 = java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX);
        float _t17 = java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ);
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        makeRotationTo_s27e2c0ff_c0(this, 0.5f * _t23, epsilon, _t15, (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t16, _t16, java.lang.Math.fma(_t15, _t15, _t17 * _t17))))), _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), _t13, _t17, _t19, _t16, _t18);
        return this;
    }

    /** {@code makeRotationTo} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatQuat makeRotationTo_mulAdd(float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon) {
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t15 = ((fromDirY) * (toDirZ) - (fromDirZ * toDirY));
        float _t16 = ((fromDirX) * (toDirY) - (fromDirY * toDirX));
        float _t17 = ((fromDirZ) * (toDirX) - (fromDirX * toDirZ));
        float _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        makeRotationTo_s27e2c0ff_c0(this, 0.5f * _t23, epsilon, _t15, (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t16) * (_t16) + (((_t15) * (_t15) + (_t17 * _t17)))))))), _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), _t13, _t17, _t19, _t16, _t18);
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
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        this.x = _t1;
        this.y = 0.0f;
        this.z = 0.0f;
        this.w = Math.cosFromSin(_t1, _t0);
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
    @Mutated public FloatQuat makeRotationXYZ(float angleX, float angleY, float angleZ) {
        if (!Math.useFma()) return makeRotationXYZ_mulAdd(angleX, angleY, angleZ);
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
        this.x = java.lang.Math.fma(_t10, _t7, _t11 * _t5);
        this.y = java.lang.Math.fma(_t11, _t7, _t10 * -_t5);
        this.z = java.lang.Math.fma(_t9, _t7, _t12 * _t5);
        this.w = java.lang.Math.fma(_t12, _t7, _t9 * -_t5);
        return this;
    }

    /** {@code makeRotationXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatQuat makeRotationXYZ_mulAdd(float angleX, float angleY, float angleZ) {
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
        this.x = ((_t10) * (_t7) + (_t11 * _t5));
        this.y = ((_t11) * (_t7) - (_t10 * _t5));
        this.z = ((_t9) * (_t7) + (_t12 * _t5));
        this.w = ((_t12) * (_t7) - (_t9 * _t5));
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
    @Mutated public FloatQuat makeRotationXZY(float angleX, float angleZ, float angleY) {
        if (!Math.useFma()) return makeRotationXZY_mulAdd(angleX, angleZ, angleY);
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
        this.x = java.lang.Math.fma(_t10, _t7, -(_t11 * _t5));
        this.y = java.lang.Math.fma(_t12, _t5, -(_t9 * _t7));
        this.z = java.lang.Math.fma(_t10, _t5, _t11 * _t7);
        this.w = java.lang.Math.fma(_t9, _t5, _t12 * _t7);
        return this;
    }

    /** {@code makeRotationXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatQuat makeRotationXZY_mulAdd(float angleX, float angleZ, float angleY) {
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
        this.x = ((_t10) * (_t7) - (_t11 * _t5));
        this.y = ((_t12) * (_t5) - (_t9 * _t7));
        this.z = ((_t10) * (_t5) + (_t11 * _t7));
        this.w = ((_t9) * (_t5) + (_t12 * _t7));
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
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        this.x = 0.0f;
        this.y = _t1;
        this.z = 0.0f;
        this.w = Math.cosFromSin(_t1, _t0);
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
    @Mutated public FloatQuat makeRotationYXZ(float angleY, float angleX, float angleZ) {
        if (!Math.useFma()) return makeRotationYXZ_mulAdd(angleY, angleX, angleZ);
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
        this.x = java.lang.Math.fma(_t10, _t7, _t11 * _t5);
        this.y = java.lang.Math.fma(_t11, _t7, -(_t10 * _t5));
        this.z = java.lang.Math.fma(_t12, _t5, -(_t9 * _t7));
        this.w = java.lang.Math.fma(_t9, _t5, _t12 * _t7);
        return this;
    }

    /** {@code makeRotationYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatQuat makeRotationYXZ_mulAdd(float angleY, float angleX, float angleZ) {
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
        this.x = ((_t10) * (_t7) + (_t11 * _t5));
        this.y = ((_t11) * (_t7) - (_t10 * _t5));
        this.z = ((_t12) * (_t5) - (_t9 * _t7));
        this.w = ((_t9) * (_t5) + (_t12 * _t7));
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
    @Mutated public FloatQuat makeRotationYZX(float angleY, float angleZ, float angleX) {
        if (!Math.useFma()) return makeRotationYZX_mulAdd(angleY, angleZ, angleX);
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
        this.x = java.lang.Math.fma(_t9, _t6, _t12 * _t5);
        this.y = java.lang.Math.fma(_t10, _t6, _t11 * _t5);
        this.z = java.lang.Math.fma(_t11, _t6, _t10 * -_t5);
        this.w = java.lang.Math.fma(_t12, _t6, _t9 * -_t5);
        return this;
    }

    /** {@code makeRotationYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatQuat makeRotationYZX_mulAdd(float angleY, float angleZ, float angleX) {
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
        this.x = ((_t9) * (_t6) + (_t12 * _t5));
        this.y = ((_t10) * (_t6) + (_t11 * _t5));
        this.z = ((_t11) * (_t6) - (_t10 * _t5));
        this.w = ((_t12) * (_t6) - (_t9 * _t5));
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
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = _t1;
        this.w = Math.cosFromSin(_t1, _t0);
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
    @Mutated public FloatQuat makeRotationZXY(float angleZ, float angleX, float angleY) {
        if (!Math.useFma()) return makeRotationZXY_mulAdd(angleZ, angleX, angleY);
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
        this.x = java.lang.Math.fma(_t10, _t7, _t11 * -_t5);
        this.y = java.lang.Math.fma(_t9, _t7, _t12 * _t5);
        this.z = java.lang.Math.fma(_t10, _t5, _t11 * _t7);
        this.w = java.lang.Math.fma(_t12, _t7, _t9 * -_t5);
        return this;
    }

    /** {@code makeRotationZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatQuat makeRotationZXY_mulAdd(float angleZ, float angleX, float angleY) {
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
        this.x = ((_t10) * (_t7) - (_t11 * _t5));
        this.y = ((_t9) * (_t7) + (_t12 * _t5));
        this.z = ((_t10) * (_t5) + (_t11 * _t7));
        this.w = ((_t12) * (_t7) - (_t9 * _t5));
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
    @Mutated public FloatQuat makeRotationZYX(float angleZ, float angleY, float angleX) {
        if (!Math.useFma()) return makeRotationZYX_mulAdd(angleZ, angleY, angleX);
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
        this.x = java.lang.Math.fma(_t12, _t5, -(_t9 * _t8));
        this.y = java.lang.Math.fma(_t10, _t8, _t11 * _t5);
        this.z = java.lang.Math.fma(_t11, _t8, -(_t10 * _t5));
        this.w = java.lang.Math.fma(_t9, _t5, _t12 * _t8);
        return this;
    }

    /** {@code makeRotationZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatQuat makeRotationZYX_mulAdd(float angleZ, float angleY, float angleX) {
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
        this.x = ((_t12) * (_t5) - (_t9 * _t8));
        this.y = ((_t10) * (_t8) + (_t11 * _t5));
        this.z = ((_t11) * (_t8) - (_t10 * _t5));
        this.w = ((_t9) * (_t5) + (_t12 * _t8));
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
        if (Math.useFma()) {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            float _rd0 = this.x;
            float _rd1 = this.y;
            d.x = java.lang.Math.fma(_rd0, _t2, this.w * _t1);
            d.y = java.lang.Math.fma(_rd1, _t2, -(this.z * _t1));
            d.z = java.lang.Math.fma(_rd1, _t1, this.z * _t2);
            d.w = java.lang.Math.fma(this.w, _t2, -(_rd0 * _t1));
            return d;
        } else {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            float _rd0 = this.x;
            float _rd1 = this.y;
            d.x = ((_rd0) * (_t2) + (this.w * _t1));
            d.y = ((_rd1) * (_t2) - (this.z * _t1));
            d.z = ((_rd1) * (_t1) + (this.z * _t2));
            d.w = ((this.w) * (_t2) - (_rd0 * _t1));
            return d;
        }
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
        if (Math.useFma()) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            d.x = java.lang.Math.fma(this.x, _t2, this.w * _t1);
            d.y = java.lang.Math.fma(this.y, _t2, -(this.z * _t1));
            d.z = java.lang.Math.fma(this.y, _t1, this.z * _t2);
            d.w = java.lang.Math.fma(this.w, _t2, -(this.x * _t1));
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            d.x = ((this.x) * (_t2) + (this.w * _t1));
            d.y = ((this.y) * (_t2) - (this.z * _t1));
            d.z = ((this.y) * (_t1) + (this.z * _t2));
            d.w = ((this.w) * (_t2) - (this.x * _t1));
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
    public FloatQuat preRotateY(float angle, @Mutated FloatQuat dest) {
        if (Math.useFma()) {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            float _rd0 = this.x;
            float _rd1 = this.y;
            d.x = java.lang.Math.fma(_rd0, _t2, this.z * _t1);
            d.y = java.lang.Math.fma(_rd1, _t2, this.w * _t1);
            d.z = java.lang.Math.fma(this.z, _t2, -(_rd0 * _t1));
            d.w = java.lang.Math.fma(this.w, _t2, -(_rd1 * _t1));
            return d;
        } else {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            float _rd0 = this.x;
            float _rd1 = this.y;
            d.x = ((_rd0) * (_t2) + (this.z * _t1));
            d.y = ((_rd1) * (_t2) + (this.w * _t1));
            d.z = ((this.z) * (_t2) - (_rd0 * _t1));
            d.w = ((this.w) * (_t2) - (_rd1 * _t1));
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
        if (Math.useFma()) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            d.x = java.lang.Math.fma(this.x, _t2, this.z * _t1);
            d.y = java.lang.Math.fma(this.y, _t2, this.w * _t1);
            d.z = java.lang.Math.fma(this.z, _t2, -(this.x * _t1));
            d.w = java.lang.Math.fma(this.w, _t2, -(this.y * _t1));
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            d.x = ((this.x) * (_t2) + (this.z * _t1));
            d.y = ((this.y) * (_t2) + (this.w * _t1));
            d.z = ((this.z) * (_t2) - (this.x * _t1));
            d.w = ((this.w) * (_t2) - (this.y * _t1));
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
    public FloatQuat preRotateZ(float angle, @Mutated FloatQuat dest) {
        if (Math.useFma()) {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            float _rd0 = this.x;
            float _rd1 = this.z;
            d.x = java.lang.Math.fma(_rd0, _t2, -(this.y * _t1));
            d.y = java.lang.Math.fma(_rd0, _t1, this.y * _t2);
            d.z = java.lang.Math.fma(_rd1, _t2, this.w * _t1);
            d.w = java.lang.Math.fma(this.w, _t2, -(_rd1 * _t1));
            return d;
        } else {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            float _rd0 = this.x;
            float _rd1 = this.z;
            d.x = ((_rd0) * (_t2) - (this.y * _t1));
            d.y = ((_rd0) * (_t1) + (this.y * _t2));
            d.z = ((_rd1) * (_t2) + (this.w * _t1));
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
        if (Math.useFma()) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            d.x = java.lang.Math.fma(this.x, _t2, -(this.y * _t1));
            d.y = java.lang.Math.fma(this.x, _t1, this.y * _t2);
            d.z = java.lang.Math.fma(this.z, _t2, this.w * _t1);
            d.w = java.lang.Math.fma(this.w, _t2, -(this.z * _t1));
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            d.x = ((this.x) * (_t2) - (this.y * _t1));
            d.y = ((this.x) * (_t1) + (this.y * _t2));
            d.z = ((this.z) * (_t2) + (this.w * _t1));
            d.w = ((this.w) * (_t2) - (this.z * _t1));
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
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = axisX * _t1;
        float _t3 = axisZ * _t1;
        float _t4 = axisY * _t1;
        float _t5 = Math.cosFromSin(_t1, _t0);
        float _rd0 = this.x;
        float _rd1 = this.y;
        float _rd2 = this.z;
        d.x = Math.fma(_rd0, _t5, this.w * _t2) + Math.fma(_rd1, _t3, -(_rd2 * _t4));
        d.y = Math.fma(_rd1, _t5, _rd2 * _t2) + Math.fma(this.w, _t4, -_rd0 * _t3);
        d.z = Math.fma(_rd0, _t4, this.w * _t3) + Math.fma(_rd2, _t5, -(_rd1 * _t2));
        d.w = Math.fma(this.w, _t5, -_rd0 * _t2) - Math.fma(_rd1, _t4, _rd2 * _t3);
        return d;
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
        if (axisY == 0 && axisZ == 0 && java.lang.Math.abs(axisX) == 1) return rotateX(axisX * angle, dest);
        if (axisX == 0 && axisZ == 0 && java.lang.Math.abs(axisY) == 1) return rotateY(axisY * angle, dest);
        if (axisX == 0 && axisY == 0 && java.lang.Math.abs(axisZ) == 1) return rotateZ(axisZ * angle, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        float _t2 = axisX * _t1;
        float _t3 = axisZ * _t1;
        float _t4 = axisY * _t1;
        float _t5 = Math.cosFromSin(_t1, _t0);
        d.x = Math.fma(this.x, _t5, this.w * _t2) + Math.fma(this.y, _t3, -(this.z * _t4));
        d.y = Math.fma(this.y, _t5, this.z * _t2) + Math.fma(this.w, _t4, -this.x * _t3);
        d.z = Math.fma(this.x, _t4, this.w * _t3) + Math.fma(this.z, _t5, -(this.y * _t2));
        d.w = Math.fma(this.w, _t5, -this.x * _t2) - Math.fma(this.y, _t4, this.z * _t3);
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
        float fromDirX = fromDir.x();
        float fromDirY = fromDir.y();
        float fromDirZ = fromDir.z();
        float toDirX = toDir.x();
        float toDirY = toDir.y();
        float toDirZ = toDir.z();
        if (!Math.useFma()) return rotateTo_mulAdd(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, dest);
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        rotateTo_s3892ffea_tail_fma(d, _t23, java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX), java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY)), java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ), 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), _t13, _t18, _t19, _r0, _r1, _r2, _r3);
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
        float fromDirX = fromDir.x();
        float fromDirY = fromDir.y();
        float fromDirZ = fromDir.z();
        float toDirX = toDir.x();
        float toDirY = toDir.y();
        float toDirZ = toDir.z();
        if (!Math.useFma()) return rotateTo_mulAdd(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        rotateTo_s3b843f3f_tail_fma(d, _t23, java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX), java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY)), java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ), 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), _t13, _t18, _t19, _r0, _r1, _r2, _r3);
        return d;
    }

    /**
     * Private store group 0 of {@code rotateTo}: computes and stores it. Shared by the identical
     * private paths of {@code rotateTo}, {@code rotateXYZ}, {@code rotateXZY}, {@code rotateYXZ},
     * {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only through them.
     */
    private void rotateTo_s3892ffea_c0_fma(FloatQuatImpl _dst, float _r0, float _t44, float _r1, float _t45, float _r2, float _t46, float _r3, float _t47) {
        _dst.x = java.lang.Math.fma(_r0, _t44, _r1 * _t45) + java.lang.Math.fma(_r2, _t46, -(_r3 * _t47));
        _dst.y = java.lang.Math.fma(_r2, _t44, _r3 * _t45) + java.lang.Math.fma(_r1, _t47, -_r0 * _t46);
        _dst.z = java.lang.Math.fma(_r0, _t47, _r1 * _t46) + java.lang.Math.fma(_r3, _t44, -(_r2 * _t45));
        _dst.w = java.lang.Math.fma(_r1, _t44, -_r0 * _t45) - java.lang.Math.fma(_r2, _t47, _r3 * _t46);
    }

    /**
     * Private store group 0 of {@code rotateTo}: computes and stores it. Shared by the identical
     * private paths of {@code rotateTo}, {@code rotateXYZ}, {@code rotateXZY}, {@code rotateYXZ},
     * {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only through them.
     */
    private void rotateTo_s3892ffea_c0_mulAdd(FloatQuatImpl _dst, float _r0, float _t44, float _r1, float _t45, float _r2, float _t46, float _r3, float _t47) {
        _dst.x = ((_r0) * (_t44) + (_r1 * _t45)) + ((_r2) * (_t46) - (_r3 * _t47));
        _dst.y = ((_r2) * (_t44) + (_r3 * _t45)) + ((_r1) * (_t47) - (_r0 * _t46));
        _dst.z = ((_r0) * (_t47) + (_r1 * _t46)) + ((_r3) * (_t44) - (_r2 * _t45));
        _dst.w = ((_r1) * (_t44) - (_r0 * _t45)) - ((_r2) * (_t47) + (_r3 * _t46));
    }

    /** Private tail of {@code rotateTo}; reached only through it. */
    private void rotateTo_s3892ffea_tail_fma(FloatQuatImpl _dst, float _t23, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t13, float _t18, float _t19, float _r0, float _r1, float _r2, float _r3) {
        float _t35 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17)))));
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
        rotateTo_s3892ffea_c0_fma(_dst, _r0, _t44, _r1, _t45, _r2, _t46, _r3, _t47);
    }

    /** Private tail of {@code rotateTo}; reached only through it. */
    private void rotateTo_s3892ffea_tail_mulAdd(FloatQuatImpl _dst, float _t23, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t13, float _t18, float _t19, float _r0, float _r1, float _r2, float _r3) {
        float _t35 = (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))))))));
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
        rotateTo_s3892ffea_c0_mulAdd(_dst, _r0, _t44, _r1, _t45, _r2, _t46, _r3, _t47);
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
        if (!Math.useFma()) return rotateTo_mulAdd(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, dest);
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        rotateTo_s3892ffea_tail_fma(d, _t23, java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX), java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY)), java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ), 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), _t13, _t18, _t19, _r0, _r1, _r2, _r3);
        return d;
    }

    /** {@code rotateTo} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatQuat rotateTo_mulAdd(float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, @Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        rotateTo_s3892ffea_tail_mulAdd(d, _t23, ((fromDirX) * (toDirY) - (fromDirY * toDirX)), ((fromDirY) * (toDirZ) - (fromDirZ * toDirY)), ((fromDirZ) * (toDirX) - (fromDirX * toDirZ)), 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), _t13, _t18, _t19, _r0, _r1, _r2, _r3);
        return d;
    }

    /**
     * Private store group 0 of {@code rotateTo}: computes and stores it. Shared by the identical
     * private paths of {@code rotateTo}, {@code rotateXYZ}, {@code rotateXZY}, {@code rotateYXZ},
     * {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only through them.
     */
    private void rotateTo_s3b843f3f_c0_fma(DoubleQuatImpl _dst, float _r0, float _t44, float _r1, float _t45, float _r2, float _t46, float _r3, float _t47) {
        _dst.x = java.lang.Math.fma(_r0, _t44, _r1 * _t45) + java.lang.Math.fma(_r2, _t46, -(_r3 * _t47));
        _dst.y = java.lang.Math.fma(_r2, _t44, _r3 * _t45) + java.lang.Math.fma(_r1, _t47, -_r0 * _t46);
        _dst.z = java.lang.Math.fma(_r0, _t47, _r1 * _t46) + java.lang.Math.fma(_r3, _t44, -(_r2 * _t45));
        _dst.w = java.lang.Math.fma(_r1, _t44, -_r0 * _t45) - java.lang.Math.fma(_r2, _t47, _r3 * _t46);
    }

    /**
     * Private store group 0 of {@code rotateTo}: computes and stores it. Shared by the identical
     * private paths of {@code rotateTo}, {@code rotateXYZ}, {@code rotateXZY}, {@code rotateYXZ},
     * {@code rotateYZX}, {@code rotateZXY} and {@code rotateZYX}; reached only through them.
     */
    private void rotateTo_s3b843f3f_c0_mulAdd(DoubleQuatImpl _dst, float _r0, float _t44, float _r1, float _t45, float _r2, float _t46, float _r3, float _t47) {
        _dst.x = ((_r0) * (_t44) + (_r1 * _t45)) + ((_r2) * (_t46) - (_r3 * _t47));
        _dst.y = ((_r2) * (_t44) + (_r3 * _t45)) + ((_r1) * (_t47) - (_r0 * _t46));
        _dst.z = ((_r0) * (_t47) + (_r1 * _t46)) + ((_r3) * (_t44) - (_r2 * _t45));
        _dst.w = ((_r1) * (_t44) - (_r0 * _t45)) - ((_r2) * (_t47) + (_r3 * _t46));
    }

    /** Private tail of {@code rotateTo}; reached only through it. */
    private void rotateTo_s3b843f3f_tail_fma(DoubleQuatImpl _dst, float _t23, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t13, float _t18, float _t19, float _r0, float _r1, float _r2, float _r3) {
        float _t35 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17)))));
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
        rotateTo_s3b843f3f_c0_fma(_dst, _r0, _t44, _r1, _t45, _r2, _t46, _r3, _t47);
    }

    /** Private tail of {@code rotateTo}; reached only through it. */
    private void rotateTo_s3b843f3f_tail_mulAdd(DoubleQuatImpl _dst, float _t23, float _t15, float _t16, float _t17, float _t24, float _t29, float _t30, float _t13, float _t18, float _t19, float _r0, float _r1, float _r2, float _r3) {
        float _t35 = (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))))))));
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
        rotateTo_s3b843f3f_c0_mulAdd(_dst, _r0, _t44, _r1, _t45, _r2, _t46, _r3, _t47);
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
        if (!Math.useFma()) return rotateTo_mulAdd(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        rotateTo_s3b843f3f_tail_fma(d, _t23, java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX), java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY)), java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ), 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), _t13, _t18, _t19, _r0, _r1, _r2, _r3);
        return d;
    }

    /** {@code rotateTo} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateTo_mulAdd(float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        rotateTo_s3b843f3f_tail_mulAdd(d, _t23, ((fromDirX) * (toDirY) - (fromDirY * toDirX)), ((fromDirY) * (toDirZ) - (fromDirZ * toDirY)), ((fromDirZ) * (toDirX) - (fromDirX * toDirZ)), 0.5f * _t23, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), _t13, _t18, _t19, _r0, _r1, _r2, _r3);
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
     *        without it uses {@code 6e-8}
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat rotateTo(Float3R fromDir, Float3R toDir, float epsilon, @Mutated FloatQuat dest) {
        float fromDirX = fromDir.x();
        float fromDirY = fromDir.y();
        float fromDirZ = fromDir.z();
        float toDirX = toDir.x();
        float toDirY = toDir.y();
        float toDirZ = toDir.z();
        if (!Math.useFma()) return rotateTo_mulAdd(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon, dest);
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        rotateTo_s1833b3be_tail_fma(d, _t23, java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX), java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY)), java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ), 0.5f * _t23, epsilon, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), _t13, _t18, _t19, _r0, _r1, _r2, _r3);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
     * <p>
     * Valid input: {@code fromDir} must have unit length; {@code toDir} must have unit length;
     * {@code epsilon} must not be negative.
     *
     * @param fromDir the direction to rotate from (must be a unit vector)
     * @param toDir the direction to rotate onto (must be a unit vector)
     * @param epsilon the value of {@code 1 + dot(fromDir, toDir)} at or below which the directions
     *        count as opposite and an arbitrary perpendicular rotation axis is chosen; the overload
     *        without it uses {@code 6e-8}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat rotateTo(Float3R fromDir, Float3R toDir, float epsilon, @Mutated DoubleQuat dest) {
        float fromDirX = fromDir.x();
        float fromDirY = fromDir.y();
        float fromDirZ = fromDir.z();
        float toDirX = toDir.x();
        float toDirY = toDir.y();
        float toDirZ = toDir.z();
        if (!Math.useFma()) return rotateTo_mulAdd(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        rotateTo_s4ffa05eb_tail_fma(d, _t23, java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX), java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY)), java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ), 0.5f * _t23, epsilon, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), _t13, _t18, _t19, _r0, _r1, _r2, _r3);
        return d;
    }

    /** Private tail of {@code rotateTo}; reached only through it. */
    private void rotateTo_s1833b3be_tail_fma(FloatQuatImpl _dst, float _t23, float _t15, float _t16, float _t17, float _t24, float epsilon, float _t29, float _t30, float _t13, float _t18, float _t19, float _r0, float _r1, float _r2, float _r3) {
        float _t35 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17)))));
        float _t44, _t45, _t46, _t47;
        if (_t24 > epsilon) {
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
        rotateTo_s3892ffea_c0_fma(_dst, _r0, _t44, _r1, _t45, _r2, _t46, _r3, _t47);
    }

    /** Private tail of {@code rotateTo}; reached only through it. */
    private void rotateTo_s1833b3be_tail_mulAdd(FloatQuatImpl _dst, float _t23, float _t15, float _t16, float _t17, float _t24, float epsilon, float _t29, float _t30, float _t13, float _t18, float _t19, float _r0, float _r1, float _r2, float _r3) {
        float _t35 = (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))))))));
        float _t44, _t45, _t46, _t47;
        if (_t24 > epsilon) {
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
        rotateTo_s3892ffea_c0_mulAdd(_dst, _r0, _t44, _r1, _t45, _r2, _t46, _r3, _t47);
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
     *        without it uses {@code 6e-8}
     * @param dest will hold the result
     * @return dest
     */
    public FloatQuat rotateTo(float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon, @Mutated FloatQuat dest) {
        if (!Math.useFma()) return rotateTo_mulAdd(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon, dest);
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        rotateTo_s1833b3be_tail_fma(d, _t23, java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX), java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY)), java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ), 0.5f * _t23, epsilon, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), _t13, _t18, _t19, _r0, _r1, _r2, _r3);
        return d;
    }

    /** {@code rotateTo} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatQuat rotateTo_mulAdd(float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon, @Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        rotateTo_s1833b3be_tail_mulAdd(d, _t23, ((fromDirX) * (toDirY) - (fromDirY * toDirX)), ((fromDirY) * (toDirZ) - (fromDirZ * toDirY)), ((fromDirZ) * (toDirX) - (fromDirX * toDirZ)), 0.5f * _t23, epsilon, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), _t13, _t18, _t19, _r0, _r1, _r2, _r3);
        return d;
    }

    /** Private tail of {@code rotateTo}; reached only through it. */
    private void rotateTo_s4ffa05eb_tail_fma(DoubleQuatImpl _dst, float _t23, float _t15, float _t16, float _t17, float _t24, float epsilon, float _t29, float _t30, float _t13, float _t18, float _t19, float _r0, float _r1, float _r2, float _r3) {
        float _t35 = (1.0f / (float) java.lang.Math.sqrt(java.lang.Math.fma(0.25f, _t23 * _t23, java.lang.Math.fma(_t15, _t15, java.lang.Math.fma(_t16, _t16, _t17 * _t17)))));
        float _t44, _t45, _t46, _t47;
        if (_t24 > epsilon) {
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
        rotateTo_s3b843f3f_c0_fma(_dst, _r0, _t44, _r1, _t45, _r2, _t46, _r3, _t47);
    }

    /** Private tail of {@code rotateTo}; reached only through it. */
    private void rotateTo_s4ffa05eb_tail_mulAdd(DoubleQuatImpl _dst, float _t23, float _t15, float _t16, float _t17, float _t24, float epsilon, float _t29, float _t30, float _t13, float _t18, float _t19, float _r0, float _r1, float _r2, float _r3) {
        float _t35 = (1.0f / (float) java.lang.Math.sqrt(((0.25f) * (_t23 * _t23) + (((_t15) * (_t15) + (((_t16) * (_t16) + (_t17 * _t17))))))));
        float _t44, _t45, _t46, _t47;
        if (_t24 > epsilon) {
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
        rotateTo_s3b843f3f_c0_mulAdd(_dst, _r0, _t44, _r1, _t45, _r2, _t46, _r3, _t47);
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
     * The computation is performed at {@code float} precision; each result component is widened to
     * {@code double} only when stored.
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
     *        without it uses {@code 6e-8}
     * @param dest will hold the result
     * @return dest
     */
    public DoubleQuat rotateTo(float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon, @Mutated DoubleQuat dest) {
        if (!Math.useFma()) return rotateTo_mulAdd(fromDirX, fromDirY, fromDirZ, toDirX, toDirY, toDirZ, epsilon, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t23 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t29 = java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t13, _t13, _t19 * _t19));
        rotateTo_s4ffa05eb_tail_fma(d, _t23, java.lang.Math.fma(fromDirX, toDirY, -fromDirY * toDirX), java.lang.Math.fma(fromDirY, toDirZ, -(fromDirZ * toDirY)), java.lang.Math.fma(fromDirZ, toDirX, -fromDirX * toDirZ), 0.5f * _t23, epsilon, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), _t13, _t18, _t19, _r0, _r1, _r2, _r3);
        return d;
    }

    /** {@code rotateTo} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateTo_mulAdd(float fromDirX, float fromDirY, float fromDirZ, float toDirX, float toDirY, float toDirZ, float epsilon, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
        float _t4 = fromDirZ + toDirZ;
        float _t5 = fromDirX + toDirX;
        float _t6 = fromDirY + toDirY;
        float _t13, _t18, _t19;
        if (java.lang.Math.abs(fromDirZ) < java.lang.Math.abs(fromDirX)) {
            _t13 = fromDirY;
            _t18 = 0.0f;
            _t19 = -fromDirX;
        } else {
            _t13 = 0.0f;
            _t18 = -fromDirY;
            _t19 = fromDirZ;
        }
        float _t23 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t29 = ((_t18) * (_t18) + (((_t13) * (_t13) + (_t19 * _t19))));
        rotateTo_s4ffa05eb_tail_mulAdd(d, _t23, ((fromDirX) * (toDirY) - (fromDirY * toDirX)), ((fromDirY) * (toDirZ) - (fromDirZ * toDirY)), ((fromDirZ) * (toDirX) - (fromDirX * toDirZ)), 0.5f * _t23, epsilon, _t29, (1.0f / (float) java.lang.Math.sqrt(_t29)), _t13, _t18, _t19, _r0, _r1, _r2, _r3);
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
    public FloatQuat rotateX(float angle, @Mutated FloatQuat dest) {
        if (Math.useFma()) {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            float _rd0 = this.x;
            float _rd1 = this.y;
            d.x = java.lang.Math.fma(_rd0, _t2, this.w * _t1);
            d.y = java.lang.Math.fma(_rd1, _t2, this.z * _t1);
            d.z = java.lang.Math.fma(this.z, _t2, -(_rd1 * _t1));
            d.w = java.lang.Math.fma(this.w, _t2, -(_rd0 * _t1));
            return d;
        } else {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            float _rd0 = this.x;
            float _rd1 = this.y;
            d.x = ((_rd0) * (_t2) + (this.w * _t1));
            d.y = ((_rd1) * (_t2) + (this.z * _t1));
            d.z = ((this.z) * (_t2) - (_rd1 * _t1));
            d.w = ((this.w) * (_t2) - (_rd0 * _t1));
            return d;
        }
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
        if (Math.useFma()) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            d.x = java.lang.Math.fma(this.x, _t2, this.w * _t1);
            d.y = java.lang.Math.fma(this.y, _t2, this.z * _t1);
            d.z = java.lang.Math.fma(this.z, _t2, -(this.y * _t1));
            d.w = java.lang.Math.fma(this.w, _t2, -(this.x * _t1));
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            d.x = ((this.x) * (_t2) + (this.w * _t1));
            d.y = ((this.y) * (_t2) + (this.z * _t1));
            d.z = ((this.z) * (_t2) - (this.y * _t1));
            d.w = ((this.w) * (_t2) - (this.x * _t1));
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
    public FloatQuat rotateXYZ(float angleX, float angleY, float angleZ, @Mutated FloatQuat dest) {
        if (!Math.useFma()) return rotateXYZ_mulAdd(angleX, angleY, angleZ, dest);
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
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
        rotateTo_s3892ffea_c0_fma(d, _r0, java.lang.Math.fma(_t14, _t8, _t9 * -_t5), _r1, java.lang.Math.fma(_t10, _t8, _t11 * _t5), _r2, java.lang.Math.fma(_t9, _t8, _t14 * _t5), _r3, java.lang.Math.fma(_t11, _t8, _t10 * -_t5));
        return d;
    }

    /** {@code rotateXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatQuat rotateXYZ_mulAdd(float angleX, float angleY, float angleZ, @Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
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
        rotateTo_s3892ffea_c0_mulAdd(d, _r0, ((_t14) * (_t8) - (_t9 * _t5)), _r1, ((_t10) * (_t8) + (_t11 * _t5)), _r2, ((_t9) * (_t8) + (_t14 * _t5)), _r3, ((_t11) * (_t8) - (_t10 * _t5)));
        return d;
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
        if (!Math.useFma()) return rotateXYZ_mulAdd(angleX, angleY, angleZ, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
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
        rotateTo_s3b843f3f_c0_fma(d, _r0, java.lang.Math.fma(_t14, _t8, _t9 * -_t5), _r1, java.lang.Math.fma(_t10, _t8, _t11 * _t5), _r2, java.lang.Math.fma(_t9, _t8, _t14 * _t5), _r3, java.lang.Math.fma(_t11, _t8, _t10 * -_t5));
        return d;
    }

    /** {@code rotateXYZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateXYZ_mulAdd(float angleX, float angleY, float angleZ, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
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
        rotateTo_s3b843f3f_c0_mulAdd(d, _r0, ((_t14) * (_t8) - (_t9 * _t5)), _r1, ((_t10) * (_t8) + (_t11 * _t5)), _r2, ((_t9) * (_t8) + (_t14 * _t5)), _r3, ((_t11) * (_t8) - (_t10 * _t5)));
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
    public FloatQuat rotateXZY(float angleX, float angleZ, float angleY, @Mutated FloatQuat dest) {
        if (!Math.useFma()) return rotateXZY_mulAdd(angleX, angleZ, angleY, dest);
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
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
        rotateTo_s3892ffea_c0_fma(d, _r0, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r1, java.lang.Math.fma(_t10, _t8, -(_t11 * _t5)), _r2, java.lang.Math.fma(_t10, _t5, _t11 * _t8), _r3, java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)));
        return d;
    }

    /** {@code rotateXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatQuat rotateXZY_mulAdd(float angleX, float angleZ, float angleY, @Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
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
        rotateTo_s3892ffea_c0_mulAdd(d, _r0, ((_t9) * (_t5) + (_t12 * _t8)), _r1, ((_t10) * (_t8) - (_t11 * _t5)), _r2, ((_t10) * (_t5) + (_t11 * _t8)), _r3, ((_t12) * (_t5) - (_t9 * _t8)));
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
        if (!Math.useFma()) return rotateXZY_mulAdd(angleX, angleZ, angleY, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
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
        rotateTo_s3b843f3f_c0_fma(d, _r0, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r1, java.lang.Math.fma(_t10, _t8, -(_t11 * _t5)), _r2, java.lang.Math.fma(_t10, _t5, _t11 * _t8), _r3, java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)));
        return d;
    }

    /** {@code rotateXZY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateXZY_mulAdd(float angleX, float angleZ, float angleY, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
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
        rotateTo_s3b843f3f_c0_mulAdd(d, _r0, ((_t9) * (_t5) + (_t12 * _t8)), _r1, ((_t10) * (_t8) - (_t11 * _t5)), _r2, ((_t10) * (_t5) + (_t11 * _t8)), _r3, ((_t12) * (_t5) - (_t9 * _t8)));
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
    public FloatQuat rotateY(float angle, @Mutated FloatQuat dest) {
        if (Math.useFma()) {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            float _rd0 = this.x;
            float _rd1 = this.y;
            d.x = java.lang.Math.fma(_rd0, _t2, -(this.z * _t1));
            d.y = java.lang.Math.fma(_rd1, _t2, this.w * _t1);
            d.z = java.lang.Math.fma(_rd0, _t1, this.z * _t2);
            d.w = java.lang.Math.fma(this.w, _t2, -(_rd1 * _t1));
            return d;
        } else {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            float _rd0 = this.x;
            float _rd1 = this.y;
            d.x = ((_rd0) * (_t2) - (this.z * _t1));
            d.y = ((_rd1) * (_t2) + (this.w * _t1));
            d.z = ((_rd0) * (_t1) + (this.z * _t2));
            d.w = ((this.w) * (_t2) - (_rd1 * _t1));
            return d;
        }
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
        if (Math.useFma()) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            d.x = java.lang.Math.fma(this.x, _t2, -(this.z * _t1));
            d.y = java.lang.Math.fma(this.y, _t2, this.w * _t1);
            d.z = java.lang.Math.fma(this.x, _t1, this.z * _t2);
            d.w = java.lang.Math.fma(this.w, _t2, -(this.y * _t1));
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            d.x = ((this.x) * (_t2) - (this.z * _t1));
            d.y = ((this.y) * (_t2) + (this.w * _t1));
            d.z = ((this.x) * (_t1) + (this.z * _t2));
            d.w = ((this.w) * (_t2) - (this.y * _t1));
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
    public FloatQuat rotateYXZ(float angleY, float angleX, float angleZ, @Mutated FloatQuat dest) {
        if (!Math.useFma()) return rotateYXZ_mulAdd(angleY, angleX, angleZ, dest);
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
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
        rotateTo_s3892ffea_c0_fma(d, _r0, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r1, java.lang.Math.fma(_t10, _t8, _t11 * _t5), _r2, java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)), _r3, java.lang.Math.fma(_t11, _t8, -(_t10 * _t5)));
        return d;
    }

    /** {@code rotateYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatQuat rotateYXZ_mulAdd(float angleY, float angleX, float angleZ, @Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
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
        rotateTo_s3892ffea_c0_mulAdd(d, _r0, ((_t9) * (_t5) + (_t12 * _t8)), _r1, ((_t10) * (_t8) + (_t11 * _t5)), _r2, ((_t12) * (_t5) - (_t9 * _t8)), _r3, ((_t11) * (_t8) - (_t10 * _t5)));
        return d;
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
        if (!Math.useFma()) return rotateYXZ_mulAdd(angleY, angleX, angleZ, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
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
        rotateTo_s3b843f3f_c0_fma(d, _r0, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r1, java.lang.Math.fma(_t10, _t8, _t11 * _t5), _r2, java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)), _r3, java.lang.Math.fma(_t11, _t8, -(_t10 * _t5)));
        return d;
    }

    /** {@code rotateYXZ} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateYXZ_mulAdd(float angleY, float angleX, float angleZ, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
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
        rotateTo_s3b843f3f_c0_mulAdd(d, _r0, ((_t9) * (_t5) + (_t12 * _t8)), _r1, ((_t10) * (_t8) + (_t11 * _t5)), _r2, ((_t12) * (_t5) - (_t9 * _t8)), _r3, ((_t11) * (_t8) - (_t10 * _t5)));
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
    public FloatQuat rotateYZX(float angleY, float angleZ, float angleX, @Mutated FloatQuat dest) {
        if (!Math.useFma()) return rotateYZX_mulAdd(angleY, angleZ, angleX, dest);
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
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
        rotateTo_s3892ffea_c0_fma(d, _r0, java.lang.Math.fma(_t14, _t8, _t9 * -_t5), _r1, java.lang.Math.fma(_t9, _t8, _t14 * _t5), _r2, java.lang.Math.fma(_t10, _t8, _t11 * -_t5), _r3, java.lang.Math.fma(_t11, _t8, _t10 * _t5));
        return d;
    }

    /** {@code rotateYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatQuat rotateYZX_mulAdd(float angleY, float angleZ, float angleX, @Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
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
        rotateTo_s3892ffea_c0_mulAdd(d, _r0, ((_t14) * (_t8) - (_t9 * _t5)), _r1, ((_t9) * (_t8) + (_t14 * _t5)), _r2, ((_t10) * (_t8) - (_t11 * _t5)), _r3, ((_t11) * (_t8) + (_t10 * _t5)));
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
        if (!Math.useFma()) return rotateYZX_mulAdd(angleY, angleZ, angleX, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
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
        rotateTo_s3b843f3f_c0_fma(d, _r0, java.lang.Math.fma(_t14, _t8, _t9 * -_t5), _r1, java.lang.Math.fma(_t9, _t8, _t14 * _t5), _r2, java.lang.Math.fma(_t10, _t8, _t11 * -_t5), _r3, java.lang.Math.fma(_t11, _t8, _t10 * _t5));
        return d;
    }

    /** {@code rotateYZX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateYZX_mulAdd(float angleY, float angleZ, float angleX, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
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
        rotateTo_s3b843f3f_c0_mulAdd(d, _r0, ((_t14) * (_t8) - (_t9 * _t5)), _r1, ((_t9) * (_t8) + (_t14 * _t5)), _r2, ((_t10) * (_t8) - (_t11 * _t5)), _r3, ((_t11) * (_t8) + (_t10 * _t5)));
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
    public FloatQuat rotateZ(float angle, @Mutated FloatQuat dest) {
        if (Math.useFma()) {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            float _rd0 = this.x;
            float _rd1 = this.z;
            d.x = java.lang.Math.fma(_rd0, _t2, this.y * _t1);
            d.y = java.lang.Math.fma(this.y, _t2, -(_rd0 * _t1));
            d.z = java.lang.Math.fma(_rd1, _t2, this.w * _t1);
            d.w = java.lang.Math.fma(this.w, _t2, -(_rd1 * _t1));
            return d;
        } else {
            FloatQuatImpl d = (FloatQuatImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            float _rd0 = this.x;
            float _rd1 = this.z;
            d.x = ((_rd0) * (_t2) + (this.y * _t1));
            d.y = ((this.y) * (_t2) - (_rd0 * _t1));
            d.z = ((_rd1) * (_t2) + (this.w * _t1));
            d.w = ((this.w) * (_t2) - (_rd1 * _t1));
            return d;
        }
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
        if (Math.useFma()) {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            d.x = java.lang.Math.fma(this.x, _t2, this.y * _t1);
            d.y = java.lang.Math.fma(this.y, _t2, -(this.x * _t1));
            d.z = java.lang.Math.fma(this.z, _t2, this.w * _t1);
            d.w = java.lang.Math.fma(this.w, _t2, -(this.z * _t1));
            return d;
        } else {
            DoubleQuatImpl d = (DoubleQuatImpl) dest;
            float _t0 = 0.5f * angle;
            float _t1 = Math.sin(_t0);
            float _t2 = Math.cosFromSin(_t1, _t0);
            d.x = ((this.x) * (_t2) + (this.y * _t1));
            d.y = ((this.y) * (_t2) - (this.x * _t1));
            d.z = ((this.z) * (_t2) + (this.w * _t1));
            d.w = ((this.w) * (_t2) - (this.z * _t1));
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
    public FloatQuat rotateZXY(float angleZ, float angleX, float angleY, @Mutated FloatQuat dest) {
        if (!Math.useFma()) return rotateZXY_mulAdd(angleZ, angleX, angleY, dest);
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
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
        rotateTo_s3892ffea_c0_fma(d, _r0, java.lang.Math.fma(_t14, _t8, _t9 * -_t5), _r1, java.lang.Math.fma(_t10, _t8, _t11 * -_t5), _r2, java.lang.Math.fma(_t10, _t5, _t11 * _t8), _r3, java.lang.Math.fma(_t9, _t8, _t14 * _t5));
        return d;
    }

    /** {@code rotateZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatQuat rotateZXY_mulAdd(float angleZ, float angleX, float angleY, @Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
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
        rotateTo_s3892ffea_c0_mulAdd(d, _r0, ((_t14) * (_t8) - (_t9 * _t5)), _r1, ((_t10) * (_t8) - (_t11 * _t5)), _r2, ((_t10) * (_t5) + (_t11 * _t8)), _r3, ((_t9) * (_t8) + (_t14 * _t5)));
        return d;
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
        if (!Math.useFma()) return rotateZXY_mulAdd(angleZ, angleX, angleY, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
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
        rotateTo_s3b843f3f_c0_fma(d, _r0, java.lang.Math.fma(_t14, _t8, _t9 * -_t5), _r1, java.lang.Math.fma(_t10, _t8, _t11 * -_t5), _r2, java.lang.Math.fma(_t10, _t5, _t11 * _t8), _r3, java.lang.Math.fma(_t9, _t8, _t14 * _t5));
        return d;
    }

    /** {@code rotateZXY} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateZXY_mulAdd(float angleZ, float angleX, float angleY, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
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
        rotateTo_s3b843f3f_c0_mulAdd(d, _r0, ((_t14) * (_t8) - (_t9 * _t5)), _r1, ((_t10) * (_t8) - (_t11 * _t5)), _r2, ((_t10) * (_t5) + (_t11 * _t8)), _r3, ((_t9) * (_t8) + (_t14 * _t5)));
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
    public FloatQuat rotateZYX(float angleZ, float angleY, float angleX, @Mutated FloatQuat dest) {
        if (!Math.useFma()) return rotateZYX_mulAdd(angleZ, angleY, angleX, dest);
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
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
        rotateTo_s3892ffea_c0_fma(d, _r0, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r1, java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)), _r2, java.lang.Math.fma(_t10, _t8, -(_t11 * _t5)), _r3, java.lang.Math.fma(_t11, _t8, _t10 * _t5));
        return d;
    }

    /** {@code rotateZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private FloatQuat rotateZYX_mulAdd(float angleZ, float angleY, float angleX, @Mutated FloatQuat dest) {
        FloatQuatImpl d = (FloatQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
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
        rotateTo_s3892ffea_c0_mulAdd(d, _r0, ((_t9) * (_t5) + (_t12 * _t8)), _r1, ((_t12) * (_t5) - (_t9 * _t8)), _r2, ((_t10) * (_t8) - (_t11 * _t5)), _r3, ((_t11) * (_t8) + (_t10 * _t5)));
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
        if (!Math.useFma()) return rotateZYX_mulAdd(angleZ, angleY, angleX, dest);
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
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
        rotateTo_s3b843f3f_c0_fma(d, _r0, java.lang.Math.fma(_t9, _t5, _t12 * _t8), _r1, java.lang.Math.fma(_t12, _t5, -(_t9 * _t8)), _r2, java.lang.Math.fma(_t10, _t8, -(_t11 * _t5)), _r3, java.lang.Math.fma(_t11, _t8, _t10 * _t5));
        return d;
    }

    /** {@code rotateZYX} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private DoubleQuat rotateZYX_mulAdd(float angleZ, float angleY, float angleX, @Mutated DoubleQuat dest) {
        DoubleQuatImpl d = (DoubleQuatImpl) dest;
        float _r0 = this.x;
        float _r1 = this.w;
        float _r2 = this.y;
        float _r3 = this.z;
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
        rotateTo_s3b843f3f_c0_mulAdd(d, _r0, ((_t9) * (_t5) + (_t12 * _t8)), _r1, ((_t12) * (_t5) - (_t9 * _t8)), _r2, ((_t10) * (_t8) - (_t11 * _t5)), _r3, ((_t11) * (_t8) + (_t10 * _t5)));
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
    public Float3 transform(Float3R v, @Mutated Float3 dest) {
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        if (!Math.useFma()) return transform_mulAdd(vX, vY, vZ, dest);
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, vY, -(this.y * vX));
        float _t10 = 2.0f * java.lang.Math.fma(this.z, vX, -(this.x * vZ));
        float _t11 = 2.0f * java.lang.Math.fma(this.y, vZ, -(this.z * vY));
        d.x = java.lang.Math.fma(this.y, _t9, java.lang.Math.fma(-this.z, _t10, java.lang.Math.fma(this.w, _t11, vX)));
        d.y = java.lang.Math.fma(this.z, _t11, java.lang.Math.fma(-this.x, _t9, java.lang.Math.fma(this.w, _t10, vY)));
        d.z = java.lang.Math.fma(this.x, _t10, java.lang.Math.fma(-this.y, _t11, java.lang.Math.fma(this.w, _t9, vZ)));
        return d;
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
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        if (!Math.useFma()) return transform_mulAdd(vX, vY, vZ, dest);
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, vY, -(this.y * vX));
        float _t10 = 2.0f * java.lang.Math.fma(this.z, vX, -(this.x * vZ));
        float _t11 = 2.0f * java.lang.Math.fma(this.y, vZ, -(this.z * vY));
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
    public Float3 transform(float vX, float vY, float vZ, @Mutated Float3 dest) {
        if (!Math.useFma()) return transform_mulAdd(vX, vY, vZ, dest);
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, vY, -(this.y * vX));
        float _t10 = 2.0f * java.lang.Math.fma(this.z, vX, -(this.x * vZ));
        float _t11 = 2.0f * java.lang.Math.fma(this.y, vZ, -(this.z * vY));
        d.x = java.lang.Math.fma(this.y, _t9, java.lang.Math.fma(-this.z, _t10, java.lang.Math.fma(this.w, _t11, vX)));
        d.y = java.lang.Math.fma(this.z, _t11, java.lang.Math.fma(-this.x, _t9, java.lang.Math.fma(this.w, _t10, vY)));
        d.z = java.lang.Math.fma(this.x, _t10, java.lang.Math.fma(-this.y, _t11, java.lang.Math.fma(this.w, _t9, vZ)));
        return d;
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 transform_mulAdd(float vX, float vY, float vZ, @Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * ((this.x) * (vY) - (this.y * vX));
        float _t10 = 2.0f * ((this.z) * (vX) - (this.x * vZ));
        float _t11 = 2.0f * ((this.y) * (vZ) - (this.z * vY));
        d.x = ((this.y) * (_t9) + (((((this.w) * (_t11) + (vX))) - (this.z) * (_t10))));
        d.y = ((this.z) * (_t11) + (((((this.w) * (_t10) + (vY))) - (this.x) * (_t9))));
        d.z = ((this.x) * (_t10) + (((((this.w) * (_t9) + (vZ))) - (this.y) * (_t11))));
        return d;
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
        if (!Math.useFma()) return transform_mulAdd(vX, vY, vZ, dest);
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, vY, -(this.y * vX));
        float _t10 = 2.0f * java.lang.Math.fma(this.z, vX, -(this.x * vZ));
        float _t11 = 2.0f * java.lang.Math.fma(this.y, vZ, -(this.z * vY));
        d.x = java.lang.Math.fma(this.y, _t9, java.lang.Math.fma(-this.z, _t10, java.lang.Math.fma(this.w, _t11, vX)));
        d.y = java.lang.Math.fma(this.z, _t11, java.lang.Math.fma(-this.x, _t9, java.lang.Math.fma(this.w, _t10, vY)));
        d.z = java.lang.Math.fma(this.x, _t10, java.lang.Math.fma(-this.y, _t11, java.lang.Math.fma(this.w, _t9, vZ)));
        return d;
    }

    /** {@code transform} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transform_mulAdd(float vX, float vY, float vZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * ((this.x) * (vY) - (this.y * vX));
        float _t10 = 2.0f * ((this.z) * (vX) - (this.x * vZ));
        float _t11 = 2.0f * ((this.y) * (vZ) - (this.z * vY));
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
    public Float3 transformInverse(Float3R v, @Mutated Float3 dest) {
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        if (!Math.useFma()) return transformInverse_mulAdd(vX, vY, vZ, dest);
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, vZ, -(this.z * vX));
        float _t10 = 2.0f * java.lang.Math.fma(this.y, vX, -(this.x * vY));
        float _t11 = 2.0f * java.lang.Math.fma(this.z, vY, -(this.y * vZ));
        d.x = java.lang.Math.fma(this.z, _t9, java.lang.Math.fma(-this.y, _t10, java.lang.Math.fma(this.w, _t11, vX)));
        d.y = java.lang.Math.fma(this.x, _t10, java.lang.Math.fma(-this.z, _t11, java.lang.Math.fma(this.w, _t9, vY)));
        d.z = java.lang.Math.fma(this.y, _t11, java.lang.Math.fma(-this.x, _t9, java.lang.Math.fma(this.w, _t10, vZ)));
        return d;
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
        float vX = v.x();
        float vY = v.y();
        float vZ = v.z();
        if (!Math.useFma()) return transformInverse_mulAdd(vX, vY, vZ, dest);
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, vZ, -(this.z * vX));
        float _t10 = 2.0f * java.lang.Math.fma(this.y, vX, -(this.x * vY));
        float _t11 = 2.0f * java.lang.Math.fma(this.z, vY, -(this.y * vZ));
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
    public Float3 transformInverse(float vX, float vY, float vZ, @Mutated Float3 dest) {
        if (!Math.useFma()) return transformInverse_mulAdd(vX, vY, vZ, dest);
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, vZ, -(this.z * vX));
        float _t10 = 2.0f * java.lang.Math.fma(this.y, vX, -(this.x * vY));
        float _t11 = 2.0f * java.lang.Math.fma(this.z, vY, -(this.y * vZ));
        d.x = java.lang.Math.fma(this.z, _t9, java.lang.Math.fma(-this.y, _t10, java.lang.Math.fma(this.w, _t11, vX)));
        d.y = java.lang.Math.fma(this.x, _t10, java.lang.Math.fma(-this.z, _t11, java.lang.Math.fma(this.w, _t9, vY)));
        d.z = java.lang.Math.fma(this.y, _t11, java.lang.Math.fma(-this.x, _t9, java.lang.Math.fma(this.w, _t10, vZ)));
        return d;
    }

    /** {@code transformInverse} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Float3 transformInverse_mulAdd(float vX, float vY, float vZ, @Mutated Float3 dest) {
        Float3Impl d = (Float3Impl) dest;
        float _t9 = 2.0f * ((this.x) * (vZ) - (this.z * vX));
        float _t10 = 2.0f * ((this.y) * (vX) - (this.x * vY));
        float _t11 = 2.0f * ((this.z) * (vY) - (this.y * vZ));
        d.x = ((this.z) * (_t9) + (((((this.w) * (_t11) + (vX))) - (this.y) * (_t10))));
        d.y = ((this.x) * (_t10) + (((((this.w) * (_t9) + (vY))) - (this.z) * (_t11))));
        d.z = ((this.y) * (_t11) + (((((this.w) * (_t10) + (vZ))) - (this.x) * (_t9))));
        return d;
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
        if (!Math.useFma()) return transformInverse_mulAdd(vX, vY, vZ, dest);
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * java.lang.Math.fma(this.x, vZ, -(this.z * vX));
        float _t10 = 2.0f * java.lang.Math.fma(this.y, vX, -(this.x * vY));
        float _t11 = 2.0f * java.lang.Math.fma(this.z, vY, -(this.y * vZ));
        d.x = java.lang.Math.fma(this.z, _t9, java.lang.Math.fma(-this.y, _t10, java.lang.Math.fma(this.w, _t11, vX)));
        d.y = java.lang.Math.fma(this.x, _t10, java.lang.Math.fma(-this.z, _t11, java.lang.Math.fma(this.w, _t9, vY)));
        d.z = java.lang.Math.fma(this.y, _t11, java.lang.Math.fma(-this.x, _t9, java.lang.Math.fma(this.w, _t10, vZ)));
        return d;
    }

    /** {@code transformInverse} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private Double3 transformInverse_mulAdd(float vX, float vY, float vZ, @Mutated Double3 dest) {
        Double3Impl d = (Double3Impl) dest;
        float _t9 = 2.0f * ((this.x) * (vZ) - (this.z * vX));
        float _t10 = 2.0f * ((this.y) * (vX) - (this.x * vY));
        float _t11 = 2.0f * ((this.z) * (vY) - (this.y * vZ));
        d.x = ((this.z) * (_t9) + (((((this.w) * (_t11) + (vX))) - (this.y) * (_t10))));
        d.y = ((this.x) * (_t10) + (((((this.w) * (_t9) + (vY))) - (this.z) * (_t11))));
        d.z = ((this.y) * (_t11) + (((((this.w) * (_t10) + (vZ))) - (this.x) * (_t9))));
        return d;
    }

    public float x() { return this.x; }
    public float y() { return this.y; }
    public float z() { return this.z; }
    public float w() { return this.w; }

    @Override public String toString() {
        return "FloatQuat(" + x() + ", " + y() + ", " + z() + ", " + w() + ")";
    }

    @Override public boolean equals(@org.jspecify.annotations.Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FloatQuatImpl)) return false;
        FloatQuatImpl o = (FloatQuatImpl) obj;
        return Float.floatToIntBits(x) == Float.floatToIntBits(o.x)
            && Float.floatToIntBits(y) == Float.floatToIntBits(o.y)
            && Float.floatToIntBits(z) == Float.floatToIntBits(o.z)
            && Float.floatToIntBits(w) == Float.floatToIntBits(o.w);
    }

    @Override public int hashCode() {
        int h = 1;
        h = 31 * h + Float.floatToIntBits(x);
        h = 31 * h + Float.floatToIntBits(y);
        h = 31 * h + Float.floatToIntBits(z);
        h = 31 * h + Float.floatToIntBits(w);
        return h;
    }

    @Override public boolean isFinite() {
        return Float.isFinite(x)
            && Float.isFinite(y)
            && Float.isFinite(z)
            && Float.isFinite(w);
    }

    @Override public boolean isNaN() {
        return Float.isNaN(x)
            || Float.isNaN(y)
            || Float.isNaN(z)
            || Float.isNaN(w);
    }

    @Override public boolean equalsEpsilon(FloatQuatR other, float epsilon) {
        return java.lang.Math.abs(x - other.x()) <= epsilon
            && java.lang.Math.abs(y - other.y()) <= epsilon
            && java.lang.Math.abs(z - other.z()) <= epsilon
            && java.lang.Math.abs(w - other.w()) <= epsilon;
    }

    public float[] store(@Mutated float[] dest, int offset) {
        dest[offset] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        dest[offset + 3] = this.w;
        return dest;
    }

    @Mutated public FloatQuat load(float[] src, int offset) {
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

    @Mutated public FloatQuat load(FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }

    @Mutated public FloatQuat loadAbsolute(int index, FloatBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }

    @Mutated public FloatQuat loadRelative(FloatBuffer buf) {
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
        if (buf.remaining() < 16) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return buf;
    }

    @Mutated public FloatQuat load(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }

    @Mutated public FloatQuat loadAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }

    @Mutated public FloatQuat loadRelative(ByteBuffer buf) {
        if (buf.remaining() < 16) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 16);
        return this;
    }

    public FloatQuat storeUnsafe(long address) {
        return StoreLoad.RAW_OPS.storeUnsafe(this, address);
    }

    @Mutated public FloatQuat loadUnsafe(long address) {
        return StoreLoad.RAW_OPS.loadUnsafe(this, address);
    }

    public double[] store(@Mutated double[] dest, int offset) {
        dest[offset] = this.x;
        dest[offset + 1] = this.y;
        dest[offset + 2] = this.z;
        dest[offset + 3] = this.w;
        return dest;
    }

    @Mutated public FloatQuat load(double[] src, int offset) {
        this.x = (float) src[offset];
        this.y = (float) src[offset + 1];
        this.z = (float) src[offset + 2];
        this.w = (float) src[offset + 3];
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

    @Mutated public FloatQuat load(DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, buf.position(), buf);
    }

    @Mutated public FloatQuat loadAbsolute(int index, DoubleBuffer buf) {
        return StoreLoad.BB_OPS.loadAbsolute(this, index, buf);
    }

    @Mutated public FloatQuat loadRelative(DoubleBuffer buf) {
        if (buf.remaining() < 4) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadAbsolute(this, pos, buf);
        buf.position(pos + 4);
        return this;
    }

    public ByteBuffer storeDouble(@Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, buf.position(), buf);
    }

    public ByteBuffer storeDoubleAbsolute(int index, @Mutated ByteBuffer buf) {
        return StoreLoad.BB_OPS.storeDoubleAbsolute(this, index, buf);
    }

    public ByteBuffer storeDoubleRelative(@Mutated ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferOverflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.storeDoubleAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return buf;
    }

    @Mutated public FloatQuat loadDouble(ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, buf.position(), buf);
    }

    @Mutated public FloatQuat loadDoubleAbsolute(int index, ByteBuffer buf) {
        return StoreLoad.BB_OPS.loadDoubleAbsolute(this, index, buf);
    }

    @Mutated public FloatQuat loadDoubleRelative(ByteBuffer buf) {
        if (buf.remaining() < 32) throw new java.nio.BufferUnderflowException();
        int pos = buf.position();
        StoreLoad.BB_OPS.loadDoubleAbsolute(this, pos, buf);
        buf.position(pos + 32);
        return this;
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
        return s > d ? 2.0f * Math.asin(0.5f * (float) java.lang.Math.sqrt(d))
                : (float) Math.PI - 2.0f * Math.asin(0.5f * (float) java.lang.Math.sqrt(s));
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
