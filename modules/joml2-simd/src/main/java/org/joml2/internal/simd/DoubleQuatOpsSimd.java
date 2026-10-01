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

    public static java.lang.foreign.MemorySegment add(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        DoubleVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder()).add(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static double[] mul(double[] dest, int destOffset, double[] src, int srcOffset, double scalar) {
        DoubleVector.broadcast(SIMD_SPECIES, scalar).mul(DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double scalar) {
        DoubleVector.broadcast(SIMD_SPECIES, scalar).mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static double[] negate(double[] dest, int destOffset, double[] src, int srcOffset) {
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).neg().intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).neg().intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static double[] sub(double[] dest, int destOffset, double[] src, int srcOffset, double[] other, int otherOffset) {
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).sub(DoubleVector.fromArray(SIMD_SPECIES, other, otherOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).sub(DoubleVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static double[] set(double[] dest, int destOffset, double[] v, int vOffset) {
        DoubleVector.fromArray(SIMD_SPECIES, v, vOffset).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        DoubleVector.fromMemorySegment(SIMD_SPECIES, v, vOffset, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
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

    public static java.lang.foreign.MemorySegment toDualQuat(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return toDualQuat_unsafe(dest, destOffset, src, srcOffset);
        return toDualQuat_api(dest, destOffset, src, srcOffset);
    }

    public static java.lang.foreign.MemorySegment toDualQuat_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        var _vcp0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        _vcp0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        UnsafeOpsHolder.U.putDouble(_destBase + 32L, 0.0);
        UnsafeOpsHolder.U.putDouble(_destBase + 40L, 0.0);
        UnsafeOpsHolder.U.putDouble(_destBase + 48L, 0.0);
        UnsafeOpsHolder.U.putDouble(_destBase + 56L, 0.0);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toDualQuat_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        var _vcp0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        _vcp0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 32L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 40L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 48L, 0.0);
        dest.set(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, destOffset + 56L, 0.0);
        return dest;
    }

    public static double[] makeZero(double[] dest, int destOffset) {
        DoubleVector.broadcast(SIMD_SPECIES, 0.0).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeZero(java.lang.foreign.MemorySegment dest, long destOffset) {
        DoubleVector.broadcast(SIMD_SPECIES, 0.0).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
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

    public static java.lang.foreign.MemorySegment lerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    public static java.lang.foreign.MemorySegment lerp_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        DoubleVector.broadcast(SIMD_SPECIES, t).fma(DoubleVector.zero(SIMD_SPECIES).withLane(0, otherX).withLane(1, otherY).withLane(2, otherZ).withLane(3, otherW).sub(_sv0), _sv0).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double otherX, double otherY, double otherZ, double otherW, double t) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        DoubleVector.broadcast(SIMD_SPECIES, t).mul(DoubleVector.zero(SIMD_SPECIES).withLane(0, otherX).withLane(1, otherY).withLane(2, otherZ).withLane(3, otherW).sub(_sv0)).add(_sv0).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
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

    public static java.lang.foreign.MemorySegment lerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static java.lang.foreign.MemorySegment lerp_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        DoubleVector.broadcast(SIMD_SPECIES, t).fma(DoubleVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder()).sub(_sv0), _sv0).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double t) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        DoubleVector.broadcast(SIMD_SPECIES, t).mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder()).sub(_sv0)).add(_sv0).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
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

    public static java.lang.foreign.MemorySegment nlerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double alpha) {
        if (SimdSupport.USE_FMA) return nlerp_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return nlerp_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment nlerp_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double alpha) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, alpha).fma(DoubleVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder()).sub(_sv0), _sv0);
        double _t11 = _sv1.mul(_sv1).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t11  !=  0.0 ? _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, (1.0 / java.lang.Math.sqrt(_t11)))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment nlerp_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double alpha) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.broadcast(SIMD_SPECIES, alpha).mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder()).sub(_sv0)).add(_sv0);
        double _t11 = _sv1.mul(_sv1).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t11  !=  0.0 ? _sv1.mul(DoubleVector.broadcast(SIMD_SPECIES, (1.0 / java.lang.Math.sqrt(_t11)))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
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

    public static java.lang.foreign.MemorySegment nlerpShortest(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double alpha) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return nlerpShortest_fma_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
            return nlerpShortest_fma_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return nlerpShortest_mulAdd_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return nlerpShortest_mulAdd_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment nlerpShortest_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return nlerpShortest_fma_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return nlerpShortest_fma_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment nlerpShortest_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double alpha) {
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, alpha).fma((-Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 24L), UnsafeOpsHolder.U.getDouble(_targetBase + 24L), Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 16L), UnsafeOpsHolder.U.getDouble(_targetBase + 16L), Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase), UnsafeOpsHolder.U.getDouble(_targetBase), UnsafeOpsHolder.U.getDouble(_srcBase + 8L) * UnsafeOpsHolder.U.getDouble(_targetBase + 8L))))  >  0.0 ? _sv0.neg() : _sv0).sub(_sv1), _sv1);
        double _t24 = _sv2.mul(_sv2).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t24  !=  0.0 ? _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, (1.0 / java.lang.Math.sqrt(_t24)))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment nlerpShortest_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double alpha) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, alpha).fma((-Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L), target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset + 24L), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L), target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset + 16L), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) * target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset + 8L))))  >  0.0 ? _sv0.neg() : _sv0).sub(_sv1), _sv1);
        double _t24 = _sv2.mul(_sv2).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t24  !=  0.0 ? _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, (1.0 / java.lang.Math.sqrt(_t24)))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment nlerpShortest_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return nlerpShortest_mulAdd_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return nlerpShortest_mulAdd_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment nlerpShortest_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double alpha) {
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, alpha).mul((-Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 24L), UnsafeOpsHolder.U.getDouble(_targetBase + 24L), Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 16L), UnsafeOpsHolder.U.getDouble(_targetBase + 16L), Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase), UnsafeOpsHolder.U.getDouble(_targetBase), UnsafeOpsHolder.U.getDouble(_srcBase + 8L) * UnsafeOpsHolder.U.getDouble(_targetBase + 8L))))  >  0.0 ? _sv0.neg() : _sv0).sub(_sv1)).add(_sv1);
        double _t24 = _sv2.mul(_sv2).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t24  !=  0.0 ? _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, (1.0 / java.lang.Math.sqrt(_t24)))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment nlerpShortest_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double alpha) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = DoubleVector.broadcast(SIMD_SPECIES, alpha).mul((-Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L), target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset + 24L), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L), target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset + 16L), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) * target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset + 8L))))  >  0.0 ? _sv0.neg() : _sv0).sub(_sv1)).add(_sv1);
        double _t24 = _sv2.mul(_sv2).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t24  !=  0.0 ? _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, (1.0 / java.lang.Math.sqrt(_t24)))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
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

    public static java.lang.foreign.MemorySegment slerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return slerp_fma_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
            return slerp_fma_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return slerp_mulAdd_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return slerp_mulAdd_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    public static java.lang.foreign.MemorySegment slerp_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return slerp_fma_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return slerp_fma_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    public static java.lang.foreign.MemorySegment slerp_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        long _srcBase = src.address() + srcOffset;
        double _t0 = 1.0 - alpha;
        double _t1 = UnsafeOpsHolder.U.getDouble(_srcBase + 24L) + targetW;
        double _t2 = UnsafeOpsHolder.U.getDouble(_srcBase + 16L) + targetZ;
        double _t3 = UnsafeOpsHolder.U.getDouble(_srcBase) + targetX;
        double _t4 = UnsafeOpsHolder.U.getDouble(_srcBase + 8L) + targetY;
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
        DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).fma(DoubleVector.broadcast(SIMD_SPECIES, _w0), DoubleVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(DoubleVector.broadcast(SIMD_SPECIES, _w1))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _t0 = 1.0 - alpha;
        double _t1 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) + targetW;
        double _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) + targetZ;
        double _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) + targetX;
        double _t4 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) + targetY;
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
        DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).fma(DoubleVector.broadcast(SIMD_SPECIES, _w0), DoubleVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(DoubleVector.broadcast(SIMD_SPECIES, _w1))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return slerp_mulAdd_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return slerp_mulAdd_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    public static java.lang.foreign.MemorySegment slerp_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        long _srcBase = src.address() + srcOffset;
        double _t0 = 1.0 - alpha;
        double _t1 = UnsafeOpsHolder.U.getDouble(_srcBase + 24L) + targetW;
        double _t2 = UnsafeOpsHolder.U.getDouble(_srcBase + 16L) + targetZ;
        double _t3 = UnsafeOpsHolder.U.getDouble(_srcBase) + targetX;
        double _t4 = UnsafeOpsHolder.U.getDouble(_srcBase + 8L) + targetY;
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
        slerp_mulAdd_unsafe_sf9840a7_v(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, _w0, _w1);
        return dest;
    }

    private static void slerp_mulAdd_unsafe_sf9840a7_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double targetX, double targetY, double targetZ, double targetW, double _w0, double _w1) {
        DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(DoubleVector.broadcast(SIMD_SPECIES, _w0)).add(DoubleVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(DoubleVector.broadcast(SIMD_SPECIES, _w1))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment slerp_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, double targetX, double targetY, double targetZ, double targetW, double alpha) {
        double _t0 = 1.0 - alpha;
        double _t1 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) + targetW;
        double _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) + targetZ;
        double _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) + targetX;
        double _t4 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) + targetY;
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
        DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(DoubleVector.broadcast(SIMD_SPECIES, _w0)).add(DoubleVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(DoubleVector.broadcast(SIMD_SPECIES, _w1))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
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

    public static java.lang.foreign.MemorySegment slerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double alpha) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return slerp_fma_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
            return slerp_fma_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return slerp_mulAdd_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerp_mulAdd_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment slerp_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return slerp_fma_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerp_fma_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment slerp_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double alpha) {
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        double _t0 = 1.0 - alpha;
        double _t1 = UnsafeOpsHolder.U.getDouble(_srcBase + 24L) + UnsafeOpsHolder.U.getDouble(_targetBase + 24L);
        double _t2 = UnsafeOpsHolder.U.getDouble(_srcBase + 16L) + UnsafeOpsHolder.U.getDouble(_targetBase + 16L);
        double _t3 = UnsafeOpsHolder.U.getDouble(_srcBase) + UnsafeOpsHolder.U.getDouble(_targetBase);
        double _t4 = UnsafeOpsHolder.U.getDouble(_srcBase + 8L) + UnsafeOpsHolder.U.getDouble(_targetBase + 8L);
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
        slerp_fma_unsafe_s2fe35279_v(dest, destOffset, src, srcOffset, target, targetOffset, _w0, _w1);
        return dest;
    }

    private static void slerp_fma_unsafe_s2fe35279_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double _w0, double _w1) {
        DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).fma(DoubleVector.broadcast(SIMD_SPECIES, _w0), DoubleVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder()).mul(DoubleVector.broadcast(SIMD_SPECIES, _w1))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment slerp_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double alpha) {
        double _t0 = 1.0 - alpha;
        double _t1 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) + target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset + 24L);
        double _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) + target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset + 16L);
        double _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) + target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset);
        double _t4 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) + target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset + 8L);
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
        slerp_fma_unsafe_s2fe35279_v(dest, destOffset, src, srcOffset, target, targetOffset, _w0, _w1);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return slerp_mulAdd_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerp_mulAdd_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment slerp_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double alpha) {
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        double _t0 = 1.0 - alpha;
        double _t1 = UnsafeOpsHolder.U.getDouble(_srcBase + 24L) + UnsafeOpsHolder.U.getDouble(_targetBase + 24L);
        double _t2 = UnsafeOpsHolder.U.getDouble(_srcBase + 16L) + UnsafeOpsHolder.U.getDouble(_targetBase + 16L);
        double _t3 = UnsafeOpsHolder.U.getDouble(_srcBase) + UnsafeOpsHolder.U.getDouble(_targetBase);
        double _t4 = UnsafeOpsHolder.U.getDouble(_srcBase + 8L) + UnsafeOpsHolder.U.getDouble(_targetBase + 8L);
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
        slerp_mulAdd_unsafe_sabbcc62e_v(dest, destOffset, src, srcOffset, target, targetOffset, _w0, _w1);
        return dest;
    }

    private static void slerp_mulAdd_unsafe_sabbcc62e_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double _w0, double _w1) {
        DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(DoubleVector.broadcast(SIMD_SPECIES, _w0)).add(DoubleVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder()).mul(DoubleVector.broadcast(SIMD_SPECIES, _w1))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment slerp_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double alpha) {
        double _t0 = 1.0 - alpha;
        double _t1 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L) + target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset + 24L);
        double _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L) + target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset + 16L);
        double _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset) + target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset);
        double _t4 = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) + target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset + 8L);
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
        slerp_mulAdd_unsafe_sabbcc62e_v(dest, destOffset, src, srcOffset, target, targetOffset, _w0, _w1);
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

    public static java.lang.foreign.MemorySegment slerpShortest(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double alpha) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return slerpShortest_fma_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
            return slerpShortest_fma_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return slerpShortest_mulAdd_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerpShortest_mulAdd_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment slerpShortest_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return slerpShortest_fma_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerpShortest_fma_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment slerpShortest_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double alpha) {
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        double _t12 = Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 24L), UnsafeOpsHolder.U.getDouble(_targetBase + 24L), Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 16L), UnsafeOpsHolder.U.getDouble(_targetBase + 16L), Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase), UnsafeOpsHolder.U.getDouble(_targetBase), UnsafeOpsHolder.U.getDouble(_srcBase + 8L) * UnsafeOpsHolder.U.getDouble(_targetBase + 8L))));
        double _t16 = Math.acos(java.lang.Math.min(1.0, java.lang.Math.abs(_t12)));
        slerpShortest_fma_unsafe_s8c8b547_v(dest, destOffset, src, srcOffset, target, targetOffset, alpha, 1.0 - alpha, _t12, _t16, Math.sin(_t16));
        return dest;
    }

    private static void slerpShortest_fma_unsafe_s8c8b547_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double alpha, double _t0, double _t12, double _t16, double _t17) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = (-_t12  >  0.0 ? _sv1.neg() : _sv1);
        var _sv3 = (_t17  >  0.0 ? _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.sin(_t0 * _t16)), DoubleVector.broadcast(SIMD_SPECIES, Math.sin(alpha * _t16)).mul(_sv2)).mul(DoubleVector.broadcast(SIMD_SPECIES, 1.0 / _t17)) : DoubleVector.broadcast(SIMD_SPECIES, alpha).fma(_sv2, _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t0))));
        double _t49 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t49  !=  0.0 ? _sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, (1.0 / java.lang.Math.sqrt(_t49)))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment slerpShortest_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double alpha) {
        double _t12 = Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L), target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset + 24L), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L), target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset + 16L), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) * target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset + 8L))));
        double _t16 = Math.acos(java.lang.Math.min(1.0, java.lang.Math.abs(_t12)));
        slerpShortest_fma_unsafe_s8c8b547_v(dest, destOffset, src, srcOffset, target, targetOffset, alpha, 1.0 - alpha, _t12, _t16, Math.sin(_t16));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerpShortest_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return slerpShortest_mulAdd_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerpShortest_mulAdd_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment slerpShortest_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double alpha) {
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        double _t12 = Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 24L), UnsafeOpsHolder.U.getDouble(_targetBase + 24L), Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase + 16L), UnsafeOpsHolder.U.getDouble(_targetBase + 16L), Math.fma(UnsafeOpsHolder.U.getDouble(_srcBase), UnsafeOpsHolder.U.getDouble(_targetBase), UnsafeOpsHolder.U.getDouble(_srcBase + 8L) * UnsafeOpsHolder.U.getDouble(_targetBase + 8L))));
        double _t16 = Math.acos(java.lang.Math.min(1.0, java.lang.Math.abs(_t12)));
        slerpShortest_mulAdd_unsafe_s6b0935c4_v(dest, destOffset, src, srcOffset, target, targetOffset, alpha, 1.0 - alpha, _t12, _t16, Math.sin(_t16));
        return dest;
    }

    private static void slerpShortest_mulAdd_unsafe_s6b0935c4_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double alpha, double _t0, double _t12, double _t16, double _t17) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = (-_t12  >  0.0 ? _sv1.neg() : _sv1);
        var _sv3 = (_t17  >  0.0 ? _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.sin(_t0 * _t16))).add(DoubleVector.broadcast(SIMD_SPECIES, Math.sin(alpha * _t16)).mul(_sv2)).mul(DoubleVector.broadcast(SIMD_SPECIES, 1.0 / _t17)) : DoubleVector.broadcast(SIMD_SPECIES, alpha).mul(_sv2).add(_sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t0))));
        double _t49 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t49  !=  0.0 ? _sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, (1.0 / java.lang.Math.sqrt(_t49)))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment slerpShortest_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double alpha) {
        double _t12 = Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L), target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset + 24L), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L), target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset + 16L), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset), target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset), src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L) * target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset + 8L))));
        double _t16 = Math.acos(java.lang.Math.min(1.0, java.lang.Math.abs(_t12)));
        slerpShortest_mulAdd_unsafe_s6b0935c4_v(dest, destOffset, src, srcOffset, target, targetOffset, alpha, 1.0 - alpha, _t12, _t16, Math.sin(_t16));
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

    public static java.lang.foreign.MemorySegment addScaled(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double weight) {
        if (SimdSupport.USE_FMA) return addScaled_fma(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return addScaled_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    public static java.lang.foreign.MemorySegment addScaled_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double weight) {
        DoubleVector.broadcast(SIMD_SPECIES, weight).fma(DoubleVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder()), DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, double weight) {
        DoubleVector.broadcast(SIMD_SPECIES, weight).mul(DoubleVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder())).add(DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static double[] calculateW(double[] dest, int destOffset, double[] src, int srcOffset) {
        double _selfx = src[srcOffset];
        double _selfy = src[srcOffset + 1];
        double _selfz = src[srcOffset + 2];
        DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(3, java.lang.Math.sqrt(java.lang.Math.max(0.0, Math.fma(-_selfx, _selfx, Math.fma(-_selfy, _selfy, Math.fma(-_selfz, _selfz, 1.0)))))).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment calculateW(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return calculateW_unsafe(dest, destOffset, src, srcOffset);
        return calculateW_api(dest, destOffset, src, srcOffset);
    }

    public static java.lang.foreign.MemorySegment calculateW_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        double _selfx = UnsafeOpsHolder.U.getDouble(_srcBase);
        double _selfy = UnsafeOpsHolder.U.getDouble(_srcBase + 8L);
        double _selfz = UnsafeOpsHolder.U.getDouble(_srcBase + 16L);
        DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).withLane(3, java.lang.Math.sqrt(java.lang.Math.max(0.0, Math.fma(-_selfx, _selfx, Math.fma(-_selfy, _selfy, Math.fma(-_selfz, _selfz, 1.0)))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment calculateW_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).withLane(3, java.lang.Math.sqrt(java.lang.Math.max(0.0, Math.fma(-_selfx, _selfx, Math.fma(-_selfy, _selfy, Math.fma(-_selfz, _selfz, 1.0)))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
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

    public static java.lang.foreign.MemorySegment exp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return exp_unsafe(dest, destOffset, src, srcOffset);
        return exp_api(dest, destOffset, src, srcOffset);
    }

    public static java.lang.foreign.MemorySegment exp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        double _selfz = UnsafeOpsHolder.U.getDouble(_srcBase + 16L);
        double _selfx = UnsafeOpsHolder.U.getDouble(_srcBase);
        double _selfy = UnsafeOpsHolder.U.getDouble(_srcBase + 8L);
        double _t2 = java.lang.Math.min(Math.exp(UnsafeOpsHolder.U.getDouble(_srcBase + 24L)), 1.7976931348623157E308);
        double _t4 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        double _t5 = java.lang.Math.sqrt(_t4);
        double _t7 = Math.sin(_t5);
        (_t4  >  0.0 ? DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).withLane(3, Math.cosFromSin(_t7, _t5)).mul(DoubleVector.broadcast(SIMD_SPECIES, _t2 * (_t7 * (1.0 / java.lang.Math.sqrt(_t4)))).withLane(3, _t2)) : DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, Math.cosFromSin(_t7, _t5) * _t2)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _t2 = java.lang.Math.min(Math.exp(src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L)), 1.7976931348623157E308);
        double _t4 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        double _t5 = java.lang.Math.sqrt(_t4);
        double _t7 = Math.sin(_t5);
        (_t4  >  0.0 ? DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).withLane(3, Math.cosFromSin(_t7, _t5)).mul(DoubleVector.broadcast(SIMD_SPECIES, _t2 * (_t7 * (1.0 / java.lang.Math.sqrt(_t4)))).withLane(3, _t2)) : DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, Math.cosFromSin(_t7, _t5) * _t2)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
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

    public static java.lang.foreign.MemorySegment log(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return log_unsafe(dest, destOffset, src, srcOffset);
        return log_api(dest, destOffset, src, srcOffset);
    }

    public static java.lang.foreign.MemorySegment log_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        double _selfw = UnsafeOpsHolder.U.getDouble(_srcBase + 24L);
        double _selfz = UnsafeOpsHolder.U.getDouble(_srcBase + 16L);
        double _selfx = UnsafeOpsHolder.U.getDouble(_srcBase);
        double _selfy = UnsafeOpsHolder.U.getDouble(_srcBase + 8L);
        double _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        (_t2  >  0.0 ? DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(DoubleVector.broadcast(SIMD_SPECIES, Math.atan2(java.lang.Math.sqrt(_t2), _selfw) * (1.0 / java.lang.Math.sqrt(_t2)))).withLane(3, Math.log(java.lang.Math.sqrt(Math.fma(_selfw, _selfw, _t2)))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, Math.log(java.lang.Math.sqrt(Math.fma(_selfw, _selfw, _t2))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment log_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        (_t2  >  0.0 ? DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(DoubleVector.broadcast(SIMD_SPECIES, Math.atan2(java.lang.Math.sqrt(_t2), _selfw) * (1.0 / java.lang.Math.sqrt(_t2)))).withLane(3, Math.log(java.lang.Math.sqrt(Math.fma(_selfw, _selfw, _t2)))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, Math.log(java.lang.Math.sqrt(Math.fma(_selfw, _selfw, _t2))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static double[] normalize(double[] dest, int destOffset, double[] src, int srcOffset) {
        var _sv0 = DoubleVector.fromArray(SIMD_SPECIES, src, srcOffset);
        double _t3 = _sv0.mul(_sv0).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t3  !=  0.0 ? _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, (1.0 / java.lang.Math.sqrt(_t3)))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalize(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        double _t3 = _sv0.mul(_sv0).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t3  !=  0.0 ? _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, (1.0 / java.lang.Math.sqrt(_t3)))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
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

    public static java.lang.foreign.MemorySegment rotateTowards(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double step) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return rotateTowards_fma_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, step);
            return rotateTowards_fma_api(dest, destOffset, src, srcOffset, target, targetOffset, step);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return rotateTowards_mulAdd_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, step);
        return rotateTowards_mulAdd_api(dest, destOffset, src, srcOffset, target, targetOffset, step);
    }

    public static java.lang.foreign.MemorySegment rotateTowards_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double step) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return rotateTowards_fma_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, step);
        return rotateTowards_fma_api(dest, destOffset, src, srcOffset, target, targetOffset, step);
    }

    public static java.lang.foreign.MemorySegment rotateTowards_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double step) {
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        double _selfw = UnsafeOpsHolder.U.getDouble(_srcBase + 24L);
        double _targetw = UnsafeOpsHolder.U.getDouble(_targetBase + 24L);
        double _selfz = UnsafeOpsHolder.U.getDouble(_srcBase + 16L);
        double _targetz = UnsafeOpsHolder.U.getDouble(_targetBase + 16L);
        double _selfx = UnsafeOpsHolder.U.getDouble(_srcBase);
        double _targetx = UnsafeOpsHolder.U.getDouble(_targetBase);
        double _selfy = UnsafeOpsHolder.U.getDouble(_srcBase + 8L);
        double _targety = UnsafeOpsHolder.U.getDouble(_targetBase + 8L);
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
        return rotateTowards_fma_unsafe_sad98c058_1(dest, destOffset, src, srcOffset, target, targetOffset, step, _t9, Math.acos(java.lang.Math.min(1.0, java.lang.Math.abs(_t7))), _selfw - _t13, _selfz - _t14, _selfx - _t15, _selfy - _t16, _selfw + _t13, _selfz + _t14, _selfx + _t15, _selfy + _t16);
    }

    /** Piece 2 of {@code rotateTowards_fma_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_fma_unsafe_sad98c058_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double step, double _t9, double _t11, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24) {
        double _t36 = 4.0 * Math.atan2(java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))), java.lang.Math.sqrt(Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)))));
        double _t39 = _t36 > 0.0 ? java.lang.Math.min(1.0, step / _t36) : 0.0;
        rotateTowards_fma_unsafe_sfad26460_v(dest, destOffset, src, srcOffset, target, targetOffset, _t9, _t11, Math.sin(_t11), _t39, 1.0 - _t39);
        return dest;
    }

    private static void rotateTowards_fma_unsafe_sfad26460_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double _t9, double _t11, double _t12, double _t39, double _t40) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = (_t9  >  0.0 ? _sv1.neg() : _sv1);
        var _sv3 = (_t12  >  0.0 ? _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, Math.sin(_t40 * _t11)), DoubleVector.broadcast(SIMD_SPECIES, Math.sin(_t11 * _t39)).mul(_sv2)).mul(DoubleVector.broadcast(SIMD_SPECIES, 1.0 / _t12)) : _sv0.fma(DoubleVector.broadcast(SIMD_SPECIES, _t40), _sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t39))));
        double _t72 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t72  !=  0.0 ? _sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, (1.0 / java.lang.Math.sqrt(_t72)))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment rotateTowards_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double step) {
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _targetw = target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset + 24L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _targetz = target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset + 16L);
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _targetx = target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _targety = target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset + 8L);
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
        return rotateTowards_fma_api_s4f6456f2_1(dest, destOffset, src, srcOffset, target, targetOffset, step, _t9, Math.acos(java.lang.Math.min(1.0, java.lang.Math.abs(_t7))), _selfw - _t13, _selfz - _t14, _selfx - _t15, _selfy - _t16, _selfw + _t13, _selfz + _t14, _selfx + _t15, _selfy + _t16);
    }

    /** Piece 2 of {@code rotateTowards_fma_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_fma_api_s4f6456f2_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double step, double _t9, double _t11, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24) {
        double _t36 = 4.0 * Math.atan2(java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))), java.lang.Math.sqrt(Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)))));
        double _t39 = _t36 > 0.0 ? java.lang.Math.min(1.0, step / _t36) : 0.0;
        rotateTowards_fma_unsafe_sfad26460_v(dest, destOffset, src, srcOffset, target, targetOffset, _t9, _t11, Math.sin(_t11), _t39, 1.0 - _t39);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateTowards_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double step) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return rotateTowards_mulAdd_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, step);
        return rotateTowards_mulAdd_api(dest, destOffset, src, srcOffset, target, targetOffset, step);
    }

    public static java.lang.foreign.MemorySegment rotateTowards_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double step) {
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        double _selfw = UnsafeOpsHolder.U.getDouble(_srcBase + 24L);
        double _targetw = UnsafeOpsHolder.U.getDouble(_targetBase + 24L);
        double _selfz = UnsafeOpsHolder.U.getDouble(_srcBase + 16L);
        double _targetz = UnsafeOpsHolder.U.getDouble(_targetBase + 16L);
        double _selfx = UnsafeOpsHolder.U.getDouble(_srcBase);
        double _targetx = UnsafeOpsHolder.U.getDouble(_targetBase);
        double _selfy = UnsafeOpsHolder.U.getDouble(_srcBase + 8L);
        double _targety = UnsafeOpsHolder.U.getDouble(_targetBase + 8L);
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
        return rotateTowards_mulAdd_unsafe_se29b3097_1(dest, destOffset, src, srcOffset, target, targetOffset, step, _t9, Math.acos(java.lang.Math.min(1.0, java.lang.Math.abs(_t7))), _selfw - _t13, _selfz - _t14, _selfx - _t15, _selfy - _t16, _selfw + _t13, _selfz + _t14, _selfx + _t15, _selfy + _t16);
    }

    /** Piece 2 of {@code rotateTowards_mulAdd_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_mulAdd_unsafe_se29b3097_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double step, double _t9, double _t11, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24) {
        double _t36 = 4.0 * Math.atan2(java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))), java.lang.Math.sqrt(Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)))));
        double _t39 = _t36 > 0.0 ? java.lang.Math.min(1.0, step / _t36) : 0.0;
        rotateTowards_mulAdd_unsafe_sa62c6497_v(dest, destOffset, src, srcOffset, target, targetOffset, _t9, _t11, Math.sin(_t11), _t39, 1.0 - _t39);
        return dest;
    }

    private static void rotateTowards_mulAdd_unsafe_sa62c6497_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double _t9, double _t11, double _t12, double _t39, double _t40) {
        var _sv0 = DoubleVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = DoubleVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = (_t9  >  0.0 ? _sv1.neg() : _sv1);
        var _sv3 = (_t12  >  0.0 ? _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, Math.sin(_t40 * _t11))).add(DoubleVector.broadcast(SIMD_SPECIES, Math.sin(_t11 * _t39)).mul(_sv2)).mul(DoubleVector.broadcast(SIMD_SPECIES, 1.0 / _t12)) : _sv0.mul(DoubleVector.broadcast(SIMD_SPECIES, _t40)).add(_sv2.mul(DoubleVector.broadcast(SIMD_SPECIES, _t39))));
        double _t72 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t72  !=  0.0 ? _sv3.mul(DoubleVector.broadcast(SIMD_SPECIES, (1.0 / java.lang.Math.sqrt(_t72)))) : DoubleVector.broadcast(SIMD_SPECIES, 0.0)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment rotateTowards_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double step) {
        double _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 24L);
        double _targetw = target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset + 24L);
        double _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 16L);
        double _targetz = target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset + 16L);
        double _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset);
        double _targetx = target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset);
        double _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, srcOffset + 8L);
        double _targety = target.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, targetOffset + 8L);
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
        return rotateTowards_mulAdd_api_s7f5991f_1(dest, destOffset, src, srcOffset, target, targetOffset, step, _t9, Math.acos(java.lang.Math.min(1.0, java.lang.Math.abs(_t7))), _selfw - _t13, _selfz - _t14, _selfx - _t15, _selfy - _t16, _selfw + _t13, _selfz + _t14, _selfx + _t15, _selfy + _t16);
    }

    /** Piece 2 of {@code rotateTowards_mulAdd_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_mulAdd_api_s7f5991f_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, double step, double _t9, double _t11, double _t17, double _t18, double _t19, double _t20, double _t21, double _t22, double _t23, double _t24) {
        double _t36 = 4.0 * Math.atan2(java.lang.Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))), java.lang.Math.sqrt(Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)))));
        double _t39 = _t36 > 0.0 ? java.lang.Math.min(1.0, step / _t36) : 0.0;
        rotateTowards_mulAdd_unsafe_sa62c6497_v(dest, destOffset, src, srcOffset, target, targetOffset, _t9, _t11, Math.sin(_t11), _t39, 1.0 - _t39);
        return dest;
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

    public static java.lang.foreign.MemorySegment makeRotationAxis(java.lang.foreign.MemorySegment dest, long destOffset, double angle, double axisX, double axisY, double axisZ) {
        if (SimdSupport.USE_FMA) return makeRotationAxis_fma(dest, destOffset, angle, axisX, axisY, axisZ);
        return makeRotationAxis_mulAdd(dest, destOffset, angle, axisX, axisY, axisZ);
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_fma(java.lang.foreign.MemorySegment dest, long destOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        DoubleVector.zero(SIMD_SPECIES).withLane(0, axisX).withLane(1, axisY).withLane(2, axisZ).fma(DoubleVector.broadcast(SIMD_SPECIES, _t1), DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, Math.cosFromSin(_t1, _t0))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, double angle, double axisX, double axisY, double axisZ) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        DoubleVector.zero(SIMD_SPECIES).withLane(0, axisX).withLane(1, axisY).withLane(2, axisZ).mul(DoubleVector.broadcast(SIMD_SPECIES, _t1)).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, Math.cosFromSin(_t1, _t0))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
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

    public static java.lang.foreign.MemorySegment makeRotationAxis(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && axis.isNative()) return makeRotationAxis_fma_unsafe(dest, destOffset, axis, axisOffset, angle);
            return makeRotationAxis_fma_api(dest, destOffset, axis, axisOffset, angle);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && axis.isNative()) return makeRotationAxis_mulAdd_unsafe(dest, destOffset, axis, axisOffset, angle);
        return makeRotationAxis_mulAdd_api(dest, destOffset, axis, axisOffset, angle);
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && axis.isNative()) return makeRotationAxis_fma_unsafe(dest, destOffset, axis, axisOffset, angle);
        return makeRotationAxis_fma_api(dest, destOffset, axis, axisOffset, angle);
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        long _axisBase = axis.address() + axisOffset;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        DoubleVector.zero(SIMD_SPECIES).withLane(0, UnsafeOpsHolder.U.getDouble(_axisBase)).withLane(1, UnsafeOpsHolder.U.getDouble(_axisBase + 8L)).withLane(2, UnsafeOpsHolder.U.getDouble(_axisBase + 16L)).fma(DoubleVector.broadcast(SIMD_SPECIES, _t1), DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, Math.cosFromSin(_t1, _t0))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        DoubleVector.zero(SIMD_SPECIES).withLane(0, axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset)).withLane(1, axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset + 8L)).withLane(2, axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset + 16L)).fma(DoubleVector.broadcast(SIMD_SPECIES, _t1), DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, Math.cosFromSin(_t1, _t0))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && axis.isNative()) return makeRotationAxis_mulAdd_unsafe(dest, destOffset, axis, axisOffset, angle);
        return makeRotationAxis_mulAdd_api(dest, destOffset, axis, axisOffset, angle);
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        long _axisBase = axis.address() + axisOffset;
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        DoubleVector.zero(SIMD_SPECIES).withLane(0, UnsafeOpsHolder.U.getDouble(_axisBase)).withLane(1, UnsafeOpsHolder.U.getDouble(_axisBase + 8L)).withLane(2, UnsafeOpsHolder.U.getDouble(_axisBase + 16L)).mul(DoubleVector.broadcast(SIMD_SPECIES, _t1)).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, Math.cosFromSin(_t1, _t0))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, double angle) {
        double _t0 = 0.5 * angle;
        double _t1 = Math.sin(_t0);
        DoubleVector.zero(SIMD_SPECIES).withLane(0, axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset)).withLane(1, axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset + 8L)).withLane(2, axis.get(java.lang.foreign.ValueLayout.JAVA_DOUBLE_UNALIGNED, axisOffset + 16L)).mul(DoubleVector.broadcast(SIMD_SPECIES, _t1)).add(DoubleVector.broadcast(SIMD_SPECIES, 0.0).withLane(3, Math.cosFromSin(_t1, _t0))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
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

    public static java.lang.foreign.MemorySegment makeRotationYXZ(java.lang.foreign.MemorySegment dest, long destOffset, double angleY, double angleX, double angleZ) {
        if (SimdSupport.USE_FMA) return makeRotationYXZ_fma(dest, destOffset, angleY, angleX, angleZ);
        return makeRotationYXZ_mulAdd(dest, destOffset, angleY, angleX, angleZ);
    }

    public static java.lang.foreign.MemorySegment makeRotationYXZ_fma(java.lang.foreign.MemorySegment dest, long destOffset, double angleY, double angleX, double angleZ) {
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
        DoubleVector.zero(SIMD_SPECIES).withLane(0, _t11).withLane(2, _t12).withLane(3, _t9).fma(DoubleVector.broadcast(SIMD_SPECIES, _t5), DoubleVector.broadcast(SIMD_SPECIES, _t10).withLane(3, _t12).mul(DoubleVector.broadcast(SIMD_SPECIES, _t7)).withLane(2, -(_t9 * _t7))).withLane(1, _t11 * _t7 - _t10 * _t5).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationYXZ_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, double angleY, double angleX, double angleZ) {
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
        DoubleVector.zero(SIMD_SPECIES).withLane(0, _t11).withLane(2, _t12).withLane(3, _t9).mul(DoubleVector.broadcast(SIMD_SPECIES, _t5)).add(DoubleVector.broadcast(SIMD_SPECIES, _t10).withLane(3, _t12).mul(DoubleVector.broadcast(SIMD_SPECIES, _t7)).withLane(2, -(_t9 * _t7))).withLane(1, _t11 * _t7 - _t10 * _t5).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
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
        if (PREFERRED_LANES >= 4) {
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset).intoArray(dest, destOffset);
        }
        else {
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset).intoArray(dest, destOffset);
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 2).intoArray(dest, destOffset + 2);
        }
    }

    private static void copyArrSeg_one(double[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (PREFERRED_LANES >= 4) {
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset);
        }
        else {
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_128, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset);
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_128, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + 2);
        }
    }

    private static void copySegArr_one(java.lang.foreign.MemorySegment dest, long destOffset, double[] src, int srcOffset) {
        if (PREFERRED_LANES >= 4) {
            DoubleVector.fromArray(DoubleVector.SPECIES_256, src, srcOffset).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        }
        else {
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            DoubleVector.fromArray(DoubleVector.SPECIES_128, src, srcOffset + 2).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
        }
    }

    private static void copySegSeg_one(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (PREFERRED_LANES >= 4) {
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_256, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        }
        else {
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_128, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            DoubleVector.fromMemorySegment(DoubleVector.SPECIES_128, src, srcOffset + 16L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + 16L, java.nio.ByteOrder.nativeOrder());
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
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg_one(dest, destOffset, _srcSeg, srcOffset * 8L);
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
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg(dest, destOffset, _srcSeg, srcOffset * 8L, count * 4);
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
        copyArrSeg(dest, destOffset, _srcSeg, srcOffset, count * 4);
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        copyArrSeg_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static double[] copy(double[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        copyArrSeg(dest, destOffset, src, srcOffset, count * 4);
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, double[] src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
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
        if (dest.hasArray() && destOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && destOffset <= dest.limit() - (count > 536870911 ? -1 : count * 4)) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr(_destArr, _destOff, src, srcOffset, count * 4);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegArr(_destSeg, destOffset * 8L, src, srcOffset, count * 4);
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
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copyArrSeg_one(_destArr, _destOff, _srcSeg, srcOffset * 8L);
            }
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
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
        if (dest.hasArray() && destOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && destOffset <= dest.limit() - (count > 536870911 ? -1 : count * 4)) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && srcOffset <= src.limit() - (count > 536870911 ? -1 : count * 4)) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr(_destArr, _destOff, _srcArr, _srcOff, count * 4);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copyArrSeg(_destArr, _destOff, _srcSeg, srcOffset * 8L, count * 4);
            }
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            if (src.hasArray() && srcOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && srcOffset <= src.limit() - (count > 536870911 ? -1 : count * 4)) {
                double[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copySegArr(_destSeg, destOffset * 8L, _srcArr, _srcOff, count * 4);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copySegSeg(_destSeg, destOffset * 8L, _srcSeg, srcOffset * 8L, count * 4);
            }
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
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
        if (dest.hasArray() && destOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && destOffset <= dest.limit() - (count > 536870911 ? -1 : count * 4)) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg(_destArr, _destOff, _srcSeg, srcOffset, count * 4);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg(_destSeg, destOffset * 8L, _srcSeg, srcOffset, count * 4);
        }
        return dest;
    }

    public static java.nio.DoubleBuffer copy(java.nio.DoubleBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
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
        if (dest.hasArray() && destOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && destOffset <= dest.limit() - (count > 536870911 ? -1 : count * 4)) {
            double[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrSeg(_destArr, _destOff, src, srcOffset, count * 4);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegSeg(_destSeg, destOffset * 8L, src, srcOffset, count * 4);
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
        copySegArr(_destSeg, destOffset, src, srcOffset, count * 4);
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
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
        if (src.hasArray() && srcOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && srcOffset <= src.limit() - (count > 536870911 ? -1 : count * 4)) {
            double[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr(_destSeg, destOffset, _srcArr, _srcOff, count * 4);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg(_destSeg, destOffset, _srcSeg, srcOffset * 8L, count * 4);
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
        copySegSeg(_destSeg, destOffset, _srcSeg, srcOffset, count * 4);
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
        copySegSeg(_destSeg, destOffset, src, srcOffset, count * 4);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, double[] src, int srcOffset) {
        copySegArr_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, double[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        copySegArr(dest, destOffset, src, srcOffset, count * 4);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.DoubleBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
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
        if (src.hasArray() && srcOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && srcOffset <= src.limit() - (count > 536870911 ? -1 : count * 4)) {
            double[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr(dest, destOffset, _srcArr, _srcOff, count * 4);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg(dest, destOffset, _srcSeg, srcOffset * 8L, count * 4);
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
        copySegSeg(dest, destOffset, _srcSeg, srcOffset, count * 4);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        copySegSeg_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        copySegSeg(dest, destOffset, src, srcOffset, count * 4);
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
