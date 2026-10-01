// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.opssimd;

import jdk.incubator.vector.*;
import org.joml2.*;
import org.joml2.Math;
import org.joml2.ops.*;
import org.joml2.internal.unsafe.*;
import org.joml2.internal.kernels.*;
import org.joml2.internal.simd.*;

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
    private static final float[] DATA_0 = new float[] {0.0f, 0.0f, 0.0f, 1.0f};

    public static float[] add(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        FloatVector.fromArray(SIMD_SPECIES, other, otherOffset).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment add(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder()).add(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] mul(float[] dest, int destOffset, float[] src, int srcOffset, float scalar) {
        FloatVector.broadcast(SIMD_SPECIES, scalar).mul(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment mul(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float scalar) {
        FloatVector.broadcast(SIMD_SPECIES, scalar).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] negate(float[] dest, int destOffset, float[] src, int srcOffset) {
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).neg().intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment negate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).neg().intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] sub(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset) {
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).sub(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment sub(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).sub(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] set(float[] dest, int destOffset, float[] v, int vOffset) {
        FloatVector.fromArray(SIMD_SPECIES, v, vOffset).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment set(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment v, long vOffset) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, v, vOffset, java.nio.ByteOrder.nativeOrder()).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
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

    public static float[] makeIdentity(float[] dest, int destOffset) {
        FloatVector.fromArray(FloatVector.SPECIES_128, DATA_0, 0).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] makeZero(float[] dest, int destOffset) {
        FloatVector.broadcast(SIMD_SPECIES, 0.0f).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeZero(java.lang.foreign.MemorySegment dest, long destOffset) {
        FloatVector.broadcast(SIMD_SPECIES, 0.0f).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] lerp(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    public static float[] lerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        FloatVector.broadcast(SIMD_SPECIES, t).fma(FloatVector.zero(SIMD_SPECIES).withLane(0, otherX).withLane(1, otherY).withLane(2, otherZ).withLane(3, otherW).sub(_sv0), _sv0).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] lerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        FloatVector.broadcast(SIMD_SPECIES, t).mul(FloatVector.zero(SIMD_SPECIES).withLane(0, otherX).withLane(1, otherY).withLane(2, otherZ).withLane(3, otherW).sub(_sv0)).add(_sv0).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, otherX, otherY, otherZ, otherW, t);
    }

    public static java.lang.foreign.MemorySegment lerp_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, t).fma(FloatVector.zero(SIMD_SPECIES).withLane(0, otherX).withLane(1, otherY).withLane(2, otherZ).withLane(3, otherW).sub(_sv0), _sv0).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float otherX, float otherY, float otherZ, float otherW, float t) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, t).mul(FloatVector.zero(SIMD_SPECIES).withLane(0, otherX).withLane(1, otherY).withLane(2, otherZ).withLane(3, otherW).sub(_sv0)).add(_sv0).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] lerp(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static float[] lerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        FloatVector.broadcast(SIMD_SPECIES, t).fma(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset).sub(_sv0), _sv0).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] lerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float t) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        FloatVector.broadcast(SIMD_SPECIES, t).mul(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset).sub(_sv0)).add(_sv0).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        if (SimdSupport.USE_FMA) return lerp_fma(dest, destOffset, src, srcOffset, other, otherOffset, t);
        return lerp_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, t);
    }

    public static java.lang.foreign.MemorySegment lerp_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, t).fma(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder()).sub(_sv0), _sv0).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment lerp_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float t) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        FloatVector.broadcast(SIMD_SPECIES, t).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder()).sub(_sv0)).add(_sv0).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] nlerp(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        if (SimdSupport.USE_FMA) return nlerp_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return nlerp_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static float[] nlerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.broadcast(_SIMD_SPECIES, alpha).fma(FloatVector.fromArray(_SIMD_SPECIES, target, targetOffset), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f - alpha)));
        float _t12 = _sv0.mul(_sv0).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t12 != 0.0f ? _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t12)))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f)).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] nlerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.broadcast(_SIMD_SPECIES, alpha).mul(FloatVector.fromArray(_SIMD_SPECIES, target, targetOffset)).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f - alpha)));
        float _t12 = _sv0.mul(_sv0).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t12 != 0.0f ? _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t12)))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment nlerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        if (SimdSupport.USE_FMA) return nlerp_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return nlerp_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment nlerp_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.broadcast(_SIMD_SPECIES, alpha).fma(FloatVector.fromMemorySegment(_SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder()), FloatVector.fromMemorySegment(_SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f - alpha)));
        float _t12 = _sv0.mul(_sv0).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t12 != 0.0f ? _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t12)))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment nlerp_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.broadcast(_SIMD_SPECIES, alpha).mul(FloatVector.fromMemorySegment(_SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder())).add(FloatVector.fromMemorySegment(_SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f - alpha)));
        float _t12 = _sv0.mul(_sv0).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t12 != 0.0f ? _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t12)))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] nlerpShortest(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        if (SimdSupport.USE_FMA) return nlerpShortest_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return nlerpShortest_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static float[] nlerpShortest_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, target, targetOffset);
        var _sv1 = FloatVector.broadcast(_SIMD_SPECIES, alpha).fma((-java.lang.Math.fma(src[srcOffset + 3], target[targetOffset + 3], java.lang.Math.fma(src[srcOffset + 2], target[targetOffset + 2], java.lang.Math.fma(src[srcOffset], target[targetOffset], src[srcOffset + 1] * target[targetOffset + 1]))) > 0.0f ? _sv0.neg() : _sv0), FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f - alpha)));
        float _t25 = _sv1.mul(_sv1).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t25 != 0.0f ? _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t25)))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f)).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] nlerpShortest_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, target, targetOffset);
        var _sv1 = FloatVector.broadcast(_SIMD_SPECIES, alpha).mul((-((src[srcOffset + 3]) * (target[targetOffset + 3]) + (((src[srcOffset + 2]) * (target[targetOffset + 2]) + (((src[srcOffset]) * (target[targetOffset]) + (src[srcOffset + 1] * target[targetOffset + 1])))))) > 0.0f ? _sv0.neg() : _sv0)).add(FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f - alpha)));
        float _t25 = _sv1.mul(_sv1).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t25 != 0.0f ? _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t25)))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment nlerpShortest(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return nlerpShortest_fma_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
            return nlerpShortest_fma_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return nlerpShortest_mulAdd_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return nlerpShortest_mulAdd_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment nlerpShortest_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return nlerpShortest_fma_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return nlerpShortest_fma_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment nlerpShortest_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromMemorySegment(_SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.broadcast(_SIMD_SPECIES, alpha).fma((-java.lang.Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 12L), UnsafeOpsHolder.U.getFloat(_targetBase + 12L), java.lang.Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 8L), UnsafeOpsHolder.U.getFloat(_targetBase + 8L), java.lang.Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase), UnsafeOpsHolder.U.getFloat(_targetBase), UnsafeOpsHolder.U.getFloat(_srcBase + 4L) * UnsafeOpsHolder.U.getFloat(_targetBase + 4L)))) > 0.0f ? _sv0.neg() : _sv0), FloatVector.fromMemorySegment(_SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f - alpha)));
        float _t25 = _sv1.mul(_sv1).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t25 != 0.0f ? _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t25)))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment nlerpShortest_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromMemorySegment(_SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder());
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        var _sv1 = FloatVector.broadcast(_SIMD_SPECIES, alpha).fma((-java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L), target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 12L), java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L), target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 8L), java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), target.get(_JAVA_FLOAT_UNALIGNED, targetOffset), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) * target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 4L)))) > 0.0f ? _sv0.neg() : _sv0), FloatVector.fromMemorySegment(_SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f - alpha)));
        float _t25 = _sv1.mul(_sv1).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t25 != 0.0f ? _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t25)))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment nlerpShortest_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return nlerpShortest_mulAdd_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return nlerpShortest_mulAdd_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment nlerpShortest_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromMemorySegment(_SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.broadcast(_SIMD_SPECIES, alpha).mul((-((UnsafeOpsHolder.U.getFloat(_srcBase + 12L)) * (UnsafeOpsHolder.U.getFloat(_targetBase + 12L)) + (((UnsafeOpsHolder.U.getFloat(_srcBase + 8L)) * (UnsafeOpsHolder.U.getFloat(_targetBase + 8L)) + (((UnsafeOpsHolder.U.getFloat(_srcBase)) * (UnsafeOpsHolder.U.getFloat(_targetBase)) + (UnsafeOpsHolder.U.getFloat(_srcBase + 4L) * UnsafeOpsHolder.U.getFloat(_targetBase + 4L))))))) > 0.0f ? _sv0.neg() : _sv0)).add(FloatVector.fromMemorySegment(_SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f - alpha)));
        float _t25 = _sv1.mul(_sv1).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t25 != 0.0f ? _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t25)))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment nlerpShortest_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromMemorySegment(_SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder());
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        var _sv1 = FloatVector.broadcast(_SIMD_SPECIES, alpha).mul((-((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L)) * (target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 12L)) + (((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L)) * (target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 8L)) + (((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (target.get(_JAVA_FLOAT_UNALIGNED, targetOffset)) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) * target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 4L))))))) > 0.0f ? _sv0.neg() : _sv0)).add(FloatVector.fromMemorySegment(_SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f - alpha)));
        float _t25 = _sv1.mul(_sv1).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t25 != 0.0f ? _sv1.mul(FloatVector.broadcast(_SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t25)))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] slerp(float[] dest, int destOffset, float[] src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (SimdSupport.USE_FMA) return slerp_fma(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return slerp_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    public static float[] slerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _t2 = src[srcOffset + 3] + targetW;
        float _t3 = src[srcOffset + 2] + targetZ;
        float _t4 = src[srcOffset] + targetX;
        float _t5 = src[srcOffset + 1] + targetY;
        float _t10 = Math.min(4.0f, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_fma(_t10);
        float _t17 = 2.0f * Math.sinWithCos(_t14);
        float _ct1 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct1 > 2.0E-6f && _ct1 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        float _t20_inv = 1.0f / _ct1;
        float _t22 = _t17 * _t20_inv;
        float _t24 = Math.cosWithSin(_t14) - java.lang.Math.fma(0.5f, _t10, -1.0f) * _t17 * _t20_inv;
        float _w0, _w1;
        if (alpha < 0.5f) {
            _w0 = _t24;
            _w1 = _t22;
        } else {
            _w0 = _t22;
            _w1 = _t24;
        }
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(FloatVector.broadcast(SIMD_SPECIES, _w0), FloatVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(FloatVector.broadcast(SIMD_SPECIES, _w1))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] slerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _t2 = src[srcOffset + 3] + targetW;
        float _t3 = src[srcOffset + 2] + targetZ;
        float _t4 = src[srcOffset] + targetX;
        float _t5 = src[srcOffset + 1] + targetY;
        float _t10 = Math.min(4.0f, ((_t2) * (_t2) + (((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5)))))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_mulAdd(_t10);
        float _t17 = 2.0f * Math.sinWithCos(_t14);
        float _ct3 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct3 > 2.0E-6f && _ct3 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        float _t20_inv = 1.0f / _ct3;
        float _t22 = _t17 * _t20_inv;
        float _t24 = Math.cosWithSin(_t14) - ((0.5f) * (_t10) - (1.0f)) * _t17 * _t20_inv;
        float _w0, _w1;
        if (alpha < 0.5f) {
            _w0 = _t24;
            _w1 = _t22;
        } else {
            _w0 = _t22;
            _w1 = _t24;
        }
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(FloatVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(FloatVector.broadcast(SIMD_SPECIES, _w1))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] slerp_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (SimdSupport.USE_FMA) return slerp_degenerate_fma(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return slerp_degenerate_mulAdd(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    public static float[] slerp_degenerate_fma(float[] dest, int destOffset, float[] src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _t1 = src[srcOffset + 3] + targetW;
        float _t2 = src[srcOffset + 2] + targetZ;
        float _t3 = src[srcOffset] + targetX;
        float _t4 = src[srcOffset + 1] + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, _t4 * _t4))));
        float _w0, _w1;
        if (_t11 > 4.0f - _t11) {
            _w0 = 1.0f - alpha;
            _w1 = alpha;
        } else {
            _w0 = _t5;
            _w1 = 1.0f - _t5;
        }
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(FloatVector.broadcast(SIMD_SPECIES, _w0), FloatVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(FloatVector.broadcast(SIMD_SPECIES, _w1))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] slerp_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _t1 = src[srcOffset + 3] + targetW;
        float _t2 = src[srcOffset + 2] + targetZ;
        float _t3 = src[srcOffset] + targetX;
        float _t4 = src[srcOffset + 1] + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, ((_t1) * (_t1) + (((_t2) * (_t2) + (((_t3) * (_t3) + (_t4 * _t4)))))));
        float _w0, _w1;
        if (_t11 > 4.0f - _t11) {
            _w0 = 1.0f - alpha;
            _w1 = alpha;
        } else {
            _w0 = _t5;
            _w1 = 1.0f - _t5;
        }
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(FloatVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(FloatVector.broadcast(SIMD_SPECIES, _w1))).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return slerp_fma_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
            return slerp_fma_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return slerp_mulAdd_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return slerp_mulAdd_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    public static java.lang.foreign.MemorySegment slerp_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return slerp_fma_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return slerp_fma_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    public static java.lang.foreign.MemorySegment slerp_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        long _srcBase = src.address() + srcOffset;
        float _t2 = UnsafeOpsHolder.U.getFloat(_srcBase + 12L) + targetW;
        float _t3 = UnsafeOpsHolder.U.getFloat(_srcBase + 8L) + targetZ;
        float _t4 = UnsafeOpsHolder.U.getFloat(_srcBase) + targetX;
        float _t5 = UnsafeOpsHolder.U.getFloat(_srcBase + 4L) + targetY;
        float _t10 = Math.min(4.0f, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_fma(_t10);
        float _t17 = 2.0f * Math.sinWithCos(_t14);
        float _ct2 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct2 > 2.0E-6f && _ct2 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        float _t20_inv = 1.0f / _ct2;
        float _t22 = _t17 * _t20_inv;
        float _t24 = Math.cosWithSin(_t14) - java.lang.Math.fma(0.5f, _t10, -1.0f) * _t17 * _t20_inv;
        float _w0, _w1;
        if (alpha < 0.5f) {
            _w0 = _t24;
            _w1 = _t22;
        } else {
            _w0 = _t22;
            _w1 = _t24;
        }
        slerp_fma_unsafe_s350056bb_v(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, _w0, _w1);
        return dest;
    }

    private static void slerp_fma_unsafe_s350056bb_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float _w0, float _w1) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.broadcast(SIMD_SPECIES, _w0), FloatVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(FloatVector.broadcast(SIMD_SPECIES, _w1))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment slerp_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L) + targetW;
        float _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) + targetZ;
        float _t4 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) + targetX;
        float _t5 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) + targetY;
        float _t10 = Math.min(4.0f, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_fma(_t10);
        float _t17 = 2.0f * Math.sinWithCos(_t14);
        float _ct3 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct3 > 2.0E-6f && _ct3 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        float _t20_inv = 1.0f / _ct3;
        float _t22 = _t17 * _t20_inv;
        float _t24 = Math.cosWithSin(_t14) - java.lang.Math.fma(0.5f, _t10, -1.0f) * _t17 * _t20_inv;
        float _w0, _w1;
        if (alpha < 0.5f) {
            _w0 = _t24;
            _w1 = _t22;
        } else {
            _w0 = _t22;
            _w1 = _t24;
        }
        slerp_fma_unsafe_s350056bb_v(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, _w0, _w1);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return slerp_mulAdd_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return slerp_mulAdd_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    public static java.lang.foreign.MemorySegment slerp_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        long _srcBase = src.address() + srcOffset;
        float _t2 = UnsafeOpsHolder.U.getFloat(_srcBase + 12L) + targetW;
        float _t3 = UnsafeOpsHolder.U.getFloat(_srcBase + 8L) + targetZ;
        float _t4 = UnsafeOpsHolder.U.getFloat(_srcBase) + targetX;
        float _t5 = UnsafeOpsHolder.U.getFloat(_srcBase + 4L) + targetY;
        float _t10 = Math.min(4.0f, ((_t2) * (_t2) + (((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5)))))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_mulAdd(_t10);
        float _t17 = 2.0f * Math.sinWithCos(_t14);
        float _ct5 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct5 > 2.0E-6f && _ct5 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        float _t20_inv = 1.0f / _ct5;
        float _t22 = _t17 * _t20_inv;
        float _t24 = Math.cosWithSin(_t14) - ((0.5f) * (_t10) - (1.0f)) * _t17 * _t20_inv;
        float _w0, _w1;
        if (alpha < 0.5f) {
            _w0 = _t24;
            _w1 = _t22;
        } else {
            _w0 = _t22;
            _w1 = _t24;
        }
        slerp_mulAdd_unsafe_s213cfb5c_v(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, _w0, _w1);
        return dest;
    }

    private static void slerp_mulAdd_unsafe_s213cfb5c_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float _w0, float _w1) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(FloatVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(FloatVector.broadcast(SIMD_SPECIES, _w1))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment slerp_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L) + targetW;
        float _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) + targetZ;
        float _t4 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) + targetX;
        float _t5 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) + targetY;
        float _t10 = Math.min(4.0f, ((_t2) * (_t2) + (((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5)))))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_mulAdd(_t10);
        float _t17 = 2.0f * Math.sinWithCos(_t14);
        float _ct6 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct6 > 2.0E-6f && _ct6 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        float _t20_inv = 1.0f / _ct6;
        float _t22 = _t17 * _t20_inv;
        float _t24 = Math.cosWithSin(_t14) - ((0.5f) * (_t10) - (1.0f)) * _t17 * _t20_inv;
        float _w0, _w1;
        if (alpha < 0.5f) {
            _w0 = _t24;
            _w1 = _t22;
        } else {
            _w0 = _t22;
            _w1 = _t24;
        }
        slerp_mulAdd_unsafe_s213cfb5c_v(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, _w0, _w1);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return slerp_degenerate_fma_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
            return slerp_degenerate_fma_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return slerp_degenerate_mulAdd_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return slerp_degenerate_mulAdd_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return slerp_degenerate_fma_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return slerp_degenerate_fma_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        long _srcBase = src.address() + srcOffset;
        float _t1 = UnsafeOpsHolder.U.getFloat(_srcBase + 12L) + targetW;
        float _t2 = UnsafeOpsHolder.U.getFloat(_srcBase + 8L) + targetZ;
        float _t3 = UnsafeOpsHolder.U.getFloat(_srcBase) + targetX;
        float _t4 = UnsafeOpsHolder.U.getFloat(_srcBase + 4L) + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, _t4 * _t4))));
        float _w0, _w1;
        if (_t11 > 4.0f - _t11) {
            _w0 = 1.0f - alpha;
            _w1 = alpha;
        } else {
            _w0 = _t5;
            _w1 = 1.0f - _t5;
        }
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.broadcast(SIMD_SPECIES, _w0), FloatVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(FloatVector.broadcast(SIMD_SPECIES, _w1))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _t1 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L) + targetW;
        float _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) + targetZ;
        float _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) + targetX;
        float _t4 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, _t4 * _t4))));
        float _w0, _w1;
        if (_t11 > 4.0f - _t11) {
            _w0 = 1.0f - alpha;
            _w1 = alpha;
        } else {
            _w0 = _t5;
            _w1 = 1.0f - _t5;
        }
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.broadcast(SIMD_SPECIES, _w0), FloatVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(FloatVector.broadcast(SIMD_SPECIES, _w1))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return slerp_degenerate_mulAdd_unsafe(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
        return slerp_degenerate_mulAdd_api(dest, destOffset, src, srcOffset, targetX, targetY, targetZ, targetW, alpha);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        long _srcBase = src.address() + srcOffset;
        float _t1 = UnsafeOpsHolder.U.getFloat(_srcBase + 12L) + targetW;
        float _t2 = UnsafeOpsHolder.U.getFloat(_srcBase + 8L) + targetZ;
        float _t3 = UnsafeOpsHolder.U.getFloat(_srcBase) + targetX;
        float _t4 = UnsafeOpsHolder.U.getFloat(_srcBase + 4L) + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, ((_t1) * (_t1) + (((_t2) * (_t2) + (((_t3) * (_t3) + (_t4 * _t4)))))));
        float _w0, _w1;
        if (_t11 > 4.0f - _t11) {
            _w0 = 1.0f - alpha;
            _w1 = alpha;
        } else {
            _w0 = _t5;
            _w1 = 1.0f - _t5;
        }
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(FloatVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(FloatVector.broadcast(SIMD_SPECIES, _w1))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, float targetX, float targetY, float targetZ, float targetW, float alpha) {
        float _t1 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L) + targetW;
        float _t2 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L) + targetZ;
        float _t3 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset) + targetX;
        float _t4 = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L) + targetY;
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, ((_t1) * (_t1) + (((_t2) * (_t2) + (((_t3) * (_t3) + (_t4 * _t4)))))));
        float _w0, _w1;
        if (_t11 > 4.0f - _t11) {
            _w0 = 1.0f - alpha;
            _w1 = alpha;
        } else {
            _w0 = _t5;
            _w1 = 1.0f - _t5;
        }
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(FloatVector.zero(SIMD_SPECIES).withLane(0, targetX).withLane(1, targetY).withLane(2, targetZ).withLane(3, targetW).mul(FloatVector.broadcast(SIMD_SPECIES, _w1))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] slerp(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        if (SimdSupport.USE_FMA) return slerp_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerp_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static float[] slerp_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        float _t2 = src[srcOffset + 3] + target[targetOffset + 3];
        float _t3 = src[srcOffset + 2] + target[targetOffset + 2];
        float _t4 = src[srcOffset] + target[targetOffset];
        float _t5 = src[srcOffset + 1] + target[targetOffset + 1];
        float _t10 = Math.min(4.0f, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_fma(_t10);
        float _t17 = 2.0f * Math.sinWithCos(_t14);
        float _ct1 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct1 > 2.0E-6f && _ct1 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        float _t20_inv = 1.0f / _ct1;
        float _t22 = _t17 * _t20_inv;
        float _t24 = Math.cosWithSin(_t14) - java.lang.Math.fma(0.5f, _t10, -1.0f) * _t17 * _t20_inv;
        float _w0, _w1;
        if (alpha < 0.5f) {
            _w0 = _t24;
            _w1 = _t22;
        } else {
            _w0 = _t22;
            _w1 = _t24;
        }
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(FloatVector.broadcast(SIMD_SPECIES, _w0), FloatVector.fromArray(SIMD_SPECIES, target, targetOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _w1))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] slerp_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        float _t2 = src[srcOffset + 3] + target[targetOffset + 3];
        float _t3 = src[srcOffset + 2] + target[targetOffset + 2];
        float _t4 = src[srcOffset] + target[targetOffset];
        float _t5 = src[srcOffset + 1] + target[targetOffset + 1];
        float _t10 = Math.min(4.0f, ((_t2) * (_t2) + (((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5)))))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_mulAdd(_t10);
        float _t17 = 2.0f * Math.sinWithCos(_t14);
        float _ct3 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct3 > 2.0E-6f && _ct3 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsArray.slerp_degenerate(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        float _t20_inv = 1.0f / _ct3;
        float _t22 = _t17 * _t20_inv;
        float _t24 = Math.cosWithSin(_t14) - ((0.5f) * (_t10) - (1.0f)) * _t17 * _t20_inv;
        float _w0, _w1;
        if (alpha < 0.5f) {
            _w0 = _t24;
            _w1 = _t22;
        } else {
            _w0 = _t22;
            _w1 = _t24;
        }
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(FloatVector.fromArray(SIMD_SPECIES, target, targetOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _w1))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] slerp_degenerate(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        if (SimdSupport.USE_FMA) return slerp_degenerate_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerp_degenerate_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static float[] slerp_degenerate_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        float _t1 = src[srcOffset + 3] + target[targetOffset + 3];
        float _t2 = src[srcOffset + 2] + target[targetOffset + 2];
        float _t3 = src[srcOffset] + target[targetOffset];
        float _t4 = src[srcOffset + 1] + target[targetOffset + 1];
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, _t4 * _t4))));
        float _w0, _w1;
        if (_t11 > 4.0f - _t11) {
            _w0 = 1.0f - alpha;
            _w1 = alpha;
        } else {
            _w0 = _t5;
            _w1 = 1.0f - _t5;
        }
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).fma(FloatVector.broadcast(SIMD_SPECIES, _w0), FloatVector.fromArray(SIMD_SPECIES, target, targetOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _w1))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] slerp_degenerate_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        float _t1 = src[srcOffset + 3] + target[targetOffset + 3];
        float _t2 = src[srcOffset + 2] + target[targetOffset + 2];
        float _t3 = src[srcOffset] + target[targetOffset];
        float _t4 = src[srcOffset + 1] + target[targetOffset + 1];
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, ((_t1) * (_t1) + (((_t2) * (_t2) + (((_t3) * (_t3) + (_t4 * _t4)))))));
        float _w0, _w1;
        if (_t11 > 4.0f - _t11) {
            _w0 = 1.0f - alpha;
            _w1 = alpha;
        } else {
            _w0 = _t5;
            _w1 = 1.0f - _t5;
        }
        FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(FloatVector.fromArray(SIMD_SPECIES, target, targetOffset).mul(FloatVector.broadcast(SIMD_SPECIES, _w1))).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return slerp_fma_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
            return slerp_fma_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return slerp_mulAdd_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerp_mulAdd_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment slerp_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return slerp_fma_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerp_fma_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment slerp_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        float _t2 = UnsafeOpsHolder.U.getFloat(_srcBase + 12L) + UnsafeOpsHolder.U.getFloat(_targetBase + 12L);
        float _t3 = UnsafeOpsHolder.U.getFloat(_srcBase + 8L) + UnsafeOpsHolder.U.getFloat(_targetBase + 8L);
        float _t4 = UnsafeOpsHolder.U.getFloat(_srcBase) + UnsafeOpsHolder.U.getFloat(_targetBase);
        float _t5 = UnsafeOpsHolder.U.getFloat(_srcBase + 4L) + UnsafeOpsHolder.U.getFloat(_targetBase + 4L);
        float _t10 = Math.min(4.0f, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_fma(_t10);
        float _t17 = 2.0f * Math.sinWithCos(_t14);
        float _ct2 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct2 > 2.0E-6f && _ct2 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        float _t20_inv = 1.0f / _ct2;
        return slerp_fma_unsafe_s1ae64b4a_1(dest, destOffset, src, srcOffset, target, targetOffset, alpha, _t17 * _t20_inv, Math.cosWithSin(_t14) - java.lang.Math.fma(0.5f, _t10, -1.0f) * _t17 * _t20_inv);
    }

    /** Piece 2 of {@code slerp_fma_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_fma_unsafe_s1ae64b4a_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha, float _t22, float _t24) {
        float _w0, _w1;
        if (alpha < 0.5f) {
            _w0 = _t24;
            _w1 = _t22;
        } else {
            _w0 = _t22;
            _w1 = _t24;
        }
        slerp_fma_unsafe_s88be4546_v(dest, destOffset, src, srcOffset, target, targetOffset, _w0, _w1);
        return dest;
    }

    private static void slerp_fma_unsafe_s88be4546_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float _w0, float _w1) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.broadcast(SIMD_SPECIES, _w0), FloatVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, _w1))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment slerp_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) + target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 12L);
        float _t3 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) + target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 8L);
        float _t4 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) + target.get(_JAVA_FLOAT_UNALIGNED, targetOffset);
        float _t5 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) + target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 4L);
        float _t10 = Math.min(4.0f, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, java.lang.Math.fma(_t4, _t4, _t5 * _t5))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_fma(_t10);
        float _t17 = 2.0f * Math.sinWithCos(_t14);
        float _ct3 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct3 > 2.0E-6f && _ct3 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        float _t20_inv = 1.0f / _ct3;
        return slerp_fma_api_s516e5d7e_1(dest, destOffset, src, srcOffset, target, targetOffset, alpha, _t17 * _t20_inv, Math.cosWithSin(_t14) - java.lang.Math.fma(0.5f, _t10, -1.0f) * _t17 * _t20_inv);
    }

    /** Piece 2 of {@code slerp_fma_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_fma_api_s516e5d7e_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha, float _t22, float _t24) {
        float _w0, _w1;
        if (alpha < 0.5f) {
            _w0 = _t24;
            _w1 = _t22;
        } else {
            _w0 = _t22;
            _w1 = _t24;
        }
        slerp_fma_unsafe_s88be4546_v(dest, destOffset, src, srcOffset, target, targetOffset, _w0, _w1);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return slerp_mulAdd_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerp_mulAdd_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment slerp_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        float _t2 = UnsafeOpsHolder.U.getFloat(_srcBase + 12L) + UnsafeOpsHolder.U.getFloat(_targetBase + 12L);
        float _t3 = UnsafeOpsHolder.U.getFloat(_srcBase + 8L) + UnsafeOpsHolder.U.getFloat(_targetBase + 8L);
        float _t4 = UnsafeOpsHolder.U.getFloat(_srcBase) + UnsafeOpsHolder.U.getFloat(_targetBase);
        float _t5 = UnsafeOpsHolder.U.getFloat(_srcBase + 4L) + UnsafeOpsHolder.U.getFloat(_targetBase + 4L);
        float _t10 = Math.min(4.0f, ((_t2) * (_t2) + (((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5)))))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_mulAdd(_t10);
        float _t17 = 2.0f * Math.sinWithCos(_t14);
        float _ct5 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct5 > 2.0E-6f && _ct5 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        float _t20_inv = 1.0f / _ct5;
        return slerp_mulAdd_unsafe_sb9e4c857_1(dest, destOffset, src, srcOffset, target, targetOffset, alpha, _t17 * _t20_inv, Math.cosWithSin(_t14) - ((0.5f) * (_t10) - (1.0f)) * _t17 * _t20_inv);
    }

    /** Piece 2 of {@code slerp_mulAdd_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_mulAdd_unsafe_sb9e4c857_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha, float _t22, float _t24) {
        float _w0, _w1;
        if (alpha < 0.5f) {
            _w0 = _t24;
            _w1 = _t22;
        } else {
            _w0 = _t22;
            _w1 = _t24;
        }
        slerp_mulAdd_unsafe_sc958c657_v(dest, destOffset, src, srcOffset, target, targetOffset, _w0, _w1);
        return dest;
    }

    private static void slerp_mulAdd_unsafe_sc958c657_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float _w0, float _w1) {
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(FloatVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, _w1))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment slerp_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) + target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 12L);
        float _t3 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) + target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 8L);
        float _t4 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) + target.get(_JAVA_FLOAT_UNALIGNED, targetOffset);
        float _t5 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) + target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 4L);
        float _t10 = Math.min(4.0f, ((_t2) * (_t2) + (((_t3) * (_t3) + (((_t4) * (_t4) + (_t5 * _t5)))))));
        float _t14 = Math.min(alpha, 1.0f - alpha) * quatArcAngle_mulAdd(_t10);
        float _t17 = 2.0f * Math.sinWithCos(_t14);
        float _ct6 = (float) java.lang.Math.sqrt((4.0f - _t10) * _t10);
        if (!(_ct6 > 2.0E-6f && _ct6 < Float.POSITIVE_INFINITY)) return FloatQuatOpsKernelsSegment.slerp_degenerate(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        float _t20_inv = 1.0f / _ct6;
        return slerp_mulAdd_api_sb0627645_1(dest, destOffset, src, srcOffset, target, targetOffset, alpha, _t17 * _t20_inv, Math.cosWithSin(_t14) - ((0.5f) * (_t10) - (1.0f)) * _t17 * _t20_inv);
    }

    /** Piece 2 of {@code slerp_mulAdd_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment slerp_mulAdd_api_sb0627645_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha, float _t22, float _t24) {
        float _w0, _w1;
        if (alpha < 0.5f) {
            _w0 = _t24;
            _w1 = _t22;
        } else {
            _w0 = _t22;
            _w1 = _t24;
        }
        slerp_mulAdd_unsafe_sc958c657_v(dest, destOffset, src, srcOffset, target, targetOffset, _w0, _w1);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return slerp_degenerate_fma_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
            return slerp_degenerate_fma_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return slerp_degenerate_mulAdd_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerp_degenerate_mulAdd_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return slerp_degenerate_fma_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerp_degenerate_fma_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        float _t1 = UnsafeOpsHolder.U.getFloat(_srcBase + 12L) + UnsafeOpsHolder.U.getFloat(_targetBase + 12L);
        float _t2 = UnsafeOpsHolder.U.getFloat(_srcBase + 8L) + UnsafeOpsHolder.U.getFloat(_targetBase + 8L);
        float _t3 = UnsafeOpsHolder.U.getFloat(_srcBase) + UnsafeOpsHolder.U.getFloat(_targetBase);
        float _t4 = UnsafeOpsHolder.U.getFloat(_srcBase + 4L) + UnsafeOpsHolder.U.getFloat(_targetBase + 4L);
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, _t4 * _t4))));
        float _w0, _w1;
        if (_t11 > 4.0f - _t11) {
            _w0 = 1.0f - alpha;
            _w1 = alpha;
        } else {
            _w0 = _t5;
            _w1 = 1.0f - _t5;
        }
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.broadcast(SIMD_SPECIES, _w0), FloatVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, _w1))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) + target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 12L);
        float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) + target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 8L);
        float _t3 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) + target.get(_JAVA_FLOAT_UNALIGNED, targetOffset);
        float _t4 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) + target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 4L);
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, java.lang.Math.fma(_t1, _t1, java.lang.Math.fma(_t2, _t2, java.lang.Math.fma(_t3, _t3, _t4 * _t4))));
        float _w0, _w1;
        if (_t11 > 4.0f - _t11) {
            _w0 = 1.0f - alpha;
            _w1 = alpha;
        } else {
            _w0 = _t5;
            _w1 = 1.0f - _t5;
        }
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).fma(FloatVector.broadcast(SIMD_SPECIES, _w0), FloatVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, _w1))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return slerp_degenerate_mulAdd_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerp_degenerate_mulAdd_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        float _t1 = UnsafeOpsHolder.U.getFloat(_srcBase + 12L) + UnsafeOpsHolder.U.getFloat(_targetBase + 12L);
        float _t2 = UnsafeOpsHolder.U.getFloat(_srcBase + 8L) + UnsafeOpsHolder.U.getFloat(_targetBase + 8L);
        float _t3 = UnsafeOpsHolder.U.getFloat(_srcBase) + UnsafeOpsHolder.U.getFloat(_targetBase);
        float _t4 = UnsafeOpsHolder.U.getFloat(_srcBase + 4L) + UnsafeOpsHolder.U.getFloat(_targetBase + 4L);
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, ((_t1) * (_t1) + (((_t2) * (_t2) + (((_t3) * (_t3) + (_t4 * _t4)))))));
        float _w0, _w1;
        if (_t11 > 4.0f - _t11) {
            _w0 = 1.0f - alpha;
            _w1 = alpha;
        } else {
            _w0 = _t5;
            _w1 = 1.0f - _t5;
        }
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(FloatVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, _w1))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerp_degenerate_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t1 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L) + target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 12L);
        float _t2 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L) + target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 8L);
        float _t3 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset) + target.get(_JAVA_FLOAT_UNALIGNED, targetOffset);
        float _t4 = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) + target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 4L);
        float _t5 = alpha < 0.5f ? 1.0f : 0.0f;
        float _t11 = Math.min(4.0f, ((_t1) * (_t1) + (((_t2) * (_t2) + (((_t3) * (_t3) + (_t4 * _t4)))))));
        float _w0, _w1;
        if (_t11 > 4.0f - _t11) {
            _w0 = 1.0f - alpha;
            _w1 = alpha;
        } else {
            _w0 = _t5;
            _w1 = 1.0f - _t5;
        }
        FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, _w0)).add(FloatVector.fromMemorySegment(SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, _w1))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] slerpShortest(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        if (SimdSupport.USE_FMA) return slerpShortest_fma(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerpShortest_mulAdd(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static float[] slerpShortest_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        float _t12 = java.lang.Math.fma(src[srcOffset + 3], target[targetOffset + 3], java.lang.Math.fma(src[srcOffset + 2], target[targetOffset + 2], java.lang.Math.fma(src[srcOffset], target[targetOffset], src[srcOffset + 1] * target[targetOffset + 1])));
        float _t15 = Math.min(1.0f, java.lang.Math.abs(_t12));
        float _t18 = alpha * Math.acos(_t15);
        float _t29 = java.lang.Math.fma(-_t15, _t15, 1.0f);
        float _t32 = Math.sinWithCos(_t18) * (1.0f / (float) java.lang.Math.sqrt(_t29));
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, target, targetOffset);
        var _sv2 = (-_t12 > 0.0f ? _sv1.neg() : _sv1);
        var _sv3 = ((float) java.lang.Math.sqrt(_t29) > 0.0f ? _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(-_t32, _t15, Math.cosWithSin(_t18))), FloatVector.broadcast(_SIMD_SPECIES, _t32).mul(_sv2)) : FloatVector.broadcast(_SIMD_SPECIES, alpha).fma(_sv2, _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f - alpha))));
        float _t50 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t50 != 0.0f ? _sv3.mul(FloatVector.broadcast(_SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t50)))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f)).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] slerpShortest_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float alpha) {
        float _t12 = ((src[srcOffset + 3]) * (target[targetOffset + 3]) + (((src[srcOffset + 2]) * (target[targetOffset + 2]) + (((src[srcOffset]) * (target[targetOffset]) + (src[srcOffset + 1] * target[targetOffset + 1]))))));
        float _t15 = Math.min(1.0f, java.lang.Math.abs(_t12));
        float _t18 = alpha * Math.acos(_t15);
        float _t29 = ((1.0f) - (_t15) * (_t15));
        float _t32 = Math.sinWithCos(_t18) * (1.0f / (float) java.lang.Math.sqrt(_t29));
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, target, targetOffset);
        var _sv2 = (-_t12 > 0.0f ? _sv1.neg() : _sv1);
        var _sv3 = ((float) java.lang.Math.sqrt(_t29) > 0.0f ? _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((Math.cosWithSin(_t18)) - (_t32) * (_t15)))).add(FloatVector.broadcast(_SIMD_SPECIES, _t32).mul(_sv2)) : FloatVector.broadcast(_SIMD_SPECIES, alpha).mul(_sv2).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f - alpha))));
        float _t50 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t50 != 0.0f ? _sv3.mul(FloatVector.broadcast(_SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t50)))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerpShortest(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return slerpShortest_fma_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
            return slerpShortest_fma_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return slerpShortest_mulAdd_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerpShortest_mulAdd_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment slerpShortest_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return slerpShortest_fma_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerpShortest_fma_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment slerpShortest_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        float _t12 = java.lang.Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 12L), UnsafeOpsHolder.U.getFloat(_targetBase + 12L), java.lang.Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase + 8L), UnsafeOpsHolder.U.getFloat(_targetBase + 8L), java.lang.Math.fma(UnsafeOpsHolder.U.getFloat(_srcBase), UnsafeOpsHolder.U.getFloat(_targetBase), UnsafeOpsHolder.U.getFloat(_srcBase + 4L) * UnsafeOpsHolder.U.getFloat(_targetBase + 4L))));
        float _t15 = Math.min(1.0f, java.lang.Math.abs(_t12));
        float _t18 = alpha * Math.acos(_t15);
        float _t29 = java.lang.Math.fma(-_t15, _t15, 1.0f);
        slerpShortest_fma_unsafe_s86965de8_v(dest, destOffset, src, srcOffset, target, targetOffset, alpha, _t12, _t15, _t18, _t29, Math.sinWithCos(_t18) * (1.0f / (float) java.lang.Math.sqrt(_t29)));
        return dest;
    }

    private static void slerpShortest_fma_unsafe_s86965de8_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha, float _t12, float _t15, float _t18, float _t29, float _t32) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromMemorySegment(_SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(_SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = (-_t12 > 0.0f ? _sv1.neg() : _sv1);
        var _sv3 = ((float) java.lang.Math.sqrt(_t29) > 0.0f ? _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(-_t32, _t15, Math.cosWithSin(_t18))), FloatVector.broadcast(_SIMD_SPECIES, _t32).mul(_sv2)) : FloatVector.broadcast(_SIMD_SPECIES, alpha).fma(_sv2, _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f - alpha))));
        float _t50 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t50 != 0.0f ? _sv3.mul(FloatVector.broadcast(_SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t50)))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment slerpShortest_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t12 = java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L), target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 12L), java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L), target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 8L), java.lang.Math.fma(src.get(_JAVA_FLOAT_UNALIGNED, srcOffset), target.get(_JAVA_FLOAT_UNALIGNED, targetOffset), src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) * target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 4L))));
        float _t15 = Math.min(1.0f, java.lang.Math.abs(_t12));
        float _t18 = alpha * Math.acos(_t15);
        float _t29 = java.lang.Math.fma(-_t15, _t15, 1.0f);
        slerpShortest_fma_unsafe_s86965de8_v(dest, destOffset, src, srcOffset, target, targetOffset, alpha, _t12, _t15, _t18, _t29, Math.sinWithCos(_t18) * (1.0f / (float) java.lang.Math.sqrt(_t29)));
        return dest;
    }

    public static java.lang.foreign.MemorySegment slerpShortest_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return slerpShortest_mulAdd_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
        return slerpShortest_mulAdd_api(dest, destOffset, src, srcOffset, target, targetOffset, alpha);
    }

    public static java.lang.foreign.MemorySegment slerpShortest_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        long _srcBase = src.address() + srcOffset;
        long _targetBase = target.address() + targetOffset;
        float _t12 = ((UnsafeOpsHolder.U.getFloat(_srcBase + 12L)) * (UnsafeOpsHolder.U.getFloat(_targetBase + 12L)) + (((UnsafeOpsHolder.U.getFloat(_srcBase + 8L)) * (UnsafeOpsHolder.U.getFloat(_targetBase + 8L)) + (((UnsafeOpsHolder.U.getFloat(_srcBase)) * (UnsafeOpsHolder.U.getFloat(_targetBase)) + (UnsafeOpsHolder.U.getFloat(_srcBase + 4L) * UnsafeOpsHolder.U.getFloat(_targetBase + 4L)))))));
        float _t15 = Math.min(1.0f, java.lang.Math.abs(_t12));
        float _t18 = alpha * Math.acos(_t15);
        float _t29 = ((1.0f) - (_t15) * (_t15));
        slerpShortest_mulAdd_unsafe_s55aebe59_v(dest, destOffset, src, srcOffset, target, targetOffset, alpha, _t12, _t15, _t18, _t29, Math.sinWithCos(_t18) * (1.0f / (float) java.lang.Math.sqrt(_t29)));
        return dest;
    }

    private static void slerpShortest_mulAdd_unsafe_s55aebe59_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha, float _t12, float _t15, float _t18, float _t29, float _t32) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromMemorySegment(_SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(_SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = (-_t12 > 0.0f ? _sv1.neg() : _sv1);
        var _sv3 = ((float) java.lang.Math.sqrt(_t29) > 0.0f ? _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((Math.cosWithSin(_t18)) - (_t32) * (_t15)))).add(FloatVector.broadcast(_SIMD_SPECIES, _t32).mul(_sv2)) : FloatVector.broadcast(_SIMD_SPECIES, alpha).mul(_sv2).add(_sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f - alpha))));
        float _t50 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t50 != 0.0f ? _sv3.mul(FloatVector.broadcast(_SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t50)))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment slerpShortest_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float alpha) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _t12 = ((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L)) * (target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 12L)) + (((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L)) * (target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 8L)) + (((src.get(_JAVA_FLOAT_UNALIGNED, srcOffset)) * (target.get(_JAVA_FLOAT_UNALIGNED, targetOffset)) + (src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L) * target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 4L)))))));
        float _t15 = Math.min(1.0f, java.lang.Math.abs(_t12));
        float _t18 = alpha * Math.acos(_t15);
        float _t29 = ((1.0f) - (_t15) * (_t15));
        slerpShortest_mulAdd_unsafe_s55aebe59_v(dest, destOffset, src, srcOffset, target, targetOffset, alpha, _t12, _t15, _t18, _t29, Math.sinWithCos(_t18) * (1.0f / (float) java.lang.Math.sqrt(_t29)));
        return dest;
    }

    public static float[] addScaled(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float weight) {
        if (SimdSupport.USE_FMA) return addScaled_fma(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return addScaled_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    public static float[] addScaled_fma(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float weight) {
        FloatVector.broadcast(SIMD_SPECIES, weight).fma(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset), FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] addScaled_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] other, int otherOffset, float weight) {
        FloatVector.broadcast(SIMD_SPECIES, weight).mul(FloatVector.fromArray(SIMD_SPECIES, other, otherOffset)).add(FloatVector.fromArray(SIMD_SPECIES, src, srcOffset)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float weight) {
        if (SimdSupport.USE_FMA) return addScaled_fma(dest, destOffset, src, srcOffset, other, otherOffset, weight);
        return addScaled_mulAdd(dest, destOffset, src, srcOffset, other, otherOffset, weight);
    }

    public static java.lang.foreign.MemorySegment addScaled_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float weight) {
        FloatVector.broadcast(SIMD_SPECIES, weight).fma(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder()), FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment addScaled_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment other, long otherOffset, float weight) {
        FloatVector.broadcast(SIMD_SPECIES, weight).mul(FloatVector.fromMemorySegment(SIMD_SPECIES, other, otherOffset, java.nio.ByteOrder.nativeOrder())).add(FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder())).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] calculateW(float[] dest, int destOffset, float[] src, int srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src[srcOffset];
            float _selfy = src[srcOffset + 1];
            float _selfz = src[srcOffset + 2];
            FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(3, (float) java.lang.Math.sqrt(Math.max(0.0f, java.lang.Math.fma(-_selfx, _selfx, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(-_selfz, _selfz, 1.0f)))))).intoArray(dest, destOffset);
            return dest;
        } else {
            float _selfx = src[srcOffset];
            float _selfy = src[srcOffset + 1];
            float _selfz = src[srcOffset + 2];
            FloatVector.fromArray(SIMD_SPECIES, src, srcOffset).withLane(3, (float) java.lang.Math.sqrt(Math.max(0.0f, ((((((1.0f) - (_selfz) * (_selfz))) - (_selfy) * (_selfy))) - (_selfx) * (_selfx))))).intoArray(dest, destOffset);
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment calculateW(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return calculateW_unsafe(dest, destOffset, src, srcOffset);
        return calculateW_api(dest, destOffset, src, srcOffset);
    }

    public static java.lang.foreign.MemorySegment calculateW_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            long _srcBase = src.address() + srcOffset;
            float _selfx = UnsafeOpsHolder.U.getFloat(_srcBase);
            float _selfy = UnsafeOpsHolder.U.getFloat(_srcBase + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(_srcBase + 8L);
            FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).withLane(3, (float) java.lang.Math.sqrt(Math.max(0.0f, java.lang.Math.fma(-_selfx, _selfx, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(-_selfz, _selfz, 1.0f)))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            return dest;
        } else {
            long _srcBase = src.address() + srcOffset;
            float _selfx = UnsafeOpsHolder.U.getFloat(_srcBase);
            float _selfy = UnsafeOpsHolder.U.getFloat(_srcBase + 4L);
            float _selfz = UnsafeOpsHolder.U.getFloat(_srcBase + 8L);
            FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).withLane(3, (float) java.lang.Math.sqrt(Math.max(0.0f, ((((((1.0f) - (_selfz) * (_selfz))) - (_selfy) * (_selfy))) - (_selfx) * (_selfx))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment calculateW_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).withLane(3, (float) java.lang.Math.sqrt(Math.max(0.0f, java.lang.Math.fma(-_selfx, _selfx, java.lang.Math.fma(-_selfy, _selfy, java.lang.Math.fma(-_selfz, _selfz, 1.0f)))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            return dest;
        } else {
            float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
            float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
            float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
            FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).withLane(3, (float) java.lang.Math.sqrt(Math.max(0.0f, ((((((1.0f) - (_selfz) * (_selfz))) - (_selfy) * (_selfy))) - (_selfx) * (_selfx))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
            return dest;
        }
    }

    public static float[] exp(float[] dest, int destOffset, float[] src, int srcOffset) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfz = src[srcOffset + 2];
            float _selfx = src[srcOffset];
            float _selfy = src[srcOffset + 1];
            float _t2 = Math.min(Math.exp(src[srcOffset + 3]), 3.4028235E38f);
            float _t4 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
            float _t5 = (float) java.lang.Math.sqrt(_t4);
            (_t4 > 0.0f ? FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).withLane(3, Math.cosWithSin(_t5)).mul(FloatVector.broadcast(_SIMD_SPECIES, _t2 * (Math.sinWithCos(_t5) * (1.0f / (float) java.lang.Math.sqrt(_t4)))).withLane(3, _t2)) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f).withLane(3, Math.cosWithSin(_t5) * _t2)).intoArray(dest, destOffset);
            return dest;
        } else {
            float _selfz = src[srcOffset + 2];
            float _selfx = src[srcOffset];
            float _selfy = src[srcOffset + 1];
            float _t2 = Math.min(Math.exp(src[srcOffset + 3]), 3.4028235E38f);
            float _t4 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
            float _t5 = (float) java.lang.Math.sqrt(_t4);
            (_t4 > 0.0f ? FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).withLane(3, Math.cosWithSin(_t5)).mul(FloatVector.broadcast(_SIMD_SPECIES, _t2 * (Math.sinWithCos(_t5) * (1.0f / (float) java.lang.Math.sqrt(_t4)))).withLane(3, _t2)) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f).withLane(3, Math.cosWithSin(_t5) * _t2)).intoArray(dest, destOffset);
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment exp(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return exp_unsafe(dest, destOffset, src, srcOffset);
        return exp_api(dest, destOffset, src, srcOffset);
    }

    public static java.lang.foreign.MemorySegment exp_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return exp_unsafe_mulAdd(dest, destOffset, src, srcOffset);
        long _srcBase = src.address() + srcOffset;
        float _selfz = UnsafeOpsHolder.U.getFloat(_srcBase + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(_srcBase);
        float _selfy = UnsafeOpsHolder.U.getFloat(_srcBase + 4L);
        float _t2 = Math.min(Math.exp(UnsafeOpsHolder.U.getFloat(_srcBase + 12L)), 3.4028235E38f);
        float _t4 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t5 = (float) java.lang.Math.sqrt(_t4);
        (_t4 > 0.0f ? FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).withLane(3, Math.cosWithSin(_t5)).mul(FloatVector.broadcast(SIMD_SPECIES, _t2 * (Math.sinWithCos(_t5) * (1.0f / (float) java.lang.Math.sqrt(_t4)))).withLane(3, _t2)) : FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, Math.cosWithSin(_t5) * _t2)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    /** {@code exp_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment exp_unsafe_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        float _selfz = UnsafeOpsHolder.U.getFloat(_srcBase + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(_srcBase);
        float _selfy = UnsafeOpsHolder.U.getFloat(_srcBase + 4L);
        float _t2 = Math.min(Math.exp(UnsafeOpsHolder.U.getFloat(_srcBase + 12L)), 3.4028235E38f);
        float _t4 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t5 = (float) java.lang.Math.sqrt(_t4);
        (_t4 > 0.0f ? FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).withLane(3, Math.cosWithSin(_t5)).mul(FloatVector.broadcast(SIMD_SPECIES, _t2 * (Math.sinWithCos(_t5) * (1.0f / (float) java.lang.Math.sqrt(_t4)))).withLane(3, _t2)) : FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, Math.cosWithSin(_t5) * _t2)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment exp_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return exp_api_mulAdd(dest, destOffset, src, srcOffset);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t2 = Math.min(Math.exp(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L)), 3.4028235E38f);
        float _t4 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        float _t5 = (float) java.lang.Math.sqrt(_t4);
        (_t4 > 0.0f ? FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).withLane(3, Math.cosWithSin(_t5)).mul(FloatVector.broadcast(SIMD_SPECIES, _t2 * (Math.sinWithCos(_t5) * (1.0f / (float) java.lang.Math.sqrt(_t4)))).withLane(3, _t2)) : FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, Math.cosWithSin(_t5) * _t2)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    /** {@code exp_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment exp_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t2 = Math.min(Math.exp(src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L)), 3.4028235E38f);
        float _t4 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        float _t5 = (float) java.lang.Math.sqrt(_t4);
        (_t4 > 0.0f ? FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).withLane(3, Math.cosWithSin(_t5)).mul(FloatVector.broadcast(SIMD_SPECIES, _t2 * (Math.sinWithCos(_t5) * (1.0f / (float) java.lang.Math.sqrt(_t4)))).withLane(3, _t2)) : FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, Math.cosWithSin(_t5) * _t2)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] log(float[] dest, int destOffset, float[] src, int srcOffset) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        if (org.joml2.internal.kernels.FmaSwitch.ON) {
            float _selfw = src[srcOffset + 3];
            float _selfz = src[srcOffset + 2];
            float _selfx = src[srcOffset];
            float _selfy = src[srcOffset + 1];
            float _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
            (_t2 > 0.0f ? FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, Math.atan2((float) java.lang.Math.sqrt(_t2), _selfw) * (1.0f / (float) java.lang.Math.sqrt(_t2)))).withLane(3, Math.log((float) java.lang.Math.sqrt(java.lang.Math.fma(_selfw, _selfw, _t2)))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f).withLane(3, Math.log((float) java.lang.Math.sqrt(java.lang.Math.fma(_selfw, _selfw, _t2))))).intoArray(dest, destOffset);
            return dest;
        } else {
            float _selfw = src[srcOffset + 3];
            float _selfz = src[srcOffset + 2];
            float _selfx = src[srcOffset];
            float _selfy = src[srcOffset + 1];
            float _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
            (_t2 > 0.0f ? FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset).mul(FloatVector.broadcast(_SIMD_SPECIES, Math.atan2((float) java.lang.Math.sqrt(_t2), _selfw) * (1.0f / (float) java.lang.Math.sqrt(_t2)))).withLane(3, Math.log((float) java.lang.Math.sqrt(((_selfw) * (_selfw) + (_t2))))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f).withLane(3, Math.log((float) java.lang.Math.sqrt(((_selfw) * (_selfw) + (_t2)))))).intoArray(dest, destOffset);
            return dest;
        }
    }

    public static java.lang.foreign.MemorySegment log(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative()) return log_unsafe(dest, destOffset, src, srcOffset);
        return log_api(dest, destOffset, src, srcOffset);
    }

    public static java.lang.foreign.MemorySegment log_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return log_unsafe_mulAdd(dest, destOffset, src, srcOffset);
        long _srcBase = src.address() + srcOffset;
        float _selfw = UnsafeOpsHolder.U.getFloat(_srcBase + 12L);
        float _selfz = UnsafeOpsHolder.U.getFloat(_srcBase + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(_srcBase);
        float _selfy = UnsafeOpsHolder.U.getFloat(_srcBase + 4L);
        float _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        (_t2 > 0.0f ? FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, Math.atan2((float) java.lang.Math.sqrt(_t2), _selfw) * (1.0f / (float) java.lang.Math.sqrt(_t2)))).withLane(3, Math.log((float) java.lang.Math.sqrt(java.lang.Math.fma(_selfw, _selfw, _t2)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, Math.log((float) java.lang.Math.sqrt(java.lang.Math.fma(_selfw, _selfw, _t2))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    /** {@code log_unsafe} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment log_unsafe_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        long _srcBase = src.address() + srcOffset;
        float _selfw = UnsafeOpsHolder.U.getFloat(_srcBase + 12L);
        float _selfz = UnsafeOpsHolder.U.getFloat(_srcBase + 8L);
        float _selfx = UnsafeOpsHolder.U.getFloat(_srcBase);
        float _selfy = UnsafeOpsHolder.U.getFloat(_srcBase + 4L);
        float _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        (_t2 > 0.0f ? FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, Math.atan2((float) java.lang.Math.sqrt(_t2), _selfw) * (1.0f / (float) java.lang.Math.sqrt(_t2)))).withLane(3, Math.log((float) java.lang.Math.sqrt(((_selfw) * (_selfw) + (_t2))))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, Math.log((float) java.lang.Math.sqrt(((_selfw) * (_selfw) + (_t2)))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment log_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        if (!org.joml2.internal.kernels.FmaSwitch.ON) return log_api_mulAdd(dest, destOffset, src, srcOffset);
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t2 = java.lang.Math.fma(_selfz, _selfz, java.lang.Math.fma(_selfx, _selfx, _selfy * _selfy));
        (_t2 > 0.0f ? FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, Math.atan2((float) java.lang.Math.sqrt(_t2), _selfw) * (1.0f / (float) java.lang.Math.sqrt(_t2)))).withLane(3, Math.log((float) java.lang.Math.sqrt(java.lang.Math.fma(_selfw, _selfw, _t2)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, Math.log((float) java.lang.Math.sqrt(java.lang.Math.fma(_selfw, _selfw, _t2))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    /** {@code log_api} with plain multiply-adds ({@code joml.useFma}); reached only through it. */
    private static java.lang.foreign.MemorySegment log_api_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        float _selfw = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _selfz = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _selfx = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset);
        float _selfy = src.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _t2 = ((_selfz) * (_selfz) + (((_selfx) * (_selfx) + (_selfy * _selfy))));
        (_t2 > 0.0f ? FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder()).mul(FloatVector.broadcast(SIMD_SPECIES, Math.atan2((float) java.lang.Math.sqrt(_t2), _selfw) * (1.0f / (float) java.lang.Math.sqrt(_t2)))).withLane(3, Math.log((float) java.lang.Math.sqrt(((_selfw) * (_selfw) + (_t2))))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, Math.log((float) java.lang.Math.sqrt(((_selfw) * (_selfw) + (_t2)))))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] normalize(float[] dest, int destOffset, float[] src, int srcOffset) {
        var _sv0 = FloatVector.fromArray(SIMD_SPECIES, src, srcOffset);
        float _t3 = _sv0.mul(_sv0).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t3 != 0.0f ? _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t3)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f)).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment normalize(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset) {
        var _sv0 = FloatVector.fromMemorySegment(SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        float _t3 = _sv0.mul(_sv0).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t3 != 0.0f ? _sv0.mul(FloatVector.broadcast(SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t3)))) : FloatVector.broadcast(SIMD_SPECIES, 0.0f)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
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
        float _selfx = src[srcOffset];
        float _targetx = target[targetOffset];
        float _selfy = src[srcOffset + 1];
        float _targety = target[targetOffset + 1];
        float _t8 = -java.lang.Math.fma(_selfw, _targetw, java.lang.Math.fma(_selfz, _targetz, java.lang.Math.fma(_selfx, _targetx, _selfy * _targety)));
        float _t9, _t10, _t11, _t12;
        if (_t8 > 0.0f) {
            _t9 = -_targetw;
            _t10 = -_targetz;
            _t11 = -_targetx;
            _t12 = -_targety;
        } else {
            _t9 = _targetw;
            _t10 = _targetz;
            _t11 = _targetx;
            _t12 = _targety;
        }
        float _t13 = _selfw + _t9;
        float _t14 = _selfz + _t10;
        float _t15 = _selfx + _t11;
        float _t16 = _selfy + _t12;
        float _t17 = _selfw - _t9;
        float _t18 = _selfz - _t10;
        float _t19 = _selfx - _t11;
        float _t20 = _selfy - _t12;
        float _t30 = java.lang.Math.fma(_t15, _t15, _t16 * _t16);
        float _t37 = java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, _t30));
        float _t38 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        return rotateTowards_fma_s8375f417_1(dest, destOffset, src, srcOffset, target, targetOffset, step, _t8, _t13, _t14, _t17, _t18, _t19, _t20, _t30, _t37 * _t38, Math.atan2((float) java.lang.Math.sqrt(_t38), (float) java.lang.Math.sqrt(_t37)));
    }

    /** Piece 2 of {@code rotateTowards_fma}, split to fit the inline budget; reached only through it. */
    private static float[] rotateTowards_fma_s8375f417_1(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float step, float _t8, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30, float _t43, float _t46) {
        float _t49 = 4.0f * _t46;
        float _t55 = _t49 > 0.0f ? Math.min(1.0f, step / _t49) : 0.0f;
        float _t61 = (_t46 + _t46) * _t55;
        rotateTowards_fma_saed59c17_v(dest, destOffset, src, srcOffset, target, targetOffset, _t8, _t13, _t14, _t17, _t18, _t19, _t20, _t30, _t43, _t55, _t61, 2.0f * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t43)));
        return dest;
    }

    private static void rotateTowards_fma_saed59c17_v(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float _t8, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30, float _t43, float _t55, float _t61, float _t65) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, target, targetOffset);
        var _sv2 = (_t8 > 0.0f ? _sv1.neg() : _sv1);
        var _sv3 = (0.5f * (float) java.lang.Math.sqrt(_t43) > 0.0f ? _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(-(0.25f * (_t30 + java.lang.Math.fma(_t14, _t14, _t13 * _t13) + (java.lang.Math.fma(-_t20, _t20, -(_t19 * _t19)) + java.lang.Math.fma(-_t17, _t17, -(_t18 * _t18))))), _t65, Math.cosWithSin(_t61))), FloatVector.broadcast(_SIMD_SPECIES, _t65).mul(_sv2)) : _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, 1.0f - _t55), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t55))));
        float _t86 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t86 != 0.0f ? _sv3.mul(FloatVector.broadcast(_SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t86)))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f)).intoArray(dest, destOffset);
    }

    public static float[] rotateTowards_mulAdd(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float step) {
        float _selfw = src[srcOffset + 3];
        float _targetw = target[targetOffset + 3];
        float _selfz = src[srcOffset + 2];
        float _targetz = target[targetOffset + 2];
        float _selfx = src[srcOffset];
        float _targetx = target[targetOffset];
        float _selfy = src[srcOffset + 1];
        float _targety = target[targetOffset + 1];
        float _t8 = -((_selfw) * (_targetw) + (((_selfz) * (_targetz) + (((_selfx) * (_targetx) + (_selfy * _targety))))));
        float _t9, _t10, _t11, _t12;
        if (_t8 > 0.0f) {
            _t9 = -_targetw;
            _t10 = -_targetz;
            _t11 = -_targetx;
            _t12 = -_targety;
        } else {
            _t9 = _targetw;
            _t10 = _targetz;
            _t11 = _targetx;
            _t12 = _targety;
        }
        float _t13 = _selfw + _t9;
        float _t14 = _selfz + _t10;
        float _t15 = _selfx + _t11;
        float _t16 = _selfy + _t12;
        float _t17 = _selfw - _t9;
        float _t18 = _selfz - _t10;
        float _t19 = _selfx - _t11;
        float _t20 = _selfy - _t12;
        float _t30 = ((_t15) * (_t15) + (_t16 * _t16));
        float _t37 = ((_t13) * (_t13) + (((_t14) * (_t14) + (_t30))));
        float _t38 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t46 = Math.atan2((float) java.lang.Math.sqrt(_t38), (float) java.lang.Math.sqrt(_t37));
        return rotateTowards_mulAdd_s35a7abba_1(dest, destOffset, src, srcOffset, target, targetOffset, step, _t8, _t13, _t14, _t17, _t18, _t19, _t20, _t30, _t37 * _t38, _t46, 4.0f * _t46);
    }

    /** Piece 2 of {@code rotateTowards_mulAdd}, split to fit the inline budget; reached only through it. */
    private static float[] rotateTowards_mulAdd_s35a7abba_1(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float step, float _t8, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30, float _t43, float _t46, float _t49) {
        float _t55 = _t49 > 0.0f ? Math.min(1.0f, step / _t49) : 0.0f;
        float _t61 = (_t46 + _t46) * _t55;
        rotateTowards_mulAdd_sccafa0d6_v(dest, destOffset, src, srcOffset, target, targetOffset, _t8, _t13, _t14, _t17, _t18, _t19, _t20, _t30, _t43, _t55, _t61, 2.0f * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t43)));
        return dest;
    }

    private static void rotateTowards_mulAdd_sccafa0d6_v(float[] dest, int destOffset, float[] src, int srcOffset, float[] target, int targetOffset, float _t8, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30, float _t43, float _t55, float _t61, float _t65) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromArray(_SIMD_SPECIES, src, srcOffset);
        var _sv1 = FloatVector.fromArray(_SIMD_SPECIES, target, targetOffset);
        var _sv2 = (_t8 > 0.0f ? _sv1.neg() : _sv1);
        var _sv3 = (0.5f * (float) java.lang.Math.sqrt(_t43) > 0.0f ? _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((Math.cosWithSin(_t61)) - (0.25f * (_t30 + ((_t14) * (_t14) + (_t13 * _t13)) + (((-_t20) * (_t20) - (_t19 * _t19)) + ((-_t17) * (_t17) - (_t18 * _t18))))) * (_t65)))).add(FloatVector.broadcast(_SIMD_SPECIES, _t65).mul(_sv2)) : _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f - _t55)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t55))));
        float _t86 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t86 != 0.0f ? _sv3.mul(FloatVector.broadcast(_SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t86)))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f)).intoArray(dest, destOffset);
    }

    public static java.lang.foreign.MemorySegment rotateTowards(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float step) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return rotateTowards_fma_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, step);
            return rotateTowards_fma_api(dest, destOffset, src, srcOffset, target, targetOffset, step);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && src.isNative() && target.isNative()) return rotateTowards_mulAdd_unsafe(dest, destOffset, src, srcOffset, target, targetOffset, step);
        return rotateTowards_mulAdd_api(dest, destOffset, src, srcOffset, target, targetOffset, step);
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
        float _selfx = UnsafeOpsHolder.U.getFloat(_srcBase);
        float _targetx = UnsafeOpsHolder.U.getFloat(_targetBase);
        float _selfy = UnsafeOpsHolder.U.getFloat(_srcBase + 4L);
        float _targety = UnsafeOpsHolder.U.getFloat(_targetBase + 4L);
        float _t8 = -java.lang.Math.fma(_selfw, _targetw, java.lang.Math.fma(_selfz, _targetz, java.lang.Math.fma(_selfx, _targetx, _selfy * _targety)));
        float _t9, _t10, _t11, _t12;
        if (_t8 > 0.0f) {
            _t9 = -_targetw;
            _t10 = -_targetz;
            _t11 = -_targetx;
            _t12 = -_targety;
        } else {
            _t9 = _targetw;
            _t10 = _targetz;
            _t11 = _targetx;
            _t12 = _targety;
        }
        float _t13 = _selfw + _t9;
        float _t14 = _selfz + _t10;
        float _t15 = _selfx + _t11;
        float _t16 = _selfy + _t12;
        float _t30 = java.lang.Math.fma(_t15, _t15, _t16 * _t16);
        return rotateTowards_fma_unsafe_sf61b5957_1(dest, destOffset, src, srcOffset, target, targetOffset, step, _t8, _t13, _t14, _selfw - _t9, _selfz - _t10, _selfx - _t11, _selfy - _t12, _t30, java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, _t30)));
    }

    /** Piece 2 of {@code rotateTowards_fma_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_fma_unsafe_sf61b5957_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float step, float _t8, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30, float _t37) {
        float _t38 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t43 = _t37 * _t38;
        float _t46 = Math.atan2((float) java.lang.Math.sqrt(_t38), (float) java.lang.Math.sqrt(_t37));
        float _t49 = 4.0f * _t46;
        float _t55 = _t49 > 0.0f ? Math.min(1.0f, step / _t49) : 0.0f;
        float _t61 = (_t46 + _t46) * _t55;
        rotateTowards_fma_unsafe_s521eaf57_v(dest, destOffset, src, srcOffset, target, targetOffset, _t8, _t13, _t14, _t17, _t18, _t19, _t20, _t30, _t43, _t55, _t61, 2.0f * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t43)));
        return dest;
    }

    private static void rotateTowards_fma_unsafe_s521eaf57_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float _t8, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30, float _t43, float _t55, float _t61, float _t65) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromMemorySegment(_SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(_SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = (_t8 > 0.0f ? _sv1.neg() : _sv1);
        var _sv3 = (0.5f * (float) java.lang.Math.sqrt(_t43) > 0.0f ? _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, java.lang.Math.fma(-(0.25f * (_t30 + java.lang.Math.fma(_t14, _t14, _t13 * _t13) + (java.lang.Math.fma(-_t20, _t20, -(_t19 * _t19)) + java.lang.Math.fma(-_t17, _t17, -(_t18 * _t18))))), _t65, Math.cosWithSin(_t61))), FloatVector.broadcast(_SIMD_SPECIES, _t65).mul(_sv2)) : _sv0.fma(FloatVector.broadcast(_SIMD_SPECIES, 1.0f - _t55), _sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t55))));
        float _t86 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t86 != 0.0f ? _sv3.mul(FloatVector.broadcast(_SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t86)))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment rotateTowards_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float step) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _targetw = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 12L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _targetz = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 8L);
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _targetx = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _targety = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 4L);
        float _t8 = -java.lang.Math.fma(_selfw, _targetw, java.lang.Math.fma(_selfz, _targetz, java.lang.Math.fma(_selfx, _targetx, _selfy * _targety)));
        float _t9, _t10, _t11, _t12;
        if (_t8 > 0.0f) {
            _t9 = -_targetw;
            _t10 = -_targetz;
            _t11 = -_targetx;
            _t12 = -_targety;
        } else {
            _t9 = _targetw;
            _t10 = _targetz;
            _t11 = _targetx;
            _t12 = _targety;
        }
        float _t13 = _selfw + _t9;
        float _t14 = _selfz + _t10;
        float _t15 = _selfx + _t11;
        float _t16 = _selfy + _t12;
        float _t30 = java.lang.Math.fma(_t15, _t15, _t16 * _t16);
        return rotateTowards_fma_api_s102b8d55_1(dest, destOffset, src, srcOffset, target, targetOffset, step, _t8, _t13, _t14, _selfw - _t9, _selfz - _t10, _selfx - _t11, _selfy - _t12, _t30, java.lang.Math.fma(_t13, _t13, java.lang.Math.fma(_t14, _t14, _t30)));
    }

    /** Piece 2 of {@code rotateTowards_fma_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_fma_api_s102b8d55_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float step, float _t8, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30, float _t37) {
        float _t38 = java.lang.Math.fma(_t17, _t17, java.lang.Math.fma(_t18, _t18, java.lang.Math.fma(_t19, _t19, _t20 * _t20)));
        float _t43 = _t37 * _t38;
        float _t46 = Math.atan2((float) java.lang.Math.sqrt(_t38), (float) java.lang.Math.sqrt(_t37));
        float _t49 = 4.0f * _t46;
        float _t55 = _t49 > 0.0f ? Math.min(1.0f, step / _t49) : 0.0f;
        float _t61 = (_t46 + _t46) * _t55;
        rotateTowards_fma_unsafe_s521eaf57_v(dest, destOffset, src, srcOffset, target, targetOffset, _t8, _t13, _t14, _t17, _t18, _t19, _t20, _t30, _t43, _t55, _t61, 2.0f * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t43)));
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
        float _selfx = UnsafeOpsHolder.U.getFloat(_srcBase);
        float _targetx = UnsafeOpsHolder.U.getFloat(_targetBase);
        float _selfy = UnsafeOpsHolder.U.getFloat(_srcBase + 4L);
        float _targety = UnsafeOpsHolder.U.getFloat(_targetBase + 4L);
        float _t8 = -((_selfw) * (_targetw) + (((_selfz) * (_targetz) + (((_selfx) * (_targetx) + (_selfy * _targety))))));
        float _t9, _t10, _t11, _t12;
        if (_t8 > 0.0f) {
            _t9 = -_targetw;
            _t10 = -_targetz;
            _t11 = -_targetx;
            _t12 = -_targety;
        } else {
            _t9 = _targetw;
            _t10 = _targetz;
            _t11 = _targetx;
            _t12 = _targety;
        }
        float _t13 = _selfw + _t9;
        float _t14 = _selfz + _t10;
        float _t15 = _selfx + _t11;
        float _t16 = _selfy + _t12;
        float _t30 = ((_t15) * (_t15) + (_t16 * _t16));
        return rotateTowards_mulAdd_unsafe_s2482556_1(dest, destOffset, src, srcOffset, target, targetOffset, step, _t8, _t13, _t14, _selfw - _t9, _selfz - _t10, _selfx - _t11, _selfy - _t12, _t30, ((_t13) * (_t13) + (((_t14) * (_t14) + (_t30)))));
    }

    /** Piece 2 of {@code rotateTowards_mulAdd_unsafe}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_mulAdd_unsafe_s2482556_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float step, float _t8, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30, float _t37) {
        float _t38 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t43 = _t37 * _t38;
        float _t46 = Math.atan2((float) java.lang.Math.sqrt(_t38), (float) java.lang.Math.sqrt(_t37));
        float _t49 = 4.0f * _t46;
        float _t55 = _t49 > 0.0f ? Math.min(1.0f, step / _t49) : 0.0f;
        float _t61 = (_t46 + _t46) * _t55;
        rotateTowards_mulAdd_unsafe_s89ca0212_v(dest, destOffset, src, srcOffset, target, targetOffset, _t8, _t13, _t14, _t17, _t18, _t19, _t20, _t30, _t43, _t55, _t61, 2.0f * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t43)));
        return dest;
    }

    private static void rotateTowards_mulAdd_unsafe_s89ca0212_v(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float _t8, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30, float _t43, float _t55, float _t61, float _t65) {
        VectorSpecies<Float> _SIMD_SPECIES = SIMD_SPECIES;
        var _sv0 = FloatVector.fromMemorySegment(_SIMD_SPECIES, src, srcOffset, java.nio.ByteOrder.nativeOrder());
        var _sv1 = FloatVector.fromMemorySegment(_SIMD_SPECIES, target, targetOffset, java.nio.ByteOrder.nativeOrder());
        var _sv2 = (_t8 > 0.0f ? _sv1.neg() : _sv1);
        var _sv3 = (0.5f * (float) java.lang.Math.sqrt(_t43) > 0.0f ? _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, ((Math.cosWithSin(_t61)) - (0.25f * (_t30 + ((_t14) * (_t14) + (_t13 * _t13)) + (((-_t20) * (_t20) - (_t19 * _t19)) + ((-_t17) * (_t17) - (_t18 * _t18))))) * (_t65)))).add(FloatVector.broadcast(_SIMD_SPECIES, _t65).mul(_sv2)) : _sv0.mul(FloatVector.broadcast(_SIMD_SPECIES, 1.0f - _t55)).add(_sv2.mul(FloatVector.broadcast(_SIMD_SPECIES, _t55))));
        float _t86 = _sv3.mul(_sv3).reduceLanes(jdk.incubator.vector.VectorOperators.ADD);
        (_t86 != 0.0f ? _sv3.mul(FloatVector.broadcast(_SIMD_SPECIES, (1.0f / (float) java.lang.Math.sqrt(_t86)))) : FloatVector.broadcast(_SIMD_SPECIES, 0.0f)).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
    }

    public static java.lang.foreign.MemorySegment rotateTowards_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float step) {
        java.lang.foreign.ValueLayout.OfFloat _JAVA_FLOAT_UNALIGNED = java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED;
        float _selfw = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 12L);
        float _targetw = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 12L);
        float _selfz = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 8L);
        float _targetz = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 8L);
        float _selfx = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset);
        float _targetx = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset);
        float _selfy = src.get(_JAVA_FLOAT_UNALIGNED, srcOffset + 4L);
        float _targety = target.get(_JAVA_FLOAT_UNALIGNED, targetOffset + 4L);
        float _t8 = -((_selfw) * (_targetw) + (((_selfz) * (_targetz) + (((_selfx) * (_targetx) + (_selfy * _targety))))));
        float _t9, _t10, _t11, _t12;
        if (_t8 > 0.0f) {
            _t9 = -_targetw;
            _t10 = -_targetz;
            _t11 = -_targetx;
            _t12 = -_targety;
        } else {
            _t9 = _targetw;
            _t10 = _targetz;
            _t11 = _targetx;
            _t12 = _targety;
        }
        float _t13 = _selfw + _t9;
        float _t14 = _selfz + _t10;
        float _t15 = _selfx + _t11;
        float _t16 = _selfy + _t12;
        float _t30 = ((_t15) * (_t15) + (_t16 * _t16));
        return rotateTowards_mulAdd_api_se0d120ea_1(dest, destOffset, src, srcOffset, target, targetOffset, step, _t8, _t13, _t14, _selfw - _t9, _selfz - _t10, _selfx - _t11, _selfy - _t12, _t30, ((_t13) * (_t13) + (((_t14) * (_t14) + (_t30)))));
    }

    /** Piece 2 of {@code rotateTowards_mulAdd_api}, split to fit the inline budget; reached only through it. */
    private static java.lang.foreign.MemorySegment rotateTowards_mulAdd_api_se0d120ea_1(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment src, long srcOffset, java.lang.foreign.MemorySegment target, long targetOffset, float step, float _t8, float _t13, float _t14, float _t17, float _t18, float _t19, float _t20, float _t30, float _t37) {
        float _t38 = ((_t17) * (_t17) + (((_t18) * (_t18) + (((_t19) * (_t19) + (_t20 * _t20))))));
        float _t43 = _t37 * _t38;
        float _t46 = Math.atan2((float) java.lang.Math.sqrt(_t38), (float) java.lang.Math.sqrt(_t37));
        float _t49 = 4.0f * _t46;
        float _t55 = _t49 > 0.0f ? Math.min(1.0f, step / _t49) : 0.0f;
        float _t61 = (_t46 + _t46) * _t55;
        rotateTowards_mulAdd_unsafe_s89ca0212_v(dest, destOffset, src, srcOffset, target, targetOffset, _t8, _t13, _t14, _t17, _t18, _t19, _t20, _t30, _t43, _t55, _t61, 2.0f * Math.sinWithCos(_t61) * (1.0f / (float) java.lang.Math.sqrt(_t43)));
        return dest;
    }

    public static float[] makeRotationAxis(float[] dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        if (SimdSupport.USE_FMA) return makeRotationAxis_fma(dest, destOffset, angle, axisX, axisY, axisZ);
        return makeRotationAxis_mulAdd(dest, destOffset, angle, axisX, axisY, axisZ);
    }

    public static float[] makeRotationAxis_fma(float[] dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = 0.5f * angle;
        FloatVector.zero(SIMD_SPECIES).withLane(0, axisX).withLane(1, axisY).withLane(2, axisZ).fma(FloatVector.broadcast(SIMD_SPECIES, Math.sinWithCos(_t0)), FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, Math.cosWithSin(_t0))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] makeRotationAxis_mulAdd(float[] dest, int destOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = 0.5f * angle;
        FloatVector.zero(SIMD_SPECIES).withLane(0, axisX).withLane(1, axisY).withLane(2, axisZ).mul(FloatVector.broadcast(SIMD_SPECIES, Math.sinWithCos(_t0))).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, Math.cosWithSin(_t0))).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis(java.lang.foreign.MemorySegment dest, long destOffset, float angle, float axisX, float axisY, float axisZ) {
        if (SimdSupport.USE_FMA) return makeRotationAxis_fma(dest, destOffset, angle, axisX, axisY, axisZ);
        return makeRotationAxis_mulAdd(dest, destOffset, angle, axisX, axisY, axisZ);
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_fma(java.lang.foreign.MemorySegment dest, long destOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = 0.5f * angle;
        FloatVector.zero(SIMD_SPECIES).withLane(0, axisX).withLane(1, axisY).withLane(2, axisZ).fma(FloatVector.broadcast(SIMD_SPECIES, Math.sinWithCos(_t0)), FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, Math.cosWithSin(_t0))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float angle, float axisX, float axisY, float axisZ) {
        float _t0 = 0.5f * angle;
        FloatVector.zero(SIMD_SPECIES).withLane(0, axisX).withLane(1, axisY).withLane(2, axisZ).mul(FloatVector.broadcast(SIMD_SPECIES, Math.sinWithCos(_t0))).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, Math.cosWithSin(_t0))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static float[] makeRotationAxis(float[] dest, int destOffset, float[] axis, int axisOffset, float angle) {
        if (SimdSupport.USE_FMA) return makeRotationAxis_fma(dest, destOffset, axis, axisOffset, angle);
        return makeRotationAxis_mulAdd(dest, destOffset, axis, axisOffset, angle);
    }

    public static float[] makeRotationAxis_fma(float[] dest, int destOffset, float[] axis, int axisOffset, float angle) {
        float _t0 = 0.5f * angle;
        FloatVector.zero(SIMD_SPECIES).withLane(0, axis[axisOffset]).withLane(1, axis[axisOffset + 1]).withLane(2, axis[axisOffset + 2]).fma(FloatVector.broadcast(SIMD_SPECIES, Math.sinWithCos(_t0)), FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, Math.cosWithSin(_t0))).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] makeRotationAxis_mulAdd(float[] dest, int destOffset, float[] axis, int axisOffset, float angle) {
        float _t0 = 0.5f * angle;
        FloatVector.zero(SIMD_SPECIES).withLane(0, axis[axisOffset]).withLane(1, axis[axisOffset + 1]).withLane(2, axis[axisOffset + 2]).mul(FloatVector.broadcast(SIMD_SPECIES, Math.sinWithCos(_t0))).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, Math.cosWithSin(_t0))).intoArray(dest, destOffset);
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        if (SimdSupport.USE_FMA) {
            if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && axis.isNative()) return makeRotationAxis_fma_unsafe(dest, destOffset, axis, axisOffset, angle);
            return makeRotationAxis_fma_api(dest, destOffset, axis, axisOffset, angle);
        }
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && axis.isNative()) return makeRotationAxis_mulAdd_unsafe(dest, destOffset, axis, axisOffset, angle);
        return makeRotationAxis_mulAdd_api(dest, destOffset, axis, axisOffset, angle);
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_fma(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && axis.isNative()) return makeRotationAxis_fma_unsafe(dest, destOffset, axis, axisOffset, angle);
        return makeRotationAxis_fma_api(dest, destOffset, axis, axisOffset, angle);
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_fma_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        long _axisBase = axis.address() + axisOffset;
        float _t0 = 0.5f * angle;
        FloatVector.zero(SIMD_SPECIES).withLane(0, UnsafeOpsHolder.U.getFloat(_axisBase)).withLane(1, UnsafeOpsHolder.U.getFloat(_axisBase + 4L)).withLane(2, UnsafeOpsHolder.U.getFloat(_axisBase + 8L)).fma(FloatVector.broadcast(SIMD_SPECIES, Math.sinWithCos(_t0)), FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, Math.cosWithSin(_t0))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_fma_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        float _t0 = 0.5f * angle;
        FloatVector.zero(SIMD_SPECIES).withLane(0, axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset)).withLane(1, axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 4L)).withLane(2, axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 8L)).fma(FloatVector.broadcast(SIMD_SPECIES, Math.sinWithCos(_t0)), FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, Math.cosWithSin(_t0))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        if (Joml.storeLoadBackend() == StoreLoadBackend.UNSAFE && axis.isNative()) return makeRotationAxis_mulAdd_unsafe(dest, destOffset, axis, axisOffset, angle);
        return makeRotationAxis_mulAdd_api(dest, destOffset, axis, axisOffset, angle);
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_mulAdd_unsafe(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        long _axisBase = axis.address() + axisOffset;
        float _t0 = 0.5f * angle;
        FloatVector.zero(SIMD_SPECIES).withLane(0, UnsafeOpsHolder.U.getFloat(_axisBase)).withLane(1, UnsafeOpsHolder.U.getFloat(_axisBase + 4L)).withLane(2, UnsafeOpsHolder.U.getFloat(_axisBase + 8L)).mul(FloatVector.broadcast(SIMD_SPECIES, Math.sinWithCos(_t0))).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, Math.cosWithSin(_t0))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationAxis_mulAdd_api(java.lang.foreign.MemorySegment dest, long destOffset, java.lang.foreign.MemorySegment axis, long axisOffset, float angle) {
        float _t0 = 0.5f * angle;
        FloatVector.zero(SIMD_SPECIES).withLane(0, axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset)).withLane(1, axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 4L)).withLane(2, axis.get(java.lang.foreign.ValueLayout.JAVA_FLOAT_UNALIGNED, axisOffset + 8L)).mul(FloatVector.broadcast(SIMD_SPECIES, Math.sinWithCos(_t0))).add(FloatVector.broadcast(SIMD_SPECIES, 0.0f).withLane(3, Math.cosWithSin(_t0))).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
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
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t1);
        float _t7 = Math.cosWithSin(_t0);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t4;
        float _t12 = _t3 * _t6;
        FloatVector.zero(SIMD_SPECIES).withLane(0, _t9).withLane(1, _t10).withLane(3, _t11).fma(FloatVector.broadcast(SIMD_SPECIES, _t5), FloatVector.broadcast(SIMD_SPECIES, _t10).withLane(3, _t12).mul(FloatVector.broadcast(SIMD_SPECIES, _t8)).withLane(1, -(_t9 * _t8))).withLane(2, _t11 * _t8 - _t12 * _t5).intoArray(dest, destOffset);
        return dest;
    }

    public static float[] makeRotationYXZ_mulAdd(float[] dest, int destOffset, float angleY, float angleX, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t1);
        float _t7 = Math.cosWithSin(_t0);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t4;
        float _t12 = _t3 * _t6;
        FloatVector.zero(SIMD_SPECIES).withLane(0, _t9).withLane(1, _t10).withLane(3, _t11).mul(FloatVector.broadcast(SIMD_SPECIES, _t5)).add(FloatVector.broadcast(SIMD_SPECIES, _t10).withLane(3, _t12).mul(FloatVector.broadcast(SIMD_SPECIES, _t8)).withLane(1, -(_t9 * _t8))).withLane(2, _t11 * _t8 - _t12 * _t5).intoArray(dest, destOffset);
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
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t1);
        float _t7 = Math.cosWithSin(_t0);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t4;
        float _t12 = _t3 * _t6;
        FloatVector.zero(SIMD_SPECIES).withLane(0, _t9).withLane(1, _t10).withLane(3, _t11).fma(FloatVector.broadcast(SIMD_SPECIES, _t5), FloatVector.broadcast(SIMD_SPECIES, _t10).withLane(3, _t12).mul(FloatVector.broadcast(SIMD_SPECIES, _t8)).withLane(1, -(_t9 * _t8))).withLane(2, _t11 * _t8 - _t12 * _t5).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
        return dest;
    }

    public static java.lang.foreign.MemorySegment makeRotationYXZ_mulAdd(java.lang.foreign.MemorySegment dest, long destOffset, float angleY, float angleX, float angleZ) {
        float _t0 = 0.5f * angleX;
        float _t1 = 0.5f * angleY;
        float _t2 = 0.5f * angleZ;
        float _t3 = Math.sinWithCos(_t0);
        float _t4 = Math.cosWithSin(_t1);
        float _t5 = Math.cosWithSin(_t2);
        float _t6 = Math.sinWithCos(_t1);
        float _t7 = Math.cosWithSin(_t0);
        float _t8 = Math.sinWithCos(_t2);
        float _t9 = _t3 * _t4;
        float _t10 = _t6 * _t7;
        float _t11 = _t7 * _t4;
        float _t12 = _t3 * _t6;
        FloatVector.zero(SIMD_SPECIES).withLane(0, _t9).withLane(1, _t10).withLane(3, _t11).mul(FloatVector.broadcast(SIMD_SPECIES, _t5)).add(FloatVector.broadcast(SIMD_SPECIES, _t10).withLane(3, _t12).mul(FloatVector.broadcast(SIMD_SPECIES, _t8)).withLane(1, -(_t9 * _t8))).withLane(2, _t11 * _t8 - _t12 * _t5).intoMemorySegment(dest, destOffset, java.nio.ByteOrder.nativeOrder());
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
            copyArrSeg_one(dest, destOffset, _srcSeg, srcOffset * 4L);
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
            copyArrSeg(dest, destOffset, _srcSeg, srcOffset * 4L, count * 4);
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
            copySegArr_one(_destSeg, destOffset * 4L, src, srcOffset);
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
            copySegArr(_destSeg, destOffset * 4L, src, srcOffset, count * 4);
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
                copyArrSeg_one(_destArr, _destOff, _srcSeg, srcOffset * 4L);
            }
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            if (src.hasArray() && srcOffset >= 0 && srcOffset <= src.limit() - 4) {
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
        if (dest.hasArray() && destOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && destOffset <= dest.limit() - (count > 536870911 ? -1 : count * 4)) {
            float[] _destArr = dest.array();
            int _destOff = dest.arrayOffset() + destOffset;
            if (src.hasArray() && srcOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && srcOffset <= src.limit() - (count > 536870911 ? -1 : count * 4)) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copyArrArr(_destArr, _destOff, _srcArr, _srcOff, count * 4);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copyArrSeg(_destArr, _destOff, _srcSeg, srcOffset * 4L, count * 4);
            }
        } else {
            java.lang.foreign.MemorySegment _destSeg = java.lang.foreign.MemorySegment.ofBuffer(dest.duplicate().position(0));
            if (src.hasArray() && srcOffset >= 0 && (count > 536870911 ? -1 : count * 4) >= 0 && srcOffset <= src.limit() - (count > 536870911 ? -1 : count * 4)) {
                float[] _srcArr = src.array();
                int _srcOff = src.arrayOffset() + srcOffset;
                copySegArr(_destSeg, destOffset * 4L, _srcArr, _srcOff, count * 4);
            } else {
                java.lang.foreign.MemorySegment _srcSeg = java.lang.foreign.MemorySegment.ofBuffer(src.duplicate().position(0));
                copySegSeg(_destSeg, destOffset * 4L, _srcSeg, srcOffset * 4L, count * 4);
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
            copySegSeg_one(_destSeg, destOffset * 4L, _srcSeg, srcOffset);
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
            copySegSeg(_destSeg, destOffset * 4L, _srcSeg, srcOffset, count * 4);
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
            copySegSeg_one(_destSeg, destOffset * 4L, src, srcOffset);
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
            copySegSeg(_destSeg, destOffset * 4L, src, srcOffset, count * 4);
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
            copySegSeg_one(_destSeg, destOffset, _srcSeg, srcOffset * 4L);
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
            copySegSeg(_destSeg, destOffset, _srcSeg, srcOffset * 4L, count * 4);
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
            copySegSeg_one(dest, destOffset, _srcSeg, srcOffset * 4L);
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
            copySegSeg(dest, destOffset, _srcSeg, srcOffset * 4L, count * 4);
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

    /** cos(k pi/8) and sin(k pi/8), k = 0..4: the float quatArcAngle's rotations. */
    private static final double[] QUAT_ARC_ANGLE_COS = new double[] {1.0, 0x1.d906bcf328d46p-1, 0x1.6a09e667f3bcdp-1, 0x1.87de2a6aea963p-2, 0.0};
    private static final double[] QUAT_ARC_ANGLE_SIN = new double[] {0.0, 0x1.87de2a6aea963p-2, 0x1.6a09e667f3bcdp-1, 0x1.d906bcf328d46p-1, 1.0};

    /**
     * The angle between two unit quaternions a and b from s = |a+b|^2, clamped to [0, 4]. With
     * |a-b|^2 = 4 - s and half the angle in [0, pi/2], that half is k pi/8 + asin(|a-b|/2
     * cos(k pi/8) - |a+b|/2 sin(k pi/8)) for the k (four comparisons of 4 - s) that keeps the
     * argument within sin(pi/16): there the arcsine's odd Taylor polynomial to x^11 (3e-10 of it
     * left out), in double, is exact to well below a float ulp. Within 0.5005 ulp of the angle,
     * and no call, where java.lang.Math.asin is one: slerp is 15-24% faster.
     */
    private static float quatArcAngle_fma(float s) {
        double d = 4.0 - s, sd = java.lang.Math.sqrt(d), ss = java.lang.Math.sqrt(s);
        int k = (d > 0x1.37ca1866b95cfp-3 ? 1 : 0) + (d > 0x1.3c10eaca8ab4fp+0 ? 1 : 0)
                + (d > 0x1.61f78a9abaa59p+1 ? 1 : 0) + (d > 0x1.ec835e79946a3p+1 ? 1 : 0);
        double x = 0.5 * java.lang.Math.fma(sd, QUAT_ARC_ANGLE_COS[k], -ss * QUAT_ARC_ANGLE_SIN[k]), t = x * x;
        double p = java.lang.Math.fma(t, java.lang.Math.fma(t, java.lang.Math.fma(t, java.lang.Math.fma(t, 0x1.6e8ba2e8ba2e9p-6, 0x1.f1c71c71c71c7p-6),
                0x1.6db6db6db6db7p-5), 0x1.3333333333333p-4), 0x1.5555555555555p-3);
        return (float) (k * (Math.PI / 4) + 2 * java.lang.Math.fma(x * t, p, x));
    }

    /**
     * The angle between two unit quaternions a and b from s = |a+b|^2, clamped to [0, 4]. With
     * |a-b|^2 = 4 - s and half the angle in [0, pi/2], that half is k pi/8 + asin(|a-b|/2
     * cos(k pi/8) - |a+b|/2 sin(k pi/8)) for the k (four comparisons of 4 - s) that keeps the
     * argument within sin(pi/16): there the arcsine's odd Taylor polynomial to x^11 (3e-10 of it
     * left out), in double, is exact to well below a float ulp. Within 0.5005 ulp of the angle,
     * and no call, where java.lang.Math.asin is one: slerp is 15-24% faster.
     */
    private static float quatArcAngle_mulAdd(float s) {
        double d = 4.0 - s, sd = java.lang.Math.sqrt(d), ss = java.lang.Math.sqrt(s);
        int k = (d > 0x1.37ca1866b95cfp-3 ? 1 : 0) + (d > 0x1.3c10eaca8ab4fp+0 ? 1 : 0)
                + (d > 0x1.61f78a9abaa59p+1 ? 1 : 0) + (d > 0x1.ec835e79946a3p+1 ? 1 : 0);
        double x = 0.5 * ((sd) * (QUAT_ARC_ANGLE_COS[k]) - (ss * QUAT_ARC_ANGLE_SIN[k])), t = x * x;
        double p = ((t) * (((t) * (((t) * (((t) * (0x1.6e8ba2e8ba2e9p-6) + (0x1.f1c71c71c71c7p-6))) + (0x1.6db6db6db6db7p-5))) + (0x1.3333333333333p-4))) + (0x1.5555555555555p-3));
        return (float) (k * (Math.PI / 4) + 2 * ((x * t) * (p) + (x)));
    }
}
