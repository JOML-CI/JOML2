// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.kernels;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.simd.*;

/**
 * Scalar/Unsafe kernel leaves of {@link FloatQuatOps} whose leading storage
 * parameter is a {@code float[]} array. Split into a sibling compilation unit purely
 * to keep generated sources IDE-sized; package-private, called only from
 * {@code FloatQuatOps} and its sibling kernel units. Not public API.
 */
public final class FloatQuatOpsKernelsArray {
    private FloatQuatOpsKernelsArray() {}

    public static float[] add_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _otherw = other[otherOffset + 3];
        dest[destOffset] = other[otherOffset] + src[srcOffset];
        dest[destOffset + 1] = _othery + _selfy;
        dest[destOffset + 2] = _otherz + _selfz;
        dest[destOffset + 3] = _otherw + _selfw;
        return dest;
    }

    public static float[] mul_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float scalar) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = scalar * src[srcOffset];
        dest[destOffset + 1] = scalar * _selfy;
        dest[destOffset + 2] = scalar * _selfz;
        dest[destOffset + 3] = scalar * _selfw;
        return dest;
    }

    public static float[] negate_scalar(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = -src[srcOffset];
        dest[destOffset + 1] = -_selfy;
        dest[destOffset + 2] = -_selfz;
        dest[destOffset + 3] = -_selfw;
        return dest;
    }

    public static float[] sub_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _otherw = other[otherOffset + 3];
        dest[destOffset] = src[srcOffset] - other[otherOffset];
        dest[destOffset + 1] = _selfy - _othery;
        dest[destOffset + 2] = _selfz - _otherz;
        dest[destOffset + 3] = _selfw - _otherw;
        return dest;
    }

    public static float[] set_scalar(float[] dest, int destOffset, float[] v, int vOffset) {
        float _vy = v[vOffset + 1];
        float _vz = v[vOffset + 2];
        float _vw = v[vOffset + 3];
        dest[destOffset] = v[vOffset];
        dest[destOffset + 1] = _vy;
        dest[destOffset + 2] = _vz;
        dest[destOffset + 3] = _vw;
        return dest;
    }

    public static float[] toDualQuat_scalar(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = src[srcOffset];
        dest[destOffset + 1] = _selfy;
        dest[destOffset + 2] = _selfz;
        dest[destOffset + 3] = _selfw;
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = 0.0f;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        return dest;
    }

    public static float[] makeIdentity_scalar(float[] dest, int destOffset) {
        dest[destOffset] = 0.0f;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 1.0f;
        return dest;
    }

    public static float[] makeZero_scalar(float[] dest, int destOffset) {
        dest[destOffset] = 0.0f;
        dest[destOffset + 1] = 0.0f;
        dest[destOffset + 2] = 0.0f;
        dest[destOffset + 3] = 0.0f;
        return dest;
    }

    public static float[] lerp_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        dest[destOffset] = Math.fma(t, otherX - _selfx, _selfx);
        dest[destOffset + 1] = Math.fma(t, otherY - _selfy, _selfy);
        dest[destOffset + 2] = Math.fma(t, otherZ - _selfz, _selfz);
        dest[destOffset + 3] = Math.fma(t, otherW - _selfw, _selfw);
        return dest;
    }

    public static float[] lerp_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _otherw = other[otherOffset + 3];
        dest[destOffset] = Math.fma(t, other[otherOffset] - _selfx, _selfx);
        dest[destOffset + 1] = Math.fma(t, _othery - _selfy, _selfy);
        dest[destOffset + 2] = Math.fma(t, _otherz - _selfz, _selfz);
        dest[destOffset + 3] = Math.fma(t, _otherw - _selfw, _selfw);
        return dest;
    }

    public static float[] nlerp_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t4 = Math.fma(alpha, target[targetOffset + 3] - _selfw, _selfw);
        float _t5 = Math.fma(alpha, target[targetOffset + 2] - _selfz, _selfz);
        float _t6 = Math.fma(alpha, target[targetOffset] - _selfx, _selfx);
        float _t7 = Math.fma(alpha, target[targetOffset + 1] - _selfy, _selfy);
        float _t11 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, Math.fma(_t6, _t6, _t7 * _t7)));
        float _t12 = (1.0f / (float) java.lang.Math.sqrt(_t11));
        if (_t11 != 0.0f) {
            dest[destOffset] = _t6 * _t12;
            dest[destOffset + 1] = _t7 * _t12;
            dest[destOffset + 2] = _t5 * _t12;
            dest[destOffset + 3] = _t4 * _t12;
        } else {
            dest[destOffset] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
            dest[destOffset + 3] = 0.0f;
        }
        return dest;
    }

    public static float[] nlerpShortest_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _targetx = target[targetOffset];
        float _targety = target[targetOffset + 1];
        float _targetz = target[targetOffset + 2];
        float _targetw = target[targetOffset + 3];
        float _t17, _t18, _t19, _t20;
        if (-Math.fma(_selfw, _targetw, Math.fma(_selfz, _targetz, Math.fma(_selfx, _targetx, _selfy * _targety))) > 0.0f) {
            _t17 = Math.fma(alpha, -_targetw - _selfw, _selfw);
            _t18 = Math.fma(alpha, -_targetz - _selfz, _selfz);
            _t19 = Math.fma(alpha, -_targetx - _selfx, _selfx);
            _t20 = Math.fma(alpha, -_targety - _selfy, _selfy);
        } else {
            _t17 = Math.fma(alpha, _targetw - _selfw, _selfw);
            _t18 = Math.fma(alpha, _targetz - _selfz, _selfz);
            _t19 = Math.fma(alpha, _targetx - _selfx, _selfx);
            _t20 = Math.fma(alpha, _targety - _selfy, _selfy);
        }
        float _t24 = Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)));
        return nlerpShortest_scalar_s9c94b261_1(dest, destOffset, _t17, _t18, _t19, _t20, _t24, (1.0f / (float) java.lang.Math.sqrt(_t24)));
    }

    /** Piece 2 of {@code nlerpShortest_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] nlerpShortest_scalar_s9c94b261_1(float[] dest, int destOffset, float _t17, float _t18, float _t19, float _t20, float _t24, float _t25) {
        if (_t24 != 0.0f) {
            dest[destOffset] = _t19 * _t25;
            dest[destOffset + 1] = _t20 * _t25;
            dest[destOffset + 2] = _t18 * _t25;
            dest[destOffset + 3] = _t17 * _t25;
        } else {
            dest[destOffset] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
            dest[destOffset + 3] = 0.0f;
        }
        return dest;
    }

    public static float[] slerp_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t0 = 1.0f - alpha;
        float _t1 = _selfw + targetW;
        float _t2 = _selfz + targetZ;
        float _t3 = _selfx + targetX;
        float _t4 = _selfy + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = java.lang.Math.min(4.0f, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
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
        dest[destOffset] = Math.fma(_selfx, _t27, targetX * _t26);
        dest[destOffset + 1] = Math.fma(_selfy, _t27, targetY * _t26);
        dest[destOffset + 2] = Math.fma(_selfz, _t27, targetZ * _t26);
        dest[destOffset + 3] = Math.fma(_selfw, _t27, targetW * _t26);
        return dest;
    }

    public static float[] slerp_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _targetx = target[targetOffset];
        float _targety = target[targetOffset + 1];
        float _targetz = target[targetOffset + 2];
        float _targetw = target[targetOffset + 3];
        float _t0 = 1.0f - alpha;
        float _t1 = _selfw + _targetw;
        float _t2 = _selfz + _targetz;
        float _t3 = _selfx + _targetx;
        float _t4 = _selfy + _targety;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = java.lang.Math.min(4.0f, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
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
        dest[destOffset] = Math.fma(_selfx, _t27, _targetx * _t26);
        dest[destOffset + 1] = Math.fma(_selfy, _t27, _targety * _t26);
        dest[destOffset + 2] = Math.fma(_selfz, _t27, _targetz * _t26);
        dest[destOffset + 3] = Math.fma(_selfw, _t27, _targetw * _t26);
        return dest;
    }

    public static float[] slerpShortest_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _targetx = target[targetOffset];
        float _targety = target[targetOffset + 1];
        float _targetz = target[targetOffset + 2];
        float _targetw = target[targetOffset + 3];
        float _t0 = 1.0f - alpha;
        float _t12 = Math.fma(_selfw, _targetw, Math.fma(_selfz, _targetz, Math.fma(_selfx, _targetx, _selfy * _targety)));
        float _t16 = Math.acos(java.lang.Math.min(1.0f, java.lang.Math.abs(_t12)));
        float _t17 = Math.sin(_t16);
        float _t21, _t22, _t23, _t24;
        if (-_t12 > 0.0f) {
            _t21 = -_targetw;
            _t22 = -_targetz;
            _t23 = -_targetx;
            _t24 = -_targety;
        } else {
            _t21 = _targetw;
            _t22 = _targetz;
            _t23 = _targetx;
            _t24 = _targety;
        }
        return slerpShortest_scalar_sc9fdbff0_1(dest, destOffset, alpha, _selfx, _selfy, _selfz, _selfw, _t0, _t17, 1.0f / _t17, Math.sin(alpha * _t16), _t21, _t22, _t23, _t24, Math.sin(_t0 * _t16));
    }

    /** Piece 2 of {@code slerpShortest_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] slerpShortest_scalar_sc9fdbff0_1(float[] dest, int destOffset, float alpha, float _selfx, float _selfy, float _selfz, float _selfw, float _t0, float _t17, float _t17_inv, float _t19, float _t21, float _t22, float _t23, float _t24, float _t25) {
        float _t42, _t43, _t44, _t45;
        if (_t17 > 0.0f) {
            _t42 = Math.fma(_selfw, _t25, _t19 * _t21) * _t17_inv;
            _t43 = Math.fma(_selfz, _t25, _t19 * _t22) * _t17_inv;
            _t44 = Math.fma(_selfx, _t25, _t19 * _t23) * _t17_inv;
            _t45 = Math.fma(_selfy, _t25, _t19 * _t24) * _t17_inv;
        } else {
            _t42 = Math.fma(alpha, _t21, _selfw * _t0);
            _t43 = Math.fma(alpha, _t22, _selfz * _t0);
            _t44 = Math.fma(alpha, _t23, _selfx * _t0);
            _t45 = Math.fma(alpha, _t24, _selfy * _t0);
        }
        float _t49 = Math.fma(_t42, _t42, Math.fma(_t43, _t43, Math.fma(_t44, _t44, _t45 * _t45)));
        float _t50 = (1.0f / (float) java.lang.Math.sqrt(_t49));
        if (_t49 != 0.0f) {
            dest[destOffset] = _t50 * _t44;
            dest[destOffset + 1] = _t50 * _t45;
            dest[destOffset + 2] = _t50 * _t43;
            dest[destOffset + 3] = _t50 * _t42;
        } else {
            dest[destOffset] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
            dest[destOffset + 3] = 0.0f;
        }
        return dest;
    }

    public static float[] addScaled_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float weight) {
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _othery = other[otherOffset + 1];
        float _otherz = other[otherOffset + 2];
        float _otherw = other[otherOffset + 3];
        dest[destOffset] = Math.fma(weight, other[otherOffset], src[srcOffset]);
        dest[destOffset + 1] = Math.fma(weight, _othery, _selfy);
        dest[destOffset + 2] = Math.fma(weight, _otherz, _selfz);
        dest[destOffset + 3] = Math.fma(weight, _otherw, _selfw);
        return dest;
    }

    public static float[] calculateW_scalar(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        dest[destOffset] = _selfx;
        dest[destOffset + 1] = _selfy;
        dest[destOffset + 2] = _selfz;
        dest[destOffset + 3] = (float) java.lang.Math.sqrt(java.lang.Math.max(0.0f, Math.fma(-_selfx, _selfx, Math.fma(-_selfy, _selfy, Math.fma(-_selfz, _selfz, 1.0f)))));
        return dest;
    }

    public static float[] exp_scalar(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _t2 = java.lang.Math.min(Math.exp(src[srcOffset + 3]), 3.4028235E38f);
        float _t4 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t5 = (float) java.lang.Math.sqrt(_t4);
        float _t7 = Math.sin(_t5);
        float _t10 = _t2 * (_t7 / _t5);
        if (_t4 > 0.0f) {
            dest[destOffset] = _selfx * _t10;
            dest[destOffset + 1] = _selfy * _t10;
            dest[destOffset + 2] = _selfz * _t10;
        } else {
            dest[destOffset] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
        }
        dest[destOffset + 3] = Math.cosFromSin(_t7, _t5) * _t2;
        return dest;
    }

    public static float[] log_scalar(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t6 = Math.atan2((float) java.lang.Math.sqrt(_t2), _selfw) * (1.0f / (float) java.lang.Math.sqrt(_t2));
        if (_t2 > 0.0f) {
            dest[destOffset] = _selfx * _t6;
            dest[destOffset + 1] = _selfy * _t6;
            dest[destOffset + 2] = _selfz * _t6;
        } else {
            dest[destOffset] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
        }
        dest[destOffset + 3] = Math.log((float) java.lang.Math.sqrt(Math.fma(_selfw, _selfw, _t2)));
        return dest;
    }

    public static float[] normalize_scalar(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _t3 = Math.fma(_selfw, _selfw, Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy)));
        float _t4 = (1.0f / (float) java.lang.Math.sqrt(_t3));
        if (_t3 != 0.0f) {
            dest[destOffset] = _selfx * _t4;
            dest[destOffset + 1] = _selfy * _t4;
            dest[destOffset + 2] = _selfz * _t4;
            dest[destOffset + 3] = _selfw * _t4;
        } else {
            dest[destOffset] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
            dest[destOffset + 3] = 0.0f;
        }
        return dest;
    }

    public static float[] rotateTowards_scalar(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float step) {
        float _selfx = src[srcOffset];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        float _selfw = src[srcOffset + 3];
        float _targetx = target[targetOffset];
        float _targety = target[targetOffset + 1];
        float _targetz = target[targetOffset + 2];
        float _targetw = target[targetOffset + 3];
        float _t7 = Math.fma(_selfw, _targetw, Math.fma(_selfz, _targetz, Math.fma(_selfx, _targetx, _selfy * _targety)));
        float _t11 = Math.acos(java.lang.Math.min(1.0f, java.lang.Math.abs(_t7)));
        float _t13, _t14, _t15, _t16;
        if (-_t7 > 0.0f) {
            _t13 = -_targetw;
            _t14 = -_targetz;
            _t15 = -_targetx;
            _t16 = -_targety;
        } else {
            _t13 = _targetw;
            _t14 = _targetz;
            _t15 = _targetx;
            _t16 = _targety;
        }
        return rotateTowards_scalar_sbd203585_1(dest, destOffset, step, _selfx, _selfy, _selfz, _selfw, _t11, Math.sin(_t11), _t13, _t14, _t15, _t16, _selfw - _t13, _selfz - _t14, _selfx - _t15, _selfy - _t16, _selfw + _t13, _selfz + _t14, _selfx + _t15, _selfy + _t16);
    }

    /** Piece 2 of {@code rotateTowards_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] rotateTowards_scalar_sbd203585_1(float[] dest, int destOffset, float step, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t13, float _t14, float _t15, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _t22, float _t23, float _t24) {
        return rotateTowards_scalar_s3746ff11_1(dest, destOffset, step, _selfx, _selfy, _selfz, _selfw, _t11, _t12, 1.0f / _t12, _t13, _t14, _t15, _t16, 4.0f * Math.atan2((float) java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))), (float) java.lang.Math.sqrt(Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24))))));
    }

    /** Piece 2 of {@code rotateTowards_scalar}, split to fit the inline budget; reached only through it. */
    private static float[] rotateTowards_scalar_s3746ff11_1(float[] dest, int destOffset, float step, float _selfx, float _selfy, float _selfz, float _selfw, float _t11, float _t12, float _t12_inv, float _t13, float _t14, float _t15, float _t16, float _t36) {
        float _t39 = _t36 > 0.0f ? java.lang.Math.min(1.0f, step / _t36) : 0.0f;
        float _t40 = 1.0f - _t39;
        float _t42 = Math.sin(_t11 * _t39);
        float _t44 = Math.sin(_t40 * _t11);
        float _t65, _t66, _t67, _t68;
        if (_t12 > 0.0f) {
            _t65 = Math.fma(_selfw, _t44, _t42 * _t13) * _t12_inv;
            _t66 = Math.fma(_selfz, _t44, _t42 * _t14) * _t12_inv;
            _t67 = Math.fma(_selfx, _t44, _t42 * _t15) * _t12_inv;
            _t68 = Math.fma(_selfy, _t44, _t42 * _t16) * _t12_inv;
        } else {
            _t65 = Math.fma(_selfw, _t40, _t13 * _t39);
            _t66 = Math.fma(_selfz, _t40, _t14 * _t39);
            _t67 = Math.fma(_selfx, _t40, _t15 * _t39);
            _t68 = Math.fma(_selfy, _t40, _t16 * _t39);
        }
        float _t72 = Math.fma(_t65, _t65, Math.fma(_t66, _t66, Math.fma(_t67, _t67, _t68 * _t68)));
        float _t73 = (1.0f / (float) java.lang.Math.sqrt(_t72));
        if (_t72 != 0.0f) {
            dest[destOffset] = _t73 * _t67;
            dest[destOffset + 1] = _t73 * _t68;
            dest[destOffset + 2] = _t73 * _t66;
            dest[destOffset + 3] = _t73 * _t65;
        } else {
            dest[destOffset] = 0.0f;
            dest[destOffset + 1] = 0.0f;
            dest[destOffset + 2] = 0.0f;
            dest[destOffset + 3] = 0.0f;
        }
        return dest;
    }

    public static float[] makeRotationAxis_scalar(float[] dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        dest[destOffset] = axisX * _t1;
        dest[destOffset + 1] = axisY * _t1;
        dest[destOffset + 2] = axisZ * _t1;
        dest[destOffset + 3] = Math.cosFromSin(_t1, _t0);
        return dest;
    }

    public static float[] makeRotationAxis_scalar(float[] dest, int destOffset, float[] axis, int axisOffset, float angle) {
        float _axisy = axis[axisOffset + 1];
        float _axisz = axis[axisOffset + 2];
        float _t0 = 0.5f * angle;
        float _t1 = Math.sin(_t0);
        dest[destOffset] = axis[axisOffset] * _t1;
        dest[destOffset + 1] = _axisy * _t1;
        dest[destOffset + 2] = _axisz * _t1;
        dest[destOffset + 3] = Math.cosFromSin(_t1, _t0);
        return dest;
    }

    public static float[] makeRotationYXZ_scalar(float[] dest, int destOffset, float angleY, float angleX, float angleZ) {
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
        dest[destOffset] = Math.fma(_t10, _t7, _t11 * _t5);
        dest[destOffset + 1] = Math.fma(_t11, _t7, -(_t10 * _t5));
        dest[destOffset + 2] = Math.fma(_t12, _t5, -(_t9 * _t7));
        dest[destOffset + 3] = Math.fma(_t9, _t5, _t12 * _t7);
        return dest;
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
}
