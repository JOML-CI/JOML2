// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.opssimd;

import jdk.incubator.vector.*;
import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.simd.*;

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
    private static final float[] DATA_0 = new float[] {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_1 = new float[] {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_2 = new float[] {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_3 = new float[] {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_4 = new float[] {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_5 = new float[] {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_6 = new float[] {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_7 = new float[] {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_8 = new float[] {0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_9 = new float[] {0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_10 = new float[] {0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_11 = new float[] {0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_12 = new float[] {0.0f, 1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_13 = new float[] {0.0f, 1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_14 = new float[] {0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_15 = new float[] {0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_16 = new float[] {0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_17 = new float[] {0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_18 = new float[] {0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_19 = new float[] {0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_20 = new float[] {0.0f, 0.0f, 1.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_21 = new float[] {0.0f, 0.0f, 1.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_22 = new float[] {0.0f, 0.0f, 1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_23 = new float[] {0.0f, 0.0f, 1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_24 = new float[] {-1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_25 = new float[] {-1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_26 = new float[] {-1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_27 = new float[] {-1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_28 = new float[] {-1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_29 = new float[] {-1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_30 = new float[] {-1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_31 = new float[] {-1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_32 = new float[] {0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_33 = new float[] {0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_34 = new float[] {0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_35 = new float[] {0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_36 = new float[] {0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_37 = new float[] {0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_38 = new float[] {0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_39 = new float[] {0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_40 = new float[] {0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_41 = new float[] {0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_42 = new float[] {0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_43 = new float[] {0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_44 = new float[] {0.0f, 0.0f, -1.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_45 = new float[] {0.0f, 0.0f, -1.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_46 = new float[] {0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};
    private static final float[] DATA_47 = new float[] {0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, -1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f};

    public static float[] add(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        for (int _li = 0; _li < 4; _li++) {
            FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + _li * 4).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] mul(float[] dest, int destOffset, float[] src, int srcOffset, float scalar) {
        for (int _li = 0; _li < 4; _li++) {
            FloatVector.broadcast(SIMD_SPECIES, scalar).mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] negate(float[] dest, int destOffset, float[] src, int srcOffset) {
        for (int _li = 0; _li < 4; _li++) {
            FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4).neg().intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] sub(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        for (int _li = 0; _li < 4; _li++) {
            FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4).sub(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + _li * 4)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] set(float[] dest, int destOffset, float[] v, int vOffset) {
        for (int _li = 0; _li < 4; _li++) {
            FloatVector.fromArray(SIMD_SPECIES, v, vOffset + _li * 4).intoArray(dest, destOffset + _li * 4);
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
        float _tx = t[tOffset];
        float _ty = t[tOffset + 1];
        float _tz = t[tOffset + 2];
        float _self33 = src[srcOffset + 15];
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

    public static float[] makeIdentity(float[] dest, int destOffset) {
        float[] _DATA_0 = DATA_0;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_0, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_0, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_0, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_0, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_0, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_0, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_0, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] lerp(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static float[] lerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        for (int _li = 0; _li < 4; _li++) {
            int _lo1 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, t).fma(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + _li * 4).sub(FloatVector.fromArray(SIMD_SPECIES, src, _lo1)), FloatVector.fromArray(SIMD_SPECIES, src, _lo1)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] lerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        for (int _li = 0; _li < 4; _li++) {
            int _lo1 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, t).mul(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + _li * 4).sub(FloatVector.fromArray(SIMD_SPECIES, src, _lo1))).add(FloatVector.fromArray(SIMD_SPECIES, src, _lo1)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] mul(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        if (SimdSupport.USE_FMA) return mul_fma(dest, destOffset, src, srcOffset, right, rightOffset);
        return mul_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static float[] mul_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = rightOffset + _li * 4;
            FloatVector.broadcast(_SIMD_SPECIES, right[_lo0 + 3]).fma(_sv0, FloatVector.broadcast(_SIMD_SPECIES, right[_lo0 + 2]).fma(_sv1, FloatVector.broadcast(_SIMD_SPECIES, right[_lo0]).fma(_sv2, FloatVector.broadcast(_SIMD_SPECIES, right[_lo0 + 1]).mul(_sv3)))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] mul_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = rightOffset + _li * 4;
            FloatVector.broadcast(_SIMD_SPECIES, right[_lo0 + 3]).mul(_sv0).add(FloatVector.broadcast(_SIMD_SPECIES, right[_lo0 + 2]).mul(_sv1).add(FloatVector.broadcast(_SIMD_SPECIES, right[_lo0]).mul(_sv2).add(FloatVector.broadcast(_SIMD_SPECIES, right[_lo0 + 1]).mul(_sv3)))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] mulMat2x2(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        if (SimdSupport.USE_FMA) return mulMat2x2_fma(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat2x2_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static float[] mulMat2x2_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 2]).fma(_sv0, FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 3]).mul(_sv1));
        var _c2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset]).fma(_sv0, FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 1]).mul(_sv1)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mulMat2x2_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 2]).mul(_sv0).add(FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 3]).mul(_sv1));
        var _c2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset]).mul(_sv0).add(FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 1]).mul(_sv1)).intoArray(dest, destOffset);
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
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 2]).fma(_sv0, FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 3]).mul(_sv1));
        var _c2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 4]).fma(_sv0, FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 5]).fma(_sv1, FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset]).fma(_sv0, FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 1]).mul(_sv1)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mulMat2x3_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 2]).mul(_sv0).add(FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 3]).mul(_sv1));
        var _c2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 4]).mul(_sv0).add(FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 5]).mul(_sv1).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset]).mul(_sv0).add(FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 1]).mul(_sv1)).intoArray(dest, destOffset);
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
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 5]).fma(_sv0, FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 3]).fma(_sv1, FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 4]).mul(_sv2)));
        var _c2 = FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 8]).fma(_sv0, FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 6]).fma(_sv1, FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 7]).mul(_sv2)));
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 2]).fma(_sv0, FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset]).fma(_sv1, FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 1]).mul(_sv2))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mulMat3x3_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 5]).mul(_sv0).add(FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 3]).mul(_sv1).add(FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 4]).mul(_sv2)));
        var _c2 = FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 8]).mul(_sv0).add(FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 6]).mul(_sv1).add(FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 7]).mul(_sv2)));
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 2]).mul(_sv0).add(FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset]).mul(_sv1).add(FloatVector.broadcast(_SIMD_SPECIES, right[rightOffset + 1]).mul(_sv2))).intoArray(dest, destOffset);
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
        mulMat3x4_fma_s328875c1_v(dest, destOffset, src, srcOffset, right[rightOffset + 8], right[rightOffset], right[rightOffset + 4], right[rightOffset + 9], right[rightOffset + 1], right[rightOffset + 5], right[rightOffset + 10], right[rightOffset + 2], right[rightOffset + 6], right[rightOffset + 3], right[rightOffset + 7], right[rightOffset + 11]);
        return dest;
    }

    private static void mulMat3x4_fma_s328875c1_v(float[] dest, int destOffset, float[] src, int srcOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8, float _h9, float _h10, float _h11) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.broadcast(_SIMD_SPECIES, _h9).fma(_sv1, FloatVector.broadcast(_SIMD_SPECIES, _h10).fma(_sv2, FloatVector.broadcast(_SIMD_SPECIES, _h11).fma(_sv0, FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        FloatVector.broadcast(_SIMD_SPECIES, _h0).fma(_sv0, FloatVector.broadcast(_SIMD_SPECIES, _h1).fma(_sv1, FloatVector.broadcast(_SIMD_SPECIES, _h2).mul(_sv2))).intoArray(dest, destOffset);
        FloatVector.broadcast(_SIMD_SPECIES, _h3).fma(_sv0, FloatVector.broadcast(_SIMD_SPECIES, _h4).fma(_sv1, FloatVector.broadcast(_SIMD_SPECIES, _h5).mul(_sv2))).intoArray(dest, destOffset + 4);
        FloatVector.broadcast(_SIMD_SPECIES, _h6).fma(_sv0, FloatVector.broadcast(_SIMD_SPECIES, _h7).fma(_sv1, FloatVector.broadcast(_SIMD_SPECIES, _h8).mul(_sv2))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] mulMat3x4_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        mulMat3x4_mulAdd_s49f53474_v(dest, destOffset, src, srcOffset, right[rightOffset + 8], right[rightOffset], right[rightOffset + 4], right[rightOffset + 9], right[rightOffset + 1], right[rightOffset + 5], right[rightOffset + 10], right[rightOffset + 2], right[rightOffset + 6], right[rightOffset + 3], right[rightOffset + 7], right[rightOffset + 11]);
        return dest;
    }

    private static void mulMat3x4_mulAdd_s49f53474_v(float[] dest, int destOffset, float[] src, int srcOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8, float _h9, float _h10, float _h11) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.broadcast(_SIMD_SPECIES, _h9).mul(_sv1).add(FloatVector.broadcast(_SIMD_SPECIES, _h10).mul(_sv2).add(FloatVector.broadcast(_SIMD_SPECIES, _h11).mul(_sv0).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        FloatVector.broadcast(_SIMD_SPECIES, _h0).mul(_sv0).add(FloatVector.broadcast(_SIMD_SPECIES, _h1).mul(_sv1).add(FloatVector.broadcast(_SIMD_SPECIES, _h2).mul(_sv2))).intoArray(dest, destOffset);
        FloatVector.broadcast(_SIMD_SPECIES, _h3).mul(_sv0).add(FloatVector.broadcast(_SIMD_SPECIES, _h4).mul(_sv1).add(FloatVector.broadcast(_SIMD_SPECIES, _h5).mul(_sv2))).intoArray(dest, destOffset + 4);
        FloatVector.broadcast(_SIMD_SPECIES, _h6).mul(_sv0).add(FloatVector.broadcast(_SIMD_SPECIES, _h7).mul(_sv1).add(FloatVector.broadcast(_SIMD_SPECIES, _h8).mul(_sv2))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] preMul(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        if (SimdSupport.USE_FMA) return preMul_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMul_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static float[] preMul_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, other, otherOffset + 12);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, other, otherOffset + 8);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, other, otherOffset);
        var _sv3 = FloatVector.fromArray(_SIMD_SPECIES, other, otherOffset + 4);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 3]), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]), _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0]), _sv3.mul(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]))))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preMul_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, other, otherOffset + 12);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, other, otherOffset + 8);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, other, otherOffset);
        var _sv3 = FloatVector.fromArray(_SIMD_SPECIES, other, otherOffset + 4);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 3])).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2])).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0])).add(_sv3.mul(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]))))).intoArray(dest, destOffset + _li * 4);
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
        float _other00 = other[otherOffset];
        float _other10 = other[otherOffset + 1];
        float _other20 = other[otherOffset + 2];
        float _other01 = other[otherOffset + 3];
        float _other11 = other[otherOffset + 4];
        float _other21 = other[otherOffset + 5];
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _other02).withLane(1, _other12).withLane(2, _other22);
        var _sv1 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _other00).withLane(1, _other10).withLane(2, _other20);
        var _sv2 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _other01).withLane(1, _other11).withLane(2, _other21);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0]), _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]), FloatVector.broadcast(_SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preMulMat3x3_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        float _other02 = other[otherOffset + 6];
        float _other12 = other[otherOffset + 7];
        float _other22 = other[otherOffset + 8];
        float _other00 = other[otherOffset];
        float _other10 = other[otherOffset + 1];
        float _other20 = other[otherOffset + 2];
        float _other01 = other[otherOffset + 3];
        float _other11 = other[otherOffset + 4];
        float _other21 = other[otherOffset + 5];
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _other02).withLane(1, _other12).withLane(2, _other22);
        var _sv1 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _other00).withLane(1, _other10).withLane(2, _other20);
        var _sv2 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _other01).withLane(1, _other11).withLane(2, _other21);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2])).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0])).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1])).add(FloatVector.broadcast(_SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preMulMat3x4(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        if (SimdSupport.USE_FMA) return preMulMat3x4_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat3x4_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static float[] preMulMat3x4_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        preMulMat3x4_fma_s38ab7552_v(dest, destOffset, src, srcOffset, other[otherOffset + 3], other[otherOffset + 7], other[otherOffset + 11], other[otherOffset + 2], other[otherOffset + 6], other[otherOffset + 10], other[otherOffset], other[otherOffset + 4], other[otherOffset + 8], other[otherOffset + 1], other[otherOffset + 5], other[otherOffset + 9]);
        return dest;
    }

    private static void preMulMat3x4_fma_s38ab7552_v(float[] dest, int destOffset, float[] src, int srcOffset, float _other03, float _other13, float _other23, float _other02, float _other12, float _other22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _other03).withLane(1, _other13).withLane(2, _other23);
        var _sv1 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _other02).withLane(1, _other12).withLane(2, _other22);
        var _sv2 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _other00).withLane(1, _other10).withLane(2, _other20);
        var _sv3 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _other01).withLane(1, _other11).withLane(2, _other21);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 3]), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]), _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0]), _sv3.fma(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]), FloatVector.broadcast(_SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3]))))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] preMulMat3x4_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        preMulMat3x4_mulAdd_s68bf9a65_v(dest, destOffset, src, srcOffset, other[otherOffset + 3], other[otherOffset + 7], other[otherOffset + 11], other[otherOffset + 2], other[otherOffset + 6], other[otherOffset + 10], other[otherOffset], other[otherOffset + 4], other[otherOffset + 8], other[otherOffset + 1], other[otherOffset + 5], other[otherOffset + 9]);
        return dest;
    }

    private static void preMulMat3x4_mulAdd_s68bf9a65_v(float[] dest, int destOffset, float[] src, int srcOffset, float _other03, float _other13, float _other23, float _other02, float _other12, float _other22, float _other00, float _other10, float _other20, float _other01, float _other11, float _other21) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _other03).withLane(1, _other13).withLane(2, _other23);
        var _sv1 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _other02).withLane(1, _other12).withLane(2, _other22);
        var _sv2 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _other00).withLane(1, _other10).withLane(2, _other20);
        var _sv3 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _other01).withLane(1, _other11).withLane(2, _other21);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 3])).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2])).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0])).add(_sv3.mul(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1])).add(FloatVector.broadcast(_SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3]))))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] addScaled(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float weight) {
        if (SimdSupport.USE_FMA) return addScaled_fma(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return addScaled_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    public static float[] addScaled_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float weight) {
        for (int _li = 0; _li < 4; _li++) {
            FloatVector.broadcast(SIMD_SPECIES, weight).fma(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + _li * 4), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] addScaled_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float weight) {
        for (int _li = 0; _li < 4; _li++) {
            FloatVector.broadcast(SIMD_SPECIES, weight).mul(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + _li * 4)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] makeOuterProduct(float[] dest, int destOffset, float colX, float colY, float colZ, float colW, float rowX, float rowY, float rowZ, float rowW) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, colX).withLane(1, colY).withLane(2, colZ).withLane(3, colW);
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, rowX)).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, rowY)).intoArray(dest, destOffset + 4);
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, rowZ)).intoArray(dest, destOffset + 8);
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, rowW)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] makeOuterProduct(float[] dest, int destOffset, float[] col, int colOffset, float[] row, int rowOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, col, colOffset);
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, row[rowOffset + 1]));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, row[rowOffset + 2]));
        var _c3 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, row[rowOffset + 3]));
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, row[rowOffset])).intoArray(dest, destOffset);
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
        float _t0 = Math.sinWithCos(angleY);
        float _t1 = Math.cosWithSin(angleX);
        float _t2 = Math.cosWithSin(angleY);
        float _t3 = Math.sinWithCos(angleX);
        arcball_fma_s76935e4c_v(dest, destOffset, src, srcOffset, radius, centerX, centerZ, _t0, _t1, _t2, _t3, -centerZ, -centerY, _t0 * _t1, _t3 * _t0, _t1 * _t2, _t3 * _t2);
        return dest;
    }

    private static void arcball_fma_s76935e4c_v(float[] dest, int destOffset, float[] src, int srcOffset, float radius, float centerX, float centerZ, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t7, float _t8, float _t9) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t4, _t0, -(centerX * _t2))), _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(centerZ, _t9, java.lang.Math.fma(_t5, _t1, -(centerX * _t7)))), _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(centerX, _t6, java.lang.Math.fma(_t5, _t3, java.lang.Math.fma(_t4, _t8, -radius)))), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, -_t6), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _t2), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t7)))).intoArray(dest, destOffset);
        _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, _t1), _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t3))).intoArray(dest, destOffset + 4);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t8), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _t0), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -_t9)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] arcball_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float radius, float centerX, float centerY, float centerZ, float angleX, float angleY) {
        float _t0 = Math.sinWithCos(angleY);
        float _t1 = Math.cosWithSin(angleX);
        float _t2 = Math.cosWithSin(angleY);
        float _t3 = Math.sinWithCos(angleX);
        arcball_mulAdd_s5c111b23_v(dest, destOffset, src, srcOffset, radius, centerX, centerZ, _t0, _t1, _t2, _t3, -centerZ, -centerY, _t0 * _t1, _t3 * _t0, _t1 * _t2, _t3 * _t2);
        return dest;
    }

    private static void arcball_mulAdd_s5c111b23_v(float[] dest, int destOffset, float[] src, int srcOffset, float radius, float centerX, float centerZ, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t7, float _t8, float _t9) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t4) * (_t0) - (centerX * _t2)))).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((centerZ) * (_t9) + (((_t5) * (_t1) - (centerX * _t7)))))).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((centerX) * (_t6) + (((_t5) * (_t3) + (((_t4) * (_t8) - (radius)))))))).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, -_t6)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t2)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t7)))).intoArray(dest, destOffset);
        _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t1)).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t3))).intoArray(dest, destOffset + 4);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t8)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t0)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -_t9)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] arcball(float[] dest, int destOffset, float[] src, int srcOffset, float[] center, int centerOffset, float radius, float angleX, float angleY) {
        if (SimdSupport.USE_FMA) return arcball_fma(dest, destOffset, src, srcOffset, center, centerOffset, radius, angleX, angleY);
        return arcball_mulAdd(dest, destOffset, src, srcOffset, center, centerOffset, radius, angleX, angleY);
    }

    public static float[] arcball_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] center, int centerOffset, float radius, float angleX, float angleY) {
        float _centerz = center[centerOffset + 2];
        float _t0 = Math.sinWithCos(angleY);
        float _t1 = Math.cosWithSin(angleX);
        float _t2 = Math.cosWithSin(angleY);
        float _t3 = Math.sinWithCos(angleX);
        arcball_fma_s76935e4c_v(dest, destOffset, src, srcOffset, radius, center[centerOffset], _centerz, _t0, _t1, _t2, _t3, -_centerz, -center[centerOffset + 1], _t0 * _t1, _t3 * _t0, _t1 * _t2, _t3 * _t2);
        return dest;
    }

    public static float[] arcball_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] center, int centerOffset, float radius, float angleX, float angleY) {
        float _centerz = center[centerOffset + 2];
        float _t0 = Math.sinWithCos(angleY);
        float _t1 = Math.cosWithSin(angleX);
        float _t2 = Math.cosWithSin(angleY);
        float _t3 = Math.sinWithCos(angleX);
        arcball_mulAdd_s5c111b23_v(dest, destOffset, src, srcOffset, radius, center[centerOffset], _centerz, _t0, _t1, _t2, _t3, -_centerz, -center[centerOffset + 1], _t0 * _t1, _t3 * _t0, _t1 * _t2, _t3 * _t2);
        return dest;
    }

    public static float[] axonometricDimetric(float[] dest, int destOffset, float[] src, int srcOffset, float alpha) {
        if (SimdSupport.USE_FMA) return axonometricDimetric_fma(dest, destOffset, src, srcOffset, alpha);
        return axonometricDimetric_mulAdd(dest, destOffset, src, srcOffset, alpha);
    }

    public static float[] axonometricDimetric_fma(float[] dest, int destOffset, float[] src, int srcOffset, float alpha) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _t0 = Math.cosWithSin(alpha);
        float _t1 = 1.4142135f;
        float _t2 = Math.sinWithCos(alpha);
        float _t9 = 0.5f * _t0 * _t1;
        float _t10 = 0.5f * _t2 * _t1;
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(_SIMD_SPECIES, 0.5f);
        var _sv3 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _self00 * _t1).withLane(1, _self10 * _t1).withLane(2, _self20 * _t1).withLane(3, _self30 * _t1);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, -_t9), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _t10), _sv2.mul(_sv3))).intoArray(dest, destOffset);
        _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _t0), _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t2))).intoArray(dest, destOffset + 4);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t9), _sv2.fma(_sv3, _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, -_t10)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] axonometricDimetric_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float alpha) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self30 = src[srcOffset + 3];
        float _t0 = Math.cosWithSin(alpha);
        float _t1 = 1.4142135f;
        float _t2 = Math.sinWithCos(alpha);
        float _t9 = 0.5f * _t0 * _t1;
        float _t10 = 0.5f * _t2 * _t1;
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(_SIMD_SPECIES, 0.5f);
        var _sv3 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _self00 * _t1).withLane(1, _self10 * _t1).withLane(2, _self20 * _t1).withLane(3, _self30 * _t1);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, -_t9)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t10)).add(_sv2.mul(_sv3))).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t0)).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t2))).intoArray(dest, destOffset + 4);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t9)).add(_sv2.mul(_sv3).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, -_t10)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] axonometricIsometric(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return axonometricIsometric_fma(dest, destOffset, src, srcOffset);
        return axonometricIsometric_mulAdd(dest, destOffset, src, srcOffset);
    }

    /** {@code axonometricIsometric} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] axonometricIsometric_fma(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self00 = src[srcOffset];
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
        axonometricIsometric_fma_s13d7cdc2_1(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32);
        axonometricIsometric_fma_s13d7cdc2_2(dest, destOffset, _self20, _self30, _self21, _self31, _self22, _self32);
        _vcp0.intoArray(dest, destOffset + 12);
        return dest;
    }

    /** Part 1 of {@code axonometricIsometric_fma}, split to fit the inline budget; reached only through it. */
    private static void axonometricIsometric_fma_s13d7cdc2_1(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32) {
        float _t0 = 1.7320508f;
        float _t1 = 1.4142135f;
        float _t2 = 2.4494898f;
        float _sp0 = 0.16666667f * _t2;
        float _t3 = _self02 * _t0;
        float _t4 = _self00 * _t1;
        float _t6 = _self12 * _t0;
        float _t7 = _self10 * _t1;
        float _t9 = _self22 * _t0;
        float _t12 = _self32 * _t0;
        float _t15 = _sp0 * _self01;
        float _t16 = _sp0 * _self11;
        dest[destOffset] = java.lang.Math.fma(-0.33333334f, _t3, java.lang.Math.fma(0.5f, _t4, _t15));
        dest[destOffset + 1] = java.lang.Math.fma(-0.33333334f, _t6, java.lang.Math.fma(0.5f, _t7, _t16));
        dest[destOffset + 2] = java.lang.Math.fma(-0.33333334f, _t9, java.lang.Math.fma(0.5f, (_self20 * _t1), (_sp0 * _self21)));
        dest[destOffset + 3] = java.lang.Math.fma(-0.33333334f, _t12, java.lang.Math.fma(0.5f, (_self30 * _t1), (_sp0 * _self31)));
        dest[destOffset + 4] = 0.33333334f * java.lang.Math.fma(_self01, _t2, _t3);
        dest[destOffset + 5] = 0.33333334f * java.lang.Math.fma(_self11, _t2, _t6);
        dest[destOffset + 6] = 0.33333334f * java.lang.Math.fma(_self21, _t2, _t9);
        dest[destOffset + 7] = 0.33333334f * java.lang.Math.fma(_self31, _t2, _t12);
        dest[destOffset + 8] = java.lang.Math.fma(0.33333334f, _t3, java.lang.Math.fma(0.5f, _t4, -_t15));
        dest[destOffset + 9] = java.lang.Math.fma(0.33333334f, _t6, java.lang.Math.fma(0.5f, _t7, -_t16));
    }

    /** Part 2 of {@code axonometricIsometric_fma}, split to fit the inline budget; reached only through it. */
    private static void axonometricIsometric_fma_s13d7cdc2_2(float[] dest, int destOffset, float _self20, float _self30, float _self21, float _self31, float _self22, float _self32) {
        float _t0 = 1.7320508f;
        float _t1 = 1.4142135f;
        float _sp0 = 0.16666667f * (2.4494898f);
        dest[destOffset + 10] = java.lang.Math.fma(0.33333334f, (_self22 * _t0), java.lang.Math.fma(0.5f, (_self20 * _t1), -(_sp0 * _self21)));
        dest[destOffset + 11] = java.lang.Math.fma(0.33333334f, (_self32 * _t0), java.lang.Math.fma(0.5f, (_self30 * _t1), -(_sp0 * _self31)));
    }

    /** {@code axonometricIsometric} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] axonometricIsometric_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _self00 = src[srcOffset];
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
        axonometricIsometric_mulAdd_s5cb59471_1(dest, destOffset, _self00, _self10, _self01, _self11, _self02, _self12);
        axonometricIsometric_mulAdd_s5cb59471_2(dest, destOffset, _self00, _self10, _self20, _self30, _self01, _self11, _self21, _self31, _self02, _self12, _self22, _self32);
        _vcp0.intoArray(dest, destOffset + 12);
        return dest;
    }

    /** Part 1 of {@code axonometricIsometric_mulAdd}, split to fit the inline budget; reached only through it. */
    private static void axonometricIsometric_mulAdd_s5cb59471_1(float[] dest, int destOffset, float _self00, float _self10, float _self01, float _self11, float _self02, float _self12) {
        float _t0 = 1.7320508f;
        float _t1 = 1.4142135f;
        float _sp0 = 0.16666667f * (2.4494898f);
        dest[destOffset] = ((((0.5f) * ((_self00 * _t1)) + ((_sp0 * _self01)))) - (0.33333334f) * ((_self02 * _t0)));
        dest[destOffset + 1] = ((((0.5f) * ((_self10 * _t1)) + ((_sp0 * _self11)))) - (0.33333334f) * ((_self12 * _t0)));
    }

    /** Part 2 of {@code axonometricIsometric_mulAdd}, split to fit the inline budget; reached only through it. */
    private static void axonometricIsometric_mulAdd_s5cb59471_2(float[] dest, int destOffset, float _self00, float _self10, float _self20, float _self30, float _self01, float _self11, float _self21, float _self31, float _self02, float _self12, float _self22, float _self32) {
        float _t0 = 1.7320508f;
        float _t1 = 1.4142135f;
        float _t2 = 2.4494898f;
        float _sp0 = 0.16666667f * _t2;
        float _t3 = _self02 * _t0;
        float _t6 = _self12 * _t0;
        float _t9 = _self22 * _t0;
        float _t10 = _self20 * _t1;
        float _t12 = _self32 * _t0;
        float _t13 = _self30 * _t1;
        float _t17 = _sp0 * _self21;
        float _t18 = _sp0 * _self31;
        dest[destOffset + 2] = ((((0.5f) * (_t10) + (_t17))) - (0.33333334f) * (_t9));
        dest[destOffset + 3] = ((((0.5f) * (_t13) + (_t18))) - (0.33333334f) * (_t12));
        dest[destOffset + 4] = 0.33333334f * ((_self01) * (_t2) + (_t3));
        dest[destOffset + 5] = 0.33333334f * ((_self11) * (_t2) + (_t6));
        dest[destOffset + 6] = 0.33333334f * ((_self21) * (_t2) + (_t9));
        dest[destOffset + 7] = 0.33333334f * ((_self31) * (_t2) + (_t12));
        dest[destOffset + 8] = ((0.33333334f) * (_t3) + (((0.5f) * ((_self00 * _t1)) - ((_sp0 * _self01)))));
        dest[destOffset + 9] = ((0.33333334f) * (_t6) + (((0.5f) * ((_self10 * _t1)) - ((_sp0 * _self11)))));
        dest[destOffset + 10] = ((0.33333334f) * (_t9) + (((0.5f) * (_t10) - (_t17))));
        dest[destOffset + 11] = ((0.33333334f) * (_t12) + (((0.5f) * (_t13) - (_t18))));
    }

    public static float[] axonometricTrimetric(float[] dest, int destOffset, float[] src, int srcOffset, float alphaX, float alphaY) {
        if (SimdSupport.USE_FMA) return axonometricTrimetric_fma(dest, destOffset, src, srcOffset, alphaX, alphaY);
        return axonometricTrimetric_mulAdd(dest, destOffset, src, srcOffset, alphaX, alphaY);
    }

    public static float[] axonometricTrimetric_fma(float[] dest, int destOffset, float[] src, int srcOffset, float alphaX, float alphaY) {
        float _t0 = Math.sinWithCos(alphaY);
        float _t1 = Math.cosWithSin(alphaX);
        float _t2 = Math.cosWithSin(alphaY);
        float _t3 = Math.sinWithCos(alphaX);
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, -(_t0 * _t1)), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _t2), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t3 * _t0)))).intoArray(dest, destOffset);
        _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, _t1), _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t3))).intoArray(dest, destOffset + 4);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t1 * _t2), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _t0), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -(_t3 * _t2))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] axonometricTrimetric_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float alphaX, float alphaY) {
        float _t0 = Math.sinWithCos(alphaY);
        float _t1 = Math.cosWithSin(alphaX);
        float _t2 = Math.cosWithSin(alphaY);
        float _t3 = Math.sinWithCos(alphaX);
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, -(_t0 * _t1))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t2)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t3 * _t0)))).intoArray(dest, destOffset);
        _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t1)).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t3))).intoArray(dest, destOffset + 4);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t1 * _t2)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t0)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -(_t3 * _t2))))).intoArray(dest, destOffset + 8);
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
        composeTRSMul_fma_sde574f0a_v(dest, destOffset, m, mOffset, translationX, translationY, translationZ, java.lang.Math.fma(rotationX, rotationZ, _t3) * _t0, java.lang.Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t0, java.lang.Math.fma(-java.lang.Math.fma(rotationX, rotationX, rotationY * rotationY), _t0, scaleZ), java.lang.Math.fma(-java.lang.Math.fma(rotationY, rotationY, _t4), _t1, scaleX), java.lang.Math.fma(rotationX, rotationY, _t5) * _t1, java.lang.Math.fma(rotationX, rotationZ, -_t3) * _t1, java.lang.Math.fma(rotationX, rotationY, -_t5) * _t2, java.lang.Math.fma(-java.lang.Math.fma(rotationX, rotationX, _t4), _t2, scaleY), java.lang.Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t2);
        return dest;
    }

    private static void composeTRSMul_fma_sde574f0a_v(float[] dest, int destOffset, float[] m, int mOffset, float translationX, float translationY, float translationZ, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, translationX).withLane(1, translationY).withLane(2, translationZ);
        var _sv1 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv2 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _h3).withLane(1, _h4).withLane(2, _h5);
        var _sv3 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _h6).withLane(1, _h7).withLane(2, _h8);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = mOffset + _li * 4;
            FloatVector.broadcast(_SIMD_SPECIES, m[_lo0 + 3]).fma(_sv0, FloatVector.broadcast(_SIMD_SPECIES, m[_lo0 + 2]).fma(_sv1, FloatVector.broadcast(_SIMD_SPECIES, m[_lo0]).fma(_sv2, FloatVector.broadcast(_SIMD_SPECIES, m[_lo0 + 1]).fma(_sv3, FloatVector.broadcast(_SIMD_SPECIES, 0.0f).withLane(3, m[_lo0 + 3]))))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] composeTRSMul_mulAdd(float[] dest, int destOffset, float[] m, int mOffset, float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ) {
        float _t0 = scaleZ + scaleZ;
        float _t1 = scaleX + scaleX;
        float _t2 = scaleY + scaleY;
        float _t3 = rotationY * rotationW;
        float _t4 = rotationZ * rotationZ;
        float _t5 = rotationZ * rotationW;
        composeTRSMul_mulAdd_sd5c5c337_v(dest, destOffset, m, mOffset, translationX, translationY, translationZ, ((rotationX) * (rotationZ) + (_t3)) * _t0, ((rotationY) * (rotationZ) - (rotationX * rotationW)) * _t0, ((scaleZ) - ((rotationX) * (rotationX) + (rotationY * rotationY)) * (_t0)), ((scaleX) - ((rotationY) * (rotationY) + (_t4)) * (_t1)), ((rotationX) * (rotationY) + (_t5)) * _t1, ((rotationX) * (rotationZ) - (_t3)) * _t1, ((rotationX) * (rotationY) - (_t5)) * _t2, ((scaleY) - ((rotationX) * (rotationX) + (_t4)) * (_t2)), ((rotationX) * (rotationW) + (rotationY * rotationZ)) * _t2);
        return dest;
    }

    private static void composeTRSMul_mulAdd_sd5c5c337_v(float[] dest, int destOffset, float[] m, int mOffset, float translationX, float translationY, float translationZ, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, translationX).withLane(1, translationY).withLane(2, translationZ);
        var _sv1 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv2 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _h3).withLane(1, _h4).withLane(2, _h5);
        var _sv3 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _h6).withLane(1, _h7).withLane(2, _h8);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = mOffset + _li * 4;
            FloatVector.broadcast(_SIMD_SPECIES, m[_lo0 + 3]).mul(_sv0).add(FloatVector.broadcast(_SIMD_SPECIES, m[_lo0 + 2]).mul(_sv1).add(FloatVector.broadcast(_SIMD_SPECIES, m[_lo0]).mul(_sv2).add(FloatVector.broadcast(_SIMD_SPECIES, m[_lo0 + 1]).mul(_sv3).add(FloatVector.broadcast(_SIMD_SPECIES, 0.0f).withLane(3, m[_lo0 + 3]))))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] composeTRSMul(float[] dest, int destOffset, float[] translation, int translationOffset, float[] rotation, int rotationOffset, float[] scale, int scaleOffset, float[] m, int mOffset) {
        if (SimdSupport.USE_FMA) return composeTRSMul_fma(dest, destOffset, translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, m, mOffset);
        return composeTRSMul_mulAdd(dest, destOffset, translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, m, mOffset);
    }

    public static float[] composeTRSMul_fma(float[] dest, int destOffset, float[] translation, int translationOffset, float[] rotation, int rotationOffset, float[] scale, int scaleOffset, float[] m, int mOffset) {
        float _scalez = scale[scaleOffset + 2];
        float _scalex = scale[scaleOffset];
        float _scaley = scale[scaleOffset + 1];
        float _rotationy = rotation[rotationOffset + 1];
        float _rotationw = rotation[rotationOffset + 3];
        float _rotationz = rotation[rotationOffset + 2];
        float _rotationx = rotation[rotationOffset];
        float _t0 = _scalez + _scalez;
        float _t1 = _scalex + _scalex;
        float _t2 = _scaley + _scaley;
        float _t3 = _rotationy * _rotationw;
        float _t4 = _rotationz * _rotationz;
        float _t5 = _rotationz * _rotationw;
        composeTRSMul_fma_sde574f0a_v(dest, destOffset, m, mOffset, translation[translationOffset], translation[translationOffset + 1], translation[translationOffset + 2], java.lang.Math.fma(_rotationx, _rotationz, _t3) * _t0, java.lang.Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t0, java.lang.Math.fma(-java.lang.Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t0, _scalez), java.lang.Math.fma(-java.lang.Math.fma(_rotationy, _rotationy, _t4), _t1, _scalex), java.lang.Math.fma(_rotationx, _rotationy, _t5) * _t1, java.lang.Math.fma(_rotationx, _rotationz, -_t3) * _t1, java.lang.Math.fma(_rotationx, _rotationy, -_t5) * _t2, java.lang.Math.fma(-java.lang.Math.fma(_rotationx, _rotationx, _t4), _t2, _scaley), java.lang.Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t2);
        return dest;
    }

    public static float[] composeTRSMul_mulAdd(float[] dest, int destOffset, float[] translation, int translationOffset, float[] rotation, int rotationOffset, float[] scale, int scaleOffset, float[] m, int mOffset) {
        float _scalez = scale[scaleOffset + 2];
        float _scalex = scale[scaleOffset];
        float _scaley = scale[scaleOffset + 1];
        float _rotationy = rotation[rotationOffset + 1];
        float _rotationw = rotation[rotationOffset + 3];
        float _rotationz = rotation[rotationOffset + 2];
        float _rotationx = rotation[rotationOffset];
        float _t0 = _scalez + _scalez;
        float _t1 = _scalex + _scalex;
        float _t2 = _scaley + _scaley;
        float _t3 = _rotationy * _rotationw;
        float _t4 = _rotationz * _rotationz;
        float _t5 = _rotationz * _rotationw;
        composeTRSMul_mulAdd_sd5c5c337_v(dest, destOffset, m, mOffset, translation[translationOffset], translation[translationOffset + 1], translation[translationOffset + 2], ((_rotationx) * (_rotationz) + (_t3)) * _t0, ((_rotationy) * (_rotationz) - (_rotationx * _rotationw)) * _t0, ((_scalez) - ((_rotationx) * (_rotationx) + (_rotationy * _rotationy)) * (_t0)), ((_scalex) - ((_rotationy) * (_rotationy) + (_t4)) * (_t1)), ((_rotationx) * (_rotationy) + (_t5)) * _t1, ((_rotationx) * (_rotationz) - (_t3)) * _t1, ((_rotationx) * (_rotationy) - (_t5)) * _t2, ((_scaley) - ((_rotationx) * (_rotationx) + (_t4)) * (_t2)), ((_rotationx) * (_rotationw) + (_rotationy * _rotationz)) * _t2);
        return dest;
    }

    public static float[] frustum_no_lh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        if (SimdSupport.USE_FMA) return frustum_no_lh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return frustum_no_lh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static float[] frustum_no_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t3_inv = 1.0f / (zNear - zFar);
        float _t12, _w0, _w1;
        if (zNear == Float.POSITIVE_INFINITY) {
            if (zFar == Float.POSITIVE_INFINITY) {
                _t12 = 2.0f * zFar;
                _w0 = 1.0f;
                _w1 = -(zNear + zNear);
            } else {
                _t12 = 2.0f * zFar;
                _w0 = -1.0f;
                _w1 = zFar + zFar;
            }
        } else {
            if (zFar == Float.POSITIVE_INFINITY) {
                _t12 = 2.0f * zNear;
                _w0 = 1.0f;
                _w1 = -(zNear + zNear);
            } else {
                _t12 = 2.0f * zNear;
                _w0 = -((zFar + zNear) * _t3_inv);
                _w1 = (zFar + zFar) * zNear * _t3_inv;
            }
        }
        frustum_no_lh_fma_s53dd5caf_v(dest, destOffset, src, srcOffset, left, right, bottom, top, 1.0f / (right - left), 1.0f / (top - bottom), _t12, _w0, _w1);
        return dest;
    }

    private static void frustum_no_lh_fma_s53dd5caf_v(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float _t0_inv, float _t1_inv, float _t12, float _w0, float _w1) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, _w0), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv), _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, -(left + right) * _t0_inv), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t12 * _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t12 * _t1_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
    }

    public static float[] frustum_no_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t3_inv = 1.0f / (zNear - zFar);
        float _t12, _w0, _w1;
        if (zNear == Float.POSITIVE_INFINITY) {
            if (zFar == Float.POSITIVE_INFINITY) {
                _t12 = 2.0f * zFar;
                _w0 = 1.0f;
                _w1 = -(zNear + zNear);
            } else {
                _t12 = 2.0f * zFar;
                _w0 = -1.0f;
                _w1 = zFar + zFar;
            }
        } else {
            if (zFar == Float.POSITIVE_INFINITY) {
                _t12 = 2.0f * zNear;
                _w0 = 1.0f;
                _w1 = -(zNear + zNear);
            } else {
                _t12 = 2.0f * zNear;
                _w0 = -((zFar + zNear) * _t3_inv);
                _w1 = (zFar + zFar) * zNear * _t3_inv;
            }
        }
        frustum_no_lh_mulAdd_se594eb50_v(dest, destOffset, src, srcOffset, left, right, bottom, top, 1.0f / (right - left), 1.0f / (top - bottom), _t12, _w0, _w1);
        return dest;
    }

    private static void frustum_no_lh_mulAdd_se594eb50_v(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float _t0_inv, float _t1_inv, float _t12, float _w0, float _w1) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _w0)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv)).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, -(left + right) * _t0_inv)).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t12 * _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t12 * _t1_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
    }

    public static float[] frustum_no_rh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        if (SimdSupport.USE_FMA) return frustum_no_rh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return frustum_no_rh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static float[] frustum_no_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t3_inv = 1.0f / (zNear - zFar);
        float _t12, _w0, _w1;
        if (zNear == Float.POSITIVE_INFINITY) {
            if (zFar == Float.POSITIVE_INFINITY) {
                _t12 = 2.0f * zFar;
                _w0 = -1.0f;
                _w1 = -(zNear + zNear);
            } else {
                _t12 = 2.0f * zFar;
                _w0 = 1.0f;
                _w1 = zFar + zFar;
            }
        } else {
            if (zFar == Float.POSITIVE_INFINITY) {
                _t12 = 2.0f * zNear;
                _w0 = -1.0f;
                _w1 = -(zNear + zNear);
            } else {
                _t12 = 2.0f * zNear;
                _w0 = (zFar + zNear) * _t3_inv;
                _w1 = (zFar + zFar) * zNear * _t3_inv;
            }
        }
        frustum_no_rh_fma_s2a506905_v(dest, destOffset, src, srcOffset, left, right, bottom, top, 1.0f / (right - left), 1.0f / (top - bottom), _t12, _w0, _w1);
        return dest;
    }

    private static void frustum_no_rh_fma_s2a506905_v(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float _t0_inv, float _t1_inv, float _t12, float _w0, float _w1) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, _w0), _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, (left + right) * _t0_inv), _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, (bottom + top) * _t1_inv))).sub(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t12 * _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t12 * _t1_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
    }

    public static float[] frustum_no_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t3_inv = 1.0f / (zNear - zFar);
        float _t12, _w0, _w1;
        if (zNear == Float.POSITIVE_INFINITY) {
            if (zFar == Float.POSITIVE_INFINITY) {
                _t12 = 2.0f * zFar;
                _w0 = -1.0f;
                _w1 = -(zNear + zNear);
            } else {
                _t12 = 2.0f * zFar;
                _w0 = 1.0f;
                _w1 = zFar + zFar;
            }
        } else {
            if (zFar == Float.POSITIVE_INFINITY) {
                _t12 = 2.0f * zNear;
                _w0 = -1.0f;
                _w1 = -(zNear + zNear);
            } else {
                _t12 = 2.0f * zNear;
                _w0 = (zFar + zNear) * _t3_inv;
                _w1 = (zFar + zFar) * zNear * _t3_inv;
            }
        }
        frustum_no_rh_mulAdd_sb644bac6_v(dest, destOffset, src, srcOffset, left, right, bottom, top, 1.0f / (right - left), 1.0f / (top - bottom), _t12, _w0, _w1);
        return dest;
    }

    private static void frustum_no_rh_mulAdd_sb644bac6_v(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float _t0_inv, float _t1_inv, float _t12, float _w0, float _w1) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _w0)).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, (left + right) * _t0_inv)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, (bottom + top) * _t1_inv))).sub(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t12 * _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t12 * _t1_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
    }

    public static float[] frustum_zo_lh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        if (SimdSupport.USE_FMA) return frustum_zo_lh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return frustum_zo_lh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static float[] frustum_zo_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        float _t2_inv = 1.0f / (zNear - zFar);
        float _t9, _w0, _w1;
        if (zNear == Float.POSITIVE_INFINITY) {
            if (zFar == Float.POSITIVE_INFINITY) {
                _t9 = 2.0f * zFar;
                _w0 = 1.0f;
                _w1 = -zNear;
            } else {
                _t9 = 2.0f * zFar;
                _w0 = 0.0f;
                _w1 = zFar;
            }
        } else {
            if (zFar == Float.POSITIVE_INFINITY) {
                _t9 = 2.0f * zNear;
                _w0 = 1.0f;
                _w1 = -zNear;
            } else {
                _t9 = 2.0f * zNear;
                _w0 = -(zFar * _t2_inv);
                _w1 = zFar * zNear * _t2_inv;
            }
        }
        frustum_zo_lh_fma_s5083c323_v(dest, destOffset, src, srcOffset, _w0, _w1, _t9 * _t0_inv, _t9 * _t1_inv, -(bottom + top) * _t1_inv, -(left + right) * _t0_inv);
        return dest;
    }

    private static void frustum_zo_lh_fma_s5083c323_v(float[] dest, int destOffset, float[] src, int srcOffset, float _w0, float _w1, float _h0, float _h1, float _h2, float _h3) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, _w0), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _h2), _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _h3), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _h0)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _h1)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
    }

    public static float[] frustum_zo_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t2_inv = 1.0f / (zNear - zFar);
        float _t9, _w0, _w1;
        if (zNear == Float.POSITIVE_INFINITY) {
            if (zFar == Float.POSITIVE_INFINITY) {
                _t9 = 2.0f * zFar;
                _w0 = 1.0f;
                _w1 = -zNear;
            } else {
                _t9 = 2.0f * zFar;
                _w0 = 0.0f;
                _w1 = zFar;
            }
        } else {
            if (zFar == Float.POSITIVE_INFINITY) {
                _t9 = 2.0f * zNear;
                _w0 = 1.0f;
                _w1 = -zNear;
            } else {
                _t9 = 2.0f * zNear;
                _w0 = -(zFar * _t2_inv);
                _w1 = zFar * zNear * _t2_inv;
            }
        }
        frustum_no_lh_mulAdd_se594eb50_v(dest, destOffset, src, srcOffset, left, right, bottom, top, 1.0f / (right - left), 1.0f / (top - bottom), _t9, _w0, _w1);
        return dest;
    }

    public static float[] frustum_zo_rh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        if (SimdSupport.USE_FMA) return frustum_zo_rh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return frustum_zo_rh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static float[] frustum_zo_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t2_inv = 1.0f / (zNear - zFar);
        float _t8, _w0, _w1;
        if (zNear == Float.POSITIVE_INFINITY) {
            if (zFar == Float.POSITIVE_INFINITY) {
                _t8 = 2.0f * zFar;
                _w0 = -1.0f;
                _w1 = -zNear;
            } else {
                _t8 = 2.0f * zFar;
                _w0 = 0.0f;
                _w1 = zFar;
            }
        } else {
            if (zFar == Float.POSITIVE_INFINITY) {
                _t8 = 2.0f * zNear;
                _w0 = -1.0f;
                _w1 = -zNear;
            } else {
                _t8 = 2.0f * zNear;
                _w0 = zFar * _t2_inv;
                _w1 = zFar * zNear * _t2_inv;
            }
        }
        frustum_no_rh_fma_s2a506905_v(dest, destOffset, src, srcOffset, left, right, bottom, top, 1.0f / (right - left), 1.0f / (top - bottom), _t8, _w0, _w1);
        return dest;
    }

    public static float[] frustum_zo_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t2_inv = 1.0f / (zNear - zFar);
        float _t8, _w0, _w1;
        if (zNear == Float.POSITIVE_INFINITY) {
            if (zFar == Float.POSITIVE_INFINITY) {
                _t8 = 2.0f * zFar;
                _w0 = -1.0f;
                _w1 = -zNear;
            } else {
                _t8 = 2.0f * zFar;
                _w0 = 0.0f;
                _w1 = zFar;
            }
        } else {
            if (zFar == Float.POSITIVE_INFINITY) {
                _t8 = 2.0f * zNear;
                _w0 = -1.0f;
                _w1 = -zNear;
            } else {
                _t8 = 2.0f * zNear;
                _w0 = zFar * _t2_inv;
                _w1 = zFar * zNear * _t2_inv;
            }
        }
        frustum_no_rh_mulAdd_sb644bac6_v(dest, destOffset, src, srcOffset, left, right, bottom, top, 1.0f / (right - left), 1.0f / (top - bottom), _t8, _w0, _w1);
        return dest;
    }

    public static float[] lookAlong_lh(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (SimdSupport.USE_FMA) return lookAlong_lh_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_lh_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static float[] lookAlong_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirX * _t6;
            _t12 = dirZ * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(upZ, _t12, java.lang.Math.fma(upX, _t11, upY * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t11, upX);
        float _t19 = java.lang.Math.fma(_t17, _t13, upY);
        float _t20 = java.lang.Math.fma(_t17, _t12, upZ);
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t11));
        float _t28 = java.lang.Math.fma(_t19, _t12, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t18 * _t12));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        lookAlong_lh_fma_sd05d2e07_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    private static void lookAlong_lh_fma_sd05d2e07_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t11), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _t37), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t38, _t13, -(_t39 * _t12)))))).intoArray(dest, destOffset);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t13), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _t39), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t37, _t12, -(_t38 * _t11)))))).intoArray(dest, destOffset + 4);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t12), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _t38), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t39, _t11, -(_t37 * _t13)))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] lookAlong_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirX * _t6;
            _t12 = dirZ * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((upZ) * (_t12) + (((upX) * (_t11) + (upY * _t13))));
        float _t18 = ((_t17) * (_t11) + (upX));
        float _t19 = ((_t17) * (_t13) + (upY));
        float _t20 = ((_t17) * (_t12) + (upZ));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t11));
        float _t28 = ((_t19) * (_t12) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t11) - (_t18 * _t12));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        lookAlong_lh_mulAdd_sbd1d8738_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    private static void lookAlong_lh_mulAdd_sbd1d8738_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t11)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t37)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t38) * (_t13) - (_t39 * _t12)))))).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t13)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t39)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t37) * (_t12) - (_t38 * _t11)))))).intoArray(dest, destOffset + 4);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t12)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t38)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t39) * (_t11) - (_t37 * _t13)))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] lookAlong_rh(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (SimdSupport.USE_FMA) return lookAlong_rh_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_rh_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static float[] lookAlong_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t15, _t16, _t17;
        if (_t8 != 0.0f) {
            _t15 = dirX * _t10;
            _t16 = dirZ * _t10;
            _t17 = dirY * _t10;
        } else {
            _t15 = 0.0f;
            _t16 = 0.0f;
            _t17 = 0.0f;
        }
        float _t21 = -java.lang.Math.fma(upZ, _t16, java.lang.Math.fma(upX, _t15, upY * _t17));
        float _t22 = java.lang.Math.fma(_t21, _t17, upY);
        float _t23 = java.lang.Math.fma(_t21, _t15, upX);
        float _t24 = java.lang.Math.fma(_t21, _t16, upZ);
        float _t31 = java.lang.Math.fma(_t22, _t15, -(_t23 * _t17));
        float _t32 = java.lang.Math.fma(_t23, _t16, -(_t24 * _t15));
        float _t33 = java.lang.Math.fma(_t24, _t17, -(_t22 * _t16));
        float _t36 = java.lang.Math.fma(_t31, _t31, java.lang.Math.fma(_t32, _t32, _t33 * _t33));
        float _t37 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41, _t42, _t43;
        if (_t36 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t41 = _t33 * _t37;
            _t42 = _t32 * _t37;
            _t43 = _t31 * _t37;
        } else {
            _t41 = 0.0f;
            _t42 = 0.0f;
            _t43 = 0.0f;
        }
        return lookAlong_rh_fma_s1508638d_1(dest, destOffset, src, srcOffset, _t15, _t16, _t17, _t41, _t42, _t43);
    }

    /** Piece 2 of {@code lookAlong_rh_fma}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_fma_s1508638d_1(float[] dest, int destOffset, float[] src, int srcOffset, float _t15, float _t16, float _t17, float _t41, float _t42, float _t43) {
        lookAlong_rh_fma_s666918b9_v(dest, destOffset, src, srcOffset, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t15, _t16, _t17, _t41, _t42, _t43);
        return dest;
    }

    private static void lookAlong_rh_fma_s666918b9_v(float[] dest, int destOffset, float[] src, int srcOffset, float _self02, float _self12, float _self22, float _self32, float _t15, float _t16, float _t17, float _t41, float _t42, float _t43) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t15), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _t41), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t42, _t16, -(_t43 * _t17)))))).intoArray(dest, destOffset);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t17), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _t42), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t43, _t15, -(_t41 * _t16)))))).intoArray(dest, destOffset + 4);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t16), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _t43), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t41, _t17, -(_t42 * _t15)))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] lookAlong_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t15, _t16, _t17;
        if (_t8 != 0.0f) {
            _t15 = dirX * _t10;
            _t16 = dirZ * _t10;
            _t17 = dirY * _t10;
        } else {
            _t15 = 0.0f;
            _t16 = 0.0f;
            _t17 = 0.0f;
        }
        float _t21 = -((upZ) * (_t16) + (((upX) * (_t15) + (upY * _t17))));
        float _t22 = ((_t21) * (_t17) + (upY));
        float _t23 = ((_t21) * (_t15) + (upX));
        float _t24 = ((_t21) * (_t16) + (upZ));
        float _t31 = ((_t22) * (_t15) - (_t23 * _t17));
        float _t32 = ((_t23) * (_t16) - (_t24 * _t15));
        float _t33 = ((_t24) * (_t17) - (_t22 * _t16));
        float _t36 = ((_t31) * (_t31) + (((_t32) * (_t32) + (_t33 * _t33))));
        float _t37 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41, _t42, _t43;
        if (_t36 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t41 = _t33 * _t37;
            _t42 = _t32 * _t37;
            _t43 = _t31 * _t37;
        } else {
            _t41 = 0.0f;
            _t42 = 0.0f;
            _t43 = 0.0f;
        }
        lookAlong_rh_mulAdd_s2def7b0a_v(dest, destOffset, src, srcOffset, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t15, _t16, _t17, _t41, _t42, _t43);
        return dest;
    }

    private static void lookAlong_rh_mulAdd_s2def7b0a_v(float[] dest, int destOffset, float[] src, int srcOffset, float _self02, float _self12, float _self22, float _self32, float _t15, float _t16, float _t17, float _t41, float _t42, float _t43) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t15)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t41)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t42) * (_t16) - (_t43 * _t17)))))).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t17)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t42)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t43) * (_t15) - (_t41 * _t16)))))).intoArray(dest, destOffset + 4);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t16)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t43)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t41) * (_t17) - (_t42 * _t15)))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] lookAlong_lh(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        if (SimdSupport.USE_FMA) return lookAlong_lh_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_lh_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static float[] lookAlong_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        float _dirz = dir[dirOffset + 2];
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirx * _t6;
            _t12 = _dirz * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(_upz, _t12, java.lang.Math.fma(_upx, _t11, _upy * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t11, _upx);
        float _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        float _t20 = java.lang.Math.fma(_t17, _t12, _upz);
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t11));
        float _t28 = java.lang.Math.fma(_t19, _t12, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t18 * _t12));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return lookAlong_lh_fma_s38d6723b_1(dest, destOffset, src, srcOffset, _upz, _upx, _upy, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code lookAlong_lh_fma}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_fma_s38d6723b_1(float[] dest, int destOffset, float[] src, int srcOffset, float _upz, float _upx, float _upy, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        lookAlong_lh_fma_sd05d2e07_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static float[] lookAlong_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        float _dirz = dir[dirOffset + 2];
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirx * _t6;
            _t12 = _dirz * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((_upz) * (_t12) + (((_upx) * (_t11) + (_upy * _t13))));
        float _t18 = ((_t17) * (_t11) + (_upx));
        float _t19 = ((_t17) * (_t13) + (_upy));
        float _t20 = ((_t17) * (_t12) + (_upz));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t11));
        float _t28 = ((_t19) * (_t12) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t11) - (_t18 * _t12));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return lookAlong_lh_mulAdd_saf127fb2_1(dest, destOffset, src, srcOffset, _upz, _upx, _upy, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code lookAlong_lh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_mulAdd_saf127fb2_1(float[] dest, int destOffset, float[] src, int srcOffset, float _upz, float _upx, float _upy, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
        float _t37, _t38, _t39;
        if (_t32 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        lookAlong_lh_mulAdd_sbd1d8738_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static float[] lookAlong_rh(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        if (SimdSupport.USE_FMA) return lookAlong_rh_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_rh_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static float[] lookAlong_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        float _dirz = dir[dirOffset + 2];
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _t8 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t15, _t16, _t17;
        if (_t8 != 0.0f) {
            _t15 = _dirx * _t10;
            _t16 = _dirz * _t10;
            _t17 = _diry * _t10;
        } else {
            _t15 = 0.0f;
            _t16 = 0.0f;
            _t17 = 0.0f;
        }
        float _t21 = -java.lang.Math.fma(_upz, _t16, java.lang.Math.fma(_upx, _t15, _upy * _t17));
        float _t22 = java.lang.Math.fma(_t21, _t17, _upy);
        float _t23 = java.lang.Math.fma(_t21, _t15, _upx);
        float _t24 = java.lang.Math.fma(_t21, _t16, _upz);
        float _t31 = java.lang.Math.fma(_t22, _t15, -(_t23 * _t17));
        float _t32 = java.lang.Math.fma(_t23, _t16, -(_t24 * _t15));
        float _t33 = java.lang.Math.fma(_t24, _t17, -(_t22 * _t16));
        float _t36 = java.lang.Math.fma(_t31, _t31, java.lang.Math.fma(_t32, _t32, _t33 * _t33));
        return lookAlong_rh_fma_s622f41bd_1(dest, destOffset, src, srcOffset, _upz, _upx, _upy, _t15, _t16, _t17, _t31, _t32, _t33, _t36, (1.0f / (float) java.lang.Math.sqrt(_t36)));
    }

    /** Piece 2 of {@code lookAlong_rh_fma}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_fma_s622f41bd_1(float[] dest, int destOffset, float[] src, int srcOffset, float _upz, float _upx, float _upy, float _t15, float _t16, float _t17, float _t31, float _t32, float _t33, float _t36, float _t37) {
        float _t41, _t42, _t43;
        if (_t36 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t41 = _t33 * _t37;
            _t42 = _t32 * _t37;
            _t43 = _t31 * _t37;
        } else {
            _t41 = 0.0f;
            _t42 = 0.0f;
            _t43 = 0.0f;
        }
        lookAlong_rh_fma_s666918b9_v(dest, destOffset, src, srcOffset, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t15, _t16, _t17, _t41, _t42, _t43);
        return dest;
    }

    public static float[] lookAlong_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        float _dirz = dir[dirOffset + 2];
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _t8 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t15, _t16, _t17;
        if (_t8 != 0.0f) {
            _t15 = _dirx * _t10;
            _t16 = _dirz * _t10;
            _t17 = _diry * _t10;
        } else {
            _t15 = 0.0f;
            _t16 = 0.0f;
            _t17 = 0.0f;
        }
        float _t21 = -((_upz) * (_t16) + (((_upx) * (_t15) + (_upy * _t17))));
        float _t22 = ((_t21) * (_t17) + (_upy));
        float _t23 = ((_t21) * (_t15) + (_upx));
        float _t24 = ((_t21) * (_t16) + (_upz));
        float _t31 = ((_t22) * (_t15) - (_t23 * _t17));
        float _t32 = ((_t23) * (_t16) - (_t24 * _t15));
        float _t33 = ((_t24) * (_t17) - (_t22 * _t16));
        float _t36 = ((_t31) * (_t31) + (((_t32) * (_t32) + (_t33 * _t33))));
        return lookAlong_rh_mulAdd_s6eac0b0c_1(dest, destOffset, src, srcOffset, _upz, _upx, _upy, _t15, _t16, _t17, _t31, _t32, _t33, _t36, (1.0f / (float) java.lang.Math.sqrt(_t36)));
    }

    /** Piece 2 of {@code lookAlong_rh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_mulAdd_s6eac0b0c_1(float[] dest, int destOffset, float[] src, int srcOffset, float _upz, float _upx, float _upy, float _t15, float _t16, float _t17, float _t31, float _t32, float _t33, float _t36, float _t37) {
        float _t41, _t42, _t43;
        if (_t36 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t41 = _t33 * _t37;
            _t42 = _t32 * _t37;
            _t43 = _t31 * _t37;
        } else {
            _t41 = 0.0f;
            _t42 = 0.0f;
            _t43 = 0.0f;
        }
        lookAlong_rh_mulAdd_s2def7b0a_v(dest, destOffset, src, srcOffset, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t15, _t16, _t17, _t41, _t42, _t43);
        return dest;
    }

    public static float[] lookAlong_lh(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (SimdSupport.USE_FMA) return lookAlong_lh_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_lh_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static float[] lookAlong_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirX * _t6;
            _t12 = dirZ * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(upZ, _t12, java.lang.Math.fma(upX, _t11, upY * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t11, upX);
        float _t19 = java.lang.Math.fma(_t17, _t13, upY);
        float _t20 = java.lang.Math.fma(_t17, _t12, upZ);
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t11));
        float _t28 = java.lang.Math.fma(_t19, _t12, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t18 * _t12));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        lookAlong_lh_fma_sd05d2e07_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static float[] lookAlong_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = dirX * _t6;
            _t12 = dirZ * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((upZ) * (_t12) + (((upX) * (_t11) + (upY * _t13))));
        float _t18 = ((_t17) * (_t11) + (upX));
        float _t19 = ((_t17) * (_t13) + (upY));
        float _t20 = ((_t17) * (_t12) + (upZ));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t11));
        float _t28 = ((_t19) * (_t12) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t11) - (_t18 * _t12));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        lookAlong_lh_mulAdd_sbd1d8738_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static float[] lookAlong_rh(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (SimdSupport.USE_FMA) return lookAlong_rh_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return lookAlong_rh_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static float[] lookAlong_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t8 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t15, _t16, _t17;
        if (_t8 != 0.0f) {
            _t15 = dirX * _t10;
            _t16 = dirZ * _t10;
            _t17 = dirY * _t10;
        } else {
            _t15 = 0.0f;
            _t16 = 0.0f;
            _t17 = 0.0f;
        }
        float _t21 = -java.lang.Math.fma(upZ, _t16, java.lang.Math.fma(upX, _t15, upY * _t17));
        float _t22 = java.lang.Math.fma(_t21, _t17, upY);
        float _t23 = java.lang.Math.fma(_t21, _t15, upX);
        float _t24 = java.lang.Math.fma(_t21, _t16, upZ);
        float _t31 = java.lang.Math.fma(_t22, _t15, -(_t23 * _t17));
        float _t32 = java.lang.Math.fma(_t23, _t16, -(_t24 * _t15));
        float _t33 = java.lang.Math.fma(_t24, _t17, -(_t22 * _t16));
        float _t36 = java.lang.Math.fma(_t31, _t31, java.lang.Math.fma(_t32, _t32, _t33 * _t33));
        float _t37 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41, _t42, _t43;
        if (_t36 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t41 = _t33 * _t37;
            _t42 = _t32 * _t37;
            _t43 = _t31 * _t37;
        } else {
            _t41 = 0.0f;
            _t42 = 0.0f;
            _t43 = 0.0f;
        }
        return lookAlong_rh_fma_sc2039bf_1(dest, destOffset, src, srcOffset, _t15, _t16, _t17, _t41, _t42, _t43);
    }

    /** Piece 2 of {@code lookAlong_rh_fma}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_fma_sc2039bf_1(float[] dest, int destOffset, float[] src, int srcOffset, float _t15, float _t16, float _t17, float _t41, float _t42, float _t43) {
        lookAlong_rh_fma_s666918b9_v(dest, destOffset, src, srcOffset, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t15, _t16, _t17, _t41, _t42, _t43);
        return dest;
    }

    public static float[] lookAlong_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t8 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t15, _t16, _t17;
        if (_t8 != 0.0f) {
            _t15 = dirX * _t10;
            _t16 = dirZ * _t10;
            _t17 = dirY * _t10;
        } else {
            _t15 = 0.0f;
            _t16 = 0.0f;
            _t17 = 0.0f;
        }
        float _t21 = -((upZ) * (_t16) + (((upX) * (_t15) + (upY * _t17))));
        float _t22 = ((_t21) * (_t17) + (upY));
        float _t23 = ((_t21) * (_t15) + (upX));
        float _t24 = ((_t21) * (_t16) + (upZ));
        float _t31 = ((_t22) * (_t15) - (_t23 * _t17));
        float _t32 = ((_t23) * (_t16) - (_t24 * _t15));
        float _t33 = ((_t24) * (_t17) - (_t22 * _t16));
        float _t36 = ((_t31) * (_t31) + (((_t32) * (_t32) + (_t33 * _t33))));
        float _t37 = (1.0f / (float) java.lang.Math.sqrt(_t36));
        float _t41, _t42, _t43;
        if (_t36 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t41 = _t33 * _t37;
            _t42 = _t32 * _t37;
            _t43 = _t31 * _t37;
        } else {
            _t41 = 0.0f;
            _t42 = 0.0f;
            _t43 = 0.0f;
        }
        lookAlong_rh_mulAdd_s2def7b0a_v(dest, destOffset, src, srcOffset, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t15, _t16, _t17, _t41, _t42, _t43);
        return dest;
    }

    public static float[] lookAlong_lh(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        if (SimdSupport.USE_FMA) return lookAlong_lh_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_lh_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static float[] lookAlong_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        float _dirz = dir[dirOffset + 2];
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirx * _t6;
            _t12 = _dirz * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -java.lang.Math.fma(_upz, _t12, java.lang.Math.fma(_upx, _t11, _upy * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t11, _upx);
        float _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        float _t20 = java.lang.Math.fma(_t17, _t12, _upz);
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t11));
        float _t28 = java.lang.Math.fma(_t19, _t12, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t11, -(_t18 * _t12));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return lookAlong_lh_fma_sdee9a4e1_1(dest, destOffset, src, srcOffset, epsilon, _upz, _upx, _upy, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code lookAlong_lh_fma}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_fma_sdee9a4e1_1(float[] dest, int destOffset, float[] src, int srcOffset, float epsilon, float _upz, float _upx, float _upy, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        lookAlong_lh_fma_sd05d2e07_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static float[] lookAlong_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        float _dirz = dir[dirOffset + 2];
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
        float _t11, _t12, _t13;
        if (_t4 != 0.0f) {
            _t11 = _dirx * _t6;
            _t12 = _dirz * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0f;
            _t12 = 0.0f;
            _t13 = 0.0f;
        }
        float _t17 = -((_upz) * (_t12) + (((_upx) * (_t11) + (_upy * _t13))));
        float _t18 = ((_t17) * (_t11) + (_upx));
        float _t19 = ((_t17) * (_t13) + (_upy));
        float _t20 = ((_t17) * (_t12) + (_upz));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t11));
        float _t28 = ((_t19) * (_t12) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t11) - (_t18 * _t12));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return lookAlong_lh_mulAdd_s393e0ae_1(dest, destOffset, src, srcOffset, epsilon, _upz, _upx, _upy, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code lookAlong_lh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_lh_mulAdd_s393e0ae_1(float[] dest, int destOffset, float[] src, int srcOffset, float epsilon, float _upz, float _upx, float _upy, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
        float _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t37 = _t28 * _t33;
            _t38 = _t27 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        lookAlong_lh_mulAdd_sbd1d8738_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static float[] lookAlong_rh(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        if (SimdSupport.USE_FMA) return lookAlong_rh_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return lookAlong_rh_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static float[] lookAlong_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        float _dirz = dir[dirOffset + 2];
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _t8 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t15, _t16, _t17;
        if (_t8 != 0.0f) {
            _t15 = _dirx * _t10;
            _t16 = _dirz * _t10;
            _t17 = _diry * _t10;
        } else {
            _t15 = 0.0f;
            _t16 = 0.0f;
            _t17 = 0.0f;
        }
        float _t21 = -java.lang.Math.fma(_upz, _t16, java.lang.Math.fma(_upx, _t15, _upy * _t17));
        float _t22 = java.lang.Math.fma(_t21, _t17, _upy);
        float _t23 = java.lang.Math.fma(_t21, _t15, _upx);
        float _t24 = java.lang.Math.fma(_t21, _t16, _upz);
        float _t31 = java.lang.Math.fma(_t22, _t15, -(_t23 * _t17));
        float _t32 = java.lang.Math.fma(_t23, _t16, -(_t24 * _t15));
        float _t33 = java.lang.Math.fma(_t24, _t17, -(_t22 * _t16));
        float _t36 = java.lang.Math.fma(_t31, _t31, java.lang.Math.fma(_t32, _t32, _t33 * _t33));
        return lookAlong_rh_fma_sf5332b23_1(dest, destOffset, src, srcOffset, epsilon, _upz, _upx, _upy, _t15, _t16, _t17, _t31, _t32, _t33, _t36, (1.0f / (float) java.lang.Math.sqrt(_t36)));
    }

    /** Piece 2 of {@code lookAlong_rh_fma}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_fma_sf5332b23_1(float[] dest, int destOffset, float[] src, int srcOffset, float epsilon, float _upz, float _upx, float _upy, float _t15, float _t16, float _t17, float _t31, float _t32, float _t33, float _t36, float _t37) {
        float _t41, _t42, _t43;
        if (_t36 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t41 = _t33 * _t37;
            _t42 = _t32 * _t37;
            _t43 = _t31 * _t37;
        } else {
            _t41 = 0.0f;
            _t42 = 0.0f;
            _t43 = 0.0f;
        }
        lookAlong_rh_fma_s666918b9_v(dest, destOffset, src, srcOffset, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t15, _t16, _t17, _t41, _t42, _t43);
        return dest;
    }

    public static float[] lookAlong_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        float _dirz = dir[dirOffset + 2];
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _t8 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t8));
        float _t15, _t16, _t17;
        if (_t8 != 0.0f) {
            _t15 = _dirx * _t10;
            _t16 = _dirz * _t10;
            _t17 = _diry * _t10;
        } else {
            _t15 = 0.0f;
            _t16 = 0.0f;
            _t17 = 0.0f;
        }
        float _t21 = -((_upz) * (_t16) + (((_upx) * (_t15) + (_upy * _t17))));
        float _t22 = ((_t21) * (_t17) + (_upy));
        float _t23 = ((_t21) * (_t15) + (_upx));
        float _t24 = ((_t21) * (_t16) + (_upz));
        float _t31 = ((_t22) * (_t15) - (_t23 * _t17));
        float _t32 = ((_t23) * (_t16) - (_t24 * _t15));
        float _t33 = ((_t24) * (_t17) - (_t22 * _t16));
        float _t36 = ((_t31) * (_t31) + (((_t32) * (_t32) + (_t33 * _t33))));
        return lookAlong_rh_mulAdd_s3c23add0_1(dest, destOffset, src, srcOffset, epsilon, _upz, _upx, _upy, _t15, _t16, _t17, _t31, _t32, _t33, _t36, (1.0f / (float) java.lang.Math.sqrt(_t36)));
    }

    /** Piece 2 of {@code lookAlong_rh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_rh_mulAdd_s3c23add0_1(float[] dest, int destOffset, float[] src, int srcOffset, float epsilon, float _upz, float _upx, float _upy, float _t15, float _t16, float _t17, float _t31, float _t32, float _t33, float _t36, float _t37) {
        float _t41, _t42, _t43;
        if (_t36 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t41 = _t33 * _t37;
            _t42 = _t32 * _t37;
            _t43 = _t31 * _t37;
        } else {
            _t41 = 0.0f;
            _t42 = 0.0f;
            _t43 = 0.0f;
        }
        lookAlong_rh_mulAdd_s2def7b0a_v(dest, destOffset, src, srcOffset, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _t15, _t16, _t17, _t41, _t42, _t43);
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
        float _t9 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
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
        float _t23 = -java.lang.Math.fma(upZ, _t15, java.lang.Math.fma(upX, _t14, upY * _t16));
        float _t24 = java.lang.Math.fma(_t23, _t14, upX);
        float _t25 = java.lang.Math.fma(_t23, _t16, upY);
        float _t26 = java.lang.Math.fma(_t23, _t15, upZ);
        float _t33 = java.lang.Math.fma(_t24, _t16, -(_t25 * _t14));
        float _t34 = java.lang.Math.fma(_t25, _t15, -(_t26 * _t16));
        float _t35 = java.lang.Math.fma(_t26, _t14, -(_t24 * _t15));
        float _t38 = java.lang.Math.fma(_t33, _t33, java.lang.Math.fma(_t34, _t34, _t35 * _t35));
        return lookAt_lh_fma_s63bc8cb3_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t14, _t15, _t16, _t33, _t34, _t35, _t38, (1.0f / (float) java.lang.Math.sqrt(_t38)));
    }

    /** Piece 2 of {@code lookAt_lh_fma}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_fma_s63bc8cb3_1(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _t14, float _t15, float _t16, float _t33, float _t34, float _t35, float _t38, float _t39) {
        float _t43, _t44, _t45;
        if (_t38 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0f;
            _t44 = 0.0f;
            _t45 = 0.0f;
        }
        lookAt_lh_fma_s7dab4453_v(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t14, _t15, _t16, _t43, _t44, _t45, java.lang.Math.fma(_t44, _t16, -(_t45 * _t15)), java.lang.Math.fma(_t43, _t15, -(_t44 * _t14)), java.lang.Math.fma(_t45, _t14, -(_t43 * _t16)));
        return dest;
    }

    private static void lookAt_lh_fma_s7dab4453_v(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float _t14, float _t15, float _t16, float _t43, float _t44, float _t45, float _t54, float _t55, float _t56) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, -java.lang.Math.fma(eyeZ, _t44, java.lang.Math.fma(eyeX, _t43, eyeY * _t45))), _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, -java.lang.Math.fma(eyeZ, _t56, java.lang.Math.fma(eyeX, _t54, eyeY * _t55))), _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, -java.lang.Math.fma(eyeZ, _t15, java.lang.Math.fma(eyeX, _t14, eyeY * _t16))), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t14), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _t43), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t54)))).intoArray(dest, destOffset);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t16), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _t45), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t55)))).intoArray(dest, destOffset + 4);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t15), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _t44), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t56)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] lookAt_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
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
        float _t23 = -((upZ) * (_t15) + (((upX) * (_t14) + (upY * _t16))));
        float _t24 = ((_t23) * (_t14) + (upX));
        float _t25 = ((_t23) * (_t16) + (upY));
        float _t26 = ((_t23) * (_t15) + (upZ));
        float _t33 = ((_t24) * (_t16) - (_t25 * _t14));
        float _t34 = ((_t25) * (_t15) - (_t26 * _t16));
        float _t35 = ((_t26) * (_t14) - (_t24 * _t15));
        float _t38 = ((_t33) * (_t33) + (((_t34) * (_t34) + (_t35 * _t35))));
        float _t39 = (1.0f / (float) java.lang.Math.sqrt(_t38));
        float _t43, _t44, _t45;
        if (_t38 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0f;
            _t44 = 0.0f;
            _t45 = 0.0f;
        }
        return lookAt_lh_mulAdd_s3607e316_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t14, _t15, _t16, _t43, _t44, _t45);
    }

    /** Piece 2 of {@code lookAt_lh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_mulAdd_s3607e316_1(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float _t14, float _t15, float _t16, float _t43, float _t44, float _t45) {
        float _t54 = ((_t44) * (_t16) - (_t45 * _t15));
        float _t55 = ((_t43) * (_t15) - (_t44 * _t14));
        float _t56 = ((_t45) * (_t14) - (_t43 * _t16));
        lookAt_lh_mulAdd_s91fa99d2_v(dest, destOffset, src, srcOffset, _t14, _t15, _t16, _t43, _t44, _t45, _t54, _t55, _t56, -((eyeZ) * (_t44) + (((eyeX) * (_t43) + (eyeY * _t45)))), -((eyeZ) * (_t56) + (((eyeX) * (_t54) + (eyeY * _t55)))), -((eyeZ) * (_t15) + (((eyeX) * (_t14) + (eyeY * _t16)))));
        return dest;
    }

    private static void lookAt_lh_mulAdd_s91fa99d2_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t14, float _t15, float _t16, float _t43, float _t44, float _t45, float _t54, float _t55, float _t56, float _h0, float _h1, float _h2) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _h0)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _h1)).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _h2)).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t14)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t43)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t54)))).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t16)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t45)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t55)))).intoArray(dest, destOffset + 4);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t15)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t44)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t56)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] lookAt_rh(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (SimdSupport.USE_FMA) return lookAt_rh_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return lookAt_rh_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static float[] lookAt_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t4 = centerZ - eyeZ;
        float _t5 = centerX - eyeX;
        float _t6 = centerY - eyeY;
        float _t13 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t13));
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
        float _t27 = -java.lang.Math.fma(upZ, _t19, java.lang.Math.fma(upX, _t18, upY * _t20));
        float _t28 = java.lang.Math.fma(_t27, _t20, upY);
        float _t29 = java.lang.Math.fma(_t27, _t18, upX);
        float _t30 = java.lang.Math.fma(_t27, _t19, upZ);
        float _t37 = java.lang.Math.fma(_t28, _t18, -(_t29 * _t20));
        float _t38 = java.lang.Math.fma(_t29, _t19, -(_t30 * _t18));
        float _t39 = java.lang.Math.fma(_t30, _t20, -(_t28 * _t19));
        float _t42 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t39 * _t39));
        return lookAt_rh_fma_s16fd7b99_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t18, _t19, _t20, _t37, _t38, _t39, _t42, (1.0f / (float) java.lang.Math.sqrt(_t42)));
    }

    /** Piece 2 of {@code lookAt_rh_fma}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_fma_s16fd7b99_1(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _t18, float _t19, float _t20, float _t37, float _t38, float _t39, float _t42, float _t43) {
        float _t47, _t48, _t49;
        if (_t42 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0f;
            _t48 = 0.0f;
            _t49 = 0.0f;
        }
        float _t58 = java.lang.Math.fma(_t48, _t19, -(_t49 * _t20));
        float _t59 = java.lang.Math.fma(_t49, _t18, -(_t47 * _t19));
        float _t60 = java.lang.Math.fma(_t47, _t20, -(_t48 * _t18));
        lookAt_rh_fma_sf51a6345_v(dest, destOffset, src, srcOffset, _t18, _t19, _t20, _t47, _t48, _t49, _t58, _t59, _t60, -src[srcOffset + 8], -src[srcOffset + 9], -src[srcOffset + 10], -src[srcOffset + 11], -java.lang.Math.fma(eyeZ, _t49, java.lang.Math.fma(eyeX, _t47, eyeY * _t48)), -java.lang.Math.fma(eyeZ, _t60, java.lang.Math.fma(eyeX, _t58, eyeY * _t59)), java.lang.Math.fma(eyeZ, _t19, java.lang.Math.fma(eyeX, _t18, eyeY * _t20)));
        return dest;
    }

    private static void lookAt_rh_fma_sf51a6345_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t18, float _t19, float _t20, float _t47, float _t48, float _t49, float _t58, float _t59, float _t60, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2).withLane(3, _h3);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _h4), _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, _h5), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).fma(FloatVector.broadcast(_SIMD_SPECIES, _h6), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t18), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _t47), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t58)))).intoArray(dest, destOffset);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t20), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _t48), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t59)))).intoArray(dest, destOffset + 4);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t19), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _t49), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t60)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] lookAt_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t4 = centerZ - eyeZ;
        float _t5 = centerX - eyeX;
        float _t6 = centerY - eyeY;
        float _t13 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t13));
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
        float _t27 = -((upZ) * (_t19) + (((upX) * (_t18) + (upY * _t20))));
        float _t28 = ((_t27) * (_t20) + (upY));
        float _t29 = ((_t27) * (_t18) + (upX));
        float _t30 = ((_t27) * (_t19) + (upZ));
        float _t37 = ((_t28) * (_t18) - (_t29 * _t20));
        float _t38 = ((_t29) * (_t19) - (_t30 * _t18));
        float _t39 = ((_t30) * (_t20) - (_t28 * _t19));
        float _t42 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t39 * _t39))));
        float _t43 = (1.0f / (float) java.lang.Math.sqrt(_t42));
        float _t47, _t48, _t49;
        if (_t42 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0f;
            _t48 = 0.0f;
            _t49 = 0.0f;
        }
        return lookAt_rh_mulAdd_sbad010d4_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t18, _t19, _t20, _t47, _t48, _t49);
    }

    /** Piece 2 of {@code lookAt_rh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_mulAdd_sbad010d4_1(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float _t18, float _t19, float _t20, float _t47, float _t48, float _t49) {
        float _t58 = ((_t48) * (_t19) - (_t49 * _t20));
        float _t59 = ((_t49) * (_t18) - (_t47 * _t19));
        float _t60 = ((_t47) * (_t20) - (_t48 * _t18));
        lookAt_rh_mulAdd_se6ecb5dc_v(dest, destOffset, src, srcOffset, _t18, _t19, _t20, _t47, _t48, _t49, _t58, _t59, _t60, -src[srcOffset + 8], -src[srcOffset + 9], -src[srcOffset + 10], -src[srcOffset + 11], -((eyeZ) * (_t49) + (((eyeX) * (_t47) + (eyeY * _t48)))), -((eyeZ) * (_t60) + (((eyeX) * (_t58) + (eyeY * _t59)))), ((eyeZ) * (_t19) + (((eyeX) * (_t18) + (eyeY * _t20)))));
        return dest;
    }

    private static void lookAt_rh_mulAdd_se6ecb5dc_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t18, float _t19, float _t20, float _t47, float _t48, float _t49, float _t58, float _t59, float _t60, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2).withLane(3, _h3);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _h4)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _h5)).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).mul(FloatVector.broadcast(_SIMD_SPECIES, _h6)).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t18)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t47)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t58)))).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t20)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t48)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t59)))).intoArray(dest, destOffset + 4);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t19)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t49)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t60)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] lookAt_lh(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        if (SimdSupport.USE_FMA) return lookAt_lh_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return lookAt_lh_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static float[] lookAt_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        float _eyez = eye[eyeOffset + 2];
        float _eyex = eye[eyeOffset];
        float _eyey = eye[eyeOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _t0 = center[centerOffset + 2] - _eyez;
        float _t1 = center[centerOffset] - _eyex;
        float _t2 = center[centerOffset + 1] - _eyey;
        float _t9 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
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
        float _t23 = -java.lang.Math.fma(_upz, _t15, java.lang.Math.fma(_upx, _t14, _upy * _t16));
        float _t24 = java.lang.Math.fma(_t23, _t14, _upx);
        float _t25 = java.lang.Math.fma(_t23, _t16, _upy);
        float _t26 = java.lang.Math.fma(_t23, _t15, _upz);
        float _t33 = java.lang.Math.fma(_t24, _t16, -(_t25 * _t14));
        float _t34 = java.lang.Math.fma(_t25, _t15, -(_t26 * _t16));
        float _t35 = java.lang.Math.fma(_t26, _t14, -(_t24 * _t15));
        return lookAt_lh_fma_sece1bb5_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t14, _t15, _t16, _t33, _t34, _t35, java.lang.Math.fma(_t33, _t33, java.lang.Math.fma(_t34, _t34, _t35 * _t35)));
    }

    /** Piece 2 of {@code lookAt_lh_fma}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_fma_sece1bb5_1(float[] dest, int destOffset, float[] src, int srcOffset, float _eyez, float _eyex, float _eyey, float _upz, float _upx, float _upy, float _t14, float _t15, float _t16, float _t33, float _t34, float _t35, float _t38) {
        float _t39 = (1.0f / (float) java.lang.Math.sqrt(_t38));
        float _t43, _t44, _t45;
        if (_t38 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0f;
            _t44 = 0.0f;
            _t45 = 0.0f;
        }
        lookAt_lh_fma_sadc67aa1_v(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _t14, _t15, _t16, _t43, _t44, _t45, java.lang.Math.fma(_t44, _t16, -(_t45 * _t15)), java.lang.Math.fma(_t43, _t15, -(_t44 * _t14)), java.lang.Math.fma(_t45, _t14, -(_t43 * _t16)));
        return dest;
    }

    private static void lookAt_lh_fma_sadc67aa1_v(float[] dest, int destOffset, float[] src, int srcOffset, float _eyez, float _eyex, float _eyey, float _t14, float _t15, float _t16, float _t43, float _t44, float _t45, float _t54, float _t55, float _t56) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, -java.lang.Math.fma(_eyez, _t44, java.lang.Math.fma(_eyex, _t43, _eyey * _t45))), _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, -java.lang.Math.fma(_eyez, _t56, java.lang.Math.fma(_eyex, _t54, _eyey * _t55))), _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, -java.lang.Math.fma(_eyez, _t15, java.lang.Math.fma(_eyex, _t14, _eyey * _t16))), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t14), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _t43), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t54)))).intoArray(dest, destOffset);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t16), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _t45), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t55)))).intoArray(dest, destOffset + 4);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t15), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _t44), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t56)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] lookAt_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        float _eyez = eye[eyeOffset + 2];
        float _eyex = eye[eyeOffset];
        float _eyey = eye[eyeOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _t0 = center[centerOffset + 2] - _eyez;
        float _t1 = center[centerOffset] - _eyex;
        float _t2 = center[centerOffset + 1] - _eyey;
        float _t9 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
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
        float _t23 = -((_upz) * (_t15) + (((_upx) * (_t14) + (_upy * _t16))));
        float _t24 = ((_t23) * (_t14) + (_upx));
        float _t25 = ((_t23) * (_t16) + (_upy));
        float _t26 = ((_t23) * (_t15) + (_upz));
        float _t33 = ((_t24) * (_t16) - (_t25 * _t14));
        float _t34 = ((_t25) * (_t15) - (_t26 * _t16));
        float _t35 = ((_t26) * (_t14) - (_t24 * _t15));
        float _t38 = ((_t33) * (_t33) + (((_t34) * (_t34) + (_t35 * _t35))));
        return lookAt_lh_mulAdd_sa38e3030_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t14, _t15, _t16, _t33, _t34, _t35, _t38, (1.0f / (float) java.lang.Math.sqrt(_t38)));
    }

    /** Piece 2 of {@code lookAt_lh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_mulAdd_sa38e3030_1(float[] dest, int destOffset, float[] src, int srcOffset, float _eyez, float _eyex, float _eyey, float _upz, float _upx, float _upy, float _t14, float _t15, float _t16, float _t33, float _t34, float _t35, float _t38, float _t39) {
        float _t43, _t44, _t45;
        if (_t38 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0f;
            _t44 = 0.0f;
            _t45 = 0.0f;
        }
        float _t54 = ((_t44) * (_t16) - (_t45 * _t15));
        float _t55 = ((_t43) * (_t15) - (_t44 * _t14));
        float _t56 = ((_t45) * (_t14) - (_t43 * _t16));
        lookAt_lh_mulAdd_s91fa99d2_v(dest, destOffset, src, srcOffset, _t14, _t15, _t16, _t43, _t44, _t45, _t54, _t55, _t56, -((_eyez) * (_t44) + (((_eyex) * (_t43) + (_eyey * _t45)))), -((_eyez) * (_t56) + (((_eyex) * (_t54) + (_eyey * _t55)))), -((_eyez) * (_t15) + (((_eyex) * (_t14) + (_eyey * _t16)))));
        return dest;
    }

    public static float[] lookAt_rh(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        if (SimdSupport.USE_FMA) return lookAt_rh_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return lookAt_rh_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static float[] lookAt_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        float _eyez = eye[eyeOffset + 2];
        float _eyex = eye[eyeOffset];
        float _eyey = eye[eyeOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _t4 = center[centerOffset + 2] - _eyez;
        float _t5 = center[centerOffset] - _eyex;
        float _t6 = center[centerOffset + 1] - _eyey;
        float _t13 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t13));
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
        float _t27 = -java.lang.Math.fma(_upz, _t19, java.lang.Math.fma(_upx, _t18, _upy * _t20));
        float _t28 = java.lang.Math.fma(_t27, _t20, _upy);
        float _t29 = java.lang.Math.fma(_t27, _t18, _upx);
        float _t30 = java.lang.Math.fma(_t27, _t19, _upz);
        float _t37 = java.lang.Math.fma(_t28, _t18, -(_t29 * _t20));
        float _t38 = java.lang.Math.fma(_t29, _t19, -(_t30 * _t18));
        float _t39 = java.lang.Math.fma(_t30, _t20, -(_t28 * _t19));
        return lookAt_rh_fma_sf72dfd3b_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t18, _t19, _t20, _t37, _t38, _t39, java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t39 * _t39)));
    }

    /** Piece 2 of {@code lookAt_rh_fma}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_fma_sf72dfd3b_1(float[] dest, int destOffset, float[] src, int srcOffset, float _eyez, float _eyex, float _eyey, float _upz, float _upx, float _upy, float _t18, float _t19, float _t20, float _t37, float _t38, float _t39, float _t42) {
        float _t43 = (1.0f / (float) java.lang.Math.sqrt(_t42));
        float _t47, _t48, _t49;
        if (_t42 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0f;
            _t48 = 0.0f;
            _t49 = 0.0f;
        }
        float _t58 = java.lang.Math.fma(_t48, _t19, -(_t49 * _t20));
        float _t59 = java.lang.Math.fma(_t49, _t18, -(_t47 * _t19));
        float _t60 = java.lang.Math.fma(_t47, _t20, -(_t48 * _t18));
        lookAt_rh_fma_sf51a6345_v(dest, destOffset, src, srcOffset, _t18, _t19, _t20, _t47, _t48, _t49, _t58, _t59, _t60, -src[srcOffset + 8], -src[srcOffset + 9], -src[srcOffset + 10], -src[srcOffset + 11], -java.lang.Math.fma(_eyez, _t49, java.lang.Math.fma(_eyex, _t47, _eyey * _t48)), -java.lang.Math.fma(_eyez, _t60, java.lang.Math.fma(_eyex, _t58, _eyey * _t59)), java.lang.Math.fma(_eyez, _t19, java.lang.Math.fma(_eyex, _t18, _eyey * _t20)));
        return dest;
    }

    public static float[] lookAt_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        float _eyez = eye[eyeOffset + 2];
        float _eyex = eye[eyeOffset];
        float _eyey = eye[eyeOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _t4 = center[centerOffset + 2] - _eyez;
        float _t5 = center[centerOffset] - _eyex;
        float _t6 = center[centerOffset + 1] - _eyey;
        float _t13 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t13));
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
        float _t27 = -((_upz) * (_t19) + (((_upx) * (_t18) + (_upy * _t20))));
        float _t28 = ((_t27) * (_t20) + (_upy));
        float _t29 = ((_t27) * (_t18) + (_upx));
        float _t30 = ((_t27) * (_t19) + (_upz));
        float _t37 = ((_t28) * (_t18) - (_t29 * _t20));
        float _t38 = ((_t29) * (_t19) - (_t30 * _t18));
        float _t39 = ((_t30) * (_t20) - (_t28 * _t19));
        float _t42 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t39 * _t39))));
        return lookAt_rh_mulAdd_sa4019602_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t18, _t19, _t20, _t37, _t38, _t39, _t42, (1.0f / (float) java.lang.Math.sqrt(_t42)));
    }

    /** Piece 2 of {@code lookAt_rh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_mulAdd_sa4019602_1(float[] dest, int destOffset, float[] src, int srcOffset, float _eyez, float _eyex, float _eyey, float _upz, float _upx, float _upy, float _t18, float _t19, float _t20, float _t37, float _t38, float _t39, float _t42, float _t43) {
        float _t47, _t48, _t49;
        if (_t42 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0f;
            _t48 = 0.0f;
            _t49 = 0.0f;
        }
        float _t58 = ((_t48) * (_t19) - (_t49 * _t20));
        float _t59 = ((_t49) * (_t18) - (_t47 * _t19));
        float _t60 = ((_t47) * (_t20) - (_t48 * _t18));
        lookAt_rh_mulAdd_se6ecb5dc_v(dest, destOffset, src, srcOffset, _t18, _t19, _t20, _t47, _t48, _t49, _t58, _t59, _t60, -src[srcOffset + 8], -src[srcOffset + 9], -src[srcOffset + 10], -src[srcOffset + 11], -((_eyez) * (_t49) + (((_eyex) * (_t47) + (_eyey * _t48)))), -((_eyez) * (_t60) + (((_eyex) * (_t58) + (_eyey * _t59)))), ((_eyez) * (_t19) + (((_eyex) * (_t18) + (_eyey * _t20)))));
        return dest;
    }

    public static float[] lookAt_lh(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ, float epsilon) {
        if (SimdSupport.USE_FMA) return lookAt_lh_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
        return lookAt_lh_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
    }

    public static float[] lookAt_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ, float epsilon) {
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
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
        float _t23 = -java.lang.Math.fma(upZ, _t15, java.lang.Math.fma(upX, _t14, upY * _t16));
        float _t24 = java.lang.Math.fma(_t23, _t14, upX);
        float _t25 = java.lang.Math.fma(_t23, _t16, upY);
        float _t26 = java.lang.Math.fma(_t23, _t15, upZ);
        float _t33 = java.lang.Math.fma(_t24, _t16, -(_t25 * _t14));
        float _t34 = java.lang.Math.fma(_t25, _t15, -(_t26 * _t16));
        float _t35 = java.lang.Math.fma(_t26, _t14, -(_t24 * _t15));
        float _t38 = java.lang.Math.fma(_t33, _t33, java.lang.Math.fma(_t34, _t34, _t35 * _t35));
        return lookAt_lh_fma_sde0e3939_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, epsilon, _t14, _t15, _t16, _t33, _t34, _t35, _t38, (1.0f / (float) java.lang.Math.sqrt(_t38)));
    }

    /** Piece 2 of {@code lookAt_lh_fma}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_fma_sde0e3939_1(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float epsilon, float _t14, float _t15, float _t16, float _t33, float _t34, float _t35, float _t38, float _t39) {
        float _t43, _t44, _t45;
        if (_t38 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0f;
            _t44 = 0.0f;
            _t45 = 0.0f;
        }
        lookAt_lh_fma_s7dab4453_v(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t14, _t15, _t16, _t43, _t44, _t45, java.lang.Math.fma(_t44, _t16, -(_t45 * _t15)), java.lang.Math.fma(_t43, _t15, -(_t44 * _t14)), java.lang.Math.fma(_t45, _t14, -(_t43 * _t16)));
        return dest;
    }

    public static float[] lookAt_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ, float epsilon) {
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
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
        float _t23 = -((upZ) * (_t15) + (((upX) * (_t14) + (upY * _t16))));
        float _t24 = ((_t23) * (_t14) + (upX));
        float _t25 = ((_t23) * (_t16) + (upY));
        float _t26 = ((_t23) * (_t15) + (upZ));
        float _t33 = ((_t24) * (_t16) - (_t25 * _t14));
        float _t34 = ((_t25) * (_t15) - (_t26 * _t16));
        float _t35 = ((_t26) * (_t14) - (_t24 * _t15));
        float _t38 = ((_t33) * (_t33) + (((_t34) * (_t34) + (_t35 * _t35))));
        float _t39 = (1.0f / (float) java.lang.Math.sqrt(_t38));
        float _t43, _t44, _t45;
        if (_t38 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0f;
            _t44 = 0.0f;
            _t45 = 0.0f;
        }
        return lookAt_lh_mulAdd_s5238face_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t14, _t15, _t16, _t43, _t44, _t45);
    }

    /** Piece 2 of {@code lookAt_lh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_mulAdd_s5238face_1(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float _t14, float _t15, float _t16, float _t43, float _t44, float _t45) {
        float _t54 = ((_t44) * (_t16) - (_t45 * _t15));
        float _t55 = ((_t43) * (_t15) - (_t44 * _t14));
        float _t56 = ((_t45) * (_t14) - (_t43 * _t16));
        lookAt_lh_mulAdd_s91fa99d2_v(dest, destOffset, src, srcOffset, _t14, _t15, _t16, _t43, _t44, _t45, _t54, _t55, _t56, -((eyeZ) * (_t44) + (((eyeX) * (_t43) + (eyeY * _t45)))), -((eyeZ) * (_t56) + (((eyeX) * (_t54) + (eyeY * _t55)))), -((eyeZ) * (_t15) + (((eyeX) * (_t14) + (eyeY * _t16)))));
        return dest;
    }

    public static float[] lookAt_rh(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ, float epsilon) {
        if (SimdSupport.USE_FMA) return lookAt_rh_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
        return lookAt_rh_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ, epsilon);
    }

    public static float[] lookAt_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = centerZ - eyeZ;
        float _t5 = centerX - eyeX;
        float _t6 = centerY - eyeY;
        float _t13 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t13));
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
        float _t27 = -java.lang.Math.fma(upZ, _t19, java.lang.Math.fma(upX, _t18, upY * _t20));
        float _t28 = java.lang.Math.fma(_t27, _t20, upY);
        float _t29 = java.lang.Math.fma(_t27, _t18, upX);
        float _t30 = java.lang.Math.fma(_t27, _t19, upZ);
        float _t37 = java.lang.Math.fma(_t28, _t18, -(_t29 * _t20));
        float _t38 = java.lang.Math.fma(_t29, _t19, -(_t30 * _t18));
        float _t39 = java.lang.Math.fma(_t30, _t20, -(_t28 * _t19));
        float _t42 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t39 * _t39));
        return lookAt_rh_fma_s5956c4b_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, epsilon, _t18, _t19, _t20, _t37, _t38, _t39, _t42, (1.0f / (float) java.lang.Math.sqrt(_t42)));
    }

    /** Piece 2 of {@code lookAt_rh_fma}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_fma_s5956c4b_1(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float epsilon, float _t18, float _t19, float _t20, float _t37, float _t38, float _t39, float _t42, float _t43) {
        float _t47, _t48, _t49;
        if (_t42 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0f;
            _t48 = 0.0f;
            _t49 = 0.0f;
        }
        float _t58 = java.lang.Math.fma(_t48, _t19, -(_t49 * _t20));
        float _t59 = java.lang.Math.fma(_t49, _t18, -(_t47 * _t19));
        float _t60 = java.lang.Math.fma(_t47, _t20, -(_t48 * _t18));
        lookAt_rh_fma_sf51a6345_v(dest, destOffset, src, srcOffset, _t18, _t19, _t20, _t47, _t48, _t49, _t58, _t59, _t60, -src[srcOffset + 8], -src[srcOffset + 9], -src[srcOffset + 10], -src[srcOffset + 11], -java.lang.Math.fma(eyeZ, _t49, java.lang.Math.fma(eyeX, _t47, eyeY * _t48)), -java.lang.Math.fma(eyeZ, _t60, java.lang.Math.fma(eyeX, _t58, eyeY * _t59)), java.lang.Math.fma(eyeZ, _t19, java.lang.Math.fma(eyeX, _t18, eyeY * _t20)));
        return dest;
    }

    public static float[] lookAt_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = centerZ - eyeZ;
        float _t5 = centerX - eyeX;
        float _t6 = centerY - eyeY;
        float _t13 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t13));
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
        float _t27 = -((upZ) * (_t19) + (((upX) * (_t18) + (upY * _t20))));
        float _t28 = ((_t27) * (_t20) + (upY));
        float _t29 = ((_t27) * (_t18) + (upX));
        float _t30 = ((_t27) * (_t19) + (upZ));
        float _t37 = ((_t28) * (_t18) - (_t29 * _t20));
        float _t38 = ((_t29) * (_t19) - (_t30 * _t18));
        float _t39 = ((_t30) * (_t20) - (_t28 * _t19));
        float _t42 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t39 * _t39))));
        float _t43 = (1.0f / (float) java.lang.Math.sqrt(_t42));
        float _t47, _t48, _t49;
        if (_t42 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0f;
            _t48 = 0.0f;
            _t49 = 0.0f;
        }
        return lookAt_rh_mulAdd_scfd07d60_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t18, _t19, _t20, _t47, _t48, _t49);
    }

    /** Piece 2 of {@code lookAt_rh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_mulAdd_scfd07d60_1(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float _t18, float _t19, float _t20, float _t47, float _t48, float _t49) {
        float _t58 = ((_t48) * (_t19) - (_t49 * _t20));
        float _t59 = ((_t49) * (_t18) - (_t47 * _t19));
        float _t60 = ((_t47) * (_t20) - (_t48 * _t18));
        lookAt_rh_mulAdd_se6ecb5dc_v(dest, destOffset, src, srcOffset, _t18, _t19, _t20, _t47, _t48, _t49, _t58, _t59, _t60, -src[srcOffset + 8], -src[srcOffset + 9], -src[srcOffset + 10], -src[srcOffset + 11], -((eyeZ) * (_t49) + (((eyeX) * (_t47) + (eyeY * _t48)))), -((eyeZ) * (_t60) + (((eyeX) * (_t58) + (eyeY * _t59)))), ((eyeZ) * (_t19) + (((eyeX) * (_t18) + (eyeY * _t20)))));
        return dest;
    }

    public static float[] lookAt_lh(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset, float epsilon) {
        if (SimdSupport.USE_FMA) return lookAt_lh_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset, epsilon);
        return lookAt_lh_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset, epsilon);
    }

    public static float[] lookAt_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset, float epsilon) {
        float _eyez = eye[eyeOffset + 2];
        float _eyex = eye[eyeOffset];
        float _eyey = eye[eyeOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _t0 = center[centerOffset + 2] - _eyez;
        float _t1 = center[centerOffset] - _eyex;
        float _t2 = center[centerOffset + 1] - _eyey;
        float _t9 = java.lang.Math.fma(_t0, _t0, java.lang.Math.fma(_t1, _t1, _t2 * _t2));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
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
        float _t23 = -java.lang.Math.fma(_upz, _t15, java.lang.Math.fma(_upx, _t14, _upy * _t16));
        float _t24 = java.lang.Math.fma(_t23, _t14, _upx);
        float _t25 = java.lang.Math.fma(_t23, _t16, _upy);
        float _t26 = java.lang.Math.fma(_t23, _t15, _upz);
        return lookAt_lh_fma_seaaa9087_1(dest, destOffset, src, srcOffset, epsilon, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t14, _t15, _t16, java.lang.Math.fma(_t24, _t16, -(_t25 * _t14)), java.lang.Math.fma(_t25, _t15, -(_t26 * _t16)), java.lang.Math.fma(_t26, _t14, -(_t24 * _t15)));
    }

    /** Piece 2 of {@code lookAt_lh_fma}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_fma_seaaa9087_1(float[] dest, int destOffset, float[] src, int srcOffset, float epsilon, float _eyez, float _eyex, float _eyey, float _upz, float _upx, float _upy, float _t14, float _t15, float _t16, float _t33, float _t34, float _t35) {
        float _t38 = java.lang.Math.fma(_t33, _t33, java.lang.Math.fma(_t34, _t34, _t35 * _t35));
        float _t39 = (1.0f / (float) java.lang.Math.sqrt(_t38));
        float _t43, _t44, _t45;
        if (_t38 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0f;
            _t44 = 0.0f;
            _t45 = 0.0f;
        }
        lookAt_lh_fma_sadc67aa1_v(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _t14, _t15, _t16, _t43, _t44, _t45, java.lang.Math.fma(_t44, _t16, -(_t45 * _t15)), java.lang.Math.fma(_t43, _t15, -(_t44 * _t14)), java.lang.Math.fma(_t45, _t14, -(_t43 * _t16)));
        return dest;
    }

    public static float[] lookAt_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset, float epsilon) {
        float _eyez = eye[eyeOffset + 2];
        float _eyex = eye[eyeOffset];
        float _eyey = eye[eyeOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _t0 = center[centerOffset + 2] - _eyez;
        float _t1 = center[centerOffset] - _eyex;
        float _t2 = center[centerOffset + 1] - _eyey;
        float _t9 = ((_t0) * (_t0) + (((_t1) * (_t1) + (_t2 * _t2))));
        float _t10 = (1.0f / (float) java.lang.Math.sqrt(_t9));
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
        float _t23 = -((_upz) * (_t15) + (((_upx) * (_t14) + (_upy * _t16))));
        float _t24 = ((_t23) * (_t14) + (_upx));
        float _t25 = ((_t23) * (_t16) + (_upy));
        float _t26 = ((_t23) * (_t15) + (_upz));
        float _t33 = ((_t24) * (_t16) - (_t25 * _t14));
        float _t34 = ((_t25) * (_t15) - (_t26 * _t16));
        float _t35 = ((_t26) * (_t14) - (_t24 * _t15));
        float _t38 = ((_t33) * (_t33) + (((_t34) * (_t34) + (_t35 * _t35))));
        return lookAt_lh_mulAdd_s999a2f04_1(dest, destOffset, src, srcOffset, epsilon, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t14, _t15, _t16, _t33, _t34, _t35, _t38, (1.0f / (float) java.lang.Math.sqrt(_t38)));
    }

    /** Piece 2 of {@code lookAt_lh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_mulAdd_s999a2f04_1(float[] dest, int destOffset, float[] src, int srcOffset, float epsilon, float _eyez, float _eyex, float _eyey, float _upz, float _upx, float _upy, float _t14, float _t15, float _t16, float _t33, float _t34, float _t35, float _t38, float _t39) {
        float _t43, _t44, _t45;
        if (_t38 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0f;
            _t44 = 0.0f;
            _t45 = 0.0f;
        }
        float _t54 = ((_t44) * (_t16) - (_t45 * _t15));
        float _t55 = ((_t43) * (_t15) - (_t44 * _t14));
        float _t56 = ((_t45) * (_t14) - (_t43 * _t16));
        lookAt_lh_mulAdd_s91fa99d2_v(dest, destOffset, src, srcOffset, _t14, _t15, _t16, _t43, _t44, _t45, _t54, _t55, _t56, -((_eyez) * (_t44) + (((_eyex) * (_t43) + (_eyey * _t45)))), -((_eyez) * (_t56) + (((_eyex) * (_t54) + (_eyey * _t55)))), -((_eyez) * (_t15) + (((_eyex) * (_t14) + (_eyey * _t16)))));
        return dest;
    }

    public static float[] lookAt_rh(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset, float epsilon) {
        if (SimdSupport.USE_FMA) return lookAt_rh_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset, epsilon);
        return lookAt_rh_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset, epsilon);
    }

    public static float[] lookAt_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset, float epsilon) {
        float _eyez = eye[eyeOffset + 2];
        float _eyex = eye[eyeOffset];
        float _eyey = eye[eyeOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _t4 = center[centerOffset + 2] - _eyez;
        float _t5 = center[centerOffset] - _eyex;
        float _t6 = center[centerOffset + 1] - _eyey;
        float _t13 = java.lang.Math.fma(_t4, _t4, java.lang.Math.fma(_t5, _t5, _t6 * _t6));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t13));
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
        float _t27 = -java.lang.Math.fma(_upz, _t19, java.lang.Math.fma(_upx, _t18, _upy * _t20));
        float _t28 = java.lang.Math.fma(_t27, _t20, _upy);
        float _t29 = java.lang.Math.fma(_t27, _t18, _upx);
        float _t30 = java.lang.Math.fma(_t27, _t19, _upz);
        return lookAt_rh_fma_sff30de1_1(dest, destOffset, src, srcOffset, epsilon, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t18, _t19, _t20, java.lang.Math.fma(_t28, _t18, -(_t29 * _t20)), java.lang.Math.fma(_t29, _t19, -(_t30 * _t18)), java.lang.Math.fma(_t30, _t20, -(_t28 * _t19)));
    }

    /** Piece 2 of {@code lookAt_rh_fma}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_fma_sff30de1_1(float[] dest, int destOffset, float[] src, int srcOffset, float epsilon, float _eyez, float _eyex, float _eyey, float _upz, float _upx, float _upy, float _t18, float _t19, float _t20, float _t37, float _t38, float _t39) {
        float _t42 = java.lang.Math.fma(_t37, _t37, java.lang.Math.fma(_t38, _t38, _t39 * _t39));
        float _t43 = (1.0f / (float) java.lang.Math.sqrt(_t42));
        float _t47, _t48, _t49;
        if (_t42 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0f;
            _t48 = 0.0f;
            _t49 = 0.0f;
        }
        float _t58 = java.lang.Math.fma(_t48, _t19, -(_t49 * _t20));
        float _t59 = java.lang.Math.fma(_t49, _t18, -(_t47 * _t19));
        float _t60 = java.lang.Math.fma(_t47, _t20, -(_t48 * _t18));
        lookAt_rh_fma_sf51a6345_v(dest, destOffset, src, srcOffset, _t18, _t19, _t20, _t47, _t48, _t49, _t58, _t59, _t60, -src[srcOffset + 8], -src[srcOffset + 9], -src[srcOffset + 10], -src[srcOffset + 11], -java.lang.Math.fma(_eyez, _t49, java.lang.Math.fma(_eyex, _t47, _eyey * _t48)), -java.lang.Math.fma(_eyez, _t60, java.lang.Math.fma(_eyex, _t58, _eyey * _t59)), java.lang.Math.fma(_eyez, _t19, java.lang.Math.fma(_eyex, _t18, _eyey * _t20)));
        return dest;
    }

    public static float[] lookAt_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset, float epsilon) {
        float _eyez = eye[eyeOffset + 2];
        float _eyex = eye[eyeOffset];
        float _eyey = eye[eyeOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _t4 = center[centerOffset + 2] - _eyez;
        float _t5 = center[centerOffset] - _eyex;
        float _t6 = center[centerOffset + 1] - _eyey;
        float _t13 = ((_t4) * (_t4) + (((_t5) * (_t5) + (_t6 * _t6))));
        float _t14 = (1.0f / (float) java.lang.Math.sqrt(_t13));
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
        float _t27 = -((_upz) * (_t19) + (((_upx) * (_t18) + (_upy * _t20))));
        float _t28 = ((_t27) * (_t20) + (_upy));
        float _t29 = ((_t27) * (_t18) + (_upx));
        float _t30 = ((_t27) * (_t19) + (_upz));
        float _t37 = ((_t28) * (_t18) - (_t29 * _t20));
        float _t38 = ((_t29) * (_t19) - (_t30 * _t18));
        float _t39 = ((_t30) * (_t20) - (_t28 * _t19));
        float _t42 = ((_t37) * (_t37) + (((_t38) * (_t38) + (_t39 * _t39))));
        return lookAt_rh_mulAdd_sab77c77e_1(dest, destOffset, src, srcOffset, epsilon, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t18, _t19, _t20, _t37, _t38, _t39, _t42, (1.0f / (float) java.lang.Math.sqrt(_t42)));
    }

    /** Piece 2 of {@code lookAt_rh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_mulAdd_sab77c77e_1(float[] dest, int destOffset, float[] src, int srcOffset, float epsilon, float _eyez, float _eyex, float _eyey, float _upz, float _upx, float _upy, float _t18, float _t19, float _t20, float _t37, float _t38, float _t39, float _t42, float _t43) {
        float _t47, _t48, _t49;
        if (_t42 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t47 = _t39 * _t43;
            _t48 = _t38 * _t43;
            _t49 = _t37 * _t43;
        } else {
            _t47 = 0.0f;
            _t48 = 0.0f;
            _t49 = 0.0f;
        }
        float _t58 = ((_t48) * (_t19) - (_t49 * _t20));
        float _t59 = ((_t49) * (_t18) - (_t47 * _t19));
        float _t60 = ((_t47) * (_t20) - (_t48 * _t18));
        lookAt_rh_mulAdd_se6ecb5dc_v(dest, destOffset, src, srcOffset, _t18, _t19, _t20, _t47, _t48, _t49, _t58, _t59, _t60, -src[srcOffset + 8], -src[srcOffset + 9], -src[srcOffset + 10], -src[srcOffset + 11], -((_eyez) * (_t49) + (((_eyex) * (_t47) + (_eyey * _t48)))), -((_eyez) * (_t60) + (((_eyex) * (_t58) + (_eyey * _t59)))), ((_eyez) * (_t19) + (((_eyex) * (_t18) + (_eyey * _t20)))));
        return dest;
    }

    public static float[] makeMappingXYnZ(float[] dest, int destOffset) {
        float[] _DATA_1 = DATA_1;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_1, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_1, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_1, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_1, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_1, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_1, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_1, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingXZY(float[] dest, int destOffset) {
        float[] _DATA_2 = DATA_2;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_2, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_2, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_2, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_2, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_2, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_2, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_2, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingXZnY(float[] dest, int destOffset) {
        float[] _DATA_3 = DATA_3;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_3, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_3, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_3, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_3, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_3, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_3, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_3, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingXnYZ(float[] dest, int destOffset) {
        float[] _DATA_4 = DATA_4;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_4, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_4, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_4, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_4, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_4, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_4, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_4, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingXnYnZ(float[] dest, int destOffset) {
        float[] _DATA_5 = DATA_5;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_5, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_5, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_5, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_5, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_5, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_5, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_5, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingXnZY(float[] dest, int destOffset) {
        float[] _DATA_6 = DATA_6;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_6, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_6, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_6, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_6, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_6, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_6, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_6, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingXnZnY(float[] dest, int destOffset) {
        float[] _DATA_7 = DATA_7;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_7, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_7, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_7, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_7, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_7, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_7, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_7, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingYXZ(float[] dest, int destOffset) {
        float[] _DATA_8 = DATA_8;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_8, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_8, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_8, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_8, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_8, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_8, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_8, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingYXnZ(float[] dest, int destOffset) {
        float[] _DATA_9 = DATA_9;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_9, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_9, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_9, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_9, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_9, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_9, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_9, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingYZX(float[] dest, int destOffset) {
        float[] _DATA_10 = DATA_10;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_10, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_10, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_10, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_10, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_10, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_10, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_10, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingYZnX(float[] dest, int destOffset) {
        float[] _DATA_11 = DATA_11;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_11, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_11, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_11, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_11, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_11, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_11, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_11, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingYnXZ(float[] dest, int destOffset) {
        float[] _DATA_12 = DATA_12;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_12, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_12, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_12, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_12, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_12, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_12, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_12, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingYnXnZ(float[] dest, int destOffset) {
        float[] _DATA_13 = DATA_13;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_13, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_13, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_13, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_13, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_13, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_13, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_13, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingYnZX(float[] dest, int destOffset) {
        float[] _DATA_14 = DATA_14;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_14, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_14, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_14, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_14, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_14, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_14, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_14, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingYnZnX(float[] dest, int destOffset) {
        float[] _DATA_15 = DATA_15;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_15, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_15, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_15, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_15, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_15, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_15, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_15, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingZXY(float[] dest, int destOffset) {
        float[] _DATA_16 = DATA_16;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_16, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_16, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_16, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_16, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_16, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_16, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_16, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingZXnY(float[] dest, int destOffset) {
        float[] _DATA_17 = DATA_17;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_17, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_17, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_17, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_17, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_17, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_17, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_17, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingZYX(float[] dest, int destOffset) {
        float[] _DATA_18 = DATA_18;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_18, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_18, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_18, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_18, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_18, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_18, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_18, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingZYnX(float[] dest, int destOffset) {
        float[] _DATA_19 = DATA_19;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_19, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_19, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_19, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_19, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_19, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_19, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_19, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingZnXY(float[] dest, int destOffset) {
        float[] _DATA_20 = DATA_20;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_20, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_20, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_20, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_20, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_20, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_20, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_20, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingZnXnY(float[] dest, int destOffset) {
        float[] _DATA_21 = DATA_21;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_21, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_21, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_21, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_21, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_21, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_21, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_21, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingZnYX(float[] dest, int destOffset) {
        float[] _DATA_22 = DATA_22;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_22, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_22, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_22, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_22, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_22, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_22, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_22, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingZnYnX(float[] dest, int destOffset) {
        float[] _DATA_23 = DATA_23;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_23, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_23, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_23, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_23, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_23, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_23, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_23, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingnXYZ(float[] dest, int destOffset) {
        float[] _DATA_24 = DATA_24;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_24, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_24, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_24, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_24, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_24, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_24, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_24, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingnXYnZ(float[] dest, int destOffset) {
        float[] _DATA_25 = DATA_25;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_25, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_25, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_25, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_25, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_25, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_25, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_25, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingnXZY(float[] dest, int destOffset) {
        float[] _DATA_26 = DATA_26;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_26, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_26, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_26, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_26, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_26, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_26, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_26, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingnXZnY(float[] dest, int destOffset) {
        float[] _DATA_27 = DATA_27;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_27, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_27, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_27, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_27, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_27, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_27, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_27, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingnXnYZ(float[] dest, int destOffset) {
        float[] _DATA_28 = DATA_28;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_28, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_28, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_28, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_28, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_28, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_28, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_28, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingnXnYnZ(float[] dest, int destOffset) {
        float[] _DATA_29 = DATA_29;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_29, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_29, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_29, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_29, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_29, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_29, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_29, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingnXnZY(float[] dest, int destOffset) {
        float[] _DATA_30 = DATA_30;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_30, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_30, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_30, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_30, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_30, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_30, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_30, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingnXnZnY(float[] dest, int destOffset) {
        float[] _DATA_31 = DATA_31;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_31, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_31, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_31, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_31, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_31, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_31, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_31, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingnYXZ(float[] dest, int destOffset) {
        float[] _DATA_32 = DATA_32;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_32, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_32, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_32, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_32, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_32, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_32, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_32, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingnYXnZ(float[] dest, int destOffset) {
        float[] _DATA_33 = DATA_33;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_33, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_33, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_33, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_33, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_33, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_33, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_33, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingnYZX(float[] dest, int destOffset) {
        float[] _DATA_34 = DATA_34;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_34, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_34, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_34, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_34, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_34, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_34, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_34, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingnYZnX(float[] dest, int destOffset) {
        float[] _DATA_35 = DATA_35;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_35, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_35, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_35, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_35, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_35, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_35, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_35, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingnYnXZ(float[] dest, int destOffset) {
        float[] _DATA_36 = DATA_36;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_36, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_36, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_36, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_36, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_36, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_36, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_36, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingnYnXnZ(float[] dest, int destOffset) {
        float[] _DATA_37 = DATA_37;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_37, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_37, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_37, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_37, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_37, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_37, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_37, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingnYnZX(float[] dest, int destOffset) {
        float[] _DATA_38 = DATA_38;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_38, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_38, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_38, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_38, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_38, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_38, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_38, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingnYnZnX(float[] dest, int destOffset) {
        float[] _DATA_39 = DATA_39;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_39, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_39, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_39, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_39, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_39, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_39, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_39, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingnZXY(float[] dest, int destOffset) {
        float[] _DATA_40 = DATA_40;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_40, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_40, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_40, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_40, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_40, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_40, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_40, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingnZXnY(float[] dest, int destOffset) {
        float[] _DATA_41 = DATA_41;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_41, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_41, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_41, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_41, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_41, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_41, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_41, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingnZYX(float[] dest, int destOffset) {
        float[] _DATA_42 = DATA_42;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_42, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_42, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_42, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_42, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_42, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_42, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_42, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingnZYnX(float[] dest, int destOffset) {
        float[] _DATA_43 = DATA_43;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_43, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_43, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_43, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_43, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_43, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_43, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_43, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingnZnXY(float[] dest, int destOffset) {
        float[] _DATA_44 = DATA_44;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_44, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_44, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_44, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_44, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_44, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_44, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_44, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingnZnXnY(float[] dest, int destOffset) {
        float[] _DATA_45 = DATA_45;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_45, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_45, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_45, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_45, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_45, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_45, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_45, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingnZnYX(float[] dest, int destOffset) {
        float[] _DATA_46 = DATA_46;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_46, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_46, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_46, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_46, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_46, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_46, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_46, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] makeMappingnZnYnX(float[] dest, int destOffset) {
        float[] _DATA_47 = DATA_47;
        jdk.incubator.vector.VectorSpecies<Float> _SPECIES_128 = FloatVector.SPECIES_128;
        if (PREFERRED_LANES >= 16) {
            FloatVector.fromArray(FloatVector.SPECIES_512, _DATA_47, 0).intoArray(dest, destOffset);
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_47, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, _DATA_47, 8).intoArray(dest, destOffset + 8);
        } else {
            FloatVector.fromArray(_SPECIES_128, _DATA_47, 0).intoArray(dest, destOffset);
            FloatVector.fromArray(_SPECIES_128, _DATA_47, 4).intoArray(dest, destOffset + 4);
            FloatVector.fromArray(_SPECIES_128, _DATA_47, 8).intoArray(dest, destOffset + 8);
            FloatVector.fromArray(_SPECIES_128, _DATA_47, 12).intoArray(dest, destOffset + 12);
        }
        return dest;
    }

    public static float[] mapXYZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        for (int _li = 0; _li < 4; _li++) {
            FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] mapXYnZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapXZY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapXZnY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapXnYZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapXnYnZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapXnZY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapXnZnY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapYXZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapYXnZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapYZX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapYZnX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapYnXZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapYnXnZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapYnZX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapYnZnX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapZXY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapZXnY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapZYX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapZYnX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapZnXY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapZnXnY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapZnYX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapZnYnX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnXYZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnXYnZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnXZY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnXZnY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnXnYZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnXnYnZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnXnZY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnXnZnY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnYXZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnYXnZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnYZX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnYZnX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnYnXZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnYnXnZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnYnZX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnYnZnX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnZXY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnZXnY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnZYX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnZYnX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnZnXY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnZnXnY(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnZnYX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg().intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mapnZnYnX(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).neg();
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).neg().intoArray(dest, destOffset);
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
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, -(0.5f * Math.cosWithSin(angle))), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, -(0.5f * Math.sinWithCos(angle))), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8)));
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.intoArray(dest, destOffset);
        _sv1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] obliqueCabinet_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, -(0.5f * Math.cosWithSin(angle)))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, -(0.5f * Math.sinWithCos(angle)))).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8)));
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.intoArray(dest, destOffset);
        _sv1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] obliqueCavalier(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        if (SimdSupport.USE_FMA) return obliqueCavalier_fma(dest, destOffset, src, srcOffset, angle);
        return obliqueCavalier_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static float[] obliqueCavalier_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, -Math.cosWithSin(angle)), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, -Math.sinWithCos(angle)), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8)));
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.intoArray(dest, destOffset);
        _sv1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] obliqueCavalier_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, -Math.cosWithSin(angle))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, -Math.sinWithCos(angle))).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8)));
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.intoArray(dest, destOffset);
        _sv1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] obliqueMilitary(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        if (SimdSupport.USE_FMA) return obliqueMilitary_fma(dest, destOffset, src, srcOffset, angle);
        return obliqueMilitary_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static float[] obliqueMilitary_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, Math.cosWithSin(angle)), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, Math.sinWithCos(angle)), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).neg()));
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _sv1.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] obliqueMilitary_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, Math.cosWithSin(angle))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, Math.sinWithCos(angle))).sub(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8)));
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _sv1.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] obliquePlanometric(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        if (SimdSupport.USE_FMA) return obliquePlanometric_fma(dest, destOffset, src, srcOffset, angle);
        return obliquePlanometric_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static float[] obliquePlanometric_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(_SIMD_SPECIES, Math.sinWithCos(angle));
        var _c1 = _sv1.add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8));
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t0), _sv1.mul(_sv2)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _sv0.fma(_sv2, _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, -_t0))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] obliquePlanometric_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t0 = Math.cosWithSin(angle);
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(_SIMD_SPECIES, Math.sinWithCos(angle));
        var _c1 = _sv1.add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8));
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t0)).add(_sv1.mul(_sv2)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _sv0.mul(_sv2).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, -_t0))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] obliqueZ_no_lh(float[] dest, int destOffset, float[] src, int srcOffset, float planeX, float planeY, float planeZ, float planeW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return obliqueZ_no_lh_fma(dest, destOffset, src, srcOffset, planeX, planeY, planeZ, planeW);
        return obliqueZ_no_lh_mulAdd(dest, destOffset, src, srcOffset, planeX, planeY, planeZ, planeW);
    }

    /** {@code obliqueZ_no_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] obliqueZ_no_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float planeX, float planeY, float planeZ, float planeW) {
        float _self23 = src[srcOffset + 14];
        float _t0 = _self23 + _self23;
        float _t13 = planeZ + (planeX * ((planeX < 0.0f ? -1.0f : planeX > 0.0f ? 1.0f : 0.0f) - src[srcOffset + 8]) / src[srcOffset] + planeY * ((planeY < 0.0f ? -1.0f : planeY > 0.0f ? 1.0f : 0.0f) - src[srcOffset + 9]) / src[srcOffset + 5]);
        float _t14 = _self23 * _t13;
        float _t18_inv = 1.0f / (java.lang.Math.fma(planeW, 1.0f - src[srcOffset + 10], _t14) + java.lang.Math.fma(_self23, _t13, -_t14));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, planeY * _t0 * _t18_inv - src[srcOffset + 7]);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, planeZ * _t0 * _t18_inv - src[srcOffset + 11]);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, planeW * _t0 * _t18_inv - src[srcOffset + 15]);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, planeX * _t0 * _t18_inv - src[srcOffset + 3]).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    /** {@code obliqueZ_no_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] obliqueZ_no_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float planeX, float planeY, float planeZ, float planeW) {
        float _self23 = src[srcOffset + 14];
        float _t0 = _self23 + _self23;
        float _t13 = planeZ + (planeX * ((planeX < 0.0f ? -1.0f : planeX > 0.0f ? 1.0f : 0.0f) - src[srcOffset + 8]) / src[srcOffset] + planeY * ((planeY < 0.0f ? -1.0f : planeY > 0.0f ? 1.0f : 0.0f) - src[srcOffset + 9]) / src[srcOffset + 5]);
        float _t14 = _self23 * _t13;
        float _t18_inv = 1.0f / (((planeW) * (1.0f - src[srcOffset + 10]) + (_t14)) + ((_self23) * (_t13) - (_t14)));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, planeY * _t0 * _t18_inv - src[srcOffset + 7]);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, planeZ * _t0 * _t18_inv - src[srcOffset + 11]);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, planeW * _t0 * _t18_inv - src[srcOffset + 15]);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, planeX * _t0 * _t18_inv - src[srcOffset + 3]).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] obliqueZ_no_rh(float[] dest, int destOffset, float[] src, int srcOffset, float planeX, float planeY, float planeZ, float planeW) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return obliqueZ_no_rh_fma(dest, destOffset, src, srcOffset, planeX, planeY, planeZ, planeW);
        return obliqueZ_no_rh_mulAdd(dest, destOffset, src, srcOffset, planeX, planeY, planeZ, planeW);
    }

    /** {@code obliqueZ_no_rh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] obliqueZ_no_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float planeX, float planeY, float planeZ, float planeW) {
        float _self23 = src[srcOffset + 14];
        float _t0 = _self23 + _self23;
        float _t13 = planeX * (src[srcOffset + 8] + (planeX < 0.0f ? -1.0f : planeX > 0.0f ? 1.0f : 0.0f)) / src[srcOffset] + planeY * (src[srcOffset + 9] + (planeY < 0.0f ? -1.0f : planeY > 0.0f ? 1.0f : 0.0f)) / src[srcOffset + 5] - planeZ;
        float _t14 = _self23 * _t13;
        float _t18_inv = 1.0f / (java.lang.Math.fma(planeW, 1.0f + src[srcOffset + 10], _t14) + java.lang.Math.fma(_self23, _t13, -_t14));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, planeY * _t0 * _t18_inv - src[srcOffset + 7]);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, planeZ * _t0 * _t18_inv - src[srcOffset + 11]);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, planeW * _t0 * _t18_inv - src[srcOffset + 15]);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, planeX * _t0 * _t18_inv - src[srcOffset + 3]).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    /** {@code obliqueZ_no_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] obliqueZ_no_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float planeX, float planeY, float planeZ, float planeW) {
        float _self23 = src[srcOffset + 14];
        float _t0 = _self23 + _self23;
        float _t13 = planeX * (src[srcOffset + 8] + (planeX < 0.0f ? -1.0f : planeX > 0.0f ? 1.0f : 0.0f)) / src[srcOffset] + planeY * (src[srcOffset + 9] + (planeY < 0.0f ? -1.0f : planeY > 0.0f ? 1.0f : 0.0f)) / src[srcOffset + 5] - planeZ;
        float _t14 = _self23 * _t13;
        float _t18_inv = 1.0f / (((planeW) * (1.0f + src[srcOffset + 10]) + (_t14)) + ((_self23) * (_t13) - (_t14)));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, planeY * _t0 * _t18_inv - src[srcOffset + 7]);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, planeZ * _t0 * _t18_inv - src[srcOffset + 11]);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, planeW * _t0 * _t18_inv - src[srcOffset + 15]);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, planeX * _t0 * _t18_inv - src[srcOffset + 3]).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] obliqueZ_zo_lh(float[] dest, int destOffset, float[] src, int srcOffset, float planeX, float planeY, float planeZ, float planeW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return obliqueZ_zo_lh_mulAdd(dest, destOffset, src, srcOffset, planeX, planeY, planeZ, planeW);
        float _self23 = src[srcOffset + 14];
        float _t12 = planeZ + (planeX * ((planeX < 0.0f ? -1.0f : planeX > 0.0f ? 1.0f : 0.0f) - src[srcOffset + 8]) / src[srcOffset] + planeY * ((planeY < 0.0f ? -1.0f : planeY > 0.0f ? 1.0f : 0.0f) - src[srcOffset + 9]) / src[srcOffset + 5]);
        float _t13 = _self23 * _t12;
        float _t17_inv = 1.0f / (java.lang.Math.fma(planeW, 1.0f - src[srcOffset + 10], _t13) + java.lang.Math.fma(_self23, _t12, -_t13));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, planeY * _self23 * _t17_inv);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, planeZ * _self23 * _t17_inv);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, planeW * _self23 * _t17_inv);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, planeX * _self23 * _t17_inv).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    /** {@code obliqueZ_zo_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] obliqueZ_zo_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float planeX, float planeY, float planeZ, float planeW) {
        float _self23 = src[srcOffset + 14];
        float _t12 = planeZ + (planeX * ((planeX < 0.0f ? -1.0f : planeX > 0.0f ? 1.0f : 0.0f) - src[srcOffset + 8]) / src[srcOffset] + planeY * ((planeY < 0.0f ? -1.0f : planeY > 0.0f ? 1.0f : 0.0f) - src[srcOffset + 9]) / src[srcOffset + 5]);
        float _t13 = _self23 * _t12;
        float _t17_inv = 1.0f / (((planeW) * (1.0f - src[srcOffset + 10]) + (_t13)) + ((_self23) * (_t12) - (_t13)));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, planeY * _self23 * _t17_inv);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, planeZ * _self23 * _t17_inv);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, planeW * _self23 * _t17_inv);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, planeX * _self23 * _t17_inv).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] obliqueZ_zo_rh(float[] dest, int destOffset, float[] src, int srcOffset, float planeX, float planeY, float planeZ, float planeW) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return obliqueZ_zo_rh_mulAdd(dest, destOffset, src, srcOffset, planeX, planeY, planeZ, planeW);
        float _self23 = src[srcOffset + 14];
        float _t12 = planeX * (src[srcOffset + 8] + (planeX < 0.0f ? -1.0f : planeX > 0.0f ? 1.0f : 0.0f)) / src[srcOffset] + planeY * (src[srcOffset + 9] + (planeY < 0.0f ? -1.0f : planeY > 0.0f ? 1.0f : 0.0f)) / src[srcOffset + 5] - planeZ;
        float _t13 = _self23 * _t12;
        float _t17_inv = 1.0f / (java.lang.Math.fma(planeW, 1.0f + src[srcOffset + 10], _t13) + java.lang.Math.fma(_self23, _t12, -_t13));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, planeY * _self23 * _t17_inv);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, planeZ * _self23 * _t17_inv);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, planeW * _self23 * _t17_inv);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, planeX * _self23 * _t17_inv).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    /** {@code obliqueZ_zo_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] obliqueZ_zo_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float planeX, float planeY, float planeZ, float planeW) {
        float _self23 = src[srcOffset + 14];
        float _t12 = planeX * (src[srcOffset + 8] + (planeX < 0.0f ? -1.0f : planeX > 0.0f ? 1.0f : 0.0f)) / src[srcOffset] + planeY * (src[srcOffset + 9] + (planeY < 0.0f ? -1.0f : planeY > 0.0f ? 1.0f : 0.0f)) / src[srcOffset + 5] - planeZ;
        float _t13 = _self23 * _t12;
        float _t17_inv = 1.0f / (((planeW) * (1.0f + src[srcOffset + 10]) + (_t13)) + ((_self23) * (_t12) - (_t13)));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, planeY * _self23 * _t17_inv);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, planeZ * _self23 * _t17_inv);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, planeW * _self23 * _t17_inv);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, planeX * _self23 * _t17_inv).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] obliqueZ_no_lh(float[] dest, int destOffset, float[] src, int srcOffset, float[] plane, int planeOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return obliqueZ_no_lh_fma(dest, destOffset, src, srcOffset, plane, planeOffset);
        return obliqueZ_no_lh_mulAdd(dest, destOffset, src, srcOffset, plane, planeOffset);
    }

    /** {@code obliqueZ_no_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] obliqueZ_no_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] plane, int planeOffset) {
        float _planex = plane[planeOffset];
        float _planey = plane[planeOffset + 1];
        float _planez = plane[planeOffset + 2];
        float _planew = plane[planeOffset + 3];
        float _self23 = src[srcOffset + 14];
        float _t13 = _planez + (_planex * ((_planex < 0.0f ? -1.0f : _planex > 0.0f ? 1.0f : 0.0f) - src[srcOffset + 8]) / src[srcOffset] + _planey * ((_planey < 0.0f ? -1.0f : _planey > 0.0f ? 1.0f : 0.0f) - src[srcOffset + 9]) / src[srcOffset + 5]);
        float _t14 = _self23 * _t13;
        obliqueZ_no_lh_fma_v672c8304(dest, destOffset, src, srcOffset, _planex, _planey, _planez, _planew, _self23 + _self23, 1.0f / (java.lang.Math.fma(_planew, 1.0f - src[srcOffset + 10], _t14) + java.lang.Math.fma(_self23, _t13, -_t14)));
        return dest;
    }

    private static void obliqueZ_no_lh_fma_v672c8304(float[] dest, int destOffset, float[] src, int srcOffset, float _planex, float _planey, float _planez, float _planew, float _t0, float _t18_inv) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, _planey * _t0 * _t18_inv - src[srcOffset + 7]);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _planez * _t0 * _t18_inv - src[srcOffset + 11]);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _planew * _t0 * _t18_inv - src[srcOffset + 15]);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, _planex * _t0 * _t18_inv - src[srcOffset + 3]).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    /** {@code obliqueZ_no_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] obliqueZ_no_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] plane, int planeOffset) {
        float _planex = plane[planeOffset];
        float _planey = plane[planeOffset + 1];
        float _planez = plane[planeOffset + 2];
        float _planew = plane[planeOffset + 3];
        float _self23 = src[srcOffset + 14];
        float _t13 = _planez + (_planex * ((_planex < 0.0f ? -1.0f : _planex > 0.0f ? 1.0f : 0.0f) - src[srcOffset + 8]) / src[srcOffset] + _planey * ((_planey < 0.0f ? -1.0f : _planey > 0.0f ? 1.0f : 0.0f) - src[srcOffset + 9]) / src[srcOffset + 5]);
        float _t14 = _self23 * _t13;
        obliqueZ_no_lh_mulAdd_v47dcd7cd(dest, destOffset, src, srcOffset, _planex, _planey, _planez, _planew, _self23 + _self23, 1.0f / (((_planew) * (1.0f - src[srcOffset + 10]) + (_t14)) + ((_self23) * (_t13) - (_t14))));
        return dest;
    }

    private static void obliqueZ_no_lh_mulAdd_v47dcd7cd(float[] dest, int destOffset, float[] src, int srcOffset, float _planex, float _planey, float _planez, float _planew, float _t0, float _t18_inv) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, _planey * _t0 * _t18_inv - src[srcOffset + 7]);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _planez * _t0 * _t18_inv - src[srcOffset + 11]);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _planew * _t0 * _t18_inv - src[srcOffset + 15]);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, _planex * _t0 * _t18_inv - src[srcOffset + 3]).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] obliqueZ_no_rh(float[] dest, int destOffset, float[] src, int srcOffset, float[] plane, int planeOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return obliqueZ_no_rh_fma(dest, destOffset, src, srcOffset, plane, planeOffset);
        return obliqueZ_no_rh_mulAdd(dest, destOffset, src, srcOffset, plane, planeOffset);
    }

    /** {@code obliqueZ_no_rh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] obliqueZ_no_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] plane, int planeOffset) {
        float _planex = plane[planeOffset];
        float _planey = plane[planeOffset + 1];
        float _planez = plane[planeOffset + 2];
        float _planew = plane[planeOffset + 3];
        float _self23 = src[srcOffset + 14];
        float _t13 = _planex * (src[srcOffset + 8] + (_planex < 0.0f ? -1.0f : _planex > 0.0f ? 1.0f : 0.0f)) / src[srcOffset] + _planey * (src[srcOffset + 9] + (_planey < 0.0f ? -1.0f : _planey > 0.0f ? 1.0f : 0.0f)) / src[srcOffset + 5] - _planez;
        float _t14 = _self23 * _t13;
        obliqueZ_no_rh_fma_v62a8a62(dest, destOffset, src, srcOffset, _planex, _planey, _planez, _planew, _self23 + _self23, 1.0f / (java.lang.Math.fma(_planew, 1.0f + src[srcOffset + 10], _t14) + java.lang.Math.fma(_self23, _t13, -_t14)));
        return dest;
    }

    private static void obliqueZ_no_rh_fma_v62a8a62(float[] dest, int destOffset, float[] src, int srcOffset, float _planex, float _planey, float _planez, float _planew, float _t0, float _t18_inv) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, _planey * _t0 * _t18_inv - src[srcOffset + 7]);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _planez * _t0 * _t18_inv - src[srcOffset + 11]);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _planew * _t0 * _t18_inv - src[srcOffset + 15]);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, _planex * _t0 * _t18_inv - src[srcOffset + 3]).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    /** {@code obliqueZ_no_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] obliqueZ_no_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] plane, int planeOffset) {
        float _planex = plane[planeOffset];
        float _planey = plane[planeOffset + 1];
        float _planez = plane[planeOffset + 2];
        float _planew = plane[planeOffset + 3];
        float _self23 = src[srcOffset + 14];
        float _t13 = _planex * (src[srcOffset + 8] + (_planex < 0.0f ? -1.0f : _planex > 0.0f ? 1.0f : 0.0f)) / src[srcOffset] + _planey * (src[srcOffset + 9] + (_planey < 0.0f ? -1.0f : _planey > 0.0f ? 1.0f : 0.0f)) / src[srcOffset + 5] - _planez;
        float _t14 = _self23 * _t13;
        obliqueZ_no_rh_mulAdd_vcf25671b(dest, destOffset, src, srcOffset, _planex, _planey, _planez, _planew, _self23 + _self23, 1.0f / (((_planew) * (1.0f + src[srcOffset + 10]) + (_t14)) + ((_self23) * (_t13) - (_t14))));
        return dest;
    }

    private static void obliqueZ_no_rh_mulAdd_vcf25671b(float[] dest, int destOffset, float[] src, int srcOffset, float _planex, float _planey, float _planez, float _planew, float _t0, float _t18_inv) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, _planey * _t0 * _t18_inv - src[srcOffset + 7]);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _planez * _t0 * _t18_inv - src[srcOffset + 11]);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _planew * _t0 * _t18_inv - src[srcOffset + 15]);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, _planex * _t0 * _t18_inv - src[srcOffset + 3]).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] obliqueZ_zo_lh(float[] dest, int destOffset, float[] src, int srcOffset, float[] plane, int planeOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return obliqueZ_zo_lh_fma(dest, destOffset, src, srcOffset, plane, planeOffset);
        return obliqueZ_zo_lh_mulAdd(dest, destOffset, src, srcOffset, plane, planeOffset);
    }

    /** {@code obliqueZ_zo_lh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] obliqueZ_zo_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] plane, int planeOffset) {
        float _planex = plane[planeOffset];
        float _self23 = src[srcOffset + 14];
        float _planey = plane[planeOffset + 1];
        float _planez = plane[planeOffset + 2];
        float _planew = plane[planeOffset + 3];
        float _t12 = _planez + (_planex * ((_planex < 0.0f ? -1.0f : _planex > 0.0f ? 1.0f : 0.0f) - src[srcOffset + 8]) / src[srcOffset] + _planey * ((_planey < 0.0f ? -1.0f : _planey > 0.0f ? 1.0f : 0.0f) - src[srcOffset + 9]) / src[srcOffset + 5]);
        float _t13 = _self23 * _t12;
        float _t17_inv = 1.0f / (java.lang.Math.fma(_planew, 1.0f - src[srcOffset + 10], _t13) + java.lang.Math.fma(_self23, _t12, -_t13));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, _planey * _self23 * _t17_inv);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _planez * _self23 * _t17_inv);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _planew * _self23 * _t17_inv);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, _planex * _self23 * _t17_inv).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    /** {@code obliqueZ_zo_lh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] obliqueZ_zo_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] plane, int planeOffset) {
        float _planex = plane[planeOffset];
        float _self23 = src[srcOffset + 14];
        float _planey = plane[planeOffset + 1];
        float _planez = plane[planeOffset + 2];
        float _planew = plane[planeOffset + 3];
        float _t12 = _planez + (_planex * ((_planex < 0.0f ? -1.0f : _planex > 0.0f ? 1.0f : 0.0f) - src[srcOffset + 8]) / src[srcOffset] + _planey * ((_planey < 0.0f ? -1.0f : _planey > 0.0f ? 1.0f : 0.0f) - src[srcOffset + 9]) / src[srcOffset + 5]);
        float _t13 = _self23 * _t12;
        float _t17_inv = 1.0f / (((_planew) * (1.0f - src[srcOffset + 10]) + (_t13)) + ((_self23) * (_t12) - (_t13)));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, _planey * _self23 * _t17_inv);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _planez * _self23 * _t17_inv);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _planew * _self23 * _t17_inv);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, _planex * _self23 * _t17_inv).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] obliqueZ_zo_rh(float[] dest, int destOffset, float[] src, int srcOffset, float[] plane, int planeOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return obliqueZ_zo_rh_fma(dest, destOffset, src, srcOffset, plane, planeOffset);
        return obliqueZ_zo_rh_mulAdd(dest, destOffset, src, srcOffset, plane, planeOffset);
    }

    /** {@code obliqueZ_zo_rh} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] obliqueZ_zo_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] plane, int planeOffset) {
        float _planex = plane[planeOffset];
        float _self23 = src[srcOffset + 14];
        float _planey = plane[planeOffset + 1];
        float _planez = plane[planeOffset + 2];
        float _planew = plane[planeOffset + 3];
        float _t12 = _planex * (src[srcOffset + 8] + (_planex < 0.0f ? -1.0f : _planex > 0.0f ? 1.0f : 0.0f)) / src[srcOffset] + _planey * (src[srcOffset + 9] + (_planey < 0.0f ? -1.0f : _planey > 0.0f ? 1.0f : 0.0f)) / src[srcOffset + 5] - _planez;
        float _t13 = _self23 * _t12;
        float _t17_inv = 1.0f / (java.lang.Math.fma(_planew, 1.0f + src[srcOffset + 10], _t13) + java.lang.Math.fma(_self23, _t12, -_t13));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, _planey * _self23 * _t17_inv);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _planez * _self23 * _t17_inv);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _planew * _self23 * _t17_inv);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, _planex * _self23 * _t17_inv).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    /** {@code obliqueZ_zo_rh} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] obliqueZ_zo_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] plane, int planeOffset) {
        float _planex = plane[planeOffset];
        float _self23 = src[srcOffset + 14];
        float _planey = plane[planeOffset + 1];
        float _planez = plane[planeOffset + 2];
        float _planew = plane[planeOffset + 3];
        float _t12 = _planex * (src[srcOffset + 8] + (_planex < 0.0f ? -1.0f : _planex > 0.0f ? 1.0f : 0.0f)) / src[srcOffset] + _planey * (src[srcOffset + 9] + (_planey < 0.0f ? -1.0f : _planey > 0.0f ? 1.0f : 0.0f)) / src[srcOffset + 5] - _planez;
        float _t13 = _self23 * _t12;
        float _t17_inv = 1.0f / (((_planew) * (1.0f + src[srcOffset + 10]) + (_t13)) + ((_self23) * (_t12) - (_t13)));
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(2, _planey * _self23 * _t17_inv);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _planez * _self23 * _t17_inv);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _planew * _self23 * _t17_inv);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(2, _planex * _self23 * _t17_inv).intoArray(dest, destOffset);
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
        float _t1_inv = 1.0f / (top - bottom);
        float _t2_inv = 1.0f / (zFar - zNear);
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).add(_sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, -(zFar + zNear) * _t2_inv), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv), _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, (left + right) * -_t0_inv)))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t2_inv + _t2_inv)).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho_no_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        float _t2_inv = 1.0f / (zFar - zNear);
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -(zFar + zNear) * _t2_inv)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv)).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, (left + right) * -_t0_inv)))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t2_inv + _t2_inv)).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho_no_rh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        if (SimdSupport.USE_FMA) return ortho_no_rh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return ortho_no_rh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static float[] ortho_no_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        float _t2_inv = 1.0f / (zFar - zNear);
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).add(_sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, -(zFar + zNear) * _t2_inv), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv), _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, (left + right) * -_t0_inv)))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -2.0f * _t2_inv)).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho_no_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        float _t2_inv = 1.0f / (zFar - zNear);
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -(zFar + zNear) * _t2_inv)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv)).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, (left + right) * -_t0_inv)))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -2.0f * _t2_inv)).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho_zo_lh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        if (SimdSupport.USE_FMA) return ortho_zo_lh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return ortho_zo_lh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static float[] ortho_zo_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        float _t2_inv = 1.0f / (zFar - zNear);
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).add(_sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, -zNear * _t2_inv), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv), _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, (left + right) * -_t0_inv)))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t2_inv)).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho_zo_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        float _t2_inv = 1.0f / (zFar - zNear);
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -zNear * _t2_inv)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv)).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, (left + right) * -_t0_inv)))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t2_inv)).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho_zo_rh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        if (SimdSupport.USE_FMA) return ortho_zo_rh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
        return ortho_zo_rh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top, zNear, zFar);
    }

    public static float[] ortho_zo_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        float _t2_inv = 1.0f / (zFar - zNear);
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).add(_sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, -zNear * _t2_inv), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv), _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, (left + right) * -_t0_inv)))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -_t2_inv)).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho_zo_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top, float zNear, float zFar) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        float _t2_inv = 1.0f / (zFar - zNear);
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -zNear * _t2_inv)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv)).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, (left + right) * -_t0_inv)))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -_t2_inv)).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho2D_no_lh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top) {
        if (SimdSupport.USE_FMA) return ortho2D_no_lh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top);
        return ortho2D_no_lh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top);
    }

    public static float[] ortho2D_no_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).add(_sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv), _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, (left + right) * -_t0_inv))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho2D_no_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv)).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, (left + right) * -_t0_inv))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
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
        float _t1_inv = 1.0f / (top - bottom);
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).add(_sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv), _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, (left + right) * -_t0_inv))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho2D_no_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).neg();
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv)).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, (left + right) * -_t0_inv))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho2D_zo_lh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top) {
        if (SimdSupport.USE_FMA) return ortho2D_zo_lh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top);
        return ortho2D_zo_lh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top);
    }

    public static float[] ortho2D_zo_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(_SIMD_SPECIES, 0.5f);
        var _sv3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv2.fma(_sv3, _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv), _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, -(left + right) * _t0_inv), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        _sv2.mul(_sv3).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho2D_zo_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(_SIMD_SPECIES, 0.5f);
        var _sv3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv2.mul(_sv3).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv)).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, -(left + right) * _t0_inv)).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        _sv2.mul(_sv3).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho2D_zo_rh(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top) {
        if (SimdSupport.USE_FMA) return ortho2D_zo_rh_fma(dest, destOffset, src, srcOffset, left, right, bottom, top);
        return ortho2D_zo_rh_mulAdd(dest, destOffset, src, srcOffset, left, right, bottom, top);
    }

    public static float[] ortho2D_zo_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.broadcast(_SIMD_SPECIES, 0.5f).fma(_sv2, _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv), _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, -(left + right) * _t0_inv), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        FloatVector.broadcast(_SIMD_SPECIES, -0.5f).mul(_sv2).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] ortho2D_zo_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float left, float right, float bottom, float top) {
        float _t0_inv = 1.0f / (right - left);
        float _t1_inv = 1.0f / (top - bottom);
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.broadcast(_SIMD_SPECIES, 0.5f).mul(_sv2).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, -(bottom + top) * _t1_inv)).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, -(left + right) * _t0_inv)).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t0_inv + _t0_inv)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t1_inv + _t1_inv)).intoArray(dest, destOffset + 4);
        FloatVector.broadcast(_SIMD_SPECIES, -0.5f).mul(_sv2).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspective_no_lh(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far) {
        if (SimdSupport.USE_FMA) return perspective_no_lh_fma(dest, destOffset, src, srcOffset, fovy, aspect, near, far);
        return perspective_no_lh_mulAdd(dest, destOffset, src, srcOffset, fovy, aspect, near, far);
    }

    public static float[] perspective_no_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far) {
        float _t2_inv = 1.0f / (near - far);
        float _t6 = Math.tan(0.5f * fovy);
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
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f / _t6));
        var _c2 = _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _w0), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12));
        FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f / (aspect * _t6))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspective_no_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far) {
        float _t2_inv = 1.0f / (near - far);
        float _t6 = Math.tan(0.5f * fovy);
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
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f / _t6));
        var _c2 = _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _w0)).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12));
        FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f / (aspect * _t6))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspective_no_rh(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far) {
        if (SimdSupport.USE_FMA) return perspective_no_rh_fma(dest, destOffset, src, srcOffset, fovy, aspect, near, far);
        return perspective_no_rh_mulAdd(dest, destOffset, src, srcOffset, fovy, aspect, near, far);
    }

    public static float[] perspective_no_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far) {
        float _t2_inv = 1.0f / (near - far);
        float _t6 = Math.tan(0.5f * fovy);
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
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f / _t6));
        var _c2 = _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _w0), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).neg());
        FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f / (aspect * _t6))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspective_no_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far) {
        float _t2_inv = 1.0f / (near - far);
        float _t6 = Math.tan(0.5f * fovy);
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
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f / _t6));
        var _c2 = _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _w0)).sub(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12));
        FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f / (aspect * _t6))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspective_zo_lh(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far) {
        if (SimdSupport.USE_FMA) return perspective_zo_lh_fma(dest, destOffset, src, srcOffset, fovy, aspect, near, far);
        return perspective_zo_lh_mulAdd(dest, destOffset, src, srcOffset, fovy, aspect, near, far);
    }

    public static float[] perspective_zo_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far) {
        float _t1_inv = 1.0f / (near - far);
        float _t3 = Math.tan(0.5f * fovy);
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
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f / _t3));
        var _c2 = _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _w0), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12));
        FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f / (aspect * _t3))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspective_zo_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far) {
        float _t1_inv = 1.0f / (near - far);
        float _t3 = Math.tan(0.5f * fovy);
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
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f / _t3));
        var _c2 = _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _w0)).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12));
        FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f / (aspect * _t3))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspective_zo_rh(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far) {
        if (SimdSupport.USE_FMA) return perspective_zo_rh_fma(dest, destOffset, src, srcOffset, fovy, aspect, near, far);
        return perspective_zo_rh_mulAdd(dest, destOffset, src, srcOffset, fovy, aspect, near, far);
    }

    public static float[] perspective_zo_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far) {
        float _t1_inv = 1.0f / (near - far);
        float _t3 = Math.tan(0.5f * fovy);
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
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f / _t3));
        var _c2 = _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _w0), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).neg());
        FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f / (aspect * _t3))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspective_zo_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float fovy, float aspect, float near, float far) {
        float _t1_inv = 1.0f / (near - far);
        float _t3 = Math.tan(0.5f * fovy);
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
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f / _t3));
        var _c2 = _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _w0)).sub(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12));
        FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f / (aspect * _t3))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspectiveFovRange_no_lh(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        if (SimdSupport.USE_FMA) return perspectiveFovRange_no_lh_fma(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far);
        return perspectiveFovRange_no_lh_mulAdd(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far);
    }

    public static float[] perspectiveFovRange_no_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        float _t0 = Math.tan(angleMax);
        float _t1 = Math.tan(angleMin);
        float _t3_inv = 1.0f / (near - far);
        float _t8 = _t0 - _t1;
        float _t8_inv = 1.0f / _t8;
        float _t11_inv = 1.0f / (aspect * _t8);
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
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _w0), _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, -(_t0 + _t1) * _t8_inv), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, _t11_inv + _t11_inv)).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t8_inv + _t8_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspectiveFovRange_no_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        float _t0 = Math.tan(angleMax);
        float _t1 = Math.tan(angleMin);
        float _t3_inv = 1.0f / (near - far);
        float _t8 = _t0 - _t1;
        float _t8_inv = 1.0f / _t8;
        float _t11_inv = 1.0f / (aspect * _t8);
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
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _w0)).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, -(_t0 + _t1) * _t8_inv)).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, _t11_inv + _t11_inv)).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t8_inv + _t8_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspectiveFovRange_no_rh(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        if (SimdSupport.USE_FMA) return perspectiveFovRange_no_rh_fma(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far);
        return perspectiveFovRange_no_rh_mulAdd(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far);
    }

    public static float[] perspectiveFovRange_no_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        float _t0 = Math.tan(angleMax);
        float _t1 = Math.tan(angleMin);
        float _t3_inv = 1.0f / (near - far);
        float _t8 = _t0 - _t1;
        float _t8_inv = 1.0f / _t8;
        float _t11_inv = 1.0f / (aspect * _t8);
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
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _w0), _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, (_t0 + _t1) * _t8_inv), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).neg()));
        FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, _t11_inv + _t11_inv)).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t8_inv + _t8_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspectiveFovRange_no_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        float _t0 = Math.tan(angleMax);
        float _t1 = Math.tan(angleMin);
        float _t3_inv = 1.0f / (near - far);
        float _t8 = _t0 - _t1;
        float _t8_inv = 1.0f / _t8;
        float _t11_inv = 1.0f / (aspect * _t8);
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
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _w0)).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, (_t0 + _t1) * _t8_inv)).sub(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, _t11_inv + _t11_inv)).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t8_inv + _t8_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspectiveFovRange_zo_lh(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        if (SimdSupport.USE_FMA) return perspectiveFovRange_zo_lh_fma(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far);
        return perspectiveFovRange_zo_lh_mulAdd(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far);
    }

    public static float[] perspectiveFovRange_zo_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        float _t0 = Math.tan(angleMax);
        float _t1 = Math.tan(angleMin);
        float _t2_inv = 1.0f / (near - far);
        float _t4 = _t0 - _t1;
        float _t4_inv = 1.0f / _t4;
        float _t8_inv = 1.0f / (aspect * _t4);
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
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _w0), _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, -(_t0 + _t1) * _t4_inv), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, _t8_inv + _t8_inv)).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t4_inv + _t4_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspectiveFovRange_zo_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        float _t0 = Math.tan(angleMax);
        float _t1 = Math.tan(angleMin);
        float _t2_inv = 1.0f / (near - far);
        float _t4 = _t0 - _t1;
        float _t4_inv = 1.0f / _t4;
        float _t8_inv = 1.0f / (aspect * _t4);
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
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _w0)).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, -(_t0 + _t1) * _t4_inv)).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, _t8_inv + _t8_inv)).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t4_inv + _t4_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspectiveFovRange_zo_rh(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        if (SimdSupport.USE_FMA) return perspectiveFovRange_zo_rh_fma(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far);
        return perspectiveFovRange_zo_rh_mulAdd(dest, destOffset, src, srcOffset, angleMin, angleMax, aspect, near, far);
    }

    public static float[] perspectiveFovRange_zo_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        float _t0 = Math.tan(angleMax);
        float _t1 = Math.tan(angleMin);
        float _t2_inv = 1.0f / (near - far);
        float _t4 = _t0 - _t1;
        float _t4_inv = 1.0f / _t4;
        float _t7_inv = 1.0f / (aspect * _t4);
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
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _w0), _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, (_t0 + _t1) * _t4_inv), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).neg()));
        FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, _t7_inv + _t7_inv)).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t4_inv + _t4_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspectiveFovRange_zo_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleMin, float angleMax, float aspect, float near, float far) {
        float _t0 = Math.tan(angleMax);
        float _t1 = Math.tan(angleMin);
        float _t2_inv = 1.0f / (near - far);
        float _t4 = _t0 - _t1;
        float _t4_inv = 1.0f / _t4;
        float _t7_inv = 1.0f / (aspect * _t4);
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
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _w0)).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, (_t0 + _t1) * _t4_inv)).sub(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, _t7_inv + _t7_inv)).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t4_inv + _t4_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
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
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _w0);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _w1);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
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
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _w0);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _w1);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
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
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _w0);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _w1);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
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
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(2, _w0);
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12).withLane(2, _w1);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
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
        float _t0 = Math.tan(angleRight);
        float _t1 = Math.tan(angleLeft);
        float _t2 = Math.tan(angleUp);
        float _t3 = Math.tan(angleDown);
        float _t5_inv = 1.0f / (near - far);
        float _t10_inv = 1.0f / (_t0 - _t1);
        float _t11_inv = 1.0f / (_t2 - _t3);
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
        frustum_zo_lh_fma_s5083c323_v(dest, destOffset, src, srcOffset, _w0, _w1, _t10_inv + _t10_inv, _t11_inv + _t11_inv, -(_t3 + _t2) * _t11_inv, -(_t1 + _t0) * _t10_inv);
        return dest;
    }

    public static float[] perspectiveOffCenterFov_no_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        float _t0 = Math.tan(angleRight);
        float _t1 = Math.tan(angleLeft);
        float _t2 = Math.tan(angleUp);
        float _t3 = Math.tan(angleDown);
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
        perspectiveOffCenterFov_no_lh_mulAdd_s8110bc56_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, _t3, 1.0f / (_t0 - _t1), 1.0f / (_t2 - _t3), _w0, _w1);
        return dest;
    }

    private static void perspectiveOffCenterFov_no_lh_mulAdd_s8110bc56_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _t10_inv, float _t11_inv, float _w0, float _w1) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _w0)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, -(_t3 + _t2) * _t11_inv)).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, -(_t1 + _t0) * _t10_inv)).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t10_inv + _t10_inv)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t11_inv + _t11_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
    }

    public static float[] perspectiveOffCenterFov_no_rh(float[] dest, int destOffset, float[] src, int srcOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        if (SimdSupport.USE_FMA) return perspectiveOffCenterFov_no_rh_fma(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far);
        return perspectiveOffCenterFov_no_rh_mulAdd(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far);
    }

    public static float[] perspectiveOffCenterFov_no_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        float _t0 = Math.tan(angleRight);
        float _t1 = Math.tan(angleLeft);
        float _t2 = Math.tan(angleUp);
        float _t3 = Math.tan(angleDown);
        float _t5_inv = 1.0f / (near - far);
        float _t10_inv = 1.0f / (_t0 - _t1);
        float _t11_inv = 1.0f / (_t2 - _t3);
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
        perspectiveOffCenterFov_no_rh_fma_se6deaa53_v(dest, destOffset, src, srcOffset, _w0, _w1, _t10_inv + _t10_inv, _t11_inv + _t11_inv, (_t1 + _t0) * _t10_inv, (_t3 + _t2) * _t11_inv);
        return dest;
    }

    private static void perspectiveOffCenterFov_no_rh_fma_se6deaa53_v(float[] dest, int destOffset, float[] src, int srcOffset, float _w0, float _w1, float _h0, float _h1, float _h2, float _h3) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, _w0), _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _h2), _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _h3))).sub(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _h0)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _h1)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
    }

    public static float[] perspectiveOffCenterFov_no_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        float _t0 = Math.tan(angleRight);
        float _t1 = Math.tan(angleLeft);
        float _t2 = Math.tan(angleUp);
        float _t3 = Math.tan(angleDown);
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
        perspectiveOffCenterFov_no_rh_mulAdd_sf3852118_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, _t3, 1.0f / (_t0 - _t1), 1.0f / (_t2 - _t3), _w0, _w1);
        return dest;
    }

    private static void perspectiveOffCenterFov_no_rh_mulAdd_sf3852118_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _t10_inv, float _t11_inv, float _w0, float _w1) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _w0)).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, (_t1 + _t0) * _t10_inv)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, (_t3 + _t2) * _t11_inv))).sub(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t10_inv + _t10_inv)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t11_inv + _t11_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
    }

    public static float[] perspectiveOffCenterFov_zo_lh(float[] dest, int destOffset, float[] src, int srcOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        if (SimdSupport.USE_FMA) return perspectiveOffCenterFov_zo_lh_fma(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far);
        return perspectiveOffCenterFov_zo_lh_mulAdd(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far);
    }

    public static float[] perspectiveOffCenterFov_zo_lh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        float _t0 = Math.tan(angleRight);
        float _t1 = Math.tan(angleLeft);
        float _t2 = Math.tan(angleUp);
        float _t3 = Math.tan(angleDown);
        float _t4_inv = 1.0f / (near - far);
        float _t6_inv = 1.0f / (_t0 - _t1);
        float _t7_inv = 1.0f / (_t2 - _t3);
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
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, _w0), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, -(_t3 + _t2) * _t7_inv), _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, -(_t1 + _t0) * _t6_inv), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t6_inv + _t6_inv)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t7_inv + _t7_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspectiveOffCenterFov_zo_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        float _t0 = Math.tan(angleRight);
        float _t1 = Math.tan(angleLeft);
        float _t2 = Math.tan(angleUp);
        float _t3 = Math.tan(angleDown);
        float _t4_inv = 1.0f / (near - far);
        float _t6_inv = 1.0f / (_t0 - _t1);
        float _t7_inv = 1.0f / (_t2 - _t3);
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
        perspectiveOffCenterFov_zo_lh_mulAdd_s42572aa_v(dest, destOffset, src, srcOffset, _w0, _w1, _t6_inv + _t6_inv, _t7_inv + _t7_inv, -(_t3 + _t2) * _t7_inv, -(_t1 + _t0) * _t6_inv);
        return dest;
    }

    private static void perspectiveOffCenterFov_zo_lh_mulAdd_s42572aa_v(float[] dest, int destOffset, float[] src, int srcOffset, float _w0, float _w1, float _h0, float _h1, float _h2, float _h3) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _w0)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _h2)).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _h3)).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _h0)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _h1)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
    }

    public static float[] perspectiveOffCenterFov_zo_rh(float[] dest, int destOffset, float[] src, int srcOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        if (SimdSupport.USE_FMA) return perspectiveOffCenterFov_zo_rh_fma(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far);
        return perspectiveOffCenterFov_zo_rh_mulAdd(dest, destOffset, src, srcOffset, angleLeft, angleRight, angleDown, angleUp, near, far);
    }

    public static float[] perspectiveOffCenterFov_zo_rh_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        float _t0 = Math.tan(angleRight);
        float _t1 = Math.tan(angleLeft);
        float _t2 = Math.tan(angleUp);
        float _t3 = Math.tan(angleDown);
        float _t4_inv = 1.0f / (near - far);
        float _t6_inv = 1.0f / (_t0 - _t1);
        float _t7_inv = 1.0f / (_t2 - _t3);
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
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, _w0), _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, (_t1 + _t0) * _t6_inv), _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, (_t3 + _t2) * _t7_inv))).sub(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t6_inv + _t6_inv)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t7_inv + _t7_inv)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] perspectiveOffCenterFov_zo_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleLeft, float angleRight, float angleDown, float angleUp, float near, float far) {
        float _t0 = Math.tan(angleRight);
        float _t1 = Math.tan(angleLeft);
        float _t2 = Math.tan(angleUp);
        float _t3 = Math.tan(angleDown);
        float _t4_inv = 1.0f / (near - far);
        float _t6_inv = 1.0f / (_t0 - _t1);
        float _t7_inv = 1.0f / (_t2 - _t3);
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
        perspectiveOffCenterFov_zo_rh_mulAdd_sd790d544_v(dest, destOffset, src, srcOffset, _w0, _w1, _t6_inv + _t6_inv, _t7_inv + _t7_inv, (_t1 + _t0) * _t6_inv, (_t3 + _t2) * _t7_inv);
        return dest;
    }

    private static void perspectiveOffCenterFov_zo_rh_mulAdd_sd790d544_v(float[] dest, int destOffset, float[] src, int srcOffset, float _w0, float _w1, float _h0, float _h1, float _h2, float _h3) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c2 = _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _w0)).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _h2)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _h3))).sub(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _h0)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _h1)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _w1)).intoArray(dest, destOffset + 12);
    }

    public static float[] pickMatrix(float[] dest, int destOffset, float[] src, int srcOffset, float centerX, float centerY, float deltaX, float deltaY, float vpX, float vpY, float vpW, float vpH) {
        if (SimdSupport.USE_FMA) return pickMatrix_fma(dest, destOffset, src, srcOffset, centerX, centerY, deltaX, deltaY, vpX, vpY, vpW, vpH);
        return pickMatrix_mulAdd(dest, destOffset, src, srcOffset, centerX, centerY, deltaX, deltaY, vpX, vpY, vpW, vpH);
    }

    public static float[] pickMatrix_fma(float[] dest, int destOffset, float[] src, int srcOffset, float centerX, float centerY, float deltaX, float deltaY, float vpX, float vpY, float vpW, float vpH) {
        float _rcp0 = 1.0f / deltaX;
        float _rcp1 = 1.0f / deltaY;
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).add(_sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(-2.0f, centerX - vpX, vpW) * _rcp0), _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(-2.0f, centerY - vpY, vpH) * _rcp1))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, vpW * _rcp0)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, vpH * _rcp1)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] pickMatrix_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float centerX, float centerY, float deltaX, float deltaY, float vpX, float vpY, float vpW, float vpH) {
        float _rcp0 = 1.0f / deltaX;
        float _rcp1 = 1.0f / deltaY;
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((vpW) - (2.0f) * (centerX - vpX)) * _rcp0)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, ((vpH) - (2.0f) * (centerY - vpY)) * _rcp1))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, vpW * _rcp0)).intoArray(dest, destOffset);
        _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, vpH * _rcp1)).intoArray(dest, destOffset + 4);
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
        float _t16 = java.lang.Math.fma(-rotZ, _t7, 1.0f);
        float _t18 = java.lang.Math.fma(rotZ, _t5, _t8);
        float _t21 = java.lang.Math.fma(rotY, _t5, _t10);
        float _t22 = java.lang.Math.fma(rotZ, _t6, _t11);
        float _t24 = java.lang.Math.fma(rotY, _t5, -_t10);
        float _t25 = java.lang.Math.fma(rotZ, _t6, -_t11);
        float _t26 = java.lang.Math.fma(rotZ, _t5, -_t8);
        preRotateAround_fma_s9e20ed84_v(dest, destOffset, src, srcOffset, _t18, _t21, _t22, _t24, _t25, _t26, java.lang.Math.fma(_t0, _t18, java.lang.Math.fma(pivotX, java.lang.Math.fma(rotY, _t6, _t9), -(pivotY * _t24))), java.lang.Math.fma(_t0, _t25, java.lang.Math.fma(pivotY, java.lang.Math.fma(rotX, _t5, _t9), -(pivotX * _t21))), java.lang.Math.fma(-pivotY, _t22, java.lang.Math.fma(pivotZ, java.lang.Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t26))), java.lang.Math.fma(_t3, _t5, java.lang.Math.fma(_t1, _t6, 1.0f)), java.lang.Math.fma(_t1, _t6, _t16), java.lang.Math.fma(_t3, _t5, _t16));
        return dest;
    }

    private static void preRotateAround_fma_s9e20ed84_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t18, float _t21, float _t22, float _t24, float _t25, float _t26, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv1 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _t18).withLane(1, _t25).withLane(2, _h3);
        var _sv2 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _h4).withLane(1, _t21).withLane(2, _t26);
        var _sv3 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _t24).withLane(1, _h5).withLane(2, _t22);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 3]).fma(_sv0, FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).fma(_sv1, FloatVector.broadcast(_SIMD_SPECIES, src[_lo0]).fma(_sv2, FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).fma(_sv3, FloatVector.broadcast(_SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3]))))).intoArray(dest, destOffset + _li * 4);
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
        float _t16 = ((1.0f) - (rotZ) * (_t7));
        float _t18 = ((rotZ) * (_t5) + (_t8));
        float _t21 = ((rotY) * (_t5) + (_t10));
        float _t22 = ((rotZ) * (_t6) + (_t11));
        float _t24 = ((rotY) * (_t5) - (_t10));
        float _t25 = ((rotZ) * (_t6) - (_t11));
        float _t26 = ((rotZ) * (_t5) - (_t8));
        preRotateAround_mulAdd_s842595dd_v(dest, destOffset, src, srcOffset, _t18, _t21, _t22, _t24, _t25, _t26, ((_t0) * (_t18) + (((pivotX) * (((rotY) * (_t6) + (_t9))) - (pivotY * _t24)))), ((_t0) * (_t25) + (((pivotY) * (((rotX) * (_t5) + (_t9))) - (pivotX * _t21)))), ((((pivotZ) * (((rotX) * (_t5) + (rotY * _t6))) - (pivotX * _t26))) - (pivotY) * (_t22)), ((_t3) * (_t5) + (((_t1) * (_t6) + (1.0f)))), ((_t1) * (_t6) + (_t16)), ((_t3) * (_t5) + (_t16)));
        return dest;
    }

    private static void preRotateAround_mulAdd_s842595dd_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t18, float _t21, float _t22, float _t24, float _t25, float _t26, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv1 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _t18).withLane(1, _t25).withLane(2, _h3);
        var _sv2 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _h4).withLane(1, _t21).withLane(2, _t26);
        var _sv3 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _t24).withLane(1, _h5).withLane(2, _t22);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 3]).mul(_sv0).add(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).mul(_sv1).add(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0]).mul(_sv2).add(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).mul(_sv3).add(FloatVector.broadcast(_SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3]))))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] preRotateAround(float[] dest, int destOffset, float[] src, int srcOffset, float[] rot, int rotOffset, float[] pivot, int pivotOffset) {
        if (SimdSupport.USE_FMA) return preRotateAround_fma(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
        return preRotateAround_mulAdd(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
    }

    public static float[] preRotateAround_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] rot, int rotOffset, float[] pivot, int pivotOffset) {
        float _pivotx = pivot[pivotOffset];
        float _roty = rot[rotOffset + 1];
        float _pivoty = pivot[pivotOffset + 1];
        float _rotx = rot[rotOffset];
        float _pivotz = pivot[pivotOffset + 2];
        float _rotz = rot[rotOffset + 2];
        float _rotw = rot[rotOffset + 3];
        float _t1 = -_roty;
        float _t3 = -_rotx;
        float _t5 = _rotx + _rotx;
        float _t6 = _roty + _roty;
        float _t7 = _rotz + _rotz;
        float _t8 = _rotw * _t6;
        float _t10 = _rotw * _t7;
        float _t11 = _rotw * _t5;
        float _t16 = java.lang.Math.fma(-_rotz, _t7, 1.0f);
        float _t22 = java.lang.Math.fma(_rotz, _t6, _t11);
        float _t26 = java.lang.Math.fma(_rotz, _t5, -_t8);
        preRotateAround_fma_s707d7a3d_v(dest, destOffset, src, srcOffset, _pivotx, _roty, _pivoty, _rotx, -_pivotz, _t5, _t6, _rotz * _t7, java.lang.Math.fma(_rotz, _t5, _t8), java.lang.Math.fma(_roty, _t5, _t10), _t22, java.lang.Math.fma(_roty, _t5, -_t10), java.lang.Math.fma(_rotz, _t6, -_t11), _t26, java.lang.Math.fma(-_pivoty, _t22, java.lang.Math.fma(_pivotz, java.lang.Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t26))), java.lang.Math.fma(_t3, _t5, java.lang.Math.fma(_t1, _t6, 1.0f)), java.lang.Math.fma(_t1, _t6, _t16), java.lang.Math.fma(_t3, _t5, _t16));
        return dest;
    }

    private static void preRotateAround_fma_s707d7a3d_v(float[] dest, int destOffset, float[] src, int srcOffset, float _pivotx, float _roty, float _pivoty, float _rotx, float _t0, float _t5, float _t6, float _t9, float _t18, float _t21, float _t22, float _t24, float _t25, float _t26, float _h0, float _h1, float _h2, float _h3) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_t0, _t18, java.lang.Math.fma(_pivotx, java.lang.Math.fma(_roty, _t6, _t9), -(_pivoty * _t24)))).withLane(1, java.lang.Math.fma(_t0, _t25, java.lang.Math.fma(_pivoty, java.lang.Math.fma(_rotx, _t5, _t9), -(_pivotx * _t21)))).withLane(2, _h0);
        var _sv1 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _t18).withLane(1, _t25).withLane(2, _h1);
        var _sv2 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _h2).withLane(1, _t21).withLane(2, _t26);
        var _sv3 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _t24).withLane(1, _h3).withLane(2, _t22);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 3]).fma(_sv0, FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).fma(_sv1, FloatVector.broadcast(_SIMD_SPECIES, src[_lo0]).fma(_sv2, FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).fma(_sv3, FloatVector.broadcast(_SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3]))))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] preRotateAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] rot, int rotOffset, float[] pivot, int pivotOffset) {
        float _pivotx = pivot[pivotOffset];
        float _roty = rot[rotOffset + 1];
        float _pivoty = pivot[pivotOffset + 1];
        float _rotx = rot[rotOffset];
        float _pivotz = pivot[pivotOffset + 2];
        float _rotz = rot[rotOffset + 2];
        float _rotw = rot[rotOffset + 3];
        float _t1 = -_roty;
        float _t3 = -_rotx;
        float _t5 = _rotx + _rotx;
        float _t6 = _roty + _roty;
        float _t7 = _rotz + _rotz;
        float _t8 = _rotw * _t6;
        float _t10 = _rotw * _t7;
        float _t11 = _rotw * _t5;
        float _t16 = ((1.0f) - (_rotz) * (_t7));
        float _t22 = ((_rotz) * (_t6) + (_t11));
        float _t26 = ((_rotz) * (_t5) - (_t8));
        preRotateAround_mulAdd_s62626b90_v(dest, destOffset, src, srcOffset, _pivotx, _roty, _pivoty, _rotx, -_pivotz, _t5, _t6, _rotz * _t7, ((_rotz) * (_t5) + (_t8)), ((_roty) * (_t5) + (_t10)), _t22, ((_roty) * (_t5) - (_t10)), ((_rotz) * (_t6) - (_t11)), _t26, ((((_pivotz) * (((_rotx) * (_t5) + (_roty * _t6))) - (_pivotx * _t26))) - (_pivoty) * (_t22)), ((_t3) * (_t5) + (((_t1) * (_t6) + (1.0f)))), ((_t1) * (_t6) + (_t16)), ((_t3) * (_t5) + (_t16)));
        return dest;
    }

    private static void preRotateAround_mulAdd_s62626b90_v(float[] dest, int destOffset, float[] src, int srcOffset, float _pivotx, float _roty, float _pivoty, float _rotx, float _t0, float _t5, float _t6, float _t9, float _t18, float _t21, float _t22, float _t24, float _t25, float _t26, float _h0, float _h1, float _h2, float _h3) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, ((_t0) * (_t18) + (((_pivotx) * (((_roty) * (_t6) + (_t9))) - (_pivoty * _t24))))).withLane(1, ((_t0) * (_t25) + (((_pivoty) * (((_rotx) * (_t5) + (_t9))) - (_pivotx * _t21))))).withLane(2, _h0);
        var _sv1 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _t18).withLane(1, _t25).withLane(2, _h1);
        var _sv2 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _h2).withLane(1, _t21).withLane(2, _t26);
        var _sv3 = FloatVector.zero(_SIMD_SPECIES).withLane(0, _t24).withLane(1, _h3).withLane(2, _t22);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 3]).mul(_sv0).add(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).mul(_sv1).add(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0]).mul(_sv2).add(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).mul(_sv3).add(FloatVector.broadcast(_SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3]))))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] preRotateAxis(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (SimdSupport.USE_FMA) return preRotateAxis_fma(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return preRotateAxis_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    public static float[] preRotateAxis_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t1 = Math.cosWithSin(angle);
        preRotateAxis_fma_s507f1790_v(dest, destOffset, src, srcOffset, axisX, axisY, axisZ, Math.sinWithCos(angle), _t1, 1.0f - _t1, axisX * axisZ, axisX * axisY, axisY * axisZ);
        return dest;
    }

    private static void preRotateAxis_fma_s507f1790_v(float[] dest, int destOffset, float[] src, int srcOffset, float axisX, float axisY, float axisZ, float _t0, float _t1, float _t2, float _t3, float _t5, float _t7) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(axisY, _t0, _t2 * _t3)).withLane(1, java.lang.Math.fma(_t2, _t7, -(axisX * _t0))).withLane(2, java.lang.Math.fma(_t2, axisZ * axisZ, _t1));
        var _sv1 = FloatVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_t2, axisX * axisX, _t1)).withLane(1, java.lang.Math.fma(axisZ, _t0, _t2 * _t5)).withLane(2, java.lang.Math.fma(_t2, _t3, -(axisY * _t0)));
        var _sv2 = FloatVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_t2, _t5, -(axisZ * _t0))).withLane(1, java.lang.Math.fma(_t2, axisY * axisY, _t1)).withLane(2, java.lang.Math.fma(axisX, _t0, _t2 * _t7));
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).fma(_sv0, FloatVector.broadcast(_SIMD_SPECIES, src[_lo0]).fma(_sv1, FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).fma(_sv2, FloatVector.broadcast(_SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] preRotateAxis_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t1 = Math.cosWithSin(angle);
        preRotateAxis_mulAdd_s4c8392a1_v(dest, destOffset, src, srcOffset, axisX, axisY, axisZ, Math.sinWithCos(angle), _t1, 1.0f - _t1, axisX * axisZ, axisX * axisY, axisY * axisZ);
        return dest;
    }

    private static void preRotateAxis_mulAdd_s4c8392a1_v(float[] dest, int destOffset, float[] src, int srcOffset, float axisX, float axisY, float axisZ, float _t0, float _t1, float _t2, float _t3, float _t5, float _t7) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, ((axisY) * (_t0) + (_t2 * _t3))).withLane(1, ((_t2) * (_t7) - (axisX * _t0))).withLane(2, ((_t2) * (axisZ * axisZ) + (_t1)));
        var _sv1 = FloatVector.zero(_SIMD_SPECIES).withLane(0, ((_t2) * (axisX * axisX) + (_t1))).withLane(1, ((axisZ) * (_t0) + (_t2 * _t5))).withLane(2, ((_t2) * (_t3) - (axisY * _t0)));
        var _sv2 = FloatVector.zero(_SIMD_SPECIES).withLane(0, ((_t2) * (_t5) - (axisZ * _t0))).withLane(1, ((_t2) * (axisY * axisY) + (_t1))).withLane(2, ((axisX) * (_t0) + (_t2 * _t7)));
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).mul(_sv0).add(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0]).mul(_sv1).add(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(FloatVector.broadcast(_SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] preRotateAxis(float[] dest, int destOffset, float[] src, int srcOffset, float[] axis, int axisOffset, float angle) {
        if (SimdSupport.USE_FMA) return preRotateAxis_fma(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return preRotateAxis_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    public static float[] preRotateAxis_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] axis, int axisOffset, float angle) {
        float _axisy = axis[axisOffset + 1];
        float _axisx = axis[axisOffset];
        float _axisz = axis[axisOffset + 2];
        float _t1 = Math.cosWithSin(angle);
        preRotateAxis_fma_sb08dc62e_v(dest, destOffset, src, srcOffset, _axisy, _axisx, _axisz, Math.sinWithCos(angle), _t1, 1.0f - _t1, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz);
        return dest;
    }

    private static void preRotateAxis_fma_sb08dc62e_v(float[] dest, int destOffset, float[] src, int srcOffset, float _axisy, float _axisx, float _axisz, float _t0, float _t1, float _t2, float _t3, float _t5, float _t7) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_axisy, _t0, _t2 * _t3)).withLane(1, java.lang.Math.fma(_t2, _t7, -(_axisx * _t0))).withLane(2, java.lang.Math.fma(_t2, _axisz * _axisz, _t1));
        var _sv1 = FloatVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_t2, _axisx * _axisx, _t1)).withLane(1, java.lang.Math.fma(_axisz, _t0, _t2 * _t5)).withLane(2, java.lang.Math.fma(_t2, _t3, -(_axisy * _t0)));
        var _sv2 = FloatVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_t2, _t5, -(_axisz * _t0))).withLane(1, java.lang.Math.fma(_t2, _axisy * _axisy, _t1)).withLane(2, java.lang.Math.fma(_axisx, _t0, _t2 * _t7));
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).fma(_sv0, FloatVector.broadcast(_SIMD_SPECIES, src[_lo0]).fma(_sv1, FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).fma(_sv2, FloatVector.broadcast(_SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] preRotateAxis_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] axis, int axisOffset, float angle) {
        float _axisy = axis[axisOffset + 1];
        float _axisx = axis[axisOffset];
        float _axisz = axis[axisOffset + 2];
        float _t1 = Math.cosWithSin(angle);
        preRotateAxis_mulAdd_s7a4f62b5_v(dest, destOffset, src, srcOffset, _axisy, _axisx, _axisz, Math.sinWithCos(angle), _t1, 1.0f - _t1, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz);
        return dest;
    }

    private static void preRotateAxis_mulAdd_s7a4f62b5_v(float[] dest, int destOffset, float[] src, int srcOffset, float _axisy, float _axisx, float _axisz, float _t0, float _t1, float _t2, float _t3, float _t5, float _t7) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, ((_axisy) * (_t0) + (_t2 * _t3))).withLane(1, ((_t2) * (_t7) - (_axisx * _t0))).withLane(2, ((_t2) * (_axisz * _axisz) + (_t1)));
        var _sv1 = FloatVector.zero(_SIMD_SPECIES).withLane(0, ((_t2) * (_axisx * _axisx) + (_t1))).withLane(1, ((_axisz) * (_t0) + (_t2 * _t5))).withLane(2, ((_t2) * (_t3) - (_axisy * _t0)));
        var _sv2 = FloatVector.zero(_SIMD_SPECIES).withLane(0, ((_t2) * (_t5) - (_axisz * _t0))).withLane(1, ((_t2) * (_axisy * _axisy) + (_t1))).withLane(2, ((_axisx) * (_t0) + (_t2 * _t7)));
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).mul(_sv0).add(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0]).mul(_sv1).add(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(FloatVector.broadcast(_SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
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
        preRotateQuat_fma_s63e88155_v(dest, destOffset, src, srcOffset, qY, qZ, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, java.lang.Math.fma(-qZ, _t5, 1.0f));
        return dest;
    }

    private static void preRotateQuat_fma_s63e88155_v(float[] dest, int destOffset, float[] src, int srcOffset, float qY, float qZ, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(qZ, _t3, _t6)).withLane(1, java.lang.Math.fma(qZ, _t4, -_t8)).withLane(2, java.lang.Math.fma(_t2, _t3, java.lang.Math.fma(_t0, _t4, 1.0f)));
        var _sv1 = FloatVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_t0, _t4, _t12)).withLane(1, java.lang.Math.fma(qY, _t3, _t7)).withLane(2, java.lang.Math.fma(qZ, _t3, -_t6));
        var _sv2 = FloatVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(qY, _t3, -_t7)).withLane(1, java.lang.Math.fma(_t2, _t3, _t12)).withLane(2, java.lang.Math.fma(qZ, _t4, _t8));
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).fma(_sv0, FloatVector.broadcast(_SIMD_SPECIES, src[_lo0]).fma(_sv1, FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).fma(_sv2, FloatVector.broadcast(_SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] preRotateQuat_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float qX, float qY, float qZ, float qW) {
        float _t3 = qX + qX;
        float _t4 = qY + qY;
        float _t5 = qZ + qZ;
        preRotateQuat_mulAdd_s4441ade4_v(dest, destOffset, src, srcOffset, qY, qZ, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, ((1.0f) - (qZ) * (_t5)));
        return dest;
    }

    private static void preRotateQuat_mulAdd_s4441ade4_v(float[] dest, int destOffset, float[] src, int srcOffset, float qY, float qZ, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, ((qZ) * (_t3) + (_t6))).withLane(1, ((qZ) * (_t4) - (_t8))).withLane(2, ((_t2) * (_t3) + (((_t0) * (_t4) + (1.0f)))));
        var _sv1 = FloatVector.zero(_SIMD_SPECIES).withLane(0, ((_t0) * (_t4) + (_t12))).withLane(1, ((qY) * (_t3) + (_t7))).withLane(2, ((qZ) * (_t3) - (_t6)));
        var _sv2 = FloatVector.zero(_SIMD_SPECIES).withLane(0, ((qY) * (_t3) - (_t7))).withLane(1, ((_t2) * (_t3) + (_t12))).withLane(2, ((qZ) * (_t4) + (_t8)));
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).mul(_sv0).add(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0]).mul(_sv1).add(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(FloatVector.broadcast(_SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] preRotateQuat(float[] dest, int destOffset, float[] src, int srcOffset, float[] q, int qOffset) {
        if (SimdSupport.USE_FMA) return preRotateQuat_fma(dest, destOffset, src, srcOffset, q, qOffset);
        return preRotateQuat_mulAdd(dest, destOffset, src, srcOffset, q, qOffset);
    }

    public static float[] preRotateQuat_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] q, int qOffset) {
        float _qz = q[qOffset + 2];
        float _qy = q[qOffset + 1];
        float _qx = q[qOffset];
        float _qw = q[qOffset + 3];
        float _t3 = _qx + _qx;
        float _t4 = _qy + _qy;
        float _t5 = _qz + _qz;
        preRotateQuat_fma_s59e36b6f_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, java.lang.Math.fma(-_qz, _t5, 1.0f));
        return dest;
    }

    private static void preRotateQuat_fma_s59e36b6f_v(float[] dest, int destOffset, float[] src, int srcOffset, float _qz, float _qy, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_qz, _t3, _t6)).withLane(1, java.lang.Math.fma(_qz, _t4, -_t8)).withLane(2, java.lang.Math.fma(_t2, _t3, java.lang.Math.fma(_t0, _t4, 1.0f)));
        var _sv1 = FloatVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_t0, _t4, _t12)).withLane(1, java.lang.Math.fma(_qy, _t3, _t7)).withLane(2, java.lang.Math.fma(_qz, _t3, -_t6));
        var _sv2 = FloatVector.zero(_SIMD_SPECIES).withLane(0, java.lang.Math.fma(_qy, _t3, -_t7)).withLane(1, java.lang.Math.fma(_t2, _t3, _t12)).withLane(2, java.lang.Math.fma(_qz, _t4, _t8));
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).fma(_sv0, FloatVector.broadcast(_SIMD_SPECIES, src[_lo0]).fma(_sv1, FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).fma(_sv2, FloatVector.broadcast(_SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] preRotateQuat_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] q, int qOffset) {
        float _qz = q[qOffset + 2];
        float _qy = q[qOffset + 1];
        float _qx = q[qOffset];
        float _qw = q[qOffset + 3];
        float _t3 = _qx + _qx;
        float _t4 = _qy + _qy;
        float _t5 = _qz + _qz;
        preRotateQuat_mulAdd_s4be95bce_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, ((1.0f) - (_qz) * (_t5)));
        return dest;
    }

    private static void preRotateQuat_mulAdd_s4be95bce_v(float[] dest, int destOffset, float[] src, int srcOffset, float _qz, float _qy, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, ((_qz) * (_t3) + (_t6))).withLane(1, ((_qz) * (_t4) - (_t8))).withLane(2, ((_t2) * (_t3) + (((_t0) * (_t4) + (1.0f)))));
        var _sv1 = FloatVector.zero(_SIMD_SPECIES).withLane(0, ((_t0) * (_t4) + (_t12))).withLane(1, ((_qy) * (_t3) + (_t7))).withLane(2, ((_qz) * (_t3) - (_t6)));
        var _sv2 = FloatVector.zero(_SIMD_SPECIES).withLane(0, ((_qy) * (_t3) - (_t7))).withLane(1, ((_t2) * (_t3) + (_t12))).withLane(2, ((_qz) * (_t4) + (_t8)));
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 2]).mul(_sv0).add(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0]).mul(_sv1).add(FloatVector.broadcast(_SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(FloatVector.broadcast(_SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] preScale(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        if (SimdSupport.USE_FMA) return preScale_fma(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return preScale_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    public static float[] preScale_fma(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.fromArray(SIMD_SPECIES, src, _lo0).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preScale_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.fromArray(SIMD_SPECIES, src, _lo0).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preScale(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        if (SimdSupport.USE_FMA) return preScale_fma(dest, destOffset, src, srcOffset, v, vOffset);
        return preScale_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
    }

    public static float[] preScale_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        float _vx = v[vOffset];
        float _vy = v[vOffset + 1];
        float _vz = v[vOffset + 2];
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.fromArray(SIMD_SPECIES, src, _lo0).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preScale_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        float _vx = v[vOffset];
        float _vy = v[vOffset + 1];
        float _vz = v[vOffset + 2];
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.fromArray(SIMD_SPECIES, src, _lo0).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preScale(float[] dest, int destOffset, float[] src, int srcOffset, float s) {
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, s).mul(FloatVector.fromArray(SIMD_SPECIES, src, _lo0)).withLane(3, src[_lo0 + 3]).intoArray(dest, destOffset + _li * 4);
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
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, s).fma(FloatVector.fromArray(SIMD_SPECIES, src, _lo0), FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 3]).mul(_sv0)).withLane(3, src[_lo0 + 3]).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preScaleAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        float _t0 = 1.0f - s;
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, pivotX * _t0).withLane(1, pivotY * _t0).withLane(2, pivotZ * _t0);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, s).mul(FloatVector.fromArray(SIMD_SPECIES, src, _lo0)).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 3]).mul(_sv0)).withLane(3, src[_lo0 + 3]).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preScaleAround(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float s) {
        if (SimdSupport.USE_FMA) return preScaleAround_fma(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return preScaleAround_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    public static float[] preScaleAround_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float s) {
        float _pivotx = pivot[pivotOffset];
        float _pivoty = pivot[pivotOffset + 1];
        float _pivotz = pivot[pivotOffset + 2];
        float _t0 = 1.0f - s;
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _pivotx * _t0).withLane(1, _pivoty * _t0).withLane(2, _pivotz * _t0);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, s).fma(FloatVector.fromArray(SIMD_SPECIES, src, _lo0), FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 3]).mul(_sv0)).withLane(3, src[_lo0 + 3]).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preScaleAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float s) {
        float _pivotx = pivot[pivotOffset];
        float _pivoty = pivot[pivotOffset + 1];
        float _pivotz = pivot[pivotOffset + 2];
        float _t0 = 1.0f - s;
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _pivotx * _t0).withLane(1, _pivoty * _t0).withLane(2, _pivotz * _t0);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, s).mul(FloatVector.fromArray(SIMD_SPECIES, src, _lo0)).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 3]).mul(_sv0)).withLane(3, src[_lo0 + 3]).intoArray(dest, destOffset + _li * 4);
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
            int _lo0 = srcOffset + _li * 4;
            _sv0.fma(FloatVector.fromArray(SIMD_SPECIES, src, _lo0), FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 3]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3]))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preScaleAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, sX).withLane(1, sY).withLane(2, sZ);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, pivotX * (1.0f - sX)).withLane(1, pivotY * (1.0f - sY)).withLane(2, pivotZ * (1.0f - sZ));
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, _lo0)).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 3]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3]))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preScaleAround(float[] dest, int destOffset, float[] src, int srcOffset, float[] s, int sOffset, float[] pivot, int pivotOffset) {
        if (SimdSupport.USE_FMA) return preScaleAround_fma(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return preScaleAround_mulAdd(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    public static float[] preScaleAround_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] s, int sOffset, float[] pivot, int pivotOffset) {
        float _sx = s[sOffset];
        float _sy = s[sOffset + 1];
        float _sz = s[sOffset + 2];
        float _pivotx = pivot[pivotOffset];
        float _pivoty = pivot[pivotOffset + 1];
        float _pivotz = pivot[pivotOffset + 2];
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _sx).withLane(1, _sy).withLane(2, _sz);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _pivotx * (1.0f - _sx)).withLane(1, _pivoty * (1.0f - _sy)).withLane(2, _pivotz * (1.0f - _sz));
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.fma(FloatVector.fromArray(SIMD_SPECIES, src, _lo0), FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 3]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3]))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preScaleAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] s, int sOffset, float[] pivot, int pivotOffset) {
        float _sx = s[sOffset];
        float _sy = s[sOffset + 1];
        float _sz = s[sOffset + 2];
        float _pivotx = pivot[pivotOffset];
        float _pivoty = pivot[pivotOffset + 1];
        float _pivotz = pivot[pivotOffset + 2];
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _sx).withLane(1, _sy).withLane(2, _sz);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _pivotx * (1.0f - _sx)).withLane(1, _pivoty * (1.0f - _sy)).withLane(2, _pivotz * (1.0f - _sz));
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, _lo0)).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 3]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3]))).intoArray(dest, destOffset + _li * 4);
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
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 3]).fma(_sv0, FloatVector.fromArray(SIMD_SPECIES, src, _lo0)).withLane(3, src[_lo0 + 3]).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preTranslate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 3]).mul(_sv0).add(FloatVector.fromArray(SIMD_SPECIES, src, _lo0)).withLane(3, src[_lo0 + 3]).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preTranslate(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        if (SimdSupport.USE_FMA) return preTranslate_fma(dest, destOffset, src, srcOffset, v, vOffset);
        return preTranslate_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
    }

    public static float[] preTranslate_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        float _vx = v[vOffset];
        float _vy = v[vOffset + 1];
        float _vz = v[vOffset + 2];
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 3]).fma(_sv0, FloatVector.fromArray(SIMD_SPECIES, src, _lo0)).withLane(3, src[_lo0 + 3]).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preTranslate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        float _vx = v[vOffset];
        float _vy = v[vOffset + 1];
        float _vz = v[vOffset + 2];
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 3]).mul(_sv0).add(FloatVector.fromArray(SIMD_SPECIES, src, _lo0)).withLane(3, src[_lo0 + 3]).intoArray(dest, destOffset + _li * 4);
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
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.broadcast(_SIMD_SPECIES, -((normalX + normalX) * normalY));
        var _c2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(-2.0f, normalZ * normalZ, 1.0f)), _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, -_t12), _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, -_t10))));
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t10), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(-2.0f, normalX * normalX, 1.0f)), _sv2.mul(_sv3))).intoArray(dest, destOffset);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t12), _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(-2.0f, normalY * normalY, 1.0f)), _sv1.mul(_sv3))).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] reflect_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float normalX, float normalY, float normalZ) {
        reflect_mulAdd_s88ff2b0e_v(dest, destOffset, src, srcOffset, normalX, normalY, normalZ, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], (normalX + normalX) * normalZ, (normalY + normalY) * normalZ);
        return dest;
    }

    private static void reflect_mulAdd_s88ff2b0e_v(float[] dest, int destOffset, float[] src, int srcOffset, float normalX, float normalY, float normalZ, float _self02, float _self12, float _self22, float _self32, float _t10, float _t12) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.broadcast(_SIMD_SPECIES, -((normalX + normalX) * normalY));
        var _c2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).mul(FloatVector.broadcast(_SIMD_SPECIES, ((1.0f) - (2.0f) * (normalZ * normalZ)))).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -_t12)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, -_t10))));
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t10)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, ((1.0f) - (2.0f) * (normalX * normalX)))).add(_sv2.mul(_sv3))).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t12)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((1.0f) - (2.0f) * (normalY * normalY)))).add(_sv1.mul(_sv3))).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] reflect(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        if (SimdSupport.USE_FMA) return reflect_fma(dest, destOffset, src, srcOffset, normal, normalOffset);
        return reflect_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    public static float[] reflect_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        float _normalx = normal[normalOffset];
        float _normaly = normal[normalOffset + 1];
        float _normalz = normal[normalOffset + 2];
        reflect_fma_s429546cf_v(dest, destOffset, src, srcOffset, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _normalx, _normaly, _normalz, (_normalx + _normalx) * _normalz, (_normaly + _normaly) * _normalz);
        return dest;
    }

    private static void reflect_fma_s429546cf_v(float[] dest, int destOffset, float[] src, int srcOffset, float _self02, float _self12, float _self22, float _self32, float _normalx, float _normaly, float _normalz, float _t10, float _t12) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.broadcast(_SIMD_SPECIES, -((_normalx + _normalx) * _normaly));
        var _c2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(-2.0f, _normalz * _normalz, 1.0f)), _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, -_t12), _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, -_t10))));
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t10), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(-2.0f, _normalx * _normalx, 1.0f)), _sv2.mul(_sv3))).intoArray(dest, destOffset);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t12), _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(-2.0f, _normaly * _normaly, 1.0f)), _sv1.mul(_sv3))).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] reflect_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        float _normalx = normal[normalOffset];
        float _normaly = normal[normalOffset + 1];
        float _normalz = normal[normalOffset + 2];
        reflect_mulAdd_s97e34512_v(dest, destOffset, src, srcOffset, src[srcOffset + 8], src[srcOffset + 9], src[srcOffset + 10], src[srcOffset + 11], _normalx, _normaly, _normalz, (_normalx + _normalx) * _normalz, (_normaly + _normaly) * _normalz);
        return dest;
    }

    private static void reflect_mulAdd_s97e34512_v(float[] dest, int destOffset, float[] src, int srcOffset, float _self02, float _self12, float _self22, float _self32, float _normalx, float _normaly, float _normalz, float _t10, float _t12) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.broadcast(_SIMD_SPECIES, -((_normalx + _normalx) * _normaly));
        var _c2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).mul(FloatVector.broadcast(_SIMD_SPECIES, ((1.0f) - (2.0f) * (_normalz * _normalz)))).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -_t12)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, -_t10))));
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t10)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, ((1.0f) - (2.0f) * (_normalx * _normalx)))).add(_sv2.mul(_sv3))).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t12)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((1.0f) - (2.0f) * (_normaly * _normaly)))).add(_sv1.mul(_sv3))).intoArray(dest, destOffset + 4);
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
        float _t16 = java.lang.Math.fma(-rotZ, _t7, 1.0f);
        float _t18 = java.lang.Math.fma(rotY, _t5, _t9);
        float _t19 = java.lang.Math.fma(rotZ, _t6, _t10);
        float _t20 = java.lang.Math.fma(rotZ, _t5, _t8);
        float _t24 = java.lang.Math.fma(rotZ, _t5, -_t8);
        float _t25 = java.lang.Math.fma(rotY, _t5, -_t9);
        float _t26 = java.lang.Math.fma(rotZ, _t6, -_t10);
        rotateAround_fma_s4369e68d_v(dest, destOffset, src, srcOffset, _t18, _t19, _t20, _t24, _t25, _t26, java.lang.Math.fma(_t0, _t6, _t16), java.lang.Math.fma(_t2, _t5, _t16), java.lang.Math.fma(_t2, _t5, java.lang.Math.fma(_t0, _t6, 1.0f)), java.lang.Math.fma(_t3, _t20, java.lang.Math.fma(pivotX, java.lang.Math.fma(rotY, _t6, _t11), -(pivotY * _t25))), java.lang.Math.fma(_t3, _t26, java.lang.Math.fma(pivotY, java.lang.Math.fma(rotX, _t5, _t11), -(pivotX * _t18))), java.lang.Math.fma(-pivotY, _t19, java.lang.Math.fma(pivotZ, java.lang.Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t24))));
        return dest;
    }

    private static void rotateAround_fma_s4369e68d_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t18, float _t19, float _t20, float _t24, float _t25, float _t26, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _h3), _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, _h4), _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _h5), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t24), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _h0), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t18)))).intoArray(dest, destOffset);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t19), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _t25), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _h1)))).intoArray(dest, destOffset + 4);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _h2), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _t20), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t26)))).intoArray(dest, destOffset + 8);
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
        float _t16 = ((1.0f) - (rotZ) * (_t7));
        float _t18 = ((rotY) * (_t5) + (_t9));
        float _t19 = ((rotZ) * (_t6) + (_t10));
        float _t20 = ((rotZ) * (_t5) + (_t8));
        float _t24 = ((rotZ) * (_t5) - (_t8));
        float _t25 = ((rotY) * (_t5) - (_t9));
        float _t26 = ((rotZ) * (_t6) - (_t10));
        rotateAround_mulAdd_s41feee86_v(dest, destOffset, src, srcOffset, _t18, _t19, _t20, _t24, _t25, _t26, ((_t0) * (_t6) + (_t16)), ((_t2) * (_t5) + (_t16)), ((_t2) * (_t5) + (((_t0) * (_t6) + (1.0f)))), ((_t3) * (_t20) + (((pivotX) * (((rotY) * (_t6) + (_t11))) - (pivotY * _t25)))), ((_t3) * (_t26) + (((pivotY) * (((rotX) * (_t5) + (_t11))) - (pivotX * _t18)))), ((((pivotZ) * (((rotX) * (_t5) + (rotY * _t6))) - (pivotX * _t24))) - (pivotY) * (_t19)));
        return dest;
    }

    private static void rotateAround_mulAdd_s41feee86_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t18, float _t19, float _t20, float _t24, float _t25, float _t26, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _h3)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _h4)).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _h5)).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t24)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _h0)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t18)))).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t19)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t25)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _h1)))).intoArray(dest, destOffset + 4);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _h2)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t20)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t26)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateAround(float[] dest, int destOffset, float[] src, int srcOffset, float[] rot, int rotOffset, float[] pivot, int pivotOffset) {
        if (SimdSupport.USE_FMA) return rotateAround_fma(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
        return rotateAround_mulAdd(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
    }

    public static float[] rotateAround_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] rot, int rotOffset, float[] pivot, int pivotOffset) {
        float _pivotx = pivot[pivotOffset];
        float _roty = rot[rotOffset + 1];
        float _pivoty = pivot[pivotOffset + 1];
        float _rotx = rot[rotOffset];
        float _pivotz = pivot[pivotOffset + 2];
        float _rotz = rot[rotOffset + 2];
        float _rotw = rot[rotOffset + 3];
        float _t0 = -_roty;
        float _t2 = -_rotx;
        float _t5 = _rotx + _rotx;
        float _t6 = _roty + _roty;
        float _t7 = _rotz + _rotz;
        float _t8 = _rotw * _t6;
        float _t9 = _rotw * _t7;
        float _t10 = _rotw * _t5;
        float _t16 = java.lang.Math.fma(-_rotz, _t7, 1.0f);
        float _t19 = java.lang.Math.fma(_rotz, _t6, _t10);
        float _t24 = java.lang.Math.fma(_rotz, _t5, -_t8);
        rotateAround_fma_s19b187e0_v(dest, destOffset, src, srcOffset, _pivotx, _roty, _pivoty, _rotx, -_pivotz, _t5, _t6, _rotz * _t7, java.lang.Math.fma(_roty, _t5, _t9), _t19, java.lang.Math.fma(_rotz, _t5, _t8), _t24, java.lang.Math.fma(_roty, _t5, -_t9), java.lang.Math.fma(_rotz, _t6, -_t10), java.lang.Math.fma(_t0, _t6, _t16), java.lang.Math.fma(_t2, _t5, _t16), java.lang.Math.fma(_t2, _t5, java.lang.Math.fma(_t0, _t6, 1.0f)), java.lang.Math.fma(-_pivoty, _t19, java.lang.Math.fma(_pivotz, java.lang.Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t24))));
        return dest;
    }

    private static void rotateAround_fma_s19b187e0_v(float[] dest, int destOffset, float[] src, int srcOffset, float _pivotx, float _roty, float _pivoty, float _rotx, float _t3, float _t5, float _t6, float _t11, float _t18, float _t19, float _t20, float _t24, float _t25, float _t26, float _h0, float _h1, float _h2, float _h3) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t3, _t20, java.lang.Math.fma(_pivotx, java.lang.Math.fma(_roty, _t6, _t11), -(_pivoty * _t25)))), _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t3, _t26, java.lang.Math.fma(_pivoty, java.lang.Math.fma(_rotx, _t5, _t11), -(_pivotx * _t18)))), _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _h3), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t24), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _h0), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t18)))).intoArray(dest, destOffset);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t19), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _t25), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _h1)))).intoArray(dest, destOffset + 4);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _h2), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _t20), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t26)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] rot, int rotOffset, float[] pivot, int pivotOffset) {
        float _roty = rot[rotOffset + 1];
        float _rotx = rot[rotOffset];
        float _pivotz = pivot[pivotOffset + 2];
        float _rotz = rot[rotOffset + 2];
        float _rotw = rot[rotOffset + 3];
        float _t5 = _rotx + _rotx;
        float _t6 = _roty + _roty;
        float _t7 = _rotz + _rotz;
        float _t8 = _rotw * _t6;
        float _t9 = _rotw * _t7;
        float _t10 = _rotw * _t5;
        return rotateAround_mulAdd_s89fd6e5b_1(dest, destOffset, src, srcOffset, _roty, _rotx, _pivotz, pivot[pivotOffset], pivot[pivotOffset + 1], -_roty, -_rotx, -_pivotz, _t5, _t6, _rotz * _t7, ((1.0f) - (_rotz) * (_t7)), ((_roty) * (_t5) + (_t9)), ((_rotz) * (_t6) + (_t10)), ((_rotz) * (_t5) + (_t8)), ((_rotz) * (_t5) - (_t8)), ((_roty) * (_t5) - (_t9)), ((_rotz) * (_t6) - (_t10)));
    }

    /** Piece 2 of {@code rotateAround_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float[] rotateAround_mulAdd_s89fd6e5b_1(float[] dest, int destOffset, float[] src, int srcOffset, float _roty, float _rotx, float _pivotz, float _pivotx, float _pivoty, float _t0, float _t2, float _t3, float _t5, float _t6, float _t11, float _t16, float _t18, float _t19, float _t20, float _t24, float _t25, float _t26) {
        rotateAround_mulAdd_s41feee86_v(dest, destOffset, src, srcOffset, _t18, _t19, _t20, _t24, _t25, _t26, ((_t0) * (_t6) + (_t16)), ((_t2) * (_t5) + (_t16)), ((_t2) * (_t5) + (((_t0) * (_t6) + (1.0f)))), ((_t3) * (_t20) + (((_pivotx) * (((_roty) * (_t6) + (_t11))) - (_pivoty * _t25)))), ((_t3) * (_t26) + (((_pivoty) * (((_rotx) * (_t5) + (_t11))) - (_pivotx * _t18)))), ((((_pivotz) * (((_rotx) * (_t5) + (_roty * _t6))) - (_pivotx * _t24))) - (_pivoty) * (_t19)));
        return dest;
    }

    public static float[] rotateAxis(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (SimdSupport.USE_FMA) return rotateAxis_fma(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return rotateAxis_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    public static float[] rotateAxis_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.cosWithSin(angle);
        rotateAxis_fma_s1712e579_v(dest, destOffset, src, srcOffset, axisX, axisY, axisZ, _t0, Math.sinWithCos(angle), 1.0f - _t0, axisX * axisZ, axisX * axisY, axisY * axisZ);
        return dest;
    }

    private static void rotateAxis_fma_s1712e579_v(float[] dest, int destOffset, float[] src, int srcOffset, float axisX, float axisY, float axisZ, float _t0, float _t1, float _t2, float _t3, float _t6, float _t7) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _t3, -(axisY * _t1))), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, axisX * axisX, _t0)), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(axisZ, _t1, _t2 * _t6))))).intoArray(dest, destOffset);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(axisX, _t1, _t2 * _t7)), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _t6, -(axisZ * _t1))), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, axisY * axisY, _t0))))).intoArray(dest, destOffset + 4);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, axisZ * axisZ, _t0)), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(axisY, _t1, _t2 * _t3)), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _t7, -(axisX * _t1)))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateAxis_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.cosWithSin(angle);
        rotateAxis_mulAdd_s2eafaf86_v(dest, destOffset, src, srcOffset, axisX, axisY, axisZ, _t0, Math.sinWithCos(angle), 1.0f - _t0, axisX * axisZ, axisX * axisY, axisY * axisZ);
        return dest;
    }

    private static void rotateAxis_mulAdd_s2eafaf86_v(float[] dest, int destOffset, float[] src, int srcOffset, float axisX, float axisY, float axisZ, float _t0, float _t1, float _t2, float _t3, float _t6, float _t7) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t3) - (axisY * _t1)))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t2) * (axisX * axisX) + (_t0)))).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((axisZ) * (_t1) + (_t2 * _t6)))))).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((axisX) * (_t1) + (_t2 * _t7)))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t6) - (axisZ * _t1)))).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t2) * (axisY * axisY) + (_t0)))))).intoArray(dest, destOffset + 4);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t2) * (axisZ * axisZ) + (_t0)))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, ((axisY) * (_t1) + (_t2 * _t3)))).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t7) - (axisX * _t1)))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateAxis(float[] dest, int destOffset, float[] src, int srcOffset, float[] axis, int axisOffset, float angle) {
        if (SimdSupport.USE_FMA) return rotateAxis_fma(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return rotateAxis_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    public static float[] rotateAxis_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] axis, int axisOffset, float angle) {
        float _axisy = axis[axisOffset + 1];
        float _axisx = axis[axisOffset];
        float _axisz = axis[axisOffset + 2];
        float _t0 = Math.cosWithSin(angle);
        rotateAxis_fma_s470c9c0d_v(dest, destOffset, src, srcOffset, _axisy, _axisx, _axisz, _t0, Math.sinWithCos(angle), 1.0f - _t0, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz);
        return dest;
    }

    private static void rotateAxis_fma_s470c9c0d_v(float[] dest, int destOffset, float[] src, int srcOffset, float _axisy, float _axisx, float _axisz, float _t0, float _t1, float _t2, float _t3, float _t6, float _t7) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _t3, -(_axisy * _t1))), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _axisx * _axisx, _t0)), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_axisz, _t1, _t2 * _t6))))).intoArray(dest, destOffset);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_axisx, _t1, _t2 * _t7)), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _t6, -(_axisz * _t1))), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _axisy * _axisy, _t0))))).intoArray(dest, destOffset + 4);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _axisz * _axisz, _t0)), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_axisy, _t1, _t2 * _t3)), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _t7, -(_axisx * _t1)))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateAxis_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] axis, int axisOffset, float angle) {
        float _axisy = axis[axisOffset + 1];
        float _axisx = axis[axisOffset];
        float _axisz = axis[axisOffset + 2];
        float _t0 = Math.cosWithSin(angle);
        rotateAxis_mulAdd_sed598ce0_v(dest, destOffset, src, srcOffset, _axisy, _axisx, _axisz, _t0, Math.sinWithCos(angle), 1.0f - _t0, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz);
        return dest;
    }

    private static void rotateAxis_mulAdd_sed598ce0_v(float[] dest, int destOffset, float[] src, int srcOffset, float _axisy, float _axisx, float _axisz, float _t0, float _t1, float _t2, float _t3, float _t6, float _t7) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t3) - (_axisy * _t1)))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t2) * (_axisx * _axisx) + (_t0)))).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_axisz) * (_t1) + (_t2 * _t6)))))).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_axisx) * (_t1) + (_t2 * _t7)))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t6) - (_axisz * _t1)))).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t2) * (_axisy * _axisy) + (_t0)))))).intoArray(dest, destOffset + 4);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t2) * (_axisz * _axisz) + (_t0)))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_axisy) * (_t1) + (_t2 * _t3)))).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t7) - (_axisx * _t1)))))).intoArray(dest, destOffset + 8);
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
        rotateQuat_fma_sf0d530e4_v(dest, destOffset, src, srcOffset, qY, qZ, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, java.lang.Math.fma(-qZ, _t5, 1.0f));
        return dest;
    }

    private static void rotateQuat_fma_sf0d530e4_v(float[] dest, int destOffset, float[] src, int srcOffset, float qY, float qZ, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(qZ, _t3, -_t6)), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t0, _t4, _t12)), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(qY, _t3, _t7))))).intoArray(dest, destOffset);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(qZ, _t4, _t8)), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(qY, _t3, -_t7)), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _t3, _t12))))).intoArray(dest, destOffset + 4);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _t3, java.lang.Math.fma(_t0, _t4, 1.0f))), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(qZ, _t3, _t6)), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(qZ, _t4, -_t8))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateQuat_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float qX, float qY, float qZ, float qW) {
        float _t3 = qX + qX;
        float _t4 = qY + qY;
        float _t5 = qZ + qZ;
        rotateQuat_mulAdd_sb4742b03_v(dest, destOffset, src, srcOffset, qY, qZ, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, ((1.0f) - (qZ) * (_t5)));
        return dest;
    }

    private static void rotateQuat_mulAdd_sb4742b03_v(float[] dest, int destOffset, float[] src, int srcOffset, float qY, float qZ, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((qZ) * (_t3) - (_t6)))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t0) * (_t4) + (_t12)))).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((qY) * (_t3) + (_t7)))))).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((qZ) * (_t4) + (_t8)))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, ((qY) * (_t3) - (_t7)))).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t3) + (_t12)))))).intoArray(dest, destOffset + 4);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t3) + (((_t0) * (_t4) + (1.0f)))))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, ((qZ) * (_t3) + (_t6)))).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((qZ) * (_t4) - (_t8)))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateQuat(float[] dest, int destOffset, float[] src, int srcOffset, float[] q, int qOffset) {
        if (SimdSupport.USE_FMA) return rotateQuat_fma(dest, destOffset, src, srcOffset, q, qOffset);
        return rotateQuat_mulAdd(dest, destOffset, src, srcOffset, q, qOffset);
    }

    public static float[] rotateQuat_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] q, int qOffset) {
        float _qz = q[qOffset + 2];
        float _qy = q[qOffset + 1];
        float _qx = q[qOffset];
        float _qw = q[qOffset + 3];
        float _t3 = _qx + _qx;
        float _t4 = _qy + _qy;
        float _t5 = _qz + _qz;
        rotateQuat_fma_s35461ece_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, java.lang.Math.fma(-_qz, _t5, 1.0f));
        return dest;
    }

    private static void rotateQuat_fma_s35461ece_v(float[] dest, int destOffset, float[] src, int srcOffset, float _qz, float _qy, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_qz, _t3, -_t6)), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t0, _t4, _t12)), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_qy, _t3, _t7))))).intoArray(dest, destOffset);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_qz, _t4, _t8)), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_qy, _t3, -_t7)), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _t3, _t12))))).intoArray(dest, destOffset + 4);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t2, _t3, java.lang.Math.fma(_t0, _t4, 1.0f))), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_qz, _t3, _t6)), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_qz, _t4, -_t8))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateQuat_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] q, int qOffset) {
        float _qz = q[qOffset + 2];
        float _qy = q[qOffset + 1];
        float _qx = q[qOffset];
        float _qw = q[qOffset + 3];
        float _t3 = _qx + _qx;
        float _t4 = _qy + _qy;
        float _t5 = _qz + _qz;
        rotateQuat_mulAdd_s809f1e35_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, ((1.0f) - (_qz) * (_t5)));
        return dest;
    }

    private static void rotateQuat_mulAdd_s809f1e35_v(float[] dest, int destOffset, float[] src, int srcOffset, float _qz, float _qy, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_qz) * (_t3) - (_t6)))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t0) * (_t4) + (_t12)))).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_qy) * (_t3) + (_t7)))))).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_qz) * (_t4) + (_t8)))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_qy) * (_t3) - (_t7)))).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t3) + (_t12)))))).intoArray(dest, destOffset + 4);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t3) + (((_t0) * (_t4) + (1.0f)))))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_qz) * (_t3) + (_t6)))).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_qz) * (_t4) - (_t8)))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateTowards(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (SimdSupport.USE_FMA) return rotateTowards_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return rotateTowards_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static float[] rotateTowards_fma(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
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
        float _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t12, upX);
        float _t19 = java.lang.Math.fma(_t17, _t13, upY);
        float _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        rotateTowards_fma_sc3357c25_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    private static void rotateTowards_fma_sc3357c25_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t37), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _t38), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t39)))).intoArray(dest, destOffset);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t39, _t12, -(_t38 * _t13))), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t37, _t13, -(_t39 * _t11))), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_t38, _t11, -(_t37 * _t12)))))).intoArray(dest, destOffset + 4);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t11), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _t12), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t13)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateTowards_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
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
        float _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t18 = ((_t17) * (_t12) + (upX));
        float _t19 = ((_t17) * (_t13) + (upY));
        float _t20 = ((_t17) * (_t11) + (upZ));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        rotateTowards_mulAdd_s8ff1f4e6_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    private static void rotateTowards_mulAdd_s8ff1f4e6_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t37)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t38)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t39)))).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t39) * (_t12) - (_t38 * _t13)))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t37) * (_t13) - (_t39 * _t11)))).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t38) * (_t11) - (_t37 * _t12)))))).intoArray(dest, destOffset + 4);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t11)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t12)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t13)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateTowards(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        if (SimdSupport.USE_FMA) return rotateTowards_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return rotateTowards_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static float[] rotateTowards_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        float _dirz = dir[dirOffset + 2];
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
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
        float _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t12, _upx);
        float _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        float _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return rotateTowards_fma_s49c8d8e1_1(dest, destOffset, src, srcOffset, _upz, _upx, _upy, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code rotateTowards_fma}, split to fit the inline budget; reached only through it. */
    private static float[] rotateTowards_fma_s49c8d8e1_1(float[] dest, int destOffset, float[] src, int srcOffset, float _upz, float _upx, float _upy, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.4551915E-11f, 1.1754944E-38f)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        rotateTowards_fma_sc3357c25_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static float[] rotateTowards_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        float _dirz = dir[dirOffset + 2];
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
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
        float _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t18 = ((_t17) * (_t12) + (_upx));
        float _t19 = ((_t17) * (_t13) + (_upy));
        float _t20 = ((_t17) * (_t11) + (_upz));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return rotateTowards_mulAdd_s69715cb0_1(dest, destOffset, src, srcOffset, _upz, _upx, _upy, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code rotateTowards_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float[] rotateTowards_mulAdd_s69715cb0_1(float[] dest, int destOffset, float[] src, int srcOffset, float _upz, float _upx, float _upy, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
        float _t37, _t38, _t39;
        if (_t32 > ((((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) * (1.4551915E-11f) + (1.1754944E-38f))) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        rotateTowards_mulAdd_s8ff1f4e6_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static float[] rotateTowards(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        if (SimdSupport.USE_FMA) return rotateTowards_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
        return rotateTowards_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ, epsilon);
    }

    public static float[] rotateTowards_fma(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = java.lang.Math.fma(dirZ, dirZ, java.lang.Math.fma(dirX, dirX, dirY * dirY));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
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
        float _t17 = -java.lang.Math.fma(upZ, _t11, java.lang.Math.fma(upX, _t12, upY * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t12, upX);
        float _t19 = java.lang.Math.fma(_t17, _t13, upY);
        float _t20 = java.lang.Math.fma(_t17, _t11, upZ);
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(upZ, upZ, java.lang.Math.fma(upX, upX, upY * upY)), 1.1754944E-38f)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        rotateTowards_fma_sc3357c25_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static float[] rotateTowards_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ, float epsilon) {
        float _t4 = ((dirZ) * (dirZ) + (((dirX) * (dirX) + (dirY * dirY))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
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
        float _t17 = -((upZ) * (_t11) + (((upX) * (_t12) + (upY * _t13))));
        float _t18 = ((_t17) * (_t12) + (upX));
        float _t19 = ((_t17) * (_t13) + (upY));
        float _t20 = ((_t17) * (_t11) + (upZ));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
        float _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((upZ) * (upZ) + (((upX) * (upX) + (upY * upY))))) + (1.1754944E-38f))) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        rotateTowards_mulAdd_s8ff1f4e6_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static float[] rotateTowards(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        if (SimdSupport.USE_FMA) return rotateTowards_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
        return rotateTowards_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset, epsilon);
    }

    public static float[] rotateTowards_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        float _dirz = dir[dirOffset + 2];
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _t4 = java.lang.Math.fma(_dirz, _dirz, java.lang.Math.fma(_dirx, _dirx, _diry * _diry));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
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
        float _t17 = -java.lang.Math.fma(_upz, _t11, java.lang.Math.fma(_upx, _t12, _upy * _t13));
        float _t18 = java.lang.Math.fma(_t17, _t12, _upx);
        float _t19 = java.lang.Math.fma(_t17, _t13, _upy);
        float _t20 = java.lang.Math.fma(_t17, _t11, _upz);
        float _t27 = java.lang.Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = java.lang.Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = java.lang.Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = java.lang.Math.fma(_t27, _t27, java.lang.Math.fma(_t28, _t28, _t29 * _t29));
        return rotateTowards_fma_s79e3de47_1(dest, destOffset, src, srcOffset, epsilon, _upz, _upx, _upy, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code rotateTowards_fma}, split to fit the inline budget; reached only through it. */
    private static float[] rotateTowards_fma_s79e3de47_1(float[] dest, int destOffset, float[] src, int srcOffset, float epsilon, float _upz, float _upx, float _upy, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
        float _t37, _t38, _t39;
        if (_t32 > java.lang.Math.fma(epsilon, java.lang.Math.fma(_upz, _upz, java.lang.Math.fma(_upx, _upx, _upy * _upy)), 1.1754944E-38f)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        rotateTowards_fma_sc3357c25_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static float[] rotateTowards_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset, float epsilon) {
        float _dirz = dir[dirOffset + 2];
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _t4 = ((_dirz) * (_dirz) + (((_dirx) * (_dirx) + (_diry * _diry))));
        float _t6 = (1.0f / (float) java.lang.Math.sqrt(_t4));
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
        float _t17 = -((_upz) * (_t11) + (((_upx) * (_t12) + (_upy * _t13))));
        float _t18 = ((_t17) * (_t12) + (_upx));
        float _t19 = ((_t17) * (_t13) + (_upy));
        float _t20 = ((_t17) * (_t11) + (_upz));
        float _t27 = ((_t18) * (_t13) - (_t19 * _t12));
        float _t28 = ((_t19) * (_t11) - (_t20 * _t13));
        float _t29 = ((_t20) * (_t12) - (_t18 * _t11));
        float _t32 = ((_t27) * (_t27) + (((_t28) * (_t28) + (_t29 * _t29))));
        return rotateTowards_mulAdd_s84978084_1(dest, destOffset, src, srcOffset, epsilon, _upz, _upx, _upy, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code rotateTowards_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float[] rotateTowards_mulAdd_s84978084_1(float[] dest, int destOffset, float[] src, int srcOffset, float epsilon, float _upz, float _upx, float _upy, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
        float _t37, _t38, _t39;
        if (_t32 > ((epsilon) * (((_upz) * (_upz) + (((_upx) * (_upx) + (_upy * _upy))))) + (1.1754944E-38f))) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0f;
            _t38 = 0.0f;
            _t39 = 0.0f;
        }
        rotateTowards_mulAdd_s8ff1f4e6_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static float[] rotateX(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        if (SimdSupport.USE_FMA) return rotateX_fma(dest, destOffset, src, srcOffset, angle);
        return rotateX_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static float[] rotateX_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t1 = Math.sinWithCos(angle);
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = FloatVector.broadcast(_SIMD_SPECIES, Math.cosWithSin(angle));
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
        _sv0.fma(_sv1, _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t1))).intoArray(dest, destOffset + 4);
        _sv2.fma(_sv1, _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, -_t1))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] rotateX_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t1 = Math.sinWithCos(angle);
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = FloatVector.broadcast(_SIMD_SPECIES, Math.cosWithSin(angle));
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
        _sv0.mul(_sv1).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t1))).intoArray(dest, destOffset + 4);
        _sv2.mul(_sv1).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, -_t1))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] rotateXYZ(float[] dest, int destOffset, float[] src, int srcOffset, float angleX, float angleY, float angleZ) {
        if (SimdSupport.USE_FMA) return rotateXYZ_fma(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
        return rotateXYZ_mulAdd(dest, destOffset, src, srcOffset, angleX, angleY, angleZ);
    }

    public static float[] rotateXYZ_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angleX, float angleY, float angleZ) {
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.sinWithCos(angleZ);
        float _t2 = Math.sinWithCos(angleY);
        float _t3 = Math.cosWithSin(angleX);
        float _t4 = Math.cosWithSin(angleZ);
        float _t5 = Math.cosWithSin(angleY);
        float _t6 = _t2 * _t3;
        float _t8 = _t0 * _t2;
        rotateXYZ_fma_s4ce4eafa_v(dest, destOffset, src, srcOffset, _t2, java.lang.Math.fma(_t0, _t1, -(_t6 * _t4)), _t5 * _t4, java.lang.Math.fma(_t8, _t4, _t1 * _t3), java.lang.Math.fma(_t6, _t1, _t0 * _t4), java.lang.Math.fma(_t3, _t4, -(_t8 * _t1)), -(_t1 * _t5), _t3 * _t5, -(_t0 * _t5));
        return dest;
    }

    private static void rotateXYZ_fma_s4ce4eafa_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t2, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _h0), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _h1), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _h2)))).intoArray(dest, destOffset);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _h3), _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, _h4), _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _h5)))).intoArray(dest, destOffset + 4);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _h6), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _t2), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _h7)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateXYZ_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleX, float angleY, float angleZ) {
        float _t0 = Math.sinWithCos(angleX);
        float _t2 = Math.sinWithCos(angleY);
        float _t3 = Math.cosWithSin(angleX);
        rotateXYZ_mulAdd_s3803180d_v(dest, destOffset, src, srcOffset, _t0, Math.sinWithCos(angleZ), _t2, _t3, Math.cosWithSin(angleZ), Math.cosWithSin(angleY), _t2 * _t3, _t0 * _t2);
        return dest;
    }

    private static void rotateXYZ_mulAdd_s3803180d_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t8) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t0) * (_t1) - (_t6 * _t4)))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t5 * _t4)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t8) * (_t4) + (_t1 * _t3)))))).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t6) * (_t1) + (_t0 * _t4)))).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t3) * (_t4) - (_t8 * _t1)))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, -(_t1 * _t5))))).intoArray(dest, destOffset + 4);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t3 * _t5)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t2)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -(_t0 * _t5))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateXZY(float[] dest, int destOffset, float[] src, int srcOffset, float angleX, float angleZ, float angleY) {
        if (SimdSupport.USE_FMA) return rotateXZY_fma(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
        return rotateXZY_mulAdd(dest, destOffset, src, srcOffset, angleX, angleZ, angleY);
    }

    public static float[] rotateXZY_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angleX, float angleZ, float angleY) {
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.sinWithCos(angleZ);
        float _t2 = Math.cosWithSin(angleY);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.cosWithSin(angleX);
        float _t5 = Math.cosWithSin(angleZ);
        float _t6 = _t0 * _t1;
        float _t9 = _t1 * _t4;
        rotateXZY_fma_s3f7f0110_v(dest, destOffset, src, srcOffset, java.lang.Math.fma(_t6, _t2, -(_t3 * _t4)), _t2 * _t5, java.lang.Math.fma(_t9, _t2, _t0 * _t3), _t0 * _t5, _t4 * _t5, -_t1, java.lang.Math.fma(_t6, _t3, _t4 * _t2), _t3 * _t5, java.lang.Math.fma(_t9, _t3, -(_t0 * _t2)));
        return dest;
    }

    private static void rotateXZY_fma_s3f7f0110_v(float[] dest, int destOffset, float[] src, int srcOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _h0), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _h1), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _h2)))).intoArray(dest, destOffset);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _h3), _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, _h4), _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _h5)))).intoArray(dest, destOffset + 4);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _h6), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _h7), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _h8)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateXZY_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleX, float angleZ, float angleY) {
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.sinWithCos(angleZ);
        float _t4 = Math.cosWithSin(angleX);
        rotateXZY_mulAdd_sfbec7f93_v(dest, destOffset, src, srcOffset, _t0, _t1, Math.cosWithSin(angleY), Math.sinWithCos(angleY), _t4, Math.cosWithSin(angleZ), _t0 * _t1, _t1 * _t4);
        return dest;
    }

    private static void rotateXZY_mulAdd_sfbec7f93_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t9) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t6) * (_t2) - (_t3 * _t4)))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t2 * _t5)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t9) * (_t2) + (_t0 * _t3)))))).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t0 * _t5)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t4 * _t5)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, -_t1)))).intoArray(dest, destOffset + 4);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t6) * (_t3) + (_t4 * _t2)))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t3 * _t5)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t9) * (_t3) - (_t0 * _t2)))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateY(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        if (SimdSupport.USE_FMA) return rotateY_fma(dest, destOffset, src, srcOffset, angle);
        return rotateY_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static float[] rotateY_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t1 = Math.sinWithCos(angle);
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(_SIMD_SPECIES, Math.cosWithSin(angle));
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(_sv1, _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -_t1))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t1), _sv2.mul(_sv1)).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] rotateY_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t1 = Math.sinWithCos(angle);
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(_SIMD_SPECIES, Math.cosWithSin(angle));
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(_sv1).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -_t1))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t1)).add(_sv2.mul(_sv1)).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] rotateYXZ(float[] dest, int destOffset, float[] src, int srcOffset, float angleY, float angleX, float angleZ) {
        if (SimdSupport.USE_FMA) return rotateYXZ_fma(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return rotateYXZ_mulAdd(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
    }

    public static float[] rotateYXZ_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angleY, float angleX, float angleZ) {
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.cosWithSin(angleY);
        float _t2 = Math.sinWithCos(angleZ);
        float _t3 = Math.sinWithCos(angleY);
        float _t4 = Math.cosWithSin(angleZ);
        float _t5 = Math.cosWithSin(angleX);
        float _t6 = _t0 * _t1;
        float _t8 = _t0 * _t3;
        rotateYXZ_fma_s7bb9f37c_v(dest, destOffset, src, srcOffset, java.lang.Math.fma(_t6, _t2, -(_t3 * _t4)), java.lang.Math.fma(_t8, _t2, _t1 * _t4), _t2 * _t5, java.lang.Math.fma(_t6, _t4, _t3 * _t2), java.lang.Math.fma(_t8, _t4, -(_t2 * _t1)), _t5 * _t4, _t5 * _t1, _t3 * _t5, -_t0);
        return dest;
    }

    private static void rotateYXZ_fma_s7bb9f37c_v(float[] dest, int destOffset, float[] src, int srcOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _h0), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _h1), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _h2)))).intoArray(dest, destOffset);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _h3), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _h4), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _h5)))).intoArray(dest, destOffset + 4);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _h6), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _h7), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _h8)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateYXZ_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleY, float angleX, float angleZ) {
        float _t0 = Math.sinWithCos(angleX);
        float _t1 = Math.cosWithSin(angleY);
        float _t3 = Math.sinWithCos(angleY);
        rotateYXZ_mulAdd_sb0d4e247_v(dest, destOffset, src, srcOffset, _t0, _t1, Math.sinWithCos(angleZ), _t3, Math.cosWithSin(angleZ), Math.cosWithSin(angleX), _t0 * _t1, _t0 * _t3);
        return dest;
    }

    private static void rotateYXZ_mulAdd_sb0d4e247_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t8) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t6) * (_t2) - (_t3 * _t4)))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t8) * (_t2) + (_t1 * _t4)))).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t2 * _t5)))).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t6) * (_t4) + (_t3 * _t2)))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t8) * (_t4) - (_t2 * _t1)))).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t5 * _t4)))).intoArray(dest, destOffset + 4);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t5 * _t1)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t3 * _t5)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -_t0)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateYZX(float[] dest, int destOffset, float[] src, int srcOffset, float angleY, float angleZ, float angleX) {
        if (SimdSupport.USE_FMA) return rotateYZX_fma(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
        return rotateYZX_mulAdd(dest, destOffset, src, srcOffset, angleY, angleZ, angleX);
    }

    public static float[] rotateYZX_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angleY, float angleZ, float angleX) {
        float _t0 = Math.sinWithCos(angleY);
        float _t1 = Math.cosWithSin(angleZ);
        float _t2 = Math.cosWithSin(angleY);
        float _t3 = Math.sinWithCos(angleZ);
        float _t4 = Math.cosWithSin(angleX);
        float _t5 = Math.sinWithCos(angleX);
        float _t8 = _t0 * _t3;
        float _t10 = _t3 * _t2;
        rotateYZX_fma_s61c5d7fc_v(dest, destOffset, src, srcOffset, _t3, -(_t0 * _t1), _t2 * _t1, java.lang.Math.fma(_t8, _t4, _t5 * _t2), java.lang.Math.fma(_t5, _t0, -(_t10 * _t4)), _t4 * _t1, java.lang.Math.fma(_t4, _t2, -(_t8 * _t5)), java.lang.Math.fma(_t10, _t5, _t0 * _t4), -(_t5 * _t1));
        return dest;
    }

    private static void rotateYZX_fma_s61c5d7fc_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t3, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _h0), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _h1), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t3)))).intoArray(dest, destOffset);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _h2), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _h3), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _h4)))).intoArray(dest, destOffset + 4);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _h5), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _h6), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _h7)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateYZX_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleY, float angleZ, float angleX) {
        float _t0 = Math.sinWithCos(angleY);
        float _t2 = Math.cosWithSin(angleY);
        float _t3 = Math.sinWithCos(angleZ);
        rotateYZX_mulAdd_s11cf53bf_v(dest, destOffset, src, srcOffset, _t0, Math.cosWithSin(angleZ), _t2, _t3, Math.cosWithSin(angleX), Math.sinWithCos(angleX), _t0 * _t3, _t3 * _t2);
        return dest;
    }

    private static void rotateYZX_mulAdd_s11cf53bf_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t8, float _t10) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, -(_t0 * _t1))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t2 * _t1)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t3)))).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t8) * (_t4) + (_t5 * _t2)))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t5) * (_t0) - (_t10 * _t4)))).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t4 * _t1)))).intoArray(dest, destOffset + 4);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t4) * (_t2) - (_t8 * _t5)))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t10) * (_t5) + (_t0 * _t4)))).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -(_t5 * _t1))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateZ(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        if (SimdSupport.USE_FMA) return rotateZ_fma(dest, destOffset, src, srcOffset, angle);
        return rotateZ_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static float[] rotateZ_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t1 = Math.sinWithCos(angle);
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(_SIMD_SPECIES, Math.cosWithSin(angle));
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(_sv1, _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t1))).intoArray(dest, destOffset);
        _sv2.fma(_sv1, _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, -_t1))).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] rotateZ_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t1 = Math.sinWithCos(angle);
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(_SIMD_SPECIES, Math.cosWithSin(angle));
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(_sv1).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t1))).intoArray(dest, destOffset);
        _sv2.mul(_sv1).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, -_t1))).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] rotateZXY(float[] dest, int destOffset, float[] src, int srcOffset, float angleZ, float angleX, float angleY) {
        if (SimdSupport.USE_FMA) return rotateZXY_fma(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
        return rotateZXY_mulAdd(dest, destOffset, src, srcOffset, angleZ, angleX, angleY);
    }

    public static float[] rotateZXY_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angleZ, float angleX, float angleY) {
        float _t0 = Math.sinWithCos(angleY);
        float _t1 = Math.cosWithSin(angleX);
        float _t2 = Math.cosWithSin(angleY);
        float _t3 = Math.cosWithSin(angleZ);
        float _t4 = Math.sinWithCos(angleX);
        float _t5 = Math.sinWithCos(angleZ);
        float _t7 = _t4 * _t5;
        float _t8 = _t4 * _t3;
        rotateZXY_fma_sbae691a4_v(dest, destOffset, src, srcOffset, _t4, -(_t0 * _t1), java.lang.Math.fma(_t2, _t3, -(_t7 * _t0)), java.lang.Math.fma(_t8, _t0, _t5 * _t2), _t1 * _t3, -(_t5 * _t1), _t1 * _t2, java.lang.Math.fma(_t7, _t2, _t0 * _t3), java.lang.Math.fma(_t0, _t5, -(_t8 * _t2)));
        return dest;
    }

    private static void rotateZXY_fma_sbae691a4_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t4, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _h0), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _h1), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _h2)))).intoArray(dest, destOffset);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _t4), _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, _h3), _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _h4)))).intoArray(dest, destOffset + 4);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _h5), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _h6), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _h7)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateZXY_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleZ, float angleX, float angleY) {
        float _t3 = Math.cosWithSin(angleZ);
        float _t4 = Math.sinWithCos(angleX);
        float _t5 = Math.sinWithCos(angleZ);
        rotateZXY_mulAdd_sa4c97f6f_v(dest, destOffset, src, srcOffset, Math.sinWithCos(angleY), Math.cosWithSin(angleX), Math.cosWithSin(angleY), _t3, _t4, _t5, _t4 * _t5, _t4 * _t3);
        return dest;
    }

    private static void rotateZXY_mulAdd_sa4c97f6f_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t7, float _t8) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, -(_t0 * _t1))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t2) * (_t3) - (_t7 * _t0)))).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t8) * (_t0) + (_t5 * _t2)))))).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t4)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t1 * _t3)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, -(_t5 * _t1))))).intoArray(dest, destOffset + 4);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t1 * _t2)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t7) * (_t2) + (_t0 * _t3)))).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t0) * (_t5) - (_t8 * _t2)))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] rotateZYX(float[] dest, int destOffset, float[] src, int srcOffset, float angleZ, float angleY, float angleX) {
        if (SimdSupport.USE_FMA) return rotateZYX_fma(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
        return rotateZYX_mulAdd(dest, destOffset, src, srcOffset, angleZ, angleY, angleX);
    }

    public static float[] rotateZYX_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angleZ, float angleY, float angleX) {
        float _t0 = Math.sinWithCos(angleY);
        float _t1 = Math.cosWithSin(angleY);
        float _t2 = Math.cosWithSin(angleZ);
        float _t3 = Math.sinWithCos(angleZ);
        float _t4 = Math.sinWithCos(angleX);
        float _t5 = Math.cosWithSin(angleX);
        float _t9 = _t0 * _t2;
        float _t11 = _t0 * _t3;
        rotateYXZ_fma_s7bb9f37c_v(dest, destOffset, src, srcOffset, -_t0, _t1 * _t2, _t3 * _t1, _t4 * _t1, java.lang.Math.fma(_t9, _t4, -(_t3 * _t5)), java.lang.Math.fma(_t11, _t4, _t5 * _t2), _t5 * _t1, java.lang.Math.fma(_t9, _t5, _t4 * _t3), java.lang.Math.fma(_t11, _t5, -(_t4 * _t2)));
        return dest;
    }

    public static float[] rotateZYX_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleZ, float angleY, float angleX) {
        float _t0 = Math.sinWithCos(angleY);
        float _t2 = Math.cosWithSin(angleZ);
        float _t3 = Math.sinWithCos(angleZ);
        rotateZYX_mulAdd_s534aa8d1_v(dest, destOffset, src, srcOffset, _t0, Math.cosWithSin(angleY), _t2, _t3, Math.sinWithCos(angleX), Math.cosWithSin(angleX), _t0 * _t2, _t0 * _t3);
        return dest;
    }

    private static void rotateZYX_mulAdd_s534aa8d1_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t9, float _t11) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, -_t0)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _t1 * _t2)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t3 * _t1)))).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t4 * _t1)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t9) * (_t4) - (_t3 * _t5)))).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t11) * (_t4) + (_t5 * _t2)))))).intoArray(dest, destOffset + 4);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _t5 * _t1)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t9) * (_t5) + (_t4 * _t3)))).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_t11) * (_t5) - (_t4 * _t2)))))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] scale(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _c1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(_SIMD_SPECIES, vY));
        var _c2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).mul(FloatVector.broadcast(_SIMD_SPECIES, vZ));
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, vX)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] scale(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _c1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(_SIMD_SPECIES, v[vOffset + 1]));
        var _c2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).mul(FloatVector.broadcast(_SIMD_SPECIES, v[vOffset + 2]));
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, v[vOffset])).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] scale(float[] dest, int destOffset, float[] src, int srcOffset, float s) {
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, s);
        var _c1 = _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4));
        var _c2 = _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8));
        var _c3 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 12);
        _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
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
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.broadcast(_SIMD_SPECIES, s);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, pivotX * _t0), _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, pivotY * _t0), _sv3.fma(FloatVector.broadcast(_SIMD_SPECIES, pivotZ * _t0), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(_sv1).intoArray(dest, destOffset);
        _sv0.mul(_sv2).intoArray(dest, destOffset + 4);
        _sv0.mul(_sv3).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] scaleAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        float _t0 = 1.0f - s;
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.broadcast(_SIMD_SPECIES, s);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, pivotX * _t0)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, pivotY * _t0)).add(_sv3.mul(FloatVector.broadcast(_SIMD_SPECIES, pivotZ * _t0)).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(_sv1).intoArray(dest, destOffset);
        _sv0.mul(_sv2).intoArray(dest, destOffset + 4);
        _sv0.mul(_sv3).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] scaleAround(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float s) {
        if (SimdSupport.USE_FMA) return scaleAround_fma(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return scaleAround_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    public static float[] scaleAround_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float s) {
        float _t0 = 1.0f - s;
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.broadcast(_SIMD_SPECIES, s);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, pivot[pivotOffset] * _t0), _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, pivot[pivotOffset + 1] * _t0), _sv3.fma(FloatVector.broadcast(_SIMD_SPECIES, pivot[pivotOffset + 2] * _t0), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(_sv1).intoArray(dest, destOffset);
        _sv0.mul(_sv2).intoArray(dest, destOffset + 4);
        _sv0.mul(_sv3).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] scaleAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float s) {
        float _t0 = 1.0f - s;
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.broadcast(_SIMD_SPECIES, s);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, pivot[pivotOffset] * _t0)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, pivot[pivotOffset + 1] * _t0)).add(_sv3.mul(FloatVector.broadcast(_SIMD_SPECIES, pivot[pivotOffset + 2] * _t0)).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.mul(_sv1).intoArray(dest, destOffset);
        _sv0.mul(_sv2).intoArray(dest, destOffset + 4);
        _sv0.mul(_sv3).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] scaleAround(float[] dest, int destOffset, float[] src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        if (SimdSupport.USE_FMA) return scaleAround_fma(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return scaleAround_mulAdd(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
    }

    public static float[] scaleAround_fma(float[] dest, int destOffset, float[] src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, pivotX * (1.0f - sX)), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, pivotY * (1.0f - sY)), _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, pivotZ * (1.0f - sZ)), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        FloatVector.broadcast(_SIMD_SPECIES, sX).mul(_sv0).intoArray(dest, destOffset);
        FloatVector.broadcast(_SIMD_SPECIES, sY).mul(_sv1).intoArray(dest, destOffset + 4);
        FloatVector.broadcast(_SIMD_SPECIES, sZ).mul(_sv2).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] scaleAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, pivotX * (1.0f - sX))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, pivotY * (1.0f - sY))).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, pivotZ * (1.0f - sZ))).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        FloatVector.broadcast(_SIMD_SPECIES, sX).mul(_sv0).intoArray(dest, destOffset);
        FloatVector.broadcast(_SIMD_SPECIES, sY).mul(_sv1).intoArray(dest, destOffset + 4);
        FloatVector.broadcast(_SIMD_SPECIES, sZ).mul(_sv2).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] scaleAround(float[] dest, int destOffset, float[] src, int srcOffset, float[] s, int sOffset, float[] pivot, int pivotOffset) {
        if (SimdSupport.USE_FMA) return scaleAround_fma(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return scaleAround_mulAdd(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    public static float[] scaleAround_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] s, int sOffset, float[] pivot, int pivotOffset) {
        float _sx = s[sOffset];
        float _sy = s[sOffset + 1];
        float _sz = s[sOffset + 2];
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, pivot[pivotOffset] * (1.0f - _sx)), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, pivot[pivotOffset + 1] * (1.0f - _sy)), _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, pivot[pivotOffset + 2] * (1.0f - _sz)), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        FloatVector.broadcast(_SIMD_SPECIES, _sx).mul(_sv0).intoArray(dest, destOffset);
        FloatVector.broadcast(_SIMD_SPECIES, _sy).mul(_sv1).intoArray(dest, destOffset + 4);
        FloatVector.broadcast(_SIMD_SPECIES, _sz).mul(_sv2).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] scaleAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] s, int sOffset, float[] pivot, int pivotOffset) {
        float _sx = s[sOffset];
        float _sy = s[sOffset + 1];
        float _sz = s[sOffset + 2];
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, pivot[pivotOffset] * (1.0f - _sx))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, pivot[pivotOffset + 1] * (1.0f - _sy))).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, pivot[pivotOffset + 2] * (1.0f - _sz))).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        FloatVector.broadcast(_SIMD_SPECIES, _sx).mul(_sv0).intoArray(dest, destOffset);
        FloatVector.broadcast(_SIMD_SPECIES, _sy).mul(_sv1).intoArray(dest, destOffset + 4);
        FloatVector.broadcast(_SIMD_SPECIES, _sz).mul(_sv2).intoArray(dest, destOffset + 8);
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
        float _t28 = java.lang.Math.fma(lightX, planeX, lightY * planeY);
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, -_self03).withLane(1, -_self13).withLane(2, -_self23).withLane(3, -_self33);
        var _sv1 = FloatVector.zero(_SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = FloatVector.zero(_SIMD_SPECIES).withLane(0, -_self01).withLane(1, -_self11).withLane(2, -_self21).withLane(3, -_self31);
        var _c2 = _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, lightW * planeZ), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(lightW, planeW, _t28)), _sv4.fma(FloatVector.broadcast(_SIMD_SPECIES, lightY * planeZ), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -(lightX * planeZ))))));
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(lightZ, planeZ, _t28)), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, lightZ * planeW), _sv4.fma(FloatVector.broadcast(_SIMD_SPECIES, lightY * planeW), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -(lightX * planeW))))));
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, lightW * planeX), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, lightZ * planeX), _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(lightW, planeW, java.lang.Math.fma(lightY, planeY, _t14))), _sv3.mul(FloatVector.broadcast(_SIMD_SPECIES, -(lightY * planeX)))))).intoArray(dest, destOffset);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, lightW * planeY), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, lightZ * planeY), _sv3.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(lightW, planeW, java.lang.Math.fma(lightX, planeX, _t14))), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -(lightX * planeY)))))).intoArray(dest, destOffset + 4);
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
        float _t28 = ((lightX) * (planeX) + (lightY * planeY));
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, -_self03).withLane(1, -_self13).withLane(2, -_self23).withLane(3, -_self33);
        var _sv1 = FloatVector.zero(_SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = FloatVector.zero(_SIMD_SPECIES).withLane(0, -_self01).withLane(1, -_self11).withLane(2, -_self21).withLane(3, -_self31);
        var _c2 = _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, lightW * planeZ)).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).mul(FloatVector.broadcast(_SIMD_SPECIES, ((lightW) * (planeW) + (_t28)))).add(_sv4.mul(FloatVector.broadcast(_SIMD_SPECIES, lightY * planeZ)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -(lightX * planeZ))))));
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).mul(FloatVector.broadcast(_SIMD_SPECIES, ((lightZ) * (planeZ) + (_t28)))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, lightZ * planeW)).add(_sv4.mul(FloatVector.broadcast(_SIMD_SPECIES, lightY * planeW)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -(lightX * planeW))))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, lightW * planeX)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, lightZ * planeX)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((lightW) * (planeW) + (((lightY) * (planeY) + (_t14)))))).add(_sv3.mul(FloatVector.broadcast(_SIMD_SPECIES, -(lightY * planeX)))))).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, lightW * planeY)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, lightZ * planeY)).add(_sv3.mul(FloatVector.broadcast(_SIMD_SPECIES, ((lightW) * (planeW) + (((lightX) * (planeX) + (_t14)))))).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -(lightX * planeY)))))).intoArray(dest, destOffset + 4);
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
        float _planex = plane[planeOffset];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _lightz = light[lightOffset + 2];
        float _planew = plane[planeOffset + 3];
        float _lighty = light[lightOffset + 1];
        float _planey = plane[planeOffset + 1];
        float _lightx = light[lightOffset];
        float _planez = plane[planeOffset + 2];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _t14 = _lightz * _planez;
        float _t28 = java.lang.Math.fma(_lightx, _planex, _lighty * _planey);
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, -_self03).withLane(1, -_self13).withLane(2, -_self23).withLane(3, -_self33);
        var _sv1 = FloatVector.zero(_SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = FloatVector.zero(_SIMD_SPECIES).withLane(0, -_self01).withLane(1, -_self11).withLane(2, -_self21).withLane(3, -_self31);
        var _c2 = _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _lightw * _planez), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_lightw, _planew, _t28)), _sv4.fma(FloatVector.broadcast(_SIMD_SPECIES, _lighty * _planez), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -(_lightx * _planez))))));
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_lightz, _planez, _t28)), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _lightz * _planew), _sv4.fma(FloatVector.broadcast(_SIMD_SPECIES, _lighty * _planew), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -(_lightx * _planew))))));
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _lightw * _planex), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _lightz * _planex), _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_lightw, _planew, java.lang.Math.fma(_lighty, _planey, _t14))), _sv3.mul(FloatVector.broadcast(_SIMD_SPECIES, -(_lighty * _planex)))))).intoArray(dest, destOffset);
        _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, _lightw * _planey), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, _lightz * _planey), _sv3.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(_lightw, _planew, java.lang.Math.fma(_lightx, _planex, _t14))), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -(_lightx * _planey)))))).intoArray(dest, destOffset + 4);
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
        float _planex = plane[planeOffset];
        float _self02 = src[srcOffset + 8];
        float _self12 = src[srcOffset + 9];
        float _self22 = src[srcOffset + 10];
        float _self32 = src[srcOffset + 11];
        float _lightz = light[lightOffset + 2];
        float _planew = plane[planeOffset + 3];
        float _lighty = light[lightOffset + 1];
        float _planey = plane[planeOffset + 1];
        float _lightx = light[lightOffset];
        float _planez = plane[planeOffset + 2];
        float _self01 = src[srcOffset + 4];
        float _self11 = src[srcOffset + 5];
        float _self21 = src[srcOffset + 6];
        float _self31 = src[srcOffset + 7];
        float _t14 = _lightz * _planez;
        float _t28 = ((_lightx) * (_planex) + (_lighty * _planey));
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.zero(_SIMD_SPECIES).withLane(0, -_self03).withLane(1, -_self13).withLane(2, -_self23).withLane(3, -_self33);
        var _sv1 = FloatVector.zero(_SIMD_SPECIES).withLane(0, -_self02).withLane(1, -_self12).withLane(2, -_self22).withLane(3, -_self32);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv4 = FloatVector.zero(_SIMD_SPECIES).withLane(0, -_self01).withLane(1, -_self11).withLane(2, -_self21).withLane(3, -_self31);
        var _c2 = _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _lightw * _planez)).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).mul(FloatVector.broadcast(_SIMD_SPECIES, ((_lightw) * (_planew) + (_t28)))).add(_sv4.mul(FloatVector.broadcast(_SIMD_SPECIES, _lighty * _planez)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -(_lightx * _planez))))));
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).mul(FloatVector.broadcast(_SIMD_SPECIES, ((_lightz) * (_planez) + (_t28)))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _lightz * _planew)).add(_sv4.mul(FloatVector.broadcast(_SIMD_SPECIES, _lighty * _planew)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -(_lightx * _planew))))));
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _lightw * _planex)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _lightz * _planex)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_lightw) * (_planew) + (((_lighty) * (_planey) + (_t14)))))).add(_sv3.mul(FloatVector.broadcast(_SIMD_SPECIES, -(_lighty * _planex)))))).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, _lightw * _planey)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, _lightz * _planey)).add(_sv3.mul(FloatVector.broadcast(_SIMD_SPECIES, ((_lightw) * (_planew) + (((_lightx) * (_planex) + (_t14)))))).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, -(_lightx * _planey)))))).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] shear(float[] dest, int destOffset, float[] src, int srcOffset, float xy, float xz, float yx, float yz, float zx, float zy) {
        if (SimdSupport.USE_FMA) return shear_fma(dest, destOffset, src, srcOffset, xy, xz, yx, yz, zx, zy);
        return shear_mulAdd(dest, destOffset, src, srcOffset, xy, xz, yx, yz, zx, zy);
    }

    public static float[] shear_fma(float[] dest, int destOffset, float[] src, int srcOffset, float xy, float xz, float yx, float yz, float zx, float zy) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.broadcast(_SIMD_SPECIES, yx).fma(_sv0, FloatVector.broadcast(_SIMD_SPECIES, zx).fma(_sv1, _sv2)).intoArray(dest, destOffset);
        FloatVector.broadcast(_SIMD_SPECIES, xy).fma(_sv2, FloatVector.broadcast(_SIMD_SPECIES, zy).fma(_sv1, _sv0)).intoArray(dest, destOffset + 4);
        FloatVector.broadcast(_SIMD_SPECIES, xz).fma(_sv2, FloatVector.broadcast(_SIMD_SPECIES, yz).fma(_sv0, _sv1)).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] shear_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float xy, float xz, float yx, float yz, float zx, float zy) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _c3 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12);
        FloatVector.broadcast(_SIMD_SPECIES, yx).mul(_sv0).add(FloatVector.broadcast(_SIMD_SPECIES, zx).mul(_sv1).add(_sv2)).intoArray(dest, destOffset);
        FloatVector.broadcast(_SIMD_SPECIES, xy).mul(_sv2).add(FloatVector.broadcast(_SIMD_SPECIES, zy).mul(_sv1).add(_sv0)).intoArray(dest, destOffset + 4);
        FloatVector.broadcast(_SIMD_SPECIES, xz).mul(_sv2).add(FloatVector.broadcast(_SIMD_SPECIES, yz).mul(_sv0).add(_sv1)).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] tile(float[] dest, int destOffset, float[] src, int srcOffset, float x, float y, float w, float h) {
        if (SimdSupport.USE_FMA) return tile_fma(dest, destOffset, src, srcOffset, x, y, w, h);
        return tile_mulAdd(dest, destOffset, src, srcOffset, x, y, w, h);
    }

    public static float[] tile_fma(float[] dest, int destOffset, float[] src, int srcOffset, float x, float y, float w, float h) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(-2.0f, x, w - 1.0f)), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(-2.0f, y, h - 1.0f)), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        FloatVector.broadcast(_SIMD_SPECIES, w).mul(_sv0).intoArray(dest, destOffset);
        FloatVector.broadcast(_SIMD_SPECIES, h).mul(_sv1).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] tile_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float x, float y, float w, float h) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((w - 1.0f) - (2.0f) * (x)))).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, ((h - 1.0f) - (2.0f) * (y)))).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12)));
        FloatVector.broadcast(_SIMD_SPECIES, w).mul(_sv0).intoArray(dest, destOffset);
        FloatVector.broadcast(_SIMD_SPECIES, h).mul(_sv1).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] translate(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        if (SimdSupport.USE_FMA) return translate_fma(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return translate_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    public static float[] translate_fma(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, vX), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, vY), _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, vZ), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.intoArray(dest, destOffset);
        _sv1.intoArray(dest, destOffset + 4);
        _sv2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] translate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, vX)).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, vY)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, vZ)).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.intoArray(dest, destOffset);
        _sv1.intoArray(dest, destOffset + 4);
        _sv2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] translate(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        if (SimdSupport.USE_FMA) return translate_fma(dest, destOffset, src, srcOffset, v, vOffset);
        return translate_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
    }

    public static float[] translate_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, v[vOffset]), _sv1.fma(FloatVector.broadcast(_SIMD_SPECIES, v[vOffset + 1]), _sv2.fma(FloatVector.broadcast(_SIMD_SPECIES, v[vOffset + 2]), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.intoArray(dest, destOffset);
        _sv1.intoArray(dest, destOffset + 4);
        _sv2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] translate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8);
        var _c3 = _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, v[vOffset])).add(_sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, v[vOffset + 1])).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, v[vOffset + 2])).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12))));
        _sv0.intoArray(dest, destOffset);
        _sv1.intoArray(dest, destOffset + 4);
        _sv2.intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
        return dest;
    }

    public static float[] mulVec4(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ, float vW) {
        if (SimdSupport.USE_FMA) return mulVec4_fma(dest, destOffset, src, srcOffset, vX, vY, vZ, vW);
        return mulVec4_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ, vW);
    }

    public static float[] mulVec4_fma(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ, float vW) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).fma(FloatVector.broadcast(_SIMD_SPECIES, vW), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).fma(FloatVector.broadcast(_SIMD_SPECIES, vZ), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).fma(FloatVector.broadcast(_SIMD_SPECIES, vX), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(_SIMD_SPECIES, vY))))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] mulVec4_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ, float vW) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).mul(FloatVector.broadcast(_SIMD_SPECIES, vW)).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).mul(FloatVector.broadcast(_SIMD_SPECIES, vZ)).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, vX)).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(_SIMD_SPECIES, vY))))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] mulVec4(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        if (SimdSupport.USE_FMA) return mulVec4_fma(dest, destOffset, src, srcOffset, v, vOffset);
        return mulVec4_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
    }

    public static float[] mulVec4_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).fma(FloatVector.broadcast(_SIMD_SPECIES, v[vOffset + 3]), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).fma(FloatVector.broadcast(_SIMD_SPECIES, v[vOffset + 2]), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).fma(FloatVector.broadcast(_SIMD_SPECIES, v[vOffset]), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(_SIMD_SPECIES, v[vOffset + 1]))))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] mulVec4_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 12).mul(FloatVector.broadcast(_SIMD_SPECIES, v[vOffset + 3])).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 8).mul(FloatVector.broadcast(_SIMD_SPECIES, v[vOffset + 2])).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, v[vOffset])).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(_SIMD_SPECIES, v[vOffset + 1]))))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] transformPosition_vecArr(float[] dest, int destOffset, float[] matrix, int matrixOffset, float[] points, int pointsOffset, int count) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return transformPosition_vecArr_fma(dest, destOffset, matrix, matrixOffset, points, pointsOffset, count);
        return transformPosition_vecArr_mulAdd(dest, destOffset, matrix, matrixOffset, points, pointsOffset, count);
    }

    /** {@code transformPosition_vecArr} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] transformPosition_vecArr_fma(float[] dest, int destOffset, float[] matrix, int matrixOffset, float[] points, int pointsOffset, int count) {
        float _m00 = matrix[matrixOffset];
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
        float px = points[pointsOffset], py = points[pointsOffset + 1], pz = points[pointsOffset + 2];
        int _i = 0;
        for (; _i < count - 1; _i++) {
            var _v = FloatVector.broadcast(_sp, px).fma(_c0, FloatVector.broadcast(_sp, py).fma(_c1, FloatVector.broadcast(_sp, pz).fma(_c2, _c3)));
            int _pn = pointsOffset + (_i + 1) * 3;
            px = points[_pn];
            py = points[_pn + 1];
            pz = points[_pn + 2];
            _v.intoArray(dest, destOffset + _i * 3);
        }
        int _do = destOffset + _i * 3;
        dest[_do] = java.lang.Math.fma(_m00, px, java.lang.Math.fma(_m01, py, java.lang.Math.fma(_m02, pz, _m03)));
        dest[_do + 1] = java.lang.Math.fma(_m10, px, java.lang.Math.fma(_m11, py, java.lang.Math.fma(_m12, pz, _m13)));
        dest[_do + 2] = java.lang.Math.fma(_m20, px, java.lang.Math.fma(_m21, py, java.lang.Math.fma(_m22, pz, _m23)));
        return dest;
    }

    /** {@code transformPosition_vecArr} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] transformPosition_vecArr_mulAdd(float[] dest, int destOffset, float[] matrix, int matrixOffset, float[] points, int pointsOffset, int count) {
        float _m00 = matrix[matrixOffset];
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
        float px = points[pointsOffset], py = points[pointsOffset + 1], pz = points[pointsOffset + 2];
        int _i = 0;
        for (; _i < count - 1; _i++) {
            var _v = FloatVector.broadcast(_sp, px).fma(_c0, FloatVector.broadcast(_sp, py).fma(_c1, FloatVector.broadcast(_sp, pz).fma(_c2, _c3)));
            int _pn = pointsOffset + (_i + 1) * 3;
            px = points[_pn];
            py = points[_pn + 1];
            pz = points[_pn + 2];
            _v.intoArray(dest, destOffset + _i * 3);
        }
        int _do = destOffset + _i * 3;
        dest[_do] = ((_m00) * (px) + (((_m01) * (py) + (((_m02) * (pz) + (_m03))))));
        dest[_do + 1] = ((_m10) * (px) + (((_m11) * (py) + (((_m12) * (pz) + (_m13))))));
        dest[_do + 2] = ((_m20) * (px) + (((_m21) * (py) + (((_m22) * (pz) + (_m23))))));
        return dest;
    }

    public static float[] transformDirection_vecArr(float[] dest, int destOffset, float[] matrix, int matrixOffset, float[] points, int pointsOffset, int count) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return transformDirection_vecArr_fma(dest, destOffset, matrix, matrixOffset, points, pointsOffset, count);
        return transformDirection_vecArr_mulAdd(dest, destOffset, matrix, matrixOffset, points, pointsOffset, count);
    }

    /** {@code transformDirection_vecArr} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] transformDirection_vecArr_fma(float[] dest, int destOffset, float[] matrix, int matrixOffset, float[] points, int pointsOffset, int count) {
        transformDirection_vecArr_fma_vba915529(dest, destOffset, points, pointsOffset, count, matrix[matrixOffset], matrix[matrixOffset + 4], matrix[matrixOffset + 8], matrix[matrixOffset + 1], matrix[matrixOffset + 5], matrix[matrixOffset + 9], matrix[matrixOffset + 2], matrix[matrixOffset + 6], matrix[matrixOffset + 10]);
        return dest;
    }

    private static void transformDirection_vecArr_fma_vba915529(float[] dest, int destOffset, float[] points, int pointsOffset, int count, float _m00, float _m01, float _m02, float _m10, float _m11, float _m12, float _m20, float _m21, float _m22) {
        var _sp = FloatVector.SPECIES_128;
        var _c0 = FloatVector.zero(_sp).withLane(0, _m00).withLane(1, _m10).withLane(2, _m20);
        var _c1 = FloatVector.zero(_sp).withLane(0, _m01).withLane(1, _m11).withLane(2, _m21);
        var _c2 = FloatVector.zero(_sp).withLane(0, _m02).withLane(1, _m12).withLane(2, _m22);
        float px = points[pointsOffset], py = points[pointsOffset + 1], pz = points[pointsOffset + 2];
        int _i = 0;
        for (; _i < count - 1; _i++) {
            var _v = FloatVector.broadcast(_sp, pz).fma(_c2, FloatVector.broadcast(_sp, px).fma(_c0, FloatVector.broadcast(_sp, py).mul(_c1)));
            int _pn = pointsOffset + (_i + 1) * 3;
            px = points[_pn];
            py = points[_pn + 1];
            pz = points[_pn + 2];
            _v.intoArray(dest, destOffset + _i * 3);
        }
        int _do = destOffset + _i * 3;
        dest[_do] = java.lang.Math.fma(_m02, pz, java.lang.Math.fma(_m00, px, _m01 * py));
        dest[_do + 1] = java.lang.Math.fma(_m12, pz, java.lang.Math.fma(_m10, px, _m11 * py));
        dest[_do + 2] = java.lang.Math.fma(_m22, pz, java.lang.Math.fma(_m20, px, _m21 * py));
    }

    /** {@code transformDirection_vecArr} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] transformDirection_vecArr_mulAdd(float[] dest, int destOffset, float[] matrix, int matrixOffset, float[] points, int pointsOffset, int count) {
        transformDirection_vecArr_mulAdd_v2929cb7c(dest, destOffset, points, pointsOffset, count, matrix[matrixOffset], matrix[matrixOffset + 4], matrix[matrixOffset + 8], matrix[matrixOffset + 1], matrix[matrixOffset + 5], matrix[matrixOffset + 9], matrix[matrixOffset + 2], matrix[matrixOffset + 6], matrix[matrixOffset + 10]);
        return dest;
    }

    private static void transformDirection_vecArr_mulAdd_v2929cb7c(float[] dest, int destOffset, float[] points, int pointsOffset, int count, float _m00, float _m01, float _m02, float _m10, float _m11, float _m12, float _m20, float _m21, float _m22) {
        var _sp = FloatVector.SPECIES_128;
        var _c0 = FloatVector.zero(_sp).withLane(0, _m00).withLane(1, _m10).withLane(2, _m20);
        var _c1 = FloatVector.zero(_sp).withLane(0, _m01).withLane(1, _m11).withLane(2, _m21);
        var _c2 = FloatVector.zero(_sp).withLane(0, _m02).withLane(1, _m12).withLane(2, _m22);
        float px = points[pointsOffset], py = points[pointsOffset + 1], pz = points[pointsOffset + 2];
        int _i = 0;
        for (; _i < count - 1; _i++) {
            var _v = FloatVector.broadcast(_sp, pz).fma(_c2, FloatVector.broadcast(_sp, px).fma(_c0, FloatVector.broadcast(_sp, py).mul(_c1)));
            int _pn = pointsOffset + (_i + 1) * 3;
            px = points[_pn];
            py = points[_pn + 1];
            pz = points[_pn + 2];
            _v.intoArray(dest, destOffset + _i * 3);
        }
        int _do = destOffset + _i * 3;
        dest[_do] = ((_m02) * (pz) + (((_m00) * (px) + (_m01 * py))));
        dest[_do + 1] = ((_m12) * (pz) + (((_m10) * (px) + (_m11 * py))));
        dest[_do + 2] = ((_m22) * (pz) + (((_m20) * (px) + (_m21 * py))));
    }

    public static float[] transformProject_vecArr(float[] dest, int destOffset, float[] matrix, int matrixOffset, float[] points, int pointsOffset, int count) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) return transformProject_vecArr_fma(dest, destOffset, matrix, matrixOffset, points, pointsOffset, count);
        return transformProject_vecArr_mulAdd(dest, destOffset, matrix, matrixOffset, points, pointsOffset, count);
    }

    /** {@code transformProject_vecArr} with fused multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] transformProject_vecArr_fma(float[] dest, int destOffset, float[] matrix, int matrixOffset, float[] points, int pointsOffset, int count) {
        float _m00 = matrix[matrixOffset];
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
        float px = points[pointsOffset], py = points[pointsOffset + 1], pz = points[pointsOffset + 2];
        int _i = 0;
        for (; _i < count - 1; _i++) {
            var _v = FloatVector.broadcast(_sp, px).fma(_c0, FloatVector.broadcast(_sp, py).fma(_c1, FloatVector.broadcast(_sp, pz).fma(_c2, _c3)));
            float _inv = 1.0f / _v.lane(3);
            _v = _v.mul(FloatVector.broadcast(_sp, _inv));
            int _pn = pointsOffset + (_i + 1) * 3;
            px = points[_pn];
            py = points[_pn + 1];
            pz = points[_pn + 2];
            _v.intoArray(dest, destOffset + _i * 3);
        }
        int _do = destOffset + _i * 3;
        float _w = java.lang.Math.fma(_m30, px, java.lang.Math.fma(_m31, py, java.lang.Math.fma(_m32, pz, _m33)));
        float _inv = 1.0f / _w;
        dest[_do] = (java.lang.Math.fma(_m00, px, java.lang.Math.fma(_m01, py, java.lang.Math.fma(_m02, pz, _m03)))) * _inv;
        dest[_do + 1] = (java.lang.Math.fma(_m10, px, java.lang.Math.fma(_m11, py, java.lang.Math.fma(_m12, pz, _m13)))) * _inv;
        dest[_do + 2] = (java.lang.Math.fma(_m20, px, java.lang.Math.fma(_m21, py, java.lang.Math.fma(_m22, pz, _m23)))) * _inv;
        return dest;
    }

    /** {@code transformProject_vecArr} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static float[] transformProject_vecArr_mulAdd(float[] dest, int destOffset, float[] matrix, int matrixOffset, float[] points, int pointsOffset, int count) {
        float _m00 = matrix[matrixOffset];
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
        float px = points[pointsOffset], py = points[pointsOffset + 1], pz = points[pointsOffset + 2];
        int _i = 0;
        for (; _i < count - 1; _i++) {
            var _v = FloatVector.broadcast(_sp, px).fma(_c0, FloatVector.broadcast(_sp, py).fma(_c1, FloatVector.broadcast(_sp, pz).fma(_c2, _c3)));
            float _inv = 1.0f / _v.lane(3);
            _v = _v.mul(FloatVector.broadcast(_sp, _inv));
            int _pn = pointsOffset + (_i + 1) * 3;
            px = points[_pn];
            py = points[_pn + 1];
            pz = points[_pn + 2];
            _v.intoArray(dest, destOffset + _i * 3);
        }
        int _do = destOffset + _i * 3;
        float _w = ((_m30) * (px) + (((_m31) * (py) + (((_m32) * (pz) + (_m33))))));
        float _inv = 1.0f / _w;
        dest[_do] = (((_m00) * (px) + (((_m01) * (py) + (((_m02) * (pz) + (_m03))))))) * _inv;
        dest[_do + 1] = (((_m10) * (px) + (((_m11) * (py) + (((_m12) * (pz) + (_m13))))))) * _inv;
        dest[_do + 2] = (((_m20) * (px) + (((_m21) * (py) + (((_m22) * (pz) + (_m23))))))) * _inv;
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
        } else if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, src, srcOffset).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_256, src, srcOffset + 8).intoArray(dest, destOffset + 8);
        } else {
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
