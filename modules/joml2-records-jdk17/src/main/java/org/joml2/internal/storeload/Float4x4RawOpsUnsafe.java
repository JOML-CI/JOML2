// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float4x4RawOpsUnsafe extends Float4x4RawOps {
    public Float4x4 storeCMUnsafe(Float4x4 self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4L, self.m10());
        _U.putFloat(address + 8L, self.m20());
        _U.putFloat(address + 12L, self.m30());
        _U.putFloat(address + 16L, self.m01());
        _U.putFloat(address + 20L, self.m11());
        _U.putFloat(address + 24L, self.m21());
        _U.putFloat(address + 28L, self.m31());
        _U.putFloat(address + 32L, self.m02());
        _U.putFloat(address + 36L, self.m12());
        _U.putFloat(address + 40L, self.m22());
        _U.putFloat(address + 44L, self.m32());
        _U.putFloat(address + 48L, self.m03());
        _U.putFloat(address + 52L, self.m13());
        _U.putFloat(address + 56L, self.m23());
        _U.putFloat(address + 60L, self.m33());
        return self;
    }

    public Float4x4 storeCMHeap(Float4x4 self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00());
        _U.putFloat(bytes, address + 4L, self.m10());
        _U.putFloat(bytes, address + 8L, self.m20());
        _U.putFloat(bytes, address + 12L, self.m30());
        _U.putFloat(bytes, address + 16L, self.m01());
        _U.putFloat(bytes, address + 20L, self.m11());
        _U.putFloat(bytes, address + 24L, self.m21());
        _U.putFloat(bytes, address + 28L, self.m31());
        _U.putFloat(bytes, address + 32L, self.m02());
        _U.putFloat(bytes, address + 36L, self.m12());
        _U.putFloat(bytes, address + 40L, self.m22());
        _U.putFloat(bytes, address + 44L, self.m32());
        _U.putFloat(bytes, address + 48L, self.m03());
        _U.putFloat(bytes, address + 52L, self.m13());
        _U.putFloat(bytes, address + 56L, self.m23());
        _U.putFloat(bytes, address + 60L, self.m33());
        return self;
    }

    public Float4x4 loadCMUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(address);
        float _c4 = _U.getFloat(address + 4L);
        float _c8 = _U.getFloat(address + 8L);
        float _c12 = _U.getFloat(address + 12L);
        float _c1 = _U.getFloat(address + 16L);
        float _c5 = _U.getFloat(address + 20L);
        float _c9 = _U.getFloat(address + 24L);
        float _c13 = _U.getFloat(address + 28L);
        float _c2 = _U.getFloat(address + 32L);
        float _c6 = _U.getFloat(address + 36L);
        float _c10 = _U.getFloat(address + 40L);
        float _c14 = _U.getFloat(address + 44L);
        float _c3 = _U.getFloat(address + 48L);
        float _c7 = _U.getFloat(address + 52L);
        float _c11 = _U.getFloat(address + 56L);
        float _c15 = _U.getFloat(address + 60L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Float4x4 loadCMHeap(byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(bytes, address);
        float _c4 = _U.getFloat(bytes, address + 4L);
        float _c8 = _U.getFloat(bytes, address + 8L);
        float _c12 = _U.getFloat(bytes, address + 12L);
        float _c1 = _U.getFloat(bytes, address + 16L);
        float _c5 = _U.getFloat(bytes, address + 20L);
        float _c9 = _U.getFloat(bytes, address + 24L);
        float _c13 = _U.getFloat(bytes, address + 28L);
        float _c2 = _U.getFloat(bytes, address + 32L);
        float _c6 = _U.getFloat(bytes, address + 36L);
        float _c10 = _U.getFloat(bytes, address + 40L);
        float _c14 = _U.getFloat(bytes, address + 44L);
        float _c3 = _U.getFloat(bytes, address + 48L);
        float _c7 = _U.getFloat(bytes, address + 52L);
        float _c11 = _U.getFloat(bytes, address + 56L);
        float _c15 = _U.getFloat(bytes, address + 60L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Float4x4 storeCMDoubleUnsafe(Float4x4 self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m10());
        _U.putDouble(address + 16L, self.m20());
        _U.putDouble(address + 24L, self.m30());
        _U.putDouble(address + 32L, self.m01());
        _U.putDouble(address + 40L, self.m11());
        _U.putDouble(address + 48L, self.m21());
        _U.putDouble(address + 56L, self.m31());
        _U.putDouble(address + 64L, self.m02());
        _U.putDouble(address + 72L, self.m12());
        _U.putDouble(address + 80L, self.m22());
        _U.putDouble(address + 88L, self.m32());
        _U.putDouble(address + 96L, self.m03());
        _U.putDouble(address + 104L, self.m13());
        _U.putDouble(address + 112L, self.m23());
        _U.putDouble(address + 120L, self.m33());
        return self;
    }

    public Float4x4 storeCMDoubleHeap(Float4x4 self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8L, self.m10());
        _U.putDouble(bytes, address + 16L, self.m20());
        _U.putDouble(bytes, address + 24L, self.m30());
        _U.putDouble(bytes, address + 32L, self.m01());
        _U.putDouble(bytes, address + 40L, self.m11());
        _U.putDouble(bytes, address + 48L, self.m21());
        _U.putDouble(bytes, address + 56L, self.m31());
        _U.putDouble(bytes, address + 64L, self.m02());
        _U.putDouble(bytes, address + 72L, self.m12());
        _U.putDouble(bytes, address + 80L, self.m22());
        _U.putDouble(bytes, address + 88L, self.m32());
        _U.putDouble(bytes, address + 96L, self.m03());
        _U.putDouble(bytes, address + 104L, self.m13());
        _U.putDouble(bytes, address + 112L, self.m23());
        _U.putDouble(bytes, address + 120L, self.m33());
        return self;
    }

    public Float4x4 loadCMDoubleUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(address);
        float _c4 = (float) _U.getDouble(address + 8L);
        float _c8 = (float) _U.getDouble(address + 16L);
        float _c12 = (float) _U.getDouble(address + 24L);
        float _c1 = (float) _U.getDouble(address + 32L);
        float _c5 = (float) _U.getDouble(address + 40L);
        float _c9 = (float) _U.getDouble(address + 48L);
        float _c13 = (float) _U.getDouble(address + 56L);
        float _c2 = (float) _U.getDouble(address + 64L);
        float _c6 = (float) _U.getDouble(address + 72L);
        float _c10 = (float) _U.getDouble(address + 80L);
        float _c14 = (float) _U.getDouble(address + 88L);
        float _c3 = (float) _U.getDouble(address + 96L);
        float _c7 = (float) _U.getDouble(address + 104L);
        float _c11 = (float) _U.getDouble(address + 112L);
        float _c15 = (float) _U.getDouble(address + 120L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Float4x4 loadCMDoubleHeap(byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(bytes, address);
        float _c4 = (float) _U.getDouble(bytes, address + 8L);
        float _c8 = (float) _U.getDouble(bytes, address + 16L);
        float _c12 = (float) _U.getDouble(bytes, address + 24L);
        float _c1 = (float) _U.getDouble(bytes, address + 32L);
        float _c5 = (float) _U.getDouble(bytes, address + 40L);
        float _c9 = (float) _U.getDouble(bytes, address + 48L);
        float _c13 = (float) _U.getDouble(bytes, address + 56L);
        float _c2 = (float) _U.getDouble(bytes, address + 64L);
        float _c6 = (float) _U.getDouble(bytes, address + 72L);
        float _c10 = (float) _U.getDouble(bytes, address + 80L);
        float _c14 = (float) _U.getDouble(bytes, address + 88L);
        float _c3 = (float) _U.getDouble(bytes, address + 96L);
        float _c7 = (float) _U.getDouble(bytes, address + 104L);
        float _c11 = (float) _U.getDouble(bytes, address + 112L);
        float _c15 = (float) _U.getDouble(bytes, address + 120L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Float4x4 storeRMUnsafe(Float4x4 self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4L, self.m01());
        _U.putFloat(address + 8L, self.m02());
        _U.putFloat(address + 12L, self.m03());
        _U.putFloat(address + 16L, self.m10());
        _U.putFloat(address + 20L, self.m11());
        _U.putFloat(address + 24L, self.m12());
        _U.putFloat(address + 28L, self.m13());
        _U.putFloat(address + 32L, self.m20());
        _U.putFloat(address + 36L, self.m21());
        _U.putFloat(address + 40L, self.m22());
        _U.putFloat(address + 44L, self.m23());
        _U.putFloat(address + 48L, self.m30());
        _U.putFloat(address + 52L, self.m31());
        _U.putFloat(address + 56L, self.m32());
        _U.putFloat(address + 60L, self.m33());
        return self;
    }

    public Float4x4 storeRMHeap(Float4x4 self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00());
        _U.putFloat(bytes, address + 4L, self.m01());
        _U.putFloat(bytes, address + 8L, self.m02());
        _U.putFloat(bytes, address + 12L, self.m03());
        _U.putFloat(bytes, address + 16L, self.m10());
        _U.putFloat(bytes, address + 20L, self.m11());
        _U.putFloat(bytes, address + 24L, self.m12());
        _U.putFloat(bytes, address + 28L, self.m13());
        _U.putFloat(bytes, address + 32L, self.m20());
        _U.putFloat(bytes, address + 36L, self.m21());
        _U.putFloat(bytes, address + 40L, self.m22());
        _U.putFloat(bytes, address + 44L, self.m23());
        _U.putFloat(bytes, address + 48L, self.m30());
        _U.putFloat(bytes, address + 52L, self.m31());
        _U.putFloat(bytes, address + 56L, self.m32());
        _U.putFloat(bytes, address + 60L, self.m33());
        return self;
    }

    public Float4x4 loadRMUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(address);
        float _c1 = _U.getFloat(address + 4L);
        float _c2 = _U.getFloat(address + 8L);
        float _c3 = _U.getFloat(address + 12L);
        float _c4 = _U.getFloat(address + 16L);
        float _c5 = _U.getFloat(address + 20L);
        float _c6 = _U.getFloat(address + 24L);
        float _c7 = _U.getFloat(address + 28L);
        float _c8 = _U.getFloat(address + 32L);
        float _c9 = _U.getFloat(address + 36L);
        float _c10 = _U.getFloat(address + 40L);
        float _c11 = _U.getFloat(address + 44L);
        float _c12 = _U.getFloat(address + 48L);
        float _c13 = _U.getFloat(address + 52L);
        float _c14 = _U.getFloat(address + 56L);
        float _c15 = _U.getFloat(address + 60L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Float4x4 loadRMHeap(byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(bytes, address);
        float _c1 = _U.getFloat(bytes, address + 4L);
        float _c2 = _U.getFloat(bytes, address + 8L);
        float _c3 = _U.getFloat(bytes, address + 12L);
        float _c4 = _U.getFloat(bytes, address + 16L);
        float _c5 = _U.getFloat(bytes, address + 20L);
        float _c6 = _U.getFloat(bytes, address + 24L);
        float _c7 = _U.getFloat(bytes, address + 28L);
        float _c8 = _U.getFloat(bytes, address + 32L);
        float _c9 = _U.getFloat(bytes, address + 36L);
        float _c10 = _U.getFloat(bytes, address + 40L);
        float _c11 = _U.getFloat(bytes, address + 44L);
        float _c12 = _U.getFloat(bytes, address + 48L);
        float _c13 = _U.getFloat(bytes, address + 52L);
        float _c14 = _U.getFloat(bytes, address + 56L);
        float _c15 = _U.getFloat(bytes, address + 60L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Float4x4 storeRMDoubleUnsafe(Float4x4 self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8L, self.m01());
        _U.putDouble(address + 16L, self.m02());
        _U.putDouble(address + 24L, self.m03());
        _U.putDouble(address + 32L, self.m10());
        _U.putDouble(address + 40L, self.m11());
        _U.putDouble(address + 48L, self.m12());
        _U.putDouble(address + 56L, self.m13());
        _U.putDouble(address + 64L, self.m20());
        _U.putDouble(address + 72L, self.m21());
        _U.putDouble(address + 80L, self.m22());
        _U.putDouble(address + 88L, self.m23());
        _U.putDouble(address + 96L, self.m30());
        _U.putDouble(address + 104L, self.m31());
        _U.putDouble(address + 112L, self.m32());
        _U.putDouble(address + 120L, self.m33());
        return self;
    }

    public Float4x4 storeRMDoubleHeap(Float4x4 self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8L, self.m01());
        _U.putDouble(bytes, address + 16L, self.m02());
        _U.putDouble(bytes, address + 24L, self.m03());
        _U.putDouble(bytes, address + 32L, self.m10());
        _U.putDouble(bytes, address + 40L, self.m11());
        _U.putDouble(bytes, address + 48L, self.m12());
        _U.putDouble(bytes, address + 56L, self.m13());
        _U.putDouble(bytes, address + 64L, self.m20());
        _U.putDouble(bytes, address + 72L, self.m21());
        _U.putDouble(bytes, address + 80L, self.m22());
        _U.putDouble(bytes, address + 88L, self.m23());
        _U.putDouble(bytes, address + 96L, self.m30());
        _U.putDouble(bytes, address + 104L, self.m31());
        _U.putDouble(bytes, address + 112L, self.m32());
        _U.putDouble(bytes, address + 120L, self.m33());
        return self;
    }

    public Float4x4 loadRMDoubleUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(address);
        float _c1 = (float) _U.getDouble(address + 8L);
        float _c2 = (float) _U.getDouble(address + 16L);
        float _c3 = (float) _U.getDouble(address + 24L);
        float _c4 = (float) _U.getDouble(address + 32L);
        float _c5 = (float) _U.getDouble(address + 40L);
        float _c6 = (float) _U.getDouble(address + 48L);
        float _c7 = (float) _U.getDouble(address + 56L);
        float _c8 = (float) _U.getDouble(address + 64L);
        float _c9 = (float) _U.getDouble(address + 72L);
        float _c10 = (float) _U.getDouble(address + 80L);
        float _c11 = (float) _U.getDouble(address + 88L);
        float _c12 = (float) _U.getDouble(address + 96L);
        float _c13 = (float) _U.getDouble(address + 104L);
        float _c14 = (float) _U.getDouble(address + 112L);
        float _c15 = (float) _U.getDouble(address + 120L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Float4x4 loadRMDoubleHeap(byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(bytes, address);
        float _c1 = (float) _U.getDouble(bytes, address + 8L);
        float _c2 = (float) _U.getDouble(bytes, address + 16L);
        float _c3 = (float) _U.getDouble(bytes, address + 24L);
        float _c4 = (float) _U.getDouble(bytes, address + 32L);
        float _c5 = (float) _U.getDouble(bytes, address + 40L);
        float _c6 = (float) _U.getDouble(bytes, address + 48L);
        float _c7 = (float) _U.getDouble(bytes, address + 56L);
        float _c8 = (float) _U.getDouble(bytes, address + 64L);
        float _c9 = (float) _U.getDouble(bytes, address + 72L);
        float _c10 = (float) _U.getDouble(bytes, address + 80L);
        float _c11 = (float) _U.getDouble(bytes, address + 88L);
        float _c12 = (float) _U.getDouble(bytes, address + 96L);
        float _c13 = (float) _U.getDouble(bytes, address + 104L);
        float _c14 = (float) _U.getDouble(bytes, address + 112L);
        float _c15 = (float) _U.getDouble(bytes, address + 120L);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Float4x4 storeCMUnsafe(Float4x4 self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4, self.m10());
        _U.putFloat(address + 8, self.m20());
        _U.putFloat(address + 12, self.m30());
        _U.putFloat(_p1, self.m01());
        _U.putFloat(_p1 + 4, self.m11());
        _U.putFloat(_p1 + 8, self.m21());
        _U.putFloat(_p1 + 12, self.m31());
        _U.putFloat(_p2, self.m02());
        _U.putFloat(_p2 + 4, self.m12());
        _U.putFloat(_p2 + 8, self.m22());
        _U.putFloat(_p2 + 12, self.m32());
        _U.putFloat(_p3, self.m03());
        _U.putFloat(_p3 + 4, self.m13());
        _U.putFloat(_p3 + 8, self.m23());
        _U.putFloat(_p3 + 12, self.m33());
        return self;
    }

    public Float4x4 storeCMHeap(Float4x4 self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00());
        _U.putFloat(bytes, address + 4, self.m10());
        _U.putFloat(bytes, address + 8, self.m20());
        _U.putFloat(bytes, address + 12, self.m30());
        _U.putFloat(bytes, _p1, self.m01());
        _U.putFloat(bytes, _p1 + 4, self.m11());
        _U.putFloat(bytes, _p1 + 8, self.m21());
        _U.putFloat(bytes, _p1 + 12, self.m31());
        _U.putFloat(bytes, _p2, self.m02());
        _U.putFloat(bytes, _p2 + 4, self.m12());
        _U.putFloat(bytes, _p2 + 8, self.m22());
        _U.putFloat(bytes, _p2 + 12, self.m32());
        _U.putFloat(bytes, _p3, self.m03());
        _U.putFloat(bytes, _p3 + 4, self.m13());
        _U.putFloat(bytes, _p3 + 8, self.m23());
        _U.putFloat(bytes, _p3 + 12, self.m33());
        return self;
    }

    public Float4x4 loadCMUnsafe(long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(address);
        float _c4 = _U.getFloat(address + 4);
        float _c8 = _U.getFloat(address + 8);
        float _c12 = _U.getFloat(address + 12);
        float _c1 = _U.getFloat(_p1);
        float _c5 = _U.getFloat(_p1 + 4);
        float _c9 = _U.getFloat(_p1 + 8);
        float _c13 = _U.getFloat(_p1 + 12);
        float _c2 = _U.getFloat(_p2);
        float _c6 = _U.getFloat(_p2 + 4);
        float _c10 = _U.getFloat(_p2 + 8);
        float _c14 = _U.getFloat(_p2 + 12);
        float _c3 = _U.getFloat(_p3);
        float _c7 = _U.getFloat(_p3 + 4);
        float _c11 = _U.getFloat(_p3 + 8);
        float _c15 = _U.getFloat(_p3 + 12);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Float4x4 loadCMHeap(byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(bytes, address);
        float _c4 = _U.getFloat(bytes, address + 4);
        float _c8 = _U.getFloat(bytes, address + 8);
        float _c12 = _U.getFloat(bytes, address + 12);
        float _c1 = _U.getFloat(bytes, _p1);
        float _c5 = _U.getFloat(bytes, _p1 + 4);
        float _c9 = _U.getFloat(bytes, _p1 + 8);
        float _c13 = _U.getFloat(bytes, _p1 + 12);
        float _c2 = _U.getFloat(bytes, _p2);
        float _c6 = _U.getFloat(bytes, _p2 + 4);
        float _c10 = _U.getFloat(bytes, _p2 + 8);
        float _c14 = _U.getFloat(bytes, _p2 + 12);
        float _c3 = _U.getFloat(bytes, _p3);
        float _c7 = _U.getFloat(bytes, _p3 + 4);
        float _c11 = _U.getFloat(bytes, _p3 + 8);
        float _c15 = _U.getFloat(bytes, _p3 + 12);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Float4x4 storeCMDoubleUnsafe(Float4x4 self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8, self.m10());
        _U.putDouble(address + 16, self.m20());
        _U.putDouble(address + 24, self.m30());
        _U.putDouble(_p1, self.m01());
        _U.putDouble(_p1 + 8, self.m11());
        _U.putDouble(_p1 + 16, self.m21());
        _U.putDouble(_p1 + 24, self.m31());
        _U.putDouble(_p2, self.m02());
        _U.putDouble(_p2 + 8, self.m12());
        _U.putDouble(_p2 + 16, self.m22());
        _U.putDouble(_p2 + 24, self.m32());
        _U.putDouble(_p3, self.m03());
        _U.putDouble(_p3 + 8, self.m13());
        _U.putDouble(_p3 + 16, self.m23());
        _U.putDouble(_p3 + 24, self.m33());
        return self;
    }

    public Float4x4 storeCMDoubleHeap(Float4x4 self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8, self.m10());
        _U.putDouble(bytes, address + 16, self.m20());
        _U.putDouble(bytes, address + 24, self.m30());
        _U.putDouble(bytes, _p1, self.m01());
        _U.putDouble(bytes, _p1 + 8, self.m11());
        _U.putDouble(bytes, _p1 + 16, self.m21());
        _U.putDouble(bytes, _p1 + 24, self.m31());
        _U.putDouble(bytes, _p2, self.m02());
        _U.putDouble(bytes, _p2 + 8, self.m12());
        _U.putDouble(bytes, _p2 + 16, self.m22());
        _U.putDouble(bytes, _p2 + 24, self.m32());
        _U.putDouble(bytes, _p3, self.m03());
        _U.putDouble(bytes, _p3 + 8, self.m13());
        _U.putDouble(bytes, _p3 + 16, self.m23());
        _U.putDouble(bytes, _p3 + 24, self.m33());
        return self;
    }

    public Float4x4 loadCMDoubleUnsafe(long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(address);
        float _c4 = (float) _U.getDouble(address + 8);
        float _c8 = (float) _U.getDouble(address + 16);
        float _c12 = (float) _U.getDouble(address + 24);
        float _c1 = (float) _U.getDouble(_p1);
        float _c5 = (float) _U.getDouble(_p1 + 8);
        float _c9 = (float) _U.getDouble(_p1 + 16);
        float _c13 = (float) _U.getDouble(_p1 + 24);
        float _c2 = (float) _U.getDouble(_p2);
        float _c6 = (float) _U.getDouble(_p2 + 8);
        float _c10 = (float) _U.getDouble(_p2 + 16);
        float _c14 = (float) _U.getDouble(_p2 + 24);
        float _c3 = (float) _U.getDouble(_p3);
        float _c7 = (float) _U.getDouble(_p3 + 8);
        float _c11 = (float) _U.getDouble(_p3 + 16);
        float _c15 = (float) _U.getDouble(_p3 + 24);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Float4x4 loadCMDoubleHeap(byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(bytes, address);
        float _c4 = (float) _U.getDouble(bytes, address + 8);
        float _c8 = (float) _U.getDouble(bytes, address + 16);
        float _c12 = (float) _U.getDouble(bytes, address + 24);
        float _c1 = (float) _U.getDouble(bytes, _p1);
        float _c5 = (float) _U.getDouble(bytes, _p1 + 8);
        float _c9 = (float) _U.getDouble(bytes, _p1 + 16);
        float _c13 = (float) _U.getDouble(bytes, _p1 + 24);
        float _c2 = (float) _U.getDouble(bytes, _p2);
        float _c6 = (float) _U.getDouble(bytes, _p2 + 8);
        float _c10 = (float) _U.getDouble(bytes, _p2 + 16);
        float _c14 = (float) _U.getDouble(bytes, _p2 + 24);
        float _c3 = (float) _U.getDouble(bytes, _p3);
        float _c7 = (float) _U.getDouble(bytes, _p3 + 8);
        float _c11 = (float) _U.getDouble(bytes, _p3 + 16);
        float _c15 = (float) _U.getDouble(bytes, _p3 + 24);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Float4x4 storeRMUnsafe(Float4x4 self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.m00());
        _U.putFloat(address + 4, self.m01());
        _U.putFloat(address + 8, self.m02());
        _U.putFloat(address + 12, self.m03());
        _U.putFloat(_p1, self.m10());
        _U.putFloat(_p1 + 4, self.m11());
        _U.putFloat(_p1 + 8, self.m12());
        _U.putFloat(_p1 + 12, self.m13());
        _U.putFloat(_p2, self.m20());
        _U.putFloat(_p2 + 4, self.m21());
        _U.putFloat(_p2 + 8, self.m22());
        _U.putFloat(_p2 + 12, self.m23());
        _U.putFloat(_p3, self.m30());
        _U.putFloat(_p3 + 4, self.m31());
        _U.putFloat(_p3 + 8, self.m32());
        _U.putFloat(_p3 + 12, self.m33());
        return self;
    }

    public Float4x4 storeRMHeap(Float4x4 self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.m00());
        _U.putFloat(bytes, address + 4, self.m01());
        _U.putFloat(bytes, address + 8, self.m02());
        _U.putFloat(bytes, address + 12, self.m03());
        _U.putFloat(bytes, _p1, self.m10());
        _U.putFloat(bytes, _p1 + 4, self.m11());
        _U.putFloat(bytes, _p1 + 8, self.m12());
        _U.putFloat(bytes, _p1 + 12, self.m13());
        _U.putFloat(bytes, _p2, self.m20());
        _U.putFloat(bytes, _p2 + 4, self.m21());
        _U.putFloat(bytes, _p2 + 8, self.m22());
        _U.putFloat(bytes, _p2 + 12, self.m23());
        _U.putFloat(bytes, _p3, self.m30());
        _U.putFloat(bytes, _p3 + 4, self.m31());
        _U.putFloat(bytes, _p3 + 8, self.m32());
        _U.putFloat(bytes, _p3 + 12, self.m33());
        return self;
    }

    public Float4x4 loadRMUnsafe(long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(address);
        float _c1 = _U.getFloat(address + 4);
        float _c2 = _U.getFloat(address + 8);
        float _c3 = _U.getFloat(address + 12);
        float _c4 = _U.getFloat(_p1);
        float _c5 = _U.getFloat(_p1 + 4);
        float _c6 = _U.getFloat(_p1 + 8);
        float _c7 = _U.getFloat(_p1 + 12);
        float _c8 = _U.getFloat(_p2);
        float _c9 = _U.getFloat(_p2 + 4);
        float _c10 = _U.getFloat(_p2 + 8);
        float _c11 = _U.getFloat(_p2 + 12);
        float _c12 = _U.getFloat(_p3);
        float _c13 = _U.getFloat(_p3 + 4);
        float _c14 = _U.getFloat(_p3 + 8);
        float _c15 = _U.getFloat(_p3 + 12);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Float4x4 loadRMHeap(byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(bytes, address);
        float _c1 = _U.getFloat(bytes, address + 4);
        float _c2 = _U.getFloat(bytes, address + 8);
        float _c3 = _U.getFloat(bytes, address + 12);
        float _c4 = _U.getFloat(bytes, _p1);
        float _c5 = _U.getFloat(bytes, _p1 + 4);
        float _c6 = _U.getFloat(bytes, _p1 + 8);
        float _c7 = _U.getFloat(bytes, _p1 + 12);
        float _c8 = _U.getFloat(bytes, _p2);
        float _c9 = _U.getFloat(bytes, _p2 + 4);
        float _c10 = _U.getFloat(bytes, _p2 + 8);
        float _c11 = _U.getFloat(bytes, _p2 + 12);
        float _c12 = _U.getFloat(bytes, _p3);
        float _c13 = _U.getFloat(bytes, _p3 + 4);
        float _c14 = _U.getFloat(bytes, _p3 + 8);
        float _c15 = _U.getFloat(bytes, _p3 + 12);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Float4x4 storeRMDoubleUnsafe(Float4x4 self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.m00());
        _U.putDouble(address + 8, self.m01());
        _U.putDouble(address + 16, self.m02());
        _U.putDouble(address + 24, self.m03());
        _U.putDouble(_p1, self.m10());
        _U.putDouble(_p1 + 8, self.m11());
        _U.putDouble(_p1 + 16, self.m12());
        _U.putDouble(_p1 + 24, self.m13());
        _U.putDouble(_p2, self.m20());
        _U.putDouble(_p2 + 8, self.m21());
        _U.putDouble(_p2 + 16, self.m22());
        _U.putDouble(_p2 + 24, self.m23());
        _U.putDouble(_p3, self.m30());
        _U.putDouble(_p3 + 8, self.m31());
        _U.putDouble(_p3 + 16, self.m32());
        _U.putDouble(_p3 + 24, self.m33());
        return self;
    }

    public Float4x4 storeRMDoubleHeap(Float4x4 self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.m00());
        _U.putDouble(bytes, address + 8, self.m01());
        _U.putDouble(bytes, address + 16, self.m02());
        _U.putDouble(bytes, address + 24, self.m03());
        _U.putDouble(bytes, _p1, self.m10());
        _U.putDouble(bytes, _p1 + 8, self.m11());
        _U.putDouble(bytes, _p1 + 16, self.m12());
        _U.putDouble(bytes, _p1 + 24, self.m13());
        _U.putDouble(bytes, _p2, self.m20());
        _U.putDouble(bytes, _p2 + 8, self.m21());
        _U.putDouble(bytes, _p2 + 16, self.m22());
        _U.putDouble(bytes, _p2 + 24, self.m23());
        _U.putDouble(bytes, _p3, self.m30());
        _U.putDouble(bytes, _p3 + 8, self.m31());
        _U.putDouble(bytes, _p3 + 16, self.m32());
        _U.putDouble(bytes, _p3 + 24, self.m33());
        return self;
    }

    public Float4x4 loadRMDoubleUnsafe(long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(address);
        float _c1 = (float) _U.getDouble(address + 8);
        float _c2 = (float) _U.getDouble(address + 16);
        float _c3 = (float) _U.getDouble(address + 24);
        float _c4 = (float) _U.getDouble(_p1);
        float _c5 = (float) _U.getDouble(_p1 + 8);
        float _c6 = (float) _U.getDouble(_p1 + 16);
        float _c7 = (float) _U.getDouble(_p1 + 24);
        float _c8 = (float) _U.getDouble(_p2);
        float _c9 = (float) _U.getDouble(_p2 + 8);
        float _c10 = (float) _U.getDouble(_p2 + 16);
        float _c11 = (float) _U.getDouble(_p2 + 24);
        float _c12 = (float) _U.getDouble(_p3);
        float _c13 = (float) _U.getDouble(_p3 + 8);
        float _c14 = (float) _U.getDouble(_p3 + 16);
        float _c15 = (float) _U.getDouble(_p3 + 24);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Float4x4 loadRMDoubleHeap(byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(bytes, address);
        float _c1 = (float) _U.getDouble(bytes, address + 8);
        float _c2 = (float) _U.getDouble(bytes, address + 16);
        float _c3 = (float) _U.getDouble(bytes, address + 24);
        float _c4 = (float) _U.getDouble(bytes, _p1);
        float _c5 = (float) _U.getDouble(bytes, _p1 + 8);
        float _c6 = (float) _U.getDouble(bytes, _p1 + 16);
        float _c7 = (float) _U.getDouble(bytes, _p1 + 24);
        float _c8 = (float) _U.getDouble(bytes, _p2);
        float _c9 = (float) _U.getDouble(bytes, _p2 + 8);
        float _c10 = (float) _U.getDouble(bytes, _p2 + 16);
        float _c11 = (float) _U.getDouble(bytes, _p2 + 24);
        float _c12 = (float) _U.getDouble(bytes, _p3);
        float _c13 = (float) _U.getDouble(bytes, _p3 + 8);
        float _c14 = (float) _U.getDouble(bytes, _p3 + 16);
        float _c15 = (float) _U.getDouble(bytes, _p3 + 24);
        return new Float4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
}
