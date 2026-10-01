// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.opssimd;

import jdk.incubator.vector.*;
import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.kernels.*;
import org.joml2.internal.simd.*;

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
    private static final float[] DATA_0 = new float[] {0.0f, 0.0f, 0.0f, 1.0f};

    public static float[] add(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        FloatVector.fromArray(SIMD_SPECIES, other, otherOffset).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] mul(float[] dest, int destOffset, float[] src, int srcOffset, float scalar) {
        FloatVector.broadcast(SIMD_SPECIES, scalar).mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] negate(float[] dest, int destOffset, float[] src, int srcOffset) {
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg().intoArray(dest, destOffset);
        return dest;
    }

    public static float[] sub(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).sub(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] set(float[] dest, int destOffset, float[] v, int vOffset) {
        FloatVector.fromArray(SIMD_SPECIES, v, vOffset).intoArray(dest, destOffset);
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

    public static float[] makeIdentity(float[] dest, int destOffset) {
        FloatVector.fromArray(FloatVector.SPECIES_128, DATA_0, 0).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] makeZero(float[] dest, int destOffset) {
        FloatVector.broadcast(SIMD_SPECIES, 0.0f).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] lerp(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    public static float[] lerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        FloatVector.broadcast(SIMD_SPECIES, t).fma(FloatVector.zero(SIMD_SPECIES).withLane(0, otherX).withLane(1, otherY).withLane(2, otherZ).withLane(3, otherW).sub(_sv0), _sv0).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] lerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        FloatVector.broadcast(SIMD_SPECIES, t).mul(FloatVector.zero(SIMD_SPECIES).withLane(0, otherX).withLane(1, otherY).withLane(2, otherZ).withLane(3, otherW).sub(_sv0)).add(_sv0).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] lerp(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static float[] lerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        FloatVector.broadcast(SIMD_SPECIES, t).fma(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset).sub(_sv0), _sv0).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] lerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        FloatVector.broadcast(SIMD_SPECIES, t).mul(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset).sub(_sv0)).add(_sv0).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] nlerp(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        if (SimdSupport.USE_FMA) return nlerp_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return nlerp_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static float[] nlerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.broadcast(_SIMD_SPECIES, alpha).fma(FloatVector.fromArray(_SIMD_SPECIES, target, targetOffset), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f - alpha)));
        float _t12 = _sv0.mul(_sv0).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t12 != 0.0f ? _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t12)))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f)).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] nlerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.broadcast(_SIMD_SPECIES, alpha).mul(FloatVector.fromArray(_SIMD_SPECIES, target, targetOffset)).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f - alpha)));
        float _t12 = _sv0.mul(_sv0).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t12 != 0.0f ? _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t12)))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f)).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] nlerpShortest(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        if (SimdSupport.USE_FMA) return nlerpShortest_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return nlerpShortest_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static float[] nlerpShortest_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, target, targetOffset);
        var _sv1 = FloatVector.broadcast(_SIMD_SPECIES, alpha).fma((-java.lang.Math.fma(src[srcOffset + 3], target[targetOffset + 3], java.lang.Math.fma(src[srcOffset + 2], target[targetOffset + 2], java.lang.Math.fma(src[srcOffset], target[targetOffset], src[srcOffset + 1] * target[targetOffset + 1]))) > 0.0f ? _sv0.neg() : _sv0), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f - alpha)));
        float _t25 = _sv1.mul(_sv1).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t25 != 0.0f ? _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t25)))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f)).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] nlerpShortest_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, target, targetOffset);
        var _sv1 = FloatVector.broadcast(_SIMD_SPECIES, alpha).mul((-((src[srcOffset + 3]) * (target[targetOffset + 3]) + (((src[srcOffset + 2]) * (target[targetOffset + 2]) + (((src[srcOffset]) * (target[targetOffset]) + (src[srcOffset + 1] * target[targetOffset + 1])))))) > 0.0f ? _sv0.neg() : _sv0)).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f - alpha)));
        float _t25 = _sv1.mul(_sv1).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t25 != 0.0f ? _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t25)))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f)).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] slerp(float[] dest, int destOffset, float[] src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (SimdSupport.USE_FMA) return slerp_fma(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return slerp_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    public static float[] slerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _t2 = src[srcOffset + 3] + targetW;
        float _t3 = src[srcOffset + 2] + targetZ;
        float _t4 = src[srcOffset] + targetX;
        float _t5 = src[srcOffset + 1] + targetY;
        float _t10 = Math.min(4.0f, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_fma(_t10);
        float _t17 = 2.0f * Math.sinWithCos(_t14);
        float _ct1 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct1 > 2.0E-6f && _ct1 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        float _t20_inv = 1.0f / _ct1;
        float _t22 = _t17 * _t20_inv;
        float _t24 = Math.cosWithSin(_t14) - java.lang.Math.fma(0.5f, _t10, -1.0f) * _t17 * _t20_inv;
        float _w0, _w1;
        if (alpha < 0.5f) {
            _w0 = _t24;
            _w1 = _t22;
        } else {
            _w0 = _t22;
            _w1 = _t24;
        }
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(FloatVector.broadcast(SIMD_SPECIES, _w0), FloatVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(FloatVector.broadcast(SIMD_SPECIES, _w1))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] slerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _t2 = src[srcOffset + 3] + targetW;
        float _t3 = src[srcOffset + 2] + targetZ;
        float _t4 = src[srcOffset] + targetX;
        float _t5 = src[srcOffset + 1] + targetY;
        float _t10 = Math.min(4.0f, ((_t2) * (_t2) + (((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5)))))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_mulAdd(_t10);
        float _t17 = 2.0f * Math.sinWithCos(_t14);
        float _ct3 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct3 > 2.0E-6f && _ct3 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        float _t20_inv = 1.0f / _ct3;
        float _t22 = _t17 * _t20_inv;
        float _t24 = Math.cosWithSin(_t14) - ((0.5f) * (_t10) - (1.0f)) * _t17 * _t20_inv;
        float _w0, _w1;
        if (alpha < 0.5f) {
            _w0 = _t24;
            _w1 = _t22;
        } else {
            _w0 = _t22;
            _w1 = _t24;
        }
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(FloatVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(FloatVector.broadcast(SIMD_SPECIES, _w1))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] slerp_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (SimdSupport.USE_FMA) return slerp_degenerate_fma(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return slerp_degenerate_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    public static float[] slerp_degenerate_fma(float[] dest, int destOffset, float[] src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _t1 = src[srcOffset + 3] + targetW;
        float _t2 = src[srcOffset + 2] + targetZ;
        float _t3 = src[srcOffset] + targetX;
        float _t4 = src[srcOffset + 1] + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, _t4 * _t4))));
        float _w0, _w1;
        if (_t11 > 4.0f - _t11) {
            _w0 = 1.0f - alpha;
            _w1 = alpha;
        } else {
            _w0 = _t5;
            _w1 = 1.0f - _t5;
        }
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(FloatVector.broadcast(SIMD_SPECIES, _w0), FloatVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(FloatVector.broadcast(SIMD_SPECIES, _w1))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] slerp_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _t1 = src[srcOffset + 3] + targetW;
        float _t2 = src[srcOffset + 2] + targetZ;
        float _t3 = src[srcOffset] + targetX;
        float _t4 = src[srcOffset + 1] + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, ((_t1) * (_t1) + (((_t2) * (_t2) + (((_t3) * (_t3) + (_t4 * _t4)))))));
        float _w0, _w1;
        if (_t11 > 4.0f - _t11) {
            _w0 = 1.0f - alpha;
            _w1 = alpha;
        } else {
            _w0 = _t5;
            _w1 = 1.0f - _t5;
        }
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(FloatVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(FloatVector.broadcast(SIMD_SPECIES, _w1))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] slerp(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        if (SimdSupport.USE_FMA) return slerp_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerp_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static float[] slerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        float _t2 = src[srcOffset + 3] + target[targetOffset + 3];
        float _t3 = src[srcOffset + 2] + target[targetOffset + 2];
        float _t4 = src[srcOffset] + target[targetOffset];
        float _t5 = src[srcOffset + 1] + target[targetOffset + 1];
        float _t10 = Math.min(4.0f, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_fma(_t10);
        float _t17 = 2.0f * Math.sinWithCos(_t14);
        float _ct1 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct1 > 2.0E-6f && _ct1 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        float _t20_inv = 1.0f / _ct1;
        float _t22 = _t17 * _t20_inv;
        float _t24 = Math.cosWithSin(_t14) - java.lang.Math.fma(0.5f, _t10, -1.0f) * _t17 * _t20_inv;
        float _w0, _w1;
        if (alpha < 0.5f) {
            _w0 = _t24;
            _w1 = _t22;
        } else {
            _w0 = _t22;
            _w1 = _t24;
        }
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(FloatVector.broadcast(SIMD_SPECIES, _w0), FloatVector.fromArray(SIMD_SPECIES, target, targetOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _w1))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] slerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        float _t2 = src[srcOffset + 3] + target[targetOffset + 3];
        float _t3 = src[srcOffset + 2] + target[targetOffset + 2];
        float _t4 = src[srcOffset] + target[targetOffset];
        float _t5 = src[srcOffset + 1] + target[targetOffset + 1];
        float _t10 = Math.min(4.0f, ((_t2) * (_t2) + (((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5)))))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_mulAdd(_t10);
        float _t17 = 2.0f * Math.sinWithCos(_t14);
        float _ct3 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct3 > 2.0E-6f && _ct3 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        float _t20_inv = 1.0f / _ct3;
        float _t22 = _t17 * _t20_inv;
        float _t24 = Math.cosWithSin(_t14) - ((0.5f) * (_t10) - (1.0f)) * _t17 * _t20_inv;
        float _w0, _w1;
        if (alpha < 0.5f) {
            _w0 = _t24;
            _w1 = _t22;
        } else {
            _w0 = _t22;
            _w1 = _t24;
        }
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(FloatVector.fromArray(SIMD_SPECIES, target, targetOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _w1))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] slerp_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        if (SimdSupport.USE_FMA) return slerp_degenerate_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerp_degenerate_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static float[] slerp_degenerate_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        float _t1 = src[srcOffset + 3] + target[targetOffset + 3];
        float _t2 = src[srcOffset + 2] + target[targetOffset + 2];
        float _t3 = src[srcOffset] + target[targetOffset];
        float _t4 = src[srcOffset + 1] + target[targetOffset + 1];
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, _t4 * _t4))));
        float _w0, _w1;
        if (_t11 > 4.0f - _t11) {
            _w0 = 1.0f - alpha;
            _w1 = alpha;
        } else {
            _w0 = _t5;
            _w1 = 1.0f - _t5;
        }
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(FloatVector.broadcast(SIMD_SPECIES, _w0), FloatVector.fromArray(SIMD_SPECIES, target, targetOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _w1))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] slerp_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        float _t1 = src[srcOffset + 3] + target[targetOffset + 3];
        float _t2 = src[srcOffset + 2] + target[targetOffset + 2];
        float _t3 = src[srcOffset] + target[targetOffset];
        float _t4 = src[srcOffset + 1] + target[targetOffset + 1];
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, ((_t1) * (_t1) + (((_t2) * (_t2) + (((_t3) * (_t3) + (_t4 * _t4)))))));
        float _w0, _w1;
        if (_t11 > 4.0f - _t11) {
            _w0 = 1.0f - alpha;
            _w1 = alpha;
        } else {
            _w0 = _t5;
            _w1 = 1.0f - _t5;
        }
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(FloatVector.fromArray(SIMD_SPECIES, target, targetOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _w1))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] slerpShortest(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        if (SimdSupport.USE_FMA) return slerpShortest_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerpShortest_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static float[] slerpShortest_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        float _t12 = java.lang.Math.fma(src[srcOffset + 3], target[targetOffset + 3], java.lang.Math.fma(src[srcOffset + 2], target[targetOffset + 2], java.lang.Math.fma(src[srcOffset], target[targetOffset], src[srcOffset + 1] * target[targetOffset + 1])));
        float _t15 = Math.min(1.0f, java.lang.Math.abs(_t12));
        float _t18 = alpha * Math.acos(_t15);
        float _t29 = java.lang.Math.fma(-_t15, _t15, 1.0f);
        float _t32 = Math.sinWithCos(_t18) * (1.0f / (float) java.lang.Math.sqrt(_t29));
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, target, targetOffset);
        var _sv2 = (-_t12 > 0.0f ? _sv1.neg() : _sv1);
        var _sv3 = ((float) java.lang.Math.sqrt(_t29) > 0.0f ? _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(-_t32, _t15, Math.cosWithSin(_t18))), FloatVector.broadcast(_SIMD_SPECIES, _t32).mul(_sv2)) : FloatVector.broadcast(_SIMD_SPECIES, alpha).fma(_sv2, _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f - alpha))));
        float _t50 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t50 != 0.0f ? _sv3.mul(FloatVector.broadcast(_SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t50)))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f)).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] slerpShortest_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        float _t12 = ((src[srcOffset + 3]) * (target[targetOffset + 3]) + (((src[srcOffset + 2]) * (target[targetOffset + 2]) + (((src[srcOffset]) * (target[targetOffset]) + (src[srcOffset + 1] * target[targetOffset + 1]))))));
        float _t15 = Math.min(1.0f, java.lang.Math.abs(_t12));
        float _t18 = alpha * Math.acos(_t15);
        float _t29 = ((1.0f) - (_t15) * (_t15));
        float _t32 = Math.sinWithCos(_t18) * (1.0f / (float) java.lang.Math.sqrt(_t29));
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, target, targetOffset);
        var _sv2 = (-_t12 > 0.0f ? _sv1.neg() : _sv1);
        var _sv3 = ((float) java.lang.Math.sqrt(_t29) > 0.0f ? _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((Math.cosWithSin(_t18)) - (_t32) * (_t15)))).add(FloatVector.broadcast(_SIMD_SPECIES, _t32).mul(_sv2)) : FloatVector.broadcast(_SIMD_SPECIES, alpha).mul(_sv2).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f - alpha))));
        float _t50 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t50 != 0.0f ? _sv3.mul(FloatVector.broadcast(_SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t50)))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f)).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] addScaled(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float weight) {
        if (SimdSupport.USE_FMA) return addScaled_fma(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return addScaled_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    public static float[] addScaled_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float weight) {
        FloatVector.broadcast(SIMD_SPECIES, weight).fma(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] addScaled_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float weight) {
        FloatVector.broadcast(SIMD_SPECIES, weight).mul(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] calculateW(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src[srcOffset];
            float _selfy = src[srcOffset + 1];
            float _selfz = src[srcOffset + 2];
            FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(3, (float) java.lang.Math.sqrt(Math.max(0.0f, java.lang.Math.fma(-_selfx, _selfx, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(-_selfz, _selfz, 1.0f)))))).intoArray(dest, destOffset);
            return dest;
        } else {
            float _selfx = src[srcOffset];
            float _selfy = src[srcOffset + 1];
            float _selfz = src[srcOffset + 2];
            FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(3, (float) java.lang.Math.sqrt(Math.max(0.0f, ((((((1.0f) - (_selfz) * (_selfz))) - (_selfy) * (_selfy))) - (_selfx) * (_selfx))))).intoArray(dest, destOffset);
            return dest;
        }
    }

    public static float[] exp(float[] dest, int destOffset, float[] src, int srcOffset) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfz = src[srcOffset + 2];
            float _selfx = src[srcOffset];
            float _selfy = src[srcOffset + 1];
            float _t2 = Math.min(Math.exp(src[srcOffset + 3]), 3.4028235E38f);
            float _t4 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
            float _t5 = (float) java.lang.Math.sqrt(_t4);
            (_t4 > 0.0f ? FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).withLane(3, Math.cosWithSin(_t5)).mul(FloatVector.broadcast(_SIMD_SPECIES, _t2 * (Math.sinWithCos(_t5) * (1.0f / (float) java.lang.Math.sqrt(_t4)))).withLane(3, _t2)) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f).withLane(3, Math.cosWithSin(_t5) * _t2)).intoArray(dest, destOffset);
            return dest;
        } else {
            float _selfz = src[srcOffset + 2];
            float _selfx = src[srcOffset];
            float _selfy = src[srcOffset + 1];
            float _t2 = Math.min(Math.exp(src[srcOffset + 3]), 3.4028235E38f);
            float _t4 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
            float _t5 = (float) java.lang.Math.sqrt(_t4);
            (_t4 > 0.0f ? FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).withLane(3, Math.cosWithSin(_t5)).mul(FloatVector.broadcast(_SIMD_SPECIES, _t2 * (Math.sinWithCos(_t5) * (1.0f / (float) java.lang.Math.sqrt(_t4)))).withLane(3, _t2)) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f).withLane(3, Math.cosWithSin(_t5) * _t2)).intoArray(dest, destOffset);
            return dest;
        }
    }

    public static float[] log(float[] dest, int destOffset, float[] src, int srcOffset) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfw = src[srcOffset + 3];
            float _selfz = src[srcOffset + 2];
            float _selfx = src[srcOffset];
            float _selfy = src[srcOffset + 1];
            float _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
            (_t2 > 0.0f ? FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, Math.atan2((float) java.lang.Math.sqrt(_t2), _selfw) * (1.0f / (float) java.lang.Math.sqrt(_t2)))).withLane(3, Math.log((float) java.lang.Math.sqrt(java.lang.Math.fma(_selfw, _selfw, _t2)))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f).withLane(3, Math.log((float) java.lang.Math.sqrt(java.lang.Math.fma(_selfw, _selfw, _t2))))).intoArray(dest, destOffset);
            return dest;
        } else {
            float _selfw = src[srcOffset + 3];
            float _selfz = src[srcOffset + 2];
            float _selfx = src[srcOffset];
            float _selfy = src[srcOffset + 1];
            float _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
            (_t2 > 0.0f ? FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, Math.atan2((float) java.lang.Math.sqrt(_t2), _selfw) * (1.0f / (float) java.lang.Math.sqrt(_t2)))).withLane(3, Math.log((float) java.lang.Math.sqrt(((_selfw) * (_selfw) + (_t2))))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f).withLane(3, Math.log((float) java.lang.Math.sqrt(((_selfw) * (_selfw) + (_t2)))))).intoArray(dest, destOffset);
            return dest;
        }
    }

    public static float[] normalize(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        float _t3 = _sv0.mul(_sv0).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t3 != 0.0f ? _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t3)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f)).intoArray(dest, destOffset);
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
        float _selfx = src[srcOffset];
        float _targetx = target[targetOffset];
        float _selfy = src[srcOffset + 1];
        float _targety = target[targetOffset + 1];
        float _t8 = -java.lang.Math.fma(_selfw, _targetw, java.lang.Math.fma(_selfz, _targetz, java.lang.Math.fma(_selfx, _targetx, _selfy * _targety)));
        float _t9, _t10, _t11, _t12;
        if (_t8 > 0.0f) {
            _t9 = -_targetw;
            _t10 = -_targetz;
            _t11 = -_targetx;
            _t12 = -_targety;
        } else {
            _t9 = _targetw;
            _t10 = _targetz;
            _t11 = _targetx;
            _t12 = _targety;
        }
        float _t13 = _selfw + _t9;
        float _t14 = _selfz + _t10;
        float _t15 = _selfx + _t11;
        float _t16 = _selfy + _t12;
        float _t17 = _selfw - _t9;
        float _t18 = _selfz - _t10;
        float _t19 = _selfx - _t11;
        float _t20 = _selfy - _t12;
        float _t30 = java.lang.Math.fma(_t15, _t15, _t16 * _t16);
        float _t37 = java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, _t30));
        float _t38 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        return rotateTowards_fma_s8375f417_1(dest, destOffset, src, srcOffset, target, targetOffset, step, _t8, _t13, _t14, _t17, _t18, _t19, _t20, _t30, _t37 * _t38, Math.atan2((float) java.lang.Math.sqrt(_t38), (float) java.lang.Math.sqrt(_t37)));
    }

    /** Piece 2 of {@code rotateTowards_fma}, split to fit the inline budget; reached only through it. */
    private static float[] rotateTowards_fma_s8375f417_1(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float step, float _t8, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30, float _t43, float _t46) {
        float _t49 = 4.0f * _t46;
        float _t55 = _t49 > 0.0f ? Math.min(1.0f, step / _t49) : 0.0f;
        float _t61 = (_t46 + _t46) * _t55;
        rotateTowards_fma_saed59c17_v(dest, destOffset, src, srcOffset, target, targetOffset, _t8, _t13, _t14, _t17, _t18, _t19, _t20, _t30, _t43, _t55, _t61, 2.0f * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t43)));
        return dest;
    }

    private static void rotateTowards_fma_saed59c17_v(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float _t8, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30, float _t43, float _t55, float _t61, float _t65) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, target, targetOffset);
        var _sv2 = (_t8 > 0.0f ? _sv1.neg() : _sv1);
        var _sv3 = (0.5f * (float) java.lang.Math.sqrt(_t43) > 0.0f ? _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(-(0.25f * (_t30 + java.lang.Math.fma(_t14, _t14, _t13 * _t13) + (java.lang.Math.fma(-_t20, _t20, -(_t19 * _t19)) + java.lang.Math.fma(-_t17, _t17, -(_t18 * _t18))))), _t65, Math.cosWithSin(_t61))), FloatVector.broadcast(_SIMD_SPECIES, _t65).mul(_sv2)) : _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, 1.0f - _t55), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t55))));
        float _t86 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t86 != 0.0f ? _sv3.mul(FloatVector.broadcast(_SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t86)))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f)).intoArray(dest, destOffset);
    }

    public static float[] rotateTowards_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float step) {
        float _selfw = src[srcOffset + 3];
        float _targetw = target[targetOffset + 3];
        float _selfz = src[srcOffset + 2];
        float _targetz = target[targetOffset + 2];
        float _selfx = src[srcOffset];
        float _targetx = target[targetOffset];
        float _selfy = src[srcOffset + 1];
        float _targety = target[targetOffset + 1];
        float _t8 = -((_selfw) * (_targetw) + (((_selfz) * (_targetz) + (((_selfx) * (_targetx) + (_selfy * _targety))))));
        float _t9, _t10, _t11, _t12;
        if (_t8 > 0.0f) {
            _t9 = -_targetw;
            _t10 = -_targetz;
            _t11 = -_targetx;
            _t12 = -_targety;
        } else {
            _t9 = _targetw;
            _t10 = _targetz;
            _t11 = _targetx;
            _t12 = _targety;
        }
        float _t13 = _selfw + _t9;
        float _t14 = _selfz + _t10;
        float _t15 = _selfx + _t11;
        float _t16 = _selfy + _t12;
        float _t17 = _selfw - _t9;
        float _t18 = _selfz - _t10;
        float _t19 = _selfx - _t11;
        float _t20 = _selfy - _t12;
        float _t30 = ((_t15) * (_t15) + (_t16 * _t16));
        float _t37 = ((_t13) * (_t13) + (((_t14) * (_t14) + (_t30))));
        float _t38 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t46 = Math.atan2((float) java.lang.Math.sqrt(_t38), (float) java.lang.Math.sqrt(_t37));
        return rotateTowards_mulAdd_s35a7abba_1(dest, destOffset, src, srcOffset, target, targetOffset, step, _t8, _t13, _t14, _t17, _t18, _t19, _t20, _t30, _t37 * _t38, _t46, 4.0f * _t46);
    }

    /** Piece 2 of {@code rotateTowards_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float[] rotateTowards_mulAdd_s35a7abba_1(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float step, float _t8, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30, float _t43, float _t46, float _t49) {
        float _t55 = _t49 > 0.0f ? Math.min(1.0f, step / _t49) : 0.0f;
        float _t61 = (_t46 + _t46) * _t55;
        rotateTowards_mulAdd_sccafa0d6_v(dest, destOffset, src, srcOffset, target, targetOffset, _t8, _t13, _t14, _t17, _t18, _t19, _t20, _t30, _t43, _t55, _t61, 2.0f * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t43)));
        return dest;
    }

    private static void rotateTowards_mulAdd_sccafa0d6_v(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float _t8, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30, float _t43, float _t55, float _t61, float _t65) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, target, targetOffset);
        var _sv2 = (_t8 > 0.0f ? _sv1.neg() : _sv1);
        var _sv3 = (0.5f * (float) java.lang.Math.sqrt(_t43) > 0.0f ? _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((Math.cosWithSin(_t61)) - (0.25f * (_t30 + ((_t14) * (_t14) + (_t13 * _t13)) + (((-_t20) * (_t20) - (_t19 * _t19)) + ((-_t17) * (_t17) - (_t18 * _t18))))) * (_t65)))).add(FloatVector.broadcast(_SIMD_SPECIES, _t65).mul(_sv2)) : _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f - _t55)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t55))));
        float _t86 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t86 != 0.0f ? _sv3.mul(FloatVector.broadcast(_SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t86)))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f)).intoArray(dest, destOffset);
    }

    public static float[] makeRotationAxis(float[] dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        if (SimdSupport.USE_FMA) return makeRotationAxis_fma(dest, destOffset, angle, axisX, axisY, axisZ);
        return makeRotationAxis_mulAdd(dest, destOffset, angle, axisX, axisY, axisZ);
    }

    public static float[] makeRotationAxis_fma(float[] dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = 0.5f * angle;
        FloatVector.zero(SIMD_SPECIES).withLane(0, axisX).withLane(1, axisY).withLane(2, axisZ).fma(FloatVector.broadcast(SIMD_SPECIES, Math.sinWithCos(_t0)), FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, Math.cosWithSin(_t0))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] makeRotationAxis_mulAdd(float[] dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = 0.5f * angle;
        FloatVector.zero(SIMD_SPECIES).withLane(0, axisX).withLane(1, axisY).withLane(2, axisZ).mul(FloatVector.broadcast(SIMD_SPECIES, Math.sinWithCos(_t0))).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, Math.cosWithSin(_t0))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] makeRotationAxis(float[] dest, int destOffset, float[] axis, int axisOffset, float angle) {
        if (SimdSupport.USE_FMA) return makeRotationAxis_fma(dest, destOffset, axis, axisOffset, angle);
        return makeRotationAxis_mulAdd(dest, destOffset, axis, axisOffset, angle);
    }

    public static float[] makeRotationAxis_fma(float[] dest, int destOffset, float[] axis, int axisOffset, float angle) {
        float _t0 = 0.5f * angle;
        FloatVector.zero(SIMD_SPECIES).withLane(0, axis[axisOffset]).withLane(1, axis[axisOffset + 1]).withLane(2, axis[axisOffset + 2]).fma(FloatVector.broadcast(SIMD_SPECIES, Math.sinWithCos(_t0)), FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, Math.cosWithSin(_t0))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] makeRotationAxis_mulAdd(float[] dest, int destOffset, float[] axis, int axisOffset, float angle) {
        float _t0 = 0.5f * angle;
        FloatVector.zero(SIMD_SPECIES).withLane(0, axis[axisOffset]).withLane(1, axis[axisOffset + 1]).withLane(2, axis[axisOffset + 2]).mul(FloatVector.broadcast(SIMD_SPECIES, Math.sinWithCos(_t0))).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, Math.cosWithSin(_t0))).intoArray(dest, destOffset);
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
        FloatVector.zero(SIMD_SPECIES).withLane(0, _t9).withLane(1, _t10).withLane(3, _t11).fma(FloatVector.broadcast(SIMD_SPECIES, _t5), FloatVector.broadcast(SIMD_SPECIES, _t10).withLane(3, _t12).mul(FloatVector.broadcast(SIMD_SPECIES, _t8)).withLane(1, -(_t9 * _t8))).withLane(2, _t11 * _t8 - _t12 * _t5).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] makeRotationYXZ_mulAdd(float[] dest, int destOffset, float angleY, float angleX, float angleZ) {
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
        FloatVector.zero(SIMD_SPECIES).withLane(0, _t9).withLane(1, _t10).withLane(3, _t11).mul(FloatVector.broadcast(SIMD_SPECIES, _t5)).add(FloatVector.broadcast(SIMD_SPECIES, _t10).withLane(3, _t12).mul(FloatVector.broadcast(SIMD_SPECIES, _t8)).withLane(1, -(_t9 * _t8))).withLane(2, _t11 * _t8 - _t12 * _t5).intoArray(dest, destOffset);
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

    /** cos(k pi/8) and sin(k pi/8), k = 0..4: the float quatArcAngle's rotations. */
    private static final double[] QUAT_ARC_ANGLE_COS = new double[] {1.0, 0x1.d906bcf328d46p-1, 0x1.6a09e667f3bcdp-1, 0x1.87de2a6aea963p-2, 0.0};
    private static final double[] QUAT_ARC_ANGLE_SIN = new double[] {0.0, 0x1.87de2a6aea963p-2, 0x1.6a09e667f3bcdp-1, 0x1.d906bcf328d46p-1, 1.0};

    /**
     * The angle between two unit quaternions a and b from s = |a+b|^2, clamped to [0, 4]. With
     * |a-b|^2 = 4 - s and half the angle in [0, pi/2], that half is k pi/8 + asin(|a-b|/2
     * cos(k pi/8) - |a+b|/2 sin(k pi/8)) for the k (four comparisons of 4 - s) that keeps the
     * argument within sin(pi/16): there the arcsine's odd Taylor polynomial to x^11 (3e-10 of it
     * left out), in double, is exact to well below a float ulp. Within 0.5005 ulp of the angle,
     * and no call, where java.lang.Math.asin is one: slerp is 15-24% faster.
     */
    private static float quatArcAngle_fma(float s) {
        double d = 4.0 - s, sd = java.lang.Math.sqrt(d), ss = java.lang.Math.sqrt(s);
        int k = (d > 0x1.37ca1866b95cfp-3 ? 1 : 0) + (d > 0x1.3c10eaca8ab4fp+0 ? 1 : 0)
                + (d > 0x1.61f78a9abaa59p+1 ? 1 : 0) + (d > 0x1.ec835e79946a3p+1 ? 1 : 0);
        double x = 0.5 * java.lang.Math.fma(sd, QUAT_ARC_ANGLE_COS[k], -ss * QUAT_ARC_ANGLE_SIN[k]), t = x * x;
        double p = java.lang.Math.fma(t, java.lang.Math.fma(t, java.lang.Math.fma(t, java.lang.Math.fma(t, 0x1.6e8ba2e8ba2e9p-6, 0x1.f1c71c71c71c7p-6),
                0x1.6db6db6db6db7p-5), 0x1.3333333333333p-4), 0x1.5555555555555p-3);
        return (float) (k * (Math.PI / 4) + 2 * java.lang.Math.fma(x * t, p, x));
    }

    /**
     * The angle between two unit quaternions a and b from s = |a+b|^2, clamped to [0, 4]. With
     * |a-b|^2 = 4 - s and half the angle in [0, pi/2], that half is k pi/8 + asin(|a-b|/2
     * cos(k pi/8) - |a+b|/2 sin(k pi/8)) for the k (four comparisons of 4 - s) that keeps the
     * argument within sin(pi/16): there the arcsine's odd Taylor polynomial to x^11 (3e-10 of it
     * left out), in double, is exact to well below a float ulp. Within 0.5005 ulp of the angle,
     * and no call, where java.lang.Math.asin is one: slerp is 15-24% faster.
     */
    private static float quatArcAngle_mulAdd(float s) {
        double d = 4.0 - s, sd = java.lang.Math.sqrt(d), ss = java.lang.Math.sqrt(s);
        int k = (d > 0x1.37ca1866b95cfp-3 ? 1 : 0) + (d > 0x1.3c10eaca8ab4fp+0 ? 1 : 0)
                + (d > 0x1.61f78a9abaa59p+1 ? 1 : 0) + (d > 0x1.ec835e79946a3p+1 ? 1 : 0);
        double x = 0.5 * ((sd) * (QUAT_ARC_ANGLE_COS[k]) - (ss * QUAT_ARC_ANGLE_SIN[k])), t = x * x;
        double p = ((t) * (((t) * (((t) * (((t) * (0x1.6e8ba2e8ba2e9p-6) + (0x1.f1c71c71c71c7p-6))) + (0x1.6db6db6db6db7p-5))) + (0x1.3333333333333p-4))) + (0x1.5555555555555p-3));
        return (float) (k * (Math.PI / 4) + 2 * ((x * t) * (p) + (x)));
    }
}
