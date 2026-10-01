// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.simd;

import jdk.incubator.vector.*;
import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Vector-API isolation cell for {@link Double3x4Ops}: every
 * {@code jdk.incubator.vector} reference of the Ops family lives in this class,
 * which is loaded and initialized only behind {@code SimdSupport.VECTOR_API}
 * guards - {@code Double3x4Ops} and its kernel siblings link
 * and run without the incubator module. Not public API.
 */
public final class Double3x4OpsSimd {
    private Double3x4OpsSimd() {}
    private static final VectorSpecies<Double> SIMD_SPECIES = DoubleVector.SPECIES_256;
    private static final int PREFERRED_LANES = DoubleVector.SPECIES_PREFERRED.length();

    public static double[] transpose(double[] dest, int destOffset, double[] src, int srcOffset) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment transpose(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 32L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static double[] add(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset + _li * 4).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment add(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + _li * 32L, java.nio.ByteOrder.nativeOrder()).add(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 32L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static double[] mul(double[] dest, int destOffset, double[] src, int srcOffset, double scalar) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.broadcast(SIMD_SPECIES, scalar).mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.broadcast(SIMD_SPECIES, scalar).mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 32L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static double[] negate(double[] dest, int destOffset, double[] src, int srcOffset) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4).neg().intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 32L, java.nio.ByteOrder.nativeOrder()).neg().intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static double[] sub(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4).sub(DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset + _li * 4)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 32L, java.nio.ByteOrder.nativeOrder()).sub(DoubleVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + _li * 32L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static double[] set(double[] dest, int destOffset, double[] v, int vOffset) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.fromArray(SIMD_SPECIES, v, vOffset + _li * 4).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment set(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.fromMemorySegment(SIMD_SPECIES, v, vOffset + _li * 32L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static double[] withTranslation(double[] dest, int destOffset, double[] src, int srcOffset, double tX, double tY, double tZ) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(3, tY);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(3, tZ);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(3, tX).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static java.lang.foreign.MemorySegment withTranslation(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double tX, double tY, double tZ) {
        var _c1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).withLane(3, tY);
        var _c2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder()).withLane(3, tZ);
        DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).withLane(3, tX).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static double[] withTranslation(double[] dest, int destOffset, double[] src, int srcOffset, double[] t, int tOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).withLane(3, t[tOffset + 1]);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).withLane(3, t[tOffset + 2]);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(3, t[tOffset]).intoArray(dest, destOffset);
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
        var _c1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).withLane(3, UnsafeOpsHolder.U.getDouble(_tBase + 8L));
        var _c2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder()).withLane(3, UnsafeOpsHolder.U.getDouble(_tBase + 16L));
        DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).withLane(3, UnsafeOpsHolder.U.getDouble(_tBase)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment withTranslation_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment t, long tOffset) {
        var _c1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).withLane(3, t.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, tOffset + 8L));
        var _c2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder()).withLane(3, t.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, tOffset + 16L));
        DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).withLane(3, t.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, tOffset)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static double[] lerp(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static double[] lerp_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t) {
        for (int _li = 0; _li < 3; _li++) {
            int _lo1 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, t).fma(DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset + _li * 4).sub(DoubleVector.fromArray(SIMD_SPECIES, src, _lo1)), DoubleVector.fromArray(SIMD_SPECIES, src, _lo1)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] lerp_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double t) {
        for (int _li = 0; _li < 3; _li++) {
            int _lo1 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, t).mul(DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset + _li * 4).sub(DoubleVector.fromArray(SIMD_SPECIES, src, _lo1))).add(DoubleVector.fromArray(SIMD_SPECIES, src, _lo1)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static java.lang.foreign.MemorySegment lerp_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        for (int _li = 0; _li < 3; _li++) {
            var _lv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
            DoubleVector.broadcast(SIMD_SPECIES, t).fma(DoubleVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + _li * 32L, java.nio.ByteOrder.nativeOrder()).sub(_lv0), _lv0).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        for (int _li = 0; _li < 3; _li++) {
            var _lv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
            DoubleVector.broadcast(SIMD_SPECIES, t).mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + _li * 32L, java.nio.ByteOrder.nativeOrder()).sub(_lv0)).add(_lv0).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static double[] mul(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        if (SimdSupport.USE_FMA) return mul_fma(dest, destOffset, src, srcOffset, right, rightOffset);
        return mul_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static double[] mul_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, right, rightOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, right, rightOffset + 4);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, right, rightOffset + 8);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0]), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]), DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] mul_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, right, rightOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, right, rightOffset + 4);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, right, rightOffset + 8);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0])).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 1])).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 2])).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
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
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, right, rightOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, right, rightOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, right, rightOffset + 64L, java.nio.ByteOrder.nativeOrder());
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L)), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 16L)), DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, right, rightOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, right, rightOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, right, rightOffset + 64L, java.nio.ByteOrder.nativeOrder());
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L)), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)), DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return mul_mulAdd_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return mul_mulAdd_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static java.lang.foreign.MemorySegment mul_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        long _srcBase = src.address() + srcOffset;
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, right, rightOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, right, rightOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, right, rightOffset + 64L, java.nio.ByteOrder.nativeOrder());
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 16L))).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, right, rightOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, right, rightOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, right, rightOffset + 64L, java.nio.ByteOrder.nativeOrder());
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L))).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static double[] mulMat2x3(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        double _right00 = right[rightOffset];
        double _right01 = right[rightOffset + 2];
        double _right10 = right[rightOffset + 1];
        double _right11 = right[rightOffset + 3];
        double _right02 = right[rightOffset + 4];
        double _right12 = right[rightOffset + 5];
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, _right00).withLane(1, _right01).mul(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0])).withLane(3, src[_lo0 + 3]).add(DoubleVector.broadcast(SIMD_SPECIES, _right10).withLane(1, _right11).mul(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 1])).withLane(3, _right02 * src[_lo0] + _right12 * src[_lo0 + 1])).withLane(2, src[_lo0 + 2]).intoArray(dest, destOffset + _li * 4);
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
        double _self00 = UnsafeOpsHolder.U.getDouble(_srcBase);
        double _self01 = UnsafeOpsHolder.U.getDouble(_srcBase + 8L);
        double _right02 = UnsafeOpsHolder.U.getDouble(_rightBase + 32L);
        double _right12 = UnsafeOpsHolder.U.getDouble(_rightBase + 40L);
        double _self10 = UnsafeOpsHolder.U.getDouble(_srcBase + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(_srcBase + 40L);
        double _self20 = UnsafeOpsHolder.U.getDouble(_srcBase + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(_srcBase + 72L);
        mulMat2x3_unsafe_s67d66b86_v(dest, destOffset, UnsafeOpsHolder.U.getDouble(_rightBase), UnsafeOpsHolder.U.getDouble(_rightBase + 16L), _self00, UnsafeOpsHolder.U.getDouble(_srcBase + 24L), UnsafeOpsHolder.U.getDouble(_rightBase + 8L), UnsafeOpsHolder.U.getDouble(_rightBase + 24L), _self01, _right02 * _self00 + _right12 * _self01, UnsafeOpsHolder.U.getDouble(_srcBase + 16L), _self10, UnsafeOpsHolder.U.getDouble(_srcBase + 56L), _self11, _right02 * _self10 + _right12 * _self11, UnsafeOpsHolder.U.getDouble(_srcBase + 48L), _self20, UnsafeOpsHolder.U.getDouble(_srcBase + 88L), _self21, _right02 * _self20 + _right12 * _self21, UnsafeOpsHolder.U.getDouble(_srcBase + 80L));
        return dest;
    }

    private static void mulMat2x3_unsafe_s67d66b86_v(java.lang.foreign.MemorySegment dest, long destOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8, double _h9, double _h10, double _h11, double _h12, double _h13, double _h14, double _h15, double _h16, double _h17, double _h18) {
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, _h0).withLane(1, _h1);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, _h4).withLane(1, _h5);
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h2)).withLane(3, _h3).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h6)).withLane(3, _h7)).withLane(2, _h8).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h9)).withLane(3, _h10).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h11)).withLane(3, _h12)).withLane(2, _h13).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h14)).withLane(3, _h15).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h16)).withLane(3, _h17)).withLane(2, _h18).intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment mulMat2x3_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _right02 = right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + 32L);
        double _right12 = right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + 40L);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 72L);
        return mulMat2x3_api_sb92eea6_1(dest, destOffset, src, srcOffset, right, rightOffset, _self00, _self01, _right02, _right12, _self10, _self11, _self20, _self21);
    }

    /** Piece 2 of {@code mulMat2x3_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment mulMat2x3_api_sb92eea6_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset, double _self00, double _self01, double _right02, double _right12, double _self10, double _self11, double _self20, double _self21) {
        mulMat2x3_unsafe_s67d66b86_v(dest, destOffset, right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset), right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + 16L), _self00, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L), right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + 8L), right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + 24L), _self01, _right02 * _self00 + _right12 * _self01, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L), _self10, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L), _self11, _right02 * _self10 + _right12 * _self11, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L), _self20, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 88L), _self21, _right02 * _self20 + _right12 * _self21, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 80L));
        return dest;
    }

    public static double[] mulMat3x3(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        if (SimdSupport.USE_FMA) return mulMat3x3_fma(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat3x3_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static double[] mulMat3x3_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        double _right20 = right[rightOffset + 2];
        double _right21 = right[rightOffset + 5];
        double _right22 = right[rightOffset + 8];
        double _right00 = right[rightOffset];
        double _right01 = right[rightOffset + 3];
        double _right02 = right[rightOffset + 6];
        double _right10 = right[rightOffset + 1];
        double _right11 = right[rightOffset + 4];
        double _right12 = right[rightOffset + 7];
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _right20).withLane(1, _right21).withLane(2, _right22);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _right00).withLane(1, _right01).withLane(2, _right02);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _right10).withLane(1, _right11).withLane(2, _right12);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0]), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]), DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] mulMat3x3_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        double _right20 = right[rightOffset + 2];
        double _right21 = right[rightOffset + 5];
        double _right22 = right[rightOffset + 8];
        double _right00 = right[rightOffset];
        double _right01 = right[rightOffset + 3];
        double _right02 = right[rightOffset + 6];
        double _right10 = right[rightOffset + 1];
        double _right11 = right[rightOffset + 4];
        double _right12 = right[rightOffset + 7];
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _right20).withLane(1, _right21).withLane(2, _right22);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _right00).withLane(1, _right01).withLane(2, _right02);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _right10).withLane(1, _right11).withLane(2, _right12);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 2])).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0])).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 1])).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
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
        mulMat3x3_fma_unsafe_sfcb7544e_v(dest, destOffset, src.address() + srcOffset, UnsafeOpsHolder.U.getDouble(_rightBase + 16L), UnsafeOpsHolder.U.getDouble(_rightBase + 40L), UnsafeOpsHolder.U.getDouble(_rightBase + 64L), UnsafeOpsHolder.U.getDouble(_rightBase), UnsafeOpsHolder.U.getDouble(_rightBase + 24L), UnsafeOpsHolder.U.getDouble(_rightBase + 48L), UnsafeOpsHolder.U.getDouble(_rightBase + 8L), UnsafeOpsHolder.U.getDouble(_rightBase + 32L), UnsafeOpsHolder.U.getDouble(_rightBase + 56L));
        return dest;
    }

    private static void mulMat3x3_fma_unsafe_sfcb7544e_v(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, double _right20, double _right21, double _right22, double _right00, double _right01, double _right02, double _right10, double _right11, double _right12) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _right20).withLane(1, _right21).withLane(2, _right22);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _right00).withLane(1, _right01).withLane(2, _right02);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _right10).withLane(1, _right11).withLane(2, _right12);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 16L)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1)), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L)), DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment mulMat3x3_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        mulMat3x3_fma_api_s5c4f191e_v(dest, destOffset, src, srcOffset, right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + 16L), right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + 40L), right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + 64L), right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset), right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + 24L), right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + 48L), right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + 8L), right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + 32L), right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + 56L));
        return dest;
    }

    private static void mulMat3x3_fma_api_s5c4f191e_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _right20, double _right21, double _right22, double _right00, double _right01, double _right02, double _right10, double _right11, double _right12) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _right20).withLane(1, _right21).withLane(2, _right22);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _right00).withLane(1, _right01).withLane(2, _right02);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _right10).withLane(1, _right11).withLane(2, _right12);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L)), DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment mulMat3x3_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && right.isNative()) return mulMat3x3_mulAdd_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat3x3_mulAdd_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static java.lang.foreign.MemorySegment mulMat3x3_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        long _rightBase = right.address() + rightOffset;
        mulMat3x3_mulAdd_unsafe_sf3e87885_v(dest, destOffset, src.address() + srcOffset, UnsafeOpsHolder.U.getDouble(_rightBase + 16L), UnsafeOpsHolder.U.getDouble(_rightBase + 40L), UnsafeOpsHolder.U.getDouble(_rightBase + 64L), UnsafeOpsHolder.U.getDouble(_rightBase), UnsafeOpsHolder.U.getDouble(_rightBase + 24L), UnsafeOpsHolder.U.getDouble(_rightBase + 48L), UnsafeOpsHolder.U.getDouble(_rightBase + 8L), UnsafeOpsHolder.U.getDouble(_rightBase + 32L), UnsafeOpsHolder.U.getDouble(_rightBase + 56L));
        return dest;
    }

    private static void mulMat3x3_mulAdd_unsafe_sf3e87885_v(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, double _right20, double _right21, double _right22, double _right00, double _right01, double _right02, double _right10, double _right11, double _right12) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _right20).withLane(1, _right21).withLane(2, _right22);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _right00).withLane(1, _right01).withLane(2, _right02);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _right10).withLane(1, _right11).withLane(2, _right12);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 16L))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L))).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment mulMat3x3_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        mulMat3x3_mulAdd_api_s5ae0b4cf_v(dest, destOffset, src, srcOffset, right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + 16L), right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + 40L), right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + 64L), right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset), right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + 24L), right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + 48L), right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + 8L), right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + 32L), right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rightOffset + 56L));
        return dest;
    }

    private static void mulMat3x3_mulAdd_api_s5ae0b4cf_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _right20, double _right21, double _right22, double _right00, double _right01, double _right02, double _right10, double _right11, double _right12) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _right20).withLane(1, _right21).withLane(2, _right22);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _right00).withLane(1, _right01).withLane(2, _right02);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _right10).withLane(1, _right11).withLane(2, _right12);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L))).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static double[] mulMat4x4(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        if (SimdSupport.USE_FMA) return mulMat4x4_fma(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat4x4_mulAdd(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static double[] mulMat4x4_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        mulMat4x4_fma_vf9677ccf(dest, destOffset, right, rightOffset, src[srcOffset + 3], src[srcOffset + 7], src[srcOffset + 11], src[srcOffset + 2], src[srcOffset + 6], src[srcOffset + 10], src[srcOffset], src[srcOffset + 4], src[srcOffset + 8], src[srcOffset + 1], src[srcOffset + 5], src[srcOffset + 9]);
        return dest;
    }

    private static void mulMat4x4_fma_vf9677ccf(double[] dest, int destOffset, double[] right, int rightOffset, double _self03, double _self13, double _self23, double _self02, double _self12, double _self22, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _self03).withLane(1, _self13).withLane(2, _self23);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _self02).withLane(1, _self12).withLane(2, _self22);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _self00).withLane(1, _self10).withLane(2, _self20);
        var _sv3 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _self01).withLane(1, _self11).withLane(2, _self21);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = rightOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, right[_lo0 + 3]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, right[_lo0 + 2]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, right[_lo0]).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, right[_lo0 + 1]).fma(_sv3, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, right[_lo0 + 3]))))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] mulMat4x4_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] right, int rightOffset) {
        mulMat4x4_mulAdd_s526e04ec_v(dest, destOffset, right, rightOffset, src[srcOffset + 3], src[srcOffset + 7], src[srcOffset + 11], src[srcOffset + 2], src[srcOffset + 6], src[srcOffset + 10], src[srcOffset], src[srcOffset + 4], src[srcOffset + 8], src[srcOffset + 1], src[srcOffset + 5], src[srcOffset + 9]);
        return dest;
    }

    private static void mulMat4x4_mulAdd_s526e04ec_v(double[] dest, int destOffset, double[] right, int rightOffset, double _self03, double _self13, double _self23, double _self02, double _self12, double _self22, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _self03).withLane(1, _self13).withLane(2, _self23);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _self02).withLane(1, _self12).withLane(2, _self22);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _self00).withLane(1, _self10).withLane(2, _self20);
        var _sv3 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _self01).withLane(1, _self11).withLane(2, _self21);
        for (int _li = 0; _li < 4; _li++) {
            int _lo0 = rightOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, right[_lo0 + 3]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, right[_lo0 + 2]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, right[_lo0]).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, right[_lo0 + 1]).mul(_sv3).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, right[_lo0 + 3]))))).intoArray(dest, destOffset + _li * 4);
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
        mulMat4x4_fma_unsafe_s599ef746_v(dest, destOffset, right.address() + rightOffset, UnsafeOpsHolder.U.getDouble(_srcBase + 24L), UnsafeOpsHolder.U.getDouble(_srcBase + 56L), UnsafeOpsHolder.U.getDouble(_srcBase + 88L), UnsafeOpsHolder.U.getDouble(_srcBase + 16L), UnsafeOpsHolder.U.getDouble(_srcBase + 48L), UnsafeOpsHolder.U.getDouble(_srcBase + 80L), UnsafeOpsHolder.U.getDouble(_srcBase), UnsafeOpsHolder.U.getDouble(_srcBase + 32L), UnsafeOpsHolder.U.getDouble(_srcBase + 64L), UnsafeOpsHolder.U.getDouble(_srcBase + 8L), UnsafeOpsHolder.U.getDouble(_srcBase + 40L), UnsafeOpsHolder.U.getDouble(_srcBase + 72L));
        return dest;
    }

    private static void mulMat4x4_fma_unsafe_s599ef746_v(java.lang.foreign.MemorySegment dest, long destOffset, long _rightBase, double _self03, double _self13, double _self23, double _self02, double _self12, double _self22, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _self03).withLane(1, _self13).withLane(2, _self23);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _self02).withLane(1, _self12).withLane(2, _self22);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _self00).withLane(1, _self10).withLane(2, _self20);
        var _sv3 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _self01).withLane(1, _self11).withLane(2, _self21);
        for (int _li = 0; _li < 4; _li++) {
            long _lb1 = _rightBase + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 24L)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 16L)).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1)).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L)).fma(_sv3, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L)))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment mulMat4x4_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        mulMat4x4_fma_api_s9fd90466_v(dest, destOffset, right, rightOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 88L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 80L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 72L));
        return dest;
    }

    private static void mulMat4x4_fma_api_s9fd90466_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment right, long rightOffset, double _self03, double _self13, double _self23, double _self02, double _self12, double _self22, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _self03).withLane(1, _self13).withLane(2, _self23);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _self02).withLane(1, _self12).withLane(2, _self22);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _self00).withLane(1, _self10).withLane(2, _self20);
        var _sv3 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _self01).withLane(1, _self11).withLane(2, _self21);
        for (int _li = 0; _li < 4; _li++) {
            long _lo0 = rightOffset + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L)).fma(_sv3, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L)))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment mulMat4x4_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && right.isNative()) return mulMat4x4_mulAdd_unsafe(dest, destOffset, src, srcOffset, right, rightOffset);
        return mulMat4x4_mulAdd_api(dest, destOffset, src, srcOffset, right, rightOffset);
    }

    public static java.lang.foreign.MemorySegment mulMat4x4_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        long _srcBase = src.address() + srcOffset;
        mulMat4x4_mulAdd_unsafe_s3e1f251d_v(dest, destOffset, right.address() + rightOffset, UnsafeOpsHolder.U.getDouble(_srcBase + 24L), UnsafeOpsHolder.U.getDouble(_srcBase + 56L), UnsafeOpsHolder.U.getDouble(_srcBase + 88L), UnsafeOpsHolder.U.getDouble(_srcBase + 16L), UnsafeOpsHolder.U.getDouble(_srcBase + 48L), UnsafeOpsHolder.U.getDouble(_srcBase + 80L), UnsafeOpsHolder.U.getDouble(_srcBase), UnsafeOpsHolder.U.getDouble(_srcBase + 32L), UnsafeOpsHolder.U.getDouble(_srcBase + 64L), UnsafeOpsHolder.U.getDouble(_srcBase + 8L), UnsafeOpsHolder.U.getDouble(_srcBase + 40L), UnsafeOpsHolder.U.getDouble(_srcBase + 72L));
        return dest;
    }

    private static void mulMat4x4_mulAdd_unsafe_s3e1f251d_v(java.lang.foreign.MemorySegment dest, long destOffset, long _rightBase, double _self03, double _self13, double _self23, double _self02, double _self12, double _self22, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _self03).withLane(1, _self13).withLane(2, _self23);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _self02).withLane(1, _self12).withLane(2, _self22);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _self00).withLane(1, _self10).withLane(2, _self20);
        var _sv3 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _self01).withLane(1, _self11).withLane(2, _self21);
        for (int _li = 0; _li < 4; _li++) {
            long _lb1 = _rightBase + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 24L)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 16L)).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1)).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L)).mul(_sv3).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L)))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment mulMat4x4_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment right, long rightOffset) {
        mulMat4x4_mulAdd_api_s10dc5017_v(dest, destOffset, right, rightOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 88L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 80L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 72L));
        return dest;
    }

    private static void mulMat4x4_mulAdd_api_s10dc5017_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment right, long rightOffset, double _self03, double _self13, double _self23, double _self02, double _self12, double _self22, double _self00, double _self10, double _self20, double _self01, double _self11, double _self21) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _self03).withLane(1, _self13).withLane(2, _self23);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _self02).withLane(1, _self12).withLane(2, _self22);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _self00).withLane(1, _self10).withLane(2, _self20);
        var _sv3 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _self01).withLane(1, _self11).withLane(2, _self21);
        for (int _li = 0; _li < 4; _li++) {
            long _lo0 = rightOffset + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L)).mul(_sv3).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, right.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L)))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static double[] preMul(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        if (SimdSupport.USE_FMA) return preMul_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMul_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static double[] preMul_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = otherOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, other[_lo0]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, other[_lo0 + 1]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, other[_lo0 + 2]).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, other[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] preMul_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = otherOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, other[_lo0]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, other[_lo0 + 1]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, other[_lo0 + 2]).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, other[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
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
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder());
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _otherBase + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L)).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 16L)).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder());
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = otherOffset + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L)).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && other.isNative()) return preMul_mulAdd_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMul_mulAdd_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.lang.foreign.MemorySegment preMul_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _otherBase = other.address() + otherOffset;
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder());
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _otherBase + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L)).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 16L)).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMul_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder());
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = otherOffset + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L)).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static double[] preMulMat2x2(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        if (SimdSupport.USE_FMA) return preMulMat2x2_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat2x2_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static double[] preMulMat2x2_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset + 1]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset + 3]).mul(_sv1));
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset + 2]).mul(_sv1)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preMulMat2x2_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset + 1]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset + 3]).mul(_sv1));
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset + 2]).mul(_sv1)).intoArray(dest, destOffset);
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
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_otherBase + 8L)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_otherBase + 24L)).mul(_sv1));
        var _c2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder());
        DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_otherBase)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_otherBase + 16L)).mul(_sv1)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat2x2_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 8L)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 24L)).mul(_sv1));
        var _c2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder());
        DoubleVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 16L)).mul(_sv1)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat2x2_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && other.isNative()) return preMulMat2x2_mulAdd_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat2x2_mulAdd_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.lang.foreign.MemorySegment preMulMat2x2_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _otherBase = other.address() + otherOffset;
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_otherBase + 8L)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_otherBase + 24L)).mul(_sv1));
        var _c2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder());
        DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_otherBase)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_otherBase + 16L)).mul(_sv1)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat2x2_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 8L)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 24L)).mul(_sv1));
        var _c2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder());
        DoubleVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 16L)).mul(_sv1)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static double[] preMulMat2x3(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        if (SimdSupport.USE_FMA) return preMulMat2x3_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat2x3_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static double[] preMulMat2x3_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset + 1]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset + 3]).fma(_sv1, _sv2.withLane(3, other[otherOffset + 5])));
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset + 2]).fma(_sv1, _sv2.withLane(3, other[otherOffset + 4]))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preMulMat2x3_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset + 1]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset + 3]).mul(_sv1).add(_sv2.withLane(3, other[otherOffset + 5])));
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset + 2]).mul(_sv1).add(_sv2.withLane(3, other[otherOffset + 4]))).intoArray(dest, destOffset);
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
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_otherBase + 8L)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_otherBase + 24L)).fma(_sv1, _sv2.withLane(3, UnsafeOpsHolder.U.getDouble(_otherBase + 40L))));
        var _c2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder());
        DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_otherBase)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_otherBase + 16L)).fma(_sv1, _sv2.withLane(3, UnsafeOpsHolder.U.getDouble(_otherBase + 32L)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat2x3_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 8L)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 24L)).fma(_sv1, _sv2.withLane(3, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 40L))));
        var _c2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder());
        DoubleVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 16L)).fma(_sv1, _sv2.withLane(3, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 32L)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat2x3_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && other.isNative()) return preMulMat2x3_mulAdd_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat2x3_mulAdd_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.lang.foreign.MemorySegment preMulMat2x3_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _otherBase = other.address() + otherOffset;
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_otherBase + 8L)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_otherBase + 24L)).mul(_sv1).add(_sv2.withLane(3, UnsafeOpsHolder.U.getDouble(_otherBase + 40L))));
        var _c2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder());
        DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_otherBase)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_otherBase + 16L)).mul(_sv1).add(_sv2.withLane(3, UnsafeOpsHolder.U.getDouble(_otherBase + 32L)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat2x3_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 8L)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 24L)).mul(_sv1).add(_sv2.withLane(3, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 40L))));
        var _c2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder());
        DoubleVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 16L)).mul(_sv1).add(_sv2.withLane(3, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 32L)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static double[] preMulMat3x3(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        if (SimdSupport.USE_FMA) return preMulMat3x3_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat3x3_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static double[] preMulMat3x3_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset + 7]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset + 1]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset + 4]).mul(_sv2)));
        var _c2 = DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset + 8]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset + 2]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset + 5]).mul(_sv2)));
        DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset + 6]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset + 3]).mul(_sv2))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preMulMat3x3_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset + 7]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset + 1]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset + 4]).mul(_sv2)));
        var _c2 = DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset + 8]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset + 2]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset + 5]).mul(_sv2)));
        DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset + 6]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, other[otherOffset + 3]).mul(_sv2))).intoArray(dest, destOffset);
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
        preMulMat3x3_fma_unsafe_s4246b911_v(dest, destOffset, src, srcOffset, UnsafeOpsHolder.U.getDouble(_otherBase + 48L), UnsafeOpsHolder.U.getDouble(_otherBase), UnsafeOpsHolder.U.getDouble(_otherBase + 24L), UnsafeOpsHolder.U.getDouble(_otherBase + 56L), UnsafeOpsHolder.U.getDouble(_otherBase + 8L), UnsafeOpsHolder.U.getDouble(_otherBase + 32L), UnsafeOpsHolder.U.getDouble(_otherBase + 64L), UnsafeOpsHolder.U.getDouble(_otherBase + 16L), UnsafeOpsHolder.U.getDouble(_otherBase + 40L));
        return dest;
    }

    private static void preMulMat3x3_fma_unsafe_s4246b911_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        DoubleVector.broadcast(SIMD_SPECIES, _h0).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, _h1).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, _h2).mul(_sv2))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        DoubleVector.broadcast(SIMD_SPECIES, _h3).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, _h4).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, _h5).mul(_sv2))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        DoubleVector.broadcast(SIMD_SPECIES, _h6).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, _h7).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, _h8).mul(_sv2))).intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment preMulMat3x3_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        preMulMat3x3_fma_unsafe_s4246b911_v(dest, destOffset, src, srcOffset, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 48L), other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset), other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 24L), other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 56L), other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 8L), other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 32L), other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 64L), other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 16L), other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 40L));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat3x3_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && other.isNative()) return preMulMat3x3_mulAdd_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat3x3_mulAdd_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.lang.foreign.MemorySegment preMulMat3x3_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _otherBase = other.address() + otherOffset;
        preMulMat3x3_mulAdd_unsafe_sbc16c30_v(dest, destOffset, src, srcOffset, UnsafeOpsHolder.U.getDouble(_otherBase + 48L), UnsafeOpsHolder.U.getDouble(_otherBase), UnsafeOpsHolder.U.getDouble(_otherBase + 24L), UnsafeOpsHolder.U.getDouble(_otherBase + 56L), UnsafeOpsHolder.U.getDouble(_otherBase + 8L), UnsafeOpsHolder.U.getDouble(_otherBase + 32L), UnsafeOpsHolder.U.getDouble(_otherBase + 64L), UnsafeOpsHolder.U.getDouble(_otherBase + 16L), UnsafeOpsHolder.U.getDouble(_otherBase + 40L));
        return dest;
    }

    private static void preMulMat3x3_mulAdd_unsafe_sbc16c30_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        DoubleVector.broadcast(SIMD_SPECIES, _h0).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, _h1).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, _h2).mul(_sv2))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        DoubleVector.broadcast(SIMD_SPECIES, _h3).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, _h4).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, _h5).mul(_sv2))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        DoubleVector.broadcast(SIMD_SPECIES, _h6).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, _h7).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, _h8).mul(_sv2))).intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment preMulMat3x3_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        preMulMat3x3_mulAdd_unsafe_sbc16c30_v(dest, destOffset, src, srcOffset, other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 48L), other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset), other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 24L), other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 56L), other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 8L), other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 32L), other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 64L), other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 16L), other.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, otherOffset + 40L));
        return dest;
    }

    public static double[] preMulMat4x4(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        if (SimdSupport.USE_FMA) return preMulMat4x4_fma(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat4x4_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static double[] preMulMat4x4_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        preMulMat4x4_fma_s1657eee8_v(dest, destOffset, other, otherOffset, src[srcOffset + 8], src[srcOffset], src[srcOffset + 4], src[srcOffset + 9], src[srcOffset + 1], src[srcOffset + 5], src[srcOffset + 10], src[srcOffset + 2], src[srcOffset + 6], src[srcOffset + 3], src[srcOffset + 7], src[srcOffset + 11]);
        return dest;
    }

    private static void preMulMat4x4_fma_s1657eee8_v(double[] dest, int destOffset, double[] other, int otherOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8, double _h9, double _h10, double _h11) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset + 4);
        var _c3 = _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _h9), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, _h10), _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _h11), DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset + 12))));
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _h0), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _h1), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h2)))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _h3), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _h4), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h5)))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _h6), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _h7), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h8)))).intoArray(dest, destOffset + 8);
        _c3.intoArray(dest, destOffset + 12);
    }

    public static double[] preMulMat4x4_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        preMulMat4x4_mulAdd_sdb6cce97_v(dest, destOffset, other, otherOffset, src[srcOffset + 8], src[srcOffset], src[srcOffset + 4], src[srcOffset + 9], src[srcOffset + 1], src[srcOffset + 5], src[srcOffset + 10], src[srcOffset + 2], src[srcOffset + 6], src[srcOffset + 3], src[srcOffset + 7], src[srcOffset + 11]);
        return dest;
    }

    private static void preMulMat4x4_mulAdd_sdb6cce97_v(double[] dest, int destOffset, double[] other, int otherOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8, double _h9, double _h10, double _h11) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset + 4);
        var _c3 = _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h9)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h10)).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h11)).add(DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset + 12))));
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h0)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h1)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h2)))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h3)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h4)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h5)))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h6)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h7)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h8)))).intoArray(dest, destOffset + 8);
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
        preMulMat4x4_fma_unsafe_sceb040a9_v(dest, destOffset, other, otherOffset, UnsafeOpsHolder.U.getDouble(_srcBase + 64L), UnsafeOpsHolder.U.getDouble(_srcBase), UnsafeOpsHolder.U.getDouble(_srcBase + 32L), UnsafeOpsHolder.U.getDouble(_srcBase + 72L), UnsafeOpsHolder.U.getDouble(_srcBase + 8L), UnsafeOpsHolder.U.getDouble(_srcBase + 40L), UnsafeOpsHolder.U.getDouble(_srcBase + 80L), UnsafeOpsHolder.U.getDouble(_srcBase + 16L), UnsafeOpsHolder.U.getDouble(_srcBase + 48L), UnsafeOpsHolder.U.getDouble(_srcBase + 24L), UnsafeOpsHolder.U.getDouble(_srcBase + 56L), UnsafeOpsHolder.U.getDouble(_srcBase + 88L));
        return dest;
    }

    private static void preMulMat4x4_fma_unsafe_sceb040a9_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment other, long otherOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8, double _h9, double _h10, double _h11) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + 64L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _c3 = _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _h9), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, _h10), _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _h11), DoubleVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + 96L, java.nio.ByteOrder.nativeOrder()))));
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _h0), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _h1), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h2)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _h3), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _h4), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h5)))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _h6), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _h7), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h8)))).intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        _c3.intoMemorySegment(dest, destOffset + 96L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment preMulMat4x4_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        preMulMat4x4_fma_unsafe_sceb040a9_v(dest, destOffset, other, otherOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 72L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 80L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 88L));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat4x4_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return preMulMat4x4_mulAdd_unsafe(dest, destOffset, src, srcOffset, other, otherOffset);
        return preMulMat4x4_mulAdd_api(dest, destOffset, src, srcOffset, other, otherOffset);
    }

    public static java.lang.foreign.MemorySegment preMulMat4x4_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        long _srcBase = src.address() + srcOffset;
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + 64L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _c1 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_srcBase + 72L))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_srcBase + 8L))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_srcBase + 40L)))));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_srcBase + 80L))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_srcBase + 16L))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_srcBase + 48L)))));
        var _c3 = _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_srcBase + 24L))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_srcBase + 56L))).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_srcBase + 88L))).add(DoubleVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + 96L, java.nio.ByteOrder.nativeOrder()))));
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_srcBase + 64L))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_srcBase))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_srcBase + 32L))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        _c3.intoMemorySegment(dest, destOffset + 96L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preMulMat4x4_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + 64L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _c1 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 72L))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L)))));
        var _c2 = _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 80L))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L)))));
        var _c3 = _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L))).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 88L))).add(DoubleVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + 96L, java.nio.ByteOrder.nativeOrder()))));
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        _c3.intoMemorySegment(dest, destOffset + 96L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static double[] addScaled(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double weight) {
        if (SimdSupport.USE_FMA) return addScaled_fma(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return addScaled_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    public static double[] addScaled_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double weight) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.broadcast(SIMD_SPECIES, weight).fma(DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset + _li * 4), DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] addScaled_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset, double weight) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.broadcast(SIMD_SPECIES, weight).mul(DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset + _li * 4)).add(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double weight) {
        if (SimdSupport.USE_FMA) return addScaled_fma(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return addScaled_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    public static java.lang.foreign.MemorySegment addScaled_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double weight) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.broadcast(SIMD_SPECIES, weight).fma(DoubleVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + _li * 32L, java.nio.ByteOrder.nativeOrder()), DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 32L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double weight) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.broadcast(SIMD_SPECIES, weight).mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset + _li * 32L, java.nio.ByteOrder.nativeOrder())).add(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 32L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static double[] composeTRSMul(double[] dest, int destOffset, double[] m, int mOffset, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ) {
        if (SimdSupport.USE_FMA) return composeTRSMul_fma(dest, destOffset, m, mOffset, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
        return composeTRSMul_mulAdd(dest, destOffset, m, mOffset, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
    }

    public static double[] composeTRSMul_fma(double[] dest, int destOffset, double[] m, int mOffset, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ) {
        double _t0 = scaleZ + scaleZ;
        double _t1 = scaleX + scaleX;
        double _t2 = scaleY + scaleY;
        double _t3 = rotationY * rotationW;
        double _t4 = rotationZ * rotationZ;
        double _t5 = rotationZ * rotationW;
        composeTRSMul_fma_s8387ac18_v(dest, destOffset, m, mOffset, translationX, translationY, translationZ, Math.fma(rotationX, rotationZ, _t3) * _t0, Math.fma(rotationX, rotationY, -_t5) * _t2, Math.fma(-Math.fma(rotationY, rotationY, _t4), _t1, scaleX), Math.fma(rotationX, rotationY, _t5) * _t1, Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t0, Math.fma(-Math.fma(rotationX, rotationX, _t4), _t2, scaleY), Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t2, Math.fma(rotationX, rotationZ, -_t3) * _t1, Math.fma(-Math.fma(rotationX, rotationX, rotationY * rotationY), _t0, scaleZ));
        return dest;
    }

    private static void composeTRSMul_fma_s8387ac18_v(double[] dest, int destOffset, double[] m, int mOffset, double translationX, double translationY, double translationZ, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, m, mOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, m, mOffset + 4);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, m, mOffset);
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _h0), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _h1), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, _h2), _sv3.withLane(3, translationX)))).intoArray(dest, destOffset);
        _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, _h3), _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _h4), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _h5), _sv3.withLane(3, translationY)))).intoArray(dest, destOffset + 4);
        _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _h6), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, _h7), _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _h8), _sv3.withLane(3, translationZ)))).intoArray(dest, destOffset + 8);
    }

    public static double[] composeTRSMul_mulAdd(double[] dest, int destOffset, double[] m, int mOffset, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ) {
        double _t0 = scaleZ + scaleZ;
        double _t1 = scaleX + scaleX;
        double _t2 = scaleY + scaleY;
        double _t3 = rotationY * rotationW;
        double _t4 = rotationZ * rotationZ;
        double _t5 = rotationZ * rotationW;
        composeTRSMul_mulAdd_s3467d619_v(dest, destOffset, m, mOffset, translationX, translationY, translationZ, Math.fma(rotationX, rotationZ, _t3) * _t0, Math.fma(rotationX, rotationY, -_t5) * _t2, Math.fma(-Math.fma(rotationY, rotationY, _t4), _t1, scaleX), Math.fma(rotationX, rotationY, _t5) * _t1, Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t0, Math.fma(-Math.fma(rotationX, rotationX, _t4), _t2, scaleY), Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t2, Math.fma(rotationX, rotationZ, -_t3) * _t1, Math.fma(-Math.fma(rotationX, rotationX, rotationY * rotationY), _t0, scaleZ));
        return dest;
    }

    private static void composeTRSMul_mulAdd_s3467d619_v(double[] dest, int destOffset, double[] m, int mOffset, double translationX, double translationY, double translationZ, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, m, mOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, m, mOffset + 4);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, m, mOffset);
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h0)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h1)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h2)).add(_sv3.withLane(3, translationX)))).intoArray(dest, destOffset);
        _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h3)).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h4)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h5)).add(_sv3.withLane(3, translationY)))).intoArray(dest, destOffset + 4);
        _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h6)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h7)).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h8)).add(_sv3.withLane(3, translationZ)))).intoArray(dest, destOffset + 8);
    }

    public static java.lang.foreign.MemorySegment composeTRSMul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ) {
        if (SimdSupport.USE_FMA) return composeTRSMul_fma(dest, destOffset, m, mOffset, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
        return composeTRSMul_mulAdd(dest, destOffset, m, mOffset, translationX, translationY, translationZ, rotationX, rotationY, rotationZ, rotationW, scaleX, scaleY, scaleZ);
    }

    public static java.lang.foreign.MemorySegment composeTRSMul_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ) {
        double _t0 = scaleZ + scaleZ;
        double _t1 = scaleX + scaleX;
        double _t2 = scaleY + scaleY;
        double _t3 = rotationY * rotationW;
        double _t4 = rotationZ * rotationZ;
        double _t5 = rotationZ * rotationW;
        composeTRSMul_fma_s2207efa4_v(dest, destOffset, m, mOffset, translationX, translationY, translationZ, Math.fma(rotationX, rotationZ, _t3) * _t0, Math.fma(rotationX, rotationY, -_t5) * _t2, Math.fma(-Math.fma(rotationY, rotationY, _t4), _t1, scaleX), Math.fma(rotationX, rotationY, _t5) * _t1, Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t0, Math.fma(-Math.fma(rotationX, rotationX, _t4), _t2, scaleY), Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t2, Math.fma(rotationX, rotationZ, -_t3) * _t1, Math.fma(-Math.fma(rotationX, rotationX, rotationY * rotationY), _t0, scaleZ));
        return dest;
    }

    private static void composeTRSMul_fma_s2207efa4_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset, double translationX, double translationY, double translationZ, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, m, mOffset + 64L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, m, mOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, m, mOffset, java.nio.ByteOrder.nativeOrder());
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _h0), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _h1), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, _h2), _sv3.withLane(3, translationX)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, _h3), _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _h4), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _h5), _sv3.withLane(3, translationY)))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _h6), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, _h7), _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _h8), _sv3.withLane(3, translationZ)))).intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment composeTRSMul_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset, double translationX, double translationY, double translationZ, double rotationX, double rotationY, double rotationZ, double rotationW, double scaleX, double scaleY, double scaleZ) {
        double _t0 = scaleZ + scaleZ;
        double _t1 = scaleX + scaleX;
        double _t2 = scaleY + scaleY;
        double _t3 = rotationY * rotationW;
        double _t4 = rotationZ * rotationZ;
        double _t5 = rotationZ * rotationW;
        composeTRSMul_mulAdd_s1f162f59_v(dest, destOffset, m, mOffset, translationX, translationY, translationZ, Math.fma(rotationX, rotationZ, _t3) * _t0, Math.fma(rotationX, rotationY, -_t5) * _t2, Math.fma(-Math.fma(rotationY, rotationY, _t4), _t1, scaleX), Math.fma(rotationX, rotationY, _t5) * _t1, Math.fma(rotationY, rotationZ, -(rotationX * rotationW)) * _t0, Math.fma(-Math.fma(rotationX, rotationX, _t4), _t2, scaleY), Math.fma(rotationX, rotationW, rotationY * rotationZ) * _t2, Math.fma(rotationX, rotationZ, -_t3) * _t1, Math.fma(-Math.fma(rotationX, rotationX, rotationY * rotationY), _t0, scaleZ));
        return dest;
    }

    private static void composeTRSMul_mulAdd_s1f162f59_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset, double translationX, double translationY, double translationZ, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, m, mOffset + 64L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, m, mOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, m, mOffset, java.nio.ByteOrder.nativeOrder());
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h0)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h1)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h2)).add(_sv3.withLane(3, translationX)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h3)).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h4)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h5)).add(_sv3.withLane(3, translationY)))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h6)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h7)).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h8)).add(_sv3.withLane(3, translationZ)))).intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
    }

    public static double[] composeTRSMul(double[] dest, int destOffset, double[] translation, int translationOffset, double[] rotation, int rotationOffset, double[] scale, int scaleOffset, double[] m, int mOffset) {
        if (SimdSupport.USE_FMA) return composeTRSMul_fma(dest, destOffset, translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, m, mOffset);
        return composeTRSMul_mulAdd(dest, destOffset, translation, translationOffset, rotation, rotationOffset, scale, scaleOffset, m, mOffset);
    }

    public static double[] composeTRSMul_fma(double[] dest, int destOffset, double[] translation, int translationOffset, double[] rotation, int rotationOffset, double[] scale, int scaleOffset, double[] m, int mOffset) {
        double _scalez = scale[scaleOffset + 2];
        double _scalex = scale[scaleOffset];
        double _scaley = scale[scaleOffset + 1];
        double _rotationy = rotation[rotationOffset + 1];
        double _rotationw = rotation[rotationOffset + 3];
        double _rotationz = rotation[rotationOffset + 2];
        double _rotationx = rotation[rotationOffset];
        double _t0 = _scalez + _scalez;
        double _t1 = _scalex + _scalex;
        double _t2 = _scaley + _scaley;
        double _t3 = _rotationy * _rotationw;
        double _t4 = _rotationz * _rotationz;
        double _t5 = _rotationz * _rotationw;
        composeTRSMul_fma_s712b2612_v(dest, destOffset, m, mOffset, Math.fma(_rotationx, _rotationz, _t3) * _t0, Math.fma(_rotationx, _rotationy, -_t5) * _t2, Math.fma(-Math.fma(_rotationy, _rotationy, _t4), _t1, _scalex), translation[translationOffset], Math.fma(_rotationx, _rotationy, _t5) * _t1, Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t0, Math.fma(-Math.fma(_rotationx, _rotationx, _t4), _t2, _scaley), translation[translationOffset + 1], Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t2, Math.fma(_rotationx, _rotationz, -_t3) * _t1, Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t0, _scalez), translation[translationOffset + 2]);
        return dest;
    }

    private static void composeTRSMul_fma_s712b2612_v(double[] dest, int destOffset, double[] m, int mOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8, double _h9, double _h10, double _h11) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, m, mOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, m, mOffset + 4);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, m, mOffset);
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _h0), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _h1), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, _h2), _sv3.withLane(3, _h3)))).intoArray(dest, destOffset);
        _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, _h4), _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _h5), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _h6), _sv3.withLane(3, _h7)))).intoArray(dest, destOffset + 4);
        _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _h8), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, _h9), _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _h10), _sv3.withLane(3, _h11)))).intoArray(dest, destOffset + 8);
    }

    public static double[] composeTRSMul_mulAdd(double[] dest, int destOffset, double[] translation, int translationOffset, double[] rotation, int rotationOffset, double[] scale, int scaleOffset, double[] m, int mOffset) {
        double _scalez = scale[scaleOffset + 2];
        double _scalex = scale[scaleOffset];
        double _scaley = scale[scaleOffset + 1];
        double _rotationy = rotation[rotationOffset + 1];
        double _rotationw = rotation[rotationOffset + 3];
        double _rotationz = rotation[rotationOffset + 2];
        double _rotationx = rotation[rotationOffset];
        double _t0 = _scalez + _scalez;
        double _t1 = _scalex + _scalex;
        double _t2 = _scaley + _scaley;
        double _t3 = _rotationy * _rotationw;
        double _t4 = _rotationz * _rotationz;
        double _t5 = _rotationz * _rotationw;
        composeTRSMul_mulAdd_s13f9a605_v(dest, destOffset, m, mOffset, Math.fma(_rotationx, _rotationz, _t3) * _t0, Math.fma(_rotationx, _rotationy, -_t5) * _t2, Math.fma(-Math.fma(_rotationy, _rotationy, _t4), _t1, _scalex), translation[translationOffset], Math.fma(_rotationx, _rotationy, _t5) * _t1, Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t0, Math.fma(-Math.fma(_rotationx, _rotationx, _t4), _t2, _scaley), translation[translationOffset + 1], Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t2, Math.fma(_rotationx, _rotationz, -_t3) * _t1, Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t0, _scalez), translation[translationOffset + 2]);
        return dest;
    }

    private static void composeTRSMul_mulAdd_s13f9a605_v(double[] dest, int destOffset, double[] m, int mOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8, double _h9, double _h10, double _h11) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, m, mOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, m, mOffset + 4);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, m, mOffset);
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h0)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h1)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h2)).add(_sv3.withLane(3, _h3)))).intoArray(dest, destOffset);
        _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h4)).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h5)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h6)).add(_sv3.withLane(3, _h7)))).intoArray(dest, destOffset + 4);
        _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h8)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h9)).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h10)).add(_sv3.withLane(3, _h11)))).intoArray(dest, destOffset + 8);
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
        double _scalez = UnsafeOpsHolder.U.getDouble(_scaleBase + 16L);
        double _scalex = UnsafeOpsHolder.U.getDouble(_scaleBase);
        double _scaley = UnsafeOpsHolder.U.getDouble(_scaleBase + 8L);
        double _rotationy = UnsafeOpsHolder.U.getDouble(_rotationBase + 8L);
        double _rotationw = UnsafeOpsHolder.U.getDouble(_rotationBase + 24L);
        double _rotationz = UnsafeOpsHolder.U.getDouble(_rotationBase + 16L);
        double _rotationx = UnsafeOpsHolder.U.getDouble(_rotationBase);
        return composeTRSMul_fma_unsafe_sddd6f2c5_1(dest, destOffset, m, mOffset, _translationBase, _scalez, _scalex, _scaley, _rotationy, _rotationw, _rotationz, _rotationx, _scalez + _scalez, _scalex + _scalex, _scaley + _scaley, _rotationy * _rotationw, _rotationz * _rotationz, _rotationz * _rotationw);
    }

    /** Piece 2 of {@code composeTRSMul_fma_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment composeTRSMul_fma_unsafe_sddd6f2c5_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset, long _translationBase, double _scalez, double _scalex, double _scaley, double _rotationy, double _rotationw, double _rotationz, double _rotationx, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5) {
        composeTRSMul_fma_unsafe_s1a7baa51_v(dest, destOffset, m, mOffset, Math.fma(_rotationx, _rotationz, _t3) * _t0, Math.fma(_rotationx, _rotationy, -_t5) * _t2, Math.fma(-Math.fma(_rotationy, _rotationy, _t4), _t1, _scalex), UnsafeOpsHolder.U.getDouble(_translationBase), Math.fma(_rotationx, _rotationy, _t5) * _t1, Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t0, Math.fma(-Math.fma(_rotationx, _rotationx, _t4), _t2, _scaley), UnsafeOpsHolder.U.getDouble(_translationBase + 8L), Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t2, Math.fma(_rotationx, _rotationz, -_t3) * _t1, Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t0, _scalez), UnsafeOpsHolder.U.getDouble(_translationBase + 16L));
        return dest;
    }

    private static void composeTRSMul_fma_unsafe_s1a7baa51_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8, double _h9, double _h10, double _h11) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, m, mOffset + 64L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, m, mOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, m, mOffset, java.nio.ByteOrder.nativeOrder());
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _h0), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _h1), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, _h2), _sv3.withLane(3, _h3)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, _h4), _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _h5), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _h6), _sv3.withLane(3, _h7)))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _h8), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, _h9), _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _h10), _sv3.withLane(3, _h11)))).intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment composeTRSMul_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment translation, long translationOffset, java.lang.foreign.MemorySegment rotation, long rotationOffset, java.lang.foreign.MemorySegment scale, long scaleOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        double _scalez = scale.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, scaleOffset + 16L);
        double _scalex = scale.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, scaleOffset);
        double _scaley = scale.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, scaleOffset + 8L);
        double _rotationy = rotation.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rotationOffset + 8L);
        double _rotationw = rotation.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rotationOffset + 24L);
        double _rotationz = rotation.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rotationOffset + 16L);
        double _rotationx = rotation.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rotationOffset);
        return composeTRSMul_fma_api_se6d0489b_1(dest, destOffset, translation, translationOffset, m, mOffset, _scalez, _scalex, _scaley, _rotationy, _rotationw, _rotationz, _rotationx, _scalez + _scalez, _scalex + _scalex, _scaley + _scaley, _rotationy * _rotationw, _rotationz * _rotationz, _rotationz * _rotationw);
    }

    /** Piece 2 of {@code composeTRSMul_fma_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment composeTRSMul_fma_api_se6d0489b_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment translation, long translationOffset, java.lang.foreign.MemorySegment m, long mOffset, double _scalez, double _scalex, double _scaley, double _rotationy, double _rotationw, double _rotationz, double _rotationx, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5) {
        composeTRSMul_fma_unsafe_s1a7baa51_v(dest, destOffset, m, mOffset, Math.fma(_rotationx, _rotationz, _t3) * _t0, Math.fma(_rotationx, _rotationy, -_t5) * _t2, Math.fma(-Math.fma(_rotationy, _rotationy, _t4), _t1, _scalex), translation.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, translationOffset), Math.fma(_rotationx, _rotationy, _t5) * _t1, Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t0, Math.fma(-Math.fma(_rotationx, _rotationx, _t4), _t2, _scaley), translation.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, translationOffset + 8L), Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t2, Math.fma(_rotationx, _rotationz, -_t3) * _t1, Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t0, _scalez), translation.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, translationOffset + 16L));
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
        double _scalez = UnsafeOpsHolder.U.getDouble(_scaleBase + 16L);
        double _scalex = UnsafeOpsHolder.U.getDouble(_scaleBase);
        double _scaley = UnsafeOpsHolder.U.getDouble(_scaleBase + 8L);
        double _rotationy = UnsafeOpsHolder.U.getDouble(_rotationBase + 8L);
        double _rotationw = UnsafeOpsHolder.U.getDouble(_rotationBase + 24L);
        double _rotationz = UnsafeOpsHolder.U.getDouble(_rotationBase + 16L);
        double _rotationx = UnsafeOpsHolder.U.getDouble(_rotationBase);
        return composeTRSMul_mulAdd_unsafe_sd50414c8_1(dest, destOffset, m, mOffset, _translationBase, _scalez, _scalex, _scaley, _rotationy, _rotationw, _rotationz, _rotationx, _scalez + _scalez, _scalex + _scalex, _scaley + _scaley, _rotationy * _rotationw, _rotationz * _rotationz, _rotationz * _rotationw);
    }

    /** Piece 2 of {@code composeTRSMul_mulAdd_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment composeTRSMul_mulAdd_unsafe_sd50414c8_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset, long _translationBase, double _scalez, double _scalex, double _scaley, double _rotationy, double _rotationw, double _rotationz, double _rotationx, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5) {
        composeTRSMul_mulAdd_unsafe_sac5793f0_v(dest, destOffset, m, mOffset, Math.fma(_rotationx, _rotationz, _t3) * _t0, Math.fma(_rotationx, _rotationy, -_t5) * _t2, Math.fma(-Math.fma(_rotationy, _rotationy, _t4), _t1, _scalex), UnsafeOpsHolder.U.getDouble(_translationBase), Math.fma(_rotationx, _rotationy, _t5) * _t1, Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t0, Math.fma(-Math.fma(_rotationx, _rotationx, _t4), _t2, _scaley), UnsafeOpsHolder.U.getDouble(_translationBase + 8L), Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t2, Math.fma(_rotationx, _rotationz, -_t3) * _t1, Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t0, _scalez), UnsafeOpsHolder.U.getDouble(_translationBase + 16L));
        return dest;
    }

    private static void composeTRSMul_mulAdd_unsafe_sac5793f0_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment m, long mOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8, double _h9, double _h10, double _h11) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, m, mOffset + 64L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, m, mOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, m, mOffset, java.nio.ByteOrder.nativeOrder());
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h0)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h1)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h2)).add(_sv3.withLane(3, _h3)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h4)).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h5)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h6)).add(_sv3.withLane(3, _h7)))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h8)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h9)).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h10)).add(_sv3.withLane(3, _h11)))).intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment composeTRSMul_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment translation, long translationOffset, java.lang.foreign.MemorySegment rotation, long rotationOffset, java.lang.foreign.MemorySegment scale, long scaleOffset, java.lang.foreign.MemorySegment m, long mOffset) {
        double _scalez = scale.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, scaleOffset + 16L);
        double _scalex = scale.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, scaleOffset);
        double _scaley = scale.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, scaleOffset + 8L);
        double _rotationy = rotation.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rotationOffset + 8L);
        double _rotationw = rotation.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rotationOffset + 24L);
        double _rotationz = rotation.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rotationOffset + 16L);
        double _rotationx = rotation.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rotationOffset);
        return composeTRSMul_mulAdd_api_sc3df1f58_1(dest, destOffset, translation, translationOffset, m, mOffset, _scalez, _scalex, _scaley, _rotationy, _rotationw, _rotationz, _rotationx, _scalez + _scalez, _scalex + _scalex, _scaley + _scaley, _rotationy * _rotationw, _rotationz * _rotationz, _rotationz * _rotationw);
    }

    /** Piece 2 of {@code composeTRSMul_mulAdd_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment composeTRSMul_mulAdd_api_sc3df1f58_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment translation, long translationOffset, java.lang.foreign.MemorySegment m, long mOffset, double _scalez, double _scalex, double _scaley, double _rotationy, double _rotationw, double _rotationz, double _rotationx, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5) {
        composeTRSMul_mulAdd_unsafe_sac5793f0_v(dest, destOffset, m, mOffset, Math.fma(_rotationx, _rotationz, _t3) * _t0, Math.fma(_rotationx, _rotationy, -_t5) * _t2, Math.fma(-Math.fma(_rotationy, _rotationy, _t4), _t1, _scalex), translation.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, translationOffset), Math.fma(_rotationx, _rotationy, _t5) * _t1, Math.fma(_rotationy, _rotationz, -(_rotationx * _rotationw)) * _t0, Math.fma(-Math.fma(_rotationx, _rotationx, _t4), _t2, _scaley), translation.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, translationOffset + 8L), Math.fma(_rotationx, _rotationw, _rotationy * _rotationz) * _t2, Math.fma(_rotationx, _rotationz, -_t3) * _t1, Math.fma(-Math.fma(_rotationx, _rotationx, _rotationy * _rotationy), _t0, _scalez), translation.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, translationOffset + 16L));
        return dest;
    }

    public static double[] lookAlong(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (SimdSupport.USE_FMA) return lookAlong_fma(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_mulAdd(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static double[] lookAlong_fma(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        double _t18 = Math.fma(_t17, _t12, upX);
        double _t19 = Math.fma(_t17, _t13, upY);
        double _t20 = Math.fma(_t17, _t11, upZ);
        double _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        lookAlong_fma_sd42ad6ea_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    private static void lookAlong_fma_sd42ad6ea_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t37).withLane(1, Math.fma(_t39, _t12, -(_t38 * _t13))).withLane(2, _t11);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t38).withLane(1, Math.fma(_t37, _t13, -(_t39 * _t11))).withLane(2, _t12);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t39).withLane(1, Math.fma(_t38, _t11, -(_t37 * _t12))).withLane(2, _t13);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, src[_lo0]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] lookAlong_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        double _t18 = Math.fma(_t17, _t12, upX);
        double _t19 = Math.fma(_t17, _t13, upY);
        double _t20 = Math.fma(_t17, _t11, upZ);
        double _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        lookAlong_mulAdd_s7aa6e3f_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    private static void lookAlong_mulAdd_s7aa6e3f_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t37).withLane(1, Math.fma(_t39, _t12, -(_t38 * _t13))).withLane(2, _t11);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t38).withLane(1, Math.fma(_t37, _t13, -(_t39 * _t11))).withLane(2, _t12);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t39).withLane(1, Math.fma(_t38, _t11, -(_t37 * _t12))).withLane(2, _t13);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static java.lang.foreign.MemorySegment lookAlong(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return lookAlong_fma_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
            return lookAlong_fma_api(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return lookAlong_mulAdd_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_mulAdd_api(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.lang.foreign.MemorySegment lookAlong_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return lookAlong_fma_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_fma_api(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.lang.foreign.MemorySegment lookAlong_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        double _t18 = Math.fma(_t17, _t12, upX);
        double _t19 = Math.fma(_t17, _t13, upY);
        double _t20 = Math.fma(_t17, _t11, upZ);
        double _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        lookAlong_fma_unsafe_s34521037_v(dest, destOffset, src.address() + srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    private static void lookAlong_fma_unsafe_s34521037_v(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t37).withLane(1, Math.fma(_t39, _t12, -(_t38 * _t13))).withLane(2, _t11);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t38).withLane(1, Math.fma(_t37, _t13, -(_t39 * _t11))).withLane(2, _t12);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t39).withLane(1, Math.fma(_t38, _t11, -(_t37 * _t12))).withLane(2, _t13);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 16L)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1)).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L)).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment lookAlong_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        double _t18 = Math.fma(_t17, _t12, upX);
        double _t19 = Math.fma(_t17, _t13, upY);
        double _t20 = Math.fma(_t17, _t11, upZ);
        double _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        lookAlong_fma_api_s4367997_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    private static void lookAlong_fma_api_s4367997_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t37).withLane(1, Math.fma(_t39, _t12, -(_t38 * _t13))).withLane(2, _t11);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t38).withLane(1, Math.fma(_t37, _t13, -(_t39 * _t11))).withLane(2, _t12);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t39).withLane(1, Math.fma(_t38, _t11, -(_t37 * _t12))).withLane(2, _t13);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L)).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment lookAlong_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return lookAlong_mulAdd_unsafe(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
        return lookAlong_mulAdd_api(dest, destOffset, src, srcOffset, dirX, dirY, dirZ, upX, upY, upZ);
    }

    public static java.lang.foreign.MemorySegment lookAlong_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        double _t18 = Math.fma(_t17, _t12, upX);
        double _t19 = Math.fma(_t17, _t13, upY);
        double _t20 = Math.fma(_t17, _t11, upZ);
        double _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        lookAlong_mulAdd_unsafe_sf050eec0_v(dest, destOffset, src.address() + srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    private static void lookAlong_mulAdd_unsafe_sf050eec0_v(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t37).withLane(1, Math.fma(_t39, _t12, -(_t38 * _t13))).withLane(2, _t11);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t38).withLane(1, Math.fma(_t37, _t13, -(_t39 * _t11))).withLane(2, _t12);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t39).withLane(1, Math.fma(_t38, _t11, -(_t37 * _t12))).withLane(2, _t13);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 16L)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1)).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L)).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment lookAlong_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double dirX, double dirY, double dirZ, double upX, double upY, double upZ) {
        double _t4 = Math.fma(dirZ, dirZ, Math.fma(dirX, dirX, dirY * dirY));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = dirZ * _t6;
            _t12 = dirX * _t6;
            _t13 = dirY * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -Math.fma(upZ, _t11, Math.fma(upX, _t12, upY * _t13));
        double _t18 = Math.fma(_t17, _t12, upX);
        double _t19 = Math.fma(_t17, _t13, upY);
        double _t20 = Math.fma(_t17, _t11, upZ);
        double _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        lookAlong_mulAdd_api_s908bf4e_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    private static void lookAlong_mulAdd_api_s908bf4e_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _t11, double _t12, double _t13, double _t37, double _t38, double _t39) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t37).withLane(1, Math.fma(_t39, _t12, -(_t38 * _t13))).withLane(2, _t11);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t38).withLane(1, Math.fma(_t37, _t13, -(_t39 * _t11))).withLane(2, _t12);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t39).withLane(1, Math.fma(_t38, _t11, -(_t37 * _t12))).withLane(2, _t13);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L)).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static double[] lookAlong(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        if (SimdSupport.USE_FMA) return lookAlong_fma(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_mulAdd(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static double[] lookAlong_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        double _dirz = dir[dirOffset + 2];
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = Math.fma(_t17, _t12, _upx);
        double _t19 = Math.fma(_t17, _t13, _upy);
        double _t20 = Math.fma(_t17, _t11, _upz);
        double _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        return lookAlong_fma_sb09462de_1(dest, destOffset, src, srcOffset, _upz, _upx, _upy, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code lookAlong_fma}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_fma_sb09462de_1(double[] dest, int destOffset, double[] src, int srcOffset, double _upz, double _upx, double _upy, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        lookAlong_fma_sd42ad6ea_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static double[] lookAlong_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] dir, int dirOffset, double[] up, int upOffset) {
        double _dirz = dir[dirOffset + 2];
        double _dirx = dir[dirOffset];
        double _diry = dir[dirOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = Math.fma(_t17, _t12, _upx);
        double _t19 = Math.fma(_t17, _t13, _upy);
        double _t20 = Math.fma(_t17, _t11, _upz);
        double _t27 = Math.fma(_t18, _t13, -(_t19 * _t12));
        double _t28 = Math.fma(_t19, _t11, -(_t20 * _t13));
        double _t29 = Math.fma(_t20, _t12, -(_t18 * _t11));
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        return lookAlong_mulAdd_s3749b5ed_1(dest, destOffset, src, srcOffset, _upz, _upx, _upy, _t11, _t12, _t13, _t27, _t28, _t29, _t32, (1.0 / java.lang.Math.sqrt(_t32)));
    }

    /** Piece 2 of {@code lookAlong_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] lookAlong_mulAdd_s3749b5ed_1(double[] dest, int destOffset, double[] src, int srcOffset, double _upz, double _upx, double _upy, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29, double _t32, double _t33) {
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        lookAlong_mulAdd_s7aa6e3f_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
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
        double _dirz = UnsafeOpsHolder.U.getDouble(_dirBase + 16L);
        double _dirx = UnsafeOpsHolder.U.getDouble(_dirBase);
        double _diry = UnsafeOpsHolder.U.getDouble(_dirBase + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(_upBase + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(_upBase);
        double _upy = UnsafeOpsHolder.U.getDouble(_upBase + 8L);
        double _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = Math.fma(_t17, _t12, _upx);
        double _t19 = Math.fma(_t17, _t13, _upy);
        double _t20 = Math.fma(_t17, _t11, _upz);
        return lookAlong_fma_unsafe_s6c31f8d7_1(dest, destOffset, src, srcOffset, _upz, _upx, _upy, _t11, _t12, _t13, Math.fma(_t18, _t13, -(_t19 * _t12)), Math.fma(_t19, _t11, -(_t20 * _t13)), Math.fma(_t20, _t12, -(_t18 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_fma_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_fma_unsafe_s6c31f8d7_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _upz, double _upx, double _upy, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29) {
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        lookAlong_fma_unsafe_s34521037_v(dest, destOffset, src.address() + srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        double _dirz = dir.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, dirOffset + 16L);
        double _dirx = dir.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, dirOffset);
        double _diry = dir.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, dirOffset + 8L);
        double _upz = up.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, upOffset + 16L);
        double _upx = up.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, upOffset);
        double _upy = up.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, upOffset + 8L);
        double _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = Math.fma(_t17, _t12, _upx);
        double _t19 = Math.fma(_t17, _t13, _upy);
        double _t20 = Math.fma(_t17, _t11, _upz);
        return lookAlong_fma_api_s791204b7_1(dest, destOffset, src, srcOffset, _upz, _upx, _upy, _t11, _t12, _t13, Math.fma(_t18, _t13, -(_t19 * _t12)), Math.fma(_t19, _t11, -(_t20 * _t13)), Math.fma(_t20, _t12, -(_t18 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_fma_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_fma_api_s791204b7_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _upz, double _upx, double _upy, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29) {
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        lookAlong_fma_api_s4367997_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && dir.isNative() && up.isNative()) return lookAlong_mulAdd_unsafe(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
        return lookAlong_mulAdd_api(dest, destOffset, src, srcOffset, dir, dirOffset, up, upOffset);
    }

    public static java.lang.foreign.MemorySegment lookAlong_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        long _dirBase = dir.address() + dirOffset;
        long _upBase = up.address() + upOffset;
        double _dirz = UnsafeOpsHolder.U.getDouble(_dirBase + 16L);
        double _dirx = UnsafeOpsHolder.U.getDouble(_dirBase);
        double _diry = UnsafeOpsHolder.U.getDouble(_dirBase + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(_upBase + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(_upBase);
        double _upy = UnsafeOpsHolder.U.getDouble(_upBase + 8L);
        double _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = Math.fma(_t17, _t12, _upx);
        double _t19 = Math.fma(_t17, _t13, _upy);
        double _t20 = Math.fma(_t17, _t11, _upz);
        return lookAlong_mulAdd_unsafe_s55c634ce_1(dest, destOffset, src, srcOffset, _upz, _upx, _upy, _t11, _t12, _t13, Math.fma(_t18, _t13, -(_t19 * _t12)), Math.fma(_t19, _t11, -(_t20 * _t13)), Math.fma(_t20, _t12, -(_t18 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_mulAdd_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_mulAdd_unsafe_s55c634ce_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _upz, double _upx, double _upy, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29) {
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        lookAlong_mulAdd_unsafe_sf050eec0_v(dest, destOffset, src.address() + srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAlong_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment dir, long dirOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        double _dirz = dir.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, dirOffset + 16L);
        double _dirx = dir.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, dirOffset);
        double _diry = dir.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, dirOffset + 8L);
        double _upz = up.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, upOffset + 16L);
        double _upx = up.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, upOffset);
        double _upy = up.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, upOffset + 8L);
        double _t4 = Math.fma(_dirz, _dirz, Math.fma(_dirx, _dirx, _diry * _diry));
        double _t6 = (1.0 / java.lang.Math.sqrt(_t4));
        double _t11, _t12, _t13;
        if (_t4 != 0.0) {
            _t11 = _dirz * _t6;
            _t12 = _dirx * _t6;
            _t13 = _diry * _t6;
        } else {
            _t11 = 0.0;
            _t12 = 0.0;
            _t13 = 0.0;
        }
        double _t17 = -Math.fma(_upz, _t11, Math.fma(_upx, _t12, _upy * _t13));
        double _t18 = Math.fma(_t17, _t12, _upx);
        double _t19 = Math.fma(_t17, _t13, _upy);
        double _t20 = Math.fma(_t17, _t11, _upz);
        return lookAlong_mulAdd_api_s56df923c_1(dest, destOffset, src, srcOffset, _upz, _upx, _upy, _t11, _t12, _t13, Math.fma(_t18, _t13, -(_t19 * _t12)), Math.fma(_t19, _t11, -(_t20 * _t13)), Math.fma(_t20, _t12, -(_t18 * _t11)));
    }

    /** Piece 2 of {@code lookAlong_mulAdd_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAlong_mulAdd_api_s56df923c_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _upz, double _upx, double _upy, double _t11, double _t12, double _t13, double _t27, double _t28, double _t29) {
        double _t32 = Math.fma(_t27, _t27, Math.fma(_t28, _t28, _t29 * _t29));
        double _t33 = (1.0 / java.lang.Math.sqrt(_t32));
        double _t37, _t38, _t39;
        if (_t32 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t37 = _t27 * _t33;
            _t38 = _t28 * _t33;
            _t39 = _t29 * _t33;
        } else {
            _t37 = 0.0;
            _t38 = 0.0;
            _t39 = 0.0;
        }
        lookAlong_mulAdd_api_s908bf4e_v(dest, destOffset, src, srcOffset, _t11, _t12, _t13, _t37, _t38, _t39);
        return dest;
    }

    public static double[] lookAt_lh(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        if (SimdSupport.USE_FMA) return lookAt_lh_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return lookAt_lh_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static double[] lookAt_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _t0 = centerZ - eyeZ;
        double _t1 = centerX - eyeX;
        double _t2 = centerY - eyeY;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t23 = -Math.fma(upZ, _t15, Math.fma(upX, _t14, upY * _t16));
        double _t24 = Math.fma(_t23, _t14, upX);
        double _t25 = Math.fma(_t23, _t16, upY);
        double _t26 = Math.fma(_t23, _t15, upZ);
        double _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        double _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        double _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        double _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        return lookAt_lh_fma_s84b6f51c_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t14, _t15, _t16, _t33, _t34, _t35, _t38, (1.0 / java.lang.Math.sqrt(_t38)));
    }

    /** Piece 2 of {@code lookAt_lh_fma}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_lh_fma_s84b6f51c_1(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _t14, double _t15, double _t16, double _t33, double _t34, double _t35, double _t38, double _t39) {
        double _t43, _t44, _t45;
        if (_t38 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t54 = Math.fma(_t44, _t16, -(_t45 * _t15));
        double _t55 = Math.fma(_t43, _t15, -(_t44 * _t14));
        double _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        lookAt_lh_fma_s8c9f864_v(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t14, _t15, _t16, Math.fma(eyeZ, _t15, Math.fma(eyeX, _t14, eyeY * _t16)), _t43, _t44, _t45, _t54, _t55, _t56, Math.fma(eyeZ, _t56, Math.fma(eyeX, _t54, eyeY * _t55)));
        return dest;
    }

    private static void lookAt_lh_fma_s8c9f864_v(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double _t14, double _t15, double _t16, double _t22, double _t43, double _t44, double _t45, double _t54, double _t55, double _t56, double _t60) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, Math.fma(eyeZ, _t44, Math.fma(eyeX, _t43, eyeY * _t45)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, src[_lo0]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).withLane(3, -src[_lo0]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, -src[_lo0 + 1] * _t60 - src[_lo0 + 2] * _t22)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] lookAt_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _t0 = centerZ - eyeZ;
        double _t1 = centerX - eyeX;
        double _t2 = centerY - eyeY;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t23 = -Math.fma(upZ, _t15, Math.fma(upX, _t14, upY * _t16));
        double _t24 = Math.fma(_t23, _t14, upX);
        double _t25 = Math.fma(_t23, _t16, upY);
        double _t26 = Math.fma(_t23, _t15, upZ);
        double _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        double _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        double _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        double _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        return lookAt_lh_mulAdd_sd8f2a58f_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t14, _t15, _t16, _t33, _t34, _t35, _t38, (1.0 / java.lang.Math.sqrt(_t38)));
    }

    /** Piece 2 of {@code lookAt_lh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_lh_mulAdd_sd8f2a58f_1(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _t14, double _t15, double _t16, double _t33, double _t34, double _t35, double _t38, double _t39) {
        double _t43, _t44, _t45;
        if (_t38 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t54 = Math.fma(_t44, _t16, -(_t45 * _t15));
        double _t55 = Math.fma(_t43, _t15, -(_t44 * _t14));
        double _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        lookAt_lh_mulAdd_se73a766f_v(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t14, _t15, _t16, Math.fma(eyeZ, _t15, Math.fma(eyeX, _t14, eyeY * _t16)), _t43, _t44, _t45, _t54, _t55, _t56, Math.fma(eyeZ, _t56, Math.fma(eyeX, _t54, eyeY * _t55)));
        return dest;
    }

    private static void lookAt_lh_mulAdd_se73a766f_v(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double _t14, double _t15, double _t16, double _t22, double _t43, double _t44, double _t45, double _t54, double _t55, double _t56, double _t60) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, Math.fma(eyeZ, _t44, Math.fma(eyeX, _t43, eyeY * _t45)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, src[_lo0]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).withLane(3, -src[_lo0]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, -src[_lo0 + 1] * _t60 - src[_lo0 + 2] * _t22)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] lookAt_rh(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        if (SimdSupport.USE_FMA) return lookAt_rh_fma(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return lookAt_rh_mulAdd(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static double[] lookAt_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _t3 = centerZ - eyeZ;
        double _t4 = centerX - eyeX;
        double _t5 = centerY - eyeY;
        double _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t17, _t18, _t19;
        if (_t12 != 0.0) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0;
            _t18 = 0.0;
            _t19 = 0.0;
        }
        double _t26 = -Math.fma(upZ, _t18, Math.fma(upX, _t17, upY * _t19));
        double _t27 = Math.fma(_t26, _t19, upY);
        double _t28 = Math.fma(_t26, _t17, upX);
        double _t29 = Math.fma(_t26, _t18, upZ);
        double _t36 = Math.fma(_t27, _t17, -(_t28 * _t19));
        double _t37 = Math.fma(_t28, _t18, -(_t29 * _t17));
        double _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        double _t41 = Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38));
        return lookAt_rh_fma_sa88f0056_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t17, _t18, _t19, _t36, _t37, _t38, _t41, (1.0 / java.lang.Math.sqrt(_t41)));
    }

    /** Piece 2 of {@code lookAt_rh_fma}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_fma_sa88f0056_1(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _t17, double _t18, double _t19, double _t36, double _t37, double _t38, double _t41, double _t42) {
        double _t46, _t47, _t48;
        if (_t41 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0;
            _t47 = 0.0;
            _t48 = 0.0;
        }
        double _t57 = Math.fma(_t47, _t18, -(_t48 * _t19));
        double _t58 = Math.fma(_t48, _t17, -(_t46 * _t18));
        double _t59 = Math.fma(_t46, _t19, -(_t47 * _t17));
        lookAt_rh_fma_se2b0a512_v(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t17, _t18, _t19, _t46, _t47, _t48, _t57, _t58, _t59, Math.fma(eyeZ, _t48, Math.fma(eyeX, _t46, eyeY * _t47)), Math.fma(eyeZ, _t59, Math.fma(eyeX, _t57, eyeY * _t58)));
        return dest;
    }

    private static void lookAt_rh_fma_se2b0a512_v(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double _t17, double _t18, double _t19, double _t46, double _t47, double _t48, double _t57, double _t58, double _t59, double _t61, double _t63) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, Math.fma(eyeZ, _t18, Math.fma(eyeX, _t17, eyeY * _t19)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, -src[_lo0 + 2]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0]).withLane(3, src[_lo0 + 2]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, -src[_lo0] * _t61 - src[_lo0 + 1] * _t63)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] lookAt_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _t3 = centerZ - eyeZ;
        double _t4 = centerX - eyeX;
        double _t5 = centerY - eyeY;
        double _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t17, _t18, _t19;
        if (_t12 != 0.0) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0;
            _t18 = 0.0;
            _t19 = 0.0;
        }
        double _t26 = -Math.fma(upZ, _t18, Math.fma(upX, _t17, upY * _t19));
        double _t27 = Math.fma(_t26, _t19, upY);
        double _t28 = Math.fma(_t26, _t17, upX);
        double _t29 = Math.fma(_t26, _t18, upZ);
        double _t36 = Math.fma(_t27, _t17, -(_t28 * _t19));
        double _t37 = Math.fma(_t28, _t18, -(_t29 * _t17));
        double _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        double _t41 = Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38));
        return lookAt_rh_mulAdd_s2af21ec9_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t17, _t18, _t19, _t36, _t37, _t38, _t41, (1.0 / java.lang.Math.sqrt(_t41)));
    }

    /** Piece 2 of {@code lookAt_rh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_mulAdd_s2af21ec9_1(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _t17, double _t18, double _t19, double _t36, double _t37, double _t38, double _t41, double _t42) {
        double _t46, _t47, _t48;
        if (_t41 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0;
            _t47 = 0.0;
            _t48 = 0.0;
        }
        double _t57 = Math.fma(_t47, _t18, -(_t48 * _t19));
        double _t58 = Math.fma(_t48, _t17, -(_t46 * _t18));
        double _t59 = Math.fma(_t46, _t19, -(_t47 * _t17));
        lookAt_rh_mulAdd_sbf311395_v(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t17, _t18, _t19, _t46, _t47, _t48, _t57, _t58, _t59, Math.fma(eyeZ, _t48, Math.fma(eyeX, _t46, eyeY * _t47)), Math.fma(eyeZ, _t59, Math.fma(eyeX, _t57, eyeY * _t58)));
        return dest;
    }

    private static void lookAt_rh_mulAdd_sbf311395_v(double[] dest, int destOffset, double[] src, int srcOffset, double eyeX, double eyeY, double eyeZ, double _t17, double _t18, double _t19, double _t46, double _t47, double _t48, double _t57, double _t58, double _t59, double _t61, double _t63) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, Math.fma(eyeZ, _t18, Math.fma(eyeX, _t17, eyeY * _t19)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, -src[_lo0 + 2]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0]).withLane(3, src[_lo0 + 2]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, -src[_lo0] * _t61 - src[_lo0 + 1] * _t63)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static java.lang.foreign.MemorySegment lookAt_lh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return lookAt_lh_fma_unsafe(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
            return lookAt_lh_fma_api(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return lookAt_lh_mulAdd_unsafe(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return lookAt_lh_mulAdd_api(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static java.lang.foreign.MemorySegment lookAt_lh_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return lookAt_lh_fma_unsafe(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return lookAt_lh_fma_api(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static java.lang.foreign.MemorySegment lookAt_lh_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _t0 = centerZ - eyeZ;
        double _t1 = centerX - eyeX;
        double _t2 = centerY - eyeY;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t23 = -Math.fma(upZ, _t15, Math.fma(upX, _t14, upY * _t16));
        double _t24 = Math.fma(_t23, _t14, upX);
        double _t25 = Math.fma(_t23, _t16, upY);
        double _t26 = Math.fma(_t23, _t15, upZ);
        double _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        double _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        double _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        double _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        return lookAt_lh_fma_unsafe_s15621113_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t14, _t15, _t16, _t33, _t34, _t35, _t38, (1.0 / java.lang.Math.sqrt(_t38)));
    }

    /** Piece 2 of {@code lookAt_lh_fma_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAt_lh_fma_unsafe_s15621113_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _t14, double _t15, double _t16, double _t33, double _t34, double _t35, double _t38, double _t39) {
        double _t43, _t44, _t45;
        if (_t38 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t54 = Math.fma(_t44, _t16, -(_t45 * _t15));
        double _t55 = Math.fma(_t43, _t15, -(_t44 * _t14));
        double _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        lookAt_lh_fma_unsafe_sa46180b3_v(dest, destOffset, eyeX, eyeY, eyeZ, src.address() + srcOffset, _t14, _t15, _t16, Math.fma(eyeZ, _t15, Math.fma(eyeX, _t14, eyeY * _t16)), _t43, _t44, _t45, _t54, _t55, _t56, Math.fma(eyeZ, _t56, Math.fma(eyeX, _t54, eyeY * _t55)));
        return dest;
    }

    private static void lookAt_lh_fma_unsafe_sa46180b3_v(java.lang.foreign.MemorySegment dest, long destOffset, double eyeX, double eyeY, double eyeZ, long _srcBase, double _t14, double _t15, double _t16, double _t22, double _t43, double _t44, double _t45, double _t54, double _t55, double _t56, double _t60) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, Math.fma(eyeZ, _t44, Math.fma(eyeX, _t43, eyeY * _t45)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L))).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L)).withLane(3, -UnsafeOpsHolder.U.getDouble(_lb1)).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 16L)).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, -UnsafeOpsHolder.U.getDouble(_lb1 + 8L) * _t60 - UnsafeOpsHolder.U.getDouble(_lb1 + 16L) * _t22)))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment lookAt_lh_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _t0 = centerZ - eyeZ;
        double _t1 = centerX - eyeX;
        double _t2 = centerY - eyeY;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t23 = -Math.fma(upZ, _t15, Math.fma(upX, _t14, upY * _t16));
        double _t24 = Math.fma(_t23, _t14, upX);
        double _t25 = Math.fma(_t23, _t16, upY);
        double _t26 = Math.fma(_t23, _t15, upZ);
        double _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        double _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        double _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        double _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        return lookAt_lh_fma_api_s5c3dbe09_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t14, _t15, _t16, _t33, _t34, _t35, _t38, (1.0 / java.lang.Math.sqrt(_t38)));
    }

    /** Piece 2 of {@code lookAt_lh_fma_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAt_lh_fma_api_s5c3dbe09_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _t14, double _t15, double _t16, double _t33, double _t34, double _t35, double _t38, double _t39) {
        double _t43, _t44, _t45;
        if (_t38 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t54 = Math.fma(_t44, _t16, -(_t45 * _t15));
        double _t55 = Math.fma(_t43, _t15, -(_t44 * _t14));
        double _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        lookAt_lh_fma_api_s486350d5_v(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t14, _t15, _t16, Math.fma(eyeZ, _t15, Math.fma(eyeX, _t14, eyeY * _t16)), _t43, _t44, _t45, _t54, _t55, _t56, Math.fma(eyeZ, _t56, Math.fma(eyeX, _t54, eyeY * _t55)));
        return dest;
    }

    private static void lookAt_lh_fma_api_s486350d5_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double eyeX, double eyeY, double eyeZ, double _t14, double _t15, double _t16, double _t22, double _t43, double _t44, double _t45, double _t54, double _t55, double _t56, double _t60) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, Math.fma(eyeZ, _t44, Math.fma(eyeX, _t43, eyeY * _t45)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))).add(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L)).withLane(3, -src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, -src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L) * _t60 - src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L) * _t22)))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment lookAt_lh_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return lookAt_lh_mulAdd_unsafe(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return lookAt_lh_mulAdd_api(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static java.lang.foreign.MemorySegment lookAt_lh_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _t0 = centerZ - eyeZ;
        double _t1 = centerX - eyeX;
        double _t2 = centerY - eyeY;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t23 = -Math.fma(upZ, _t15, Math.fma(upX, _t14, upY * _t16));
        double _t24 = Math.fma(_t23, _t14, upX);
        double _t25 = Math.fma(_t23, _t16, upY);
        double _t26 = Math.fma(_t23, _t15, upZ);
        double _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        double _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        double _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        double _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        return lookAt_lh_mulAdd_unsafe_s5b51de82_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t14, _t15, _t16, _t33, _t34, _t35, _t38, (1.0 / java.lang.Math.sqrt(_t38)));
    }

    /** Piece 2 of {@code lookAt_lh_mulAdd_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAt_lh_mulAdd_unsafe_s5b51de82_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _t14, double _t15, double _t16, double _t33, double _t34, double _t35, double _t38, double _t39) {
        double _t43, _t44, _t45;
        if (_t38 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t54 = Math.fma(_t44, _t16, -(_t45 * _t15));
        double _t55 = Math.fma(_t43, _t15, -(_t44 * _t14));
        double _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        lookAt_lh_mulAdd_unsafe_sfe9aed9e_v(dest, destOffset, eyeX, eyeY, eyeZ, src.address() + srcOffset, _t14, _t15, _t16, Math.fma(eyeZ, _t15, Math.fma(eyeX, _t14, eyeY * _t16)), _t43, _t44, _t45, _t54, _t55, _t56, Math.fma(eyeZ, _t56, Math.fma(eyeX, _t54, eyeY * _t55)));
        return dest;
    }

    private static void lookAt_lh_mulAdd_unsafe_sfe9aed9e_v(java.lang.foreign.MemorySegment dest, long destOffset, double eyeX, double eyeY, double eyeZ, long _srcBase, double _t14, double _t15, double _t16, double _t22, double _t43, double _t44, double _t45, double _t54, double _t55, double _t56, double _t60) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, Math.fma(eyeZ, _t44, Math.fma(eyeX, _t43, eyeY * _t45)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L))).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L)).withLane(3, -UnsafeOpsHolder.U.getDouble(_lb1)).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 16L)).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, -UnsafeOpsHolder.U.getDouble(_lb1 + 8L) * _t60 - UnsafeOpsHolder.U.getDouble(_lb1 + 16L) * _t22)))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment lookAt_lh_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _t0 = centerZ - eyeZ;
        double _t1 = centerX - eyeX;
        double _t2 = centerY - eyeY;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t22 = Math.fma(eyeZ, _t15, Math.fma(eyeX, _t14, eyeY * _t16));
        double _t23 = -Math.fma(upZ, _t15, Math.fma(upX, _t14, upY * _t16));
        double _t24 = Math.fma(_t23, _t14, upX);
        double _t25 = Math.fma(_t23, _t16, upY);
        double _t26 = Math.fma(_t23, _t15, upZ);
        double _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        double _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        double _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        double _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        double _t39 = (1.0 / java.lang.Math.sqrt(_t38));
        double _t43, _t44, _t45;
        if (_t38 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t54 = Math.fma(_t44, _t16, -(_t45 * _t15));
        double _t55 = Math.fma(_t43, _t15, -(_t44 * _t14));
        double _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        double _t60 = Math.fma(eyeZ, _t56, Math.fma(eyeX, _t54, eyeY * _t55));
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, Math.fma(eyeZ, _t44, Math.fma(eyeX, _t43, eyeY * _t45)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))).add(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L)).withLane(3, -src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, -src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L) * _t60 - src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L) * _t22)))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment lookAt_rh(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return lookAt_rh_fma_unsafe(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
            return lookAt_rh_fma_api(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return lookAt_rh_mulAdd_unsafe(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return lookAt_rh_mulAdd_api(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static java.lang.foreign.MemorySegment lookAt_rh_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return lookAt_rh_fma_unsafe(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return lookAt_rh_fma_api(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static java.lang.foreign.MemorySegment lookAt_rh_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _t3 = centerZ - eyeZ;
        double _t4 = centerX - eyeX;
        double _t5 = centerY - eyeY;
        double _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t17, _t18, _t19;
        if (_t12 != 0.0) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0;
            _t18 = 0.0;
            _t19 = 0.0;
        }
        double _t26 = -Math.fma(upZ, _t18, Math.fma(upX, _t17, upY * _t19));
        double _t27 = Math.fma(_t26, _t19, upY);
        double _t28 = Math.fma(_t26, _t17, upX);
        double _t29 = Math.fma(_t26, _t18, upZ);
        double _t36 = Math.fma(_t27, _t17, -(_t28 * _t19));
        double _t37 = Math.fma(_t28, _t18, -(_t29 * _t17));
        double _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        double _t41 = Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38));
        return lookAt_rh_fma_unsafe_s79410a69_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t17, _t18, _t19, _t36, _t37, _t38, _t41, (1.0 / java.lang.Math.sqrt(_t41)));
    }

    /** Piece 2 of {@code lookAt_rh_fma_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAt_rh_fma_unsafe_s79410a69_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _t17, double _t18, double _t19, double _t36, double _t37, double _t38, double _t41, double _t42) {
        double _t46, _t47, _t48;
        if (_t41 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0;
            _t47 = 0.0;
            _t48 = 0.0;
        }
        double _t57 = Math.fma(_t47, _t18, -(_t48 * _t19));
        double _t58 = Math.fma(_t48, _t17, -(_t46 * _t18));
        double _t59 = Math.fma(_t46, _t19, -(_t47 * _t17));
        lookAt_rh_fma_unsafe_s90001535_v(dest, destOffset, eyeX, eyeY, eyeZ, src.address() + srcOffset, _t17, _t18, _t19, _t46, _t47, _t48, _t57, _t58, _t59, Math.fma(eyeZ, _t48, Math.fma(eyeX, _t46, eyeY * _t47)), Math.fma(eyeZ, _t59, Math.fma(eyeX, _t57, eyeY * _t58)));
        return dest;
    }

    private static void lookAt_rh_fma_unsafe_s90001535_v(java.lang.foreign.MemorySegment dest, long destOffset, double eyeX, double eyeY, double eyeZ, long _srcBase, double _t17, double _t18, double _t19, double _t46, double _t47, double _t48, double _t57, double _t58, double _t59, double _t61, double _t63) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, Math.fma(eyeZ, _t18, Math.fma(eyeX, _t17, eyeY * _t19)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, -UnsafeOpsHolder.U.getDouble(_lb1 + 16L)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L))).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1)).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 16L)).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L)).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, -UnsafeOpsHolder.U.getDouble(_lb1) * _t61 - UnsafeOpsHolder.U.getDouble(_lb1 + 8L) * _t63)))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment lookAt_rh_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _t3 = centerZ - eyeZ;
        double _t4 = centerX - eyeX;
        double _t5 = centerY - eyeY;
        double _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t17, _t18, _t19;
        if (_t12 != 0.0) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0;
            _t18 = 0.0;
            _t19 = 0.0;
        }
        double _t26 = -Math.fma(upZ, _t18, Math.fma(upX, _t17, upY * _t19));
        double _t27 = Math.fma(_t26, _t19, upY);
        double _t28 = Math.fma(_t26, _t17, upX);
        double _t29 = Math.fma(_t26, _t18, upZ);
        double _t36 = Math.fma(_t27, _t17, -(_t28 * _t19));
        double _t37 = Math.fma(_t28, _t18, -(_t29 * _t17));
        double _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        double _t41 = Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38));
        return lookAt_rh_fma_api_s947b723f_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t17, _t18, _t19, _t36, _t37, _t38, _t41, (1.0 / java.lang.Math.sqrt(_t41)));
    }

    /** Piece 2 of {@code lookAt_rh_fma_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAt_rh_fma_api_s947b723f_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _t17, double _t18, double _t19, double _t36, double _t37, double _t38, double _t41, double _t42) {
        double _t46, _t47, _t48;
        if (_t41 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0;
            _t47 = 0.0;
            _t48 = 0.0;
        }
        double _t57 = Math.fma(_t47, _t18, -(_t48 * _t19));
        double _t58 = Math.fma(_t48, _t17, -(_t46 * _t18));
        double _t59 = Math.fma(_t46, _t19, -(_t47 * _t17));
        lookAt_rh_fma_api_sfe3fd43f_v(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, _t17, _t18, _t19, _t46, _t47, _t48, _t57, _t58, _t59, Math.fma(eyeZ, _t48, Math.fma(eyeX, _t46, eyeY * _t47)), Math.fma(eyeZ, _t59, Math.fma(eyeX, _t57, eyeY * _t58)));
        return dest;
    }

    private static void lookAt_rh_fma_api_sfe3fd43f_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double eyeX, double eyeY, double eyeZ, double _t17, double _t18, double _t19, double _t46, double _t47, double _t48, double _t57, double _t58, double _t59, double _t61, double _t63) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, Math.fma(eyeZ, _t18, Math.fma(eyeX, _t17, eyeY * _t19)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, -src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))).add(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L)).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, -src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0) * _t61 - src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L) * _t63)))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment lookAt_rh_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return lookAt_rh_mulAdd_unsafe(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
        return lookAt_rh_mulAdd_api(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, centerX, centerY, centerZ, upX, upY, upZ);
    }

    public static java.lang.foreign.MemorySegment lookAt_rh_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _t3 = centerZ - eyeZ;
        double _t4 = centerX - eyeX;
        double _t5 = centerY - eyeY;
        double _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t17, _t18, _t19;
        if (_t12 != 0.0) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0;
            _t18 = 0.0;
            _t19 = 0.0;
        }
        double _t26 = -Math.fma(upZ, _t18, Math.fma(upX, _t17, upY * _t19));
        double _t27 = Math.fma(_t26, _t19, upY);
        double _t28 = Math.fma(_t26, _t17, upX);
        double _t29 = Math.fma(_t26, _t18, upZ);
        double _t36 = Math.fma(_t27, _t17, -(_t28 * _t19));
        double _t37 = Math.fma(_t28, _t18, -(_t29 * _t17));
        double _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        double _t41 = Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38));
        return lookAt_rh_mulAdd_unsafe_s84227830_1(dest, destOffset, src, srcOffset, eyeX, eyeY, eyeZ, upX, upY, upZ, _t17, _t18, _t19, _t36, _t37, _t38, _t41, (1.0 / java.lang.Math.sqrt(_t41)));
    }

    /** Piece 2 of {@code lookAt_rh_mulAdd_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAt_rh_mulAdd_unsafe_s84227830_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double eyeX, double eyeY, double eyeZ, double upX, double upY, double upZ, double _t17, double _t18, double _t19, double _t36, double _t37, double _t38, double _t41, double _t42) {
        double _t46, _t47, _t48;
        if (_t41 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0;
            _t47 = 0.0;
            _t48 = 0.0;
        }
        double _t57 = Math.fma(_t47, _t18, -(_t48 * _t19));
        double _t58 = Math.fma(_t48, _t17, -(_t46 * _t18));
        double _t59 = Math.fma(_t46, _t19, -(_t47 * _t17));
        lookAt_rh_mulAdd_unsafe_s1d725ed8_v(dest, destOffset, eyeX, eyeY, eyeZ, src.address() + srcOffset, _t17, _t18, _t19, _t46, _t47, _t48, _t57, _t58, _t59, Math.fma(eyeZ, _t48, Math.fma(eyeX, _t46, eyeY * _t47)), Math.fma(eyeZ, _t59, Math.fma(eyeX, _t57, eyeY * _t58)));
        return dest;
    }

    private static void lookAt_rh_mulAdd_unsafe_s1d725ed8_v(java.lang.foreign.MemorySegment dest, long destOffset, double eyeX, double eyeY, double eyeZ, long _srcBase, double _t17, double _t18, double _t19, double _t46, double _t47, double _t48, double _t57, double _t58, double _t59, double _t61, double _t63) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, Math.fma(eyeZ, _t18, Math.fma(eyeX, _t17, eyeY * _t19)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, -UnsafeOpsHolder.U.getDouble(_lb1 + 16L)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L))).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1)).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 16L)).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L)).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, -UnsafeOpsHolder.U.getDouble(_lb1) * _t61 - UnsafeOpsHolder.U.getDouble(_lb1 + 8L) * _t63)))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment lookAt_rh_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double eyeX, double eyeY, double eyeZ, double centerX, double centerY, double centerZ, double upX, double upY, double upZ) {
        double _t3 = centerZ - eyeZ;
        double _t4 = centerX - eyeX;
        double _t5 = centerY - eyeY;
        double _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t17, _t18, _t19;
        if (_t12 != 0.0) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0;
            _t18 = 0.0;
            _t19 = 0.0;
        }
        double _t26 = -Math.fma(upZ, _t18, Math.fma(upX, _t17, upY * _t19));
        double _t27 = Math.fma(_t26, _t19, upY);
        double _t28 = Math.fma(_t26, _t17, upX);
        double _t29 = Math.fma(_t26, _t18, upZ);
        double _t36 = Math.fma(_t27, _t17, -(_t28 * _t19));
        double _t37 = Math.fma(_t28, _t18, -(_t29 * _t17));
        double _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        double _t41 = Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38));
        double _t42 = (1.0 / java.lang.Math.sqrt(_t41));
        double _t46, _t47, _t48;
        if (_t41 > Math.fma(Math.fma(upZ, upZ, Math.fma(upX, upX, upY * upY)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0;
            _t47 = 0.0;
            _t48 = 0.0;
        }
        double _t57 = Math.fma(_t47, _t18, -(_t48 * _t19));
        double _t58 = Math.fma(_t48, _t17, -(_t46 * _t18));
        double _t59 = Math.fma(_t46, _t19, -(_t47 * _t17));
        double _t61 = Math.fma(eyeZ, _t48, Math.fma(eyeX, _t46, eyeY * _t47));
        double _t63 = Math.fma(eyeZ, _t59, Math.fma(eyeX, _t57, eyeY * _t58));
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, Math.fma(eyeZ, _t18, Math.fma(eyeX, _t17, eyeY * _t19)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, -src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))).add(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L)).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, -src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0) * _t61 - src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L) * _t63)))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static double[] lookAt_lh(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset) {
        if (SimdSupport.USE_FMA) return lookAt_lh_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return lookAt_lh_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static double[] lookAt_lh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset) {
        double _eyez = eye[eyeOffset + 2];
        double _eyex = eye[eyeOffset];
        double _eyey = eye[eyeOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t0 = center[centerOffset + 2] - _eyez;
        double _t1 = center[centerOffset] - _eyex;
        double _t2 = center[centerOffset + 1] - _eyey;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t23 = -Math.fma(_upz, _t15, Math.fma(_upx, _t14, _upy * _t16));
        double _t24 = Math.fma(_t23, _t14, _upx);
        double _t25 = Math.fma(_t23, _t16, _upy);
        double _t26 = Math.fma(_t23, _t15, _upz);
        double _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        double _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        double _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        return lookAt_lh_fma_s5874f362_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t14, _t15, _t16, _t33, _t34, _t35, Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35)));
    }

    /** Piece 2 of {@code lookAt_lh_fma}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_lh_fma_s5874f362_1(double[] dest, int destOffset, double[] src, int srcOffset, double _eyez, double _eyex, double _eyey, double _upz, double _upx, double _upy, double _t14, double _t15, double _t16, double _t33, double _t34, double _t35, double _t38) {
        double _t39 = (1.0 / java.lang.Math.sqrt(_t38));
        double _t43, _t44, _t45;
        if (_t38 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t54 = Math.fma(_t44, _t16, -(_t45 * _t15));
        double _t55 = Math.fma(_t43, _t15, -(_t44 * _t14));
        double _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        lookAt_lh_fma_s2eeb267e_v(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _t14, _t15, _t16, Math.fma(_eyez, _t15, Math.fma(_eyex, _t14, _eyey * _t16)), _t43, _t44, _t45, _t54, _t55, _t56, Math.fma(_eyez, _t56, Math.fma(_eyex, _t54, _eyey * _t55)));
        return dest;
    }

    private static void lookAt_lh_fma_s2eeb267e_v(double[] dest, int destOffset, double[] src, int srcOffset, double _eyez, double _eyex, double _eyey, double _t14, double _t15, double _t16, double _t22, double _t43, double _t44, double _t45, double _t54, double _t55, double _t56, double _t60) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, Math.fma(_eyez, _t44, Math.fma(_eyex, _t43, _eyey * _t45)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, src[_lo0]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).withLane(3, -src[_lo0]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, -src[_lo0 + 1] * _t60 - src[_lo0 + 2] * _t22)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] lookAt_lh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset) {
        double _eyez = eye[eyeOffset + 2];
        double _eyex = eye[eyeOffset];
        double _eyey = eye[eyeOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t0 = center[centerOffset + 2] - _eyez;
        double _t1 = center[centerOffset] - _eyex;
        double _t2 = center[centerOffset + 1] - _eyey;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t23 = -Math.fma(_upz, _t15, Math.fma(_upx, _t14, _upy * _t16));
        double _t24 = Math.fma(_t23, _t14, _upx);
        double _t25 = Math.fma(_t23, _t16, _upy);
        double _t26 = Math.fma(_t23, _t15, _upz);
        double _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        double _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        double _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        return lookAt_lh_mulAdd_s10d0f719_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t14, _t15, _t16, _t33, _t34, _t35, Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35)));
    }

    /** Piece 2 of {@code lookAt_lh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_lh_mulAdd_s10d0f719_1(double[] dest, int destOffset, double[] src, int srcOffset, double _eyez, double _eyex, double _eyey, double _upz, double _upx, double _upy, double _t14, double _t15, double _t16, double _t33, double _t34, double _t35, double _t38) {
        double _t39 = (1.0 / java.lang.Math.sqrt(_t38));
        double _t43, _t44, _t45;
        if (_t38 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t54 = Math.fma(_t44, _t16, -(_t45 * _t15));
        double _t55 = Math.fma(_t43, _t15, -(_t44 * _t14));
        double _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        lookAt_lh_mulAdd_s72af92c5_v(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _t14, _t15, _t16, Math.fma(_eyez, _t15, Math.fma(_eyex, _t14, _eyey * _t16)), _t43, _t44, _t45, _t54, _t55, _t56, Math.fma(_eyez, _t56, Math.fma(_eyex, _t54, _eyey * _t55)));
        return dest;
    }

    private static void lookAt_lh_mulAdd_s72af92c5_v(double[] dest, int destOffset, double[] src, int srcOffset, double _eyez, double _eyex, double _eyey, double _t14, double _t15, double _t16, double _t22, double _t43, double _t44, double _t45, double _t54, double _t55, double _t56, double _t60) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, Math.fma(_eyez, _t44, Math.fma(_eyex, _t43, _eyey * _t45)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, src[_lo0]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).withLane(3, -src[_lo0]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, -src[_lo0 + 1] * _t60 - src[_lo0 + 2] * _t22)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] lookAt_rh(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset) {
        if (SimdSupport.USE_FMA) return lookAt_rh_fma(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
        return lookAt_rh_mulAdd(dest, destOffset, src, srcOffset, eye, eyeOffset, center, centerOffset, up, upOffset);
    }

    public static double[] lookAt_rh_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset) {
        double _eyez = eye[eyeOffset + 2];
        double _eyex = eye[eyeOffset];
        double _eyey = eye[eyeOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t3 = center[centerOffset + 2] - _eyez;
        double _t4 = center[centerOffset] - _eyex;
        double _t5 = center[centerOffset + 1] - _eyey;
        double _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t17, _t18, _t19;
        if (_t12 != 0.0) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0;
            _t18 = 0.0;
            _t19 = 0.0;
        }
        double _t26 = -Math.fma(_upz, _t18, Math.fma(_upx, _t17, _upy * _t19));
        double _t27 = Math.fma(_t26, _t19, _upy);
        double _t28 = Math.fma(_t26, _t17, _upx);
        double _t29 = Math.fma(_t26, _t18, _upz);
        double _t36 = Math.fma(_t27, _t17, -(_t28 * _t19));
        double _t37 = Math.fma(_t28, _t18, -(_t29 * _t17));
        double _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        return lookAt_rh_fma_sc0e4bc8c_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t17, _t18, _t19, _t36, _t37, _t38, Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38)));
    }

    /** Piece 2 of {@code lookAt_rh_fma}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_fma_sc0e4bc8c_1(double[] dest, int destOffset, double[] src, int srcOffset, double _eyez, double _eyex, double _eyey, double _upz, double _upx, double _upy, double _t17, double _t18, double _t19, double _t36, double _t37, double _t38, double _t41) {
        double _t42 = (1.0 / java.lang.Math.sqrt(_t41));
        double _t46, _t47, _t48;
        if (_t41 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0;
            _t47 = 0.0;
            _t48 = 0.0;
        }
        double _t57 = Math.fma(_t47, _t18, -(_t48 * _t19));
        double _t58 = Math.fma(_t48, _t17, -(_t46 * _t18));
        double _t59 = Math.fma(_t46, _t19, -(_t47 * _t17));
        lookAt_rh_fma_sda93f074_v(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _t17, _t18, _t19, _t46, _t47, _t48, _t57, _t58, _t59, Math.fma(_eyez, _t48, Math.fma(_eyex, _t46, _eyey * _t47)), Math.fma(_eyez, _t59, Math.fma(_eyex, _t57, _eyey * _t58)));
        return dest;
    }

    private static void lookAt_rh_fma_sda93f074_v(double[] dest, int destOffset, double[] src, int srcOffset, double _eyez, double _eyex, double _eyey, double _t17, double _t18, double _t19, double _t46, double _t47, double _t48, double _t57, double _t58, double _t59, double _t61, double _t63) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, Math.fma(_eyez, _t18, Math.fma(_eyex, _t17, _eyey * _t19)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, -src[_lo0 + 2]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0]).withLane(3, src[_lo0 + 2]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, -src[_lo0] * _t61 - src[_lo0 + 1] * _t63)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] lookAt_rh_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] eye, int eyeOffset, double[] center, int centerOffset, double[] up, int upOffset) {
        double _eyez = eye[eyeOffset + 2];
        double _eyex = eye[eyeOffset];
        double _eyey = eye[eyeOffset + 1];
        double _upz = up[upOffset + 2];
        double _upx = up[upOffset];
        double _upy = up[upOffset + 1];
        double _t3 = center[centerOffset + 2] - _eyez;
        double _t4 = center[centerOffset] - _eyex;
        double _t5 = center[centerOffset + 1] - _eyey;
        double _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t17, _t18, _t19;
        if (_t12 != 0.0) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0;
            _t18 = 0.0;
            _t19 = 0.0;
        }
        double _t26 = -Math.fma(_upz, _t18, Math.fma(_upx, _t17, _upy * _t19));
        double _t27 = Math.fma(_t26, _t19, _upy);
        double _t28 = Math.fma(_t26, _t17, _upx);
        double _t29 = Math.fma(_t26, _t18, _upz);
        double _t36 = Math.fma(_t27, _t17, -(_t28 * _t19));
        double _t37 = Math.fma(_t28, _t18, -(_t29 * _t17));
        double _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        return lookAt_rh_mulAdd_scb89fcf3_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t17, _t18, _t19, _t36, _t37, _t38, Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38)));
    }

    /** Piece 2 of {@code lookAt_rh_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] lookAt_rh_mulAdd_scb89fcf3_1(double[] dest, int destOffset, double[] src, int srcOffset, double _eyez, double _eyex, double _eyey, double _upz, double _upx, double _upy, double _t17, double _t18, double _t19, double _t36, double _t37, double _t38, double _t41) {
        double _t42 = (1.0 / java.lang.Math.sqrt(_t41));
        double _t46, _t47, _t48;
        if (_t41 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0;
            _t47 = 0.0;
            _t48 = 0.0;
        }
        double _t57 = Math.fma(_t47, _t18, -(_t48 * _t19));
        double _t58 = Math.fma(_t48, _t17, -(_t46 * _t18));
        double _t59 = Math.fma(_t46, _t19, -(_t47 * _t17));
        lookAt_rh_mulAdd_s6dacaa93_v(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _t17, _t18, _t19, _t46, _t47, _t48, _t57, _t58, _t59, Math.fma(_eyez, _t48, Math.fma(_eyex, _t46, _eyey * _t47)), Math.fma(_eyez, _t59, Math.fma(_eyex, _t57, _eyey * _t58)));
        return dest;
    }

    private static void lookAt_rh_mulAdd_s6dacaa93_v(double[] dest, int destOffset, double[] src, int srcOffset, double _eyez, double _eyex, double _eyey, double _t17, double _t18, double _t19, double _t46, double _t47, double _t48, double _t57, double _t58, double _t59, double _t61, double _t63) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, Math.fma(_eyez, _t18, Math.fma(_eyex, _t17, _eyey * _t19)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, -src[_lo0 + 2]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0]).withLane(3, src[_lo0 + 2]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, -src[_lo0] * _t61 - src[_lo0 + 1] * _t63)))).intoArray(dest, destOffset + _li * 4);
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
        double _eyez = UnsafeOpsHolder.U.getDouble(_eyeBase + 16L);
        double _eyex = UnsafeOpsHolder.U.getDouble(_eyeBase);
        double _eyey = UnsafeOpsHolder.U.getDouble(_eyeBase + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(_upBase + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(_upBase);
        double _upy = UnsafeOpsHolder.U.getDouble(_upBase + 8L);
        double _t0 = UnsafeOpsHolder.U.getDouble(_centerBase + 16L) - _eyez;
        double _t1 = UnsafeOpsHolder.U.getDouble(_centerBase) - _eyex;
        double _t2 = UnsafeOpsHolder.U.getDouble(_centerBase + 8L) - _eyey;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t23 = -Math.fma(_upz, _t15, Math.fma(_upx, _t14, _upy * _t16));
        return lookAt_lh_fma_unsafe_s5f9f94e9_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t14, _t15, _t16, Math.fma(_t23, _t14, _upx), Math.fma(_t23, _t16, _upy), Math.fma(_t23, _t15, _upz));
    }

    /** Piece 2 of {@code lookAt_lh_fma_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAt_lh_fma_unsafe_s5f9f94e9_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _eyez, double _eyex, double _eyey, double _upz, double _upx, double _upy, double _t14, double _t15, double _t16, double _t24, double _t25, double _t26) {
        double _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        double _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        double _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        double _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        double _t39 = (1.0 / java.lang.Math.sqrt(_t38));
        double _t43, _t44, _t45;
        if (_t38 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t54 = Math.fma(_t44, _t16, -(_t45 * _t15));
        double _t55 = Math.fma(_t43, _t15, -(_t44 * _t14));
        double _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        lookAt_lh_fma_unsafe_s370ffbb5_v(dest, destOffset, src.address() + srcOffset, _eyez, _eyex, _eyey, _t14, _t15, _t16, Math.fma(_eyez, _t15, Math.fma(_eyex, _t14, _eyey * _t16)), _t43, _t44, _t45, _t54, _t55, _t56, Math.fma(_eyez, _t56, Math.fma(_eyex, _t54, _eyey * _t55)));
        return dest;
    }

    private static void lookAt_lh_fma_unsafe_s370ffbb5_v(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, double _eyez, double _eyex, double _eyey, double _t14, double _t15, double _t16, double _t22, double _t43, double _t44, double _t45, double _t54, double _t55, double _t56, double _t60) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, Math.fma(_eyez, _t44, Math.fma(_eyex, _t43, _eyey * _t45)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L))).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L)).withLane(3, -UnsafeOpsHolder.U.getDouble(_lb1)).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 16L)).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, -UnsafeOpsHolder.U.getDouble(_lb1 + 8L) * _t60 - UnsafeOpsHolder.U.getDouble(_lb1 + 16L) * _t22)))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment lookAt_lh_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment eye, long eyeOffset, java.lang.foreign.MemorySegment center, long centerOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        double _eyez = eye.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, eyeOffset + 16L);
        double _eyex = eye.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, eyeOffset);
        double _eyey = eye.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, eyeOffset + 8L);
        double _upz = up.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, upOffset + 16L);
        double _upx = up.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, upOffset);
        double _upy = up.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, upOffset + 8L);
        double _t0 = center.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, centerOffset + 16L) - _eyez;
        double _t1 = center.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, centerOffset) - _eyex;
        double _t2 = center.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, centerOffset + 8L) - _eyey;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t23 = -Math.fma(_upz, _t15, Math.fma(_upx, _t14, _upy * _t16));
        return lookAt_lh_fma_api_sda12e617_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t14, _t15, _t16, Math.fma(_t23, _t14, _upx), Math.fma(_t23, _t16, _upy), Math.fma(_t23, _t15, _upz));
    }

    /** Piece 2 of {@code lookAt_lh_fma_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAt_lh_fma_api_sda12e617_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _eyez, double _eyex, double _eyey, double _upz, double _upx, double _upy, double _t14, double _t15, double _t16, double _t24, double _t25, double _t26) {
        double _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        double _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        double _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        double _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        double _t39 = (1.0 / java.lang.Math.sqrt(_t38));
        double _t43, _t44, _t45;
        if (_t38 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t54 = Math.fma(_t44, _t16, -(_t45 * _t15));
        double _t55 = Math.fma(_t43, _t15, -(_t44 * _t14));
        double _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        lookAt_lh_fma_api_s4f8be17_v(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _t14, _t15, _t16, Math.fma(_eyez, _t15, Math.fma(_eyex, _t14, _eyey * _t16)), _t43, _t44, _t45, _t54, _t55, _t56, Math.fma(_eyez, _t56, Math.fma(_eyex, _t54, _eyey * _t55)));
        return dest;
    }

    private static void lookAt_lh_fma_api_s4f8be17_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _eyez, double _eyex, double _eyey, double _t14, double _t15, double _t16, double _t22, double _t43, double _t44, double _t45, double _t54, double _t55, double _t56, double _t60) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, Math.fma(_eyez, _t44, Math.fma(_eyex, _t43, _eyey * _t45)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))).add(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L)).withLane(3, -src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, -src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L) * _t60 - src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L) * _t22)))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
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
        double _eyez = UnsafeOpsHolder.U.getDouble(_eyeBase + 16L);
        double _eyex = UnsafeOpsHolder.U.getDouble(_eyeBase);
        double _eyey = UnsafeOpsHolder.U.getDouble(_eyeBase + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(_upBase + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(_upBase);
        double _upy = UnsafeOpsHolder.U.getDouble(_upBase + 8L);
        double _t0 = UnsafeOpsHolder.U.getDouble(_centerBase + 16L) - _eyez;
        double _t1 = UnsafeOpsHolder.U.getDouble(_centerBase) - _eyex;
        double _t2 = UnsafeOpsHolder.U.getDouble(_centerBase + 8L) - _eyey;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t23 = -Math.fma(_upz, _t15, Math.fma(_upx, _t14, _upy * _t16));
        return lookAt_lh_mulAdd_unsafe_s24b30694_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t14, _t15, _t16, Math.fma(_t23, _t14, _upx), Math.fma(_t23, _t16, _upy), Math.fma(_t23, _t15, _upz));
    }

    /** Piece 2 of {@code lookAt_lh_mulAdd_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAt_lh_mulAdd_unsafe_s24b30694_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _eyez, double _eyex, double _eyey, double _upz, double _upx, double _upy, double _t14, double _t15, double _t16, double _t24, double _t25, double _t26) {
        double _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        double _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        double _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        double _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        double _t39 = (1.0 / java.lang.Math.sqrt(_t38));
        double _t43, _t44, _t45;
        if (_t38 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t54 = Math.fma(_t44, _t16, -(_t45 * _t15));
        double _t55 = Math.fma(_t43, _t15, -(_t44 * _t14));
        double _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        lookAt_lh_mulAdd_unsafe_se98fc59c_v(dest, destOffset, src.address() + srcOffset, _eyez, _eyex, _eyey, _t14, _t15, _t16, Math.fma(_eyez, _t15, Math.fma(_eyex, _t14, _eyey * _t16)), _t43, _t44, _t45, _t54, _t55, _t56, Math.fma(_eyez, _t56, Math.fma(_eyex, _t54, _eyey * _t55)));
        return dest;
    }

    private static void lookAt_lh_mulAdd_unsafe_se98fc59c_v(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, double _eyez, double _eyex, double _eyey, double _t14, double _t15, double _t16, double _t22, double _t43, double _t44, double _t45, double _t54, double _t55, double _t56, double _t60) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, Math.fma(_eyez, _t44, Math.fma(_eyex, _t43, _eyey * _t45)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L))).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L)).withLane(3, -UnsafeOpsHolder.U.getDouble(_lb1)).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 16L)).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, -UnsafeOpsHolder.U.getDouble(_lb1 + 8L) * _t60 - UnsafeOpsHolder.U.getDouble(_lb1 + 16L) * _t22)))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment lookAt_lh_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment eye, long eyeOffset, java.lang.foreign.MemorySegment center, long centerOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        double _eyez = eye.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, eyeOffset + 16L);
        double _eyex = eye.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, eyeOffset);
        double _eyey = eye.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, eyeOffset + 8L);
        double _upz = up.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, upOffset + 16L);
        double _upx = up.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, upOffset);
        double _upy = up.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, upOffset + 8L);
        double _t0 = center.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, centerOffset + 16L) - _eyez;
        double _t1 = center.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, centerOffset) - _eyex;
        double _t2 = center.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, centerOffset + 8L) - _eyey;
        double _t9 = Math.fma(_t0, _t0, Math.fma(_t1, _t1, _t2 * _t2));
        double _t10 = (1.0 / java.lang.Math.sqrt(_t9));
        double _t14, _t15, _t16;
        if (_t9 != 0.0) {
            _t14 = _t1 * _t10;
            _t15 = _t0 * _t10;
            _t16 = _t2 * _t10;
        } else {
            _t14 = 0.0;
            _t15 = 0.0;
            _t16 = 0.0;
        }
        double _t22 = Math.fma(_eyez, _t15, Math.fma(_eyex, _t14, _eyey * _t16));
        double _t23 = -Math.fma(_upz, _t15, Math.fma(_upx, _t14, _upy * _t16));
        double _t24 = Math.fma(_t23, _t14, _upx);
        double _t25 = Math.fma(_t23, _t16, _upy);
        double _t26 = Math.fma(_t23, _t15, _upz);
        double _t33 = Math.fma(_t24, _t16, -(_t25 * _t14));
        double _t34 = Math.fma(_t25, _t15, -(_t26 * _t16));
        double _t35 = Math.fma(_t26, _t14, -(_t24 * _t15));
        double _t38 = Math.fma(_t33, _t33, Math.fma(_t34, _t34, _t35 * _t35));
        double _t39 = (1.0 / java.lang.Math.sqrt(_t38));
        double _t43, _t44, _t45;
        if (_t38 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t43 = _t34 * _t39;
            _t44 = _t33 * _t39;
            _t45 = _t35 * _t39;
        } else {
            _t43 = 0.0;
            _t44 = 0.0;
            _t45 = 0.0;
        }
        double _t54 = Math.fma(_t44, _t16, -(_t45 * _t15));
        double _t55 = Math.fma(_t43, _t15, -(_t44 * _t14));
        double _t56 = Math.fma(_t45, _t14, -(_t43 * _t16));
        double _t60 = Math.fma(_eyez, _t56, Math.fma(_eyex, _t54, _eyey * _t55));
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t43).withLane(1, _t45).withLane(2, _t44);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t54).withLane(1, _t55).withLane(2, _t56).withLane(3, Math.fma(_eyez, _t44, Math.fma(_eyex, _t43, _eyey * _t45)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t14).withLane(1, _t16).withLane(2, _t15);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))).add(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L)).withLane(3, -src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, -src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L) * _t60 - src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L) * _t22)))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
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
        double _eyez = UnsafeOpsHolder.U.getDouble(_eyeBase + 16L);
        double _eyex = UnsafeOpsHolder.U.getDouble(_eyeBase);
        double _eyey = UnsafeOpsHolder.U.getDouble(_eyeBase + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(_upBase + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(_upBase);
        double _upy = UnsafeOpsHolder.U.getDouble(_upBase + 8L);
        double _t3 = UnsafeOpsHolder.U.getDouble(_centerBase + 16L) - _eyez;
        double _t4 = UnsafeOpsHolder.U.getDouble(_centerBase) - _eyex;
        double _t5 = UnsafeOpsHolder.U.getDouble(_centerBase + 8L) - _eyey;
        double _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t17, _t18, _t19;
        if (_t12 != 0.0) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0;
            _t18 = 0.0;
            _t19 = 0.0;
        }
        double _t26 = -Math.fma(_upz, _t18, Math.fma(_upx, _t17, _upy * _t19));
        return lookAt_rh_fma_unsafe_s74e64677_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t17, _t18, _t19, Math.fma(_t26, _t19, _upy), Math.fma(_t26, _t17, _upx), Math.fma(_t26, _t18, _upz));
    }

    /** Piece 2 of {@code lookAt_rh_fma_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAt_rh_fma_unsafe_s74e64677_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _eyez, double _eyex, double _eyey, double _upz, double _upx, double _upy, double _t17, double _t18, double _t19, double _t27, double _t28, double _t29) {
        double _t36 = Math.fma(_t27, _t17, -(_t28 * _t19));
        double _t37 = Math.fma(_t28, _t18, -(_t29 * _t17));
        double _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        double _t41 = Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38));
        double _t42 = (1.0 / java.lang.Math.sqrt(_t41));
        double _t46, _t47, _t48;
        if (_t41 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0;
            _t47 = 0.0;
            _t48 = 0.0;
        }
        double _t57 = Math.fma(_t47, _t18, -(_t48 * _t19));
        double _t58 = Math.fma(_t48, _t17, -(_t46 * _t18));
        double _t59 = Math.fma(_t46, _t19, -(_t47 * _t17));
        lookAt_rh_fma_unsafe_sd81a7677_v(dest, destOffset, src.address() + srcOffset, _eyez, _eyex, _eyey, _t17, _t18, _t19, _t46, _t47, _t48, _t57, _t58, _t59, Math.fma(_eyez, _t48, Math.fma(_eyex, _t46, _eyey * _t47)), Math.fma(_eyez, _t59, Math.fma(_eyex, _t57, _eyey * _t58)));
        return dest;
    }

    private static void lookAt_rh_fma_unsafe_sd81a7677_v(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, double _eyez, double _eyex, double _eyey, double _t17, double _t18, double _t19, double _t46, double _t47, double _t48, double _t57, double _t58, double _t59, double _t61, double _t63) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, Math.fma(_eyez, _t18, Math.fma(_eyex, _t17, _eyey * _t19)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, -UnsafeOpsHolder.U.getDouble(_lb1 + 16L)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L))).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1)).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 16L)).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L)).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, -UnsafeOpsHolder.U.getDouble(_lb1) * _t61 - UnsafeOpsHolder.U.getDouble(_lb1 + 8L) * _t63)))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment lookAt_rh_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment eye, long eyeOffset, java.lang.foreign.MemorySegment center, long centerOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        double _eyez = eye.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, eyeOffset + 16L);
        double _eyex = eye.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, eyeOffset);
        double _eyey = eye.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, eyeOffset + 8L);
        double _upz = up.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, upOffset + 16L);
        double _upx = up.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, upOffset);
        double _upy = up.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, upOffset + 8L);
        double _t3 = center.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, centerOffset + 16L) - _eyez;
        double _t4 = center.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, centerOffset) - _eyex;
        double _t5 = center.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, centerOffset + 8L) - _eyey;
        double _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t17, _t18, _t19;
        if (_t12 != 0.0) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0;
            _t18 = 0.0;
            _t19 = 0.0;
        }
        double _t26 = -Math.fma(_upz, _t18, Math.fma(_upx, _t17, _upy * _t19));
        return lookAt_rh_fma_api_sb157d9f5_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t17, _t18, _t19, Math.fma(_t26, _t19, _upy), Math.fma(_t26, _t17, _upx), Math.fma(_t26, _t18, _upz));
    }

    /** Piece 2 of {@code lookAt_rh_fma_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAt_rh_fma_api_sb157d9f5_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _eyez, double _eyex, double _eyey, double _upz, double _upx, double _upy, double _t17, double _t18, double _t19, double _t27, double _t28, double _t29) {
        double _t36 = Math.fma(_t27, _t17, -(_t28 * _t19));
        double _t37 = Math.fma(_t28, _t18, -(_t29 * _t17));
        double _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        double _t41 = Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38));
        double _t42 = (1.0 / java.lang.Math.sqrt(_t41));
        double _t46, _t47, _t48;
        if (_t41 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0;
            _t47 = 0.0;
            _t48 = 0.0;
        }
        double _t57 = Math.fma(_t47, _t18, -(_t48 * _t19));
        double _t58 = Math.fma(_t48, _t17, -(_t46 * _t18));
        double _t59 = Math.fma(_t46, _t19, -(_t47 * _t17));
        lookAt_rh_fma_api_s168bfee1_v(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _t17, _t18, _t19, _t46, _t47, _t48, _t57, _t58, _t59, Math.fma(_eyez, _t48, Math.fma(_eyex, _t46, _eyey * _t47)), Math.fma(_eyez, _t59, Math.fma(_eyex, _t57, _eyey * _t58)));
        return dest;
    }

    private static void lookAt_rh_fma_api_s168bfee1_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _eyez, double _eyex, double _eyey, double _t17, double _t18, double _t19, double _t46, double _t47, double _t48, double _t57, double _t58, double _t59, double _t61, double _t63) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, Math.fma(_eyez, _t18, Math.fma(_eyex, _t17, _eyey * _t19)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, -src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))).add(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L)).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, -src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0) * _t61 - src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L) * _t63)))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
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
        double _eyez = UnsafeOpsHolder.U.getDouble(_eyeBase + 16L);
        double _eyex = UnsafeOpsHolder.U.getDouble(_eyeBase);
        double _eyey = UnsafeOpsHolder.U.getDouble(_eyeBase + 8L);
        double _upz = UnsafeOpsHolder.U.getDouble(_upBase + 16L);
        double _upx = UnsafeOpsHolder.U.getDouble(_upBase);
        double _upy = UnsafeOpsHolder.U.getDouble(_upBase + 8L);
        double _t3 = UnsafeOpsHolder.U.getDouble(_centerBase + 16L) - _eyez;
        double _t4 = UnsafeOpsHolder.U.getDouble(_centerBase) - _eyex;
        double _t5 = UnsafeOpsHolder.U.getDouble(_centerBase + 8L) - _eyey;
        double _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t17, _t18, _t19;
        if (_t12 != 0.0) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0;
            _t18 = 0.0;
            _t19 = 0.0;
        }
        double _t26 = -Math.fma(_upz, _t18, Math.fma(_upx, _t17, _upy * _t19));
        return lookAt_rh_mulAdd_unsafe_sabd9850a_1(dest, destOffset, src, srcOffset, _eyez, _eyex, _eyey, _upz, _upx, _upy, _t17, _t18, _t19, Math.fma(_t26, _t19, _upy), Math.fma(_t26, _t17, _upx), Math.fma(_t26, _t18, _upz));
    }

    /** Piece 2 of {@code lookAt_rh_mulAdd_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment lookAt_rh_mulAdd_unsafe_sabd9850a_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _eyez, double _eyex, double _eyey, double _upz, double _upx, double _upy, double _t17, double _t18, double _t19, double _t27, double _t28, double _t29) {
        double _t36 = Math.fma(_t27, _t17, -(_t28 * _t19));
        double _t37 = Math.fma(_t28, _t18, -(_t29 * _t17));
        double _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        double _t41 = Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38));
        double _t42 = (1.0 / java.lang.Math.sqrt(_t41));
        double _t46, _t47, _t48;
        if (_t41 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0;
            _t47 = 0.0;
            _t48 = 0.0;
        }
        double _t57 = Math.fma(_t47, _t18, -(_t48 * _t19));
        double _t58 = Math.fma(_t48, _t17, -(_t46 * _t18));
        double _t59 = Math.fma(_t46, _t19, -(_t47 * _t17));
        lookAt_rh_mulAdd_unsafe_sd03ef906_v(dest, destOffset, src.address() + srcOffset, _eyez, _eyex, _eyey, _t17, _t18, _t19, _t46, _t47, _t48, _t57, _t58, _t59, Math.fma(_eyez, _t48, Math.fma(_eyex, _t46, _eyey * _t47)), Math.fma(_eyez, _t59, Math.fma(_eyex, _t57, _eyey * _t58)));
        return dest;
    }

    private static void lookAt_rh_mulAdd_unsafe_sd03ef906_v(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, double _eyez, double _eyex, double _eyey, double _t17, double _t18, double _t19, double _t46, double _t47, double _t48, double _t57, double _t58, double _t59, double _t61, double _t63) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, Math.fma(_eyez, _t18, Math.fma(_eyex, _t17, _eyey * _t19)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, -UnsafeOpsHolder.U.getDouble(_lb1 + 16L)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L))).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1)).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 16L)).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L)).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, -UnsafeOpsHolder.U.getDouble(_lb1) * _t61 - UnsafeOpsHolder.U.getDouble(_lb1 + 8L) * _t63)))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment lookAt_rh_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment eye, long eyeOffset, java.lang.foreign.MemorySegment center, long centerOffset, java.lang.foreign.MemorySegment up, long upOffset) {
        double _eyez = eye.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, eyeOffset + 16L);
        double _eyex = eye.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, eyeOffset);
        double _eyey = eye.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, eyeOffset + 8L);
        double _upz = up.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, upOffset + 16L);
        double _upx = up.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, upOffset);
        double _upy = up.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, upOffset + 8L);
        double _t3 = center.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, centerOffset + 16L) - _eyez;
        double _t4 = center.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, centerOffset) - _eyex;
        double _t5 = center.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, centerOffset + 8L) - _eyey;
        double _t12 = Math.fma(_t3, _t3, Math.fma(_t4, _t4, _t5 * _t5));
        double _t13 = (1.0 / java.lang.Math.sqrt(_t12));
        double _t17, _t18, _t19;
        if (_t12 != 0.0) {
            _t17 = _t4 * _t13;
            _t18 = _t3 * _t13;
            _t19 = _t5 * _t13;
        } else {
            _t17 = 0.0;
            _t18 = 0.0;
            _t19 = 0.0;
        }
        double _t26 = -Math.fma(_upz, _t18, Math.fma(_upx, _t17, _upy * _t19));
        double _t27 = Math.fma(_t26, _t19, _upy);
        double _t28 = Math.fma(_t26, _t17, _upx);
        double _t29 = Math.fma(_t26, _t18, _upz);
        double _t36 = Math.fma(_t27, _t17, -(_t28 * _t19));
        double _t37 = Math.fma(_t28, _t18, -(_t29 * _t17));
        double _t38 = Math.fma(_t29, _t19, -(_t27 * _t18));
        double _t41 = Math.fma(_t36, _t36, Math.fma(_t37, _t37, _t38 * _t38));
        double _t42 = (1.0 / java.lang.Math.sqrt(_t41));
        double _t46, _t47, _t48;
        if (_t41 > Math.fma(Math.fma(_upz, _upz, Math.fma(_upx, _upx, _upy * _upy)), 5.048709793414476E-29, 2.2250738585072014E-308)) {
            _t46 = _t38 * _t42;
            _t47 = _t37 * _t42;
            _t48 = _t36 * _t42;
        } else {
            _t46 = 0.0;
            _t47 = 0.0;
            _t48 = 0.0;
        }
        double _t57 = Math.fma(_t47, _t18, -(_t48 * _t19));
        double _t58 = Math.fma(_t48, _t17, -(_t46 * _t18));
        double _t59 = Math.fma(_t46, _t19, -(_t47 * _t17));
        double _t61 = Math.fma(_eyez, _t48, Math.fma(_eyex, _t46, _eyey * _t47));
        double _t63 = Math.fma(_eyez, _t59, Math.fma(_eyex, _t57, _eyey * _t58));
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t17).withLane(1, _t19).withLane(2, _t18);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t46).withLane(1, _t47).withLane(2, _t48).withLane(3, Math.fma(_eyez, _t18, Math.fma(_eyex, _t17, _eyey * _t19)));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t57).withLane(1, _t58).withLane(2, _t59);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, -src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))).add(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L)).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, -src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0) * _t61 - src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L) * _t63)))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static double[] mapXYZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment mapXYZ(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 32L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static double[] mapXYnZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.fromArray(SIMD_SPECIES, src, _lo0).withLane(2, -src[_lo0 + 2]).intoArray(dest, destOffset + _li * 4);
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
            DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 32L, java.nio.ByteOrder.nativeOrder()).withLane(2, -UnsafeOpsHolder.U.getDouble(_srcBase + _li * 32L + 16L)).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment mapXYnZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.fromMemorySegment(SIMD_SPECIES, src, _lo0, java.nio.ByteOrder.nativeOrder()).withLane(2, -src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static double[] mapXnYZ(double[] dest, int destOffset, double[] src, int srcOffset) {
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.fromArray(SIMD_SPECIES, src, _lo0).withLane(1, -src[_lo0 + 1]).intoArray(dest, destOffset + _li * 4);
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
            DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 32L, java.nio.ByteOrder.nativeOrder()).withLane(1, -UnsafeOpsHolder.U.getDouble(_srcBase + _li * 32L + 8L)).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment mapXnYZ_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.fromMemorySegment(SIMD_SPECIES, src, _lo0, java.nio.ByteOrder.nativeOrder()).withLane(1, -src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L)).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static double[] preRotateAround(double[] dest, int destOffset, double[] src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        if (SimdSupport.USE_FMA) return preRotateAround_fma(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return preRotateAround_mulAdd(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
    }

    public static double[] preRotateAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        double _t0 = -rotY;
        double _t2 = -pivotZ;
        double _t3 = -rotX;
        double _t4 = rotX + rotX;
        double _t5 = rotY + rotY;
        double _t6 = rotZ + rotZ;
        double _t7 = rotW * _t5;
        double _t8 = rotW * _t6;
        double _t9 = rotZ * _t6;
        double _t10 = rotW * _t4;
        double _t14 = Math.fma(-rotZ, _t6, 1.0);
        double _t16 = Math.fma(rotZ, _t4, _t7);
        double _t17 = Math.fma(rotY, _t4, _t8);
        double _t18 = Math.fma(rotZ, _t5, _t10);
        double _t19 = Math.fma(rotY, _t4, -_t8);
        double _t20 = Math.fma(rotZ, _t5, -_t10);
        double _t21 = Math.fma(rotZ, _t4, -_t7);
        preRotateAround_fma_s5306fd9d_v(dest, destOffset, src, srcOffset, _t16, _t17, _t18, _t19, _t20, _t21, Math.fma(_t0, _t5, _t14), Math.fma(_t2, _t16, Math.fma(pivotX, Math.fma(rotY, _t5, _t9), -(pivotY * _t19))), Math.fma(_t3, _t4, _t14), Math.fma(_t2, _t20, Math.fma(pivotY, Math.fma(rotX, _t4, _t9), -(pivotX * _t17))), Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0)), Math.fma(-pivotY, _t18, Math.fma(pivotZ, Math.fma(rotX, _t4, rotY * _t5), -(pivotX * _t21))));
        return dest;
    }

    private static void preRotateAround_fma_s5306fd9d_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t16), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _h0), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, _t19), _sv3.withLane(3, _h1)))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t20), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t17), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, _h2), _sv3.withLane(3, _h3)))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _h4), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t21), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, _t18), _sv3.withLane(3, _h5)))).intoArray(dest, destOffset + 8);
    }

    public static double[] preRotateAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        double _t0 = -rotY;
        double _t2 = -pivotZ;
        double _t3 = -rotX;
        double _t4 = rotX + rotX;
        double _t5 = rotY + rotY;
        double _t6 = rotZ + rotZ;
        double _t7 = rotW * _t5;
        double _t8 = rotW * _t6;
        double _t9 = rotZ * _t6;
        double _t10 = rotW * _t4;
        double _t14 = Math.fma(-rotZ, _t6, 1.0);
        double _t16 = Math.fma(rotZ, _t4, _t7);
        double _t17 = Math.fma(rotY, _t4, _t8);
        double _t18 = Math.fma(rotZ, _t5, _t10);
        double _t19 = Math.fma(rotY, _t4, -_t8);
        double _t20 = Math.fma(rotZ, _t5, -_t10);
        double _t21 = Math.fma(rotZ, _t4, -_t7);
        preRotateAround_mulAdd_s3b7753b6_v(dest, destOffset, src, srcOffset, _t16, _t17, _t18, _t19, _t20, _t21, Math.fma(_t0, _t5, _t14), Math.fma(_t2, _t16, Math.fma(pivotX, Math.fma(rotY, _t5, _t9), -(pivotY * _t19))), Math.fma(_t3, _t4, _t14), Math.fma(_t2, _t20, Math.fma(pivotY, Math.fma(rotX, _t4, _t9), -(pivotX * _t17))), Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0)), Math.fma(-pivotY, _t18, Math.fma(pivotZ, Math.fma(rotX, _t4, rotY * _t5), -(pivotX * _t21))));
        return dest;
    }

    private static void preRotateAround_mulAdd_s3b7753b6_v(double[] dest, int destOffset, double[] src, int srcOffset, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t16)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h0)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t19)).add(_sv3.withLane(3, _h1)))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t20)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t17)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h2)).add(_sv3.withLane(3, _h3)))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h4)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t21)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t18)).add(_sv3.withLane(3, _h5)))).intoArray(dest, destOffset + 8);
    }

    public static java.lang.foreign.MemorySegment preRotateAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        if (SimdSupport.USE_FMA) return preRotateAround_fma(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return preRotateAround_mulAdd(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
    }

    public static java.lang.foreign.MemorySegment preRotateAround_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        double _t0 = -rotY;
        double _t2 = -pivotZ;
        double _t3 = -rotX;
        double _t4 = rotX + rotX;
        double _t5 = rotY + rotY;
        double _t6 = rotZ + rotZ;
        double _t7 = rotW * _t5;
        double _t8 = rotW * _t6;
        double _t9 = rotZ * _t6;
        double _t10 = rotW * _t4;
        double _t14 = Math.fma(-rotZ, _t6, 1.0);
        double _t16 = Math.fma(rotZ, _t4, _t7);
        double _t17 = Math.fma(rotY, _t4, _t8);
        double _t18 = Math.fma(rotZ, _t5, _t10);
        double _t19 = Math.fma(rotY, _t4, -_t8);
        double _t20 = Math.fma(rotZ, _t5, -_t10);
        double _t21 = Math.fma(rotZ, _t4, -_t7);
        preRotateAround_fma_sc28e1c6b_v(dest, destOffset, src, srcOffset, _t16, _t17, _t18, _t19, _t20, _t21, Math.fma(_t0, _t5, _t14), Math.fma(_t2, _t16, Math.fma(pivotX, Math.fma(rotY, _t5, _t9), -(pivotY * _t19))), Math.fma(_t3, _t4, _t14), Math.fma(_t2, _t20, Math.fma(pivotY, Math.fma(rotX, _t4, _t9), -(pivotX * _t17))), Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0)), Math.fma(-pivotY, _t18, Math.fma(pivotZ, Math.fma(rotX, _t4, rotY * _t5), -(pivotX * _t21))));
        return dest;
    }

    private static void preRotateAround_fma_sc28e1c6b_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t16), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _h0), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, _t19), _sv3.withLane(3, _h1)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t20), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t17), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, _h2), _sv3.withLane(3, _h3)))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _h4), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _t21), _sv2.fma(DoubleVector.broadcast(SIMD_SPECIES, _t18), _sv3.withLane(3, _h5)))).intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment preRotateAround_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        double _t0 = -rotY;
        double _t2 = -pivotZ;
        double _t3 = -rotX;
        double _t4 = rotX + rotX;
        double _t5 = rotY + rotY;
        double _t6 = rotZ + rotZ;
        double _t7 = rotW * _t5;
        double _t8 = rotW * _t6;
        double _t9 = rotZ * _t6;
        double _t10 = rotW * _t4;
        double _t14 = Math.fma(-rotZ, _t6, 1.0);
        double _t16 = Math.fma(rotZ, _t4, _t7);
        double _t17 = Math.fma(rotY, _t4, _t8);
        double _t18 = Math.fma(rotZ, _t5, _t10);
        double _t19 = Math.fma(rotY, _t4, -_t8);
        double _t20 = Math.fma(rotZ, _t5, -_t10);
        double _t21 = Math.fma(rotZ, _t4, -_t7);
        preRotateAround_mulAdd_sc2204460_v(dest, destOffset, src, srcOffset, _t16, _t17, _t18, _t19, _t20, _t21, Math.fma(_t0, _t5, _t14), Math.fma(_t2, _t16, Math.fma(pivotX, Math.fma(rotY, _t5, _t9), -(pivotY * _t19))), Math.fma(_t3, _t4, _t14), Math.fma(_t2, _t20, Math.fma(pivotY, Math.fma(rotX, _t4, _t9), -(pivotX * _t17))), Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0)), Math.fma(-pivotY, _t18, Math.fma(pivotZ, Math.fma(rotX, _t4, rotY * _t5), -(pivotX * _t21))));
        return dest;
    }

    private static void preRotateAround_mulAdd_sc2204460_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv3 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t16)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h0)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t19)).add(_sv3.withLane(3, _h1)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t20)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t17)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h2)).add(_sv3.withLane(3, _h3)))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h4)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _t21)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t18)).add(_sv3.withLane(3, _h5)))).intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
    }

    public static double[] preRotateAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] rot, int rotOffset, double[] pivot, int pivotOffset) {
        if (SimdSupport.USE_FMA) return preRotateAround_fma(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
        return preRotateAround_mulAdd(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
    }

    public static double[] preRotateAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] rot, int rotOffset, double[] pivot, int pivotOffset) {
        double _roty = rot[rotOffset + 1];
        double _pivotz = pivot[pivotOffset + 2];
        double _rotx = rot[rotOffset];
        double _rotz = rot[rotOffset + 2];
        double _rotw = rot[rotOffset + 3];
        double _t4 = _rotx + _rotx;
        double _t5 = _roty + _roty;
        double _t6 = _rotz + _rotz;
        double _t7 = _rotw * _t5;
        double _t8 = _rotw * _t6;
        double _t10 = _rotw * _t4;
        return preRotateAround_fma_sbdfa813d_1(dest, destOffset, src, srcOffset, _roty, _pivotz, _rotx, pivot[pivotOffset], pivot[pivotOffset + 1], -_roty, -_pivotz, -_rotx, _t4, _t5, _rotz * _t6, Math.fma(-_rotz, _t6, 1.0), Math.fma(_rotz, _t4, _t7), Math.fma(_roty, _t4, _t8), Math.fma(_rotz, _t5, _t10), Math.fma(_roty, _t4, -_t8), Math.fma(_rotz, _t5, -_t10), Math.fma(_rotz, _t4, -_t7));
    }

    /** Piece 2 of {@code preRotateAround_fma}, split to fit the inline budget; reached only through it. */
    private static double[] preRotateAround_fma_sbdfa813d_1(double[] dest, int destOffset, double[] src, int srcOffset, double _roty, double _pivotz, double _rotx, double _pivotx, double _pivoty, double _t0, double _t2, double _t3, double _t4, double _t5, double _t9, double _t14, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21) {
        preRotateAround_fma_s5306fd9d_v(dest, destOffset, src, srcOffset, _t16, _t17, _t18, _t19, _t20, _t21, Math.fma(_t0, _t5, _t14), Math.fma(_t2, _t16, Math.fma(_pivotx, Math.fma(_roty, _t5, _t9), -(_pivoty * _t19))), Math.fma(_t3, _t4, _t14), Math.fma(_t2, _t20, Math.fma(_pivoty, Math.fma(_rotx, _t4, _t9), -(_pivotx * _t17))), Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0)), Math.fma(-_pivoty, _t18, Math.fma(_pivotz, Math.fma(_rotx, _t4, _roty * _t5), -(_pivotx * _t21))));
        return dest;
    }

    public static double[] preRotateAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] rot, int rotOffset, double[] pivot, int pivotOffset) {
        double _roty = rot[rotOffset + 1];
        double _pivotz = pivot[pivotOffset + 2];
        double _rotx = rot[rotOffset];
        double _rotz = rot[rotOffset + 2];
        double _rotw = rot[rotOffset + 3];
        double _t4 = _rotx + _rotx;
        double _t5 = _roty + _roty;
        double _t6 = _rotz + _rotz;
        double _t7 = _rotw * _t5;
        double _t8 = _rotw * _t6;
        double _t10 = _rotw * _t4;
        return preRotateAround_mulAdd_sd915c628_1(dest, destOffset, src, srcOffset, _roty, _pivotz, _rotx, pivot[pivotOffset], pivot[pivotOffset + 1], -_roty, -_pivotz, -_rotx, _t4, _t5, _rotz * _t6, Math.fma(-_rotz, _t6, 1.0), Math.fma(_rotz, _t4, _t7), Math.fma(_roty, _t4, _t8), Math.fma(_rotz, _t5, _t10), Math.fma(_roty, _t4, -_t8), Math.fma(_rotz, _t5, -_t10), Math.fma(_rotz, _t4, -_t7));
    }

    /** Piece 2 of {@code preRotateAround_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] preRotateAround_mulAdd_sd915c628_1(double[] dest, int destOffset, double[] src, int srcOffset, double _roty, double _pivotz, double _rotx, double _pivotx, double _pivoty, double _t0, double _t2, double _t3, double _t4, double _t5, double _t9, double _t14, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21) {
        preRotateAround_mulAdd_s3b7753b6_v(dest, destOffset, src, srcOffset, _t16, _t17, _t18, _t19, _t20, _t21, Math.fma(_t0, _t5, _t14), Math.fma(_t2, _t16, Math.fma(_pivotx, Math.fma(_roty, _t5, _t9), -(_pivoty * _t19))), Math.fma(_t3, _t4, _t14), Math.fma(_t2, _t20, Math.fma(_pivoty, Math.fma(_rotx, _t4, _t9), -(_pivotx * _t17))), Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0)), Math.fma(-_pivoty, _t18, Math.fma(_pivotz, Math.fma(_rotx, _t4, _roty * _t5), -(_pivotx * _t21))));
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
        double _roty = UnsafeOpsHolder.U.getDouble(_rotBase + 8L);
        double _pivotz = UnsafeOpsHolder.U.getDouble(_pivotBase + 16L);
        double _rotx = UnsafeOpsHolder.U.getDouble(_rotBase);
        double _rotz = UnsafeOpsHolder.U.getDouble(_rotBase + 16L);
        double _rotw = UnsafeOpsHolder.U.getDouble(_rotBase + 24L);
        double _pivotx = UnsafeOpsHolder.U.getDouble(_pivotBase);
        double _pivoty = UnsafeOpsHolder.U.getDouble(_pivotBase + 8L);
        double _t4 = _rotx + _rotx;
        double _t5 = _roty + _roty;
        double _t6 = _rotz + _rotz;
        double _t7 = _rotw * _t5;
        double _t8 = _rotw * _t6;
        double _t10 = _rotw * _t4;
        return preRotateAround_fma_unsafe_s9a2dd742_1(dest, destOffset, src, srcOffset, _roty, _pivotz, _rotx, _pivotx, _pivoty, -_roty, -_pivotz, -_rotx, _t4, _t5, _rotz * _t6, Math.fma(-_rotz, _t6, 1.0), Math.fma(_rotz, _t4, _t7), Math.fma(_roty, _t4, _t8), Math.fma(_rotz, _t5, _t10), Math.fma(_roty, _t4, -_t8), Math.fma(_rotz, _t5, -_t10), Math.fma(_rotz, _t4, -_t7));
    }

    /** Piece 2 of {@code preRotateAround_fma_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_fma_unsafe_s9a2dd742_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _roty, double _pivotz, double _rotx, double _pivotx, double _pivoty, double _t0, double _t2, double _t3, double _t4, double _t5, double _t9, double _t14, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21) {
        preRotateAround_fma_sc28e1c6b_v(dest, destOffset, src, srcOffset, _t16, _t17, _t18, _t19, _t20, _t21, Math.fma(_t0, _t5, _t14), Math.fma(_t2, _t16, Math.fma(_pivotx, Math.fma(_roty, _t5, _t9), -(_pivoty * _t19))), Math.fma(_t3, _t4, _t14), Math.fma(_t2, _t20, Math.fma(_pivoty, Math.fma(_rotx, _t4, _t9), -(_pivotx * _t17))), Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0)), Math.fma(-_pivoty, _t18, Math.fma(_pivotz, Math.fma(_rotx, _t4, _roty * _t5), -(_pivotx * _t21))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAround_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment rot, long rotOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        double _roty = rot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rotOffset + 8L);
        double _pivotz = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset + 16L);
        double _rotx = rot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rotOffset);
        double _rotz = rot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rotOffset + 16L);
        double _rotw = rot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rotOffset + 24L);
        double _pivotx = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset);
        double _pivoty = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L);
        double _t4 = _rotx + _rotx;
        double _t5 = _roty + _roty;
        double _t6 = _rotz + _rotz;
        double _t7 = _rotw * _t5;
        double _t8 = _rotw * _t6;
        double _t10 = _rotw * _t4;
        return preRotateAround_fma_api_s47bde860_1(dest, destOffset, src, srcOffset, _roty, _pivotz, _rotx, _pivotx, _pivoty, -_roty, -_pivotz, -_rotx, _t4, _t5, _rotz * _t6, Math.fma(-_rotz, _t6, 1.0), Math.fma(_rotz, _t4, _t7), Math.fma(_roty, _t4, _t8), Math.fma(_rotz, _t5, _t10), Math.fma(_roty, _t4, -_t8), Math.fma(_rotz, _t5, -_t10), Math.fma(_rotz, _t4, -_t7));
    }

    /** Piece 2 of {@code preRotateAround_fma_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_fma_api_s47bde860_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _roty, double _pivotz, double _rotx, double _pivotx, double _pivoty, double _t0, double _t2, double _t3, double _t4, double _t5, double _t9, double _t14, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21) {
        preRotateAround_fma_sc28e1c6b_v(dest, destOffset, src, srcOffset, _t16, _t17, _t18, _t19, _t20, _t21, Math.fma(_t0, _t5, _t14), Math.fma(_t2, _t16, Math.fma(_pivotx, Math.fma(_roty, _t5, _t9), -(_pivoty * _t19))), Math.fma(_t3, _t4, _t14), Math.fma(_t2, _t20, Math.fma(_pivoty, Math.fma(_rotx, _t4, _t9), -(_pivotx * _t17))), Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0)), Math.fma(-_pivoty, _t18, Math.fma(_pivotz, Math.fma(_rotx, _t4, _roty * _t5), -(_pivotx * _t21))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAround_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment rot, long rotOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && rot.isNative() && pivot.isNative()) return preRotateAround_mulAdd_unsafe(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
        return preRotateAround_mulAdd_api(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
    }

    public static java.lang.foreign.MemorySegment preRotateAround_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment rot, long rotOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        long _rotBase = rot.address() + rotOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        double _roty = UnsafeOpsHolder.U.getDouble(_rotBase + 8L);
        double _pivotz = UnsafeOpsHolder.U.getDouble(_pivotBase + 16L);
        double _rotx = UnsafeOpsHolder.U.getDouble(_rotBase);
        double _rotz = UnsafeOpsHolder.U.getDouble(_rotBase + 16L);
        double _rotw = UnsafeOpsHolder.U.getDouble(_rotBase + 24L);
        double _pivotx = UnsafeOpsHolder.U.getDouble(_pivotBase);
        double _pivoty = UnsafeOpsHolder.U.getDouble(_pivotBase + 8L);
        double _t4 = _rotx + _rotx;
        double _t5 = _roty + _roty;
        double _t6 = _rotz + _rotz;
        double _t7 = _rotw * _t5;
        double _t8 = _rotw * _t6;
        double _t10 = _rotw * _t4;
        return preRotateAround_mulAdd_unsafe_sdc4f7dd5_1(dest, destOffset, src, srcOffset, _roty, _pivotz, _rotx, _pivotx, _pivoty, -_roty, -_pivotz, -_rotx, _t4, _t5, _rotz * _t6, Math.fma(-_rotz, _t6, 1.0), Math.fma(_rotz, _t4, _t7), Math.fma(_roty, _t4, _t8), Math.fma(_rotz, _t5, _t10), Math.fma(_roty, _t4, -_t8), Math.fma(_rotz, _t5, -_t10), Math.fma(_rotz, _t4, -_t7));
    }

    /** Piece 2 of {@code preRotateAround_mulAdd_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_mulAdd_unsafe_sdc4f7dd5_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _roty, double _pivotz, double _rotx, double _pivotx, double _pivoty, double _t0, double _t2, double _t3, double _t4, double _t5, double _t9, double _t14, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21) {
        preRotateAround_mulAdd_sc2204460_v(dest, destOffset, src, srcOffset, _t16, _t17, _t18, _t19, _t20, _t21, Math.fma(_t0, _t5, _t14), Math.fma(_t2, _t16, Math.fma(_pivotx, Math.fma(_roty, _t5, _t9), -(_pivoty * _t19))), Math.fma(_t3, _t4, _t14), Math.fma(_t2, _t20, Math.fma(_pivoty, Math.fma(_rotx, _t4, _t9), -(_pivotx * _t17))), Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0)), Math.fma(-_pivoty, _t18, Math.fma(_pivotz, Math.fma(_rotx, _t4, _roty * _t5), -(_pivotx * _t21))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAround_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment rot, long rotOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        double _roty = rot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rotOffset + 8L);
        double _pivotz = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset + 16L);
        double _rotx = rot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rotOffset);
        double _rotz = rot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rotOffset + 16L);
        double _rotw = rot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rotOffset + 24L);
        double _pivotx = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset);
        double _pivoty = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L);
        double _t4 = _rotx + _rotx;
        double _t5 = _roty + _roty;
        double _t6 = _rotz + _rotz;
        double _t7 = _rotw * _t5;
        double _t8 = _rotw * _t6;
        double _t10 = _rotw * _t4;
        return preRotateAround_mulAdd_api_s4f92099d_1(dest, destOffset, src, srcOffset, _roty, _pivotz, _rotx, _pivotx, _pivoty, -_roty, -_pivotz, -_rotx, _t4, _t5, _rotz * _t6, Math.fma(-_rotz, _t6, 1.0), Math.fma(_rotz, _t4, _t7), Math.fma(_roty, _t4, _t8), Math.fma(_rotz, _t5, _t10), Math.fma(_roty, _t4, -_t8), Math.fma(_rotz, _t5, -_t10), Math.fma(_rotz, _t4, -_t7));
    }

    /** Piece 2 of {@code preRotateAround_mulAdd_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment preRotateAround_mulAdd_api_s4f92099d_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _roty, double _pivotz, double _rotx, double _pivotx, double _pivoty, double _t0, double _t2, double _t3, double _t4, double _t5, double _t9, double _t14, double _t16, double _t17, double _t18, double _t19, double _t20, double _t21) {
        preRotateAround_mulAdd_sc2204460_v(dest, destOffset, src, srcOffset, _t16, _t17, _t18, _t19, _t20, _t21, Math.fma(_t0, _t5, _t14), Math.fma(_t2, _t16, Math.fma(_pivotx, Math.fma(_roty, _t5, _t9), -(_pivoty * _t19))), Math.fma(_t3, _t4, _t14), Math.fma(_t2, _t20, Math.fma(_pivoty, Math.fma(_rotx, _t4, _t9), -(_pivotx * _t17))), Math.fma(_t3, _t4, Math.fma(_t0, _t5, 1.0)), Math.fma(-_pivoty, _t18, Math.fma(_pivotz, Math.fma(_rotx, _t4, _roty * _t5), -(_pivotx * _t21))));
        return dest;
    }

    public static double[] preRotateAxis(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (SimdSupport.USE_FMA) return preRotateAxis_fma(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return preRotateAxis_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    public static double[] preRotateAxis_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisZ;
        double _t4 = axisX * axisY;
        double _t6 = axisY * axisZ;
        double _t11 = 1.0 - _t1;
        preRotateAxis_fma_s995bbb1c_v(dest, destOffset, src, srcOffset, Math.fma(axisY, _t0, _t11 * _t2), Math.fma(_t11, axisX * axisX, _t1), Math.fma(_t11, _t4, -(axisZ * _t0)), Math.fma(_t11, _t6, -(axisX * _t0)), Math.fma(axisZ, _t0, _t11 * _t4), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(_t11, _t2, -(axisY * _t0)), Math.fma(axisX, _t0, _t11 * _t6));
        return dest;
    }

    private static void preRotateAxis_fma_s995bbb1c_v(double[] dest, int destOffset, double[] src, int srcOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _h0), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _h1), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h2)))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _h3), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _h4), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h5)))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _h6), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, _h7), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h8)))).intoArray(dest, destOffset + 8);
    }

    public static double[] preRotateAxis_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        preRotateAxis_mulAdd_sbf6928a1_v(dest, destOffset, src, srcOffset, axisX, axisY, axisZ, _t0, _t1, axisX * axisZ, axisX * axisY, axisY * axisZ, 1.0 - _t1);
        return dest;
    }

    private static void preRotateAxis_mulAdd_sbf6928a1_v(double[] dest, int destOffset, double[] src, int srcOffset, double axisX, double axisY, double axisZ, double _t0, double _t1, double _t2, double _t4, double _t6, double _t11) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(axisY, _t0, _t11 * _t2))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, axisX * axisX, _t1))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t4, -(axisZ * _t0)))))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t6, -(axisX * _t0)))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(axisZ, _t0, _t11 * _t4))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, axisY * axisY, _t1))))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, axisZ * axisZ, _t1))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t2, -(axisY * _t0)))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(axisX, _t0, _t11 * _t6))))).intoArray(dest, destOffset + 8);
    }

    public static java.lang.foreign.MemorySegment preRotateAxis(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (SimdSupport.USE_FMA) return preRotateAxis_fma(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return preRotateAxis_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    public static java.lang.foreign.MemorySegment preRotateAxis_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        preRotateAxis_fma_se6f90042_v(dest, destOffset, src, srcOffset, axisX, axisY, axisZ, _t0, _t1, axisX * axisZ, axisX * axisY, axisY * axisZ, 1.0 - _t1);
        return dest;
    }

    private static void preRotateAxis_fma_se6f90042_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double axisX, double axisY, double axisZ, double _t0, double _t1, double _t2, double _t4, double _t6, double _t11) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(axisY, _t0, _t11 * _t2)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, axisX * axisX, _t1)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t4, -(axisZ * _t0)))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t6, -(axisX * _t0))), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(axisZ, _t0, _t11 * _t4)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, axisY * axisY, _t1))))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, axisZ * axisZ, _t1)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t2, -(axisY * _t0))), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(axisX, _t0, _t11 * _t6))))).intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment preRotateAxis_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisZ;
        double _t4 = axisX * axisY;
        double _t6 = axisY * axisZ;
        double _t11 = 1.0 - _t1;
        preRotateAxis_mulAdd_s316ac7ab_v(dest, destOffset, src, srcOffset, Math.fma(axisY, _t0, _t11 * _t2), Math.fma(_t11, axisX * axisX, _t1), Math.fma(_t11, _t4, -(axisZ * _t0)), Math.fma(_t11, _t6, -(axisX * _t0)), Math.fma(axisZ, _t0, _t11 * _t4), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(_t11, _t2, -(axisY * _t0)), Math.fma(axisX, _t0, _t11 * _t6));
        return dest;
    }

    private static void preRotateAxis_mulAdd_s316ac7ab_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h0)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h1)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h2)))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h3)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h4)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h5)))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _h6)).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, _h7)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _h8)))).intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
    }

    public static double[] preRotateAxis(double[] dest, int destOffset, double[] src, int srcOffset, double[] axis, int axisOffset, double angle) {
        if (SimdSupport.USE_FMA) return preRotateAxis_fma(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return preRotateAxis_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    public static double[] preRotateAxis_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] axis, int axisOffset, double angle) {
        double _axisy = axis[axisOffset + 1];
        double _axisx = axis[axisOffset];
        double _axisz = axis[axisOffset + 2];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        preRotateAxis_fma_s703295d2_v(dest, destOffset, src, srcOffset, _axisy, _axisx, _axisz, _t0, _t1, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, 1.0 - _t1);
        return dest;
    }

    private static void preRotateAxis_fma_s703295d2_v(double[] dest, int destOffset, double[] src, int srcOffset, double _axisy, double _axisx, double _axisz, double _t0, double _t1, double _t2, double _t4, double _t6, double _t11) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_axisy, _t0, _t11 * _t2)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisx * _axisx, _t1)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t4, -(_axisz * _t0)))))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t6, -(_axisx * _t0))), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_axisz, _t0, _t11 * _t4)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisy * _axisy, _t1))))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisz * _axisz, _t1)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t2, -(_axisy * _t0))), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_axisx, _t0, _t11 * _t6))))).intoArray(dest, destOffset + 8);
    }

    public static double[] preRotateAxis_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] axis, int axisOffset, double angle) {
        double _axisy = axis[axisOffset + 1];
        double _axisx = axis[axisOffset];
        double _axisz = axis[axisOffset + 2];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        preRotateAxis_mulAdd_sb318cd01_v(dest, destOffset, src, srcOffset, _axisy, _axisx, _axisz, _t0, _t1, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, 1.0 - _t1);
        return dest;
    }

    private static void preRotateAxis_mulAdd_sb318cd01_v(double[] dest, int destOffset, double[] src, int srcOffset, double _axisy, double _axisx, double _axisz, double _t0, double _t1, double _t2, double _t4, double _t6, double _t11) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_axisy, _t0, _t11 * _t2))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisx * _axisx, _t1))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t4, -(_axisz * _t0)))))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t6, -(_axisx * _t0)))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_axisz, _t0, _t11 * _t4))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisy * _axisy, _t1))))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisz * _axisz, _t1))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t2, -(_axisy * _t0)))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_axisx, _t0, _t11 * _t6))))).intoArray(dest, destOffset + 8);
    }

    public static java.lang.foreign.MemorySegment preRotateAxis(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && axis.isNative()) return preRotateAxis_fma_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
            return preRotateAxis_fma_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && axis.isNative()) return preRotateAxis_mulAdd_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return preRotateAxis_mulAdd_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    public static java.lang.foreign.MemorySegment preRotateAxis_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && axis.isNative()) return preRotateAxis_fma_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return preRotateAxis_fma_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    public static java.lang.foreign.MemorySegment preRotateAxis_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        long _axisBase = axis.address() + axisOffset;
        double _axisy = UnsafeOpsHolder.U.getDouble(_axisBase + 8L);
        double _axisx = UnsafeOpsHolder.U.getDouble(_axisBase);
        double _axisz = UnsafeOpsHolder.U.getDouble(_axisBase + 16L);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        preRotateAxis_fma_unsafe_s7e57fbeb_v(dest, destOffset, src, srcOffset, _axisy, _axisx, _axisz, _t0, _t1, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, 1.0 - _t1);
        return dest;
    }

    private static void preRotateAxis_fma_unsafe_s7e57fbeb_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _axisy, double _axisx, double _axisz, double _t0, double _t1, double _t2, double _t4, double _t6, double _t11) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_axisy, _t0, _t11 * _t2)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisx * _axisx, _t1)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t4, -(_axisz * _t0)))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t6, -(_axisx * _t0))), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_axisz, _t0, _t11 * _t4)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisy * _axisy, _t1))))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _axisz * _axisz, _t1)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t11, _t2, -(_axisy * _t0))), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_axisx, _t0, _t11 * _t6))))).intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment preRotateAxis_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        double _axisy = axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset + 8L);
        double _axisx = axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset);
        double _axisz = axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset + 16L);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        preRotateAxis_fma_unsafe_s7e57fbeb_v(dest, destOffset, src, srcOffset, _axisy, _axisx, _axisz, _t0, _t1, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, 1.0 - _t1);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAxis_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && axis.isNative()) return preRotateAxis_mulAdd_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return preRotateAxis_mulAdd_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    public static java.lang.foreign.MemorySegment preRotateAxis_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        long _axisBase = axis.address() + axisOffset;
        double _axisx = UnsafeOpsHolder.U.getDouble(_axisBase);
        double _axisz = UnsafeOpsHolder.U.getDouble(_axisBase + 16L);
        double _axisy = UnsafeOpsHolder.U.getDouble(_axisBase + 8L);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = _axisx * _axisz;
        double _t4 = _axisx * _axisy;
        double _t6 = _axisy * _axisz;
        double _t11 = 1.0 - _t1;
        preRotateAxis_mulAdd_s316ac7ab_v(dest, destOffset, src, srcOffset, Math.fma(_axisy, _t0, _t11 * _t2), Math.fma(_t11, _axisx * _axisx, _t1), Math.fma(_t11, _t4, -(_axisz * _t0)), Math.fma(_t11, _t6, -(_axisx * _t0)), Math.fma(_axisz, _t0, _t11 * _t4), Math.fma(_t11, _axisy * _axisy, _t1), Math.fma(_t11, _axisz * _axisz, _t1), Math.fma(_t11, _t2, -(_axisy * _t0)), Math.fma(_axisx, _t0, _t11 * _t6));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateAxis_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        double _axisx = axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset);
        double _axisz = axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset + 16L);
        double _axisy = axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset + 8L);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = _axisx * _axisz;
        double _t4 = _axisx * _axisy;
        double _t6 = _axisy * _axisz;
        double _t11 = 1.0 - _t1;
        preRotateAxis_mulAdd_s316ac7ab_v(dest, destOffset, src, srcOffset, Math.fma(_axisy, _t0, _t11 * _t2), Math.fma(_t11, _axisx * _axisx, _t1), Math.fma(_t11, _t4, -(_axisz * _t0)), Math.fma(_t11, _t6, -(_axisx * _t0)), Math.fma(_axisz, _t0, _t11 * _t4), Math.fma(_t11, _axisy * _axisy, _t1), Math.fma(_t11, _axisz * _axisz, _t1), Math.fma(_t11, _t2, -(_axisy * _t0)), Math.fma(_axisx, _t0, _t11 * _t6));
        return dest;
    }

    public static double[] preRotateQuat(double[] dest, int destOffset, double[] src, int srcOffset, double qX, double qY, double qZ, double qW) {
        if (SimdSupport.USE_FMA) return preRotateQuat_fma(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return preRotateQuat_mulAdd(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
    }

    public static double[] preRotateQuat_fma(double[] dest, int destOffset, double[] src, int srcOffset, double qX, double qY, double qZ, double qW) {
        double _t0 = -qY;
        double _t2 = -qX;
        double _t3 = qX + qX;
        double _t4 = qY + qY;
        double _t5 = qZ + qZ;
        double _t6 = qW * _t4;
        double _t7 = qW * _t5;
        double _t8 = qW * _t3;
        double _t12 = Math.fma(-qZ, _t5, 1.0);
        preRotateAxis_fma_s995bbb1c_v(dest, destOffset, src, srcOffset, Math.fma(qZ, _t3, _t6), Math.fma(_t0, _t4, _t12), Math.fma(qY, _t3, -_t7), Math.fma(qZ, _t4, -_t8), Math.fma(qY, _t3, _t7), Math.fma(_t2, _t3, _t12), Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)), Math.fma(qZ, _t3, -_t6), Math.fma(qZ, _t4, _t8));
        return dest;
    }

    public static double[] preRotateQuat_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double qX, double qY, double qZ, double qW) {
        double _t3 = qX + qX;
        double _t4 = qY + qY;
        double _t5 = qZ + qZ;
        preRotateQuat_mulAdd_s51543db2_v(dest, destOffset, src, srcOffset, qY, qZ, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, Math.fma(-qZ, _t5, 1.0));
        return dest;
    }

    private static void preRotateQuat_mulAdd_s51543db2_v(double[] dest, int destOffset, double[] src, int srcOffset, double qY, double qZ, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t3, _t6))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t4, _t12))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qY, _t3, -_t7))))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t4, -_t8))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qY, _t3, _t7))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, _t12))))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t3, -_t6))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t4, _t8))))).intoArray(dest, destOffset + 8);
    }

    public static java.lang.foreign.MemorySegment preRotateQuat(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double qX, double qY, double qZ, double qW) {
        if (SimdSupport.USE_FMA) return preRotateQuat_fma(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return preRotateQuat_mulAdd(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
    }

    public static java.lang.foreign.MemorySegment preRotateQuat_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double qX, double qY, double qZ, double qW) {
        double _t3 = qX + qX;
        double _t4 = qY + qY;
        double _t5 = qZ + qZ;
        preRotateQuat_fma_s1197b2b9_v(dest, destOffset, src, srcOffset, qY, qZ, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, Math.fma(-qZ, _t5, 1.0));
        return dest;
    }

    private static void preRotateQuat_fma_s1197b2b9_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double qY, double qZ, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t3, _t6)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t4, _t12)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qY, _t3, -_t7))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t4, -_t8)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qY, _t3, _t7)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, _t12))))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0))), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t3, -_t6)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t4, _t8))))).intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment preRotateQuat_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double qX, double qY, double qZ, double qW) {
        double _t3 = qX + qX;
        double _t4 = qY + qY;
        double _t5 = qZ + qZ;
        preRotateQuat_mulAdd_s2e81c04c_v(dest, destOffset, src, srcOffset, qY, qZ, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, Math.fma(-qZ, _t5, 1.0));
        return dest;
    }

    private static void preRotateQuat_mulAdd_s2e81c04c_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double qY, double qZ, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t3, _t6))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t4, _t12))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qY, _t3, -_t7))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t4, -_t8))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qY, _t3, _t7))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, _t12))))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t3, -_t6))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(qZ, _t4, _t8))))).intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
    }

    public static double[] preRotateQuat(double[] dest, int destOffset, double[] src, int srcOffset, double[] q, int qOffset) {
        if (SimdSupport.USE_FMA) return preRotateQuat_fma(dest, destOffset, src, srcOffset, q, qOffset);
        return preRotateQuat_mulAdd(dest, destOffset, src, srcOffset, q, qOffset);
    }

    public static double[] preRotateQuat_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] q, int qOffset) {
        double _qz = q[qOffset + 2];
        double _qy = q[qOffset + 1];
        double _qx = q[qOffset];
        double _qw = q[qOffset + 3];
        double _t3 = _qx + _qx;
        double _t4 = _qy + _qy;
        double _t5 = _qz + _qz;
        preRotateQuat_fma_s78c6ffd6_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0));
        return dest;
    }

    private static void preRotateQuat_fma_s78c6ffd6_v(double[] dest, int destOffset, double[] src, int srcOffset, double _qz, double _qy, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t3, _t6)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t4, _t12)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qy, _t3, -_t7))))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t4, -_t8)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qy, _t3, _t7)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, _t12))))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0))), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t3, -_t6)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t4, _t8))))).intoArray(dest, destOffset + 8);
    }

    public static double[] preRotateQuat_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] q, int qOffset) {
        double _qz = q[qOffset + 2];
        double _qy = q[qOffset + 1];
        double _qx = q[qOffset];
        double _qw = q[qOffset + 3];
        double _t3 = _qx + _qx;
        double _t4 = _qy + _qy;
        double _t5 = _qz + _qz;
        preRotateQuat_mulAdd_sa065509_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0));
        return dest;
    }

    private static void preRotateQuat_mulAdd_sa065509_v(double[] dest, int destOffset, double[] src, int srcOffset, double _qz, double _qy, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _sv1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t3, _t6))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t4, _t12))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qy, _t3, -_t7))))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t4, -_t8))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qy, _t3, _t7))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, _t12))))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t3, -_t6))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t4, _t8))))).intoArray(dest, destOffset + 8);
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
        double _qz = UnsafeOpsHolder.U.getDouble(_qBase + 16L);
        double _qy = UnsafeOpsHolder.U.getDouble(_qBase + 8L);
        double _qx = UnsafeOpsHolder.U.getDouble(_qBase);
        double _qw = UnsafeOpsHolder.U.getDouble(_qBase + 24L);
        double _t3 = _qx + _qx;
        double _t4 = _qy + _qy;
        double _t5 = _qz + _qz;
        preRotateQuat_fma_unsafe_s15d63f2d_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0));
        return dest;
    }

    private static void preRotateQuat_fma_unsafe_s15d63f2d_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _qz, double _qy, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t3, _t6)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t4, _t12)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qy, _t3, -_t7))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t4, -_t8)), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qy, _t3, _t7)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, _t12))))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0))), _sv1.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t3, -_t6)), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t4, _t8))))).intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment preRotateQuat_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        double _qz = q.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, qOffset + 16L);
        double _qy = q.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, qOffset + 8L);
        double _qx = q.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, qOffset);
        double _qw = q.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, qOffset + 24L);
        double _t3 = _qx + _qx;
        double _t4 = _qy + _qy;
        double _t5 = _qz + _qz;
        preRotateQuat_fma_unsafe_s15d63f2d_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0));
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateQuat_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && q.isNative()) return preRotateQuat_mulAdd_unsafe(dest, destOffset, src, srcOffset, q, qOffset);
        return preRotateQuat_mulAdd_api(dest, destOffset, src, srcOffset, q, qOffset);
    }

    public static java.lang.foreign.MemorySegment preRotateQuat_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        long _qBase = q.address() + qOffset;
        double _qz = UnsafeOpsHolder.U.getDouble(_qBase + 16L);
        double _qy = UnsafeOpsHolder.U.getDouble(_qBase + 8L);
        double _qx = UnsafeOpsHolder.U.getDouble(_qBase);
        double _qw = UnsafeOpsHolder.U.getDouble(_qBase + 24L);
        double _t3 = _qx + _qx;
        double _t4 = _qy + _qy;
        double _t5 = _qz + _qz;
        preRotateQuat_mulAdd_unsafe_se1af62fc_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0));
        return dest;
    }

    private static void preRotateQuat_mulAdd_unsafe_se1af62fc_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _qz, double _qy, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t3, _t6))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t0, _t4, _t12))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qy, _t3, -_t7))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t4, -_t8))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qy, _t3, _t7))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, _t12))))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)))).add(_sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t3, -_t6))).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.fma(_qz, _t4, _t8))))).intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment preRotateQuat_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        double _qz = q.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, qOffset + 16L);
        double _qy = q.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, qOffset + 8L);
        double _qx = q.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, qOffset);
        double _qw = q.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, qOffset + 24L);
        double _t3 = _qx + _qx;
        double _t4 = _qy + _qy;
        double _t5 = _qz + _qz;
        preRotateQuat_mulAdd_unsafe_se1af62fc_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0));
        return dest;
    }

    public static double[] preRotateX(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        if (SimdSupport.USE_FMA) return preRotateX_fma(dest, destOffset, src, srcOffset, angle);
        return preRotateX_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static double[] preRotateX_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
        _sv0.fma(_sv1, _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0))).intoArray(dest, destOffset + 4);
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t0), _sv2.mul(_sv1)).intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preRotateX_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).intoArray(dest, destOffset);
        _sv0.mul(_sv1).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0))).intoArray(dest, destOffset + 4);
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t0)).add(_sv2.mul(_sv1)).intoArray(dest, destOffset + 8);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateX(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        if (SimdSupport.USE_FMA) return preRotateX_fma(dest, destOffset, src, srcOffset, angle);
        return preRotateX_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static java.lang.foreign.MemorySegment preRotateX_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder());
        DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(_sv1, _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t0), _sv2.mul(_sv1)).intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateX_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder());
        DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(_sv1).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0))).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t0)).add(_sv2.mul(_sv1)).intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static double[] preRotateY(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        if (SimdSupport.USE_FMA) return preRotateY_fma(dest, destOffset, src, srcOffset, angle);
        return preRotateY_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static double[] preRotateY_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        _sv0.fma(_sv1, _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t0))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _sv2.fma(_sv1, _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0))).intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preRotateY_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        _sv0.mul(_sv1).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t0))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _sv2.mul(_sv1).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0))).intoArray(dest, destOffset + 8);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateY(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        if (SimdSupport.USE_FMA) return preRotateY_fma(dest, destOffset, src, srcOffset, angle);
        return preRotateY_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static java.lang.foreign.MemorySegment preRotateY_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder());
        var _c1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(_sv1, _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t0))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv2.fma(_sv1, _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0))).intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateY_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder());
        var _c1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(_sv1).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t0))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _sv2.mul(_sv1).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0))).intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static double[] preRotateZ(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        if (SimdSupport.USE_FMA) return preRotateZ_fma(dest, destOffset, src, srcOffset, angle);
        return preRotateZ_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static double[] preRotateZ_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        _sv0.fma(_sv1, _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0))).intoArray(dest, destOffset);
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t0), _sv2.mul(_sv1)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preRotateZ_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4);
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8);
        _sv0.mul(_sv1).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0))).intoArray(dest, destOffset);
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t0)).add(_sv2.mul(_sv1)).intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateZ(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        if (SimdSupport.USE_FMA) return preRotateZ_fma(dest, destOffset, src, srcOffset, angle);
        return preRotateZ_mulAdd(dest, destOffset, src, srcOffset, angle);
    }

    public static java.lang.foreign.MemorySegment preRotateZ_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _c2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(_sv1, _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t0), _sv2.mul(_sv1)).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preRotateZ_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle) {
        double _t0 = Math.sin(angle);
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, Math.cosFromSin(_t0, angle));
        var _sv2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder());
        var _c2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(_sv1).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, -_t0))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t0)).add(_sv2.mul(_sv1)).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static double[] preScale(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(SIMD_SPECIES, vY));
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).mul(DoubleVector.broadcast(SIMD_SPECIES, vZ));
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, vX)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY, double vZ) {
        var _c1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).mul(DoubleVector.broadcast(SIMD_SPECIES, vY));
        var _c2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder()).mul(DoubleVector.broadcast(SIMD_SPECIES, vZ));
        DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(DoubleVector.broadcast(SIMD_SPECIES, vX)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static double[] preScale(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).mul(DoubleVector.broadcast(SIMD_SPECIES, v[vOffset + 1]));
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).mul(DoubleVector.broadcast(SIMD_SPECIES, v[vOffset + 2]));
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(DoubleVector.broadcast(SIMD_SPECIES, v[vOffset])).intoArray(dest, destOffset);
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
        var _c1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).mul(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_vBase + 8L)));
        var _c2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder()).mul(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_vBase + 16L)));
        DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_vBase))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        var _c1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).mul(DoubleVector.broadcast(SIMD_SPECIES, v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + 8L)));
        var _c2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder()).mul(DoubleVector.broadcast(SIMD_SPECIES, v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + 16L)));
        DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(DoubleVector.broadcast(SIMD_SPECIES, v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static double[] preScale(double[] dest, int destOffset, double[] src, int srcOffset, double s) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.broadcast(SIMD_SPECIES, s).mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + _li * 4)).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScale(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s) {
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.broadcast(SIMD_SPECIES, s).mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 32L, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static double[] preScaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        if (SimdSupport.USE_FMA) return preScaleAround_fma(dest, destOffset, src, srcOffset, s, pivotX, pivotY, pivotZ);
        return preScaleAround_mulAdd(dest, destOffset, src, srcOffset, s, pivotX, pivotY, pivotZ);
    }

    public static double[] preScaleAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        double _t0 = 1.0 - s;
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = _sv0.fma(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4), _sv1.withLane(3, pivotY * _t0));
        var _c2 = _sv0.fma(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8), _sv1.withLane(3, pivotZ * _t0));
        _sv0.fma(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset), _sv1.withLane(3, pivotX * _t0)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preScaleAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        double _t0 = 1.0 - s;
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4)).add(_sv1.withLane(3, pivotY * _t0));
        var _c2 = _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8)).add(_sv1.withLane(3, pivotZ * _t0));
        _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).add(_sv1.withLane(3, pivotX * _t0)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        if (SimdSupport.USE_FMA) return preScaleAround_fma(dest, destOffset, src, srcOffset, s, pivotX, pivotY, pivotZ);
        return preScaleAround_mulAdd(dest, destOffset, src, srcOffset, s, pivotX, pivotY, pivotZ);
    }

    public static java.lang.foreign.MemorySegment preScaleAround_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        double _t0 = 1.0 - s;
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = _sv0.fma(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()), _sv1.withLane(3, pivotY * _t0));
        var _c2 = _sv0.fma(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder()), _sv1.withLane(3, pivotZ * _t0));
        _sv0.fma(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()), _sv1.withLane(3, pivotX * _t0)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        double _t0 = 1.0 - s;
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = _sv0.mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder())).add(_sv1.withLane(3, pivotY * _t0));
        var _c2 = _sv0.mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder())).add(_sv1.withLane(3, pivotZ * _t0));
        _sv0.mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).add(_sv1.withLane(3, pivotX * _t0)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static double[] preScaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] pivot, int pivotOffset, double s) {
        if (SimdSupport.USE_FMA) return preScaleAround_fma(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return preScaleAround_mulAdd(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    public static double[] preScaleAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] pivot, int pivotOffset, double s) {
        double _t0 = 1.0 - s;
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = _sv0.fma(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4), _sv1.withLane(3, pivot[pivotOffset + 1] * _t0));
        var _c2 = _sv0.fma(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8), _sv1.withLane(3, pivot[pivotOffset + 2] * _t0));
        _sv0.fma(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset), _sv1.withLane(3, pivot[pivotOffset] * _t0)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preScaleAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] pivot, int pivotOffset, double s) {
        double _t0 = 1.0 - s;
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4)).add(_sv1.withLane(3, pivot[pivotOffset + 1] * _t0));
        var _c2 = _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8)).add(_sv1.withLane(3, pivot[pivotOffset + 2] * _t0));
        _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).add(_sv1.withLane(3, pivot[pivotOffset] * _t0)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double s) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && pivot.isNative()) return preScaleAround_fma_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
            return preScaleAround_fma_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && pivot.isNative()) return preScaleAround_mulAdd_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return preScaleAround_mulAdd_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    public static java.lang.foreign.MemorySegment preScaleAround_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && pivot.isNative()) return preScaleAround_fma_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return preScaleAround_fma_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    public static java.lang.foreign.MemorySegment preScaleAround_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double s) {
        long _pivotBase = pivot.address() + pivotOffset;
        double _t0 = 1.0 - s;
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = _sv0.fma(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()), _sv1.withLane(3, UnsafeOpsHolder.U.getDouble(_pivotBase + 8L) * _t0));
        var _c2 = _sv0.fma(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder()), _sv1.withLane(3, UnsafeOpsHolder.U.getDouble(_pivotBase + 16L) * _t0));
        _sv0.fma(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()), _sv1.withLane(3, UnsafeOpsHolder.U.getDouble(_pivotBase) * _t0)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double s) {
        double _t0 = 1.0 - s;
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = _sv0.fma(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()), _sv1.withLane(3, pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L) * _t0));
        var _c2 = _sv0.fma(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder()), _sv1.withLane(3, pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset + 16L) * _t0));
        _sv0.fma(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()), _sv1.withLane(3, pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset) * _t0)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && pivot.isNative()) return preScaleAround_mulAdd_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return preScaleAround_mulAdd_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    public static java.lang.foreign.MemorySegment preScaleAround_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double s) {
        long _pivotBase = pivot.address() + pivotOffset;
        double _t0 = 1.0 - s;
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = _sv0.mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder())).add(_sv1.withLane(3, UnsafeOpsHolder.U.getDouble(_pivotBase + 8L) * _t0));
        var _c2 = _sv0.mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder())).add(_sv1.withLane(3, UnsafeOpsHolder.U.getDouble(_pivotBase + 16L) * _t0));
        _sv0.mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).add(_sv1.withLane(3, UnsafeOpsHolder.U.getDouble(_pivotBase) * _t0)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double s) {
        double _t0 = 1.0 - s;
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, s);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = _sv0.mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder())).add(_sv1.withLane(3, pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L) * _t0));
        var _c2 = _sv0.mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder())).add(_sv1.withLane(3, pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset + 16L) * _t0));
        _sv0.mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).add(_sv1.withLane(3, pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset) * _t0)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static double[] preScaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        if (SimdSupport.USE_FMA) return preScaleAround_fma(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return preScaleAround_mulAdd(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
    }

    public static double[] preScaleAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, sY).fma(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4), _sv0.withLane(3, pivotY * (1.0 - sY)));
        var _c2 = DoubleVector.broadcast(SIMD_SPECIES, sZ).fma(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8), _sv0.withLane(3, pivotZ * (1.0 - sZ)));
        DoubleVector.broadcast(SIMD_SPECIES, sX).fma(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset), _sv0.withLane(3, pivotX * (1.0 - sX))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preScaleAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, sY).mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4)).add(_sv0.withLane(3, pivotY * (1.0 - sY)));
        var _c2 = DoubleVector.broadcast(SIMD_SPECIES, sZ).mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8)).add(_sv0.withLane(3, pivotZ * (1.0 - sZ)));
        DoubleVector.broadcast(SIMD_SPECIES, sX).mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).add(_sv0.withLane(3, pivotX * (1.0 - sX))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        if (SimdSupport.USE_FMA) return preScaleAround_fma(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return preScaleAround_mulAdd(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
    }

    public static java.lang.foreign.MemorySegment preScaleAround_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, sY).fma(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()), _sv0.withLane(3, pivotY * (1.0 - sY)));
        var _c2 = DoubleVector.broadcast(SIMD_SPECIES, sZ).fma(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder()), _sv0.withLane(3, pivotZ * (1.0 - sZ)));
        DoubleVector.broadcast(SIMD_SPECIES, sX).fma(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()), _sv0.withLane(3, pivotX * (1.0 - sX))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, sY).mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder())).add(_sv0.withLane(3, pivotY * (1.0 - sY)));
        var _c2 = DoubleVector.broadcast(SIMD_SPECIES, sZ).mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder())).add(_sv0.withLane(3, pivotZ * (1.0 - sZ)));
        DoubleVector.broadcast(SIMD_SPECIES, sX).mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).add(_sv0.withLane(3, pivotX * (1.0 - sX))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static double[] preScaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] s, int sOffset, double[] pivot, int pivotOffset) {
        if (SimdSupport.USE_FMA) return preScaleAround_fma(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return preScaleAround_mulAdd(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    public static double[] preScaleAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] s, int sOffset, double[] pivot, int pivotOffset) {
        double _sx = s[sOffset];
        double _sy = s[sOffset + 1];
        double _sz = s[sOffset + 2];
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, _sy).fma(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4), _sv0.withLane(3, pivot[pivotOffset + 1] * (1.0 - _sy)));
        var _c2 = DoubleVector.broadcast(SIMD_SPECIES, _sz).fma(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8), _sv0.withLane(3, pivot[pivotOffset + 2] * (1.0 - _sz)));
        DoubleVector.broadcast(SIMD_SPECIES, _sx).fma(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset), _sv0.withLane(3, pivot[pivotOffset] * (1.0 - _sx))).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static double[] preScaleAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] s, int sOffset, double[] pivot, int pivotOffset) {
        double _sx = s[sOffset];
        double _sy = s[sOffset + 1];
        double _sz = s[sOffset + 2];
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, _sy).mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4)).add(_sv0.withLane(3, pivot[pivotOffset + 1] * (1.0 - _sy)));
        var _c2 = DoubleVector.broadcast(SIMD_SPECIES, _sz).mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8)).add(_sv0.withLane(3, pivot[pivotOffset + 2] * (1.0 - _sz)));
        DoubleVector.broadcast(SIMD_SPECIES, _sx).mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).add(_sv0.withLane(3, pivot[pivotOffset] * (1.0 - _sx))).intoArray(dest, destOffset);
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
        double _sx = UnsafeOpsHolder.U.getDouble(_sBase);
        double _sy = UnsafeOpsHolder.U.getDouble(_sBase + 8L);
        double _sz = UnsafeOpsHolder.U.getDouble(_sBase + 16L);
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, _sy).fma(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()), _sv0.withLane(3, UnsafeOpsHolder.U.getDouble(_pivotBase + 8L) * (1.0 - _sy)));
        var _c2 = DoubleVector.broadcast(SIMD_SPECIES, _sz).fma(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder()), _sv0.withLane(3, UnsafeOpsHolder.U.getDouble(_pivotBase + 16L) * (1.0 - _sz)));
        DoubleVector.broadcast(SIMD_SPECIES, _sx).fma(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()), _sv0.withLane(3, UnsafeOpsHolder.U.getDouble(_pivotBase) * (1.0 - _sx))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        double _sx = s.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, sOffset);
        double _sy = s.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, sOffset + 8L);
        double _sz = s.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, sOffset + 16L);
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, _sy).fma(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()), _sv0.withLane(3, pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L) * (1.0 - _sy)));
        var _c2 = DoubleVector.broadcast(SIMD_SPECIES, _sz).fma(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder()), _sv0.withLane(3, pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset + 16L) * (1.0 - _sz)));
        DoubleVector.broadcast(SIMD_SPECIES, _sx).fma(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()), _sv0.withLane(3, pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset) * (1.0 - _sx))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && s.isNative() && pivot.isNative()) return preScaleAround_mulAdd_unsafe(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return preScaleAround_mulAdd_api(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    public static java.lang.foreign.MemorySegment preScaleAround_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        long _sBase = s.address() + sOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        double _sx = UnsafeOpsHolder.U.getDouble(_sBase);
        double _sy = UnsafeOpsHolder.U.getDouble(_sBase + 8L);
        double _sz = UnsafeOpsHolder.U.getDouble(_sBase + 16L);
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, _sy).mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder())).add(_sv0.withLane(3, UnsafeOpsHolder.U.getDouble(_pivotBase + 8L) * (1.0 - _sy)));
        var _c2 = DoubleVector.broadcast(SIMD_SPECIES, _sz).mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder())).add(_sv0.withLane(3, UnsafeOpsHolder.U.getDouble(_pivotBase + 16L) * (1.0 - _sz)));
        DoubleVector.broadcast(SIMD_SPECIES, _sx).mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).add(_sv0.withLane(3, UnsafeOpsHolder.U.getDouble(_pivotBase) * (1.0 - _sx))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preScaleAround_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        double _sx = s.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, sOffset);
        double _sy = s.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, sOffset + 8L);
        double _sz = s.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, sOffset + 16L);
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, _sy).mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder())).add(_sv0.withLane(3, pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L) * (1.0 - _sy)));
        var _c2 = DoubleVector.broadcast(SIMD_SPECIES, _sz).mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder())).add(_sv0.withLane(3, pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset + 16L) * (1.0 - _sz)));
        DoubleVector.broadcast(SIMD_SPECIES, _sx).mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).add(_sv0.withLane(3, pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset) * (1.0 - _sx))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static double[] preTranslate(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).add(_sv0.withLane(3, vY));
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).add(_sv0.withLane(3, vZ));
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).add(_sv0.withLane(3, vX)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
        return dest;
    }

    public static java.lang.foreign.MemorySegment preTranslate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY, double vZ) {
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).add(_sv0.withLane(3, vY));
        var _c2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder()).add(_sv0.withLane(3, vZ));
        DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).add(_sv0.withLane(3, vX)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static double[] preTranslate(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4).add(_sv0.withLane(3, v[vOffset + 1]));
        var _c2 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8).add(_sv0.withLane(3, v[vOffset + 2]));
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).add(_sv0.withLane(3, v[vOffset])).intoArray(dest, destOffset);
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
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).add(_sv0.withLane(3, UnsafeOpsHolder.U.getDouble(_vBase + 8L)));
        var _c2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder()).add(_sv0.withLane(3, UnsafeOpsHolder.U.getDouble(_vBase + 16L)));
        DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).add(_sv0.withLane(3, UnsafeOpsHolder.U.getDouble(_vBase))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment preTranslate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).add(_sv0.withLane(3, v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + 8L)));
        var _c2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder()).add(_sv0.withLane(3, v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + 16L)));
        DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).add(_sv0.withLane(3, v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static double[] reflect(double[] dest, int destOffset, double[] src, int srcOffset, double normalX, double normalY, double normalZ) {
        if (SimdSupport.USE_FMA) return reflect_fma(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
        return reflect_mulAdd(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
    }

    public static double[] reflect_fma(double[] dest, int destOffset, double[] src, int srcOffset, double normalX, double normalY, double normalZ) {
        double _self22 = src[srcOffset + 10];
        reflect_fma_sdd385ec2_v(dest, destOffset, src, srcOffset, src[srcOffset + 2], src[srcOffset], src[srcOffset + 1], src[srcOffset + 6], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 8], src[srcOffset + 9], _self22, -_self22, (normalX + normalX) * normalZ, (normalX + normalX) * normalY, (normalY + normalY) * normalZ, Math.fma(-2.0, normalX * normalX, 1.0), Math.fma(-2.0, normalY * normalY, 1.0), Math.fma(-2.0, normalZ * normalZ, 1.0));
        return dest;
    }

    private static void reflect_fma_sdd385ec2_v(double[] dest, int destOffset, double[] src, int srcOffset, double _self02, double _self00, double _self01, double _self12, double _self10, double _self11, double _self20, double _self21, double _self22, double _t2, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14) {
        double _ld0 = src[srcOffset + 7];
        double _ld1 = src[srcOffset + 11];
        double _ld2 = src[srcOffset + 3];
        reflect_fma_sdd385ec2_v_s56336d5c_1(dest, destOffset, _self02, _self00, _self01, _self12, _self10, _self11, _self20, _self21, _self22, _t2, _t9, _t10, _t11, _t12, _t13, _t14, _ld0, _ld1, _ld2);
    }

    /** Part 1 of {@code reflect_fma_sdd385ec2_v}, split to fit the inline budget; reached only through it. */
    private static void reflect_fma_sdd385ec2_v_s56336d5c_1(double[] dest, int destOffset, double _self02, double _self00, double _self01, double _self12, double _self10, double _self11, double _self20, double _self21, double _self22, double _t2, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14, double _ld0, double _ld1, double _ld2) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t9).withLane(1, _t11).withLane(2, _t14);
        var _c1 = DoubleVector.broadcast(SIMD_SPECIES, -_self12).withLane(2, _self12).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, _self10 * _t12).withLane(1, _self11 * _t13).sub(DoubleVector.broadcast(SIMD_SPECIES, _self11 * _t10).withLane(1, _self10 * _t10)).withLane(2, -_self11 * _t11 - _self10 * _t9)).withLane(3, _ld0);
        var _c2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t2 * _t9 + _self20 * _t12 - _self21 * _t10).withLane(1, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10)).withLane(2, _self22 * _t14 + (-_self21 * _t11 - _self20 * _t9)).withLane(3, _ld1);
        DoubleVector.broadcast(SIMD_SPECIES, -_self02).withLane(2, _self02).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, _self00 * _t12).withLane(1, _self01 * _t13).sub(DoubleVector.broadcast(SIMD_SPECIES, _self01 * _t10).withLane(1, _self00 * _t10)).withLane(2, -_self01 * _t11 - _self00 * _t9)).withLane(3, _ld2).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
    }

    public static double[] reflect_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double normalX, double normalY, double normalZ) {
        double _self22 = src[srcOffset + 10];
        double _self02 = src[srcOffset + 2];
        double _self00 = src[srcOffset];
        double _self01 = src[srcOffset + 1];
        double _self12 = src[srcOffset + 6];
        double _self10 = src[srcOffset + 4];
        double _self11 = src[srcOffset + 5];
        double _self20 = src[srcOffset + 8];
        double _self21 = src[srcOffset + 9];
        double _t2 = -_self22;
        double _t9 = (normalX + normalX) * normalZ;
        double _t10 = (normalX + normalX) * normalY;
        double _t11 = (normalY + normalY) * normalZ;
        double _t12 = Math.fma(-2.0, normalX * normalX, 1.0);
        double _t13 = Math.fma(-2.0, normalY * normalY, 1.0);
        double _t14 = Math.fma(-2.0, normalZ * normalZ, 1.0);
        reflect_mulAdd_sdd26ac6b_v(dest, destOffset, _t9, _t11, _t14, -_self02, _self02, _self00 * _t12, _self01 * _t13, _self01 * _t10, _self00 * _t10, -_self01 * _t11 - _self00 * _t9, src[srcOffset + 3], -_self12, _self12, _self10 * _t12, _self11 * _t13, _self11 * _t10, _self10 * _t10, -_self11 * _t11 - _self10 * _t9, src[srcOffset + 7], _t2 * _t9 + _self20 * _t12 - _self21 * _t10, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10), _self22 * _t14 + (-_self21 * _t11 - _self20 * _t9), src[srcOffset + 11]);
        return dest;
    }

    private static void reflect_mulAdd_sdd26ac6b_v(double[] dest, int destOffset, double _t9, double _t11, double _t14, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8, double _h9, double _h10, double _h11, double _h12, double _h13, double _h14, double _h15, double _h16, double _h17, double _h18, double _h19) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t9).withLane(1, _t11).withLane(2, _t14);
        DoubleVector.broadcast(SIMD_SPECIES, _h0).withLane(2, _h1).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, _h2).withLane(1, _h3).sub(DoubleVector.broadcast(SIMD_SPECIES, _h4).withLane(1, _h5)).withLane(2, _h6)).withLane(3, _h7).intoArray(dest, destOffset);
        DoubleVector.broadcast(SIMD_SPECIES, _h8).withLane(2, _h9).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, _h10).withLane(1, _h11).sub(DoubleVector.broadcast(SIMD_SPECIES, _h12).withLane(1, _h13)).withLane(2, _h14)).withLane(3, _h15).intoArray(dest, destOffset + 4);
        DoubleVector.zero(SIMD_SPECIES).withLane(0, _h16).withLane(1, _h17).withLane(2, _h18).withLane(3, _h19).intoArray(dest, destOffset + 8);
    }

    public static java.lang.foreign.MemorySegment reflect(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return reflect_fma_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
            return reflect_fma_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return reflect_mulAdd_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
        return reflect_mulAdd_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
    }

    public static java.lang.foreign.MemorySegment reflect_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return reflect_fma_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
        return reflect_fma_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
    }

    public static java.lang.foreign.MemorySegment reflect_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ) {
        long _srcBase = src.address() + srcOffset;
        double _self22 = UnsafeOpsHolder.U.getDouble(_srcBase + 80L);
        double _self02 = UnsafeOpsHolder.U.getDouble(_srcBase + 16L);
        double _self00 = UnsafeOpsHolder.U.getDouble(_srcBase);
        double _self01 = UnsafeOpsHolder.U.getDouble(_srcBase + 8L);
        double _self12 = UnsafeOpsHolder.U.getDouble(_srcBase + 48L);
        double _self10 = UnsafeOpsHolder.U.getDouble(_srcBase + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(_srcBase + 40L);
        double _self20 = UnsafeOpsHolder.U.getDouble(_srcBase + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(_srcBase + 72L);
        return reflect_fma_unsafe_saa64240b_1(dest, destOffset, _srcBase, _self22, _self02, _self00, _self01, _self12, _self10, _self11, _self20, _self21, -_self22, (normalX + normalX) * normalZ, (normalX + normalX) * normalY, (normalY + normalY) * normalZ, Math.fma(-2.0, normalX * normalX, 1.0), Math.fma(-2.0, normalY * normalY, 1.0), Math.fma(-2.0, normalZ * normalZ, 1.0));
    }

    /** Piece 2 of {@code reflect_fma_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment reflect_fma_unsafe_saa64240b_1(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, double _self22, double _self02, double _self00, double _self01, double _self12, double _self10, double _self11, double _self20, double _self21, double _t2, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14) {
        reflect_fma_unsafe_s972bc62b_v(dest, destOffset, _t9, _t11, _t14, -_self02, _self02, _self00 * _t12, _self01 * _t13, _self01 * _t10, _self00 * _t10, -_self01 * _t11 - _self00 * _t9, UnsafeOpsHolder.U.getDouble(_srcBase + 24L), -_self12, _self12, _self10 * _t12, _self11 * _t13, _self11 * _t10, _self10 * _t10, -_self11 * _t11 - _self10 * _t9, UnsafeOpsHolder.U.getDouble(_srcBase + 56L), _t2 * _t9 + _self20 * _t12 - _self21 * _t10, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10), _self22 * _t14 + (-_self21 * _t11 - _self20 * _t9), UnsafeOpsHolder.U.getDouble(_srcBase + 88L));
        return dest;
    }

    private static void reflect_fma_unsafe_s972bc62b_v(java.lang.foreign.MemorySegment dest, long destOffset, double _t9, double _t11, double _t14, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8, double _h9, double _h10, double _h11, double _h12, double _h13, double _h14, double _h15, double _h16, double _h17, double _h18, double _h19) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t9).withLane(1, _t11).withLane(2, _t14);
        DoubleVector.broadcast(SIMD_SPECIES, _h0).withLane(2, _h1).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, _h2).withLane(1, _h3).sub(DoubleVector.broadcast(SIMD_SPECIES, _h4).withLane(1, _h5)).withLane(2, _h6)).withLane(3, _h7).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        DoubleVector.broadcast(SIMD_SPECIES, _h8).withLane(2, _h9).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, _h10).withLane(1, _h11).sub(DoubleVector.broadcast(SIMD_SPECIES, _h12).withLane(1, _h13)).withLane(2, _h14)).withLane(3, _h15).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        DoubleVector.zero(SIMD_SPECIES).withLane(0, _h16).withLane(1, _h17).withLane(2, _h18).withLane(3, _h19).intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment reflect_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ) {
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 80L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 72L);
        return reflect_fma_api_sa0e54201_1(dest, destOffset, src, srcOffset, _self22, _self02, _self00, _self01, _self12, _self10, _self11, _self20, _self21, -_self22, (normalX + normalX) * normalZ, (normalX + normalX) * normalY, (normalY + normalY) * normalZ, Math.fma(-2.0, normalX * normalX, 1.0), Math.fma(-2.0, normalY * normalY, 1.0), Math.fma(-2.0, normalZ * normalZ, 1.0));
    }

    /** Piece 2 of {@code reflect_fma_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment reflect_fma_api_sa0e54201_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _self22, double _self02, double _self00, double _self01, double _self12, double _self10, double _self11, double _self20, double _self21, double _t2, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14) {
        reflect_fma_unsafe_s972bc62b_v(dest, destOffset, _t9, _t11, _t14, -_self02, _self02, _self00 * _t12, _self01 * _t13, _self01 * _t10, _self00 * _t10, -_self01 * _t11 - _self00 * _t9, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L), -_self12, _self12, _self10 * _t12, _self11 * _t13, _self11 * _t10, _self10 * _t10, -_self11 * _t11 - _self10 * _t9, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L), _t2 * _t9 + _self20 * _t12 - _self21 * _t10, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10), _self22 * _t14 + (-_self21 * _t11 - _self20 * _t9), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 88L));
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return reflect_mulAdd_unsafe(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
        return reflect_mulAdd_api(dest, destOffset, src, srcOffset, normalX, normalY, normalZ);
    }

    public static java.lang.foreign.MemorySegment reflect_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ) {
        long _srcBase = src.address() + srcOffset;
        double _self22 = UnsafeOpsHolder.U.getDouble(_srcBase + 80L);
        double _self02 = UnsafeOpsHolder.U.getDouble(_srcBase + 16L);
        double _self00 = UnsafeOpsHolder.U.getDouble(_srcBase);
        double _self01 = UnsafeOpsHolder.U.getDouble(_srcBase + 8L);
        double _self12 = UnsafeOpsHolder.U.getDouble(_srcBase + 48L);
        double _self10 = UnsafeOpsHolder.U.getDouble(_srcBase + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(_srcBase + 40L);
        double _self20 = UnsafeOpsHolder.U.getDouble(_srcBase + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(_srcBase + 72L);
        return reflect_mulAdd_unsafe_s2f26c22c_1(dest, destOffset, _srcBase, _self22, _self02, _self00, _self01, _self12, _self10, _self11, _self20, _self21, -_self22, (normalX + normalX) * normalZ, (normalX + normalX) * normalY, (normalY + normalY) * normalZ, Math.fma(-2.0, normalX * normalX, 1.0), Math.fma(-2.0, normalY * normalY, 1.0), Math.fma(-2.0, normalZ * normalZ, 1.0));
    }

    /** Piece 2 of {@code reflect_mulAdd_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment reflect_mulAdd_unsafe_s2f26c22c_1(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, double _self22, double _self02, double _self00, double _self01, double _self12, double _self10, double _self11, double _self20, double _self21, double _t2, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14) {
        reflect_mulAdd_unsafe_s57b3ae14_v(dest, destOffset, _t9, _t11, _t14, -_self02, _self02, _self00 * _t12, _self01 * _t13, _self01 * _t10, _self00 * _t10, -_self01 * _t11 - _self00 * _t9, UnsafeOpsHolder.U.getDouble(_srcBase + 24L), -_self12, _self12, _self10 * _t12, _self11 * _t13, _self11 * _t10, _self10 * _t10, -_self11 * _t11 - _self10 * _t9, UnsafeOpsHolder.U.getDouble(_srcBase + 56L), _t2 * _t9 + _self20 * _t12 - _self21 * _t10, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10), _self22 * _t14 + (-_self21 * _t11 - _self20 * _t9), UnsafeOpsHolder.U.getDouble(_srcBase + 88L));
        return dest;
    }

    private static void reflect_mulAdd_unsafe_s57b3ae14_v(java.lang.foreign.MemorySegment dest, long destOffset, double _t9, double _t11, double _t14, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8, double _h9, double _h10, double _h11, double _h12, double _h13, double _h14, double _h15, double _h16, double _h17, double _h18, double _h19) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t9).withLane(1, _t11).withLane(2, _t14);
        DoubleVector.broadcast(SIMD_SPECIES, _h0).withLane(2, _h1).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, _h2).withLane(1, _h3).sub(DoubleVector.broadcast(SIMD_SPECIES, _h4).withLane(1, _h5)).withLane(2, _h6)).withLane(3, _h7).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        DoubleVector.broadcast(SIMD_SPECIES, _h8).withLane(2, _h9).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, _h10).withLane(1, _h11).sub(DoubleVector.broadcast(SIMD_SPECIES, _h12).withLane(1, _h13)).withLane(2, _h14)).withLane(3, _h15).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        DoubleVector.zero(SIMD_SPECIES).withLane(0, _h16).withLane(1, _h17).withLane(2, _h18).withLane(3, _h19).intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment reflect_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double normalX, double normalY, double normalZ) {
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 80L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 72L);
        return reflect_mulAdd_api_s7dbf281c_1(dest, destOffset, src, srcOffset, _self22, _self02, _self00, _self01, _self12, _self10, _self11, _self20, _self21, -_self22, (normalX + normalX) * normalZ, (normalX + normalX) * normalY, (normalY + normalY) * normalZ, Math.fma(-2.0, normalX * normalX, 1.0), Math.fma(-2.0, normalY * normalY, 1.0), Math.fma(-2.0, normalZ * normalZ, 1.0));
    }

    /** Piece 2 of {@code reflect_mulAdd_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment reflect_mulAdd_api_s7dbf281c_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _self22, double _self02, double _self00, double _self01, double _self12, double _self10, double _self11, double _self20, double _self21, double _t2, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14) {
        reflect_mulAdd_unsafe_s57b3ae14_v(dest, destOffset, _t9, _t11, _t14, -_self02, _self02, _self00 * _t12, _self01 * _t13, _self01 * _t10, _self00 * _t10, -_self01 * _t11 - _self00 * _t9, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L), -_self12, _self12, _self10 * _t12, _self11 * _t13, _self11 * _t10, _self10 * _t10, -_self11 * _t11 - _self10 * _t9, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L), _t2 * _t9 + _self20 * _t12 - _self21 * _t10, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10), _self22 * _t14 + (-_self21 * _t11 - _self20 * _t9), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 88L));
        return dest;
    }

    public static double[] reflect(double[] dest, int destOffset, double[] src, int srcOffset, double[] normal, int normalOffset) {
        if (SimdSupport.USE_FMA) return reflect_fma(dest, destOffset, src, srcOffset, normal, normalOffset);
        return reflect_mulAdd(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    public static double[] reflect_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] normal, int normalOffset) {
        double _self22 = src[srcOffset + 10];
        double _normalx = normal[normalOffset];
        double _normalz = normal[normalOffset + 2];
        double _normaly = normal[normalOffset + 1];
        reflect_fma_sdd385ec2_v(dest, destOffset, src, srcOffset, src[srcOffset + 2], src[srcOffset], src[srcOffset + 1], src[srcOffset + 6], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 8], src[srcOffset + 9], _self22, -_self22, (_normalx + _normalx) * _normalz, (_normalx + _normalx) * _normaly, (_normaly + _normaly) * _normalz, Math.fma(-2.0, _normalx * _normalx, 1.0), Math.fma(-2.0, _normaly * _normaly, 1.0), Math.fma(-2.0, _normalz * _normalz, 1.0));
        return dest;
    }

    public static double[] reflect_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] normal, int normalOffset) {
        double _self22 = src[srcOffset + 10];
        double _normalx = normal[normalOffset];
        double _normalz = normal[normalOffset + 2];
        double _normaly = normal[normalOffset + 1];
        return reflect_mulAdd_sd09fdfb9_1(dest, destOffset, src, srcOffset, _self22, src[srcOffset + 2], src[srcOffset], src[srcOffset + 1], src[srcOffset + 6], src[srcOffset + 4], src[srcOffset + 5], src[srcOffset + 8], src[srcOffset + 9], -_self22, (_normalx + _normalx) * _normalz, (_normalx + _normalx) * _normaly, (_normaly + _normaly) * _normalz, Math.fma(-2.0, _normalx * _normalx, 1.0), Math.fma(-2.0, _normaly * _normaly, 1.0), Math.fma(-2.0, _normalz * _normalz, 1.0));
    }

    /** Piece 2 of {@code reflect_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] reflect_mulAdd_sd09fdfb9_1(double[] dest, int destOffset, double[] src, int srcOffset, double _self22, double _self02, double _self00, double _self01, double _self12, double _self10, double _self11, double _self20, double _self21, double _t2, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14) {
        reflect_mulAdd_sdd26ac6b_v(dest, destOffset, _t9, _t11, _t14, -_self02, _self02, _self00 * _t12, _self01 * _t13, _self01 * _t10, _self00 * _t10, -_self01 * _t11 - _self00 * _t9, src[srcOffset + 3], -_self12, _self12, _self10 * _t12, _self11 * _t13, _self11 * _t10, _self10 * _t10, -_self11 * _t11 - _self10 * _t9, src[srcOffset + 7], _t2 * _t9 + _self20 * _t12 - _self21 * _t10, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10), _self22 * _t14 + (-_self21 * _t11 - _self20 * _t9), src[srcOffset + 11]);
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
        double _self22 = UnsafeOpsHolder.U.getDouble(_srcBase + 80L);
        double _normalx = UnsafeOpsHolder.U.getDouble(_normalBase);
        double _normalz = UnsafeOpsHolder.U.getDouble(_normalBase + 16L);
        double _normaly = UnsafeOpsHolder.U.getDouble(_normalBase + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(_srcBase + 16L);
        double _self00 = UnsafeOpsHolder.U.getDouble(_srcBase);
        double _self01 = UnsafeOpsHolder.U.getDouble(_srcBase + 8L);
        double _self12 = UnsafeOpsHolder.U.getDouble(_srcBase + 48L);
        double _self10 = UnsafeOpsHolder.U.getDouble(_srcBase + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(_srcBase + 40L);
        double _self20 = UnsafeOpsHolder.U.getDouble(_srcBase + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(_srcBase + 72L);
        return reflect_fma_unsafe_s1153b997_1(dest, destOffset, _srcBase, _self22, _self02, _self00, _self01, _self12, _self10, _self11, _self20, _self21, -_self22, (_normalx + _normalx) * _normalz, (_normalx + _normalx) * _normaly, (_normaly + _normaly) * _normalz, Math.fma(-2.0, _normalx * _normalx, 1.0), Math.fma(-2.0, _normaly * _normaly, 1.0), Math.fma(-2.0, _normalz * _normalz, 1.0));
    }

    /** Piece 2 of {@code reflect_fma_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment reflect_fma_unsafe_s1153b997_1(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, double _self22, double _self02, double _self00, double _self01, double _self12, double _self10, double _self11, double _self20, double _self21, double _t2, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14) {
        reflect_fma_unsafe_s972bc62b_v(dest, destOffset, _t9, _t11, _t14, -_self02, _self02, _self00 * _t12, _self01 * _t13, _self01 * _t10, _self00 * _t10, -_self01 * _t11 - _self00 * _t9, UnsafeOpsHolder.U.getDouble(_srcBase + 24L), -_self12, _self12, _self10 * _t12, _self11 * _t13, _self11 * _t10, _self10 * _t10, -_self11 * _t11 - _self10 * _t9, UnsafeOpsHolder.U.getDouble(_srcBase + 56L), _t2 * _t9 + _self20 * _t12 - _self21 * _t10, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10), _self22 * _t14 + (-_self21 * _t11 - _self20 * _t9), UnsafeOpsHolder.U.getDouble(_srcBase + 88L));
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 80L);
        double _normalx = normal.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, normalOffset);
        double _normalz = normal.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, normalOffset + 16L);
        double _normaly = normal.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, normalOffset + 8L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 72L);
        return reflect_fma_api_s29bdfeb9_1(dest, destOffset, src, srcOffset, _self22, _self02, _self00, _self01, _self12, _self10, _self11, _self20, _self21, -_self22, (_normalx + _normalx) * _normalz, (_normalx + _normalx) * _normaly, (_normaly + _normaly) * _normalz, Math.fma(-2.0, _normalx * _normalx, 1.0), Math.fma(-2.0, _normaly * _normaly, 1.0), Math.fma(-2.0, _normalz * _normalz, 1.0));
    }

    /** Piece 2 of {@code reflect_fma_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment reflect_fma_api_s29bdfeb9_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _self22, double _self02, double _self00, double _self01, double _self12, double _self10, double _self11, double _self20, double _self21, double _t2, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14) {
        reflect_fma_unsafe_s972bc62b_v(dest, destOffset, _t9, _t11, _t14, -_self02, _self02, _self00 * _t12, _self01 * _t13, _self01 * _t10, _self00 * _t10, -_self01 * _t11 - _self00 * _t9, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L), -_self12, _self12, _self10 * _t12, _self11 * _t13, _self11 * _t10, _self10 * _t10, -_self11 * _t11 - _self10 * _t9, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L), _t2 * _t9 + _self20 * _t12 - _self21 * _t10, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10), _self22 * _t14 + (-_self21 * _t11 - _self20 * _t9), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 88L));
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && normal.isNative()) return reflect_mulAdd_unsafe(dest, destOffset, src, srcOffset, normal, normalOffset);
        return reflect_mulAdd_api(dest, destOffset, src, srcOffset, normal, normalOffset);
    }

    public static java.lang.foreign.MemorySegment reflect_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        long _srcBase = src.address() + srcOffset;
        long _normalBase = normal.address() + normalOffset;
        double _self22 = UnsafeOpsHolder.U.getDouble(_srcBase + 80L);
        double _normalx = UnsafeOpsHolder.U.getDouble(_normalBase);
        double _normalz = UnsafeOpsHolder.U.getDouble(_normalBase + 16L);
        double _normaly = UnsafeOpsHolder.U.getDouble(_normalBase + 8L);
        double _self02 = UnsafeOpsHolder.U.getDouble(_srcBase + 16L);
        double _self00 = UnsafeOpsHolder.U.getDouble(_srcBase);
        double _self01 = UnsafeOpsHolder.U.getDouble(_srcBase + 8L);
        double _self12 = UnsafeOpsHolder.U.getDouble(_srcBase + 48L);
        double _self10 = UnsafeOpsHolder.U.getDouble(_srcBase + 32L);
        double _self11 = UnsafeOpsHolder.U.getDouble(_srcBase + 40L);
        double _self20 = UnsafeOpsHolder.U.getDouble(_srcBase + 64L);
        double _self21 = UnsafeOpsHolder.U.getDouble(_srcBase + 72L);
        return reflect_mulAdd_unsafe_s9a6dc71a_1(dest, destOffset, _srcBase, _self22, _self02, _self00, _self01, _self12, _self10, _self11, _self20, _self21, -_self22, (_normalx + _normalx) * _normalz, (_normalx + _normalx) * _normaly, (_normaly + _normaly) * _normalz, Math.fma(-2.0, _normalx * _normalx, 1.0), Math.fma(-2.0, _normaly * _normaly, 1.0), Math.fma(-2.0, _normalz * _normalz, 1.0));
    }

    /** Piece 2 of {@code reflect_mulAdd_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment reflect_mulAdd_unsafe_s9a6dc71a_1(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, double _self22, double _self02, double _self00, double _self01, double _self12, double _self10, double _self11, double _self20, double _self21, double _t2, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14) {
        reflect_mulAdd_unsafe_s57b3ae14_v(dest, destOffset, _t9, _t11, _t14, -_self02, _self02, _self00 * _t12, _self01 * _t13, _self01 * _t10, _self00 * _t10, -_self01 * _t11 - _self00 * _t9, UnsafeOpsHolder.U.getDouble(_srcBase + 24L), -_self12, _self12, _self10 * _t12, _self11 * _t13, _self11 * _t10, _self10 * _t10, -_self11 * _t11 - _self10 * _t9, UnsafeOpsHolder.U.getDouble(_srcBase + 56L), _t2 * _t9 + _self20 * _t12 - _self21 * _t10, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10), _self22 * _t14 + (-_self21 * _t11 - _self20 * _t9), UnsafeOpsHolder.U.getDouble(_srcBase + 88L));
        return dest;
    }

    public static java.lang.foreign.MemorySegment reflect_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment normal, long normalOffset) {
        double _self22 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 80L);
        double _normalx = normal.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, normalOffset);
        double _normalz = normal.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, normalOffset + 16L);
        double _normaly = normal.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, normalOffset + 8L);
        double _self02 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _self00 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _self01 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _self12 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L);
        double _self10 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L);
        double _self11 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L);
        double _self20 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L);
        double _self21 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 72L);
        return reflect_mulAdd_api_sdf348c62_1(dest, destOffset, src, srcOffset, _self22, _self02, _self00, _self01, _self12, _self10, _self11, _self20, _self21, -_self22, (_normalx + _normalx) * _normalz, (_normalx + _normalx) * _normaly, (_normaly + _normaly) * _normalz, Math.fma(-2.0, _normalx * _normalx, 1.0), Math.fma(-2.0, _normaly * _normaly, 1.0), Math.fma(-2.0, _normalz * _normalz, 1.0));
    }

    /** Piece 2 of {@code reflect_mulAdd_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment reflect_mulAdd_api_sdf348c62_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _self22, double _self02, double _self00, double _self01, double _self12, double _self10, double _self11, double _self20, double _self21, double _t2, double _t9, double _t10, double _t11, double _t12, double _t13, double _t14) {
        reflect_mulAdd_unsafe_s57b3ae14_v(dest, destOffset, _t9, _t11, _t14, -_self02, _self02, _self00 * _t12, _self01 * _t13, _self01 * _t10, _self00 * _t10, -_self01 * _t11 - _self00 * _t9, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L), -_self12, _self12, _self10 * _t12, _self11 * _t13, _self11 * _t10, _self10 * _t10, -_self11 * _t11 - _self10 * _t9, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L), _t2 * _t9 + _self20 * _t12 - _self21 * _t10, _t2 * _t11 + (_self21 * _t13 - _self20 * _t10), _self22 * _t14 + (-_self21 * _t11 - _self20 * _t9), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 88L));
        return dest;
    }

    public static double[] rotateAround(double[] dest, int destOffset, double[] src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        if (SimdSupport.USE_FMA) return rotateAround_fma(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return rotateAround_mulAdd(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
    }

    public static double[] rotateAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        double _t3 = -pivotZ;
        double _t5 = rotX + rotX;
        double _t6 = rotY + rotY;
        double _t7 = rotZ + rotZ;
        double _t8 = rotW * _t6;
        double _t9 = rotW * _t7;
        double _t10 = rotW * _t5;
        double _t11 = rotZ * _t7;
        double _t18 = Math.fma(rotY, _t5, _t9);
        double _t19 = Math.fma(rotZ, _t6, _t10);
        double _t24 = Math.fma(rotZ, _t5, -_t8);
        double _t26 = Math.fma(rotZ, _t6, -_t10);
        rotateAround_fma_s9107c306_v(dest, destOffset, src, srcOffset, rotY, pivotX, pivotY, -rotY, -rotX, _t3, _t5, _t6, _t11, Math.fma(-rotZ, _t7, 1.0), _t18, _t19, Math.fma(rotZ, _t5, _t8), _t24, Math.fma(rotY, _t5, -_t9), _t26, Math.fma(_t3, _t26, Math.fma(pivotY, Math.fma(rotX, _t5, _t11), -(pivotX * _t18))), Math.fma(-pivotY, _t19, Math.fma(pivotZ, Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t24))));
        return dest;
    }

    private static void rotateAround_fma_s9107c306_v(double[] dest, int destOffset, double[] src, int srcOffset, double rotY, double pivotX, double pivotY, double _t0, double _t2, double _t3, double _t5, double _t6, double _t11, double _t16, double _t18, double _t19, double _t20, double _t24, double _t25, double _t26, double _t40, double _t41) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t6, _t16)).withLane(1, _t25).withLane(2, _t20);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, Math.fma(_t2, _t5, _t16)).withLane(2, _t26).withLane(3, Math.fma(_t3, _t20, Math.fma(pivotX, Math.fma(rotY, _t6, _t11), -(pivotY * _t25))));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, _t19).withLane(2, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0)));
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, src[_lo0]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).withLane(3, src[_lo0]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 1] * _t40 + src[_lo0 + 2] * _t41)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] rotateAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        double _t3 = -pivotZ;
        double _t5 = rotX + rotX;
        double _t6 = rotY + rotY;
        double _t7 = rotZ + rotZ;
        double _t8 = rotW * _t6;
        double _t9 = rotW * _t7;
        double _t10 = rotW * _t5;
        double _t11 = rotZ * _t7;
        double _t18 = Math.fma(rotY, _t5, _t9);
        double _t19 = Math.fma(rotZ, _t6, _t10);
        double _t24 = Math.fma(rotZ, _t5, -_t8);
        double _t26 = Math.fma(rotZ, _t6, -_t10);
        rotateAround_mulAdd_s6f860be3_v(dest, destOffset, src, srcOffset, rotY, pivotX, pivotY, -rotY, -rotX, _t3, _t5, _t6, _t11, Math.fma(-rotZ, _t7, 1.0), _t18, _t19, Math.fma(rotZ, _t5, _t8), _t24, Math.fma(rotY, _t5, -_t9), _t26, Math.fma(_t3, _t26, Math.fma(pivotY, Math.fma(rotX, _t5, _t11), -(pivotX * _t18))), Math.fma(-pivotY, _t19, Math.fma(pivotZ, Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t24))));
        return dest;
    }

    private static void rotateAround_mulAdd_s6f860be3_v(double[] dest, int destOffset, double[] src, int srcOffset, double rotY, double pivotX, double pivotY, double _t0, double _t2, double _t3, double _t5, double _t6, double _t11, double _t16, double _t18, double _t19, double _t20, double _t24, double _t25, double _t26, double _t40, double _t41) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t6, _t16)).withLane(1, _t25).withLane(2, _t20);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, Math.fma(_t2, _t5, _t16)).withLane(2, _t26).withLane(3, Math.fma(_t3, _t20, Math.fma(pivotX, Math.fma(rotY, _t6, _t11), -(pivotY * _t25))));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, _t19).withLane(2, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0)));
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, src[_lo0]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).withLane(3, src[_lo0]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 1] * _t40 + src[_lo0 + 2] * _t41)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static java.lang.foreign.MemorySegment rotateAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateAround_fma_unsafe(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
            return rotateAround_fma_api(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateAround_mulAdd_unsafe(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return rotateAround_mulAdd_api(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
    }

    public static java.lang.foreign.MemorySegment rotateAround_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateAround_fma_unsafe(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return rotateAround_fma_api(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
    }

    public static java.lang.foreign.MemorySegment rotateAround_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        long _srcBase = src.address() + srcOffset;
        double _t0 = -rotY;
        double _t2 = -rotX;
        double _t3 = -pivotZ;
        double _t5 = rotX + rotX;
        double _t6 = rotY + rotY;
        double _t7 = rotZ + rotZ;
        double _t8 = rotW * _t6;
        double _t9 = rotW * _t7;
        double _t10 = rotW * _t5;
        double _t11 = rotZ * _t7;
        double _t16 = Math.fma(-rotZ, _t7, 1.0);
        double _t18 = Math.fma(rotY, _t5, _t9);
        double _t19 = Math.fma(rotZ, _t6, _t10);
        double _t20 = Math.fma(rotZ, _t5, _t8);
        double _t24 = Math.fma(rotZ, _t5, -_t8);
        double _t25 = Math.fma(rotY, _t5, -_t9);
        double _t26 = Math.fma(rotZ, _t6, -_t10);
        double _t40 = Math.fma(_t3, _t26, Math.fma(pivotY, Math.fma(rotX, _t5, _t11), -(pivotX * _t18)));
        double _t41 = Math.fma(-pivotY, _t19, Math.fma(pivotZ, Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t24)));
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t6, _t16)).withLane(1, _t25).withLane(2, _t20);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, Math.fma(_t2, _t5, _t16)).withLane(2, _t26).withLane(3, Math.fma(_t3, _t20, Math.fma(pivotX, Math.fma(rotY, _t6, _t11), -(pivotY * _t25))));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, _t19).withLane(2, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0)));
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L))).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L)).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1)).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 16L)).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 8L) * _t40 + UnsafeOpsHolder.U.getDouble(_lb1 + 16L) * _t41)))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        double _t0 = -rotY;
        double _t2 = -rotX;
        double _t3 = -pivotZ;
        double _t5 = rotX + rotX;
        double _t6 = rotY + rotY;
        double _t7 = rotZ + rotZ;
        double _t8 = rotW * _t6;
        double _t9 = rotW * _t7;
        double _t10 = rotW * _t5;
        double _t11 = rotZ * _t7;
        double _t16 = Math.fma(-rotZ, _t7, 1.0);
        double _t18 = Math.fma(rotY, _t5, _t9);
        double _t19 = Math.fma(rotZ, _t6, _t10);
        double _t20 = Math.fma(rotZ, _t5, _t8);
        double _t24 = Math.fma(rotZ, _t5, -_t8);
        double _t25 = Math.fma(rotY, _t5, -_t9);
        double _t26 = Math.fma(rotZ, _t6, -_t10);
        double _t40 = Math.fma(_t3, _t26, Math.fma(pivotY, Math.fma(rotX, _t5, _t11), -(pivotX * _t18)));
        double _t41 = Math.fma(-pivotY, _t19, Math.fma(pivotZ, Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t24)));
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t6, _t16)).withLane(1, _t25).withLane(2, _t20);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, Math.fma(_t2, _t5, _t16)).withLane(2, _t26).withLane(3, Math.fma(_t3, _t20, Math.fma(pivotX, Math.fma(rotY, _t6, _t11), -(pivotY * _t25))));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, _t19).withLane(2, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0)));
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))).add(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L)).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L) * _t40 + src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L) * _t41)))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateAround_mulAdd_unsafe(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
        return rotateAround_mulAdd_api(dest, destOffset, src, srcOffset, rotX, rotY, rotZ, rotW, pivotX, pivotY, pivotZ);
    }

    public static java.lang.foreign.MemorySegment rotateAround_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        long _srcBase = src.address() + srcOffset;
        double _t0 = -rotY;
        double _t2 = -rotX;
        double _t3 = -pivotZ;
        double _t5 = rotX + rotX;
        double _t6 = rotY + rotY;
        double _t7 = rotZ + rotZ;
        double _t8 = rotW * _t6;
        double _t9 = rotW * _t7;
        double _t10 = rotW * _t5;
        double _t11 = rotZ * _t7;
        double _t16 = Math.fma(-rotZ, _t7, 1.0);
        double _t18 = Math.fma(rotY, _t5, _t9);
        double _t19 = Math.fma(rotZ, _t6, _t10);
        double _t20 = Math.fma(rotZ, _t5, _t8);
        double _t24 = Math.fma(rotZ, _t5, -_t8);
        double _t25 = Math.fma(rotY, _t5, -_t9);
        double _t26 = Math.fma(rotZ, _t6, -_t10);
        double _t40 = Math.fma(_t3, _t26, Math.fma(pivotY, Math.fma(rotX, _t5, _t11), -(pivotX * _t18)));
        double _t41 = Math.fma(-pivotY, _t19, Math.fma(pivotZ, Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t24)));
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t6, _t16)).withLane(1, _t25).withLane(2, _t20);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, Math.fma(_t2, _t5, _t16)).withLane(2, _t26).withLane(3, Math.fma(_t3, _t20, Math.fma(pivotX, Math.fma(rotY, _t6, _t11), -(pivotY * _t25))));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, _t19).withLane(2, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0)));
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L))).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L)).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1)).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 16L)).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 8L) * _t40 + UnsafeOpsHolder.U.getDouble(_lb1 + 16L) * _t41)))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double rotX, double rotY, double rotZ, double rotW, double pivotX, double pivotY, double pivotZ) {
        double _t0 = -rotY;
        double _t2 = -rotX;
        double _t3 = -pivotZ;
        double _t5 = rotX + rotX;
        double _t6 = rotY + rotY;
        double _t7 = rotZ + rotZ;
        double _t8 = rotW * _t6;
        double _t9 = rotW * _t7;
        double _t10 = rotW * _t5;
        double _t11 = rotZ * _t7;
        double _t16 = Math.fma(-rotZ, _t7, 1.0);
        double _t18 = Math.fma(rotY, _t5, _t9);
        double _t19 = Math.fma(rotZ, _t6, _t10);
        double _t20 = Math.fma(rotZ, _t5, _t8);
        double _t24 = Math.fma(rotZ, _t5, -_t8);
        double _t25 = Math.fma(rotY, _t5, -_t9);
        double _t26 = Math.fma(rotZ, _t6, -_t10);
        double _t40 = Math.fma(_t3, _t26, Math.fma(pivotY, Math.fma(rotX, _t5, _t11), -(pivotX * _t18)));
        double _t41 = Math.fma(-pivotY, _t19, Math.fma(pivotZ, Math.fma(rotX, _t5, rotY * _t6), -(pivotX * _t24)));
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t6, _t16)).withLane(1, _t25).withLane(2, _t20);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, Math.fma(_t2, _t5, _t16)).withLane(2, _t26).withLane(3, Math.fma(_t3, _t20, Math.fma(pivotX, Math.fma(rotY, _t6, _t11), -(pivotY * _t25))));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, _t19).withLane(2, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0)));
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))).add(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L)).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L) * _t40 + src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L) * _t41)))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static double[] rotateAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] rot, int rotOffset, double[] pivot, int pivotOffset) {
        if (SimdSupport.USE_FMA) return rotateAround_fma(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
        return rotateAround_mulAdd(dest, destOffset, src, srcOffset, rot, rotOffset, pivot, pivotOffset);
    }

    public static double[] rotateAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] rot, int rotOffset, double[] pivot, int pivotOffset) {
        double _pivotx = pivot[pivotOffset];
        double _roty = rot[rotOffset + 1];
        double _pivoty = pivot[pivotOffset + 1];
        double _rotx = rot[rotOffset];
        double _pivotz = pivot[pivotOffset + 2];
        double _rotz = rot[rotOffset + 2];
        double _rotw = rot[rotOffset + 3];
        double _t3 = -_pivotz;
        double _t5 = _rotx + _rotx;
        double _t6 = _roty + _roty;
        double _t7 = _rotz + _rotz;
        double _t8 = _rotw * _t6;
        double _t9 = _rotw * _t7;
        double _t10 = _rotw * _t5;
        double _t11 = _rotz * _t7;
        double _t18 = Math.fma(_roty, _t5, _t9);
        double _t19 = Math.fma(_rotz, _t6, _t10);
        double _t24 = Math.fma(_rotz, _t5, -_t8);
        double _t26 = Math.fma(_rotz, _t6, -_t10);
        rotateAround_fma_s3319d5f8_v(dest, destOffset, src, srcOffset, _pivotx, _roty, _pivoty, -_roty, -_rotx, _t3, _t5, _t6, _t11, Math.fma(-_rotz, _t7, 1.0), _t18, _t19, Math.fma(_rotz, _t5, _t8), _t24, Math.fma(_roty, _t5, -_t9), _t26, Math.fma(_t3, _t26, Math.fma(_pivoty, Math.fma(_rotx, _t5, _t11), -(_pivotx * _t18))), Math.fma(-_pivoty, _t19, Math.fma(_pivotz, Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t24))));
        return dest;
    }

    private static void rotateAround_fma_s3319d5f8_v(double[] dest, int destOffset, double[] src, int srcOffset, double _pivotx, double _roty, double _pivoty, double _t0, double _t2, double _t3, double _t5, double _t6, double _t11, double _t16, double _t18, double _t19, double _t20, double _t24, double _t25, double _t26, double _t40, double _t41) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t6, _t16)).withLane(1, _t25).withLane(2, _t20);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, Math.fma(_t2, _t5, _t16)).withLane(2, _t26).withLane(3, Math.fma(_t3, _t20, Math.fma(_pivotx, Math.fma(_roty, _t6, _t11), -(_pivoty * _t25))));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, _t19).withLane(2, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0)));
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, src[_lo0]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).withLane(3, src[_lo0]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 1] * _t40 + src[_lo0 + 2] * _t41)))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] rotateAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] rot, int rotOffset, double[] pivot, int pivotOffset) {
        double _pivotx = pivot[pivotOffset];
        double _roty = rot[rotOffset + 1];
        double _pivoty = pivot[pivotOffset + 1];
        double _rotx = rot[rotOffset];
        double _pivotz = pivot[pivotOffset + 2];
        double _rotz = rot[rotOffset + 2];
        double _rotw = rot[rotOffset + 3];
        double _t3 = -_pivotz;
        double _t5 = _rotx + _rotx;
        double _t6 = _roty + _roty;
        double _t7 = _rotz + _rotz;
        double _t8 = _rotw * _t6;
        double _t9 = _rotw * _t7;
        double _t10 = _rotw * _t5;
        double _t11 = _rotz * _t7;
        double _t18 = Math.fma(_roty, _t5, _t9);
        double _t19 = Math.fma(_rotz, _t6, _t10);
        double _t24 = Math.fma(_rotz, _t5, -_t8);
        double _t26 = Math.fma(_rotz, _t6, -_t10);
        rotateAround_mulAdd_s6b6a071b_v(dest, destOffset, src, srcOffset, _pivotx, _roty, _pivoty, -_roty, -_rotx, _t3, _t5, _t6, _t11, Math.fma(-_rotz, _t7, 1.0), _t18, _t19, Math.fma(_rotz, _t5, _t8), _t24, Math.fma(_roty, _t5, -_t9), _t26, Math.fma(_t3, _t26, Math.fma(_pivoty, Math.fma(_rotx, _t5, _t11), -(_pivotx * _t18))), Math.fma(-_pivoty, _t19, Math.fma(_pivotz, Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t24))));
        return dest;
    }

    private static void rotateAround_mulAdd_s6b6a071b_v(double[] dest, int destOffset, double[] src, int srcOffset, double _pivotx, double _roty, double _pivoty, double _t0, double _t2, double _t3, double _t5, double _t6, double _t11, double _t16, double _t18, double _t19, double _t20, double _t24, double _t25, double _t26, double _t40, double _t41) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t6, _t16)).withLane(1, _t25).withLane(2, _t20);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, Math.fma(_t2, _t5, _t16)).withLane(2, _t26).withLane(3, Math.fma(_t3, _t20, Math.fma(_pivotx, Math.fma(_roty, _t6, _t11), -(_pivoty * _t25))));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, _t19).withLane(2, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0)));
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, src[_lo0]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).withLane(3, src[_lo0]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 1] * _t40 + src[_lo0 + 2] * _t41)))).intoArray(dest, destOffset + _li * 4);
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
        double _pivotx = UnsafeOpsHolder.U.getDouble(_pivotBase);
        double _roty = UnsafeOpsHolder.U.getDouble(_rotBase + 8L);
        double _pivoty = UnsafeOpsHolder.U.getDouble(_pivotBase + 8L);
        double _rotx = UnsafeOpsHolder.U.getDouble(_rotBase);
        double _pivotz = UnsafeOpsHolder.U.getDouble(_pivotBase + 16L);
        double _rotz = UnsafeOpsHolder.U.getDouble(_rotBase + 16L);
        double _rotw = UnsafeOpsHolder.U.getDouble(_rotBase + 24L);
        double _t0 = -_roty;
        double _t2 = -_rotx;
        double _t3 = -_pivotz;
        double _t5 = _rotx + _rotx;
        double _t6 = _roty + _roty;
        double _t7 = _rotz + _rotz;
        double _t8 = _rotw * _t6;
        double _t9 = _rotw * _t7;
        double _t10 = _rotw * _t5;
        double _t11 = _rotz * _t7;
        double _t16 = Math.fma(-_rotz, _t7, 1.0);
        double _t18 = Math.fma(_roty, _t5, _t9);
        double _t19 = Math.fma(_rotz, _t6, _t10);
        double _t20 = Math.fma(_rotz, _t5, _t8);
        double _t24 = Math.fma(_rotz, _t5, -_t8);
        double _t25 = Math.fma(_roty, _t5, -_t9);
        double _t26 = Math.fma(_rotz, _t6, -_t10);
        double _t40 = Math.fma(_t3, _t26, Math.fma(_pivoty, Math.fma(_rotx, _t5, _t11), -(_pivotx * _t18)));
        double _t41 = Math.fma(-_pivoty, _t19, Math.fma(_pivotz, Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t24)));
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t6, _t16)).withLane(1, _t25).withLane(2, _t20);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, Math.fma(_t2, _t5, _t16)).withLane(2, _t26).withLane(3, Math.fma(_t3, _t20, Math.fma(_pivotx, Math.fma(_roty, _t6, _t11), -(_pivoty * _t25))));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, _t19).withLane(2, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0)));
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L))).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L)).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1)).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 16L)).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 8L) * _t40 + UnsafeOpsHolder.U.getDouble(_lb1 + 16L) * _t41)))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment rot, long rotOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        double _pivotx = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset);
        double _roty = rot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rotOffset + 8L);
        double _pivoty = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L);
        double _rotx = rot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rotOffset);
        double _pivotz = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset + 16L);
        double _rotz = rot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rotOffset + 16L);
        double _rotw = rot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rotOffset + 24L);
        double _t0 = -_roty;
        double _t2 = -_rotx;
        double _t3 = -_pivotz;
        double _t5 = _rotx + _rotx;
        double _t6 = _roty + _roty;
        double _t7 = _rotz + _rotz;
        double _t8 = _rotw * _t6;
        double _t9 = _rotw * _t7;
        double _t10 = _rotw * _t5;
        double _t11 = _rotz * _t7;
        double _t16 = Math.fma(-_rotz, _t7, 1.0);
        double _t18 = Math.fma(_roty, _t5, _t9);
        double _t19 = Math.fma(_rotz, _t6, _t10);
        double _t20 = Math.fma(_rotz, _t5, _t8);
        double _t24 = Math.fma(_rotz, _t5, -_t8);
        double _t25 = Math.fma(_roty, _t5, -_t9);
        double _t26 = Math.fma(_rotz, _t6, -_t10);
        double _t40 = Math.fma(_t3, _t26, Math.fma(_pivoty, Math.fma(_rotx, _t5, _t11), -(_pivotx * _t18)));
        double _t41 = Math.fma(-_pivoty, _t19, Math.fma(_pivotz, Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t24)));
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t6, _t16)).withLane(1, _t25).withLane(2, _t20);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, Math.fma(_t2, _t5, _t16)).withLane(2, _t26).withLane(3, Math.fma(_t3, _t20, Math.fma(_pivotx, Math.fma(_roty, _t6, _t11), -(_pivoty * _t25))));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, _t19).withLane(2, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0)));
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))).add(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L)).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L) * _t40 + src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L) * _t41)))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
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
        double _pivotx = UnsafeOpsHolder.U.getDouble(_pivotBase);
        double _roty = UnsafeOpsHolder.U.getDouble(_rotBase + 8L);
        double _pivoty = UnsafeOpsHolder.U.getDouble(_pivotBase + 8L);
        double _rotx = UnsafeOpsHolder.U.getDouble(_rotBase);
        double _pivotz = UnsafeOpsHolder.U.getDouble(_pivotBase + 16L);
        double _rotz = UnsafeOpsHolder.U.getDouble(_rotBase + 16L);
        double _rotw = UnsafeOpsHolder.U.getDouble(_rotBase + 24L);
        double _t0 = -_roty;
        double _t2 = -_rotx;
        double _t3 = -_pivotz;
        double _t5 = _rotx + _rotx;
        double _t6 = _roty + _roty;
        double _t7 = _rotz + _rotz;
        double _t8 = _rotw * _t6;
        double _t9 = _rotw * _t7;
        double _t10 = _rotw * _t5;
        double _t11 = _rotz * _t7;
        double _t16 = Math.fma(-_rotz, _t7, 1.0);
        double _t18 = Math.fma(_roty, _t5, _t9);
        double _t19 = Math.fma(_rotz, _t6, _t10);
        double _t20 = Math.fma(_rotz, _t5, _t8);
        double _t24 = Math.fma(_rotz, _t5, -_t8);
        double _t25 = Math.fma(_roty, _t5, -_t9);
        double _t26 = Math.fma(_rotz, _t6, -_t10);
        double _t40 = Math.fma(_t3, _t26, Math.fma(_pivoty, Math.fma(_rotx, _t5, _t11), -(_pivotx * _t18)));
        double _t41 = Math.fma(-_pivoty, _t19, Math.fma(_pivotz, Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t24)));
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t6, _t16)).withLane(1, _t25).withLane(2, _t20);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, Math.fma(_t2, _t5, _t16)).withLane(2, _t26).withLane(3, Math.fma(_t3, _t20, Math.fma(_pivotx, Math.fma(_roty, _t6, _t11), -(_pivoty * _t25))));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, _t19).withLane(2, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0)));
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L))).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L)).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1)).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 16L)).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 8L) * _t40 + UnsafeOpsHolder.U.getDouble(_lb1 + 16L) * _t41)))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAround_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment rot, long rotOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        double _pivotx = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset);
        double _roty = rot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rotOffset + 8L);
        double _pivoty = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L);
        double _rotx = rot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rotOffset);
        double _pivotz = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset + 16L);
        double _rotz = rot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rotOffset + 16L);
        double _rotw = rot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, rotOffset + 24L);
        double _t0 = -_roty;
        double _t2 = -_rotx;
        double _t3 = -_pivotz;
        double _t5 = _rotx + _rotx;
        double _t6 = _roty + _roty;
        double _t7 = _rotz + _rotz;
        double _t8 = _rotw * _t6;
        double _t9 = _rotw * _t7;
        double _t10 = _rotw * _t5;
        double _t11 = _rotz * _t7;
        double _t16 = Math.fma(-_rotz, _t7, 1.0);
        double _t18 = Math.fma(_roty, _t5, _t9);
        double _t19 = Math.fma(_rotz, _t6, _t10);
        double _t20 = Math.fma(_rotz, _t5, _t8);
        double _t24 = Math.fma(_rotz, _t5, -_t8);
        double _t25 = Math.fma(_roty, _t5, -_t9);
        double _t26 = Math.fma(_rotz, _t6, -_t10);
        double _t40 = Math.fma(_t3, _t26, Math.fma(_pivoty, Math.fma(_rotx, _t5, _t11), -(_pivotx * _t18)));
        double _t41 = Math.fma(-_pivoty, _t19, Math.fma(_pivotz, Math.fma(_rotx, _t5, _roty * _t6), -(_pivotx * _t24)));
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t6, _t16)).withLane(1, _t25).withLane(2, _t20);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t18).withLane(1, Math.fma(_t2, _t5, _t16)).withLane(2, _t26).withLane(3, Math.fma(_t3, _t20, Math.fma(_pivotx, Math.fma(_roty, _t6, _t11), -(_pivoty * _t25))));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _t24).withLane(1, _t19).withLane(2, Math.fma(_t2, _t5, Math.fma(_t0, _t6, 1.0)));
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))).add(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L)).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L) * _t40 + src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L) * _t41)))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static double[] rotateAxis(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (SimdSupport.USE_FMA) return rotateAxis_fma(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return rotateAxis_mulAdd(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    public static double[] rotateAxis_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisZ;
        double _t5 = axisX * axisY;
        double _t6 = axisY * axisZ;
        double _t11 = 1.0 - _t1;
        rotateAxis_fma_s118d95b9_v(dest, destOffset, src, srcOffset, Math.fma(_t11, _t2, -(axisY * _t0)), Math.fma(axisX, _t0, _t11 * _t6), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(_t11, axisX * axisX, _t1), Math.fma(_t11, _t5, -(axisZ * _t0)), Math.fma(axisY, _t0, _t11 * _t2), Math.fma(axisZ, _t0, _t11 * _t5), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, _t6, -(axisX * _t0)));
        return dest;
    }

    private static void rotateAxis_fma_s118d95b9_v(double[] dest, int destOffset, double[] src, int srcOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h3).withLane(1, _h4).withLane(2, _h5);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h6).withLane(1, _h7).withLane(2, _h8);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, src[_lo0]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] rotateAxis_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        rotateAxis_mulAdd_s461c3d92_v(dest, destOffset, src, srcOffset, axisX, axisY, axisZ, _t0, _t1, axisX * axisZ, axisX * axisY, axisY * axisZ, 1.0 - _t1);
        return dest;
    }

    private static void rotateAxis_mulAdd_s461c3d92_v(double[] dest, int destOffset, double[] src, int srcOffset, double axisX, double axisY, double axisZ, double _t0, double _t1, double _t2, double _t5, double _t6, double _t11) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _t2, -(axisY * _t0))).withLane(1, Math.fma(axisX, _t0, _t11 * _t6)).withLane(2, Math.fma(_t11, axisZ * axisZ, _t1));
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, axisX * axisX, _t1)).withLane(1, Math.fma(_t11, _t5, -(axisZ * _t0))).withLane(2, Math.fma(axisY, _t0, _t11 * _t2));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(axisZ, _t0, _t11 * _t5)).withLane(1, Math.fma(_t11, axisY * axisY, _t1)).withLane(2, Math.fma(_t11, _t6, -(axisX * _t0)));
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static java.lang.foreign.MemorySegment rotateAxis(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateAxis_fma_unsafe(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
            return rotateAxis_fma_api(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateAxis_mulAdd_unsafe(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return rotateAxis_mulAdd_api(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    public static java.lang.foreign.MemorySegment rotateAxis_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateAxis_fma_unsafe(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return rotateAxis_fma_api(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    public static java.lang.foreign.MemorySegment rotateAxis_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        rotateAxis_fma_unsafe_s88bfa61a_v(dest, destOffset, axisX, axisY, axisZ, src.address() + srcOffset, _t0, _t1, axisX * axisZ, axisX * axisY, axisY * axisZ, 1.0 - _t1);
        return dest;
    }

    private static void rotateAxis_fma_unsafe_s88bfa61a_v(java.lang.foreign.MemorySegment dest, long destOffset, double axisX, double axisY, double axisZ, long _srcBase, double _t0, double _t1, double _t2, double _t5, double _t6, double _t11) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _t2, -(axisY * _t0))).withLane(1, Math.fma(axisX, _t0, _t11 * _t6)).withLane(2, Math.fma(_t11, axisZ * axisZ, _t1));
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, axisX * axisX, _t1)).withLane(1, Math.fma(_t11, _t5, -(axisZ * _t0))).withLane(2, Math.fma(axisY, _t0, _t11 * _t2));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(axisZ, _t0, _t11 * _t5)).withLane(1, Math.fma(_t11, axisY * axisY, _t1)).withLane(2, Math.fma(_t11, _t6, -(axisX * _t0)));
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 16L)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1)).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L)).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment rotateAxis_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        rotateAxis_fma_api_sd74cb2d2_v(dest, destOffset, src, srcOffset, axisX, axisY, axisZ, _t0, _t1, axisX * axisZ, axisX * axisY, axisY * axisZ, 1.0 - _t1);
        return dest;
    }

    private static void rotateAxis_fma_api_sd74cb2d2_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double axisX, double axisY, double axisZ, double _t0, double _t1, double _t2, double _t5, double _t6, double _t11) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _t2, -(axisY * _t0))).withLane(1, Math.fma(axisX, _t0, _t11 * _t6)).withLane(2, Math.fma(_t11, axisZ * axisZ, _t1));
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, axisX * axisX, _t1)).withLane(1, Math.fma(_t11, _t5, -(axisZ * _t0))).withLane(2, Math.fma(axisY, _t0, _t11 * _t2));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(axisZ, _t0, _t11 * _t5)).withLane(1, Math.fma(_t11, axisY * axisY, _t1)).withLane(2, Math.fma(_t11, _t6, -(axisX * _t0)));
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L)).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment rotateAxis_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double axisX, double axisY, double axisZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateAxis_mulAdd_unsafe(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
        return rotateAxis_mulAdd_api(dest, destOffset, src, srcOffset, angle, axisX, axisY, axisZ);
    }

    public static java.lang.foreign.MemorySegment rotateAxis_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisZ;
        double _t5 = axisX * axisY;
        double _t6 = axisY * axisZ;
        double _t11 = 1.0 - _t1;
        rotateAxis_mulAdd_unsafe_s96b8f007_v(dest, destOffset, src.address() + srcOffset, Math.fma(_t11, _t2, -(axisY * _t0)), Math.fma(axisX, _t0, _t11 * _t6), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(_t11, axisX * axisX, _t1), Math.fma(_t11, _t5, -(axisZ * _t0)), Math.fma(axisY, _t0, _t11 * _t2), Math.fma(axisZ, _t0, _t11 * _t5), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, _t6, -(axisX * _t0)));
        return dest;
    }

    private static void rotateAxis_mulAdd_unsafe_s96b8f007_v(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h3).withLane(1, _h4).withLane(2, _h5);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h6).withLane(1, _h7).withLane(2, _h8);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 16L)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1)).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L)).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment rotateAxis_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = axisX * axisZ;
        double _t5 = axisX * axisY;
        double _t6 = axisY * axisZ;
        double _t11 = 1.0 - _t1;
        rotateAxis_mulAdd_api_sff4205bd_v(dest, destOffset, src, srcOffset, Math.fma(_t11, _t2, -(axisY * _t0)), Math.fma(axisX, _t0, _t11 * _t6), Math.fma(_t11, axisZ * axisZ, _t1), Math.fma(_t11, axisX * axisX, _t1), Math.fma(_t11, _t5, -(axisZ * _t0)), Math.fma(axisY, _t0, _t11 * _t2), Math.fma(axisZ, _t0, _t11 * _t5), Math.fma(_t11, axisY * axisY, _t1), Math.fma(_t11, _t6, -(axisX * _t0)));
        return dest;
    }

    private static void rotateAxis_mulAdd_api_sff4205bd_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h3).withLane(1, _h4).withLane(2, _h5);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h6).withLane(1, _h7).withLane(2, _h8);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L)).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static double[] rotateAxis(double[] dest, int destOffset, double[] src, int srcOffset, double[] axis, int axisOffset, double angle) {
        if (SimdSupport.USE_FMA) return rotateAxis_fma(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return rotateAxis_mulAdd(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    public static double[] rotateAxis_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] axis, int axisOffset, double angle) {
        double _axisy = axis[axisOffset + 1];
        double _axisx = axis[axisOffset];
        double _axisz = axis[axisOffset + 2];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        rotateAxis_fma_s84fc82b9_v(dest, destOffset, src, srcOffset, _axisy, _axisx, _axisz, _t0, _t1, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, 1.0 - _t1);
        return dest;
    }

    private static void rotateAxis_fma_s84fc82b9_v(double[] dest, int destOffset, double[] src, int srcOffset, double _axisy, double _axisx, double _axisz, double _t0, double _t1, double _t2, double _t5, double _t6, double _t11) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _t2, -(_axisy * _t0))).withLane(1, Math.fma(_axisx, _t0, _t11 * _t6)).withLane(2, Math.fma(_t11, _axisz * _axisz, _t1));
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _axisx * _axisx, _t1)).withLane(1, Math.fma(_t11, _t5, -(_axisz * _t0))).withLane(2, Math.fma(_axisy, _t0, _t11 * _t2));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_axisz, _t0, _t11 * _t5)).withLane(1, Math.fma(_t11, _axisy * _axisy, _t1)).withLane(2, Math.fma(_t11, _t6, -(_axisx * _t0)));
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, src[_lo0]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] rotateAxis_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] axis, int axisOffset, double angle) {
        double _axisy = axis[axisOffset + 1];
        double _axisx = axis[axisOffset];
        double _axisz = axis[axisOffset + 2];
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        rotateAxis_mulAdd_sabf16874_v(dest, destOffset, src, srcOffset, _axisy, _axisx, _axisz, _t0, _t1, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, 1.0 - _t1);
        return dest;
    }

    private static void rotateAxis_mulAdd_sabf16874_v(double[] dest, int destOffset, double[] src, int srcOffset, double _axisy, double _axisx, double _axisz, double _t0, double _t1, double _t2, double _t5, double _t6, double _t11) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _t2, -(_axisy * _t0))).withLane(1, Math.fma(_axisx, _t0, _t11 * _t6)).withLane(2, Math.fma(_t11, _axisz * _axisz, _t1));
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _axisx * _axisx, _t1)).withLane(1, Math.fma(_t11, _t5, -(_axisz * _t0))).withLane(2, Math.fma(_axisy, _t0, _t11 * _t2));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_axisz, _t0, _t11 * _t5)).withLane(1, Math.fma(_t11, _axisy * _axisy, _t1)).withLane(2, Math.fma(_t11, _t6, -(_axisx * _t0)));
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static java.lang.foreign.MemorySegment rotateAxis(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && axis.isNative()) return rotateAxis_fma_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
            return rotateAxis_fma_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && axis.isNative()) return rotateAxis_mulAdd_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return rotateAxis_mulAdd_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    public static java.lang.foreign.MemorySegment rotateAxis_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && axis.isNative()) return rotateAxis_fma_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return rotateAxis_fma_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    public static java.lang.foreign.MemorySegment rotateAxis_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        long _axisBase = axis.address() + axisOffset;
        double _axisy = UnsafeOpsHolder.U.getDouble(_axisBase + 8L);
        double _axisx = UnsafeOpsHolder.U.getDouble(_axisBase);
        double _axisz = UnsafeOpsHolder.U.getDouble(_axisBase + 16L);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        rotateAxis_fma_unsafe_s9d93fdc6_v(dest, destOffset, src.address() + srcOffset, _axisy, _axisx, _axisz, _t0, _t1, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, 1.0 - _t1);
        return dest;
    }

    private static void rotateAxis_fma_unsafe_s9d93fdc6_v(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, double _axisy, double _axisx, double _axisz, double _t0, double _t1, double _t2, double _t5, double _t6, double _t11) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _t2, -(_axisy * _t0))).withLane(1, Math.fma(_axisx, _t0, _t11 * _t6)).withLane(2, Math.fma(_t11, _axisz * _axisz, _t1));
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _axisx * _axisx, _t1)).withLane(1, Math.fma(_t11, _t5, -(_axisz * _t0))).withLane(2, Math.fma(_axisy, _t0, _t11 * _t2));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_axisz, _t0, _t11 * _t5)).withLane(1, Math.fma(_t11, _axisy * _axisy, _t1)).withLane(2, Math.fma(_t11, _t6, -(_axisx * _t0)));
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 16L)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1)).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L)).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment rotateAxis_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        double _axisy = axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset + 8L);
        double _axisx = axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset);
        double _axisz = axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset + 16L);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        rotateAxis_fma_api_sd2661a3e_v(dest, destOffset, src, srcOffset, _axisy, _axisx, _axisz, _t0, _t1, _axisx * _axisz, _axisx * _axisy, _axisy * _axisz, 1.0 - _t1);
        return dest;
    }

    private static void rotateAxis_fma_api_sd2661a3e_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _axisy, double _axisx, double _axisz, double _t0, double _t1, double _t2, double _t5, double _t6, double _t11) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _t2, -(_axisy * _t0))).withLane(1, Math.fma(_axisx, _t0, _t11 * _t6)).withLane(2, Math.fma(_t11, _axisz * _axisz, _t1));
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t11, _axisx * _axisx, _t1)).withLane(1, Math.fma(_t11, _t5, -(_axisz * _t0))).withLane(2, Math.fma(_axisy, _t0, _t11 * _t2));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_axisz, _t0, _t11 * _t5)).withLane(1, Math.fma(_t11, _axisy * _axisy, _t1)).withLane(2, Math.fma(_t11, _t6, -(_axisx * _t0)));
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L)).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment rotateAxis_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && axis.isNative()) return rotateAxis_mulAdd_unsafe(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
        return rotateAxis_mulAdd_api(dest, destOffset, src, srcOffset, axis, axisOffset, angle);
    }

    public static java.lang.foreign.MemorySegment rotateAxis_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        long _axisBase = axis.address() + axisOffset;
        double _axisx = UnsafeOpsHolder.U.getDouble(_axisBase);
        double _axisz = UnsafeOpsHolder.U.getDouble(_axisBase + 16L);
        double _axisy = UnsafeOpsHolder.U.getDouble(_axisBase + 8L);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = _axisx * _axisz;
        double _t5 = _axisx * _axisy;
        double _t6 = _axisy * _axisz;
        double _t11 = 1.0 - _t1;
        rotateAxis_mulAdd_unsafe_s96b8f007_v(dest, destOffset, src.address() + srcOffset, Math.fma(_t11, _t2, -(_axisy * _t0)), Math.fma(_axisx, _t0, _t11 * _t6), Math.fma(_t11, _axisz * _axisz, _t1), Math.fma(_t11, _axisx * _axisx, _t1), Math.fma(_t11, _t5, -(_axisz * _t0)), Math.fma(_axisy, _t0, _t11 * _t2), Math.fma(_axisz, _t0, _t11 * _t5), Math.fma(_t11, _axisy * _axisy, _t1), Math.fma(_t11, _t6, -(_axisx * _t0)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateAxis_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        double _axisx = axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset);
        double _axisz = axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset + 16L);
        double _axisy = axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset + 8L);
        double _t0 = Math.sin(angle);
        double _t1 = Math.cosFromSin(_t0, angle);
        double _t2 = _axisx * _axisz;
        double _t5 = _axisx * _axisy;
        double _t6 = _axisy * _axisz;
        double _t11 = 1.0 - _t1;
        rotateAxis_mulAdd_api_sff4205bd_v(dest, destOffset, src, srcOffset, Math.fma(_t11, _t2, -(_axisy * _t0)), Math.fma(_axisx, _t0, _t11 * _t6), Math.fma(_t11, _axisz * _axisz, _t1), Math.fma(_t11, _axisx * _axisx, _t1), Math.fma(_t11, _t5, -(_axisz * _t0)), Math.fma(_axisy, _t0, _t11 * _t2), Math.fma(_axisz, _t0, _t11 * _t5), Math.fma(_t11, _axisy * _axisy, _t1), Math.fma(_t11, _t6, -(_axisx * _t0)));
        return dest;
    }

    public static double[] rotateQuat(double[] dest, int destOffset, double[] src, int srcOffset, double qX, double qY, double qZ, double qW) {
        if (SimdSupport.USE_FMA) return rotateQuat_fma(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return rotateQuat_mulAdd(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
    }

    public static double[] rotateQuat_fma(double[] dest, int destOffset, double[] src, int srcOffset, double qX, double qY, double qZ, double qW) {
        double _t0 = -qY;
        double _t2 = -qX;
        double _t3 = qX + qX;
        double _t4 = qY + qY;
        double _t5 = qZ + qZ;
        double _t6 = qW * _t4;
        double _t7 = qW * _t5;
        double _t8 = qW * _t3;
        double _t12 = Math.fma(-qZ, _t5, 1.0);
        rotateAxis_fma_s118d95b9_v(dest, destOffset, src, srcOffset, Math.fma(qZ, _t3, -_t6), Math.fma(qZ, _t4, _t8), Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)), Math.fma(_t0, _t4, _t12), Math.fma(qY, _t3, -_t7), Math.fma(qZ, _t3, _t6), Math.fma(qY, _t3, _t7), Math.fma(_t2, _t3, _t12), Math.fma(qZ, _t4, -_t8));
        return dest;
    }

    public static double[] rotateQuat_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double qX, double qY, double qZ, double qW) {
        double _t0 = -qY;
        double _t2 = -qX;
        double _t3 = qX + qX;
        double _t4 = qY + qY;
        double _t5 = qZ + qZ;
        double _t6 = qW * _t4;
        double _t7 = qW * _t5;
        double _t8 = qW * _t3;
        double _t12 = Math.fma(-qZ, _t5, 1.0);
        rotateQuat_mulAdd_sa23574f1_v(dest, destOffset, src, srcOffset, Math.fma(qZ, _t3, -_t6), Math.fma(qZ, _t4, _t8), Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)), Math.fma(_t0, _t4, _t12), Math.fma(qY, _t3, -_t7), Math.fma(qZ, _t3, _t6), Math.fma(qY, _t3, _t7), Math.fma(_t2, _t3, _t12), Math.fma(qZ, _t4, -_t8));
        return dest;
    }

    private static void rotateQuat_mulAdd_sa23574f1_v(double[] dest, int destOffset, double[] src, int srcOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h3).withLane(1, _h4).withLane(2, _h5);
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h6).withLane(1, _h7).withLane(2, _h8);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static java.lang.foreign.MemorySegment rotateQuat(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double qX, double qY, double qZ, double qW) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateQuat_fma_unsafe(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
            return rotateQuat_fma_api(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateQuat_mulAdd_unsafe(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return rotateQuat_mulAdd_api(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
    }

    public static java.lang.foreign.MemorySegment rotateQuat_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double qX, double qY, double qZ, double qW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateQuat_fma_unsafe(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return rotateQuat_fma_api(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
    }

    public static java.lang.foreign.MemorySegment rotateQuat_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double qX, double qY, double qZ, double qW) {
        double _t3 = qX + qX;
        double _t4 = qY + qY;
        double _t5 = qZ + qZ;
        rotateQuat_fma_unsafe_s9e930785_v(dest, destOffset, qY, qZ, src.address() + srcOffset, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, Math.fma(-qZ, _t5, 1.0));
        return dest;
    }

    private static void rotateQuat_fma_unsafe_s9e930785_v(java.lang.foreign.MemorySegment dest, long destOffset, double qY, double qZ, long _srcBase, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(qZ, _t3, -_t6)).withLane(1, Math.fma(qZ, _t4, _t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)));
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(qY, _t3, -_t7)).withLane(2, Math.fma(qZ, _t3, _t6));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(qY, _t3, _t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(qZ, _t4, -_t8));
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 16L)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1)).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L)).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment rotateQuat_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double qX, double qY, double qZ, double qW) {
        double _t3 = qX + qX;
        double _t4 = qY + qY;
        double _t5 = qZ + qZ;
        rotateQuat_fma_api_sa3d0d6b5_v(dest, destOffset, src, srcOffset, qY, qZ, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, Math.fma(-qZ, _t5, 1.0));
        return dest;
    }

    private static void rotateQuat_fma_api_sa3d0d6b5_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double qY, double qZ, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(qZ, _t3, -_t6)).withLane(1, Math.fma(qZ, _t4, _t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)));
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(qY, _t3, -_t7)).withLane(2, Math.fma(qZ, _t3, _t6));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(qY, _t3, _t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(qZ, _t4, -_t8));
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L)).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment rotateQuat_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double qX, double qY, double qZ, double qW) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateQuat_mulAdd_unsafe(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
        return rotateQuat_mulAdd_api(dest, destOffset, src, srcOffset, qX, qY, qZ, qW);
    }

    public static java.lang.foreign.MemorySegment rotateQuat_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double qX, double qY, double qZ, double qW) {
        double _t3 = qX + qX;
        double _t4 = qY + qY;
        double _t5 = qZ + qZ;
        rotateQuat_mulAdd_unsafe_s422baccc_v(dest, destOffset, qY, qZ, src.address() + srcOffset, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, Math.fma(-qZ, _t5, 1.0));
        return dest;
    }

    private static void rotateQuat_mulAdd_unsafe_s422baccc_v(java.lang.foreign.MemorySegment dest, long destOffset, double qY, double qZ, long _srcBase, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(qZ, _t3, -_t6)).withLane(1, Math.fma(qZ, _t4, _t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)));
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(qY, _t3, -_t7)).withLane(2, Math.fma(qZ, _t3, _t6));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(qY, _t3, _t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(qZ, _t4, -_t8));
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 16L)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1)).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L)).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment rotateQuat_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double qX, double qY, double qZ, double qW) {
        double _t3 = qX + qX;
        double _t4 = qY + qY;
        double _t5 = qZ + qZ;
        rotateQuat_mulAdd_api_s61f781aa_v(dest, destOffset, src, srcOffset, qY, qZ, -qY, -qX, _t3, _t4, qW * _t4, qW * _t5, qW * _t3, Math.fma(-qZ, _t5, 1.0));
        return dest;
    }

    private static void rotateQuat_mulAdd_api_s61f781aa_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double qY, double qZ, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(qZ, _t3, -_t6)).withLane(1, Math.fma(qZ, _t4, _t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)));
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(qY, _t3, -_t7)).withLane(2, Math.fma(qZ, _t3, _t6));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(qY, _t3, _t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(qZ, _t4, -_t8));
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L)).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static double[] rotateQuat(double[] dest, int destOffset, double[] src, int srcOffset, double[] q, int qOffset) {
        if (SimdSupport.USE_FMA) return rotateQuat_fma(dest, destOffset, src, srcOffset, q, qOffset);
        return rotateQuat_mulAdd(dest, destOffset, src, srcOffset, q, qOffset);
    }

    public static double[] rotateQuat_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] q, int qOffset) {
        double _qz = q[qOffset + 2];
        double _qy = q[qOffset + 1];
        double _qx = q[qOffset];
        double _qw = q[qOffset + 3];
        double _t3 = _qx + _qx;
        double _t4 = _qy + _qy;
        double _t5 = _qz + _qz;
        rotateQuat_fma_s768c7609_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0));
        return dest;
    }

    private static void rotateQuat_fma_s768c7609_v(double[] dest, int destOffset, double[] src, int srcOffset, double _qz, double _qy, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qz, _t3, -_t6)).withLane(1, Math.fma(_qz, _t4, _t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)));
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(_qy, _t3, -_t7)).withLane(2, Math.fma(_qz, _t3, _t6));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qy, _t3, _t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(_qz, _t4, -_t8));
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, src[_lo0]).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
    }

    public static double[] rotateQuat_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] q, int qOffset) {
        double _qz = q[qOffset + 2];
        double _qy = q[qOffset + 1];
        double _qx = q[qOffset];
        double _qw = q[qOffset + 3];
        double _t3 = _qx + _qx;
        double _t4 = _qy + _qy;
        double _t5 = _qz + _qz;
        rotateQuat_mulAdd_s9e0cc6e0_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0));
        return dest;
    }

    private static void rotateQuat_mulAdd_s9e0cc6e0_v(double[] dest, int destOffset, double[] src, int srcOffset, double _qz, double _qy, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qz, _t3, -_t6)).withLane(1, Math.fma(_qz, _t4, _t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)));
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(_qy, _t3, -_t7)).withLane(2, Math.fma(_qz, _t3, _t6));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qy, _t3, _t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(_qz, _t4, -_t8));
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 2]).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0]).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, src[_lo0 + 1]).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
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
        double _qz = UnsafeOpsHolder.U.getDouble(_qBase + 16L);
        double _qy = UnsafeOpsHolder.U.getDouble(_qBase + 8L);
        double _qx = UnsafeOpsHolder.U.getDouble(_qBase);
        double _qw = UnsafeOpsHolder.U.getDouble(_qBase + 24L);
        double _t3 = _qx + _qx;
        double _t4 = _qy + _qy;
        double _t5 = _qz + _qz;
        rotateQuat_fma_unsafe_sdb511dfc_v(dest, destOffset, src.address() + srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0));
        return dest;
    }

    private static void rotateQuat_fma_unsafe_sdb511dfc_v(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, double _qz, double _qy, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qz, _t3, -_t6)).withLane(1, Math.fma(_qz, _t4, _t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)));
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(_qy, _t3, -_t7)).withLane(2, Math.fma(_qz, _t3, _t6));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qy, _t3, _t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(_qz, _t4, -_t8));
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 16L)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1)).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L)).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment rotateQuat_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        double _qz = q.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, qOffset + 16L);
        double _qy = q.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, qOffset + 8L);
        double _qx = q.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, qOffset);
        double _qw = q.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, qOffset + 24L);
        double _t3 = _qx + _qx;
        double _t4 = _qy + _qy;
        double _t5 = _qz + _qz;
        rotateQuat_fma_api_s6194b4c_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0));
        return dest;
    }

    private static void rotateQuat_fma_api_s6194b4c_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _qz, double _qy, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qz, _t3, -_t6)).withLane(1, Math.fma(_qz, _t4, _t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)));
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(_qy, _t3, -_t7)).withLane(2, Math.fma(_qz, _t3, _t6));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qy, _t3, _t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(_qz, _t4, -_t8));
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).fma(_sv1, DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L)).fma(_sv2, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment rotateQuat_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && q.isNative()) return rotateQuat_mulAdd_unsafe(dest, destOffset, src, srcOffset, q, qOffset);
        return rotateQuat_mulAdd_api(dest, destOffset, src, srcOffset, q, qOffset);
    }

    public static java.lang.foreign.MemorySegment rotateQuat_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        long _qBase = q.address() + qOffset;
        double _qz = UnsafeOpsHolder.U.getDouble(_qBase + 16L);
        double _qy = UnsafeOpsHolder.U.getDouble(_qBase + 8L);
        double _qx = UnsafeOpsHolder.U.getDouble(_qBase);
        double _qw = UnsafeOpsHolder.U.getDouble(_qBase + 24L);
        double _t3 = _qx + _qx;
        double _t4 = _qy + _qy;
        double _t5 = _qz + _qz;
        rotateQuat_mulAdd_unsafe_s2e2b3fdb_v(dest, destOffset, src.address() + srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0));
        return dest;
    }

    private static void rotateQuat_mulAdd_unsafe_s2e2b3fdb_v(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, double _qz, double _qy, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qz, _t3, -_t6)).withLane(1, Math.fma(_qz, _t4, _t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)));
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(_qy, _t3, -_t7)).withLane(2, Math.fma(_qz, _t3, _t6));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qy, _t3, _t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(_qz, _t4, -_t8));
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 16L)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1)).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L)).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment rotateQuat_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment q, long qOffset) {
        double _qz = q.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, qOffset + 16L);
        double _qy = q.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, qOffset + 8L);
        double _qx = q.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, qOffset);
        double _qw = q.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, qOffset + 24L);
        double _t3 = _qx + _qx;
        double _t4 = _qy + _qy;
        double _t5 = _qz + _qz;
        rotateQuat_mulAdd_api_sfe9a91f5_v(dest, destOffset, src, srcOffset, _qz, _qy, -_qy, -_qx, _t3, _t4, _qw * _t4, _qw * _t5, _qw * _t3, Math.fma(-_qz, _t5, 1.0));
        return dest;
    }

    private static void rotateQuat_mulAdd_api_sfe9a91f5_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _qz, double _qy, double _t0, double _t2, double _t3, double _t4, double _t6, double _t7, double _t8, double _t12) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qz, _t3, -_t6)).withLane(1, Math.fma(_qz, _t4, _t8)).withLane(2, Math.fma(_t2, _t3, Math.fma(_t0, _t4, 1.0)));
        var _sv1 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t0, _t4, _t12)).withLane(1, Math.fma(_qy, _t3, -_t7)).withLane(2, Math.fma(_qz, _t3, _t6));
        var _sv2 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_qy, _t3, _t7)).withLane(1, Math.fma(_t2, _t3, _t12)).withLane(2, Math.fma(_qz, _t4, -_t8));
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).mul(_sv1).add(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L)).mul(_sv2).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static double[] rotateYXZ(double[] dest, int destOffset, double[] src, int srcOffset, double angleY, double angleX, double angleZ) {
        if (SimdSupport.USE_FMA) return rotateYXZ_fma(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return rotateYXZ_mulAdd(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
    }

    public static double[] rotateYXZ_fma(double[] dest, int destOffset, double[] src, int srcOffset, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _t3 = Math.cosFromSin(_t1, angleY);
        double _t4 = Math.cosFromSin(_t2, angleZ);
        double _t5 = Math.cosFromSin(_t0, angleX);
        double _t8 = _t0 * _t3;
        return rotateYXZ_fma_s3ab102eb_1(dest, destOffset, src, srcOffset, src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 9], src[srcOffset + 10], _t0, _t1, _t2, _t3, _t4, _t5, _t0 * _t1, _t2 * _t5, _t5 * _t4, _t5 * _t3, Math.fma(_t8, _t4, _t1 * _t2), Math.fma(_t8, _t2, -(_t1 * _t4)));
    }

    /** Piece 2 of {@code rotateYXZ_fma}, split to fit the inline budget; reached only through it. */
    private static double[] rotateYXZ_fma_s3ab102eb_1(double[] dest, int destOffset, double[] src, int srcOffset, double _self01, double _self02, double _self11, double _self12, double _self21, double _self22, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t6, double _t10, double _t16, double _t17, double _t19, double _t20) {
        rotateYXZ_fma_s75ba388b_v(dest, destOffset, src[srcOffset], Math.fma(_t6, _t2, _t3 * _t4), Math.fma(_t6, _t4, -(_t2 * _t3)), _t1 * _t5, _self01 * _t10, _self01 * _t16, _self02 * _t20, _self02 * _t19, _self02 * _t17 - _self01 * _t0, src[srcOffset + 3], src[srcOffset + 4], _self11 * _t10, _self11 * _t16, _self12 * _t20, _self12 * _t19, _self12 * _t17 - _self11 * _t0, src[srcOffset + 7], src[srcOffset + 8], _self21 * _t10, _self21 * _t16, _self22 * _t20, _self22 * _t19, _self22 * _t17 - _self21 * _t0, src[srcOffset + 11]);
        return dest;
    }

    private static void rotateYXZ_fma_s75ba388b_v(double[] dest, int destOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8, double _h9, double _h10, double _h11, double _h12, double _h13, double _h14, double _h15, double _h16, double _h17, double _h18, double _h19, double _h20, double _h21, double _h22, double _h23) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h1).withLane(1, _h2).withLane(2, _h3);
        DoubleVector.broadcast(SIMD_SPECIES, _h0).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, _h4).withLane(1, _h5).add(DoubleVector.broadcast(SIMD_SPECIES, _h6).withLane(1, _h7)).withLane(2, _h8)).withLane(3, _h9).intoArray(dest, destOffset);
        DoubleVector.broadcast(SIMD_SPECIES, _h10).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, _h11).withLane(1, _h12).add(DoubleVector.broadcast(SIMD_SPECIES, _h13).withLane(1, _h14)).withLane(2, _h15)).withLane(3, _h16).intoArray(dest, destOffset + 4);
        DoubleVector.broadcast(SIMD_SPECIES, _h17).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, _h18).withLane(1, _h19).add(DoubleVector.broadcast(SIMD_SPECIES, _h20).withLane(1, _h21)).withLane(2, _h22)).withLane(3, _h23).intoArray(dest, destOffset + 8);
    }

    public static double[] rotateYXZ_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _t3 = Math.cosFromSin(_t1, angleY);
        double _t4 = Math.cosFromSin(_t2, angleZ);
        double _t5 = Math.cosFromSin(_t0, angleX);
        double _t8 = _t0 * _t3;
        return rotateYXZ_mulAdd_sfde087f6_1(dest, destOffset, src, srcOffset, src[srcOffset + 1], src[srcOffset + 2], src[srcOffset + 5], src[srcOffset + 6], src[srcOffset + 9], src[srcOffset + 10], _t0, _t1, _t2, _t3, _t4, _t5, _t0 * _t1, _t2 * _t5, _t5 * _t4, _t5 * _t3, Math.fma(_t8, _t4, _t1 * _t2), Math.fma(_t8, _t2, -(_t1 * _t4)));
    }

    /** Piece 2 of {@code rotateYXZ_mulAdd}, split to fit the inline budget; reached only through it. */
    private static double[] rotateYXZ_mulAdd_sfde087f6_1(double[] dest, int destOffset, double[] src, int srcOffset, double _self01, double _self02, double _self11, double _self12, double _self21, double _self22, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t6, double _t10, double _t16, double _t17, double _t19, double _t20) {
        rotateYXZ_mulAdd_sc6df9532_v(dest, destOffset, src[srcOffset], Math.fma(_t6, _t2, _t3 * _t4), Math.fma(_t6, _t4, -(_t2 * _t3)), _t1 * _t5, _self01 * _t10, _self01 * _t16, _self02 * _t20, _self02 * _t19, _self02 * _t17 - _self01 * _t0, src[srcOffset + 3], src[srcOffset + 4], _self11 * _t10, _self11 * _t16, _self12 * _t20, _self12 * _t19, _self12 * _t17 - _self11 * _t0, src[srcOffset + 7], src[srcOffset + 8], _self21 * _t10, _self21 * _t16, _self22 * _t20, _self22 * _t19, _self22 * _t17 - _self21 * _t0, src[srcOffset + 11]);
        return dest;
    }

    private static void rotateYXZ_mulAdd_sc6df9532_v(double[] dest, int destOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5, double _h6, double _h7, double _h8, double _h9, double _h10, double _h11, double _h12, double _h13, double _h14, double _h15, double _h16, double _h17, double _h18, double _h19, double _h20, double _h21, double _h22, double _h23) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h1).withLane(1, _h2).withLane(2, _h3);
        DoubleVector.broadcast(SIMD_SPECIES, _h0).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, _h4).withLane(1, _h5).add(DoubleVector.broadcast(SIMD_SPECIES, _h6).withLane(1, _h7)).withLane(2, _h8)).withLane(3, _h9).intoArray(dest, destOffset);
        DoubleVector.broadcast(SIMD_SPECIES, _h10).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, _h11).withLane(1, _h12).add(DoubleVector.broadcast(SIMD_SPECIES, _h13).withLane(1, _h14)).withLane(2, _h15)).withLane(3, _h16).intoArray(dest, destOffset + 4);
        DoubleVector.broadcast(SIMD_SPECIES, _h17).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, _h18).withLane(1, _h19).add(DoubleVector.broadcast(SIMD_SPECIES, _h20).withLane(1, _h21)).withLane(2, _h22)).withLane(3, _h23).intoArray(dest, destOffset + 8);
    }

    public static java.lang.foreign.MemorySegment rotateYXZ(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angleY, double angleX, double angleZ) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateYXZ_fma_unsafe(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
            return rotateYXZ_fma_api(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateYXZ_mulAdd_unsafe(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return rotateYXZ_mulAdd_api(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
    }

    public static java.lang.foreign.MemorySegment rotateYXZ_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angleY, double angleX, double angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateYXZ_fma_unsafe(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return rotateYXZ_fma_api(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
    }

    public static java.lang.foreign.MemorySegment rotateYXZ_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _t3 = Math.cosFromSin(_t1, angleY);
        double _t4 = Math.cosFromSin(_t2, angleZ);
        double _t5 = Math.cosFromSin(_t0, angleX);
        double _t8 = _t0 * _t3;
        rotateYXZ_fma_unsafe_s433c3b6a_v(dest, destOffset, src.address() + srcOffset, _t0, _t1, _t2, _t3, _t4, _t5, _t0 * _t1, _t2 * _t5, _t5 * _t4, _t5 * _t3, Math.fma(_t8, _t4, _t1 * _t2), Math.fma(_t8, _t2, -(_t1 * _t4)));
        return dest;
    }

    private static void rotateYXZ_fma_unsafe_s433c3b6a_v(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t6, double _t10, double _t16, double _t17, double _t19, double _t20) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t6, _t2, _t3 * _t4)).withLane(1, Math.fma(_t6, _t4, -(_t2 * _t3))).withLane(2, _t1 * _t5);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L) * _t10).withLane(1, UnsafeOpsHolder.U.getDouble(_lb1 + 8L) * _t16).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 16L) * _t20).withLane(1, UnsafeOpsHolder.U.getDouble(_lb1 + 16L) * _t19)).withLane(2, UnsafeOpsHolder.U.getDouble(_lb1 + 16L) * _t17 - UnsafeOpsHolder.U.getDouble(_lb1 + 8L) * _t0)).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L)).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment rotateYXZ_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _t3 = Math.cosFromSin(_t1, angleY);
        double _t4 = Math.cosFromSin(_t2, angleZ);
        double _t5 = Math.cosFromSin(_t0, angleX);
        double _t8 = _t0 * _t3;
        rotateYXZ_fma_api_s5a94053a_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, _t3, _t4, _t5, _t0 * _t1, _t2 * _t5, _t5 * _t4, _t5 * _t3, Math.fma(_t8, _t4, _t1 * _t2), Math.fma(_t8, _t2, -(_t1 * _t4)));
        return dest;
    }

    private static void rotateYXZ_fma_api_s5a94053a_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t6, double _t10, double _t16, double _t17, double _t19, double _t20) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t6, _t2, _t3 * _t4)).withLane(1, Math.fma(_t6, _t4, -(_t2 * _t3))).withLane(2, _t1 * _t5);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L) * _t10).withLane(1, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L) * _t16).add(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L) * _t20).withLane(1, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L) * _t19)).withLane(2, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L) * _t17 - src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L) * _t0)).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L)).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment rotateYXZ_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angleY, double angleX, double angleZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return rotateYXZ_mulAdd_unsafe(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
        return rotateYXZ_mulAdd_api(dest, destOffset, src, srcOffset, angleY, angleX, angleZ);
    }

    public static java.lang.foreign.MemorySegment rotateYXZ_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _t3 = Math.cosFromSin(_t1, angleY);
        double _t4 = Math.cosFromSin(_t2, angleZ);
        double _t5 = Math.cosFromSin(_t0, angleX);
        double _t8 = _t0 * _t3;
        rotateYXZ_mulAdd_unsafe_sb655e791_v(dest, destOffset, src.address() + srcOffset, _t0, _t1, _t2, _t3, _t4, _t5, _t0 * _t1, _t2 * _t5, _t5 * _t4, _t5 * _t3, Math.fma(_t8, _t4, _t1 * _t2), Math.fma(_t8, _t2, -(_t1 * _t4)));
        return dest;
    }

    private static void rotateYXZ_mulAdd_unsafe_sb655e791_v(java.lang.foreign.MemorySegment dest, long destOffset, long _srcBase, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t6, double _t10, double _t16, double _t17, double _t19, double _t20) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t6, _t2, _t3 * _t4)).withLane(1, Math.fma(_t6, _t4, -(_t2 * _t3))).withLane(2, _t1 * _t5);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 8L) * _t10).withLane(1, UnsafeOpsHolder.U.getDouble(_lb1 + 8L) * _t16).add(DoubleVector.broadcast(SIMD_SPECIES, UnsafeOpsHolder.U.getDouble(_lb1 + 16L) * _t20).withLane(1, UnsafeOpsHolder.U.getDouble(_lb1 + 16L) * _t19)).withLane(2, UnsafeOpsHolder.U.getDouble(_lb1 + 16L) * _t17 - UnsafeOpsHolder.U.getDouble(_lb1 + 8L) * _t0)).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1 + 24L)).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment rotateYXZ_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double angleY, double angleX, double angleZ) {
        double _t0 = Math.sin(angleX);
        double _t1 = Math.sin(angleY);
        double _t2 = Math.sin(angleZ);
        double _t3 = Math.cosFromSin(_t1, angleY);
        double _t4 = Math.cosFromSin(_t2, angleZ);
        double _t5 = Math.cosFromSin(_t0, angleX);
        double _t8 = _t0 * _t3;
        rotateYXZ_mulAdd_api_sdd84062f_v(dest, destOffset, src, srcOffset, _t0, _t1, _t2, _t3, _t4, _t5, _t0 * _t1, _t2 * _t5, _t5 * _t4, _t5 * _t3, Math.fma(_t8, _t4, _t1 * _t2), Math.fma(_t8, _t2, -(_t1 * _t4)));
        return dest;
    }

    private static void rotateYXZ_mulAdd_api_sdd84062f_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _t0, double _t1, double _t2, double _t3, double _t4, double _t5, double _t6, double _t10, double _t16, double _t17, double _t19, double _t20) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, Math.fma(_t6, _t2, _t3 * _t4)).withLane(1, Math.fma(_t6, _t4, -(_t2 * _t3))).withLane(2, _t1 * _t5);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0)).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L) * _t10).withLane(1, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L) * _t16).add(DoubleVector.broadcast(SIMD_SPECIES, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L) * _t20).withLane(1, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L) * _t19)).withLane(2, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L) * _t17 - src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L) * _t0)).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L)).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static double[] scale(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        if (SimdSupport.USE_FMA) return scale_fma(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return scale_mulAdd(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    public static double[] scale_fma(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.fromArray(SIMD_SPECIES, src, _lo0).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] scale_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.fromArray(SIMD_SPECIES, src, _lo0).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY, double vZ) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return scale_fma_unsafe(dest, destOffset, src, srcOffset, vX, vY, vZ);
            return scale_fma_api(dest, destOffset, src, srcOffset, vX, vY, vZ);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return scale_mulAdd_unsafe(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return scale_mulAdd_api(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    public static java.lang.foreign.MemorySegment scale_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY, double vZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return scale_fma_unsafe(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return scale_fma_api(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    public static java.lang.foreign.MemorySegment scale_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY, double vZ) {
        long _srcBase = src.address() + srcOffset;
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 32L, java.nio.ByteOrder.nativeOrder()).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_srcBase + _li * 32L + 24L))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY, double vZ) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.fromMemorySegment(SIMD_SPECIES, src, _lo0, java.nio.ByteOrder.nativeOrder()).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY, double vZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return scale_mulAdd_unsafe(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return scale_mulAdd_api(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    public static java.lang.foreign.MemorySegment scale_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY, double vZ) {
        long _srcBase = src.address() + srcOffset;
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 32L, java.nio.ByteOrder.nativeOrder()).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_srcBase + _li * 32L + 24L))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY, double vZ) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, vX).withLane(1, vY).withLane(2, vZ);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.fromMemorySegment(SIMD_SPECIES, src, _lo0, java.nio.ByteOrder.nativeOrder()).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static double[] scale(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        if (SimdSupport.USE_FMA) return scale_fma(dest, destOffset, src, srcOffset, v, vOffset);
        return scale_mulAdd(dest, destOffset, src, srcOffset, v, vOffset);
    }

    public static double[] scale_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        double _vx = v[vOffset];
        double _vy = v[vOffset + 1];
        double _vz = v[vOffset + 2];
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.fromArray(SIMD_SPECIES, src, _lo0).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] scale_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        double _vx = v[vOffset];
        double _vy = v[vOffset + 1];
        double _vz = v[vOffset + 2];
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.fromArray(SIMD_SPECIES, src, _lo0).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0 + 3])).intoArray(dest, destOffset + _li * 4);
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
        double _vx = UnsafeOpsHolder.U.getDouble(_vBase);
        double _vy = UnsafeOpsHolder.U.getDouble(_vBase + 8L);
        double _vz = UnsafeOpsHolder.U.getDouble(_vBase + 16L);
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 32L, java.nio.ByteOrder.nativeOrder()).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_srcBase + _li * 32L + 24L))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        double _vx = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset);
        double _vy = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        double _vz = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + 16L);
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.fromMemorySegment(SIMD_SPECIES, src, _lo0, java.nio.ByteOrder.nativeOrder()).fma(_sv0, DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
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
        double _vx = UnsafeOpsHolder.U.getDouble(_vBase);
        double _vy = UnsafeOpsHolder.U.getDouble(_vBase + 8L);
        double _vz = UnsafeOpsHolder.U.getDouble(_vBase + 16L);
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 32L, java.nio.ByteOrder.nativeOrder()).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_srcBase + _li * 32L + 24L))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        double _vx = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset);
        double _vy = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        double _vz = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + 16L);
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _vx).withLane(1, _vy).withLane(2, _vz);
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.fromMemorySegment(SIMD_SPECIES, src, _lo0, java.nio.ByteOrder.nativeOrder()).mul(_sv0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static double[] scale(double[] dest, int destOffset, double[] src, int srcOffset, double s) {
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, s).mul(DoubleVector.fromArray(SIMD_SPECIES, src, _lo0)).withLane(3, src[_lo0 + 3]).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return scale_unsafe(dest, destOffset, src, srcOffset, s);
        return scale_api(dest, destOffset, src, srcOffset, s);
    }

    public static java.lang.foreign.MemorySegment scale_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s) {
        long _srcBase = src.address() + srcOffset;
        for (int _li = 0; _li < 3; _li++) {
            DoubleVector.broadcast(SIMD_SPECIES, s).mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 32L, java.nio.ByteOrder.nativeOrder())).withLane(3, UnsafeOpsHolder.U.getDouble(_srcBase + _li * 32L + 24L)).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment scale_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s) {
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.broadcast(SIMD_SPECIES, s).mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, _lo0, java.nio.ByteOrder.nativeOrder())).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 24L)).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static double[] scaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        double _t3 = pivotZ * _t0;
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, s).mul(DoubleVector.fromArray(SIMD_SPECIES, src, _lo0)).withLane(3, Math.fma(src[_lo0], _t1, Math.fma(src[_lo0 + 1], _t2, Math.fma(src[_lo0 + 2], _t3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return scaleAround_unsafe(dest, destOffset, src, srcOffset, s, pivotX, pivotY, pivotZ);
        return scaleAround_api(dest, destOffset, src, srcOffset, s, pivotX, pivotY, pivotZ);
    }

    public static java.lang.foreign.MemorySegment scaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        long _srcBase = src.address() + srcOffset;
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        double _t3 = pivotZ * _t0;
        scaleAround_unsafe_s2ba7e9b_v(dest, destOffset, src, srcOffset, s, Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase), _t1, Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 8L), _t2, Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 16L), _t3, UnsafeOpsHolder.U.getDouble(_srcBase + 24L)))), Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 32L), _t1, Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 40L), _t2, Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 48L), _t3, UnsafeOpsHolder.U.getDouble(_srcBase + 56L)))), Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 64L), _t1, Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 72L), _t2, Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 80L), _t3, UnsafeOpsHolder.U.getDouble(_srcBase + 88L)))));
        return dest;
    }

    private static void scaleAround_unsafe_s2ba7e9b_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s, double _h0, double _h1, double _h2) {
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, s);
        var _c1 = _sv0.mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder())).withLane(3, _h1);
        var _c2 = _sv0.mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder())).withLane(3, _h2);
        _sv0.mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).withLane(3, _h0).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment scaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double s, double pivotX, double pivotY, double pivotZ) {
        double _t0 = 1.0 - s;
        double _t1 = pivotX * _t0;
        double _t2 = pivotY * _t0;
        double _t3 = pivotZ * _t0;
        scaleAround_unsafe_s2ba7e9b_v(dest, destOffset, src, srcOffset, s, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), _t1, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L), _t2, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L), _t3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L)))), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L), _t1, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L), _t2, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L), _t3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L)))), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L), _t1, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 72L), _t2, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 80L), _t3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 88L)))));
        return dest;
    }

    public static double[] scaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] pivot, int pivotOffset, double s) {
        double _t0 = 1.0 - s;
        double _t1 = pivot[pivotOffset] * _t0;
        double _t2 = pivot[pivotOffset + 1] * _t0;
        double _t3 = pivot[pivotOffset + 2] * _t0;
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.broadcast(SIMD_SPECIES, s).mul(DoubleVector.fromArray(SIMD_SPECIES, src, _lo0)).withLane(3, Math.fma(src[_lo0], _t1, Math.fma(src[_lo0 + 1], _t2, Math.fma(src[_lo0 + 2], _t3, src[_lo0 + 3])))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double s) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && pivot.isNative()) return scaleAround_unsafe(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
        return scaleAround_api(dest, destOffset, src, srcOffset, pivot, pivotOffset, s);
    }

    public static java.lang.foreign.MemorySegment scaleAround_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double s) {
        long _srcBase = src.address() + srcOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        double _t0 = 1.0 - s;
        double _t1 = UnsafeOpsHolder.U.getDouble(_pivotBase) * _t0;
        double _t2 = UnsafeOpsHolder.U.getDouble(_pivotBase + 8L) * _t0;
        double _t3 = UnsafeOpsHolder.U.getDouble(_pivotBase + 16L) * _t0;
        scaleAround_unsafe_s2ba7e9b_v(dest, destOffset, src, srcOffset, s, Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase), _t1, Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 8L), _t2, Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 16L), _t3, UnsafeOpsHolder.U.getDouble(_srcBase + 24L)))), Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 32L), _t1, Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 40L), _t2, Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 48L), _t3, UnsafeOpsHolder.U.getDouble(_srcBase + 56L)))), Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 64L), _t1, Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 72L), _t2, Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 80L), _t3, UnsafeOpsHolder.U.getDouble(_srcBase + 88L)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double s) {
        double _t0 = 1.0 - s;
        double _t1 = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset) * _t0;
        double _t2 = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L) * _t0;
        double _t3 = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset + 16L) * _t0;
        scaleAround_unsafe_s2ba7e9b_v(dest, destOffset, src, srcOffset, s, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), _t1, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L), _t2, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L), _t3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L)))), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L), _t1, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L), _t2, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L), _t3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L)))), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L), _t1, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 72L), _t2, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 80L), _t3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 88L)))));
        return dest;
    }

    public static double[] scaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        if (SimdSupport.USE_FMA) return scaleAround_fma(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return scaleAround_mulAdd(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
    }

    public static double[] scaleAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        double _t3 = pivotX * (1.0 - sX);
        double _t4 = pivotY * (1.0 - sY);
        double _t5 = pivotZ * (1.0 - sZ);
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, sX).withLane(1, sY).withLane(2, sZ);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.fma(DoubleVector.fromArray(SIMD_SPECIES, src, _lo0), DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, Math.fma(src[_lo0], _t3, Math.fma(src[_lo0 + 1], _t4, Math.fma(src[_lo0 + 2], _t5, src[_lo0 + 3]))))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static double[] scaleAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        double _t3 = pivotX * (1.0 - sX);
        double _t4 = pivotY * (1.0 - sY);
        double _t5 = pivotZ * (1.0 - sZ);
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, sX).withLane(1, sY).withLane(2, sZ);
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, _lo0)).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, Math.fma(src[_lo0], _t3, Math.fma(src[_lo0 + 1], _t4, Math.fma(src[_lo0 + 2], _t5, src[_lo0 + 3]))))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return scaleAround_fma_unsafe(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
            return scaleAround_fma_api(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return scaleAround_mulAdd_unsafe(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return scaleAround_mulAdd_api(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
    }

    public static java.lang.foreign.MemorySegment scaleAround_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return scaleAround_fma_unsafe(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return scaleAround_fma_api(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
    }

    public static java.lang.foreign.MemorySegment scaleAround_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        long _srcBase = src.address() + srcOffset;
        double _t3 = pivotX * (1.0 - sX);
        double _t4 = pivotY * (1.0 - sY);
        double _t5 = pivotZ * (1.0 - sZ);
        scaleAround_fma_unsafe_s5cc9862f_v(dest, destOffset, src, srcOffset, sX, sY, sZ, Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase), _t3, Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 8L), _t4, Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 16L), _t5, UnsafeOpsHolder.U.getDouble(_srcBase + 24L)))), Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 32L), _t3, Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 40L), _t4, Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 48L), _t5, UnsafeOpsHolder.U.getDouble(_srcBase + 56L)))), Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 64L), _t3, Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 72L), _t4, Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 80L), _t5, UnsafeOpsHolder.U.getDouble(_srcBase + 88L)))));
        return dest;
    }

    private static void scaleAround_fma_unsafe_s5cc9862f_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sX, double sY, double sZ, double _h0, double _h1, double _h2) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, sX).withLane(1, sY).withLane(2, sZ);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = _sv0.fma(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()), _sv1.withLane(3, _h1));
        var _c2 = _sv0.fma(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder()), _sv1.withLane(3, _h2));
        _sv0.fma(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()), _sv1.withLane(3, _h0)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment scaleAround_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        double _t3 = pivotX * (1.0 - sX);
        double _t4 = pivotY * (1.0 - sY);
        double _t5 = pivotZ * (1.0 - sZ);
        scaleAround_fma_unsafe_s5cc9862f_v(dest, destOffset, src, srcOffset, sX, sY, sZ, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), _t3, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L), _t4, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L), _t5, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L)))), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L), _t3, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L), _t4, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L), _t5, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L)))), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L), _t3, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 72L), _t4, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 80L), _t5, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 88L)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return scaleAround_mulAdd_unsafe(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
        return scaleAround_mulAdd_api(dest, destOffset, src, srcOffset, sX, sY, sZ, pivotX, pivotY, pivotZ);
    }

    public static java.lang.foreign.MemorySegment scaleAround_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        long _srcBase = src.address() + srcOffset;
        double _t3 = pivotX * (1.0 - sX);
        double _t4 = pivotY * (1.0 - sY);
        double _t5 = pivotZ * (1.0 - sZ);
        scaleAround_mulAdd_unsafe_s41a16350_v(dest, destOffset, src, srcOffset, sX, sY, sZ, Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase), _t3, Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 8L), _t4, Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 16L), _t5, UnsafeOpsHolder.U.getDouble(_srcBase + 24L)))), Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 32L), _t3, Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 40L), _t4, Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 48L), _t5, UnsafeOpsHolder.U.getDouble(_srcBase + 56L)))), Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 64L), _t3, Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 72L), _t4, Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 80L), _t5, UnsafeOpsHolder.U.getDouble(_srcBase + 88L)))));
        return dest;
    }

    private static void scaleAround_mulAdd_unsafe_s41a16350_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sX, double sY, double sZ, double _h0, double _h1, double _h2) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, sX).withLane(1, sY).withLane(2, sZ);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = _sv0.mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder())).add(_sv1.withLane(3, _h1));
        var _c2 = _sv0.mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder())).add(_sv1.withLane(3, _h2));
        _sv0.mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).add(_sv1.withLane(3, _h0)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment scaleAround_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double sX, double sY, double sZ, double pivotX, double pivotY, double pivotZ) {
        double _t3 = pivotX * (1.0 - sX);
        double _t4 = pivotY * (1.0 - sY);
        double _t5 = pivotZ * (1.0 - sZ);
        scaleAround_mulAdd_unsafe_s41a16350_v(dest, destOffset, src, srcOffset, sX, sY, sZ, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), _t3, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L), _t4, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L), _t5, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L)))), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L), _t3, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L), _t4, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L), _t5, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L)))), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L), _t3, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 72L), _t4, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 80L), _t5, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 88L)))));
        return dest;
    }

    public static double[] scaleAround(double[] dest, int destOffset, double[] src, int srcOffset, double[] s, int sOffset, double[] pivot, int pivotOffset) {
        if (SimdSupport.USE_FMA) return scaleAround_fma(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return scaleAround_mulAdd(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    public static double[] scaleAround_fma(double[] dest, int destOffset, double[] src, int srcOffset, double[] s, int sOffset, double[] pivot, int pivotOffset) {
        double _sx = s[sOffset];
        double _sy = s[sOffset + 1];
        double _sz = s[sOffset + 2];
        double _t3 = pivot[pivotOffset] * (1.0 - _sx);
        double _t4 = pivot[pivotOffset + 1] * (1.0 - _sy);
        double _t5 = pivot[pivotOffset + 2] * (1.0 - _sz);
        scaleAround_fma_s8282f5fd_v(dest, destOffset, src, srcOffset, _sx, _sy, _sz, Math.fma(src[srcOffset], _t3, Math.fma(src[srcOffset + 1], _t4, Math.fma(src[srcOffset + 2], _t5, src[srcOffset + 3]))), Math.fma(src[srcOffset + 4], _t3, Math.fma(src[srcOffset + 5], _t4, Math.fma(src[srcOffset + 6], _t5, src[srcOffset + 7]))), Math.fma(src[srcOffset + 8], _t3, Math.fma(src[srcOffset + 9], _t4, Math.fma(src[srcOffset + 10], _t5, src[srcOffset + 11]))));
        return dest;
    }

    private static void scaleAround_fma_s8282f5fd_v(double[] dest, int destOffset, double[] src, int srcOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = _sv0.fma(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4), _sv1.withLane(3, _h4));
        var _c2 = _sv0.fma(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8), _sv1.withLane(3, _h5));
        _sv0.fma(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset), _sv1.withLane(3, _h3)).intoArray(dest, destOffset);
        _c1.intoArray(dest, destOffset + 4);
        _c2.intoArray(dest, destOffset + 8);
    }

    public static double[] scaleAround_mulAdd(double[] dest, int destOffset, double[] src, int srcOffset, double[] s, int sOffset, double[] pivot, int pivotOffset) {
        double _sx = s[sOffset];
        double _sy = s[sOffset + 1];
        double _sz = s[sOffset + 2];
        double _t3 = pivot[pivotOffset] * (1.0 - _sx);
        double _t4 = pivot[pivotOffset + 1] * (1.0 - _sy);
        double _t5 = pivot[pivotOffset + 2] * (1.0 - _sz);
        scaleAround_mulAdd_s8f6259fc_v(dest, destOffset, src, srcOffset, _sx, _sy, _sz, Math.fma(src[srcOffset], _t3, Math.fma(src[srcOffset + 1], _t4, Math.fma(src[srcOffset + 2], _t5, src[srcOffset + 3]))), Math.fma(src[srcOffset + 4], _t3, Math.fma(src[srcOffset + 5], _t4, Math.fma(src[srcOffset + 6], _t5, src[srcOffset + 7]))), Math.fma(src[srcOffset + 8], _t3, Math.fma(src[srcOffset + 9], _t4, Math.fma(src[srcOffset + 10], _t5, src[srcOffset + 11]))));
        return dest;
    }

    private static void scaleAround_mulAdd_s8f6259fc_v(double[] dest, int destOffset, double[] src, int srcOffset, double _h0, double _h1, double _h2, double _h3, double _h4, double _h5) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _h0).withLane(1, _h1).withLane(2, _h2);
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 4)).add(_sv1.withLane(3, _h4));
        var _c2 = _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset + 8)).add(_sv1.withLane(3, _h5));
        _sv0.mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).add(_sv1.withLane(3, _h3)).intoArray(dest, destOffset);
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
        double _sx = UnsafeOpsHolder.U.getDouble(_sBase);
        double _sy = UnsafeOpsHolder.U.getDouble(_sBase + 8L);
        double _sz = UnsafeOpsHolder.U.getDouble(_sBase + 16L);
        double _pivotx = UnsafeOpsHolder.U.getDouble(_pivotBase);
        double _pivoty = UnsafeOpsHolder.U.getDouble(_pivotBase + 8L);
        double _pivotz = UnsafeOpsHolder.U.getDouble(_pivotBase + 16L);
        scaleAround_fma_unsafe_s5eee419e_v(dest, destOffset, src, srcOffset, src.address() + srcOffset, _sx, _sy, _sz, _pivotx * (1.0 - _sx), _pivoty * (1.0 - _sy), _pivotz * (1.0 - _sz));
        return dest;
    }

    private static void scaleAround_fma_unsafe_s5eee419e_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, long _srcBase, double _sx, double _sy, double _sz, double _t3, double _t4, double _t5) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _sx).withLane(1, _sy).withLane(2, _sz);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            _sv0.fma(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 32L, java.nio.ByteOrder.nativeOrder()), DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, Math.fma(UnsafeOpsHolder.U.getDouble(_lb1), _t3, Math.fma(UnsafeOpsHolder.U.getDouble(_lb1 + 8L), _t4, Math.fma(UnsafeOpsHolder.U.getDouble(_lb1 + 16L), _t5, UnsafeOpsHolder.U.getDouble(_lb1 + 24L)))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment scaleAround_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        double _sx = s.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, sOffset);
        double _sy = s.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, sOffset + 8L);
        double _sz = s.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, sOffset + 16L);
        return scaleAround_fma_api_sa1aa1c14_1(dest, destOffset, src, srcOffset, pivot, pivotOffset, _sx, _sy, _sz);
    }

    /** Piece 2 of {@code scaleAround_fma_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_fma_api_sa1aa1c14_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double _sx, double _sy, double _sz) {
        double _t3 = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset) * (1.0 - _sx);
        double _t4 = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L) * (1.0 - _sy);
        double _t5 = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset + 16L) * (1.0 - _sz);
        scaleAround_fma_unsafe_s5cc9862f_v(dest, destOffset, src, srcOffset, _sx, _sy, _sz, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), _t3, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L), _t4, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L), _t5, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L)))), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L), _t3, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L), _t4, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L), _t5, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L)))), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L), _t3, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 72L), _t4, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 80L), _t5, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 88L)))));
        return dest;
    }

    public static java.lang.foreign.MemorySegment scaleAround_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && s.isNative() && pivot.isNative()) return scaleAround_mulAdd_unsafe(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
        return scaleAround_mulAdd_api(dest, destOffset, src, srcOffset, s, sOffset, pivot, pivotOffset);
    }

    public static java.lang.foreign.MemorySegment scaleAround_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        long _sBase = s.address() + sOffset;
        long _pivotBase = pivot.address() + pivotOffset;
        double _sx = UnsafeOpsHolder.U.getDouble(_sBase);
        double _sy = UnsafeOpsHolder.U.getDouble(_sBase + 8L);
        double _sz = UnsafeOpsHolder.U.getDouble(_sBase + 16L);
        double _pivotx = UnsafeOpsHolder.U.getDouble(_pivotBase);
        double _pivoty = UnsafeOpsHolder.U.getDouble(_pivotBase + 8L);
        double _pivotz = UnsafeOpsHolder.U.getDouble(_pivotBase + 16L);
        scaleAround_mulAdd_unsafe_s766cd391_v(dest, destOffset, src, srcOffset, src.address() + srcOffset, _sx, _sy, _sz, _pivotx * (1.0 - _sx), _pivoty * (1.0 - _sy), _pivotz * (1.0 - _sz));
        return dest;
    }

    private static void scaleAround_mulAdd_unsafe_s766cd391_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, long _srcBase, double _sx, double _sy, double _sz, double _t3, double _t4, double _t5) {
        var _sv0 = DoubleVector.zero(SIMD_SPECIES).withLane(0, _sx).withLane(1, _sy).withLane(2, _sz);
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            _sv0.mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 32L, java.nio.ByteOrder.nativeOrder())).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, Math.fma(UnsafeOpsHolder.U.getDouble(_lb1), _t3, Math.fma(UnsafeOpsHolder.U.getDouble(_lb1 + 8L), _t4, Math.fma(UnsafeOpsHolder.U.getDouble(_lb1 + 16L), _t5, UnsafeOpsHolder.U.getDouble(_lb1 + 24L)))))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
    }

    public static java.lang.foreign.MemorySegment scaleAround_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment s, long sOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset) {
        double _sx = s.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, sOffset);
        double _sy = s.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, sOffset + 8L);
        double _sz = s.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, sOffset + 16L);
        return scaleAround_mulAdd_api_sa56c4201_1(dest, destOffset, src, srcOffset, pivot, pivotOffset, _sx, _sy, _sz);
    }

    /** Piece 2 of {@code scaleAround_mulAdd_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment scaleAround_mulAdd_api_sa56c4201_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment pivot, long pivotOffset, double _sx, double _sy, double _sz) {
        double _t3 = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset) * (1.0 - _sx);
        double _t4 = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset + 8L) * (1.0 - _sy);
        double _t5 = pivot.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, pivotOffset + 16L) * (1.0 - _sz);
        scaleAround_mulAdd_unsafe_s41a16350_v(dest, destOffset, src, srcOffset, _sx, _sy, _sz, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), _t3, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L), _t4, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L), _t5, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L)))), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L), _t3, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L), _t4, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L), _t5, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 56L)))), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L), _t3, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 72L), _t4, Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 80L), _t5, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 88L)))));
        return dest;
    }

    public static double[] translate(double[] dest, int destOffset, double[] src, int srcOffset, double vX, double vY, double vZ) {
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.fromArray(SIMD_SPECIES, src, _lo0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0] * vX + (src[_lo0 + 1] * vY + src[_lo0 + 2] * vZ))).intoArray(dest, destOffset + _li * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment translate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY, double vZ) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return translate_unsafe(dest, destOffset, src, srcOffset, vX, vY, vZ);
        return translate_api(dest, destOffset, src, srcOffset, vX, vY, vZ);
    }

    public static java.lang.foreign.MemorySegment translate_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY, double vZ) {
        long _srcBase = src.address() + srcOffset;
        for (int _li = 0; _li < 3; _li++) {
            long _lb1 = _srcBase + _li * 32L;
            DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + _li * 32L, java.nio.ByteOrder.nativeOrder()).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, UnsafeOpsHolder.U.getDouble(_lb1) * vX + (UnsafeOpsHolder.U.getDouble(_lb1 + 8L) * vY + UnsafeOpsHolder.U.getDouble(_lb1 + 16L) * vZ))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment translate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double vX, double vY, double vZ) {
        for (int _li = 0; _li < 3; _li++) {
            long _lo0 = srcOffset + _li * 32L;
            DoubleVector.fromMemorySegment(SIMD_SPECIES, src, _lo0, java.nio.ByteOrder.nativeOrder()).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0) * vX + (src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 8L) * vY + src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, _lo0 + 16L) * vZ))).intoMemorySegment(dest, destOffset + _li * 32L, java.nio.ByteOrder.nativeOrder());
        }
        return dest;
    }

    public static double[] translate(double[] dest, int destOffset, double[] src, int srcOffset, double[] v, int vOffset) {
        double _vx = v[vOffset];
        double _vy = v[vOffset + 1];
        double _vz = v[vOffset + 2];
        for (int _li = 0; _li < 3; _li++) {
            int _lo0 = srcOffset + _li * 4;
            DoubleVector.fromArray(SIMD_SPECIES, src, _lo0).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, src[_lo0] * _vx + (src[_lo0 + 1] * _vy + src[_lo0 + 2] * _vz))).intoArray(dest, destOffset + _li * 4);
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
        double _vx = UnsafeOpsHolder.U.getDouble(_vBase);
        double _vy = UnsafeOpsHolder.U.getDouble(_vBase + 8L);
        double _vz = UnsafeOpsHolder.U.getDouble(_vBase + 16L);
        translate_unsafe_s533bc9_v(dest, destOffset, src, srcOffset, UnsafeOpsHolder.U.getDouble(_srcBase) * _vx + (UnsafeOpsHolder.U.getDouble(_srcBase + 8L) * _vy + UnsafeOpsHolder.U.getDouble(_srcBase + 16L) * _vz), UnsafeOpsHolder.U.getDouble(_srcBase + 32L) * _vx + (UnsafeOpsHolder.U.getDouble(_srcBase + 40L) * _vy + UnsafeOpsHolder.U.getDouble(_srcBase + 48L) * _vz), UnsafeOpsHolder.U.getDouble(_srcBase + 64L) * _vx + (UnsafeOpsHolder.U.getDouble(_srcBase + 72L) * _vy + UnsafeOpsHolder.U.getDouble(_srcBase + 80L) * _vz));
        return dest;
    }

    private static void translate_unsafe_s533bc9_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double _h0, double _h1, double _h2) {
        var _sv0 = DoubleVector.broadcast(SIMD_SPECIES, 0.0);
        var _c1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).add(_sv0.withLane(3, _h1));
        var _c2 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder()).add(_sv0.withLane(3, _h2));
        DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).add(_sv0.withLane(3, _h0)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        _c1.intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
        _c2.intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment translate_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        double _vx = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset);
        double _vy = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + 8L);
        double _vz = v.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, vOffset + 16L);
        translate_unsafe_s533bc9_v(dest, destOffset, src, srcOffset, src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) * _vx + (src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) * _vy + src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) * _vz), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 32L) * _vx + (src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 40L) * _vy + src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 48L) * _vz), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 64L) * _vx + (src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 72L) * _vy + src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 80L) * _vz));
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
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset + 8).intoArray(dest, destOffset + 8);
        }
        else if (PREFERRED_LANES >= 4) {
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset).intoArray(dest, destOffset);
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset + 4).intoArray(dest, destOffset + 4);
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset + 8).intoArray(dest, destOffset + 8);
        }
        else {
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset).intoArray(dest, destOffset);
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 2).intoArray(dest, destOffset + 2);
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 4).intoArray(dest, destOffset + 4);
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 6).intoArray(dest, destOffset + 6);
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 8).intoArray(dest, destOffset + 8);
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 10).intoArray(dest, destOffset + 10);
        }
    }

    private static void copyArrSeg_one(double[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (PREFERRED_LANES >= 8) {
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_512, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset);
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + 8);
        }
        else if (PREFERRED_LANES >= 4) {
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset);
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + 4);
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + 8);
        }
        else {
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_128, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset);
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_128, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + 2);
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_128, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + 4);
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_128, src, srcOffset + 48L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + 6);
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_128, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + 8);
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_128, src, srcOffset + 80L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + 10);
        }
    }

    private static void copySegArr_one(java.lang.foreign.MemorySegment dest, long destOffset, double[] src, int srcOffset) {
        if (PREFERRED_LANES >= 8) {
            DoubleVector.fromArray(DoubleVector.SPECIES_512, src, srcOffset).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset + 8).intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        }
        else if (PREFERRED_LANES >= 4) {
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset + 4).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset + 8).intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        }
        else {
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 2).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 4).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 6).intoMemorySegment(dest, destOffset + 48L, java.nio.ByteOrder.nativeOrder());
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 8).intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 10).intoMemorySegment(dest, destOffset + 80L, java.nio.ByteOrder.nativeOrder());
        }
    }

    private static void copySegSeg_one(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (PREFERRED_LANES >= 8) {
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_512, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        }
        else if (PREFERRED_LANES >= 4) {
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
        }
        else {
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_128, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_128, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_128, src, srcOffset + 32L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + 32L, java.nio.ByteOrder.nativeOrder());
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_128, src, srcOffset + 48L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + 48L, java.nio.ByteOrder.nativeOrder());
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_128, src, srcOffset + 64L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + 64L, java.nio.ByteOrder.nativeOrder());
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_128, src, srcOffset + 80L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + 80L, java.nio.ByteOrder.nativeOrder());
        }
    }


    public static double[] copy(double[] dest, int destOffset, double[] src, int srcOffset) {
        copyArrArr_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        copyArrArr(dest, destOffset, src, srcOffset, count * 12);
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
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
        if (src.hasArray() && srcOffset >= 0 && (count > 178956970 ? -1 : count * 12) >= 0 && srcOffset <= src.limit() - (count > 178956970 ? -1 : count * 12)) {
            double[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr(dest, destOffset, _srcArr, _srcOff, count * 12);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg(dest, destOffset, _srcSeg, srcOffset * 8L, count * 12);
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
        copyArrSeg(dest, destOffset, _srcSeg, srcOffset, count * 12);
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        copyArrSeg_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        copyArrSeg(dest, destOffset, src, srcOffset, count * 12);
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, double[] src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
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
        if (dest.hasArray() && destOffset >= 0 && (count > 178956970 ? -1 : count * 12) >= 0 && destOffset <= dest.limit() - (count > 178956970 ? -1 : count * 12)) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr(_destArr, _destOff, src, srcOffset, count * 12);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegArr(_destSeg, destOffset * 8L, src, srcOffset, count * 12);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr_one(_destArr, _destOff, _srcArr, _srcOff);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copyArrSeg_one(_destArr, _destOff, _srcSeg, srcOffset * 8L);
            }
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
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
        if (dest.hasArray() && destOffset >= 0 && (count > 178956970 ? -1 : count * 12) >= 0 && destOffset <= dest.limit() - (count > 178956970 ? -1 : count * 12)) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && (count > 178956970 ? -1 : count * 12) >= 0 && srcOffset <= src.limit() - (count > 178956970 ? -1 : count * 12)) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr(_destArr, _destOff, _srcArr, _srcOff, count * 12);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copyArrSeg(_destArr, _destOff, _srcSeg, srcOffset * 8L, count * 12);
            }
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            if (src.hasArray() && srcOffset >= 0 && (count > 178956970 ? -1 : count * 12) >= 0 && srcOffset <= src.limit() - (count > 178956970 ? -1 : count * 12)) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copySegArr(_destSeg, destOffset * 8L, _srcArr, _srcOff, count * 12);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copySegSeg(_destSeg, destOffset * 8L, _srcSeg, srcOffset * 8L, count * 12);
            }
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
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
        if (dest.hasArray() && destOffset >= 0 && (count > 178956970 ? -1 : count * 12) >= 0 && destOffset <= dest.limit() - (count > 178956970 ? -1 : count * 12)) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg(_destArr, _destOff, _srcSeg, srcOffset, count * 12);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg(_destSeg, destOffset * 8L, _srcSeg, srcOffset, count * 12);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 12) {
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
        if (dest.hasArray() && destOffset >= 0 && (count > 178956970 ? -1 : count * 12) >= 0 && destOffset <= dest.limit() - (count > 178956970 ? -1 : count * 12)) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrSeg(_destArr, _destOff, src, srcOffset, count * 12);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegSeg(_destSeg, destOffset * 8L, src, srcOffset, count * 12);
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
        copySegArr(_destSeg, destOffset, src, srcOffset, count * 12);
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
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
        if (src.hasArray() && srcOffset >= 0 && (count > 178956970 ? -1 : count * 12) >= 0 && srcOffset <= src.limit() - (count > 178956970 ? -1 : count * 12)) {
            double[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr(_destSeg, destOffset, _srcArr, _srcOff, count * 12);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg(_destSeg, destOffset, _srcSeg, srcOffset * 8L, count * 12);
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

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, double[] src, int srcOffset) {
        copySegArr_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        copySegArr(dest, destOffset, src, srcOffset, count * 12);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 12) {
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
        if (src.hasArray() && srcOffset >= 0 && (count > 178956970 ? -1 : count * 12) >= 0 && srcOffset <= src.limit() - (count > 178956970 ? -1 : count * 12)) {
            double[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr(dest, destOffset, _srcArr, _srcOff, count * 12);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg(dest, destOffset, _srcSeg, srcOffset * 8L, count * 12);
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
