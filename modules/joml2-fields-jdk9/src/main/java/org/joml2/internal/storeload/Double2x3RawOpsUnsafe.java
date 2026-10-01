// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double2x3RawOpsUnsafe implements Double2x3RawOps {
    public Double2x3 storeCMUnsafe(Double2x3Impl self, long address) {
        U.putDouble(address + 0L, self.m00);
        U.putDouble(address + 8L, self.m10);
        U.putDouble(address + 16L, self.m01);
        U.putDouble(address + 24L, self.m11);
        U.putDouble(address + 32L, self.m02);
        U.putDouble(address + 40L, self.m12);
        return self;
    }
    public Double2x3 loadCMUnsafe(Double2x3Impl self, long address) {
        self.m00 = U.getDouble(address + 0L);
        self.m10 = U.getDouble(address + 8L);
        self.m01 = U.getDouble(address + 16L);
        self.m11 = U.getDouble(address + 24L);
        self.m02 = U.getDouble(address + 32L);
        self.m12 = U.getDouble(address + 40L);
        self.properties = self.determineProperties();
        return self;
    }
    public Double2x3 storeCMFloatUnsafe(Double2x3Impl self, long address) {
        U.putFloat(address + 0L, (float) self.m00);
        U.putFloat(address + 4L, (float) self.m10);
        U.putFloat(address + 8L, (float) self.m01);
        U.putFloat(address + 12L, (float) self.m11);
        U.putFloat(address + 16L, (float) self.m02);
        U.putFloat(address + 20L, (float) self.m12);
        return self;
    }
    public Double2x3 loadCMFloatUnsafe(Double2x3Impl self, long address) {
        self.m00 = U.getFloat(address + 0L);
        self.m10 = U.getFloat(address + 4L);
        self.m01 = U.getFloat(address + 8L);
        self.m11 = U.getFloat(address + 12L);
        self.m02 = U.getFloat(address + 16L);
        self.m12 = U.getFloat(address + 20L);
        self.properties = self.determineProperties();
        return self;
    }
    public Double2x3 storeRMUnsafe(Double2x3Impl self, long address) {
        U.putDouble(address + 0L, self.m00);
        U.putDouble(address + 8L, self.m01);
        U.putDouble(address + 16L, self.m02);
        U.putDouble(address + 24L, self.m10);
        U.putDouble(address + 32L, self.m11);
        U.putDouble(address + 40L, self.m12);
        return self;
    }
    public Double2x3 loadRMUnsafe(Double2x3Impl self, long address) {
        self.m00 = U.getDouble(address + 0L);
        self.m01 = U.getDouble(address + 8L);
        self.m02 = U.getDouble(address + 16L);
        self.m10 = U.getDouble(address + 24L);
        self.m11 = U.getDouble(address + 32L);
        self.m12 = U.getDouble(address + 40L);
        self.properties = self.determineProperties();
        return self;
    }
    public Double2x3 storeRMFloatUnsafe(Double2x3Impl self, long address) {
        U.putFloat(address + 0L, (float) self.m00);
        U.putFloat(address + 4L, (float) self.m01);
        U.putFloat(address + 8L, (float) self.m02);
        U.putFloat(address + 12L, (float) self.m10);
        U.putFloat(address + 16L, (float) self.m11);
        U.putFloat(address + 20L, (float) self.m12);
        return self;
    }
    public Double2x3 loadRMFloatUnsafe(Double2x3Impl self, long address) {
        self.m00 = U.getFloat(address + 0L);
        self.m01 = U.getFloat(address + 4L);
        self.m02 = U.getFloat(address + 8L);
        self.m10 = U.getFloat(address + 12L);
        self.m11 = U.getFloat(address + 16L);
        self.m12 = U.getFloat(address + 20L);
        self.properties = self.determineProperties();
        return self;
    }
    public Double2x3 storeCMUnsafe(Double2x3Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8, self.m10);
        U.putDouble(_p1, self.m01);
        U.putDouble(_p1 + 8, self.m11);
        U.putDouble(_p2, self.m02);
        U.putDouble(_p2 + 8, self.m12);
        return self;
    }
    public Double2x3 loadCMUnsafe(Double2x3Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = U.getDouble(address);
        self.m10 = U.getDouble(address + 8);
        self.m01 = U.getDouble(_p1);
        self.m11 = U.getDouble(_p1 + 8);
        self.m02 = U.getDouble(_p2);
        self.m12 = U.getDouble(_p2 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public Double2x3 storeCMFloatUnsafe(Double2x3Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, (float) self.m00);
        U.putFloat(address + 4, (float) self.m10);
        U.putFloat(_p1, (float) self.m01);
        U.putFloat(_p1 + 4, (float) self.m11);
        U.putFloat(_p2, (float) self.m02);
        U.putFloat(_p2 + 4, (float) self.m12);
        return self;
    }
    public Double2x3 loadCMFloatUnsafe(Double2x3Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = U.getFloat(address);
        self.m10 = U.getFloat(address + 4);
        self.m01 = U.getFloat(_p1);
        self.m11 = U.getFloat(_p1 + 4);
        self.m02 = U.getFloat(_p2);
        self.m12 = U.getFloat(_p2 + 4);
        self.properties = self.determineProperties();
        return self;
    }
    public Double2x3 storeRMUnsafe(Double2x3Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8, self.m01);
        U.putDouble(address + 16, self.m02);
        U.putDouble(_p1, self.m10);
        U.putDouble(_p1 + 8, self.m11);
        U.putDouble(_p1 + 16, self.m12);
        return self;
    }
    public Double2x3 loadRMUnsafe(Double2x3Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        self.m00 = U.getDouble(address);
        self.m01 = U.getDouble(address + 8);
        self.m02 = U.getDouble(address + 16);
        self.m10 = U.getDouble(_p1);
        self.m11 = U.getDouble(_p1 + 8);
        self.m12 = U.getDouble(_p1 + 16);
        self.properties = self.determineProperties();
        return self;
    }
    public Double2x3 storeRMFloatUnsafe(Double2x3Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        U.putFloat(address, (float) self.m00);
        U.putFloat(address + 4, (float) self.m01);
        U.putFloat(address + 8, (float) self.m02);
        U.putFloat(_p1, (float) self.m10);
        U.putFloat(_p1 + 4, (float) self.m11);
        U.putFloat(_p1 + 8, (float) self.m12);
        return self;
    }
    public Double2x3 loadRMFloatUnsafe(Double2x3Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        self.m00 = U.getFloat(address);
        self.m01 = U.getFloat(address + 4);
        self.m02 = U.getFloat(address + 8);
        self.m10 = U.getFloat(_p1);
        self.m11 = U.getFloat(_p1 + 4);
        self.m12 = U.getFloat(_p1 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public Double2x3 storeCM3x3Unsafe(Double2x3Impl self, long address) {
        U.putDouble(address + 0L, self.m00);
        U.putDouble(address + 8L, self.m10);
        U.putDouble(address + 16L, 0.0);
        U.putDouble(address + 24L, self.m01);
        U.putDouble(address + 32L, self.m11);
        U.putDouble(address + 40L, 0.0);
        U.putDouble(address + 48L, self.m02);
        U.putDouble(address + 56L, self.m12);
        U.putDouble(address + 64L, 1.0);
        return self;
    }
    public Double2x3 storeCM3x3FloatUnsafe(Double2x3Impl self, long address) {
        U.putFloat(address + 0L, (float) self.m00);
        U.putFloat(address + 4L, (float) self.m10);
        U.putFloat(address + 8L, (float) 0.0);
        U.putFloat(address + 12L, (float) self.m01);
        U.putFloat(address + 16L, (float) self.m11);
        U.putFloat(address + 20L, (float) 0.0);
        U.putFloat(address + 24L, (float) self.m02);
        U.putFloat(address + 28L, (float) self.m12);
        U.putFloat(address + 32L, (float) 1.0);
        return self;
    }
    public Double2x3 storeRM3x3Unsafe(Double2x3Impl self, long address) {
        U.putDouble(address + 0L, self.m00);
        U.putDouble(address + 8L, self.m01);
        U.putDouble(address + 16L, self.m02);
        U.putDouble(address + 24L, self.m10);
        U.putDouble(address + 32L, self.m11);
        U.putDouble(address + 40L, self.m12);
        U.putDouble(address + 48L, 0.0);
        U.putDouble(address + 56L, 0.0);
        U.putDouble(address + 64L, 1.0);
        return self;
    }
    public Double2x3 storeRM3x3FloatUnsafe(Double2x3Impl self, long address) {
        U.putFloat(address + 0L, (float) self.m00);
        U.putFloat(address + 4L, (float) self.m01);
        U.putFloat(address + 8L, (float) self.m02);
        U.putFloat(address + 12L, (float) self.m10);
        U.putFloat(address + 16L, (float) self.m11);
        U.putFloat(address + 20L, (float) self.m12);
        U.putFloat(address + 24L, (float) 0.0);
        U.putFloat(address + 28L, (float) 0.0);
        U.putFloat(address + 32L, (float) 1.0);
        return self;
    }
    public Double2x3 storeCM4x4Unsafe(Double2x3Impl self, long address) {
        U.putDouble(address + 0L, self.m00);
        U.putDouble(address + 8L, self.m10);
        U.putDouble(address + 16L, 0.0);
        U.putDouble(address + 24L, 0.0);
        U.putDouble(address + 32L, self.m01);
        U.putDouble(address + 40L, self.m11);
        U.putDouble(address + 48L, 0.0);
        U.putDouble(address + 56L, 0.0);
        U.putDouble(address + 64L, 0.0);
        U.putDouble(address + 72L, 0.0);
        U.putDouble(address + 80L, 1.0);
        U.putDouble(address + 88L, 0.0);
        U.putDouble(address + 96L, self.m02);
        U.putDouble(address + 104L, self.m12);
        U.putDouble(address + 112L, 0.0);
        U.putDouble(address + 120L, 1.0);
        return self;
    }
    public Double2x3 storeCM4x4FloatUnsafe(Double2x3Impl self, long address) {
        U.putFloat(address + 0L, (float) self.m00);
        U.putFloat(address + 4L, (float) self.m10);
        U.putFloat(address + 8L, (float) 0.0);
        U.putFloat(address + 12L, (float) 0.0);
        U.putFloat(address + 16L, (float) self.m01);
        U.putFloat(address + 20L, (float) self.m11);
        U.putFloat(address + 24L, (float) 0.0);
        U.putFloat(address + 28L, (float) 0.0);
        U.putFloat(address + 32L, (float) 0.0);
        U.putFloat(address + 36L, (float) 0.0);
        U.putFloat(address + 40L, (float) 1.0);
        U.putFloat(address + 44L, (float) 0.0);
        U.putFloat(address + 48L, (float) self.m02);
        U.putFloat(address + 52L, (float) self.m12);
        U.putFloat(address + 56L, (float) 0.0);
        U.putFloat(address + 60L, (float) 1.0);
        return self;
    }
    public Double2x3 storeRM4x4Unsafe(Double2x3Impl self, long address) {
        U.putDouble(address + 0L, self.m00);
        U.putDouble(address + 8L, self.m01);
        U.putDouble(address + 16L, 0.0);
        U.putDouble(address + 24L, self.m02);
        U.putDouble(address + 32L, self.m10);
        U.putDouble(address + 40L, self.m11);
        U.putDouble(address + 48L, 0.0);
        U.putDouble(address + 56L, self.m12);
        U.putDouble(address + 64L, 0.0);
        U.putDouble(address + 72L, 0.0);
        U.putDouble(address + 80L, 1.0);
        U.putDouble(address + 88L, 0.0);
        U.putDouble(address + 96L, 0.0);
        U.putDouble(address + 104L, 0.0);
        U.putDouble(address + 112L, 0.0);
        U.putDouble(address + 120L, 1.0);
        return self;
    }
    public Double2x3 storeRM4x4FloatUnsafe(Double2x3Impl self, long address) {
        U.putFloat(address + 0L, (float) self.m00);
        U.putFloat(address + 4L, (float) self.m01);
        U.putFloat(address + 8L, (float) 0.0);
        U.putFloat(address + 12L, (float) self.m02);
        U.putFloat(address + 16L, (float) self.m10);
        U.putFloat(address + 20L, (float) self.m11);
        U.putFloat(address + 24L, (float) 0.0);
        U.putFloat(address + 28L, (float) self.m12);
        U.putFloat(address + 32L, (float) 0.0);
        U.putFloat(address + 36L, (float) 0.0);
        U.putFloat(address + 40L, (float) 1.0);
        U.putFloat(address + 44L, (float) 0.0);
        U.putFloat(address + 48L, (float) 0.0);
        U.putFloat(address + 52L, (float) 0.0);
        U.putFloat(address + 56L, (float) 0.0);
        U.putFloat(address + 60L, (float) 1.0);
        return self;
    }
}
