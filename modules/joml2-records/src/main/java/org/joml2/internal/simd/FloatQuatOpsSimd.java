// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.simd;

import jdk.incubator.vector.*;
import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;

/**
 * Vector-API isolation cell for {@link FloatQuatOps}: every
 * {@code jdk.incubator.vector} reference of the Ops family lives in this class,
 * which is loaded and initialized only behind {@code SimdSupport.VECTOR_API}
 * guards - {@code FloatQuatOps} and its kernel siblings link
 * and run without the incubator module. Not public API.
 */
public final class FloatQuatOpsSimd {
    private FloatQuatOpsSimd() {}
    private static final VectorSpecies<Float> SIMD_SPECIES = FloatVector.SPECIES_128;
    private static final int PREFERRED_LANES = FloatVector.SPECIES_PREFERRED.length();
    private static final FloatVector UNIT_W = FloatVector.fromArray(SIMD_SPECIES, new float[]{0.0f, 0.0f, 0.0f, 1.0f}, 0);

    public static float[] add(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, other, otherOffset).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        var _c0 = FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder()).add(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] mul(float[] dest, int destOffset, float[] src, int srcOffset, float scalar) {
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, scalar).mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, scalar).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] negate(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg();
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        var _c0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).neg();
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] sub(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).sub(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        var _c0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).sub(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder()));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] set(float[] dest, int destOffset, float[] v, int vOffset) {
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, v, vOffset);
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        var _c0 = FloatVector.fromMemorySegment(SIMD_SPECIES, v, vOffset, java.nio.ByteOrder.nativeOrder());
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] toDualQuat(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _vcp0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        _vcp0.intoArray(dest, destOffset);
        dest[destOffset + 4] = 0.0f;
        dest[destOffset + 5] = 0.0f;
        dest[destOffset + 6] = 0.0f;
        dest[destOffset + 7] = 0.0f;
        return dest;
    }

    public static java.lang.foreign.MemorySegment toDualQuat(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && dest.isNative() && !dest.isReadOnly() && src.isNative()) return toDualQuat_unsafe(dest, destOffset, src, srcOffset);
        return toDualQuat_api(dest, destOffset, src, srcOffset);
    }

    public static java.lang.foreign.MemorySegment toDualQuat_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _destBase = dest.address() + destOffset;
        var _vcp0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        _vcp0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        UnsafeOpsHolder.U.putFloat(_destBase + 16L, 0.0f);
        UnsafeOpsHolder.U.putFloat(_destBase + 20L, 0.0f);
        UnsafeOpsHolder.U.putFloat(_destBase + 24L, 0.0f);
        UnsafeOpsHolder.U.putFloat(_destBase + 28L, 0.0f);
        return dest;
    }

    public static java.lang.foreign.MemorySegment toDualQuat_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        var _vcp0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        _vcp0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 16L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 20L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 24L, 0.0f);
        dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + 28L, 0.0f);
        return dest;
    }

    public static float[] makeZero(float[] dest, int destOffset) {
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeZero(java.lang.foreign.MemorySegment dest, long destOffset) {
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, 0.0f);
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] lerp(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    public static float[] lerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, t).fma(FloatVector.zero(SIMD_SPECIES).withLane(0, otherX).withLane(1, otherY).withLane(2, otherZ).withLane(3, otherW).sub(_sv0), _sv0);
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] lerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, t).mul(FloatVector.zero(SIMD_SPECIES).withLane(0, otherX).withLane(1, otherY).withLane(2, otherZ).withLane(3, otherW).sub(_sv0)).add(_sv0);
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    public static java.lang.foreign.MemorySegment lerp_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, t).fma(FloatVector.zero(SIMD_SPECIES).withLane(0, otherX).withLane(1, otherY).withLane(2, otherZ).withLane(3, otherW).sub(_sv0), _sv0);
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, t).mul(FloatVector.zero(SIMD_SPECIES).withLane(0, otherX).withLane(1, otherY).withLane(2, otherZ).withLane(3, otherW).sub(_sv0)).add(_sv0);
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] lerp(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static float[] lerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, t).fma(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset).sub(_sv0), _sv0);
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] lerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, t).mul(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset).sub(_sv0)).add(_sv0);
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static java.lang.foreign.MemorySegment lerp_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, t).fma(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder()).sub(_sv0), _sv0);
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, t).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder()).sub(_sv0)).add(_sv0);
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] nlerp(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        if (SimdSupport.USE_FMA) return nlerp_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return nlerp_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static float[] nlerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, alpha).fma(FloatVector.fromArray(SIMD_SPECIES, target, targetOffset).sub(_sv0), _sv0);
        float _t11 = _sv1.mul(_sv1).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t11  !=  0.0f ? _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t11)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] nlerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, alpha).mul(FloatVector.fromArray(SIMD_SPECIES, target, targetOffset).sub(_sv0)).add(_sv0);
        float _t11 = _sv1.mul(_sv1).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t11  !=  0.0f ? _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t11)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment nlerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        if (SimdSupport.USE_FMA) return nlerp_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return nlerp_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment nlerp_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, alpha).fma(FloatVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder()).sub(_sv0), _sv0);
        float _t11 = _sv1.mul(_sv1).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t11  !=  0.0f ? _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t11)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment nlerp_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.broadcast(SIMD_SPECIES, alpha).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder()).sub(_sv0)).add(_sv0);
        float _t11 = _sv1.mul(_sv1).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t11  !=  0.0f ? _sv1.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t11)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] nlerpShortest(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        if (SimdSupport.USE_FMA) return nlerpShortest_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return nlerpShortest_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static float[] nlerpShortest_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, target, targetOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, alpha).fma((-Math.fma(src[srcOffset + 3], target[targetOffset + 3], Math.fma(src[srcOffset + 2], target[targetOffset + 2], Math.fma(src[srcOffset + 0], target[targetOffset + 0], src[srcOffset + 1] * target[targetOffset + 1])))  >  0.0f ? _sv0.neg() : _sv0).sub(_sv1), _sv1);
        float _t24 = _sv2.mul(_sv2).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t24  !=  0.0f ? _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t24)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] nlerpShortest_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, target, targetOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, alpha).mul((-Math.fma(src[srcOffset + 3], target[targetOffset + 3], Math.fma(src[srcOffset + 2], target[targetOffset + 2], Math.fma(src[srcOffset + 0], target[targetOffset + 0], src[srcOffset + 1] * target[targetOffset + 1])))  >  0.0f ? _sv0.neg() : _sv0).sub(_sv1)).add(_sv1);
        float _t24 = _sv2.mul(_sv2).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t24  !=  0.0f ? _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t24)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment nlerpShortest(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        if (SimdSupport.USE_FMA) return nlerpShortest_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return nlerpShortest_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment nlerpShortest_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return nlerpShortest_fma_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return nlerpShortest_fma_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment nlerpShortest_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, alpha).fma((-Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 12L), UnsafeOpsHolder.U.getFloat(_targetBase + 12L), Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 8L), UnsafeOpsHolder.U.getFloat(_targetBase + 8L), Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 0L), UnsafeOpsHolder.U.getFloat(_targetBase + 0L), UnsafeOpsHolder.U.getFloat(_srcBase + 4L) * UnsafeOpsHolder.U.getFloat(_targetBase + 4L))))  >  0.0f ? _sv0.neg() : _sv0).sub(_sv1), _sv1);
        float _t24 = _sv2.mul(_sv2).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t24  !=  0.0f ? _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t24)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment nlerpShortest_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, alpha).fma((-Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L), target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 12L), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 8L), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 0L), target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 0L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) * target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 4L))))  >  0.0f ? _sv0.neg() : _sv0).sub(_sv1), _sv1);
        float _t24 = _sv2.mul(_sv2).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t24  !=  0.0f ? _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t24)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment nlerpShortest_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return nlerpShortest_mulAdd_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return nlerpShortest_mulAdd_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment nlerpShortest_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, alpha).mul((-Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 12L), UnsafeOpsHolder.U.getFloat(_targetBase + 12L), Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 8L), UnsafeOpsHolder.U.getFloat(_targetBase + 8L), Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 0L), UnsafeOpsHolder.U.getFloat(_targetBase + 0L), UnsafeOpsHolder.U.getFloat(_srcBase + 4L) * UnsafeOpsHolder.U.getFloat(_targetBase + 4L))))  >  0.0f ? _sv0.neg() : _sv0).sub(_sv1)).add(_sv1);
        float _t24 = _sv2.mul(_sv2).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t24  !=  0.0f ? _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t24)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment nlerpShortest_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = FloatVector.broadcast(SIMD_SPECIES, alpha).mul((-Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L), target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 12L), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 8L), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 0L), target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 0L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) * target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 4L))))  >  0.0f ? _sv0.neg() : _sv0).sub(_sv1)).add(_sv1);
        float _t24 = _sv2.mul(_sv2).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t24  !=  0.0f ? _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t24)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] slerp(float[] dest, int destOffset, float[] src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (SimdSupport.USE_FMA) return slerp_fma(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return slerp_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    public static float[] slerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _t0 = 1.0f - alpha;
        float _t1 = src[srcOffset + 3] + targetW;
        float _t2 = src[srcOffset + 2] + targetZ;
        float _t3 = src[srcOffset + 0] + targetX;
        float _t4 = src[srcOffset + 1] + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        float _t12 = quatArcAngle(_t11);
        float _t13 = 4.0f - _t11;
        float _t19 = (float) Math.sqrt(_t13 * _t11);
        float _t21 = 2.0f / _t19;
        float _w0, _w1;
        if (_t19 > 2.0E-6f) {
            _w0 = _t21 * (float) Math.sin(_t0 * _t12);
            _w1 = _t21 * (float) Math.sin(alpha * _t12);
        } else {
            if (_t11 > _t13) {
                _w0 = _t0;
                _w1 = alpha;
            } else {
                _w0 = _t5;
                _w1 = 1.0f - _t5;
            }
        }
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(FloatVector.broadcast(SIMD_SPECIES, _w0), FloatVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(FloatVector.broadcast(SIMD_SPECIES, _w1)));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] slerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _t0 = 1.0f - alpha;
        float _t1 = src[srcOffset + 3] + targetW;
        float _t2 = src[srcOffset + 2] + targetZ;
        float _t3 = src[srcOffset + 0] + targetX;
        float _t4 = src[srcOffset + 1] + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        float _t12 = quatArcAngle(_t11);
        float _t13 = 4.0f - _t11;
        float _t19 = (float) Math.sqrt(_t13 * _t11);
        float _t21 = 2.0f / _t19;
        float _w0, _w1;
        if (_t19 > 2.0E-6f) {
            _w0 = _t21 * (float) Math.sin(_t0 * _t12);
            _w1 = _t21 * (float) Math.sin(alpha * _t12);
        } else {
            if (_t11 > _t13) {
                _w0 = _t0;
                _w1 = alpha;
            } else {
                _w0 = _t5;
                _w1 = 1.0f - _t5;
            }
        }
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(FloatVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(FloatVector.broadcast(SIMD_SPECIES, _w1)));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (SimdSupport.USE_FMA) return slerp_fma(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return slerp_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    public static java.lang.foreign.MemorySegment slerp_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return slerp_fma_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return slerp_fma_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    public static java.lang.foreign.MemorySegment slerp_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        long _srcBase = src.address() + srcOffset;
        float _t0 = 1.0f - alpha;
        float _t1 = UnsafeOpsHolder.U.getFloat(_srcBase + 12L) + targetW;
        float _t2 = UnsafeOpsHolder.U.getFloat(_srcBase + 8L) + targetZ;
        float _t3 = UnsafeOpsHolder.U.getFloat(_srcBase + 0L) + targetX;
        float _t4 = UnsafeOpsHolder.U.getFloat(_srcBase + 4L) + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        float _t12 = quatArcAngle(_t11);
        float _t13 = 4.0f - _t11;
        float _t19 = (float) Math.sqrt(_t13 * _t11);
        float _t21 = 2.0f / _t19;
        float _w0, _w1;
        if (_t19 > 2.0E-6f) {
            _w0 = _t21 * (float) Math.sin(_t0 * _t12);
            _w1 = _t21 * (float) Math.sin(alpha * _t12);
        } else {
            if (_t11 > _t13) {
                _w0 = _t0;
                _w1 = alpha;
            } else {
                _w0 = _t5;
                _w1 = 1.0f - _t5;
            }
        }
        slerp_fma_unsafe_s350056bb_v(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, _w0, _w1);
        return dest;
    }

    private static void slerp_fma_unsafe_s350056bb_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float _w0, float _w1) {
        var _c0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.broadcast(SIMD_SPECIES, _w0), FloatVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(FloatVector.broadcast(SIMD_SPECIES, _w1)));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment slerp_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _t0 = 1.0f - alpha;
        float _t1 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L) + targetW;
        float _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) + targetZ;
        float _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 0L) + targetX;
        float _t4 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        float _t12 = quatArcAngle(_t11);
        float _t13 = 4.0f - _t11;
        float _t19 = (float) Math.sqrt(_t13 * _t11);
        float _t21 = 2.0f / _t19;
        float _w0, _w1;
        if (_t19 > 2.0E-6f) {
            _w0 = _t21 * (float) Math.sin(_t0 * _t12);
            _w1 = _t21 * (float) Math.sin(alpha * _t12);
        } else {
            if (_t11 > _t13) {
                _w0 = _t0;
                _w1 = alpha;
            } else {
                _w0 = _t5;
                _w1 = 1.0f - _t5;
            }
        }
        slerp_fma_api_sd4171c23_v(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, _w0, _w1);
        return dest;
    }

    private static void slerp_fma_api_sd4171c23_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float _w0, float _w1) {
        var _c0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.broadcast(SIMD_SPECIES, _w0), FloatVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(FloatVector.broadcast(SIMD_SPECIES, _w1)));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment slerp_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return slerp_mulAdd_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return slerp_mulAdd_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    public static java.lang.foreign.MemorySegment slerp_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        long _srcBase = src.address() + srcOffset;
        float _t0 = 1.0f - alpha;
        float _t1 = UnsafeOpsHolder.U.getFloat(_srcBase + 12L) + targetW;
        float _t2 = UnsafeOpsHolder.U.getFloat(_srcBase + 8L) + targetZ;
        float _t3 = UnsafeOpsHolder.U.getFloat(_srcBase + 0L) + targetX;
        float _t4 = UnsafeOpsHolder.U.getFloat(_srcBase + 4L) + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        float _t12 = quatArcAngle(_t11);
        float _t13 = 4.0f - _t11;
        float _t19 = (float) Math.sqrt(_t13 * _t11);
        float _t21 = 2.0f / _t19;
        float _w0, _w1;
        if (_t19 > 2.0E-6f) {
            _w0 = _t21 * (float) Math.sin(_t0 * _t12);
            _w1 = _t21 * (float) Math.sin(alpha * _t12);
        } else {
            if (_t11 > _t13) {
                _w0 = _t0;
                _w1 = alpha;
            } else {
                _w0 = _t5;
                _w1 = 1.0f - _t5;
            }
        }
        slerp_mulAdd_unsafe_s213cfb5c_v(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, _w0, _w1);
        return dest;
    }

    private static void slerp_mulAdd_unsafe_s213cfb5c_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float _w0, float _w1) {
        var _c0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(FloatVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(FloatVector.broadcast(SIMD_SPECIES, _w1)));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment slerp_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _t0 = 1.0f - alpha;
        float _t1 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L) + targetW;
        float _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) + targetZ;
        float _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 0L) + targetX;
        float _t4 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        float _t12 = quatArcAngle(_t11);
        float _t13 = 4.0f - _t11;
        float _t19 = (float) Math.sqrt(_t13 * _t11);
        float _t21 = 2.0f / _t19;
        float _w0, _w1;
        if (_t19 > 2.0E-6f) {
            _w0 = _t21 * (float) Math.sin(_t0 * _t12);
            _w1 = _t21 * (float) Math.sin(alpha * _t12);
        } else {
            if (_t11 > _t13) {
                _w0 = _t0;
                _w1 = alpha;
            } else {
                _w0 = _t5;
                _w1 = 1.0f - _t5;
            }
        }
        slerp_mulAdd_api_s27b3dfc6_v(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, _w0, _w1);
        return dest;
    }

    private static void slerp_mulAdd_api_s27b3dfc6_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float _w0, float _w1) {
        var _c0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(FloatVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(FloatVector.broadcast(SIMD_SPECIES, _w1)));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }

    public static float[] slerp(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        if (SimdSupport.USE_FMA) return slerp_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerp_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static float[] slerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        float _t0 = 1.0f - alpha;
        float _t1 = src[srcOffset + 3] + target[targetOffset + 3];
        float _t2 = src[srcOffset + 2] + target[targetOffset + 2];
        float _t3 = src[srcOffset + 0] + target[targetOffset + 0];
        float _t4 = src[srcOffset + 1] + target[targetOffset + 1];
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        float _t12 = quatArcAngle(_t11);
        float _t13 = 4.0f - _t11;
        float _t19 = (float) Math.sqrt(_t13 * _t11);
        float _t21 = 2.0f / _t19;
        float _w0, _w1;
        if (_t19 > 2.0E-6f) {
            _w0 = _t21 * (float) Math.sin(_t0 * _t12);
            _w1 = _t21 * (float) Math.sin(alpha * _t12);
        } else {
            if (_t11 > _t13) {
                _w0 = _t0;
                _w1 = alpha;
            } else {
                _w0 = _t5;
                _w1 = 1.0f - _t5;
            }
        }
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(FloatVector.broadcast(SIMD_SPECIES, _w0), FloatVector.fromArray(SIMD_SPECIES, target, targetOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _w1)));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] slerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        float _t0 = 1.0f - alpha;
        float _t1 = src[srcOffset + 3] + target[targetOffset + 3];
        float _t2 = src[srcOffset + 2] + target[targetOffset + 2];
        float _t3 = src[srcOffset + 0] + target[targetOffset + 0];
        float _t4 = src[srcOffset + 1] + target[targetOffset + 1];
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        float _t12 = quatArcAngle(_t11);
        float _t13 = 4.0f - _t11;
        float _t19 = (float) Math.sqrt(_t13 * _t11);
        float _t21 = 2.0f / _t19;
        float _w0, _w1;
        if (_t19 > 2.0E-6f) {
            _w0 = _t21 * (float) Math.sin(_t0 * _t12);
            _w1 = _t21 * (float) Math.sin(alpha * _t12);
        } else {
            if (_t11 > _t13) {
                _w0 = _t0;
                _w1 = alpha;
            } else {
                _w0 = _t5;
                _w1 = 1.0f - _t5;
            }
        }
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(FloatVector.fromArray(SIMD_SPECIES, target, targetOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _w1)));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        if (SimdSupport.USE_FMA) return slerp_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerp_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment slerp_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return slerp_fma_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerp_fma_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment slerp_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        float _t0 = 1.0f - alpha;
        float _t1 = UnsafeOpsHolder.U.getFloat(_srcBase + 12L) + UnsafeOpsHolder.U.getFloat(_targetBase + 12L);
        float _t2 = UnsafeOpsHolder.U.getFloat(_srcBase + 8L) + UnsafeOpsHolder.U.getFloat(_targetBase + 8L);
        float _t3 = UnsafeOpsHolder.U.getFloat(_srcBase + 0L) + UnsafeOpsHolder.U.getFloat(_targetBase + 0L);
        float _t4 = UnsafeOpsHolder.U.getFloat(_srcBase + 4L) + UnsafeOpsHolder.U.getFloat(_targetBase + 4L);
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        float _t12 = quatArcAngle(_t11);
        float _t13 = 4.0f - _t11;
        float _t19 = (float) Math.sqrt(_t13 * _t11);
        float _t21 = 2.0f / _t19;
        float _w0, _w1;
        if (_t19 > 2.0E-6f) {
            _w0 = _t21 * (float) Math.sin(_t0 * _t12);
            _w1 = _t21 * (float) Math.sin(alpha * _t12);
        } else {
            if (_t11 > _t13) {
                _w0 = _t0;
                _w1 = alpha;
            } else {
                _w0 = _t5;
                _w1 = 1.0f - _t5;
            }
        }
        var _c0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.broadcast(SIMD_SPECIES, _w0), FloatVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, _w1)));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        float _t0 = 1.0f - alpha;
        float _t1 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L) + target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 12L);
        float _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) + target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 8L);
        float _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 0L) + target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 0L);
        float _t4 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) + target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 4L);
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        float _t12 = quatArcAngle(_t11);
        float _t13 = 4.0f - _t11;
        float _t19 = (float) Math.sqrt(_t13 * _t11);
        float _t21 = 2.0f / _t19;
        float _w0, _w1;
        if (_t19 > 2.0E-6f) {
            _w0 = _t21 * (float) Math.sin(_t0 * _t12);
            _w1 = _t21 * (float) Math.sin(alpha * _t12);
        } else {
            if (_t11 > _t13) {
                _w0 = _t0;
                _w1 = alpha;
            } else {
                _w0 = _t5;
                _w1 = 1.0f - _t5;
            }
        }
        slerp_fma_api_s516e5d7e_v(dest, destOffset, src, srcOffset, target, targetOffset, _w0, _w1);
        return dest;
    }

    private static void slerp_fma_api_s516e5d7e_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float _w0, float _w1) {
        var _c0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.broadcast(SIMD_SPECIES, _w0), FloatVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, _w1)));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment slerp_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return slerp_mulAdd_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerp_mulAdd_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment slerp_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        float _t0 = 1.0f - alpha;
        float _t1 = UnsafeOpsHolder.U.getFloat(_srcBase + 12L) + UnsafeOpsHolder.U.getFloat(_targetBase + 12L);
        float _t2 = UnsafeOpsHolder.U.getFloat(_srcBase + 8L) + UnsafeOpsHolder.U.getFloat(_targetBase + 8L);
        float _t3 = UnsafeOpsHolder.U.getFloat(_srcBase + 0L) + UnsafeOpsHolder.U.getFloat(_targetBase + 0L);
        float _t4 = UnsafeOpsHolder.U.getFloat(_srcBase + 4L) + UnsafeOpsHolder.U.getFloat(_targetBase + 4L);
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        float _t12 = quatArcAngle(_t11);
        float _t13 = 4.0f - _t11;
        float _t19 = (float) Math.sqrt(_t13 * _t11);
        float _t21 = 2.0f / _t19;
        float _w0, _w1;
        if (_t19 > 2.0E-6f) {
            _w0 = _t21 * (float) Math.sin(_t0 * _t12);
            _w1 = _t21 * (float) Math.sin(alpha * _t12);
        } else {
            if (_t11 > _t13) {
                _w0 = _t0;
                _w1 = alpha;
            } else {
                _w0 = _t5;
                _w1 = 1.0f - _t5;
            }
        }
        var _c0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(FloatVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, _w1)));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        float _t0 = 1.0f - alpha;
        float _t1 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L) + target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 12L);
        float _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) + target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 8L);
        float _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 0L) + target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 0L);
        float _t4 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) + target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 4L);
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, Math.fma(_t1, _t1, Math.fma(_t2, _t2, Math.fma(_t3, _t3, _t4 * _t4))));
        float _t12 = quatArcAngle(_t11);
        float _t13 = 4.0f - _t11;
        float _t19 = (float) Math.sqrt(_t13 * _t11);
        float _t21 = 2.0f / _t19;
        float _w0, _w1;
        if (_t19 > 2.0E-6f) {
            _w0 = _t21 * (float) Math.sin(_t0 * _t12);
            _w1 = _t21 * (float) Math.sin(alpha * _t12);
        } else {
            if (_t11 > _t13) {
                _w0 = _t0;
                _w1 = alpha;
            } else {
                _w0 = _t5;
                _w1 = 1.0f - _t5;
            }
        }
        slerp_mulAdd_api_sb0627645_v(dest, destOffset, src, srcOffset, target, targetOffset, _w0, _w1);
        return dest;
    }

    private static void slerp_mulAdd_api_sb0627645_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float _w0, float _w1) {
        var _c0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(FloatVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, _w1)));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }

    public static float[] slerpShortest(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        if (SimdSupport.USE_FMA) return slerpShortest_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerpShortest_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static float[] slerpShortest_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        float _t0 = 1.0f - alpha;
        float _t12 = Math.fma(src[srcOffset + 3], target[targetOffset + 3], Math.fma(src[srcOffset + 2], target[targetOffset + 2], Math.fma(src[srcOffset + 0], target[targetOffset + 0], src[srcOffset + 1] * target[targetOffset + 1])));
        float _t16 = (float) Math.acos(Math.min(1.0f, Math.abs(_t12)));
        float _t17 = (float) Math.sin(_t16);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, target, targetOffset);
        var _sv2 = (-_t12  >  0.0f ? _sv1.neg() : _sv1);
        var _sv3 = (_t17  >  0.0f ? _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(_t0 * _t16)), FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(alpha * _t16)).mul(_sv2)).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / _t17)) : FloatVector.broadcast(SIMD_SPECIES, alpha).fma(_sv2, _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t0))));
        float _t49 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t49  !=  0.0f ? _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t49)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] slerpShortest_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        float _t0 = 1.0f - alpha;
        float _t12 = Math.fma(src[srcOffset + 3], target[targetOffset + 3], Math.fma(src[srcOffset + 2], target[targetOffset + 2], Math.fma(src[srcOffset + 0], target[targetOffset + 0], src[srcOffset + 1] * target[targetOffset + 1])));
        float _t16 = (float) Math.acos(Math.min(1.0f, Math.abs(_t12)));
        float _t17 = (float) Math.sin(_t16);
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, target, targetOffset);
        var _sv2 = (-_t12  >  0.0f ? _sv1.neg() : _sv1);
        var _sv3 = (_t17  >  0.0f ? _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(_t0 * _t16))).add(FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(alpha * _t16)).mul(_sv2)).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / _t17)) : FloatVector.broadcast(SIMD_SPECIES, alpha).mul(_sv2).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t0))));
        float _t49 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t49  !=  0.0f ? _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t49)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerpShortest(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        if (SimdSupport.USE_FMA) return slerpShortest_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerpShortest_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment slerpShortest_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return slerpShortest_fma_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerpShortest_fma_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment slerpShortest_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        float _t12 = Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 12L), UnsafeOpsHolder.U.getFloat(_targetBase + 12L), Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 8L), UnsafeOpsHolder.U.getFloat(_targetBase + 8L), Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 0L), UnsafeOpsHolder.U.getFloat(_targetBase + 0L), UnsafeOpsHolder.U.getFloat(_srcBase + 4L) * UnsafeOpsHolder.U.getFloat(_targetBase + 4L))));
        float _t16 = (float) Math.acos(Math.min(1.0f, Math.abs(_t12)));
        slerpShortest_fma_unsafe_s86965de8_v(dest, destOffset, src, srcOffset, target, targetOffset, alpha, 1.0f - alpha, _t12, _t16, (float) Math.sin(_t16));
        return dest;
    }

    private static void slerpShortest_fma_unsafe_s86965de8_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha, float _t0, float _t12, float _t16, float _t17) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = (-_t12  >  0.0f ? _sv1.neg() : _sv1);
        var _sv3 = (_t17  >  0.0f ? _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(_t0 * _t16)), FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(alpha * _t16)).mul(_sv2)).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / _t17)) : FloatVector.broadcast(SIMD_SPECIES, alpha).fma(_sv2, _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t0))));
        float _t49 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t49  !=  0.0f ? _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t49)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment slerpShortest_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        float _t12 = Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L), target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 12L), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 8L), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 0L), target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 0L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) * target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 4L))));
        float _t16 = (float) Math.acos(Math.min(1.0f, Math.abs(_t12)));
        slerpShortest_fma_api_sf8bc8598_v(dest, destOffset, src, srcOffset, target, targetOffset, alpha, 1.0f - alpha, _t12, _t16, (float) Math.sin(_t16));
        return dest;
    }

    private static void slerpShortest_fma_api_sf8bc8598_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha, float _t0, float _t12, float _t16, float _t17) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = (-_t12  >  0.0f ? _sv1.neg() : _sv1);
        var _sv3 = (_t17  >  0.0f ? _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(_t0 * _t16)), FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(alpha * _t16)).mul(_sv2)).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / _t17)) : FloatVector.broadcast(SIMD_SPECIES, alpha).fma(_sv2, _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t0))));
        float _t49 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t49  !=  0.0f ? _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t49)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment slerpShortest_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return slerpShortest_mulAdd_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerpShortest_mulAdd_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment slerpShortest_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        float _t12 = Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 12L), UnsafeOpsHolder.U.getFloat(_targetBase + 12L), Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 8L), UnsafeOpsHolder.U.getFloat(_targetBase + 8L), Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 0L), UnsafeOpsHolder.U.getFloat(_targetBase + 0L), UnsafeOpsHolder.U.getFloat(_srcBase + 4L) * UnsafeOpsHolder.U.getFloat(_targetBase + 4L))));
        float _t16 = (float) Math.acos(Math.min(1.0f, Math.abs(_t12)));
        slerpShortest_mulAdd_unsafe_s55aebe59_v(dest, destOffset, src, srcOffset, target, targetOffset, alpha, 1.0f - alpha, _t12, _t16, (float) Math.sin(_t16));
        return dest;
    }

    private static void slerpShortest_mulAdd_unsafe_s55aebe59_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha, float _t0, float _t12, float _t16, float _t17) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = (-_t12  >  0.0f ? _sv1.neg() : _sv1);
        var _sv3 = (_t17  >  0.0f ? _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(_t0 * _t16))).add(FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(alpha * _t16)).mul(_sv2)).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / _t17)) : FloatVector.broadcast(SIMD_SPECIES, alpha).mul(_sv2).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t0))));
        float _t49 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t49  !=  0.0f ? _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t49)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment slerpShortest_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        float _t12 = Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L), target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 12L), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L), target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 8L), Math.fma(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 0L), target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 0L), src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) * target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 4L))));
        float _t16 = (float) Math.acos(Math.min(1.0f, Math.abs(_t12)));
        slerpShortest_mulAdd_api_sa1526d5f_v(dest, destOffset, src, srcOffset, target, targetOffset, alpha, 1.0f - alpha, _t12, _t16, (float) Math.sin(_t16));
        return dest;
    }

    private static void slerpShortest_mulAdd_api_sa1526d5f_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha, float _t0, float _t12, float _t16, float _t17) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = (-_t12  >  0.0f ? _sv1.neg() : _sv1);
        var _sv3 = (_t17  >  0.0f ? _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(_t0 * _t16))).add(FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(alpha * _t16)).mul(_sv2)).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / _t17)) : FloatVector.broadcast(SIMD_SPECIES, alpha).mul(_sv2).add(_sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t0))));
        float _t49 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t49  !=  0.0f ? _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t49)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }

    public static float[] addScaled(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float weight) {
        if (SimdSupport.USE_FMA) return addScaled_fma(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return addScaled_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    public static float[] addScaled_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float weight) {
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, weight).fma(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] addScaled_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float weight) {
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, weight).mul(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float weight) {
        if (SimdSupport.USE_FMA) return addScaled_fma(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return addScaled_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    public static java.lang.foreign.MemorySegment addScaled_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float weight) {
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, weight).fma(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder()), FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float weight) {
        var _c0 = FloatVector.broadcast(SIMD_SPECIES, weight).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder())).add(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] calculateW(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _selfz = src[srcOffset + 2];
        var _c0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(3, (float) Math.sqrt(Math.max(0.0f, Math.fma(-_selfx, _selfx, Math.fma(-_selfy, _selfy, Math.fma(-_selfz, _selfz, 1.0f))))));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment calculateW(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return calculateW_unsafe(dest, destOffset, src, srcOffset);
        return calculateW_api(dest, destOffset, src, srcOffset);
    }

    public static java.lang.foreign.MemorySegment calculateW_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        float _selfx = UnsafeOpsHolder.U.getFloat(_srcBase + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(_srcBase + 4L);
        float _selfz = UnsafeOpsHolder.U.getFloat(_srcBase + 8L);
        var _c0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).withLane(3, (float) Math.sqrt(Math.max(0.0f, Math.fma(-_selfx, _selfx, Math.fma(-_selfy, _selfy, Math.fma(-_selfz, _selfz, 1.0f))))));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment calculateW_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 0L);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        var _c0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).withLane(3, (float) Math.sqrt(Math.max(0.0f, Math.fma(-_selfx, _selfx, Math.fma(-_selfy, _selfy, Math.fma(-_selfz, _selfz, 1.0f))))));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] exp(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfz = src[srcOffset + 2];
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _t2 = Math.min((float) Math.exp(src[srcOffset + 3]), 3.4028235E38f);
        float _t4 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t5 = (float) Math.sqrt(_t4);
        float _t7 = (float) Math.sin(_t5);
        var _c0 = (_t4  >  0.0f ? FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(3, (float) Math.cosFromSin(_t7, _t5)).mul(FloatVector.broadcast(SIMD_SPECIES, _t2 * (_t7 * (1.0f / (float) Math.sqrt(_t4)))).withLane(3, _t2)) : FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, (float) Math.cosFromSin(_t7, _t5) * _t2));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return exp_unsafe(dest, destOffset, src, srcOffset);
        return exp_api(dest, destOffset, src, srcOffset);
    }

    public static java.lang.foreign.MemorySegment exp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        float _selfz = UnsafeOpsHolder.U.getFloat(_srcBase + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(_srcBase + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(_srcBase + 4L);
        float _t2 = Math.min((float) Math.exp(UnsafeOpsHolder.U.getFloat(_srcBase + 12L)), 3.4028235E38f);
        float _t4 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t5 = (float) Math.sqrt(_t4);
        float _t7 = (float) Math.sin(_t5);
        var _c0 = (_t4  >  0.0f ? FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).withLane(3, (float) Math.cosFromSin(_t7, _t5)).mul(FloatVector.broadcast(SIMD_SPECIES, _t2 * (_t7 * (1.0f / (float) Math.sqrt(_t4)))).withLane(3, _t2)) : FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, (float) Math.cosFromSin(_t7, _t5) * _t2));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 0L);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t2 = Math.min((float) Math.exp(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L)), 3.4028235E38f);
        float _t4 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t5 = (float) Math.sqrt(_t4);
        float _t7 = (float) Math.sin(_t5);
        var _c0 = (_t4  >  0.0f ? FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).withLane(3, (float) Math.cosFromSin(_t7, _t5)).mul(FloatVector.broadcast(SIMD_SPECIES, _t2 * (_t7 * (1.0f / (float) Math.sqrt(_t4)))).withLane(3, _t2)) : FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, (float) Math.cosFromSin(_t7, _t5) * _t2));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] log(float[] dest, int destOffset, float[] src, int srcOffset) {
        float _selfw = src[srcOffset + 3];
        float _selfz = src[srcOffset + 2];
        float _selfx = src[srcOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        var _c0 = (_t2  >  0.0f ? FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, (float) Math.atan2((float) Math.sqrt(_t2), _selfw) * (1.0f / (float) Math.sqrt(_t2)))).withLane(3, (float) Math.log((float) Math.sqrt(Math.fma(_selfw, _selfw, _t2)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, (float) Math.log((float) Math.sqrt(Math.fma(_selfw, _selfw, _t2)))));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment log(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return log_unsafe(dest, destOffset, src, srcOffset);
        return log_api(dest, destOffset, src, srcOffset);
    }

    public static java.lang.foreign.MemorySegment log_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        float _selfw = UnsafeOpsHolder.U.getFloat(_srcBase + 12L);
        float _selfz = UnsafeOpsHolder.U.getFloat(_srcBase + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(_srcBase + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(_srcBase + 4L);
        float _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        var _c0 = (_t2  >  0.0f ? FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, (float) Math.atan2((float) Math.sqrt(_t2), _selfw) * (1.0f / (float) Math.sqrt(_t2)))).withLane(3, (float) Math.log((float) Math.sqrt(Math.fma(_selfw, _selfw, _t2)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, (float) Math.log((float) Math.sqrt(Math.fma(_selfw, _selfw, _t2)))));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment log_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 0L);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t2 = Math.fma(_selfz, _selfz, Math.fma(_selfx, _selfx, _selfy * _selfy));
        var _c0 = (_t2  >  0.0f ? FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, (float) Math.atan2((float) Math.sqrt(_t2), _selfw) * (1.0f / (float) Math.sqrt(_t2)))).withLane(3, (float) Math.log((float) Math.sqrt(Math.fma(_selfw, _selfw, _t2)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, (float) Math.log((float) Math.sqrt(Math.fma(_selfw, _selfw, _t2)))));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] normalize(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        float _t3 = _sv0.mul(_sv0).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t3  !=  0.0f ? _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t3)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalize(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        float _t3 = _sv0.mul(_sv0).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t3  !=  0.0f ? _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t3)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] rotateTowards(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float step) {
        if (SimdSupport.USE_FMA) return rotateTowards_fma(dest, destOffset, src, srcOffset, target, targetOffset, step);
        return rotateTowards_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, step);
    }

    public static float[] rotateTowards_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float step) {
        float _selfw = src[srcOffset + 3];
        float _targetw = target[targetOffset + 3];
        float _selfz = src[srcOffset + 2];
        float _targetz = target[targetOffset + 2];
        float _selfx = src[srcOffset + 0];
        float _targetx = target[targetOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _targety = target[targetOffset + 1];
        float _t7 = Math.fma(_selfw, _targetw, Math.fma(_selfz, _targetz, Math.fma(_selfx, _targetx, _selfy * _targety)));
        float _t9 = -_t7;
        float _t11 = (float) Math.acos(Math.min(1.0f, Math.abs(_t7)));
        float _t12 = (float) Math.sin(_t11);
        float _t13, _t14, _t15, _t16;
        if (_t9 > 0.0f) {
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
        float _t17 = _selfw - _t13;
        float _t18 = _selfz - _t14;
        float _t19 = _selfx - _t15;
        float _t20 = _selfy - _t16;
        float _t21 = _selfw + _t13;
        float _t22 = _selfz + _t14;
        float _t23 = _selfx + _t15;
        float _t24 = _selfy + _t16;
        float _t36 = 4.0f * (float) Math.atan2((float) Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))), (float) Math.sqrt(Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)))));
        float _t39 = _t36 > 0.0f ? Math.min(1.0f, step / _t36) : 0.0f;
        float _t40 = 1.0f - _t39;
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, target, targetOffset);
        var _sv2 = (_t9  >  0.0f ? _sv1.neg() : _sv1);
        var _sv3 = (_t12  >  0.0f ? _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(_t40 * _t11)), FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(_t11 * _t39)).mul(_sv2)).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / _t12)) : _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t40), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t39))));
        float _t72 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t72  !=  0.0f ? _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t72)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] rotateTowards_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float step) {
        float _selfw = src[srcOffset + 3];
        float _targetw = target[targetOffset + 3];
        float _selfz = src[srcOffset + 2];
        float _targetz = target[targetOffset + 2];
        float _selfx = src[srcOffset + 0];
        float _targetx = target[targetOffset + 0];
        float _selfy = src[srcOffset + 1];
        float _targety = target[targetOffset + 1];
        float _t7 = Math.fma(_selfw, _targetw, Math.fma(_selfz, _targetz, Math.fma(_selfx, _targetx, _selfy * _targety)));
        float _t9 = -_t7;
        float _t11 = (float) Math.acos(Math.min(1.0f, Math.abs(_t7)));
        float _t12 = (float) Math.sin(_t11);
        float _t13, _t14, _t15, _t16;
        if (_t9 > 0.0f) {
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
        float _t17 = _selfw - _t13;
        float _t18 = _selfz - _t14;
        float _t19 = _selfx - _t15;
        float _t20 = _selfy - _t16;
        float _t21 = _selfw + _t13;
        float _t22 = _selfz + _t14;
        float _t23 = _selfx + _t15;
        float _t24 = _selfy + _t16;
        float _t36 = 4.0f * (float) Math.atan2((float) Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))), (float) Math.sqrt(Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)))));
        float _t39 = _t36 > 0.0f ? Math.min(1.0f, step / _t36) : 0.0f;
        float _t40 = 1.0f - _t39;
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(SIMD_SPECIES, target, targetOffset);
        var _sv2 = (_t9  >  0.0f ? _sv1.neg() : _sv1);
        var _sv3 = (_t12  >  0.0f ? _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(_t40 * _t11))).add(FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(_t11 * _t39)).mul(_sv2)).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / _t12)) : _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t40)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t39))));
        float _t72 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t72  !=  0.0f ? _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t72)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateTowards(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float step) {
        if (SimdSupport.USE_FMA) return rotateTowards_fma(dest, destOffset, src, srcOffset, target, targetOffset, step);
        return rotateTowards_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, step);
    }

    public static java.lang.foreign.MemorySegment rotateTowards_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float step) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return rotateTowards_fma_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, step);
        return rotateTowards_fma_api(dest, destOffset, src, srcOffset, target, targetOffset, step);
    }

    public static java.lang.foreign.MemorySegment rotateTowards_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float step) {
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        float _selfw = UnsafeOpsHolder.U.getFloat(_srcBase + 12L);
        float _targetw = UnsafeOpsHolder.U.getFloat(_targetBase + 12L);
        float _selfz = UnsafeOpsHolder.U.getFloat(_srcBase + 8L);
        float _targetz = UnsafeOpsHolder.U.getFloat(_targetBase + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(_srcBase + 0L);
        float _targetx = UnsafeOpsHolder.U.getFloat(_targetBase + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(_srcBase + 4L);
        float _targety = UnsafeOpsHolder.U.getFloat(_targetBase + 4L);
        float _t7 = Math.fma(_selfw, _targetw, Math.fma(_selfz, _targetz, Math.fma(_selfx, _targetx, _selfy * _targety)));
        float _t9 = -_t7;
        float _t11 = (float) Math.acos(Math.min(1.0f, Math.abs(_t7)));
        float _t12 = (float) Math.sin(_t11);
        float _t13, _t14, _t15, _t16;
        if (_t9 > 0.0f) {
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
        float _t17 = _selfw - _t13;
        float _t18 = _selfz - _t14;
        float _t19 = _selfx - _t15;
        float _t20 = _selfy - _t16;
        float _t21 = _selfw + _t13;
        float _t22 = _selfz + _t14;
        float _t23 = _selfx + _t15;
        float _t24 = _selfy + _t16;
        float _t36 = 4.0f * (float) Math.atan2((float) Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))), (float) Math.sqrt(Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)))));
        float _t39 = _t36 > 0.0f ? Math.min(1.0f, step / _t36) : 0.0f;
        float _t40 = 1.0f - _t39;
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = (_t9  >  0.0f ? _sv1.neg() : _sv1);
        var _sv3 = (_t12  >  0.0f ? _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(_t40 * _t11)), FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(_t11 * _t39)).mul(_sv2)).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / _t12)) : _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t40), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t39))));
        float _t72 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t72  !=  0.0f ? _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t72)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateTowards_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float step) {
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _targetw = target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 12L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _targetz = target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 8L);
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 0L);
        float _targetx = target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 0L);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _targety = target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 4L);
        float _t7 = Math.fma(_selfw, _targetw, Math.fma(_selfz, _targetz, Math.fma(_selfx, _targetx, _selfy * _targety)));
        float _t9 = -_t7;
        float _t11 = (float) Math.acos(Math.min(1.0f, Math.abs(_t7)));
        float _t12 = (float) Math.sin(_t11);
        float _t13, _t14, _t15, _t16;
        if (_t9 > 0.0f) {
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
        float _t17 = _selfw - _t13;
        float _t18 = _selfz - _t14;
        float _t19 = _selfx - _t15;
        float _t20 = _selfy - _t16;
        float _t21 = _selfw + _t13;
        float _t22 = _selfz + _t14;
        float _t23 = _selfx + _t15;
        float _t24 = _selfy + _t16;
        float _t36 = 4.0f * (float) Math.atan2((float) Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))), (float) Math.sqrt(Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)))));
        float _t39 = _t36 > 0.0f ? Math.min(1.0f, step / _t36) : 0.0f;
        float _t40 = 1.0f - _t39;
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = (_t9  >  0.0f ? _sv1.neg() : _sv1);
        var _sv3 = (_t12  >  0.0f ? _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(_t40 * _t11)), FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(_t11 * _t39)).mul(_sv2)).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / _t12)) : _sv0.fma(FloatVector.broadcast(SIMD_SPECIES, _t40), _sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t39))));
        float _t72 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t72  !=  0.0f ? _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t72)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateTowards_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float step) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return rotateTowards_mulAdd_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, step);
        return rotateTowards_mulAdd_api(dest, destOffset, src, srcOffset, target, targetOffset, step);
    }

    public static java.lang.foreign.MemorySegment rotateTowards_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float step) {
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        float _selfw = UnsafeOpsHolder.U.getFloat(_srcBase + 12L);
        float _targetw = UnsafeOpsHolder.U.getFloat(_targetBase + 12L);
        float _selfz = UnsafeOpsHolder.U.getFloat(_srcBase + 8L);
        float _targetz = UnsafeOpsHolder.U.getFloat(_targetBase + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(_srcBase + 0L);
        float _targetx = UnsafeOpsHolder.U.getFloat(_targetBase + 0L);
        float _selfy = UnsafeOpsHolder.U.getFloat(_srcBase + 4L);
        float _targety = UnsafeOpsHolder.U.getFloat(_targetBase + 4L);
        float _t7 = Math.fma(_selfw, _targetw, Math.fma(_selfz, _targetz, Math.fma(_selfx, _targetx, _selfy * _targety)));
        float _t9 = -_t7;
        float _t11 = (float) Math.acos(Math.min(1.0f, Math.abs(_t7)));
        float _t12 = (float) Math.sin(_t11);
        float _t13, _t14, _t15, _t16;
        if (_t9 > 0.0f) {
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
        float _t17 = _selfw - _t13;
        float _t18 = _selfz - _t14;
        float _t19 = _selfx - _t15;
        float _t20 = _selfy - _t16;
        float _t21 = _selfw + _t13;
        float _t22 = _selfz + _t14;
        float _t23 = _selfx + _t15;
        float _t24 = _selfy + _t16;
        float _t36 = 4.0f * (float) Math.atan2((float) Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))), (float) Math.sqrt(Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)))));
        float _t39 = _t36 > 0.0f ? Math.min(1.0f, step / _t36) : 0.0f;
        float _t40 = 1.0f - _t39;
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = (_t9  >  0.0f ? _sv1.neg() : _sv1);
        var _sv3 = (_t12  >  0.0f ? _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(_t40 * _t11))).add(FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(_t11 * _t39)).mul(_sv2)).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / _t12)) : _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t40)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t39))));
        float _t72 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t72  !=  0.0f ? _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t72)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment rotateTowards_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float step) {
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _targetw = target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 12L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _targetz = target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 8L);
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 0L);
        float _targetx = target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 0L);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _targety = target.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, targetOffset + 4L);
        float _t7 = Math.fma(_selfw, _targetw, Math.fma(_selfz, _targetz, Math.fma(_selfx, _targetx, _selfy * _targety)));
        float _t9 = -_t7;
        float _t11 = (float) Math.acos(Math.min(1.0f, Math.abs(_t7)));
        float _t12 = (float) Math.sin(_t11);
        float _t13, _t14, _t15, _t16;
        if (_t9 > 0.0f) {
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
        float _t17 = _selfw - _t13;
        float _t18 = _selfz - _t14;
        float _t19 = _selfx - _t15;
        float _t20 = _selfy - _t16;
        float _t21 = _selfw + _t13;
        float _t22 = _selfz + _t14;
        float _t23 = _selfx + _t15;
        float _t24 = _selfy + _t16;
        float _t36 = 4.0f * (float) Math.atan2((float) Math.sqrt(Math.fma(_t17, _t17, Math.fma(_t18, _t18, Math.fma(_t19, _t19, _t20 * _t20)))), (float) Math.sqrt(Math.fma(_t21, _t21, Math.fma(_t22, _t22, Math.fma(_t23, _t23, _t24 * _t24)))));
        float _t39 = _t36 > 0.0f ? Math.min(1.0f, step / _t36) : 0.0f;
        float _t40 = 1.0f - _t39;
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = (_t9  >  0.0f ? _sv1.neg() : _sv1);
        var _sv3 = (_t12  >  0.0f ? _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(_t40 * _t11))).add(FloatVector.broadcast(SIMD_SPECIES, (float) Math.sin(_t11 * _t39)).mul(_sv2)).mul(FloatVector.broadcast(SIMD_SPECIES, 1.0f / _t12)) : _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, _t40)).add(_sv2.mul(FloatVector.broadcast(SIMD_SPECIES, _t39))));
        float _t72 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        var _c0 = (_t72  !=  0.0f ? _sv3.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) Math.sqrt(_t72)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] makeRotationAxis(float[] dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        if (SimdSupport.USE_FMA) return makeRotationAxis_fma(dest, destOffset, angle, axisX, axisY, axisZ);
        return makeRotationAxis_mulAdd(dest, destOffset, angle, axisX, axisY, axisZ);
    }

    public static float[] makeRotationAxis_fma(float[] dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        var _c0 = FloatVector.zero(SIMD_SPECIES).withLane(0, axisX).withLane(1, axisY).withLane(2, axisZ).fma(FloatVector.broadcast(SIMD_SPECIES, _t1), FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, (float) Math.cosFromSin(_t1, _t0)));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] makeRotationAxis_mulAdd(float[] dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        var _c0 = FloatVector.zero(SIMD_SPECIES).withLane(0, axisX).withLane(1, axisY).withLane(2, axisZ).mul(FloatVector.broadcast(SIMD_SPECIES, _t1)).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, (float) Math.cosFromSin(_t1, _t0)));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis(java.lang.foreign.MemorySegment dest, long destOffset, float angle, float axisX, float axisY, float axisZ) {
        if (SimdSupport.USE_FMA) return makeRotationAxis_fma(dest, destOffset, angle, axisX, axisY, axisZ);
        return makeRotationAxis_mulAdd(dest, destOffset, angle, axisX, axisY, axisZ);
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_fma(java.lang.foreign.MemorySegment dest, long destOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        var _c0 = FloatVector.zero(SIMD_SPECIES).withLane(0, axisX).withLane(1, axisY).withLane(2, axisZ).fma(FloatVector.broadcast(SIMD_SPECIES, _t1), FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, (float) Math.cosFromSin(_t1, _t0)));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        var _c0 = FloatVector.zero(SIMD_SPECIES).withLane(0, axisX).withLane(1, axisY).withLane(2, axisZ).mul(FloatVector.broadcast(SIMD_SPECIES, _t1)).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, (float) Math.cosFromSin(_t1, _t0)));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] makeRotationAxis(float[] dest, int destOffset, float[] axis, int axisOffset, float angle) {
        if (SimdSupport.USE_FMA) return makeRotationAxis_fma(dest, destOffset, axis, axisOffset, angle);
        return makeRotationAxis_mulAdd(dest, destOffset, axis, axisOffset, angle);
    }

    public static float[] makeRotationAxis_fma(float[] dest, int destOffset, float[] axis, int axisOffset, float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        var _c0 = FloatVector.zero(SIMD_SPECIES).withLane(0, axis[axisOffset + 0]).withLane(1, axis[axisOffset + 1]).withLane(2, axis[axisOffset + 2]).fma(FloatVector.broadcast(SIMD_SPECIES, _t1), FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, (float) Math.cosFromSin(_t1, _t0)));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] makeRotationAxis_mulAdd(float[] dest, int destOffset, float[] axis, int axisOffset, float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        var _c0 = FloatVector.zero(SIMD_SPECIES).withLane(0, axis[axisOffset + 0]).withLane(1, axis[axisOffset + 1]).withLane(2, axis[axisOffset + 2]).mul(FloatVector.broadcast(SIMD_SPECIES, _t1)).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, (float) Math.cosFromSin(_t1, _t0)));
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        if (SimdSupport.USE_FMA) return makeRotationAxis_fma(dest, destOffset, axis, axisOffset, angle);
        return makeRotationAxis_mulAdd(dest, destOffset, axis, axisOffset, angle);
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && axis.isNative()) return makeRotationAxis_fma_unsafe(dest, destOffset, axis, axisOffset, angle);
        return makeRotationAxis_fma_api(dest, destOffset, axis, axisOffset, angle);
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        long _axisBase = axis.address() + axisOffset;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        var _c0 = FloatVector.zero(SIMD_SPECIES).withLane(0, UnsafeOpsHolder.U.getFloat(_axisBase + 0L)).withLane(1, UnsafeOpsHolder.U.getFloat(_axisBase + 4L)).withLane(2, UnsafeOpsHolder.U.getFloat(_axisBase + 8L)).fma(FloatVector.broadcast(SIMD_SPECIES, _t1), FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, (float) Math.cosFromSin(_t1, _t0)));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        var _c0 = FloatVector.zero(SIMD_SPECIES).withLane(0, axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 0L)).withLane(1, axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 4L)).withLane(2, axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 8L)).fma(FloatVector.broadcast(SIMD_SPECIES, _t1), FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, (float) Math.cosFromSin(_t1, _t0)));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && axis.isNative()) return makeRotationAxis_mulAdd_unsafe(dest, destOffset, axis, axisOffset, angle);
        return makeRotationAxis_mulAdd_api(dest, destOffset, axis, axisOffset, angle);
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        long _axisBase = axis.address() + axisOffset;
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        var _c0 = FloatVector.zero(SIMD_SPECIES).withLane(0, UnsafeOpsHolder.U.getFloat(_axisBase + 0L)).withLane(1, UnsafeOpsHolder.U.getFloat(_axisBase + 4L)).withLane(2, UnsafeOpsHolder.U.getFloat(_axisBase + 8L)).mul(FloatVector.broadcast(SIMD_SPECIES, _t1)).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, (float) Math.cosFromSin(_t1, _t0)));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        float _t0 = 0.5f * angle;
        float _t1 = (float) Math.sin(_t0);
        var _c0 = FloatVector.zero(SIMD_SPECIES).withLane(0, axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 0L)).withLane(1, axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 4L)).withLane(2, axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 8L)).mul(FloatVector.broadcast(SIMD_SPECIES, _t1)).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, (float) Math.cosFromSin(_t1, _t0)));
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] makeRotationYXZ(float[] dest, int destOffset, float angleY, float angleX, float angleZ) {
        if (SimdSupport.USE_FMA) return makeRotationYXZ_fma(dest, destOffset, angleY, angleX, angleZ);
        return makeRotationYXZ_mulAdd(dest, destOffset, angleY, angleX, angleZ);
    }

    public static float[] makeRotationYXZ_fma(float[] dest, int destOffset, float angleY, float angleX, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t4, _t1);
        float _t7 = (float) Math.cosFromSin(_t5, _t2);
        float _t8 = (float) Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        var _c0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t11).withLane(2, _t12).withLane(3, _t9).fma(FloatVector.broadcast(SIMD_SPECIES, _t5), FloatVector.broadcast(SIMD_SPECIES, _t10).withLane(3, _t12).mul(FloatVector.broadcast(SIMD_SPECIES, _t7)).withLane(2, -(_t9 * _t7))).withLane(1, _t11 * _t7 - _t10 * _t5);
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static float[] makeRotationYXZ_mulAdd(float[] dest, int destOffset, float angleY, float angleX, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t4, _t1);
        float _t7 = (float) Math.cosFromSin(_t5, _t2);
        float _t8 = (float) Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        var _c0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t11).withLane(2, _t12).withLane(3, _t9).mul(FloatVector.broadcast(SIMD_SPECIES, _t5)).add(FloatVector.broadcast(SIMD_SPECIES, _t10).withLane(3, _t12).mul(FloatVector.broadcast(SIMD_SPECIES, _t7)).withLane(2, -(_t9 * _t7))).withLane(1, _t11 * _t7 - _t10 * _t5);
        _c0.intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationYXZ(java.lang.foreign.MemorySegment dest, long destOffset, float angleY, float angleX, float angleZ) {
        if (SimdSupport.USE_FMA) return makeRotationYXZ_fma(dest, destOffset, angleY, angleX, angleZ);
        return makeRotationYXZ_mulAdd(dest, destOffset, angleY, angleX, angleZ);
    }

    public static java.lang.foreign.MemorySegment makeRotationYXZ_fma(java.lang.foreign.MemorySegment dest, long destOffset, float angleY, float angleX, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t4, _t1);
        float _t7 = (float) Math.cosFromSin(_t5, _t2);
        float _t8 = (float) Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        var _c0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t11).withLane(2, _t12).withLane(3, _t9).fma(FloatVector.broadcast(SIMD_SPECIES, _t5), FloatVector.broadcast(SIMD_SPECIES, _t10).withLane(3, _t12).mul(FloatVector.broadcast(SIMD_SPECIES, _t7)).withLane(2, -(_t9 * _t7))).withLane(1, _t11 * _t7 - _t10 * _t5);
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationYXZ_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float angleY, float angleX, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = (float) Math.sin(_t0);
        float _t4 = (float) Math.sin(_t1);
        float _t5 = (float) Math.sin(_t2);
        float _t6 = (float) Math.cosFromSin(_t4, _t1);
        float _t7 = (float) Math.cosFromSin(_t5, _t2);
        float _t8 = (float) Math.cosFromSin(_t3, _t0);
        float _t9 = _t3 * _t4;
        float _t10 = _t3 * _t6;
        float _t11 = _t4 * _t8;
        float _t12 = _t8 * _t6;
        var _c0 = FloatVector.zero(SIMD_SPECIES).withLane(0, _t11).withLane(2, _t12).withLane(3, _t9).mul(FloatVector.broadcast(SIMD_SPECIES, _t5)).add(FloatVector.broadcast(SIMD_SPECIES, _t10).withLane(3, _t12).mul(FloatVector.broadcast(SIMD_SPECIES, _t7)).withLane(2, -(_t9 * _t7))).withLane(1, _t11 * _t7 - _t10 * _t5);
        _c0.intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
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

    private static void copyArrSeg(float[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int n) {
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length())
            FloatVector.fromMemorySegment(_sp, src, srcOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + _i);
        for (; _i <= n - 8; _i += 8)
            FloatVector.fromMemorySegment(FloatVector.SPECIES_256, src, srcOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + _i);
        for (; _i <= n - 4; _i += 4)
            FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset + _i);
        for (; _i < n; _i++)
            dest[destOffset + _i] = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + (long) _i * 4L);
    }

    private static void copySegArr(java.lang.foreign.MemorySegment dest, long destOffset, float[] src, int srcOffset, int n) {
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length())
            FloatVector.fromArray(_sp, src, srcOffset + _i).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        for (; _i <= n - 8; _i += 8)
            FloatVector.fromArray(FloatVector.SPECIES_256, src, srcOffset + _i).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        for (; _i <= n - 4; _i += 4)
            FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset + _i).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        for (; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + (long) _i * 4L, src[srcOffset + _i]);
    }

    private static void copySegSeg(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int n) {
        var _sp = FloatVector.SPECIES_PREFERRED;
        int _bound = _sp.loopBound(n);
        int _i = 0;
        for (; _i < _bound; _i += _sp.length())
            FloatVector.fromMemorySegment(_sp, src, srcOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        for (; _i <= n - 8; _i += 8)
            FloatVector.fromMemorySegment(FloatVector.SPECIES_256, src, srcOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        for (; _i <= n - 4; _i += 4)
            FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset + (long) _i * 4L, java.nio.ByteOrder.nativeOrder());
        for (; _i < n; _i++)
            dest.set(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, destOffset + (long) _i * 4L, src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + (long) _i * 4L));
    }

    private static void copyArrArr_one(float[] dest, int destOffset, float[] src, int srcOffset) {
        FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset).intoArray(dest, destOffset);
    }

    private static void copyArrSeg_one(float[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoArray(dest, destOffset);
    }

    private static void copySegArr_one(java.lang.foreign.MemorySegment dest, long destOffset, float[] src, int srcOffset) {
        FloatVector.fromArray(FloatVector.SPECIES_128, src, srcOffset).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }

    private static void copySegSeg_one(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        FloatVector.fromMemorySegment(FloatVector.SPECIES_128, src, srcOffset, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }


    public static float[] copy(float[] dest, int destOffset, float[] src, int srcOffset) {
        copyArrArr_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        copyArrArr(dest, destOffset, src, srcOffset, count * 4);
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr_one(dest, destOffset, _srcArr, _srcOff);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg_one(dest, destOffset, _srcSeg, (long) srcOffset * 4);
        }
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (src.hasArray() && srcOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && srcOffset <= src.limit() - (count > 536870911 ? -1 : count * 4)) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copyArrArr(dest, destOffset, _srcArr, _srcOff, count * 4);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg(dest, destOffset, _srcSeg, (long) srcOffset * 4, count * 4);
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
        copyArrSeg(dest, destOffset, _srcSeg, srcOffset, count * 4);
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        copyArrSeg_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static float[] copy(float[] dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        copyArrSeg(dest, destOffset, src, srcOffset, count * 4);
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, float[] src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr_one(_destArr, _destOff, src, srcOffset);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegArr_one(_destSeg, (long) destOffset * 4, src, srcOffset);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, float[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && destOffset <= dest.limit() - (count > 536870911 ? -1 : count * 4)) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrArr(_destArr, _destOff, src, srcOffset, count * 4);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegArr(_destSeg, (long) destOffset * 4, src, srcOffset, count * 4);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr_one(_destArr, _destOff, _srcArr, _srcOff);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copyArrSeg_one(_destArr, _destOff, _srcSeg, (long) srcOffset * 4);
            }
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copySegArr_one(_destSeg, (long) destOffset * 4, _srcArr, _srcOff);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copySegSeg_one(_destSeg, (long) destOffset * 4, _srcSeg, (long) srcOffset * 4);
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && destOffset <= dest.limit() - (count > 536870911 ? -1 : count * 4)) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && srcOffset <= src.limit() - (count > 536870911 ? -1 : count * 4)) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr(_destArr, _destOff, _srcArr, _srcOff, count * 4);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copyArrSeg(_destArr, _destOff, _srcSeg, (long) srcOffset * 4, count * 4);
            }
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            if (src.hasArray() && srcOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && srcOffset <= src.limit() - (count > 536870911 ? -1 : count * 4)) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copySegArr(_destSeg, (long) destOffset * 4, _srcArr, _srcOff, count * 4);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copySegSeg(_destSeg, (long) destOffset * 4, _srcSeg, (long) srcOffset * 4, count * 4);
            }
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg_one(_destArr, _destOff, _srcSeg, srcOffset);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg_one(_destSeg, (long) destOffset * 4, _srcSeg, srcOffset);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.nio.ByteBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && destOffset <= dest.limit() - (count > 536870911 ? -1 : count * 4)) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copyArrSeg(_destArr, _destOff, _srcSeg, srcOffset, count * 4);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg(_destSeg, (long) destOffset * 4, _srcSeg, srcOffset, count * 4);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (dest.hasArray() && destOffset >= 0 && destOffset <= dest.limit() - 4) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrSeg_one(_destArr, _destOff, src, srcOffset);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegSeg_one(_destSeg, (long) destOffset * 4, src, srcOffset);
        }
        return dest;
    }

    public static java.nio.FloatBuffer copy(java.nio.FloatBuffer dest, int destOffset, java.lang.foreign.MemorySegment src, long srcOffset, int count) {
        if (count < 0) return dest;
        if (dest.hasArray() && destOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && destOffset <= dest.limit() - (count > 536870911 ? -1 : count * 4)) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            copyArrSeg(_destArr, _destOff, src, srcOffset, count * 4);
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            copySegSeg(_destSeg, (long) destOffset * 4, src, srcOffset, count * 4);
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
        copySegArr(_destSeg, destOffset, src, srcOffset, count * 4);
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset) {
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr_one(_destSeg, destOffset, _srcArr, _srcOff);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg_one(_destSeg, destOffset, _srcSeg, (long) srcOffset * 4);
        }
        return dest;
    }

    public static java.nio.ByteBuffer copy(java.nio.ByteBuffer dest, int destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
        if (src.hasArray() && srcOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && srcOffset <= src.limit() - (count > 536870911 ? -1 : count * 4)) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr(_destSeg, destOffset, _srcArr, _srcOff, count * 4);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg(_destSeg, destOffset, _srcSeg, (long) srcOffset * 4, count * 4);
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

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, float[] src, int srcOffset) {
        copySegArr_one(dest, destOffset, src, srcOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, float[] src, int srcOffset, int count) {
        if (count < 0) return dest;
        copySegArr(dest, destOffset, src, srcOffset, count * 4);
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.FloatBuffer src, int srcOffset) {
        if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr_one(dest, destOffset, _srcArr, _srcOff);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg_one(dest, destOffset, _srcSeg, (long) srcOffset * 4);
        }
        return dest;
    }

    public static java.lang.foreign.MemorySegment copy(java.lang.foreign.MemorySegment dest, long destOffset, java.nio.FloatBuffer src, int srcOffset, int count) {
        if (count < 0) return dest;
        if (src.hasArray() && srcOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && srcOffset <= src.limit() - (count > 536870911 ? -1 : count * 4)) {
            float[] _srcArr = src.array();
            int _srcOff = src.arrayOffset() + srcOffset;
            copySegArr(dest, destOffset, _srcArr, _srcOff, count * 4);
        } else {
            java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
            copySegSeg(dest, destOffset, _srcSeg, (long) srcOffset * 4, count * 4);
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
    private static float quatArcAngle(float s) {
        float d = 4.0f - s;
        return s > d ? 2.0f * (float) Math.asin(0.5f * (float) Math.sqrt(d))
                : (float) Math.PI - 2.0f * (float) Math.asin(0.5f * (float) Math.sqrt(s));
    }

    /** Double-precision twin of {@link #quatArcAngle(float)}. */
    private static double quatArcAngle(double s) {
        double d = 4.0 - s;
        return s > d ? 2.0 * Math.asin(0.5 * Math.sqrt(d)) : Math.PI - 2.0 * Math.asin(0.5 * Math.sqrt(s));
    }
}
