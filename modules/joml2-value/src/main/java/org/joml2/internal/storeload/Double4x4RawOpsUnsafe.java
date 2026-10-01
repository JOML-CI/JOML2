// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double4x4RawOpsUnsafe extends Double4x4RawOps {
    public Double4x4 storeCMUnsafe(Double4x4 self, long address) {
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

    public Double4x4 storeCMHeap(Double4x4 self, byte[] bytes, long address) {
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

    public Double4x4 loadCMUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(address);
        double _c4 = _U.getDouble(address + 8L);
        double _c8 = _U.getDouble(address + 16L);
        double _c12 = _U.getDouble(address + 24L);
        double _c1 = _U.getDouble(address + 32L);
        double _c5 = _U.getDouble(address + 40L);
        double _c9 = _U.getDouble(address + 48L);
        double _c13 = _U.getDouble(address + 56L);
        double _c2 = _U.getDouble(address + 64L);
        double _c6 = _U.getDouble(address + 72L);
        double _c10 = _U.getDouble(address + 80L);
        double _c14 = _U.getDouble(address + 88L);
        double _c3 = _U.getDouble(address + 96L);
        double _c7 = _U.getDouble(address + 104L);
        double _c11 = _U.getDouble(address + 112L);
        double _c15 = _U.getDouble(address + 120L);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Double4x4 loadCMHeap(byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(bytes, address);
        double _c4 = _U.getDouble(bytes, address + 8L);
        double _c8 = _U.getDouble(bytes, address + 16L);
        double _c12 = _U.getDouble(bytes, address + 24L);
        double _c1 = _U.getDouble(bytes, address + 32L);
        double _c5 = _U.getDouble(bytes, address + 40L);
        double _c9 = _U.getDouble(bytes, address + 48L);
        double _c13 = _U.getDouble(bytes, address + 56L);
        double _c2 = _U.getDouble(bytes, address + 64L);
        double _c6 = _U.getDouble(bytes, address + 72L);
        double _c10 = _U.getDouble(bytes, address + 80L);
        double _c14 = _U.getDouble(bytes, address + 88L);
        double _c3 = _U.getDouble(bytes, address + 96L);
        double _c7 = _U.getDouble(bytes, address + 104L);
        double _c11 = _U.getDouble(bytes, address + 112L);
        double _c15 = _U.getDouble(bytes, address + 120L);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Double4x4 storeCMFloatUnsafe(Double4x4 self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00());
        _U.putFloat(address + 4L, (float) self.m10());
        _U.putFloat(address + 8L, (float) self.m20());
        _U.putFloat(address + 12L, (float) self.m30());
        _U.putFloat(address + 16L, (float) self.m01());
        _U.putFloat(address + 20L, (float) self.m11());
        _U.putFloat(address + 24L, (float) self.m21());
        _U.putFloat(address + 28L, (float) self.m31());
        _U.putFloat(address + 32L, (float) self.m02());
        _U.putFloat(address + 36L, (float) self.m12());
        _U.putFloat(address + 40L, (float) self.m22());
        _U.putFloat(address + 44L, (float) self.m32());
        _U.putFloat(address + 48L, (float) self.m03());
        _U.putFloat(address + 52L, (float) self.m13());
        _U.putFloat(address + 56L, (float) self.m23());
        _U.putFloat(address + 60L, (float) self.m33());
        return self;
    }

    public Double4x4 storeCMFloatHeap(Double4x4 self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00());
        _U.putFloat(bytes, address + 4L, (float) self.m10());
        _U.putFloat(bytes, address + 8L, (float) self.m20());
        _U.putFloat(bytes, address + 12L, (float) self.m30());
        _U.putFloat(bytes, address + 16L, (float) self.m01());
        _U.putFloat(bytes, address + 20L, (float) self.m11());
        _U.putFloat(bytes, address + 24L, (float) self.m21());
        _U.putFloat(bytes, address + 28L, (float) self.m31());
        _U.putFloat(bytes, address + 32L, (float) self.m02());
        _U.putFloat(bytes, address + 36L, (float) self.m12());
        _U.putFloat(bytes, address + 40L, (float) self.m22());
        _U.putFloat(bytes, address + 44L, (float) self.m32());
        _U.putFloat(bytes, address + 48L, (float) self.m03());
        _U.putFloat(bytes, address + 52L, (float) self.m13());
        _U.putFloat(bytes, address + 56L, (float) self.m23());
        _U.putFloat(bytes, address + 60L, (float) self.m33());
        return self;
    }

    public Double4x4 loadCMFloatUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(address);
        double _c4 = _U.getFloat(address + 4L);
        double _c8 = _U.getFloat(address + 8L);
        double _c12 = _U.getFloat(address + 12L);
        double _c1 = _U.getFloat(address + 16L);
        double _c5 = _U.getFloat(address + 20L);
        double _c9 = _U.getFloat(address + 24L);
        double _c13 = _U.getFloat(address + 28L);
        double _c2 = _U.getFloat(address + 32L);
        double _c6 = _U.getFloat(address + 36L);
        double _c10 = _U.getFloat(address + 40L);
        double _c14 = _U.getFloat(address + 44L);
        double _c3 = _U.getFloat(address + 48L);
        double _c7 = _U.getFloat(address + 52L);
        double _c11 = _U.getFloat(address + 56L);
        double _c15 = _U.getFloat(address + 60L);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Double4x4 loadCMFloatHeap(byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(bytes, address);
        double _c4 = _U.getFloat(bytes, address + 4L);
        double _c8 = _U.getFloat(bytes, address + 8L);
        double _c12 = _U.getFloat(bytes, address + 12L);
        double _c1 = _U.getFloat(bytes, address + 16L);
        double _c5 = _U.getFloat(bytes, address + 20L);
        double _c9 = _U.getFloat(bytes, address + 24L);
        double _c13 = _U.getFloat(bytes, address + 28L);
        double _c2 = _U.getFloat(bytes, address + 32L);
        double _c6 = _U.getFloat(bytes, address + 36L);
        double _c10 = _U.getFloat(bytes, address + 40L);
        double _c14 = _U.getFloat(bytes, address + 44L);
        double _c3 = _U.getFloat(bytes, address + 48L);
        double _c7 = _U.getFloat(bytes, address + 52L);
        double _c11 = _U.getFloat(bytes, address + 56L);
        double _c15 = _U.getFloat(bytes, address + 60L);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Double4x4 storeRMUnsafe(Double4x4 self, long address) {
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

    public Double4x4 storeRMHeap(Double4x4 self, byte[] bytes, long address) {
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

    public Double4x4 loadRMUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(address);
        double _c1 = _U.getDouble(address + 8L);
        double _c2 = _U.getDouble(address + 16L);
        double _c3 = _U.getDouble(address + 24L);
        double _c4 = _U.getDouble(address + 32L);
        double _c5 = _U.getDouble(address + 40L);
        double _c6 = _U.getDouble(address + 48L);
        double _c7 = _U.getDouble(address + 56L);
        double _c8 = _U.getDouble(address + 64L);
        double _c9 = _U.getDouble(address + 72L);
        double _c10 = _U.getDouble(address + 80L);
        double _c11 = _U.getDouble(address + 88L);
        double _c12 = _U.getDouble(address + 96L);
        double _c13 = _U.getDouble(address + 104L);
        double _c14 = _U.getDouble(address + 112L);
        double _c15 = _U.getDouble(address + 120L);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Double4x4 loadRMHeap(byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(bytes, address);
        double _c1 = _U.getDouble(bytes, address + 8L);
        double _c2 = _U.getDouble(bytes, address + 16L);
        double _c3 = _U.getDouble(bytes, address + 24L);
        double _c4 = _U.getDouble(bytes, address + 32L);
        double _c5 = _U.getDouble(bytes, address + 40L);
        double _c6 = _U.getDouble(bytes, address + 48L);
        double _c7 = _U.getDouble(bytes, address + 56L);
        double _c8 = _U.getDouble(bytes, address + 64L);
        double _c9 = _U.getDouble(bytes, address + 72L);
        double _c10 = _U.getDouble(bytes, address + 80L);
        double _c11 = _U.getDouble(bytes, address + 88L);
        double _c12 = _U.getDouble(bytes, address + 96L);
        double _c13 = _U.getDouble(bytes, address + 104L);
        double _c14 = _U.getDouble(bytes, address + 112L);
        double _c15 = _U.getDouble(bytes, address + 120L);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Double4x4 storeRMFloatUnsafe(Double4x4 self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00());
        _U.putFloat(address + 4L, (float) self.m01());
        _U.putFloat(address + 8L, (float) self.m02());
        _U.putFloat(address + 12L, (float) self.m03());
        _U.putFloat(address + 16L, (float) self.m10());
        _U.putFloat(address + 20L, (float) self.m11());
        _U.putFloat(address + 24L, (float) self.m12());
        _U.putFloat(address + 28L, (float) self.m13());
        _U.putFloat(address + 32L, (float) self.m20());
        _U.putFloat(address + 36L, (float) self.m21());
        _U.putFloat(address + 40L, (float) self.m22());
        _U.putFloat(address + 44L, (float) self.m23());
        _U.putFloat(address + 48L, (float) self.m30());
        _U.putFloat(address + 52L, (float) self.m31());
        _U.putFloat(address + 56L, (float) self.m32());
        _U.putFloat(address + 60L, (float) self.m33());
        return self;
    }

    public Double4x4 storeRMFloatHeap(Double4x4 self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00());
        _U.putFloat(bytes, address + 4L, (float) self.m01());
        _U.putFloat(bytes, address + 8L, (float) self.m02());
        _U.putFloat(bytes, address + 12L, (float) self.m03());
        _U.putFloat(bytes, address + 16L, (float) self.m10());
        _U.putFloat(bytes, address + 20L, (float) self.m11());
        _U.putFloat(bytes, address + 24L, (float) self.m12());
        _U.putFloat(bytes, address + 28L, (float) self.m13());
        _U.putFloat(bytes, address + 32L, (float) self.m20());
        _U.putFloat(bytes, address + 36L, (float) self.m21());
        _U.putFloat(bytes, address + 40L, (float) self.m22());
        _U.putFloat(bytes, address + 44L, (float) self.m23());
        _U.putFloat(bytes, address + 48L, (float) self.m30());
        _U.putFloat(bytes, address + 52L, (float) self.m31());
        _U.putFloat(bytes, address + 56L, (float) self.m32());
        _U.putFloat(bytes, address + 60L, (float) self.m33());
        return self;
    }

    public Double4x4 loadRMFloatUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(address);
        double _c1 = _U.getFloat(address + 4L);
        double _c2 = _U.getFloat(address + 8L);
        double _c3 = _U.getFloat(address + 12L);
        double _c4 = _U.getFloat(address + 16L);
        double _c5 = _U.getFloat(address + 20L);
        double _c6 = _U.getFloat(address + 24L);
        double _c7 = _U.getFloat(address + 28L);
        double _c8 = _U.getFloat(address + 32L);
        double _c9 = _U.getFloat(address + 36L);
        double _c10 = _U.getFloat(address + 40L);
        double _c11 = _U.getFloat(address + 44L);
        double _c12 = _U.getFloat(address + 48L);
        double _c13 = _U.getFloat(address + 52L);
        double _c14 = _U.getFloat(address + 56L);
        double _c15 = _U.getFloat(address + 60L);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Double4x4 loadRMFloatHeap(byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(bytes, address);
        double _c1 = _U.getFloat(bytes, address + 4L);
        double _c2 = _U.getFloat(bytes, address + 8L);
        double _c3 = _U.getFloat(bytes, address + 12L);
        double _c4 = _U.getFloat(bytes, address + 16L);
        double _c5 = _U.getFloat(bytes, address + 20L);
        double _c6 = _U.getFloat(bytes, address + 24L);
        double _c7 = _U.getFloat(bytes, address + 28L);
        double _c8 = _U.getFloat(bytes, address + 32L);
        double _c9 = _U.getFloat(bytes, address + 36L);
        double _c10 = _U.getFloat(bytes, address + 40L);
        double _c11 = _U.getFloat(bytes, address + 44L);
        double _c12 = _U.getFloat(bytes, address + 48L);
        double _c13 = _U.getFloat(bytes, address + 52L);
        double _c14 = _U.getFloat(bytes, address + 56L);
        double _c15 = _U.getFloat(bytes, address + 60L);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Double4x4 storeCMUnsafe(Double4x4 self, long address, int stride) {
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

    public Double4x4 storeCMHeap(Double4x4 self, byte[] bytes, long address, int stride) {
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

    public Double4x4 loadCMUnsafe(long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(address);
        double _c4 = _U.getDouble(address + 8);
        double _c8 = _U.getDouble(address + 16);
        double _c12 = _U.getDouble(address + 24);
        double _c1 = _U.getDouble(_p1);
        double _c5 = _U.getDouble(_p1 + 8);
        double _c9 = _U.getDouble(_p1 + 16);
        double _c13 = _U.getDouble(_p1 + 24);
        double _c2 = _U.getDouble(_p2);
        double _c6 = _U.getDouble(_p2 + 8);
        double _c10 = _U.getDouble(_p2 + 16);
        double _c14 = _U.getDouble(_p2 + 24);
        double _c3 = _U.getDouble(_p3);
        double _c7 = _U.getDouble(_p3 + 8);
        double _c11 = _U.getDouble(_p3 + 16);
        double _c15 = _U.getDouble(_p3 + 24);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Double4x4 loadCMHeap(byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(bytes, address);
        double _c4 = _U.getDouble(bytes, address + 8);
        double _c8 = _U.getDouble(bytes, address + 16);
        double _c12 = _U.getDouble(bytes, address + 24);
        double _c1 = _U.getDouble(bytes, _p1);
        double _c5 = _U.getDouble(bytes, _p1 + 8);
        double _c9 = _U.getDouble(bytes, _p1 + 16);
        double _c13 = _U.getDouble(bytes, _p1 + 24);
        double _c2 = _U.getDouble(bytes, _p2);
        double _c6 = _U.getDouble(bytes, _p2 + 8);
        double _c10 = _U.getDouble(bytes, _p2 + 16);
        double _c14 = _U.getDouble(bytes, _p2 + 24);
        double _c3 = _U.getDouble(bytes, _p3);
        double _c7 = _U.getDouble(bytes, _p3 + 8);
        double _c11 = _U.getDouble(bytes, _p3 + 16);
        double _c15 = _U.getDouble(bytes, _p3 + 24);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Double4x4 storeCMFloatUnsafe(Double4x4 self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00());
        _U.putFloat(address + 4, (float) self.m10());
        _U.putFloat(address + 8, (float) self.m20());
        _U.putFloat(address + 12, (float) self.m30());
        _U.putFloat(_p1, (float) self.m01());
        _U.putFloat(_p1 + 4, (float) self.m11());
        _U.putFloat(_p1 + 8, (float) self.m21());
        _U.putFloat(_p1 + 12, (float) self.m31());
        _U.putFloat(_p2, (float) self.m02());
        _U.putFloat(_p2 + 4, (float) self.m12());
        _U.putFloat(_p2 + 8, (float) self.m22());
        _U.putFloat(_p2 + 12, (float) self.m32());
        _U.putFloat(_p3, (float) self.m03());
        _U.putFloat(_p3 + 4, (float) self.m13());
        _U.putFloat(_p3 + 8, (float) self.m23());
        _U.putFloat(_p3 + 12, (float) self.m33());
        return self;
    }

    public Double4x4 storeCMFloatHeap(Double4x4 self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00());
        _U.putFloat(bytes, address + 4, (float) self.m10());
        _U.putFloat(bytes, address + 8, (float) self.m20());
        _U.putFloat(bytes, address + 12, (float) self.m30());
        _U.putFloat(bytes, _p1, (float) self.m01());
        _U.putFloat(bytes, _p1 + 4, (float) self.m11());
        _U.putFloat(bytes, _p1 + 8, (float) self.m21());
        _U.putFloat(bytes, _p1 + 12, (float) self.m31());
        _U.putFloat(bytes, _p2, (float) self.m02());
        _U.putFloat(bytes, _p2 + 4, (float) self.m12());
        _U.putFloat(bytes, _p2 + 8, (float) self.m22());
        _U.putFloat(bytes, _p2 + 12, (float) self.m32());
        _U.putFloat(bytes, _p3, (float) self.m03());
        _U.putFloat(bytes, _p3 + 4, (float) self.m13());
        _U.putFloat(bytes, _p3 + 8, (float) self.m23());
        _U.putFloat(bytes, _p3 + 12, (float) self.m33());
        return self;
    }

    public Double4x4 loadCMFloatUnsafe(long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(address);
        double _c4 = _U.getFloat(address + 4);
        double _c8 = _U.getFloat(address + 8);
        double _c12 = _U.getFloat(address + 12);
        double _c1 = _U.getFloat(_p1);
        double _c5 = _U.getFloat(_p1 + 4);
        double _c9 = _U.getFloat(_p1 + 8);
        double _c13 = _U.getFloat(_p1 + 12);
        double _c2 = _U.getFloat(_p2);
        double _c6 = _U.getFloat(_p2 + 4);
        double _c10 = _U.getFloat(_p2 + 8);
        double _c14 = _U.getFloat(_p2 + 12);
        double _c3 = _U.getFloat(_p3);
        double _c7 = _U.getFloat(_p3 + 4);
        double _c11 = _U.getFloat(_p3 + 8);
        double _c15 = _U.getFloat(_p3 + 12);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Double4x4 loadCMFloatHeap(byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(bytes, address);
        double _c4 = _U.getFloat(bytes, address + 4);
        double _c8 = _U.getFloat(bytes, address + 8);
        double _c12 = _U.getFloat(bytes, address + 12);
        double _c1 = _U.getFloat(bytes, _p1);
        double _c5 = _U.getFloat(bytes, _p1 + 4);
        double _c9 = _U.getFloat(bytes, _p1 + 8);
        double _c13 = _U.getFloat(bytes, _p1 + 12);
        double _c2 = _U.getFloat(bytes, _p2);
        double _c6 = _U.getFloat(bytes, _p2 + 4);
        double _c10 = _U.getFloat(bytes, _p2 + 8);
        double _c14 = _U.getFloat(bytes, _p2 + 12);
        double _c3 = _U.getFloat(bytes, _p3);
        double _c7 = _U.getFloat(bytes, _p3 + 4);
        double _c11 = _U.getFloat(bytes, _p3 + 8);
        double _c15 = _U.getFloat(bytes, _p3 + 12);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Double4x4 storeRMUnsafe(Double4x4 self, long address, int stride) {
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

    public Double4x4 storeRMHeap(Double4x4 self, byte[] bytes, long address, int stride) {
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

    public Double4x4 loadRMUnsafe(long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(address);
        double _c1 = _U.getDouble(address + 8);
        double _c2 = _U.getDouble(address + 16);
        double _c3 = _U.getDouble(address + 24);
        double _c4 = _U.getDouble(_p1);
        double _c5 = _U.getDouble(_p1 + 8);
        double _c6 = _U.getDouble(_p1 + 16);
        double _c7 = _U.getDouble(_p1 + 24);
        double _c8 = _U.getDouble(_p2);
        double _c9 = _U.getDouble(_p2 + 8);
        double _c10 = _U.getDouble(_p2 + 16);
        double _c11 = _U.getDouble(_p2 + 24);
        double _c12 = _U.getDouble(_p3);
        double _c13 = _U.getDouble(_p3 + 8);
        double _c14 = _U.getDouble(_p3 + 16);
        double _c15 = _U.getDouble(_p3 + 24);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Double4x4 loadRMHeap(byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(bytes, address);
        double _c1 = _U.getDouble(bytes, address + 8);
        double _c2 = _U.getDouble(bytes, address + 16);
        double _c3 = _U.getDouble(bytes, address + 24);
        double _c4 = _U.getDouble(bytes, _p1);
        double _c5 = _U.getDouble(bytes, _p1 + 8);
        double _c6 = _U.getDouble(bytes, _p1 + 16);
        double _c7 = _U.getDouble(bytes, _p1 + 24);
        double _c8 = _U.getDouble(bytes, _p2);
        double _c9 = _U.getDouble(bytes, _p2 + 8);
        double _c10 = _U.getDouble(bytes, _p2 + 16);
        double _c11 = _U.getDouble(bytes, _p2 + 24);
        double _c12 = _U.getDouble(bytes, _p3);
        double _c13 = _U.getDouble(bytes, _p3 + 8);
        double _c14 = _U.getDouble(bytes, _p3 + 16);
        double _c15 = _U.getDouble(bytes, _p3 + 24);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Double4x4 storeRMFloatUnsafe(Double4x4 self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.m00());
        _U.putFloat(address + 4, (float) self.m01());
        _U.putFloat(address + 8, (float) self.m02());
        _U.putFloat(address + 12, (float) self.m03());
        _U.putFloat(_p1, (float) self.m10());
        _U.putFloat(_p1 + 4, (float) self.m11());
        _U.putFloat(_p1 + 8, (float) self.m12());
        _U.putFloat(_p1 + 12, (float) self.m13());
        _U.putFloat(_p2, (float) self.m20());
        _U.putFloat(_p2 + 4, (float) self.m21());
        _U.putFloat(_p2 + 8, (float) self.m22());
        _U.putFloat(_p2 + 12, (float) self.m23());
        _U.putFloat(_p3, (float) self.m30());
        _U.putFloat(_p3 + 4, (float) self.m31());
        _U.putFloat(_p3 + 8, (float) self.m32());
        _U.putFloat(_p3 + 12, (float) self.m33());
        return self;
    }

    public Double4x4 storeRMFloatHeap(Double4x4 self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.m00());
        _U.putFloat(bytes, address + 4, (float) self.m01());
        _U.putFloat(bytes, address + 8, (float) self.m02());
        _U.putFloat(bytes, address + 12, (float) self.m03());
        _U.putFloat(bytes, _p1, (float) self.m10());
        _U.putFloat(bytes, _p1 + 4, (float) self.m11());
        _U.putFloat(bytes, _p1 + 8, (float) self.m12());
        _U.putFloat(bytes, _p1 + 12, (float) self.m13());
        _U.putFloat(bytes, _p2, (float) self.m20());
        _U.putFloat(bytes, _p2 + 4, (float) self.m21());
        _U.putFloat(bytes, _p2 + 8, (float) self.m22());
        _U.putFloat(bytes, _p2 + 12, (float) self.m23());
        _U.putFloat(bytes, _p3, (float) self.m30());
        _U.putFloat(bytes, _p3 + 4, (float) self.m31());
        _U.putFloat(bytes, _p3 + 8, (float) self.m32());
        _U.putFloat(bytes, _p3 + 12, (float) self.m33());
        return self;
    }

    public Double4x4 loadRMFloatUnsafe(long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(address);
        double _c1 = _U.getFloat(address + 4);
        double _c2 = _U.getFloat(address + 8);
        double _c3 = _U.getFloat(address + 12);
        double _c4 = _U.getFloat(_p1);
        double _c5 = _U.getFloat(_p1 + 4);
        double _c6 = _U.getFloat(_p1 + 8);
        double _c7 = _U.getFloat(_p1 + 12);
        double _c8 = _U.getFloat(_p2);
        double _c9 = _U.getFloat(_p2 + 4);
        double _c10 = _U.getFloat(_p2 + 8);
        double _c11 = _U.getFloat(_p2 + 12);
        double _c12 = _U.getFloat(_p3);
        double _c13 = _U.getFloat(_p3 + 4);
        double _c14 = _U.getFloat(_p3 + 8);
        double _c15 = _U.getFloat(_p3 + 12);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }

    public Double4x4 loadRMFloatHeap(byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(bytes, address);
        double _c1 = _U.getFloat(bytes, address + 4);
        double _c2 = _U.getFloat(bytes, address + 8);
        double _c3 = _U.getFloat(bytes, address + 12);
        double _c4 = _U.getFloat(bytes, _p1);
        double _c5 = _U.getFloat(bytes, _p1 + 4);
        double _c6 = _U.getFloat(bytes, _p1 + 8);
        double _c7 = _U.getFloat(bytes, _p1 + 12);
        double _c8 = _U.getFloat(bytes, _p2);
        double _c9 = _U.getFloat(bytes, _p2 + 4);
        double _c10 = _U.getFloat(bytes, _p2 + 8);
        double _c11 = _U.getFloat(bytes, _p2 + 12);
        double _c12 = _U.getFloat(bytes, _p3);
        double _c13 = _U.getFloat(bytes, _p3 + 4);
        double _c14 = _U.getFloat(bytes, _p3 + 8);
        double _c15 = _U.getFloat(bytes, _p3 + 12);
        return new Double4x4(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14, _c15);
    }
}
