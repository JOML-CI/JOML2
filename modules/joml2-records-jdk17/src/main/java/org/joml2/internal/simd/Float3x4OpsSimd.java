// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.simd;

import jdk.incubator.vector.*;
import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Vector-API isolation cell for {@link Float3x4Ops}: every
 * {@code jdk.incubator.vector} reference of the Ops family lives in this class,
 * which is loaded and initialized only behind {@code SimdSupport.VECTOR_API}
 * guards - {@code Float3x4Ops} and its kernel siblings link
 * and run without the incubator module. Not public API.
 */
public final class Float3x4OpsSimd {
    private Float3x4OpsSimd() {}
    private static final VectorSpecies<Float> SIMD_SPECIES = FloatVector.SPECIES_128;
    private static final int PREFERRED_LANES = FloatVector.SPECIES_PREFERRED.length();
    private static final FloatVector UNIT_W = FloatVector.fromArray(SIMD_SPECIES, new float[]{0.0f, 0.0f, 0.0f, 1.0f}, 0);

    public static float[] transpose(float[] dest, int destOffset, float[] src, int srcOffset) {
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] add(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.fromArray(SIMD_SPECIES, other, (otherOffset + _li * 4)).add(FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] mul(float[] dest, int destOffset, float[] src, int srcOffset, float scalar) {
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, scalar).mul(FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] negate(float[] dest, int destOffset, float[] src, int srcOffset) {
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)).neg();
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] sub(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)).sub(FloatVector.fromArray(SIMD_SPECIES, other, (otherOffset + _li * 4)));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] set(float[] dest, int destOffset, float[] v, int vOffset) {
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.fromArray(SIMD_SPECIES, v, (vOffset + _li * 4));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] withTranslation(float[] dest, int destOffset, float[] src, int srcOffset, float tX, float tY, float tZ) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(3, tX);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(3, tY);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(3, tZ);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] withTranslation(float[] dest, int destOffset, float[] src, int srcOffset, float[] t, int tOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(3, t[tOffset + 0]);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(3, t[tOffset + 1]);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(3, t[tOffset + 2]);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] lerp(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static float[] lerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, t).fma(FloatVector.fromArray(SIMD_SPECIES, other, (otherOffset + _li * 4)).sub(FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4))), FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] lerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, t).mul(FloatVector.fromArray(SIMD_SPECIES, other, (otherOffset + _li * 4)).sub(FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)))).add(FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] mul(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        if (SimdSupport.USE_FMA) return mul_fma(dest, destOffset, src, srcOffset, right, rightOffset);
        return mul_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static float[] mul_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, right, rightOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, right, rightOffset + 4);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, right, rightOffset + 8);
        for (int _li = 0; _li < 3; _li++) {
            var _c = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]), FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] mul_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, right, rightOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, right, rightOffset + 4);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, right, rightOffset + 8);
        for (int _li = 0; _li < 3; _li++) {
            var _c = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0])).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1])).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2])).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] mulMat2x3(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        float _right00 = right[rightOffset + 0];
        float _right01 = right[rightOffset + 2];
        float _right10 = right[rightOffset + 1];
        float _right11 = right[rightOffset + 3];
        float _right02 = right[rightOffset + 4];
        float _right12 = right[rightOffset + 5];
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, _right00).withLane(1, _right01).mul(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0])).withLane(3, src[(srcOffset + _li * 4) + 3]).add(FloatVector.broadcast(SIMD_SPECIES, _right10).withLane(1, _right11).mul(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1])).withLane(3, _right02 * src[(srcOffset + _li * 4) + 0] + _right12 * src[(srcOffset + _li * 4) + 1])).withLane(2, src[(srcOffset + _li * 4) + 2]);
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] mulMat3x3(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        if (SimdSupport.USE_FMA) return mulMat3x3_fma(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat3x3_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static float[] mulMat3x3_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        float _right20 = right[rightOffset + 2];
        float _right21 = right[rightOffset + 5];
        float _right22 = right[rightOffset + 8];
        float _right00 = right[rightOffset + 0];
        float _right01 = right[rightOffset + 3];
        float _right02 = right[rightOffset + 6];
        float _right10 = right[rightOffset + 1];
        float _right11 = right[rightOffset + 4];
        float _right12 = right[rightOffset + 7];
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _right20).withLane(1, _right21).withLane(2, _right22);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _right00).withLane(1, _right01).withLane(2, _right02);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _right10).withLane(1, _right11).withLane(2, _right12);
        for (int _li = 0; _li < 3; _li++) {
            var _c = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]), FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] mulMat3x3_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        float _right20 = right[rightOffset + 2];
        float _right21 = right[rightOffset + 5];
        float _right22 = right[rightOffset + 8];
        float _right00 = right[rightOffset + 0];
        float _right01 = right[rightOffset + 3];
        float _right02 = right[rightOffset + 6];
        float _right10 = right[rightOffset + 1];
        float _right11 = right[rightOffset + 4];
        float _right12 = right[rightOffset + 7];
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _right20).withLane(1, _right21).withLane(2, _right22);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _right00).withLane(1, _right01).withLane(2, _right02);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _right10).withLane(1, _right11).withLane(2, _right12);
        for (int _li = 0; _li < 3; _li++) {
            var _c = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2])).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0])).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1])).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] mulMat4x4(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        if (SimdSupport.USE_FMA) return mulMat4x4_fma(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat4x4_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static float[] mulMat4x4_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        mulMat4x4_fma_s3b039c4e_v(dest, destOffset, right, rightOffset, src[srcOffset + 3], src[srcOffset + 7], src[srcOffset + 11], src[srcOffset + 2], src[srcOffset + 6], src[srcOffset + 10], src[srcOffset + 0], src[srcOffset + 4], src[srcOffset + 8], src[srcOffset + 1], src[srcOffset + 5], src[srcOffset + 9]);
        return dest;
    }

    private static void mulMat4x4_fma_s3b039c4e_v(float[] dest, int destOffset, float[] right, int rightOffset, float _self03, float _self13, float _self23, float _self02, float _self12, float _self22, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self03).withLane(1, _self13).withLane(2, _self23);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self02).withLane(1, _self12).withLane(2, _self22);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self00).withLane(1, _self10).withLane(2, _self20);
        var _sv3 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self01).withLane(1, _self11).withLane(2, _self21);
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, right[(rightOffset + _li * 4) + 3]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, right[(rightOffset + _li * 4) + 2]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, right[(rightOffset + _li * 4) + 0]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, right[(rightOffset + _li * 4) + 1]).fma(_sv3, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, right[(rightOffset + _li * 4) + 3])))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] mulMat4x4_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        mulMat4x4_mulAdd_se276a1_v(dest, destOffset, right, rightOffset, src[srcOffset + 3], src[srcOffset + 7], src[srcOffset + 11], src[srcOffset + 2], src[srcOffset + 6], src[srcOffset + 10], src[srcOffset + 0], src[srcOffset + 4], src[srcOffset + 8], src[srcOffset + 1], src[srcOffset + 5], src[srcOffset + 9]);
        return dest;
    }

    private static void mulMat4x4_mulAdd_se276a1_v(float[] dest, int destOffset, float[] right, int rightOffset, float _self03, float _self13, float _self23, float _self02, float _self12, float _self22, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self03).withLane(1, _self13).withLane(2, _self23);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self02).withLane(1, _self12).withLane(2, _self22);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self00).withLane(1, _self10).withLane(2, _self20);
        var _sv3 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self01).withLane(1, _self11).withLane(2, _self21);
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, right[(rightOffset + _li * 4) + 3]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, right[(rightOffset + _li * 4) + 2]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, right[(rightOffset + _li * 4) + 0]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, right[(rightOffset + _li * 4) + 1]).mul(_sv3).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, right[(rightOffset + _li * 4) + 3])))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] preMul(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        if (SimdSupport.USE_FMA) return preMul_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMul_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static float[] preMul_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, other[(otherOffset + _li * 4) + 0]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, other[(otherOffset + _li * 4) + 1]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, other[(otherOffset + _li * 4) + 2]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, other[(otherOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preMul_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, other[(otherOffset + _li * 4) + 0]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, other[(otherOffset + _li * 4) + 1]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, other[(otherOffset + _li * 4) + 2]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, other[(otherOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preMulMat2x2(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        if (SimdSupport.USE_FMA) return preMulMat2x2_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat2x2_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static float[] preMulMat2x2_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 0]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 2]).mul(_sv1));
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 1]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 3]).mul(_sv1));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preMulMat2x2_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 0]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 2]).mul(_sv1));
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 1]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 3]).mul(_sv1));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preMulMat2x3(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        if (SimdSupport.USE_FMA) return preMulMat2x3_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat2x3_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static float[] preMulMat2x3_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 0]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 2]).fma(_sv1, _sv2.withLane(3, other[otherOffset + 4])));
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 1]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 3]).fma(_sv1, _sv2.withLane(3, other[otherOffset + 5])));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preMulMat2x3_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 0]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 2]).mul(_sv1).add(_sv2.withLane(3, other[otherOffset + 4])));
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 1]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 3]).mul(_sv1).add(_sv2.withLane(3, other[otherOffset + 5])));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preMulMat3x3(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        if (SimdSupport.USE_FMA) return preMulMat3x3_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat3x3_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static float[] preMulMat3x3_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 6]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 0]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 3]).mul(_sv2)));
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 7]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 1]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 4]).mul(_sv2)));
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 8]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 2]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 5]).mul(_sv2)));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preMulMat3x3_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 6]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 0]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 3]).mul(_sv2)));
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 7]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 1]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 4]).mul(_sv2)));
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 8]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 2]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 5]).mul(_sv2)));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preMulMat4x4(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        if (SimdSupport.USE_FMA) return preMulMat4x4_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat4x4_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static float[] preMulMat4x4_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        preMulMat4x4_fma_sb916082d_v(dest, destOffset, other, otherOffset, src[srcOffset + 8], src[srcOffset + 0], src[srcOffset + 4], src[srcOffset + 9], src[srcOffset + 1], src[srcOffset + 5], src[srcOffset + 10], src[srcOffset + 2], src[srcOffset + 6], src[srcOffset + 3], src[srcOffset + 7], src[srcOffset + 11]);
        return dest;
    }

    private static void preMulMat4x4_fma_sb916082d_v(float[] dest, int destOffset, float[] other, int otherOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8, float _h9, float _h10, float _h11) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, other, otherOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + 4);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h0), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h1), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h2))));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h3), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h4), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h5))));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h6), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h7), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h8))));
        var _c3 = _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h9), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _h10), _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h11), FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] preMulMat4x4_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        preMulMat4x4_mulAdd_s81140ba0_v(dest, destOffset, other, otherOffset, src[srcOffset + 8], src[srcOffset + 0], src[srcOffset + 4], src[srcOffset + 9], src[srcOffset + 1], src[srcOffset + 5], src[srcOffset + 10], src[srcOffset + 2], src[srcOffset + 6], src[srcOffset + 3], src[srcOffset + 7], src[srcOffset + 11]);
        return dest;
    }

    private static void preMulMat4x4_mulAdd_s81140ba0_v(float[] dest, int destOffset, float[] other, int otherOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8, float _h9, float _h10, float _h11) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, other, otherOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + 4);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h0)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h1)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h2))));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h3)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h4)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h5))));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h6)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h7)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h8))));
        var _c3 = _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h9)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h10)).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h11)).add(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] addScaled(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float weight) {
        if (SimdSupport.USE_FMA) return addScaled_fma(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return addScaled_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    public static float[] addScaled_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float weight) {
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, weight).fma(FloatVector.fromArray(SIMD_SPECIES, other, (otherOffset + _li * 4)), FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] addScaled_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float weight) {
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, weight).mul(FloatVector.fromArray(SIMD_SPECIES, other, (otherOffset + _li * 4))).add(FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] composeTRSMul(float[] dest, int destOffset, float[] m, int mOffset, float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ) {
        if (SimdSupport.USE_FMA) return composeTRSMul_fma(dest, destOffset, m, mOffset, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
        return composeTRSMul_mulAdd(dest, destOffset, m, mOffset, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
    }

    public static float[] composeTRSMul_fma(float[] dest, int destOffset, float[] m, int mOffset, float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ) {
        float _t0 = scaleZ + scaleZ;
        float _t1 = scaleX + scaleX;
        float _t2 = scaleY + scaleY;
        float _t3 = rotationY * rotationW;
        float _t4 = rotationZ * rotationZ;
        float _t5 = rotationZ * rotationW;
        composeTRSMul_fma_sde574f0a_v(dest, destOffset, m, mOffset, translationX, translationY, translationZ, Math.fma(rotationX, rotationZ, _t3) * _t0, Math.fma(rotationX, rotationY, -_t5) * _t2, Math.fma(-Math.fma(rotationY, rotationY, _t4), _t1, scaleX), Math.fma(rotationX, rotationY, _t5) * _t1, Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t0, Math.fma(-Math.fma(rotationX, rotationX, _t4), _t2, scaleY), Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t2, Math.fma(rotationX, rotationZ, -_t3) * _t1, Math.fma(-Math.fma(rotationX, rotationX, rotationY * rotationY), _t0, scaleZ));
        return dest;
    }

    private static void composeTRSMul_fma_sde574f0a_v(float[] dest, int destOffset, float[] m, int mOffset, float translationX, float translationY, float translationZ, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, m, mOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, m, mOffset + 4);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, m, mOffset);
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h0), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h1), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _h2), _sv3.withLane(3, translationX))));
        var _c1 = _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _h3), _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h4), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h5), _sv3.withLane(3, translationY))));
        var _c2 = _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h6), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _h7), _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h8), _sv3.withLane(3, translationZ))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
    }

    public static float[] composeTRSMul_mulAdd(float[] dest, int destOffset, float[] m, int mOffset, float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ) {
        float _t0 = scaleZ + scaleZ;
        float _t1 = scaleX + scaleX;
        float _t2 = scaleY + scaleY;
        float _t3 = rotationY * rotationW;
        float _t4 = rotationZ * rotationZ;
        float _t5 = rotationZ * rotationW;
        composeTRSMul_mulAdd_sd5c5c337_v(dest, destOffset, m, mOffset, translationX, translationY, translationZ, Math.fma(rotationX, rotationZ, _t3) * _t0, Math.fma(rotationX, rotationY, -_t5) * _t2, Math.fma(-Math.fma(rotationY, rotationY, _t4), _t1, scaleX), Math.fma(rotationX, rotationY, _t5) * _t1, Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t0, Math.fma(-Math.fma(rotationX, rotationX, _t4), _t2, scaleY), Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t2, Math.fma(rotationX, rotationZ, -_t3) * _t1, Math.fma(-Math.fma(rotationX, rotationX, rotationY * rotationY), _t0, scaleZ));
        return dest;
    }

    private static void composeTRSMul_mulAdd_sd5c5c337_v(float[] dest, int destOffset, float[] m, int mOffset, float translationX, float translationY, float translationZ, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, m, mOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, m, mOffset + 4);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, m, mOffset);
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h0)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h1)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h2)).add(_sv3.withLane(3, translationX))));
        var _c1 = _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h3)).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h4)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h5)).add(_sv3.withLane(3, translationY))));
        var _c2 = _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h6)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h7)).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h8)).add(_sv3.withLane(3, translationZ))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
    }

    public static float[] composeTRSMul(float[] dest, int destOffset, float[] translation, int translationOffset, float[] rotation, int rotationOffset, float[] scale, int scaleOffset, float[] m, int mOffset) {
        if (SimdSupport.USE_FMA) return composeTRSMul_fma(dest, destOffset, translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, m, mOffset);
        return composeTRSMul_mulAdd(dest, destOffset, translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, m, mOffset);
    }

    public static float[] composeTRSMul_fma(float[] dest, int destOffset, float[] translation, int translationOffset, float[] rotation, int rotationOffset, float[] scale, int scaleOffset, float[] m, int mOffset) {
        float _scalez = scale[scaleOffset + 2];
        float _scalex = scale[scaleOffset + 0];
        float _scaley = scale[scaleOffset + 1];
        float _rotationy = rotation[rotationOffset + 1];
        float _rotationw = rotation[rotationOffset + 3];
        float _rotationz = rotation[rotationOffset + 2];
        float _rotationx = rotation[rotationOffset + 0];
        float _t0 = _scalez + _scalez;
        float _t1 = _scalex + _scalex;
        float _t2 = _scaley + _scaley;
        float _t3 = _rotationy * _rotationw;
        float _t4 = _rotationz * _rotationz;
        float _t5 = _rotationz * _rotationw;
        composeTRSMul_fma_s9c518a4d_v(dest, destOffset, m, mOffset, Math.fma(_rotationx, _rotationz, _t3) * _t0, Math.fma(_rotationx, _rotationy, -_t5) * _t2, Math.fma(-Math.fma(_rotationy, _rotationy, _t4), _t1, _scalex), translation[translationOffset + 0], Math.fma(_rotationx, _rotationy, _t5) * _t1, Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t0, Math.fma(-Math.fma(_rotationx, _rotationx, _t4), _t2, _scaley), translation[translationOffset + 1], Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t2, Math.fma(_rotationx, _rotationz, -_t3) * _t1, Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t0, _scalez), translation[translationOffset + 2]);
        return dest;
    }

    private static void composeTRSMul_fma_s9c518a4d_v(float[] dest, int destOffset, float[] m, int mOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8, float _h9, float _h10, float _h11) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, m, mOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, m, mOffset + 4);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, m, mOffset);
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h0), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h1), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _h2), _sv3.withLane(3, _h3))));
        var _c1 = _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _h4), _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h5), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h6), _sv3.withLane(3, _h7))));
        var _c2 = _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h8), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _h9), _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h10), _sv3.withLane(3, _h11))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
    }

    public static float[] composeTRSMul_mulAdd(float[] dest, int destOffset, float[] translation, int translationOffset, float[] rotation, int rotationOffset, float[] scale, int scaleOffset, float[] m, int mOffset) {
        float _scalez = scale[scaleOffset + 2];
        float _scalex = scale[scaleOffset + 0];
        float _scaley = scale[scaleOffset + 1];
        float _rotationy = rotation[rotationOffset + 1];
        float _rotationw = rotation[rotationOffset + 3];
        float _rotationz = rotation[rotationOffset + 2];
        float _rotationx = rotation[rotationOffset + 0];
        float _t0 = _scalez + _scalez;
        float _t1 = _scalex + _scalex;
        float _t2 = _scaley + _scaley;
        float _t3 = _rotationy * _rotationw;
        float _t4 = _rotationz * _rotationz;
        float _t5 = _rotationz * _rotationw;
        composeTRSMul_mulAdd_sc1e6fe58_v(dest, destOffset, m, mOffset, Math.fma(_rotationx, _rotationz, _t3) * _t0, Math.fma(_rotationx, _rotationy, -_t5) * _t2, Math.fma(-Math.fma(_rotationy, _rotationy, _t4), _t1, _scalex), translation[translationOffset + 0], Math.fma(_rotationx, _rotationy, _t5) * _t1, Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t0, Math.fma(-Math.fma(_rotationx, _rotationx, _t4), _t2, _scaley), translation[translationOffset + 1], Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t2, Math.fma(_rotationx, _rotationz, -_t3) * _t1, Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t0, _scalez), translation[translationOffset + 2]);
        return dest;
    }

    private static void composeTRSMul_mulAdd_sc1e6fe58_v(float[] dest, int destOffset, float[] m, int mOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8, float _h9, float _h10, float _h11) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, m, mOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, m, mOffset + 4);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, m, mOffset);
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h0)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h1)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h2)).add(_sv3.withLane(3, _h3))));
        var _c1 = _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h4)).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h5)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h6)).add(_sv3.withLane(3, _h7))));
        var _c2 = _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h8)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h9)).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h10)).add(_sv3.withLane(3, _h11))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
    }

    public static float[] lookAlong(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (SimdSupport.USE_FMA) return lookAlong_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static float[] lookAlong_fma(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        float _t18 = Math.fma(_t17, _t12, upX);
        float _t19 = Math.fma(_t17, _t13, upY);
        float _t20 = Math.fma(_t17, _t11, upZ);
        float _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t37).withLane(1, Math.fma(_t39, _t12, -(_t38 * _t13))).withLane(2, _t11);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t38).withLane(1, Math.fma(_t37, _t13, -(_t39 * _t11))).withLane(2, _t12);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t39).withLane(1, Math.fma(_t38, _t11, -(_t37 * _t12))).withLane(2, _t13);
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] lookAlong_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        float _t18 = Math.fma(_t17, _t12, upX);
        float _t19 = Math.fma(_t17, _t13, upY);
        float _t20 = Math.fma(_t17, _t11, upZ);
        float _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t37).withLane(1, Math.fma(_t39, _t12, -(_t38 * _t13))).withLane(2, _t11);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t38).withLane(1, Math.fma(_t37, _t13, -(_t39 * _t11))).withLane(2, _t12);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t39).withLane(1, Math.fma(_t38, _t11, -(_t37 * _t12))).withLane(2, _t13);
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] lookAlong(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        if (SimdSupport.USE_FMA) return lookAlong_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static float[] lookAlong_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        float _dirz = dir[dirOffset + 2];
        float _dirx = dir[dirOffset + 0];
        float _diry = dir[dirOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset + 0];
        float _upy = up[upOffset + 1];
        float _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        float _t18 = Math.fma(_t17, _t12, _upx);
        float _t19 = Math.fma(_t17, _t13, _upy);
        float _t20 = Math.fma(_t17, _t11, _upz);
        float _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t37).withLane(1, Math.fma(_t39, _t12, -(_t38 * _t13))).withLane(2, _t11);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t38).withLane(1, Math.fma(_t37, _t13, -(_t39 * _t11))).withLane(2, _t12);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t39).withLane(1, Math.fma(_t38, _t11, -(_t37 * _t12))).withLane(2, _t13);
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] lookAlong_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        float _dirz = dir[dirOffset + 2];
        float _dirx = dir[dirOffset + 0];
        float _diry = dir[dirOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset + 0];
        float _upy = up[upOffset + 1];
        float _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        float _t18 = Math.fma(_t17, _t12, _upx);
        float _t19 = Math.fma(_t17, _t13, _upy);
        float _t20 = Math.fma(_t17, _t11, _upz);
        float _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t37).withLane(1, Math.fma(_t39, _t12, -(_t38 * _t13))).withLane(2, _t11);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t38).withLane(1, Math.fma(_t37, _t13, -(_t39 * _t11))).withLane(2, _t12);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t39).withLane(1, Math.fma(_t38, _t11, -(_t37 * _t12))).withLane(2, _t13);
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] lookAt_lh(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (SimdSupport.USE_FMA) return lookAt_lh_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return lookAt_lh_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static float[] lookAt_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t22 = Math.fma(eyeZ, _t15, Math.fma(eyeX, _t14, eyeY * _t16));
        float _t23 = -Math.fma(upZ, _t15, Math.fma(upX, _t14, upY * _t16));
        float _t24 = Math.fma(_t23, _t14, upX);
        float _t25 = Math.fma(_t23, _t16, upY);
        float _t26 = Math.fma(_t23, _t15, upZ);
        float _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        float _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        float _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        float _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        float _t39 = (1.0f / (float) Math.sqrt(_t38));
        float _t43, _t44, _t45;
        if (_t38 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0f;
            _t44 = 0.0f;
            _t45 = 0.0f;
        }
        float _t54 = Math.fma(_t44, _t16, -(_t45 * _t15));
        float _t55 = Math.fma(_t43, _t15, -(_t44 * _t14));
        float _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        float _t60 = Math.fma(eyeZ, _t56, Math.fma(eyeX, _t54, eyeY * _t55));
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, Math.fma(eyeZ, _t44, Math.fma(eyeX, _t43, eyeY * _t45)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3])).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).withLane(3, -src[(srcOffset + _li * 4) + 0]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -src[(srcOffset + _li * 4) + 1] * _t60 + -src[(srcOffset + _li * 4) + 2] * _t22))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] lookAt_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t22 = Math.fma(eyeZ, _t15, Math.fma(eyeX, _t14, eyeY * _t16));
        float _t23 = -Math.fma(upZ, _t15, Math.fma(upX, _t14, upY * _t16));
        float _t24 = Math.fma(_t23, _t14, upX);
        float _t25 = Math.fma(_t23, _t16, upY);
        float _t26 = Math.fma(_t23, _t15, upZ);
        float _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        float _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        float _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        float _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        float _t39 = (1.0f / (float) Math.sqrt(_t38));
        float _t43, _t44, _t45;
        if (_t38 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0f;
            _t44 = 0.0f;
            _t45 = 0.0f;
        }
        float _t54 = Math.fma(_t44, _t16, -(_t45 * _t15));
        float _t55 = Math.fma(_t43, _t15, -(_t44 * _t14));
        float _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        float _t60 = Math.fma(eyeZ, _t56, Math.fma(eyeX, _t54, eyeY * _t55));
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, Math.fma(eyeZ, _t44, Math.fma(eyeX, _t43, eyeY * _t45)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3])).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).withLane(3, -src[(srcOffset + _li * 4) + 0]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -src[(srcOffset + _li * 4) + 1] * _t60 + -src[(srcOffset + _li * 4) + 2] * _t22))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] lookAt_rh(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (SimdSupport.USE_FMA) return lookAt_rh_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return lookAt_rh_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static float[] lookAt_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t3 = centerZ - eyeZ;
        float _t4 = centerX - eyeX;
        float _t5 = centerY - eyeY;
        float _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        float _t13 = (1.0f / (float) Math.sqrt(_t12));
        float _t17, _t18, _t19;
        if (_t12 != 0.0f) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0f;
            _t18 = 0.0f;
            _t19 = 0.0f;
        }
        float _t26 = -Math.fma(upZ, _t18, Math.fma(upX, _t17, upY * _t19));
        float _t27 = Math.fma(_t26, _t19, upY);
        float _t28 = Math.fma(_t26, _t17, upX);
        float _t29 = Math.fma(_t26, _t18, upZ);
        float _t36 = Math.fma(_t27, _t17, -(_t28 * _t19));
        float _t37 = Math.fma(_t28, _t18, -(_t29 * _t17));
        float _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        float _t41 = Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38));
        float _t42 = (1.0f / (float) Math.sqrt(_t41));
        float _t46, _t47, _t48;
        if (_t41 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0f;
            _t47 = 0.0f;
            _t48 = 0.0f;
        }
        float _t57 = Math.fma(_t47, _t18, -(_t48 * _t19));
        float _t58 = Math.fma(_t48, _t17, -(_t46 * _t18));
        float _t59 = Math.fma(_t46, _t19, -(_t47 * _t17));
        float _t61 = Math.fma(eyeZ, _t48, Math.fma(eyeX, _t46, eyeY * _t47));
        float _t63 = Math.fma(eyeZ, _t59, Math.fma(eyeX, _t57, eyeY * _t58));
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, Math.fma(eyeZ, _t18, Math.fma(eyeX, _t17, eyeY * _t19)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, -src[(srcOffset + _li * 4) + 2]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3])).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).withLane(3, src[(srcOffset + _li * 4) + 2]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -src[(srcOffset + _li * 4) + 0] * _t61 + -src[(srcOffset + _li * 4) + 1] * _t63))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] lookAt_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t3 = centerZ - eyeZ;
        float _t4 = centerX - eyeX;
        float _t5 = centerY - eyeY;
        float _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        float _t13 = (1.0f / (float) Math.sqrt(_t12));
        float _t17, _t18, _t19;
        if (_t12 != 0.0f) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0f;
            _t18 = 0.0f;
            _t19 = 0.0f;
        }
        float _t26 = -Math.fma(upZ, _t18, Math.fma(upX, _t17, upY * _t19));
        float _t27 = Math.fma(_t26, _t19, upY);
        float _t28 = Math.fma(_t26, _t17, upX);
        float _t29 = Math.fma(_t26, _t18, upZ);
        float _t36 = Math.fma(_t27, _t17, -(_t28 * _t19));
        float _t37 = Math.fma(_t28, _t18, -(_t29 * _t17));
        float _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        float _t41 = Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38));
        float _t42 = (1.0f / (float) Math.sqrt(_t41));
        float _t46, _t47, _t48;
        if (_t41 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0f;
            _t47 = 0.0f;
            _t48 = 0.0f;
        }
        float _t57 = Math.fma(_t47, _t18, -(_t48 * _t19));
        float _t58 = Math.fma(_t48, _t17, -(_t46 * _t18));
        float _t59 = Math.fma(_t46, _t19, -(_t47 * _t17));
        float _t61 = Math.fma(eyeZ, _t48, Math.fma(eyeX, _t46, eyeY * _t47));
        float _t63 = Math.fma(eyeZ, _t59, Math.fma(eyeX, _t57, eyeY * _t58));
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, Math.fma(eyeZ, _t18, Math.fma(eyeX, _t17, eyeY * _t19)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, -src[(srcOffset + _li * 4) + 2]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3])).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).withLane(3, src[(srcOffset + _li * 4) + 2]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -src[(srcOffset + _li * 4) + 0] * _t61 + -src[(srcOffset + _li * 4) + 1] * _t63))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] lookAt_lh(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        if (SimdSupport.USE_FMA) return lookAt_lh_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return lookAt_lh_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static float[] lookAt_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        float _eyez = eye[eyeOffset + 2];
        float _eyex = eye[eyeOffset + 0];
        float _eyey = eye[eyeOffset + 1];
        float _centerz = center[centerOffset + 2];
        float _centerx = center[centerOffset + 0];
        float _centery = center[centerOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset + 0];
        float _upy = up[upOffset + 1];
        float _t0 = _centerz - _eyez;
        float _t1 = _centerx - _eyex;
        float _t2 = _centery - _eyey;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t22 = Math.fma(_eyez, _t15, Math.fma(_eyex, _t14, _eyey * _t16));
        float _t23 = -Math.fma(_upz, _t15, Math.fma(_upx, _t14, _upy * _t16));
        float _t24 = Math.fma(_t23, _t14, _upx);
        float _t25 = Math.fma(_t23, _t16, _upy);
        float _t26 = Math.fma(_t23, _t15, _upz);
        float _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        float _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        float _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        float _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        float _t39 = (1.0f / (float) Math.sqrt(_t38));
        float _t43, _t44, _t45;
        if (_t38 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0f;
            _t44 = 0.0f;
            _t45 = 0.0f;
        }
        float _t54 = Math.fma(_t44, _t16, -(_t45 * _t15));
        float _t55 = Math.fma(_t43, _t15, -(_t44 * _t14));
        float _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        float _t60 = Math.fma(_eyez, _t56, Math.fma(_eyex, _t54, _eyey * _t55));
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, Math.fma(_eyez, _t44, Math.fma(_eyex, _t43, _eyey * _t45)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3])).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).withLane(3, -src[(srcOffset + _li * 4) + 0]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -src[(srcOffset + _li * 4) + 1] * _t60 + -src[(srcOffset + _li * 4) + 2] * _t22))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] lookAt_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        float _eyez = eye[eyeOffset + 2];
        float _eyex = eye[eyeOffset + 0];
        float _eyey = eye[eyeOffset + 1];
        float _centerz = center[centerOffset + 2];
        float _centerx = center[centerOffset + 0];
        float _centery = center[centerOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset + 0];
        float _upy = up[upOffset + 1];
        float _t0 = _centerz - _eyez;
        float _t1 = _centerx - _eyex;
        float _t2 = _centery - _eyey;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) Math.sqrt(_t9));
        float _t14, _t15, _t16;
        if (_t9 != 0.0f) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0f;
            _t15 = 0.0f;
            _t16 = 0.0f;
        }
        float _t22 = Math.fma(_eyez, _t15, Math.fma(_eyex, _t14, _eyey * _t16));
        float _t23 = -Math.fma(_upz, _t15, Math.fma(_upx, _t14, _upy * _t16));
        float _t24 = Math.fma(_t23, _t14, _upx);
        float _t25 = Math.fma(_t23, _t16, _upy);
        float _t26 = Math.fma(_t23, _t15, _upz);
        float _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        float _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        float _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        float _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        float _t39 = (1.0f / (float) Math.sqrt(_t38));
        float _t43, _t44, _t45;
        if (_t38 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0f;
            _t44 = 0.0f;
            _t45 = 0.0f;
        }
        float _t54 = Math.fma(_t44, _t16, -(_t45 * _t15));
        float _t55 = Math.fma(_t43, _t15, -(_t44 * _t14));
        float _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        float _t60 = Math.fma(_eyez, _t56, Math.fma(_eyex, _t54, _eyey * _t55));
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, Math.fma(_eyez, _t44, Math.fma(_eyex, _t43, _eyey * _t45)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3])).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).withLane(3, -src[(srcOffset + _li * 4) + 0]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -src[(srcOffset + _li * 4) + 1] * _t60 + -src[(srcOffset + _li * 4) + 2] * _t22))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] lookAt_rh(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        if (SimdSupport.USE_FMA) return lookAt_rh_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return lookAt_rh_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static float[] lookAt_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        float _eyez = eye[eyeOffset + 2];
        float _eyex = eye[eyeOffset + 0];
        float _eyey = eye[eyeOffset + 1];
        float _centerz = center[centerOffset + 2];
        float _centerx = center[centerOffset + 0];
        float _centery = center[centerOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset + 0];
        float _upy = up[upOffset + 1];
        float _t3 = _centerz - _eyez;
        float _t4 = _centerx - _eyex;
        float _t5 = _centery - _eyey;
        float _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        float _t13 = (1.0f / (float) Math.sqrt(_t12));
        float _t17, _t18, _t19;
        if (_t12 != 0.0f) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0f;
            _t18 = 0.0f;
            _t19 = 0.0f;
        }
        float _t26 = -Math.fma(_upz, _t18, Math.fma(_upx, _t17, _upy * _t19));
        float _t27 = Math.fma(_t26, _t19, _upy);
        float _t28 = Math.fma(_t26, _t17, _upx);
        float _t29 = Math.fma(_t26, _t18, _upz);
        float _t36 = Math.fma(_t27, _t17, -(_t28 * _t19));
        float _t37 = Math.fma(_t28, _t18, -(_t29 * _t17));
        float _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        float _t41 = Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38));
        float _t42 = (1.0f / (float) Math.sqrt(_t41));
        float _t46, _t47, _t48;
        if (_t41 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0f;
            _t47 = 0.0f;
            _t48 = 0.0f;
        }
        float _t57 = Math.fma(_t47, _t18, -(_t48 * _t19));
        float _t58 = Math.fma(_t48, _t17, -(_t46 * _t18));
        float _t59 = Math.fma(_t46, _t19, -(_t47 * _t17));
        float _t61 = Math.fma(_eyez, _t48, Math.fma(_eyex, _t46, _eyey * _t47));
        float _t63 = Math.fma(_eyez, _t59, Math.fma(_eyex, _t57, _eyey * _t58));
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, Math.fma(_eyez, _t18, Math.fma(_eyex, _t17, _eyey * _t19)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, -src[(srcOffset + _li * 4) + 2]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3])).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).withLane(3, src[(srcOffset + _li * 4) + 2]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -src[(srcOffset + _li * 4) + 0] * _t61 + -src[(srcOffset + _li * 4) + 1] * _t63))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] lookAt_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        float _eyez = eye[eyeOffset + 2];
        float _eyex = eye[eyeOffset + 0];
        float _eyey = eye[eyeOffset + 1];
        float _centerz = center[centerOffset + 2];
        float _centerx = center[centerOffset + 0];
        float _centery = center[centerOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset + 0];
        float _upy = up[upOffset + 1];
        float _t3 = _centerz - _eyez;
        float _t4 = _centerx - _eyex;
        float _t5 = _centery - _eyey;
        float _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        float _t13 = (1.0f / (float) Math.sqrt(_t12));
        float _t17, _t18, _t19;
        if (_t12 != 0.0f) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0f;
            _t18 = 0.0f;
            _t19 = 0.0f;
        }
        float _t26 = -Math.fma(_upz, _t18, Math.fma(_upx, _t17, _upy * _t19));
        float _t27 = Math.fma(_t26, _t19, _upy);
        float _t28 = Math.fma(_t26, _t17, _upx);
        float _t29 = Math.fma(_t26, _t18, _upz);
        float _t36 = Math.fma(_t27, _t17, -(_t28 * _t19));
        float _t37 = Math.fma(_t28, _t18, -(_t29 * _t17));
        float _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        float _t41 = Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38));
        float _t42 = (1.0f / (float) Math.sqrt(_t41));
        float _t46, _t47, _t48;
        if (_t41 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0f;
            _t47 = 0.0f;
            _t48 = 0.0f;
        }
        float _t57 = Math.fma(_t47, _t18, -(_t48 * _t19));
        float _t58 = Math.fma(_t48, _t17, -(_t46 * _t18));
        float _t59 = Math.fma(_t46, _t19, -(_t47 * _t17));
        float _t61 = Math.fma(_eyez, _t48, Math.fma(_eyex, _t46, _eyey * _t47));
        float _t63 = Math.fma(_eyez, _t59, Math.fma(_eyex, _t57, _eyey * _t58));
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, Math.fma(_eyez, _t18, Math.fma(_eyex, _t17, _eyey * _t19)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, -src[(srcOffset + _li * 4) + 2]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3])).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).withLane(3, src[(srcOffset + _li * 4) + 2]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -src[(srcOffset + _li * 4) + 0] * _t61 + -src[(srcOffset + _li * 4) + 1] * _t63))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] mapXYZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] mapXYnZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)).withLane(2, -src[(srcOffset + _li * 4) + 2]);
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] mapXnYZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)).withLane(1, -src[(srcOffset + _li * 4) + 1]);
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preRotateAround(float[] dest, int destOffset, float[] src, int srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        if (SimdSupport.USE_FMA) return preRotateAround_fma(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return preRotateAround_mulAdd(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
    }

    public static float[] preRotateAround_fma(float[] dest, int destOffset, float[] src, int srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        float _t0 = -rotY;
        float _t2 = -pivotZ;
        float _t3 = -rotX;
        float _t4 = rotX + rotX;
        float _t5 = rotY + rotY;
        float _t6 = rotZ + rotZ;
        float _t7 = rotW * _t5;
        float _t8 = rotW * _t6;
        float _t9 = rotZ * _t6;
        float _t10 = rotW * _t4;
        float _t14 = Math.fma(-rotZ, _t6, 1.0f);
        float _t16 = Math.fma(rotZ, _t4, _t7);
        float _t17 = Math.fma(rotY, _t4, _t8);
        float _t18 = Math.fma(rotZ, _t5, _t10);
        float _t19 = Math.fma(rotY, _t4, -_t8);
        float _t20 = Math.fma(rotZ, _t5, -_t10);
        float _t21 = Math.fma(rotZ, _t4, -_t7);
        preRotateAround_fma_s9e20ed84_v(dest, destOffset, src, srcOffset, _t16, _t17, _t18, _t19, _t20, _t21, Math.fma(_t0, _t5, _t14), Math.fma(_t2, _t16, Math.fma(pivotX, Math.fma(rotY, _t5, _t9), -(pivotY * _t19))), Math.fma(_t3, _t4, _t14), Math.fma(_t2, _t20, Math.fma(pivotY, Math.fma(rotX, _t4, _t9), -(pivotX * _t17))), Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0f)), Math.fma(-pivotY, _t18, Math.fma(pivotZ, Math.fma(rotX, _t4, rotY * _t5), -(pivotX * _t21))));
        return dest;
    }

    private static void preRotateAround_fma_s9e20ed84_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t16), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h0), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _t19), _sv3.withLane(3, _h1))));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t20), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t17), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _h2), _sv3.withLane(3, _h3))));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h4), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t21), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _t18), _sv3.withLane(3, _h5))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
    }

    public static float[] preRotateAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        float _t0 = -rotY;
        float _t2 = -pivotZ;
        float _t3 = -rotX;
        float _t4 = rotX + rotX;
        float _t5 = rotY + rotY;
        float _t6 = rotZ + rotZ;
        float _t7 = rotW * _t5;
        float _t8 = rotW * _t6;
        float _t9 = rotZ * _t6;
        float _t10 = rotW * _t4;
        float _t14 = Math.fma(-rotZ, _t6, 1.0f);
        float _t16 = Math.fma(rotZ, _t4, _t7);
        float _t17 = Math.fma(rotY, _t4, _t8);
        float _t18 = Math.fma(rotZ, _t5, _t10);
        float _t19 = Math.fma(rotY, _t4, -_t8);
        float _t20 = Math.fma(rotZ, _t5, -_t10);
        float _t21 = Math.fma(rotZ, _t4, -_t7);
        preRotateAround_mulAdd_s842595dd_v(dest, destOffset, src, srcOffset, _t16, _t17, _t18, _t19, _t20, _t21, Math.fma(_t0, _t5, _t14), Math.fma(_t2, _t16, Math.fma(pivotX, Math.fma(rotY, _t5, _t9), -(pivotY * _t19))), Math.fma(_t3, _t4, _t14), Math.fma(_t2, _t20, Math.fma(pivotY, Math.fma(rotX, _t4, _t9), -(pivotX * _t17))), Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0f)), Math.fma(-pivotY, _t18, Math.fma(pivotZ, Math.fma(rotX, _t4, rotY * _t5), -(pivotX * _t21))));
        return dest;
    }

    private static void preRotateAround_mulAdd_s842595dd_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t16)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h0)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t19)).add(_sv3.withLane(3, _h1))));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t20)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t17)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h2)).add(_sv3.withLane(3, _h3))));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h4)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t21)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t18)).add(_sv3.withLane(3, _h5))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
    }

    public static float[] preRotateAround(float[] dest, int destOffset, float[] src, int srcOffset, float[] rot, int rotOffset, float[] pivot, int pivotOffset) {
        if (SimdSupport.USE_FMA) return preRotateAround_fma(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
        return preRotateAround_mulAdd(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
    }

    public static float[] preRotateAround_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] rot, int rotOffset, float[] pivot, int pivotOffset) {
        float _pivotx = pivot[pivotOffset + 0];
        float _roty = rot[rotOffset + 1];
        float _pivoty = pivot[pivotOffset + 1];
        float _rotx = rot[rotOffset + 0];
        float _pivotz = pivot[pivotOffset + 2];
        float _rotz = rot[rotOffset + 2];
        float _rotw = rot[rotOffset + 3];
        float _t0 = -_roty;
        float _t2 = -_pivotz;
        float _t3 = -_rotx;
        float _t4 = _rotx + _rotx;
        float _t5 = _roty + _roty;
        float _t6 = _rotz + _rotz;
        float _t7 = _rotw * _t5;
        float _t8 = _rotw * _t6;
        float _t9 = _rotz * _t6;
        float _t10 = _rotw * _t4;
        float _t14 = Math.fma(-_rotz, _t6, 1.0f);
        float _t16 = Math.fma(_rotz, _t4, _t7);
        float _t17 = Math.fma(_roty, _t4, _t8);
        float _t18 = Math.fma(_rotz, _t5, _t10);
        float _t19 = Math.fma(_roty, _t4, -_t8);
        float _t20 = Math.fma(_rotz, _t5, -_t10);
        float _t21 = Math.fma(_rotz, _t4, -_t7);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t16), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t5, _t14)), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _t19), _sv3.withLane(3, Math.fma(_t2, _t16, Math.fma(_pivotx, Math.fma(_roty, _t5, _t9), -(_pivoty * _t19)))))));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t20), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t17), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t3, _t4, _t14)), _sv3.withLane(3, Math.fma(_t2, _t20, Math.fma(_pivoty, Math.fma(_rotx, _t4, _t9), -(_pivotx * _t17)))))));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0f))), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t21), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _t18), _sv3.withLane(3, Math.fma(-_pivoty, _t18, Math.fma(_pivotz, Math.fma(_rotx, _t4, _roty * _t5), -(_pivotx * _t21)))))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preRotateAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] rot, int rotOffset, float[] pivot, int pivotOffset) {
        float _pivotx = pivot[pivotOffset + 0];
        float _roty = rot[rotOffset + 1];
        float _pivoty = pivot[pivotOffset + 1];
        float _rotx = rot[rotOffset + 0];
        float _pivotz = pivot[pivotOffset + 2];
        float _rotz = rot[rotOffset + 2];
        float _rotw = rot[rotOffset + 3];
        float _t0 = -_roty;
        float _t2 = -_pivotz;
        float _t3 = -_rotx;
        float _t4 = _rotx + _rotx;
        float _t5 = _roty + _roty;
        float _t6 = _rotz + _rotz;
        float _t7 = _rotw * _t5;
        float _t8 = _rotw * _t6;
        float _t9 = _rotz * _t6;
        float _t10 = _rotw * _t4;
        float _t14 = Math.fma(-_rotz, _t6, 1.0f);
        float _t16 = Math.fma(_rotz, _t4, _t7);
        float _t17 = Math.fma(_roty, _t4, _t8);
        float _t18 = Math.fma(_rotz, _t5, _t10);
        float _t19 = Math.fma(_roty, _t4, -_t8);
        float _t20 = Math.fma(_rotz, _t5, -_t10);
        float _t21 = Math.fma(_rotz, _t4, -_t7);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t16)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t5, _t14))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t19)).add(_sv3.withLane(3, Math.fma(_t2, _t16, Math.fma(_pivotx, Math.fma(_roty, _t5, _t9), -(_pivoty * _t19)))))));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t20)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t17)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t3, _t4, _t14))).add(_sv3.withLane(3, Math.fma(_t2, _t20, Math.fma(_pivoty, Math.fma(_rotx, _t4, _t9), -(_pivotx * _t17)))))));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0f)))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t21)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t18)).add(_sv3.withLane(3, Math.fma(-_pivoty, _t18, Math.fma(_pivotz, Math.fma(_rotx, _t4, _roty * _t5), -(_pivotx * _t21)))))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preRotateAxis(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (SimdSupport.USE_FMA) return preRotateAxis_fma(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return preRotateAxis_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    public static float[] preRotateAxis_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        preRotateAxis_fma_s507f1790_v(dest, destOffset, src, srcOffset, axisX, axisY, axisZ, _t0, _t1, axisX * axisZ, axisX * axisY, axisY * axisZ, 1.0f - _t1);
        return dest;
    }

    private static void preRotateAxis_fma_s507f1790_v(float[] dest, int destOffset, float[] src, int srcOffset, float axisX, float axisY, float axisZ, float _t0, float _t1, float _t2, float _t4, float _t6, float _t11) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(axisY, _t0, _t11 * _t2)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, axisX * axisX, _t1)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t4, -(axisZ * _t0))))));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t6, -(axisX * _t0))), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(axisZ, _t0, _t11 * _t4)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, axisY * axisY, _t1)))));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, axisZ * axisZ, _t1)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t2, -(axisY * _t0))), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(axisX, _t0, _t11 * _t6)))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
    }

    public static float[] preRotateAxis_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        preRotateAxis_mulAdd_s4c8392a1_v(dest, destOffset, src, srcOffset, axisX, axisY, axisZ, _t0, _t1, axisX * axisZ, axisX * axisY, axisY * axisZ, 1.0f - _t1);
        return dest;
    }

    private static void preRotateAxis_mulAdd_s4c8392a1_v(float[] dest, int destOffset, float[] src, int srcOffset, float axisX, float axisY, float axisZ, float _t0, float _t1, float _t2, float _t4, float _t6, float _t11) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(axisY, _t0, _t11 * _t2))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, axisX * axisX, _t1))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t4, -(axisZ * _t0))))));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t6, -(axisX * _t0)))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(axisZ, _t0, _t11 * _t4))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, axisY * axisY, _t1)))));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, axisZ * axisZ, _t1))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t2, -(axisY * _t0)))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(axisX, _t0, _t11 * _t6)))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
    }

    public static float[] preRotateAxis(float[] dest, int destOffset, float[] src, int srcOffset, float[] axis, int axisOffset, float angle) {
        if (SimdSupport.USE_FMA) return preRotateAxis_fma(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return preRotateAxis_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    public static float[] preRotateAxis_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] axis, int axisOffset, float angle) {
        float _axisy = axis[axisOffset + 1];
        float _axisx = axis[axisOffset + 0];
        float _axisz = axis[axisOffset + 2];
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        preRotateAxis_fma_sb08dc62e_v(dest, destOffset, src, srcOffset, _axisy, _axisx, _axisz, _t0, _t1, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, 1.0f - _t1);
        return dest;
    }

    private static void preRotateAxis_fma_sb08dc62e_v(float[] dest, int destOffset, float[] src, int srcOffset, float _axisy, float _axisx, float _axisz, float _t0, float _t1, float _t2, float _t4, float _t6, float _t11) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_axisy, _t0, _t11 * _t2)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisx * _axisx, _t1)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t4, -(_axisz * _t0))))));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t6, -(_axisx * _t0))), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_axisz, _t0, _t11 * _t4)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisy * _axisy, _t1)))));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisz * _axisz, _t1)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t2, -(_axisy * _t0))), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_axisx, _t0, _t11 * _t6)))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
    }

    public static float[] preRotateAxis_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] axis, int axisOffset, float angle) {
        float _axisy = axis[axisOffset + 1];
        float _axisx = axis[axisOffset + 0];
        float _axisz = axis[axisOffset + 2];
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        preRotateAxis_mulAdd_s7a4f62b5_v(dest, destOffset, src, srcOffset, _axisy, _axisx, _axisz, _t0, _t1, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, 1.0f - _t1);
        return dest;
    }

    private static void preRotateAxis_mulAdd_s7a4f62b5_v(float[] dest, int destOffset, float[] src, int srcOffset, float _axisy, float _axisx, float _axisz, float _t0, float _t1, float _t2, float _t4, float _t6, float _t11) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_axisy, _t0, _t11 * _t2))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisx * _axisx, _t1))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t4, -(_axisz * _t0))))));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t6, -(_axisx * _t0)))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_axisz, _t0, _t11 * _t4))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisy * _axisy, _t1)))));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisz * _axisz, _t1))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t2, -(_axisy * _t0)))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_axisx, _t0, _t11 * _t6)))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
    }

    public static float[] preRotateQuat(float[] dest, int destOffset, float[] src, int srcOffset, float qX, float qY, float qZ, float qW) {
        if (SimdSupport.USE_FMA) return preRotateQuat_fma(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return preRotateQuat_mulAdd(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
    }

    public static float[] preRotateQuat_fma(float[] dest, int destOffset, float[] src, int srcOffset, float qX, float qY, float qZ, float qW) {
        float _t3 = qX + qX;
        float _t4 = qY + qY;
        float _t5 = qZ + qZ;
        preRotateQuat_fma_s63e88155_v(dest, destOffset, src, srcOffset, qY, qZ, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, Math.fma(-qZ, _t5, 1.0f));
        return dest;
    }

    private static void preRotateQuat_fma_s63e88155_v(float[] dest, int destOffset, float[] src, int srcOffset, float qY, float qZ, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t3, _t6)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t4, _t12)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qY, _t3, -_t7)))));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t4, -_t8)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qY, _t3, _t7)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, _t12)))));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f))), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t3, -_t6)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t4, _t8)))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
    }

    public static float[] preRotateQuat_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float qX, float qY, float qZ, float qW) {
        float _t3 = qX + qX;
        float _t4 = qY + qY;
        float _t5 = qZ + qZ;
        preRotateQuat_mulAdd_s4441ade4_v(dest, destOffset, src, srcOffset, qY, qZ, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, Math.fma(-qZ, _t5, 1.0f));
        return dest;
    }

    private static void preRotateQuat_mulAdd_s4441ade4_v(float[] dest, int destOffset, float[] src, int srcOffset, float qY, float qZ, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t3, _t6))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t4, _t12))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qY, _t3, -_t7)))));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t4, -_t8))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qY, _t3, _t7))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, _t12)))));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t3, -_t6))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t4, _t8)))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
    }

    public static float[] preRotateQuat(float[] dest, int destOffset, float[] src, int srcOffset, float[] q, int qOffset) {
        if (SimdSupport.USE_FMA) return preRotateQuat_fma(dest, destOffset, src, srcOffset, q, qOffset);
        return preRotateQuat_mulAdd(dest, destOffset, src, srcOffset, q, qOffset);
    }

    public static float[] preRotateQuat_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] q, int qOffset) {
        float _qz = q[qOffset + 2];
        float _qy = q[qOffset + 1];
        float _qx = q[qOffset + 0];
        float _qw = q[qOffset + 3];
        float _t3 = _qx + _qx;
        float _t4 = _qy + _qy;
        float _t5 = _qz + _qz;
        preRotateQuat_fma_s59e36b6f_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0f));
        return dest;
    }

    private static void preRotateQuat_fma_s59e36b6f_v(float[] dest, int destOffset, float[] src, int srcOffset, float _qz, float _qy, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t3, _t6)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t4, _t12)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qy, _t3, -_t7)))));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t4, -_t8)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qy, _t3, _t7)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, _t12)))));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f))), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t3, -_t6)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t4, _t8)))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
    }

    public static float[] preRotateQuat_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] q, int qOffset) {
        float _qz = q[qOffset + 2];
        float _qy = q[qOffset + 1];
        float _qx = q[qOffset + 0];
        float _qw = q[qOffset + 3];
        float _t3 = _qx + _qx;
        float _t4 = _qy + _qy;
        float _t5 = _qz + _qz;
        preRotateQuat_mulAdd_s4be95bce_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0f));
        return dest;
    }

    private static void preRotateQuat_mulAdd_s4be95bce_v(float[] dest, int destOffset, float[] src, int srcOffset, float _qz, float _qy, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t3, _t6))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t4, _t12))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qy, _t3, -_t7)))));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t4, -_t8))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qy, _t3, _t7))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, _t12)))));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t3, -_t6))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t4, _t8)))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
    }

    public static float[] preRotateX(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        if (SimdSupport.USE_FMA) return preRotateX_fma(dest, destOffset, src, srcOffset, angle);
        return preRotateX_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static float[] preRotateX_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, (float) Math.cosFromSin(_t0, angle));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = _sv0.fma(_sv1, _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -_t0)));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t0), _sv2.mul(_sv1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preRotateX_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, (float) Math.cosFromSin(_t0, angle));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = _sv0.mul(_sv1).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -_t0)));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t0)).add(_sv2.mul(_sv1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preRotateY(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        if (SimdSupport.USE_FMA) return preRotateY_fma(dest, destOffset, src, srcOffset, angle);
        return preRotateY_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static float[] preRotateY_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, (float) Math.cosFromSin(_t0, angle));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.fma(_sv1, _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t0)));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = _sv2.fma(_sv1, _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -_t0)));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preRotateY_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, (float) Math.cosFromSin(_t0, angle));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.mul(_sv1).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t0)));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = _sv2.mul(_sv1).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -_t0)));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preRotateZ(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        if (SimdSupport.USE_FMA) return preRotateZ_fma(dest, destOffset, src, srcOffset, angle);
        return preRotateZ_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static float[] preRotateZ_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, (float) Math.cosFromSin(_t0, angle));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(_sv1, _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -_t0)));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t0), _sv2.mul(_sv1));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preRotateZ_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, (float) Math.cosFromSin(_t0, angle));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(_sv1).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -_t0)));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t0)).add(_sv2.mul(_sv1));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preScale(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, vX));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(SIMD_SPECIES, vY));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).mul(FloatVector.broadcast(SIMD_SPECIES, vZ));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preScale(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, v[vOffset + 0]));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(SIMD_SPECIES, v[vOffset + 1]));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).mul(FloatVector.broadcast(SIMD_SPECIES, v[vOffset + 2]));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preScale(float[] dest, int destOffset, float[] src, int srcOffset, float s) {
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, s).mul(FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preScaleAround(float[] dest, int destOffset, float[] src, int srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        if (SimdSupport.USE_FMA) return preScaleAround_fma(dest, destOffset, src, srcOffset, s, pivotX, pivotY, pivotZ);
        return preScaleAround_mulAdd(dest, destOffset, src, srcOffset, s, pivotX, pivotY, pivotZ);
    }

    public static float[] preScaleAround_fma(float[] dest, int destOffset, float[] src, int srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        float _t0 = 1.0f - s;
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c0 = _sv0.fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset), _sv1.withLane(3, pivotX * _t0));
        var _c1 = _sv0.fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4), _sv1.withLane(3, pivotY * _t0));
        var _c2 = _sv0.fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8), _sv1.withLane(3, pivotZ * _t0));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preScaleAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        float _t0 = 1.0f - s;
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c0 = _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).add(_sv1.withLane(3, pivotX * _t0));
        var _c1 = _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4)).add(_sv1.withLane(3, pivotY * _t0));
        var _c2 = _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8)).add(_sv1.withLane(3, pivotZ * _t0));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preScaleAround(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float s) {
        if (SimdSupport.USE_FMA) return preScaleAround_fma(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return preScaleAround_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    public static float[] preScaleAround_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float s) {
        float _t0 = 1.0f - s;
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c0 = _sv0.fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset), _sv1.withLane(3, pivot[pivotOffset + 0] * _t0));
        var _c1 = _sv0.fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4), _sv1.withLane(3, pivot[pivotOffset + 1] * _t0));
        var _c2 = _sv0.fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8), _sv1.withLane(3, pivot[pivotOffset + 2] * _t0));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preScaleAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float s) {
        float _t0 = 1.0f - s;
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c0 = _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).add(_sv1.withLane(3, pivot[pivotOffset + 0] * _t0));
        var _c1 = _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4)).add(_sv1.withLane(3, pivot[pivotOffset + 1] * _t0));
        var _c2 = _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8)).add(_sv1.withLane(3, pivot[pivotOffset + 2] * _t0));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preScaleAround(float[] dest, int destOffset, float[] src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        if (SimdSupport.USE_FMA) return preScaleAround_fma(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return preScaleAround_mulAdd(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
    }

    public static float[] preScaleAround_fma(float[] dest, int destOffset, float[] src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, sX).fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset), _sv0.withLane(3, pivotX * (1.0f - sX)));
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, sY).fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4), _sv0.withLane(3, pivotY * (1.0f - sY)));
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, sZ).fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8), _sv0.withLane(3, pivotZ * (1.0f - sZ)));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preScaleAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, sX).mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).add(_sv0.withLane(3, pivotX * (1.0f - sX)));
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, sY).mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4)).add(_sv0.withLane(3, pivotY * (1.0f - sY)));
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, sZ).mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8)).add(_sv0.withLane(3, pivotZ * (1.0f - sZ)));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preScaleAround(float[] dest, int destOffset, float[] src, int srcOffset, float[] s, int sOffset, float[] pivot, int pivotOffset) {
        if (SimdSupport.USE_FMA) return preScaleAround_fma(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return preScaleAround_mulAdd(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    public static float[] preScaleAround_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] s, int sOffset, float[] pivot, int pivotOffset) {
        float _sx = s[sOffset + 0];
        float _sy = s[sOffset + 1];
        float _sz = s[sOffset + 2];
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, _sx).fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset), _sv0.withLane(3, pivot[pivotOffset + 0] * (1.0f - _sx)));
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, _sy).fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4), _sv0.withLane(3, pivot[pivotOffset + 1] * (1.0f - _sy)));
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, _sz).fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8), _sv0.withLane(3, pivot[pivotOffset + 2] * (1.0f - _sz)));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preScaleAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] s, int sOffset, float[] pivot, int pivotOffset) {
        float _sx = s[sOffset + 0];
        float _sy = s[sOffset + 1];
        float _sz = s[sOffset + 2];
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, _sx).mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).add(_sv0.withLane(3, pivot[pivotOffset + 0] * (1.0f - _sx)));
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, _sy).mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4)).add(_sv0.withLane(3, pivot[pivotOffset + 1] * (1.0f - _sy)));
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, _sz).mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8)).add(_sv0.withLane(3, pivot[pivotOffset + 2] * (1.0f - _sz)));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preTranslate(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).add(_sv0.withLane(3, vX));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).add(_sv0.withLane(3, vY));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).add(_sv0.withLane(3, vZ));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preTranslate(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).add(_sv0.withLane(3, v[vOffset + 0]));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).add(_sv0.withLane(3, v[vOffset + 1]));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).add(_sv0.withLane(3, v[vOffset + 2]));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] reflect(float[] dest, int destOffset, float[] src, int srcOffset, float normalX, float normalY, float normalZ) {
        if (SimdSupport.USE_FMA) return reflect_fma(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
        return reflect_mulAdd(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
    }

    public static float[] reflect_fma(float[] dest, int destOffset, float[] src, int srcOffset, float normalX, float normalY, float normalZ) {
        float _self22 = src[srcOffset + 10];
        float _self02 = src[srcOffset + 2];
        float _self00 = src[srcOffset + 0];
        float _self01 = src[srcOffset + 1];
        float _self12 = src[srcOffset + 6];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _t2 = -_self22;
        float _t9 = (normalX + normalX) * normalZ;
        float _t10 = (normalX + normalX) * normalY;
        float _t11 = (normalY + normalY) * normalZ;
        float _t12 = Math.fma(-2.0f, normalX * normalX, 1.0f);
        float _t13 = Math.fma(-2.0f, normalY * normalY, 1.0f);
        float _t14 = Math.fma(-2.0f, normalZ * normalZ, 1.0f);
        reflect_fma_s4659ae95_v(dest, destOffset, _t9, _t11, _t14, -_self02, _self02, _self00 * _t12, _self01 * _t13, _self01 * _t10, _self00 * _t10, -_self01 * _t11 + -(_self00 * _t9), src[srcOffset + 3], -_self12, _self12, _self10 * _t12, _self11 * _t13, _self11 * _t10, _self10 * _t10, -_self11 * _t11 + -(_self10 * _t9), src[srcOffset + 7], _t2 * _t9 + _self20 * _t12 - _self21 * _t10, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10), _self22 * _t14 + (-_self21 * _t11 + -(_self20 * _t9)), src[srcOffset + 11]);
        return dest;
    }

    private static void reflect_fma_s4659ae95_v(float[] dest, int destOffset, float _t9, float _t11, float _t14, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8, float _h9, float _h10, float _h11, float _h12, float _h13, float _h14, float _h15, float _h16, float _h17, float _h18, float _h19) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t9).withLane(1, _t11).withLane(2, _t14);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, _h0).withLane(2, _h1).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, _h2).withLane(1, _h3).sub(FloatVector.broadcast(SIMD_SPECIES, _h4).withLane(1, _h5)).withLane(2, _h6)).withLane(3, _h7);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, _h8).withLane(2, _h9).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, _h10).withLane(1, _h11).sub(FloatVector.broadcast(SIMD_SPECIES, _h12).withLane(1, _h13)).withLane(2, _h14)).withLane(3, _h15);
        var _c2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h16).withLane(1, _h17).withLane(2, _h18).withLane(3, _h19);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
    }

    public static float[] reflect_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float normalX, float normalY, float normalZ) {
        float _self22 = src[srcOffset + 10];
        float _self02 = src[srcOffset + 2];
        float _self00 = src[srcOffset + 0];
        float _self01 = src[srcOffset + 1];
        float _self12 = src[srcOffset + 6];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _t2 = -_self22;
        float _t9 = (normalX + normalX) * normalZ;
        float _t10 = (normalX + normalX) * normalY;
        float _t11 = (normalY + normalY) * normalZ;
        float _t12 = Math.fma(-2.0f, normalX * normalX, 1.0f);
        float _t13 = Math.fma(-2.0f, normalY * normalY, 1.0f);
        float _t14 = Math.fma(-2.0f, normalZ * normalZ, 1.0f);
        reflect_mulAdd_s88ff2b0e_v(dest, destOffset, _t9, _t11, _t14, -_self02, _self02, _self00 * _t12, _self01 * _t13, _self01 * _t10, _self00 * _t10, -_self01 * _t11 + -(_self00 * _t9), src[srcOffset + 3], -_self12, _self12, _self10 * _t12, _self11 * _t13, _self11 * _t10, _self10 * _t10, -_self11 * _t11 + -(_self10 * _t9), src[srcOffset + 7], _t2 * _t9 + _self20 * _t12 - _self21 * _t10, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10), _self22 * _t14 + (-_self21 * _t11 + -(_self20 * _t9)), src[srcOffset + 11]);
        return dest;
    }

    private static void reflect_mulAdd_s88ff2b0e_v(float[] dest, int destOffset, float _t9, float _t11, float _t14, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8, float _h9, float _h10, float _h11, float _h12, float _h13, float _h14, float _h15, float _h16, float _h17, float _h18, float _h19) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t9).withLane(1, _t11).withLane(2, _t14);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, _h0).withLane(2, _h1).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, _h2).withLane(1, _h3).sub(FloatVector.broadcast(SIMD_SPECIES, _h4).withLane(1, _h5)).withLane(2, _h6)).withLane(3, _h7);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, _h8).withLane(2, _h9).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, _h10).withLane(1, _h11).sub(FloatVector.broadcast(SIMD_SPECIES, _h12).withLane(1, _h13)).withLane(2, _h14)).withLane(3, _h15);
        var _c2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h16).withLane(1, _h17).withLane(2, _h18).withLane(3, _h19);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
    }

    public static float[] reflect(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        if (SimdSupport.USE_FMA) return reflect_fma(dest, destOffset, src, srcOffset, normal, normalOffset);
        return reflect_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    public static float[] reflect_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        float _self02 = src[srcOffset + 2];
        float _self00 = src[srcOffset + 0];
        float _self01 = src[srcOffset + 1];
        float _self12 = src[srcOffset + 6];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _normalx = normal[normalOffset + 0];
        float _normalz = normal[normalOffset + 2];
        float _normaly = normal[normalOffset + 1];
        float _t2 = -_self22;
        float _t9 = (_normalx + _normalx) * _normalz;
        float _t10 = (_normalx + _normalx) * _normaly;
        float _t11 = (_normaly + _normaly) * _normalz;
        float _t12 = Math.fma(-2.0f, _normalx * _normalx, 1.0f);
        float _t13 = Math.fma(-2.0f, _normaly * _normaly, 1.0f);
        float _t14 = Math.fma(-2.0f, _normalz * _normalz, 1.0f);
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t9).withLane(1, _t11).withLane(2, _t14);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, -_self02).withLane(2, _self02).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, _self00 * _t12).withLane(1, _self01 * _t13).sub(FloatVector.broadcast(SIMD_SPECIES, _self01 * _t10).withLane(1, _self00 * _t10)).withLane(2, -_self01 * _t11 + -(_self00 * _t9))).withLane(3, src[srcOffset + 3]);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, -_self12).withLane(2, _self12).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, _self10 * _t12).withLane(1, _self11 * _t13).sub(FloatVector.broadcast(SIMD_SPECIES, _self11 * _t10).withLane(1, _self10 * _t10)).withLane(2, -_self11 * _t11 + -(_self10 * _t9))).withLane(3, src[srcOffset + 7]);
        var _c2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t2 * _t9 + _self20 * _t12 - _self21 * _t10).withLane(1, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10)).withLane(2, _self22 * _t14 + (-_self21 * _t11 + -(_self20 * _t9))).withLane(3, src[srcOffset + 11]);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] reflect_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        float _self02 = src[srcOffset + 2];
        float _self00 = src[srcOffset + 0];
        float _self01 = src[srcOffset + 1];
        float _self12 = src[srcOffset + 6];
        float _self10 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self20 = src[srcOffset + 8];
        float _self21 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _normalx = normal[normalOffset + 0];
        float _normalz = normal[normalOffset + 2];
        float _normaly = normal[normalOffset + 1];
        float _t2 = -_self22;
        float _t9 = (_normalx + _normalx) * _normalz;
        float _t10 = (_normalx + _normalx) * _normaly;
        float _t11 = (_normaly + _normaly) * _normalz;
        float _t12 = Math.fma(-2.0f, _normalx * _normalx, 1.0f);
        float _t13 = Math.fma(-2.0f, _normaly * _normaly, 1.0f);
        float _t14 = Math.fma(-2.0f, _normalz * _normalz, 1.0f);
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t9).withLane(1, _t11).withLane(2, _t14);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, -_self02).withLane(2, _self02).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, _self00 * _t12).withLane(1, _self01 * _t13).sub(FloatVector.broadcast(SIMD_SPECIES, _self01 * _t10).withLane(1, _self00 * _t10)).withLane(2, -_self01 * _t11 + -(_self00 * _t9))).withLane(3, src[srcOffset + 3]);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, -_self12).withLane(2, _self12).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, _self10 * _t12).withLane(1, _self11 * _t13).sub(FloatVector.broadcast(SIMD_SPECIES, _self11 * _t10).withLane(1, _self10 * _t10)).withLane(2, -_self11 * _t11 + -(_self10 * _t9))).withLane(3, src[srcOffset + 7]);
        var _c2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t2 * _t9 + _self20 * _t12 - _self21 * _t10).withLane(1, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10)).withLane(2, _self22 * _t14 + (-_self21 * _t11 + -(_self20 * _t9))).withLane(3, src[srcOffset + 11]);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] rotateAround(float[] dest, int destOffset, float[] src, int srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        if (SimdSupport.USE_FMA) return rotateAround_fma(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return rotateAround_mulAdd(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
    }

    public static float[] rotateAround_fma(float[] dest, int destOffset, float[] src, int srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        float _t3 = -pivotZ;
        float _t5 = rotX + rotX;
        float _t6 = rotY + rotY;
        float _t7 = rotZ + rotZ;
        float _t8 = rotW * _t6;
        float _t9 = rotW * _t7;
        float _t10 = rotW * _t5;
        float _t11 = rotZ * _t7;
        float _t18 = Math.fma(rotY, _t5, _t9);
        float _t19 = Math.fma(rotZ, _t6, _t10);
        float _t24 = Math.fma(rotZ, _t5, -_t8);
        float _t26 = Math.fma(rotZ, _t6, -_t10);
        rotateAround_fma_s4369e68d_v(dest, destOffset, src, srcOffset, rotY, pivotX, pivotY, -rotY, -rotX, _t3, _t5, _t6, _t11, Math.fma(-rotZ, _t7, 1.0f), _t18, _t19, Math.fma(rotZ, _t5, _t8), _t24, Math.fma(rotY, _t5, -_t9), _t26, Math.fma(_t3, _t26, Math.fma(pivotY, Math.fma(rotX, _t5, _t11), -(pivotX * _t18))), Math.fma(-pivotY, _t19, Math.fma(pivotZ, Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t24))));
        return dest;
    }

    private static void rotateAround_fma_s4369e68d_v(float[] dest, int destOffset, float[] src, int srcOffset, float rotY, float pivotX, float pivotY, float _t0, float _t2, float _t3, float _t5, float _t6, float _t11, float _t16, float _t18, float _t19, float _t20, float _t24, float _t25, float _t26, float _t40, float _t41) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t6, _t16)).withLane(1, _t25).withLane(2, _t20);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, Math.fma(_t2, _t5, _t16)).withLane(2, _t26).withLane(3, Math.fma(_t3, _t20, Math.fma(pivotX, Math.fma(rotY, _t6, _t11), -(pivotY * _t25))));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, _t19).withLane(2, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0f)));
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3])).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).withLane(3, src[(srcOffset + _li * 4) + 0]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 1] * _t40 + src[(srcOffset + _li * 4) + 2] * _t41))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] rotateAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        float _t3 = -pivotZ;
        float _t5 = rotX + rotX;
        float _t6 = rotY + rotY;
        float _t7 = rotZ + rotZ;
        float _t8 = rotW * _t6;
        float _t9 = rotW * _t7;
        float _t10 = rotW * _t5;
        float _t11 = rotZ * _t7;
        float _t18 = Math.fma(rotY, _t5, _t9);
        float _t19 = Math.fma(rotZ, _t6, _t10);
        float _t24 = Math.fma(rotZ, _t5, -_t8);
        float _t26 = Math.fma(rotZ, _t6, -_t10);
        rotateAround_mulAdd_s41feee86_v(dest, destOffset, src, srcOffset, rotY, pivotX, pivotY, -rotY, -rotX, _t3, _t5, _t6, _t11, Math.fma(-rotZ, _t7, 1.0f), _t18, _t19, Math.fma(rotZ, _t5, _t8), _t24, Math.fma(rotY, _t5, -_t9), _t26, Math.fma(_t3, _t26, Math.fma(pivotY, Math.fma(rotX, _t5, _t11), -(pivotX * _t18))), Math.fma(-pivotY, _t19, Math.fma(pivotZ, Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t24))));
        return dest;
    }

    private static void rotateAround_mulAdd_s41feee86_v(float[] dest, int destOffset, float[] src, int srcOffset, float rotY, float pivotX, float pivotY, float _t0, float _t2, float _t3, float _t5, float _t6, float _t11, float _t16, float _t18, float _t19, float _t20, float _t24, float _t25, float _t26, float _t40, float _t41) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t6, _t16)).withLane(1, _t25).withLane(2, _t20);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, Math.fma(_t2, _t5, _t16)).withLane(2, _t26).withLane(3, Math.fma(_t3, _t20, Math.fma(pivotX, Math.fma(rotY, _t6, _t11), -(pivotY * _t25))));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, _t19).withLane(2, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0f)));
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3])).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).withLane(3, src[(srcOffset + _li * 4) + 0]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 1] * _t40 + src[(srcOffset + _li * 4) + 2] * _t41))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] rotateAround(float[] dest, int destOffset, float[] src, int srcOffset, float[] rot, int rotOffset, float[] pivot, int pivotOffset) {
        if (SimdSupport.USE_FMA) return rotateAround_fma(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
        return rotateAround_mulAdd(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
    }

    public static float[] rotateAround_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] rot, int rotOffset, float[] pivot, int pivotOffset) {
        float _pivotx = pivot[pivotOffset + 0];
        float _roty = rot[rotOffset + 1];
        float _pivoty = pivot[pivotOffset + 1];
        float _rotx = rot[rotOffset + 0];
        float _pivotz = pivot[pivotOffset + 2];
        float _rotz = rot[rotOffset + 2];
        float _rotw = rot[rotOffset + 3];
        float _t3 = -_pivotz;
        float _t5 = _rotx + _rotx;
        float _t6 = _roty + _roty;
        float _t7 = _rotz + _rotz;
        float _t8 = _rotw * _t6;
        float _t9 = _rotw * _t7;
        float _t10 = _rotw * _t5;
        float _t11 = _rotz * _t7;
        float _t18 = Math.fma(_roty, _t5, _t9);
        float _t19 = Math.fma(_rotz, _t6, _t10);
        float _t24 = Math.fma(_rotz, _t5, -_t8);
        float _t26 = Math.fma(_rotz, _t6, -_t10);
        rotateAround_fma_s19b187e0_v(dest, destOffset, src, srcOffset, _pivotx, _roty, _pivoty, -_roty, -_rotx, _t3, _t5, _t6, _t11, Math.fma(-_rotz, _t7, 1.0f), _t18, _t19, Math.fma(_rotz, _t5, _t8), _t24, Math.fma(_roty, _t5, -_t9), _t26, Math.fma(_t3, _t26, Math.fma(_pivoty, Math.fma(_rotx, _t5, _t11), -(_pivotx * _t18))), Math.fma(-_pivoty, _t19, Math.fma(_pivotz, Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t24))));
        return dest;
    }

    private static void rotateAround_fma_s19b187e0_v(float[] dest, int destOffset, float[] src, int srcOffset, float _pivotx, float _roty, float _pivoty, float _t0, float _t2, float _t3, float _t5, float _t6, float _t11, float _t16, float _t18, float _t19, float _t20, float _t24, float _t25, float _t26, float _t40, float _t41) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t6, _t16)).withLane(1, _t25).withLane(2, _t20);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, Math.fma(_t2, _t5, _t16)).withLane(2, _t26).withLane(3, Math.fma(_t3, _t20, Math.fma(_pivotx, Math.fma(_roty, _t6, _t11), -(_pivoty * _t25))));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, _t19).withLane(2, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0f)));
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3])).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).withLane(3, src[(srcOffset + _li * 4) + 0]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 1] * _t40 + src[(srcOffset + _li * 4) + 2] * _t41))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] rotateAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] rot, int rotOffset, float[] pivot, int pivotOffset) {
        float _pivotx = pivot[pivotOffset + 0];
        float _roty = rot[rotOffset + 1];
        float _pivoty = pivot[pivotOffset + 1];
        float _rotx = rot[rotOffset + 0];
        float _pivotz = pivot[pivotOffset + 2];
        float _rotz = rot[rotOffset + 2];
        float _rotw = rot[rotOffset + 3];
        float _t3 = -_pivotz;
        float _t5 = _rotx + _rotx;
        float _t6 = _roty + _roty;
        float _t7 = _rotz + _rotz;
        float _t8 = _rotw * _t6;
        float _t9 = _rotw * _t7;
        float _t10 = _rotw * _t5;
        float _t11 = _rotz * _t7;
        float _t18 = Math.fma(_roty, _t5, _t9);
        float _t19 = Math.fma(_rotz, _t6, _t10);
        float _t24 = Math.fma(_rotz, _t5, -_t8);
        float _t26 = Math.fma(_rotz, _t6, -_t10);
        rotateAround_mulAdd_s89fd6e5b_v(dest, destOffset, src, srcOffset, _pivotx, _roty, _pivoty, -_roty, -_rotx, _t3, _t5, _t6, _t11, Math.fma(-_rotz, _t7, 1.0f), _t18, _t19, Math.fma(_rotz, _t5, _t8), _t24, Math.fma(_roty, _t5, -_t9), _t26, Math.fma(_t3, _t26, Math.fma(_pivoty, Math.fma(_rotx, _t5, _t11), -(_pivotx * _t18))), Math.fma(-_pivoty, _t19, Math.fma(_pivotz, Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t24))));
        return dest;
    }

    private static void rotateAround_mulAdd_s89fd6e5b_v(float[] dest, int destOffset, float[] src, int srcOffset, float _pivotx, float _roty, float _pivoty, float _t0, float _t2, float _t3, float _t5, float _t6, float _t11, float _t16, float _t18, float _t19, float _t20, float _t24, float _t25, float _t26, float _t40, float _t41) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t6, _t16)).withLane(1, _t25).withLane(2, _t20);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, Math.fma(_t2, _t5, _t16)).withLane(2, _t26).withLane(3, Math.fma(_t3, _t20, Math.fma(_pivotx, Math.fma(_roty, _t6, _t11), -(_pivoty * _t25))));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, _t19).withLane(2, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0f)));
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3])).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).withLane(3, src[(srcOffset + _li * 4) + 0]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 1] * _t40 + src[(srcOffset + _li * 4) + 2] * _t41))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] rotateAxis(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (SimdSupport.USE_FMA) return rotateAxis_fma(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return rotateAxis_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    public static float[] rotateAxis_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        rotateAxis_fma_s1712e579_v(dest, destOffset, src, srcOffset, axisX, axisY, axisZ, _t0, _t1, axisX * axisZ, axisX * axisY, axisY * axisZ, 1.0f - _t1);
        return dest;
    }

    private static void rotateAxis_fma_s1712e579_v(float[] dest, int destOffset, float[] src, int srcOffset, float axisX, float axisY, float axisZ, float _t0, float _t1, float _t2, float _t5, float _t6, float _t11) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _t2, -(axisY * _t0))).withLane(1, Math.fma(axisX, _t0, _t11 * _t6)).withLane(2, Math.fma(_t11, axisZ * axisZ, _t1));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, axisX * axisX, _t1)).withLane(1, Math.fma(_t11, _t5, -(axisZ * _t0))).withLane(2, Math.fma(axisY, _t0, _t11 * _t2));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(axisZ, _t0, _t11 * _t5)).withLane(1, Math.fma(_t11, axisY * axisY, _t1)).withLane(2, Math.fma(_t11, _t6, -(axisX * _t0)));
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] rotateAxis_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        rotateAxis_mulAdd_s2eafaf86_v(dest, destOffset, src, srcOffset, axisX, axisY, axisZ, _t0, _t1, axisX * axisZ, axisX * axisY, axisY * axisZ, 1.0f - _t1);
        return dest;
    }

    private static void rotateAxis_mulAdd_s2eafaf86_v(float[] dest, int destOffset, float[] src, int srcOffset, float axisX, float axisY, float axisZ, float _t0, float _t1, float _t2, float _t5, float _t6, float _t11) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _t2, -(axisY * _t0))).withLane(1, Math.fma(axisX, _t0, _t11 * _t6)).withLane(2, Math.fma(_t11, axisZ * axisZ, _t1));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, axisX * axisX, _t1)).withLane(1, Math.fma(_t11, _t5, -(axisZ * _t0))).withLane(2, Math.fma(axisY, _t0, _t11 * _t2));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(axisZ, _t0, _t11 * _t5)).withLane(1, Math.fma(_t11, axisY * axisY, _t1)).withLane(2, Math.fma(_t11, _t6, -(axisX * _t0)));
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] rotateAxis(float[] dest, int destOffset, float[] src, int srcOffset, float[] axis, int axisOffset, float angle) {
        if (SimdSupport.USE_FMA) return rotateAxis_fma(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return rotateAxis_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    public static float[] rotateAxis_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] axis, int axisOffset, float angle) {
        float _axisy = axis[axisOffset + 1];
        float _axisx = axis[axisOffset + 0];
        float _axisz = axis[axisOffset + 2];
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        rotateAxis_fma_s470c9c0d_v(dest, destOffset, src, srcOffset, _axisy, _axisx, _axisz, _t0, _t1, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, 1.0f - _t1);
        return dest;
    }

    private static void rotateAxis_fma_s470c9c0d_v(float[] dest, int destOffset, float[] src, int srcOffset, float _axisy, float _axisx, float _axisz, float _t0, float _t1, float _t2, float _t5, float _t6, float _t11) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _t2, -(_axisy * _t0))).withLane(1, Math.fma(_axisx, _t0, _t11 * _t6)).withLane(2, Math.fma(_t11, _axisz * _axisz, _t1));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _axisx * _axisx, _t1)).withLane(1, Math.fma(_t11, _t5, -(_axisz * _t0))).withLane(2, Math.fma(_axisy, _t0, _t11 * _t2));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_axisz, _t0, _t11 * _t5)).withLane(1, Math.fma(_t11, _axisy * _axisy, _t1)).withLane(2, Math.fma(_t11, _t6, -(_axisx * _t0)));
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] rotateAxis_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] axis, int axisOffset, float angle) {
        float _axisy = axis[axisOffset + 1];
        float _axisx = axis[axisOffset + 0];
        float _axisz = axis[axisOffset + 2];
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        rotateAxis_mulAdd_sed598ce0_v(dest, destOffset, src, srcOffset, _axisy, _axisx, _axisz, _t0, _t1, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, 1.0f - _t1);
        return dest;
    }

    private static void rotateAxis_mulAdd_sed598ce0_v(float[] dest, int destOffset, float[] src, int srcOffset, float _axisy, float _axisx, float _axisz, float _t0, float _t1, float _t2, float _t5, float _t6, float _t11) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _t2, -(_axisy * _t0))).withLane(1, Math.fma(_axisx, _t0, _t11 * _t6)).withLane(2, Math.fma(_t11, _axisz * _axisz, _t1));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _axisx * _axisx, _t1)).withLane(1, Math.fma(_t11, _t5, -(_axisz * _t0))).withLane(2, Math.fma(_axisy, _t0, _t11 * _t2));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_axisz, _t0, _t11 * _t5)).withLane(1, Math.fma(_t11, _axisy * _axisy, _t1)).withLane(2, Math.fma(_t11, _t6, -(_axisx * _t0)));
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] rotateQuat(float[] dest, int destOffset, float[] src, int srcOffset, float qX, float qY, float qZ, float qW) {
        if (SimdSupport.USE_FMA) return rotateQuat_fma(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return rotateQuat_mulAdd(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
    }

    public static float[] rotateQuat_fma(float[] dest, int destOffset, float[] src, int srcOffset, float qX, float qY, float qZ, float qW) {
        float _t3 = qX + qX;
        float _t4 = qY + qY;
        float _t5 = qZ + qZ;
        rotateQuat_fma_sf0d530e4_v(dest, destOffset, src, srcOffset, qY, qZ, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, Math.fma(-qZ, _t5, 1.0f));
        return dest;
    }

    private static void rotateQuat_fma_sf0d530e4_v(float[] dest, int destOffset, float[] src, int srcOffset, float qY, float qZ, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(qZ, _t3, -_t6)).withLane(1, Math.fma(qZ, _t4, _t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(qY, _t3, -_t7)).withLane(2, Math.fma(qZ, _t3, _t6));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(qY, _t3, _t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(qZ, _t4, -_t8));
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] rotateQuat_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float qX, float qY, float qZ, float qW) {
        float _t3 = qX + qX;
        float _t4 = qY + qY;
        float _t5 = qZ + qZ;
        rotateQuat_mulAdd_sb4742b03_v(dest, destOffset, src, srcOffset, qY, qZ, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, Math.fma(-qZ, _t5, 1.0f));
        return dest;
    }

    private static void rotateQuat_mulAdd_sb4742b03_v(float[] dest, int destOffset, float[] src, int srcOffset, float qY, float qZ, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(qZ, _t3, -_t6)).withLane(1, Math.fma(qZ, _t4, _t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(qY, _t3, -_t7)).withLane(2, Math.fma(qZ, _t3, _t6));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(qY, _t3, _t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(qZ, _t4, -_t8));
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] rotateQuat(float[] dest, int destOffset, float[] src, int srcOffset, float[] q, int qOffset) {
        if (SimdSupport.USE_FMA) return rotateQuat_fma(dest, destOffset, src, srcOffset, q, qOffset);
        return rotateQuat_mulAdd(dest, destOffset, src, srcOffset, q, qOffset);
    }

    public static float[] rotateQuat_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] q, int qOffset) {
        float _qz = q[qOffset + 2];
        float _qy = q[qOffset + 1];
        float _qx = q[qOffset + 0];
        float _qw = q[qOffset + 3];
        float _t3 = _qx + _qx;
        float _t4 = _qy + _qy;
        float _t5 = _qz + _qz;
        rotateQuat_fma_s35461ece_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0f));
        return dest;
    }

    private static void rotateQuat_fma_s35461ece_v(float[] dest, int destOffset, float[] src, int srcOffset, float _qz, float _qy, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qz, _t3, -_t6)).withLane(1, Math.fma(_qz, _t4, _t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(_qy, _t3, -_t7)).withLane(2, Math.fma(_qz, _t3, _t6));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qy, _t3, _t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(_qz, _t4, -_t8));
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] rotateQuat_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] q, int qOffset) {
        float _qz = q[qOffset + 2];
        float _qy = q[qOffset + 1];
        float _qx = q[qOffset + 0];
        float _qw = q[qOffset + 3];
        float _t3 = _qx + _qx;
        float _t4 = _qy + _qy;
        float _t5 = _qz + _qz;
        rotateQuat_mulAdd_s809f1e35_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0f));
        return dest;
    }

    private static void rotateQuat_mulAdd_s809f1e35_v(float[] dest, int destOffset, float[] src, int srcOffset, float _qz, float _qy, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qz, _t3, -_t6)).withLane(1, Math.fma(_qz, _t4, _t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(_qy, _t3, -_t7)).withLane(2, Math.fma(_qz, _t3, _t6));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qy, _t3, _t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(_qz, _t4, -_t8));
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] rotateYXZ(float[] dest, int destOffset, float[] src, int srcOffset, float angleY, float angleX, float angleZ) {
        if (SimdSupport.USE_FMA) return rotateYXZ_fma(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return rotateYXZ_mulAdd(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
    }

    public static float[] rotateYXZ_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angleY, float angleX, float angleZ) {
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleY);
        float _t2 = (float) Math.sin(angleZ);
        float _t3 = (float) Math.cosFromSin(_t1, angleY);
        float _t4 = (float) Math.cosFromSin(_t2, angleZ);
        float _t5 = (float) Math.cosFromSin(_t0, angleX);
        float _t8 = _t0 * _t3;
        rotateYXZ_fma_s7bb9f37c_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, _t3, _t4, _t5, _t0 * _t1, _t2 * _t5, _t5 * _t4, _t5 * _t3, Math.fma(_t8, _t4, _t1 * _t2), Math.fma(_t8, _t2, -(_t1 * _t4)));
        return dest;
    }

    private static void rotateYXZ_fma_s7bb9f37c_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t10, float _t16, float _t17, float _t19, float _t20) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t6, _t2, _t3 * _t4)).withLane(1, Math.fma(_t6, _t4, -(_t2 * _t3))).withLane(2, _t1 * _t5);
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1] * _t10).withLane(1, src[(srcOffset + _li * 4) + 1] * _t16).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2] * _t20).withLane(1, src[(srcOffset + _li * 4) + 2] * _t19)).withLane(2, src[(srcOffset + _li * 4) + 2] * _t17 - src[(srcOffset + _li * 4) + 1] * _t0)).withLane(3, src[(srcOffset + _li * 4) + 3]);
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] rotateYXZ_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleY, float angleX, float angleZ) {
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleY);
        float _t2 = (float) Math.sin(angleZ);
        float _t3 = (float) Math.cosFromSin(_t1, angleY);
        float _t4 = (float) Math.cosFromSin(_t2, angleZ);
        float _t5 = (float) Math.cosFromSin(_t0, angleX);
        float _t8 = _t0 * _t3;
        rotateYXZ_mulAdd_sb0d4e247_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, _t3, _t4, _t5, _t0 * _t1, _t2 * _t5, _t5 * _t4, _t5 * _t3, Math.fma(_t8, _t4, _t1 * _t2), Math.fma(_t8, _t2, -(_t1 * _t4)));
        return dest;
    }

    private static void rotateYXZ_mulAdd_sb0d4e247_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t10, float _t16, float _t17, float _t19, float _t20) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t6, _t2, _t3 * _t4)).withLane(1, Math.fma(_t6, _t4, -(_t2 * _t3))).withLane(2, _t1 * _t5);
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1] * _t10).withLane(1, src[(srcOffset + _li * 4) + 1] * _t16).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2] * _t20).withLane(1, src[(srcOffset + _li * 4) + 2] * _t19)).withLane(2, src[(srcOffset + _li * 4) + 2] * _t17 - src[(srcOffset + _li * 4) + 1] * _t0)).withLane(3, src[(srcOffset + _li * 4) + 3]);
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] scale(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        if (SimdSupport.USE_FMA) return scale_fma(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return scale_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    public static float[] scale_fma(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] scale_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] scale(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        if (SimdSupport.USE_FMA) return scale_fma(dest, destOffset, src, srcOffset, v, vOffset);
        return scale_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
    }

    public static float[] scale_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        float _vx = v[vOffset + 0];
        float _vy = v[vOffset + 1];
        float _vz = v[vOffset + 2];
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] scale_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        float _vx = v[vOffset + 0];
        float _vy = v[vOffset + 1];
        float _vz = v[vOffset + 2];
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] scale(float[] dest, int destOffset, float[] src, int srcOffset, float s) {
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, s).mul(FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4))).withLane(3, src[(srcOffset + _li * 4) + 3]);
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] scaleAround(float[] dest, int destOffset, float[] src, int srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        float _t3 = pivotZ * _t0;
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, s).mul(FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4))).withLane(3, Math.fma(src[(srcOffset + _li * 4) + 0], _t1, Math.fma(src[(srcOffset + _li * 4) + 1], _t2, Math.fma(src[(srcOffset + _li * 4) + 2], _t3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] scaleAround(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float s) {
        float _pivotx = pivot[pivotOffset + 0];
        float _pivoty = pivot[pivotOffset + 1];
        float _pivotz = pivot[pivotOffset + 2];
        float _t0 = 1.0f - s;
        float _t1 = _pivotx * _t0;
        float _t2 = _pivoty * _t0;
        float _t3 = _pivotz * _t0;
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, s).mul(FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4))).withLane(3, Math.fma(src[(srcOffset + _li * 4) + 0], _t1, Math.fma(src[(srcOffset + _li * 4) + 1], _t2, Math.fma(src[(srcOffset + _li * 4) + 2], _t3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] scaleAround(float[] dest, int destOffset, float[] src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        if (SimdSupport.USE_FMA) return scaleAround_fma(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return scaleAround_mulAdd(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
    }

    public static float[] scaleAround_fma(float[] dest, int destOffset, float[] src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        float _t3 = pivotX * (1.0f - sX);
        float _t4 = pivotY * (1.0f - sY);
        float _t5 = pivotZ * (1.0f - sZ);
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, sX).withLane(1, sY).withLane(2, sZ);
        for (int _li = 0; _li < 3; _li++) {
            var _c = _sv0.fma(FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)), FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, Math.fma(src[(srcOffset + _li * 4) + 0], _t3, Math.fma(src[(srcOffset + _li * 4) + 1], _t4, Math.fma(src[(srcOffset + _li * 4) + 2], _t5, src[(srcOffset + _li * 4) + 3])))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] scaleAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        float _t3 = pivotX * (1.0f - sX);
        float _t4 = pivotY * (1.0f - sY);
        float _t5 = pivotZ * (1.0f - sZ);
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, sX).withLane(1, sY).withLane(2, sZ);
        for (int _li = 0; _li < 3; _li++) {
            var _c = _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4))).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, Math.fma(src[(srcOffset + _li * 4) + 0], _t3, Math.fma(src[(srcOffset + _li * 4) + 1], _t4, Math.fma(src[(srcOffset + _li * 4) + 2], _t5, src[(srcOffset + _li * 4) + 3])))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] scaleAround(float[] dest, int destOffset, float[] src, int srcOffset, float[] s, int sOffset, float[] pivot, int pivotOffset) {
        if (SimdSupport.USE_FMA) return scaleAround_fma(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return scaleAround_mulAdd(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    public static float[] scaleAround_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] s, int sOffset, float[] pivot, int pivotOffset) {
        float _sx = s[sOffset + 0];
        float _sy = s[sOffset + 1];
        float _sz = s[sOffset + 2];
        float _t3 = pivot[pivotOffset + 0] * (1.0f - _sx);
        float _t4 = pivot[pivotOffset + 1] * (1.0f - _sy);
        float _t5 = pivot[pivotOffset + 2] * (1.0f - _sz);
        scaleAround_fma_s90c0ca99_v(dest, destOffset, src, srcOffset, _sx, _sy, _sz, Math.fma(src[srcOffset + 0], _t3, Math.fma(src[srcOffset + 1], _t4, Math.fma(src[srcOffset + 2], _t5, src[srcOffset + 3]))), Math.fma(src[srcOffset + 4], _t3, Math.fma(src[srcOffset + 5], _t4, Math.fma(src[srcOffset + 6], _t5, src[srcOffset + 7]))), Math.fma(src[srcOffset + 8], _t3, Math.fma(src[srcOffset + 9], _t4, Math.fma(src[srcOffset + 10], _t5, src[srcOffset + 11]))));
        return dest;
    }

    private static void scaleAround_fma_s90c0ca99_v(float[] dest, int destOffset, float[] src, int srcOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c0 = _sv0.fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset), _sv1.withLane(3, _h3));
        var _c1 = _sv0.fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4), _sv1.withLane(3, _h4));
        var _c2 = _sv0.fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8), _sv1.withLane(3, _h5));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
    }

    public static float[] scaleAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] s, int sOffset, float[] pivot, int pivotOffset) {
        float _sx = s[sOffset + 0];
        float _sy = s[sOffset + 1];
        float _sz = s[sOffset + 2];
        float _t3 = pivot[pivotOffset + 0] * (1.0f - _sx);
        float _t4 = pivot[pivotOffset + 1] * (1.0f - _sy);
        float _t5 = pivot[pivotOffset + 2] * (1.0f - _sz);
        scaleAround_mulAdd_s92dc23e0_v(dest, destOffset, src, srcOffset, _sx, _sy, _sz, Math.fma(src[srcOffset + 0], _t3, Math.fma(src[srcOffset + 1], _t4, Math.fma(src[srcOffset + 2], _t5, src[srcOffset + 3]))), Math.fma(src[srcOffset + 4], _t3, Math.fma(src[srcOffset + 5], _t4, Math.fma(src[srcOffset + 6], _t5, src[srcOffset + 7]))), Math.fma(src[srcOffset + 8], _t3, Math.fma(src[srcOffset + 9], _t4, Math.fma(src[srcOffset + 10], _t5, src[srcOffset + 11]))));
        return dest;
    }

    private static void scaleAround_mulAdd_s92dc23e0_v(float[] dest, int destOffset, float[] src, int srcOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c0 = _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).add(_sv1.withLane(3, _h3));
        var _c1 = _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4)).add(_sv1.withLane(3, _h4));
        var _c2 = _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8)).add(_sv1.withLane(3, _h5));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
    }

    public static float[] translate(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 0] * vX + (src[(srcOffset + _li * 4) + 1] * vY + src[(srcOffset + _li * 4) + 2] * vZ)));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] translate(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        float _vx = v[vOffset + 0];
        float _vy = v[vOffset + 1];
        float _vz = v[vOffset + 2];
        for (int _li = 0; _li < 3; _li++) {
            var _c = FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 0] * _vx + (src[(srcOffset + _li * 4) + 1] * _vy + src[(srcOffset + _li * 4) + 2] * _vz)));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] transformPosition_vecArr(float[] dest, int destOffset, float[] matrix, int matrixOffset, float[] points, int pointsOffset, int count) {
        float _m00 = matrix[matrixOffset + 0];
        float _m01 = matrix[matrixOffset + 1];
        float _m02 = matrix[matrixOffset + 2];
        float _m03 = matrix[matrixOffset + 3];
        float _m10 = matrix[matrixOffset + 4];
        float _m11 = matrix[matrixOffset + 5];
        float _m12 = matrix[matrixOffset + 6];
        float _m13 = matrix[matrixOffset + 7];
        float _m20 = matrix[matrixOffset + 8];
        float _m21 = matrix[matrixOffset + 9];
        float _m22 = matrix[matrixOffset + 10];
        float _m23 = matrix[matrixOffset + 11];
        var _sp = FloatVector.SPECIES_128;
        var _c0 = FloatVector.zero(_sp).withLane(0, _m00).withLane(1, _m10).withLane(2, _m20);
        var _c1 = FloatVector.zero(_sp).withLane(0, _m01).withLane(1, _m11).withLane(2, _m21);
        var _c2 = FloatVector.zero(_sp).withLane(0, _m02).withLane(1, _m12).withLane(2, _m22);
        var _c3 = FloatVector.zero(_sp).withLane(0, _m03).withLane(1, _m13).withLane(2, _m23);
        float px = points[pointsOffset + 0], py = points[pointsOffset + 1], pz = points[pointsOffset + 2];
        int _i = 0;
        for (; _i < count - 1; _i++) {
            var _v = FloatVector.broadcast(_sp, px).fma(_c0, FloatVector.broadcast(_sp, py).fma(_c1, FloatVector.broadcast(_sp, pz).fma(_c2, _c3)));
            int _pn = pointsOffset + (_i + 1) * 3;
            px = points[_pn + 0]; py = points[_pn + 1]; pz = points[_pn + 2];
            _v.intoArray(dest, destOffset + _i * 3);
        }
        int _do = destOffset + _i * 3;
        dest[_do + 0] = Math.fma(_m00, px, Math.fma(_m01, py, Math.fma(_m02, pz, _m03)));
        dest[_do + 1] = Math.fma(_m10, px, Math.fma(_m11, py, Math.fma(_m12, pz, _m13)));
        dest[_do + 2] = Math.fma(_m20, px, Math.fma(_m21, py, Math.fma(_m22, pz, _m23)));
        return dest;
    }

    public static float[] transformDirection_vecArr(float[] dest, int destOffset, float[] matrix, int matrixOffset, float[] points, int pointsOffset, int count) {
        float _m00 = matrix[matrixOffset + 0];
        float _m01 = matrix[matrixOffset + 1];
        float _m02 = matrix[matrixOffset + 2];
        float _m10 = matrix[matrixOffset + 4];
        float _m11 = matrix[matrixOffset + 5];
        float _m12 = matrix[matrixOffset + 6];
        float _m20 = matrix[matrixOffset + 8];
        float _m21 = matrix[matrixOffset + 9];
        float _m22 = matrix[matrixOffset + 10];
        var _sp = FloatVector.SPECIES_128;
        var _c0 = FloatVector.zero(_sp).withLane(0, _m00).withLane(1, _m10).withLane(2, _m20);
        var _c1 = FloatVector.zero(_sp).withLane(0, _m01).withLane(1, _m11).withLane(2, _m21);
        var _c2 = FloatVector.zero(_sp).withLane(0, _m02).withLane(1, _m12).withLane(2, _m22);
        float px = points[pointsOffset + 0], py = points[pointsOffset + 1], pz = points[pointsOffset + 2];
        int _i = 0;
        for (; _i < count - 1; _i++) {
            var _v = FloatVector.broadcast(_sp, pz).fma(_c2, FloatVector.broadcast(_sp, px).fma(_c0, FloatVector.broadcast(_sp, py).mul(_c1)));
            int _pn = pointsOffset + (_i + 1) * 3;
            px = points[_pn + 0]; py = points[_pn + 1]; pz = points[_pn + 2];
            _v.intoArray(dest, destOffset + _i * 3);
        }
        int _do = destOffset + _i * 3;
        dest[_do + 0] = Math.fma(_m02, pz, Math.fma(_m00, px, _m01 * py));
        dest[_do + 1] = Math.fma(_m12, pz, Math.fma(_m10, px, _m11 * py));
        dest[_do + 2] = Math.fma(_m22, pz, Math.fma(_m20, px, _m21 * py));
        return dest;
    }

    public static float[] lerpComposeTRSMul_fma(float[] dest, int destOffset, float[] t1, int t1Offset, float[] t2, int t2Offset, float[] q1, int q1Offset, float[] q2, int q2Offset, float[] s1, int s1Offset, float[] s2, int s2Offset, float[] m, int mOffset, float alpha, int count) {
        var _sp = FloatVector.SPECIES_128;
        for (int _i = 0; _i < count; _i++) {
            int _t1o = t1Offset + _i * 3;
            int _t2o = t2Offset + _i * 3;
            int _q1o = q1Offset + _i * 4;
            int _q2o = q2Offset + _i * 4;
            int _s1o = s1Offset + _i * 3;
            int _s2o = s2Offset + _i * 3;
            int _mo = mOffset + _i * 12;
            int _do = destOffset + _i * 12;
            float _ax = t1[_t1o + 0], _ay = t1[_t1o + 1], _az = t1[_t1o + 2];
            float _tx = Math.fma(alpha, t2[_t2o + 0] - _ax, _ax);
            float _ty = Math.fma(alpha, t2[_t2o + 1] - _ay, _ay);
            float _tz = Math.fma(alpha, t2[_t2o + 2] - _az, _az);
            float _bx = s1[_s1o + 0], _by = s1[_s1o + 1], _bz = s1[_s1o + 2];
            float _sx = Math.fma(alpha, s2[_s2o + 0] - _bx, _bx);
            float _sy = Math.fma(alpha, s2[_s2o + 1] - _by, _by);
            float _sz = Math.fma(alpha, s2[_s2o + 2] - _bz, _bz);
            float _ux = q1[_q1o + 0], _uy = q1[_q1o + 1], _uz = q1[_q1o + 2], _uw = q1[_q1o + 3];
            float _vx = q2[_q2o + 0], _vy = q2[_q2o + 1], _vz = q2[_q2o + 2], _vw = q2[_q2o + 3];
            float _dot = Math.fma(_uw, _vw, Math.fma(_uz, _vz, Math.fma(_ux, _vx, _uy * _vy)));
            if (_dot < 0.0f) { _vx = -_vx; _vy = -_vy; _vz = -_vz; _vw = -_vw; }
            float _qx = Math.fma(alpha, _vx - _ux, _ux);
            float _qy = Math.fma(alpha, _vy - _uy, _uy);
            float _qz = Math.fma(alpha, _vz - _uz, _uz);
            float _qw = Math.fma(alpha, _vw - _uw, _uw);
            float _len2 = (_qx * _qx + _qy * _qy) + (_qz * _qz + _qw * _qw);
            float _ninv = _len2 > 0.0f ? 1.0f / (float) Math.sqrt(_len2) : 0.0f;
            _qx *= _ninv; _qy *= _ninv; _qz *= _ninv; _qw *= _ninv;
            float _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
            float _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
            float _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
            float _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
            float _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
            var _r0 = FloatVector.fromArray(_sp, m, _mo);
            var _r1 = FloatVector.fromArray(_sp, m, _mo + 4);
            var _r2 = FloatVector.fromArray(_sp, m, _mo + 8);
            var _c0 = _r2.fma(FloatVector.broadcast(_sp, _t02), _r1.fma(FloatVector.broadcast(_sp, _t01), _r0.fma(FloatVector.broadcast(_sp, _t00), FloatVector.zero(_sp).withLane(3, _tx))));
            var _c1 = _r2.fma(FloatVector.broadcast(_sp, _t12), _r1.fma(FloatVector.broadcast(_sp, _t11), _r0.fma(FloatVector.broadcast(_sp, _t10), FloatVector.zero(_sp).withLane(3, _ty))));
            var _c2 = _r2.fma(FloatVector.broadcast(_sp, _t22), _r1.fma(FloatVector.broadcast(_sp, _t21), _r0.fma(FloatVector.broadcast(_sp, _t20), FloatVector.zero(_sp).withLane(3, _tz))));
            _c0.intoArray(dest, _do);
            _c1.intoArray(dest, _do + 4);
            _c2.intoArray(dest, _do + 8);
        }
        return dest;
    }

    public static float[] composeTRSMul_fma(float[] dest, int destOffset, float[] translation, int translationOffset, float[] rotation, int rotationOffset, float[] scale, int scaleOffset, float[] m, int mOffset, int count) {
        var _sp = FloatVector.SPECIES_128;
        for (int _i = 0; _i < count; _i++) {
            int _translationo = translationOffset + _i * 3;
            int _rotationo = rotationOffset + _i * 4;
            int _scaleo = scaleOffset + _i * 3;
            int _mo = mOffset + _i * 12;
            int _do = destOffset + _i * 12;
            float _tx = translation[_translationo + 0], _ty = translation[_translationo + 1], _tz = translation[_translationo + 2];
            float _sx = scale[_scaleo + 0], _sy = scale[_scaleo + 1], _sz = scale[_scaleo + 2];
            float _qx = rotation[_rotationo + 0], _qy = rotation[_rotationo + 1], _qz = rotation[_rotationo + 2], _qw = rotation[_rotationo + 3];
            float _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
            float _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
            float _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
            float _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
            float _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
            var _r0 = FloatVector.fromArray(_sp, m, _mo);
            var _r1 = FloatVector.fromArray(_sp, m, _mo + 4);
            var _r2 = FloatVector.fromArray(_sp, m, _mo + 8);
            var _c0 = _r2.fma(FloatVector.broadcast(_sp, _t02), _r1.fma(FloatVector.broadcast(_sp, _t01), _r0.fma(FloatVector.broadcast(_sp, _t00), FloatVector.zero(_sp).withLane(3, _tx))));
            var _c1 = _r2.fma(FloatVector.broadcast(_sp, _t12), _r1.fma(FloatVector.broadcast(_sp, _t11), _r0.fma(FloatVector.broadcast(_sp, _t10), FloatVector.zero(_sp).withLane(3, _ty))));
            var _c2 = _r2.fma(FloatVector.broadcast(_sp, _t22), _r1.fma(FloatVector.broadcast(_sp, _t21), _r0.fma(FloatVector.broadcast(_sp, _t20), FloatVector.zero(_sp).withLane(3, _tz))));
            _c0.intoArray(dest, _do);
            _c1.intoArray(dest, _do + 4);
            _c2.intoArray(dest, _do + 8);
        }
        return dest;
    }

    private static final VectorShuffle<Float> CTRSP_S0 = VectorShuffle.fromValues(SIMD_SPECIES, 0, 0, 0, 0);
    private static final VectorShuffle<Float> CTRSP_S1 = VectorShuffle.fromValues(SIMD_SPECIES, 1, 1, 1, 1);
    private static final VectorShuffle<Float> CTRSP_S2 = VectorShuffle.fromValues(SIMD_SPECIES, 2, 2, 2, 2);
    private static final VectorShuffle<Float> CTRSP_W0 = VectorShuffle.fromValues(SIMD_SPECIES, 3, 3, 3, 0);
    private static final VectorShuffle<Float> CTRSP_W1 = VectorShuffle.fromValues(SIMD_SPECIES, 3, 3, 3, 1);
    private static final VectorShuffle<Float> CTRSP_W2 = VectorShuffle.fromValues(SIMD_SPECIES, 3, 3, 3, 2);

    public static float[] composeTRSMulPadded_fma(float[] dest, int destOffset, float[] translation, int translationOffset, float[] rotation, int rotationOffset, float[] scale, int scaleOffset, float[] m, int mOffset) {
        float _qx = rotation[rotationOffset + 0], _qy = rotation[rotationOffset + 1], _qz = rotation[rotationOffset + 2], _qw = rotation[rotationOffset + 3];
        float _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
        float _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
        float _r00 = 1 - 2 * (_yy + _zz), _r01 = 2 * (_xy - _zw), _r02 = 2 * (_xz + _yw);
        float _r10 = 2 * (_xy + _zw), _r11 = 1 - 2 * (_xx + _zz), _r12 = 2 * (_yz - _xw);
        float _r20 = 2 * (_xz - _yw), _r21 = 2 * (_yz + _xw), _r22 = 1 - 2 * (_xx + _yy);
        var _sv = FloatVector.fromArray(SIMD_SPECIES, scale, scaleOffset);
        var _tv = FloatVector.fromArray(SIMD_SPECIES, translation, translationOffset);
        var _m0 = FloatVector.fromArray(SIMD_SPECIES, m, mOffset).mul(_sv.rearrange(CTRSP_S0));
        var _m1 = FloatVector.fromArray(SIMD_SPECIES, m, mOffset + 4).mul(_sv.rearrange(CTRSP_S1));
        var _m2 = FloatVector.fromArray(SIMD_SPECIES, m, mOffset + 8).mul(_sv.rearrange(CTRSP_S2));
        var _c0 = _m2.fma(FloatVector.broadcast(SIMD_SPECIES, _r02), _m1.fma(FloatVector.broadcast(SIMD_SPECIES, _r01), _m0.fma(FloatVector.broadcast(SIMD_SPECIES, _r00), _tv.rearrange(CTRSP_W0))));
        var _c1 = _m2.fma(FloatVector.broadcast(SIMD_SPECIES, _r12), _m1.fma(FloatVector.broadcast(SIMD_SPECIES, _r11), _m0.fma(FloatVector.broadcast(SIMD_SPECIES, _r10), _tv.rearrange(CTRSP_W1))));
        var _c2 = _m2.fma(FloatVector.broadcast(SIMD_SPECIES, _r22), _m1.fma(FloatVector.broadcast(SIMD_SPECIES, _r21), _m0.fma(FloatVector.broadcast(SIMD_SPECIES, _r20), _tv.rearrange(CTRSP_W2))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
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
        if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, src, srcOffset).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset + 8).intoArray(dest, destOffset + 8);
        }
        else {
            FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset + 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset + 8).intoArray(dest, destOffset + 8);
        }
    }


    public static float[] copy(float[] dest, int destOffset, float[] src, int srcOffset) {
        copyArrArr_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        copyArrArr(dest, destOffset, src, srcOffset, count * 12);
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr_one(dest, destOffset, _srcArr, _srcOff);
        } else {
            for (int _i = 0; _i < 12; _i++)
                dest[destOffset + _i] = src.get(srcOffset + _i);
        }
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (src.hasArray() && srcOffset >= 0 && (count > 178956970 ? -1 : count * 12) >= 0 && srcOffset <= src.limit() - (count > 178956970 ? -1 : count * 12)) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr(dest, destOffset, _srcArr, _srcOff, count * 12);
        } else {
            for (int _i = 0; _i < count * 12; _i++)
                dest[destOffset + _i] = src.get(srcOffset + _i);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, float[] src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr_one(_destArr, _destOff, src, srcOffset);
        } else {
            for (int _i = 0; _i < 12; _i++)
                dest.put(destOffset + _i, src[srcOffset + _i]);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 178956970 ? -1 : count * 12) >= 0 && destOffset <= dest.limit() - (count > 178956970 ? -1 : count * 12)) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr(_destArr, _destOff, src, srcOffset, count * 12);
        } else {
            for (int _i = 0; _i < count * 12; _i++)
                dest.put(destOffset + _i, src[srcOffset + _i]);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr_one(_destArr, _destOff, _srcArr, _srcOff);
            } else {
                for (int _i = 0; _i < 12; _i++)
                    _destArr[_destOff + _i] = src.get(srcOffset + _i);
            }
        } else {
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                for (int _i = 0; _i < 12; _i++)
                    dest.put(destOffset + _i, _srcArr[_srcOff + _i]);
            } else {
                for (int _i = 0; _i < 12; _i++)
                    dest.put(destOffset + _i, src.get(srcOffset + _i));
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 178956970 ? -1 : count * 12) >= 0 && destOffset <= dest.limit() - (count > 178956970 ? -1 : count * 12)) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && (count > 178956970 ? -1 : count * 12) >= 0 && srcOffset <= src.limit() - (count > 178956970 ? -1 : count * 12)) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr(_destArr, _destOff, _srcArr, _srcOff, count * 12);
            } else {
                for (int _i = 0; _i < count * 12; _i++)
                    _destArr[_destOff + _i] = src.get(srcOffset + _i);
            }
        } else {
            if (src.hasArray() && srcOffset >= 0 && (count > 178956970 ? -1 : count * 12) >= 0 && srcOffset <= src.limit() - (count > 178956970 ? -1 : count * 12)) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                for (int _i = 0; _i < count * 12; _i++)
                    dest.put(destOffset + _i, _srcArr[_srcOff + _i]);
            } else {
                for (int _i = 0; _i < count * 12; _i++)
                    dest.put(destOffset + _i, src.get(srcOffset + _i));
            }
        }
        return dest;
    }
}
