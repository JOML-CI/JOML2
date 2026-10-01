// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float2x3RawOpsUnsafe implements Float2x3RawOps {
    public Float2x3 storeCMUnsafe(Float2x3 self, long address) {
        U.putFloat(address + 0L, self.m00());
        U.putFloat(address + 4L, self.m10());
        U.putFloat(address + 8L, self.m01());
        U.putFloat(address + 12L, self.m11());
        U.putFloat(address + 16L, self.m02());
        U.putFloat(address + 20L, self.m12());
        return self;
    }
    public Float2x3 loadCMUnsafe(long address) {
        float _c0 = U.getFloat(address + 0L);
        float _c3 = U.getFloat(address + 4L);
        float _c1 = U.getFloat(address + 8L);
        float _c4 = U.getFloat(address + 12L);
        float _c2 = U.getFloat(address + 16L);
        float _c5 = U.getFloat(address + 20L);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public Float2x3 storeCMDoubleUnsafe(Float2x3 self, long address) {
        U.putDouble(address + 0L, self.m00());
        U.putDouble(address + 8L, self.m10());
        U.putDouble(address + 16L, self.m01());
        U.putDouble(address + 24L, self.m11());
        U.putDouble(address + 32L, self.m02());
        U.putDouble(address + 40L, self.m12());
        return self;
    }
    public Float2x3 loadCMDoubleUnsafe(long address) {
        float _c0 = (float) U.getDouble(address + 0L);
        float _c3 = (float) U.getDouble(address + 8L);
        float _c1 = (float) U.getDouble(address + 16L);
        float _c4 = (float) U.getDouble(address + 24L);
        float _c2 = (float) U.getDouble(address + 32L);
        float _c5 = (float) U.getDouble(address + 40L);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public Float2x3 storeRMUnsafe(Float2x3 self, long address) {
        U.putFloat(address + 0L, self.m00());
        U.putFloat(address + 4L, self.m01());
        U.putFloat(address + 8L, self.m02());
        U.putFloat(address + 12L, self.m10());
        U.putFloat(address + 16L, self.m11());
        U.putFloat(address + 20L, self.m12());
        return self;
    }
    public Float2x3 loadRMUnsafe(long address) {
        float _c0 = U.getFloat(address + 0L);
        float _c1 = U.getFloat(address + 4L);
        float _c2 = U.getFloat(address + 8L);
        float _c3 = U.getFloat(address + 12L);
        float _c4 = U.getFloat(address + 16L);
        float _c5 = U.getFloat(address + 20L);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public Float2x3 storeRMDoubleUnsafe(Float2x3 self, long address) {
        U.putDouble(address + 0L, self.m00());
        U.putDouble(address + 8L, self.m01());
        U.putDouble(address + 16L, self.m02());
        U.putDouble(address + 24L, self.m10());
        U.putDouble(address + 32L, self.m11());
        U.putDouble(address + 40L, self.m12());
        return self;
    }
    public Float2x3 loadRMDoubleUnsafe(long address) {
        float _c0 = (float) U.getDouble(address + 0L);
        float _c1 = (float) U.getDouble(address + 8L);
        float _c2 = (float) U.getDouble(address + 16L);
        float _c3 = (float) U.getDouble(address + 24L);
        float _c4 = (float) U.getDouble(address + 32L);
        float _c5 = (float) U.getDouble(address + 40L);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public Float2x3 storeCMUnsafe(Float2x3 self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, self.m00());
        U.putFloat(address + 4, self.m10());
        U.putFloat(_p1, self.m01());
        U.putFloat(_p1 + 4, self.m11());
        U.putFloat(_p2, self.m02());
        U.putFloat(_p2 + 4, self.m12());
        return self;
    }
    public Float2x3 loadCMUnsafe(long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = U.getFloat(address);
        float _c3 = U.getFloat(address + 4);
        float _c1 = U.getFloat(_p1);
        float _c4 = U.getFloat(_p1 + 4);
        float _c2 = U.getFloat(_p2);
        float _c5 = U.getFloat(_p2 + 4);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public Float2x3 storeCMDoubleUnsafe(Float2x3 self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8, self.m10());
        U.putDouble(_p1, self.m01());
        U.putDouble(_p1 + 8, self.m11());
        U.putDouble(_p2, self.m02());
        U.putDouble(_p2 + 8, self.m12());
        return self;
    }
    public Float2x3 loadCMDoubleUnsafe(long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        float _c0 = (float) U.getDouble(address);
        float _c3 = (float) U.getDouble(address + 8);
        float _c1 = (float) U.getDouble(_p1);
        float _c4 = (float) U.getDouble(_p1 + 8);
        float _c2 = (float) U.getDouble(_p2);
        float _c5 = (float) U.getDouble(_p2 + 8);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public Float2x3 storeRMUnsafe(Float2x3 self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        U.putFloat(address, self.m00());
        U.putFloat(address + 4, self.m01());
        U.putFloat(address + 8, self.m02());
        U.putFloat(_p1, self.m10());
        U.putFloat(_p1 + 4, self.m11());
        U.putFloat(_p1 + 8, self.m12());
        return self;
    }
    public Float2x3 loadRMUnsafe(long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        float _c0 = U.getFloat(address);
        float _c1 = U.getFloat(address + 4);
        float _c2 = U.getFloat(address + 8);
        float _c3 = U.getFloat(_p1);
        float _c4 = U.getFloat(_p1 + 4);
        float _c5 = U.getFloat(_p1 + 8);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public Float2x3 storeRMDoubleUnsafe(Float2x3 self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8, self.m01());
        U.putDouble(address + 16, self.m02());
        U.putDouble(_p1, self.m10());
        U.putDouble(_p1 + 8, self.m11());
        U.putDouble(_p1 + 16, self.m12());
        return self;
    }
    public Float2x3 loadRMDoubleUnsafe(long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        float _c0 = (float) U.getDouble(address);
        float _c1 = (float) U.getDouble(address + 8);
        float _c2 = (float) U.getDouble(address + 16);
        float _c3 = (float) U.getDouble(_p1);
        float _c4 = (float) U.getDouble(_p1 + 8);
        float _c5 = (float) U.getDouble(_p1 + 16);
        return new Float2x3(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public Float2x3 storeCM3x3Unsafe(Float2x3 self, long address) {
        U.putFloat(address + 0L, self.m00());
        U.putFloat(address + 4L, self.m10());
        U.putFloat(address + 8L, 0.0f);
        U.putFloat(address + 12L, self.m01());
        U.putFloat(address + 16L, self.m11());
        U.putFloat(address + 20L, 0.0f);
        U.putFloat(address + 24L, self.m02());
        U.putFloat(address + 28L, self.m12());
        U.putFloat(address + 32L, 1.0f);
        return self;
    }
    public Float2x3 storeCM3x3DoubleUnsafe(Float2x3 self, long address) {
        U.putDouble(address + 0L, self.m00());
        U.putDouble(address + 8L, self.m10());
        U.putDouble(address + 16L, 0.0f);
        U.putDouble(address + 24L, self.m01());
        U.putDouble(address + 32L, self.m11());
        U.putDouble(address + 40L, 0.0f);
        U.putDouble(address + 48L, self.m02());
        U.putDouble(address + 56L, self.m12());
        U.putDouble(address + 64L, 1.0f);
        return self;
    }
    public Float2x3 storeRM3x3Unsafe(Float2x3 self, long address) {
        U.putFloat(address + 0L, self.m00());
        U.putFloat(address + 4L, self.m01());
        U.putFloat(address + 8L, self.m02());
        U.putFloat(address + 12L, self.m10());
        U.putFloat(address + 16L, self.m11());
        U.putFloat(address + 20L, self.m12());
        U.putFloat(address + 24L, 0.0f);
        U.putFloat(address + 28L, 0.0f);
        U.putFloat(address + 32L, 1.0f);
        return self;
    }
    public Float2x3 storeRM3x3DoubleUnsafe(Float2x3 self, long address) {
        U.putDouble(address + 0L, self.m00());
        U.putDouble(address + 8L, self.m01());
        U.putDouble(address + 16L, self.m02());
        U.putDouble(address + 24L, self.m10());
        U.putDouble(address + 32L, self.m11());
        U.putDouble(address + 40L, self.m12());
        U.putDouble(address + 48L, 0.0f);
        U.putDouble(address + 56L, 0.0f);
        U.putDouble(address + 64L, 1.0f);
        return self;
    }
    public Float2x3 storeCM4x4Unsafe(Float2x3 self, long address) {
        U.putFloat(address + 0L, self.m00());
        U.putFloat(address + 4L, self.m10());
        U.putFloat(address + 8L, 0.0f);
        U.putFloat(address + 12L, 0.0f);
        U.putFloat(address + 16L, self.m01());
        U.putFloat(address + 20L, self.m11());
        U.putFloat(address + 24L, 0.0f);
        U.putFloat(address + 28L, 0.0f);
        U.putFloat(address + 32L, 0.0f);
        U.putFloat(address + 36L, 0.0f);
        U.putFloat(address + 40L, 1.0f);
        U.putFloat(address + 44L, 0.0f);
        U.putFloat(address + 48L, self.m02());
        U.putFloat(address + 52L, self.m12());
        U.putFloat(address + 56L, 0.0f);
        U.putFloat(address + 60L, 1.0f);
        return self;
    }
    public Float2x3 storeCM4x4DoubleUnsafe(Float2x3 self, long address) {
        U.putDouble(address + 0L, self.m00());
        U.putDouble(address + 8L, self.m10());
        U.putDouble(address + 16L, 0.0f);
        U.putDouble(address + 24L, 0.0f);
        U.putDouble(address + 32L, self.m01());
        U.putDouble(address + 40L, self.m11());
        U.putDouble(address + 48L, 0.0f);
        U.putDouble(address + 56L, 0.0f);
        U.putDouble(address + 64L, 0.0f);
        U.putDouble(address + 72L, 0.0f);
        U.putDouble(address + 80L, 1.0f);
        U.putDouble(address + 88L, 0.0f);
        U.putDouble(address + 96L, self.m02());
        U.putDouble(address + 104L, self.m12());
        U.putDouble(address + 112L, 0.0f);
        U.putDouble(address + 120L, 1.0f);
        return self;
    }
    public Float2x3 storeRM4x4Unsafe(Float2x3 self, long address) {
        U.putFloat(address + 0L, self.m00());
        U.putFloat(address + 4L, self.m01());
        U.putFloat(address + 8L, 0.0f);
        U.putFloat(address + 12L, self.m02());
        U.putFloat(address + 16L, self.m10());
        U.putFloat(address + 20L, self.m11());
        U.putFloat(address + 24L, 0.0f);
        U.putFloat(address + 28L, self.m12());
        U.putFloat(address + 32L, 0.0f);
        U.putFloat(address + 36L, 0.0f);
        U.putFloat(address + 40L, 1.0f);
        U.putFloat(address + 44L, 0.0f);
        U.putFloat(address + 48L, 0.0f);
        U.putFloat(address + 52L, 0.0f);
        U.putFloat(address + 56L, 0.0f);
        U.putFloat(address + 60L, 1.0f);
        return self;
    }
    public Float2x3 storeRM4x4DoubleUnsafe(Float2x3 self, long address) {
        U.putDouble(address + 0L, self.m00());
        U.putDouble(address + 8L, self.m01());
        U.putDouble(address + 16L, 0.0f);
        U.putDouble(address + 24L, self.m02());
        U.putDouble(address + 32L, self.m10());
        U.putDouble(address + 40L, self.m11());
        U.putDouble(address + 48L, 0.0f);
        U.putDouble(address + 56L, self.m12());
        U.putDouble(address + 64L, 0.0f);
        U.putDouble(address + 72L, 0.0f);
        U.putDouble(address + 80L, 1.0f);
        U.putDouble(address + 88L, 0.0f);
        U.putDouble(address + 96L, 0.0f);
        U.putDouble(address + 104L, 0.0f);
        U.putDouble(address + 112L, 0.0f);
        U.putDouble(address + 120L, 1.0f);
        return self;
    }
}
