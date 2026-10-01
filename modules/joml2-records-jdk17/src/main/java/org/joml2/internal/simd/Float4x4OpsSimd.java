// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.simd;

import jdk.incubator.vector.*;
import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Vector-API isolation cell for {@link Float4x4Ops}: every
 * {@code jdk.incubator.vector} reference of the Ops family lives in this class,
 * which is loaded and initialized only behind {@code SimdSupport.VECTOR_API}
 * guards - {@code Float4x4Ops} and its kernel siblings link
 * and run without the incubator module. Not public API.
 */
public final class Float4x4OpsSimd {
    private Float4x4OpsSimd() {}
    private static final VectorSpecies<Float> SIMD_SPECIES = FloatVector.SPECIES_128;
    private static final int PREFERRED_LANES = FloatVector.SPECIES_PREFERRED.length();
    private static final FloatVector UNIT_W = FloatVector.fromArray(SIMD_SPECIES, new float[]{0.0f, 0.0f, 0.0f, 1.0f}, 0);

    public static float[] add(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.fromArray(SIMD_SPECIES, other, (otherOffset + _li * 4)).add(FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] mul(float[] dest, int destOffset, float[] src, int srcOffset, float scalar) {
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, scalar).mul(FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] negate(float[] dest, int destOffset, float[] src, int srcOffset) {
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)).neg();
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] sub(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)).sub(FloatVector.fromArray(SIMD_SPECIES, other, (otherOffset + _li * 4)));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] set(float[] dest, int destOffset, float[] v, int vOffset) {
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.fromArray(SIMD_SPECIES, v, (vOffset + _li * 4));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] withTranslation(float[] dest, int destOffset, float[] src, int srcOffset, float tX, float tY, float tZ) {
        float _self33 = src[srcOffset + 15];
        var _vcp0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _vcp1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _vcp2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        _vcp0.intoArray(dest, destOffset);
        _vcp1.intoArray(dest, destOffset + 4);
        _vcp2.intoArray(dest, destOffset + 8);
        dest[destOffset + 12] = tX;
        dest[destOffset + 13] = tY;
        dest[destOffset + 14] = tZ;
        dest[destOffset + 15] = _self33;
        return dest;
    }

    public static float[] withTranslation(float[] dest, int destOffset, float[] src, int srcOffset, float[] t, int tOffset) {
        float _self33 = src[srcOffset + 15];
        float _tx = t[tOffset + 0];
        float _ty = t[tOffset + 1];
        float _tz = t[tOffset + 2];
        var _vcp0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _vcp1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _vcp2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        _vcp0.intoArray(dest, destOffset);
        _vcp1.intoArray(dest, destOffset + 4);
        _vcp2.intoArray(dest, destOffset + 8);
        dest[destOffset + 12] = _tx;
        dest[destOffset + 13] = _ty;
        dest[destOffset + 14] = _tz;
        dest[destOffset + 15] = _self33;
        return dest;
    }

    public static float[] lerp(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static float[] lerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, t).fma(FloatVector.fromArray(SIMD_SPECIES, other, (otherOffset + _li * 4)).sub(FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4))), FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] lerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        for (int _li = 0; _li < 4; _li++) {
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
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, right[(rightOffset + _li * 4) + 3]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, right[(rightOffset + _li * 4) + 2]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, right[(rightOffset + _li * 4) + 0]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, right[(rightOffset + _li * 4) + 1]).mul(_sv3))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] mul_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, right[(rightOffset + _li * 4) + 3]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, right[(rightOffset + _li * 4) + 2]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, right[(rightOffset + _li * 4) + 0]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, right[(rightOffset + _li * 4) + 1]).mul(_sv3))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] mulMat2x2(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        if (SimdSupport.USE_FMA) return mulMat2x2_fma(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat2x2_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static float[] mulMat2x2_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 0]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 1]).mul(_sv1));
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 2]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 3]).mul(_sv1));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mulMat2x2_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 0]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 1]).mul(_sv1));
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 2]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 3]).mul(_sv1));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mulMat2x3(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        if (SimdSupport.USE_FMA) return mulMat2x3_fma(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat2x3_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static float[] mulMat2x3_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 0]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 1]).mul(_sv1));
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 2]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 3]).mul(_sv1));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 4]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 5]).fma(_sv1, FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mulMat2x3_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 0]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 1]).mul(_sv1));
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 2]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 3]).mul(_sv1));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 4]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 5]).mul(_sv1).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mulMat3x3(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        if (SimdSupport.USE_FMA) return mulMat3x3_fma(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat3x3_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static float[] mulMat3x3_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 2]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 0]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 1]).mul(_sv2)));
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 5]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 3]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 4]).mul(_sv2)));
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 8]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 6]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 7]).mul(_sv2)));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mulMat3x3_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 2]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 0]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 1]).mul(_sv2)));
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 5]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 3]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 4]).mul(_sv2)));
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 8]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 6]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, right[rightOffset + 7]).mul(_sv2)));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mulMat3x4(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        if (SimdSupport.USE_FMA) return mulMat3x4_fma(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat3x4_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static float[] mulMat3x4_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        mulMat3x4_fma_s328875c1_v(dest, destOffset, src, srcOffset, right[rightOffset + 8], right[rightOffset + 0], right[rightOffset + 4], right[rightOffset + 9], right[rightOffset + 1], right[rightOffset + 5], right[rightOffset + 10], right[rightOffset + 2], right[rightOffset + 6], right[rightOffset + 3], right[rightOffset + 7], right[rightOffset + 11]);
        return dest;
    }

    private static void mulMat3x4_fma_s328875c1_v(float[] dest, int destOffset, float[] src, int srcOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8, float _h9, float _h10, float _h11) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, _h0).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, _h1).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, _h2).mul(_sv2)));
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, _h3).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, _h4).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, _h5).mul(_sv2)));
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, _h6).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, _h7).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, _h8).mul(_sv2)));
        var _c3 = FloatVector.broadcast(SIMD_SPECIES, _h9).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, _h10).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, _h11).fma(_sv0, FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] mulMat3x4_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        mulMat3x4_mulAdd_s49f53474_v(dest, destOffset, src, srcOffset, right[rightOffset + 8], right[rightOffset + 0], right[rightOffset + 4], right[rightOffset + 9], right[rightOffset + 1], right[rightOffset + 5], right[rightOffset + 10], right[rightOffset + 2], right[rightOffset + 6], right[rightOffset + 3], right[rightOffset + 7], right[rightOffset + 11]);
        return dest;
    }

    private static void mulMat3x4_mulAdd_s49f53474_v(float[] dest, int destOffset, float[] src, int srcOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8, float _h9, float _h10, float _h11) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, _h0).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, _h1).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, _h2).mul(_sv2)));
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, _h3).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, _h4).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, _h5).mul(_sv2)));
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, _h6).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, _h7).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, _h8).mul(_sv2)));
        var _c3 = FloatVector.broadcast(SIMD_SPECIES, _h9).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, _h10).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, _h11).mul(_sv0).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] preMul(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        if (SimdSupport.USE_FMA) return preMul_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMul_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static float[] preMul_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + 12);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + 8);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, other, otherOffset);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + 4);
        for (int _li = 0; _li < 4; _li++) {
            var _c = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]), _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1])))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preMul_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + 12);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + 8);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, other, otherOffset);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + 4);
        for (int _li = 0; _li < 4; _li++) {
            var _c = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3])).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2])).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0])).add(_sv3.mul(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1])))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preMulMat3x3(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        if (SimdSupport.USE_FMA) return preMulMat3x3_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat3x3_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static float[] preMulMat3x3_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _other02 = other[otherOffset + 6];
        float _other12 = other[otherOffset + 7];
        float _other22 = other[otherOffset + 8];
        float _other00 = other[otherOffset + 0];
        float _other10 = other[otherOffset + 1];
        float _other20 = other[otherOffset + 2];
        float _other01 = other[otherOffset + 3];
        float _other11 = other[otherOffset + 4];
        float _other21 = other[otherOffset + 5];
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _other02).withLane(1, _other12).withLane(2, _other22);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _other00).withLane(1, _other10).withLane(2, _other20);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _other01).withLane(1, _other11).withLane(2, _other21);
        for (int _li = 0; _li < 4; _li++) {
            var _c = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]), FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preMulMat3x3_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _other02 = other[otherOffset + 6];
        float _other12 = other[otherOffset + 7];
        float _other22 = other[otherOffset + 8];
        float _other00 = other[otherOffset + 0];
        float _other10 = other[otherOffset + 1];
        float _other20 = other[otherOffset + 2];
        float _other01 = other[otherOffset + 3];
        float _other11 = other[otherOffset + 4];
        float _other21 = other[otherOffset + 5];
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _other02).withLane(1, _other12).withLane(2, _other22);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _other00).withLane(1, _other10).withLane(2, _other20);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _other01).withLane(1, _other11).withLane(2, _other21);
        for (int _li = 0; _li < 4; _li++) {
            var _c = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2])).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0])).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1])).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preMulMat3x4(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        if (SimdSupport.USE_FMA) return preMulMat3x4_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat3x4_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static float[] preMulMat3x4_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        preMulMat3x4_fma_s38ab7552_v(dest, destOffset, src, srcOffset, other[otherOffset + 3], other[otherOffset + 7], other[otherOffset + 11], other[otherOffset + 2], other[otherOffset + 6], other[otherOffset + 10], other[otherOffset + 0], other[otherOffset + 4], other[otherOffset + 8], other[otherOffset + 1], other[otherOffset + 5], other[otherOffset + 9]);
        return dest;
    }

    private static void preMulMat3x4_fma_s38ab7552_v(float[] dest, int destOffset, float[] src, int srcOffset, float _other03, float _other13, float _other23, float _other02, float _other12, float _other22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _other03).withLane(1, _other13).withLane(2, _other23);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _other02).withLane(1, _other12).withLane(2, _other22);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _other00).withLane(1, _other10).withLane(2, _other20);
        var _sv3 = FloatVector.zero(SIMD_SPECIES).withLane(0, _other01).withLane(1, _other11).withLane(2, _other21);
        for (int _li = 0; _li < 4; _li++) {
            var _c = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]), _sv3.fma(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]), FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3])))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] preMulMat3x4_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        preMulMat3x4_mulAdd_s68bf9a65_v(dest, destOffset, src, srcOffset, other[otherOffset + 3], other[otherOffset + 7], other[otherOffset + 11], other[otherOffset + 2], other[otherOffset + 6], other[otherOffset + 10], other[otherOffset + 0], other[otherOffset + 4], other[otherOffset + 8], other[otherOffset + 1], other[otherOffset + 5], other[otherOffset + 9]);
        return dest;
    }

    private static void preMulMat3x4_mulAdd_s68bf9a65_v(float[] dest, int destOffset, float[] src, int srcOffset, float _other03, float _other13, float _other23, float _other02, float _other12, float _other22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _other03).withLane(1, _other13).withLane(2, _other23);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _other02).withLane(1, _other12).withLane(2, _other22);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _other00).withLane(1, _other10).withLane(2, _other20);
        var _sv3 = FloatVector.zero(SIMD_SPECIES).withLane(0, _other01).withLane(1, _other11).withLane(2, _other21);
        for (int _li = 0; _li < 4; _li++) {
            var _c = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3])).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2])).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0])).add(_sv3.mul(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1])).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3])))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] addScaled(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float weight) {
        if (SimdSupport.USE_FMA) return addScaled_fma(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return addScaled_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    public static float[] addScaled_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float weight) {
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, weight).fma(FloatVector.fromArray(SIMD_SPECIES, other, (otherOffset + _li * 4)), FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] addScaled_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float weight) {
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, weight).mul(FloatVector.fromArray(SIMD_SPECIES, other, (otherOffset + _li * 4))).add(FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] makeOuterProduct(float[] dest, int destOffset, float colX, float colY, float colZ, float colW, float rowX, float rowY, float rowZ, float rowW) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, colX).withLane(1, colY).withLane(2, colZ).withLane(3, colW);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, rowX));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, rowY));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, rowZ));
        var _c3 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, rowW));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] makeOuterProduct(float[] dest, int destOffset, float[] col, int colOffset, float[] row, int rowOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, col, colOffset);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, row[rowOffset + 0]));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, row[rowOffset + 1]));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, row[rowOffset + 2]));
        var _c3 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, row[rowOffset + 3]));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] arcball(float[] dest, int destOffset, float[] src, int srcOffset, float radius, float centerX, float centerY, float centerZ, float angleX, float angleY) {
        if (SimdSupport.USE_FMA) return arcball_fma(dest, destOffset, src, srcOffset, radius, centerX, centerY, centerZ, angleX, angleY);
        return arcball_mulAdd(dest, destOffset, src, srcOffset, radius, centerX, centerY, centerZ, angleX, angleY);
    }

    public static float[] arcball_fma(float[] dest, int destOffset, float[] src, int srcOffset, float radius, float centerX, float centerY, float centerZ, float angleX, float angleY) {
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleX);
        float _t4 = (float) Math.cosFromSin(_t1, angleX);
        float _t5 = (float) Math.cosFromSin(_t0, angleY);
        arcball_fma_s76935e4c_v(dest, destOffset, src, srcOffset, radius, centerX, centerZ, _t0, _t1, -centerZ, -centerY, _t4, _t5, _t1 * _t0, _t0 * _t4, _t1 * _t5, _t4 * _t5);
        return dest;
    }

    private static void arcball_fma_s76935e4c_v(float[] dest, int destOffset, float[] src, int srcOffset, float radius, float centerX, float centerZ, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t7, float _t8, float _t12) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, -_t7), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t5), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t6))));
        var _c1 = _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _t4), _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t1)));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t12), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t0), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -_t8))));
        var _c3 = _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t0, -(centerX * _t5))), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(centerZ, _t8, Math.fma(_t3, _t4, -(centerX * _t6)))), _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(centerX, _t7, Math.fma(_t3, _t1, Math.fma(_t2, _t12, -radius)))), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] arcball_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float radius, float centerX, float centerY, float centerZ, float angleX, float angleY) {
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleX);
        float _t2 = -centerZ;
        float _t3 = -centerY;
        float _t4 = (float) Math.cosFromSin(_t1, angleX);
        float _t5 = (float) Math.cosFromSin(_t0, angleY);
        float _t6 = _t1 * _t0;
        float _t7 = _t0 * _t4;
        float _t8 = _t1 * _t5;
        float _t12 = _t4 * _t5;
        arcball_mulAdd_s5c111b23_v(dest, destOffset, src, srcOffset, _t0, _t1, _t4, _t5, _t6, _t12, -_t7, -_t8, Math.fma(_t2, _t0, -(centerX * _t5)), Math.fma(centerZ, _t8, Math.fma(_t3, _t4, -(centerX * _t6))), Math.fma(centerX, _t7, Math.fma(_t3, _t1, Math.fma(_t2, _t12, -radius))));
        return dest;
    }

    private static void arcball_mulAdd_s5c111b23_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t4, float _t5, float _t6, float _t12, float _h0, float _h1, float _h2, float _h3, float _h4) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h0)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t5)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t6))));
        var _c1 = _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t4)).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t1)));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t12)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t0)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h1))));
        var _c3 = _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h2)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h3)).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h4)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] arcball(float[] dest, int destOffset, float[] src, int srcOffset, float[] center, int centerOffset, float radius, float angleX, float angleY) {
        if (SimdSupport.USE_FMA) return arcball_fma(dest, destOffset, src, srcOffset, center, centerOffset, radius, angleX, angleY);
        return arcball_mulAdd(dest, destOffset, src, srcOffset, center, centerOffset, radius, angleX, angleY);
    }

    public static float[] arcball_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] center, int centerOffset, float radius, float angleX, float angleY) {
        float _centerz = center[centerOffset + 2];
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleX);
        float _t4 = (float) Math.cosFromSin(_t1, angleX);
        float _t5 = (float) Math.cosFromSin(_t0, angleY);
        arcball_fma_s415c43b4_v(dest, destOffset, src, srcOffset, radius, center[centerOffset + 0], _centerz, _t0, _t1, -_centerz, -center[centerOffset + 1], _t4, _t5, _t1 * _t0, _t0 * _t4, _t1 * _t5, _t4 * _t5);
        return dest;
    }

    private static void arcball_fma_s415c43b4_v(float[] dest, int destOffset, float[] src, int srcOffset, float radius, float _centerx, float _centerz, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t7, float _t8, float _t12) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, -_t7), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t5), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t6))));
        var _c1 = _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _t4), _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t1)));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t12), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t0), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -_t8))));
        var _c3 = _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t0, -(_centerx * _t5))), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_centerz, _t8, Math.fma(_t3, _t4, -(_centerx * _t6)))), _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_centerx, _t7, Math.fma(_t3, _t1, Math.fma(_t2, _t12, -radius)))), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] arcball_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] center, int centerOffset, float radius, float angleX, float angleY) {
        float _centerz = center[centerOffset + 2];
        float _centerx = center[centerOffset + 0];
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleX);
        float _t2 = -_centerz;
        float _t3 = -center[centerOffset + 1];
        float _t4 = (float) Math.cosFromSin(_t1, angleX);
        float _t5 = (float) Math.cosFromSin(_t0, angleY);
        float _t6 = _t1 * _t0;
        float _t7 = _t0 * _t4;
        float _t8 = _t1 * _t5;
        float _t12 = _t4 * _t5;
        arcball_mulAdd_s5ff6ee89_v(dest, destOffset, src, srcOffset, _t0, _t1, _t4, _t5, _t6, _t12, -_t7, -_t8, Math.fma(_t2, _t0, -(_centerx * _t5)), Math.fma(_centerz, _t8, Math.fma(_t3, _t4, -(_centerx * _t6))), Math.fma(_centerx, _t7, Math.fma(_t3, _t1, Math.fma(_t2, _t12, -radius))));
        return dest;
    }

    private static void arcball_mulAdd_s5ff6ee89_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t4, float _t5, float _t6, float _t12, float _h0, float _h1, float _h2, float _h3, float _h4) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h0)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t5)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t6))));
        var _c1 = _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t4)).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t1)));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t12)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t0)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h1))));
        var _c3 = _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h2)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h3)).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h4)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] axonometricDimetric(float[] dest, int destOffset, float[] src, int srcOffset, float alpha) {
        if (SimdSupport.USE_FMA) return axonometricDimetric_fma(dest, destOffset, src, srcOffset, alpha);
        return axonometricDimetric_mulAdd(dest, destOffset, src, srcOffset, alpha);
    }

    public static float[] axonometricDimetric_fma(float[] dest, int destOffset, float[] src, int srcOffset, float alpha) {
        float _t0 = (float) Math.sin(alpha);
        float _t1 = (float) Math.sqrt(2.0f);
        float _t2 = (float) Math.cosFromSin(_t0, alpha);
        float _t9 = 0.5f * _t0 * _t1;
        float _t10 = 0.5f * _t2 * _t1;
        axonometricDimetric_fma_s24461cee_v(dest, destOffset, src, srcOffset, _t0, _t2, _t9, _t10, -_t10, src[srcOffset + 0] * _t1, src[srcOffset + 1] * _t1, src[srcOffset + 2] * _t1, src[srcOffset + 3] * _t1, -_t9);
        return dest;
    }

    private static void axonometricDimetric_fma_s24461cee_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t2, float _t9, float _t10, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 0.5f);
        var _sv3 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h1).withLane(1, _h2).withLane(2, _h3).withLane(3, _h4);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h0), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t9), _sv2.mul(_sv3)));
        var _c1 = _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t2), _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t0)));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t10), _sv2.fma(_sv3, _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h5))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] axonometricDimetric_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float alpha) {
        float _t0 = (float) Math.sin(alpha);
        float _t1 = (float) Math.sqrt(2.0f);
        float _t2 = (float) Math.cosFromSin(_t0, alpha);
        axonometricDimetric_mulAdd_s88ecb097_v(dest, destOffset, src, srcOffset, src[srcOffset + 0], src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 3], _t0, _t1, _t2, 0.5f * _t0 * _t1, 0.5f * _t2 * _t1);
        return dest;
    }

    private static void axonometricDimetric_mulAdd_s88ecb097_v(float[] dest, int destOffset, float[] src, int srcOffset, float _self00, float _self10, float _self20, float _self30, float _t0, float _t1, float _t2, float _t9, float _t10) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 0.5f);
        var _sv3 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self00 * _t1).withLane(1, _self10 * _t1).withLane(2, _self20 * _t1).withLane(3, _self30 * _t1);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -_t10)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t9)).add(_sv2.mul(_sv3)));
        var _c1 = _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t2)).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t0)));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t10)).add(_sv2.mul(_sv3).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -_t9))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] axonometricIsometric(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self00 = src[srcOffset + 0];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        var _vcp0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        float _t0 = (float) Math.sqrt(3.0f);
        float _t1 = (float) Math.sqrt(2.0f);
        float _t2 = (float) Math.sqrt(6.0f);
        float _sp0 = 0.16666667f * _t2;
        float _t3 = _self02 * _t0;
        float _t4 = _self00 * _t1;
        float _t6 = _self12 * _t0;
        float _t7 = _self10 * _t1;
        float _t9 = _self22 * _t0;
        float _t10 = _self20 * _t1;
        float _t12 = _self32 * _t0;
        float _t13 = _self30 * _t1;
        float _t15 = _sp0 * _self01;
        float _t16 = _sp0 * _self11;
        float _t17 = _sp0 * _self21;
        float _t18 = _sp0 * _self31;
        dest[destOffset + 0] = Math.fma(-0.33333334f, _t3, Math.fma(0.5f, _t4, _t15));
        dest[destOffset + 1] = Math.fma(-0.33333334f, _t6, Math.fma(0.5f, _t7, _t16));
        dest[destOffset + 2] = Math.fma(-0.33333334f, _t9, Math.fma(0.5f, _t10, _t17));
        dest[destOffset + 3] = Math.fma(-0.33333334f, _t12, Math.fma(0.5f, _t13, _t18));
        dest[destOffset + 4] = 0.33333334f * Math.fma(_self01, _t2, _t3);
        dest[destOffset + 5] = 0.33333334f * Math.fma(_self11, _t2, _t6);
        dest[destOffset + 6] = 0.33333334f * Math.fma(_self21, _t2, _t9);
        dest[destOffset + 7] = 0.33333334f * Math.fma(_self31, _t2, _t12);
        dest[destOffset + 8] = Math.fma(0.33333334f, _t3, Math.fma(0.5f, _t4, -_t15));
        dest[destOffset + 9] = Math.fma(0.33333334f, _t6, Math.fma(0.5f, _t7, -_t16));
        dest[destOffset + 10] = Math.fma(0.33333334f, _t9, Math.fma(0.5f, _t10, -_t17));
        dest[destOffset + 11] = Math.fma(0.33333334f, _t12, Math.fma(0.5f, _t13, -_t18));
        _vcp0.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] axonometricTrimetric(float[] dest, int destOffset, float[] src, int srcOffset, float alphaX, float alphaY) {
        if (SimdSupport.USE_FMA) return axonometricTrimetric_fma(dest, destOffset, src, srcOffset, alphaX, alphaY);
        return axonometricTrimetric_mulAdd(dest, destOffset, src, srcOffset, alphaX, alphaY);
    }

    public static float[] axonometricTrimetric_fma(float[] dest, int destOffset, float[] src, int srcOffset, float alphaX, float alphaY) {
        float _t0 = (float) Math.sin(alphaY);
        float _t1 = (float) Math.sin(alphaX);
        float _t2 = (float) Math.cosFromSin(_t1, alphaX);
        float _t3 = (float) Math.cosFromSin(_t0, alphaY);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, -(_t0 * _t2)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t3), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t1 * _t0))));
        var _c1 = _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _t2), _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t1)));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t2 * _t3), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t0), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -(_t1 * _t3)))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] axonometricTrimetric_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float alphaX, float alphaY) {
        float _t0 = (float) Math.sin(alphaY);
        float _t1 = (float) Math.sin(alphaX);
        float _t2 = (float) Math.cosFromSin(_t1, alphaX);
        float _t3 = (float) Math.cosFromSin(_t0, alphaY);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -(_t0 * _t2))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t3)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t1 * _t0))));
        var _c1 = _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t2)).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t1)));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t2 * _t3)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t0)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -(_t1 * _t3)))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        composeTRSMul_fma_sde574f0a_v(dest, destOffset, m, mOffset, translationX, translationY, translationZ, Math.fma(rotationX, rotationZ, _t3) * _t0, Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t0, Math.fma(-Math.fma(rotationX, rotationX, rotationY * rotationY), _t0, scaleZ), Math.fma(-Math.fma(rotationY, rotationY, _t4), _t1, scaleX), Math.fma(rotationX, rotationY, _t5) * _t1, Math.fma(rotationX, rotationZ, -_t3) * _t1, Math.fma(rotationX, rotationY, -_t5) * _t2, Math.fma(-Math.fma(rotationX, rotationX, _t4), _t2, scaleY), Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t2);
        return dest;
    }

    private static void composeTRSMul_fma_sde574f0a_v(float[] dest, int destOffset, float[] m, int mOffset, float translationX, float translationY, float translationZ, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, translationX).withLane(1, translationY).withLane(2, translationZ);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h3).withLane(1, _h4).withLane(2, _h5);
        var _sv3 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h6).withLane(1, _h7).withLane(2, _h8);
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 3]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 2]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 0]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 1]).fma(_sv3, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, m[(mOffset + _li * 4) + 3])))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] composeTRSMul_mulAdd(float[] dest, int destOffset, float[] m, int mOffset, float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ) {
        float _t0 = scaleZ + scaleZ;
        float _t1 = scaleX + scaleX;
        float _t2 = scaleY + scaleY;
        float _t3 = rotationY * rotationW;
        float _t4 = rotationZ * rotationZ;
        float _t5 = rotationZ * rotationW;
        composeTRSMul_mulAdd_sd5c5c337_v(dest, destOffset, m, mOffset, translationX, translationY, translationZ, Math.fma(rotationX, rotationZ, _t3) * _t0, Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t0, Math.fma(-Math.fma(rotationX, rotationX, rotationY * rotationY), _t0, scaleZ), Math.fma(-Math.fma(rotationY, rotationY, _t4), _t1, scaleX), Math.fma(rotationX, rotationY, _t5) * _t1, Math.fma(rotationX, rotationZ, -_t3) * _t1, Math.fma(rotationX, rotationY, -_t5) * _t2, Math.fma(-Math.fma(rotationX, rotationX, _t4), _t2, scaleY), Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t2);
        return dest;
    }

    private static void composeTRSMul_mulAdd_sd5c5c337_v(float[] dest, int destOffset, float[] m, int mOffset, float translationX, float translationY, float translationZ, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, translationX).withLane(1, translationY).withLane(2, translationZ);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h3).withLane(1, _h4).withLane(2, _h5);
        var _sv3 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h6).withLane(1, _h7).withLane(2, _h8);
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 3]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 2]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 0]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 1]).mul(_sv3).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, m[(mOffset + _li * 4) + 3])))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
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
        composeTRSMul_fma_s9c518a4d_v(dest, destOffset, m, mOffset, translation[translationOffset + 0], translation[translationOffset + 1], translation[translationOffset + 2], Math.fma(_rotationx, _rotationz, _t3) * _t0, Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t0, Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t0, _scalez), Math.fma(-Math.fma(_rotationy, _rotationy, _t4), _t1, _scalex), Math.fma(_rotationx, _rotationy, _t5) * _t1, Math.fma(_rotationx, _rotationz, -_t3) * _t1, Math.fma(_rotationx, _rotationy, -_t5) * _t2, Math.fma(-Math.fma(_rotationx, _rotationx, _t4), _t2, _scaley), Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t2);
        return dest;
    }

    private static void composeTRSMul_fma_s9c518a4d_v(float[] dest, int destOffset, float[] m, int mOffset, float _translationx, float _translationy, float _translationz, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _translationx).withLane(1, _translationy).withLane(2, _translationz);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h3).withLane(1, _h4).withLane(2, _h5);
        var _sv3 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h6).withLane(1, _h7).withLane(2, _h8);
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 3]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 2]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 0]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 1]).fma(_sv3, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, m[(mOffset + _li * 4) + 3])))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
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
        composeTRSMul_mulAdd_sc1e6fe58_v(dest, destOffset, m, mOffset, translation[translationOffset + 0], translation[translationOffset + 1], translation[translationOffset + 2], Math.fma(_rotationx, _rotationz, _t3) * _t0, Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t0, Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t0, _scalez), Math.fma(-Math.fma(_rotationy, _rotationy, _t4), _t1, _scalex), Math.fma(_rotationx, _rotationy, _t5) * _t1, Math.fma(_rotationx, _rotationz, -_t3) * _t1, Math.fma(_rotationx, _rotationy, -_t5) * _t2, Math.fma(-Math.fma(_rotationx, _rotationx, _t4), _t2, _scaley), Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t2);
        return dest;
    }

    private static void composeTRSMul_mulAdd_sc1e6fe58_v(float[] dest, int destOffset, float[] m, int mOffset, float _translationx, float _translationy, float _translationz, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _translationx).withLane(1, _translationy).withLane(2, _translationz);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h3).withLane(1, _h4).withLane(2, _h5);
        var _sv3 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h6).withLane(1, _h7).withLane(2, _h8);
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 3]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 2]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 0]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, m[(mOffset + _li * 4) + 1]).mul(_sv3).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, m[(mOffset + _li * 4) + 3])))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] frustum_no_lh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        if (SimdSupport.USE_FMA) return frustum_no_lh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return frustum_no_lh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static float[] frustum_no_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t0 = zNear + zNear;
        float _t4_inv = 1.0f / (zNear - zFar);
        float _w0, _w1;
        if (zFar == Float.POSITIVE_INFINITY) {
            _w0 = 1.0f;
            _w1 = -_t0;
        } else {
            if (zNear == Float.POSITIVE_INFINITY) {
                _w0 = -1.0f;
                _w1 = zFar + zFar;
            } else {
                _w0 = -((zFar + zNear) * _t4_inv);
                _w1 = (zFar + zFar) * zNear * _t4_inv;
            }
        }
        frustum_no_lh_fma_s53dd5caf_v(dest, destOffset, src, srcOffset, left, right, bottom, top, _t0, _w0, _w1);
        return dest;
    }

    private static void frustum_no_lh_fma_s53dd5caf_v(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float _t0, float _w0, float _w1) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, _t0);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (right - left));
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (top - bottom));
        var _sv5 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.mul(_sv1).mul(_sv2);
        var _c1 = _sv3.mul(_sv1).mul(_sv4);
        var _c2 = _sv5.fma(FloatVector.broadcast(SIMD_SPECIES, _w0), _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, -(bottom + top))).fma(_sv4, _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -(left + right))).fma(_sv2, FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        var _c3 = _sv5.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] frustum_no_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t0 = zNear + zNear;
        float _t4_inv = 1.0f / (zNear - zFar);
        float _w0, _w1;
        if (zFar == Float.POSITIVE_INFINITY) {
            _w0 = 1.0f;
            _w1 = -_t0;
        } else {
            if (zNear == Float.POSITIVE_INFINITY) {
                _w0 = -1.0f;
                _w1 = zFar + zFar;
            } else {
                _w0 = -((zFar + zNear) * _t4_inv);
                _w1 = (zFar + zFar) * zNear * _t4_inv;
            }
        }
        frustum_no_lh_mulAdd_se594eb50_v(dest, destOffset, src, srcOffset, left, right, bottom, top, _t0, _w0, _w1);
        return dest;
    }

    private static void frustum_no_lh_mulAdd_se594eb50_v(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float _t0, float _w0, float _w1) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, _t0);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (right - left));
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (top - bottom));
        var _sv5 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.mul(_sv1).mul(_sv2);
        var _c1 = _sv3.mul(_sv1).mul(_sv4);
        var _c2 = _sv5.mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(_sv3.mul(FloatVector.broadcast(SIMD_SPECIES, -(bottom + top))).mul(_sv4).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -(left + right))).mul(_sv2).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        var _c3 = _sv5.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] frustum_no_rh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        if (SimdSupport.USE_FMA) return frustum_no_rh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return frustum_no_rh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static float[] frustum_no_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t0 = zNear + zNear;
        float _t4_inv = 1.0f / (zNear - zFar);
        float _w0, _w1;
        if (zFar == Float.POSITIVE_INFINITY) {
            _w0 = -1.0f;
            _w1 = -_t0;
        } else {
            if (zNear == Float.POSITIVE_INFINITY) {
                _w0 = 1.0f;
                _w1 = zFar + zFar;
            } else {
                _w0 = (zFar + zNear) * _t4_inv;
                _w1 = (zFar + zFar) * zNear * _t4_inv;
            }
        }
        frustum_no_rh_fma_s2a506905_v(dest, destOffset, src, srcOffset, left, right, bottom, top, _t0, _w0, _w1);
        return dest;
    }

    private static void frustum_no_rh_fma_s2a506905_v(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float _t0, float _w0, float _w1) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, _t0);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (right - left));
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (top - bottom));
        var _sv5 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.mul(_sv1).mul(_sv2);
        var _c1 = _sv3.mul(_sv1).mul(_sv4);
        var _c2 = _sv5.fma(FloatVector.broadcast(SIMD_SPECIES, _w0), _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, left + right)).fma(_sv2, _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, bottom + top)).mul(_sv4)).sub(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        var _c3 = _sv5.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] frustum_no_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t0 = zNear + zNear;
        float _t4_inv = 1.0f / (zNear - zFar);
        float _w0, _w1;
        if (zFar == Float.POSITIVE_INFINITY) {
            _w0 = -1.0f;
            _w1 = -_t0;
        } else {
            if (zNear == Float.POSITIVE_INFINITY) {
                _w0 = 1.0f;
                _w1 = zFar + zFar;
            } else {
                _w0 = (zFar + zNear) * _t4_inv;
                _w1 = (zFar + zFar) * zNear * _t4_inv;
            }
        }
        frustum_no_rh_mulAdd_sb644bac6_v(dest, destOffset, src, srcOffset, left, right, bottom, top, _t0, _w0, _w1);
        return dest;
    }

    private static void frustum_no_rh_mulAdd_sb644bac6_v(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float _t0, float _w0, float _w1) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, _t0);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (right - left));
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (top - bottom));
        var _sv5 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.mul(_sv1).mul(_sv2);
        var _c1 = _sv3.mul(_sv1).mul(_sv4);
        var _c2 = _sv5.mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, left + right)).mul(_sv2).add(_sv3.mul(FloatVector.broadcast(SIMD_SPECIES, bottom + top)).mul(_sv4)).sub(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        var _c3 = _sv5.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] frustum_zo_lh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        if (SimdSupport.USE_FMA) return frustum_zo_lh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return frustum_zo_lh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static float[] frustum_zo_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t3_inv = 1.0f / (zNear - zFar);
        float _w0, _w1;
        if (zFar == Float.POSITIVE_INFINITY) {
            _w0 = 1.0f;
            _w1 = -zNear;
        } else {
            if (zNear == Float.POSITIVE_INFINITY) {
                _w0 = 0.0f;
                _w1 = zFar;
            } else {
                _w0 = -(zFar * _t3_inv);
                _w1 = zFar * zNear * _t3_inv;
            }
        }
        frustum_zo_lh_fma_s5083c323_v(dest, destOffset, src, srcOffset, _w0, _w1, zNear + zNear, 1.0f / (right - left), 1.0f / (top - bottom), -(bottom + top), -(left + right));
        return dest;
    }

    private static void frustum_zo_lh_fma_s5083c323_v(float[] dest, int destOffset, float[] src, int srcOffset, float _w0, float _w1, float _h0, float _h1, float _h2, float _h3, float _h4) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, _h0);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, _h1);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = FloatVector.broadcast(SIMD_SPECIES, _h2);
        var _sv5 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.mul(_sv1).mul(_sv2);
        var _c1 = _sv3.mul(_sv1).mul(_sv4);
        var _c2 = _sv5.fma(FloatVector.broadcast(SIMD_SPECIES, _w0), _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, _h3)).fma(_sv4, _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h4)).fma(_sv2, FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        var _c3 = _sv5.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] frustum_zo_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t3_inv = 1.0f / (zNear - zFar);
        float _w0, _w1;
        if (zFar == Float.POSITIVE_INFINITY) {
            _w0 = 1.0f;
            _w1 = -zNear;
        } else {
            if (zNear == Float.POSITIVE_INFINITY) {
                _w0 = 0.0f;
                _w1 = zFar;
            } else {
                _w0 = -(zFar * _t3_inv);
                _w1 = zFar * zNear * _t3_inv;
            }
        }
        frustum_zo_lh_mulAdd_sb7de5344_v(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, _w0, _w1);
        return dest;
    }

    private static void frustum_zo_lh_mulAdd_sb7de5344_v(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float _w0, float _w1) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, zNear + zNear);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (right - left));
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (top - bottom));
        var _sv5 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.mul(_sv1).mul(_sv2);
        var _c1 = _sv3.mul(_sv1).mul(_sv4);
        var _c2 = _sv5.mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(_sv3.mul(FloatVector.broadcast(SIMD_SPECIES, -(bottom + top))).mul(_sv4).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -(left + right))).mul(_sv2).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        var _c3 = _sv5.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] frustum_zo_rh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        if (SimdSupport.USE_FMA) return frustum_zo_rh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return frustum_zo_rh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static float[] frustum_zo_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t3_inv = 1.0f / (zNear - zFar);
        float _w0, _w1;
        if (zFar == Float.POSITIVE_INFINITY) {
            _w0 = -1.0f;
            _w1 = -zNear;
        } else {
            if (zNear == Float.POSITIVE_INFINITY) {
                _w0 = 0.0f;
                _w1 = zFar;
            } else {
                _w0 = zFar * _t3_inv;
                _w1 = zFar * zNear * _t3_inv;
            }
        }
        frustum_zo_rh_fma_sbac695a1_v(dest, destOffset, src, srcOffset, _w0, _w1, zNear + zNear, 1.0f / (right - left), 1.0f / (top - bottom), left + right, bottom + top);
        return dest;
    }

    private static void frustum_zo_rh_fma_sbac695a1_v(float[] dest, int destOffset, float[] src, int srcOffset, float _w0, float _w1, float _h0, float _h1, float _h2, float _h3, float _h4) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, _h0);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, _h1);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = FloatVector.broadcast(SIMD_SPECIES, _h2);
        var _sv5 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.mul(_sv1).mul(_sv2);
        var _c1 = _sv3.mul(_sv1).mul(_sv4);
        var _c2 = _sv5.fma(FloatVector.broadcast(SIMD_SPECIES, _w0), _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h3)).fma(_sv2, _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, _h4)).mul(_sv4)).sub(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        var _c3 = _sv5.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] frustum_zo_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t3_inv = 1.0f / (zNear - zFar);
        float _w0, _w1;
        if (zFar == Float.POSITIVE_INFINITY) {
            _w0 = -1.0f;
            _w1 = -zNear;
        } else {
            if (zNear == Float.POSITIVE_INFINITY) {
                _w0 = 0.0f;
                _w1 = zFar;
            } else {
                _w0 = zFar * _t3_inv;
                _w1 = zFar * zNear * _t3_inv;
            }
        }
        frustum_zo_rh_mulAdd_s822efde2_v(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, _w0, _w1);
        return dest;
    }

    private static void frustum_zo_rh_mulAdd_s822efde2_v(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float _w0, float _w1) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, zNear + zNear);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (right - left));
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (top - bottom));
        var _sv5 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.mul(_sv1).mul(_sv2);
        var _c1 = _sv3.mul(_sv1).mul(_sv4);
        var _c2 = _sv5.mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, left + right)).mul(_sv2).add(_sv3.mul(FloatVector.broadcast(SIMD_SPECIES, bottom + top)).mul(_sv4)).sub(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        var _c3 = _sv5.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t37), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t38), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t39))));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t39, _t12, -(_t38 * _t13))), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t37, _t13, -(_t39 * _t11))), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t38, _t11, -(_t37 * _t12))))));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t11), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t12), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t13))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t37)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t38)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t39))));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t39, _t12, -(_t38 * _t13)))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t37, _t13, -(_t39 * _t11)))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t38, _t11, -(_t37 * _t12))))));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t11)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t12)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t13))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t37), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t38), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t39))));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t39, _t12, -(_t38 * _t13))), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t37, _t13, -(_t39 * _t11))), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t38, _t11, -(_t37 * _t12))))));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t11), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t12), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t13))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t37)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t38)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t39))));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t39, _t12, -(_t38 * _t13)))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t37, _t13, -(_t39 * _t11)))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t38, _t11, -(_t37 * _t12))))));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t11)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t12)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t13))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t14), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t43), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t54))));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t16), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t45), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t55))));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t15), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t44), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t56))));
        var _c3 = _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(eyeZ, _t44, Math.fma(eyeX, _t43, eyeY * _t45))), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(eyeZ, _t56, Math.fma(eyeX, _t54, eyeY * _t55))), _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(eyeZ, _t15, Math.fma(eyeX, _t14, eyeY * _t16))), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t14)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t43)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t54))));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t16)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t45)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t55))));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t15)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t44)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t56))));
        var _c3 = _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(eyeZ, _t44, Math.fma(eyeX, _t43, eyeY * _t45)))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(eyeZ, _t56, Math.fma(eyeX, _t54, eyeY * _t55)))).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(eyeZ, _t15, Math.fma(eyeX, _t14, eyeY * _t16)))).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] lookAt_rh(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (SimdSupport.USE_FMA) return lookAt_rh_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return lookAt_rh_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static float[] lookAt_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _t4 = centerZ - eyeZ;
        float _t5 = centerX - eyeX;
        float _t6 = centerY - eyeY;
        float _t13 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        float _t14 = (1.0f / (float) Math.sqrt(_t13));
        float _t18, _t19, _t20;
        if (_t13 != 0.0f) {
            _t18 = _t5 * _t14;
            _t19 = _t4 * _t14;
            _t20 = _t6 * _t14;
        } else {
            _t18 = 0.0f;
            _t19 = 0.0f;
            _t20 = 0.0f;
        }
        float _t27 = -Math.fma(upZ, _t19, Math.fma(upX, _t18, upY * _t20));
        float _t28 = Math.fma(_t27, _t20, upY);
        float _t29 = Math.fma(_t27, _t18, upX);
        float _t30 = Math.fma(_t27, _t19, upZ);
        float _t37 = Math.fma(_t28, _t18, -(_t29 * _t20));
        float _t38 = Math.fma(_t29, _t19, -(_t30 * _t18));
        float _t39 = Math.fma(_t30, _t20, -(_t28 * _t19));
        float _t42 = Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39));
        float _t43 = (1.0f / (float) Math.sqrt(_t42));
        float _t47, _t48, _t49;
        if (_t42 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0f;
            _t48 = 0.0f;
            _t49 = 0.0f;
        }
        float _t58 = Math.fma(_t48, _t19, -(_t49 * _t20));
        float _t59 = Math.fma(_t49, _t18, -(_t47 * _t19));
        float _t60 = Math.fma(_t47, _t20, -(_t48 * _t18));
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t18), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t47), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t58))));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t20), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t48), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t59))));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t19), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t49), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t60))));
        var _c3 = _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(eyeZ, _t49, Math.fma(eyeX, _t47, eyeY * _t48))), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(eyeZ, _t60, Math.fma(eyeX, _t58, eyeY * _t59))), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(eyeZ, _t19, Math.fma(eyeX, _t18, eyeY * _t20))), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] lookAt_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _t4 = centerZ - eyeZ;
        float _t5 = centerX - eyeX;
        float _t6 = centerY - eyeY;
        float _t13 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        float _t14 = (1.0f / (float) Math.sqrt(_t13));
        float _t18, _t19, _t20;
        if (_t13 != 0.0f) {
            _t18 = _t5 * _t14;
            _t19 = _t4 * _t14;
            _t20 = _t6 * _t14;
        } else {
            _t18 = 0.0f;
            _t19 = 0.0f;
            _t20 = 0.0f;
        }
        float _t27 = -Math.fma(upZ, _t19, Math.fma(upX, _t18, upY * _t20));
        float _t28 = Math.fma(_t27, _t20, upY);
        float _t29 = Math.fma(_t27, _t18, upX);
        float _t30 = Math.fma(_t27, _t19, upZ);
        float _t37 = Math.fma(_t28, _t18, -(_t29 * _t20));
        float _t38 = Math.fma(_t29, _t19, -(_t30 * _t18));
        float _t39 = Math.fma(_t30, _t20, -(_t28 * _t19));
        float _t42 = Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39));
        float _t43 = (1.0f / (float) Math.sqrt(_t42));
        float _t47, _t48, _t49;
        if (_t42 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0f;
            _t48 = 0.0f;
            _t49 = 0.0f;
        }
        float _t58 = Math.fma(_t48, _t19, -(_t49 * _t20));
        float _t59 = Math.fma(_t49, _t18, -(_t47 * _t19));
        float _t60 = Math.fma(_t47, _t20, -(_t48 * _t18));
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t18)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t47)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t58))));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t20)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t48)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t59))));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t19)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t49)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t60))));
        var _c3 = _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(eyeZ, _t49, Math.fma(eyeX, _t47, eyeY * _t48)))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(eyeZ, _t60, Math.fma(eyeX, _t58, eyeY * _t59)))).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(eyeZ, _t19, Math.fma(eyeX, _t18, eyeY * _t20)))).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset + 0];
        float _upy = up[upOffset + 1];
        float _t0 = center[centerOffset + 2] - _eyez;
        float _t1 = center[centerOffset + 0] - _eyex;
        float _t2 = center[centerOffset + 1] - _eyey;
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
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t14), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t43), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t54))));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t16), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t45), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t55))));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t15), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t44), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t56))));
        var _c3 = _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(_eyez, _t44, Math.fma(_eyex, _t43, _eyey * _t45))), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(_eyez, _t56, Math.fma(_eyex, _t54, _eyey * _t55))), _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(_eyez, _t15, Math.fma(_eyex, _t14, _eyey * _t16))), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] lookAt_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        float _eyez = eye[eyeOffset + 2];
        float _eyex = eye[eyeOffset + 0];
        float _eyey = eye[eyeOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset + 0];
        float _upy = up[upOffset + 1];
        float _t0 = center[centerOffset + 2] - _eyez;
        float _t1 = center[centerOffset + 0] - _eyex;
        float _t2 = center[centerOffset + 1] - _eyey;
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
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t14)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t43)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t54))));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t16)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t45)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t55))));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t15)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t44)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t56))));
        var _c3 = _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(_eyez, _t44, Math.fma(_eyex, _t43, _eyey * _t45)))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(_eyez, _t56, Math.fma(_eyex, _t54, _eyey * _t55)))).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(_eyez, _t15, Math.fma(_eyex, _t14, _eyey * _t16)))).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] lookAt_rh(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        if (SimdSupport.USE_FMA) return lookAt_rh_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return lookAt_rh_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static float[] lookAt_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _eyez = eye[eyeOffset + 2];
        float _eyex = eye[eyeOffset + 0];
        float _eyey = eye[eyeOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset + 0];
        float _upy = up[upOffset + 1];
        float _t4 = center[centerOffset + 2] - _eyez;
        float _t5 = center[centerOffset + 0] - _eyex;
        float _t6 = center[centerOffset + 1] - _eyey;
        float _t13 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        float _t14 = (1.0f / (float) Math.sqrt(_t13));
        float _t18, _t19, _t20;
        if (_t13 != 0.0f) {
            _t18 = _t5 * _t14;
            _t19 = _t4 * _t14;
            _t20 = _t6 * _t14;
        } else {
            _t18 = 0.0f;
            _t19 = 0.0f;
            _t20 = 0.0f;
        }
        float _t27 = -Math.fma(_upz, _t19, Math.fma(_upx, _t18, _upy * _t20));
        float _t28 = Math.fma(_t27, _t20, _upy);
        float _t29 = Math.fma(_t27, _t18, _upx);
        float _t30 = Math.fma(_t27, _t19, _upz);
        float _t37 = Math.fma(_t28, _t18, -(_t29 * _t20));
        float _t38 = Math.fma(_t29, _t19, -(_t30 * _t18));
        float _t39 = Math.fma(_t30, _t20, -(_t28 * _t19));
        float _t42 = Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39));
        float _t43 = (1.0f / (float) Math.sqrt(_t42));
        float _t47, _t48, _t49;
        if (_t42 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0f;
            _t48 = 0.0f;
            _t49 = 0.0f;
        }
        float _t58 = Math.fma(_t48, _t19, -(_t49 * _t20));
        float _t59 = Math.fma(_t49, _t18, -(_t47 * _t19));
        float _t60 = Math.fma(_t47, _t20, -(_t48 * _t18));
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t18), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t47), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t58))));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t20), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t48), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t59))));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t19), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t49), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t60))));
        var _c3 = _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(_eyez, _t49, Math.fma(_eyex, _t47, _eyey * _t48))), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(_eyez, _t60, Math.fma(_eyex, _t58, _eyey * _t59))), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_eyez, _t19, Math.fma(_eyex, _t18, _eyey * _t20))), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] lookAt_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _eyez = eye[eyeOffset + 2];
        float _eyex = eye[eyeOffset + 0];
        float _eyey = eye[eyeOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset + 0];
        float _upy = up[upOffset + 1];
        float _t4 = center[centerOffset + 2] - _eyez;
        float _t5 = center[centerOffset + 0] - _eyex;
        float _t6 = center[centerOffset + 1] - _eyey;
        float _t13 = Math.fma(_t4, _t4, Math.fma(_t5, _t5, _t6 * _t6));
        float _t14 = (1.0f / (float) Math.sqrt(_t13));
        float _t18, _t19, _t20;
        if (_t13 != 0.0f) {
            _t18 = _t5 * _t14;
            _t19 = _t4 * _t14;
            _t20 = _t6 * _t14;
        } else {
            _t18 = 0.0f;
            _t19 = 0.0f;
            _t20 = 0.0f;
        }
        float _t27 = -Math.fma(_upz, _t19, Math.fma(_upx, _t18, _upy * _t20));
        float _t28 = Math.fma(_t27, _t20, _upy);
        float _t29 = Math.fma(_t27, _t18, _upx);
        float _t30 = Math.fma(_t27, _t19, _upz);
        float _t37 = Math.fma(_t28, _t18, -(_t29 * _t20));
        float _t38 = Math.fma(_t29, _t19, -(_t30 * _t18));
        float _t39 = Math.fma(_t30, _t20, -(_t28 * _t19));
        float _t42 = Math.fma(_t37, _t37, Math.fma(_t38, _t38, _t39 * _t39));
        float _t43 = (1.0f / (float) Math.sqrt(_t42));
        float _t47, _t48, _t49;
        if (_t42 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0f;
            _t48 = 0.0f;
            _t49 = 0.0f;
        }
        float _t58 = Math.fma(_t48, _t19, -(_t49 * _t20));
        float _t59 = Math.fma(_t49, _t18, -(_t47 * _t19));
        float _t60 = Math.fma(_t47, _t20, -(_t48 * _t18));
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t18)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t47)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t58))));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t20)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t48)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t59))));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t19)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t49)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t60))));
        var _c3 = _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(_eyez, _t49, Math.fma(_eyex, _t47, _eyey * _t48)))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -Math.fma(_eyez, _t60, Math.fma(_eyex, _t58, _eyey * _t59)))).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_eyez, _t19, Math.fma(_eyex, _t18, _eyey * _t20)))).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapXYZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] mapXYnZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapXZY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapXZnY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapXnYZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapXnYnZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapXnZY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapXnZnY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapYXZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapYXnZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapYZX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapYZnX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapYnXZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapYnXnZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapYnZX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapYnZnX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapZXY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapZXnY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapZYX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapZYnX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapZnXY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapZnXnY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapZnYX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapZnYnX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnXYZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnXYnZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnXZY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnXZnY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnXnYZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnXnYnZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnXnZY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnXnZnY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnYXZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnYXnZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnYZX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnYZnX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnYnXZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnYnXnZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnYnZX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnYnZnX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnZXY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnZXnY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnZYX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnZYnX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnZnXY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnZnXnY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnZnYX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnZnYnX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] obliqueCabinet(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        if (SimdSupport.USE_FMA) return obliqueCabinet_fma(dest, destOffset, src, srcOffset, angle);
        return obliqueCabinet_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static float[] obliqueCabinet_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0;
        var _c1 = _sv1;
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, -(0.5f * (float) Math.cosFromSin(_t0, angle))), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, -(0.5f * _t0)), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8)));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] obliqueCabinet_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0;
        var _c1 = _sv1;
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -(0.5f * (float) Math.cosFromSin(_t0, angle)))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -(0.5f * _t0))).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8)));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] obliqueCavalier(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        if (SimdSupport.USE_FMA) return obliqueCavalier_fma(dest, destOffset, src, srcOffset, angle);
        return obliqueCavalier_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static float[] obliqueCavalier_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0;
        var _c1 = _sv1;
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, -(float) Math.cosFromSin(_t0, angle)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, -_t0), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8)));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] obliqueCavalier_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0;
        var _c1 = _sv1;
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -(float) Math.cosFromSin(_t0, angle))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -_t0)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8)));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] obliqueMilitary(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        if (SimdSupport.USE_FMA) return obliqueMilitary_fma(dest, destOffset, src, srcOffset, angle);
        return obliqueMilitary_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static float[] obliqueMilitary_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0;
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, (float) Math.cosFromSin(_t0, angle)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t0), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg()));
        var _c2 = _sv1;
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] obliqueMilitary_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0;
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, (float) Math.cosFromSin(_t0, angle))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t0)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg()));
        var _c2 = _sv1;
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] obliquePlanometric(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        if (SimdSupport.USE_FMA) return obliquePlanometric_fma(dest, destOffset, src, srcOffset, angle);
        return obliquePlanometric_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static float[] obliquePlanometric_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, _t0);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t1), _sv1.mul(_sv2));
        var _c1 = _sv1.add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8));
        var _c2 = _sv0.fma(_sv2, _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -_t1)));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] obliquePlanometric_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, _t0);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t1)).add(_sv1.mul(_sv2));
        var _c1 = _sv1.add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8));
        var _c2 = _sv0.mul(_sv2).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -_t1)));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] obliqueZ_no_lh(float[] dest, int destOffset, float[] src, int srcOffset, float planeX, float planeY, float planeZ, float planeW) {
        float _self23 = src[srcOffset + 14];
        float _t0 = _self23 + _self23;
        float _t15_inv = 1.0f / Math.fma(planeW, 1.0f - src[srcOffset + 10], _self23 * (planeZ + (planeX * ((planeX < 0.0f ? -1.0f : planeX > 0.0f ? 1.0f : 0.0f) - src[srcOffset + 8]) / src[srcOffset + 0] + planeY * ((planeY < 0.0f ? -1.0f : planeY > 0.0f ? 1.0f : 0.0f) - src[srcOffset + 9]) / src[srcOffset + 5])));
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, planeX * _t0 * _t15_inv - src[srcOffset + 3]);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, planeY * _t0 * _t15_inv - src[srcOffset + 7]);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, planeZ * _t0 * _t15_inv - src[srcOffset + 11]);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, planeW * _t0 * _t15_inv - src[srcOffset + 15]);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] obliqueZ_no_rh(float[] dest, int destOffset, float[] src, int srcOffset, float planeX, float planeY, float planeZ, float planeW) {
        float _self23 = src[srcOffset + 14];
        float _t0 = _self23 + _self23;
        float _t15_inv = 1.0f / Math.fma(planeW, 1.0f + src[srcOffset + 10], _self23 * (planeX * (src[srcOffset + 8] + (planeX < 0.0f ? -1.0f : planeX > 0.0f ? 1.0f : 0.0f)) / src[srcOffset + 0] + planeY * (src[srcOffset + 9] + (planeY < 0.0f ? -1.0f : planeY > 0.0f ? 1.0f : 0.0f)) / src[srcOffset + 5] - planeZ));
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, planeX * _t0 * _t15_inv - src[srcOffset + 3]);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, planeY * _t0 * _t15_inv - src[srcOffset + 7]);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, planeZ * _t0 * _t15_inv - src[srcOffset + 11]);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, planeW * _t0 * _t15_inv - src[srcOffset + 15]);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] obliqueZ_zo_lh(float[] dest, int destOffset, float[] src, int srcOffset, float planeX, float planeY, float planeZ, float planeW) {
        float _self23 = src[srcOffset + 14];
        float _t14_inv = 1.0f / Math.fma(planeW, 1.0f - src[srcOffset + 10], _self23 * (planeZ + (planeX * ((planeX < 0.0f ? -1.0f : planeX > 0.0f ? 1.0f : 0.0f) - src[srcOffset + 8]) / src[srcOffset + 0] + planeY * ((planeY < 0.0f ? -1.0f : planeY > 0.0f ? 1.0f : 0.0f) - src[srcOffset + 9]) / src[srcOffset + 5])));
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, planeX * _self23 * _t14_inv);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, planeY * _self23 * _t14_inv);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, planeZ * _self23 * _t14_inv);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, planeW * _self23 * _t14_inv);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] obliqueZ_zo_rh(float[] dest, int destOffset, float[] src, int srcOffset, float planeX, float planeY, float planeZ, float planeW) {
        float _self23 = src[srcOffset + 14];
        float _t14_inv = 1.0f / Math.fma(planeW, 1.0f + src[srcOffset + 10], _self23 * (planeX * (src[srcOffset + 8] + (planeX < 0.0f ? -1.0f : planeX > 0.0f ? 1.0f : 0.0f)) / src[srcOffset + 0] + planeY * (src[srcOffset + 9] + (planeY < 0.0f ? -1.0f : planeY > 0.0f ? 1.0f : 0.0f)) / src[srcOffset + 5] - planeZ));
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, planeX * _self23 * _t14_inv);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, planeY * _self23 * _t14_inv);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, planeZ * _self23 * _t14_inv);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, planeW * _self23 * _t14_inv);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] obliqueZ_no_lh(float[] dest, int destOffset, float[] src, int srcOffset, float[] plane, int planeOffset) {
        float _planex = plane[planeOffset + 0];
        float _planey = plane[planeOffset + 1];
        float _planez = plane[planeOffset + 2];
        float _planew = plane[planeOffset + 3];
        float _self23 = src[srcOffset + 14];
        float _t0 = _self23 + _self23;
        float _t15_inv = 1.0f / Math.fma(_planew, 1.0f - src[srcOffset + 10], _self23 * (_planez + (_planex * ((_planex < 0.0f ? -1.0f : _planex > 0.0f ? 1.0f : 0.0f) - src[srcOffset + 8]) / src[srcOffset + 0] + _planey * ((_planey < 0.0f ? -1.0f : _planey > 0.0f ? 1.0f : 0.0f) - src[srcOffset + 9]) / src[srcOffset + 5])));
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, _planex * _t0 * _t15_inv - src[srcOffset + 3]);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, _planey * _t0 * _t15_inv - src[srcOffset + 7]);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _planez * _t0 * _t15_inv - src[srcOffset + 11]);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _planew * _t0 * _t15_inv - src[srcOffset + 15]);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] obliqueZ_no_rh(float[] dest, int destOffset, float[] src, int srcOffset, float[] plane, int planeOffset) {
        float _planex = plane[planeOffset + 0];
        float _planey = plane[planeOffset + 1];
        float _planez = plane[planeOffset + 2];
        float _planew = plane[planeOffset + 3];
        float _self23 = src[srcOffset + 14];
        float _t0 = _self23 + _self23;
        float _t15_inv = 1.0f / Math.fma(_planew, 1.0f + src[srcOffset + 10], _self23 * (_planex * (src[srcOffset + 8] + (_planex < 0.0f ? -1.0f : _planex > 0.0f ? 1.0f : 0.0f)) / src[srcOffset + 0] + _planey * (src[srcOffset + 9] + (_planey < 0.0f ? -1.0f : _planey > 0.0f ? 1.0f : 0.0f)) / src[srcOffset + 5] - _planez));
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, _planex * _t0 * _t15_inv - src[srcOffset + 3]);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, _planey * _t0 * _t15_inv - src[srcOffset + 7]);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _planez * _t0 * _t15_inv - src[srcOffset + 11]);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _planew * _t0 * _t15_inv - src[srcOffset + 15]);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] obliqueZ_zo_lh(float[] dest, int destOffset, float[] src, int srcOffset, float[] plane, int planeOffset) {
        float _planex = plane[planeOffset + 0];
        float _self23 = src[srcOffset + 14];
        float _planey = plane[planeOffset + 1];
        float _planez = plane[planeOffset + 2];
        float _planew = plane[planeOffset + 3];
        float _t14_inv = 1.0f / Math.fma(_planew, 1.0f - src[srcOffset + 10], _self23 * (_planez + (_planex * ((_planex < 0.0f ? -1.0f : _planex > 0.0f ? 1.0f : 0.0f) - src[srcOffset + 8]) / src[srcOffset + 0] + _planey * ((_planey < 0.0f ? -1.0f : _planey > 0.0f ? 1.0f : 0.0f) - src[srcOffset + 9]) / src[srcOffset + 5])));
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, _planex * _self23 * _t14_inv);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, _planey * _self23 * _t14_inv);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _planez * _self23 * _t14_inv);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _planew * _self23 * _t14_inv);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] obliqueZ_zo_rh(float[] dest, int destOffset, float[] src, int srcOffset, float[] plane, int planeOffset) {
        float _planex = plane[planeOffset + 0];
        float _self23 = src[srcOffset + 14];
        float _planey = plane[planeOffset + 1];
        float _planez = plane[planeOffset + 2];
        float _planew = plane[planeOffset + 3];
        float _t14_inv = 1.0f / Math.fma(_planew, 1.0f + src[srcOffset + 10], _self23 * (_planex * (src[srcOffset + 8] + (_planex < 0.0f ? -1.0f : _planex > 0.0f ? 1.0f : 0.0f)) / src[srcOffset + 0] + _planey * (src[srcOffset + 9] + (_planey < 0.0f ? -1.0f : _planey > 0.0f ? 1.0f : 0.0f)) / src[srcOffset + 5] - _planez));
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, _planex * _self23 * _t14_inv);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, _planey * _self23 * _t14_inv);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _planez * _self23 * _t14_inv);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _planew * _self23 * _t14_inv);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho_no_lh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        if (SimdSupport.USE_FMA) return ortho_no_lh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return ortho_no_lh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static float[] ortho_no_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t0_inv = 1.0f / (right - left);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (top - bottom));
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv4 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (zFar - zNear));
        var _c0 = _sv0.add(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, _t0_inv));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = _sv3.add(_sv3).mul(_sv4);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).add(_sv3.mul(FloatVector.broadcast(SIMD_SPECIES, -(zFar + zNear))).fma(_sv4, _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -(bottom + top))).fma(_sv2, _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, left + right)).mul(FloatVector.broadcast(SIMD_SPECIES, -_t0_inv)))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho_no_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t0_inv = 1.0f / (right - left);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (top - bottom));
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv4 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (zFar - zNear));
        var _c0 = _sv0.add(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, _t0_inv));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = _sv3.add(_sv3).mul(_sv4);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).add(_sv3.mul(FloatVector.broadcast(SIMD_SPECIES, -(zFar + zNear))).mul(_sv4).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -(bottom + top))).mul(_sv2).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, left + right)).mul(FloatVector.broadcast(SIMD_SPECIES, -_t0_inv)))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho_no_rh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        if (SimdSupport.USE_FMA) return ortho_no_rh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return ortho_no_rh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static float[] ortho_no_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t0_inv = 1.0f / (right - left);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (top - bottom));
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv4 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (zFar - zNear));
        var _c0 = _sv0.add(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, _t0_inv));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, -2.0f).mul(_sv3).mul(_sv4);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).add(_sv3.mul(FloatVector.broadcast(SIMD_SPECIES, -(zFar + zNear))).fma(_sv4, _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -(bottom + top))).fma(_sv2, _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, left + right)).mul(FloatVector.broadcast(SIMD_SPECIES, -_t0_inv)))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho_no_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t0_inv = 1.0f / (right - left);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (top - bottom));
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv4 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (zFar - zNear));
        var _c0 = _sv0.add(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, _t0_inv));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, -2.0f).mul(_sv3).mul(_sv4);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).add(_sv3.mul(FloatVector.broadcast(SIMD_SPECIES, -(zFar + zNear))).mul(_sv4).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -(bottom + top))).mul(_sv2).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, left + right)).mul(FloatVector.broadcast(SIMD_SPECIES, -_t0_inv)))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho_zo_lh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        if (SimdSupport.USE_FMA) return ortho_zo_lh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return ortho_zo_lh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static float[] ortho_zo_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t0_inv = 1.0f / (right - left);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (top - bottom));
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv4 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (zFar - zNear));
        var _c0 = _sv0.add(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, _t0_inv));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = _sv3.mul(_sv4);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).add(FloatVector.broadcast(SIMD_SPECIES, -zNear).mul(_sv3).fma(_sv4, _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -(bottom + top))).fma(_sv2, _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, left + right)).mul(FloatVector.broadcast(SIMD_SPECIES, -_t0_inv)))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho_zo_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t0_inv = 1.0f / (right - left);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (top - bottom));
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv4 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (zFar - zNear));
        var _c0 = _sv0.add(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, _t0_inv));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = _sv3.mul(_sv4);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).add(FloatVector.broadcast(SIMD_SPECIES, -zNear).mul(_sv3).mul(_sv4).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -(bottom + top))).mul(_sv2).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, left + right)).mul(FloatVector.broadcast(SIMD_SPECIES, -_t0_inv)))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho_zo_rh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        if (SimdSupport.USE_FMA) return ortho_zo_rh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return ortho_zo_rh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static float[] ortho_zo_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t0_inv = 1.0f / (right - left);
        float _t2_inv = 1.0f / (zFar - zNear);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (top - bottom));
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, _t0_inv));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, -_t2_inv));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).add(FloatVector.broadcast(SIMD_SPECIES, -zNear).mul(_sv3).fma(FloatVector.broadcast(SIMD_SPECIES, _t2_inv), _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -(bottom + top))).fma(_sv2, _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, left + right)).mul(FloatVector.broadcast(SIMD_SPECIES, -_t0_inv)))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho_zo_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t0_inv = 1.0f / (right - left);
        float _t2_inv = 1.0f / (zFar - zNear);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (top - bottom));
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, _t0_inv));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, -_t2_inv));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).add(FloatVector.broadcast(SIMD_SPECIES, -zNear).mul(_sv3).mul(FloatVector.broadcast(SIMD_SPECIES, _t2_inv)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -(bottom + top))).mul(_sv2).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, left + right)).mul(FloatVector.broadcast(SIMD_SPECIES, -_t0_inv)))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho2D_no_lh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top) {
        if (SimdSupport.USE_FMA) return ortho2D_no_lh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top);
        return ortho2D_no_lh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top);
    }

    public static float[] ortho2D_no_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (top - bottom));
        var _c0 = _sv0.add(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, _t0_inv));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -(bottom + top))).fma(_sv2, _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, left + right)).mul(FloatVector.broadcast(SIMD_SPECIES, -_t0_inv))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho2D_no_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (top - bottom));
        var _c0 = _sv0.add(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, _t0_inv));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -(bottom + top))).mul(_sv2).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, left + right)).mul(FloatVector.broadcast(SIMD_SPECIES, -_t0_inv))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho2D_no_rh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top) {
        if (SimdSupport.USE_FMA) return ortho2D_no_rh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top);
        return ortho2D_no_rh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top);
    }

    public static float[] ortho2D_no_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (top - bottom));
        var _c0 = _sv0.add(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, _t0_inv));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -(bottom + top))).fma(_sv2, _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, left + right)).mul(FloatVector.broadcast(SIMD_SPECIES, -_t0_inv))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho2D_no_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (top - bottom));
        var _c0 = _sv0.add(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, _t0_inv));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -(bottom + top))).mul(_sv2).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, left + right)).mul(FloatVector.broadcast(SIMD_SPECIES, -_t0_inv))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho2D_zo_lh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top) {
        if (SimdSupport.USE_FMA) return ortho2D_zo_lh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top);
        return ortho2D_zo_lh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top);
    }

    public static float[] ortho2D_zo_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (right - left));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (top - bottom));
        var _sv4 = FloatVector.broadcast(SIMD_SPECIES, 0.5f);
        var _sv5 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(_sv1);
        var _c1 = _sv2.add(_sv2).mul(_sv3);
        var _c2 = _sv4.mul(_sv5);
        var _c3 = _sv4.fma(_sv5, _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -(bottom + top))).fma(_sv3, _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -(left + right))).fma(_sv1, FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho2D_zo_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (right - left));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (top - bottom));
        var _sv4 = FloatVector.broadcast(SIMD_SPECIES, 0.5f);
        var _sv5 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(_sv1);
        var _c1 = _sv2.add(_sv2).mul(_sv3);
        var _c2 = _sv4.mul(_sv5);
        var _c3 = _sv4.mul(_sv5).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -(bottom + top))).mul(_sv3).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -(left + right))).mul(_sv1).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho2D_zo_rh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top) {
        if (SimdSupport.USE_FMA) return ortho2D_zo_rh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top);
        return ortho2D_zo_rh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top);
    }

    public static float[] ortho2D_zo_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (right - left));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (top - bottom));
        var _sv4 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(_sv1);
        var _c1 = _sv2.add(_sv2).mul(_sv3);
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, -0.5f).mul(_sv4);
        var _c3 = FloatVector.broadcast(SIMD_SPECIES, 0.5f).fma(_sv4, _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -(bottom + top))).fma(_sv3, _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -(left + right))).fma(_sv1, FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho2D_zo_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (right - left));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (top - bottom));
        var _sv4 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(_sv1);
        var _c1 = _sv2.add(_sv2).mul(_sv3);
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, -0.5f).mul(_sv4);
        var _c3 = FloatVector.broadcast(SIMD_SPECIES, 0.5f).mul(_sv4).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -(bottom + top))).mul(_sv3).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -(left + right))).mul(_sv1).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspective_no_lh(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far) {
        if (SimdSupport.USE_FMA) return perspective_no_lh_fma(dest, destOffset, src, srcOffset, fovy, aspect, near, far);
        return perspective_no_lh_mulAdd(dest, destOffset, src, srcOffset, fovy, aspect, near, far);
    }

    public static float[] perspective_no_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far) {
        float _t2_inv = 1.0f / (near - far);
        float _t6 = (float) Math.tan(0.5f * fovy);
        float _w0, _w1;
        if (far == Float.POSITIVE_INFINITY) {
            _w0 = 1.0f;
            _w1 = -(near + near);
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _w0 = -1.0f;
                _w1 = far + far;
            } else {
                _w0 = -((far + near) * _t2_inv);
                _w1 = (far + far) * near * _t2_inv;
            }
        }
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / (aspect * _t6)));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / _t6));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _w0), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12));
        var _c3 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspective_no_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far) {
        float _t2_inv = 1.0f / (near - far);
        float _t6 = (float) Math.tan(0.5f * fovy);
        float _w0, _w1;
        if (far == Float.POSITIVE_INFINITY) {
            _w0 = 1.0f;
            _w1 = -(near + near);
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _w0 = -1.0f;
                _w1 = far + far;
            } else {
                _w0 = -((far + near) * _t2_inv);
                _w1 = (far + far) * near * _t2_inv;
            }
        }
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / (aspect * _t6)));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / _t6));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12));
        var _c3 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspective_no_rh(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far) {
        if (SimdSupport.USE_FMA) return perspective_no_rh_fma(dest, destOffset, src, srcOffset, fovy, aspect, near, far);
        return perspective_no_rh_mulAdd(dest, destOffset, src, srcOffset, fovy, aspect, near, far);
    }

    public static float[] perspective_no_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far) {
        float _t2_inv = 1.0f / (near - far);
        float _t6 = (float) Math.tan(0.5f * fovy);
        float _w0, _w1;
        if (far == Float.POSITIVE_INFINITY) {
            _w0 = -1.0f;
            _w1 = -(near + near);
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _w0 = 1.0f;
                _w1 = far + far;
            } else {
                _w0 = (far + near) * _t2_inv;
                _w1 = (far + far) * near * _t2_inv;
            }
        }
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / (aspect * _t6)));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / _t6));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _w0), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).neg());
        var _c3 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspective_no_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far) {
        float _t2_inv = 1.0f / (near - far);
        float _t6 = (float) Math.tan(0.5f * fovy);
        float _w0, _w1;
        if (far == Float.POSITIVE_INFINITY) {
            _w0 = -1.0f;
            _w1 = -(near + near);
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _w0 = 1.0f;
                _w1 = far + far;
            } else {
                _w0 = (far + near) * _t2_inv;
                _w1 = (far + far) * near * _t2_inv;
            }
        }
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / (aspect * _t6)));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / _t6));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).neg());
        var _c3 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspective_zo_lh(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far) {
        if (SimdSupport.USE_FMA) return perspective_zo_lh_fma(dest, destOffset, src, srcOffset, fovy, aspect, near, far);
        return perspective_zo_lh_mulAdd(dest, destOffset, src, srcOffset, fovy, aspect, near, far);
    }

    public static float[] perspective_zo_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far) {
        float _t1_inv = 1.0f / (near - far);
        float _t3 = (float) Math.tan(0.5f * fovy);
        float _w0, _w1;
        if (far == Float.POSITIVE_INFINITY) {
            _w0 = 1.0f;
            _w1 = -near;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _w0 = 0.0f;
                _w1 = far;
            } else {
                _w0 = -(far * _t1_inv);
                _w1 = far * near * _t1_inv;
            }
        }
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / (aspect * _t3)));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / _t3));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _w0), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12));
        var _c3 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspective_zo_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far) {
        float _t1_inv = 1.0f / (near - far);
        float _t3 = (float) Math.tan(0.5f * fovy);
        float _w0, _w1;
        if (far == Float.POSITIVE_INFINITY) {
            _w0 = 1.0f;
            _w1 = -near;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _w0 = 0.0f;
                _w1 = far;
            } else {
                _w0 = -(far * _t1_inv);
                _w1 = far * near * _t1_inv;
            }
        }
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / (aspect * _t3)));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / _t3));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12));
        var _c3 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspective_zo_rh(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far) {
        if (SimdSupport.USE_FMA) return perspective_zo_rh_fma(dest, destOffset, src, srcOffset, fovy, aspect, near, far);
        return perspective_zo_rh_mulAdd(dest, destOffset, src, srcOffset, fovy, aspect, near, far);
    }

    public static float[] perspective_zo_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far) {
        float _t1_inv = 1.0f / (near - far);
        float _t3 = (float) Math.tan(0.5f * fovy);
        float _w0, _w1;
        if (far == Float.POSITIVE_INFINITY) {
            _w0 = -1.0f;
            _w1 = -near;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _w0 = 0.0f;
                _w1 = far;
            } else {
                _w0 = far * _t1_inv;
                _w1 = far * near * _t1_inv;
            }
        }
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / (aspect * _t3)));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / _t3));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _w0), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).neg());
        var _c3 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspective_zo_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far) {
        float _t1_inv = 1.0f / (near - far);
        float _t3 = (float) Math.tan(0.5f * fovy);
        float _w0, _w1;
        if (far == Float.POSITIVE_INFINITY) {
            _w0 = -1.0f;
            _w1 = -near;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _w0 = 0.0f;
                _w1 = far;
            } else {
                _w0 = far * _t1_inv;
                _w1 = far * near * _t1_inv;
            }
        }
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / (aspect * _t3)));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / _t3));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).neg());
        var _c3 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspectiveFovRange_no_lh(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        if (SimdSupport.USE_FMA) return perspectiveFovRange_no_lh_fma(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far);
        return perspectiveFovRange_no_lh_mulAdd(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far);
    }

    public static float[] perspectiveFovRange_no_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        float _t0 = (float) Math.tan(angleMax);
        float _t1 = (float) Math.tan(angleMin);
        float _t3_inv = 1.0f / (near - far);
        float _t8 = _t0 - _t1;
        float _w0, _w1;
        if (far == Float.POSITIVE_INFINITY) {
            _w0 = 1.0f;
            _w1 = -(near + near);
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _w0 = -1.0f;
                _w1 = far + far;
            } else {
                _w0 = -((far + near) * _t3_inv);
                _w1 = (far + far) * near * _t3_inv;
            }
        }
        perspectiveFovRange_no_lh_fma_sf7a874bc_v(dest, destOffset, src, srcOffset, _w0, _w1, 1.0f / (aspect * _t8), 1.0f / _t8, -(_t0 + _t1));
        return dest;
    }

    private static void perspectiveFovRange_no_lh_fma_sf7a874bc_v(float[] dest, int destOffset, float[] src, int srcOffset, float _w0, float _w1, float _h0, float _h1, float _h2) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, _h1);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, _h0));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = _sv3.fma(FloatVector.broadcast(SIMD_SPECIES, _w0), _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h2)).fma(_sv2, FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        var _c3 = _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] perspectiveFovRange_no_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        float _t0 = (float) Math.tan(angleMax);
        float _t1 = (float) Math.tan(angleMin);
        float _t3_inv = 1.0f / (near - far);
        float _t8 = _t0 - _t1;
        float _w0, _w1;
        if (far == Float.POSITIVE_INFINITY) {
            _w0 = 1.0f;
            _w1 = -(near + near);
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _w0 = -1.0f;
                _w1 = far + far;
            } else {
                _w0 = -((far + near) * _t3_inv);
                _w1 = (far + far) * near * _t3_inv;
            }
        }
        perspectiveFovRange_no_lh_mulAdd_s236970ef_v(dest, destOffset, src, srcOffset, _w0, _w1, 1.0f / (aspect * _t8), 1.0f / _t8, -(_t0 + _t1));
        return dest;
    }

    private static void perspectiveFovRange_no_lh_mulAdd_s236970ef_v(float[] dest, int destOffset, float[] src, int srcOffset, float _w0, float _w1, float _h0, float _h1, float _h2) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, _h1);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, _h0));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h2)).mul(_sv2).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        var _c3 = _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] perspectiveFovRange_no_rh(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        if (SimdSupport.USE_FMA) return perspectiveFovRange_no_rh_fma(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far);
        return perspectiveFovRange_no_rh_mulAdd(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far);
    }

    public static float[] perspectiveFovRange_no_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        float _t0 = (float) Math.tan(angleMax);
        float _t1 = (float) Math.tan(angleMin);
        float _t3_inv = 1.0f / (near - far);
        float _t8 = _t0 - _t1;
        float _w0, _w1;
        if (far == Float.POSITIVE_INFINITY) {
            _w0 = -1.0f;
            _w1 = -(near + near);
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _w0 = 1.0f;
                _w1 = far + far;
            } else {
                _w0 = (far + near) * _t3_inv;
                _w1 = (far + far) * near * _t3_inv;
            }
        }
        perspectiveFovRange_no_rh_fma_sbd57f532_v(dest, destOffset, src, srcOffset, _w0, _w1, 1.0f / (aspect * _t8), 1.0f / _t8, _t0 + _t1);
        return dest;
    }

    private static void perspectiveFovRange_no_rh_fma_sbd57f532_v(float[] dest, int destOffset, float[] src, int srcOffset, float _w0, float _w1, float _h0, float _h1, float _h2) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, _h1);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, _h0));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = _sv3.fma(FloatVector.broadcast(SIMD_SPECIES, _w0), _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h2)).fma(_sv2, FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).neg()));
        var _c3 = _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] perspectiveFovRange_no_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        float _t0 = (float) Math.tan(angleMax);
        float _t1 = (float) Math.tan(angleMin);
        float _t3_inv = 1.0f / (near - far);
        float _t8 = _t0 - _t1;
        float _w0, _w1;
        if (far == Float.POSITIVE_INFINITY) {
            _w0 = -1.0f;
            _w1 = -(near + near);
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _w0 = 1.0f;
                _w1 = far + far;
            } else {
                _w0 = (far + near) * _t3_inv;
                _w1 = (far + far) * near * _t3_inv;
            }
        }
        perspectiveFovRange_no_rh_mulAdd_s4857034d_v(dest, destOffset, src, srcOffset, _w0, _w1, 1.0f / (aspect * _t8), 1.0f / _t8, _t0 + _t1);
        return dest;
    }

    private static void perspectiveFovRange_no_rh_mulAdd_s4857034d_v(float[] dest, int destOffset, float[] src, int srcOffset, float _w0, float _w1, float _h0, float _h1, float _h2) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, _h1);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, _h0));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h2)).mul(_sv2).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).neg()));
        var _c3 = _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] perspectiveFovRange_zo_lh(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        if (SimdSupport.USE_FMA) return perspectiveFovRange_zo_lh_fma(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far);
        return perspectiveFovRange_zo_lh_mulAdd(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far);
    }

    public static float[] perspectiveFovRange_zo_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        float _t0 = (float) Math.tan(angleMax);
        float _t1 = (float) Math.tan(angleMin);
        float _t2_inv = 1.0f / (near - far);
        float _t4 = _t0 - _t1;
        float _w0, _w1;
        if (far == Float.POSITIVE_INFINITY) {
            _w0 = 1.0f;
            _w1 = -near;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _w0 = 0.0f;
                _w1 = far;
            } else {
                _w0 = -(far * _t2_inv);
                _w1 = far * near * _t2_inv;
            }
        }
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / _t4);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / (aspect * _t4)));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = _sv3.fma(FloatVector.broadcast(SIMD_SPECIES, _w0), _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -(_t0 + _t1))).fma(_sv2, FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        var _c3 = _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspectiveFovRange_zo_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        float _t0 = (float) Math.tan(angleMax);
        float _t1 = (float) Math.tan(angleMin);
        float _t2_inv = 1.0f / (near - far);
        float _t4 = _t0 - _t1;
        float _w0, _w1;
        if (far == Float.POSITIVE_INFINITY) {
            _w0 = 1.0f;
            _w1 = -near;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _w0 = 0.0f;
                _w1 = far;
            } else {
                _w0 = -(far * _t2_inv);
                _w1 = far * near * _t2_inv;
            }
        }
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / _t4);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / (aspect * _t4)));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -(_t0 + _t1))).mul(_sv2).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        var _c3 = _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspectiveFovRange_zo_rh(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        if (SimdSupport.USE_FMA) return perspectiveFovRange_zo_rh_fma(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far);
        return perspectiveFovRange_zo_rh_mulAdd(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far);
    }

    public static float[] perspectiveFovRange_zo_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        float _t0 = (float) Math.tan(angleMax);
        float _t1 = (float) Math.tan(angleMin);
        float _t2_inv = 1.0f / (near - far);
        float _t4 = _t0 - _t1;
        float _w0, _w1;
        if (far == Float.POSITIVE_INFINITY) {
            _w0 = -1.0f;
            _w1 = -near;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _w0 = 0.0f;
                _w1 = far;
            } else {
                _w0 = far * _t2_inv;
                _w1 = far * near * _t2_inv;
            }
        }
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / _t4);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / (aspect * _t4)));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = _sv3.fma(FloatVector.broadcast(SIMD_SPECIES, _w0), _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t0 + _t1)).fma(_sv2, FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).neg()));
        var _c3 = _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspectiveFovRange_zo_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        float _t0 = (float) Math.tan(angleMax);
        float _t1 = (float) Math.tan(angleMin);
        float _t2_inv = 1.0f / (near - far);
        float _t4 = _t0 - _t1;
        float _w0, _w1;
        if (far == Float.POSITIVE_INFINITY) {
            _w0 = -1.0f;
            _w1 = -near;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _w0 = 0.0f;
                _w1 = far;
            } else {
                _w0 = far * _t2_inv;
                _w1 = far * near * _t2_inv;
            }
        }
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / _t4);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / (aspect * _t4)));
        var _c1 = _sv1.add(_sv1).mul(_sv2);
        var _c2 = _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t0 + _t1)).mul(_sv2).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).neg()));
        var _c3 = _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspectiveFrustumSlice_no_lh(float[] dest, int destOffset, float[] src, int srcOffset, float near, float far) {
        float _t0_inv = 1.0f / (near - far);
        float _w0, _w1;
        if (far == Float.POSITIVE_INFINITY) {
            _w0 = 1.0f;
            _w1 = -(near + near);
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _w0 = -1.0f;
                _w1 = far + far;
            } else {
                _w0 = -((far + near) * _t0_inv);
                _w1 = (far + far) * near * _t0_inv;
            }
        }
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _w0);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _w1);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspectiveFrustumSlice_no_rh(float[] dest, int destOffset, float[] src, int srcOffset, float near, float far) {
        float _t0_inv = 1.0f / (near - far);
        float _w0, _w1;
        if (far == Float.POSITIVE_INFINITY) {
            _w0 = -1.0f;
            _w1 = -(near + near);
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _w0 = 1.0f;
                _w1 = far + far;
            } else {
                _w0 = (far + near) * _t0_inv;
                _w1 = (far + far) * near * _t0_inv;
            }
        }
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _w0);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _w1);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspectiveFrustumSlice_zo_lh(float[] dest, int destOffset, float[] src, int srcOffset, float near, float far) {
        float _t0_inv = 1.0f / (near - far);
        float _w0, _w1;
        if (far == Float.POSITIVE_INFINITY) {
            _w0 = 1.0f;
            _w1 = -near;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _w0 = 0.0f;
                _w1 = far;
            } else {
                _w0 = -(far * _t0_inv);
                _w1 = far * near * _t0_inv;
            }
        }
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _w0);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _w1);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspectiveFrustumSlice_zo_rh(float[] dest, int destOffset, float[] src, int srcOffset, float near, float far) {
        float _t0_inv = 1.0f / (near - far);
        float _w0, _w1;
        if (far == Float.POSITIVE_INFINITY) {
            _w0 = -1.0f;
            _w1 = -near;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _w0 = 0.0f;
                _w1 = far;
            } else {
                _w0 = far * _t0_inv;
                _w1 = far * near * _t0_inv;
            }
        }
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _w0);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _w1);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspectiveOffCenterFov_no_lh(float[] dest, int destOffset, float[] src, int srcOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        if (SimdSupport.USE_FMA) return perspectiveOffCenterFov_no_lh_fma(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far);
        return perspectiveOffCenterFov_no_lh_mulAdd(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far);
    }

    public static float[] perspectiveOffCenterFov_no_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        float _t5_inv = 1.0f / (near - far);
        float _w0, _w1;
        if (far == Float.POSITIVE_INFINITY) {
            _w0 = 1.0f;
            _w1 = -(near + near);
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _w0 = -1.0f;
                _w1 = far + far;
            } else {
                _w0 = -((far + near) * _t5_inv);
                _w1 = (far + far) * near * _t5_inv;
            }
        }
        perspectiveOffCenterFov_no_lh_fma_s15b1ed21_v(dest, destOffset, src, srcOffset, (float) Math.tan(angleRight), (float) Math.tan(angleLeft), (float) Math.tan(angleUp), (float) Math.tan(angleDown), _w0, _w1);
        return dest;
    }

    private static void perspectiveOffCenterFov_no_lh_fma_s15b1ed21_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _w0, float _w1) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (_t0 - _t1));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (_t2 - _t3));
        var _sv4 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(_sv1);
        var _c1 = _sv2.add(_sv2).mul(_sv3);
        var _c2 = _sv4.fma(FloatVector.broadcast(SIMD_SPECIES, _w0), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -(_t3 + _t2))).fma(_sv3, _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -(_t1 + _t0))).fma(_sv1, FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        var _c3 = _sv4.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] perspectiveOffCenterFov_no_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        float _t5_inv = 1.0f / (near - far);
        float _w0, _w1;
        if (far == Float.POSITIVE_INFINITY) {
            _w0 = 1.0f;
            _w1 = -(near + near);
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _w0 = -1.0f;
                _w1 = far + far;
            } else {
                _w0 = -((far + near) * _t5_inv);
                _w1 = (far + far) * near * _t5_inv;
            }
        }
        perspectiveOffCenterFov_no_lh_mulAdd_s8110bc56_v(dest, destOffset, src, srcOffset, (float) Math.tan(angleRight), (float) Math.tan(angleLeft), (float) Math.tan(angleUp), (float) Math.tan(angleDown), _w0, _w1);
        return dest;
    }

    private static void perspectiveOffCenterFov_no_lh_mulAdd_s8110bc56_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _w0, float _w1) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (_t0 - _t1));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (_t2 - _t3));
        var _sv4 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(_sv1);
        var _c1 = _sv2.add(_sv2).mul(_sv3);
        var _c2 = _sv4.mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -(_t3 + _t2))).mul(_sv3).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -(_t1 + _t0))).mul(_sv1).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        var _c3 = _sv4.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] perspectiveOffCenterFov_no_rh(float[] dest, int destOffset, float[] src, int srcOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        if (SimdSupport.USE_FMA) return perspectiveOffCenterFov_no_rh_fma(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far);
        return perspectiveOffCenterFov_no_rh_mulAdd(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far);
    }

    public static float[] perspectiveOffCenterFov_no_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        float _t5_inv = 1.0f / (near - far);
        float _w0, _w1;
        if (far == Float.POSITIVE_INFINITY) {
            _w0 = -1.0f;
            _w1 = -(near + near);
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _w0 = 1.0f;
                _w1 = far + far;
            } else {
                _w0 = (far + near) * _t5_inv;
                _w1 = (far + far) * near * _t5_inv;
            }
        }
        perspectiveOffCenterFov_no_rh_fma_se6deaa53_v(dest, destOffset, src, srcOffset, (float) Math.tan(angleRight), (float) Math.tan(angleLeft), (float) Math.tan(angleUp), (float) Math.tan(angleDown), _w0, _w1);
        return dest;
    }

    private static void perspectiveOffCenterFov_no_rh_fma_se6deaa53_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _w0, float _w1) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (_t0 - _t1));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (_t2 - _t3));
        var _sv4 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(_sv1);
        var _c1 = _sv2.add(_sv2).mul(_sv3);
        var _c2 = _sv4.fma(FloatVector.broadcast(SIMD_SPECIES, _w0), _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t1 + _t0)).fma(_sv1, _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t3 + _t2)).mul(_sv3)).sub(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        var _c3 = _sv4.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] perspectiveOffCenterFov_no_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        float _t5_inv = 1.0f / (near - far);
        float _w0, _w1;
        if (far == Float.POSITIVE_INFINITY) {
            _w0 = -1.0f;
            _w1 = -(near + near);
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _w0 = 1.0f;
                _w1 = far + far;
            } else {
                _w0 = (far + near) * _t5_inv;
                _w1 = (far + far) * near * _t5_inv;
            }
        }
        perspectiveOffCenterFov_no_rh_mulAdd_sf3852118_v(dest, destOffset, src, srcOffset, (float) Math.tan(angleRight), (float) Math.tan(angleLeft), (float) Math.tan(angleUp), (float) Math.tan(angleDown), _w0, _w1);
        return dest;
    }

    private static void perspectiveOffCenterFov_no_rh_mulAdd_sf3852118_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _w0, float _w1) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (_t0 - _t1));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (_t2 - _t3));
        var _sv4 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(_sv1);
        var _c1 = _sv2.add(_sv2).mul(_sv3);
        var _c2 = _sv4.mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t1 + _t0)).mul(_sv1).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t3 + _t2)).mul(_sv3)).sub(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        var _c3 = _sv4.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] perspectiveOffCenterFov_zo_lh(float[] dest, int destOffset, float[] src, int srcOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        if (SimdSupport.USE_FMA) return perspectiveOffCenterFov_zo_lh_fma(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far);
        return perspectiveOffCenterFov_zo_lh_mulAdd(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far);
    }

    public static float[] perspectiveOffCenterFov_zo_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        float _t4_inv = 1.0f / (near - far);
        float _w0, _w1;
        if (far == Float.POSITIVE_INFINITY) {
            _w0 = 1.0f;
            _w1 = -near;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _w0 = 0.0f;
                _w1 = far;
            } else {
                _w0 = -(far * _t4_inv);
                _w1 = far * near * _t4_inv;
            }
        }
        perspectiveOffCenterFov_zo_lh_fma_s631dfaa5_v(dest, destOffset, src, srcOffset, (float) Math.tan(angleRight), (float) Math.tan(angleLeft), (float) Math.tan(angleUp), (float) Math.tan(angleDown), _w0, _w1);
        return dest;
    }

    private static void perspectiveOffCenterFov_zo_lh_fma_s631dfaa5_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _w0, float _w1) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (_t0 - _t1));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (_t2 - _t3));
        var _sv4 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(_sv1);
        var _c1 = _sv2.add(_sv2).mul(_sv3);
        var _c2 = _sv4.fma(FloatVector.broadcast(SIMD_SPECIES, _w0), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -(_t3 + _t2))).fma(_sv3, _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -(_t1 + _t0))).fma(_sv1, FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        var _c3 = _sv4.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] perspectiveOffCenterFov_zo_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        float _t4_inv = 1.0f / (near - far);
        float _w0, _w1;
        if (far == Float.POSITIVE_INFINITY) {
            _w0 = 1.0f;
            _w1 = -near;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _w0 = 0.0f;
                _w1 = far;
            } else {
                _w0 = -(far * _t4_inv);
                _w1 = far * near * _t4_inv;
            }
        }
        perspectiveOffCenterFov_zo_lh_mulAdd_s42572aa_v(dest, destOffset, src, srcOffset, (float) Math.tan(angleRight), (float) Math.tan(angleLeft), (float) Math.tan(angleUp), (float) Math.tan(angleDown), _w0, _w1);
        return dest;
    }

    private static void perspectiveOffCenterFov_zo_lh_mulAdd_s42572aa_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _w0, float _w1) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (_t0 - _t1));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (_t2 - _t3));
        var _sv4 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(_sv1);
        var _c1 = _sv2.add(_sv2).mul(_sv3);
        var _c2 = _sv4.mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -(_t3 + _t2))).mul(_sv3).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -(_t1 + _t0))).mul(_sv1).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        var _c3 = _sv4.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] perspectiveOffCenterFov_zo_rh(float[] dest, int destOffset, float[] src, int srcOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        if (SimdSupport.USE_FMA) return perspectiveOffCenterFov_zo_rh_fma(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far);
        return perspectiveOffCenterFov_zo_rh_mulAdd(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far);
    }

    public static float[] perspectiveOffCenterFov_zo_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        float _t4_inv = 1.0f / (near - far);
        float _w0, _w1;
        if (far == Float.POSITIVE_INFINITY) {
            _w0 = -1.0f;
            _w1 = -near;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _w0 = 0.0f;
                _w1 = far;
            } else {
                _w0 = far * _t4_inv;
                _w1 = far * near * _t4_inv;
            }
        }
        perspectiveOffCenterFov_zo_rh_fma_sd90c2abf_v(dest, destOffset, src, srcOffset, (float) Math.tan(angleRight), (float) Math.tan(angleLeft), (float) Math.tan(angleUp), (float) Math.tan(angleDown), _w0, _w1);
        return dest;
    }

    private static void perspectiveOffCenterFov_zo_rh_fma_sd90c2abf_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _w0, float _w1) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (_t0 - _t1));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (_t2 - _t3));
        var _sv4 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(_sv1);
        var _c1 = _sv2.add(_sv2).mul(_sv3);
        var _c2 = _sv4.fma(FloatVector.broadcast(SIMD_SPECIES, _w0), _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t1 + _t0)).fma(_sv1, _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t3 + _t2)).mul(_sv3)).sub(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        var _c3 = _sv4.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] perspectiveOffCenterFov_zo_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        float _t4_inv = 1.0f / (near - far);
        float _w0, _w1;
        if (far == Float.POSITIVE_INFINITY) {
            _w0 = -1.0f;
            _w1 = -near;
        } else {
            if (near == Float.POSITIVE_INFINITY) {
                _w0 = 0.0f;
                _w1 = far;
            } else {
                _w0 = far * _t4_inv;
                _w1 = far * near * _t4_inv;
            }
        }
        perspectiveOffCenterFov_zo_rh_mulAdd_sd790d544_v(dest, destOffset, src, srcOffset, (float) Math.tan(angleRight), (float) Math.tan(angleLeft), (float) Math.tan(angleUp), (float) Math.tan(angleDown), _w0, _w1);
        return dest;
    }

    private static void perspectiveOffCenterFov_zo_rh_mulAdd_sd790d544_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _w0, float _w1) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (_t0 - _t1));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, 1.0f / (_t2 - _t3));
        var _sv4 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.add(_sv0).mul(_sv1);
        var _c1 = _sv2.add(_sv2).mul(_sv3);
        var _c2 = _sv4.mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t1 + _t0)).mul(_sv1).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t3 + _t2)).mul(_sv3)).sub(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        var _c3 = _sv4.mul(FloatVector.broadcast(SIMD_SPECIES, _w1));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] pickMatrix(float[] dest, int destOffset, float[] src, int srcOffset, float centerX, float centerY, float deltaX, float deltaY, float vpX, float vpY, float vpW, float vpH) {
        if (SimdSupport.USE_FMA) return pickMatrix_fma(dest, destOffset, src, srcOffset, centerX, centerY, deltaX, deltaY, vpX, vpY, vpW, vpH);
        return pickMatrix_mulAdd(dest, destOffset, src, srcOffset, centerX, centerY, deltaX, deltaY, vpX, vpY, vpW, vpH);
    }

    public static float[] pickMatrix_fma(float[] dest, int destOffset, float[] src, int srcOffset, float centerX, float centerY, float deltaX, float deltaY, float vpX, float vpY, float vpW, float vpH) {
        float _rcp0 = 1.0f / deltaX;
        float _rcp1 = 1.0f / deltaY;
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, _rcp0);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, _rcp1);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, vpW).mul(_sv0).mul(_sv1);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, vpH).mul(_sv2).mul(_sv3);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(-2.0f, centerX - vpX, vpW))).fma(_sv1, _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(-2.0f, centerY - vpY, vpH))).mul(_sv3)));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] pickMatrix_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float centerX, float centerY, float deltaX, float deltaY, float vpX, float vpY, float vpW, float vpH) {
        float _rcp0 = 1.0f / deltaX;
        float _rcp1 = 1.0f / deltaY;
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, _rcp0);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, _rcp1);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, vpW).mul(_sv0).mul(_sv1);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, vpH).mul(_sv2).mul(_sv3);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(-2.0f, centerX - vpX, vpW))).mul(_sv1).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(-2.0f, centerY - vpY, vpH))).mul(_sv3)));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] preRotateAround(float[] dest, int destOffset, float[] src, int srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        if (SimdSupport.USE_FMA) return preRotateAround_fma(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return preRotateAround_mulAdd(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
    }

    public static float[] preRotateAround_fma(float[] dest, int destOffset, float[] src, int srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        float _t0 = -pivotZ;
        float _t1 = -rotY;
        float _t3 = -rotX;
        float _t5 = rotX + rotX;
        float _t6 = rotY + rotY;
        float _t7 = rotZ + rotZ;
        float _t8 = rotW * _t6;
        float _t9 = rotZ * _t7;
        float _t10 = rotW * _t7;
        float _t11 = rotW * _t5;
        float _t16 = Math.fma(-rotZ, _t7, 1.0f);
        float _t18 = Math.fma(rotZ, _t5, _t8);
        float _t21 = Math.fma(rotY, _t5, _t10);
        float _t22 = Math.fma(rotZ, _t6, _t11);
        float _t24 = Math.fma(rotY, _t5, -_t10);
        float _t25 = Math.fma(rotZ, _t6, -_t11);
        float _t26 = Math.fma(rotZ, _t5, -_t8);
        preRotateAround_fma_s9e20ed84_v(dest, destOffset, src, srcOffset, _t18, _t21, _t22, _t24, _t25, _t26, Math.fma(_t0, _t18, Math.fma(pivotX, Math.fma(rotY, _t6, _t9), -(pivotY * _t24))), Math.fma(_t0, _t25, Math.fma(pivotY, Math.fma(rotX, _t5, _t9), -(pivotX * _t21))), Math.fma(-pivotY, _t22, Math.fma(pivotZ, Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t26))), Math.fma(_t3, _t5, Math.fma(_t1, _t6, 1.0f)), Math.fma(_t1, _t6, _t16), Math.fma(_t3, _t5, _t16));
        return dest;
    }

    private static void preRotateAround_fma_s9e20ed84_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t18, float _t21, float _t22, float _t24, float _t25, float _t26, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, _t25).withLane(2, _h3);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h4).withLane(1, _t21).withLane(2, _t26);
        var _sv3 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, _h5).withLane(2, _t22);
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).fma(_sv3, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3])))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] preRotateAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        float _t0 = -pivotZ;
        float _t1 = -rotY;
        float _t3 = -rotX;
        float _t5 = rotX + rotX;
        float _t6 = rotY + rotY;
        float _t7 = rotZ + rotZ;
        float _t8 = rotW * _t6;
        float _t9 = rotZ * _t7;
        float _t10 = rotW * _t7;
        float _t11 = rotW * _t5;
        float _t16 = Math.fma(-rotZ, _t7, 1.0f);
        float _t18 = Math.fma(rotZ, _t5, _t8);
        float _t21 = Math.fma(rotY, _t5, _t10);
        float _t22 = Math.fma(rotZ, _t6, _t11);
        float _t24 = Math.fma(rotY, _t5, -_t10);
        float _t25 = Math.fma(rotZ, _t6, -_t11);
        float _t26 = Math.fma(rotZ, _t5, -_t8);
        preRotateAround_mulAdd_s842595dd_v(dest, destOffset, src, srcOffset, _t18, _t21, _t22, _t24, _t25, _t26, Math.fma(_t0, _t18, Math.fma(pivotX, Math.fma(rotY, _t6, _t9), -(pivotY * _t24))), Math.fma(_t0, _t25, Math.fma(pivotY, Math.fma(rotX, _t5, _t9), -(pivotX * _t21))), Math.fma(-pivotY, _t22, Math.fma(pivotZ, Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t26))), Math.fma(_t3, _t5, Math.fma(_t1, _t6, 1.0f)), Math.fma(_t1, _t6, _t16), Math.fma(_t3, _t5, _t16));
        return dest;
    }

    private static void preRotateAround_mulAdd_s842595dd_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t18, float _t21, float _t22, float _t24, float _t25, float _t26, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, _t25).withLane(2, _h3);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h4).withLane(1, _t21).withLane(2, _t26);
        var _sv3 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, _h5).withLane(2, _t22);
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).mul(_sv3).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3])))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
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
        float _t0 = -_pivotz;
        float _t1 = -_roty;
        float _t3 = -_rotx;
        float _t5 = _rotx + _rotx;
        float _t6 = _roty + _roty;
        float _t7 = _rotz + _rotz;
        float _t8 = _rotw * _t6;
        float _t9 = _rotz * _t7;
        float _t10 = _rotw * _t7;
        float _t11 = _rotw * _t5;
        float _t16 = Math.fma(-_rotz, _t7, 1.0f);
        float _t18 = Math.fma(_rotz, _t5, _t8);
        float _t21 = Math.fma(_roty, _t5, _t10);
        float _t22 = Math.fma(_rotz, _t6, _t11);
        float _t24 = Math.fma(_roty, _t5, -_t10);
        float _t25 = Math.fma(_rotz, _t6, -_t11);
        float _t26 = Math.fma(_rotz, _t5, -_t8);
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t18, Math.fma(_pivotx, Math.fma(_roty, _t6, _t9), -(_pivoty * _t24)))).withLane(1, Math.fma(_t0, _t25, Math.fma(_pivoty, Math.fma(_rotx, _t5, _t9), -(_pivotx * _t21)))).withLane(2, Math.fma(-_pivoty, _t22, Math.fma(_pivotz, Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t26))));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, _t25).withLane(2, Math.fma(_t3, _t5, Math.fma(_t1, _t6, 1.0f)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t1, _t6, _t16)).withLane(1, _t21).withLane(2, _t26);
        var _sv3 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, Math.fma(_t3, _t5, _t16)).withLane(2, _t22);
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).fma(_sv3, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3])))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
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
        float _t0 = -_pivotz;
        float _t1 = -_roty;
        float _t3 = -_rotx;
        float _t5 = _rotx + _rotx;
        float _t6 = _roty + _roty;
        float _t7 = _rotz + _rotz;
        float _t8 = _rotw * _t6;
        float _t9 = _rotz * _t7;
        float _t10 = _rotw * _t7;
        float _t11 = _rotw * _t5;
        float _t16 = Math.fma(-_rotz, _t7, 1.0f);
        float _t18 = Math.fma(_rotz, _t5, _t8);
        float _t21 = Math.fma(_roty, _t5, _t10);
        float _t22 = Math.fma(_rotz, _t6, _t11);
        float _t24 = Math.fma(_roty, _t5, -_t10);
        float _t25 = Math.fma(_rotz, _t6, -_t11);
        float _t26 = Math.fma(_rotz, _t5, -_t8);
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t18, Math.fma(_pivotx, Math.fma(_roty, _t6, _t9), -(_pivoty * _t24)))).withLane(1, Math.fma(_t0, _t25, Math.fma(_pivoty, Math.fma(_rotx, _t5, _t9), -(_pivotx * _t21)))).withLane(2, Math.fma(-_pivoty, _t22, Math.fma(_pivotz, Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t26))));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, _t25).withLane(2, Math.fma(_t3, _t5, Math.fma(_t1, _t6, 1.0f)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t1, _t6, _t16)).withLane(1, _t21).withLane(2, _t26);
        var _sv3 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, Math.fma(_t3, _t5, _t16)).withLane(2, _t22);
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).mul(_sv3).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3])))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
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
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(axisY, _t0, _t11 * _t2)).withLane(1, Math.fma(_t11, _t6, -(axisX * _t0))).withLane(2, Math.fma(_t11, axisZ * axisZ, _t1));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, axisX * axisX, _t1)).withLane(1, Math.fma(axisZ, _t0, _t11 * _t4)).withLane(2, Math.fma(_t11, _t2, -(axisY * _t0)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _t4, -(axisZ * _t0))).withLane(1, Math.fma(_t11, axisY * axisY, _t1)).withLane(2, Math.fma(axisX, _t0, _t11 * _t6));
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] preRotateAxis_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        preRotateAxis_mulAdd_s4c8392a1_v(dest, destOffset, src, srcOffset, axisX, axisY, axisZ, _t0, _t1, axisX * axisZ, axisX * axisY, axisY * axisZ, 1.0f - _t1);
        return dest;
    }

    private static void preRotateAxis_mulAdd_s4c8392a1_v(float[] dest, int destOffset, float[] src, int srcOffset, float axisX, float axisY, float axisZ, float _t0, float _t1, float _t2, float _t4, float _t6, float _t11) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(axisY, _t0, _t11 * _t2)).withLane(1, Math.fma(_t11, _t6, -(axisX * _t0))).withLane(2, Math.fma(_t11, axisZ * axisZ, _t1));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, axisX * axisX, _t1)).withLane(1, Math.fma(axisZ, _t0, _t11 * _t4)).withLane(2, Math.fma(_t11, _t2, -(axisY * _t0)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _t4, -(axisZ * _t0))).withLane(1, Math.fma(_t11, axisY * axisY, _t1)).withLane(2, Math.fma(axisX, _t0, _t11 * _t6));
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
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
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_axisy, _t0, _t11 * _t2)).withLane(1, Math.fma(_t11, _t6, -(_axisx * _t0))).withLane(2, Math.fma(_t11, _axisz * _axisz, _t1));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _axisx * _axisx, _t1)).withLane(1, Math.fma(_axisz, _t0, _t11 * _t4)).withLane(2, Math.fma(_t11, _t2, -(_axisy * _t0)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _t4, -(_axisz * _t0))).withLane(1, Math.fma(_t11, _axisy * _axisy, _t1)).withLane(2, Math.fma(_axisx, _t0, _t11 * _t6));
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
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
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_axisy, _t0, _t11 * _t2)).withLane(1, Math.fma(_t11, _t6, -(_axisx * _t0))).withLane(2, Math.fma(_t11, _axisz * _axisz, _t1));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _axisx * _axisx, _t1)).withLane(1, Math.fma(_axisz, _t0, _t11 * _t4)).withLane(2, Math.fma(_t11, _t2, -(_axisy * _t0)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _t4, -(_axisz * _t0))).withLane(1, Math.fma(_t11, _axisy * _axisy, _t1)).withLane(2, Math.fma(_axisx, _t0, _t11 * _t6));
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
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
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(qZ, _t3, _t6)).withLane(1, Math.fma(qZ, _t4, -_t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(qY, _t3, _t7)).withLane(2, Math.fma(qZ, _t3, -_t6));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(qY, _t3, -_t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(qZ, _t4, _t8));
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] preRotateQuat_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float qX, float qY, float qZ, float qW) {
        float _t3 = qX + qX;
        float _t4 = qY + qY;
        float _t5 = qZ + qZ;
        preRotateQuat_mulAdd_s4441ade4_v(dest, destOffset, src, srcOffset, qY, qZ, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, Math.fma(-qZ, _t5, 1.0f));
        return dest;
    }

    private static void preRotateQuat_mulAdd_s4441ade4_v(float[] dest, int destOffset, float[] src, int srcOffset, float qY, float qZ, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(qZ, _t3, _t6)).withLane(1, Math.fma(qZ, _t4, -_t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(qY, _t3, _t7)).withLane(2, Math.fma(qZ, _t3, -_t6));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(qY, _t3, -_t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(qZ, _t4, _t8));
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
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
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qz, _t3, _t6)).withLane(1, Math.fma(_qz, _t4, -_t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(_qy, _t3, _t7)).withLane(2, Math.fma(_qz, _t3, -_t6));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qy, _t3, -_t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(_qz, _t4, _t8));
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
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
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qz, _t3, _t6)).withLane(1, Math.fma(_qz, _t4, -_t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(_qy, _t3, _t7)).withLane(2, Math.fma(_qz, _t3, -_t6));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qy, _t3, -_t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(_qz, _t4, _t8));
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 2]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 0]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 1]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]))));
            _c.intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] preScale(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        if (SimdSupport.USE_FMA) return preScale_fma(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return preScale_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    public static float[] preScale_fma(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preScale_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preScale(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        if (SimdSupport.USE_FMA) return preScale_fma(dest, destOffset, src, srcOffset, v, vOffset);
        return preScale_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
    }

    public static float[] preScale_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        float _vx = v[vOffset + 0];
        float _vy = v[vOffset + 1];
        float _vz = v[vOffset + 2];
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preScale_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        float _vx = v[vOffset + 0];
        float _vy = v[vOffset + 1];
        float _vz = v[vOffset + 2];
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3]));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preScale(float[] dest, int destOffset, float[] src, int srcOffset, float s) {
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, s).mul(FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4))).withLane(3, src[(srcOffset + _li * 4) + 3]);
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
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, pivotX * _t0).withLane(1, pivotY * _t0).withLane(2, pivotZ * _t0);
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, s).fma(FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)), FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).mul(_sv0)).withLane(3, src[(srcOffset + _li * 4) + 3]);
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preScaleAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        float _t0 = 1.0f - s;
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, pivotX * _t0).withLane(1, pivotY * _t0).withLane(2, pivotZ * _t0);
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, s).mul(FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4))).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).mul(_sv0)).withLane(3, src[(srcOffset + _li * 4) + 3]);
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preScaleAround(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float s) {
        if (SimdSupport.USE_FMA) return preScaleAround_fma(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return preScaleAround_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    public static float[] preScaleAround_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float s) {
        float _pivotx = pivot[pivotOffset + 0];
        float _pivoty = pivot[pivotOffset + 1];
        float _pivotz = pivot[pivotOffset + 2];
        float _t0 = 1.0f - s;
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _pivotx * _t0).withLane(1, _pivoty * _t0).withLane(2, _pivotz * _t0);
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, s).fma(FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)), FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).mul(_sv0)).withLane(3, src[(srcOffset + _li * 4) + 3]);
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preScaleAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float s) {
        float _pivotx = pivot[pivotOffset + 0];
        float _pivoty = pivot[pivotOffset + 1];
        float _pivotz = pivot[pivotOffset + 2];
        float _t0 = 1.0f - s;
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _pivotx * _t0).withLane(1, _pivoty * _t0).withLane(2, _pivotz * _t0);
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, s).mul(FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4))).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).mul(_sv0)).withLane(3, src[(srcOffset + _li * 4) + 3]);
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preScaleAround(float[] dest, int destOffset, float[] src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        if (SimdSupport.USE_FMA) return preScaleAround_fma(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return preScaleAround_mulAdd(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
    }

    public static float[] preScaleAround_fma(float[] dest, int destOffset, float[] src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, sX).withLane(1, sY).withLane(2, sZ);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, pivotX * (1.0f - sX)).withLane(1, pivotY * (1.0f - sY)).withLane(2, pivotZ * (1.0f - sZ));
        for (int _li = 0; _li < 4; _li++) {
            var _c = _sv0.fma(FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)), FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3])));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preScaleAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, sX).withLane(1, sY).withLane(2, sZ);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, pivotX * (1.0f - sX)).withLane(1, pivotY * (1.0f - sY)).withLane(2, pivotZ * (1.0f - sZ));
        for (int _li = 0; _li < 4; _li++) {
            var _c = _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4))).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3])));
            _c.intoArray(dest, destOffset + _li * 4);
        }
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
        float _pivotx = pivot[pivotOffset + 0];
        float _pivoty = pivot[pivotOffset + 1];
        float _pivotz = pivot[pivotOffset + 2];
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _sx).withLane(1, _sy).withLane(2, _sz);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _pivotx * (1.0f - _sx)).withLane(1, _pivoty * (1.0f - _sy)).withLane(2, _pivotz * (1.0f - _sz));
        for (int _li = 0; _li < 4; _li++) {
            var _c = _sv0.fma(FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4)), FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3])));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preScaleAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] s, int sOffset, float[] pivot, int pivotOffset) {
        float _sx = s[sOffset + 0];
        float _sy = s[sOffset + 1];
        float _sz = s[sOffset + 2];
        float _pivotx = pivot[pivotOffset + 0];
        float _pivoty = pivot[pivotOffset + 1];
        float _pivotz = pivot[pivotOffset + 2];
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _sx).withLane(1, _sy).withLane(2, _sz);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _pivotx * (1.0f - _sx)).withLane(1, _pivoty * (1.0f - _sy)).withLane(2, _pivotz * (1.0f - _sz));
        for (int _li = 0; _li < 4; _li++) {
            var _c = _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4))).add(FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[(srcOffset + _li * 4) + 3])));
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preTranslate(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        if (SimdSupport.USE_FMA) return preTranslate_fma(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return preTranslate_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    public static float[] preTranslate_fma(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).fma(_sv0, FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4))).withLane(3, src[(srcOffset + _li * 4) + 3]);
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preTranslate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).mul(_sv0).add(FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4))).withLane(3, src[(srcOffset + _li * 4) + 3]);
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preTranslate(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        if (SimdSupport.USE_FMA) return preTranslate_fma(dest, destOffset, src, srcOffset, v, vOffset);
        return preTranslate_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
    }

    public static float[] preTranslate_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        float _vx = v[vOffset + 0];
        float _vy = v[vOffset + 1];
        float _vz = v[vOffset + 2];
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).fma(_sv0, FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4))).withLane(3, src[(srcOffset + _li * 4) + 3]);
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preTranslate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        float _vx = v[vOffset + 0];
        float _vy = v[vOffset + 1];
        float _vz = v[vOffset + 2];
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 4; _li++) {
            var _c = FloatVector.broadcast(SIMD_SPECIES, src[(srcOffset + _li * 4) + 3]).mul(_sv0).add(FloatVector.fromArray(SIMD_SPECIES, src, (srcOffset + _li * 4))).withLane(3, src[(srcOffset + _li * 4) + 3]);
            _c.intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] reflect(float[] dest, int destOffset, float[] src, int srcOffset, float normalX, float normalY, float normalZ) {
        if (SimdSupport.USE_FMA) return reflect_fma(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
        return reflect_mulAdd(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
    }

    public static float[] reflect_fma(float[] dest, int destOffset, float[] src, int srcOffset, float normalX, float normalY, float normalZ) {
        reflect_fma_s4659ae95_v(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], (normalX + normalX) * normalZ, (normalY + normalY) * normalZ);
        return dest;
    }

    private static void reflect_fma_s4659ae95_v(float[] dest, int destOffset, float[] src, int srcOffset, float normalX, float normalY, float normalZ, float _self02, float _self12, float _self22, float _self32, float _t10, float _t12) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, -((normalX + normalX) * normalY));
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t10), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(-2.0f, normalX * normalX, 1.0f)), _sv2.mul(_sv3)));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t12), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(-2.0f, normalY * normalY, 1.0f)), _sv1.mul(_sv3)));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(-2.0f, normalZ * normalZ, 1.0f)), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, -_t12), _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -_t10))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] reflect_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float normalX, float normalY, float normalZ) {
        reflect_mulAdd_s88ff2b0e_v(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], (normalX + normalX) * normalZ, (normalY + normalY) * normalZ);
        return dest;
    }

    private static void reflect_mulAdd_s88ff2b0e_v(float[] dest, int destOffset, float[] src, int srcOffset, float normalX, float normalY, float normalZ, float _self02, float _self12, float _self22, float _self32, float _t10, float _t12) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, -((normalX + normalX) * normalY));
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t10)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(-2.0f, normalX * normalX, 1.0f))).add(_sv2.mul(_sv3)));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t12)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(-2.0f, normalY * normalY, 1.0f))).add(_sv1.mul(_sv3)));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(-2.0f, normalZ * normalZ, 1.0f))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -_t12)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -_t10))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] reflect(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        if (SimdSupport.USE_FMA) return reflect_fma(dest, destOffset, src, srcOffset, normal, normalOffset);
        return reflect_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    public static float[] reflect_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        float _normalx = normal[normalOffset + 0];
        float _normaly = normal[normalOffset + 1];
        float _normalz = normal[normalOffset + 2];
        reflect_fma_s429546cf_v(dest, destOffset, src, srcOffset, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _normalx, _normaly, _normalz, (_normalx + _normalx) * _normalz, (_normaly + _normaly) * _normalz);
        return dest;
    }

    private static void reflect_fma_s429546cf_v(float[] dest, int destOffset, float[] src, int srcOffset, float _self02, float _self12, float _self22, float _self32, float _normalx, float _normaly, float _normalz, float _t10, float _t12) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, -((_normalx + _normalx) * _normaly));
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t10), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(-2.0f, _normalx * _normalx, 1.0f)), _sv2.mul(_sv3)));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t12), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(-2.0f, _normaly * _normaly, 1.0f)), _sv1.mul(_sv3)));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(-2.0f, _normalz * _normalz, 1.0f)), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, -_t12), _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -_t10))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] reflect_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        float _normalx = normal[normalOffset + 0];
        float _normaly = normal[normalOffset + 1];
        float _normalz = normal[normalOffset + 2];
        reflect_mulAdd_s97e34512_v(dest, destOffset, src, srcOffset, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _normalx, _normaly, _normalz, (_normalx + _normalx) * _normalz, (_normaly + _normaly) * _normalz);
        return dest;
    }

    private static void reflect_mulAdd_s97e34512_v(float[] dest, int destOffset, float[] src, int srcOffset, float _self02, float _self12, float _self22, float _self32, float _normalx, float _normaly, float _normalz, float _t10, float _t12) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, -((_normalx + _normalx) * _normaly));
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t10)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(-2.0f, _normalx * _normalx, 1.0f))).add(_sv2.mul(_sv3)));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t12)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(-2.0f, _normaly * _normaly, 1.0f))).add(_sv1.mul(_sv3)));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(-2.0f, _normalz * _normalz, 1.0f))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -_t12)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -_t10))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateAround(float[] dest, int destOffset, float[] src, int srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        if (SimdSupport.USE_FMA) return rotateAround_fma(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return rotateAround_mulAdd(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
    }

    public static float[] rotateAround_fma(float[] dest, int destOffset, float[] src, int srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        float _t0 = -rotY;
        float _t2 = -rotX;
        float _t3 = -pivotZ;
        float _t5 = rotX + rotX;
        float _t6 = rotY + rotY;
        float _t7 = rotZ + rotZ;
        float _t8 = rotW * _t6;
        float _t9 = rotW * _t7;
        float _t10 = rotW * _t5;
        float _t11 = rotZ * _t7;
        float _t16 = Math.fma(-rotZ, _t7, 1.0f);
        float _t18 = Math.fma(rotY, _t5, _t9);
        float _t19 = Math.fma(rotZ, _t6, _t10);
        float _t20 = Math.fma(rotZ, _t5, _t8);
        float _t24 = Math.fma(rotZ, _t5, -_t8);
        float _t25 = Math.fma(rotY, _t5, -_t9);
        float _t26 = Math.fma(rotZ, _t6, -_t10);
        rotateAround_fma_s4369e68d_v(dest, destOffset, src, srcOffset, _t18, _t19, _t20, _t24, _t25, _t26, Math.fma(_t0, _t6, _t16), Math.fma(_t2, _t5, _t16), Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0f)), Math.fma(_t3, _t20, Math.fma(pivotX, Math.fma(rotY, _t6, _t11), -(pivotY * _t25))), Math.fma(_t3, _t26, Math.fma(pivotY, Math.fma(rotX, _t5, _t11), -(pivotX * _t18))), Math.fma(-pivotY, _t19, Math.fma(pivotZ, Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t24))));
        return dest;
    }

    private static void rotateAround_fma_s4369e68d_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t18, float _t19, float _t20, float _t24, float _t25, float _t26, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t24), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h0), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t18))));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t19), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t25), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h1))));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h2), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t20), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t26))));
        var _c3 = _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h3), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _h4), _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h5), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        float _t0 = -rotY;
        float _t2 = -rotX;
        float _t3 = -pivotZ;
        float _t5 = rotX + rotX;
        float _t6 = rotY + rotY;
        float _t7 = rotZ + rotZ;
        float _t8 = rotW * _t6;
        float _t9 = rotW * _t7;
        float _t10 = rotW * _t5;
        float _t11 = rotZ * _t7;
        float _t16 = Math.fma(-rotZ, _t7, 1.0f);
        float _t18 = Math.fma(rotY, _t5, _t9);
        float _t19 = Math.fma(rotZ, _t6, _t10);
        float _t20 = Math.fma(rotZ, _t5, _t8);
        float _t24 = Math.fma(rotZ, _t5, -_t8);
        float _t25 = Math.fma(rotY, _t5, -_t9);
        float _t26 = Math.fma(rotZ, _t6, -_t10);
        rotateAround_mulAdd_s41feee86_v(dest, destOffset, src, srcOffset, _t18, _t19, _t20, _t24, _t25, _t26, Math.fma(_t0, _t6, _t16), Math.fma(_t2, _t5, _t16), Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0f)), Math.fma(_t3, _t20, Math.fma(pivotX, Math.fma(rotY, _t6, _t11), -(pivotY * _t25))), Math.fma(_t3, _t26, Math.fma(pivotY, Math.fma(rotX, _t5, _t11), -(pivotX * _t18))), Math.fma(-pivotY, _t19, Math.fma(pivotZ, Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t24))));
        return dest;
    }

    private static void rotateAround_mulAdd_s41feee86_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t18, float _t19, float _t20, float _t24, float _t25, float _t26, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t24)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h0)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t18))));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t19)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t25)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h1))));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h2)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t20)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t26))));
        var _c3 = _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h3)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h4)).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h5)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        float _t0 = -_roty;
        float _t2 = -_rotx;
        float _t3 = -_pivotz;
        float _t5 = _rotx + _rotx;
        float _t6 = _roty + _roty;
        float _t7 = _rotz + _rotz;
        float _t8 = _rotw * _t6;
        float _t9 = _rotw * _t7;
        float _t10 = _rotw * _t5;
        float _t11 = _rotz * _t7;
        float _t16 = Math.fma(-_rotz, _t7, 1.0f);
        float _t18 = Math.fma(_roty, _t5, _t9);
        float _t19 = Math.fma(_rotz, _t6, _t10);
        float _t20 = Math.fma(_rotz, _t5, _t8);
        float _t24 = Math.fma(_rotz, _t5, -_t8);
        float _t25 = Math.fma(_roty, _t5, -_t9);
        float _t26 = Math.fma(_rotz, _t6, -_t10);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t24), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t6, _t16)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t18))));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t19), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t25), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t5, _t16)))));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0f))), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t20), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t26))));
        var _c3 = _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t3, _t20, Math.fma(_pivotx, Math.fma(_roty, _t6, _t11), -(_pivoty * _t25)))), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t3, _t26, Math.fma(_pivoty, Math.fma(_rotx, _t5, _t11), -(_pivotx * _t18)))), _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(-_pivoty, _t19, Math.fma(_pivotz, Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t24)))), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] rotateAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] rot, int rotOffset, float[] pivot, int pivotOffset) {
        float _pivotx = pivot[pivotOffset + 0];
        float _roty = rot[rotOffset + 1];
        float _pivoty = pivot[pivotOffset + 1];
        float _rotx = rot[rotOffset + 0];
        float _pivotz = pivot[pivotOffset + 2];
        float _rotz = rot[rotOffset + 2];
        float _rotw = rot[rotOffset + 3];
        float _t0 = -_roty;
        float _t2 = -_rotx;
        float _t3 = -_pivotz;
        float _t5 = _rotx + _rotx;
        float _t6 = _roty + _roty;
        float _t7 = _rotz + _rotz;
        float _t8 = _rotw * _t6;
        float _t9 = _rotw * _t7;
        float _t10 = _rotw * _t5;
        float _t11 = _rotz * _t7;
        float _t16 = Math.fma(-_rotz, _t7, 1.0f);
        float _t18 = Math.fma(_roty, _t5, _t9);
        float _t19 = Math.fma(_rotz, _t6, _t10);
        float _t20 = Math.fma(_rotz, _t5, _t8);
        float _t24 = Math.fma(_rotz, _t5, -_t8);
        float _t25 = Math.fma(_roty, _t5, -_t9);
        float _t26 = Math.fma(_rotz, _t6, -_t10);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t24)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t6, _t16))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t18))));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t19)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t25)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t5, _t16)))));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0f)))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t20)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t26))));
        var _c3 = _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t3, _t20, Math.fma(_pivotx, Math.fma(_roty, _t6, _t11), -(_pivoty * _t25))))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t3, _t26, Math.fma(_pivoty, Math.fma(_rotx, _t5, _t11), -(_pivotx * _t18))))).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(-_pivoty, _t19, Math.fma(_pivotz, Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t24))))).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
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
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t2, -(axisY * _t0))), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, axisX * axisX, _t1)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(axisZ, _t0, _t11 * _t5)))));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(axisX, _t0, _t11 * _t6)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t5, -(axisZ * _t0))), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, axisY * axisY, _t1)))));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, axisZ * axisZ, _t1)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(axisY, _t0, _t11 * _t2)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t6, -(axisX * _t0))))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateAxis_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisZ;
        float _t5 = axisX * axisY;
        float _t6 = axisY * axisZ;
        float _t11 = 1.0f - _t1;
        rotateAxis_mulAdd_s2eafaf86_v(dest, destOffset, src, srcOffset, Math.fma(_t11, _t2, -(axisY * _t0)), Math.fma(_t11, axisX * axisX, _t1), Math.fma(axisZ, _t0, _t11 * _t5), Math.fma(axisX, _t0, _t11 * _t6), Math.fma(_t11, _t5, -(axisZ * _t0)), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(axisY, _t0, _t11 * _t2), Math.fma(_t11, _t6, -(axisX * _t0)));
        return dest;
    }

    private static void rotateAxis_mulAdd_s2eafaf86_v(float[] dest, int destOffset, float[] src, int srcOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h0)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h1)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h2))));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h3)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h4)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h5))));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h6)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h7)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h8))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t2, -(_axisy * _t0))), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisx * _axisx, _t1)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_axisz, _t0, _t11 * _t5)))));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_axisx, _t0, _t11 * _t6)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t5, -(_axisz * _t0))), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisy * _axisy, _t1)))));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisz * _axisz, _t1)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_axisy, _t0, _t11 * _t2)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t6, -(_axisx * _t0))))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateAxis_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] axis, int axisOffset, float angle) {
        float _axisx = axis[axisOffset + 0];
        float _axisz = axis[axisOffset + 2];
        float _axisy = axis[axisOffset + 1];
        float _t0 = (float) Math.sin(angle);
        float _t1 = (float) Math.cosFromSin(_t0, angle);
        float _t2 = _axisx * _axisz;
        float _t5 = _axisx * _axisy;
        float _t6 = _axisy * _axisz;
        float _t11 = 1.0f - _t1;
        rotateAxis_mulAdd_sed598ce0_v(dest, destOffset, src, srcOffset, Math.fma(_t11, _t2, -(_axisy * _t0)), Math.fma(_t11, _axisx * _axisx, _t1), Math.fma(_axisz, _t0, _t11 * _t5), Math.fma(_axisx, _t0, _t11 * _t6), Math.fma(_t11, _t5, -(_axisz * _t0)), Math.fma(_t11, _axisy * _axisy, _t1), Math.fma(_t11, _axisz * _axisz, _t1), Math.fma(_axisy, _t0, _t11 * _t2), Math.fma(_t11, _t6, -(_axisx * _t0)));
        return dest;
    }

    private static void rotateAxis_mulAdd_sed598ce0_v(float[] dest, int destOffset, float[] src, int srcOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h0)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h1)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h2))));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h3)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h4)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h5))));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h6)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h7)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h8))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t3, -_t6)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t4, _t12)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qY, _t3, _t7)))));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t4, _t8)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qY, _t3, -_t7)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, _t12)))));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f))), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t3, _t6)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t4, -_t8)))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateQuat_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float qX, float qY, float qZ, float qW) {
        float _t3 = qX + qX;
        float _t4 = qY + qY;
        float _t5 = qZ + qZ;
        rotateQuat_mulAdd_sb4742b03_v(dest, destOffset, src, srcOffset, qY, qZ, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, Math.fma(-qZ, _t5, 1.0f));
        return dest;
    }

    private static void rotateQuat_mulAdd_sb4742b03_v(float[] dest, int destOffset, float[] src, int srcOffset, float qY, float qZ, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t3, -_t6))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t4, _t12))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qY, _t3, _t7)))));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t4, _t8))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qY, _t3, -_t7))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, _t12)))));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t3, _t6))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t4, -_t8)))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t3, -_t6)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t4, _t12)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qy, _t3, _t7)))));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t4, _t8)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qy, _t3, -_t7)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, _t12)))));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f))), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t3, _t6)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t4, -_t8)))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t3, -_t6))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t4, _t12))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qy, _t3, _t7)))));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t4, _t8))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qy, _t3, -_t7))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, _t12)))));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t3, _t6))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t4, -_t8)))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateX(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        if (SimdSupport.USE_FMA) return rotateX_fma(dest, destOffset, src, srcOffset, angle);
        return rotateX_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static float[] rotateX_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, (float) Math.cosFromSin(_t0, angle));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = _sv0.fma(_sv1, _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t0)));
        var _c2 = _sv2.fma(_sv1, _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -_t0)));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] rotateX_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, (float) Math.cosFromSin(_t0, angle));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c1 = _sv0.mul(_sv1).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t0)));
        var _c2 = _sv2.mul(_sv1).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -_t0)));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] rotateXYZ(float[] dest, int destOffset, float[] src, int srcOffset, float angleX, float angleY, float angleZ) {
        if (SimdSupport.USE_FMA) return rotateXYZ_fma(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
        return rotateXYZ_mulAdd(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
    }

    public static float[] rotateXYZ_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angleX, float angleY, float angleZ) {
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleY);
        float _t3 = (float) Math.cosFromSin(_t0, angleX);
        rotateXYZ_fma_s4ce4eafa_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, _t3, (float) Math.cosFromSin(_t1, angleZ), (float) Math.cosFromSin(_t2, angleY), _t0 * _t2, _t2 * _t3);
        return dest;
    }

    private static void rotateXYZ_fma_s4ce4eafa_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t7) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t1, -(_t7 * _t4))), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t5 * _t4), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t4, _t1 * _t3)))));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t7, _t1, _t0 * _t4)), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t3, _t4, -(_t6 * _t1))), _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -(_t1 * _t5)))));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t3 * _t5), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t2), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -(_t0 * _t5)))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateXYZ_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleX, float angleY, float angleZ) {
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleY);
        float _t3 = (float) Math.cosFromSin(_t0, angleX);
        rotateXYZ_mulAdd_s3803180d_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, _t3, (float) Math.cosFromSin(_t1, angleZ), (float) Math.cosFromSin(_t2, angleY), _t0 * _t2, _t2 * _t3);
        return dest;
    }

    private static void rotateXYZ_mulAdd_s3803180d_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t7) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t1, -(_t7 * _t4)))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t5 * _t4)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t4, _t1 * _t3)))));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t7, _t1, _t0 * _t4))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t3, _t4, -(_t6 * _t1)))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -(_t1 * _t5)))));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t3 * _t5)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t2)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -(_t0 * _t5)))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateXZY(float[] dest, int destOffset, float[] src, int srcOffset, float angleX, float angleZ, float angleY) {
        if (SimdSupport.USE_FMA) return rotateXZY_fma(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
        return rotateXZY_mulAdd(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
    }

    public static float[] rotateXZY_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angleX, float angleZ, float angleY) {
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleY);
        float _t4 = (float) Math.cosFromSin(_t0, angleX);
        rotateXZY_fma_s3f7f0110_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, (float) Math.cosFromSin(_t2, angleY), _t4, (float) Math.cosFromSin(_t1, angleZ), _t0 * _t1, _t1 * _t4);
        return dest;
    }

    private static void rotateXZY_fma_s3f7f0110_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t9) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t3, -(_t2 * _t4))), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t3 * _t5), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t9, _t3, _t0 * _t2)))));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t0 * _t5), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _t4 * _t5), _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -_t1))));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t2, _t4 * _t3)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t2 * _t5), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t9, _t2, -(_t0 * _t3))))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateXZY_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleX, float angleZ, float angleY) {
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleY);
        float _t4 = (float) Math.cosFromSin(_t0, angleX);
        rotateXZY_mulAdd_sfbec7f93_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, (float) Math.cosFromSin(_t2, angleY), _t4, (float) Math.cosFromSin(_t1, angleZ), _t0 * _t1, _t1 * _t4);
        return dest;
    }

    private static void rotateXZY_mulAdd_sfbec7f93_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t9) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t3, -(_t2 * _t4)))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t3 * _t5)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t9, _t3, _t0 * _t2)))));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t0 * _t5)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t4 * _t5)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -_t1))));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t2, _t4 * _t3))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t2 * _t5)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t9, _t2, -(_t0 * _t3))))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateY(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        if (SimdSupport.USE_FMA) return rotateY_fma(dest, destOffset, src, srcOffset, angle);
        return rotateY_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static float[] rotateY_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, (float) Math.cosFromSin(_t0, angle));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.fma(_sv1, _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -_t0)));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t0), _sv2.mul(_sv1));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] rotateY_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, (float) Math.cosFromSin(_t0, angle));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.mul(_sv1).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -_t0)));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t0)).add(_sv2.mul(_sv1));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
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
        rotateYXZ_fma_s7bb9f37c_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, _t3, (float) Math.cosFromSin(_t2, angleZ), (float) Math.cosFromSin(_t0, angleX), _t0 * _t1, _t0 * _t3);
        return dest;
    }

    private static void rotateYXZ_fma_s7bb9f37c_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t8) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t8, _t2, -(_t1 * _t4))), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t2, _t3 * _t4)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t2 * _t5))));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t8, _t4, _t1 * _t2)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t4, -(_t2 * _t3))), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t5 * _t4))));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t5 * _t3), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t1 * _t5), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -_t0))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateYXZ_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleY, float angleX, float angleZ) {
        float _t0 = (float) Math.sin(angleX);
        float _t1 = (float) Math.sin(angleY);
        float _t2 = (float) Math.sin(angleZ);
        float _t3 = (float) Math.cosFromSin(_t1, angleY);
        rotateYXZ_mulAdd_sb0d4e247_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, _t3, (float) Math.cosFromSin(_t2, angleZ), (float) Math.cosFromSin(_t0, angleX), _t0 * _t1, _t0 * _t3);
        return dest;
    }

    private static void rotateYXZ_mulAdd_sb0d4e247_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t8) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t8, _t2, -(_t1 * _t4)))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t2, _t3 * _t4))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t2 * _t5))));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t8, _t4, _t1 * _t2))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t4, -(_t2 * _t3)))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t5 * _t4))));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t5 * _t3)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t1 * _t5)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -_t0))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateYZX(float[] dest, int destOffset, float[] src, int srcOffset, float angleY, float angleZ, float angleX) {
        if (SimdSupport.USE_FMA) return rotateYZX_fma(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
        return rotateYZX_mulAdd(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
    }

    public static float[] rotateYZX_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angleY, float angleZ, float angleX) {
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t4 = (float) Math.cosFromSin(_t0, angleY);
        rotateYZX_fma_s61c5d7fc_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, (float) Math.cosFromSin(_t1, angleZ), _t4, (float) Math.cosFromSin(_t2, angleX), _t0 * _t1, _t1 * _t4);
        return dest;
    }

    private static void rotateYZX_fma_s61c5d7fc_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t9) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, -(_t0 * _t3)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t4 * _t3), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t1))));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t5, _t2 * _t4)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t0, -(_t9 * _t5))), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t5 * _t3))));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t5, _t4, -(_t6 * _t2))), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t9, _t2, _t0 * _t5)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -(_t2 * _t3)))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateYZX_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleY, float angleZ, float angleX) {
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t4 = (float) Math.cosFromSin(_t0, angleY);
        rotateYZX_mulAdd_s11cf53bf_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, (float) Math.cosFromSin(_t1, angleZ), _t4, (float) Math.cosFromSin(_t2, angleX), _t0 * _t1, _t1 * _t4);
        return dest;
    }

    private static void rotateYZX_mulAdd_s11cf53bf_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t9) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -(_t0 * _t3))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t4 * _t3)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t1))));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t5, _t2 * _t4))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t0, -(_t9 * _t5)))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t5 * _t3))));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t5, _t4, -(_t6 * _t2)))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t9, _t2, _t0 * _t5))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -(_t2 * _t3)))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateZ(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        if (SimdSupport.USE_FMA) return rotateZ_fma(dest, destOffset, src, srcOffset, angle);
        return rotateZ_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static float[] rotateZ_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, (float) Math.cosFromSin(_t0, angle));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(_sv1, _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t0)));
        var _c1 = _sv2.fma(_sv1, _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -_t0)));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] rotateZ_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t0 = (float) Math.sin(angle);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, (float) Math.cosFromSin(_t0, angle));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(_sv1).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t0)));
        var _c1 = _sv2.mul(_sv1).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -_t0)));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] rotateZXY(float[] dest, int destOffset, float[] src, int srcOffset, float angleZ, float angleX, float angleY) {
        if (SimdSupport.USE_FMA) return rotateZXY_fma(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
        return rotateZXY_mulAdd(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
    }

    public static float[] rotateZXY_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angleZ, float angleX, float angleY) {
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleX);
        float _t2 = (float) Math.sin(angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleZ);
        rotateZXY_fma_sbae691a4_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, (float) Math.cosFromSin(_t1, angleX), (float) Math.cosFromSin(_t0, angleY), _t5, _t1 * _t2, _t1 * _t5);
        return dest;
    }

    private static void rotateZXY_fma_sbae691a4_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t8) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, -(_t0 * _t3)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t4, _t5, -(_t6 * _t0))), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t8, _t0, _t2 * _t4)))));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t1), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _t3 * _t5), _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -(_t2 * _t3)))));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t3 * _t4), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t4, _t0 * _t5)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t2, -(_t8 * _t4))))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateZXY_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleZ, float angleX, float angleY) {
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleX);
        float _t2 = (float) Math.sin(angleZ);
        float _t5 = (float) Math.cosFromSin(_t2, angleZ);
        rotateZXY_mulAdd_sa4c97f6f_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, (float) Math.cosFromSin(_t1, angleX), (float) Math.cosFromSin(_t0, angleY), _t5, _t1 * _t2, _t1 * _t5);
        return dest;
    }

    private static void rotateZXY_mulAdd_sa4c97f6f_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t8) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -(_t0 * _t3))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t4, _t5, -(_t6 * _t0)))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t8, _t0, _t2 * _t4)))));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t1)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t3 * _t5)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, -(_t2 * _t3)))));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t3 * _t4)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t4, _t0 * _t5))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t2, -(_t8 * _t4))))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateZYX(float[] dest, int destOffset, float[] src, int srcOffset, float angleZ, float angleY, float angleX) {
        if (SimdSupport.USE_FMA) return rotateZYX_fma(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
        return rotateZYX_mulAdd(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
    }

    public static float[] rotateZYX_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angleZ, float angleY, float angleX) {
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        rotateZYX_fma_s6016a8de_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, (float) Math.cosFromSin(_t0, angleY), _t4, (float) Math.cosFromSin(_t2, angleX), _t0 * _t1, _t0 * _t4);
        return dest;
    }

    private static void rotateZYX_fma_s6016a8de_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t10) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, -_t0), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t3 * _t4), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t1 * _t3))));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t2 * _t3), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t10, _t2, -(_t1 * _t5))), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t2, _t5 * _t4)))));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t5 * _t3), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t10, _t5, _t2 * _t1)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t5, -(_t2 * _t4))))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateZYX_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleZ, float angleY, float angleX) {
        float _t0 = (float) Math.sin(angleY);
        float _t1 = (float) Math.sin(angleZ);
        float _t2 = (float) Math.sin(angleX);
        float _t4 = (float) Math.cosFromSin(_t1, angleZ);
        rotateZYX_mulAdd_s534aa8d1_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, (float) Math.cosFromSin(_t0, angleY), _t4, (float) Math.cosFromSin(_t2, angleX), _t0 * _t1, _t0 * _t4);
        return dest;
    }

    private static void rotateZYX_mulAdd_s534aa8d1_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t10) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -_t0)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t3 * _t4)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t1 * _t3))));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t2 * _t3)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t10, _t2, -(_t1 * _t5)))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t2, _t5 * _t4)))));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t5 * _t3)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t10, _t5, _t2 * _t1))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t6, _t5, -(_t2 * _t4))))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] scale(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, vX));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(SIMD_SPECIES, vY));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).mul(FloatVector.broadcast(SIMD_SPECIES, vZ));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] scale(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, v[vOffset + 0]));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(SIMD_SPECIES, v[vOffset + 1]));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).mul(FloatVector.broadcast(SIMD_SPECIES, v[vOffset + 2]));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] scale(float[] dest, int destOffset, float[] src, int srcOffset, float s) {
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, s);
        var _c0 = _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset));
        var _c1 = _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4));
        var _c2 = _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] scaleAround(float[] dest, int destOffset, float[] src, int srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        if (SimdSupport.USE_FMA) return scaleAround_fma(dest, destOffset, src, srcOffset, s, pivotX, pivotY, pivotZ);
        return scaleAround_mulAdd(dest, destOffset, src, srcOffset, s, pivotX, pivotY, pivotZ);
    }

    public static float[] scaleAround_fma(float[] dest, int destOffset, float[] src, int srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        float _t0 = 1.0f - s;
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.mul(_sv1);
        var _c1 = _sv0.mul(_sv2);
        var _c2 = _sv0.mul(_sv3);
        var _c3 = _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, pivotX * _t0), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, pivotY * _t0), _sv3.fma(FloatVector.broadcast(SIMD_SPECIES, pivotZ * _t0), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] scaleAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        float _t0 = 1.0f - s;
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.mul(_sv1);
        var _c1 = _sv0.mul(_sv2);
        var _c2 = _sv0.mul(_sv3);
        var _c3 = _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, pivotX * _t0)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, pivotY * _t0)).add(_sv3.mul(FloatVector.broadcast(SIMD_SPECIES, pivotZ * _t0)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] scaleAround(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float s) {
        if (SimdSupport.USE_FMA) return scaleAround_fma(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return scaleAround_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    public static float[] scaleAround_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float s) {
        float _t0 = 1.0f - s;
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.mul(_sv1);
        var _c1 = _sv0.mul(_sv2);
        var _c2 = _sv0.mul(_sv3);
        var _c3 = _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, pivot[pivotOffset + 0] * _t0), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, pivot[pivotOffset + 1] * _t0), _sv3.fma(FloatVector.broadcast(SIMD_SPECIES, pivot[pivotOffset + 2] * _t0), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] scaleAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float s) {
        float _t0 = 1.0f - s;
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0.mul(_sv1);
        var _c1 = _sv0.mul(_sv2);
        var _c2 = _sv0.mul(_sv3);
        var _c3 = _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, pivot[pivotOffset + 0] * _t0)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, pivot[pivotOffset + 1] * _t0)).add(_sv3.mul(FloatVector.broadcast(SIMD_SPECIES, pivot[pivotOffset + 2] * _t0)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] scaleAround(float[] dest, int destOffset, float[] src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        if (SimdSupport.USE_FMA) return scaleAround_fma(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return scaleAround_mulAdd(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
    }

    public static float[] scaleAround_fma(float[] dest, int destOffset, float[] src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, sX).mul(_sv0);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, sY).mul(_sv1);
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, sZ).mul(_sv2);
        var _c3 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, pivotX * (1.0f - sX)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, pivotY * (1.0f - sY)), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, pivotZ * (1.0f - sZ)), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] scaleAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, sX).mul(_sv0);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, sY).mul(_sv1);
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, sZ).mul(_sv2);
        var _c3 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, pivotX * (1.0f - sX))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, pivotY * (1.0f - sY))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, pivotZ * (1.0f - sZ))).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
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
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, _sx).mul(_sv0);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, _sy).mul(_sv1);
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, _sz).mul(_sv2);
        var _c3 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, pivot[pivotOffset + 0] * (1.0f - _sx)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, pivot[pivotOffset + 1] * (1.0f - _sy)), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, pivot[pivotOffset + 2] * (1.0f - _sz)), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] scaleAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] s, int sOffset, float[] pivot, int pivotOffset) {
        float _sx = s[sOffset + 0];
        float _sy = s[sOffset + 1];
        float _sz = s[sOffset + 2];
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, _sx).mul(_sv0);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, _sy).mul(_sv1);
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, _sz).mul(_sv2);
        var _c3 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, pivot[pivotOffset + 0] * (1.0f - _sx))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, pivot[pivotOffset + 1] * (1.0f - _sy))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, pivot[pivotOffset + 2] * (1.0f - _sz))).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] shadow(float[] dest, int destOffset, float[] src, int srcOffset, float lightX, float lightY, float lightZ, float lightW, float planeX, float planeY, float planeZ, float planeW) {
        if (SimdSupport.USE_FMA) return shadow_fma(dest, destOffset, src, srcOffset, lightX, lightY, lightZ, lightW, planeX, planeY, planeZ, planeW);
        return shadow_mulAdd(dest, destOffset, src, srcOffset, lightX, lightY, lightZ, lightW, planeX, planeY, planeZ, planeW);
    }

    public static float[] shadow_fma(float[] dest, int destOffset, float[] src, int srcOffset, float lightX, float lightY, float lightZ, float lightW, float planeX, float planeY, float planeZ, float planeW) {
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _t14 = lightZ * planeZ;
        float _t28 = Math.fma(lightX, planeX, lightY * planeY);
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, -_self03).withLane(1, -_self13).withLane(2, -_self23).withLane(3, -_self33);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = FloatVector.zero(SIMD_SPECIES).withLane(0, -_self01).withLane(1, -_self11).withLane(2, -_self21).withLane(3, -_self31);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, lightW * planeX), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, lightZ * planeX), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(lightW, planeW, Math.fma(lightY, planeY, _t14))), _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, -(lightY * planeX))))));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, lightW * planeY), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, lightZ * planeY), _sv3.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(lightW, planeW, Math.fma(lightX, planeX, _t14))), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -(lightX * planeY))))));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, lightW * planeZ), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(lightW, planeW, _t28)), _sv4.fma(FloatVector.broadcast(SIMD_SPECIES, lightY * planeZ), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -(lightX * planeZ))))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(lightZ, planeZ, _t28)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, lightZ * planeW), _sv4.fma(FloatVector.broadcast(SIMD_SPECIES, lightY * planeW), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -(lightX * planeW))))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] shadow_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float lightX, float lightY, float lightZ, float lightW, float planeX, float planeY, float planeZ, float planeW) {
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _t14 = lightZ * planeZ;
        float _t28 = Math.fma(lightX, planeX, lightY * planeY);
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, -_self03).withLane(1, -_self13).withLane(2, -_self23).withLane(3, -_self33);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = FloatVector.zero(SIMD_SPECIES).withLane(0, -_self01).withLane(1, -_self11).withLane(2, -_self21).withLane(3, -_self31);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, lightW * planeX)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, lightZ * planeX)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(lightW, planeW, Math.fma(lightY, planeY, _t14)))).add(_sv3.mul(FloatVector.broadcast(SIMD_SPECIES, -(lightY * planeX))))));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, lightW * planeY)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, lightZ * planeY)).add(_sv3.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(lightW, planeW, Math.fma(lightX, planeX, _t14)))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -(lightX * planeY))))));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, lightW * planeZ)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(lightW, planeW, _t28))).add(_sv4.mul(FloatVector.broadcast(SIMD_SPECIES, lightY * planeZ)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -(lightX * planeZ))))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(lightZ, planeZ, _t28))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, lightZ * planeW)).add(_sv4.mul(FloatVector.broadcast(SIMD_SPECIES, lightY * planeW)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -(lightX * planeW))))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] shadow(float[] dest, int destOffset, float[] src, int srcOffset, float[] light, int lightOffset, float[] plane, int planeOffset) {
        if (SimdSupport.USE_FMA) return shadow_fma(dest, destOffset, src, srcOffset, light, lightOffset, plane, planeOffset);
        return shadow_mulAdd(dest, destOffset, src, srcOffset, light, lightOffset, plane, planeOffset);
    }

    public static float[] shadow_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] light, int lightOffset, float[] plane, int planeOffset) {
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _lightw = light[lightOffset + 3];
        float _planex = plane[planeOffset + 0];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _lightz = light[lightOffset + 2];
        float _planew = plane[planeOffset + 3];
        float _lighty = light[lightOffset + 1];
        float _planey = plane[planeOffset + 1];
        float _lightx = light[lightOffset + 0];
        float _planez = plane[planeOffset + 2];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _t14 = _lightz * _planez;
        float _t28 = Math.fma(_lightx, _planex, _lighty * _planey);
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, -_self03).withLane(1, -_self13).withLane(2, -_self23).withLane(3, -_self33);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = FloatVector.zero(SIMD_SPECIES).withLane(0, -_self01).withLane(1, -_self11).withLane(2, -_self21).withLane(3, -_self31);
        var _c0 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _lightw * _planex), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _lightz * _planex), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_lightw, _planew, Math.fma(_lighty, _planey, _t14))), _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, -(_lighty * _planex))))));
        var _c1 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _lightw * _planey), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _lightz * _planey), _sv3.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_lightw, _planew, Math.fma(_lightx, _planex, _t14))), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -(_lightx * _planey))))));
        var _c2 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _lightw * _planez), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_lightw, _planew, _t28)), _sv4.fma(FloatVector.broadcast(SIMD_SPECIES, _lighty * _planez), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -(_lightx * _planez))))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_lightz, _planez, _t28)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _lightz * _planew), _sv4.fma(FloatVector.broadcast(SIMD_SPECIES, _lighty * _planew), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -(_lightx * _planew))))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] shadow_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] light, int lightOffset, float[] plane, int planeOffset) {
        float _self03 = src[srcOffset + 12];
        float _self13 = src[srcOffset + 13];
        float _self23 = src[srcOffset + 14];
        float _self33 = src[srcOffset + 15];
        float _lightw = light[lightOffset + 3];
        float _planex = plane[planeOffset + 0];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _lightz = light[lightOffset + 2];
        float _planew = plane[planeOffset + 3];
        float _lighty = light[lightOffset + 1];
        float _planey = plane[planeOffset + 1];
        float _lightx = light[lightOffset + 0];
        float _planez = plane[planeOffset + 2];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _t14 = _lightz * _planez;
        float _t28 = Math.fma(_lightx, _planex, _lighty * _planey);
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, -_self03).withLane(1, -_self13).withLane(2, -_self23).withLane(3, -_self33);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = FloatVector.zero(SIMD_SPECIES).withLane(0, -_self01).withLane(1, -_self11).withLane(2, -_self21).withLane(3, -_self31);
        var _c0 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _lightw * _planex)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _lightz * _planex)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_lightw, _planew, Math.fma(_lighty, _planey, _t14)))).add(_sv3.mul(FloatVector.broadcast(SIMD_SPECIES, -(_lighty * _planex))))));
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _lightw * _planey)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _lightz * _planey)).add(_sv3.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_lightw, _planew, Math.fma(_lightx, _planex, _t14)))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -(_lightx * _planey))))));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _lightw * _planez)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_lightw, _planew, _t28))).add(_sv4.mul(FloatVector.broadcast(SIMD_SPECIES, _lighty * _planez)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -(_lightx * _planez))))));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_lightz, _planez, _t28))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _lightz * _planew)).add(_sv4.mul(FloatVector.broadcast(SIMD_SPECIES, _lighty * _planew)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -(_lightx * _planew))))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] shear(float[] dest, int destOffset, float[] src, int srcOffset, float xy, float xz, float yx, float yz, float zx, float zy) {
        if (SimdSupport.USE_FMA) return shear_fma(dest, destOffset, src, srcOffset, xy, xz, yx, yz, zx, zy);
        return shear_mulAdd(dest, destOffset, src, srcOffset, xy, xz, yx, yz, zx, zy);
    }

    public static float[] shear_fma(float[] dest, int destOffset, float[] src, int srcOffset, float xy, float xz, float yx, float yz, float zx, float zy) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, yx).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, zx).fma(_sv1, _sv2));
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, xy).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, zy).fma(_sv1, _sv0));
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, xz).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, yz).fma(_sv0, _sv1));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] shear_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float xy, float xz, float yx, float yz, float zx, float zy) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, yx).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, zx).mul(_sv1).add(_sv2));
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, xy).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, zy).mul(_sv1).add(_sv0));
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, xz).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, yz).mul(_sv0).add(_sv1));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] tile(float[] dest, int destOffset, float[] src, int srcOffset, float x, float y, float w, float h) {
        if (SimdSupport.USE_FMA) return tile_fma(dest, destOffset, src, srcOffset, x, y, w, h);
        return tile_mulAdd(dest, destOffset, src, srcOffset, x, y, w, h);
    }

    public static float[] tile_fma(float[] dest, int destOffset, float[] src, int srcOffset, float x, float y, float w, float h) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, w).mul(_sv0);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, h).mul(_sv1);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(-2.0f, x, w - 1.0f)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(-2.0f, y, h - 1.0f)), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] tile_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float x, float y, float w, float h) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, w).mul(_sv0);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, h).mul(_sv1);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(-2.0f, x, w - 1.0f))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(-2.0f, y, h - 1.0f))).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12)));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] translate(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        if (SimdSupport.USE_FMA) return translate_fma(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return translate_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    public static float[] translate_fma(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0;
        var _c1 = _sv1;
        var _c2 = _sv2;
        var _c3 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, vX), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, vY), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, vZ), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] translate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0;
        var _c1 = _sv1;
        var _c2 = _sv2;
        var _c3 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, vX)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, vY)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, vZ)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] translate(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        if (SimdSupport.USE_FMA) return translate_fma(dest, destOffset, src, srcOffset, v, vOffset);
        return translate_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
    }

    public static float[] translate_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0;
        var _c1 = _sv1;
        var _c2 = _sv2;
        var _c3 = _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, v[vOffset + 0]), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, v[vOffset + 1]), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, v[vOffset + 2]), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] translate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c0 = _sv0;
        var _c1 = _sv1;
        var _c2 = _sv2;
        var _c3 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, v[vOffset + 0])).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, v[vOffset + 1])).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, v[vOffset + 2])).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12))));
        _c0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mulVec4(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ, float vW) {
        if (SimdSupport.USE_FMA) return mulVec4_fma(dest, destOffset, src, srcOffset, vX, vY, vZ, vW);
        return mulVec4_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ, vW);
    }

    public static float[] mulVec4_fma(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ, float vW) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).fma(FloatVector.broadcast(SIMD_SPECIES, vW), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).fma(FloatVector.broadcast(SIMD_SPECIES, vZ), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(FloatVector.broadcast(SIMD_SPECIES, vX), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(SIMD_SPECIES, vY)))));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] mulVec4_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ, float vW) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).mul(FloatVector.broadcast(SIMD_SPECIES, vW)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).mul(FloatVector.broadcast(SIMD_SPECIES, vZ)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, vX)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(SIMD_SPECIES, vY)))));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] mulVec4(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        if (SimdSupport.USE_FMA) return mulVec4_fma(dest, destOffset, src, srcOffset, v, vOffset);
        return mulVec4_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
    }

    public static float[] mulVec4_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).fma(FloatVector.broadcast(SIMD_SPECIES, v[vOffset + 3]), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).fma(FloatVector.broadcast(SIMD_SPECIES, v[vOffset + 2]), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(FloatVector.broadcast(SIMD_SPECIES, v[vOffset + 0]), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(SIMD_SPECIES, v[vOffset + 1])))));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] mulVec4_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).mul(FloatVector.broadcast(SIMD_SPECIES, v[vOffset + 3])).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).mul(FloatVector.broadcast(SIMD_SPECIES, v[vOffset + 2])).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, v[vOffset + 0])).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(SIMD_SPECIES, v[vOffset + 1])))));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] transformPosition_vecArr(float[] dest, int destOffset, float[] matrix, int matrixOffset, float[] points, int pointsOffset, int count) {
        float _m00 = matrix[matrixOffset + 0];
        float _m01 = matrix[matrixOffset + 4];
        float _m02 = matrix[matrixOffset + 8];
        float _m03 = matrix[matrixOffset + 12];
        float _m10 = matrix[matrixOffset + 1];
        float _m11 = matrix[matrixOffset + 5];
        float _m12 = matrix[matrixOffset + 9];
        float _m13 = matrix[matrixOffset + 13];
        float _m20 = matrix[matrixOffset + 2];
        float _m21 = matrix[matrixOffset + 6];
        float _m22 = matrix[matrixOffset + 10];
        float _m23 = matrix[matrixOffset + 14];
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
        float _m01 = matrix[matrixOffset + 4];
        float _m02 = matrix[matrixOffset + 8];
        float _m10 = matrix[matrixOffset + 1];
        float _m11 = matrix[matrixOffset + 5];
        float _m12 = matrix[matrixOffset + 9];
        float _m20 = matrix[matrixOffset + 2];
        float _m21 = matrix[matrixOffset + 6];
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

    public static float[] transformProject_vecArr(float[] dest, int destOffset, float[] matrix, int matrixOffset, float[] points, int pointsOffset, int count) {
        float _m00 = matrix[matrixOffset + 0];
        float _m01 = matrix[matrixOffset + 4];
        float _m02 = matrix[matrixOffset + 8];
        float _m03 = matrix[matrixOffset + 12];
        float _m10 = matrix[matrixOffset + 1];
        float _m11 = matrix[matrixOffset + 5];
        float _m12 = matrix[matrixOffset + 9];
        float _m13 = matrix[matrixOffset + 13];
        float _m20 = matrix[matrixOffset + 2];
        float _m21 = matrix[matrixOffset + 6];
        float _m22 = matrix[matrixOffset + 10];
        float _m23 = matrix[matrixOffset + 14];
        float _m30 = matrix[matrixOffset + 3];
        float _m31 = matrix[matrixOffset + 7];
        float _m32 = matrix[matrixOffset + 11];
        float _m33 = matrix[matrixOffset + 15];
        var _sp = FloatVector.SPECIES_128;
        var _c0 = FloatVector.zero(_sp).withLane(0, _m00).withLane(1, _m10).withLane(2, _m20).withLane(3, _m30);
        var _c1 = FloatVector.zero(_sp).withLane(0, _m01).withLane(1, _m11).withLane(2, _m21).withLane(3, _m31);
        var _c2 = FloatVector.zero(_sp).withLane(0, _m02).withLane(1, _m12).withLane(2, _m22).withLane(3, _m32);
        var _c3 = FloatVector.zero(_sp).withLane(0, _m03).withLane(1, _m13).withLane(2, _m23).withLane(3, _m33);
        float px = points[pointsOffset + 0], py = points[pointsOffset + 1], pz = points[pointsOffset + 2];
        int _i = 0;
        for (; _i < count - 1; _i++) {
            var _v = FloatVector.broadcast(_sp, px).fma(_c0, FloatVector.broadcast(_sp, py).fma(_c1, FloatVector.broadcast(_sp, pz).fma(_c2, _c3)));
            float _inv = 1.0f / _v.lane(3);
            _v = _v.mul(FloatVector.broadcast(_sp, _inv));
            int _pn = pointsOffset + (_i + 1) * 3;
            px = points[_pn + 0]; py = points[_pn + 1]; pz = points[_pn + 2];
            _v.intoArray(dest, destOffset + _i * 3);
        }
        int _do = destOffset + _i * 3;
        float _w = Math.fma(_m30, px, Math.fma(_m31, py, Math.fma(_m32, pz, _m33)));
        float _inv = 1.0f / _w;
        dest[_do + 0] = (Math.fma(_m00, px, Math.fma(_m01, py, Math.fma(_m02, pz, _m03)))) * _inv;
        dest[_do + 1] = (Math.fma(_m10, px, Math.fma(_m11, py, Math.fma(_m12, pz, _m13)))) * _inv;
        dest[_do + 2] = (Math.fma(_m20, px, Math.fma(_m21, py, Math.fma(_m22, pz, _m23)))) * _inv;
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
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, src, srcOffset).intoArray(dest, destOffset);
        }
        else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, src, srcOffset).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, src, srcOffset + 8).intoArray(dest, destOffset + 8);
        }
        else {
            FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset + 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset + 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset + 12).intoArray(dest, destOffset + 12);
        }
    }


    public static float[] copy(float[] dest, int destOffset, float[] src, int srcOffset) {
        copyArrArr_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        copyArrArr(dest, destOffset, src, srcOffset, count * 16);
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 16) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr_one(dest, destOffset, _srcArr, _srcOff);
        } else {
            for (int _i = 0; _i < 16; _i++)
                dest[destOffset + _i] = src.get(srcOffset + _i);
        }
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (src.hasArray() && srcOffset >= 0 && (count > 134217727 ? -1 : count * 16) >= 0 && srcOffset <= src.limit() - (count > 134217727 ? -1 : count * 16)) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr(dest, destOffset, _srcArr, _srcOff, count * 16);
        } else {
            for (int _i = 0; _i < count * 16; _i++)
                dest[destOffset + _i] = src.get(srcOffset + _i);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, float[] src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 16) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr_one(_destArr, _destOff, src, srcOffset);
        } else {
            for (int _i = 0; _i < 16; _i++)
                dest.put(destOffset + _i, src[srcOffset + _i]);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 134217727 ? -1 : count * 16) >= 0 && destOffset <= dest.limit() - (count > 134217727 ? -1 : count * 16)) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr(_destArr, _destOff, src, srcOffset, count * 16);
        } else {
            for (int _i = 0; _i < count * 16; _i++)
                dest.put(destOffset + _i, src[srcOffset + _i]);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 16) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 16) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr_one(_destArr, _destOff, _srcArr, _srcOff);
            } else {
                for (int _i = 0; _i < 16; _i++)
                    _destArr[_destOff + _i] = src.get(srcOffset + _i);
            }
        } else {
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 16) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                for (int _i = 0; _i < 16; _i++)
                    dest.put(destOffset + _i, _srcArr[_srcOff + _i]);
            } else {
                for (int _i = 0; _i < 16; _i++)
                    dest.put(destOffset + _i, src.get(srcOffset + _i));
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 134217727 ? -1 : count * 16) >= 0 && destOffset <= dest.limit() - (count > 134217727 ? -1 : count * 16)) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && (count > 134217727 ? -1 : count * 16) >= 0 && srcOffset <= src.limit() - (count > 134217727 ? -1 : count * 16)) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr(_destArr, _destOff, _srcArr, _srcOff, count * 16);
            } else {
                for (int _i = 0; _i < count * 16; _i++)
                    _destArr[_destOff + _i] = src.get(srcOffset + _i);
            }
        } else {
            if (src.hasArray() && srcOffset >= 0 && (count > 134217727 ? -1 : count * 16) >= 0 && srcOffset <= src.limit() - (count > 134217727 ? -1 : count * 16)) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                for (int _i = 0; _i < count * 16; _i++)
                    dest.put(destOffset + _i, _srcArr[_srcOff + _i]);
            } else {
                for (int _i = 0; _i < count * 16; _i++)
                    dest.put(destOffset + _i, src.get(srcOffset + _i));
            }
        }
        return dest;
    }
}
