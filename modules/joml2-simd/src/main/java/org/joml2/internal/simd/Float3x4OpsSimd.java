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
    private static final float[] DATA_0 = new float[] {1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};

    public static float[] transpose(float[] dest, int destOffset, float[] src, int srcOffset) {
        for (int _li = 0; _li < 3; _li++) {
            FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment transpose(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.DOUBLE_COLUMNS && SimdSupport.X86 && dest.isNative() && src.isNative()) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_256, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        } else {
            for (int _li = 0; _li < 3; _li++) {
                FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 16L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
            }
        }
        return dest;
    }

    public static float[] add(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        for (int _li = 0; _li < 3; _li++) {
            FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + _li * 4).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment add(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (SimdSupport.DOUBLE_COLUMNS && SimdSupport.X86 && dest.isNative() && other.isNative() && src.isNative()) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_256, other, otherOffset, java.nio.ByteOrder.nativeOrder()).add(FloatVector.fromMemorySegment(FloatVector.SPECIES_256, src, srcOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + 32L, java.nio.ByteOrder.nativeOrder()).add(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        } else {
            for (int _li = 0; _li < 3; _li++) {
                FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + _li * 16L, java.nio.ByteOrder.nativeOrder()).add(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 16L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
            }
        }
        return dest;
    }

    public static float[] mul(float[] dest, int destOffset, float[] src, int srcOffset, float scalar) {
        for (int _li = 0; _li < 3; _li++) {
            FloatVector.broadcast(SIMD_SPECIES, scalar).mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        if (SimdSupport.DOUBLE_COLUMNS && SimdSupport.X86 && dest.isNative() && src.isNative()) {
            FloatVector.broadcast(FloatVector.SPECIES_256, scalar).mul(FloatVector.fromMemorySegment(FloatVector.SPECIES_256, src, srcOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            FloatVector.broadcast(SIMD_SPECIES, scalar).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        } else {
            for (int _li = 0; _li < 3; _li++) {
                FloatVector.broadcast(SIMD_SPECIES, scalar).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 16L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
            }
        }
        return dest;
    }

    public static float[] negate(float[] dest, int destOffset, float[] src, int srcOffset) {
        for (int _li = 0; _li < 3; _li++) {
            FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4).neg().intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.DOUBLE_COLUMNS && SimdSupport.X86 && dest.isNative() && src.isNative()) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_256, src, srcOffset, java.nio.ByteOrder.nativeOrder()).neg().intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).neg().intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        } else {
            for (int _li = 0; _li < 3; _li++) {
                FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 16L, java.nio.ByteOrder.nativeOrder()).neg().intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
            }
        }
        return dest;
    }

    public static float[] sub(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        for (int _li = 0; _li < 3; _li++) {
            FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4).sub(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + _li * 4)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (SimdSupport.DOUBLE_COLUMNS && SimdSupport.X86 && dest.isNative() && src.isNative() && other.isNative()) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_256, src, srcOffset, java.nio.ByteOrder.nativeOrder()).sub(FloatVector.fromMemorySegment(FloatVector.SPECIES_256, other, otherOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).sub(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + 32L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        } else {
            for (int _li = 0; _li < 3; _li++) {
                FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 16L, java.nio.ByteOrder.nativeOrder()).sub(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + _li * 16L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
            }
        }
        return dest;
    }

    public static float[] set(float[] dest, int destOffset, float[] v, int vOffset) {
        for (int _li = 0; _li < 3; _li++) {
            FloatVector.fromArray(SIMD_SPECIES, v, vOffset + _li * 4).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment set(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        if (SimdSupport.DOUBLE_COLUMNS && SimdSupport.X86 && dest.isNative() && v.isNative()) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_256, v, vOffset, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            FloatVector.fromMemorySegment(SIMD_SPECIES, v, vOffset + 32L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        } else {
            for (int _li = 0; _li < 3; _li++) {
                FloatVector.fromMemorySegment(SIMD_SPECIES, v, vOffset + _li * 16L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
            }
        }
        return dest;
    }

    public static float[] withTranslation(float[] dest, int destOffset, float[] src, int srcOffset, float tX, float tY, float tZ) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(3, tY);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(3, tZ);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(3, tX).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static java.lang.foreign.MemorySegment withTranslation(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float tX, float tY, float tZ) {
        var _c1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder()).withLane(3, tY);
        var _c2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).withLane(3, tZ);
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).withLane(3, tX).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] withTranslation(float[] dest, int destOffset, float[] src, int srcOffset, float[] t, int tOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(3, t[tOffset + 1]);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(3, t[tOffset + 2]);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(3, t[tOffset]).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static java.lang.foreign.MemorySegment withTranslation(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && t.isNative()) return withTranslation_unsafe(dest, destOffset, src, srcOffset, t, tOffset);
        return withTranslation_api(dest, destOffset, src, srcOffset, t, tOffset);
    }

    public static java.lang.foreign.MemorySegment withTranslation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        long _tBase = t.address() + tOffset;
        var _c1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder()).withLane(3, UnsafeOpsHolder.U.getFloat(_tBase + 4L));
        var _c2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).withLane(3, UnsafeOpsHolder.U.getFloat(_tBase + 8L));
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).withLane(3, UnsafeOpsHolder.U.getFloat(_tBase)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment withTranslation_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        var _c1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder()).withLane(3, t.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, tOffset + 4L));
        var _c2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).withLane(3, t.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, tOffset + 8L));
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).withLane(3, t.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, tOffset)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] makeIdentity(float[] dest, int destOffset) {
        FloatVector.fromArray(FloatVector.SPECIES_128, DATA_0, 0).intoArray(dest, destOffset);
        FloatVector.fromArray(FloatVector.SPECIES_128, DATA_0, 4).intoArray(dest, destOffset + 4);
        FloatVector.fromArray(FloatVector.SPECIES_128, DATA_0, 8).intoArray(dest, destOffset + 8);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeIdentity(java.lang.foreign.MemorySegment dest, long destOffset) {
        FloatVector.fromArray(FloatVector.SPECIES_128, DATA_0, 0).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        FloatVector.fromArray(FloatVector.SPECIES_128, DATA_0, 4).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        FloatVector.fromArray(FloatVector.SPECIES_128, DATA_0, 8).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] lerp(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static float[] lerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        for (int _li = 0; _li < 3; _li++) {
            int _lo1 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, t).fma(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + _li * 4).sub(FloatVector.fromArray(SIMD_SPECIES, src, _lo1)), FloatVector.fromArray(SIMD_SPECIES, src, _lo1)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] lerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        for (int _li = 0; _li < 3; _li++) {
            int _lo1 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, t).mul(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + _li * 4).sub(FloatVector.fromArray(SIMD_SPECIES, src, _lo1))).add(FloatVector.fromArray(SIMD_SPECIES, src, _lo1)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static java.lang.foreign.MemorySegment lerp_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        if (SimdSupport.DOUBLE_COLUMNS && SimdSupport.X86 && dest.isNative() && other.isNative() && src.isNative()) {
            var _lwv0 = FloatVector.fromMemorySegment(FloatVector.SPECIES_256, src, srcOffset, java.nio.ByteOrder.nativeOrder());
            FloatVector.broadcast(FloatVector.SPECIES_256, t).fma(FloatVector.fromMemorySegment(FloatVector.SPECIES_256, other, otherOffset, java.nio.ByteOrder.nativeOrder()).sub(_lwv0), _lwv0).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            var _lv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
            FloatVector.broadcast(SIMD_SPECIES, t).fma(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + 32L, java.nio.ByteOrder.nativeOrder()).sub(_lv0), _lv0).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        } else {
            for (int _li = 0; _li < 3; _li++) {
                var _lv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
                FloatVector.broadcast(SIMD_SPECIES, t).fma(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + _li * 16L, java.nio.ByteOrder.nativeOrder()).sub(_lv0), _lv0).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
            }
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        if (SimdSupport.DOUBLE_COLUMNS && SimdSupport.X86 && dest.isNative() && other.isNative() && src.isNative()) {
            var _lwv0 = FloatVector.fromMemorySegment(FloatVector.SPECIES_256, src, srcOffset, java.nio.ByteOrder.nativeOrder());
            FloatVector.broadcast(FloatVector.SPECIES_256, t).mul(FloatVector.fromMemorySegment(FloatVector.SPECIES_256, other, otherOffset, java.nio.ByteOrder.nativeOrder()).sub(_lwv0)).add(_lwv0).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            var _lv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
            FloatVector.broadcast(SIMD_SPECIES, t).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + 32L, java.nio.ByteOrder.nativeOrder()).sub(_lv0)).add(_lv0).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        } else {
            for (int _li = 0; _li < 3; _li++) {
                var _lv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
                FloatVector.broadcast(SIMD_SPECIES, t).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + _li * 16L, java.nio.ByteOrder.nativeOrder()).sub(_lv0)).add(_lv0).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
            }
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
            int _lo0 = srcOffset + _li * 4;
            _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, src[_lo0]), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]), FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] mul_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, right, rightOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, right, rightOffset + 4);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, right, rightOffset + 8);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, src[_lo0])).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 1])).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 2])).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return mul_fma_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
            return mul_fma_api(dest, destOffset, src, srcOffset, right, rightOffset);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return mul_mulAdd_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return mul_mulAdd_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static java.lang.foreign.MemorySegment mul_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return mul_fma_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return mul_fma_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static java.lang.foreign.MemorySegment mul_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        long _srcBase = src.address() + srcOffset;
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, right, rightOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, right, rightOffset + 16L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, right, rightOffset + 32L, java.nio.ByteOrder.nativeOrder());
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L)), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 8L)), FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, right, rightOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, right, rightOffset + 16L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, right, rightOffset + 32L, java.nio.ByteOrder.nativeOrder());
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L)), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)), FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return mul_mulAdd_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return mul_mulAdd_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static java.lang.foreign.MemorySegment mul_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        long _srcBase = src.address() + srcOffset;
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, right, rightOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, right, rightOffset + 16L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, right, rightOffset + 32L, java.nio.ByteOrder.nativeOrder());
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 8L))).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, right, rightOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, right, rightOffset + 16L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, right, rightOffset + 32L, java.nio.ByteOrder.nativeOrder());
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L))).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static float[] mulMat2x3(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        float _right00 = right[rightOffset];
        float _right01 = right[rightOffset + 2];
        float _right10 = right[rightOffset + 1];
        float _right11 = right[rightOffset + 3];
        float _right02 = right[rightOffset + 4];
        float _right12 = right[rightOffset + 5];
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, _right00).withLane(1, _right01).mul(FloatVector.broadcast(SIMD_SPECIES, src[_lo0])).withLane(3, src[_lo0 + 3]).add(FloatVector.broadcast(SIMD_SPECIES, _right10).withLane(1, _right11).mul(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 1])).withLane(3, _right02 * src[_lo0] + _right12 * src[_lo0 + 1])).withLane(2, src[_lo0 + 2]).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulMat2x3(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && right.isNative()) return mulMat2x3_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat2x3_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static java.lang.foreign.MemorySegment mulMat2x3_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        long _srcBase = src.address() + srcOffset;
        long _rightBase = right.address() + rightOffset;
        float _self00 = UnsafeOpsHolder.U.getFloat(_srcBase);
        float _self01 = UnsafeOpsHolder.U.getFloat(_srcBase + 4L);
        float _right02 = UnsafeOpsHolder.U.getFloat(_rightBase + 16L);
        float _right12 = UnsafeOpsHolder.U.getFloat(_rightBase + 20L);
        float _self10 = UnsafeOpsHolder.U.getFloat(_srcBase + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(_srcBase + 20L);
        float _self20 = UnsafeOpsHolder.U.getFloat(_srcBase + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(_srcBase + 36L);
        mulMat2x3_unsafe_s67d66b86_v(dest, destOffset, UnsafeOpsHolder.U.getFloat(_rightBase), UnsafeOpsHolder.U.getFloat(_rightBase + 8L), _self00, UnsafeOpsHolder.U.getFloat(_srcBase + 12L), UnsafeOpsHolder.U.getFloat(_rightBase + 4L), UnsafeOpsHolder.U.getFloat(_rightBase + 12L), _self01, _right02 * _self00 + _right12 * _self01, UnsafeOpsHolder.U.getFloat(_srcBase + 8L), _self10, UnsafeOpsHolder.U.getFloat(_srcBase + 28L), _self11, _right02 * _self10 + _right12 * _self11, UnsafeOpsHolder.U.getFloat(_srcBase + 24L), _self20, UnsafeOpsHolder.U.getFloat(_srcBase + 44L), _self21, _right02 * _self20 + _right12 * _self21, UnsafeOpsHolder.U.getFloat(_srcBase + 40L));
        return dest;
    }

    private static void mulMat2x3_unsafe_s67d66b86_v(java.lang.foreign.MemorySegment dest, long destOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8, float _h9, float _h10, float _h11, float _h12, float _h13, float _h14, float _h15, float _h16, float _h17, float _h18) {
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, _h0).withLane(1, _h1);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, _h4).withLane(1, _h5);
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h2)).withLane(3, _h3).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h6)).withLane(3, _h7)).withLane(2, _h8).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h9)).withLane(3, _h10).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h11)).withLane(3, _h12)).withLane(2, _h13).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h14)).withLane(3, _h15).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h16)).withLane(3, _h17)).withLane(2, _h18).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment mulMat2x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        float _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _right02 = right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rightOffset + 16L);
        float _right12 = right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rightOffset + 20L);
        float _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 36L);
        return mulMat2x3_api_sb92eea6_1(dest, destOffset, src, srcOffset, right, rightOffset, _self00, _self01, _right02, _right12, _self10, _self11, _self20, _self21);
    }

    /** Piece 2 of {@code mulMat2x3_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment mulMat2x3_api_sb92eea6_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset, float _self00, float _self01, float _right02, float _right12, float _self10, float _self11, float _self20, float _self21) {
        mulMat2x3_unsafe_s67d66b86_v(dest, destOffset, right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rightOffset), right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rightOffset + 8L), _self00, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L), right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rightOffset + 4L), right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rightOffset + 12L), _self01, _right02 * _self00 + _right12 * _self01, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), _self10, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 28L), _self11, _right02 * _self10 + _right12 * _self11, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 24L), _self20, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 44L), _self21, _right02 * _self20 + _right12 * _self21, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 40L));
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
        float _right00 = right[rightOffset];
        float _right01 = right[rightOffset + 3];
        float _right02 = right[rightOffset + 6];
        float _right10 = right[rightOffset + 1];
        float _right11 = right[rightOffset + 4];
        float _right12 = right[rightOffset + 7];
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _right20).withLane(1, _right21).withLane(2, _right22);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _right00).withLane(1, _right01).withLane(2, _right02);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _right10).withLane(1, _right11).withLane(2, _right12);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, src[_lo0]), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]), FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] mulMat3x3_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        float _right20 = right[rightOffset + 2];
        float _right21 = right[rightOffset + 5];
        float _right22 = right[rightOffset + 8];
        float _right00 = right[rightOffset];
        float _right01 = right[rightOffset + 3];
        float _right02 = right[rightOffset + 6];
        float _right10 = right[rightOffset + 1];
        float _right11 = right[rightOffset + 4];
        float _right12 = right[rightOffset + 7];
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _right20).withLane(1, _right21).withLane(2, _right22);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _right00).withLane(1, _right01).withLane(2, _right02);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _right10).withLane(1, _right11).withLane(2, _right12);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 2])).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, src[_lo0])).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 1])).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment mulMat3x3(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && right.isNative()) return mulMat3x3_fma_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
            return mulMat3x3_fma_api(dest, destOffset, src, srcOffset, right, rightOffset);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && right.isNative()) return mulMat3x3_mulAdd_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat3x3_mulAdd_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static java.lang.foreign.MemorySegment mulMat3x3_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && right.isNative()) return mulMat3x3_fma_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat3x3_fma_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static java.lang.foreign.MemorySegment mulMat3x3_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        long _rightBase = right.address() + rightOffset;
        mulMat3x3_fma_unsafe_sfcb7544e_v(dest, destOffset, src.address() + srcOffset, UnsafeOpsHolder.U.getFloat(_rightBase + 8L), UnsafeOpsHolder.U.getFloat(_rightBase + 20L), UnsafeOpsHolder.U.getFloat(_rightBase + 32L), UnsafeOpsHolder.U.getFloat(_rightBase), UnsafeOpsHolder.U.getFloat(_rightBase + 12L), UnsafeOpsHolder.U.getFloat(_rightBase + 24L), UnsafeOpsHolder.U.getFloat(_rightBase + 4L), UnsafeOpsHolder.U.getFloat(_rightBase + 16L), UnsafeOpsHolder.U.getFloat(_rightBase + 28L));
        return dest;
    }

    private static void mulMat3x3_fma_unsafe_sfcb7544e_v(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, float _right20, float _right21, float _right22, float _right00, float _right01, float _right02, float _right10, float _right11, float _right12) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _right20).withLane(1, _right21).withLane(2, _right22);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _right00).withLane(1, _right01).withLane(2, _right02);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _right10).withLane(1, _right11).withLane(2, _right12);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 8L)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1)), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L)), FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment mulMat3x3_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        mulMat3x3_fma_api_s5c4f191e_v(dest, destOffset, src, srcOffset, right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rightOffset + 8L), right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rightOffset + 20L), right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rightOffset + 32L), right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rightOffset), right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rightOffset + 12L), right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rightOffset + 24L), right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rightOffset + 4L), right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rightOffset + 16L), right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rightOffset + 28L));
        return dest;
    }

    private static void mulMat3x3_fma_api_s5c4f191e_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _right20, float _right21, float _right22, float _right00, float _right01, float _right02, float _right10, float _right11, float _right12) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _right20).withLane(1, _right21).withLane(2, _right22);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _right00).withLane(1, _right01).withLane(2, _right02);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _right10).withLane(1, _right11).withLane(2, _right12);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L)), FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment mulMat3x3_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && right.isNative()) return mulMat3x3_mulAdd_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat3x3_mulAdd_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static java.lang.foreign.MemorySegment mulMat3x3_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        long _rightBase = right.address() + rightOffset;
        mulMat3x3_mulAdd_unsafe_sf3e87885_v(dest, destOffset, src.address() + srcOffset, UnsafeOpsHolder.U.getFloat(_rightBase + 8L), UnsafeOpsHolder.U.getFloat(_rightBase + 20L), UnsafeOpsHolder.U.getFloat(_rightBase + 32L), UnsafeOpsHolder.U.getFloat(_rightBase), UnsafeOpsHolder.U.getFloat(_rightBase + 12L), UnsafeOpsHolder.U.getFloat(_rightBase + 24L), UnsafeOpsHolder.U.getFloat(_rightBase + 4L), UnsafeOpsHolder.U.getFloat(_rightBase + 16L), UnsafeOpsHolder.U.getFloat(_rightBase + 28L));
        return dest;
    }

    private static void mulMat3x3_mulAdd_unsafe_sf3e87885_v(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, float _right20, float _right21, float _right22, float _right00, float _right01, float _right02, float _right10, float _right11, float _right12) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _right20).withLane(1, _right21).withLane(2, _right22);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _right00).withLane(1, _right01).withLane(2, _right02);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _right10).withLane(1, _right11).withLane(2, _right12);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 8L))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L))).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment mulMat3x3_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        mulMat3x3_mulAdd_api_s5ae0b4cf_v(dest, destOffset, src, srcOffset, right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rightOffset + 8L), right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rightOffset + 20L), right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rightOffset + 32L), right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rightOffset), right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rightOffset + 12L), right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rightOffset + 24L), right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rightOffset + 4L), right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rightOffset + 16L), right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rightOffset + 28L));
        return dest;
    }

    private static void mulMat3x3_mulAdd_api_s5ae0b4cf_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _right20, float _right21, float _right22, float _right00, float _right01, float _right02, float _right10, float _right11, float _right12) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _right20).withLane(1, _right21).withLane(2, _right22);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _right00).withLane(1, _right01).withLane(2, _right02);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _right10).withLane(1, _right11).withLane(2, _right12);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L))).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static float[] mulMat4x4(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        if (SimdSupport.USE_FMA) return mulMat4x4_fma(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat4x4_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static float[] mulMat4x4_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        mulMat4x4_fma_v57b04262(dest, destOffset, right, rightOffset, src[srcOffset + 3], src[srcOffset + 7], src[srcOffset + 11], src[srcOffset + 2], src[srcOffset + 6], src[srcOffset + 10], src[srcOffset], src[srcOffset + 4], src[srcOffset + 8], src[srcOffset + 1], src[srcOffset + 5], src[srcOffset + 9]);
        return dest;
    }

    private static void mulMat4x4_fma_v57b04262(float[] dest, int destOffset, float[] right, int rightOffset, float _self03, float _self13, float _self23, float _self02, float _self12, float _self22, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self03).withLane(1, _self13).withLane(2, _self23);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self02).withLane(1, _self12).withLane(2, _self22);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self00).withLane(1, _self10).withLane(2, _self20);
        var _sv3 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self01).withLane(1, _self11).withLane(2, _self21);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = rightOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, right[_lo0 + 3]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, right[_lo0 + 2]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, right[_lo0]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, right[_lo0 + 1]).fma(_sv3, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, right[_lo0 + 3]))))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] mulMat4x4_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] right, int rightOffset) {
        mulMat4x4_mulAdd_se276a1_v(dest, destOffset, right, rightOffset, src[srcOffset + 3], src[srcOffset + 7], src[srcOffset + 11], src[srcOffset + 2], src[srcOffset + 6], src[srcOffset + 10], src[srcOffset], src[srcOffset + 4], src[srcOffset + 8], src[srcOffset + 1], src[srcOffset + 5], src[srcOffset + 9]);
        return dest;
    }

    private static void mulMat4x4_mulAdd_se276a1_v(float[] dest, int destOffset, float[] right, int rightOffset, float _self03, float _self13, float _self23, float _self02, float _self12, float _self22, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self03).withLane(1, _self13).withLane(2, _self23);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self02).withLane(1, _self12).withLane(2, _self22);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self00).withLane(1, _self10).withLane(2, _self20);
        var _sv3 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self01).withLane(1, _self11).withLane(2, _self21);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = rightOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, right[_lo0 + 3]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, right[_lo0 + 2]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, right[_lo0]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, right[_lo0 + 1]).mul(_sv3).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, right[_lo0 + 3]))))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static java.lang.foreign.MemorySegment mulMat4x4(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && right.isNative()) return mulMat4x4_fma_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
            return mulMat4x4_fma_api(dest, destOffset, src, srcOffset, right, rightOffset);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && right.isNative()) return mulMat4x4_mulAdd_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat4x4_mulAdd_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static java.lang.foreign.MemorySegment mulMat4x4_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && right.isNative()) return mulMat4x4_fma_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat4x4_fma_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static java.lang.foreign.MemorySegment mulMat4x4_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        long _srcBase = src.address() + srcOffset;
        mulMat4x4_fma_unsafe_s599ef746_v(dest, destOffset, right.address() + rightOffset, UnsafeOpsHolder.U.getFloat(_srcBase + 12L), UnsafeOpsHolder.U.getFloat(_srcBase + 28L), UnsafeOpsHolder.U.getFloat(_srcBase + 44L), UnsafeOpsHolder.U.getFloat(_srcBase + 8L), UnsafeOpsHolder.U.getFloat(_srcBase + 24L), UnsafeOpsHolder.U.getFloat(_srcBase + 40L), UnsafeOpsHolder.U.getFloat(_srcBase), UnsafeOpsHolder.U.getFloat(_srcBase + 16L), UnsafeOpsHolder.U.getFloat(_srcBase + 32L), UnsafeOpsHolder.U.getFloat(_srcBase + 4L), UnsafeOpsHolder.U.getFloat(_srcBase + 20L), UnsafeOpsHolder.U.getFloat(_srcBase + 36L));
        return dest;
    }

    private static void mulMat4x4_fma_unsafe_s599ef746_v(java.lang.foreign.MemorySegment dest, long destOffset, long _rightBase, float _self03, float _self13, float _self23, float _self02, float _self12, float _self22, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self03).withLane(1, _self13).withLane(2, _self23);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self02).withLane(1, _self12).withLane(2, _self22);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self00).withLane(1, _self10).withLane(2, _self20);
        var _sv3 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self01).withLane(1, _self11).withLane(2, _self21);
        for (int _li = 0; _li < 4; _li++) {
            long _lb1 = _rightBase + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 12L)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 8L)).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1)).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L)).fma(_sv3, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L)))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment mulMat4x4_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        mulMat4x4_fma_api_s9fd90466_v(dest, destOffset, right, rightOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 28L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 44L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 24L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 40L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 16L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 32L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 20L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 36L));
        return dest;
    }

    private static void mulMat4x4_fma_api_s9fd90466_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment right, long rightOffset, float _self03, float _self13, float _self23, float _self02, float _self12, float _self22, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self03).withLane(1, _self13).withLane(2, _self23);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self02).withLane(1, _self12).withLane(2, _self22);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self00).withLane(1, _self10).withLane(2, _self20);
        var _sv3 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self01).withLane(1, _self11).withLane(2, _self21);
        for (int _li = 0; _li < 4; _li++) {
            long _lo0 = rightOffset + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L)).fma(_sv3, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L)))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment mulMat4x4_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && right.isNative()) return mulMat4x4_mulAdd_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat4x4_mulAdd_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static java.lang.foreign.MemorySegment mulMat4x4_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        long _srcBase = src.address() + srcOffset;
        mulMat4x4_mulAdd_unsafe_s3e1f251d_v(dest, destOffset, right.address() + rightOffset, UnsafeOpsHolder.U.getFloat(_srcBase + 12L), UnsafeOpsHolder.U.getFloat(_srcBase + 28L), UnsafeOpsHolder.U.getFloat(_srcBase + 44L), UnsafeOpsHolder.U.getFloat(_srcBase + 8L), UnsafeOpsHolder.U.getFloat(_srcBase + 24L), UnsafeOpsHolder.U.getFloat(_srcBase + 40L), UnsafeOpsHolder.U.getFloat(_srcBase), UnsafeOpsHolder.U.getFloat(_srcBase + 16L), UnsafeOpsHolder.U.getFloat(_srcBase + 32L), UnsafeOpsHolder.U.getFloat(_srcBase + 4L), UnsafeOpsHolder.U.getFloat(_srcBase + 20L), UnsafeOpsHolder.U.getFloat(_srcBase + 36L));
        return dest;
    }

    private static void mulMat4x4_mulAdd_unsafe_s3e1f251d_v(java.lang.foreign.MemorySegment dest, long destOffset, long _rightBase, float _self03, float _self13, float _self23, float _self02, float _self12, float _self22, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self03).withLane(1, _self13).withLane(2, _self23);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self02).withLane(1, _self12).withLane(2, _self22);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self00).withLane(1, _self10).withLane(2, _self20);
        var _sv3 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self01).withLane(1, _self11).withLane(2, _self21);
        for (int _li = 0; _li < 4; _li++) {
            long _lb1 = _rightBase + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 12L)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 8L)).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1)).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L)).mul(_sv3).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L)))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment mulMat4x4_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        mulMat4x4_mulAdd_api_s10dc5017_v(dest, destOffset, right, rightOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 28L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 44L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 24L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 40L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 16L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 32L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 20L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 36L));
        return dest;
    }

    private static void mulMat4x4_mulAdd_api_s10dc5017_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment right, long rightOffset, float _self03, float _self13, float _self23, float _self02, float _self12, float _self22, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self03).withLane(1, _self13).withLane(2, _self23);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self02).withLane(1, _self12).withLane(2, _self22);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self00).withLane(1, _self10).withLane(2, _self20);
        var _sv3 = FloatVector.zero(SIMD_SPECIES).withLane(0, _self01).withLane(1, _self11).withLane(2, _self21);
        for (int _li = 0; _li < 4; _li++) {
            long _lo0 = rightOffset + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L)).mul(_sv3).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, right.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L)))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
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
            int _lo0 = otherOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, other[_lo0]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, other[_lo0 + 1]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, other[_lo0 + 2]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, other[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] preMul_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = otherOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, other[_lo0]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, other[_lo0 + 1]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, other[_lo0 + 2]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, other[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && other.isNative()) return preMul_fma_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
            return preMul_fma_api(dest, destOffset, src, srcOffset, other, otherOffset);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && other.isNative()) return preMul_mulAdd_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMul_mulAdd_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.lang.foreign.MemorySegment preMul_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && other.isNative()) return preMul_fma_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMul_fma_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.lang.foreign.MemorySegment preMul_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _otherBase = other.address() + otherOffset;
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _otherBase + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L)).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 8L)).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = otherOffset + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L)).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && other.isNative()) return preMul_mulAdd_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMul_mulAdd_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.lang.foreign.MemorySegment preMul_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _otherBase = other.address() + otherOffset;
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _otherBase + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L)).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 8L)).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = otherOffset + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L)).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
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
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 1]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 3]).mul(_sv1));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        FloatVector.broadcast(SIMD_SPECIES, other[otherOffset]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 2]).mul(_sv1)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preMulMat2x2_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 1]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 3]).mul(_sv1));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        FloatVector.broadcast(SIMD_SPECIES, other[otherOffset]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 2]).mul(_sv1)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat2x2(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && other.isNative()) return preMulMat2x2_fma_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
            return preMulMat2x2_fma_api(dest, destOffset, src, srcOffset, other, otherOffset);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && other.isNative()) return preMulMat2x2_mulAdd_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat2x2_mulAdd_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.lang.foreign.MemorySegment preMulMat2x2_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && other.isNative()) return preMulMat2x2_fma_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat2x2_fma_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.lang.foreign.MemorySegment preMulMat2x2_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _otherBase = other.address() + otherOffset;
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder());
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_otherBase + 4L)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_otherBase + 12L)).mul(_sv1));
        var _c2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_otherBase)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_otherBase + 8L)).mul(_sv1)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat2x2_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder());
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 12L)).mul(_sv1));
        var _c2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L)).mul(_sv1)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat2x2_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && other.isNative()) return preMulMat2x2_mulAdd_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat2x2_mulAdd_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.lang.foreign.MemorySegment preMulMat2x2_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _otherBase = other.address() + otherOffset;
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder());
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_otherBase + 4L)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_otherBase + 12L)).mul(_sv1));
        var _c2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_otherBase)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_otherBase + 8L)).mul(_sv1)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat2x2_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder());
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 12L)).mul(_sv1));
        var _c2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L)).mul(_sv1)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
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
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 1]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 3]).fma(_sv1, _sv2.withLane(3, other[otherOffset + 5])));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        FloatVector.broadcast(SIMD_SPECIES, other[otherOffset]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 2]).fma(_sv1, _sv2.withLane(3, other[otherOffset + 4]))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preMulMat2x3_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 1]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 3]).mul(_sv1).add(_sv2.withLane(3, other[otherOffset + 5])));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        FloatVector.broadcast(SIMD_SPECIES, other[otherOffset]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 2]).mul(_sv1).add(_sv2.withLane(3, other[otherOffset + 4]))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat2x3(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && other.isNative()) return preMulMat2x3_fma_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
            return preMulMat2x3_fma_api(dest, destOffset, src, srcOffset, other, otherOffset);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && other.isNative()) return preMulMat2x3_mulAdd_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat2x3_mulAdd_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.lang.foreign.MemorySegment preMulMat2x3_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && other.isNative()) return preMulMat2x3_fma_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat2x3_fma_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.lang.foreign.MemorySegment preMulMat2x3_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _otherBase = other.address() + otherOffset;
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_otherBase + 4L)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_otherBase + 12L)).fma(_sv1, _sv2.withLane(3, UnsafeOpsHolder.U.getFloat(_otherBase + 20L))));
        var _c2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_otherBase)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_otherBase + 8L)).fma(_sv1, _sv2.withLane(3, UnsafeOpsHolder.U.getFloat(_otherBase + 16L)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat2x3_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 12L)).fma(_sv1, _sv2.withLane(3, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 20L))));
        var _c2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L)).fma(_sv1, _sv2.withLane(3, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 16L)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat2x3_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && other.isNative()) return preMulMat2x3_mulAdd_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat2x3_mulAdd_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.lang.foreign.MemorySegment preMulMat2x3_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _otherBase = other.address() + otherOffset;
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_otherBase + 4L)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_otherBase + 12L)).mul(_sv1).add(_sv2.withLane(3, UnsafeOpsHolder.U.getFloat(_otherBase + 20L))));
        var _c2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_otherBase)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_otherBase + 8L)).mul(_sv1).add(_sv2.withLane(3, UnsafeOpsHolder.U.getFloat(_otherBase + 16L)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat2x3_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 12L)).mul(_sv1).add(_sv2.withLane(3, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 20L))));
        var _c2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L)).mul(_sv1).add(_sv2.withLane(3, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 16L)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
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
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 7]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 1]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 4]).mul(_sv2)));
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 8]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 2]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 5]).mul(_sv2)));
        FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 6]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, other[otherOffset]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 3]).mul(_sv2))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preMulMat3x3_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 7]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 1]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 4]).mul(_sv2)));
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 8]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 2]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 5]).mul(_sv2)));
        FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 6]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, other[otherOffset]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, other[otherOffset + 3]).mul(_sv2))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat3x3(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && other.isNative()) return preMulMat3x3_fma_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
            return preMulMat3x3_fma_api(dest, destOffset, src, srcOffset, other, otherOffset);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && other.isNative()) return preMulMat3x3_mulAdd_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat3x3_mulAdd_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.lang.foreign.MemorySegment preMulMat3x3_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && other.isNative()) return preMulMat3x3_fma_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat3x3_fma_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.lang.foreign.MemorySegment preMulMat3x3_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _otherBase = other.address() + otherOffset;
        preMulMat3x3_fma_unsafe_s4246b911_v(dest, destOffset, src, srcOffset, UnsafeOpsHolder.U.getFloat(_otherBase + 24L), UnsafeOpsHolder.U.getFloat(_otherBase), UnsafeOpsHolder.U.getFloat(_otherBase + 12L), UnsafeOpsHolder.U.getFloat(_otherBase + 28L), UnsafeOpsHolder.U.getFloat(_otherBase + 4L), UnsafeOpsHolder.U.getFloat(_otherBase + 16L), UnsafeOpsHolder.U.getFloat(_otherBase + 32L), UnsafeOpsHolder.U.getFloat(_otherBase + 8L), UnsafeOpsHolder.U.getFloat(_otherBase + 20L));
        return dest;
    }

    private static void preMulMat3x3_fma_unsafe_s4246b911_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, _h0).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, _h1).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, _h2).mul(_sv2))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, _h3).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, _h4).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, _h5).mul(_sv2))).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, _h6).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, _h7).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, _h8).mul(_sv2))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment preMulMat3x3_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        preMulMat3x3_fma_unsafe_s4246b911_v(dest, destOffset, src, srcOffset, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 24L), other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset), other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 12L), other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 28L), other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L), other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 16L), other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 32L), other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L), other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 20L));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat3x3_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && other.isNative()) return preMulMat3x3_mulAdd_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat3x3_mulAdd_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.lang.foreign.MemorySegment preMulMat3x3_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _otherBase = other.address() + otherOffset;
        preMulMat3x3_mulAdd_unsafe_sbc16c30_v(dest, destOffset, src, srcOffset, UnsafeOpsHolder.U.getFloat(_otherBase + 24L), UnsafeOpsHolder.U.getFloat(_otherBase), UnsafeOpsHolder.U.getFloat(_otherBase + 12L), UnsafeOpsHolder.U.getFloat(_otherBase + 28L), UnsafeOpsHolder.U.getFloat(_otherBase + 4L), UnsafeOpsHolder.U.getFloat(_otherBase + 16L), UnsafeOpsHolder.U.getFloat(_otherBase + 32L), UnsafeOpsHolder.U.getFloat(_otherBase + 8L), UnsafeOpsHolder.U.getFloat(_otherBase + 20L));
        return dest;
    }

    private static void preMulMat3x3_mulAdd_unsafe_sbc16c30_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, _h0).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, _h1).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, _h2).mul(_sv2))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, _h3).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, _h4).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, _h5).mul(_sv2))).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, _h6).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, _h7).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, _h8).mul(_sv2))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment preMulMat3x3_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        preMulMat3x3_mulAdd_unsafe_sbc16c30_v(dest, destOffset, src, srcOffset, other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 24L), other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset), other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 12L), other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 28L), other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 4L), other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 16L), other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 32L), other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 8L), other.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, otherOffset + 20L));
        return dest;
    }

    public static float[] preMulMat4x4(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        if (SimdSupport.USE_FMA) return preMulMat4x4_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat4x4_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static float[] preMulMat4x4_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        preMulMat4x4_fma_sb916082d_v(dest, destOffset, other, otherOffset, src[srcOffset + 8], src[srcOffset], src[srcOffset + 4], src[srcOffset + 9], src[srcOffset + 1], src[srcOffset + 5], src[srcOffset + 10], src[srcOffset + 2], src[srcOffset + 6], src[srcOffset + 3], src[srcOffset + 7], src[srcOffset + 11]);
        return dest;
    }

    private static void preMulMat4x4_fma_sb916082d_v(float[] dest, int destOffset, float[] other, int otherOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8, float _h9, float _h10, float _h11) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, other, otherOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + 4);
        var _c3 = _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h9), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _h10), _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h11), FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + 12))));
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h0), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h1), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h2)))).intoArray(dest, destOffset);
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h3), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h4), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h5)))).intoArray(dest, destOffset + 4);
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h6), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h7), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h8)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static float[] preMulMat4x4_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        preMulMat4x4_mulAdd_s81140ba0_v(dest, destOffset, other, otherOffset, src[srcOffset + 8], src[srcOffset], src[srcOffset + 4], src[srcOffset + 9], src[srcOffset + 1], src[srcOffset + 5], src[srcOffset + 10], src[srcOffset + 2], src[srcOffset + 6], src[srcOffset + 3], src[srcOffset + 7], src[srcOffset + 11]);
        return dest;
    }

    private static void preMulMat4x4_mulAdd_s81140ba0_v(float[] dest, int destOffset, float[] other, int otherOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8, float _h9, float _h10, float _h11) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, other, otherOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + 4);
        var _c3 = _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h9)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h10)).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h11)).add(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + 12))));
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h0)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h1)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h2)))).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h3)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h4)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h5)))).intoArray(dest, destOffset + 4);
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h6)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h7)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h8)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static java.lang.foreign.MemorySegment preMulMat4x4(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return preMulMat4x4_fma_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
            return preMulMat4x4_fma_api(dest, destOffset, src, srcOffset, other, otherOffset);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return preMulMat4x4_mulAdd_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat4x4_mulAdd_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.lang.foreign.MemorySegment preMulMat4x4_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return preMulMat4x4_fma_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat4x4_fma_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.lang.foreign.MemorySegment preMulMat4x4_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        preMulMat4x4_fma_unsafe_sceb040a9_v(dest, destOffset, other, otherOffset, UnsafeOpsHolder.U.getFloat(_srcBase + 32L), UnsafeOpsHolder.U.getFloat(_srcBase), UnsafeOpsHolder.U.getFloat(_srcBase + 16L), UnsafeOpsHolder.U.getFloat(_srcBase + 36L), UnsafeOpsHolder.U.getFloat(_srcBase + 4L), UnsafeOpsHolder.U.getFloat(_srcBase + 20L), UnsafeOpsHolder.U.getFloat(_srcBase + 40L), UnsafeOpsHolder.U.getFloat(_srcBase + 8L), UnsafeOpsHolder.U.getFloat(_srcBase + 24L), UnsafeOpsHolder.U.getFloat(_srcBase + 12L), UnsafeOpsHolder.U.getFloat(_srcBase + 28L), UnsafeOpsHolder.U.getFloat(_srcBase + 44L));
        return dest;
    }

    private static void preMulMat4x4_fma_unsafe_sceb040a9_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment other, long otherOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8, float _h9, float _h10, float _h11) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + 16L, java.nio.ByteOrder.nativeOrder());
        var _c3 = _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h9), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _h10), _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h11), FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + 48L, java.nio.ByteOrder.nativeOrder()))));
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h0), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h1), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h2)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h3), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h4), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h5)))).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h6), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h7), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h8)))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c3.intoMemorySegment(dest, destOffset + 48L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment preMulMat4x4_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        preMulMat4x4_fma_unsafe_sceb040a9_v(dest, destOffset, other, otherOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 32L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 16L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 36L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 20L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 40L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 24L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 28L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 44L));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat4x4_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return preMulMat4x4_mulAdd_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat4x4_mulAdd_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.lang.foreign.MemorySegment preMulMat4x4_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + 16L, java.nio.ByteOrder.nativeOrder());
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_srcBase + 36L))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_srcBase + 4L))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_srcBase + 20L)))));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_srcBase + 40L))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_srcBase + 8L))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_srcBase + 24L)))));
        var _c3 = _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_srcBase + 12L))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_srcBase + 28L))).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_srcBase + 44L))).add(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + 48L, java.nio.ByteOrder.nativeOrder()))));
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_srcBase + 32L))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_srcBase))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_srcBase + 16L))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c3.intoMemorySegment(dest, destOffset + 48L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat4x4_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + 16L, java.nio.ByteOrder.nativeOrder());
        var _c1 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 36L))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 20L)))));
        var _c2 = _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 40L))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 24L)))));
        var _c3 = _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 28L))).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 44L))).add(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + 48L, java.nio.ByteOrder.nativeOrder()))));
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 32L))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 16L))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c3.intoMemorySegment(dest, destOffset + 48L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] addScaled(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float weight) {
        if (SimdSupport.USE_FMA) return addScaled_fma(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return addScaled_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    public static float[] addScaled_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float weight) {
        for (int _li = 0; _li < 3; _li++) {
            FloatVector.broadcast(SIMD_SPECIES, weight).fma(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + _li * 4), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] addScaled_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float weight) {
        for (int _li = 0; _li < 3; _li++) {
            FloatVector.broadcast(SIMD_SPECIES, weight).mul(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset + _li * 4)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float weight) {
        if (SimdSupport.USE_FMA) return addScaled_fma(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return addScaled_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    public static java.lang.foreign.MemorySegment addScaled_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float weight) {
        if (SimdSupport.DOUBLE_COLUMNS && SimdSupport.X86 && dest.isNative() && other.isNative() && src.isNative()) {
            FloatVector.broadcast(FloatVector.SPECIES_256, weight).fma(FloatVector.fromMemorySegment(FloatVector.SPECIES_256, other, otherOffset, java.nio.ByteOrder.nativeOrder()), FloatVector.fromMemorySegment(FloatVector.SPECIES_256, src, srcOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            FloatVector.broadcast(SIMD_SPECIES, weight).fma(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + 32L, java.nio.ByteOrder.nativeOrder()), FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        } else {
            for (int _li = 0; _li < 3; _li++) {
                FloatVector.broadcast(SIMD_SPECIES, weight).fma(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + _li * 16L, java.nio.ByteOrder.nativeOrder()), FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 16L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
            }
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float weight) {
        if (SimdSupport.DOUBLE_COLUMNS && SimdSupport.X86 && dest.isNative() && other.isNative() && src.isNative()) {
            FloatVector.broadcast(FloatVector.SPECIES_256, weight).mul(FloatVector.fromMemorySegment(FloatVector.SPECIES_256, other, otherOffset, java.nio.ByteOrder.nativeOrder())).add(FloatVector.fromMemorySegment(FloatVector.SPECIES_256, src, srcOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            FloatVector.broadcast(SIMD_SPECIES, weight).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + 32L, java.nio.ByteOrder.nativeOrder())).add(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        } else {
            for (int _li = 0; _li < 3; _li++) {
                FloatVector.broadcast(SIMD_SPECIES, weight).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + _li * 16L, java.nio.ByteOrder.nativeOrder())).add(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 16L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
            }
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
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h0), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h1), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _h2), _sv3.withLane(3, translationX)))).intoArray(dest, destOffset);
        _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _h3), _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h4), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h5), _sv3.withLane(3, translationY)))).intoArray(dest, destOffset + 4);
        _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h6), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _h7), _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h8), _sv3.withLane(3, translationZ)))).intoArray(dest, destOffset + 8);
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
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h0)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h1)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h2)).add(_sv3.withLane(3, translationX)))).intoArray(dest, destOffset);
        _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h3)).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h4)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h5)).add(_sv3.withLane(3, translationY)))).intoArray(dest, destOffset + 4);
        _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h6)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h7)).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h8)).add(_sv3.withLane(3, translationZ)))).intoArray(dest, destOffset + 8);
    }

    public static java.lang.foreign.MemorySegment composeTRSMul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset, float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ) {
        if (SimdSupport.USE_FMA) return composeTRSMul_fma(dest, destOffset, m, mOffset, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
        return composeTRSMul_mulAdd(dest, destOffset, m, mOffset, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
    }

    public static java.lang.foreign.MemorySegment composeTRSMul_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset, float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ) {
        float _t0 = scaleZ + scaleZ;
        float _t1 = scaleX + scaleX;
        float _t2 = scaleY + scaleY;
        float _t3 = rotationY * rotationW;
        float _t4 = rotationZ * rotationZ;
        float _t5 = rotationZ * rotationW;
        composeTRSMul_fma_s9f066040_v(dest, destOffset, m, mOffset, translationX, translationY, translationZ, Math.fma(rotationX, rotationZ, _t3) * _t0, Math.fma(rotationX, rotationY, -_t5) * _t2, Math.fma(-Math.fma(rotationY, rotationY, _t4), _t1, scaleX), Math.fma(rotationX, rotationY, _t5) * _t1, Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t0, Math.fma(-Math.fma(rotationX, rotationX, _t4), _t2, scaleY), Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t2, Math.fma(rotationX, rotationZ, -_t3) * _t1, Math.fma(-Math.fma(rotationX, rotationX, rotationY * rotationY), _t0, scaleZ));
        return dest;
    }

    private static void composeTRSMul_fma_s9f066040_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset, float translationX, float translationY, float translationZ, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, m, mOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, m, mOffset + 16L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, m, mOffset, java.nio.ByteOrder.nativeOrder());
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h0), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h1), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _h2), _sv3.withLane(3, translationX)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _h3), _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h4), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h5), _sv3.withLane(3, translationY)))).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h6), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _h7), _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h8), _sv3.withLane(3, translationZ)))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment composeTRSMul_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset, float translationX, float translationY, float translationZ, float rotationX, float rotationY, float rotationZ, float rotationW, float scaleX, float scaleY, float scaleZ) {
        float _t0 = scaleZ + scaleZ;
        float _t1 = scaleX + scaleX;
        float _t2 = scaleY + scaleY;
        float _t3 = rotationY * rotationW;
        float _t4 = rotationZ * rotationZ;
        float _t5 = rotationZ * rotationW;
        composeTRSMul_mulAdd_s97fed5e5_v(dest, destOffset, m, mOffset, translationX, translationY, translationZ, Math.fma(rotationX, rotationZ, _t3) * _t0, Math.fma(rotationX, rotationY, -_t5) * _t2, Math.fma(-Math.fma(rotationY, rotationY, _t4), _t1, scaleX), Math.fma(rotationX, rotationY, _t5) * _t1, Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t0, Math.fma(-Math.fma(rotationX, rotationX, _t4), _t2, scaleY), Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t2, Math.fma(rotationX, rotationZ, -_t3) * _t1, Math.fma(-Math.fma(rotationX, rotationX, rotationY * rotationY), _t0, scaleZ));
        return dest;
    }

    private static void composeTRSMul_mulAdd_s97fed5e5_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset, float translationX, float translationY, float translationZ, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, m, mOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, m, mOffset + 16L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, m, mOffset, java.nio.ByteOrder.nativeOrder());
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h0)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h1)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h2)).add(_sv3.withLane(3, translationX)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h3)).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h4)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h5)).add(_sv3.withLane(3, translationY)))).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h6)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h7)).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h8)).add(_sv3.withLane(3, translationZ)))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
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
        composeTRSMul_fma_s9c518a4d_v(dest, destOffset, m, mOffset, Math.fma(_rotationx, _rotationz, _t3) * _t0, Math.fma(_rotationx, _rotationy, -_t5) * _t2, Math.fma(-Math.fma(_rotationy, _rotationy, _t4), _t1, _scalex), translation[translationOffset], Math.fma(_rotationx, _rotationy, _t5) * _t1, Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t0, Math.fma(-Math.fma(_rotationx, _rotationx, _t4), _t2, _scaley), translation[translationOffset + 1], Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t2, Math.fma(_rotationx, _rotationz, -_t3) * _t1, Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t0, _scalez), translation[translationOffset + 2]);
        return dest;
    }

    private static void composeTRSMul_fma_s9c518a4d_v(float[] dest, int destOffset, float[] m, int mOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8, float _h9, float _h10, float _h11) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, m, mOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, m, mOffset + 4);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, m, mOffset);
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h0), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h1), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _h2), _sv3.withLane(3, _h3)))).intoArray(dest, destOffset);
        _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _h4), _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h5), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h6), _sv3.withLane(3, _h7)))).intoArray(dest, destOffset + 4);
        _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h8), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _h9), _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h10), _sv3.withLane(3, _h11)))).intoArray(dest, destOffset + 8);
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
        composeTRSMul_mulAdd_sc1e6fe58_v(dest, destOffset, m, mOffset, Math.fma(_rotationx, _rotationz, _t3) * _t0, Math.fma(_rotationx, _rotationy, -_t5) * _t2, Math.fma(-Math.fma(_rotationy, _rotationy, _t4), _t1, _scalex), translation[translationOffset], Math.fma(_rotationx, _rotationy, _t5) * _t1, Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t0, Math.fma(-Math.fma(_rotationx, _rotationx, _t4), _t2, _scaley), translation[translationOffset + 1], Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t2, Math.fma(_rotationx, _rotationz, -_t3) * _t1, Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t0, _scalez), translation[translationOffset + 2]);
        return dest;
    }

    private static void composeTRSMul_mulAdd_sc1e6fe58_v(float[] dest, int destOffset, float[] m, int mOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8, float _h9, float _h10, float _h11) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, m, mOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, m, mOffset + 4);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, m, mOffset);
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h0)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h1)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h2)).add(_sv3.withLane(3, _h3)))).intoArray(dest, destOffset);
        _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h4)).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h5)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h6)).add(_sv3.withLane(3, _h7)))).intoArray(dest, destOffset + 4);
        _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h8)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h9)).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h10)).add(_sv3.withLane(3, _h11)))).intoArray(dest, destOffset + 8);
    }

    public static java.lang.foreign.MemorySegment composeTRSMul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment translation, long translationOffset, java.lang.foreign.MemorySegment rotation, long rotationOffset, java.lang.foreign.MemorySegment scale, long scaleOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && translation.isNative() && rotation.isNative() && scale.isNative()) return composeTRSMul_fma_unsafe(dest, destOffset, translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, m, mOffset);
            return composeTRSMul_fma_api(dest, destOffset, translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, m, mOffset);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && translation.isNative() && rotation.isNative() && scale.isNative()) return composeTRSMul_mulAdd_unsafe(dest, destOffset, translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, m, mOffset);
        return composeTRSMul_mulAdd_api(dest, destOffset, translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, m, mOffset);
    }

    public static java.lang.foreign.MemorySegment composeTRSMul_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment translation, long translationOffset, java.lang.foreign.MemorySegment rotation, long rotationOffset, java.lang.foreign.MemorySegment scale, long scaleOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && translation.isNative() && rotation.isNative() && scale.isNative()) return composeTRSMul_fma_unsafe(dest, destOffset, translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, m, mOffset);
        return composeTRSMul_fma_api(dest, destOffset, translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, m, mOffset);
    }

    public static java.lang.foreign.MemorySegment composeTRSMul_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment translation, long translationOffset, java.lang.foreign.MemorySegment rotation, long rotationOffset, java.lang.foreign.MemorySegment scale, long scaleOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        long _translationBase = translation.address() + translationOffset;
        long _rotationBase = rotation.address() + rotationOffset;
        long _scaleBase = scale.address() + scaleOffset;
        float _scalez = UnsafeOpsHolder.U.getFloat(_scaleBase + 8L);
        float _scalex = UnsafeOpsHolder.U.getFloat(_scaleBase);
        float _scaley = UnsafeOpsHolder.U.getFloat(_scaleBase + 4L);
        float _rotationy = UnsafeOpsHolder.U.getFloat(_rotationBase + 4L);
        float _rotationw = UnsafeOpsHolder.U.getFloat(_rotationBase + 12L);
        float _rotationz = UnsafeOpsHolder.U.getFloat(_rotationBase + 8L);
        float _rotationx = UnsafeOpsHolder.U.getFloat(_rotationBase);
        return composeTRSMul_fma_unsafe_sddd6f2c5_1(dest, destOffset, m, mOffset, _translationBase, _scalez, _scalex, _scaley, _rotationy, _rotationw, _rotationz, _rotationx, _scalez + _scalez, _scalex + _scalex, _scaley + _scaley, _rotationy * _rotationw, _rotationz * _rotationz, _rotationz * _rotationw);
    }

    /** Piece 2 of {@code composeTRSMul_fma_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment composeTRSMul_fma_unsafe_sddd6f2c5_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset, long _translationBase, float _scalez, float _scalex, float _scaley, float _rotationy, float _rotationw, float _rotationz, float _rotationx, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5) {
        composeTRSMul_fma_unsafe_s1a7baa51_v(dest, destOffset, m, mOffset, Math.fma(_rotationx, _rotationz, _t3) * _t0, Math.fma(_rotationx, _rotationy, -_t5) * _t2, Math.fma(-Math.fma(_rotationy, _rotationy, _t4), _t1, _scalex), UnsafeOpsHolder.U.getFloat(_translationBase), Math.fma(_rotationx, _rotationy, _t5) * _t1, Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t0, Math.fma(-Math.fma(_rotationx, _rotationx, _t4), _t2, _scaley), UnsafeOpsHolder.U.getFloat(_translationBase + 4L), Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t2, Math.fma(_rotationx, _rotationz, -_t3) * _t1, Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t0, _scalez), UnsafeOpsHolder.U.getFloat(_translationBase + 8L));
        return dest;
    }

    private static void composeTRSMul_fma_unsafe_s1a7baa51_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8, float _h9, float _h10, float _h11) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, m, mOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, m, mOffset + 16L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, m, mOffset, java.nio.ByteOrder.nativeOrder());
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h0), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h1), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _h2), _sv3.withLane(3, _h3)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _h4), _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h5), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h6), _sv3.withLane(3, _h7)))).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h8), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _h9), _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h10), _sv3.withLane(3, _h11)))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment composeTRSMul_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment translation, long translationOffset, java.lang.foreign.MemorySegment rotation, long rotationOffset, java.lang.foreign.MemorySegment scale, long scaleOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        float _scalez = scale.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, scaleOffset + 8L);
        float _scalex = scale.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, scaleOffset);
        float _scaley = scale.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, scaleOffset + 4L);
        float _rotationy = rotation.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rotationOffset + 4L);
        float _rotationw = rotation.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rotationOffset + 12L);
        float _rotationz = rotation.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rotationOffset + 8L);
        float _rotationx = rotation.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rotationOffset);
        return composeTRSMul_fma_api_se6d0489b_1(dest, destOffset, translation, translationOffset, m, mOffset, _scalez, _scalex, _scaley, _rotationy, _rotationw, _rotationz, _rotationx, _scalez + _scalez, _scalex + _scalex, _scaley + _scaley, _rotationy * _rotationw, _rotationz * _rotationz, _rotationz * _rotationw);
    }

    /** Piece 2 of {@code composeTRSMul_fma_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment composeTRSMul_fma_api_se6d0489b_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment translation, long translationOffset, java.lang.foreign.MemorySegment m, long mOffset, float _scalez, float _scalex, float _scaley, float _rotationy, float _rotationw, float _rotationz, float _rotationx, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5) {
        composeTRSMul_fma_unsafe_s1a7baa51_v(dest, destOffset, m, mOffset, Math.fma(_rotationx, _rotationz, _t3) * _t0, Math.fma(_rotationx, _rotationy, -_t5) * _t2, Math.fma(-Math.fma(_rotationy, _rotationy, _t4), _t1, _scalex), translation.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, translationOffset), Math.fma(_rotationx, _rotationy, _t5) * _t1, Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t0, Math.fma(-Math.fma(_rotationx, _rotationx, _t4), _t2, _scaley), translation.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, translationOffset + 4L), Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t2, Math.fma(_rotationx, _rotationz, -_t3) * _t1, Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t0, _scalez), translation.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, translationOffset + 8L));
        return dest;
    }

    public static java.lang.foreign.MemorySegment composeTRSMul_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment translation, long translationOffset, java.lang.foreign.MemorySegment rotation, long rotationOffset, java.lang.foreign.MemorySegment scale, long scaleOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && translation.isNative() && rotation.isNative() && scale.isNative()) return composeTRSMul_mulAdd_unsafe(dest, destOffset, translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, m, mOffset);
        return composeTRSMul_mulAdd_api(dest, destOffset, translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, m, mOffset);
    }

    public static java.lang.foreign.MemorySegment composeTRSMul_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment translation, long translationOffset, java.lang.foreign.MemorySegment rotation, long rotationOffset, java.lang.foreign.MemorySegment scale, long scaleOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        long _translationBase = translation.address() + translationOffset;
        long _rotationBase = rotation.address() + rotationOffset;
        long _scaleBase = scale.address() + scaleOffset;
        float _scalez = UnsafeOpsHolder.U.getFloat(_scaleBase + 8L);
        float _scalex = UnsafeOpsHolder.U.getFloat(_scaleBase);
        float _scaley = UnsafeOpsHolder.U.getFloat(_scaleBase + 4L);
        float _rotationy = UnsafeOpsHolder.U.getFloat(_rotationBase + 4L);
        float _rotationw = UnsafeOpsHolder.U.getFloat(_rotationBase + 12L);
        float _rotationz = UnsafeOpsHolder.U.getFloat(_rotationBase + 8L);
        float _rotationx = UnsafeOpsHolder.U.getFloat(_rotationBase);
        return composeTRSMul_mulAdd_unsafe_sd50414c8_1(dest, destOffset, m, mOffset, _translationBase, _scalez, _scalex, _scaley, _rotationy, _rotationw, _rotationz, _rotationx, _scalez + _scalez, _scalex + _scalex, _scaley + _scaley, _rotationy * _rotationw, _rotationz * _rotationz, _rotationz * _rotationw);
    }

    /** Piece 2 of {@code composeTRSMul_mulAdd_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment composeTRSMul_mulAdd_unsafe_sd50414c8_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset, long _translationBase, float _scalez, float _scalex, float _scaley, float _rotationy, float _rotationw, float _rotationz, float _rotationx, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5) {
        composeTRSMul_mulAdd_unsafe_sac5793f0_v(dest, destOffset, m, mOffset, Math.fma(_rotationx, _rotationz, _t3) * _t0, Math.fma(_rotationx, _rotationy, -_t5) * _t2, Math.fma(-Math.fma(_rotationy, _rotationy, _t4), _t1, _scalex), UnsafeOpsHolder.U.getFloat(_translationBase), Math.fma(_rotationx, _rotationy, _t5) * _t1, Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t0, Math.fma(-Math.fma(_rotationx, _rotationx, _t4), _t2, _scaley), UnsafeOpsHolder.U.getFloat(_translationBase + 4L), Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t2, Math.fma(_rotationx, _rotationz, -_t3) * _t1, Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t0, _scalez), UnsafeOpsHolder.U.getFloat(_translationBase + 8L));
        return dest;
    }

    private static void composeTRSMul_mulAdd_unsafe_sac5793f0_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8, float _h9, float _h10, float _h11) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, m, mOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, m, mOffset + 16L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, m, mOffset, java.nio.ByteOrder.nativeOrder());
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h0)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h1)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h2)).add(_sv3.withLane(3, _h3)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h4)).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h5)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h6)).add(_sv3.withLane(3, _h7)))).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h8)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h9)).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h10)).add(_sv3.withLane(3, _h11)))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment composeTRSMul_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment translation, long translationOffset, java.lang.foreign.MemorySegment rotation, long rotationOffset, java.lang.foreign.MemorySegment scale, long scaleOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        float _scalez = scale.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, scaleOffset + 8L);
        float _scalex = scale.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, scaleOffset);
        float _scaley = scale.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, scaleOffset + 4L);
        float _rotationy = rotation.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rotationOffset + 4L);
        float _rotationw = rotation.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rotationOffset + 12L);
        float _rotationz = rotation.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rotationOffset + 8L);
        float _rotationx = rotation.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rotationOffset);
        return composeTRSMul_mulAdd_api_sc3df1f58_1(dest, destOffset, translation, translationOffset, m, mOffset, _scalez, _scalex, _scaley, _rotationy, _rotationw, _rotationz, _rotationx, _scalez + _scalez, _scalex + _scalex, _scaley + _scaley, _rotationy * _rotationw, _rotationz * _rotationz, _rotationz * _rotationw);
    }

    /** Piece 2 of {@code composeTRSMul_mulAdd_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment composeTRSMul_mulAdd_api_sc3df1f58_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment translation, long translationOffset, java.lang.foreign.MemorySegment m, long mOffset, float _scalez, float _scalex, float _scaley, float _rotationy, float _rotationw, float _rotationz, float _rotationx, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5) {
        composeTRSMul_mulAdd_unsafe_sac5793f0_v(dest, destOffset, m, mOffset, Math.fma(_rotationx, _rotationz, _t3) * _t0, Math.fma(_rotationx, _rotationy, -_t5) * _t2, Math.fma(-Math.fma(_rotationy, _rotationy, _t4), _t1, _scalex), translation.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, translationOffset), Math.fma(_rotationx, _rotationy, _t5) * _t1, Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t0, Math.fma(-Math.fma(_rotationx, _rotationx, _t4), _t2, _scaley), translation.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, translationOffset + 4L), Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t2, Math.fma(_rotationx, _rotationz, -_t3) * _t1, Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t0, _scalez), translation.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, translationOffset + 8L));
        return dest;
    }

    public static float[] lookAlong(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (SimdSupport.USE_FMA) return lookAlong_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static float[] lookAlong_fma(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
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
        float _t17 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        float _t18 = Math.fma(_t17, _t12, upX);
        float _t19 = Math.fma(_t17, _t13, upY);
        float _t20 = Math.fma(_t17, _t11, upZ);
        float _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
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
        lookAlong_fma_s93d3deb8_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    private static void lookAlong_fma_s93d3deb8_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t37).withLane(1, Math.fma(_t39, _t12, -(_t38 * _t13))).withLane(2, _t11);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t38).withLane(1, Math.fma(_t37, _t13, -(_t39 * _t11))).withLane(2, _t12);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t39).withLane(1, Math.fma(_t38, _t11, -(_t37 * _t12))).withLane(2, _t13);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, src[_lo0]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] lookAlong_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
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
        float _t17 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        float _t18 = Math.fma(_t17, _t12, upX);
        float _t19 = Math.fma(_t17, _t13, upY);
        float _t20 = Math.fma(_t17, _t11, upZ);
        float _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
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
        lookAlong_mulAdd_sc16e9115_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    private static void lookAlong_mulAdd_sc16e9115_v(float[] dest, int destOffset, float[] src, int srcOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t37).withLane(1, Math.fma(_t39, _t12, -(_t38 * _t13))).withLane(2, _t11);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t38).withLane(1, Math.fma(_t37, _t13, -(_t39 * _t11))).withLane(2, _t12);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t39).withLane(1, Math.fma(_t38, _t11, -(_t37 * _t12))).withLane(2, _t13);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static java.lang.foreign.MemorySegment lookAlong(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return lookAlong_fma_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
            return lookAlong_fma_api(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return lookAlong_mulAdd_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_mulAdd_api(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.lang.foreign.MemorySegment lookAlong_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return lookAlong_fma_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_fma_api(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.lang.foreign.MemorySegment lookAlong_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
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
        float _t17 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        float _t18 = Math.fma(_t17, _t12, upX);
        float _t19 = Math.fma(_t17, _t13, upY);
        float _t20 = Math.fma(_t17, _t11, upZ);
        float _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
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
        lookAlong_fma_unsafe_s91aab55b_v(dest, destOffset, src.address() + srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    private static void lookAlong_fma_unsafe_s91aab55b_v(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t37).withLane(1, Math.fma(_t39, _t12, -(_t38 * _t13))).withLane(2, _t11);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t38).withLane(1, Math.fma(_t37, _t13, -(_t39 * _t11))).withLane(2, _t12);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t39).withLane(1, Math.fma(_t38, _t11, -(_t37 * _t12))).withLane(2, _t13);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 8L)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1)).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L)).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment lookAlong_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
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
        float _t17 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        float _t18 = Math.fma(_t17, _t12, upX);
        float _t19 = Math.fma(_t17, _t13, upY);
        float _t20 = Math.fma(_t17, _t11, upZ);
        float _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
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
        lookAlong_fma_api_sf4bf46bb_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    private static void lookAlong_fma_api_sf4bf46bb_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t37).withLane(1, Math.fma(_t39, _t12, -(_t38 * _t13))).withLane(2, _t11);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t38).withLane(1, Math.fma(_t37, _t13, -(_t39 * _t11))).withLane(2, _t12);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t39).withLane(1, Math.fma(_t38, _t11, -(_t37 * _t12))).withLane(2, _t13);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L)).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment lookAlong_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return lookAlong_mulAdd_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_mulAdd_api(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.lang.foreign.MemorySegment lookAlong_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
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
        float _t17 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        float _t18 = Math.fma(_t17, _t12, upX);
        float _t19 = Math.fma(_t17, _t13, upY);
        float _t20 = Math.fma(_t17, _t11, upZ);
        float _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
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
        lookAlong_mulAdd_unsafe_sd9a1ef50_v(dest, destOffset, src.address() + srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    private static void lookAlong_mulAdd_unsafe_sd9a1ef50_v(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t37).withLane(1, Math.fma(_t39, _t12, -(_t38 * _t13))).withLane(2, _t11);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t38).withLane(1, Math.fma(_t37, _t13, -(_t39 * _t11))).withLane(2, _t12);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t39).withLane(1, Math.fma(_t38, _t11, -(_t37 * _t12))).withLane(2, _t13);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 8L)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1)).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L)).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment lookAlong_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float dirX, float dirY, float dirZ, float upX, float upY, float upZ) {
        float _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
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
        float _t17 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        float _t18 = Math.fma(_t17, _t12, upX);
        float _t19 = Math.fma(_t17, _t13, upY);
        float _t20 = Math.fma(_t17, _t11, upZ);
        float _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
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
        lookAlong_mulAdd_api_s5a15b0fe_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    private static void lookAlong_mulAdd_api_s5a15b0fe_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _t11, float _t12, float _t13, float _t37, float _t38, float _t39) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t37).withLane(1, Math.fma(_t39, _t12, -(_t38 * _t13))).withLane(2, _t11);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t38).withLane(1, Math.fma(_t37, _t13, -(_t39 * _t11))).withLane(2, _t12);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t39).withLane(1, Math.fma(_t38, _t11, -(_t37 * _t12))).withLane(2, _t13);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L)).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static float[] lookAlong(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        if (SimdSupport.USE_FMA) return lookAlong_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static float[] lookAlong_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        float _dirz = dir[dirOffset + 2];
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
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
        float _t17 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        float _t18 = Math.fma(_t17, _t12, _upx);
        float _t19 = Math.fma(_t17, _t13, _upy);
        float _t20 = Math.fma(_t17, _t11, _upz);
        float _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        return lookAlong_fma_sa9599e32_1(dest, destOffset, src, srcOffset, _upz, _upx, _upy, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code lookAlong_fma}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_fma_sa9599e32_1(float[] dest, int destOffset, float[] src, int srcOffset, float _upz, float _upx, float _upy, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        lookAlong_fma_s93d3deb8_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static float[] lookAlong_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] dir, int dirOffset, float[] up, int upOffset) {
        float _dirz = dir[dirOffset + 2];
        float _dirx = dir[dirOffset];
        float _diry = dir[dirOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
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
        float _t17 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        float _t18 = Math.fma(_t17, _t12, _upx);
        float _t19 = Math.fma(_t17, _t13, _upy);
        float _t20 = Math.fma(_t17, _t11, _upz);
        float _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        float _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        float _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        float _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        return lookAlong_mulAdd_s4fc89591_1(dest, destOffset, src, srcOffset, _upz, _upx, _upy, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0f / (float) java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code lookAlong_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float[] lookAlong_mulAdd_s4fc89591_1(float[] dest, int destOffset, float[] src, int srcOffset, float _upz, float _upx, float _upy, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29, float _t32, float _t33) {
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
        lookAlong_mulAdd_sc16e9115_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && dir.isNative() && up.isNative()) return lookAlong_fma_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
            return lookAlong_fma_api(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && dir.isNative() && up.isNative()) return lookAlong_mulAdd_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_mulAdd_api(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static java.lang.foreign.MemorySegment lookAlong_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && dir.isNative() && up.isNative()) return lookAlong_fma_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_fma_api(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static java.lang.foreign.MemorySegment lookAlong_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        long _dirBase = dir.address() + dirOffset;
        long _upBase = up.address() + upOffset;
        float _dirz = UnsafeOpsHolder.U.getFloat(_dirBase + 8L);
        float _dirx = UnsafeOpsHolder.U.getFloat(_dirBase);
        float _diry = UnsafeOpsHolder.U.getFloat(_dirBase + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(_upBase + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(_upBase);
        float _upy = UnsafeOpsHolder.U.getFloat(_upBase + 4L);
        float _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
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
        float _t17 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        float _t18 = Math.fma(_t17, _t12, _upx);
        float _t19 = Math.fma(_t17, _t13, _upy);
        float _t20 = Math.fma(_t17, _t11, _upz);
        return lookAlong_fma_unsafe_s6c31f8d7_1(dest, destOffset, src, srcOffset, _upz, _upx, _upy, _t11, _t12, _t13, Math.fma(_t18, _t13, -(_t19 * _t12)), Math.fma(_t19, _t11, -(_t20 * _t13)), Math.fma(_t20, _t12, -(_t18 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_fma_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_fma_unsafe_s6c31f8d7_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _upz, float _upx, float _upy, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
        float _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
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
        lookAlong_fma_unsafe_s91aab55b_v(dest, destOffset, src.address() + srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        float _dirz = dir.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _dirx = dir.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _upz = up.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _upx = up.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
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
        float _t17 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        float _t18 = Math.fma(_t17, _t12, _upx);
        float _t19 = Math.fma(_t17, _t13, _upy);
        float _t20 = Math.fma(_t17, _t11, _upz);
        return lookAlong_fma_api_s791204b7_1(dest, destOffset, src, srcOffset, _upz, _upx, _upy, _t11, _t12, _t13, Math.fma(_t18, _t13, -(_t19 * _t12)), Math.fma(_t19, _t11, -(_t20 * _t13)), Math.fma(_t20, _t12, -(_t18 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_fma_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_fma_api_s791204b7_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _upz, float _upx, float _upy, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
        float _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
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
        lookAlong_fma_api_sf4bf46bb_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && dir.isNative() && up.isNative()) return lookAlong_mulAdd_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_mulAdd_api(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static java.lang.foreign.MemorySegment lookAlong_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        long _dirBase = dir.address() + dirOffset;
        long _upBase = up.address() + upOffset;
        float _dirz = UnsafeOpsHolder.U.getFloat(_dirBase + 8L);
        float _dirx = UnsafeOpsHolder.U.getFloat(_dirBase);
        float _diry = UnsafeOpsHolder.U.getFloat(_dirBase + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(_upBase + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(_upBase);
        float _upy = UnsafeOpsHolder.U.getFloat(_upBase + 4L);
        float _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
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
        float _t17 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        float _t18 = Math.fma(_t17, _t12, _upx);
        float _t19 = Math.fma(_t17, _t13, _upy);
        float _t20 = Math.fma(_t17, _t11, _upz);
        return lookAlong_mulAdd_unsafe_s55c634ce_1(dest, destOffset, src, srcOffset, _upz, _upx, _upy, _t11, _t12, _t13, Math.fma(_t18, _t13, -(_t19 * _t12)), Math.fma(_t19, _t11, -(_t20 * _t13)), Math.fma(_t20, _t12, -(_t18 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_mulAdd_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_mulAdd_unsafe_s55c634ce_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _upz, float _upx, float _upy, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
        float _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
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
        lookAlong_mulAdd_unsafe_sd9a1ef50_v(dest, destOffset, src.address() + srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        float _dirz = dir.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, dirOffset + 8L);
        float _dirx = dir.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, dirOffset);
        float _diry = dir.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, dirOffset + 4L);
        float _upz = up.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _upx = up.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
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
        float _t17 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        float _t18 = Math.fma(_t17, _t12, _upx);
        float _t19 = Math.fma(_t17, _t13, _upy);
        float _t20 = Math.fma(_t17, _t11, _upz);
        return lookAlong_mulAdd_api_s56df923c_1(dest, destOffset, src, srcOffset, _upz, _upx, _upy, _t11, _t12, _t13, Math.fma(_t18, _t13, -(_t19 * _t12)), Math.fma(_t19, _t11, -(_t20 * _t13)), Math.fma(_t20, _t12, -(_t18 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_mulAdd_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_mulAdd_api_s56df923c_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _upz, float _upx, float _upy, float _t11, float _t12, float _t13, float _t27, float _t28, float _t29) {
        float _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        float _t33 = (1.0f / (float) java.lang.Math.sqrt(_t32));
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
        lookAlong_mulAdd_api_s5a15b0fe_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
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
        float _t23 = -Math.fma(upZ, _t15, Math.fma(upX, _t14, upY * _t16));
        float _t24 = Math.fma(_t23, _t14, upX);
        float _t25 = Math.fma(_t23, _t16, upY);
        float _t26 = Math.fma(_t23, _t15, upZ);
        float _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        float _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        float _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        float _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        return lookAt_lh_fma_s63bc8cb3_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t14, _t15, _t16, _t33, _t34, _t35, _t38, (1.0f / (float) java.lang.Math.sqrt(_t38)));
    }

    /** Piece 2 of {@code lookAt_lh_fma}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_fma_s63bc8cb3_1(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _t14, float _t15, float _t16, float _t33, float _t34, float _t35, float _t38, float _t39) {
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
        lookAt_lh_fma_s7dab4453_v(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t14, _t15, _t16, Math.fma(eyeZ, _t15, Math.fma(eyeX, _t14, eyeY * _t16)), _t43, _t44, _t45, _t54, _t55, _t56, Math.fma(eyeZ, _t56, Math.fma(eyeX, _t54, eyeY * _t55)));
        return dest;
    }

    private static void lookAt_lh_fma_s7dab4453_v(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float _t14, float _t15, float _t16, float _t22, float _t43, float _t44, float _t45, float _t54, float _t55, float _t56, float _t60) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, Math.fma(eyeZ, _t44, Math.fma(eyeX, _t43, eyeY * _t45)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, src[_lo0]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).withLane(3, -src[_lo0]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -src[_lo0 + 1] * _t60 - src[_lo0 + 2] * _t22)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] lookAt_lh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
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
        float _t23 = -Math.fma(upZ, _t15, Math.fma(upX, _t14, upY * _t16));
        float _t24 = Math.fma(_t23, _t14, upX);
        float _t25 = Math.fma(_t23, _t16, upY);
        float _t26 = Math.fma(_t23, _t15, upZ);
        float _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        float _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        float _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        float _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        return lookAt_lh_mulAdd_s3607e316_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t14, _t15, _t16, _t33, _t34, _t35, _t38, (1.0f / (float) java.lang.Math.sqrt(_t38)));
    }

    /** Piece 2 of {@code lookAt_lh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_mulAdd_s3607e316_1(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _t14, float _t15, float _t16, float _t33, float _t34, float _t35, float _t38, float _t39) {
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
        lookAt_lh_mulAdd_s91fa99d2_v(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t14, _t15, _t16, Math.fma(eyeZ, _t15, Math.fma(eyeX, _t14, eyeY * _t16)), _t43, _t44, _t45, _t54, _t55, _t56, Math.fma(eyeZ, _t56, Math.fma(eyeX, _t54, eyeY * _t55)));
        return dest;
    }

    private static void lookAt_lh_mulAdd_s91fa99d2_v(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float _t14, float _t15, float _t16, float _t22, float _t43, float _t44, float _t45, float _t54, float _t55, float _t56, float _t60) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, Math.fma(eyeZ, _t44, Math.fma(eyeX, _t43, eyeY * _t45)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, src[_lo0]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).withLane(3, -src[_lo0]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -src[_lo0 + 1] * _t60 - src[_lo0 + 2] * _t22)))).intoArray(dest, destOffset + _li * 4);
        }
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
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
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
        return lookAt_rh_fma_s16fd7b99_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t17, _t18, _t19, _t36, _t37, _t38, _t41, (1.0f / (float) java.lang.Math.sqrt(_t41)));
    }

    /** Piece 2 of {@code lookAt_rh_fma}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_fma_s16fd7b99_1(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _t17, float _t18, float _t19, float _t36, float _t37, float _t38, float _t41, float _t42) {
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
        lookAt_rh_fma_sf51a6345_v(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t17, _t18, _t19, _t46, _t47, _t48, _t57, _t58, _t59, Math.fma(eyeZ, _t48, Math.fma(eyeX, _t46, eyeY * _t47)), Math.fma(eyeZ, _t59, Math.fma(eyeX, _t57, eyeY * _t58)));
        return dest;
    }

    private static void lookAt_rh_fma_sf51a6345_v(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float _t17, float _t18, float _t19, float _t46, float _t47, float _t48, float _t57, float _t58, float _t59, float _t61, float _t63) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, Math.fma(eyeZ, _t18, Math.fma(eyeX, _t17, eyeY * _t19)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, -src[_lo0 + 2]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0]).withLane(3, src[_lo0 + 2]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -src[_lo0] * _t61 - src[_lo0 + 1] * _t63)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] lookAt_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t3 = centerZ - eyeZ;
        float _t4 = centerX - eyeX;
        float _t5 = centerY - eyeY;
        float _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
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
        return lookAt_rh_mulAdd_sbad010d4_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t17, _t18, _t19, _t36, _t37, _t38, _t41, (1.0f / (float) java.lang.Math.sqrt(_t41)));
    }

    /** Piece 2 of {@code lookAt_rh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_mulAdd_sbad010d4_1(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _t17, float _t18, float _t19, float _t36, float _t37, float _t38, float _t41, float _t42) {
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
        lookAt_rh_mulAdd_se6ecb5dc_v(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t17, _t18, _t19, _t46, _t47, _t48, _t57, _t58, _t59, Math.fma(eyeZ, _t48, Math.fma(eyeX, _t46, eyeY * _t47)), Math.fma(eyeZ, _t59, Math.fma(eyeX, _t57, eyeY * _t58)));
        return dest;
    }

    private static void lookAt_rh_mulAdd_se6ecb5dc_v(float[] dest, int destOffset, float[] src, int srcOffset, float eyeX, float eyeY, float eyeZ, float _t17, float _t18, float _t19, float _t46, float _t47, float _t48, float _t57, float _t58, float _t59, float _t61, float _t63) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, Math.fma(eyeZ, _t18, Math.fma(eyeX, _t17, eyeY * _t19)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, -src[_lo0 + 2]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0]).withLane(3, src[_lo0 + 2]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -src[_lo0] * _t61 - src[_lo0 + 1] * _t63)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static java.lang.foreign.MemorySegment lookAt_lh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return lookAt_lh_fma_unsafe(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
            return lookAt_lh_fma_api(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return lookAt_lh_mulAdd_unsafe(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return lookAt_lh_mulAdd_api(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static java.lang.foreign.MemorySegment lookAt_lh_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return lookAt_lh_fma_unsafe(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return lookAt_lh_fma_api(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static java.lang.foreign.MemorySegment lookAt_lh_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
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
        float _t23 = -Math.fma(upZ, _t15, Math.fma(upX, _t14, upY * _t16));
        float _t24 = Math.fma(_t23, _t14, upX);
        float _t25 = Math.fma(_t23, _t16, upY);
        float _t26 = Math.fma(_t23, _t15, upZ);
        float _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        float _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        float _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        float _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        return lookAt_lh_fma_unsafe_sde9638a8_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t14, _t15, _t16, _t33, _t34, _t35, _t38, (1.0f / (float) java.lang.Math.sqrt(_t38)));
    }

    /** Piece 2 of {@code lookAt_lh_fma_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAt_lh_fma_unsafe_sde9638a8_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _t14, float _t15, float _t16, float _t33, float _t34, float _t35, float _t38, float _t39) {
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
        lookAt_lh_fma_unsafe_s744435d0_v(dest, destOffset, eyeX, eyeY, eyeZ, src.address() + srcOffset, _t14, _t15, _t16, Math.fma(eyeZ, _t15, Math.fma(eyeX, _t14, eyeY * _t16)), _t43, _t44, _t45, _t54, _t55, _t56, Math.fma(eyeZ, _t56, Math.fma(eyeX, _t54, eyeY * _t55)));
        return dest;
    }

    private static void lookAt_lh_fma_unsafe_s744435d0_v(java.lang.foreign.MemorySegment dest, long destOffset, float eyeX, float eyeY, float eyeZ, long _srcBase, float _t14, float _t15, float _t16, float _t22, float _t43, float _t44, float _t45, float _t54, float _t55, float _t56, float _t60) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, Math.fma(eyeZ, _t44, Math.fma(eyeX, _t43, eyeY * _t45)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L))).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L)).withLane(3, -UnsafeOpsHolder.U.getFloat(_lb1)).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 8L)).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -UnsafeOpsHolder.U.getFloat(_lb1 + 4L) * _t60 - UnsafeOpsHolder.U.getFloat(_lb1 + 8L) * _t22)))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment lookAt_lh_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
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
        float _t23 = -Math.fma(upZ, _t15, Math.fma(upX, _t14, upY * _t16));
        float _t24 = Math.fma(_t23, _t14, upX);
        float _t25 = Math.fma(_t23, _t16, upY);
        float _t26 = Math.fma(_t23, _t15, upZ);
        float _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        float _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        float _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        float _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        return lookAt_lh_fma_api_se23a979a_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t14, _t15, _t16, _t33, _t34, _t35, _t38, (1.0f / (float) java.lang.Math.sqrt(_t38)));
    }

    /** Piece 2 of {@code lookAt_lh_fma_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAt_lh_fma_api_se23a979a_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _t14, float _t15, float _t16, float _t33, float _t34, float _t35, float _t38, float _t39) {
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
        lookAt_lh_fma_api_s1dadcb36_v(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t14, _t15, _t16, Math.fma(eyeZ, _t15, Math.fma(eyeX, _t14, eyeY * _t16)), _t43, _t44, _t45, _t54, _t55, _t56, Math.fma(eyeZ, _t56, Math.fma(eyeX, _t54, eyeY * _t55)));
        return dest;
    }

    private static void lookAt_lh_fma_api_s1dadcb36_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float eyeX, float eyeY, float eyeZ, float _t14, float _t15, float _t16, float _t22, float _t43, float _t44, float _t45, float _t54, float _t55, float _t56, float _t60) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, Math.fma(eyeZ, _t44, Math.fma(eyeX, _t43, eyeY * _t45)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))).add(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L)).withLane(3, -src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L) * _t60 - src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L) * _t22)))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment lookAt_lh_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return lookAt_lh_mulAdd_unsafe(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return lookAt_lh_mulAdd_api(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static java.lang.foreign.MemorySegment lookAt_lh_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
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
        float _t23 = -Math.fma(upZ, _t15, Math.fma(upX, _t14, upY * _t16));
        float _t24 = Math.fma(_t23, _t14, upX);
        float _t25 = Math.fma(_t23, _t16, upY);
        float _t26 = Math.fma(_t23, _t15, upZ);
        float _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        float _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        float _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        float _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        return lookAt_lh_mulAdd_unsafe_sf0f4763f_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t14, _t15, _t16, _t33, _t34, _t35, _t38, (1.0f / (float) java.lang.Math.sqrt(_t38)));
    }

    /** Piece 2 of {@code lookAt_lh_mulAdd_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAt_lh_mulAdd_unsafe_sf0f4763f_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _t14, float _t15, float _t16, float _t33, float _t34, float _t35, float _t38, float _t39) {
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
        lookAt_lh_mulAdd_unsafe_s18a8383f_v(dest, destOffset, eyeX, eyeY, eyeZ, src.address() + srcOffset, _t14, _t15, _t16, Math.fma(eyeZ, _t15, Math.fma(eyeX, _t14, eyeY * _t16)), _t43, _t44, _t45, _t54, _t55, _t56, Math.fma(eyeZ, _t56, Math.fma(eyeX, _t54, eyeY * _t55)));
        return dest;
    }

    private static void lookAt_lh_mulAdd_unsafe_s18a8383f_v(java.lang.foreign.MemorySegment dest, long destOffset, float eyeX, float eyeY, float eyeZ, long _srcBase, float _t14, float _t15, float _t16, float _t22, float _t43, float _t44, float _t45, float _t54, float _t55, float _t56, float _t60) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, Math.fma(eyeZ, _t44, Math.fma(eyeX, _t43, eyeY * _t45)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L))).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L)).withLane(3, -UnsafeOpsHolder.U.getFloat(_lb1)).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 8L)).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -UnsafeOpsHolder.U.getFloat(_lb1 + 4L) * _t60 - UnsafeOpsHolder.U.getFloat(_lb1 + 8L) * _t22)))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment lookAt_lh_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t0 = centerZ - eyeZ;
        float _t1 = centerX - eyeX;
        float _t2 = centerY - eyeY;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
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
        float _t22 = Math.fma(eyeZ, _t15, Math.fma(eyeX, _t14, eyeY * _t16));
        float _t23 = -Math.fma(upZ, _t15, Math.fma(upX, _t14, upY * _t16));
        float _t24 = Math.fma(_t23, _t14, upX);
        float _t25 = Math.fma(_t23, _t16, upY);
        float _t26 = Math.fma(_t23, _t15, upZ);
        float _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        float _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        float _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        float _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        float _t39 = (1.0f / (float) java.lang.Math.sqrt(_t38));
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
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))).add(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L)).withLane(3, -src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L) * _t60 - src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L) * _t22)))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAt_rh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return lookAt_rh_fma_unsafe(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
            return lookAt_rh_fma_api(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return lookAt_rh_mulAdd_unsafe(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return lookAt_rh_mulAdd_api(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static java.lang.foreign.MemorySegment lookAt_rh_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return lookAt_rh_fma_unsafe(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return lookAt_rh_fma_api(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static java.lang.foreign.MemorySegment lookAt_rh_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t3 = centerZ - eyeZ;
        float _t4 = centerX - eyeX;
        float _t5 = centerY - eyeY;
        float _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
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
        return lookAt_rh_fma_unsafe_s7b215ffa_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t17, _t18, _t19, _t36, _t37, _t38, _t41, (1.0f / (float) java.lang.Math.sqrt(_t41)));
    }

    /** Piece 2 of {@code lookAt_rh_fma_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAt_rh_fma_unsafe_s7b215ffa_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _t17, float _t18, float _t19, float _t36, float _t37, float _t38, float _t41, float _t42) {
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
        lookAt_rh_fma_unsafe_s17caab16_v(dest, destOffset, eyeX, eyeY, eyeZ, src.address() + srcOffset, _t17, _t18, _t19, _t46, _t47, _t48, _t57, _t58, _t59, Math.fma(eyeZ, _t48, Math.fma(eyeX, _t46, eyeY * _t47)), Math.fma(eyeZ, _t59, Math.fma(eyeX, _t57, eyeY * _t58)));
        return dest;
    }

    private static void lookAt_rh_fma_unsafe_s17caab16_v(java.lang.foreign.MemorySegment dest, long destOffset, float eyeX, float eyeY, float eyeZ, long _srcBase, float _t17, float _t18, float _t19, float _t46, float _t47, float _t48, float _t57, float _t58, float _t59, float _t61, float _t63) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, Math.fma(eyeZ, _t18, Math.fma(eyeX, _t17, eyeY * _t19)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, -UnsafeOpsHolder.U.getFloat(_lb1 + 8L)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L))).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1)).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 8L)).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L)).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -UnsafeOpsHolder.U.getFloat(_lb1) * _t61 - UnsafeOpsHolder.U.getFloat(_lb1 + 4L) * _t63)))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment lookAt_rh_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t3 = centerZ - eyeZ;
        float _t4 = centerX - eyeX;
        float _t5 = centerY - eyeY;
        float _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
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
        return lookAt_rh_fma_api_sb1afa594_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t17, _t18, _t19, _t36, _t37, _t38, _t41, (1.0f / (float) java.lang.Math.sqrt(_t41)));
    }

    /** Piece 2 of {@code lookAt_rh_fma_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAt_rh_fma_api_sb1afa594_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _t17, float _t18, float _t19, float _t36, float _t37, float _t38, float _t41, float _t42) {
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
        lookAt_rh_fma_api_sfe1d8c9c_v(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t17, _t18, _t19, _t46, _t47, _t48, _t57, _t58, _t59, Math.fma(eyeZ, _t48, Math.fma(eyeX, _t46, eyeY * _t47)), Math.fma(eyeZ, _t59, Math.fma(eyeX, _t57, eyeY * _t58)));
        return dest;
    }

    private static void lookAt_rh_fma_api_sfe1d8c9c_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float eyeX, float eyeY, float eyeZ, float _t17, float _t18, float _t19, float _t46, float _t47, float _t48, float _t57, float _t58, float _t59, float _t61, float _t63) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, Math.fma(eyeZ, _t18, Math.fma(eyeX, _t17, eyeY * _t19)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, -src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))).add(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L)).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0) * _t61 - src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L) * _t63)))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment lookAt_rh_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return lookAt_rh_mulAdd_unsafe(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return lookAt_rh_mulAdd_api(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static java.lang.foreign.MemorySegment lookAt_rh_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t3 = centerZ - eyeZ;
        float _t4 = centerX - eyeX;
        float _t5 = centerY - eyeY;
        float _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
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
        return lookAt_rh_mulAdd_unsafe_sb49eefe1_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t17, _t18, _t19, _t36, _t37, _t38, _t41, (1.0f / (float) java.lang.Math.sqrt(_t41)));
    }

    /** Piece 2 of {@code lookAt_rh_mulAdd_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAt_rh_mulAdd_unsafe_sb49eefe1_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float eyeX, float eyeY, float eyeZ, float upX, float upY, float upZ, float _t17, float _t18, float _t19, float _t36, float _t37, float _t38, float _t41, float _t42) {
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
        lookAt_rh_mulAdd_unsafe_scb74492d_v(dest, destOffset, eyeX, eyeY, eyeZ, src.address() + srcOffset, _t17, _t18, _t19, _t46, _t47, _t48, _t57, _t58, _t59, Math.fma(eyeZ, _t48, Math.fma(eyeX, _t46, eyeY * _t47)), Math.fma(eyeZ, _t59, Math.fma(eyeX, _t57, eyeY * _t58)));
        return dest;
    }

    private static void lookAt_rh_mulAdd_unsafe_scb74492d_v(java.lang.foreign.MemorySegment dest, long destOffset, float eyeX, float eyeY, float eyeZ, long _srcBase, float _t17, float _t18, float _t19, float _t46, float _t47, float _t48, float _t57, float _t58, float _t59, float _t61, float _t63) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, Math.fma(eyeZ, _t18, Math.fma(eyeX, _t17, eyeY * _t19)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, -UnsafeOpsHolder.U.getFloat(_lb1 + 8L)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L))).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1)).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 8L)).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L)).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -UnsafeOpsHolder.U.getFloat(_lb1) * _t61 - UnsafeOpsHolder.U.getFloat(_lb1 + 4L) * _t63)))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment lookAt_rh_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float eyeX, float eyeY, float eyeZ, float centerX, float centerY, float centerZ, float upX, float upY, float upZ) {
        float _t3 = centerZ - eyeZ;
        float _t4 = centerX - eyeX;
        float _t5 = centerY - eyeY;
        float _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
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
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t41));
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
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, -src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))).add(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L)).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0) * _t61 - src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L) * _t63)))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
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
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
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
        float _t23 = -Math.fma(_upz, _t15, Math.fma(_upx, _t14, _upy * _t16));
        float _t24 = Math.fma(_t23, _t14, _upx);
        float _t25 = Math.fma(_t23, _t16, _upy);
        float _t26 = Math.fma(_t23, _t15, _upz);
        float _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        float _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        float _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        return lookAt_lh_fma_sece1bb5_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t14, _t15, _t16, _t33, _t34, _t35, Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35)));
    }

    /** Piece 2 of {@code lookAt_lh_fma}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_fma_sece1bb5_1(float[] dest, int destOffset, float[] src, int srcOffset, float _eyez, float _eyex, float _eyey, float _upz, float _upx, float _upy, float _t14, float _t15, float _t16, float _t33, float _t34, float _t35, float _t38) {
        float _t39 = (1.0f / (float) java.lang.Math.sqrt(_t38));
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
        lookAt_lh_fma_sadc67aa1_v(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _t14, _t15, _t16, Math.fma(_eyez, _t15, Math.fma(_eyex, _t14, _eyey * _t16)), _t43, _t44, _t45, _t54, _t55, _t56, Math.fma(_eyez, _t56, Math.fma(_eyex, _t54, _eyey * _t55)));
        return dest;
    }

    private static void lookAt_lh_fma_sadc67aa1_v(float[] dest, int destOffset, float[] src, int srcOffset, float _eyez, float _eyex, float _eyey, float _t14, float _t15, float _t16, float _t22, float _t43, float _t44, float _t45, float _t54, float _t55, float _t56, float _t60) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, Math.fma(_eyez, _t44, Math.fma(_eyex, _t43, _eyey * _t45)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, src[_lo0]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).withLane(3, -src[_lo0]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -src[_lo0 + 1] * _t60 - src[_lo0 + 2] * _t22)))).intoArray(dest, destOffset + _li * 4);
        }
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
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
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
        float _t23 = -Math.fma(_upz, _t15, Math.fma(_upx, _t14, _upy * _t16));
        float _t24 = Math.fma(_t23, _t14, _upx);
        float _t25 = Math.fma(_t23, _t16, _upy);
        float _t26 = Math.fma(_t23, _t15, _upz);
        float _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        float _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        float _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        return lookAt_lh_mulAdd_sf960ab08_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t14, _t15, _t16, _t33, _t34, _t35, Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35)));
    }

    /** Piece 2 of {@code lookAt_lh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_lh_mulAdd_sf960ab08_1(float[] dest, int destOffset, float[] src, int srcOffset, float _eyez, float _eyex, float _eyey, float _upz, float _upx, float _upy, float _t14, float _t15, float _t16, float _t33, float _t34, float _t35, float _t38) {
        float _t39 = (1.0f / (float) java.lang.Math.sqrt(_t38));
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
        lookAt_lh_mulAdd_sa38e3030_v(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _t14, _t15, _t16, Math.fma(_eyez, _t15, Math.fma(_eyex, _t14, _eyey * _t16)), _t43, _t44, _t45, _t54, _t55, _t56, Math.fma(_eyez, _t56, Math.fma(_eyex, _t54, _eyey * _t55)));
        return dest;
    }

    private static void lookAt_lh_mulAdd_sa38e3030_v(float[] dest, int destOffset, float[] src, int srcOffset, float _eyez, float _eyex, float _eyey, float _t14, float _t15, float _t16, float _t22, float _t43, float _t44, float _t45, float _t54, float _t55, float _t56, float _t60) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, Math.fma(_eyez, _t44, Math.fma(_eyex, _t43, _eyey * _t45)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, src[_lo0]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).withLane(3, -src[_lo0]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -src[_lo0 + 1] * _t60 - src[_lo0 + 2] * _t22)))).intoArray(dest, destOffset + _li * 4);
        }
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
        float _t3 = center[centerOffset + 2] - _eyez;
        float _t4 = center[centerOffset] - _eyex;
        float _t5 = center[centerOffset + 1] - _eyey;
        float _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
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
        return lookAt_rh_fma_s7aa8bfb_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t17, _t18, _t19, _t36, _t37, _t38, Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38)));
    }

    /** Piece 2 of {@code lookAt_rh_fma}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_fma_s7aa8bfb_1(float[] dest, int destOffset, float[] src, int srcOffset, float _eyez, float _eyex, float _eyey, float _upz, float _upx, float _upy, float _t17, float _t18, float _t19, float _t36, float _t37, float _t38, float _t41) {
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t41));
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
        lookAt_rh_fma_sf72dfd3b_v(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _t17, _t18, _t19, _t46, _t47, _t48, _t57, _t58, _t59, Math.fma(_eyez, _t48, Math.fma(_eyex, _t46, _eyey * _t47)), Math.fma(_eyez, _t59, Math.fma(_eyex, _t57, _eyey * _t58)));
        return dest;
    }

    private static void lookAt_rh_fma_sf72dfd3b_v(float[] dest, int destOffset, float[] src, int srcOffset, float _eyez, float _eyex, float _eyey, float _t17, float _t18, float _t19, float _t46, float _t47, float _t48, float _t57, float _t58, float _t59, float _t61, float _t63) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, Math.fma(_eyez, _t18, Math.fma(_eyex, _t17, _eyey * _t19)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, -src[_lo0 + 2]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0]).withLane(3, src[_lo0 + 2]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -src[_lo0] * _t61 - src[_lo0 + 1] * _t63)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] lookAt_rh_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] eye, int eyeOffset, float[] center, int centerOffset, float[] up, int upOffset) {
        float _eyez = eye[eyeOffset + 2];
        float _eyex = eye[eyeOffset];
        float _eyey = eye[eyeOffset + 1];
        float _upz = up[upOffset + 2];
        float _upx = up[upOffset];
        float _upy = up[upOffset + 1];
        float _t3 = center[centerOffset + 2] - _eyez;
        float _t4 = center[centerOffset] - _eyex;
        float _t5 = center[centerOffset + 1] - _eyey;
        float _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
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
        return lookAt_rh_mulAdd_se7740066_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t17, _t18, _t19, _t36, _t37, _t38, Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38)));
    }

    /** Piece 2 of {@code lookAt_rh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float[] lookAt_rh_mulAdd_se7740066_1(float[] dest, int destOffset, float[] src, int srcOffset, float _eyez, float _eyex, float _eyey, float _upz, float _upx, float _upy, float _t17, float _t18, float _t19, float _t36, float _t37, float _t38, float _t41) {
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t41));
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
        lookAt_rh_mulAdd_sa4019602_v(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _t17, _t18, _t19, _t46, _t47, _t48, _t57, _t58, _t59, Math.fma(_eyez, _t48, Math.fma(_eyex, _t46, _eyey * _t47)), Math.fma(_eyez, _t59, Math.fma(_eyex, _t57, _eyey * _t58)));
        return dest;
    }

    private static void lookAt_rh_mulAdd_sa4019602_v(float[] dest, int destOffset, float[] src, int srcOffset, float _eyez, float _eyex, float _eyey, float _t17, float _t18, float _t19, float _t46, float _t47, float _t48, float _t57, float _t58, float _t59, float _t61, float _t63) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, Math.fma(_eyez, _t18, Math.fma(_eyex, _t17, _eyey * _t19)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, -src[_lo0 + 2]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0]).withLane(3, src[_lo0 + 2]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -src[_lo0] * _t61 - src[_lo0 + 1] * _t63)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static java.lang.foreign.MemorySegment lookAt_lh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment eye, long eyeOffset, java.lang.foreign.MemorySegment center, long centerOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && eye.isNative() && center.isNative() && up.isNative()) return lookAt_lh_fma_unsafe(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
            return lookAt_lh_fma_api(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && eye.isNative() && center.isNative() && up.isNative()) return lookAt_lh_mulAdd_unsafe(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return lookAt_lh_mulAdd_api(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static java.lang.foreign.MemorySegment lookAt_lh_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment eye, long eyeOffset, java.lang.foreign.MemorySegment center, long centerOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && eye.isNative() && center.isNative() && up.isNative()) return lookAt_lh_fma_unsafe(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return lookAt_lh_fma_api(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static java.lang.foreign.MemorySegment lookAt_lh_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment eye, long eyeOffset, java.lang.foreign.MemorySegment center, long centerOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        long _eyeBase = eye.address() + eyeOffset;
        long _centerBase = center.address() + centerOffset;
        long _upBase = up.address() + upOffset;
        float _eyez = UnsafeOpsHolder.U.getFloat(_eyeBase + 8L);
        float _eyex = UnsafeOpsHolder.U.getFloat(_eyeBase);
        float _eyey = UnsafeOpsHolder.U.getFloat(_eyeBase + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(_upBase + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(_upBase);
        float _upy = UnsafeOpsHolder.U.getFloat(_upBase + 4L);
        float _t0 = UnsafeOpsHolder.U.getFloat(_centerBase + 8L) - _eyez;
        float _t1 = UnsafeOpsHolder.U.getFloat(_centerBase) - _eyex;
        float _t2 = UnsafeOpsHolder.U.getFloat(_centerBase + 4L) - _eyey;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
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
        float _t23 = -Math.fma(_upz, _t15, Math.fma(_upx, _t14, _upy * _t16));
        return lookAt_lh_fma_unsafe_s5f9f94e9_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t14, _t15, _t16, Math.fma(_t23, _t14, _upx), Math.fma(_t23, _t16, _upy), Math.fma(_t23, _t15, _upz));
    }

    /** Piece 2 of {@code lookAt_lh_fma_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAt_lh_fma_unsafe_s5f9f94e9_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _eyez, float _eyex, float _eyey, float _upz, float _upx, float _upy, float _t14, float _t15, float _t16, float _t24, float _t25, float _t26) {
        float _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        float _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        float _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        float _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        float _t39 = (1.0f / (float) java.lang.Math.sqrt(_t38));
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
        lookAt_lh_fma_unsafe_s370ffbb5_v(dest, destOffset, src.address() + srcOffset, _eyez, _eyex, _eyey, _t14, _t15, _t16, Math.fma(_eyez, _t15, Math.fma(_eyex, _t14, _eyey * _t16)), _t43, _t44, _t45, _t54, _t55, _t56, Math.fma(_eyez, _t56, Math.fma(_eyex, _t54, _eyey * _t55)));
        return dest;
    }

    private static void lookAt_lh_fma_unsafe_s370ffbb5_v(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, float _eyez, float _eyex, float _eyey, float _t14, float _t15, float _t16, float _t22, float _t43, float _t44, float _t45, float _t54, float _t55, float _t56, float _t60) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, Math.fma(_eyez, _t44, Math.fma(_eyex, _t43, _eyey * _t45)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L))).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L)).withLane(3, -UnsafeOpsHolder.U.getFloat(_lb1)).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 8L)).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -UnsafeOpsHolder.U.getFloat(_lb1 + 4L) * _t60 - UnsafeOpsHolder.U.getFloat(_lb1 + 8L) * _t22)))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment lookAt_lh_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment eye, long eyeOffset, java.lang.foreign.MemorySegment center, long centerOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        float _eyez = eye.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, eyeOffset + 8L);
        float _eyex = eye.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, eyeOffset);
        float _eyey = eye.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, eyeOffset + 4L);
        float _upz = up.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _upx = up.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _t0 = center.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, centerOffset + 8L) - _eyez;
        float _t1 = center.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, centerOffset) - _eyex;
        float _t2 = center.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, centerOffset + 4L) - _eyey;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
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
        float _t23 = -Math.fma(_upz, _t15, Math.fma(_upx, _t14, _upy * _t16));
        return lookAt_lh_fma_api_sda12e617_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t14, _t15, _t16, Math.fma(_t23, _t14, _upx), Math.fma(_t23, _t16, _upy), Math.fma(_t23, _t15, _upz));
    }

    /** Piece 2 of {@code lookAt_lh_fma_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAt_lh_fma_api_sda12e617_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _eyez, float _eyex, float _eyey, float _upz, float _upx, float _upy, float _t14, float _t15, float _t16, float _t24, float _t25, float _t26) {
        float _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        float _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        float _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        float _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        float _t39 = (1.0f / (float) java.lang.Math.sqrt(_t38));
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
        lookAt_lh_fma_api_s4f8be17_v(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _t14, _t15, _t16, Math.fma(_eyez, _t15, Math.fma(_eyex, _t14, _eyey * _t16)), _t43, _t44, _t45, _t54, _t55, _t56, Math.fma(_eyez, _t56, Math.fma(_eyex, _t54, _eyey * _t55)));
        return dest;
    }

    private static void lookAt_lh_fma_api_s4f8be17_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _eyez, float _eyex, float _eyey, float _t14, float _t15, float _t16, float _t22, float _t43, float _t44, float _t45, float _t54, float _t55, float _t56, float _t60) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, Math.fma(_eyez, _t44, Math.fma(_eyex, _t43, _eyey * _t45)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))).add(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L)).withLane(3, -src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L) * _t60 - src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L) * _t22)))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment lookAt_lh_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment eye, long eyeOffset, java.lang.foreign.MemorySegment center, long centerOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && eye.isNative() && center.isNative() && up.isNative()) return lookAt_lh_mulAdd_unsafe(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return lookAt_lh_mulAdd_api(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static java.lang.foreign.MemorySegment lookAt_lh_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment eye, long eyeOffset, java.lang.foreign.MemorySegment center, long centerOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        long _eyeBase = eye.address() + eyeOffset;
        long _centerBase = center.address() + centerOffset;
        long _upBase = up.address() + upOffset;
        float _eyez = UnsafeOpsHolder.U.getFloat(_eyeBase + 8L);
        float _eyex = UnsafeOpsHolder.U.getFloat(_eyeBase);
        float _eyey = UnsafeOpsHolder.U.getFloat(_eyeBase + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(_upBase + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(_upBase);
        float _upy = UnsafeOpsHolder.U.getFloat(_upBase + 4L);
        float _t0 = UnsafeOpsHolder.U.getFloat(_centerBase + 8L) - _eyez;
        float _t1 = UnsafeOpsHolder.U.getFloat(_centerBase) - _eyex;
        float _t2 = UnsafeOpsHolder.U.getFloat(_centerBase + 4L) - _eyey;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
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
        float _t23 = -Math.fma(_upz, _t15, Math.fma(_upx, _t14, _upy * _t16));
        return lookAt_lh_mulAdd_unsafe_s24b30694_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t14, _t15, _t16, Math.fma(_t23, _t14, _upx), Math.fma(_t23, _t16, _upy), Math.fma(_t23, _t15, _upz));
    }

    /** Piece 2 of {@code lookAt_lh_mulAdd_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAt_lh_mulAdd_unsafe_s24b30694_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _eyez, float _eyex, float _eyey, float _upz, float _upx, float _upy, float _t14, float _t15, float _t16, float _t24, float _t25, float _t26) {
        float _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        float _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        float _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        float _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        float _t39 = (1.0f / (float) java.lang.Math.sqrt(_t38));
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
        lookAt_lh_mulAdd_unsafe_se98fc59c_v(dest, destOffset, src.address() + srcOffset, _eyez, _eyex, _eyey, _t14, _t15, _t16, Math.fma(_eyez, _t15, Math.fma(_eyex, _t14, _eyey * _t16)), _t43, _t44, _t45, _t54, _t55, _t56, Math.fma(_eyez, _t56, Math.fma(_eyex, _t54, _eyey * _t55)));
        return dest;
    }

    private static void lookAt_lh_mulAdd_unsafe_se98fc59c_v(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, float _eyez, float _eyex, float _eyey, float _t14, float _t15, float _t16, float _t22, float _t43, float _t44, float _t45, float _t54, float _t55, float _t56, float _t60) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, Math.fma(_eyez, _t44, Math.fma(_eyex, _t43, _eyey * _t45)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L))).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L)).withLane(3, -UnsafeOpsHolder.U.getFloat(_lb1)).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 8L)).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -UnsafeOpsHolder.U.getFloat(_lb1 + 4L) * _t60 - UnsafeOpsHolder.U.getFloat(_lb1 + 8L) * _t22)))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment lookAt_lh_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment eye, long eyeOffset, java.lang.foreign.MemorySegment center, long centerOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        float _eyez = eye.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, eyeOffset + 8L);
        float _eyex = eye.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, eyeOffset);
        float _eyey = eye.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, eyeOffset + 4L);
        float _upz = up.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _upx = up.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _t0 = center.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, centerOffset + 8L) - _eyez;
        float _t1 = center.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, centerOffset) - _eyex;
        float _t2 = center.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, centerOffset + 4L) - _eyey;
        float _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
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
        float _t22 = Math.fma(_eyez, _t15, Math.fma(_eyex, _t14, _eyey * _t16));
        float _t23 = -Math.fma(_upz, _t15, Math.fma(_upx, _t14, _upy * _t16));
        float _t24 = Math.fma(_t23, _t14, _upx);
        float _t25 = Math.fma(_t23, _t16, _upy);
        float _t26 = Math.fma(_t23, _t15, _upz);
        float _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        float _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        float _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        float _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        float _t39 = (1.0f / (float) java.lang.Math.sqrt(_t38));
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
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))).add(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L)).withLane(3, -src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L) * _t60 - src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L) * _t22)))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAt_rh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment eye, long eyeOffset, java.lang.foreign.MemorySegment center, long centerOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && eye.isNative() && center.isNative() && up.isNative()) return lookAt_rh_fma_unsafe(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
            return lookAt_rh_fma_api(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && eye.isNative() && center.isNative() && up.isNative()) return lookAt_rh_mulAdd_unsafe(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return lookAt_rh_mulAdd_api(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static java.lang.foreign.MemorySegment lookAt_rh_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment eye, long eyeOffset, java.lang.foreign.MemorySegment center, long centerOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && eye.isNative() && center.isNative() && up.isNative()) return lookAt_rh_fma_unsafe(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return lookAt_rh_fma_api(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static java.lang.foreign.MemorySegment lookAt_rh_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment eye, long eyeOffset, java.lang.foreign.MemorySegment center, long centerOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        long _eyeBase = eye.address() + eyeOffset;
        long _centerBase = center.address() + centerOffset;
        long _upBase = up.address() + upOffset;
        float _eyez = UnsafeOpsHolder.U.getFloat(_eyeBase + 8L);
        float _eyex = UnsafeOpsHolder.U.getFloat(_eyeBase);
        float _eyey = UnsafeOpsHolder.U.getFloat(_eyeBase + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(_upBase + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(_upBase);
        float _upy = UnsafeOpsHolder.U.getFloat(_upBase + 4L);
        float _t3 = UnsafeOpsHolder.U.getFloat(_centerBase + 8L) - _eyez;
        float _t4 = UnsafeOpsHolder.U.getFloat(_centerBase) - _eyex;
        float _t5 = UnsafeOpsHolder.U.getFloat(_centerBase + 4L) - _eyey;
        float _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
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
        return lookAt_rh_fma_unsafe_s74e64677_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t17, _t18, _t19, Math.fma(_t26, _t19, _upy), Math.fma(_t26, _t17, _upx), Math.fma(_t26, _t18, _upz));
    }

    /** Piece 2 of {@code lookAt_rh_fma_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAt_rh_fma_unsafe_s74e64677_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _eyez, float _eyex, float _eyey, float _upz, float _upx, float _upy, float _t17, float _t18, float _t19, float _t27, float _t28, float _t29) {
        float _t36 = Math.fma(_t27, _t17, -(_t28 * _t19));
        float _t37 = Math.fma(_t28, _t18, -(_t29 * _t17));
        float _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        float _t41 = Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38));
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t41));
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
        lookAt_rh_fma_unsafe_sd81a7677_v(dest, destOffset, src.address() + srcOffset, _eyez, _eyex, _eyey, _t17, _t18, _t19, _t46, _t47, _t48, _t57, _t58, _t59, Math.fma(_eyez, _t48, Math.fma(_eyex, _t46, _eyey * _t47)), Math.fma(_eyez, _t59, Math.fma(_eyex, _t57, _eyey * _t58)));
        return dest;
    }

    private static void lookAt_rh_fma_unsafe_sd81a7677_v(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, float _eyez, float _eyex, float _eyey, float _t17, float _t18, float _t19, float _t46, float _t47, float _t48, float _t57, float _t58, float _t59, float _t61, float _t63) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, Math.fma(_eyez, _t18, Math.fma(_eyex, _t17, _eyey * _t19)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, -UnsafeOpsHolder.U.getFloat(_lb1 + 8L)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L))).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1)).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 8L)).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L)).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -UnsafeOpsHolder.U.getFloat(_lb1) * _t61 - UnsafeOpsHolder.U.getFloat(_lb1 + 4L) * _t63)))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment lookAt_rh_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment eye, long eyeOffset, java.lang.foreign.MemorySegment center, long centerOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        float _eyez = eye.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, eyeOffset + 8L);
        float _eyex = eye.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, eyeOffset);
        float _eyey = eye.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, eyeOffset + 4L);
        float _upz = up.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _upx = up.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _t3 = center.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, centerOffset + 8L) - _eyez;
        float _t4 = center.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, centerOffset) - _eyex;
        float _t5 = center.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, centerOffset + 4L) - _eyey;
        float _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
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
        return lookAt_rh_fma_api_sb157d9f5_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t17, _t18, _t19, Math.fma(_t26, _t19, _upy), Math.fma(_t26, _t17, _upx), Math.fma(_t26, _t18, _upz));
    }

    /** Piece 2 of {@code lookAt_rh_fma_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAt_rh_fma_api_sb157d9f5_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _eyez, float _eyex, float _eyey, float _upz, float _upx, float _upy, float _t17, float _t18, float _t19, float _t27, float _t28, float _t29) {
        float _t36 = Math.fma(_t27, _t17, -(_t28 * _t19));
        float _t37 = Math.fma(_t28, _t18, -(_t29 * _t17));
        float _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        float _t41 = Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38));
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t41));
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
        lookAt_rh_fma_api_s168bfee1_v(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _t17, _t18, _t19, _t46, _t47, _t48, _t57, _t58, _t59, Math.fma(_eyez, _t48, Math.fma(_eyex, _t46, _eyey * _t47)), Math.fma(_eyez, _t59, Math.fma(_eyex, _t57, _eyey * _t58)));
        return dest;
    }

    private static void lookAt_rh_fma_api_s168bfee1_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _eyez, float _eyex, float _eyey, float _t17, float _t18, float _t19, float _t46, float _t47, float _t48, float _t57, float _t58, float _t59, float _t61, float _t63) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, Math.fma(_eyez, _t18, Math.fma(_eyex, _t17, _eyey * _t19)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, -src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))).add(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L)).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0) * _t61 - src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L) * _t63)))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment lookAt_rh_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment eye, long eyeOffset, java.lang.foreign.MemorySegment center, long centerOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && eye.isNative() && center.isNative() && up.isNative()) return lookAt_rh_mulAdd_unsafe(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return lookAt_rh_mulAdd_api(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static java.lang.foreign.MemorySegment lookAt_rh_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment eye, long eyeOffset, java.lang.foreign.MemorySegment center, long centerOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        long _eyeBase = eye.address() + eyeOffset;
        long _centerBase = center.address() + centerOffset;
        long _upBase = up.address() + upOffset;
        float _eyez = UnsafeOpsHolder.U.getFloat(_eyeBase + 8L);
        float _eyex = UnsafeOpsHolder.U.getFloat(_eyeBase);
        float _eyey = UnsafeOpsHolder.U.getFloat(_eyeBase + 4L);
        float _upz = UnsafeOpsHolder.U.getFloat(_upBase + 8L);
        float _upx = UnsafeOpsHolder.U.getFloat(_upBase);
        float _upy = UnsafeOpsHolder.U.getFloat(_upBase + 4L);
        float _t3 = UnsafeOpsHolder.U.getFloat(_centerBase + 8L) - _eyez;
        float _t4 = UnsafeOpsHolder.U.getFloat(_centerBase) - _eyex;
        float _t5 = UnsafeOpsHolder.U.getFloat(_centerBase + 4L) - _eyey;
        float _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
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
        return lookAt_rh_mulAdd_unsafe_sabd9850a_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t17, _t18, _t19, Math.fma(_t26, _t19, _upy), Math.fma(_t26, _t17, _upx), Math.fma(_t26, _t18, _upz));
    }

    /** Piece 2 of {@code lookAt_rh_mulAdd_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAt_rh_mulAdd_unsafe_sabd9850a_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _eyez, float _eyex, float _eyey, float _upz, float _upx, float _upy, float _t17, float _t18, float _t19, float _t27, float _t28, float _t29) {
        float _t36 = Math.fma(_t27, _t17, -(_t28 * _t19));
        float _t37 = Math.fma(_t28, _t18, -(_t29 * _t17));
        float _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        float _t41 = Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38));
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t41));
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
        lookAt_rh_mulAdd_unsafe_sd03ef906_v(dest, destOffset, src.address() + srcOffset, _eyez, _eyex, _eyey, _t17, _t18, _t19, _t46, _t47, _t48, _t57, _t58, _t59, Math.fma(_eyez, _t48, Math.fma(_eyex, _t46, _eyey * _t47)), Math.fma(_eyez, _t59, Math.fma(_eyex, _t57, _eyey * _t58)));
        return dest;
    }

    private static void lookAt_rh_mulAdd_unsafe_sd03ef906_v(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, float _eyez, float _eyex, float _eyey, float _t17, float _t18, float _t19, float _t46, float _t47, float _t48, float _t57, float _t58, float _t59, float _t61, float _t63) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, Math.fma(_eyez, _t18, Math.fma(_eyex, _t17, _eyey * _t19)));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, -UnsafeOpsHolder.U.getFloat(_lb1 + 8L)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L))).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1)).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 8L)).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L)).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -UnsafeOpsHolder.U.getFloat(_lb1) * _t61 - UnsafeOpsHolder.U.getFloat(_lb1 + 4L) * _t63)))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment lookAt_rh_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment eye, long eyeOffset, java.lang.foreign.MemorySegment center, long centerOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        float _eyez = eye.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, eyeOffset + 8L);
        float _eyex = eye.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, eyeOffset);
        float _eyey = eye.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, eyeOffset + 4L);
        float _upz = up.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, upOffset + 8L);
        float _upx = up.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, upOffset);
        float _upy = up.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, upOffset + 4L);
        float _t3 = center.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, centerOffset + 8L) - _eyez;
        float _t4 = center.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, centerOffset) - _eyex;
        float _t5 = center.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, centerOffset + 4L) - _eyey;
        float _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        float _t13 = (1.0f / (float) java.lang.Math.sqrt(_t12));
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
        float _t42 = (1.0f / (float) java.lang.Math.sqrt(_t41));
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
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, -src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))).add(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L)).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, -src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0) * _t61 - src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L) * _t63)))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static float[] mapXYZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        for (int _li = 0; _li < 3; _li++) {
            FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment mapXYZ(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (SimdSupport.DOUBLE_COLUMNS && SimdSupport.X86 && dest.isNative() && src.isNative()) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_256, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        } else {
            for (int _li = 0; _li < 3; _li++) {
                FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 16L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
            }
        }
        return dest;
    }

    public static float[] mapXYnZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.fromArray(SIMD_SPECIES, src, _lo0).withLane(2, -src[_lo0 + 2]).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment mapXYnZ(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return mapXYnZ_unsafe(dest, destOffset, src, srcOffset);
        return mapXYnZ_api(dest, destOffset, src, srcOffset);
    }

    public static java.lang.foreign.MemorySegment mapXYnZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        for (int _li = 0; _li < 3; _li++) {
            FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 16L, java.nio.ByteOrder.nativeOrder()).withLane(2, -UnsafeOpsHolder.U.getFloat(_srcBase + _li * 16L + 8L)).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment mapXYnZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.fromMemorySegment(SIMD_SPECIES, src, _lo0, java.nio.ByteOrder.nativeOrder()).withLane(2, -src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static float[] mapXnYZ(float[] dest, int destOffset, float[] src, int srcOffset) {
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.fromArray(SIMD_SPECIES, src, _lo0).withLane(1, -src[_lo0 + 1]).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment mapXnYZ(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return mapXnYZ_unsafe(dest, destOffset, src, srcOffset);
        return mapXnYZ_api(dest, destOffset, src, srcOffset);
    }

    public static java.lang.foreign.MemorySegment mapXnYZ_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        for (int _li = 0; _li < 3; _li++) {
            FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 16L, java.nio.ByteOrder.nativeOrder()).withLane(1, -UnsafeOpsHolder.U.getFloat(_srcBase + _li * 16L + 4L)).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment mapXnYZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.fromMemorySegment(SIMD_SPECIES, src, _lo0, java.nio.ByteOrder.nativeOrder()).withLane(1, -src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L)).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
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
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t16), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h0), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _t19), _sv3.withLane(3, _h1)))).intoArray(dest, destOffset);
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t20), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t17), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _h2), _sv3.withLane(3, _h3)))).intoArray(dest, destOffset + 4);
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h4), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t21), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _t18), _sv3.withLane(3, _h5)))).intoArray(dest, destOffset + 8);
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
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t16)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h0)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t19)).add(_sv3.withLane(3, _h1)))).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t20)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t17)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h2)).add(_sv3.withLane(3, _h3)))).intoArray(dest, destOffset + 4);
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h4)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t21)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t18)).add(_sv3.withLane(3, _h5)))).intoArray(dest, destOffset + 8);
    }

    public static java.lang.foreign.MemorySegment preRotateAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        if (SimdSupport.USE_FMA) return preRotateAround_fma(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return preRotateAround_mulAdd(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
    }

    public static java.lang.foreign.MemorySegment preRotateAround_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
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
        preRotateAround_fma_sfeab7c88_v(dest, destOffset, src, srcOffset, _t16, _t17, _t18, _t19, _t20, _t21, Math.fma(_t0, _t5, _t14), Math.fma(_t2, _t16, Math.fma(pivotX, Math.fma(rotY, _t5, _t9), -(pivotY * _t19))), Math.fma(_t3, _t4, _t14), Math.fma(_t2, _t20, Math.fma(pivotY, Math.fma(rotX, _t4, _t9), -(pivotX * _t17))), Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0f)), Math.fma(-pivotY, _t18, Math.fma(pivotZ, Math.fma(rotX, _t4, rotY * _t5), -(pivotX * _t21))));
        return dest;
    }

    private static void preRotateAround_fma_sfeab7c88_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder());
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t16), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h0), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _t19), _sv3.withLane(3, _h1)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t20), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t17), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _h2), _sv3.withLane(3, _h3)))).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h4), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _t21), _sv2.fma(FloatVector.broadcast(SIMD_SPECIES, _t18), _sv3.withLane(3, _h5)))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment preRotateAround_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
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
        preRotateAround_mulAdd_s2a34d939_v(dest, destOffset, src, srcOffset, _t16, _t17, _t18, _t19, _t20, _t21, Math.fma(_t0, _t5, _t14), Math.fma(_t2, _t16, Math.fma(pivotX, Math.fma(rotY, _t5, _t9), -(pivotY * _t19))), Math.fma(_t3, _t4, _t14), Math.fma(_t2, _t20, Math.fma(pivotY, Math.fma(rotX, _t4, _t9), -(pivotX * _t17))), Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0f)), Math.fma(-pivotY, _t18, Math.fma(pivotZ, Math.fma(rotX, _t4, rotY * _t5), -(pivotX * _t21))));
        return dest;
    }

    private static void preRotateAround_mulAdd_s2a34d939_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder());
        var _sv3 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t16)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h0)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t19)).add(_sv3.withLane(3, _h1)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t20)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t17)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h2)).add(_sv3.withLane(3, _h3)))).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h4)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _t21)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t18)).add(_sv3.withLane(3, _h5)))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
    }

    public static float[] preRotateAround(float[] dest, int destOffset, float[] src, int srcOffset, float[] rot, int rotOffset, float[] pivot, int pivotOffset) {
        if (SimdSupport.USE_FMA) return preRotateAround_fma(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
        return preRotateAround_mulAdd(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
    }

    public static float[] preRotateAround_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] rot, int rotOffset, float[] pivot, int pivotOffset) {
        float _roty = rot[rotOffset + 1];
        float _pivotz = pivot[pivotOffset + 2];
        float _rotx = rot[rotOffset];
        float _rotz = rot[rotOffset + 2];
        float _rotw = rot[rotOffset + 3];
        float _t4 = _rotx + _rotx;
        float _t5 = _roty + _roty;
        float _t6 = _rotz + _rotz;
        float _t7 = _rotw * _t5;
        float _t8 = _rotw * _t6;
        float _t10 = _rotw * _t4;
        return preRotateAround_fma_s707d7a3d_1(dest, destOffset, src, srcOffset, _roty, _pivotz, _rotx, pivot[pivotOffset], pivot[pivotOffset + 1], -_roty, -_pivotz, -_rotx, _t4, _t5, _rotz * _t6, Math.fma(-_rotz, _t6, 1.0f), Math.fma(_rotz, _t4, _t7), Math.fma(_roty, _t4, _t8), Math.fma(_rotz, _t5, _t10), Math.fma(_roty, _t4, -_t8), Math.fma(_rotz, _t5, -_t10), Math.fma(_rotz, _t4, -_t7));
    }

    /** Piece 2 of {@code preRotateAround_fma}, split to fit the inline budget; reached only through it. */
    private static float[] preRotateAround_fma_s707d7a3d_1(float[] dest, int destOffset, float[] src, int srcOffset, float _roty, float _pivotz, float _rotx, float _pivotx, float _pivoty, float _t0, float _t2, float _t3, float _t4, float _t5, float _t9, float _t14, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21) {
        preRotateAround_fma_s9e20ed84_v(dest, destOffset, src, srcOffset, _t16, _t17, _t18, _t19, _t20, _t21, Math.fma(_t0, _t5, _t14), Math.fma(_t2, _t16, Math.fma(_pivotx, Math.fma(_roty, _t5, _t9), -(_pivoty * _t19))), Math.fma(_t3, _t4, _t14), Math.fma(_t2, _t20, Math.fma(_pivoty, Math.fma(_rotx, _t4, _t9), -(_pivotx * _t17))), Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0f)), Math.fma(-_pivoty, _t18, Math.fma(_pivotz, Math.fma(_rotx, _t4, _roty * _t5), -(_pivotx * _t21))));
        return dest;
    }

    public static float[] preRotateAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] rot, int rotOffset, float[] pivot, int pivotOffset) {
        float _roty = rot[rotOffset + 1];
        float _pivotz = pivot[pivotOffset + 2];
        float _rotx = rot[rotOffset];
        float _rotz = rot[rotOffset + 2];
        float _rotw = rot[rotOffset + 3];
        float _t4 = _rotx + _rotx;
        float _t5 = _roty + _roty;
        float _t6 = _rotz + _rotz;
        float _t7 = _rotw * _t5;
        float _t8 = _rotw * _t6;
        float _t10 = _rotw * _t4;
        return preRotateAround_mulAdd_s62626b90_1(dest, destOffset, src, srcOffset, _roty, _pivotz, _rotx, pivot[pivotOffset], pivot[pivotOffset + 1], -_roty, -_pivotz, -_rotx, _t4, _t5, _rotz * _t6, Math.fma(-_rotz, _t6, 1.0f), Math.fma(_rotz, _t4, _t7), Math.fma(_roty, _t4, _t8), Math.fma(_rotz, _t5, _t10), Math.fma(_roty, _t4, -_t8), Math.fma(_rotz, _t5, -_t10), Math.fma(_rotz, _t4, -_t7));
    }

    /** Piece 2 of {@code preRotateAround_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float[] preRotateAround_mulAdd_s62626b90_1(float[] dest, int destOffset, float[] src, int srcOffset, float _roty, float _pivotz, float _rotx, float _pivotx, float _pivoty, float _t0, float _t2, float _t3, float _t4, float _t5, float _t9, float _t14, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21) {
        preRotateAround_mulAdd_s842595dd_v(dest, destOffset, src, srcOffset, _t16, _t17, _t18, _t19, _t20, _t21, Math.fma(_t0, _t5, _t14), Math.fma(_t2, _t16, Math.fma(_pivotx, Math.fma(_roty, _t5, _t9), -(_pivoty * _t19))), Math.fma(_t3, _t4, _t14), Math.fma(_t2, _t20, Math.fma(_pivoty, Math.fma(_rotx, _t4, _t9), -(_pivotx * _t17))), Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0f)), Math.fma(-_pivoty, _t18, Math.fma(_pivotz, Math.fma(_rotx, _t4, _roty * _t5), -(_pivotx * _t21))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment rot, long rotOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && rot.isNative() && pivot.isNative()) return preRotateAround_fma_unsafe(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
            return preRotateAround_fma_api(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && rot.isNative() && pivot.isNative()) return preRotateAround_mulAdd_unsafe(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
        return preRotateAround_mulAdd_api(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
    }

    public static java.lang.foreign.MemorySegment preRotateAround_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment rot, long rotOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && rot.isNative() && pivot.isNative()) return preRotateAround_fma_unsafe(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
        return preRotateAround_fma_api(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
    }

    public static java.lang.foreign.MemorySegment preRotateAround_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment rot, long rotOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        long _rotBase = rot.address() + rotOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        float _roty = UnsafeOpsHolder.U.getFloat(_rotBase + 4L);
        float _pivotz = UnsafeOpsHolder.U.getFloat(_pivotBase + 8L);
        float _rotx = UnsafeOpsHolder.U.getFloat(_rotBase);
        float _rotz = UnsafeOpsHolder.U.getFloat(_rotBase + 8L);
        float _rotw = UnsafeOpsHolder.U.getFloat(_rotBase + 12L);
        float _pivotx = UnsafeOpsHolder.U.getFloat(_pivotBase);
        float _pivoty = UnsafeOpsHolder.U.getFloat(_pivotBase + 4L);
        float _t4 = _rotx + _rotx;
        float _t5 = _roty + _roty;
        float _t6 = _rotz + _rotz;
        float _t7 = _rotw * _t5;
        float _t8 = _rotw * _t6;
        float _t10 = _rotw * _t4;
        return preRotateAround_fma_unsafe_s9a2dd742_1(dest, destOffset, src, srcOffset, _roty, _pivotz, _rotx, _pivotx, _pivoty, -_roty, -_pivotz, -_rotx, _t4, _t5, _rotz * _t6, Math.fma(-_rotz, _t6, 1.0f), Math.fma(_rotz, _t4, _t7), Math.fma(_roty, _t4, _t8), Math.fma(_rotz, _t5, _t10), Math.fma(_roty, _t4, -_t8), Math.fma(_rotz, _t5, -_t10), Math.fma(_rotz, _t4, -_t7));
    }

    /** Piece 2 of {@code preRotateAround_fma_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_fma_unsafe_s9a2dd742_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _roty, float _pivotz, float _rotx, float _pivotx, float _pivoty, float _t0, float _t2, float _t3, float _t4, float _t5, float _t9, float _t14, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21) {
        preRotateAround_fma_sfeab7c88_v(dest, destOffset, src, srcOffset, _t16, _t17, _t18, _t19, _t20, _t21, Math.fma(_t0, _t5, _t14), Math.fma(_t2, _t16, Math.fma(_pivotx, Math.fma(_roty, _t5, _t9), -(_pivoty * _t19))), Math.fma(_t3, _t4, _t14), Math.fma(_t2, _t20, Math.fma(_pivoty, Math.fma(_rotx, _t4, _t9), -(_pivotx * _t17))), Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0f)), Math.fma(-_pivoty, _t18, Math.fma(_pivotz, Math.fma(_rotx, _t4, _roty * _t5), -(_pivotx * _t21))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAround_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment rot, long rotOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        float _roty = rot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rotOffset + 4L);
        float _pivotz = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 8L);
        float _rotx = rot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rotOffset);
        float _rotz = rot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rotOffset + 8L);
        float _rotw = rot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rotOffset + 12L);
        float _pivotx = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset);
        float _pivoty = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 4L);
        float _t4 = _rotx + _rotx;
        float _t5 = _roty + _roty;
        float _t6 = _rotz + _rotz;
        float _t7 = _rotw * _t5;
        float _t8 = _rotw * _t6;
        float _t10 = _rotw * _t4;
        return preRotateAround_fma_api_s47bde860_1(dest, destOffset, src, srcOffset, _roty, _pivotz, _rotx, _pivotx, _pivoty, -_roty, -_pivotz, -_rotx, _t4, _t5, _rotz * _t6, Math.fma(-_rotz, _t6, 1.0f), Math.fma(_rotz, _t4, _t7), Math.fma(_roty, _t4, _t8), Math.fma(_rotz, _t5, _t10), Math.fma(_roty, _t4, -_t8), Math.fma(_rotz, _t5, -_t10), Math.fma(_rotz, _t4, -_t7));
    }

    /** Piece 2 of {@code preRotateAround_fma_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_fma_api_s47bde860_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _roty, float _pivotz, float _rotx, float _pivotx, float _pivoty, float _t0, float _t2, float _t3, float _t4, float _t5, float _t9, float _t14, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21) {
        preRotateAround_fma_sfeab7c88_v(dest, destOffset, src, srcOffset, _t16, _t17, _t18, _t19, _t20, _t21, Math.fma(_t0, _t5, _t14), Math.fma(_t2, _t16, Math.fma(_pivotx, Math.fma(_roty, _t5, _t9), -(_pivoty * _t19))), Math.fma(_t3, _t4, _t14), Math.fma(_t2, _t20, Math.fma(_pivoty, Math.fma(_rotx, _t4, _t9), -(_pivotx * _t17))), Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0f)), Math.fma(-_pivoty, _t18, Math.fma(_pivotz, Math.fma(_rotx, _t4, _roty * _t5), -(_pivotx * _t21))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAround_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment rot, long rotOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && rot.isNative() && pivot.isNative()) return preRotateAround_mulAdd_unsafe(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
        return preRotateAround_mulAdd_api(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
    }

    public static java.lang.foreign.MemorySegment preRotateAround_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment rot, long rotOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        long _rotBase = rot.address() + rotOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        float _roty = UnsafeOpsHolder.U.getFloat(_rotBase + 4L);
        float _pivotz = UnsafeOpsHolder.U.getFloat(_pivotBase + 8L);
        float _rotx = UnsafeOpsHolder.U.getFloat(_rotBase);
        float _rotz = UnsafeOpsHolder.U.getFloat(_rotBase + 8L);
        float _rotw = UnsafeOpsHolder.U.getFloat(_rotBase + 12L);
        float _pivotx = UnsafeOpsHolder.U.getFloat(_pivotBase);
        float _pivoty = UnsafeOpsHolder.U.getFloat(_pivotBase + 4L);
        float _t4 = _rotx + _rotx;
        float _t5 = _roty + _roty;
        float _t6 = _rotz + _rotz;
        float _t7 = _rotw * _t5;
        float _t8 = _rotw * _t6;
        float _t10 = _rotw * _t4;
        return preRotateAround_mulAdd_unsafe_sdc4f7dd5_1(dest, destOffset, src, srcOffset, _roty, _pivotz, _rotx, _pivotx, _pivoty, -_roty, -_pivotz, -_rotx, _t4, _t5, _rotz * _t6, Math.fma(-_rotz, _t6, 1.0f), Math.fma(_rotz, _t4, _t7), Math.fma(_roty, _t4, _t8), Math.fma(_rotz, _t5, _t10), Math.fma(_roty, _t4, -_t8), Math.fma(_rotz, _t5, -_t10), Math.fma(_rotz, _t4, -_t7));
    }

    /** Piece 2 of {@code preRotateAround_mulAdd_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_mulAdd_unsafe_sdc4f7dd5_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _roty, float _pivotz, float _rotx, float _pivotx, float _pivoty, float _t0, float _t2, float _t3, float _t4, float _t5, float _t9, float _t14, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21) {
        preRotateAround_mulAdd_s2a34d939_v(dest, destOffset, src, srcOffset, _t16, _t17, _t18, _t19, _t20, _t21, Math.fma(_t0, _t5, _t14), Math.fma(_t2, _t16, Math.fma(_pivotx, Math.fma(_roty, _t5, _t9), -(_pivoty * _t19))), Math.fma(_t3, _t4, _t14), Math.fma(_t2, _t20, Math.fma(_pivoty, Math.fma(_rotx, _t4, _t9), -(_pivotx * _t17))), Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0f)), Math.fma(-_pivoty, _t18, Math.fma(_pivotz, Math.fma(_rotx, _t4, _roty * _t5), -(_pivotx * _t21))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAround_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment rot, long rotOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        float _roty = rot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rotOffset + 4L);
        float _pivotz = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 8L);
        float _rotx = rot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rotOffset);
        float _rotz = rot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rotOffset + 8L);
        float _rotw = rot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rotOffset + 12L);
        float _pivotx = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset);
        float _pivoty = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 4L);
        float _t4 = _rotx + _rotx;
        float _t5 = _roty + _roty;
        float _t6 = _rotz + _rotz;
        float _t7 = _rotw * _t5;
        float _t8 = _rotw * _t6;
        float _t10 = _rotw * _t4;
        return preRotateAround_mulAdd_api_s4f92099d_1(dest, destOffset, src, srcOffset, _roty, _pivotz, _rotx, _pivotx, _pivoty, -_roty, -_pivotz, -_rotx, _t4, _t5, _rotz * _t6, Math.fma(-_rotz, _t6, 1.0f), Math.fma(_rotz, _t4, _t7), Math.fma(_roty, _t4, _t8), Math.fma(_rotz, _t5, _t10), Math.fma(_roty, _t4, -_t8), Math.fma(_rotz, _t5, -_t10), Math.fma(_rotz, _t4, -_t7));
    }

    /** Piece 2 of {@code preRotateAround_mulAdd_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_mulAdd_api_s4f92099d_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _roty, float _pivotz, float _rotx, float _pivotx, float _pivoty, float _t0, float _t2, float _t3, float _t4, float _t5, float _t9, float _t14, float _t16, float _t17, float _t18, float _t19, float _t20, float _t21) {
        preRotateAround_mulAdd_s2a34d939_v(dest, destOffset, src, srcOffset, _t16, _t17, _t18, _t19, _t20, _t21, Math.fma(_t0, _t5, _t14), Math.fma(_t2, _t16, Math.fma(_pivotx, Math.fma(_roty, _t5, _t9), -(_pivoty * _t19))), Math.fma(_t3, _t4, _t14), Math.fma(_t2, _t20, Math.fma(_pivoty, Math.fma(_rotx, _t4, _t9), -(_pivotx * _t17))), Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0f)), Math.fma(-_pivoty, _t18, Math.fma(_pivotz, Math.fma(_rotx, _t4, _roty * _t5), -(_pivotx * _t21))));
        return dest;
    }

    public static float[] preRotateAxis(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (SimdSupport.USE_FMA) return preRotateAxis_fma(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return preRotateAxis_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    public static float[] preRotateAxis_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisZ;
        float _t4 = axisX * axisY;
        float _t6 = axisY * axisZ;
        float _t11 = 1.0f - _t1;
        preRotateAxis_fma_s507f1790_v(dest, destOffset, src, srcOffset, Math.fma(axisY, _t0, _t11 * _t2), Math.fma(_t11, axisX * axisX, _t1), Math.fma(_t11, _t4, -(axisZ * _t0)), Math.fma(_t11, _t6, -(axisX * _t0)), Math.fma(axisZ, _t0, _t11 * _t4), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(_t11, _t2, -(axisY * _t0)), Math.fma(axisX, _t0, _t11 * _t6));
        return dest;
    }

    private static void preRotateAxis_fma_s507f1790_v(float[] dest, int destOffset, float[] src, int srcOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h0), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h1), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h2)))).intoArray(dest, destOffset);
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h3), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h4), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h5)))).intoArray(dest, destOffset + 4);
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _h6), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, _h7), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h8)))).intoArray(dest, destOffset + 8);
    }

    public static float[] preRotateAxis_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        preRotateAxis_mulAdd_s4c8392a1_v(dest, destOffset, src, srcOffset, axisX, axisY, axisZ, _t0, _t1, axisX * axisZ, axisX * axisY, axisY * axisZ, 1.0f - _t1);
        return dest;
    }

    private static void preRotateAxis_mulAdd_s4c8392a1_v(float[] dest, int destOffset, float[] src, int srcOffset, float axisX, float axisY, float axisZ, float _t0, float _t1, float _t2, float _t4, float _t6, float _t11) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(axisY, _t0, _t11 * _t2))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, axisX * axisX, _t1))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t4, -(axisZ * _t0)))))).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t6, -(axisX * _t0)))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(axisZ, _t0, _t11 * _t4))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, axisY * axisY, _t1))))).intoArray(dest, destOffset + 4);
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, axisZ * axisZ, _t1))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t2, -(axisY * _t0)))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(axisX, _t0, _t11 * _t6))))).intoArray(dest, destOffset + 8);
    }

    public static java.lang.foreign.MemorySegment preRotateAxis(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (SimdSupport.USE_FMA) return preRotateAxis_fma(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return preRotateAxis_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    public static java.lang.foreign.MemorySegment preRotateAxis_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        preRotateAxis_fma_s17b632cc_v(dest, destOffset, src, srcOffset, axisX, axisY, axisZ, _t0, _t1, axisX * axisZ, axisX * axisY, axisY * axisZ, 1.0f - _t1);
        return dest;
    }

    private static void preRotateAxis_fma_s17b632cc_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float axisX, float axisY, float axisZ, float _t0, float _t1, float _t2, float _t4, float _t6, float _t11) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(axisY, _t0, _t11 * _t2)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, axisX * axisX, _t1)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t4, -(axisZ * _t0)))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t6, -(axisX * _t0))), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(axisZ, _t0, _t11 * _t4)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, axisY * axisY, _t1))))).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, axisZ * axisZ, _t1)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t2, -(axisY * _t0))), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(axisX, _t0, _t11 * _t6))))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment preRotateAxis_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisZ;
        float _t4 = axisX * axisY;
        float _t6 = axisY * axisZ;
        float _t11 = 1.0f - _t1;
        preRotateAxis_mulAdd_sa988e115_v(dest, destOffset, src, srcOffset, Math.fma(axisY, _t0, _t11 * _t2), Math.fma(_t11, axisX * axisX, _t1), Math.fma(_t11, _t4, -(axisZ * _t0)), Math.fma(_t11, _t6, -(axisX * _t0)), Math.fma(axisZ, _t0, _t11 * _t4), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(_t11, _t2, -(axisY * _t0)), Math.fma(axisX, _t0, _t11 * _t6));
        return dest;
    }

    private static void preRotateAxis_mulAdd_sa988e115_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h0)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h1)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h2)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h3)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h4)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h5)))).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _h6)).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, _h7)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _h8)))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
    }

    public static float[] preRotateAxis(float[] dest, int destOffset, float[] src, int srcOffset, float[] axis, int axisOffset, float angle) {
        if (SimdSupport.USE_FMA) return preRotateAxis_fma(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return preRotateAxis_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    public static float[] preRotateAxis_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] axis, int axisOffset, float angle) {
        float _axisy = axis[axisOffset + 1];
        float _axisx = axis[axisOffset];
        float _axisz = axis[axisOffset + 2];
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        preRotateAxis_fma_sb08dc62e_v(dest, destOffset, src, srcOffset, _axisy, _axisx, _axisz, _t0, _t1, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, 1.0f - _t1);
        return dest;
    }

    private static void preRotateAxis_fma_sb08dc62e_v(float[] dest, int destOffset, float[] src, int srcOffset, float _axisy, float _axisx, float _axisz, float _t0, float _t1, float _t2, float _t4, float _t6, float _t11) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_axisy, _t0, _t11 * _t2)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisx * _axisx, _t1)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t4, -(_axisz * _t0)))))).intoArray(dest, destOffset);
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t6, -(_axisx * _t0))), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_axisz, _t0, _t11 * _t4)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisy * _axisy, _t1))))).intoArray(dest, destOffset + 4);
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisz * _axisz, _t1)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t2, -(_axisy * _t0))), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_axisx, _t0, _t11 * _t6))))).intoArray(dest, destOffset + 8);
    }

    public static float[] preRotateAxis_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] axis, int axisOffset, float angle) {
        float _axisy = axis[axisOffset + 1];
        float _axisx = axis[axisOffset];
        float _axisz = axis[axisOffset + 2];
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        preRotateAxis_mulAdd_s7a4f62b5_v(dest, destOffset, src, srcOffset, _axisy, _axisx, _axisz, _t0, _t1, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, 1.0f - _t1);
        return dest;
    }

    private static void preRotateAxis_mulAdd_s7a4f62b5_v(float[] dest, int destOffset, float[] src, int srcOffset, float _axisy, float _axisx, float _axisz, float _t0, float _t1, float _t2, float _t4, float _t6, float _t11) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_axisy, _t0, _t11 * _t2))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisx * _axisx, _t1))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t4, -(_axisz * _t0)))))).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t6, -(_axisx * _t0)))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_axisz, _t0, _t11 * _t4))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisy * _axisy, _t1))))).intoArray(dest, destOffset + 4);
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisz * _axisz, _t1))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t2, -(_axisy * _t0)))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_axisx, _t0, _t11 * _t6))))).intoArray(dest, destOffset + 8);
    }

    public static java.lang.foreign.MemorySegment preRotateAxis(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && axis.isNative()) return preRotateAxis_fma_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
            return preRotateAxis_fma_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && axis.isNative()) return preRotateAxis_mulAdd_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return preRotateAxis_mulAdd_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    public static java.lang.foreign.MemorySegment preRotateAxis_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && axis.isNative()) return preRotateAxis_fma_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return preRotateAxis_fma_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    public static java.lang.foreign.MemorySegment preRotateAxis_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        long _axisBase = axis.address() + axisOffset;
        float _axisy = UnsafeOpsHolder.U.getFloat(_axisBase + 4L);
        float _axisx = UnsafeOpsHolder.U.getFloat(_axisBase);
        float _axisz = UnsafeOpsHolder.U.getFloat(_axisBase + 8L);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        preRotateAxis_fma_unsafe_sd956cac6_v(dest, destOffset, src, srcOffset, _axisy, _axisx, _axisz, _t0, _t1, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, 1.0f - _t1);
        return dest;
    }

    private static void preRotateAxis_fma_unsafe_sd956cac6_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _axisy, float _axisx, float _axisz, float _t0, float _t1, float _t2, float _t4, float _t6, float _t11) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_axisy, _t0, _t11 * _t2)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisx * _axisx, _t1)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t4, -(_axisz * _t0)))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t6, -(_axisx * _t0))), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_axisz, _t0, _t11 * _t4)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisy * _axisy, _t1))))).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisz * _axisz, _t1)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t2, -(_axisy * _t0))), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_axisx, _t0, _t11 * _t6))))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment preRotateAxis_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        float _axisy = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _axisx = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset);
        float _axisz = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        preRotateAxis_fma_unsafe_sd956cac6_v(dest, destOffset, src, srcOffset, _axisy, _axisx, _axisz, _t0, _t1, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, 1.0f - _t1);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAxis_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && axis.isNative()) return preRotateAxis_mulAdd_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return preRotateAxis_mulAdd_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    public static java.lang.foreign.MemorySegment preRotateAxis_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        long _axisBase = axis.address() + axisOffset;
        float _axisx = UnsafeOpsHolder.U.getFloat(_axisBase);
        float _axisz = UnsafeOpsHolder.U.getFloat(_axisBase + 8L);
        float _axisy = UnsafeOpsHolder.U.getFloat(_axisBase + 4L);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = _axisx * _axisz;
        float _t4 = _axisx * _axisy;
        float _t6 = _axisy * _axisz;
        float _t11 = 1.0f - _t1;
        preRotateAxis_mulAdd_sa988e115_v(dest, destOffset, src, srcOffset, Math.fma(_axisy, _t0, _t11 * _t2), Math.fma(_t11, _axisx * _axisx, _t1), Math.fma(_t11, _t4, -(_axisz * _t0)), Math.fma(_t11, _t6, -(_axisx * _t0)), Math.fma(_axisz, _t0, _t11 * _t4), Math.fma(_t11, _axisy * _axisy, _t1), Math.fma(_t11, _axisz * _axisz, _t1), Math.fma(_t11, _t2, -(_axisy * _t0)), Math.fma(_axisx, _t0, _t11 * _t6));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAxis_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        float _axisx = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset);
        float _axisz = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
        float _axisy = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = _axisx * _axisz;
        float _t4 = _axisx * _axisy;
        float _t6 = _axisy * _axisz;
        float _t11 = 1.0f - _t1;
        preRotateAxis_mulAdd_sa988e115_v(dest, destOffset, src, srcOffset, Math.fma(_axisy, _t0, _t11 * _t2), Math.fma(_t11, _axisx * _axisx, _t1), Math.fma(_t11, _t4, -(_axisz * _t0)), Math.fma(_t11, _t6, -(_axisx * _t0)), Math.fma(_axisz, _t0, _t11 * _t4), Math.fma(_t11, _axisy * _axisy, _t1), Math.fma(_t11, _axisz * _axisz, _t1), Math.fma(_t11, _t2, -(_axisy * _t0)), Math.fma(_axisx, _t0, _t11 * _t6));
        return dest;
    }

    public static float[] preRotateQuat(float[] dest, int destOffset, float[] src, int srcOffset, float qX, float qY, float qZ, float qW) {
        if (SimdSupport.USE_FMA) return preRotateQuat_fma(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return preRotateQuat_mulAdd(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
    }

    public static float[] preRotateQuat_fma(float[] dest, int destOffset, float[] src, int srcOffset, float qX, float qY, float qZ, float qW) {
        float _t0 = -qY;
        float _t2 = -qX;
        float _t3 = qX + qX;
        float _t4 = qY + qY;
        float _t5 = qZ + qZ;
        float _t6 = qW * _t4;
        float _t7 = qW * _t5;
        float _t8 = qW * _t3;
        float _t12 = Math.fma(-qZ, _t5, 1.0f);
        preRotateAxis_fma_s507f1790_v(dest, destOffset, src, srcOffset, Math.fma(qZ, _t3, _t6), Math.fma(_t0, _t4, _t12), Math.fma(qY, _t3, -_t7), Math.fma(qZ, _t4, -_t8), Math.fma(qY, _t3, _t7), Math.fma(_t2, _t3, _t12), Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)), Math.fma(qZ, _t3, -_t6), Math.fma(qZ, _t4, _t8));
        return dest;
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
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t3, _t6))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t4, _t12))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qY, _t3, -_t7))))).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t4, -_t8))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qY, _t3, _t7))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, _t12))))).intoArray(dest, destOffset + 4);
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t3, -_t6))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t4, _t8))))).intoArray(dest, destOffset + 8);
    }

    public static java.lang.foreign.MemorySegment preRotateQuat(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float qX, float qY, float qZ, float qW) {
        if (SimdSupport.USE_FMA) return preRotateQuat_fma(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return preRotateQuat_mulAdd(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
    }

    public static java.lang.foreign.MemorySegment preRotateQuat_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float qX, float qY, float qZ, float qW) {
        float _t3 = qX + qX;
        float _t4 = qY + qY;
        float _t5 = qZ + qZ;
        preRotateQuat_fma_s5ba23131_v(dest, destOffset, src, srcOffset, qY, qZ, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, Math.fma(-qZ, _t5, 1.0f));
        return dest;
    }

    private static void preRotateQuat_fma_s5ba23131_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float qY, float qZ, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t3, _t6)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t4, _t12)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qY, _t3, -_t7))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t4, -_t8)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qY, _t3, _t7)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, _t12))))).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f))), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t3, -_t6)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t4, _t8))))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment preRotateQuat_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float qX, float qY, float qZ, float qW) {
        float _t3 = qX + qX;
        float _t4 = qY + qY;
        float _t5 = qZ + qZ;
        preRotateQuat_mulAdd_scdf5fb38_v(dest, destOffset, src, srcOffset, qY, qZ, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, Math.fma(-qZ, _t5, 1.0f));
        return dest;
    }

    private static void preRotateQuat_mulAdd_scdf5fb38_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float qY, float qZ, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t3, _t6))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t4, _t12))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qY, _t3, -_t7))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t4, -_t8))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qY, _t3, _t7))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, _t12))))).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t3, -_t6))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t4, _t8))))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
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
        preRotateQuat_fma_s59e36b6f_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0f));
        return dest;
    }

    private static void preRotateQuat_fma_s59e36b6f_v(float[] dest, int destOffset, float[] src, int srcOffset, float _qz, float _qy, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t3, _t6)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t4, _t12)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qy, _t3, -_t7))))).intoArray(dest, destOffset);
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t4, -_t8)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qy, _t3, _t7)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, _t12))))).intoArray(dest, destOffset + 4);
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f))), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t3, -_t6)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t4, _t8))))).intoArray(dest, destOffset + 8);
    }

    public static float[] preRotateQuat_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] q, int qOffset) {
        float _qz = q[qOffset + 2];
        float _qy = q[qOffset + 1];
        float _qx = q[qOffset];
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
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t3, _t6))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t4, _t12))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qy, _t3, -_t7))))).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t4, -_t8))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qy, _t3, _t7))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, _t12))))).intoArray(dest, destOffset + 4);
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t3, -_t6))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t4, _t8))))).intoArray(dest, destOffset + 8);
    }

    public static java.lang.foreign.MemorySegment preRotateQuat(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && q.isNative()) return preRotateQuat_fma_unsafe(dest, destOffset, src, srcOffset, q, qOffset);
            return preRotateQuat_fma_api(dest, destOffset, src, srcOffset, q, qOffset);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && q.isNative()) return preRotateQuat_mulAdd_unsafe(dest, destOffset, src, srcOffset, q, qOffset);
        return preRotateQuat_mulAdd_api(dest, destOffset, src, srcOffset, q, qOffset);
    }

    public static java.lang.foreign.MemorySegment preRotateQuat_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && q.isNative()) return preRotateQuat_fma_unsafe(dest, destOffset, src, srcOffset, q, qOffset);
        return preRotateQuat_fma_api(dest, destOffset, src, srcOffset, q, qOffset);
    }

    public static java.lang.foreign.MemorySegment preRotateQuat_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        long _qBase = q.address() + qOffset;
        float _qz = UnsafeOpsHolder.U.getFloat(_qBase + 8L);
        float _qy = UnsafeOpsHolder.U.getFloat(_qBase + 4L);
        float _qx = UnsafeOpsHolder.U.getFloat(_qBase);
        float _qw = UnsafeOpsHolder.U.getFloat(_qBase + 12L);
        float _t3 = _qx + _qx;
        float _t4 = _qy + _qy;
        float _t5 = _qz + _qz;
        preRotateQuat_fma_unsafe_s15d63f2d_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0f));
        return dest;
    }

    private static void preRotateQuat_fma_unsafe_s15d63f2d_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _qz, float _qy, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t3, _t6)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t4, _t12)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qy, _t3, -_t7))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t4, -_t8)), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qy, _t3, _t7)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, _t12))))).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f))), _sv1.fma(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t3, -_t6)), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t4, _t8))))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment preRotateQuat_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        float _qz = q.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, qOffset + 8L);
        float _qy = q.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, qOffset + 4L);
        float _qx = q.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, qOffset);
        float _qw = q.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, qOffset + 12L);
        float _t3 = _qx + _qx;
        float _t4 = _qy + _qy;
        float _t5 = _qz + _qz;
        preRotateQuat_fma_unsafe_s15d63f2d_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0f));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateQuat_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && q.isNative()) return preRotateQuat_mulAdd_unsafe(dest, destOffset, src, srcOffset, q, qOffset);
        return preRotateQuat_mulAdd_api(dest, destOffset, src, srcOffset, q, qOffset);
    }

    public static java.lang.foreign.MemorySegment preRotateQuat_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        long _qBase = q.address() + qOffset;
        float _qz = UnsafeOpsHolder.U.getFloat(_qBase + 8L);
        float _qy = UnsafeOpsHolder.U.getFloat(_qBase + 4L);
        float _qx = UnsafeOpsHolder.U.getFloat(_qBase);
        float _qw = UnsafeOpsHolder.U.getFloat(_qBase + 12L);
        float _t3 = _qx + _qx;
        float _t4 = _qy + _qy;
        float _t5 = _qz + _qz;
        preRotateQuat_mulAdd_unsafe_se1af62fc_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0f));
        return dest;
    }

    private static void preRotateQuat_mulAdd_unsafe_se1af62fc_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _qz, float _qy, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t3, _t6))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t4, _t12))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qy, _t3, -_t7))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t4, -_t8))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qy, _t3, _t7))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, _t12))))).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)))).add(_sv1.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t3, -_t6))).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t4, _t8))))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment preRotateQuat_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        float _qz = q.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, qOffset + 8L);
        float _qy = q.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, qOffset + 4L);
        float _qx = q.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, qOffset);
        float _qw = q.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, qOffset + 12L);
        float _t3 = _qx + _qx;
        float _t4 = _qy + _qy;
        float _t5 = _qz + _qz;
        preRotateQuat_mulAdd_unsafe_se1af62fc_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0f));
        return dest;
    }

    public static float[] preRotateX(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        if (SimdSupport.USE_FMA) return preRotateX_fma(dest, destOffset, src, srcOffset, angle);
        return preRotateX_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static float[] preRotateX_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t0 = Math.sin(angle);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
        _sv0.fma(_sv1, _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -_t0))).intoArray(dest, destOffset + 4);
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t0), _sv2.mul(_sv1)).intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preRotateX_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t0 = Math.sin(angle);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
        _sv0.mul(_sv1).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -_t0))).intoArray(dest, destOffset + 4);
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t0)).add(_sv2.mul(_sv1)).intoArray(dest, destOffset + 8);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateX(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        if (SimdSupport.USE_FMA) return preRotateX_fma(dest, destOffset, src, srcOffset, angle);
        return preRotateX_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static java.lang.foreign.MemorySegment preRotateX_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _t0 = Math.sin(angle);
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(_sv1, _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -_t0))).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t0), _sv2.mul(_sv1)).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateX_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _t0 = Math.sin(angle);
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(_sv1).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -_t0))).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t0)).add(_sv2.mul(_sv1)).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] preRotateY(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        if (SimdSupport.USE_FMA) return preRotateY_fma(dest, destOffset, src, srcOffset, angle);
        return preRotateY_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static float[] preRotateY_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t0 = Math.sin(angle);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        _sv0.fma(_sv1, _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t0))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _sv2.fma(_sv1, _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -_t0))).intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preRotateY_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t0 = Math.sin(angle);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        _sv0.mul(_sv1).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t0))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _sv2.mul(_sv1).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -_t0))).intoArray(dest, destOffset + 8);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateY(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        if (SimdSupport.USE_FMA) return preRotateY_fma(dest, destOffset, src, srcOffset, angle);
        return preRotateY_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static java.lang.foreign.MemorySegment preRotateY_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _t0 = Math.sin(angle);
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _c1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(_sv1, _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t0))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _sv2.fma(_sv1, _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -_t0))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateY_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _t0 = Math.sin(angle);
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _c1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(_sv1).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t0))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _sv2.mul(_sv1).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, -_t0))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] preRotateZ(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        if (SimdSupport.USE_FMA) return preRotateZ_fma(dest, destOffset, src, srcOffset, angle);
        return preRotateZ_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static float[] preRotateZ_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t0 = Math.sin(angle);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        _sv0.fma(_sv1, _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -_t0))).intoArray(dest, destOffset);
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t0), _sv2.mul(_sv1)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preRotateZ_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle) {
        float _t0 = Math.sin(angle);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        _sv0.mul(_sv1).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -_t0))).intoArray(dest, destOffset);
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t0)).add(_sv2.mul(_sv1)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateZ(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        if (SimdSupport.USE_FMA) return preRotateZ_fma(dest, destOffset, src, srcOffset, angle);
        return preRotateZ_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static java.lang.foreign.MemorySegment preRotateZ_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _t0 = Math.sin(angle);
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder());
        var _c2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(_sv1, _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -_t0))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t0), _sv2.mul(_sv1)).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateZ_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle) {
        float _t0 = Math.sin(angle);
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder());
        var _c2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(_sv1).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, -_t0))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t0)).add(_sv2.mul(_sv1)).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] preScale(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(SIMD_SPECIES, vY));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).mul(FloatVector.broadcast(SIMD_SPECIES, vZ));
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, vX)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        var _c1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, vY));
        var _c2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, vZ));
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, vX)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] preScale(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(FloatVector.broadcast(SIMD_SPECIES, v[vOffset + 1]));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).mul(FloatVector.broadcast(SIMD_SPECIES, v[vOffset + 2]));
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, v[vOffset])).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && v.isNative()) return preScale_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return preScale_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    public static java.lang.foreign.MemorySegment preScale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _vBase = v.address() + vOffset;
        var _c1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_vBase + 4L)));
        var _c2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_vBase + 8L)));
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_vBase))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        var _c1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset + 4L)));
        var _c2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset + 8L)));
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] preScale(float[] dest, int destOffset, float[] src, int srcOffset, float s) {
        for (int _li = 0; _li < 3; _li++) {
            FloatVector.broadcast(SIMD_SPECIES, s).mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s) {
        if (SimdSupport.DOUBLE_COLUMNS && SimdSupport.X86 && dest.isNative() && src.isNative()) {
            FloatVector.broadcast(FloatVector.SPECIES_256, s).mul(FloatVector.fromMemorySegment(FloatVector.SPECIES_256, src, srcOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            FloatVector.broadcast(SIMD_SPECIES, s).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        } else {
            for (int _li = 0; _li < 3; _li++) {
                FloatVector.broadcast(SIMD_SPECIES, s).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 16L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
            }
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
        var _c1 = _sv0.fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4), _sv1.withLane(3, pivotY * _t0));
        var _c2 = _sv0.fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8), _sv1.withLane(3, pivotZ * _t0));
        _sv0.fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset), _sv1.withLane(3, pivotX * _t0)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preScaleAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        float _t0 = 1.0f - s;
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4)).add(_sv1.withLane(3, pivotY * _t0));
        var _c2 = _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8)).add(_sv1.withLane(3, pivotZ * _t0));
        _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).add(_sv1.withLane(3, pivotX * _t0)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        if (SimdSupport.USE_FMA) return preScaleAround_fma(dest, destOffset, src, srcOffset, s, pivotX, pivotY, pivotZ);
        return preScaleAround_mulAdd(dest, destOffset, src, srcOffset, s, pivotX, pivotY, pivotZ);
    }

    public static java.lang.foreign.MemorySegment preScaleAround_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        float _t0 = 1.0f - s;
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = _sv0.fma(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder()), _sv1.withLane(3, pivotY * _t0));
        var _c2 = _sv0.fma(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()), _sv1.withLane(3, pivotZ * _t0));
        _sv0.fma(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()), _sv1.withLane(3, pivotX * _t0)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        float _t0 = 1.0f - s;
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = _sv0.mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder())).add(_sv1.withLane(3, pivotY * _t0));
        var _c2 = _sv0.mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder())).add(_sv1.withLane(3, pivotZ * _t0));
        _sv0.mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).add(_sv1.withLane(3, pivotX * _t0)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
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
        var _c1 = _sv0.fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4), _sv1.withLane(3, pivot[pivotOffset + 1] * _t0));
        var _c2 = _sv0.fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8), _sv1.withLane(3, pivot[pivotOffset + 2] * _t0));
        _sv0.fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset), _sv1.withLane(3, pivot[pivotOffset] * _t0)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preScaleAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float s) {
        float _t0 = 1.0f - s;
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4)).add(_sv1.withLane(3, pivot[pivotOffset + 1] * _t0));
        var _c2 = _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8)).add(_sv1.withLane(3, pivot[pivotOffset + 2] * _t0));
        _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).add(_sv1.withLane(3, pivot[pivotOffset] * _t0)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float s) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && pivot.isNative()) return preScaleAround_fma_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
            return preScaleAround_fma_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && pivot.isNative()) return preScaleAround_mulAdd_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return preScaleAround_mulAdd_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    public static java.lang.foreign.MemorySegment preScaleAround_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && pivot.isNative()) return preScaleAround_fma_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return preScaleAround_fma_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    public static java.lang.foreign.MemorySegment preScaleAround_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float s) {
        long _pivotBase = pivot.address() + pivotOffset;
        float _t0 = 1.0f - s;
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = _sv0.fma(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder()), _sv1.withLane(3, UnsafeOpsHolder.U.getFloat(_pivotBase + 4L) * _t0));
        var _c2 = _sv0.fma(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()), _sv1.withLane(3, UnsafeOpsHolder.U.getFloat(_pivotBase + 8L) * _t0));
        _sv0.fma(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()), _sv1.withLane(3, UnsafeOpsHolder.U.getFloat(_pivotBase) * _t0)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float s) {
        float _t0 = 1.0f - s;
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = _sv0.fma(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder()), _sv1.withLane(3, pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 4L) * _t0));
        var _c2 = _sv0.fma(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()), _sv1.withLane(3, pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 8L) * _t0));
        _sv0.fma(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()), _sv1.withLane(3, pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset) * _t0)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && pivot.isNative()) return preScaleAround_mulAdd_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return preScaleAround_mulAdd_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    public static java.lang.foreign.MemorySegment preScaleAround_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float s) {
        long _pivotBase = pivot.address() + pivotOffset;
        float _t0 = 1.0f - s;
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = _sv0.mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder())).add(_sv1.withLane(3, UnsafeOpsHolder.U.getFloat(_pivotBase + 4L) * _t0));
        var _c2 = _sv0.mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder())).add(_sv1.withLane(3, UnsafeOpsHolder.U.getFloat(_pivotBase + 8L) * _t0));
        _sv0.mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).add(_sv1.withLane(3, UnsafeOpsHolder.U.getFloat(_pivotBase) * _t0)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float s) {
        float _t0 = 1.0f - s;
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = _sv0.mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder())).add(_sv1.withLane(3, pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 4L) * _t0));
        var _c2 = _sv0.mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder())).add(_sv1.withLane(3, pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 8L) * _t0));
        _sv0.mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).add(_sv1.withLane(3, pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset) * _t0)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] preScaleAround(float[] dest, int destOffset, float[] src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        if (SimdSupport.USE_FMA) return preScaleAround_fma(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return preScaleAround_mulAdd(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
    }

    public static float[] preScaleAround_fma(float[] dest, int destOffset, float[] src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, sY).fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4), _sv0.withLane(3, pivotY * (1.0f - sY)));
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, sZ).fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8), _sv0.withLane(3, pivotZ * (1.0f - sZ)));
        FloatVector.broadcast(SIMD_SPECIES, sX).fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset), _sv0.withLane(3, pivotX * (1.0f - sX))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preScaleAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, sY).mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4)).add(_sv0.withLane(3, pivotY * (1.0f - sY)));
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, sZ).mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8)).add(_sv0.withLane(3, pivotZ * (1.0f - sZ)));
        FloatVector.broadcast(SIMD_SPECIES, sX).mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).add(_sv0.withLane(3, pivotX * (1.0f - sX))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        if (SimdSupport.USE_FMA) return preScaleAround_fma(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return preScaleAround_mulAdd(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
    }

    public static java.lang.foreign.MemorySegment preScaleAround_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, sY).fma(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder()), _sv0.withLane(3, pivotY * (1.0f - sY)));
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, sZ).fma(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()), _sv0.withLane(3, pivotZ * (1.0f - sZ)));
        FloatVector.broadcast(SIMD_SPECIES, sX).fma(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()), _sv0.withLane(3, pivotX * (1.0f - sX))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, sY).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder())).add(_sv0.withLane(3, pivotY * (1.0f - sY)));
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, sZ).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder())).add(_sv0.withLane(3, pivotZ * (1.0f - sZ)));
        FloatVector.broadcast(SIMD_SPECIES, sX).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).add(_sv0.withLane(3, pivotX * (1.0f - sX))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
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
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, _sy).fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4), _sv0.withLane(3, pivot[pivotOffset + 1] * (1.0f - _sy)));
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, _sz).fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8), _sv0.withLane(3, pivot[pivotOffset + 2] * (1.0f - _sz)));
        FloatVector.broadcast(SIMD_SPECIES, _sx).fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset), _sv0.withLane(3, pivot[pivotOffset] * (1.0f - _sx))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static float[] preScaleAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] s, int sOffset, float[] pivot, int pivotOffset) {
        float _sx = s[sOffset];
        float _sy = s[sOffset + 1];
        float _sz = s[sOffset + 2];
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, _sy).mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4)).add(_sv0.withLane(3, pivot[pivotOffset + 1] * (1.0f - _sy)));
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, _sz).mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8)).add(_sv0.withLane(3, pivot[pivotOffset + 2] * (1.0f - _sz)));
        FloatVector.broadcast(SIMD_SPECIES, _sx).mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).add(_sv0.withLane(3, pivot[pivotOffset] * (1.0f - _sx))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && s.isNative() && pivot.isNative()) return preScaleAround_fma_unsafe(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
            return preScaleAround_fma_api(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && s.isNative() && pivot.isNative()) return preScaleAround_mulAdd_unsafe(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return preScaleAround_mulAdd_api(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    public static java.lang.foreign.MemorySegment preScaleAround_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && s.isNative() && pivot.isNative()) return preScaleAround_fma_unsafe(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return preScaleAround_fma_api(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    public static java.lang.foreign.MemorySegment preScaleAround_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        long _sBase = s.address() + sOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        float _sx = UnsafeOpsHolder.U.getFloat(_sBase);
        float _sy = UnsafeOpsHolder.U.getFloat(_sBase + 4L);
        float _sz = UnsafeOpsHolder.U.getFloat(_sBase + 8L);
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, _sy).fma(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder()), _sv0.withLane(3, UnsafeOpsHolder.U.getFloat(_pivotBase + 4L) * (1.0f - _sy)));
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, _sz).fma(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()), _sv0.withLane(3, UnsafeOpsHolder.U.getFloat(_pivotBase + 8L) * (1.0f - _sz)));
        FloatVector.broadcast(SIMD_SPECIES, _sx).fma(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()), _sv0.withLane(3, UnsafeOpsHolder.U.getFloat(_pivotBase) * (1.0f - _sx))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        float _sx = s.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, sOffset);
        float _sy = s.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, sOffset + 4L);
        float _sz = s.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, sOffset + 8L);
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, _sy).fma(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder()), _sv0.withLane(3, pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 4L) * (1.0f - _sy)));
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, _sz).fma(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()), _sv0.withLane(3, pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 8L) * (1.0f - _sz)));
        FloatVector.broadcast(SIMD_SPECIES, _sx).fma(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()), _sv0.withLane(3, pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset) * (1.0f - _sx))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && s.isNative() && pivot.isNative()) return preScaleAround_mulAdd_unsafe(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return preScaleAround_mulAdd_api(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    public static java.lang.foreign.MemorySegment preScaleAround_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        long _sBase = s.address() + sOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        float _sx = UnsafeOpsHolder.U.getFloat(_sBase);
        float _sy = UnsafeOpsHolder.U.getFloat(_sBase + 4L);
        float _sz = UnsafeOpsHolder.U.getFloat(_sBase + 8L);
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, _sy).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder())).add(_sv0.withLane(3, UnsafeOpsHolder.U.getFloat(_pivotBase + 4L) * (1.0f - _sy)));
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, _sz).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder())).add(_sv0.withLane(3, UnsafeOpsHolder.U.getFloat(_pivotBase + 8L) * (1.0f - _sz)));
        FloatVector.broadcast(SIMD_SPECIES, _sx).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).add(_sv0.withLane(3, UnsafeOpsHolder.U.getFloat(_pivotBase) * (1.0f - _sx))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        float _sx = s.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, sOffset);
        float _sy = s.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, sOffset + 4L);
        float _sz = s.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, sOffset + 8L);
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, _sy).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder())).add(_sv0.withLane(3, pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 4L) * (1.0f - _sy)));
        var _c2 = FloatVector.broadcast(SIMD_SPECIES, _sz).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder())).add(_sv0.withLane(3, pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 8L) * (1.0f - _sz)));
        FloatVector.broadcast(SIMD_SPECIES, _sx).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).add(_sv0.withLane(3, pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset) * (1.0f - _sx))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] preTranslate(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).add(_sv0.withLane(3, vY));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).add(_sv0.withLane(3, vZ));
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).add(_sv0.withLane(3, vX)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preTranslate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder()).add(_sv0.withLane(3, vY));
        var _c2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).add(_sv0.withLane(3, vZ));
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).add(_sv0.withLane(3, vX)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] preTranslate(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).add(_sv0.withLane(3, v[vOffset + 1]));
        var _c2 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).add(_sv0.withLane(3, v[vOffset + 2]));
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).add(_sv0.withLane(3, v[vOffset])).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preTranslate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && v.isNative()) return preTranslate_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return preTranslate_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    public static java.lang.foreign.MemorySegment preTranslate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _vBase = v.address() + vOffset;
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder()).add(_sv0.withLane(3, UnsafeOpsHolder.U.getFloat(_vBase + 4L)));
        var _c2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).add(_sv0.withLane(3, UnsafeOpsHolder.U.getFloat(_vBase + 8L)));
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).add(_sv0.withLane(3, UnsafeOpsHolder.U.getFloat(_vBase))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preTranslate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder()).add(_sv0.withLane(3, v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset + 4L)));
        var _c2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).add(_sv0.withLane(3, v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset + 8L)));
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).add(_sv0.withLane(3, v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] reflect(float[] dest, int destOffset, float[] src, int srcOffset, float normalX, float normalY, float normalZ) {
        if (SimdSupport.USE_FMA) return reflect_fma(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
        return reflect_mulAdd(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
    }

    public static float[] reflect_fma(float[] dest, int destOffset, float[] src, int srcOffset, float normalX, float normalY, float normalZ) {
        float _self22 = src[srcOffset + 10];
        reflect_fma_s4659ae95_v(dest, destOffset, src, srcOffset, src[srcOffset + 2], src[srcOffset], src[srcOffset + 1], src[srcOffset + 6], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 8], src[srcOffset + 9], _self22, -_self22, (normalX + normalX) * normalZ, (normalX + normalX) * normalY, (normalY + normalY) * normalZ, Math.fma(-2.0f, normalX * normalX, 1.0f), Math.fma(-2.0f, normalY * normalY, 1.0f), Math.fma(-2.0f, normalZ * normalZ, 1.0f));
        return dest;
    }

    private static void reflect_fma_s4659ae95_v(float[] dest, int destOffset, float[] src, int srcOffset, float _self02, float _self00, float _self01, float _self12, float _self10, float _self11, float _self20, float _self21, float _self22, float _t2, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14) {
        float _ld0 = src[srcOffset + 7];
        float _ld1 = src[srcOffset + 11];
        float _ld2 = src[srcOffset + 3];
        reflect_fma_s4659ae95_v_sdc725724_1(dest, destOffset, _self02, _self00, _self01, _self12, _self10, _self11, _self20, _self21, _self22, _t2, _t9, _t10, _t11, _t12, _t13, _t14, _ld0, _ld1, _ld2);
    }

    /** Part 1 of {@code reflect_fma_s4659ae95_v}, split to fit the inline budget; reached only through it. */
    private static void reflect_fma_s4659ae95_v_sdc725724_1(float[] dest, int destOffset, float _self02, float _self00, float _self01, float _self12, float _self10, float _self11, float _self20, float _self21, float _self22, float _t2, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14, float _ld0, float _ld1, float _ld2) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t9).withLane(1, _t11).withLane(2, _t14);
        var _c1 = FloatVector.broadcast(SIMD_SPECIES, -_self12).withLane(2, _self12).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, _self10 * _t12).withLane(1, _self11 * _t13).sub(FloatVector.broadcast(SIMD_SPECIES, _self11 * _t10).withLane(1, _self10 * _t10)).withLane(2, -_self11 * _t11 - _self10 * _t9)).withLane(3, _ld0);
        var _c2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t2 * _t9 + _self20 * _t12 - _self21 * _t10).withLane(1, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10)).withLane(2, _self22 * _t14 + (-_self21 * _t11 - _self20 * _t9)).withLane(3, _ld1);
        FloatVector.broadcast(SIMD_SPECIES, -_self02).withLane(2, _self02).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, _self00 * _t12).withLane(1, _self01 * _t13).sub(FloatVector.broadcast(SIMD_SPECIES, _self01 * _t10).withLane(1, _self00 * _t10)).withLane(2, -_self01 * _t11 - _self00 * _t9)).withLane(3, _ld2).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
    }

    public static float[] reflect_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float normalX, float normalY, float normalZ) {
        float _self22 = src[srcOffset + 10];
        float _self02 = src[srcOffset + 2];
        float _self00 = src[srcOffset];
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
        reflect_mulAdd_s88ff2b0e_v(dest, destOffset, _t9, _t11, _t14, -_self02, _self02, _self00 * _t12, _self01 * _t13, _self01 * _t10, _self00 * _t10, -_self01 * _t11 - _self00 * _t9, src[srcOffset + 3], -_self12, _self12, _self10 * _t12, _self11 * _t13, _self11 * _t10, _self10 * _t10, -_self11 * _t11 - _self10 * _t9, src[srcOffset + 7], _t2 * _t9 + _self20 * _t12 - _self21 * _t10, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10), _self22 * _t14 + (-_self21 * _t11 - _self20 * _t9), src[srcOffset + 11]);
        return dest;
    }

    private static void reflect_mulAdd_s88ff2b0e_v(float[] dest, int destOffset, float _t9, float _t11, float _t14, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8, float _h9, float _h10, float _h11, float _h12, float _h13, float _h14, float _h15, float _h16, float _h17, float _h18, float _h19) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t9).withLane(1, _t11).withLane(2, _t14);
        FloatVector.broadcast(SIMD_SPECIES, _h0).withLane(2, _h1).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, _h2).withLane(1, _h3).sub(FloatVector.broadcast(SIMD_SPECIES, _h4).withLane(1, _h5)).withLane(2, _h6)).withLane(3, _h7).intoArray(dest, destOffset);
        FloatVector.broadcast(SIMD_SPECIES, _h8).withLane(2, _h9).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, _h10).withLane(1, _h11).sub(FloatVector.broadcast(SIMD_SPECIES, _h12).withLane(1, _h13)).withLane(2, _h14)).withLane(3, _h15).intoArray(dest, destOffset + 4);
        FloatVector.zero(SIMD_SPECIES).withLane(0, _h16).withLane(1, _h17).withLane(2, _h18).withLane(3, _h19).intoArray(dest, destOffset + 8);
    }

    public static java.lang.foreign.MemorySegment reflect(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return reflect_fma_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
            return reflect_fma_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return reflect_mulAdd_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
        return reflect_mulAdd_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
    }

    public static java.lang.foreign.MemorySegment reflect_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return reflect_fma_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
        return reflect_fma_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
    }

    public static java.lang.foreign.MemorySegment reflect_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ) {
        long _srcBase = src.address() + srcOffset;
        float _self22 = UnsafeOpsHolder.U.getFloat(_srcBase + 40L);
        float _self02 = UnsafeOpsHolder.U.getFloat(_srcBase + 8L);
        float _self00 = UnsafeOpsHolder.U.getFloat(_srcBase);
        float _self01 = UnsafeOpsHolder.U.getFloat(_srcBase + 4L);
        float _self12 = UnsafeOpsHolder.U.getFloat(_srcBase + 24L);
        float _self10 = UnsafeOpsHolder.U.getFloat(_srcBase + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(_srcBase + 20L);
        float _self20 = UnsafeOpsHolder.U.getFloat(_srcBase + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(_srcBase + 36L);
        return reflect_fma_unsafe_s25ee6b14_1(dest, destOffset, _srcBase, _self22, _self02, _self00, _self01, _self12, _self10, _self11, _self20, _self21, -_self22, (normalX + normalX) * normalZ, (normalX + normalX) * normalY, (normalY + normalY) * normalZ, Math.fma(-2.0f, normalX * normalX, 1.0f), Math.fma(-2.0f, normalY * normalY, 1.0f), Math.fma(-2.0f, normalZ * normalZ, 1.0f));
    }

    /** Piece 2 of {@code reflect_fma_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment reflect_fma_unsafe_s25ee6b14_1(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, float _self22, float _self02, float _self00, float _self01, float _self12, float _self10, float _self11, float _self20, float _self21, float _t2, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14) {
        reflect_fma_unsafe_s7ff6761c_v(dest, destOffset, _t9, _t11, _t14, -_self02, _self02, _self00 * _t12, _self01 * _t13, _self01 * _t10, _self00 * _t10, -_self01 * _t11 - _self00 * _t9, UnsafeOpsHolder.U.getFloat(_srcBase + 12L), -_self12, _self12, _self10 * _t12, _self11 * _t13, _self11 * _t10, _self10 * _t10, -_self11 * _t11 - _self10 * _t9, UnsafeOpsHolder.U.getFloat(_srcBase + 28L), _t2 * _t9 + _self20 * _t12 - _self21 * _t10, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10), _self22 * _t14 + (-_self21 * _t11 - _self20 * _t9), UnsafeOpsHolder.U.getFloat(_srcBase + 44L));
        return dest;
    }

    private static void reflect_fma_unsafe_s7ff6761c_v(java.lang.foreign.MemorySegment dest, long destOffset, float _t9, float _t11, float _t14, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8, float _h9, float _h10, float _h11, float _h12, float _h13, float _h14, float _h15, float _h16, float _h17, float _h18, float _h19) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t9).withLane(1, _t11).withLane(2, _t14);
        FloatVector.broadcast(SIMD_SPECIES, _h0).withLane(2, _h1).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, _h2).withLane(1, _h3).sub(FloatVector.broadcast(SIMD_SPECIES, _h4).withLane(1, _h5)).withLane(2, _h6)).withLane(3, _h7).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, _h8).withLane(2, _h9).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, _h10).withLane(1, _h11).sub(FloatVector.broadcast(SIMD_SPECIES, _h12).withLane(1, _h13)).withLane(2, _h14)).withLane(3, _h15).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        FloatVector.zero(SIMD_SPECIES).withLane(0, _h16).withLane(1, _h17).withLane(2, _h18).withLane(3, _h19).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment reflect_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ) {
        float _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 40L);
        float _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 36L);
        return reflect_fma_api_se3792966_1(dest, destOffset, src, srcOffset, _self22, _self02, _self00, _self01, _self12, _self10, _self11, _self20, _self21, -_self22, (normalX + normalX) * normalZ, (normalX + normalX) * normalY, (normalY + normalY) * normalZ, Math.fma(-2.0f, normalX * normalX, 1.0f), Math.fma(-2.0f, normalY * normalY, 1.0f), Math.fma(-2.0f, normalZ * normalZ, 1.0f));
    }

    /** Piece 2 of {@code reflect_fma_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment reflect_fma_api_se3792966_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _self22, float _self02, float _self00, float _self01, float _self12, float _self10, float _self11, float _self20, float _self21, float _t2, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14) {
        reflect_fma_unsafe_s7ff6761c_v(dest, destOffset, _t9, _t11, _t14, -_self02, _self02, _self00 * _t12, _self01 * _t13, _self01 * _t10, _self00 * _t10, -_self01 * _t11 - _self00 * _t9, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L), -_self12, _self12, _self10 * _t12, _self11 * _t13, _self11 * _t10, _self10 * _t10, -_self11 * _t11 - _self10 * _t9, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 28L), _t2 * _t9 + _self20 * _t12 - _self21 * _t10, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10), _self22 * _t14 + (-_self21 * _t11 - _self20 * _t9), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 44L));
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return reflect_mulAdd_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
        return reflect_mulAdd_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
    }

    public static java.lang.foreign.MemorySegment reflect_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ) {
        long _srcBase = src.address() + srcOffset;
        float _self22 = UnsafeOpsHolder.U.getFloat(_srcBase + 40L);
        float _self02 = UnsafeOpsHolder.U.getFloat(_srcBase + 8L);
        float _self00 = UnsafeOpsHolder.U.getFloat(_srcBase);
        float _self01 = UnsafeOpsHolder.U.getFloat(_srcBase + 4L);
        float _self12 = UnsafeOpsHolder.U.getFloat(_srcBase + 24L);
        float _self10 = UnsafeOpsHolder.U.getFloat(_srcBase + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(_srcBase + 20L);
        float _self20 = UnsafeOpsHolder.U.getFloat(_srcBase + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(_srcBase + 36L);
        return reflect_mulAdd_unsafe_s5975291_1(dest, destOffset, _srcBase, _self22, _self02, _self00, _self01, _self12, _self10, _self11, _self20, _self21, -_self22, (normalX + normalX) * normalZ, (normalX + normalX) * normalY, (normalY + normalY) * normalZ, Math.fma(-2.0f, normalX * normalX, 1.0f), Math.fma(-2.0f, normalY * normalY, 1.0f), Math.fma(-2.0f, normalZ * normalZ, 1.0f));
    }

    /** Piece 2 of {@code reflect_mulAdd_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment reflect_mulAdd_unsafe_s5975291_1(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, float _self22, float _self02, float _self00, float _self01, float _self12, float _self10, float _self11, float _self20, float _self21, float _t2, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14) {
        reflect_mulAdd_unsafe_s9d401a7d_v(dest, destOffset, _t9, _t11, _t14, -_self02, _self02, _self00 * _t12, _self01 * _t13, _self01 * _t10, _self00 * _t10, -_self01 * _t11 - _self00 * _t9, UnsafeOpsHolder.U.getFloat(_srcBase + 12L), -_self12, _self12, _self10 * _t12, _self11 * _t13, _self11 * _t10, _self10 * _t10, -_self11 * _t11 - _self10 * _t9, UnsafeOpsHolder.U.getFloat(_srcBase + 28L), _t2 * _t9 + _self20 * _t12 - _self21 * _t10, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10), _self22 * _t14 + (-_self21 * _t11 - _self20 * _t9), UnsafeOpsHolder.U.getFloat(_srcBase + 44L));
        return dest;
    }

    private static void reflect_mulAdd_unsafe_s9d401a7d_v(java.lang.foreign.MemorySegment dest, long destOffset, float _t9, float _t11, float _t14, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8, float _h9, float _h10, float _h11, float _h12, float _h13, float _h14, float _h15, float _h16, float _h17, float _h18, float _h19) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t9).withLane(1, _t11).withLane(2, _t14);
        FloatVector.broadcast(SIMD_SPECIES, _h0).withLane(2, _h1).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, _h2).withLane(1, _h3).sub(FloatVector.broadcast(SIMD_SPECIES, _h4).withLane(1, _h5)).withLane(2, _h6)).withLane(3, _h7).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, _h8).withLane(2, _h9).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, _h10).withLane(1, _h11).sub(FloatVector.broadcast(SIMD_SPECIES, _h12).withLane(1, _h13)).withLane(2, _h14)).withLane(3, _h15).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        FloatVector.zero(SIMD_SPECIES).withLane(0, _h16).withLane(1, _h17).withLane(2, _h18).withLane(3, _h19).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment reflect_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float normalX, float normalY, float normalZ) {
        float _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 40L);
        float _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 36L);
        return reflect_mulAdd_api_sa471c75_1(dest, destOffset, src, srcOffset, _self22, _self02, _self00, _self01, _self12, _self10, _self11, _self20, _self21, -_self22, (normalX + normalX) * normalZ, (normalX + normalX) * normalY, (normalY + normalY) * normalZ, Math.fma(-2.0f, normalX * normalX, 1.0f), Math.fma(-2.0f, normalY * normalY, 1.0f), Math.fma(-2.0f, normalZ * normalZ, 1.0f));
    }

    /** Piece 2 of {@code reflect_mulAdd_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment reflect_mulAdd_api_sa471c75_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _self22, float _self02, float _self00, float _self01, float _self12, float _self10, float _self11, float _self20, float _self21, float _t2, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14) {
        reflect_mulAdd_unsafe_s9d401a7d_v(dest, destOffset, _t9, _t11, _t14, -_self02, _self02, _self00 * _t12, _self01 * _t13, _self01 * _t10, _self00 * _t10, -_self01 * _t11 - _self00 * _t9, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L), -_self12, _self12, _self10 * _t12, _self11 * _t13, _self11 * _t10, _self10 * _t10, -_self11 * _t11 - _self10 * _t9, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 28L), _t2 * _t9 + _self20 * _t12 - _self21 * _t10, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10), _self22 * _t14 + (-_self21 * _t11 - _self20 * _t9), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 44L));
        return dest;
    }

    public static float[] reflect(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        if (SimdSupport.USE_FMA) return reflect_fma(dest, destOffset, src, srcOffset, normal, normalOffset);
        return reflect_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    public static float[] reflect_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        float _self22 = src[srcOffset + 10];
        float _normalx = normal[normalOffset];
        float _normalz = normal[normalOffset + 2];
        float _normaly = normal[normalOffset + 1];
        reflect_fma_s4659ae95_v(dest, destOffset, src, srcOffset, src[srcOffset + 2], src[srcOffset], src[srcOffset + 1], src[srcOffset + 6], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 8], src[srcOffset + 9], _self22, -_self22, (_normalx + _normalx) * _normalz, (_normalx + _normalx) * _normaly, (_normaly + _normaly) * _normalz, Math.fma(-2.0f, _normalx * _normalx, 1.0f), Math.fma(-2.0f, _normaly * _normaly, 1.0f), Math.fma(-2.0f, _normalz * _normalz, 1.0f));
        return dest;
    }

    public static float[] reflect_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] normal, int normalOffset) {
        float _self22 = src[srcOffset + 10];
        float _normalx = normal[normalOffset];
        float _normalz = normal[normalOffset + 2];
        float _normaly = normal[normalOffset + 1];
        return reflect_mulAdd_s97e34512_1(dest, destOffset, src, srcOffset, _self22, src[srcOffset + 2], src[srcOffset], src[srcOffset + 1], src[srcOffset + 6], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 8], src[srcOffset + 9], -_self22, (_normalx + _normalx) * _normalz, (_normalx + _normalx) * _normaly, (_normaly + _normaly) * _normalz, Math.fma(-2.0f, _normalx * _normalx, 1.0f), Math.fma(-2.0f, _normaly * _normaly, 1.0f), Math.fma(-2.0f, _normalz * _normalz, 1.0f));
    }

    /** Piece 2 of {@code reflect_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float[] reflect_mulAdd_s97e34512_1(float[] dest, int destOffset, float[] src, int srcOffset, float _self22, float _self02, float _self00, float _self01, float _self12, float _self10, float _self11, float _self20, float _self21, float _t2, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14) {
        reflect_mulAdd_s88ff2b0e_v(dest, destOffset, _t9, _t11, _t14, -_self02, _self02, _self00 * _t12, _self01 * _t13, _self01 * _t10, _self00 * _t10, -_self01 * _t11 - _self00 * _t9, src[srcOffset + 3], -_self12, _self12, _self10 * _t12, _self11 * _t13, _self11 * _t10, _self10 * _t10, -_self11 * _t11 - _self10 * _t9, src[srcOffset + 7], _t2 * _t9 + _self20 * _t12 - _self21 * _t10, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10), _self22 * _t14 + (-_self21 * _t11 - _self20 * _t9), src[srcOffset + 11]);
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && normal.isNative()) return reflect_fma_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset);
            return reflect_fma_api(dest, destOffset, src, srcOffset, normal, normalOffset);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && normal.isNative()) return reflect_mulAdd_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset);
        return reflect_mulAdd_api(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    public static java.lang.foreign.MemorySegment reflect_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && normal.isNative()) return reflect_fma_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset);
        return reflect_fma_api(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    public static java.lang.foreign.MemorySegment reflect_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        long _srcBase = src.address() + srcOffset;
        long _normalBase = normal.address() + normalOffset;
        float _self22 = UnsafeOpsHolder.U.getFloat(_srcBase + 40L);
        float _normalx = UnsafeOpsHolder.U.getFloat(_normalBase);
        float _normalz = UnsafeOpsHolder.U.getFloat(_normalBase + 8L);
        float _normaly = UnsafeOpsHolder.U.getFloat(_normalBase + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(_srcBase + 8L);
        float _self00 = UnsafeOpsHolder.U.getFloat(_srcBase);
        float _self01 = UnsafeOpsHolder.U.getFloat(_srcBase + 4L);
        float _self12 = UnsafeOpsHolder.U.getFloat(_srcBase + 24L);
        float _self10 = UnsafeOpsHolder.U.getFloat(_srcBase + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(_srcBase + 20L);
        float _self20 = UnsafeOpsHolder.U.getFloat(_srcBase + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(_srcBase + 36L);
        return reflect_fma_unsafe_s1153b997_1(dest, destOffset, _srcBase, _self22, _self02, _self00, _self01, _self12, _self10, _self11, _self20, _self21, -_self22, (_normalx + _normalx) * _normalz, (_normalx + _normalx) * _normaly, (_normaly + _normaly) * _normalz, Math.fma(-2.0f, _normalx * _normalx, 1.0f), Math.fma(-2.0f, _normaly * _normaly, 1.0f), Math.fma(-2.0f, _normalz * _normalz, 1.0f));
    }

    /** Piece 2 of {@code reflect_fma_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment reflect_fma_unsafe_s1153b997_1(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, float _self22, float _self02, float _self00, float _self01, float _self12, float _self10, float _self11, float _self20, float _self21, float _t2, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14) {
        reflect_fma_unsafe_s7ff6761c_v(dest, destOffset, _t9, _t11, _t14, -_self02, _self02, _self00 * _t12, _self01 * _t13, _self01 * _t10, _self00 * _t10, -_self01 * _t11 - _self00 * _t9, UnsafeOpsHolder.U.getFloat(_srcBase + 12L), -_self12, _self12, _self10 * _t12, _self11 * _t13, _self11 * _t10, _self10 * _t10, -_self11 * _t11 - _self10 * _t9, UnsafeOpsHolder.U.getFloat(_srcBase + 28L), _t2 * _t9 + _self20 * _t12 - _self21 * _t10, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10), _self22 * _t14 + (-_self21 * _t11 - _self20 * _t9), UnsafeOpsHolder.U.getFloat(_srcBase + 44L));
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        float _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 40L);
        float _normalx = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset);
        float _normalz = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 8L);
        float _normaly = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 4L);
        float _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 36L);
        return reflect_fma_api_s29bdfeb9_1(dest, destOffset, src, srcOffset, _self22, _self02, _self00, _self01, _self12, _self10, _self11, _self20, _self21, -_self22, (_normalx + _normalx) * _normalz, (_normalx + _normalx) * _normaly, (_normaly + _normaly) * _normalz, Math.fma(-2.0f, _normalx * _normalx, 1.0f), Math.fma(-2.0f, _normaly * _normaly, 1.0f), Math.fma(-2.0f, _normalz * _normalz, 1.0f));
    }

    /** Piece 2 of {@code reflect_fma_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment reflect_fma_api_s29bdfeb9_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _self22, float _self02, float _self00, float _self01, float _self12, float _self10, float _self11, float _self20, float _self21, float _t2, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14) {
        reflect_fma_unsafe_s7ff6761c_v(dest, destOffset, _t9, _t11, _t14, -_self02, _self02, _self00 * _t12, _self01 * _t13, _self01 * _t10, _self00 * _t10, -_self01 * _t11 - _self00 * _t9, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L), -_self12, _self12, _self10 * _t12, _self11 * _t13, _self11 * _t10, _self10 * _t10, -_self11 * _t11 - _self10 * _t9, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 28L), _t2 * _t9 + _self20 * _t12 - _self21 * _t10, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10), _self22 * _t14 + (-_self21 * _t11 - _self20 * _t9), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 44L));
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && normal.isNative()) return reflect_mulAdd_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset);
        return reflect_mulAdd_api(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    public static java.lang.foreign.MemorySegment reflect_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        long _srcBase = src.address() + srcOffset;
        long _normalBase = normal.address() + normalOffset;
        float _self22 = UnsafeOpsHolder.U.getFloat(_srcBase + 40L);
        float _normalx = UnsafeOpsHolder.U.getFloat(_normalBase);
        float _normalz = UnsafeOpsHolder.U.getFloat(_normalBase + 8L);
        float _normaly = UnsafeOpsHolder.U.getFloat(_normalBase + 4L);
        float _self02 = UnsafeOpsHolder.U.getFloat(_srcBase + 8L);
        float _self00 = UnsafeOpsHolder.U.getFloat(_srcBase);
        float _self01 = UnsafeOpsHolder.U.getFloat(_srcBase + 4L);
        float _self12 = UnsafeOpsHolder.U.getFloat(_srcBase + 24L);
        float _self10 = UnsafeOpsHolder.U.getFloat(_srcBase + 16L);
        float _self11 = UnsafeOpsHolder.U.getFloat(_srcBase + 20L);
        float _self20 = UnsafeOpsHolder.U.getFloat(_srcBase + 32L);
        float _self21 = UnsafeOpsHolder.U.getFloat(_srcBase + 36L);
        return reflect_mulAdd_unsafe_s9a6dc71a_1(dest, destOffset, _srcBase, _self22, _self02, _self00, _self01, _self12, _self10, _self11, _self20, _self21, -_self22, (_normalx + _normalx) * _normalz, (_normalx + _normalx) * _normaly, (_normaly + _normaly) * _normalz, Math.fma(-2.0f, _normalx * _normalx, 1.0f), Math.fma(-2.0f, _normaly * _normaly, 1.0f), Math.fma(-2.0f, _normalz * _normalz, 1.0f));
    }

    /** Piece 2 of {@code reflect_mulAdd_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment reflect_mulAdd_unsafe_s9a6dc71a_1(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, float _self22, float _self02, float _self00, float _self01, float _self12, float _self10, float _self11, float _self20, float _self21, float _t2, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14) {
        reflect_mulAdd_unsafe_s9d401a7d_v(dest, destOffset, _t9, _t11, _t14, -_self02, _self02, _self00 * _t12, _self01 * _t13, _self01 * _t10, _self00 * _t10, -_self01 * _t11 - _self00 * _t9, UnsafeOpsHolder.U.getFloat(_srcBase + 12L), -_self12, _self12, _self10 * _t12, _self11 * _t13, _self11 * _t10, _self10 * _t10, -_self11 * _t11 - _self10 * _t9, UnsafeOpsHolder.U.getFloat(_srcBase + 28L), _t2 * _t9 + _self20 * _t12 - _self21 * _t10, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10), _self22 * _t14 + (-_self21 * _t11 - _self20 * _t9), UnsafeOpsHolder.U.getFloat(_srcBase + 44L));
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        float _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 40L);
        float _normalx = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset);
        float _normalz = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 8L);
        float _normaly = normal.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, normalOffset + 4L);
        float _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 36L);
        return reflect_mulAdd_api_sdf348c62_1(dest, destOffset, src, srcOffset, _self22, _self02, _self00, _self01, _self12, _self10, _self11, _self20, _self21, -_self22, (_normalx + _normalx) * _normalz, (_normalx + _normalx) * _normaly, (_normaly + _normaly) * _normalz, Math.fma(-2.0f, _normalx * _normalx, 1.0f), Math.fma(-2.0f, _normaly * _normaly, 1.0f), Math.fma(-2.0f, _normalz * _normalz, 1.0f));
    }

    /** Piece 2 of {@code reflect_mulAdd_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment reflect_mulAdd_api_sdf348c62_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _self22, float _self02, float _self00, float _self01, float _self12, float _self10, float _self11, float _self20, float _self21, float _t2, float _t9, float _t10, float _t11, float _t12, float _t13, float _t14) {
        reflect_mulAdd_unsafe_s9d401a7d_v(dest, destOffset, _t9, _t11, _t14, -_self02, _self02, _self00 * _t12, _self01 * _t13, _self01 * _t10, _self00 * _t10, -_self01 * _t11 - _self00 * _t9, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L), -_self12, _self12, _self10 * _t12, _self11 * _t13, _self11 * _t10, _self10 * _t10, -_self11 * _t11 - _self10 * _t9, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 28L), _t2 * _t9 + _self20 * _t12 - _self21 * _t10, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10), _self22 * _t14 + (-_self21 * _t11 - _self20 * _t9), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 44L));
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
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, src[_lo0]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).withLane(3, src[_lo0]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 1] * _t40 + src[_lo0 + 2] * _t41)))).intoArray(dest, destOffset + _li * 4);
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
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, src[_lo0]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).withLane(3, src[_lo0]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 1] * _t40 + src[_lo0 + 2] * _t41)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static java.lang.foreign.MemorySegment rotateAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateAround_fma_unsafe(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
            return rotateAround_fma_api(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateAround_mulAdd_unsafe(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return rotateAround_mulAdd_api(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
    }

    public static java.lang.foreign.MemorySegment rotateAround_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateAround_fma_unsafe(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return rotateAround_fma_api(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
    }

    public static java.lang.foreign.MemorySegment rotateAround_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        long _srcBase = src.address() + srcOffset;
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
        float _t40 = Math.fma(_t3, _t26, Math.fma(pivotY, Math.fma(rotX, _t5, _t11), -(pivotX * _t18)));
        float _t41 = Math.fma(-pivotY, _t19, Math.fma(pivotZ, Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t24)));
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t6, _t16)).withLane(1, _t25).withLane(2, _t20);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, Math.fma(_t2, _t5, _t16)).withLane(2, _t26).withLane(3, Math.fma(_t3, _t20, Math.fma(pivotX, Math.fma(rotY, _t6, _t11), -(pivotY * _t25))));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, _t19).withLane(2, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0f)));
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L))).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L)).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1)).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 8L)).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 4L) * _t40 + UnsafeOpsHolder.U.getFloat(_lb1 + 8L) * _t41)))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
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
        float _t40 = Math.fma(_t3, _t26, Math.fma(pivotY, Math.fma(rotX, _t5, _t11), -(pivotX * _t18)));
        float _t41 = Math.fma(-pivotY, _t19, Math.fma(pivotZ, Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t24)));
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t6, _t16)).withLane(1, _t25).withLane(2, _t20);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, Math.fma(_t2, _t5, _t16)).withLane(2, _t26).withLane(3, Math.fma(_t3, _t20, Math.fma(pivotX, Math.fma(rotY, _t6, _t11), -(pivotY * _t25))));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, _t19).withLane(2, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0f)));
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))).add(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L)).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L) * _t40 + src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L) * _t41)))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateAround_mulAdd_unsafe(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return rotateAround_mulAdd_api(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
    }

    public static java.lang.foreign.MemorySegment rotateAround_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
        long _srcBase = src.address() + srcOffset;
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
        float _t40 = Math.fma(_t3, _t26, Math.fma(pivotY, Math.fma(rotX, _t5, _t11), -(pivotX * _t18)));
        float _t41 = Math.fma(-pivotY, _t19, Math.fma(pivotZ, Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t24)));
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t6, _t16)).withLane(1, _t25).withLane(2, _t20);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, Math.fma(_t2, _t5, _t16)).withLane(2, _t26).withLane(3, Math.fma(_t3, _t20, Math.fma(pivotX, Math.fma(rotY, _t6, _t11), -(pivotY * _t25))));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, _t19).withLane(2, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0f)));
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L))).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L)).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1)).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 8L)).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 4L) * _t40 + UnsafeOpsHolder.U.getFloat(_lb1 + 8L) * _t41)))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float rotX, float rotY, float rotZ, float rotW, float pivotX, float pivotY, float pivotZ) {
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
        float _t40 = Math.fma(_t3, _t26, Math.fma(pivotY, Math.fma(rotX, _t5, _t11), -(pivotX * _t18)));
        float _t41 = Math.fma(-pivotY, _t19, Math.fma(pivotZ, Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t24)));
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t6, _t16)).withLane(1, _t25).withLane(2, _t20);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, Math.fma(_t2, _t5, _t16)).withLane(2, _t26).withLane(3, Math.fma(_t3, _t20, Math.fma(pivotX, Math.fma(rotY, _t6, _t11), -(pivotY * _t25))));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, _t19).withLane(2, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0f)));
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))).add(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L)).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L) * _t40 + src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L) * _t41)))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
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
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, src[_lo0]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).withLane(3, src[_lo0]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 1] * _t40 + src[_lo0 + 2] * _t41)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] rotateAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] rot, int rotOffset, float[] pivot, int pivotOffset) {
        float _pivotx = pivot[pivotOffset];
        float _roty = rot[rotOffset + 1];
        float _pivoty = pivot[pivotOffset + 1];
        float _rotx = rot[rotOffset];
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
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, src[_lo0]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).withLane(3, src[_lo0]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 1] * _t40 + src[_lo0 + 2] * _t41)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static java.lang.foreign.MemorySegment rotateAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment rot, long rotOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && rot.isNative() && pivot.isNative()) return rotateAround_fma_unsafe(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
            return rotateAround_fma_api(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && rot.isNative() && pivot.isNative()) return rotateAround_mulAdd_unsafe(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
        return rotateAround_mulAdd_api(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
    }

    public static java.lang.foreign.MemorySegment rotateAround_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment rot, long rotOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && rot.isNative() && pivot.isNative()) return rotateAround_fma_unsafe(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
        return rotateAround_fma_api(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
    }

    public static java.lang.foreign.MemorySegment rotateAround_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment rot, long rotOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        long _srcBase = src.address() + srcOffset;
        long _rotBase = rot.address() + rotOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        float _pivotx = UnsafeOpsHolder.U.getFloat(_pivotBase);
        float _roty = UnsafeOpsHolder.U.getFloat(_rotBase + 4L);
        float _pivoty = UnsafeOpsHolder.U.getFloat(_pivotBase + 4L);
        float _rotx = UnsafeOpsHolder.U.getFloat(_rotBase);
        float _pivotz = UnsafeOpsHolder.U.getFloat(_pivotBase + 8L);
        float _rotz = UnsafeOpsHolder.U.getFloat(_rotBase + 8L);
        float _rotw = UnsafeOpsHolder.U.getFloat(_rotBase + 12L);
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
        float _t40 = Math.fma(_t3, _t26, Math.fma(_pivoty, Math.fma(_rotx, _t5, _t11), -(_pivotx * _t18)));
        float _t41 = Math.fma(-_pivoty, _t19, Math.fma(_pivotz, Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t24)));
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t6, _t16)).withLane(1, _t25).withLane(2, _t20);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, Math.fma(_t2, _t5, _t16)).withLane(2, _t26).withLane(3, Math.fma(_t3, _t20, Math.fma(_pivotx, Math.fma(_roty, _t6, _t11), -(_pivoty * _t25))));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, _t19).withLane(2, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0f)));
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L))).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L)).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1)).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 8L)).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 4L) * _t40 + UnsafeOpsHolder.U.getFloat(_lb1 + 8L) * _t41)))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment rot, long rotOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        float _pivotx = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset);
        float _roty = rot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rotOffset + 4L);
        float _pivoty = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 4L);
        float _rotx = rot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rotOffset);
        float _pivotz = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 8L);
        float _rotz = rot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rotOffset + 8L);
        float _rotw = rot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rotOffset + 12L);
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
        float _t40 = Math.fma(_t3, _t26, Math.fma(_pivoty, Math.fma(_rotx, _t5, _t11), -(_pivotx * _t18)));
        float _t41 = Math.fma(-_pivoty, _t19, Math.fma(_pivotz, Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t24)));
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t6, _t16)).withLane(1, _t25).withLane(2, _t20);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, Math.fma(_t2, _t5, _t16)).withLane(2, _t26).withLane(3, Math.fma(_t3, _t20, Math.fma(_pivotx, Math.fma(_roty, _t6, _t11), -(_pivoty * _t25))));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, _t19).withLane(2, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0f)));
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))).add(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L)).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L) * _t40 + src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L) * _t41)))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment rot, long rotOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && rot.isNative() && pivot.isNative()) return rotateAround_mulAdd_unsafe(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
        return rotateAround_mulAdd_api(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
    }

    public static java.lang.foreign.MemorySegment rotateAround_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment rot, long rotOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        long _srcBase = src.address() + srcOffset;
        long _rotBase = rot.address() + rotOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        float _pivotx = UnsafeOpsHolder.U.getFloat(_pivotBase);
        float _roty = UnsafeOpsHolder.U.getFloat(_rotBase + 4L);
        float _pivoty = UnsafeOpsHolder.U.getFloat(_pivotBase + 4L);
        float _rotx = UnsafeOpsHolder.U.getFloat(_rotBase);
        float _pivotz = UnsafeOpsHolder.U.getFloat(_pivotBase + 8L);
        float _rotz = UnsafeOpsHolder.U.getFloat(_rotBase + 8L);
        float _rotw = UnsafeOpsHolder.U.getFloat(_rotBase + 12L);
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
        float _t40 = Math.fma(_t3, _t26, Math.fma(_pivoty, Math.fma(_rotx, _t5, _t11), -(_pivotx * _t18)));
        float _t41 = Math.fma(-_pivoty, _t19, Math.fma(_pivotz, Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t24)));
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t6, _t16)).withLane(1, _t25).withLane(2, _t20);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, Math.fma(_t2, _t5, _t16)).withLane(2, _t26).withLane(3, Math.fma(_t3, _t20, Math.fma(_pivotx, Math.fma(_roty, _t6, _t11), -(_pivoty * _t25))));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, _t19).withLane(2, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0f)));
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L))).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L)).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1)).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 8L)).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 4L) * _t40 + UnsafeOpsHolder.U.getFloat(_lb1 + 8L) * _t41)))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment rot, long rotOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        float _pivotx = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset);
        float _roty = rot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rotOffset + 4L);
        float _pivoty = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 4L);
        float _rotx = rot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rotOffset);
        float _pivotz = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 8L);
        float _rotz = rot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rotOffset + 8L);
        float _rotw = rot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rotOffset + 12L);
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
        float _t40 = Math.fma(_t3, _t26, Math.fma(_pivoty, Math.fma(_rotx, _t5, _t11), -(_pivotx * _t18)));
        float _t41 = Math.fma(-_pivoty, _t19, Math.fma(_pivotz, Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t24)));
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t6, _t16)).withLane(1, _t25).withLane(2, _t20);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, Math.fma(_t2, _t5, _t16)).withLane(2, _t26).withLane(3, Math.fma(_t3, _t20, Math.fma(_pivotx, Math.fma(_roty, _t6, _t11), -(_pivoty * _t25))));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, _t19).withLane(2, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0f)));
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))).add(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L)).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L) * _t40 + src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L) * _t41)))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static float[] rotateAxis(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (SimdSupport.USE_FMA) return rotateAxis_fma(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return rotateAxis_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    public static float[] rotateAxis_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisZ;
        float _t5 = axisX * axisY;
        float _t6 = axisY * axisZ;
        float _t11 = 1.0f - _t1;
        rotateAxis_fma_s1712e579_v(dest, destOffset, src, srcOffset, Math.fma(_t11, _t2, -(axisY * _t0)), Math.fma(axisX, _t0, _t11 * _t6), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(_t11, axisX * axisX, _t1), Math.fma(_t11, _t5, -(axisZ * _t0)), Math.fma(axisY, _t0, _t11 * _t2), Math.fma(axisZ, _t0, _t11 * _t5), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, _t6, -(axisX * _t0)));
        return dest;
    }

    private static void rotateAxis_fma_s1712e579_v(float[] dest, int destOffset, float[] src, int srcOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h3).withLane(1, _h4).withLane(2, _h5);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h6).withLane(1, _h7).withLane(2, _h8);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, src[_lo0]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] rotateAxis_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        rotateAxis_mulAdd_s2eafaf86_v(dest, destOffset, src, srcOffset, axisX, axisY, axisZ, _t0, _t1, axisX * axisZ, axisX * axisY, axisY * axisZ, 1.0f - _t1);
        return dest;
    }

    private static void rotateAxis_mulAdd_s2eafaf86_v(float[] dest, int destOffset, float[] src, int srcOffset, float axisX, float axisY, float axisZ, float _t0, float _t1, float _t2, float _t5, float _t6, float _t11) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _t2, -(axisY * _t0))).withLane(1, Math.fma(axisX, _t0, _t11 * _t6)).withLane(2, Math.fma(_t11, axisZ * axisZ, _t1));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, axisX * axisX, _t1)).withLane(1, Math.fma(_t11, _t5, -(axisZ * _t0))).withLane(2, Math.fma(axisY, _t0, _t11 * _t2));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(axisZ, _t0, _t11 * _t5)).withLane(1, Math.fma(_t11, axisY * axisY, _t1)).withLane(2, Math.fma(_t11, _t6, -(axisX * _t0)));
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static java.lang.foreign.MemorySegment rotateAxis(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateAxis_fma_unsafe(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
            return rotateAxis_fma_api(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateAxis_mulAdd_unsafe(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return rotateAxis_mulAdd_api(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    public static java.lang.foreign.MemorySegment rotateAxis_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateAxis_fma_unsafe(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return rotateAxis_fma_api(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    public static java.lang.foreign.MemorySegment rotateAxis_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        rotateAxis_fma_unsafe_sc42384c4_v(dest, destOffset, axisX, axisY, axisZ, src.address() + srcOffset, _t0, _t1, axisX * axisZ, axisX * axisY, axisY * axisZ, 1.0f - _t1);
        return dest;
    }

    private static void rotateAxis_fma_unsafe_sc42384c4_v(java.lang.foreign.MemorySegment dest, long destOffset, float axisX, float axisY, float axisZ, long _srcBase, float _t0, float _t1, float _t2, float _t5, float _t6, float _t11) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _t2, -(axisY * _t0))).withLane(1, Math.fma(axisX, _t0, _t11 * _t6)).withLane(2, Math.fma(_t11, axisZ * axisZ, _t1));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, axisX * axisX, _t1)).withLane(1, Math.fma(_t11, _t5, -(axisZ * _t0))).withLane(2, Math.fma(axisY, _t0, _t11 * _t2));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(axisZ, _t0, _t11 * _t5)).withLane(1, Math.fma(_t11, axisY * axisY, _t1)).withLane(2, Math.fma(_t11, _t6, -(axisX * _t0)));
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 8L)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1)).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L)).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment rotateAxis_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        rotateAxis_fma_api_s24c6a0dc_v(dest, destOffset, src, srcOffset, axisX, axisY, axisZ, _t0, _t1, axisX * axisZ, axisX * axisY, axisY * axisZ, 1.0f - _t1);
        return dest;
    }

    private static void rotateAxis_fma_api_s24c6a0dc_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float axisX, float axisY, float axisZ, float _t0, float _t1, float _t2, float _t5, float _t6, float _t11) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _t2, -(axisY * _t0))).withLane(1, Math.fma(axisX, _t0, _t11 * _t6)).withLane(2, Math.fma(_t11, axisZ * axisZ, _t1));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, axisX * axisX, _t1)).withLane(1, Math.fma(_t11, _t5, -(axisZ * _t0))).withLane(2, Math.fma(axisY, _t0, _t11 * _t2));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(axisZ, _t0, _t11 * _t5)).withLane(1, Math.fma(_t11, axisY * axisY, _t1)).withLane(2, Math.fma(_t11, _t6, -(axisX * _t0)));
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L)).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment rotateAxis_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateAxis_mulAdd_unsafe(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return rotateAxis_mulAdd_api(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    public static java.lang.foreign.MemorySegment rotateAxis_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisZ;
        float _t5 = axisX * axisY;
        float _t6 = axisY * axisZ;
        float _t11 = 1.0f - _t1;
        rotateAxis_mulAdd_unsafe_sc04a5201_v(dest, destOffset, src.address() + srcOffset, Math.fma(_t11, _t2, -(axisY * _t0)), Math.fma(axisX, _t0, _t11 * _t6), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(_t11, axisX * axisX, _t1), Math.fma(_t11, _t5, -(axisZ * _t0)), Math.fma(axisY, _t0, _t11 * _t2), Math.fma(axisZ, _t0, _t11 * _t5), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, _t6, -(axisX * _t0)));
        return dest;
    }

    private static void rotateAxis_mulAdd_unsafe_sc04a5201_v(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h3).withLane(1, _h4).withLane(2, _h5);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h6).withLane(1, _h7).withLane(2, _h8);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 8L)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1)).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L)).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment rotateAxis_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = axisX * axisZ;
        float _t5 = axisX * axisY;
        float _t6 = axisY * axisZ;
        float _t11 = 1.0f - _t1;
        rotateAxis_mulAdd_api_sa06b8437_v(dest, destOffset, src, srcOffset, Math.fma(_t11, _t2, -(axisY * _t0)), Math.fma(axisX, _t0, _t11 * _t6), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(_t11, axisX * axisX, _t1), Math.fma(_t11, _t5, -(axisZ * _t0)), Math.fma(axisY, _t0, _t11 * _t2), Math.fma(axisZ, _t0, _t11 * _t5), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, _t6, -(axisX * _t0)));
        return dest;
    }

    private static void rotateAxis_mulAdd_api_sa06b8437_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h3).withLane(1, _h4).withLane(2, _h5);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h6).withLane(1, _h7).withLane(2, _h8);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L)).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static float[] rotateAxis(float[] dest, int destOffset, float[] src, int srcOffset, float[] axis, int axisOffset, float angle) {
        if (SimdSupport.USE_FMA) return rotateAxis_fma(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return rotateAxis_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    public static float[] rotateAxis_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] axis, int axisOffset, float angle) {
        float _axisy = axis[axisOffset + 1];
        float _axisx = axis[axisOffset];
        float _axisz = axis[axisOffset + 2];
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        rotateAxis_fma_s470c9c0d_v(dest, destOffset, src, srcOffset, _axisy, _axisx, _axisz, _t0, _t1, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, 1.0f - _t1);
        return dest;
    }

    private static void rotateAxis_fma_s470c9c0d_v(float[] dest, int destOffset, float[] src, int srcOffset, float _axisy, float _axisx, float _axisz, float _t0, float _t1, float _t2, float _t5, float _t6, float _t11) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _t2, -(_axisy * _t0))).withLane(1, Math.fma(_axisx, _t0, _t11 * _t6)).withLane(2, Math.fma(_t11, _axisz * _axisz, _t1));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _axisx * _axisx, _t1)).withLane(1, Math.fma(_t11, _t5, -(_axisz * _t0))).withLane(2, Math.fma(_axisy, _t0, _t11 * _t2));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_axisz, _t0, _t11 * _t5)).withLane(1, Math.fma(_t11, _axisy * _axisy, _t1)).withLane(2, Math.fma(_t11, _t6, -(_axisx * _t0)));
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, src[_lo0]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] rotateAxis_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] axis, int axisOffset, float angle) {
        float _axisy = axis[axisOffset + 1];
        float _axisx = axis[axisOffset];
        float _axisz = axis[axisOffset + 2];
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        rotateAxis_mulAdd_sed598ce0_v(dest, destOffset, src, srcOffset, _axisy, _axisx, _axisz, _t0, _t1, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, 1.0f - _t1);
        return dest;
    }

    private static void rotateAxis_mulAdd_sed598ce0_v(float[] dest, int destOffset, float[] src, int srcOffset, float _axisy, float _axisx, float _axisz, float _t0, float _t1, float _t2, float _t5, float _t6, float _t11) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _t2, -(_axisy * _t0))).withLane(1, Math.fma(_axisx, _t0, _t11 * _t6)).withLane(2, Math.fma(_t11, _axisz * _axisz, _t1));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _axisx * _axisx, _t1)).withLane(1, Math.fma(_t11, _t5, -(_axisz * _t0))).withLane(2, Math.fma(_axisy, _t0, _t11 * _t2));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_axisz, _t0, _t11 * _t5)).withLane(1, Math.fma(_t11, _axisy * _axisy, _t1)).withLane(2, Math.fma(_t11, _t6, -(_axisx * _t0)));
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static java.lang.foreign.MemorySegment rotateAxis(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && axis.isNative()) return rotateAxis_fma_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
            return rotateAxis_fma_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && axis.isNative()) return rotateAxis_mulAdd_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return rotateAxis_mulAdd_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    public static java.lang.foreign.MemorySegment rotateAxis_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && axis.isNative()) return rotateAxis_fma_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return rotateAxis_fma_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    public static java.lang.foreign.MemorySegment rotateAxis_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        long _axisBase = axis.address() + axisOffset;
        float _axisy = UnsafeOpsHolder.U.getFloat(_axisBase + 4L);
        float _axisx = UnsafeOpsHolder.U.getFloat(_axisBase);
        float _axisz = UnsafeOpsHolder.U.getFloat(_axisBase + 8L);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        rotateAxis_fma_unsafe_sfa8b08ed_v(dest, destOffset, src.address() + srcOffset, _axisy, _axisx, _axisz, _t0, _t1, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, 1.0f - _t1);
        return dest;
    }

    private static void rotateAxis_fma_unsafe_sfa8b08ed_v(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, float _axisy, float _axisx, float _axisz, float _t0, float _t1, float _t2, float _t5, float _t6, float _t11) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _t2, -(_axisy * _t0))).withLane(1, Math.fma(_axisx, _t0, _t11 * _t6)).withLane(2, Math.fma(_t11, _axisz * _axisz, _t1));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _axisx * _axisx, _t1)).withLane(1, Math.fma(_t11, _t5, -(_axisz * _t0))).withLane(2, Math.fma(_axisy, _t0, _t11 * _t2));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_axisz, _t0, _t11 * _t5)).withLane(1, Math.fma(_t11, _axisy * _axisy, _t1)).withLane(2, Math.fma(_t11, _t6, -(_axisx * _t0)));
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 8L)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1)).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L)).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment rotateAxis_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        float _axisy = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _axisx = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset);
        float _axisz = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        rotateAxis_fma_api_s188688d5_v(dest, destOffset, src, srcOffset, _axisy, _axisx, _axisz, _t0, _t1, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, 1.0f - _t1);
        return dest;
    }

    private static void rotateAxis_fma_api_s188688d5_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _axisy, float _axisx, float _axisz, float _t0, float _t1, float _t2, float _t5, float _t6, float _t11) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _t2, -(_axisy * _t0))).withLane(1, Math.fma(_axisx, _t0, _t11 * _t6)).withLane(2, Math.fma(_t11, _axisz * _axisz, _t1));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _axisx * _axisx, _t1)).withLane(1, Math.fma(_t11, _t5, -(_axisz * _t0))).withLane(2, Math.fma(_axisy, _t0, _t11 * _t2));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_axisz, _t0, _t11 * _t5)).withLane(1, Math.fma(_t11, _axisy * _axisy, _t1)).withLane(2, Math.fma(_t11, _t6, -(_axisx * _t0)));
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L)).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment rotateAxis_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && axis.isNative()) return rotateAxis_mulAdd_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return rotateAxis_mulAdd_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    public static java.lang.foreign.MemorySegment rotateAxis_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        long _axisBase = axis.address() + axisOffset;
        float _axisx = UnsafeOpsHolder.U.getFloat(_axisBase);
        float _axisz = UnsafeOpsHolder.U.getFloat(_axisBase + 8L);
        float _axisy = UnsafeOpsHolder.U.getFloat(_axisBase + 4L);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = _axisx * _axisz;
        float _t5 = _axisx * _axisy;
        float _t6 = _axisy * _axisz;
        float _t11 = 1.0f - _t1;
        rotateAxis_mulAdd_unsafe_sc04a5201_v(dest, destOffset, src.address() + srcOffset, Math.fma(_t11, _t2, -(_axisy * _t0)), Math.fma(_axisx, _t0, _t11 * _t6), Math.fma(_t11, _axisz * _axisz, _t1), Math.fma(_t11, _axisx * _axisx, _t1), Math.fma(_t11, _t5, -(_axisz * _t0)), Math.fma(_axisy, _t0, _t11 * _t2), Math.fma(_axisz, _t0, _t11 * _t5), Math.fma(_t11, _axisy * _axisy, _t1), Math.fma(_t11, _t6, -(_axisx * _t0)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        float _axisx = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset);
        float _axisz = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 8L);
        float _axisy = axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 4L);
        float _t0 = Math.sin(angle);
        float _t1 = Math.cosFromSin(_t0, angle);
        float _t2 = _axisx * _axisz;
        float _t5 = _axisx * _axisy;
        float _t6 = _axisy * _axisz;
        float _t11 = 1.0f - _t1;
        rotateAxis_mulAdd_api_sa06b8437_v(dest, destOffset, src, srcOffset, Math.fma(_t11, _t2, -(_axisy * _t0)), Math.fma(_axisx, _t0, _t11 * _t6), Math.fma(_t11, _axisz * _axisz, _t1), Math.fma(_t11, _axisx * _axisx, _t1), Math.fma(_t11, _t5, -(_axisz * _t0)), Math.fma(_axisy, _t0, _t11 * _t2), Math.fma(_axisz, _t0, _t11 * _t5), Math.fma(_t11, _axisy * _axisy, _t1), Math.fma(_t11, _t6, -(_axisx * _t0)));
        return dest;
    }

    public static float[] rotateQuat(float[] dest, int destOffset, float[] src, int srcOffset, float qX, float qY, float qZ, float qW) {
        if (SimdSupport.USE_FMA) return rotateQuat_fma(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return rotateQuat_mulAdd(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
    }

    public static float[] rotateQuat_fma(float[] dest, int destOffset, float[] src, int srcOffset, float qX, float qY, float qZ, float qW) {
        float _t0 = -qY;
        float _t2 = -qX;
        float _t3 = qX + qX;
        float _t4 = qY + qY;
        float _t5 = qZ + qZ;
        float _t6 = qW * _t4;
        float _t7 = qW * _t5;
        float _t8 = qW * _t3;
        float _t12 = Math.fma(-qZ, _t5, 1.0f);
        rotateAxis_fma_s1712e579_v(dest, destOffset, src, srcOffset, Math.fma(qZ, _t3, -_t6), Math.fma(qZ, _t4, _t8), Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)), Math.fma(_t0, _t4, _t12), Math.fma(qY, _t3, -_t7), Math.fma(qZ, _t3, _t6), Math.fma(qY, _t3, _t7), Math.fma(_t2, _t3, _t12), Math.fma(qZ, _t4, -_t8));
        return dest;
    }

    public static float[] rotateQuat_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float qX, float qY, float qZ, float qW) {
        float _t0 = -qY;
        float _t2 = -qX;
        float _t3 = qX + qX;
        float _t4 = qY + qY;
        float _t5 = qZ + qZ;
        float _t6 = qW * _t4;
        float _t7 = qW * _t5;
        float _t8 = qW * _t3;
        float _t12 = Math.fma(-qZ, _t5, 1.0f);
        rotateQuat_mulAdd_sb4742b03_v(dest, destOffset, src, srcOffset, Math.fma(qZ, _t3, -_t6), Math.fma(qZ, _t4, _t8), Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)), Math.fma(_t0, _t4, _t12), Math.fma(qY, _t3, -_t7), Math.fma(qZ, _t3, _t6), Math.fma(qY, _t3, _t7), Math.fma(_t2, _t3, _t12), Math.fma(qZ, _t4, -_t8));
        return dest;
    }

    private static void rotateQuat_mulAdd_sb4742b03_v(float[] dest, int destOffset, float[] src, int srcOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h3).withLane(1, _h4).withLane(2, _h5);
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h6).withLane(1, _h7).withLane(2, _h8);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static java.lang.foreign.MemorySegment rotateQuat(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float qX, float qY, float qZ, float qW) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateQuat_fma_unsafe(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
            return rotateQuat_fma_api(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateQuat_mulAdd_unsafe(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return rotateQuat_mulAdd_api(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
    }

    public static java.lang.foreign.MemorySegment rotateQuat_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float qX, float qY, float qZ, float qW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateQuat_fma_unsafe(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return rotateQuat_fma_api(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
    }

    public static java.lang.foreign.MemorySegment rotateQuat_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float qX, float qY, float qZ, float qW) {
        float _t3 = qX + qX;
        float _t4 = qY + qY;
        float _t5 = qZ + qZ;
        rotateQuat_fma_unsafe_s1b86652d_v(dest, destOffset, qY, qZ, src.address() + srcOffset, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, Math.fma(-qZ, _t5, 1.0f));
        return dest;
    }

    private static void rotateQuat_fma_unsafe_s1b86652d_v(java.lang.foreign.MemorySegment dest, long destOffset, float qY, float qZ, long _srcBase, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(qZ, _t3, -_t6)).withLane(1, Math.fma(qZ, _t4, _t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(qY, _t3, -_t7)).withLane(2, Math.fma(qZ, _t3, _t6));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(qY, _t3, _t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(qZ, _t4, -_t8));
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 8L)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1)).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L)).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment rotateQuat_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float qX, float qY, float qZ, float qW) {
        float _t3 = qX + qX;
        float _t4 = qY + qY;
        float _t5 = qZ + qZ;
        rotateQuat_fma_api_s6e6a201d_v(dest, destOffset, src, srcOffset, qY, qZ, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, Math.fma(-qZ, _t5, 1.0f));
        return dest;
    }

    private static void rotateQuat_fma_api_s6e6a201d_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float qY, float qZ, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(qZ, _t3, -_t6)).withLane(1, Math.fma(qZ, _t4, _t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(qY, _t3, -_t7)).withLane(2, Math.fma(qZ, _t3, _t6));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(qY, _t3, _t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(qZ, _t4, -_t8));
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L)).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment rotateQuat_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float qX, float qY, float qZ, float qW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateQuat_mulAdd_unsafe(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return rotateQuat_mulAdd_api(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
    }

    public static java.lang.foreign.MemorySegment rotateQuat_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float qX, float qY, float qZ, float qW) {
        float _t3 = qX + qX;
        float _t4 = qY + qY;
        float _t5 = qZ + qZ;
        rotateQuat_mulAdd_unsafe_s5fe21fb8_v(dest, destOffset, qY, qZ, src.address() + srcOffset, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, Math.fma(-qZ, _t5, 1.0f));
        return dest;
    }

    private static void rotateQuat_mulAdd_unsafe_s5fe21fb8_v(java.lang.foreign.MemorySegment dest, long destOffset, float qY, float qZ, long _srcBase, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(qZ, _t3, -_t6)).withLane(1, Math.fma(qZ, _t4, _t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(qY, _t3, -_t7)).withLane(2, Math.fma(qZ, _t3, _t6));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(qY, _t3, _t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(qZ, _t4, -_t8));
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 8L)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1)).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L)).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment rotateQuat_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float qX, float qY, float qZ, float qW) {
        float _t3 = qX + qX;
        float _t4 = qY + qY;
        float _t5 = qZ + qZ;
        rotateQuat_mulAdd_api_s4b76205e_v(dest, destOffset, src, srcOffset, qY, qZ, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, Math.fma(-qZ, _t5, 1.0f));
        return dest;
    }

    private static void rotateQuat_mulAdd_api_s4b76205e_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float qY, float qZ, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(qZ, _t3, -_t6)).withLane(1, Math.fma(qZ, _t4, _t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(qY, _t3, -_t7)).withLane(2, Math.fma(qZ, _t3, _t6));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(qY, _t3, _t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(qZ, _t4, -_t8));
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L)).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
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
        rotateQuat_fma_s35461ece_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0f));
        return dest;
    }

    private static void rotateQuat_fma_s35461ece_v(float[] dest, int destOffset, float[] src, int srcOffset, float _qz, float _qy, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qz, _t3, -_t6)).withLane(1, Math.fma(_qz, _t4, _t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(_qy, _t3, -_t7)).withLane(2, Math.fma(_qz, _t3, _t6));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qy, _t3, _t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(_qz, _t4, -_t8));
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, src[_lo0]).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static float[] rotateQuat_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] q, int qOffset) {
        float _qz = q[qOffset + 2];
        float _qy = q[qOffset + 1];
        float _qx = q[qOffset];
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
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0]).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static java.lang.foreign.MemorySegment rotateQuat(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && q.isNative()) return rotateQuat_fma_unsafe(dest, destOffset, src, srcOffset, q, qOffset);
            return rotateQuat_fma_api(dest, destOffset, src, srcOffset, q, qOffset);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && q.isNative()) return rotateQuat_mulAdd_unsafe(dest, destOffset, src, srcOffset, q, qOffset);
        return rotateQuat_mulAdd_api(dest, destOffset, src, srcOffset, q, qOffset);
    }

    public static java.lang.foreign.MemorySegment rotateQuat_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && q.isNative()) return rotateQuat_fma_unsafe(dest, destOffset, src, srcOffset, q, qOffset);
        return rotateQuat_fma_api(dest, destOffset, src, srcOffset, q, qOffset);
    }

    public static java.lang.foreign.MemorySegment rotateQuat_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        long _qBase = q.address() + qOffset;
        float _qz = UnsafeOpsHolder.U.getFloat(_qBase + 8L);
        float _qy = UnsafeOpsHolder.U.getFloat(_qBase + 4L);
        float _qx = UnsafeOpsHolder.U.getFloat(_qBase);
        float _qw = UnsafeOpsHolder.U.getFloat(_qBase + 12L);
        float _t3 = _qx + _qx;
        float _t4 = _qy + _qy;
        float _t5 = _qz + _qz;
        rotateQuat_fma_unsafe_sdb511dfc_v(dest, destOffset, src.address() + srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0f));
        return dest;
    }

    private static void rotateQuat_fma_unsafe_sdb511dfc_v(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, float _qz, float _qy, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qz, _t3, -_t6)).withLane(1, Math.fma(_qz, _t4, _t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(_qy, _t3, -_t7)).withLane(2, Math.fma(_qz, _t3, _t6));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qy, _t3, _t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(_qz, _t4, -_t8));
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 8L)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1)).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L)).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment rotateQuat_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        float _qz = q.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, qOffset + 8L);
        float _qy = q.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, qOffset + 4L);
        float _qx = q.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, qOffset);
        float _qw = q.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, qOffset + 12L);
        float _t3 = _qx + _qx;
        float _t4 = _qy + _qy;
        float _t5 = _qz + _qz;
        rotateQuat_fma_api_s6194b4c_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0f));
        return dest;
    }

    private static void rotateQuat_fma_api_s6194b4c_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _qz, float _qy, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qz, _t3, -_t6)).withLane(1, Math.fma(_qz, _t4, _t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(_qy, _t3, -_t7)).withLane(2, Math.fma(_qz, _t3, _t6));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qy, _t3, _t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(_qz, _t4, -_t8));
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).fma(_sv1, FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L)).fma(_sv2, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment rotateQuat_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && q.isNative()) return rotateQuat_mulAdd_unsafe(dest, destOffset, src, srcOffset, q, qOffset);
        return rotateQuat_mulAdd_api(dest, destOffset, src, srcOffset, q, qOffset);
    }

    public static java.lang.foreign.MemorySegment rotateQuat_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        long _qBase = q.address() + qOffset;
        float _qz = UnsafeOpsHolder.U.getFloat(_qBase + 8L);
        float _qy = UnsafeOpsHolder.U.getFloat(_qBase + 4L);
        float _qx = UnsafeOpsHolder.U.getFloat(_qBase);
        float _qw = UnsafeOpsHolder.U.getFloat(_qBase + 12L);
        float _t3 = _qx + _qx;
        float _t4 = _qy + _qy;
        float _t5 = _qz + _qz;
        rotateQuat_mulAdd_unsafe_s2e2b3fdb_v(dest, destOffset, src.address() + srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0f));
        return dest;
    }

    private static void rotateQuat_mulAdd_unsafe_s2e2b3fdb_v(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, float _qz, float _qy, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qz, _t3, -_t6)).withLane(1, Math.fma(_qz, _t4, _t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(_qy, _t3, -_t7)).withLane(2, Math.fma(_qz, _t3, _t6));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qy, _t3, _t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(_qz, _t4, -_t8));
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 8L)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1)).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L)).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment rotateQuat_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        float _qz = q.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, qOffset + 8L);
        float _qy = q.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, qOffset + 4L);
        float _qx = q.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, qOffset);
        float _qw = q.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, qOffset + 12L);
        float _t3 = _qx + _qx;
        float _t4 = _qy + _qy;
        float _t5 = _qz + _qz;
        rotateQuat_mulAdd_api_sfe9a91f5_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0f));
        return dest;
    }

    private static void rotateQuat_mulAdd_api_sfe9a91f5_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _qz, float _qy, float _t0, float _t2, float _t3, float _t4, float _t6, float _t7, float _t8, float _t12) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qz, _t3, -_t6)).withLane(1, Math.fma(_qz, _t4, _t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0f)));
        var _sv1 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(_qy, _t3, -_t7)).withLane(2, Math.fma(_qz, _t3, _t6));
        var _sv2 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qy, _t3, _t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(_qz, _t4, -_t8));
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).mul(_sv1).add(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L)).mul(_sv2).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static float[] rotateYXZ(float[] dest, int destOffset, float[] src, int srcOffset, float angleY, float angleX, float angleZ) {
        if (SimdSupport.USE_FMA) return rotateYXZ_fma(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return rotateYXZ_mulAdd(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
    }

    public static float[] rotateYXZ_fma(float[] dest, int destOffset, float[] src, int srcOffset, float angleY, float angleX, float angleZ) {
        float _t0 = Math.sin(angleX);
        float _t1 = Math.sin(angleY);
        float _t2 = Math.sin(angleZ);
        float _t3 = Math.cosFromSin(_t1, angleY);
        float _t4 = Math.cosFromSin(_t2, angleZ);
        float _t5 = Math.cosFromSin(_t0, angleX);
        float _t8 = _t0 * _t3;
        return rotateYXZ_fma_s8cfe61f4_1(dest, destOffset, src, srcOffset, src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 9], src[srcOffset + 10], _t0, _t1, _t2, _t3, _t4, _t5, _t0 * _t1, _t2 * _t5, _t5 * _t4, _t5 * _t3, Math.fma(_t8, _t4, _t1 * _t2), Math.fma(_t8, _t2, -(_t1 * _t4)));
    }

    /** Piece 2 of {@code rotateYXZ_fma}, split to fit the inline budget; reached only through it. */
    private static float[] rotateYXZ_fma_s8cfe61f4_1(float[] dest, int destOffset, float[] src, int srcOffset, float _self01, float _self02, float _self11, float _self12, float _self21, float _self22, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t10, float _t16, float _t17, float _t19, float _t20) {
        rotateYXZ_fma_s7bb9f37c_v(dest, destOffset, src[srcOffset], Math.fma(_t6, _t2, _t3 * _t4), Math.fma(_t6, _t4, -(_t2 * _t3)), _t1 * _t5, _self01 * _t10, _self01 * _t16, _self02 * _t20, _self02 * _t19, _self02 * _t17 - _self01 * _t0, src[srcOffset + 3], src[srcOffset + 4], _self11 * _t10, _self11 * _t16, _self12 * _t20, _self12 * _t19, _self12 * _t17 - _self11 * _t0, src[srcOffset + 7], src[srcOffset + 8], _self21 * _t10, _self21 * _t16, _self22 * _t20, _self22 * _t19, _self22 * _t17 - _self21 * _t0, src[srcOffset + 11]);
        return dest;
    }

    private static void rotateYXZ_fma_s7bb9f37c_v(float[] dest, int destOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8, float _h9, float _h10, float _h11, float _h12, float _h13, float _h14, float _h15, float _h16, float _h17, float _h18, float _h19, float _h20, float _h21, float _h22, float _h23) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h1).withLane(1, _h2).withLane(2, _h3);
        FloatVector.broadcast(SIMD_SPECIES, _h0).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, _h4).withLane(1, _h5).add(FloatVector.broadcast(SIMD_SPECIES, _h6).withLane(1, _h7)).withLane(2, _h8)).withLane(3, _h9).intoArray(dest, destOffset);
        FloatVector.broadcast(SIMD_SPECIES, _h10).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, _h11).withLane(1, _h12).add(FloatVector.broadcast(SIMD_SPECIES, _h13).withLane(1, _h14)).withLane(2, _h15)).withLane(3, _h16).intoArray(dest, destOffset + 4);
        FloatVector.broadcast(SIMD_SPECIES, _h17).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, _h18).withLane(1, _h19).add(FloatVector.broadcast(SIMD_SPECIES, _h20).withLane(1, _h21)).withLane(2, _h22)).withLane(3, _h23).intoArray(dest, destOffset + 8);
    }

    public static float[] rotateYXZ_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float angleY, float angleX, float angleZ) {
        float _t0 = Math.sin(angleX);
        float _t1 = Math.sin(angleY);
        float _t2 = Math.sin(angleZ);
        float _t3 = Math.cosFromSin(_t1, angleY);
        float _t4 = Math.cosFromSin(_t2, angleZ);
        float _t5 = Math.cosFromSin(_t0, angleX);
        float _t8 = _t0 * _t3;
        return rotateYXZ_mulAdd_s88fc6b67_1(dest, destOffset, src, srcOffset, src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 9], src[srcOffset + 10], _t0, _t1, _t2, _t3, _t4, _t5, _t0 * _t1, _t2 * _t5, _t5 * _t4, _t5 * _t3, Math.fma(_t8, _t4, _t1 * _t2), Math.fma(_t8, _t2, -(_t1 * _t4)));
    }

    /** Piece 2 of {@code rotateYXZ_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float[] rotateYXZ_mulAdd_s88fc6b67_1(float[] dest, int destOffset, float[] src, int srcOffset, float _self01, float _self02, float _self11, float _self12, float _self21, float _self22, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t10, float _t16, float _t17, float _t19, float _t20) {
        rotateYXZ_mulAdd_sb0d4e247_v(dest, destOffset, src[srcOffset], Math.fma(_t6, _t2, _t3 * _t4), Math.fma(_t6, _t4, -(_t2 * _t3)), _t1 * _t5, _self01 * _t10, _self01 * _t16, _self02 * _t20, _self02 * _t19, _self02 * _t17 - _self01 * _t0, src[srcOffset + 3], src[srcOffset + 4], _self11 * _t10, _self11 * _t16, _self12 * _t20, _self12 * _t19, _self12 * _t17 - _self11 * _t0, src[srcOffset + 7], src[srcOffset + 8], _self21 * _t10, _self21 * _t16, _self22 * _t20, _self22 * _t19, _self22 * _t17 - _self21 * _t0, src[srcOffset + 11]);
        return dest;
    }

    private static void rotateYXZ_mulAdd_sb0d4e247_v(float[] dest, int destOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5, float _h6, float _h7, float _h8, float _h9, float _h10, float _h11, float _h12, float _h13, float _h14, float _h15, float _h16, float _h17, float _h18, float _h19, float _h20, float _h21, float _h22, float _h23) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h1).withLane(1, _h2).withLane(2, _h3);
        FloatVector.broadcast(SIMD_SPECIES, _h0).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, _h4).withLane(1, _h5).add(FloatVector.broadcast(SIMD_SPECIES, _h6).withLane(1, _h7)).withLane(2, _h8)).withLane(3, _h9).intoArray(dest, destOffset);
        FloatVector.broadcast(SIMD_SPECIES, _h10).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, _h11).withLane(1, _h12).add(FloatVector.broadcast(SIMD_SPECIES, _h13).withLane(1, _h14)).withLane(2, _h15)).withLane(3, _h16).intoArray(dest, destOffset + 4);
        FloatVector.broadcast(SIMD_SPECIES, _h17).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, _h18).withLane(1, _h19).add(FloatVector.broadcast(SIMD_SPECIES, _h20).withLane(1, _h21)).withLane(2, _h22)).withLane(3, _h23).intoArray(dest, destOffset + 8);
    }

    public static java.lang.foreign.MemorySegment rotateYXZ(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleY, float angleX, float angleZ) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateYXZ_fma_unsafe(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
            return rotateYXZ_fma_api(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateYXZ_mulAdd_unsafe(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return rotateYXZ_mulAdd_api(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
    }

    public static java.lang.foreign.MemorySegment rotateYXZ_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleY, float angleX, float angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateYXZ_fma_unsafe(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return rotateYXZ_fma_api(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
    }

    public static java.lang.foreign.MemorySegment rotateYXZ_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleY, float angleX, float angleZ) {
        float _t0 = Math.sin(angleX);
        float _t1 = Math.sin(angleY);
        float _t2 = Math.sin(angleZ);
        float _t3 = Math.cosFromSin(_t1, angleY);
        float _t4 = Math.cosFromSin(_t2, angleZ);
        float _t5 = Math.cosFromSin(_t0, angleX);
        float _t8 = _t0 * _t3;
        rotateYXZ_fma_unsafe_sdecc7da1_v(dest, destOffset, src.address() + srcOffset, _t0, _t1, _t2, _t3, _t4, _t5, _t0 * _t1, _t2 * _t5, _t5 * _t4, _t5 * _t3, Math.fma(_t8, _t4, _t1 * _t2), Math.fma(_t8, _t2, -(_t1 * _t4)));
        return dest;
    }

    private static void rotateYXZ_fma_unsafe_sdecc7da1_v(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t10, float _t16, float _t17, float _t19, float _t20) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t6, _t2, _t3 * _t4)).withLane(1, Math.fma(_t6, _t4, -(_t2 * _t3))).withLane(2, _t1 * _t5);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L) * _t10).withLane(1, UnsafeOpsHolder.U.getFloat(_lb1 + 4L) * _t16).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 8L) * _t20).withLane(1, UnsafeOpsHolder.U.getFloat(_lb1 + 8L) * _t19)).withLane(2, UnsafeOpsHolder.U.getFloat(_lb1 + 8L) * _t17 - UnsafeOpsHolder.U.getFloat(_lb1 + 4L) * _t0)).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L)).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment rotateYXZ_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleY, float angleX, float angleZ) {
        float _t0 = Math.sin(angleX);
        float _t1 = Math.sin(angleY);
        float _t2 = Math.sin(angleZ);
        float _t3 = Math.cosFromSin(_t1, angleY);
        float _t4 = Math.cosFromSin(_t2, angleZ);
        float _t5 = Math.cosFromSin(_t0, angleX);
        float _t8 = _t0 * _t3;
        rotateYXZ_fma_api_s3f9b7a11_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, _t3, _t4, _t5, _t0 * _t1, _t2 * _t5, _t5 * _t4, _t5 * _t3, Math.fma(_t8, _t4, _t1 * _t2), Math.fma(_t8, _t2, -(_t1 * _t4)));
        return dest;
    }

    private static void rotateYXZ_fma_api_s3f9b7a11_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t10, float _t16, float _t17, float _t19, float _t20) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t6, _t2, _t3 * _t4)).withLane(1, Math.fma(_t6, _t4, -(_t2 * _t3))).withLane(2, _t1 * _t5);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L) * _t10).withLane(1, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L) * _t16).add(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L) * _t20).withLane(1, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L) * _t19)).withLane(2, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L) * _t17 - src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L) * _t0)).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L)).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment rotateYXZ_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleY, float angleX, float angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateYXZ_mulAdd_unsafe(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return rotateYXZ_mulAdd_api(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
    }

    public static java.lang.foreign.MemorySegment rotateYXZ_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleY, float angleX, float angleZ) {
        float _t0 = Math.sin(angleX);
        float _t1 = Math.sin(angleY);
        float _t2 = Math.sin(angleZ);
        float _t3 = Math.cosFromSin(_t1, angleY);
        float _t4 = Math.cosFromSin(_t2, angleZ);
        float _t5 = Math.cosFromSin(_t0, angleX);
        float _t8 = _t0 * _t3;
        rotateYXZ_mulAdd_unsafe_s59003984_v(dest, destOffset, src.address() + srcOffset, _t0, _t1, _t2, _t3, _t4, _t5, _t0 * _t1, _t2 * _t5, _t5 * _t4, _t5 * _t3, Math.fma(_t8, _t4, _t1 * _t2), Math.fma(_t8, _t2, -(_t1 * _t4)));
        return dest;
    }

    private static void rotateYXZ_mulAdd_unsafe_s59003984_v(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t10, float _t16, float _t17, float _t19, float _t20) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t6, _t2, _t3 * _t4)).withLane(1, Math.fma(_t6, _t4, -(_t2 * _t3))).withLane(2, _t1 * _t5);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 4L) * _t10).withLane(1, UnsafeOpsHolder.U.getFloat(_lb1 + 4L) * _t16).add(FloatVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getFloat(_lb1 + 8L) * _t20).withLane(1, UnsafeOpsHolder.U.getFloat(_lb1 + 8L) * _t19)).withLane(2, UnsafeOpsHolder.U.getFloat(_lb1 + 8L) * _t17 - UnsafeOpsHolder.U.getFloat(_lb1 + 4L) * _t0)).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1 + 12L)).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment rotateYXZ_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float angleY, float angleX, float angleZ) {
        float _t0 = Math.sin(angleX);
        float _t1 = Math.sin(angleY);
        float _t2 = Math.sin(angleZ);
        float _t3 = Math.cosFromSin(_t1, angleY);
        float _t4 = Math.cosFromSin(_t2, angleZ);
        float _t5 = Math.cosFromSin(_t0, angleX);
        float _t8 = _t0 * _t3;
        rotateYXZ_mulAdd_api_sf0c93dde_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, _t3, _t4, _t5, _t0 * _t1, _t2 * _t5, _t5 * _t4, _t5 * _t3, Math.fma(_t8, _t4, _t1 * _t2), Math.fma(_t8, _t2, -(_t1 * _t4)));
        return dest;
    }

    private static void rotateYXZ_mulAdd_api_sf0c93dde_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _t0, float _t1, float _t2, float _t3, float _t4, float _t5, float _t6, float _t10, float _t16, float _t17, float _t19, float _t20) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t6, _t2, _t3 * _t4)).withLane(1, Math.fma(_t6, _t4, -(_t2 * _t3))).withLane(2, _t1 * _t5);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0)).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L) * _t10).withLane(1, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L) * _t16).add(FloatVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L) * _t20).withLane(1, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L) * _t19)).withLane(2, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L) * _t17 - src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L) * _t0)).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L)).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static float[] scale(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        if (SimdSupport.USE_FMA) return scale_fma(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return scale_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    public static float[] scale_fma(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.fromArray(SIMD_SPECIES, src, _lo0).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] scale_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.fromArray(SIMD_SPECIES, src, _lo0).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return scale_fma_unsafe(dest, destOffset, src, srcOffset, vX, vY, vZ);
            return scale_fma_api(dest, destOffset, src, srcOffset, vX, vY, vZ);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return scale_mulAdd_unsafe(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return scale_mulAdd_api(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    public static java.lang.foreign.MemorySegment scale_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return scale_fma_unsafe(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return scale_fma_api(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    public static java.lang.foreign.MemorySegment scale_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        long _srcBase = src.address() + srcOffset;
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 3; _li++) {
            FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 16L, java.nio.ByteOrder.nativeOrder()).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_srcBase + _li * 16L + 12L))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.fromMemorySegment(SIMD_SPECIES, src, _lo0, java.nio.ByteOrder.nativeOrder()).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return scale_mulAdd_unsafe(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return scale_mulAdd_api(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    public static java.lang.foreign.MemorySegment scale_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        long _srcBase = src.address() + srcOffset;
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 3; _li++) {
            FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 16L, java.nio.ByteOrder.nativeOrder()).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_srcBase + _li * 16L + 12L))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.fromMemorySegment(SIMD_SPECIES, src, _lo0, java.nio.ByteOrder.nativeOrder()).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static float[] scale(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        if (SimdSupport.USE_FMA) return scale_fma(dest, destOffset, src, srcOffset, v, vOffset);
        return scale_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
    }

    public static float[] scale_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        float _vx = v[vOffset];
        float _vy = v[vOffset + 1];
        float _vz = v[vOffset + 2];
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.fromArray(SIMD_SPECIES, src, _lo0).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] scale_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        float _vx = v[vOffset];
        float _vy = v[vOffset + 1];
        float _vz = v[vOffset + 2];
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.fromArray(SIMD_SPECIES, src, _lo0).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0 + 3])).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && v.isNative()) return scale_fma_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
            return scale_fma_api(dest, destOffset, src, srcOffset, v, vOffset);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && v.isNative()) return scale_mulAdd_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return scale_mulAdd_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    public static java.lang.foreign.MemorySegment scale_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && v.isNative()) return scale_fma_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return scale_fma_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    public static java.lang.foreign.MemorySegment scale_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        float _vx = UnsafeOpsHolder.U.getFloat(_vBase);
        float _vy = UnsafeOpsHolder.U.getFloat(_vBase + 4L);
        float _vz = UnsafeOpsHolder.U.getFloat(_vBase + 8L);
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 3; _li++) {
            FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 16L, java.nio.ByteOrder.nativeOrder()).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_srcBase + _li * 16L + 12L))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        float _vx = v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _vz = v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset + 8L);
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.fromMemorySegment(SIMD_SPECIES, src, _lo0, java.nio.ByteOrder.nativeOrder()).fma(_sv0, FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && v.isNative()) return scale_mulAdd_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return scale_mulAdd_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    public static java.lang.foreign.MemorySegment scale_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        float _vx = UnsafeOpsHolder.U.getFloat(_vBase);
        float _vy = UnsafeOpsHolder.U.getFloat(_vBase + 4L);
        float _vz = UnsafeOpsHolder.U.getFloat(_vBase + 8L);
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 3; _li++) {
            FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 16L, java.nio.ByteOrder.nativeOrder()).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_srcBase + _li * 16L + 12L))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        float _vx = v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _vz = v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset + 8L);
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.fromMemorySegment(SIMD_SPECIES, src, _lo0, java.nio.ByteOrder.nativeOrder()).mul(_sv0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static float[] scale(float[] dest, int destOffset, float[] src, int srcOffset, float s) {
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, s).mul(FloatVector.fromArray(SIMD_SPECIES, src, _lo0)).withLane(3, src[_lo0 + 3]).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return scale_unsafe(dest, destOffset, src, srcOffset, s);
        return scale_api(dest, destOffset, src, srcOffset, s);
    }

    public static java.lang.foreign.MemorySegment scale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s) {
        long _srcBase = src.address() + srcOffset;
        for (int _li = 0; _li < 3; _li++) {
            FloatVector.broadcast(SIMD_SPECIES, s).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 16L, java.nio.ByteOrder.nativeOrder())).withLane(3, UnsafeOpsHolder.U.getFloat(_srcBase + _li * 16L + 12L)).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s) {
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.broadcast(SIMD_SPECIES, s).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, _lo0, java.nio.ByteOrder.nativeOrder())).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 12L)).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static float[] scaleAround(float[] dest, int destOffset, float[] src, int srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        float _t3 = pivotZ * _t0;
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, s).mul(FloatVector.fromArray(SIMD_SPECIES, src, _lo0)).withLane(3, Math.fma(src[_lo0], _t1, Math.fma(src[_lo0 + 1], _t2, Math.fma(src[_lo0 + 2], _t3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return scaleAround_unsafe(dest, destOffset, src, srcOffset, s, pivotX, pivotY, pivotZ);
        return scaleAround_api(dest, destOffset, src, srcOffset, s, pivotX, pivotY, pivotZ);
    }

    public static java.lang.foreign.MemorySegment scaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        long _srcBase = src.address() + srcOffset;
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        float _t3 = pivotZ * _t0;
        scaleAround_unsafe_s334c2479_v(dest, destOffset, src, srcOffset, s, Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase), _t1, Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 4L), _t2, Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 8L), _t3, UnsafeOpsHolder.U.getFloat(_srcBase + 12L)))), Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 16L), _t1, Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 20L), _t2, Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 24L), _t3, UnsafeOpsHolder.U.getFloat(_srcBase + 28L)))), Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 32L), _t1, Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 36L), _t2, Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 40L), _t3, UnsafeOpsHolder.U.getFloat(_srcBase + 44L)))));
        return dest;
    }

    private static void scaleAround_unsafe_s334c2479_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s, float _h0, float _h1, float _h2) {
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, s);
        var _c1 = _sv0.mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder())).withLane(3, _h1);
        var _c2 = _sv0.mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder())).withLane(3, _h2);
        _sv0.mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).withLane(3, _h0).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment scaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float s, float pivotX, float pivotY, float pivotZ) {
        float _t0 = 1.0f - s;
        float _t1 = pivotX * _t0;
        float _t2 = pivotY * _t0;
        float _t3 = pivotZ * _t0;
        scaleAround_unsafe_s334c2479_v(dest, destOffset, src, srcOffset, s, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t1, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L), _t2, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), _t3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L)))), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 16L), _t1, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 20L), _t2, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 24L), _t3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 28L)))), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 32L), _t1, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 36L), _t2, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 40L), _t3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 44L)))));
        return dest;
    }

    public static float[] scaleAround(float[] dest, int destOffset, float[] src, int srcOffset, float[] pivot, int pivotOffset, float s) {
        float _t0 = 1.0f - s;
        float _t1 = pivot[pivotOffset] * _t0;
        float _t2 = pivot[pivotOffset + 1] * _t0;
        float _t3 = pivot[pivotOffset + 2] * _t0;
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.broadcast(SIMD_SPECIES, s).mul(FloatVector.fromArray(SIMD_SPECIES, src, _lo0)).withLane(3, Math.fma(src[_lo0], _t1, Math.fma(src[_lo0 + 1], _t2, Math.fma(src[_lo0 + 2], _t3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && pivot.isNative()) return scaleAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return scaleAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    public static java.lang.foreign.MemorySegment scaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float s) {
        long _srcBase = src.address() + srcOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        float _t0 = 1.0f - s;
        float _t1 = UnsafeOpsHolder.U.getFloat(_pivotBase) * _t0;
        float _t2 = UnsafeOpsHolder.U.getFloat(_pivotBase + 4L) * _t0;
        float _t3 = UnsafeOpsHolder.U.getFloat(_pivotBase + 8L) * _t0;
        scaleAround_unsafe_s334c2479_v(dest, destOffset, src, srcOffset, s, Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase), _t1, Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 4L), _t2, Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 8L), _t3, UnsafeOpsHolder.U.getFloat(_srcBase + 12L)))), Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 16L), _t1, Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 20L), _t2, Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 24L), _t3, UnsafeOpsHolder.U.getFloat(_srcBase + 28L)))), Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 32L), _t1, Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 36L), _t2, Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 40L), _t3, UnsafeOpsHolder.U.getFloat(_srcBase + 44L)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float s) {
        float _t0 = 1.0f - s;
        float _t1 = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset) * _t0;
        float _t2 = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 4L) * _t0;
        float _t3 = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 8L) * _t0;
        scaleAround_unsafe_s334c2479_v(dest, destOffset, src, srcOffset, s, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t1, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L), _t2, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), _t3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L)))), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 16L), _t1, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 20L), _t2, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 24L), _t3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 28L)))), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 32L), _t1, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 36L), _t2, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 40L), _t3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 44L)))));
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
            int _lo0 = srcOffset + _li * 4;
            _sv0.fma(FloatVector.fromArray(SIMD_SPECIES, src, _lo0), FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, Math.fma(src[_lo0], _t3, Math.fma(src[_lo0 + 1], _t4, Math.fma(src[_lo0 + 2], _t5, src[_lo0 + 3]))))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static float[] scaleAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        float _t3 = pivotX * (1.0f - sX);
        float _t4 = pivotY * (1.0f - sY);
        float _t5 = pivotZ * (1.0f - sZ);
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, sX).withLane(1, sY).withLane(2, sZ);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, _lo0)).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, Math.fma(src[_lo0], _t3, Math.fma(src[_lo0 + 1], _t4, Math.fma(src[_lo0 + 2], _t5, src[_lo0 + 3]))))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return scaleAround_fma_unsafe(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
            return scaleAround_fma_api(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return scaleAround_mulAdd_unsafe(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return scaleAround_mulAdd_api(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
    }

    public static java.lang.foreign.MemorySegment scaleAround_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return scaleAround_fma_unsafe(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return scaleAround_fma_api(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
    }

    public static java.lang.foreign.MemorySegment scaleAround_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        long _srcBase = src.address() + srcOffset;
        float _t3 = pivotX * (1.0f - sX);
        float _t4 = pivotY * (1.0f - sY);
        float _t5 = pivotZ * (1.0f - sZ);
        scaleAround_fma_unsafe_sd5c50523_v(dest, destOffset, src, srcOffset, sX, sY, sZ, Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase), _t3, Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 4L), _t4, Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 8L), _t5, UnsafeOpsHolder.U.getFloat(_srcBase + 12L)))), Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 16L), _t3, Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 20L), _t4, Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 24L), _t5, UnsafeOpsHolder.U.getFloat(_srcBase + 28L)))), Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 32L), _t3, Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 36L), _t4, Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 40L), _t5, UnsafeOpsHolder.U.getFloat(_srcBase + 44L)))));
        return dest;
    }

    private static void scaleAround_fma_unsafe_sd5c50523_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sX, float sY, float sZ, float _h0, float _h1, float _h2) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, sX).withLane(1, sY).withLane(2, sZ);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = _sv0.fma(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder()), _sv1.withLane(3, _h1));
        var _c2 = _sv0.fma(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()), _sv1.withLane(3, _h2));
        _sv0.fma(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()), _sv1.withLane(3, _h0)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment scaleAround_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        float _t3 = pivotX * (1.0f - sX);
        float _t4 = pivotY * (1.0f - sY);
        float _t5 = pivotZ * (1.0f - sZ);
        scaleAround_fma_unsafe_sd5c50523_v(dest, destOffset, src, srcOffset, sX, sY, sZ, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t3, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L), _t4, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), _t5, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L)))), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 16L), _t3, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 20L), _t4, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 24L), _t5, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 28L)))), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 32L), _t3, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 36L), _t4, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 40L), _t5, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 44L)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return scaleAround_mulAdd_unsafe(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return scaleAround_mulAdd_api(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
    }

    public static java.lang.foreign.MemorySegment scaleAround_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        long _srcBase = src.address() + srcOffset;
        float _t3 = pivotX * (1.0f - sX);
        float _t4 = pivotY * (1.0f - sY);
        float _t5 = pivotZ * (1.0f - sZ);
        scaleAround_mulAdd_unsafe_s2810a550_v(dest, destOffset, src, srcOffset, sX, sY, sZ, Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase), _t3, Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 4L), _t4, Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 8L), _t5, UnsafeOpsHolder.U.getFloat(_srcBase + 12L)))), Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 16L), _t3, Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 20L), _t4, Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 24L), _t5, UnsafeOpsHolder.U.getFloat(_srcBase + 28L)))), Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 32L), _t3, Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 36L), _t4, Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 40L), _t5, UnsafeOpsHolder.U.getFloat(_srcBase + 44L)))));
        return dest;
    }

    private static void scaleAround_mulAdd_unsafe_s2810a550_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sX, float sY, float sZ, float _h0, float _h1, float _h2) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, sX).withLane(1, sY).withLane(2, sZ);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = _sv0.mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder())).add(_sv1.withLane(3, _h1));
        var _c2 = _sv0.mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder())).add(_sv1.withLane(3, _h2));
        _sv0.mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).add(_sv1.withLane(3, _h0)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment scaleAround_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float sX, float sY, float sZ, float pivotX, float pivotY, float pivotZ) {
        float _t3 = pivotX * (1.0f - sX);
        float _t4 = pivotY * (1.0f - sY);
        float _t5 = pivotZ * (1.0f - sZ);
        scaleAround_mulAdd_unsafe_s2810a550_v(dest, destOffset, src, srcOffset, sX, sY, sZ, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t3, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L), _t4, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), _t5, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L)))), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 16L), _t3, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 20L), _t4, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 24L), _t5, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 28L)))), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 32L), _t3, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 36L), _t4, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 40L), _t5, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 44L)))));
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
        float _t3 = pivot[pivotOffset] * (1.0f - _sx);
        float _t4 = pivot[pivotOffset + 1] * (1.0f - _sy);
        float _t5 = pivot[pivotOffset + 2] * (1.0f - _sz);
        scaleAround_fma_s90c0ca99_v(dest, destOffset, src, srcOffset, _sx, _sy, _sz, Math.fma(src[srcOffset], _t3, Math.fma(src[srcOffset + 1], _t4, Math.fma(src[srcOffset + 2], _t5, src[srcOffset + 3]))), Math.fma(src[srcOffset + 4], _t3, Math.fma(src[srcOffset + 5], _t4, Math.fma(src[srcOffset + 6], _t5, src[srcOffset + 7]))), Math.fma(src[srcOffset + 8], _t3, Math.fma(src[srcOffset + 9], _t4, Math.fma(src[srcOffset + 10], _t5, src[srcOffset + 11]))));
        return dest;
    }

    private static void scaleAround_fma_s90c0ca99_v(float[] dest, int destOffset, float[] src, int srcOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = _sv0.fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4), _sv1.withLane(3, _h4));
        var _c2 = _sv0.fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8), _sv1.withLane(3, _h5));
        _sv0.fma(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset), _sv1.withLane(3, _h3)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
    }

    public static float[] scaleAround_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] s, int sOffset, float[] pivot, int pivotOffset) {
        float _sx = s[sOffset];
        float _sy = s[sOffset + 1];
        float _sz = s[sOffset + 2];
        float _t3 = pivot[pivotOffset] * (1.0f - _sx);
        float _t4 = pivot[pivotOffset + 1] * (1.0f - _sy);
        float _t5 = pivot[pivotOffset + 2] * (1.0f - _sz);
        scaleAround_mulAdd_s92dc23e0_v(dest, destOffset, src, srcOffset, _sx, _sy, _sz, Math.fma(src[srcOffset], _t3, Math.fma(src[srcOffset + 1], _t4, Math.fma(src[srcOffset + 2], _t5, src[srcOffset + 3]))), Math.fma(src[srcOffset + 4], _t3, Math.fma(src[srcOffset + 5], _t4, Math.fma(src[srcOffset + 6], _t5, src[srcOffset + 7]))), Math.fma(src[srcOffset + 8], _t3, Math.fma(src[srcOffset + 9], _t4, Math.fma(src[srcOffset + 10], _t5, src[srcOffset + 11]))));
        return dest;
    }

    private static void scaleAround_mulAdd_s92dc23e0_v(float[] dest, int destOffset, float[] src, int srcOffset, float _h0, float _h1, float _h2, float _h3, float _h4, float _h5) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 4)).add(_sv1.withLane(3, _h4));
        var _c2 = _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset + 8)).add(_sv1.withLane(3, _h5));
        _sv0.mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).add(_sv1.withLane(3, _h3)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
    }

    public static java.lang.foreign.MemorySegment scaleAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && s.isNative() && pivot.isNative()) return scaleAround_fma_unsafe(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
            return scaleAround_fma_api(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && s.isNative() && pivot.isNative()) return scaleAround_mulAdd_unsafe(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return scaleAround_mulAdd_api(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    public static java.lang.foreign.MemorySegment scaleAround_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && s.isNative() && pivot.isNative()) return scaleAround_fma_unsafe(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return scaleAround_fma_api(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    public static java.lang.foreign.MemorySegment scaleAround_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        long _sBase = s.address() + sOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        float _sx = UnsafeOpsHolder.U.getFloat(_sBase);
        float _sy = UnsafeOpsHolder.U.getFloat(_sBase + 4L);
        float _sz = UnsafeOpsHolder.U.getFloat(_sBase + 8L);
        float _pivotx = UnsafeOpsHolder.U.getFloat(_pivotBase);
        float _pivoty = UnsafeOpsHolder.U.getFloat(_pivotBase + 4L);
        float _pivotz = UnsafeOpsHolder.U.getFloat(_pivotBase + 8L);
        scaleAround_fma_unsafe_s5eee419e_v(dest, destOffset, src, srcOffset, src.address() + srcOffset, _sx, _sy, _sz, _pivotx * (1.0f - _sx), _pivoty * (1.0f - _sy), _pivotz * (1.0f - _sz));
        return dest;
    }

    private static void scaleAround_fma_unsafe_s5eee419e_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, long _srcBase, float _sx, float _sy, float _sz, float _t3, float _t4, float _t5) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _sx).withLane(1, _sy).withLane(2, _sz);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            _sv0.fma(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 16L, java.nio.ByteOrder.nativeOrder()), FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, Math.fma(UnsafeOpsHolder.U.getFloat(_lb1), _t3, Math.fma(UnsafeOpsHolder.U.getFloat(_lb1 + 4L), _t4, Math.fma(UnsafeOpsHolder.U.getFloat(_lb1 + 8L), _t5, UnsafeOpsHolder.U.getFloat(_lb1 + 12L)))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment scaleAround_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        float _sx = s.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, sOffset);
        float _sy = s.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, sOffset + 4L);
        float _sz = s.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, sOffset + 8L);
        return scaleAround_fma_api_sa1aa1c14_1(dest, destOffset, src, srcOffset, pivot, pivotOffset, _sx, _sy, _sz);
    }

    /** Piece 2 of {@code scaleAround_fma_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_fma_api_sa1aa1c14_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float _sx, float _sy, float _sz) {
        float _t3 = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset) * (1.0f - _sx);
        float _t4 = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 4L) * (1.0f - _sy);
        float _t5 = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 8L) * (1.0f - _sz);
        scaleAround_fma_unsafe_sd5c50523_v(dest, destOffset, src, srcOffset, _sx, _sy, _sz, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t3, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L), _t4, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), _t5, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L)))), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 16L), _t3, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 20L), _t4, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 24L), _t5, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 28L)))), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 32L), _t3, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 36L), _t4, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 40L), _t5, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 44L)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && s.isNative() && pivot.isNative()) return scaleAround_mulAdd_unsafe(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return scaleAround_mulAdd_api(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    public static java.lang.foreign.MemorySegment scaleAround_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        long _sBase = s.address() + sOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        float _sx = UnsafeOpsHolder.U.getFloat(_sBase);
        float _sy = UnsafeOpsHolder.U.getFloat(_sBase + 4L);
        float _sz = UnsafeOpsHolder.U.getFloat(_sBase + 8L);
        float _pivotx = UnsafeOpsHolder.U.getFloat(_pivotBase);
        float _pivoty = UnsafeOpsHolder.U.getFloat(_pivotBase + 4L);
        float _pivotz = UnsafeOpsHolder.U.getFloat(_pivotBase + 8L);
        scaleAround_mulAdd_unsafe_s766cd391_v(dest, destOffset, src, srcOffset, src.address() + srcOffset, _sx, _sy, _sz, _pivotx * (1.0f - _sx), _pivoty * (1.0f - _sy), _pivotz * (1.0f - _sz));
        return dest;
    }

    private static void scaleAround_mulAdd_unsafe_s766cd391_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, long _srcBase, float _sx, float _sy, float _sz, float _t3, float _t4, float _t5) {
        var _sv0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _sx).withLane(1, _sy).withLane(2, _sz);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            _sv0.mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 16L, java.nio.ByteOrder.nativeOrder())).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, Math.fma(UnsafeOpsHolder.U.getFloat(_lb1), _t3, Math.fma(UnsafeOpsHolder.U.getFloat(_lb1 + 4L), _t4, Math.fma(UnsafeOpsHolder.U.getFloat(_lb1 + 8L), _t5, UnsafeOpsHolder.U.getFloat(_lb1 + 12L)))))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment scaleAround_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        float _sx = s.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, sOffset);
        float _sy = s.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, sOffset + 4L);
        float _sz = s.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, sOffset + 8L);
        return scaleAround_mulAdd_api_sa56c4201_1(dest, destOffset, src, srcOffset, pivot, pivotOffset, _sx, _sy, _sz);
    }

    /** Piece 2 of {@code scaleAround_mulAdd_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_mulAdd_api_sa56c4201_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, float _sx, float _sy, float _sz) {
        float _t3 = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset) * (1.0f - _sx);
        float _t4 = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 4L) * (1.0f - _sy);
        float _t5 = pivot.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pivotOffset + 8L) * (1.0f - _sz);
        scaleAround_mulAdd_unsafe_s2810a550_v(dest, destOffset, src, srcOffset, _sx, _sy, _sz, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset), _t3, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L), _t4, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), _t5, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L)))), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 16L), _t3, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 20L), _t4, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 24L), _t5, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 28L)))), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 32L), _t3, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 36L), _t4, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 40L), _t5, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 44L)))));
        return dest;
    }

    public static float[] translate(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY, float vZ) {
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.fromArray(SIMD_SPECIES, src, _lo0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0] * vX + (src[_lo0 + 1] * vY + src[_lo0 + 2] * vZ))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment translate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return translate_unsafe(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return translate_api(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    public static java.lang.foreign.MemorySegment translate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        long _srcBase = src.address() + srcOffset;
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 16L;
            FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 16L, java.nio.ByteOrder.nativeOrder()).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, UnsafeOpsHolder.U.getFloat(_lb1) * vX + (UnsafeOpsHolder.U.getFloat(_lb1 + 4L) * vY + UnsafeOpsHolder.U.getFloat(_lb1 + 8L) * vZ))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment translate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY, float vZ) {
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 16L;
            FloatVector.fromMemorySegment(SIMD_SPECIES, src, _lo0, java.nio.ByteOrder.nativeOrder()).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0) * vX + (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 4L) * vY + src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _lo0 + 8L) * vZ))).intoMemorySegment(dest, destOffset + _li * 16L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static float[] translate(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        float _vx = v[vOffset];
        float _vy = v[vOffset + 1];
        float _vz = v[vOffset + 2];
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            FloatVector.fromArray(SIMD_SPECIES, src, _lo0).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, src[_lo0] * _vx + (src[_lo0 + 1] * _vy + src[_lo0 + 2] * _vz))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment translate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && v.isNative()) return translate_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return translate_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    public static java.lang.foreign.MemorySegment translate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        float _vx = UnsafeOpsHolder.U.getFloat(_vBase);
        float _vy = UnsafeOpsHolder.U.getFloat(_vBase + 4L);
        float _vz = UnsafeOpsHolder.U.getFloat(_vBase + 8L);
        translate_unsafe_s533bc9_v(dest, destOffset, src, srcOffset, UnsafeOpsHolder.U.getFloat(_srcBase) * _vx + (UnsafeOpsHolder.U.getFloat(_srcBase + 4L) * _vy + UnsafeOpsHolder.U.getFloat(_srcBase + 8L) * _vz), UnsafeOpsHolder.U.getFloat(_srcBase + 16L) * _vx + (UnsafeOpsHolder.U.getFloat(_srcBase + 20L) * _vy + UnsafeOpsHolder.U.getFloat(_srcBase + 24L) * _vz), UnsafeOpsHolder.U.getFloat(_srcBase + 32L) * _vx + (UnsafeOpsHolder.U.getFloat(_srcBase + 36L) * _vy + UnsafeOpsHolder.U.getFloat(_srcBase + 40L) * _vz));
        return dest;
    }

    private static void translate_unsafe_s533bc9_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _h0, float _h1, float _h2) {
        var _sv0 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        var _c1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder()).add(_sv0.withLane(3, _h1));
        var _c2 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).add(_sv0.withLane(3, _h2));
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).add(_sv0.withLane(3, _h0)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment translate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        float _vx = v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset);
        float _vy = v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset + 4L);
        float _vz = v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset + 8L);
        translate_unsafe_s533bc9_v(dest, destOffset, src, srcOffset, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) * _vx + (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) * _vy + src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) * _vz), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 16L) * _vx + (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 20L) * _vy + src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 24L) * _vz), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 32L) * _vx + (src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 36L) * _vy + src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 40L) * _vz));
        return dest;
    }

    public static float[] transformPosition_vecArr(float[] dest, int destOffset, float[] matrix, int matrixOffset, float[] points, int pointsOffset, int count) {
        float _m00 = matrix[matrixOffset];
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
        float px = points[pointsOffset], py = points[pointsOffset + 1], pz = points[pointsOffset + 2];
        int _i = 0;
        for (; _i < count - 1; _i++) {
            var _v = FloatVector.broadcast(_sp, px).fma(_c0, FloatVector.broadcast(_sp, py).fma(_c1, FloatVector.broadcast(_sp, pz).fma(_c2, _c3)));
            int _pn = pointsOffset + (_i + 1) * 3;
            px = points[_pn]; py = points[_pn + 1]; pz = points[_pn + 2];
            _v.intoArray(dest, destOffset + _i * 3);
        }
        int _do = destOffset + _i * 3;
        dest[_do] = Math.fma(_m00, px, Math.fma(_m01, py, Math.fma(_m02, pz, _m03)));
        dest[_do + 1] = Math.fma(_m10, px, Math.fma(_m11, py, Math.fma(_m12, pz, _m13)));
        dest[_do + 2] = Math.fma(_m20, px, Math.fma(_m21, py, Math.fma(_m22, pz, _m23)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformPosition_vecUnsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment matrix, long matrixOffset, java.lang.foreign.MemorySegment points, long pointsOffset, int count) {
        long _destBase = dest.address() + destOffset;
        long _matrixBase = matrix.address() + matrixOffset;
        long _pointsBase = points.address() + pointsOffset;
        float _m00 = UnsafeOpsHolder.U.getFloat(_matrixBase);
        float _m01 = UnsafeOpsHolder.U.getFloat(_matrixBase + 4L);
        float _m02 = UnsafeOpsHolder.U.getFloat(_matrixBase + 8L);
        float _m03 = UnsafeOpsHolder.U.getFloat(_matrixBase + 12L);
        float _m10 = UnsafeOpsHolder.U.getFloat(_matrixBase + 16L);
        float _m11 = UnsafeOpsHolder.U.getFloat(_matrixBase + 20L);
        float _m12 = UnsafeOpsHolder.U.getFloat(_matrixBase + 24L);
        float _m13 = UnsafeOpsHolder.U.getFloat(_matrixBase + 28L);
        float _m20 = UnsafeOpsHolder.U.getFloat(_matrixBase + 32L);
        float _m21 = UnsafeOpsHolder.U.getFloat(_matrixBase + 36L);
        float _m22 = UnsafeOpsHolder.U.getFloat(_matrixBase + 40L);
        float _m23 = UnsafeOpsHolder.U.getFloat(_matrixBase + 44L);
        var _sp = FloatVector.SPECIES_128;
        var _c0 = FloatVector.zero(_sp).withLane(0, _m00).withLane(1, _m10).withLane(2, _m20);
        var _c1 = FloatVector.zero(_sp).withLane(0, _m01).withLane(1, _m11).withLane(2, _m21);
        var _c2 = FloatVector.zero(_sp).withLane(0, _m02).withLane(1, _m12).withLane(2, _m22);
        var _c3 = FloatVector.zero(_sp).withLane(0, _m03).withLane(1, _m13).withLane(2, _m23);
        float px = UnsafeOpsHolder.U.getFloat(_pointsBase), py = UnsafeOpsHolder.U.getFloat(_pointsBase + 4L), pz = UnsafeOpsHolder.U.getFloat(_pointsBase + 8L);
        int _i = 0;
        for (; _i < count - 1; _i++) {
            var _v = FloatVector.broadcast(_sp, px).fma(_c0, FloatVector.broadcast(_sp, py).fma(_c1, FloatVector.broadcast(_sp, pz).fma(_c2, _c3)));
            long _pn = _pointsBase + (_i + 1) * 12L;
            px = UnsafeOpsHolder.U.getFloat(_pn); py = UnsafeOpsHolder.U.getFloat(_pn + 4L); pz = UnsafeOpsHolder.U.getFloat(_pn + 8L);
            _v.intoMemorySegment(dest, destOffset + _i * 12L, java.nio.ByteOrder.nativeOrder());
        }
        long _db = _destBase + _i * 12L;
        UnsafeOpsHolder.U.putFloat(_db, Math.fma(_m00, px, Math.fma(_m01, py, Math.fma(_m02, pz, _m03))));
        UnsafeOpsHolder.U.putFloat(_db + 4L, Math.fma(_m10, px, Math.fma(_m11, py, Math.fma(_m12, pz, _m13))));
        UnsafeOpsHolder.U.putFloat(_db + 8L, Math.fma(_m20, px, Math.fma(_m21, py, Math.fma(_m22, pz, _m23))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformPosition_vecApi(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment matrix, long matrixOffset, java.lang.foreign.MemorySegment points, long pointsOffset, int count) {
        float _m00 = matrix.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matrixOffset);
        float _m01 = matrix.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matrixOffset + 4L);
        float _m02 = matrix.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matrixOffset + 8L);
        float _m03 = matrix.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matrixOffset + 12L);
        float _m10 = matrix.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matrixOffset + 16L);
        float _m11 = matrix.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matrixOffset + 20L);
        float _m12 = matrix.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matrixOffset + 24L);
        float _m13 = matrix.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matrixOffset + 28L);
        float _m20 = matrix.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matrixOffset + 32L);
        float _m21 = matrix.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matrixOffset + 36L);
        float _m22 = matrix.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matrixOffset + 40L);
        float _m23 = matrix.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matrixOffset + 44L);
        var _sp = FloatVector.SPECIES_128;
        var _c0 = FloatVector.zero(_sp).withLane(0, _m00).withLane(1, _m10).withLane(2, _m20);
        var _c1 = FloatVector.zero(_sp).withLane(0, _m01).withLane(1, _m11).withLane(2, _m21);
        var _c2 = FloatVector.zero(_sp).withLane(0, _m02).withLane(1, _m12).withLane(2, _m22);
        var _c3 = FloatVector.zero(_sp).withLane(0, _m03).withLane(1, _m13).withLane(2, _m23);
        float px = points.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pointsOffset), py = points.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pointsOffset + 4L), pz = points.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pointsOffset + 8L);
        int _i = 0;
        for (; _i < count - 1; _i++) {
            var _v = FloatVector.broadcast(_sp, px).fma(_c0, FloatVector.broadcast(_sp, py).fma(_c1, FloatVector.broadcast(_sp, pz).fma(_c2, _c3)));
            long _pn = pointsOffset + (_i + 1) * 12L;
            px = points.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _pn); py = points.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _pn + 4L); pz = points.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _pn + 8L);
            _v.intoMemorySegment(dest, destOffset + _i * 12L, java.nio.ByteOrder.nativeOrder());
        }
        long _do = destOffset + _i * 12L;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _do, Math.fma(_m00, px, Math.fma(_m01, py, Math.fma(_m02, pz, _m03))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _do + 4L, Math.fma(_m10, px, Math.fma(_m11, py, Math.fma(_m12, pz, _m13))));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _do + 8L, Math.fma(_m20, px, Math.fma(_m21, py, Math.fma(_m22, pz, _m23))));
        return dest;
    }

    public static float[] transformDirection_vecArr(float[] dest, int destOffset, float[] matrix, int matrixOffset, float[] points, int pointsOffset, int count) {
        transformDirection_vecArr_v1af02634(dest, destOffset, points, pointsOffset, count, matrix[matrixOffset], matrix[matrixOffset + 1], matrix[matrixOffset + 2], matrix[matrixOffset + 4], matrix[matrixOffset + 5], matrix[matrixOffset + 6], matrix[matrixOffset + 8], matrix[matrixOffset + 9], matrix[matrixOffset + 10]);
        return dest;
    }

    private static void transformDirection_vecArr_v1af02634(float[] dest, int destOffset, float[] points, int pointsOffset, int count, float _m00, float _m01, float _m02, float _m10, float _m11, float _m12, float _m20, float _m21, float _m22) {
        var _sp = FloatVector.SPECIES_128;
        var _c0 = FloatVector.zero(_sp).withLane(0, _m00).withLane(1, _m10).withLane(2, _m20);
        var _c1 = FloatVector.zero(_sp).withLane(0, _m01).withLane(1, _m11).withLane(2, _m21);
        var _c2 = FloatVector.zero(_sp).withLane(0, _m02).withLane(1, _m12).withLane(2, _m22);
        float px = points[pointsOffset], py = points[pointsOffset + 1], pz = points[pointsOffset + 2];
        int _i = 0;
        for (; _i < count - 1; _i++) {
            var _v = FloatVector.broadcast(_sp, pz).fma(_c2, FloatVector.broadcast(_sp, px).fma(_c0, FloatVector.broadcast(_sp, py).mul(_c1)));
            int _pn = pointsOffset + (_i + 1) * 3;
            px = points[_pn]; py = points[_pn + 1]; pz = points[_pn + 2];
            _v.intoArray(dest, destOffset + _i * 3);
        }
        int _do = destOffset + _i * 3;
        dest[_do] = Math.fma(_m02, pz, Math.fma(_m00, px, _m01 * py));
        dest[_do + 1] = Math.fma(_m12, pz, Math.fma(_m10, px, _m11 * py));
        dest[_do + 2] = Math.fma(_m22, pz, Math.fma(_m20, px, _m21 * py));
    }

    public static java.lang.foreign.MemorySegment transformDirection_vecUnsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment matrix, long matrixOffset, java.lang.foreign.MemorySegment points, long pointsOffset, int count) {
        long _destBase = dest.address() + destOffset;
        long _matrixBase = matrix.address() + matrixOffset;
        long _pointsBase = points.address() + pointsOffset;
        float _m00 = UnsafeOpsHolder.U.getFloat(_matrixBase);
        float _m01 = UnsafeOpsHolder.U.getFloat(_matrixBase + 4L);
        float _m02 = UnsafeOpsHolder.U.getFloat(_matrixBase + 8L);
        float _m10 = UnsafeOpsHolder.U.getFloat(_matrixBase + 16L);
        float _m11 = UnsafeOpsHolder.U.getFloat(_matrixBase + 20L);
        float _m12 = UnsafeOpsHolder.U.getFloat(_matrixBase + 24L);
        float _m20 = UnsafeOpsHolder.U.getFloat(_matrixBase + 32L);
        float _m21 = UnsafeOpsHolder.U.getFloat(_matrixBase + 36L);
        float _m22 = UnsafeOpsHolder.U.getFloat(_matrixBase + 40L);
        var _sp = FloatVector.SPECIES_128;
        var _c0 = FloatVector.zero(_sp).withLane(0, _m00).withLane(1, _m10).withLane(2, _m20);
        var _c1 = FloatVector.zero(_sp).withLane(0, _m01).withLane(1, _m11).withLane(2, _m21);
        var _c2 = FloatVector.zero(_sp).withLane(0, _m02).withLane(1, _m12).withLane(2, _m22);
        float px = UnsafeOpsHolder.U.getFloat(_pointsBase), py = UnsafeOpsHolder.U.getFloat(_pointsBase + 4L), pz = UnsafeOpsHolder.U.getFloat(_pointsBase + 8L);
        int _i = 0;
        for (; _i < count - 1; _i++) {
            var _v = FloatVector.broadcast(_sp, pz).fma(_c2, FloatVector.broadcast(_sp, px).fma(_c0, FloatVector.broadcast(_sp, py).mul(_c1)));
            long _pn = _pointsBase + (_i + 1) * 12L;
            px = UnsafeOpsHolder.U.getFloat(_pn); py = UnsafeOpsHolder.U.getFloat(_pn + 4L); pz = UnsafeOpsHolder.U.getFloat(_pn + 8L);
            _v.intoMemorySegment(dest, destOffset + _i * 12L, java.nio.ByteOrder.nativeOrder());
        }
        long _db = _destBase + _i * 12L;
        UnsafeOpsHolder.U.putFloat(_db, Math.fma(_m02, pz, Math.fma(_m00, px, _m01 * py)));
        UnsafeOpsHolder.U.putFloat(_db + 4L, Math.fma(_m12, pz, Math.fma(_m10, px, _m11 * py)));
        UnsafeOpsHolder.U.putFloat(_db + 8L, Math.fma(_m22, pz, Math.fma(_m20, px, _m21 * py)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment transformDirection_vecApi(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment matrix, long matrixOffset, java.lang.foreign.MemorySegment points, long pointsOffset, int count) {
        float _m00 = matrix.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matrixOffset);
        float _m01 = matrix.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matrixOffset + 4L);
        float _m02 = matrix.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matrixOffset + 8L);
        float _m10 = matrix.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matrixOffset + 16L);
        float _m11 = matrix.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matrixOffset + 20L);
        float _m12 = matrix.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matrixOffset + 24L);
        float _m20 = matrix.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matrixOffset + 32L);
        float _m21 = matrix.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matrixOffset + 36L);
        float _m22 = matrix.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, matrixOffset + 40L);
        var _sp = FloatVector.SPECIES_128;
        var _c0 = FloatVector.zero(_sp).withLane(0, _m00).withLane(1, _m10).withLane(2, _m20);
        var _c1 = FloatVector.zero(_sp).withLane(0, _m01).withLane(1, _m11).withLane(2, _m21);
        var _c2 = FloatVector.zero(_sp).withLane(0, _m02).withLane(1, _m12).withLane(2, _m22);
        float px = points.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pointsOffset), py = points.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pointsOffset + 4L), pz = points.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, pointsOffset + 8L);
        int _i = 0;
        for (; _i < count - 1; _i++) {
            var _v = FloatVector.broadcast(_sp, pz).fma(_c2, FloatVector.broadcast(_sp, px).fma(_c0, FloatVector.broadcast(_sp, py).mul(_c1)));
            long _pn = pointsOffset + (_i + 1) * 12L;
            px = points.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _pn); py = points.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _pn + 4L); pz = points.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _pn + 8L);
            _v.intoMemorySegment(dest, destOffset + _i * 12L, java.nio.ByteOrder.nativeOrder());
        }
        long _do = destOffset + _i * 12L;
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _do, Math.fma(_m02, pz, Math.fma(_m00, px, _m01 * py)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _do + 4L, Math.fma(_m12, pz, Math.fma(_m10, px, _m11 * py)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _do + 8L, Math.fma(_m22, pz, Math.fma(_m20, px, _m21 * py)));
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
            float _ax = t1[_t1o], _ay = t1[_t1o + 1], _az = t1[_t1o + 2];
            float _tx = Math.fma(alpha, t2[_t2o] - _ax, _ax);
            float _ty = Math.fma(alpha, t2[_t2o + 1] - _ay, _ay);
            float _tz = Math.fma(alpha, t2[_t2o + 2] - _az, _az);
            float _bx = s1[_s1o], _by = s1[_s1o + 1], _bz = s1[_s1o + 2];
            float _sx = Math.fma(alpha, s2[_s2o] - _bx, _bx);
            float _sy = Math.fma(alpha, s2[_s2o + 1] - _by, _by);
            float _sz = Math.fma(alpha, s2[_s2o + 2] - _bz, _bz);
            float _ux = q1[_q1o], _uy = q1[_q1o + 1], _uz = q1[_q1o + 2], _uw = q1[_q1o + 3];
            float _vx = q2[_q2o], _vy = q2[_q2o + 1], _vz = q2[_q2o + 2], _vw = q2[_q2o + 3];
            float _dot = Math.fma(_uw, _vw, Math.fma(_uz, _vz, Math.fma(_ux, _vx, _uy * _vy)));
            float _wx = _dot < 0.0f ? -_vx : _vx, _wy = _dot < 0.0f ? -_vy : _vy, _wz = _dot < 0.0f ? -_vz : _vz, _ww = _dot < 0.0f ? -_vw : _vw;
            float _rx = Math.fma(alpha, _wx - _ux, _ux);
            float _ry = Math.fma(alpha, _wy - _uy, _uy);
            float _rz = Math.fma(alpha, _wz - _uz, _uz);
            float _rw = Math.fma(alpha, _ww - _uw, _uw);
            float _len2 = (_rx * _rx + _ry * _ry) + (_rz * _rz + _rw * _rw);
            float _ninv = _len2 > 0.0f ? 1.0f / (float) java.lang.Math.sqrt(_len2) : 0.0f;
            float _qx = _rx * _ninv, _qy = _ry * _ninv, _qz = _rz * _ninv, _qw = _rw * _ninv;
            float _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
            float _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
            float _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
            float _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
            float _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
            var _r0 = FloatVector.fromArray(_sp, m, _mo);
            var _r1 = FloatVector.fromArray(_sp, m, _mo + 4);
            var _r2 = FloatVector.fromArray(_sp, m, _mo + 8);
            _r2.fma(FloatVector.broadcast(_sp, _t02), _r1.fma(FloatVector.broadcast(_sp, _t01), _r0.fma(FloatVector.broadcast(_sp, _t00), FloatVector.zero(_sp).withLane(3, _tx)))).intoArray(dest, _do);
            _r2.fma(FloatVector.broadcast(_sp, _t12), _r1.fma(FloatVector.broadcast(_sp, _t11), _r0.fma(FloatVector.broadcast(_sp, _t10), FloatVector.zero(_sp).withLane(3, _ty)))).intoArray(dest, _do + 4);
            _r2.fma(FloatVector.broadcast(_sp, _t22), _r1.fma(FloatVector.broadcast(_sp, _t21), _r0.fma(FloatVector.broadcast(_sp, _t20), FloatVector.zero(_sp).withLane(3, _tz)))).intoArray(dest, _do + 8);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerpComposeTRSMul_fmaUnsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment t1, long t1Offset, java.lang.foreign.MemorySegment t2, long t2Offset, java.lang.foreign.MemorySegment q1, long q1Offset, java.lang.foreign.MemorySegment q2, long q2Offset, java.lang.foreign.MemorySegment s1, long s1Offset, java.lang.foreign.MemorySegment s2, long s2Offset, java.lang.foreign.MemorySegment m, long mOffset, float alpha, int count) {
        long _t1Base = t1.address() + t1Offset;
        long _t2Base = t2.address() + t2Offset;
        long _q1Base = q1.address() + q1Offset;
        long _q2Base = q2.address() + q2Offset;
        long _s1Base = s1.address() + s1Offset;
        long _s2Base = s2.address() + s2Offset;
        var _sp = FloatVector.SPECIES_128;
        for (int _i = 0; _i < count; _i++) {
            long _t1o = _t1Base + _i * 12L;
            long _t2o = _t2Base + _i * 12L;
            long _q1o = _q1Base + _i * 16L;
            long _q2o = _q2Base + _i * 16L;
            long _s1o = _s1Base + _i * 12L;
            long _s2o = _s2Base + _i * 12L;
            long _mo = mOffset + _i * 48L;
            long _do = destOffset + _i * 48L;
            float _ax = UnsafeOpsHolder.U.getFloat(_t1o), _ay = UnsafeOpsHolder.U.getFloat(_t1o + 4L), _az = UnsafeOpsHolder.U.getFloat(_t1o + 8L);
            float _tx = Math.fma(alpha, UnsafeOpsHolder.U.getFloat(_t2o) - _ax, _ax);
            float _ty = Math.fma(alpha, UnsafeOpsHolder.U.getFloat(_t2o + 4L) - _ay, _ay);
            float _tz = Math.fma(alpha, UnsafeOpsHolder.U.getFloat(_t2o + 8L) - _az, _az);
            float _bx = UnsafeOpsHolder.U.getFloat(_s1o), _by = UnsafeOpsHolder.U.getFloat(_s1o + 4L), _bz = UnsafeOpsHolder.U.getFloat(_s1o + 8L);
            float _sx = Math.fma(alpha, UnsafeOpsHolder.U.getFloat(_s2o) - _bx, _bx);
            float _sy = Math.fma(alpha, UnsafeOpsHolder.U.getFloat(_s2o + 4L) - _by, _by);
            float _sz = Math.fma(alpha, UnsafeOpsHolder.U.getFloat(_s2o + 8L) - _bz, _bz);
            float _ux = UnsafeOpsHolder.U.getFloat(_q1o), _uy = UnsafeOpsHolder.U.getFloat(_q1o + 4L), _uz = UnsafeOpsHolder.U.getFloat(_q1o + 8L), _uw = UnsafeOpsHolder.U.getFloat(_q1o + 12L);
            float _vx = UnsafeOpsHolder.U.getFloat(_q2o), _vy = UnsafeOpsHolder.U.getFloat(_q2o + 4L), _vz = UnsafeOpsHolder.U.getFloat(_q2o + 8L), _vw = UnsafeOpsHolder.U.getFloat(_q2o + 12L);
            float _dot = Math.fma(_uw, _vw, Math.fma(_uz, _vz, Math.fma(_ux, _vx, _uy * _vy)));
            float _wx = _dot < 0.0f ? -_vx : _vx, _wy = _dot < 0.0f ? -_vy : _vy, _wz = _dot < 0.0f ? -_vz : _vz, _ww = _dot < 0.0f ? -_vw : _vw;
            float _qx = Math.fma(alpha, _wx - _ux, _ux);
            float _qy = Math.fma(alpha, _wy - _uy, _uy);
            float _qz = Math.fma(alpha, _wz - _uz, _uz);
            float _qw = Math.fma(alpha, _ww - _uw, _uw);
            float _len2 = (_qx * _qx + _qy * _qy) + (_qz * _qz + _qw * _qw);
            float _k = _len2 > 0.0f ? 2.0f / _len2 : 0.0f;
            float _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
            float _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
            float _c00 = Math.fma(-_k, _yy + _zz, 1.0f), _c01 = _k * (_xy - _zw), _c02 = _k * (_xz + _yw);
            float _c10 = _k * (_xy + _zw), _c11 = Math.fma(-_k, _xx + _zz, 1.0f), _c12 = _k * (_yz - _xw);
            float _c20 = _k * (_xz - _yw), _c21 = _k * (_yz + _xw), _c22 = Math.fma(-_k, _xx + _yy, 1.0f);
            var _r0 = FloatVector.fromMemorySegment(_sp, m, _mo, java.nio.ByteOrder.nativeOrder()).mul(_sx);
            var _r1 = FloatVector.fromMemorySegment(_sp, m, _mo + 16L, java.nio.ByteOrder.nativeOrder()).mul(_sy);
            var _r2 = FloatVector.fromMemorySegment(_sp, m, _mo + 32L, java.nio.ByteOrder.nativeOrder()).mul(_sz);
            _r2.fma(FloatVector.broadcast(_sp, _c02), _r1.fma(FloatVector.broadcast(_sp, _c01), _r0.fma(FloatVector.broadcast(_sp, _c00), FloatVector.zero(_sp).withLane(3, _tx)))).intoMemorySegment(dest, _do, java.nio.ByteOrder.nativeOrder());
            _r2.fma(FloatVector.broadcast(_sp, _c12), _r1.fma(FloatVector.broadcast(_sp, _c11), _r0.fma(FloatVector.broadcast(_sp, _c10), FloatVector.zero(_sp).withLane(3, _ty)))).intoMemorySegment(dest, _do + 16L, java.nio.ByteOrder.nativeOrder());
            _r2.fma(FloatVector.broadcast(_sp, _c22), _r1.fma(FloatVector.broadcast(_sp, _c21), _r0.fma(FloatVector.broadcast(_sp, _c20), FloatVector.zero(_sp).withLane(3, _tz)))).intoMemorySegment(dest, _do + 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerpComposeTRSMul_fmaApi(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment t1, long t1Offset, java.lang.foreign.MemorySegment t2, long t2Offset, java.lang.foreign.MemorySegment q1, long q1Offset, java.lang.foreign.MemorySegment q2, long q2Offset, java.lang.foreign.MemorySegment s1, long s1Offset, java.lang.foreign.MemorySegment s2, long s2Offset, java.lang.foreign.MemorySegment m, long mOffset, float alpha, int count) {
        var _sp = FloatVector.SPECIES_128;
        for (int _i = 0; _i < count; _i++) {
            long _t1o = t1Offset + _i * 12L;
            long _t2o = t2Offset + _i * 12L;
            long _q1o = q1Offset + _i * 16L;
            long _q2o = q2Offset + _i * 16L;
            long _s1o = s1Offset + _i * 12L;
            long _s2o = s2Offset + _i * 12L;
            long _mo = mOffset + _i * 48L;
            long _do = destOffset + _i * 48L;
            float _ax = t1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _t1o), _ay = t1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _t1o + 4L), _az = t1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _t1o + 8L);
            float _tx = Math.fma(alpha, t2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _t2o) - _ax, _ax);
            float _ty = Math.fma(alpha, t2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _t2o + 4L) - _ay, _ay);
            float _tz = Math.fma(alpha, t2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _t2o + 8L) - _az, _az);
            float _bx = s1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _s1o), _by = s1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _s1o + 4L), _bz = s1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _s1o + 8L);
            float _sx = Math.fma(alpha, s2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _s2o) - _bx, _bx);
            float _sy = Math.fma(alpha, s2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _s2o + 4L) - _by, _by);
            float _sz = Math.fma(alpha, s2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _s2o + 8L) - _bz, _bz);
            float _ux = q1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _q1o), _uy = q1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _q1o + 4L), _uz = q1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _q1o + 8L), _uw = q1.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _q1o + 12L);
            float _vx = q2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _q2o), _vy = q2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _q2o + 4L), _vz = q2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _q2o + 8L), _vw = q2.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _q2o + 12L);
            float _dot = Math.fma(_uw, _vw, Math.fma(_uz, _vz, Math.fma(_ux, _vx, _uy * _vy)));
            float _wx = _dot < 0.0f ? -_vx : _vx, _wy = _dot < 0.0f ? -_vy : _vy, _wz = _dot < 0.0f ? -_vz : _vz, _ww = _dot < 0.0f ? -_vw : _vw;
            float _qx = Math.fma(alpha, _wx - _ux, _ux);
            float _qy = Math.fma(alpha, _wy - _uy, _uy);
            float _qz = Math.fma(alpha, _wz - _uz, _uz);
            float _qw = Math.fma(alpha, _ww - _uw, _uw);
            float _len2 = (_qx * _qx + _qy * _qy) + (_qz * _qz + _qw * _qw);
            float _k = _len2 > 0.0f ? 2.0f / _len2 : 0.0f;
            float _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
            float _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
            float _c00 = Math.fma(-_k, _yy + _zz, 1.0f), _c01 = _k * (_xy - _zw), _c02 = _k * (_xz + _yw);
            float _c10 = _k * (_xy + _zw), _c11 = Math.fma(-_k, _xx + _zz, 1.0f), _c12 = _k * (_yz - _xw);
            float _c20 = _k * (_xz - _yw), _c21 = _k * (_yz + _xw), _c22 = Math.fma(-_k, _xx + _yy, 1.0f);
            var _r0 = FloatVector.fromMemorySegment(_sp, m, _mo, java.nio.ByteOrder.nativeOrder()).mul(_sx);
            var _r1 = FloatVector.fromMemorySegment(_sp, m, _mo + 16L, java.nio.ByteOrder.nativeOrder()).mul(_sy);
            var _r2 = FloatVector.fromMemorySegment(_sp, m, _mo + 32L, java.nio.ByteOrder.nativeOrder()).mul(_sz);
            _r2.fma(FloatVector.broadcast(_sp, _c02), _r1.fma(FloatVector.broadcast(_sp, _c01), _r0.fma(FloatVector.broadcast(_sp, _c00), FloatVector.zero(_sp).withLane(3, _tx)))).intoMemorySegment(dest, _do, java.nio.ByteOrder.nativeOrder());
            _r2.fma(FloatVector.broadcast(_sp, _c12), _r1.fma(FloatVector.broadcast(_sp, _c11), _r0.fma(FloatVector.broadcast(_sp, _c10), FloatVector.zero(_sp).withLane(3, _ty)))).intoMemorySegment(dest, _do + 16L, java.nio.ByteOrder.nativeOrder());
            _r2.fma(FloatVector.broadcast(_sp, _c22), _r1.fma(FloatVector.broadcast(_sp, _c21), _r0.fma(FloatVector.broadcast(_sp, _c20), FloatVector.zero(_sp).withLane(3, _tz)))).intoMemorySegment(dest, _do + 32L, java.nio.ByteOrder.nativeOrder());
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
            float _tx = translation[_translationo], _ty = translation[_translationo + 1], _tz = translation[_translationo + 2];
            float _sx = scale[_scaleo], _sy = scale[_scaleo + 1], _sz = scale[_scaleo + 2];
            float _qx = rotation[_rotationo], _qy = rotation[_rotationo + 1], _qz = rotation[_rotationo + 2], _qw = rotation[_rotationo + 3];
            float _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
            float _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
            float _t00 = (1 - 2 * (_yy + _zz)) * _sx, _t01 = (2 * (_xy - _zw)) * _sy, _t02 = (2 * (_xz + _yw)) * _sz;
            float _t10 = (2 * (_xy + _zw)) * _sx, _t11 = (1 - 2 * (_xx + _zz)) * _sy, _t12 = (2 * (_yz - _xw)) * _sz;
            float _t20 = (2 * (_xz - _yw)) * _sx, _t21 = (2 * (_yz + _xw)) * _sy, _t22 = (1 - 2 * (_xx + _yy)) * _sz;
            var _r0 = FloatVector.fromArray(_sp, m, _mo);
            var _r1 = FloatVector.fromArray(_sp, m, _mo + 4);
            var _r2 = FloatVector.fromArray(_sp, m, _mo + 8);
            _r2.fma(FloatVector.broadcast(_sp, _t02), _r1.fma(FloatVector.broadcast(_sp, _t01), _r0.fma(FloatVector.broadcast(_sp, _t00), FloatVector.zero(_sp).withLane(3, _tx)))).intoArray(dest, _do);
            _r2.fma(FloatVector.broadcast(_sp, _t12), _r1.fma(FloatVector.broadcast(_sp, _t11), _r0.fma(FloatVector.broadcast(_sp, _t10), FloatVector.zero(_sp).withLane(3, _ty)))).intoArray(dest, _do + 4);
            _r2.fma(FloatVector.broadcast(_sp, _t22), _r1.fma(FloatVector.broadcast(_sp, _t21), _r0.fma(FloatVector.broadcast(_sp, _t20), FloatVector.zero(_sp).withLane(3, _tz)))).intoArray(dest, _do + 8);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment composeTRSMul_fmaUnsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment translation, long translationOffset, java.lang.foreign.MemorySegment rotation, long rotationOffset, java.lang.foreign.MemorySegment scale, long scaleOffset, java.lang.foreign.MemorySegment m, long mOffset, int count) {
        long _translationBase = translation.address() + translationOffset;
        long _rotationBase = rotation.address() + rotationOffset;
        long _scaleBase = scale.address() + scaleOffset;
        var _sp = FloatVector.SPECIES_128;
        for (int _i = 0; _i < count; _i++) {
            long _translationo = _translationBase + _i * 12L;
            long _rotationo = _rotationBase + _i * 16L;
            long _scaleo = _scaleBase + _i * 12L;
            long _mo = mOffset + _i * 48L;
            long _do = destOffset + _i * 48L;
            float _tx = UnsafeOpsHolder.U.getFloat(_translationo), _ty = UnsafeOpsHolder.U.getFloat(_translationo + 4L), _tz = UnsafeOpsHolder.U.getFloat(_translationo + 8L);
            float _sx = UnsafeOpsHolder.U.getFloat(_scaleo), _sy = UnsafeOpsHolder.U.getFloat(_scaleo + 4L), _sz = UnsafeOpsHolder.U.getFloat(_scaleo + 8L);
            float _qx = UnsafeOpsHolder.U.getFloat(_rotationo), _qy = UnsafeOpsHolder.U.getFloat(_rotationo + 4L), _qz = UnsafeOpsHolder.U.getFloat(_rotationo + 8L), _qw = UnsafeOpsHolder.U.getFloat(_rotationo + 12L);
            float _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
            float _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
            float _c00 = Math.fma(-2.0f, _yy + _zz, 1.0f), _c01 = 2.0f * (_xy - _zw), _c02 = 2.0f * (_xz + _yw);
            float _c10 = 2.0f * (_xy + _zw), _c11 = Math.fma(-2.0f, _xx + _zz, 1.0f), _c12 = 2.0f * (_yz - _xw);
            float _c20 = 2.0f * (_xz - _yw), _c21 = 2.0f * (_yz + _xw), _c22 = Math.fma(-2.0f, _xx + _yy, 1.0f);
            var _r0 = FloatVector.fromMemorySegment(_sp, m, _mo, java.nio.ByteOrder.nativeOrder()).mul(_sx);
            var _r1 = FloatVector.fromMemorySegment(_sp, m, _mo + 16L, java.nio.ByteOrder.nativeOrder()).mul(_sy);
            var _r2 = FloatVector.fromMemorySegment(_sp, m, _mo + 32L, java.nio.ByteOrder.nativeOrder()).mul(_sz);
            _r2.fma(FloatVector.broadcast(_sp, _c02), _r1.fma(FloatVector.broadcast(_sp, _c01), _r0.fma(FloatVector.broadcast(_sp, _c00), FloatVector.zero(_sp).withLane(3, _tx)))).intoMemorySegment(dest, _do, java.nio.ByteOrder.nativeOrder());
            _r2.fma(FloatVector.broadcast(_sp, _c12), _r1.fma(FloatVector.broadcast(_sp, _c11), _r0.fma(FloatVector.broadcast(_sp, _c10), FloatVector.zero(_sp).withLane(3, _ty)))).intoMemorySegment(dest, _do + 16L, java.nio.ByteOrder.nativeOrder());
            _r2.fma(FloatVector.broadcast(_sp, _c22), _r1.fma(FloatVector.broadcast(_sp, _c21), _r0.fma(FloatVector.broadcast(_sp, _c20), FloatVector.zero(_sp).withLane(3, _tz)))).intoMemorySegment(dest, _do + 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment composeTRSMul_fmaApi(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment translation, long translationOffset, java.lang.foreign.MemorySegment rotation, long rotationOffset, java.lang.foreign.MemorySegment scale, long scaleOffset, java.lang.foreign.MemorySegment m, long mOffset, int count) {
        var _sp = FloatVector.SPECIES_128;
        for (int _i = 0; _i < count; _i++) {
            long _translationo = translationOffset + _i * 12L;
            long _rotationo = rotationOffset + _i * 16L;
            long _scaleo = scaleOffset + _i * 12L;
            long _mo = mOffset + _i * 48L;
            long _do = destOffset + _i * 48L;
            float _tx = translation.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _translationo), _ty = translation.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _translationo + 4L), _tz = translation.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _translationo + 8L);
            float _sx = scale.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _scaleo), _sy = scale.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _scaleo + 4L), _sz = scale.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _scaleo + 8L);
            float _qx = rotation.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _rotationo), _qy = rotation.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _rotationo + 4L), _qz = rotation.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _rotationo + 8L), _qw = rotation.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, _rotationo + 12L);
            float _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
            float _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
            float _c00 = Math.fma(-2.0f, _yy + _zz, 1.0f), _c01 = 2.0f * (_xy - _zw), _c02 = 2.0f * (_xz + _yw);
            float _c10 = 2.0f * (_xy + _zw), _c11 = Math.fma(-2.0f, _xx + _zz, 1.0f), _c12 = 2.0f * (_yz - _xw);
            float _c20 = 2.0f * (_xz - _yw), _c21 = 2.0f * (_yz + _xw), _c22 = Math.fma(-2.0f, _xx + _yy, 1.0f);
            var _r0 = FloatVector.fromMemorySegment(_sp, m, _mo, java.nio.ByteOrder.nativeOrder()).mul(_sx);
            var _r1 = FloatVector.fromMemorySegment(_sp, m, _mo + 16L, java.nio.ByteOrder.nativeOrder()).mul(_sy);
            var _r2 = FloatVector.fromMemorySegment(_sp, m, _mo + 32L, java.nio.ByteOrder.nativeOrder()).mul(_sz);
            _r2.fma(FloatVector.broadcast(_sp, _c02), _r1.fma(FloatVector.broadcast(_sp, _c01), _r0.fma(FloatVector.broadcast(_sp, _c00), FloatVector.zero(_sp).withLane(3, _tx)))).intoMemorySegment(dest, _do, java.nio.ByteOrder.nativeOrder());
            _r2.fma(FloatVector.broadcast(_sp, _c12), _r1.fma(FloatVector.broadcast(_sp, _c11), _r0.fma(FloatVector.broadcast(_sp, _c10), FloatVector.zero(_sp).withLane(3, _ty)))).intoMemorySegment(dest, _do + 16L, java.nio.ByteOrder.nativeOrder());
            _r2.fma(FloatVector.broadcast(_sp, _c22), _r1.fma(FloatVector.broadcast(_sp, _c21), _r0.fma(FloatVector.broadcast(_sp, _c20), FloatVector.zero(_sp).withLane(3, _tz)))).intoMemorySegment(dest, _do + 32L, java.nio.ByteOrder.nativeOrder());
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
        float _qx = rotation[rotationOffset], _qy = rotation[rotationOffset + 1], _qz = rotation[rotationOffset + 2], _qw = rotation[rotationOffset + 3];
        float _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
        float _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
        float _r00 = 1 - 2 * (_yy + _zz), _r01 = 2 * (_xy - _zw), _r02 = 2 * (_xz + _yw);
        float _r10 = 2 * (_xy + _zw), _r11 = 1 - 2 * (_xx + _zz), _r12 = 2 * (_yz - _xw);
        float _r20 = 2 * (_xz - _yw), _r21 = 2 * (_yz + _xw), _r22 = 1 - 2 * (_xx + _yy);
        composeTRSMulPadded_fma_v672c81d5(dest, destOffset, translation, translationOffset, scale, scaleOffset, m, mOffset, _r00, _r01, _r02, _r10, _r11, _r12, _r20, _r21, _r22);
        return dest;
    }

    private static void composeTRSMulPadded_fma_v672c81d5(float[] dest, int destOffset, float[] translation, int translationOffset, float[] scale, int scaleOffset, float[] m, int mOffset, float _r00, float _r01, float _r02, float _r10, float _r11, float _r12, float _r20, float _r21, float _r22) {
        var _sv = FloatVector.fromArray(SIMD_SPECIES, scale, scaleOffset);
        var _tv = FloatVector.fromArray(SIMD_SPECIES, translation, translationOffset);
        var _m0 = FloatVector.fromArray(SIMD_SPECIES, m, mOffset).mul(_sv.rearrange(CTRSP_S0));
        var _m1 = FloatVector.fromArray(SIMD_SPECIES, m, mOffset + 4).mul(_sv.rearrange(CTRSP_S1));
        var _m2 = FloatVector.fromArray(SIMD_SPECIES, m, mOffset + 8).mul(_sv.rearrange(CTRSP_S2));
        _m2.fma(FloatVector.broadcast(SIMD_SPECIES, _r02), _m1.fma(FloatVector.broadcast(SIMD_SPECIES, _r01), _m0.fma(FloatVector.broadcast(SIMD_SPECIES, _r00), _tv.rearrange(CTRSP_W0)))).intoArray(dest, destOffset);
        _m2.fma(FloatVector.broadcast(SIMD_SPECIES, _r12), _m1.fma(FloatVector.broadcast(SIMD_SPECIES, _r11), _m0.fma(FloatVector.broadcast(SIMD_SPECIES, _r10), _tv.rearrange(CTRSP_W1)))).intoArray(dest, destOffset + 4);
        _m2.fma(FloatVector.broadcast(SIMD_SPECIES, _r22), _m1.fma(FloatVector.broadcast(SIMD_SPECIES, _r21), _m0.fma(FloatVector.broadcast(SIMD_SPECIES, _r20), _tv.rearrange(CTRSP_W2)))).intoArray(dest, destOffset + 8);
    }

    public static java.lang.foreign.MemorySegment composeTRSMulPadded_fmaUnsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment translation, long translationOffset, java.lang.foreign.MemorySegment rotation, long rotationOffset, java.lang.foreign.MemorySegment scale, long scaleOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        long _rotationBase = rotation.address() + rotationOffset;
        float _qx = UnsafeOpsHolder.U.getFloat(_rotationBase), _qy = UnsafeOpsHolder.U.getFloat(_rotationBase + 4L), _qz = UnsafeOpsHolder.U.getFloat(_rotationBase + 8L), _qw = UnsafeOpsHolder.U.getFloat(_rotationBase + 12L);
        float _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
        float _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
        float _r00 = 1 - 2 * (_yy + _zz), _r01 = 2 * (_xy - _zw), _r02 = 2 * (_xz + _yw);
        float _r10 = 2 * (_xy + _zw), _r11 = 1 - 2 * (_xx + _zz), _r12 = 2 * (_yz - _xw);
        float _r20 = 2 * (_xz - _yw), _r21 = 2 * (_yz + _xw), _r22 = 1 - 2 * (_xx + _yy);
        composeTRSMulPadded_fmaUnsafe_v23253854(dest, destOffset, translation, translationOffset, scale, scaleOffset, m, mOffset, _r00, _r01, _r02, _r10, _r11, _r12, _r20, _r21, _r22);
        return dest;
    }

    private static void composeTRSMulPadded_fmaUnsafe_v23253854(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment translation, long translationOffset, java.lang.foreign.MemorySegment scale, long scaleOffset, java.lang.foreign.MemorySegment m, long mOffset, float _r00, float _r01, float _r02, float _r10, float _r11, float _r12, float _r20, float _r21, float _r22) {
        var _sv = FloatVector.fromMemorySegment(SIMD_SPECIES, scale, scaleOffset, java.nio.ByteOrder.nativeOrder());
        var _tv = FloatVector.fromMemorySegment(SIMD_SPECIES, translation, translationOffset, java.nio.ByteOrder.nativeOrder());
        var _m0 = FloatVector.fromMemorySegment(SIMD_SPECIES, m, mOffset, java.nio.ByteOrder.nativeOrder()).mul(_sv.rearrange(CTRSP_S0));
        var _m1 = FloatVector.fromMemorySegment(SIMD_SPECIES, m, mOffset + 16L, java.nio.ByteOrder.nativeOrder()).mul(_sv.rearrange(CTRSP_S1));
        var _m2 = FloatVector.fromMemorySegment(SIMD_SPECIES, m, mOffset + 32L, java.nio.ByteOrder.nativeOrder()).mul(_sv.rearrange(CTRSP_S2));
        _m2.fma(FloatVector.broadcast(SIMD_SPECIES, _r02), _m1.fma(FloatVector.broadcast(SIMD_SPECIES, _r01), _m0.fma(FloatVector.broadcast(SIMD_SPECIES, _r00), _tv.rearrange(CTRSP_W0)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _m2.fma(FloatVector.broadcast(SIMD_SPECIES, _r12), _m1.fma(FloatVector.broadcast(SIMD_SPECIES, _r11), _m0.fma(FloatVector.broadcast(SIMD_SPECIES, _r10), _tv.rearrange(CTRSP_W1)))).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _m2.fma(FloatVector.broadcast(SIMD_SPECIES, _r22), _m1.fma(FloatVector.broadcast(SIMD_SPECIES, _r21), _m0.fma(FloatVector.broadcast(SIMD_SPECIES, _r20), _tv.rearrange(CTRSP_W2)))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment composeTRSMulPadded_fmaApi(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment translation, long translationOffset, java.lang.foreign.MemorySegment rotation, long rotationOffset, java.lang.foreign.MemorySegment scale, long scaleOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        float _qx = rotation.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rotationOffset), _qy = rotation.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rotationOffset + 4L), _qz = rotation.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rotationOffset + 8L), _qw = rotation.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, rotationOffset + 12L);
        float _xx = _qx * _qx, _yy = _qy * _qy, _zz = _qz * _qz;
        float _xy = _qx * _qy, _xz = _qx * _qz, _yz = _qy * _qz, _xw = _qx * _qw, _yw = _qy * _qw, _zw = _qz * _qw;
        float _r00 = 1 - 2 * (_yy + _zz), _r01 = 2 * (_xy - _zw), _r02 = 2 * (_xz + _yw);
        float _r10 = 2 * (_xy + _zw), _r11 = 1 - 2 * (_xx + _zz), _r12 = 2 * (_yz - _xw);
        float _r20 = 2 * (_xz - _yw), _r21 = 2 * (_yz + _xw), _r22 = 1 - 2 * (_xx + _yy);
        composeTRSMulPadded_fmaApi_v1879f724(dest, destOffset, translation, translationOffset, scale, scaleOffset, m, mOffset, _r00, _r01, _r02, _r10, _r11, _r12, _r20, _r21, _r22);
        return dest;
    }

    private static void composeTRSMulPadded_fmaApi_v1879f724(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment translation, long translationOffset, java.lang.foreign.MemorySegment scale, long scaleOffset, java.lang.foreign.MemorySegment m, long mOffset, float _r00, float _r01, float _r02, float _r10, float _r11, float _r12, float _r20, float _r21, float _r22) {
        var _sv = FloatVector.fromMemorySegment(SIMD_SPECIES, scale, scaleOffset, java.nio.ByteOrder.nativeOrder());
        var _tv = FloatVector.fromMemorySegment(SIMD_SPECIES, translation, translationOffset, java.nio.ByteOrder.nativeOrder());
        var _m0 = FloatVector.fromMemorySegment(SIMD_SPECIES, m, mOffset, java.nio.ByteOrder.nativeOrder()).mul(_sv.rearrange(CTRSP_S0));
        var _m1 = FloatVector.fromMemorySegment(SIMD_SPECIES, m, mOffset + 16L, java.nio.ByteOrder.nativeOrder()).mul(_sv.rearrange(CTRSP_S1));
        var _m2 = FloatVector.fromMemorySegment(SIMD_SPECIES, m, mOffset + 32L, java.nio.ByteOrder.nativeOrder()).mul(_sv.rearrange(CTRSP_S2));
        _m2.fma(FloatVector.broadcast(SIMD_SPECIES, _r02), _m1.fma(FloatVector.broadcast(SIMD_SPECIES, _r01), _m0.fma(FloatVector.broadcast(SIMD_SPECIES, _r00), _tv.rearrange(CTRSP_W0)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _m2.fma(FloatVector.broadcast(SIMD_SPECIES, _r12), _m1.fma(FloatVector.broadcast(SIMD_SPECIES, _r11), _m0.fma(FloatVector.broadcast(SIMD_SPECIES, _r10), _tv.rearrange(CTRSP_W1)))).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _m2.fma(FloatVector.broadcast(SIMD_SPECIES, _r22), _m1.fma(FloatVector.broadcast(SIMD_SPECIES, _r21), _m0.fma(FloatVector.broadcast(SIMD_SPECIES, _r20), _tv.rearrange(CTRSP_W2)))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
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

    private static void copyArrSeg(float[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int n) {
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length())
            FloatVector.fromMemorySegment(_sp, src, srcOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + _i);
        for (; _i <= n - 8; _i += 8)
            FloatVector.fromMemorySegment(FloatVector.SPECIES_256, src, srcOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + _i);
        for (; _i <= n - 4; _i += 4)
            FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + _i);
        for (; _i < n; _i++)
            dest[destOffset + _i] = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + _i * 4L);
    }

    private static void copySegArr(java.lang.foreign.MemorySegment dest, long destOffset, float[] src, int srcOffset, int n) {
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length())
            FloatVector.fromArray(_sp, src, srcOffset + _i).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        for (; _i <= n - 8; _i += 8)
            FloatVector.fromArray(FloatVector.SPECIES_256, src, srcOffset + _i).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        for (; _i <= n - 4; _i += 4)
            FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset + _i).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        for (; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, src[srcOffset + _i]);
    }

    private static void copySegSeg(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int n) {
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length())
            FloatVector.fromMemorySegment(_sp, src, srcOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        for (; _i <= n - 8; _i += 8)
            FloatVector.fromMemorySegment(FloatVector.SPECIES_256, src, srcOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        for (; _i <= n - 4; _i += 4)
            FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset + _i * 4L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + _i * 4L, java.nio.ByteOrder.nativeOrder());
        for (; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + _i * 4L, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + _i * 4L));
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

    private static void copyArrSeg_one(float[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (PREFERRED_LANES >= 8) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_256, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset);
            FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + 8);
        }
        else {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset);
            FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + 4);
            FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + 8);
        }
    }

    private static void copySegArr_one(java.lang.foreign.MemorySegment dest, long destOffset, float[] src, int srcOffset) {
        if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, src, srcOffset).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset + 8).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        }
        else {
            FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset + 4).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
            FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset + 8).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    private static void copySegSeg_one(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (PREFERRED_LANES >= 8) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_256, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        }
        else {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
            FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
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
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg_one(dest, destOffset, _srcSeg, srcOffset * 4L);
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
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg(dest, destOffset, _srcSeg, srcOffset * 4L, count * 12);
        }
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
        copyArrSeg_one(dest, destOffset, _srcSeg, srcOffset);
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
        copyArrSeg(dest, destOffset, _srcSeg, srcOffset, count * 12);
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        copyArrSeg_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        copyArrSeg(dest, destOffset, src, srcOffset, count * 12);
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, float[] src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr_one(_destArr, _destOff, src, srcOffset);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegArr_one(_destSeg, destOffset * 4L, src, srcOffset);
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
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegArr(_destSeg, destOffset * 4L, src, srcOffset, count * 12);
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
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copyArrSeg_one(_destArr, _destOff, _srcSeg, srcOffset * 4L);
            }
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copySegArr_one(_destSeg, destOffset * 4L, _srcArr, _srcOff);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copySegSeg_one(_destSeg, destOffset * 4L, _srcSeg, srcOffset * 4L);
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
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copyArrSeg(_destArr, _destOff, _srcSeg, srcOffset * 4L, count * 12);
            }
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            if (src.hasArray() && srcOffset >= 0 && (count > 178956970 ? -1 : count * 12) >= 0 && srcOffset <= src.limit() - (count > 178956970 ? -1 : count * 12)) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copySegArr(_destSeg, destOffset * 4L, _srcArr, _srcOff, count * 12);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copySegSeg(_destSeg, destOffset * 4L, _srcSeg, srcOffset * 4L, count * 12);
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg_one(_destArr, _destOff, _srcSeg, srcOffset);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg_one(_destSeg, destOffset * 4L, _srcSeg, srcOffset);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 178956970 ? -1 : count * 12) >= 0 && destOffset <= dest.limit() - (count > 178956970 ? -1 : count * 12)) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg(_destArr, _destOff, _srcSeg, srcOffset, count * 12);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg(_destSeg, destOffset * 4L, _srcSeg, srcOffset, count * 12);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrSeg_one(_destArr, _destOff, src, srcOffset);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegSeg_one(_destSeg, destOffset * 4L, src, srcOffset);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 178956970 ? -1 : count * 12) >= 0 && destOffset <= dest.limit() - (count > 178956970 ? -1 : count * 12)) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrSeg(_destArr, _destOff, src, srcOffset, count * 12);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegSeg(_destSeg, destOffset * 4L, src, srcOffset, count * 12);
        }
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, float[] src, int srcOffset) {
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        copySegArr_one(_destSeg, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        copySegArr(_destSeg, destOffset, src, srcOffset, count * 12);
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr_one(_destSeg, destOffset, _srcArr, _srcOff);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg_one(_destSeg, destOffset, _srcSeg, srcOffset * 4L);
        }
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        if (src.hasArray() && srcOffset >= 0 && (count > 178956970 ? -1 : count * 12) >= 0 && srcOffset <= src.limit() - (count > 178956970 ? -1 : count * 12)) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr(_destSeg, destOffset, _srcArr, _srcOff, count * 12);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg(_destSeg, destOffset, _srcSeg, srcOffset * 4L, count * 12);
        }
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
        copySegSeg_one(_destSeg, destOffset, _srcSeg, srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
        copySegSeg(_destSeg, destOffset, _srcSeg, srcOffset, count * 12);
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        copySegSeg_one(_destSeg, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        copySegSeg(_destSeg, destOffset, src, srcOffset, count * 12);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, float[] src, int srcOffset) {
        copySegArr_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, float[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        copySegArr(dest, destOffset, src, srcOffset, count * 12);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr_one(dest, destOffset, _srcArr, _srcOff);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg_one(dest, destOffset, _srcSeg, srcOffset * 4L);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (src.hasArray() && srcOffset >= 0 && (count > 178956970 ? -1 : count * 12) >= 0 && srcOffset <= src.limit() - (count > 178956970 ? -1 : count * 12)) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr(dest, destOffset, _srcArr, _srcOff, count * 12);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg(dest, destOffset, _srcSeg, srcOffset * 4L, count * 12);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.ByteBuffer src, int srcOffset) {
        java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
        copySegSeg_one(dest, destOffset, _srcSeg, srcOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
        copySegSeg(dest, destOffset, _srcSeg, srcOffset, count * 12);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        copySegSeg_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        copySegSeg(dest, destOffset, src, srcOffset, count * 12);
        return dest;
    }
}
