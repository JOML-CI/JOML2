// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double3x4RawOpsUnsafe implements Double3x4RawOps {
    public Double3x4 storeCMUnsafe(Double3x4Impl self, long address) {
        U.putDouble(address + 0L, self.m00);
        U.putDouble(address + 8L, self.m10);
        U.putDouble(address + 16L, self.m20);
        U.putDouble(address + 24L, self.m01);
        U.putDouble(address + 32L, self.m11);
        U.putDouble(address + 40L, self.m21);
        U.putDouble(address + 48L, self.m02);
        U.putDouble(address + 56L, self.m12);
        U.putDouble(address + 64L, self.m22);
        U.putDouble(address + 72L, self.m03);
        U.putDouble(address + 80L, self.m13);
        U.putDouble(address + 88L, self.m23);
        return self;
    }
    public Double3x4 loadCMUnsafe(Double3x4Impl self, long address) {
        self.m00 = U.getDouble(address + 0L);
        self.m10 = U.getDouble(address + 8L);
        self.m20 = U.getDouble(address + 16L);
        self.m01 = U.getDouble(address + 24L);
        self.m11 = U.getDouble(address + 32L);
        self.m21 = U.getDouble(address + 40L);
        self.m02 = U.getDouble(address + 48L);
        self.m12 = U.getDouble(address + 56L);
        self.m22 = U.getDouble(address + 64L);
        self.m03 = U.getDouble(address + 72L);
        self.m13 = U.getDouble(address + 80L);
        self.m23 = U.getDouble(address + 88L);
        self.properties = self.determineProperties();
        return self;
    }
    public Double3x4 storeCMFloatUnsafe(Double3x4Impl self, long address) {
        U.putFloat(address + 0L, (float) self.m00);
        U.putFloat(address + 4L, (float) self.m10);
        U.putFloat(address + 8L, (float) self.m20);
        U.putFloat(address + 12L, (float) self.m01);
        U.putFloat(address + 16L, (float) self.m11);
        U.putFloat(address + 20L, (float) self.m21);
        U.putFloat(address + 24L, (float) self.m02);
        U.putFloat(address + 28L, (float) self.m12);
        U.putFloat(address + 32L, (float) self.m22);
        U.putFloat(address + 36L, (float) self.m03);
        U.putFloat(address + 40L, (float) self.m13);
        U.putFloat(address + 44L, (float) self.m23);
        return self;
    }
    public Double3x4 loadCMFloatUnsafe(Double3x4Impl self, long address) {
        self.m00 = U.getFloat(address + 0L);
        self.m10 = U.getFloat(address + 4L);
        self.m20 = U.getFloat(address + 8L);
        self.m01 = U.getFloat(address + 12L);
        self.m11 = U.getFloat(address + 16L);
        self.m21 = U.getFloat(address + 20L);
        self.m02 = U.getFloat(address + 24L);
        self.m12 = U.getFloat(address + 28L);
        self.m22 = U.getFloat(address + 32L);
        self.m03 = U.getFloat(address + 36L);
        self.m13 = U.getFloat(address + 40L);
        self.m23 = U.getFloat(address + 44L);
        self.properties = self.determineProperties();
        return self;
    }
    public Double3x4 storeRMUnsafe(Double3x4Impl self, long address) {
        U.putDouble(address + 0L, self.m00);
        U.putDouble(address + 8L, self.m01);
        U.putDouble(address + 16L, self.m02);
        U.putDouble(address + 24L, self.m03);
        U.putDouble(address + 32L, self.m10);
        U.putDouble(address + 40L, self.m11);
        U.putDouble(address + 48L, self.m12);
        U.putDouble(address + 56L, self.m13);
        U.putDouble(address + 64L, self.m20);
        U.putDouble(address + 72L, self.m21);
        U.putDouble(address + 80L, self.m22);
        U.putDouble(address + 88L, self.m23);
        return self;
    }
    public Double3x4 loadRMUnsafe(Double3x4Impl self, long address) {
        self.m00 = U.getDouble(address + 0L);
        self.m01 = U.getDouble(address + 8L);
        self.m02 = U.getDouble(address + 16L);
        self.m03 = U.getDouble(address + 24L);
        self.m10 = U.getDouble(address + 32L);
        self.m11 = U.getDouble(address + 40L);
        self.m12 = U.getDouble(address + 48L);
        self.m13 = U.getDouble(address + 56L);
        self.m20 = U.getDouble(address + 64L);
        self.m21 = U.getDouble(address + 72L);
        self.m22 = U.getDouble(address + 80L);
        self.m23 = U.getDouble(address + 88L);
        self.properties = self.determineProperties();
        return self;
    }
    public Double3x4 storeRMFloatUnsafe(Double3x4Impl self, long address) {
        U.putFloat(address + 0L, (float) self.m00);
        U.putFloat(address + 4L, (float) self.m01);
        U.putFloat(address + 8L, (float) self.m02);
        U.putFloat(address + 12L, (float) self.m03);
        U.putFloat(address + 16L, (float) self.m10);
        U.putFloat(address + 20L, (float) self.m11);
        U.putFloat(address + 24L, (float) self.m12);
        U.putFloat(address + 28L, (float) self.m13);
        U.putFloat(address + 32L, (float) self.m20);
        U.putFloat(address + 36L, (float) self.m21);
        U.putFloat(address + 40L, (float) self.m22);
        U.putFloat(address + 44L, (float) self.m23);
        return self;
    }
    public Double3x4 loadRMFloatUnsafe(Double3x4Impl self, long address) {
        self.m00 = U.getFloat(address + 0L);
        self.m01 = U.getFloat(address + 4L);
        self.m02 = U.getFloat(address + 8L);
        self.m03 = U.getFloat(address + 12L);
        self.m10 = U.getFloat(address + 16L);
        self.m11 = U.getFloat(address + 20L);
        self.m12 = U.getFloat(address + 24L);
        self.m13 = U.getFloat(address + 28L);
        self.m20 = U.getFloat(address + 32L);
        self.m21 = U.getFloat(address + 36L);
        self.m22 = U.getFloat(address + 40L);
        self.m23 = U.getFloat(address + 44L);
        self.properties = self.determineProperties();
        return self;
    }
    public Double3x4 storeCMUnsafe(Double3x4Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8, self.m10);
        U.putDouble(address + 16, self.m20);
        U.putDouble(_p1, self.m01);
        U.putDouble(_p1 + 8, self.m11);
        U.putDouble(_p1 + 16, self.m21);
        U.putDouble(_p2, self.m02);
        U.putDouble(_p2 + 8, self.m12);
        U.putDouble(_p2 + 16, self.m22);
        U.putDouble(_p3, self.m03);
        U.putDouble(_p3 + 8, self.m13);
        U.putDouble(_p3 + 16, self.m23);
        return self;
    }
    public Double3x4 loadCMUnsafe(Double3x4Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.m00 = U.getDouble(address);
        self.m10 = U.getDouble(address + 8);
        self.m20 = U.getDouble(address + 16);
        self.m01 = U.getDouble(_p1);
        self.m11 = U.getDouble(_p1 + 8);
        self.m21 = U.getDouble(_p1 + 16);
        self.m02 = U.getDouble(_p2);
        self.m12 = U.getDouble(_p2 + 8);
        self.m22 = U.getDouble(_p2 + 16);
        self.m03 = U.getDouble(_p3);
        self.m13 = U.getDouble(_p3 + 8);
        self.m23 = U.getDouble(_p3 + 16);
        self.properties = self.determineProperties();
        return self;
    }
    public Double3x4 storeCMFloatUnsafe(Double3x4Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        U.putFloat(address, (float) self.m00);
        U.putFloat(address + 4, (float) self.m10);
        U.putFloat(address + 8, (float) self.m20);
        U.putFloat(_p1, (float) self.m01);
        U.putFloat(_p1 + 4, (float) self.m11);
        U.putFloat(_p1 + 8, (float) self.m21);
        U.putFloat(_p2, (float) self.m02);
        U.putFloat(_p2 + 4, (float) self.m12);
        U.putFloat(_p2 + 8, (float) self.m22);
        U.putFloat(_p3, (float) self.m03);
        U.putFloat(_p3 + 4, (float) self.m13);
        U.putFloat(_p3 + 8, (float) self.m23);
        return self;
    }
    public Double3x4 loadCMFloatUnsafe(Double3x4Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.m00 = U.getFloat(address);
        self.m10 = U.getFloat(address + 4);
        self.m20 = U.getFloat(address + 8);
        self.m01 = U.getFloat(_p1);
        self.m11 = U.getFloat(_p1 + 4);
        self.m21 = U.getFloat(_p1 + 8);
        self.m02 = U.getFloat(_p2);
        self.m12 = U.getFloat(_p2 + 4);
        self.m22 = U.getFloat(_p2 + 8);
        self.m03 = U.getFloat(_p3);
        self.m13 = U.getFloat(_p3 + 4);
        self.m23 = U.getFloat(_p3 + 8);
        self.properties = self.determineProperties();
        return self;
    }
    public Double3x4 storeRMUnsafe(Double3x4Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8, self.m01);
        U.putDouble(address + 16, self.m02);
        U.putDouble(address + 24, self.m03);
        U.putDouble(_p1, self.m10);
        U.putDouble(_p1 + 8, self.m11);
        U.putDouble(_p1 + 16, self.m12);
        U.putDouble(_p1 + 24, self.m13);
        U.putDouble(_p2, self.m20);
        U.putDouble(_p2 + 8, self.m21);
        U.putDouble(_p2 + 16, self.m22);
        U.putDouble(_p2 + 24, self.m23);
        return self;
    }
    public Double3x4 loadRMUnsafe(Double3x4Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = U.getDouble(address);
        self.m01 = U.getDouble(address + 8);
        self.m02 = U.getDouble(address + 16);
        self.m03 = U.getDouble(address + 24);
        self.m10 = U.getDouble(_p1);
        self.m11 = U.getDouble(_p1 + 8);
        self.m12 = U.getDouble(_p1 + 16);
        self.m13 = U.getDouble(_p1 + 24);
        self.m20 = U.getDouble(_p2);
        self.m21 = U.getDouble(_p2 + 8);
        self.m22 = U.getDouble(_p2 + 16);
        self.m23 = U.getDouble(_p2 + 24);
        self.properties = self.determineProperties();
        return self;
    }
    public Double3x4 storeRMFloatUnsafe(Double3x4Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, (float) self.m00);
        U.putFloat(address + 4, (float) self.m01);
        U.putFloat(address + 8, (float) self.m02);
        U.putFloat(address + 12, (float) self.m03);
        U.putFloat(_p1, (float) self.m10);
        U.putFloat(_p1 + 4, (float) self.m11);
        U.putFloat(_p1 + 8, (float) self.m12);
        U.putFloat(_p1 + 12, (float) self.m13);
        U.putFloat(_p2, (float) self.m20);
        U.putFloat(_p2 + 4, (float) self.m21);
        U.putFloat(_p2 + 8, (float) self.m22);
        U.putFloat(_p2 + 12, (float) self.m23);
        return self;
    }
    public Double3x4 loadRMFloatUnsafe(Double3x4Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = U.getFloat(address);
        self.m01 = U.getFloat(address + 4);
        self.m02 = U.getFloat(address + 8);
        self.m03 = U.getFloat(address + 12);
        self.m10 = U.getFloat(_p1);
        self.m11 = U.getFloat(_p1 + 4);
        self.m12 = U.getFloat(_p1 + 8);
        self.m13 = U.getFloat(_p1 + 12);
        self.m20 = U.getFloat(_p2);
        self.m21 = U.getFloat(_p2 + 4);
        self.m22 = U.getFloat(_p2 + 8);
        self.m23 = U.getFloat(_p2 + 12);
        self.properties = self.determineProperties();
        return self;
    }
    public Double3x4 storeCM4x4Unsafe(Double3x4Impl self, long address) {
        U.putDouble(address + 0L, self.m00);
        U.putDouble(address + 8L, self.m10);
        U.putDouble(address + 16L, self.m20);
        U.putDouble(address + 24L, 0.0);
        U.putDouble(address + 32L, self.m01);
        U.putDouble(address + 40L, self.m11);
        U.putDouble(address + 48L, self.m21);
        U.putDouble(address + 56L, 0.0);
        U.putDouble(address + 64L, self.m02);
        U.putDouble(address + 72L, self.m12);
        U.putDouble(address + 80L, self.m22);
        U.putDouble(address + 88L, 0.0);
        U.putDouble(address + 96L, self.m03);
        U.putDouble(address + 104L, self.m13);
        U.putDouble(address + 112L, self.m23);
        U.putDouble(address + 120L, 1.0);
        return self;
    }
    public Double3x4 storeCM4x4FloatUnsafe(Double3x4Impl self, long address) {
        U.putFloat(address + 0L, (float) self.m00);
        U.putFloat(address + 4L, (float) self.m10);
        U.putFloat(address + 8L, (float) self.m20);
        U.putFloat(address + 12L, (float) 0.0);
        U.putFloat(address + 16L, (float) self.m01);
        U.putFloat(address + 20L, (float) self.m11);
        U.putFloat(address + 24L, (float) self.m21);
        U.putFloat(address + 28L, (float) 0.0);
        U.putFloat(address + 32L, (float) self.m02);
        U.putFloat(address + 36L, (float) self.m12);
        U.putFloat(address + 40L, (float) self.m22);
        U.putFloat(address + 44L, (float) 0.0);
        U.putFloat(address + 48L, (float) self.m03);
        U.putFloat(address + 52L, (float) self.m13);
        U.putFloat(address + 56L, (float) self.m23);
        U.putFloat(address + 60L, (float) 1.0);
        return self;
    }
    public Double3x4 storeRM4x4Unsafe(Double3x4Impl self, long address) {
        U.putDouble(address + 0L, self.m00);
        U.putDouble(address + 8L, self.m01);
        U.putDouble(address + 16L, self.m02);
        U.putDouble(address + 24L, self.m03);
        U.putDouble(address + 32L, self.m10);
        U.putDouble(address + 40L, self.m11);
        U.putDouble(address + 48L, self.m12);
        U.putDouble(address + 56L, self.m13);
        U.putDouble(address + 64L, self.m20);
        U.putDouble(address + 72L, self.m21);
        U.putDouble(address + 80L, self.m22);
        U.putDouble(address + 88L, self.m23);
        U.putDouble(address + 96L, 0.0);
        U.putDouble(address + 104L, 0.0);
        U.putDouble(address + 112L, 0.0);
        U.putDouble(address + 120L, 1.0);
        return self;
    }
    public Double3x4 storeRM4x4FloatUnsafe(Double3x4Impl self, long address) {
        U.putFloat(address + 0L, (float) self.m00);
        U.putFloat(address + 4L, (float) self.m01);
        U.putFloat(address + 8L, (float) self.m02);
        U.putFloat(address + 12L, (float) self.m03);
        U.putFloat(address + 16L, (float) self.m10);
        U.putFloat(address + 20L, (float) self.m11);
        U.putFloat(address + 24L, (float) self.m12);
        U.putFloat(address + 28L, (float) self.m13);
        U.putFloat(address + 32L, (float) self.m20);
        U.putFloat(address + 36L, (float) self.m21);
        U.putFloat(address + 40L, (float) self.m22);
        U.putFloat(address + 44L, (float) self.m23);
        U.putFloat(address + 48L, (float) 0.0);
        U.putFloat(address + 52L, (float) 0.0);
        U.putFloat(address + 56L, (float) 0.0);
        U.putFloat(address + 60L, (float) 1.0);
        return self;
    }
}
