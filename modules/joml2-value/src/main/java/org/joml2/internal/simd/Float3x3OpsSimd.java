// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.simd;

import jdk.incubator.vector.*;
import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Vector-API isolation cell for {@link Float3x3Ops}: every
 * {@code jdk.incubator.vector} reference of the Ops family lives in this class,
 * which is loaded and initialized only behind {@code SimdSupport.VECTOR_API}
 * guards - {@code Float3x3Ops} and its kernel siblings link
 * and run without the incubator module. Not public API.
 */
public final class Float3x3OpsSimd {
    private Float3x3OpsSimd() {}
    private static final VectorSpecies<Float> SIMD_SPECIES = FloatVector.SPECIES_128;
    private static final int PREFERRED_LANES = FloatVector.SPECIES_PREFERRED.length();

    public static float[] set(float[] dest, int destOffset, float[] v, int vOffset) {
        float _v22 = v[vOffset + 8];
        var _vcp0 = FloatVector.fromArray(SIMD_SPECIES, v, vOffset);
        var _vcp1 = FloatVector.fromArray(SIMD_SPECIES, v, vOffset + 4);
        _vcp0.intoArray(dest, destOffset);
        _vcp1.intoArray(dest, destOffset + 4);
        dest[destOffset + 8] = _v22;
        return dest;
    }

    public static java.lang.foreign.MemorySegment set(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && v.isNative()) return set_unsafe(dest, destOffset, v, vOffset);
        return set_api(dest, destOffset, v, vOffset);
    }

    public static java.lang.foreign.MemorySegment set_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _destBase = dest.address() + destOffset;
        long _vBase = v.address() + vOffset;
        float _v22 = UnsafeOpsHolder.U.getFloat(_vBase + 32L);
        var _vcp0 = FloatVector.fromMemorySegment(SIMD_SPECIES, v, vOffset, java.nio.ByteOrder.nativeOrder());
        var _vcp1 = FloatVector.fromMemorySegment(SIMD_SPECIES, v, vOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _vcp0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _vcp1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        UnsafeOpsHolder.U.putFloat(_destBase + 32L, _v22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        float _v22 = v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset + 32L);
        var _vcp0 = FloatVector.fromMemorySegment(SIMD_SPECIES, v, vOffset, java.nio.ByteOrder.nativeOrder());
        var _vcp1 = FloatVector.fromMemorySegment(SIMD_SPECIES, v, vOffset + 16L, java.nio.ByteOrder.nativeOrder());
        _vcp0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _vcp1.intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, _v22);
        return dest;
    }

    public static float[] withTranslation(float[] dest, int destOffset, float[] src, int srcOffset, float tX, float tY) {
        float _self11 = src[srcOffset + 4];
        float _self21 = src[srcOffset + 5];
        float _self22 = src[srcOffset + 8];
        var _vcp0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        _vcp0.intoArray(dest, destOffset);
        dest[destOffset + 4] = _self11;
        dest[destOffset + 5] = _self21;
        dest[destOffset + 6] = tX;
        dest[destOffset + 7] = tY;
        dest[destOffset + 8] = _self22;
        return dest;
    }

    public static java.lang.foreign.MemorySegment withTranslation(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float tX, float tY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return withTranslation_unsafe(dest, destOffset, src, srcOffset, tX, tY);
        return withTranslation_api(dest, destOffset, src, srcOffset, tX, tY);
    }

    public static java.lang.foreign.MemorySegment withTranslation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float tX, float tY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        float _self11 = UnsafeOpsHolder.U.getFloat(_srcBase + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(_srcBase + 20L);
        float _self22 = UnsafeOpsHolder.U.getFloat(_srcBase + 32L);
        var _vcp0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        _vcp0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        UnsafeOpsHolder.U.putFloat(_destBase + 16L, _self11);
        UnsafeOpsHolder.U.putFloat(_destBase + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(_destBase + 24L, tX);
        UnsafeOpsHolder.U.putFloat(_destBase + 28L, tY);
        UnsafeOpsHolder.U.putFloat(_destBase + 32L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment withTranslation_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float tX, float tY) {
        float _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        var _vcp0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        _vcp0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, tX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, tY);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22);
        return dest;
    }

    public static float[] withTranslation(float[] dest, int destOffset, float[] src, int srcOffset, float[] t, int tOffset) {
        float _self11 = src[srcOffset + 4];
        float _self21 = src[srcOffset + 5];
        float _self22 = src[srcOffset + 8];
        float _tx = t[tOffset];
        float _ty = t[tOffset + 1];
        var _vcp0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        _vcp0.intoArray(dest, destOffset);
        dest[destOffset + 4] = _self11;
        dest[destOffset + 5] = _self21;
        dest[destOffset + 6] = _tx;
        dest[destOffset + 7] = _ty;
        dest[destOffset + 8] = _self22;
        return dest;
    }

    public static java.lang.foreign.MemorySegment withTranslation(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && t.isNative()) return withTranslation_unsafe(dest, destOffset, src, srcOffset, t, tOffset);
        return withTranslation_api(dest, destOffset, src, srcOffset, t, tOffset);
    }

    public static java.lang.foreign.MemorySegment withTranslation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        long _tBase = t.address() + tOffset;
        float _self11 = UnsafeOpsHolder.U.getFloat(_srcBase + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(_srcBase + 20L);
        float _self22 = UnsafeOpsHolder.U.getFloat(_srcBase + 32L);
        float _tx = UnsafeOpsHolder.U.getFloat(_tBase);
        float _ty = UnsafeOpsHolder.U.getFloat(_tBase + 4L);
        var _vcp0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        _vcp0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        UnsafeOpsHolder.U.putFloat(_destBase + 16L, _self11);
        UnsafeOpsHolder.U.putFloat(_destBase + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(_destBase + 24L, _tx);
        UnsafeOpsHolder.U.putFloat(_destBase + 28L, _ty);
        UnsafeOpsHolder.U.putFloat(_destBase + 32L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment withTranslation_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        float _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _tx = t.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, tOffset);
        float _ty = t.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, tOffset + 4L);
        var _vcp0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        _vcp0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, _tx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, _ty);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, _self22);
        return dest;
    }

    public static float[] translate(float[] dest, int destOffset, float[] src, int srcOffset, float vX, float vY) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self01 = src[srcOffset + 3];
        float _self11 = src[srcOffset + 4];
        float _self21 = src[srcOffset + 5];
        float _self02 = src[srcOffset + 6];
        float _self12 = src[srcOffset + 7];
        float _self22 = src[srcOffset + 8];
        var _vcp0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        _vcp0.intoArray(dest, destOffset);
        dest[destOffset + 4] = _self11;
        dest[destOffset + 5] = _self21;
        dest[destOffset + 6] = Math.fma(_self00, vX, Math.fma(_self01, vY, _self02));
        dest[destOffset + 7] = Math.fma(_self10, vX, Math.fma(_self11, vY, _self12));
        dest[destOffset + 8] = Math.fma(_self20, vX, Math.fma(_self21, vY, _self22));
        return dest;
    }

    public static java.lang.foreign.MemorySegment translate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return translate_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return translate_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    public static java.lang.foreign.MemorySegment translate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        float _self00 = UnsafeOpsHolder.U.getFloat(_srcBase);
        float _self10 = UnsafeOpsHolder.U.getFloat(_srcBase + 4L);
        float _self20 = UnsafeOpsHolder.U.getFloat(_srcBase + 8L);
        float _self01 = UnsafeOpsHolder.U.getFloat(_srcBase + 12L);
        float _self11 = UnsafeOpsHolder.U.getFloat(_srcBase + 16L);
        float _self21 = UnsafeOpsHolder.U.getFloat(_srcBase + 20L);
        float _self02 = UnsafeOpsHolder.U.getFloat(_srcBase + 24L);
        float _self12 = UnsafeOpsHolder.U.getFloat(_srcBase + 28L);
        float _self22 = UnsafeOpsHolder.U.getFloat(_srcBase + 32L);
        var _vcp0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        _vcp0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        UnsafeOpsHolder.U.putFloat(_destBase + 16L, _self11);
        UnsafeOpsHolder.U.putFloat(_destBase + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(_destBase + 24L, Math.fma(_self00, vX, Math.fma(_self01, vY, _self02)));
        UnsafeOpsHolder.U.putFloat(_destBase + 28L, Math.fma(_self10, vX, Math.fma(_self11, vY, _self12)));
        UnsafeOpsHolder.U.putFloat(_destBase + 32L, Math.fma(_self20, vX, Math.fma(_self21, vY, _self22)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment translate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float vX, float vY) {
        float _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        var _vcp0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        _vcp0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, Math.fma(_self00, vX, Math.fma(_self01, vY, _self02)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, Math.fma(_self10, vX, Math.fma(_self11, vY, _self12)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, Math.fma(_self20, vX, Math.fma(_self21, vY, _self22)));
        return dest;
    }

    public static float[] translate(float[] dest, int destOffset, float[] src, int srcOffset, float[] v, int vOffset) {
        float _self00 = src[srcOffset];
        float _self10 = src[srcOffset + 1];
        float _self20 = src[srcOffset + 2];
        float _self01 = src[srcOffset + 3];
        float _self11 = src[srcOffset + 4];
        float _self21 = src[srcOffset + 5];
        float _self02 = src[srcOffset + 6];
        float _self12 = src[srcOffset + 7];
        float _self22 = src[srcOffset + 8];
        float _vx = v[vOffset];
        float _vy = v[vOffset + 1];
        var _vcp0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        _vcp0.intoArray(dest, destOffset);
        dest[destOffset + 4] = _self11;
        dest[destOffset + 5] = _self21;
        dest[destOffset + 6] = Math.fma(_self00, _vx, Math.fma(_self01, _vy, _self02));
        dest[destOffset + 7] = Math.fma(_self10, _vx, Math.fma(_self11, _vy, _self12));
        dest[destOffset + 8] = Math.fma(_self20, _vx, Math.fma(_self21, _vy, _self22));
        return dest;
    }

    public static java.lang.foreign.MemorySegment translate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative() && v.isNative()) return translate_unsafe(dest, destOffset, src, srcOffset, v, vOffset);
        return translate_api(dest, destOffset, src, srcOffset, v, vOffset);
    }

    public static java.lang.foreign.MemorySegment translate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        long _srcBase = src.address() + srcOffset;
        long _vBase = v.address() + vOffset;
        translate_unsafe_v8eabc86f(dest, destOffset, src, srcOffset, dest.address() + destOffset, UnsafeOpsHolder.U.getFloat(_srcBase), UnsafeOpsHolder.U.getFloat(_srcBase + 4L), UnsafeOpsHolder.U.getFloat(_srcBase + 8L), UnsafeOpsHolder.U.getFloat(_srcBase + 12L), UnsafeOpsHolder.U.getFloat(_srcBase + 16L), UnsafeOpsHolder.U.getFloat(_srcBase + 20L), UnsafeOpsHolder.U.getFloat(_srcBase + 24L), UnsafeOpsHolder.U.getFloat(_srcBase + 28L), UnsafeOpsHolder.U.getFloat(_srcBase + 32L), UnsafeOpsHolder.U.getFloat(_vBase), UnsafeOpsHolder.U.getFloat(_vBase + 4L));
        return dest;
    }

    private static void translate_unsafe_v8eabc86f(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, long _destBase, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _vx, float _vy) {
        var _vcp0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        _vcp0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        UnsafeOpsHolder.U.putFloat(_destBase + 16L, _self11);
        UnsafeOpsHolder.U.putFloat(_destBase + 20L, _self21);
        UnsafeOpsHolder.U.putFloat(_destBase + 24L, Math.fma(_self00, _vx, Math.fma(_self01, _vy, _self02)));
        UnsafeOpsHolder.U.putFloat(_destBase + 28L, Math.fma(_self10, _vx, Math.fma(_self11, _vy, _self12)));
        UnsafeOpsHolder.U.putFloat(_destBase + 32L, Math.fma(_self20, _vx, Math.fma(_self21, _vy, _self22)));
    }

    public static java.lang.foreign.MemorySegment translate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        float _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 16L);
        float _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 20L);
        float _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 24L);
        float _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 28L);
        float _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        float _vx = v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset);
        translate_api_v9db3aebd(dest, destOffset, src, srcOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _vx, v.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, vOffset + 4L));
        return dest;
    }

    private static void translate_api_v9db3aebd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float _self00, float _self10, float _self20, float _self01, float _self11, float _self21, float _self02, float _self12, float _self22, float _vx, float _vy) {
        var _vcp0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        _vcp0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, _self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, Math.fma(_self00, _vx, Math.fma(_self01, _vy, _self02)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, Math.fma(_self10, _vx, Math.fma(_self11, _vy, _self12)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, Math.fma(_self20, _vx, Math.fma(_self21, _vy, _self22)));
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
            dest[destOffset + 8] = src[srcOffset + 8];
        }
        else {
            FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset).intoArray(dest, destOffset);
            FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset + 4).intoArray(dest, destOffset + 4);
            dest[destOffset + 8] = src[srcOffset + 8];
        }
    }

    private static void copyArrSeg_one(float[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (PREFERRED_LANES >= 8) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_256, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset);
            dest[destOffset + 8] = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        }
        else {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset);
            FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + 4);
            dest[destOffset + 8] = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 32L);
        }
    }

    private static void copySegArr_one(java.lang.foreign.MemorySegment dest, long destOffset, float[] src, int srcOffset) {
        if (PREFERRED_LANES >= 8) {
            FloatVector.fromArray(FloatVector.SPECIES_256, src, srcOffset).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, src[srcOffset + 8]);
        }
        else {
            FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset + 4).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, src[srcOffset + 8]);
        }
    }

    private static void copySegSeg_one(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (PREFERRED_LANES >= 8) {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_256, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 32L));
        }
        else {
            FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 32L, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 32L));
        }
    }


    public static float[] copy(float[] dest, int destOffset, float[] src, int srcOffset) {
        copyArrArr_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        copyArrArr(dest, destOffset, src, srcOffset, count * 9);
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
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
        if (src.hasArray() && srcOffset >= 0 && (count > 238609294 ? -1 : count * 9) >= 0 && srcOffset <= src.limit() - (count > 238609294 ? -1 : count * 9)) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr(dest, destOffset, _srcArr, _srcOff, count * 9);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg(dest, destOffset, _srcSeg, srcOffset * 4L, count * 9);
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
        copyArrSeg(dest, destOffset, _srcSeg, srcOffset, count * 9);
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        copyArrSeg_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        copyArrSeg(dest, destOffset, src, srcOffset, count * 9);
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, float[] src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
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
        if (dest.hasArray() && destOffset >= 0 && (count > 238609294 ? -1 : count * 9) >= 0 && destOffset <= dest.limit() - (count > 238609294 ? -1 : count * 9)) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr(_destArr, _destOff, src, srcOffset, count * 9);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegArr(_destSeg, destOffset * 4L, src, srcOffset, count * 9);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr_one(_destArr, _destOff, _srcArr, _srcOff);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copyArrSeg_one(_destArr, _destOff, _srcSeg, srcOffset * 4L);
            }
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
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
        if (dest.hasArray() && destOffset >= 0 && (count > 238609294 ? -1 : count * 9) >= 0 && destOffset <= dest.limit() - (count > 238609294 ? -1 : count * 9)) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && (count > 238609294 ? -1 : count * 9) >= 0 && srcOffset <= src.limit() - (count > 238609294 ? -1 : count * 9)) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr(_destArr, _destOff, _srcArr, _srcOff, count * 9);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copyArrSeg(_destArr, _destOff, _srcSeg, srcOffset * 4L, count * 9);
            }
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            if (src.hasArray() && srcOffset >= 0 && (count > 238609294 ? -1 : count * 9) >= 0 && srcOffset <= src.limit() - (count > 238609294 ? -1 : count * 9)) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copySegArr(_destSeg, destOffset * 4L, _srcArr, _srcOff, count * 9);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copySegSeg(_destSeg, destOffset * 4L, _srcSeg, srcOffset * 4L, count * 9);
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
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
        if (dest.hasArray() && destOffset >= 0 && (count > 238609294 ? -1 : count * 9) >= 0 && destOffset <= dest.limit() - (count > 238609294 ? -1 : count * 9)) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg(_destArr, _destOff, _srcSeg, srcOffset, count * 9);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg(_destSeg, destOffset * 4L, _srcSeg, srcOffset, count * 9);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
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
        if (dest.hasArray() && destOffset >= 0 && (count > 238609294 ? -1 : count * 9) >= 0 && destOffset <= dest.limit() - (count > 238609294 ? -1 : count * 9)) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrSeg(_destArr, _destOff, src, srcOffset, count * 9);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegSeg(_destSeg, destOffset * 4L, src, srcOffset, count * 9);
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
        copySegArr(_destSeg, destOffset, src, srcOffset, count * 9);
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
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
        if (src.hasArray() && srcOffset >= 0 && (count > 238609294 ? -1 : count * 9) >= 0 && srcOffset <= src.limit() - (count > 238609294 ? -1 : count * 9)) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr(_destSeg, destOffset, _srcArr, _srcOff, count * 9);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg(_destSeg, destOffset, _srcSeg, srcOffset * 4L, count * 9);
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
        copySegSeg(_destSeg, destOffset, _srcSeg, srcOffset, count * 9);
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
        copySegSeg(_destSeg, destOffset, src, srcOffset, count * 9);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, float[] src, int srcOffset) {
        copySegArr_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, float[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        copySegArr(dest, destOffset, src, srcOffset, count * 9);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
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
        if (src.hasArray() && srcOffset >= 0 && (count > 238609294 ? -1 : count * 9) >= 0 && srcOffset <= src.limit() - (count > 238609294 ? -1 : count * 9)) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr(dest, destOffset, _srcArr, _srcOff, count * 9);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg(dest, destOffset, _srcSeg, srcOffset * 4L, count * 9);
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
        copySegSeg(dest, destOffset, _srcSeg, srcOffset, count * 9);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        copySegSeg_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        copySegSeg(dest, destOffset, src, srcOffset, count * 9);
        return dest;
    }
}
