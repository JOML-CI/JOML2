// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float2x2RawOpsUnsafe implements Float2x2RawOps {
    public Float2x2 storeCMUnsafe(Float2x2Impl self, long address) {
        U.putFloat(address + 0L, self.data[0]);
        U.putFloat(address + 4L, self.data[1]);
        U.putFloat(address + 8L, self.data[2]);
        U.putFloat(address + 12L, self.data[3]);
        return self;
    }
    public Float2x2 loadCMUnsafe(Float2x2Impl self, long address) {
        self.data[0] = U.getFloat(address + 0L);
        self.data[1] = U.getFloat(address + 4L);
        self.data[2] = U.getFloat(address + 8L);
        self.data[3] = U.getFloat(address + 12L);
        self.properties = self.determineProperties();
        return self;
    }
    public Float2x2 storeCMDoubleUnsafe(Float2x2Impl self, long address) {
        U.putDouble(address + 0L, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, self.data[2]);
        U.putDouble(address + 24L, self.data[3]);
        return self;
    }
    public Float2x2 loadCMDoubleUnsafe(Float2x2Impl self, long address) {
        self.data[0] = (float) U.getDouble(address + 0L);
        self.data[1] = (float) U.getDouble(address + 8L);
        self.data[2] = (float) U.getDouble(address + 16L);
        self.data[3] = (float) U.getDouble(address + 24L);
        self.properties = self.determineProperties();
        return self;
    }
    public Float2x2 storeRMUnsafe(Float2x2Impl self, long address) {
        U.putFloat(address + 0L, self.data[0]);
        U.putFloat(address + 4L, self.data[2]);
        U.putFloat(address + 8L, self.data[1]);
        U.putFloat(address + 12L, self.data[3]);
        return self;
    }
    public Float2x2 loadRMUnsafe(Float2x2Impl self, long address) {
        self.data[0] = U.getFloat(address + 0L);
        self.data[2] = U.getFloat(address + 4L);
        self.data[1] = U.getFloat(address + 8L);
        self.data[3] = U.getFloat(address + 12L);
        self.properties = self.determineProperties();
        return self;
    }
    public Float2x2 storeRMDoubleUnsafe(Float2x2Impl self, long address) {
        U.putDouble(address + 0L, self.data[0]);
        U.putDouble(address + 8L, self.data[2]);
        U.putDouble(address + 16L, self.data[1]);
        U.putDouble(address + 24L, self.data[3]);
        return self;
    }
    public Float2x2 loadRMDoubleUnsafe(Float2x2Impl self, long address) {
        self.data[0] = (float) U.getDouble(address + 0L);
        self.data[2] = (float) U.getDouble(address + 8L);
        self.data[1] = (float) U.getDouble(address + 16L);
        self.data[3] = (float) U.getDouble(address + 24L);
        self.properties = self.determineProperties();
        return self;
    }
    public Float2x2 storeCMUnsafe(Float2x2Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4, self.data[1]);
        U.putFloat(_p1, self.data[2]);
        U.putFloat(_p1 + 4, self.data[3]);
        return self;
    }
    public Float2x2 loadCMUnsafe(Float2x2Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4);
        self.data[2] = U.getFloat(_p1);
        self.data[3] = U.getFloat(_p1 + 4);
        self.properties = self.determineProperties();
        return self;
    }
    public Float2x2 storeCMDoubleUnsafe(Float2x2Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[1]);
        U.putDouble(_p1, self.data[2]);
        U.putDouble(_p1 + 8, self.data[3]);
        return self;
    }
    public Float2x2 loadCMDoubleUnsafe(Float2x2Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        self.data[0] = (float) U.getDouble(address);
        self.data[1] = (float) U.getDouble(address + 8);
        self.data[2] = (float) U.getDouble(_p1);
        self.data[3] = (float) U.getDouble(_p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public Float2x2 storeRMUnsafe(Float2x2Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4, self.data[2]);
        U.putFloat(_p1, self.data[1]);
        U.putFloat(_p1 + 4, self.data[3]);
        return self;
    }
    public Float2x2 loadRMUnsafe(Float2x2Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        self.data[0] = U.getFloat(address);
        self.data[2] = U.getFloat(address + 4);
        self.data[1] = U.getFloat(_p1);
        self.data[3] = U.getFloat(_p1 + 4);
        self.properties = self.determineProperties();
        return self;
    }
    public Float2x2 storeRMDoubleUnsafe(Float2x2Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[2]);
        U.putDouble(_p1, self.data[1]);
        U.putDouble(_p1 + 8, self.data[3]);
        return self;
    }
    public Float2x2 loadRMDoubleUnsafe(Float2x2Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        self.data[0] = (float) U.getDouble(address);
        self.data[2] = (float) U.getDouble(address + 8);
        self.data[1] = (float) U.getDouble(_p1);
        self.data[3] = (float) U.getDouble(_p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public Float2x2 storeCM3x3Unsafe(Float2x2Impl self, long address) {
        U.putFloat(address + 0L, self.data[0]);
        U.putFloat(address + 4L, self.data[1]);
        U.putFloat(address + 8L, 0.0f);
        U.putFloat(address + 12L, self.data[2]);
        U.putFloat(address + 16L, self.data[3]);
        U.putFloat(address + 20L, 0.0f);
        U.putFloat(address + 24L, 0.0f);
        U.putFloat(address + 28L, 0.0f);
        U.putFloat(address + 32L, 1.0f);
        return self;
    }
    public Float2x2 storeCM3x3DoubleUnsafe(Float2x2Impl self, long address) {
        U.putDouble(address + 0L, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, 0.0f);
        U.putDouble(address + 24L, self.data[2]);
        U.putDouble(address + 32L, self.data[3]);
        U.putDouble(address + 40L, 0.0f);
        U.putDouble(address + 48L, 0.0f);
        U.putDouble(address + 56L, 0.0f);
        U.putDouble(address + 64L, 1.0f);
        return self;
    }
    public Float2x2 storeRM3x3Unsafe(Float2x2Impl self, long address) {
        U.putFloat(address + 0L, self.data[0]);
        U.putFloat(address + 4L, self.data[2]);
        U.putFloat(address + 8L, 0.0f);
        U.putFloat(address + 12L, self.data[1]);
        U.putFloat(address + 16L, self.data[3]);
        U.putFloat(address + 20L, 0.0f);
        U.putFloat(address + 24L, 0.0f);
        U.putFloat(address + 28L, 0.0f);
        U.putFloat(address + 32L, 1.0f);
        return self;
    }
    public Float2x2 storeRM3x3DoubleUnsafe(Float2x2Impl self, long address) {
        U.putDouble(address + 0L, self.data[0]);
        U.putDouble(address + 8L, self.data[2]);
        U.putDouble(address + 16L, 0.0f);
        U.putDouble(address + 24L, self.data[1]);
        U.putDouble(address + 32L, self.data[3]);
        U.putDouble(address + 40L, 0.0f);
        U.putDouble(address + 48L, 0.0f);
        U.putDouble(address + 56L, 0.0f);
        U.putDouble(address + 64L, 1.0f);
        return self;
    }
    public Float2x2 storeCM4x4Unsafe(Float2x2Impl self, long address) {
        U.putFloat(address + 0L, self.data[0]);
        U.putFloat(address + 4L, self.data[1]);
        U.putFloat(address + 8L, 0.0f);
        U.putFloat(address + 12L, 0.0f);
        U.putFloat(address + 16L, self.data[2]);
        U.putFloat(address + 20L, self.data[3]);
        U.putFloat(address + 24L, 0.0f);
        U.putFloat(address + 28L, 0.0f);
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
    public Float2x2 storeCM4x4DoubleUnsafe(Float2x2Impl self, long address) {
        U.putDouble(address + 0L, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, 0.0f);
        U.putDouble(address + 24L, 0.0f);
        U.putDouble(address + 32L, self.data[2]);
        U.putDouble(address + 40L, self.data[3]);
        U.putDouble(address + 48L, 0.0f);
        U.putDouble(address + 56L, 0.0f);
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
    public Float2x2 storeRM4x4Unsafe(Float2x2Impl self, long address) {
        U.putFloat(address + 0L, self.data[0]);
        U.putFloat(address + 4L, self.data[2]);
        U.putFloat(address + 8L, 0.0f);
        U.putFloat(address + 12L, 0.0f);
        U.putFloat(address + 16L, self.data[1]);
        U.putFloat(address + 20L, self.data[3]);
        U.putFloat(address + 24L, 0.0f);
        U.putFloat(address + 28L, 0.0f);
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
    public Float2x2 storeRM4x4DoubleUnsafe(Float2x2Impl self, long address) {
        U.putDouble(address + 0L, self.data[0]);
        U.putDouble(address + 8L, self.data[2]);
        U.putDouble(address + 16L, 0.0f);
        U.putDouble(address + 24L, 0.0f);
        U.putDouble(address + 32L, self.data[1]);
        U.putDouble(address + 40L, self.data[3]);
        U.putDouble(address + 48L, 0.0f);
        U.putDouble(address + 56L, 0.0f);
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
