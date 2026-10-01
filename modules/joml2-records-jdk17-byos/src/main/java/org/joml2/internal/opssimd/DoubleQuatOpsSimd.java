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
 * Vector-API isolation cell for {@link DoubleQuatOps}: every
 * {@code jdk.incubator.vector} reference of the Ops family lives in this class,
 * which is loaded and initialized only behind {@code SimdSupport.VECTOR_API}
 * guards - {@code DoubleQuatOps} and its kernel siblings link
 * and run without the incubator module. Not public API.
 */
public final class DoubleQuatOpsSimd {
    private DoubleQuatOpsSimd() {}
    private static final VectorSpecies<Double> SIMD_SPECIES = DoubleVector.SPECIES_256;
    private static final int PREFERRED_LANES = DoubleVector.SPECIES_PREFERRED.length();

    public static double[] add(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] mul(double[] dest, int destOffset, double[] src, int srcOffset, double scalar) {
        DoubleVector.broadcast(SIMD_SPECIES, scalar).mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] negate(double[] dest, int destOffset, double[] src, int srcOffset) {
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg().intoArray(dest, destOffset);
        return dest;
    }

    public static double[] sub(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).sub(DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] set(double[] dest, int destOffset, double[] v, int vOffset) {
        DoubleVector.fromArray(SIMD_SPECIES, v, vOffset).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] toDualQuat(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _vcp0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        _vcp0.intoArray(dest, destOffset);
        dest[destOffset + 4] = 0.0;
        dest[destOffset + 5] = 0.0;
        dest[destOffset + 6] = 0.0;
        dest[destOffset + 7] = 0.0;
        return dest;
    }

    public static double[] makeZero(double[] dest, int destOffset) {
        DoubleVector.broadcast(SIMD_SPECIES, 0.0).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] lerp(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    public static double[] lerp_fma(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        DoubleVector.broadcast(SIMD_SPECIES, t).fma(DoubleVector.zero(SIMD_SPECIES).withLane(0, otherX).withLane(1, otherY).withLane(2, otherZ).withLane(3, otherW).sub(_sv0), _sv0).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] lerp_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        DoubleVector.broadcast(SIMD_SPECIES, t).mul(DoubleVector.zero(SIMD_SPECIES).withLane(0, otherX).withLane(1, otherY).withLane(2, otherZ).withLane(3, otherW).sub(_sv0)).add(_sv0).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] lerp(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static double[] lerp_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        DoubleVector.broadcast(SIMD_SPECIES, t).fma(DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset).sub(_sv0), _sv0).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] lerp_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        DoubleVector.broadcast(SIMD_SPECIES, t).mul(DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset).sub(_sv0)).add(_sv0).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] nlerp(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double alpha) {
        if (SimdSupport.USE_FMA) return nlerp_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return nlerp_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static double[] nlerp_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double alpha) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.broadcast(_SIMD_SPECIES, alpha).fma(DoubleVector.fromArray(_SIMD_SPECIES, target, targetOffset), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(_SIMD_SPECIES, 1.0 - alpha)));
        DoubleVector _t12_v = _sv0.mul(_sv0);
        double _t12 = SimdMath.STRICT
            ? SimdMath.sumInOrder(_t12_v)
            : _t12_v.reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t12 != 0.0 ? _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, (1.0 / java.lang.Math.sqrt(_t12)))) : DoubleVector.broadcast(_SIMD_SPECIES, 0.0)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] nlerp_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double alpha) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.broadcast(_SIMD_SPECIES, alpha).mul(DoubleVector.fromArray(_SIMD_SPECIES, target, targetOffset)).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(_SIMD_SPECIES, 1.0 - alpha)));
        DoubleVector _t12_v = _sv0.mul(_sv0);
        double _t12 = SimdMath.STRICT
            ? SimdMath.sumInOrder(_t12_v)
            : _t12_v.reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t12 != 0.0 ? _sv0.mul(DoubleVector.broadcast(_SIMD_SPECIES, (1.0 / java.lang.Math.sqrt(_t12)))) : DoubleVector.broadcast(_SIMD_SPECIES, 0.0)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] nlerpShortest(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double alpha) {
        if (SimdSupport.USE_FMA) return nlerpShortest_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return nlerpShortest_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static double[] nlerpShortest_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double alpha) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, target, targetOffset);
        var _sv1 = DoubleVector.broadcast(_SIMD_SPECIES, alpha).fma((-java.lang.Math.fma(src[srcOffset + 3], target[targetOffset + 3], java.lang.Math.fma(src[srcOffset + 2], target[targetOffset + 2], java.lang.Math.fma(src[srcOffset], target[targetOffset], src[srcOffset + 1] * target[targetOffset + 1]))) > 0.0 ? _sv0.neg() : _sv0), DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(_SIMD_SPECIES, 1.0 - alpha)));
        DoubleVector _t25_v = _sv1.mul(_sv1);
        double _t25 = SimdMath.STRICT
            ? SimdMath.sumInOrder(_t25_v)
            : _t25_v.reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t25 != 0.0 ? _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, (1.0 / java.lang.Math.sqrt(_t25)))) : DoubleVector.broadcast(_SIMD_SPECIES, 0.0)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] nlerpShortest_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double alpha) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = DoubleVector.fromArray(_SIMD_SPECIES, target, targetOffset);
        var _sv1 = DoubleVector.broadcast(_SIMD_SPECIES, alpha).mul((-((src[srcOffset + 3]) * (target[targetOffset + 3]) + (((src[srcOffset + 2]) * (target[targetOffset + 2]) + (((src[srcOffset]) * (target[targetOffset]) + (src[srcOffset + 1] * target[targetOffset + 1])))))) > 0.0 ? _sv0.neg() : _sv0)).add(DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(_SIMD_SPECIES, 1.0 - alpha)));
        DoubleVector _t25_v = _sv1.mul(_sv1);
        double _t25 = SimdMath.STRICT
            ? SimdMath.sumInOrder(_t25_v)
            : _t25_v.reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t25 != 0.0 ? _sv1.mul(DoubleVector.broadcast(_SIMD_SPECIES, (1.0 / java.lang.Math.sqrt(_t25)))) : DoubleVector.broadcast(_SIMD_SPECIES, 0.0)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] slerp(double[] dest, int destOffset, double[] src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        if (SimdSupport.USE_FMA) return slerp_fma(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return slerp_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    public static double[] slerp_fma(double[] dest, int destOffset, double[] src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _t2 = src[srcOffset + 3] + targetW;
        double _t3 = src[srcOffset + 2] + targetZ;
        double _t4 = src[srcOffset] + targetX;
        double _t5 = src[srcOffset + 1] + targetY;
        double _t10 = Math.min(4.0, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5))));
        double _t14 = Math.min(alpha, 1.0 - alpha) * quatArcAngle(_t10);
        double _t17 = 2.0 * Math.sinWithCos(_t14);
        double _ct1 = java.lang.Math.sqrt((4.0 - _t10) * _t10);
        if (!(_ct1 > 2.0E-14 && _ct1 < Double.POSITIVE_INFINITY)) return DoubleQuatOpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        double _t20_inv = 1.0 / _ct1;
        double _t22 = _t17 * _t20_inv;
        double _t24 = Math.cosWithSin(_t14) - java.lang.Math.fma(0.5, _t10, -1.0) * _t17 * _t20_inv;
        double _w0, _w1;
        if (alpha < 0.5) {
            _w0 = _t24;
            _w1 = _t22;
        } else {
            _w0 = _t22;
            _w1 = _t24;
        }
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(DoubleVector.broadcast(SIMD_SPECIES, _w0), DoubleVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(DoubleVector.broadcast(SIMD_SPECIES, _w1))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] slerp_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _t2 = src[srcOffset + 3] + targetW;
        double _t3 = src[srcOffset + 2] + targetZ;
        double _t4 = src[srcOffset] + targetX;
        double _t5 = src[srcOffset + 1] + targetY;
        double _t10 = Math.min(4.0, ((_t2) * (_t2) + (((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5)))))));
        double _t14 = Math.min(alpha, 1.0 - alpha) * quatArcAngle(_t10);
        double _t17 = 2.0 * Math.sinWithCos(_t14);
        double _ct3 = java.lang.Math.sqrt((4.0 - _t10) * _t10);
        if (!(_ct3 > 2.0E-14 && _ct3 < Double.POSITIVE_INFINITY)) return DoubleQuatOpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        double _t20_inv = 1.0 / _ct3;
        double _t22 = _t17 * _t20_inv;
        double _t24 = Math.cosWithSin(_t14) - ((0.5) * (_t10) - (1.0)) * _t17 * _t20_inv;
        double _w0, _w1;
        if (alpha < 0.5) {
            _w0 = _t24;
            _w1 = _t22;
        } else {
            _w0 = _t22;
            _w1 = _t24;
        }
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, _w0)).add(DoubleVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(DoubleVector.broadcast(SIMD_SPECIES, _w1))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] slerp_degenerate(double[] dest, int destOffset, double[] src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        if (SimdSupport.USE_FMA) return slerp_degenerate_fma(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return slerp_degenerate_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    public static double[] slerp_degenerate_fma(double[] dest, int destOffset, double[] src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _t1 = src[srcOffset + 3] + targetW;
        double _t2 = src[srcOffset + 2] + targetZ;
        double _t3 = src[srcOffset] + targetX;
        double _t4 = src[srcOffset + 1] + targetY;
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = Math.min(4.0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, _t4 * _t4))));
        double _w0, _w1;
        if (_t11 > 4.0 - _t11) {
            _w0 = 1.0 - alpha;
            _w1 = alpha;
        } else {
            _w0 = _t5;
            _w1 = 1.0 - _t5;
        }
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(DoubleVector.broadcast(SIMD_SPECIES, _w0), DoubleVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(DoubleVector.broadcast(SIMD_SPECIES, _w1))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] slerp_degenerate_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _t1 = src[srcOffset + 3] + targetW;
        double _t2 = src[srcOffset + 2] + targetZ;
        double _t3 = src[srcOffset] + targetX;
        double _t4 = src[srcOffset + 1] + targetY;
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = Math.min(4.0, ((_t1) * (_t1) + (((_t2) * (_t2) + (((_t3) * (_t3) + (_t4 * _t4)))))));
        double _w0, _w1;
        if (_t11 > 4.0 - _t11) {
            _w0 = 1.0 - alpha;
            _w1 = alpha;
        } else {
            _w0 = _t5;
            _w1 = 1.0 - _t5;
        }
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, _w0)).add(DoubleVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(DoubleVector.broadcast(SIMD_SPECIES, _w1))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] slerp(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double alpha) {
        if (SimdSupport.USE_FMA) return slerp_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerp_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static double[] slerp_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double alpha) {
        double _t2 = src[srcOffset + 3] + target[targetOffset + 3];
        double _t3 = src[srcOffset + 2] + target[targetOffset + 2];
        double _t4 = src[srcOffset] + target[targetOffset];
        double _t5 = src[srcOffset + 1] + target[targetOffset + 1];
        double _t10 = Math.min(4.0, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5))));
        double _t14 = Math.min(alpha, 1.0 - alpha) * quatArcAngle(_t10);
        double _t17 = 2.0 * Math.sinWithCos(_t14);
        double _ct1 = java.lang.Math.sqrt((4.0 - _t10) * _t10);
        if (!(_ct1 > 2.0E-14 && _ct1 < Double.POSITIVE_INFINITY)) return DoubleQuatOpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        double _t20_inv = 1.0 / _ct1;
        double _t22 = _t17 * _t20_inv;
        double _t24 = Math.cosWithSin(_t14) - java.lang.Math.fma(0.5, _t10, -1.0) * _t17 * _t20_inv;
        double _w0, _w1;
        if (alpha < 0.5) {
            _w0 = _t24;
            _w1 = _t22;
        } else {
            _w0 = _t22;
            _w1 = _t24;
        }
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(DoubleVector.broadcast(SIMD_SPECIES, _w0), DoubleVector.fromArray(SIMD_SPECIES, target, targetOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, _w1))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] slerp_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double alpha) {
        double _t2 = src[srcOffset + 3] + target[targetOffset + 3];
        double _t3 = src[srcOffset + 2] + target[targetOffset + 2];
        double _t4 = src[srcOffset] + target[targetOffset];
        double _t5 = src[srcOffset + 1] + target[targetOffset + 1];
        double _t10 = Math.min(4.0, ((_t2) * (_t2) + (((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5)))))));
        double _t14 = Math.min(alpha, 1.0 - alpha) * quatArcAngle(_t10);
        double _t17 = 2.0 * Math.sinWithCos(_t14);
        double _ct3 = java.lang.Math.sqrt((4.0 - _t10) * _t10);
        if (!(_ct3 > 2.0E-14 && _ct3 < Double.POSITIVE_INFINITY)) return DoubleQuatOpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        double _t20_inv = 1.0 / _ct3;
        double _t22 = _t17 * _t20_inv;
        double _t24 = Math.cosWithSin(_t14) - ((0.5) * (_t10) - (1.0)) * _t17 * _t20_inv;
        double _w0, _w1;
        if (alpha < 0.5) {
            _w0 = _t24;
            _w1 = _t22;
        } else {
            _w0 = _t22;
            _w1 = _t24;
        }
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, _w0)).add(DoubleVector.fromArray(SIMD_SPECIES, target, targetOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, _w1))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] slerp_degenerate(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double alpha) {
        if (SimdSupport.USE_FMA) return slerp_degenerate_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerp_degenerate_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static double[] slerp_degenerate_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double alpha) {
        double _t1 = src[srcOffset + 3] + target[targetOffset + 3];
        double _t2 = src[srcOffset + 2] + target[targetOffset + 2];
        double _t3 = src[srcOffset] + target[targetOffset];
        double _t4 = src[srcOffset + 1] + target[targetOffset + 1];
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = Math.min(4.0, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, _t4 * _t4))));
        double _w0, _w1;
        if (_t11 > 4.0 - _t11) {
            _w0 = 1.0 - alpha;
            _w1 = alpha;
        } else {
            _w0 = _t5;
            _w1 = 1.0 - _t5;
        }
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(DoubleVector.broadcast(SIMD_SPECIES, _w0), DoubleVector.fromArray(SIMD_SPECIES, target, targetOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, _w1))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] slerp_degenerate_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double alpha) {
        double _t1 = src[srcOffset + 3] + target[targetOffset + 3];
        double _t2 = src[srcOffset + 2] + target[targetOffset + 2];
        double _t3 = src[srcOffset] + target[targetOffset];
        double _t4 = src[srcOffset + 1] + target[targetOffset + 1];
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = Math.min(4.0, ((_t1) * (_t1) + (((_t2) * (_t2) + (((_t3) * (_t3) + (_t4 * _t4)))))));
        double _w0, _w1;
        if (_t11 > 4.0 - _t11) {
            _w0 = 1.0 - alpha;
            _w1 = alpha;
        } else {
            _w0 = _t5;
            _w1 = 1.0 - _t5;
        }
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, _w0)).add(DoubleVector.fromArray(SIMD_SPECIES, target, targetOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, _w1))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] addScaled(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double weight) {
        if (SimdSupport.USE_FMA) return addScaled_fma(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return addScaled_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    public static double[] addScaled_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double weight) {
        DoubleVector.broadcast(SIMD_SPECIES, weight).fma(DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] addScaled_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double weight) {
        DoubleVector.broadcast(SIMD_SPECIES, weight).mul(DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset)).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] calculateW(double[] dest, int destOffset, double[] src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfx = src[srcOffset];
            double _selfy = src[srcOffset + 1];
            double _selfz = src[srcOffset + 2];
            DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(3, java.lang.Math.sqrt(Math.max(0.0, java.lang.Math.fma(-_selfx, _selfx, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(-_selfz, _selfz, 1.0)))))).intoArray(dest, destOffset);
            return dest;
        } else {
            double _selfx = src[srcOffset];
            double _selfy = src[srcOffset + 1];
            double _selfz = src[srcOffset + 2];
            DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(3, java.lang.Math.sqrt(Math.max(0.0, ((((((1.0) - (_selfz) * (_selfz))) - (_selfy) * (_selfy))) - (_selfx) * (_selfx))))).intoArray(dest, destOffset);
            return dest;
        }
    }

    public static double[] exp(double[] dest, int destOffset, double[] src, int srcOffset) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfz = src[srcOffset + 2];
            double _selfx = src[srcOffset];
            double _selfy = src[srcOffset + 1];
            double _t2 = Math.min(Math.exp(src[srcOffset + 3]), 1.7976931348623157E308);
            double _t4 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
            double _t5 = java.lang.Math.sqrt(_t4);
            (_t4 > 0.0 ? DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).withLane(3, Math.cosWithSin(_t5)).mul(DoubleVector.broadcast(_SIMD_SPECIES, _t2 * (Math.sinWithCos(_t5) * (1.0 / java.lang.Math.sqrt(_t4)))).withLane(3, _t2)) : DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, Math.cosWithSin(_t5) * _t2)).intoArray(dest, destOffset);
            return dest;
        } else {
            double _selfz = src[srcOffset + 2];
            double _selfx = src[srcOffset];
            double _selfy = src[srcOffset + 1];
            double _t2 = Math.min(Math.exp(src[srcOffset + 3]), 1.7976931348623157E308);
            double _t4 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
            double _t5 = java.lang.Math.sqrt(_t4);
            (_t4 > 0.0 ? DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).withLane(3, Math.cosWithSin(_t5)).mul(DoubleVector.broadcast(_SIMD_SPECIES, _t2 * (Math.sinWithCos(_t5) * (1.0 / java.lang.Math.sqrt(_t4)))).withLane(3, _t2)) : DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, Math.cosWithSin(_t5) * _t2)).intoArray(dest, destOffset);
            return dest;
        }
    }

    public static double[] log(double[] dest, int destOffset, double[] src, int srcOffset) {
        VectorSpecies<Double> _SIMD_SPECIES = SIMD_SPECIES;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            double _selfw = src[srcOffset + 3];
            double _selfz = src[srcOffset + 2];
            double _selfx = src[srcOffset];
            double _selfy = src[srcOffset + 1];
            double _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
            (_t2 > 0.0 ? DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(_SIMD_SPECIES, Math.atan2(java.lang.Math.sqrt(_t2), _selfw) * (1.0 / java.lang.Math.sqrt(_t2)))).withLane(3, Math.log(java.lang.Math.sqrt(java.lang.Math.fma(_selfw, _selfw, _t2)))) : DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, Math.log(java.lang.Math.sqrt(java.lang.Math.fma(_selfw, _selfw, _t2))))).intoArray(dest, destOffset);
            return dest;
        } else {
            double _selfw = src[srcOffset + 3];
            double _selfz = src[srcOffset + 2];
            double _selfx = src[srcOffset];
            double _selfy = src[srcOffset + 1];
            double _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
            (_t2 > 0.0 ? DoubleVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(_SIMD_SPECIES, Math.atan2(java.lang.Math.sqrt(_t2), _selfw) * (1.0 / java.lang.Math.sqrt(_t2)))).withLane(3, Math.log(java.lang.Math.sqrt(((_selfw) * (_selfw) + (_t2))))) : DoubleVector.broadcast(_SIMD_SPECIES, 0.0).withLane(3, Math.log(java.lang.Math.sqrt(((_selfw) * (_selfw) + (_t2)))))).intoArray(dest, destOffset);
            return dest;
        }
    }

    public static double[] normalize(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        DoubleVector _t3_v = _sv0.mul(_sv0);
        double _t3 = SimdMath.STRICT
            ? SimdMath.sumInOrder(_t3_v)
            : _t3_v.reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t3 != 0.0 ? _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, (1.0 / java.lang.Math.sqrt(_t3)))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] makeRotationAxis(double[] dest, int destOffset, double angle, double axisX, double axisY, double axisZ) {
        if (SimdSupport.USE_FMA) return makeRotationAxis_fma(dest, destOffset, angle, axisX, axisY, axisZ);
        return makeRotationAxis_mulAdd(dest, destOffset, angle, axisX, axisY, axisZ);
    }

    public static double[] makeRotationAxis_fma(double[] dest, int destOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = 0.5 * angle;
        DoubleVector.zero(SIMD_SPECIES).withLane(0, axisX).withLane(1, axisY).withLane(2, axisZ).fma(DoubleVector.broadcast(SIMD_SPECIES, Math.sinWithCos(_t0)), DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, Math.cosWithSin(_t0))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] makeRotationAxis_mulAdd(double[] dest, int destOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = 0.5 * angle;
        DoubleVector.zero(SIMD_SPECIES).withLane(0, axisX).withLane(1, axisY).withLane(2, axisZ).mul(DoubleVector.broadcast(SIMD_SPECIES, Math.sinWithCos(_t0))).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, Math.cosWithSin(_t0))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] makeRotationAxis(double[] dest, int destOffset, double[] axis, int axisOffset, double angle) {
        if (SimdSupport.USE_FMA) return makeRotationAxis_fma(dest, destOffset, axis, axisOffset, angle);
        return makeRotationAxis_mulAdd(dest, destOffset, axis, axisOffset, angle);
    }

    public static double[] makeRotationAxis_fma(double[] dest, int destOffset, double[] axis, int axisOffset, double angle) {
        double _t0 = 0.5 * angle;
        DoubleVector.zero(SIMD_SPECIES).withLane(0, axis[axisOffset]).withLane(1, axis[axisOffset + 1]).withLane(2, axis[axisOffset + 2]).fma(DoubleVector.broadcast(SIMD_SPECIES, Math.sinWithCos(_t0)), DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, Math.cosWithSin(_t0))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] makeRotationAxis_mulAdd(double[] dest, int destOffset, double[] axis, int axisOffset, double angle) {
        double _t0 = 0.5 * angle;
        DoubleVector.zero(SIMD_SPECIES).withLane(0, axis[axisOffset]).withLane(1, axis[axisOffset + 1]).withLane(2, axis[axisOffset + 2]).mul(DoubleVector.broadcast(SIMD_SPECIES, Math.sinWithCos(_t0))).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, Math.cosWithSin(_t0))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] makeRotationYXZ(double[] dest, int destOffset, double angleY, double angleX, double angleZ) {
        if (SimdSupport.USE_FMA) return makeRotationYXZ_fma(dest, destOffset, angleY, angleX, angleZ);
        return makeRotationYXZ_mulAdd(dest, destOffset, angleY, angleX, angleZ);
    }

    public static double[] makeRotationYXZ_fma(double[] dest, int destOffset, double angleY, double angleX, double angleZ) {
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
        DoubleVector.zero(SIMD_SPECIES).withLane(0, _t9).withLane(1, _t10).withLane(3, _t11).fma(DoubleVector.broadcast(SIMD_SPECIES, _t5), DoubleVector.broadcast(SIMD_SPECIES, _t10).withLane(3, _t12).mul(DoubleVector.broadcast(SIMD_SPECIES, _t8)).withLane(1, -(_t9 * _t8))).withLane(2, _t11 * _t8 - _t12 * _t5).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] makeRotationYXZ_mulAdd(double[] dest, int destOffset, double angleY, double angleX, double angleZ) {
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
        DoubleVector.zero(SIMD_SPECIES).withLane(0, _t9).withLane(1, _t10).withLane(3, _t11).mul(DoubleVector.broadcast(SIMD_SPECIES, _t5)).add(DoubleVector.broadcast(SIMD_SPECIES, _t10).withLane(3, _t12).mul(DoubleVector.broadcast(SIMD_SPECIES, _t8)).withLane(1, -(_t9 * _t8))).withLane(2, _t11 * _t8 - _t12 * _t5).intoArray(dest, destOffset);
        return dest;
    }

    private static void copyArrArr(double[] dest, int destOffset, double[] src, int srcOffset, int n) {
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length())
            DoubleVector.fromArray(_sp, src, srcOffset + _i).intoArray(dest, destOffset + _i);
        for (; _i <= n - 4; _i += 4)
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset + _i).intoArray(dest, destOffset + _i);
        for (; _i < n; _i++)
            dest[destOffset + _i] = src[srcOffset + _i];
    }

    private static void copyArrArr_one(double[] dest, int destOffset, double[] src, int srcOffset) {
        if (PREFERRED_LANES >= 4) {
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset).intoArray(dest, destOffset);
        } else {
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset).intoArray(dest, destOffset);
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 2).intoArray(dest, destOffset + 2);
        }
    }

    public static double[] copy(double[] dest, int destOffset, double[] src, int srcOffset) {
        copyArrArr_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        copyArrArr(dest, destOffset, src, srcOffset, count * 4);
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            double[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr_one(dest, destOffset, _srcArr, _srcOff);
        } else {
            for (int _i = 0; _i < 4; _i++)
                dest[destOffset + _i] = src.get(srcOffset + _i);
        }
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (src.hasArray() && srcOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && srcOffset <= src.limit() - (count > 536870911 ? -1 : count * 4)) {
            double[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr(dest, destOffset, _srcArr, _srcOff, count * 4);
        } else {
            for (int _i = 0; _i < count * 4; _i++)
                dest[destOffset + _i] = src.get(srcOffset + _i);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, double[] src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr_one(_destArr, _destOff, src, srcOffset);
        } else {
            for (int _i = 0; _i < 4; _i++)
                dest.put(destOffset + _i, src[srcOffset + _i]);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && destOffset <= dest.limit() - (count > 536870911 ? -1 : count * 4)) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr(_destArr, _destOff, src, srcOffset, count * 4);
        } else {
            for (int _i = 0; _i < count * 4; _i++)
                dest.put(destOffset + _i, src[srcOffset + _i]);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr_one(_destArr, _destOff, _srcArr, _srcOff);
            } else {
                for (int _i = 0; _i < 4; _i++)
                    _destArr[_destOff + _i] = src.get(srcOffset + _i);
            }
        } else {
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
                double[] _srcArr = src.array();
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

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && destOffset <= dest.limit() - (count > 536870911 ? -1 : count * 4)) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && srcOffset <= src.limit() - (count > 536870911 ? -1 : count * 4)) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr(_destArr, _destOff, _srcArr, _srcOff, count * 4);
            } else {
                for (int _i = 0; _i < count * 4; _i++)
                    _destArr[_destOff + _i] = src.get(srcOffset + _i);
            }
        } else {
            if (src.hasArray() && srcOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && srcOffset <= src.limit() - (count > 536870911 ? -1 : count * 4)) {
                double[] _srcArr = src.array();
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
    private static double quatArcAngle(double s) {
        double d = 4.0 - s;
        return s > d ? 2.0 * Math.asin(0.5 * java.lang.Math.sqrt(d)) : Math.PI - 2.0 * Math.asin(0.5 * java.lang.Math.sqrt(s));
    }
}
