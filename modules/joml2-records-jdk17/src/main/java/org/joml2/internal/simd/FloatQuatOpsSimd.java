// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.simd;

import jdk.incubator.vector.*;
import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Vector-API isolation cell for {@link FloatQuatOps}: every
 * {@code jdk.incubator.vector} reference of the Ops family lives in this class,
 * which is loaded and initialized only behind {@code SimdSupport.VECTOR_API}
 * guards - {@code FloatQuatOps} and its kernel siblings link
 * and run without the incubator module. Not public API.
 */
public final class FloatQuatOpsSimd {
    private FloatQuatOpsSimd() {}
    private static final VectorSpecies<Float> SIMD_SPECIES = FloatVector.SPECIES_128;
    private static final int PREFERRED_LANES = FloatVector.SPECIES_PREFERRED.length();
    private static final FloatVector UNIT_W = FloatVector.fromArray(SIMD_SPECIES, new float[]{0.0f, 0.0f, 0.0f, 1.0f}, 0);

    public static float[] add(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, other, otherOffset).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] mul(float[] dest, int destOffset, float[] src, int srcOffset, float scalar) {
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, scalar).mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] negate(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] sub(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).sub(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] set(float[] dest, int destOffset, float[] v, int vOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, v, vOffset);
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] toDualQuat(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _vcp0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        _vcp0.intoArray(dest, destOffset);
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = 0.0f;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        return dest;
    }

    public static float[] makeZero(float[] dest, int destOffset) {
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] lerp(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    public static float[] lerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, t).fma(FloatVector.zero(SIMD_SPECIES).withLane(0, otherX).withLane(1, otherY).withLane(2, otherZ).withLane(3, otherW).sub(_sv0), _sv0);
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] lerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, t).mul(FloatVector.zero(SIMD_SPECIES).withLane(0, otherX).withLane(1, otherY).withLane(2, otherZ).withLane(3, otherW).sub(_sv0)).add(_sv0);
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] lerp(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static float[] lerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, t).fma(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset).sub(_sv0), _sv0);
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] lerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, t).mul(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset).sub(_sv0)).add(_sv0);
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] nlerp(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        if (SimdSupport.USE_FMA) return nlerp_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return nlerp_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static float[] nlerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, alpha).fma(FloatVector.fromArray(SIMD_SPECIES, target, targetOffset).sub(_sv0), _sv0);
        float _t11 = _sv1.mul(_sv1).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t11  !=  0.0f ? _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t11)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] nlerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, alpha).mul(FloatVector.fromArray(SIMD_SPECIES, target, targetOffset).sub(_sv0)).add(_sv0);
        float _t11 = _sv1.mul(_sv1).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t11  !=  0.0f ? _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t11)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] nlerpShortest(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        if (SimdSupport.USE_FMA) return nlerpShortest_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return nlerpShortest_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static float[] nlerpShortest_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, target, targetOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, alpha).fma((-Math.fma(src[srcOffset + 3], target[targetOffset + 3], Math.fma(src[srcOffset + 2], target[targetOffset + 2], Math.fma(src[srcOffset + 0], target[targetOffset + 0], src[srcOffset + 1] * target[targetOffset + 1])))  >  0.0f ? _sv0.neg() : _sv0).sub(_sv1), _sv1);
        float _t24 = _sv2.mul(_sv2).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t24  !=  0.0f ? _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t24)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] nlerpShortest_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, target, targetOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, alpha).mul((-Math.fma(src[srcOffset + 3], target[targetOffset + 3], Math.fma(src[srcOffset + 2], target[targetOffset + 2], Math.fma(src[srcOffset + 0], target[targetOffset + 0], src[srcOffset + 1] * target[targetOffset + 1])))  >  0.0f ? _sv0.neg() : _sv0).sub(_sv1)).add(_sv1);
        float _t24 = _sv2.mul(_sv2).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t24  !=  0.0f ? _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t24)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] slerp(float[] dest, int destOffset, float[] src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (SimdSupport.USE_FMA) return slerp_fma(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return slerp_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    public static float[] slerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _t0 = 1.0f - alpha;
        float _t1 = src[srcOffset + 3] + targetW;
        float _t2 = src[srcOffset + 2] + targetZ;
        float _t3 = src[srcOffset + 0] + targetX;
        float _t4 = src[srcOffset + 1] + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        float _t12 = quatArcAngle(_t11);
        float _t13 = 4.0f - _t11;
        float _t19 = (float) Math.sqrt(_t13 * _t11);
        float _t21 = 2.0f / _t19;
        float _w0, _w1;
        if (_t19 > 2.0E-6f) {
            _w0 = _t21 * (float) Math.sin(_t0 * _t12);
            _w1 = _t21 * (float) Math.sin(alpha * _t12);
        } else {
            if (_t11 > _t13) {
                _w0 = _t0;
                _w1 = alpha;
            } else {
                _w0 = _t5;
                _w1 = 1.0f - _t5;
            }
        }
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(FloatVector.broadcast(SIMD_SPECIES, _w0), FloatVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(FloatVector.broadcast(SIMD_SPECIES, _w1)));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] slerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _t0 = 1.0f - alpha;
        float _t1 = src[srcOffset + 3] + targetW;
        float _t2 = src[srcOffset + 2] + targetZ;
        float _t3 = src[srcOffset + 0] + targetX;
        float _t4 = src[srcOffset + 1] + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        float _t12 = quatArcAngle(_t11);
        float _t13 = 4.0f - _t11;
        float _t19 = (float) Math.sqrt(_t13 * _t11);
        float _t21 = 2.0f / _t19;
        float _w0, _w1;
        if (_t19 > 2.0E-6f) {
            _w0 = _t21 * (float) Math.sin(_t0 * _t12);
            _w1 = _t21 * (float) Math.sin(alpha * _t12);
        } else {
            if (_t11 > _t13) {
                _w0 = _t0;
                _w1 = alpha;
            } else {
                _w0 = _t5;
                _w1 = 1.0f - _t5;
            }
        }
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(FloatVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(FloatVector.broadcast(SIMD_SPECIES, _w1)));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] slerp(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        if (SimdSupport.USE_FMA) return slerp_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerp_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static float[] slerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        float _t0 = 1.0f - alpha;
        float _t1 = src[srcOffset + 3] + target[targetOffset + 3];
        float _t2 = src[srcOffset + 2] + target[targetOffset + 2];
        float _t3 = src[srcOffset + 0] + target[targetOffset + 0];
        float _t4 = src[srcOffset + 1] + target[targetOffset + 1];
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        float _t12 = quatArcAngle(_t11);
        float _t13 = 4.0f - _t11;
        float _t19 = (float) Math.sqrt(_t13 * _t11);
        float _t21 = 2.0f / _t19;
        float _w0, _w1;
        if (_t19 > 2.0E-6f) {
            _w0 = _t21 * (float) Math.sin(_t0 * _t12);
            _w1 = _t21 * (float) Math.sin(alpha * _t12);
        } else {
            if (_t11 > _t13) {
                _w0 = _t0;
                _w1 = alpha;
            } else {
                _w0 = _t5;
                _w1 = 1.0f - _t5;
            }
        }
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(FloatVector.broadcast(SIMD_SPECIES, _w0), FloatVector.fromArray(SIMD_SPECIES, target, targetOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _w1)));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] slerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        float _t0 = 1.0f - alpha;
        float _t1 = src[srcOffset + 3] + target[targetOffset + 3];
        float _t2 = src[srcOffset + 2] + target[targetOffset + 2];
        float _t3 = src[srcOffset + 0] + target[targetOffset + 0];
        float _t4 = src[srcOffset + 1] + target[targetOffset + 1];
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        float _t12 = quatArcAngle(_t11);
        float _t13 = 4.0f - _t11;
        float _t19 = (float) Math.sqrt(_t13 * _t11);
        float _t21 = 2.0f / _t19;
        float _w0, _w1;
        if (_t19 > 2.0E-6f) {
            _w0 = _t21 * (float) Math.sin(_t0 * _t12);
            _w1 = _t21 * (float) Math.sin(alpha * _t12);
        } else {
            if (_t11 > _t13) {
                _w0 = _t0;
                _w1 = alpha;
            } else {
                _w0 = _t5;
                _w1 = 1.0f - _t5;
            }
        }
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(FloatVector.fromArray(SIMD_SPECIES, target, targetOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _w1)));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] slerpShortest(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        if (SimdSupport.USE_FMA) return slerpShortest_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerpShortest_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static float[] slerpShortest_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        float _t0 = 1.0f - alpha;
        float _t12 = Math.fma(src[srcOffset + 3], target[targetOffset + 3], Math.fma(src[srcOffset + 2], target[targetOffset + 2], Math.fma(src[srcOffset + 0], target[targetOffset + 0], src[srcOffset + 1] * target[targetOffset + 1])));
        float _t16 = (float) Math.acos(Math.min(1.0f, Math.abs(_t12)));
        float _t17 = (float) Math.sin(_t16);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, target, targetOffset);
        var _sv2 = (-_t12  >  0.0f ? _sv1.neg() : _sv1);
        var _sv3 = (_t17  >  0.0f ? _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(_t0 * _t16)), FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(alpha * _t16)).mul(_sv2)).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / _t17)) : FloatVector.broadcast(SIMD_SPECIES, alpha).fma(_sv2, _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t0))));
        float _t49 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t49  !=  0.0f ? _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t49)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] slerpShortest_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        float _t0 = 1.0f - alpha;
        float _t12 = Math.fma(src[srcOffset + 3], target[targetOffset + 3], Math.fma(src[srcOffset + 2], target[targetOffset + 2], Math.fma(src[srcOffset + 0], target[targetOffset + 0], src[srcOffset + 1] * target[targetOffset + 1])));
        float _t16 = (float) Math.acos(Math.min(1.0f, Math.abs(_t12)));
        float _t17 = (float) Math.sin(_t16);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, target, targetOffset);
        var _sv2 = (-_t12  >  0.0f ? _sv1.neg() : _sv1);
        var _sv3 = (_t17  >  0.0f ? _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(_t0 * _t16))).add(FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(alpha * _t16)).mul(_sv2)).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / _t17)) : FloatVector.broadcast(SIMD_SPECIES, alpha).mul(_sv2).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t0))));
        float _t49 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t49  !=  0.0f ? _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t49)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] addScaled(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float weight) {
        if (SimdSupport.USE_FMA) return addScaled_fma(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return addScaled_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    public static float[] addScaled_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float weight) {
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, weight).fma(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] addScaled_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float weight) {
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, weight).mul(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] calculateW(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(3, (float) Math.sqrt(Math.max(0.0f, Math.fma(-_selfx, _selfx, Math.fma(-_selfy, _selfy, Math.fma(-_selfz, _selfz, 1.0f))))));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] exp(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfz = src[srcOffset + 2];
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _t2 = Math.min((float) Math.exp(src[srcOffset + 3]), 3.4028235E38f);
        float _t4 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t5 = (float) Math.sqrt(_t4);
        float _t7 = (float) Math.sin(_t5);
        var _c0 = (_t4  >  0.0f ? FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(3, (float) Math.cosFromSin(_t7, _t5)).mul(FloatVector.broadcast(SIMD_SPECIES, _t2 * (_t7 * (1.0f / (float) Math.sqrt(_t4)))).withLane(3, _t2)) : FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, (float) Math.cosFromSin(_t7, _t5) * _t2));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] log(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfw = src[srcOffset + 3];
        float _selfz = src[srcOffset + 2];
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        var _c0 = (_t2  >  0.0f ? FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, (float) Math.atan2((float) Math.sqrt(_t2), _selfw) * (1.0f / (float) Math.sqrt(_t2)))).withLane(3, (float) Math.log((float) Math.sqrt(Math.fma(_selfw, _selfw, _t2)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, (float) Math.log((float) Math.sqrt(Math.fma(_selfw, _selfw, _t2)))));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] normalize(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        float _t3 = _sv0.mul(_sv0).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t3  !=  0.0f ? _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t3)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] rotateTowards(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float step) {
        if (SimdSupport.USE_FMA) return rotateTowards_fma(dest, destOffset, src, srcOffset, target, targetOffset, step);
        return rotateTowards_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, step);
    }

    public static float[] rotateTowards_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float step) {
        float _selfw = src[srcOffset + 3];
        float _targetw = target[targetOffset + 3];
        float _selfz = src[srcOffset + 2];
        float _targetz = target[targetOffset + 2];
        float _selfx = src[srcOffset + 0];
        float _targetx = target[targetOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _targety = target[targetOffset + 1];
        float _t7 = Math.fma(_selfw, _targetw, Math.fma(_selfz, _targetz, Math.fma(_selfx, _targetx, _selfy * _targety)));
        float _t9 = -_t7;
        float _t11 = (float) Math.acos(Math.min(1.0f, Math.abs(_t7)));
        float _t12 = (float) Math.sin(_t11);
        float _t13, _t14, _t15, _t16;
        if (_t9 > 0.0f) {
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
        float _t17 = _selfw - _t13;
        float _t18 = _selfz - _t14;
        float _t19 = _selfx - _t15;
        float _t20 = _selfy - _t16;
        float _t21 = _selfw + _t13;
        float _t22 = _selfz + _t14;
        float _t23 = _selfx + _t15;
        float _t24 = _selfy + _t16;
        float _t36 = 4.0f * (float) Math.atan2((float) Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))), (float) Math.sqrt(Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)))));
        float _t39 = _t36 > 0.0f ? Math.min(1.0f, step / _t36) : 0.0f;
        float _t40 = 1.0f - _t39;
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, target, targetOffset);
        var _sv2 = (_t9  >  0.0f ? _sv1.neg() : _sv1);
        var _sv3 = (_t12  >  0.0f ? _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(_t40 * _t11)), FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(_t11 * _t39)).mul(_sv2)).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / _t12)) : _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t40), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t39))));
        float _t72 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t72  !=  0.0f ? _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t72)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] rotateTowards_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float step) {
        float _selfw = src[srcOffset + 3];
        float _targetw = target[targetOffset + 3];
        float _selfz = src[srcOffset + 2];
        float _targetz = target[targetOffset + 2];
        float _selfx = src[srcOffset + 0];
        float _targetx = target[targetOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _targety = target[targetOffset + 1];
        float _t7 = Math.fma(_selfw, _targetw, Math.fma(_selfz, _targetz, Math.fma(_selfx, _targetx, _selfy * _targety)));
        float _t9 = -_t7;
        float _t11 = (float) Math.acos(Math.min(1.0f, Math.abs(_t7)));
        float _t12 = (float) Math.sin(_t11);
        float _t13, _t14, _t15, _t16;
        if (_t9 > 0.0f) {
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
        float _t17 = _selfw - _t13;
        float _t18 = _selfz - _t14;
        float _t19 = _selfx - _t15;
        float _t20 = _selfy - _t16;
        float _t21 = _selfw + _t13;
        float _t22 = _selfz + _t14;
        float _t23 = _selfx + _t15;
        float _t24 = _selfy + _t16;
        float _t36 = 4.0f * (float) Math.atan2((float) Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))), (float) Math.sqrt(Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)))));
        float _t39 = _t36 > 0.0f ? Math.min(1.0f, step / _t36) : 0.0f;
        float _t40 = 1.0f - _t39;
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, target, targetOffset);
        var _sv2 = (_t9  >  0.0f ? _sv1.neg() : _sv1);
        var _sv3 = (_t12  >  0.0f ? _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(_t40 * _t11))).add(FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(_t11 * _t39)).mul(_sv2)).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / _t12)) : _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t40)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t39))));
        float _t72 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t72  !=  0.0f ? _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t72)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] makeRotationAxis(float[] dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        if (SimdSupport.USE_FMA) return makeRotationAxis_fma(dest, destOffset, angle, axisX, axisY, axisZ);
        return makeRotationAxis_mulAdd(dest, destOffset, angle, axisX, axisY, axisZ);
    }

    public static float[] makeRotationAxis_fma(float[] dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        var _c0 = FloatVector.zero(SIMD_SPECIES).withLane(0, axisX).withLane(1, axisY).withLane(2, axisZ).fma(FloatVector.broadcast(SIMD_SPECIES, _t1), FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, (float) Math.cosFromSin(_t1, _t0)));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] makeRotationAxis_mulAdd(float[] dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        var _c0 = FloatVector.zero(SIMD_SPECIES).withLane(0, axisX).withLane(1, axisY).withLane(2, axisZ).mul(FloatVector.broadcast(SIMD_SPECIES, _t1)).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, (float) Math.cosFromSin(_t1, _t0)));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] makeRotationAxis(float[] dest, int destOffset, float[] axis, int axisOffset, float angle) {
        if (SimdSupport.USE_FMA) return makeRotationAxis_fma(dest, destOffset, axis, axisOffset, angle);
        return makeRotationAxis_mulAdd(dest, destOffset, axis, axisOffset, angle);
    }

    public static float[] makeRotationAxis_fma(float[] dest, int destOffset, float[] axis, int axisOffset, float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        var _c0 = FloatVector.zero(SIMD_SPECIES).withLane(0, axis[axisOffset + 0]).withLane(1, axis[axisOffset + 1]).withLane(2, axis[axisOffset + 2]).fma(FloatVector.broadcast(SIMD_SPECIES, _t1), FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, (float) Math.cosFromSin(_t1, _t0)));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] makeRotationAxis_mulAdd(float[] dest, int destOffset, float[] axis, int axisOffset, float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        var _c0 = FloatVector.zero(SIMD_SPECIES).withLane(0, axis[axisOffset + 0]).withLane(1, axis[axisOffset + 1]).withLane(2, axis[axisOffset + 2]).mul(FloatVector.broadcast(SIMD_SPECIES, _t1)).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, (float) Math.cosFromSin(_t1, _t0)));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] makeRotationYXZ(float[] dest, int destOffset, float angleY, float angleX, float angleZ) {
        if (SimdSupport.USE_FMA) return makeRotationYXZ_fma(dest, destOffset, angleY, angleX, angleZ);
        return makeRotationYXZ_mulAdd(dest, destOffset, angleY, angleX, angleZ);
    }

    public static float[] makeRotationYXZ_fma(float[] dest, int destOffset, float angleY, float angleX, float angleZ) {
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
        var _c0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t11).withLane(2, _t12).withLane(3, _t9).fma(FloatVector.broadcast(SIMD_SPECIES, _t5), FloatVector.broadcast(SIMD_SPECIES, _t10).withLane(3, _t12).mul(FloatVector.broadcast(SIMD_SPECIES, _t7)).withLane(2, -(_t9 * _t7))).withLane(1, _t11 * _t7 - _t10 * _t5);
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] makeRotationYXZ_mulAdd(float[] dest, int destOffset, float angleY, float angleX, float angleZ) {
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
        var _c0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t11).withLane(2, _t12).withLane(3, _t9).mul(FloatVector.broadcast(SIMD_SPECIES, _t5)).add(FloatVector.broadcast(SIMD_SPECIES, _t10).withLane(3, _t12).mul(FloatVector.broadcast(SIMD_SPECIES, _t7)).withLane(2, -(_t9 * _t7))).withLane(1, _t11 * _t7 - _t10 * _t5);
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    private static void copyArrArr(float[] dest, int destOffset, float[] src, int srcOffset, int n) {
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length())
            FloatVector.fromArray(_sp, src, srcOffset + _i).intoArray(dest, destOffset + _i);
        for (; _i <= n - 8; _i += 8)
            FloatVector.fromArray(FloatVector.SPECIES_256, src, srcOffset + _i).intoArray(dest, destOffset + _i);
        for (; _i <= n - 4; _i += 4)
            FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset + _i).intoArray(dest, destOffset + _i);
        for (; _i < n; _i++)
            dest[destOffset + _i] = src[srcOffset + _i];
    }

    private static void copyArrArr_one(float[] dest, int destOffset, float[] src, int srcOffset) {
        FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset).intoArray(dest, destOffset);
    }


    public static float[] copy(float[] dest, int destOffset, float[] src, int srcOffset) {
        copyArrArr_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        copyArrArr(dest, destOffset, src, srcOffset, count * 4);
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr_one(dest, destOffset, _srcArr, _srcOff);
        } else {
            for (int _i = 0; _i < 4; _i++)
                dest[destOffset + _i] = src.get(srcOffset + _i);
        }
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (src.hasArray() && srcOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && srcOffset <= src.limit() - (count > 536870911 ? -1 : count * 4)) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr(dest, destOffset, _srcArr, _srcOff, count * 4);
        } else {
            for (int _i = 0; _i < count * 4; _i++)
                dest[destOffset + _i] = src.get(srcOffset + _i);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, float[] src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr_one(_destArr, _destOff, src, srcOffset);
        } else {
            for (int _i = 0; _i < 4; _i++)
                dest.put(destOffset + _i, src[srcOffset + _i]);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && destOffset <= dest.limit() - (count > 536870911 ? -1 : count * 4)) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr(_destArr, _destOff, src, srcOffset, count * 4);
        } else {
            for (int _i = 0; _i < count * 4; _i++)
                dest.put(destOffset + _i, src[srcOffset + _i]);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr_one(_destArr, _destOff, _srcArr, _srcOff);
            } else {
                for (int _i = 0; _i < 4; _i++)
                    _destArr[_destOff + _i] = src.get(srcOffset + _i);
            }
        } else {
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                for (int _i = 0; _i < 4; _i++)
                    dest.put(destOffset + _i, _srcArr[_srcOff + _i]);
            } else {
                for (int _i = 0; _i < 4; _i++)
                    dest.put(destOffset + _i, src.get(srcOffset + _i));
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && destOffset <= dest.limit() - (count > 536870911 ? -1 : count * 4)) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && srcOffset <= src.limit() - (count > 536870911 ? -1 : count * 4)) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr(_destArr, _destOff, _srcArr, _srcOff, count * 4);
            } else {
                for (int _i = 0; _i < count * 4; _i++)
                    _destArr[_destOff + _i] = src.get(srcOffset + _i);
            }
        } else {
            if (src.hasArray() && srcOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && srcOffset <= src.limit() - (count > 536870911 ? -1 : count * 4)) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                for (int _i = 0; _i < count * 4; _i++)
                    dest.put(destOffset + _i, _srcArr[_srcOff + _i]);
            } else {
                for (int _i = 0; _i < count * 4; _i++)
                    dest.put(destOffset + _i, src.get(srcOffset + _i));
            }
        }
        return dest;
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

    /** Double-precision twin of {@link #quatArcAngle(float)}. */
    private static double quatArcAngle(double s) {
        double d = 4.0 - s;
        return s > d ? 2.0 * Math.asin(0.5 * Math.sqrt(d)) : Math.PI - 2.0 * Math.asin(0.5 * Math.sqrt(s));
    }
}
