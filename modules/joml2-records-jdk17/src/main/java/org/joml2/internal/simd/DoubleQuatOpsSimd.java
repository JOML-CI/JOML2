// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.simd;

import jdk.incubator.vector.*;
import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

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
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, alpha).fma(DoubleVector.fromArray(SIMD_SPECIES, target, targetOffset).sub(_sv0), _sv0);
        double _t11 = _sv1.mul(_sv1).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t11  !=  0.0 ? _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, (1.0 / java.lang.Math.sqrt(_t11)))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] nlerp_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double alpha) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, alpha).mul(DoubleVector.fromArray(SIMD_SPECIES, target, targetOffset).sub(_sv0)).add(_sv0);
        double _t11 = _sv1.mul(_sv1).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t11  !=  0.0 ? _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, (1.0 / java.lang.Math.sqrt(_t11)))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] nlerpShortest(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double alpha) {
        if (SimdSupport.USE_FMA) return nlerpShortest_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return nlerpShortest_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static double[] nlerpShortest_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double alpha) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, target, targetOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, alpha).fma((-Math.fma(src[srcOffset + 3], target[targetOffset + 3], Math.fma(src[srcOffset + 2], target[targetOffset + 2], Math.fma(src[srcOffset], target[targetOffset], src[srcOffset + 1] * target[targetOffset + 1])))  >  0.0 ? _sv0.neg() : _sv0).sub(_sv1), _sv1);
        double _t24 = _sv2.mul(_sv2).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t24  !=  0.0 ? _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, (1.0 / java.lang.Math.sqrt(_t24)))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] nlerpShortest_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double alpha) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, target, targetOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, alpha).mul((-Math.fma(src[srcOffset + 3], target[targetOffset + 3], Math.fma(src[srcOffset + 2], target[targetOffset + 2], Math.fma(src[srcOffset], target[targetOffset], src[srcOffset + 1] * target[targetOffset + 1])))  >  0.0 ? _sv0.neg() : _sv0).sub(_sv1)).add(_sv1);
        double _t24 = _sv2.mul(_sv2).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t24  !=  0.0 ? _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, (1.0 / java.lang.Math.sqrt(_t24)))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] slerp(double[] dest, int destOffset, double[] src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        if (SimdSupport.USE_FMA) return slerp_fma(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return slerp_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    public static double[] slerp_fma(double[] dest, int destOffset, double[] src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _t0 = 1.0 - alpha;
        double _t1 = src[srcOffset + 3] + targetW;
        double _t2 = src[srcOffset + 2] + targetZ;
        double _t3 = src[srcOffset] + targetX;
        double _t4 = src[srcOffset + 1] + targetY;
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = java.lang.Math.min(4.0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        double _t12 = quatArcAngle(_t11);
        double _t13 = 4.0 - _t11;
        double _t19 = java.lang.Math.sqrt(_t13 * _t11);
        double _t21 = 2.0 / _t19;
        double _w0, _w1;
        if (_t19 > 2.0E-14) {
            _w0 = _t21 * Math.sin(_t0 * _t12);
            _w1 = _t21 * Math.sin(alpha * _t12);
        } else {
            if (_t11 > _t13) {
                _w0 = _t0;
                _w1 = alpha;
            } else {
                _w0 = _t5;
                _w1 = 1.0 - _t5;
            }
        }
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(DoubleVector.broadcast(SIMD_SPECIES, _w0), DoubleVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(DoubleVector.broadcast(SIMD_SPECIES, _w1))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] slerp_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _t0 = 1.0 - alpha;
        double _t1 = src[srcOffset + 3] + targetW;
        double _t2 = src[srcOffset + 2] + targetZ;
        double _t3 = src[srcOffset] + targetX;
        double _t4 = src[srcOffset + 1] + targetY;
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = java.lang.Math.min(4.0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        double _t12 = quatArcAngle(_t11);
        double _t13 = 4.0 - _t11;
        double _t19 = java.lang.Math.sqrt(_t13 * _t11);
        double _t21 = 2.0 / _t19;
        double _w0, _w1;
        if (_t19 > 2.0E-14) {
            _w0 = _t21 * Math.sin(_t0 * _t12);
            _w1 = _t21 * Math.sin(alpha * _t12);
        } else {
            if (_t11 > _t13) {
                _w0 = _t0;
                _w1 = alpha;
            } else {
                _w0 = _t5;
                _w1 = 1.0 - _t5;
            }
        }
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, _w0)).add(DoubleVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(DoubleVector.broadcast(SIMD_SPECIES, _w1))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] slerp(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double alpha) {
        if (SimdSupport.USE_FMA) return slerp_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerp_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static double[] slerp_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double alpha) {
        double _t0 = 1.0 - alpha;
        double _t1 = src[srcOffset + 3] + target[targetOffset + 3];
        double _t2 = src[srcOffset + 2] + target[targetOffset + 2];
        double _t3 = src[srcOffset] + target[targetOffset];
        double _t4 = src[srcOffset + 1] + target[targetOffset + 1];
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = java.lang.Math.min(4.0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        double _t12 = quatArcAngle(_t11);
        double _t13 = 4.0 - _t11;
        double _t19 = java.lang.Math.sqrt(_t13 * _t11);
        double _t21 = 2.0 / _t19;
        double _w0, _w1;
        if (_t19 > 2.0E-14) {
            _w0 = _t21 * Math.sin(_t0 * _t12);
            _w1 = _t21 * Math.sin(alpha * _t12);
        } else {
            if (_t11 > _t13) {
                _w0 = _t0;
                _w1 = alpha;
            } else {
                _w0 = _t5;
                _w1 = 1.0 - _t5;
            }
        }
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(DoubleVector.broadcast(SIMD_SPECIES, _w0), DoubleVector.fromArray(SIMD_SPECIES, target, targetOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, _w1))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] slerp_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double alpha) {
        double _t0 = 1.0 - alpha;
        double _t1 = src[srcOffset + 3] + target[targetOffset + 3];
        double _t2 = src[srcOffset + 2] + target[targetOffset + 2];
        double _t3 = src[srcOffset] + target[targetOffset];
        double _t4 = src[srcOffset + 1] + target[targetOffset + 1];
        double _t5 = alpha < 0.5 ? 1.0 : 0.0;
        double _t11 = java.lang.Math.min(4.0, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        double _t12 = quatArcAngle(_t11);
        double _t13 = 4.0 - _t11;
        double _t19 = java.lang.Math.sqrt(_t13 * _t11);
        double _t21 = 2.0 / _t19;
        double _w0, _w1;
        if (_t19 > 2.0E-14) {
            _w0 = _t21 * Math.sin(_t0 * _t12);
            _w1 = _t21 * Math.sin(alpha * _t12);
        } else {
            if (_t11 > _t13) {
                _w0 = _t0;
                _w1 = alpha;
            } else {
                _w0 = _t5;
                _w1 = 1.0 - _t5;
            }
        }
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, _w0)).add(DoubleVector.fromArray(SIMD_SPECIES, target, targetOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, _w1))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] slerpShortest(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double alpha) {
        if (SimdSupport.USE_FMA) return slerpShortest_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerpShortest_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static double[] slerpShortest_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double alpha) {
        double _t0 = 1.0 - alpha;
        double _t12 = Math.fma(src[srcOffset + 3], target[targetOffset + 3], Math.fma(src[srcOffset + 2], target[targetOffset + 2], Math.fma(src[srcOffset], target[targetOffset], src[srcOffset + 1] * target[targetOffset + 1])));
        double _t16 = Math.acos(java.lang.Math.min(1.0, java.lang.Math.abs(_t12)));
        double _t17 = Math.sin(_t16);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, target, targetOffset);
        var _sv2 = (-_t12  >  0.0 ? _sv1.neg() : _sv1);
        var _sv3 = (_t17  >  0.0 ? _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.sin(_t0 * _t16)), DoubleVector.broadcast(SIMD_SPECIES, Math.sin(alpha * _t16)).mul(_sv2)).mul(DoubleVector.broadcast(SIMD_SPECIES, 1.0 / _t17)) : DoubleVector.broadcast(SIMD_SPECIES, alpha).fma(_sv2, _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t0))));
        double _t49 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t49  !=  0.0 ? _sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, (1.0 / java.lang.Math.sqrt(_t49)))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] slerpShortest_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double alpha) {
        double _t0 = 1.0 - alpha;
        double _t12 = Math.fma(src[srcOffset + 3], target[targetOffset + 3], Math.fma(src[srcOffset + 2], target[targetOffset + 2], Math.fma(src[srcOffset], target[targetOffset], src[srcOffset + 1] * target[targetOffset + 1])));
        double _t16 = Math.acos(java.lang.Math.min(1.0, java.lang.Math.abs(_t12)));
        double _t17 = Math.sin(_t16);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, target, targetOffset);
        var _sv2 = (-_t12  >  0.0 ? _sv1.neg() : _sv1);
        var _sv3 = (_t17  >  0.0 ? _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.sin(_t0 * _t16))).add(DoubleVector.broadcast(SIMD_SPECIES, Math.sin(alpha * _t16)).mul(_sv2)).mul(DoubleVector.broadcast(SIMD_SPECIES, 1.0 / _t17)) : DoubleVector.broadcast(SIMD_SPECIES, alpha).mul(_sv2).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t0))));
        double _t49 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t49  !=  0.0 ? _sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, (1.0 / java.lang.Math.sqrt(_t49)))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0)).intoArray(dest, destOffset);
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
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(3, java.lang.Math.sqrt(java.lang.Math.max(0.0, Math.fma(-_selfx, _selfx, Math.fma(-_selfy, _selfy, Math.fma(-_selfz, _selfz, 1.0)))))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] exp(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfz = src[srcOffset + 2];
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _t2 = java.lang.Math.min(Math.exp(src[srcOffset + 3]), 1.7976931348623157E308);
        double _t4 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        double _t5 = java.lang.Math.sqrt(_t4);
        double _t7 = Math.sin(_t5);
        (_t4  >  0.0 ? DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(3, Math.cosFromSin(_t7, _t5)).mul(DoubleVector.broadcast(SIMD_SPECIES, _t2 * (_t7 * (1.0 / java.lang.Math.sqrt(_t4)))).withLane(3, _t2)) : DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, Math.cosFromSin(_t7, _t5) * _t2)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] log(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfw = src[srcOffset + 3];
        double _selfz = src[srcOffset + 2];
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        (_t2  >  0.0 ? DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, Math.atan2(java.lang.Math.sqrt(_t2), _selfw) * (1.0 / java.lang.Math.sqrt(_t2)))).withLane(3, Math.log(java.lang.Math.sqrt(Math.fma(_selfw, _selfw, _t2)))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, Math.log(java.lang.Math.sqrt(Math.fma(_selfw, _selfw, _t2))))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] normalize(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        double _t3 = _sv0.mul(_sv0).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t3  !=  0.0 ? _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, (1.0 / java.lang.Math.sqrt(_t3)))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0)).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] rotateTowards(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double step) {
        if (SimdSupport.USE_FMA) return rotateTowards_fma(dest, destOffset, src, srcOffset, target, targetOffset, step);
        return rotateTowards_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, step);
    }

    public static double[] rotateTowards_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double step) {
        double _selfw = src[srcOffset + 3];
        double _targetw = target[targetOffset + 3];
        double _selfz = src[srcOffset + 2];
        double _targetz = target[targetOffset + 2];
        double _selfx = src[srcOffset];
        double _targetx = target[targetOffset];
        double _selfy = src[srcOffset + 1];
        double _targety = target[targetOffset + 1];
        double _t7 = Math.fma(_selfw, _targetw, Math.fma(_selfz, _targetz, Math.fma(_selfx, _targetx, _selfy * _targety)));
        double _t9 = -_t7;
        double _t13, _t14, _t15, _t16;
        if (_t9 > 0.0) {
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
        double _t17 = _selfw - _t13;
        double _t18 = _selfz - _t14;
        double _t19 = _selfx - _t15;
        double _t20 = _selfy - _t16;
        double _t21 = _selfw + _t13;
        double _t22 = _selfz + _t14;
        double _t23 = _selfx + _t15;
        double _t24 = _selfy + _t16;
        return rotateTowards_fma_sd1bbab73_1(dest, destOffset, src, srcOffset, target, targetOffset, step, _t9, Math.acos(java.lang.Math.min(1.0, java.lang.Math.abs(_t7))), 4.0 * Math.atan2(java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))), java.lang.Math.sqrt(Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24))))));
    }

    /** Piece 2 of {@code rotateTowards_fma}, split to fit the inline budget; reached only through it. */
    private static double[] rotateTowards_fma_sd1bbab73_1(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double step, double _t9, double _t11, double _t36) {
        double _t39 = _t36 > 0.0 ? java.lang.Math.min(1.0, step / _t36) : 0.0;
        rotateTowards_fma_sbf2a1513_v(dest, destOffset, src, srcOffset, target, targetOffset, _t9, _t11, Math.sin(_t11), _t39, 1.0 - _t39);
        return dest;
    }

    private static void rotateTowards_fma_sbf2a1513_v(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double _t9, double _t11, double _t12, double _t39, double _t40) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, target, targetOffset);
        var _sv2 = (_t9  >  0.0 ? _sv1.neg() : _sv1);
        var _sv3 = (_t12  >  0.0 ? _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.sin(_t40 * _t11)), DoubleVector.broadcast(SIMD_SPECIES, Math.sin(_t11 * _t39)).mul(_sv2)).mul(DoubleVector.broadcast(SIMD_SPECIES, 1.0 / _t12)) : _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t40), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t39))));
        double _t72 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t72  !=  0.0 ? _sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, (1.0 / java.lang.Math.sqrt(_t72)))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0)).intoArray(dest, destOffset);
    }

    public static double[] rotateTowards_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double step) {
        double _selfw = src[srcOffset + 3];
        double _targetw = target[targetOffset + 3];
        double _selfz = src[srcOffset + 2];
        double _targetz = target[targetOffset + 2];
        double _selfx = src[srcOffset];
        double _targetx = target[targetOffset];
        double _selfy = src[srcOffset + 1];
        double _targety = target[targetOffset + 1];
        double _t7 = Math.fma(_selfw, _targetw, Math.fma(_selfz, _targetz, Math.fma(_selfx, _targetx, _selfy * _targety)));
        double _t9 = -_t7;
        double _t13, _t14, _t15, _t16;
        if (_t9 > 0.0) {
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
        double _t17 = _selfw - _t13;
        double _t18 = _selfz - _t14;
        double _t19 = _selfx - _t15;
        double _t20 = _selfy - _t16;
        double _t21 = _selfw + _t13;
        double _t22 = _selfz + _t14;
        double _t23 = _selfx + _t15;
        double _t24 = _selfy + _t16;
        return rotateTowards_mulAdd_s10261c56_1(dest, destOffset, src, srcOffset, target, targetOffset, step, _t9, Math.acos(java.lang.Math.min(1.0, java.lang.Math.abs(_t7))), 4.0 * Math.atan2(java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))), java.lang.Math.sqrt(Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24))))));
    }

    /** Piece 2 of {@code rotateTowards_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] rotateTowards_mulAdd_s10261c56_1(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double step, double _t9, double _t11, double _t36) {
        double _t39 = _t36 > 0.0 ? java.lang.Math.min(1.0, step / _t36) : 0.0;
        rotateTowards_mulAdd_sa0936112_v(dest, destOffset, src, srcOffset, target, targetOffset, _t9, _t11, Math.sin(_t11), _t39, 1.0 - _t39);
        return dest;
    }

    private static void rotateTowards_mulAdd_sa0936112_v(double[] dest, int destOffset, double[] src, int srcOffset, double[] target, int targetOffset, double _t9, double _t11, double _t12, double _t39, double _t40) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, target, targetOffset);
        var _sv2 = (_t9  >  0.0 ? _sv1.neg() : _sv1);
        var _sv3 = (_t12  >  0.0 ? _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.sin(_t40 * _t11))).add(DoubleVector.broadcast(SIMD_SPECIES, Math.sin(_t11 * _t39)).mul(_sv2)).mul(DoubleVector.broadcast(SIMD_SPECIES, 1.0 / _t12)) : _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t40)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t39))));
        double _t72 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t72  !=  0.0 ? _sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, (1.0 / java.lang.Math.sqrt(_t72)))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0)).intoArray(dest, destOffset);
    }

    public static double[] makeRotationAxis(double[] dest, int destOffset, double angle, double axisX, double axisY, double axisZ) {
        if (SimdSupport.USE_FMA) return makeRotationAxis_fma(dest, destOffset, angle, axisX, axisY, axisZ);
        return makeRotationAxis_mulAdd(dest, destOffset, angle, axisX, axisY, axisZ);
    }

    public static double[] makeRotationAxis_fma(double[] dest, int destOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        DoubleVector.zero(SIMD_SPECIES).withLane(0, axisX).withLane(1, axisY).withLane(2, axisZ).fma(DoubleVector.broadcast(SIMD_SPECIES, _t1), DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, Math.cosFromSin(_t1, _t0))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] makeRotationAxis_mulAdd(double[] dest, int destOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        DoubleVector.zero(SIMD_SPECIES).withLane(0, axisX).withLane(1, axisY).withLane(2, axisZ).mul(DoubleVector.broadcast(SIMD_SPECIES, _t1)).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, Math.cosFromSin(_t1, _t0))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] makeRotationAxis(double[] dest, int destOffset, double[] axis, int axisOffset, double angle) {
        if (SimdSupport.USE_FMA) return makeRotationAxis_fma(dest, destOffset, axis, axisOffset, angle);
        return makeRotationAxis_mulAdd(dest, destOffset, axis, axisOffset, angle);
    }

    public static double[] makeRotationAxis_fma(double[] dest, int destOffset, double[] axis, int axisOffset, double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        DoubleVector.zero(SIMD_SPECIES).withLane(0, axis[axisOffset]).withLane(1, axis[axisOffset + 1]).withLane(2, axis[axisOffset + 2]).fma(DoubleVector.broadcast(SIMD_SPECIES, _t1), DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, Math.cosFromSin(_t1, _t0))).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] makeRotationAxis_mulAdd(double[] dest, int destOffset, double[] axis, int axisOffset, double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        DoubleVector.zero(SIMD_SPECIES).withLane(0, axis[axisOffset]).withLane(1, axis[axisOffset + 1]).withLane(2, axis[axisOffset + 2]).mul(DoubleVector.broadcast(SIMD_SPECIES, _t1)).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, Math.cosFromSin(_t1, _t0))).intoArray(dest, destOffset);
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
        DoubleVector.zero(SIMD_SPECIES).withLane(0, _t11).withLane(2, _t12).withLane(3, _t9).fma(DoubleVector.broadcast(SIMD_SPECIES, _t5), DoubleVector.broadcast(SIMD_SPECIES, _t10).withLane(3, _t12).mul(DoubleVector.broadcast(SIMD_SPECIES, _t7)).withLane(2, -(_t9 * _t7))).withLane(1, _t11 * _t7 - _t10 * _t5).intoArray(dest, destOffset);
        return dest;
    }

    public static double[] makeRotationYXZ_mulAdd(double[] dest, int destOffset, double angleY, double angleX, double angleZ) {
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
        DoubleVector.zero(SIMD_SPECIES).withLane(0, _t11).withLane(2, _t12).withLane(3, _t9).mul(DoubleVector.broadcast(SIMD_SPECIES, _t5)).add(DoubleVector.broadcast(SIMD_SPECIES, _t10).withLane(3, _t12).mul(DoubleVector.broadcast(SIMD_SPECIES, _t7)).withLane(2, -(_t9 * _t7))).withLane(1, _t11 * _t7 - _t10 * _t5).intoArray(dest, destOffset);
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
        }
        else {
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
