// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float3x4RawOpsUnsafe implements Float3x4RawOps {
    public Float3x4 storeCMUnsafe(Float3x4Impl self, long address) {
        U.putFloat(address + 0L, self.m00);
        U.putFloat(address + 4L, self.m10);
        U.putFloat(address + 8L, self.m20);
        U.putFloat(address + 12L, self.m01);
        U.putFloat(address + 16L, self.m11);
        U.putFloat(address + 20L, self.m21);
        U.putFloat(address + 24L, self.m02);
        U.putFloat(address + 28L, self.m12);
        U.putFloat(address + 32L, self.m22);
        U.putFloat(address + 36L, self.m03);
        U.putFloat(address + 40L, self.m13);
        U.putFloat(address + 44L, self.m23);
        return self;
    }
    public Float3x4 loadCMUnsafe(Float3x4Impl self, long address) {
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
    public Float3x4 storeCMDoubleUnsafe(Float3x4Impl self, long address) {
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
    public Float3x4 loadCMDoubleUnsafe(Float3x4Impl self, long address) {
        self.m00 = (float) U.getDouble(address + 0L);
        self.m10 = (float) U.getDouble(address + 8L);
        self.m20 = (float) U.getDouble(address + 16L);
        self.m01 = (float) U.getDouble(address + 24L);
        self.m11 = (float) U.getDouble(address + 32L);
        self.m21 = (float) U.getDouble(address + 40L);
        self.m02 = (float) U.getDouble(address + 48L);
        self.m12 = (float) U.getDouble(address + 56L);
        self.m22 = (float) U.getDouble(address + 64L);
        self.m03 = (float) U.getDouble(address + 72L);
        self.m13 = (float) U.getDouble(address + 80L);
        self.m23 = (float) U.getDouble(address + 88L);
        self.properties = self.determineProperties();
        return self;
    }
    public Float3x4 storeRMUnsafe(Float3x4Impl self, long address) {
        U.putFloat(address + 0L, self.m00);
        U.putFloat(address + 4L, self.m01);
        U.putFloat(address + 8L, self.m02);
        U.putFloat(address + 12L, self.m03);
        U.putFloat(address + 16L, self.m10);
        U.putFloat(address + 20L, self.m11);
        U.putFloat(address + 24L, self.m12);
        U.putFloat(address + 28L, self.m13);
        U.putFloat(address + 32L, self.m20);
        U.putFloat(address + 36L, self.m21);
        U.putFloat(address + 40L, self.m22);
        U.putFloat(address + 44L, self.m23);
        return self;
    }
    public Float3x4 loadRMUnsafe(Float3x4Impl self, long address) {
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
    public Float3x4 storeRMDoubleUnsafe(Float3x4Impl self, long address) {
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
    public Float3x4 loadRMDoubleUnsafe(Float3x4Impl self, long address) {
        self.m00 = (float) U.getDouble(address + 0L);
        self.m01 = (float) U.getDouble(address + 8L);
        self.m02 = (float) U.getDouble(address + 16L);
        self.m03 = (float) U.getDouble(address + 24L);
        self.m10 = (float) U.getDouble(address + 32L);
        self.m11 = (float) U.getDouble(address + 40L);
        self.m12 = (float) U.getDouble(address + 48L);
        self.m13 = (float) U.getDouble(address + 56L);
        self.m20 = (float) U.getDouble(address + 64L);
        self.m21 = (float) U.getDouble(address + 72L);
        self.m22 = (float) U.getDouble(address + 80L);
        self.m23 = (float) U.getDouble(address + 88L);
        self.properties = self.determineProperties();
        return self;
    }
    public Float3x4 storeCMUnsafe(Float3x4Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4, self.m10);
        U.putFloat(address + 8, self.m20);
        U.putFloat(_p1, self.m01);
        U.putFloat(_p1 + 4, self.m11);
        U.putFloat(_p1 + 8, self.m21);
        U.putFloat(_p2, self.m02);
        U.putFloat(_p2 + 4, self.m12);
        U.putFloat(_p2 + 8, self.m22);
        U.putFloat(_p3, self.m03);
        U.putFloat(_p3 + 4, self.m13);
        U.putFloat(_p3 + 8, self.m23);
        return self;
    }
    public Float3x4 loadCMUnsafe(Float3x4Impl self, long address, int stride) {
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
    public Float3x4 storeCMDoubleUnsafe(Float3x4Impl self, long address, int stride) {
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
    public Float3x4 loadCMDoubleUnsafe(Float3x4Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.m00 = (float) U.getDouble(address);
        self.m10 = (float) U.getDouble(address + 8);
        self.m20 = (float) U.getDouble(address + 16);
        self.m01 = (float) U.getDouble(_p1);
        self.m11 = (float) U.getDouble(_p1 + 8);
        self.m21 = (float) U.getDouble(_p1 + 16);
        self.m02 = (float) U.getDouble(_p2);
        self.m12 = (float) U.getDouble(_p2 + 8);
        self.m22 = (float) U.getDouble(_p2 + 16);
        self.m03 = (float) U.getDouble(_p3);
        self.m13 = (float) U.getDouble(_p3 + 8);
        self.m23 = (float) U.getDouble(_p3 + 16);
        self.properties = self.determineProperties();
        return self;
    }
    public Float3x4 storeRMUnsafe(Float3x4Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4, self.m01);
        U.putFloat(address + 8, self.m02);
        U.putFloat(address + 12, self.m03);
        U.putFloat(_p1, self.m10);
        U.putFloat(_p1 + 4, self.m11);
        U.putFloat(_p1 + 8, self.m12);
        U.putFloat(_p1 + 12, self.m13);
        U.putFloat(_p2, self.m20);
        U.putFloat(_p2 + 4, self.m21);
        U.putFloat(_p2 + 8, self.m22);
        U.putFloat(_p2 + 12, self.m23);
        return self;
    }
    public Float3x4 loadRMUnsafe(Float3x4Impl self, long address, int stride) {
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
    public Float3x4 storeRMDoubleUnsafe(Float3x4Impl self, long address, int stride) {
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
    public Float3x4 loadRMDoubleUnsafe(Float3x4Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.m00 = (float) U.getDouble(address);
        self.m01 = (float) U.getDouble(address + 8);
        self.m02 = (float) U.getDouble(address + 16);
        self.m03 = (float) U.getDouble(address + 24);
        self.m10 = (float) U.getDouble(_p1);
        self.m11 = (float) U.getDouble(_p1 + 8);
        self.m12 = (float) U.getDouble(_p1 + 16);
        self.m13 = (float) U.getDouble(_p1 + 24);
        self.m20 = (float) U.getDouble(_p2);
        self.m21 = (float) U.getDouble(_p2 + 8);
        self.m22 = (float) U.getDouble(_p2 + 16);
        self.m23 = (float) U.getDouble(_p2 + 24);
        self.properties = self.determineProperties();
        return self;
    }
    public Float3x4 storeCM4x4Unsafe(Float3x4Impl self, long address) {
        U.putFloat(address + 0L, self.m00);
        U.putFloat(address + 4L, self.m10);
        U.putFloat(address + 8L, self.m20);
        U.putFloat(address + 12L, 0.0f);
        U.putFloat(address + 16L, self.m01);
        U.putFloat(address + 20L, self.m11);
        U.putFloat(address + 24L, self.m21);
        U.putFloat(address + 28L, 0.0f);
        U.putFloat(address + 32L, self.m02);
        U.putFloat(address + 36L, self.m12);
        U.putFloat(address + 40L, self.m22);
        U.putFloat(address + 44L, 0.0f);
        U.putFloat(address + 48L, self.m03);
        U.putFloat(address + 52L, self.m13);
        U.putFloat(address + 56L, self.m23);
        U.putFloat(address + 60L, 1.0f);
        return self;
    }
    public Float3x4 storeCM4x4DoubleUnsafe(Float3x4Impl self, long address) {
        U.putDouble(address + 0L, self.m00);
        U.putDouble(address + 8L, self.m10);
        U.putDouble(address + 16L, self.m20);
        U.putDouble(address + 24L, 0.0f);
        U.putDouble(address + 32L, self.m01);
        U.putDouble(address + 40L, self.m11);
        U.putDouble(address + 48L, self.m21);
        U.putDouble(address + 56L, 0.0f);
        U.putDouble(address + 64L, self.m02);
        U.putDouble(address + 72L, self.m12);
        U.putDouble(address + 80L, self.m22);
        U.putDouble(address + 88L, 0.0f);
        U.putDouble(address + 96L, self.m03);
        U.putDouble(address + 104L, self.m13);
        U.putDouble(address + 112L, self.m23);
        U.putDouble(address + 120L, 1.0f);
        return self;
    }
    public Float3x4 storeRM4x4Unsafe(Float3x4Impl self, long address) {
        U.putFloat(address + 0L, self.m00);
        U.putFloat(address + 4L, self.m01);
        U.putFloat(address + 8L, self.m02);
        U.putFloat(address + 12L, self.m03);
        U.putFloat(address + 16L, self.m10);
        U.putFloat(address + 20L, self.m11);
        U.putFloat(address + 24L, self.m12);
        U.putFloat(address + 28L, self.m13);
        U.putFloat(address + 32L, self.m20);
        U.putFloat(address + 36L, self.m21);
        U.putFloat(address + 40L, self.m22);
        U.putFloat(address + 44L, self.m23);
        U.putFloat(address + 48L, 0.0f);
        U.putFloat(address + 52L, 0.0f);
        U.putFloat(address + 56L, 0.0f);
        U.putFloat(address + 60L, 1.0f);
        return self;
    }
    public Float3x4 storeRM4x4DoubleUnsafe(Float3x4Impl self, long address) {
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
        U.putDouble(address + 96L, 0.0f);
        U.putDouble(address + 104L, 0.0f);
        U.putDouble(address + 112L, 0.0f);
        U.putDouble(address + 120L, 1.0f);
        return self;
    }
}
