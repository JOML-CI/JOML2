// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float3x3RawOpsUnsafe implements Float3x3RawOps {
    public Float3x3 storeCMUnsafe(Float3x3Impl self, long address) {
        U.putFloat(address + 0L, self.data[0]);
        U.putFloat(address + 4L, self.data[1]);
        U.putFloat(address + 8L, self.data[2]);
        U.putFloat(address + 12L, self.data[3]);
        U.putFloat(address + 16L, self.data[4]);
        U.putFloat(address + 20L, self.data[5]);
        U.putFloat(address + 24L, self.data[6]);
        U.putFloat(address + 28L, self.data[7]);
        U.putFloat(address + 32L, self.data[8]);
        return self;
    }
    public Float3x3 loadCMUnsafe(Float3x3Impl self, long address) {
        self.data[0] = U.getFloat(address + 0L);
        self.data[1] = U.getFloat(address + 4L);
        self.data[2] = U.getFloat(address + 8L);
        self.data[3] = U.getFloat(address + 12L);
        self.data[4] = U.getFloat(address + 16L);
        self.data[5] = U.getFloat(address + 20L);
        self.data[6] = U.getFloat(address + 24L);
        self.data[7] = U.getFloat(address + 28L);
        self.data[8] = U.getFloat(address + 32L);
        self.properties = self.determineProperties();
        return self;
    }
    public Float3x3 storeCMDoubleUnsafe(Float3x3Impl self, long address) {
        U.putDouble(address + 0L, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, self.data[2]);
        U.putDouble(address + 24L, self.data[3]);
        U.putDouble(address + 32L, self.data[4]);
        U.putDouble(address + 40L, self.data[5]);
        U.putDouble(address + 48L, self.data[6]);
        U.putDouble(address + 56L, self.data[7]);
        U.putDouble(address + 64L, self.data[8]);
        return self;
    }
    public Float3x3 loadCMDoubleUnsafe(Float3x3Impl self, long address) {
        self.data[0] = (float) U.getDouble(address + 0L);
        self.data[1] = (float) U.getDouble(address + 8L);
        self.data[2] = (float) U.getDouble(address + 16L);
        self.data[3] = (float) U.getDouble(address + 24L);
        self.data[4] = (float) U.getDouble(address + 32L);
        self.data[5] = (float) U.getDouble(address + 40L);
        self.data[6] = (float) U.getDouble(address + 48L);
        self.data[7] = (float) U.getDouble(address + 56L);
        self.data[8] = (float) U.getDouble(address + 64L);
        self.properties = self.determineProperties();
        return self;
    }
    public Float3x3 storeRMUnsafe(Float3x3Impl self, long address) {
        U.putFloat(address + 0L, self.data[0]);
        U.putFloat(address + 4L, self.data[3]);
        U.putFloat(address + 8L, self.data[6]);
        U.putFloat(address + 12L, self.data[1]);
        U.putFloat(address + 16L, self.data[4]);
        U.putFloat(address + 20L, self.data[7]);
        U.putFloat(address + 24L, self.data[2]);
        U.putFloat(address + 28L, self.data[5]);
        U.putFloat(address + 32L, self.data[8]);
        return self;
    }
    public Float3x3 loadRMUnsafe(Float3x3Impl self, long address) {
        self.data[0] = U.getFloat(address + 0L);
        self.data[3] = U.getFloat(address + 4L);
        self.data[6] = U.getFloat(address + 8L);
        self.data[1] = U.getFloat(address + 12L);
        self.data[4] = U.getFloat(address + 16L);
        self.data[7] = U.getFloat(address + 20L);
        self.data[2] = U.getFloat(address + 24L);
        self.data[5] = U.getFloat(address + 28L);
        self.data[8] = U.getFloat(address + 32L);
        self.properties = self.determineProperties();
        return self;
    }
    public Float3x3 storeRMDoubleUnsafe(Float3x3Impl self, long address) {
        U.putDouble(address + 0L, self.data[0]);
        U.putDouble(address + 8L, self.data[3]);
        U.putDouble(address + 16L, self.data[6]);
        U.putDouble(address + 24L, self.data[1]);
        U.putDouble(address + 32L, self.data[4]);
        U.putDouble(address + 40L, self.data[7]);
        U.putDouble(address + 48L, self.data[2]);
        U.putDouble(address + 56L, self.data[5]);
        U.putDouble(address + 64L, self.data[8]);
        return self;
    }
    public Float3x3 loadRMDoubleUnsafe(Float3x3Impl self, long address) {
        self.data[0] = (float) U.getDouble(address + 0L);
        self.data[3] = (float) U.getDouble(address + 8L);
        self.data[6] = (float) U.getDouble(address + 16L);
        self.data[1] = (float) U.getDouble(address + 24L);
        self.data[4] = (float) U.getDouble(address + 32L);
        self.data[7] = (float) U.getDouble(address + 40L);
        self.data[2] = (float) U.getDouble(address + 48L);
        self.data[5] = (float) U.getDouble(address + 56L);
        self.data[8] = (float) U.getDouble(address + 64L);
        self.properties = self.determineProperties();
        return self;
    }
    public Float3x3 storeCMUnsafe(Float3x3Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4, self.data[1]);
        U.putFloat(address + 8, self.data[2]);
        U.putFloat(_p1, self.data[3]);
        U.putFloat(_p1 + 4, self.data[4]);
        U.putFloat(_p1 + 8, self.data[5]);
        U.putFloat(_p2, self.data[6]);
        U.putFloat(_p2 + 4, self.data[7]);
        U.putFloat(_p2 + 8, self.data[8]);
        return self;
    }
    public Float3x3 loadCMUnsafe(Float3x3Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4);
        self.data[2] = U.getFloat(address + 8);
        self.data[3] = U.getFloat(_p1);
        self.data[4] = U.getFloat(_p1 + 4);
        self.data[5] = U.getFloat(_p1 + 8);
        self.data[6] = U.getFloat(_p2);
        self.data[7] = U.getFloat(_p2 + 4);
        self.data[8] = U.getFloat(_p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public Float3x3 storeCMDoubleUnsafe(Float3x3Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[1]);
        U.putDouble(address + 16, self.data[2]);
        U.putDouble(_p1, self.data[3]);
        U.putDouble(_p1 + 8, self.data[4]);
        U.putDouble(_p1 + 16, self.data[5]);
        U.putDouble(_p2, self.data[6]);
        U.putDouble(_p2 + 8, self.data[7]);
        U.putDouble(_p2 + 16, self.data[8]);
        return self;
    }
    public Float3x3 loadCMDoubleUnsafe(Float3x3Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = (float) U.getDouble(address);
        self.data[1] = (float) U.getDouble(address + 8);
        self.data[2] = (float) U.getDouble(address + 16);
        self.data[3] = (float) U.getDouble(_p1);
        self.data[4] = (float) U.getDouble(_p1 + 8);
        self.data[5] = (float) U.getDouble(_p1 + 16);
        self.data[6] = (float) U.getDouble(_p2);
        self.data[7] = (float) U.getDouble(_p2 + 8);
        self.data[8] = (float) U.getDouble(_p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }
    public Float3x3 storeRMUnsafe(Float3x3Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4, self.data[3]);
        U.putFloat(address + 8, self.data[6]);
        U.putFloat(_p1, self.data[1]);
        U.putFloat(_p1 + 4, self.data[4]);
        U.putFloat(_p1 + 8, self.data[7]);
        U.putFloat(_p2, self.data[2]);
        U.putFloat(_p2 + 4, self.data[5]);
        U.putFloat(_p2 + 8, self.data[8]);
        return self;
    }
    public Float3x3 loadRMUnsafe(Float3x3Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = U.getFloat(address);
        self.data[3] = U.getFloat(address + 4);
        self.data[6] = U.getFloat(address + 8);
        self.data[1] = U.getFloat(_p1);
        self.data[4] = U.getFloat(_p1 + 4);
        self.data[7] = U.getFloat(_p1 + 8);
        self.data[2] = U.getFloat(_p2);
        self.data[5] = U.getFloat(_p2 + 4);
        self.data[8] = U.getFloat(_p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public Float3x3 storeRMDoubleUnsafe(Float3x3Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[3]);
        U.putDouble(address + 16, self.data[6]);
        U.putDouble(_p1, self.data[1]);
        U.putDouble(_p1 + 8, self.data[4]);
        U.putDouble(_p1 + 16, self.data[7]);
        U.putDouble(_p2, self.data[2]);
        U.putDouble(_p2 + 8, self.data[5]);
        U.putDouble(_p2 + 16, self.data[8]);
        return self;
    }
    public Float3x3 loadRMDoubleUnsafe(Float3x3Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = (float) U.getDouble(address);
        self.data[3] = (float) U.getDouble(address + 8);
        self.data[6] = (float) U.getDouble(address + 16);
        self.data[1] = (float) U.getDouble(_p1);
        self.data[4] = (float) U.getDouble(_p1 + 8);
        self.data[7] = (float) U.getDouble(_p1 + 16);
        self.data[2] = (float) U.getDouble(_p2);
        self.data[5] = (float) U.getDouble(_p2 + 8);
        self.data[8] = (float) U.getDouble(_p2 + 16);
        self.properties = self.determineProperties();
        return self;
    }
    public Float3x3 storeCM4x4Unsafe(Float3x3Impl self, long address) {
        U.putFloat(address + 0L, self.data[0]);
        U.putFloat(address + 4L, self.data[1]);
        U.putFloat(address + 8L, self.data[2]);
        U.putFloat(address + 12L, 0.0f);
        U.putFloat(address + 16L, self.data[3]);
        U.putFloat(address + 20L, self.data[4]);
        U.putFloat(address + 24L, self.data[5]);
        U.putFloat(address + 28L, 0.0f);
        U.putFloat(address + 32L, self.data[6]);
        U.putFloat(address + 36L, self.data[7]);
        U.putFloat(address + 40L, self.data[8]);
        U.putFloat(address + 44L, 0.0f);
        U.putFloat(address + 48L, 0.0f);
        U.putFloat(address + 52L, 0.0f);
        U.putFloat(address + 56L, 0.0f);
        U.putFloat(address + 60L, 1.0f);
        return self;
    }
    public Float3x3 storeCM4x4DoubleUnsafe(Float3x3Impl self, long address) {
        U.putDouble(address + 0L, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, self.data[2]);
        U.putDouble(address + 24L, 0.0f);
        U.putDouble(address + 32L, self.data[3]);
        U.putDouble(address + 40L, self.data[4]);
        U.putDouble(address + 48L, self.data[5]);
        U.putDouble(address + 56L, 0.0f);
        U.putDouble(address + 64L, self.data[6]);
        U.putDouble(address + 72L, self.data[7]);
        U.putDouble(address + 80L, self.data[8]);
        U.putDouble(address + 88L, 0.0f);
        U.putDouble(address + 96L, 0.0f);
        U.putDouble(address + 104L, 0.0f);
        U.putDouble(address + 112L, 0.0f);
        U.putDouble(address + 120L, 1.0f);
        return self;
    }
    public Float3x3 storeRM4x4Unsafe(Float3x3Impl self, long address) {
        U.putFloat(address + 0L, self.data[0]);
        U.putFloat(address + 4L, self.data[3]);
        U.putFloat(address + 8L, self.data[6]);
        U.putFloat(address + 12L, 0.0f);
        U.putFloat(address + 16L, self.data[1]);
        U.putFloat(address + 20L, self.data[4]);
        U.putFloat(address + 24L, self.data[7]);
        U.putFloat(address + 28L, 0.0f);
        U.putFloat(address + 32L, self.data[2]);
        U.putFloat(address + 36L, self.data[5]);
        U.putFloat(address + 40L, self.data[8]);
        U.putFloat(address + 44L, 0.0f);
        U.putFloat(address + 48L, 0.0f);
        U.putFloat(address + 52L, 0.0f);
        U.putFloat(address + 56L, 0.0f);
        U.putFloat(address + 60L, 1.0f);
        return self;
    }
    public Float3x3 storeRM4x4DoubleUnsafe(Float3x3Impl self, long address) {
        U.putDouble(address + 0L, self.data[0]);
        U.putDouble(address + 8L, self.data[3]);
        U.putDouble(address + 16L, self.data[6]);
        U.putDouble(address + 24L, 0.0f);
        U.putDouble(address + 32L, self.data[1]);
        U.putDouble(address + 40L, self.data[4]);
        U.putDouble(address + 48L, self.data[7]);
        U.putDouble(address + 56L, 0.0f);
        U.putDouble(address + 64L, self.data[2]);
        U.putDouble(address + 72L, self.data[5]);
        U.putDouble(address + 80L, self.data[8]);
        U.putDouble(address + 88L, 0.0f);
        U.putDouble(address + 96L, 0.0f);
        U.putDouble(address + 104L, 0.0f);
        U.putDouble(address + 112L, 0.0f);
        U.putDouble(address + 120L, 1.0f);
        return self;
    }
}
