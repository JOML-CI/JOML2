// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.simd;

import jdk.incubator.vector.*;
import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Vector-API isolation cell for {@link Double3x3Ops}: every
 * {@code jdk.incubator.vector} reference of the Ops family lives in this class,
 * which is loaded and initialized only behind {@code SimdSupport.VECTOR_API}
 * guards - {@code Double3x3Ops} and its kernel siblings link
 * and run without the incubator module. Not public API.
 */
public final class Double3x3OpsSimd {
    private Double3x3OpsSimd() {}
    private static final VectorSpecies<Double> SIMD_SPECIES = DoubleVector.SPECIES_256;
    private static final int PREFERRED_LANES = DoubleVector.SPECIES_PREFERRED.length();

    public static double[] set(double[] dest, int destOffset, double[] v, int vOffset) {
        double _v22 = v[vOffset + 8];
        var _vcp0 = DoubleVector.fromArray(SIMD_SPECIES, v, vOffset);
        var _vcp1 = DoubleVector.fromArray(SIMD_SPECIES, v, vOffset + 4);
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
        double _v22 = UnsafeOpsHolder.U.getDouble(_vBase + 64L);
        var _vcp0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, v, vOffset, java.nio.ByteOrder.nativeOrder());
        var _vcp1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, v, vOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _vcp0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _vcp1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        UnsafeOpsHolder.U.putDouble(_destBase + 64L, _v22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        double _v22 = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + 64L);
        var _vcp0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, v, vOffset, java.nio.ByteOrder.nativeOrder());
        var _vcp1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, v, vOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _vcp0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _vcp1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _v22);
        return dest;
    }

    public static double[] withTranslation(double[] dest, int destOffset, double[] src, int srcOffset, double tX, double tY) {
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self22 = src[srcOffset + 8];
        var _vcp0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        _vcp0.intoArray(dest, destOffset);
        dest[destOffset + 4] = _self11;
        dest[destOffset + 5] = _self21;
        dest[destOffset + 6] = tX;
        dest[destOffset + 7] = tY;
        dest[destOffset + 8] = _self22;
        return dest;
    }

    public static java.lang.foreign.MemorySegment withTranslation(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double tX, double tY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return withTranslation_unsafe(dest, destOffset, src, srcOffset, tX, tY);
        return withTranslation_api(dest, destOffset, src, srcOffset, tX, tY);
    }

    public static java.lang.foreign.MemorySegment withTranslation_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double tX, double tY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        double _self11 = UnsafeOpsHolder.U.getDouble(_srcBase + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(_srcBase + 40L);
        double _self22 = UnsafeOpsHolder.U.getDouble(_srcBase + 64L);
        var _vcp0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        _vcp0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        UnsafeOpsHolder.U.putDouble(_destBase + 32L, _self11);
        UnsafeOpsHolder.U.putDouble(_destBase + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(_destBase + 48L, tX);
        UnsafeOpsHolder.U.putDouble(_destBase + 56L, tY);
        UnsafeOpsHolder.U.putDouble(_destBase + 64L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment withTranslation_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double tX, double tY) {
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        var _vcp0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        _vcp0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, tX);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, tY);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _self22);
        return dest;
    }

    public static double[] withTranslation(double[] dest, int destOffset, double[] src, int srcOffset, double[] t, int tOffset) {
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self22 = src[srcOffset + 8];
        double _tx = t[tOffset];
        double _ty = t[tOffset + 1];
        var _vcp0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
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
        double _self11 = UnsafeOpsHolder.U.getDouble(_srcBase + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(_srcBase + 40L);
        double _self22 = UnsafeOpsHolder.U.getDouble(_srcBase + 64L);
        double _tx = UnsafeOpsHolder.U.getDouble(_tBase);
        double _ty = UnsafeOpsHolder.U.getDouble(_tBase + 8L);
        var _vcp0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        _vcp0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        UnsafeOpsHolder.U.putDouble(_destBase + 32L, _self11);
        UnsafeOpsHolder.U.putDouble(_destBase + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(_destBase + 48L, _tx);
        UnsafeOpsHolder.U.putDouble(_destBase + 56L, _ty);
        UnsafeOpsHolder.U.putDouble(_destBase + 64L, _self22);
        return dest;
    }

    public static java.lang.foreign.MemorySegment withTranslation_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _tx = t.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, tOffset);
        double _ty = t.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, tOffset + 8L);
        var _vcp0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        _vcp0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, _tx);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, _ty);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, _self22);
        return dest;
    }

    public static double[] translate(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        var _vcp0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        _vcp0.intoArray(dest, destOffset);
        dest[destOffset + 4] = _self11;
        dest[destOffset + 5] = _self21;
        dest[destOffset + 6] = Math.fma(_self00, vX, Math.fma(_self01, vY, _self02));
        dest[destOffset + 7] = Math.fma(_self10, vX, Math.fma(_self11, vY, _self12));
        dest[destOffset + 8] = Math.fma(_self20, vX, Math.fma(_self21, vY, _self22));
        return dest;
    }

    public static java.lang.foreign.MemorySegment translate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return translate_unsafe(dest, destOffset, src, srcOffset, vX, vY);
        return translate_api(dest, destOffset, src, srcOffset, vX, vY);
    }

    public static java.lang.foreign.MemorySegment translate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        long _destBase = dest.address() + destOffset;
        long _srcBase = src.address() + srcOffset;
        double _self00 = UnsafeOpsHolder.U.getDouble(_srcBase);
        double _self10 = UnsafeOpsHolder.U.getDouble(_srcBase + 8L);
        double _self20 = UnsafeOpsHolder.U.getDouble(_srcBase + 16L);
        double _self01 = UnsafeOpsHolder.U.getDouble(_srcBase + 24L);
        double _self11 = UnsafeOpsHolder.U.getDouble(_srcBase + 32L);
        double _self21 = UnsafeOpsHolder.U.getDouble(_srcBase + 40L);
        double _self02 = UnsafeOpsHolder.U.getDouble(_srcBase + 48L);
        double _self12 = UnsafeOpsHolder.U.getDouble(_srcBase + 56L);
        double _self22 = UnsafeOpsHolder.U.getDouble(_srcBase + 64L);
        var _vcp0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        _vcp0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        UnsafeOpsHolder.U.putDouble(_destBase + 32L, _self11);
        UnsafeOpsHolder.U.putDouble(_destBase + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(_destBase + 48L, Math.fma(_self00, vX, Math.fma(_self01, vY, _self02)));
        UnsafeOpsHolder.U.putDouble(_destBase + 56L, Math.fma(_self10, vX, Math.fma(_self11, vY, _self12)));
        UnsafeOpsHolder.U.putDouble(_destBase + 64L, Math.fma(_self20, vX, Math.fma(_self21, vY, _self22)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment translate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        var _vcp0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        _vcp0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self00, vX, Math.fma(_self01, vY, _self02)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self10, vX, Math.fma(_self11, vY, _self12)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_self20, vX, Math.fma(_self21, vY, _self22)));
        return dest;
    }

    public static double[] translate(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        double _self00 = src[srcOffset];
        double _self10 = src[srcOffset + 1];
        double _self20 = src[srcOffset + 2];
        double _self01 = src[srcOffset + 3];
        double _self11 = src[srcOffset + 4];
        double _self21 = src[srcOffset + 5];
        double _self02 = src[srcOffset + 6];
        double _self12 = src[srcOffset + 7];
        double _self22 = src[srcOffset + 8];
        double _vx = v[vOffset];
        double _vy = v[vOffset + 1];
        var _vcp0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
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
        translate_unsafe_v8eabc86f(dest, destOffset, src, srcOffset, dest.address() + destOffset, UnsafeOpsHolder.U.getDouble(_srcBase), UnsafeOpsHolder.U.getDouble(_srcBase + 8L), UnsafeOpsHolder.U.getDouble(_srcBase + 16L), UnsafeOpsHolder.U.getDouble(_srcBase + 24L), UnsafeOpsHolder.U.getDouble(_srcBase + 32L), UnsafeOpsHolder.U.getDouble(_srcBase + 40L), UnsafeOpsHolder.U.getDouble(_srcBase + 48L), UnsafeOpsHolder.U.getDouble(_srcBase + 56L), UnsafeOpsHolder.U.getDouble(_srcBase + 64L), UnsafeOpsHolder.U.getDouble(_vBase), UnsafeOpsHolder.U.getDouble(_vBase + 8L));
        return dest;
    }

    private static void translate_unsafe_v8eabc86f(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, long _destBase, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _vx, double _vy) {
        var _vcp0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        _vcp0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        UnsafeOpsHolder.U.putDouble(_destBase + 32L, _self11);
        UnsafeOpsHolder.U.putDouble(_destBase + 40L, _self21);
        UnsafeOpsHolder.U.putDouble(_destBase + 48L, Math.fma(_self00, _vx, Math.fma(_self01, _vy, _self02)));
        UnsafeOpsHolder.U.putDouble(_destBase + 56L, Math.fma(_self10, _vx, Math.fma(_self11, _vy, _self12)));
        UnsafeOpsHolder.U.putDouble(_destBase + 64L, Math.fma(_self20, _vx, Math.fma(_self21, _vy, _self22)));
    }

    public static java.lang.foreign.MemorySegment translate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L);
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _vx = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset);
        translate_api_v9db3aebd(dest, destOffset, src, srcOffset, _self00, _self10, _self20, _self01, _self11, _self21, _self02, _self12, _self22, _vx, v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + 8L));
        return dest;
    }

    private static void translate_api_v9db3aebd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21, double _self02, double _self12, double _self22, double _vx, double _vy) {
        var _vcp0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        _vcp0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, _self11);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, _self21);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, Math.fma(_self00, _vx, Math.fma(_self01, _vy, _self02)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, Math.fma(_self10, _vx, Math.fma(_self11, _vy, _self12)));
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, Math.fma(_self20, _vx, Math.fma(_self21, _vy, _self22)));
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

    private static void copyArrSeg(double[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int n) {
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length())
            DoubleVector.fromMemorySegment(_sp, src, srcOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + _i);
        for (; _i <= n - 4; _i += 4)
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, src, srcOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + _i);
        for (; _i < n; _i++)
            dest[destOffset + _i] = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _i * 8L);
    }

    private static void copySegArr(java.lang.foreign.MemorySegment dest, long destOffset, double[] src, int srcOffset, int n) {
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length())
            DoubleVector.fromArray(_sp, src, srcOffset + _i).intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        for (; _i <= n - 4; _i += 4)
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset + _i).intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        for (; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, src[srcOffset + _i]);
    }

    private static void copySegSeg(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int n) {
        var _sp = DoubleVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length())
            DoubleVector.fromMemorySegment(_sp, src, srcOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        for (; _i <= n - 4; _i += 4)
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, src, srcOffset + _i * 8L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + _i * 8L, java.nio.ByteOrder.nativeOrder());
        for (; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + _i * 8L, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + _i * 8L));
    }

    private static void copyArrArr_one(double[] dest, int destOffset, double[] src, int srcOffset) {
        if (PREFERRED_LANES >= 8) {
            DoubleVector.fromArray(DoubleVector.SPECIES_512, src, srcOffset).intoArray(dest, destOffset);
            dest[destOffset + 8] = src[srcOffset + 8];
        }
        else if (PREFERRED_LANES >= 4) {
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset).intoArray(dest, destOffset);
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset + 4).intoArray(dest, destOffset + 4);
            dest[destOffset + 8] = src[srcOffset + 8];
        }
        else {
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset).intoArray(dest, destOffset);
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 2).intoArray(dest, destOffset + 2);
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 4).intoArray(dest, destOffset + 4);
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 6).intoArray(dest, destOffset + 6);
            dest[destOffset + 8] = src[srcOffset + 8];
        }
    }

    private static void copyArrSeg_one(double[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (PREFERRED_LANES >= 8) {
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_512, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset);
            dest[destOffset + 8] = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        }
        else if (PREFERRED_LANES >= 4) {
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset);
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + 4);
            dest[destOffset + 8] = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        }
        else {
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_128, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset);
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_128, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + 2);
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_128, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + 4);
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_128, src, srcOffset + 48L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + 6);
            dest[destOffset + 8] = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        }
    }

    private static void copySegArr_one(java.lang.foreign.MemorySegment dest, long destOffset, double[] src, int srcOffset) {
        if (PREFERRED_LANES >= 8) {
            DoubleVector.fromArray(DoubleVector.SPECIES_512, src, srcOffset).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, src[srcOffset + 8]);
        }
        else if (PREFERRED_LANES >= 4) {
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset + 4).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, src[srcOffset + 8]);
        }
        else {
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 2).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 4).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 6).intoMemorySegment(dest, destOffset + 48L, java.nio.ByteOrder.nativeOrder());
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, src[srcOffset + 8]);
        }
    }

    private static void copySegSeg_one(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (PREFERRED_LANES >= 8) {
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_512, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L));
        }
        else if (PREFERRED_LANES >= 4) {
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L));
        }
        else {
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_128, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_128, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_128, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_128, src, srcOffset + 48L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + 48L, java.nio.ByteOrder.nativeOrder());
            dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 64L, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L));
        }
    }


    public static double[] copy(double[] dest, int destOffset, double[] src, int srcOffset) {
        copyArrArr_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        copyArrArr(dest, destOffset, src, srcOffset, count * 9);
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            double[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr_one(dest, destOffset, _srcArr, _srcOff);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg_one(dest, destOffset, _srcSeg, srcOffset * 8L);
        }
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (src.hasArray() && srcOffset >= 0 && (count > 238609294 ? -1 : count * 9) >= 0 && srcOffset <= src.limit() - (count > 238609294 ? -1 : count * 9)) {
            double[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr(dest, destOffset, _srcArr, _srcOff, count * 9);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg(dest, destOffset, _srcSeg, srcOffset * 8L, count * 9);
        }
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
        copyArrSeg_one(dest, destOffset, _srcSeg, srcOffset);
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
        copyArrSeg(dest, destOffset, _srcSeg, srcOffset, count * 9);
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        copyArrSeg_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        copyArrSeg(dest, destOffset, src, srcOffset, count * 9);
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, double[] src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr_one(_destArr, _destOff, src, srcOffset);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegArr_one(_destSeg, destOffset * 8L, src, srcOffset);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 238609294 ? -1 : count * 9) >= 0 && destOffset <= dest.limit() - (count > 238609294 ? -1 : count * 9)) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr(_destArr, _destOff, src, srcOffset, count * 9);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegArr(_destSeg, destOffset * 8L, src, srcOffset, count * 9);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr_one(_destArr, _destOff, _srcArr, _srcOff);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copyArrSeg_one(_destArr, _destOff, _srcSeg, srcOffset * 8L);
            }
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copySegArr_one(_destSeg, destOffset * 8L, _srcArr, _srcOff);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copySegSeg_one(_destSeg, destOffset * 8L, _srcSeg, srcOffset * 8L);
            }
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 238609294 ? -1 : count * 9) >= 0 && destOffset <= dest.limit() - (count > 238609294 ? -1 : count * 9)) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && (count > 238609294 ? -1 : count * 9) >= 0 && srcOffset <= src.limit() - (count > 238609294 ? -1 : count * 9)) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr(_destArr, _destOff, _srcArr, _srcOff, count * 9);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copyArrSeg(_destArr, _destOff, _srcSeg, srcOffset * 8L, count * 9);
            }
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            if (src.hasArray() && srcOffset >= 0 && (count > 238609294 ? -1 : count * 9) >= 0 && srcOffset <= src.limit() - (count > 238609294 ? -1 : count * 9)) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copySegArr(_destSeg, destOffset * 8L, _srcArr, _srcOff, count * 9);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copySegSeg(_destSeg, destOffset * 8L, _srcSeg, srcOffset * 8L, count * 9);
            }
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg_one(_destArr, _destOff, _srcSeg, srcOffset);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg_one(_destSeg, destOffset * 8L, _srcSeg, srcOffset);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 238609294 ? -1 : count * 9) >= 0 && destOffset <= dest.limit() - (count > 238609294 ? -1 : count * 9)) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg(_destArr, _destOff, _srcSeg, srcOffset, count * 9);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg(_destSeg, destOffset * 8L, _srcSeg, srcOffset, count * 9);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 9) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrSeg_one(_destArr, _destOff, src, srcOffset);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegSeg_one(_destSeg, destOffset * 8L, src, srcOffset);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 238609294 ? -1 : count * 9) >= 0 && destOffset <= dest.limit() - (count > 238609294 ? -1 : count * 9)) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrSeg(_destArr, _destOff, src, srcOffset, count * 9);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegSeg(_destSeg, destOffset * 8L, src, srcOffset, count * 9);
        }
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, double[] src, int srcOffset) {
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        copySegArr_one(_destSeg, destOffset, src, srcOffset);
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        copySegArr(_destSeg, destOffset, src, srcOffset, count * 9);
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            double[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr_one(_destSeg, destOffset, _srcArr, _srcOff);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg_one(_destSeg, destOffset, _srcSeg, srcOffset * 8L);
        }
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        if (src.hasArray() && srcOffset >= 0 && (count > 238609294 ? -1 : count * 9) >= 0 && srcOffset <= src.limit() - (count > 238609294 ? -1 : count * 9)) {
            double[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr(_destSeg, destOffset, _srcArr, _srcOff, count * 9);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg(_destSeg, destOffset, _srcSeg, srcOffset * 8L, count * 9);
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

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, double[] src, int srcOffset) {
        copySegArr_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        copySegArr(dest, destOffset, src, srcOffset, count * 9);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 9) {
            double[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr_one(dest, destOffset, _srcArr, _srcOff);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg_one(dest, destOffset, _srcSeg, srcOffset * 8L);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.DoubleBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (src.hasArray() && srcOffset >= 0 && (count > 238609294 ? -1 : count * 9) >= 0 && srcOffset <= src.limit() - (count > 238609294 ? -1 : count * 9)) {
            double[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr(dest, destOffset, _srcArr, _srcOff, count * 9);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg(dest, destOffset, _srcSeg, srcOffset * 8L, count * 9);
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
