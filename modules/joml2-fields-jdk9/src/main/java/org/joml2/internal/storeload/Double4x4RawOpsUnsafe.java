// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double4x4RawOpsUnsafe implements Double4x4RawOps {
    public Double4x4 storeCMUnsafe(Double4x4Impl self, long address) {
        U.putDouble(address + 0L, self.m00);
        U.putDouble(address + 8L, self.m10);
        U.putDouble(address + 16L, self.m20);
        U.putDouble(address + 24L, self.m30);
        U.putDouble(address + 32L, self.m01);
        U.putDouble(address + 40L, self.m11);
        U.putDouble(address + 48L, self.m21);
        U.putDouble(address + 56L, self.m31);
        U.putDouble(address + 64L, self.m02);
        U.putDouble(address + 72L, self.m12);
        U.putDouble(address + 80L, self.m22);
        U.putDouble(address + 88L, self.m32);
        U.putDouble(address + 96L, self.m03);
        U.putDouble(address + 104L, self.m13);
        U.putDouble(address + 112L, self.m23);
        U.putDouble(address + 120L, self.m33);
        return self;
    }
    public Double4x4 loadCMUnsafe(Double4x4Impl self, long address) {
        self.m00 = U.getDouble(address + 0L);
        self.m10 = U.getDouble(address + 8L);
        self.m20 = U.getDouble(address + 16L);
        self.m30 = U.getDouble(address + 24L);
        self.m01 = U.getDouble(address + 32L);
        self.m11 = U.getDouble(address + 40L);
        self.m21 = U.getDouble(address + 48L);
        self.m31 = U.getDouble(address + 56L);
        self.m02 = U.getDouble(address + 64L);
        self.m12 = U.getDouble(address + 72L);
        self.m22 = U.getDouble(address + 80L);
        self.m32 = U.getDouble(address + 88L);
        self.m03 = U.getDouble(address + 96L);
        self.m13 = U.getDouble(address + 104L);
        self.m23 = U.getDouble(address + 112L);
        self.m33 = U.getDouble(address + 120L);
        self.properties = self.determineProperties();
        return self;
    }
    public Double4x4 storeCMFloatUnsafe(Double4x4Impl self, long address) {
        U.putFloat(address + 0L, (float) self.m00);
        U.putFloat(address + 4L, (float) self.m10);
        U.putFloat(address + 8L, (float) self.m20);
        U.putFloat(address + 12L, (float) self.m30);
        U.putFloat(address + 16L, (float) self.m01);
        U.putFloat(address + 20L, (float) self.m11);
        U.putFloat(address + 24L, (float) self.m21);
        U.putFloat(address + 28L, (float) self.m31);
        U.putFloat(address + 32L, (float) self.m02);
        U.putFloat(address + 36L, (float) self.m12);
        U.putFloat(address + 40L, (float) self.m22);
        U.putFloat(address + 44L, (float) self.m32);
        U.putFloat(address + 48L, (float) self.m03);
        U.putFloat(address + 52L, (float) self.m13);
        U.putFloat(address + 56L, (float) self.m23);
        U.putFloat(address + 60L, (float) self.m33);
        return self;
    }
    public Double4x4 loadCMFloatUnsafe(Double4x4Impl self, long address) {
        self.m00 = U.getFloat(address + 0L);
        self.m10 = U.getFloat(address + 4L);
        self.m20 = U.getFloat(address + 8L);
        self.m30 = U.getFloat(address + 12L);
        self.m01 = U.getFloat(address + 16L);
        self.m11 = U.getFloat(address + 20L);
        self.m21 = U.getFloat(address + 24L);
        self.m31 = U.getFloat(address + 28L);
        self.m02 = U.getFloat(address + 32L);
        self.m12 = U.getFloat(address + 36L);
        self.m22 = U.getFloat(address + 40L);
        self.m32 = U.getFloat(address + 44L);
        self.m03 = U.getFloat(address + 48L);
        self.m13 = U.getFloat(address + 52L);
        self.m23 = U.getFloat(address + 56L);
        self.m33 = U.getFloat(address + 60L);
        self.properties = self.determineProperties();
        return self;
    }
    public Double4x4 storeRMUnsafe(Double4x4Impl self, long address) {
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
        U.putDouble(address + 96L, self.m30);
        U.putDouble(address + 104L, self.m31);
        U.putDouble(address + 112L, self.m32);
        U.putDouble(address + 120L, self.m33);
        return self;
    }
    public Double4x4 loadRMUnsafe(Double4x4Impl self, long address) {
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
        self.m30 = U.getDouble(address + 96L);
        self.m31 = U.getDouble(address + 104L);
        self.m32 = U.getDouble(address + 112L);
        self.m33 = U.getDouble(address + 120L);
        self.properties = self.determineProperties();
        return self;
    }
    public Double4x4 storeRMFloatUnsafe(Double4x4Impl self, long address) {
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
        U.putFloat(address + 48L, (float) self.m30);
        U.putFloat(address + 52L, (float) self.m31);
        U.putFloat(address + 56L, (float) self.m32);
        U.putFloat(address + 60L, (float) self.m33);
        return self;
    }
    public Double4x4 loadRMFloatUnsafe(Double4x4Impl self, long address) {
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
        self.m30 = U.getFloat(address + 48L);
        self.m31 = U.getFloat(address + 52L);
        self.m32 = U.getFloat(address + 56L);
        self.m33 = U.getFloat(address + 60L);
        self.properties = self.determineProperties();
        return self;
    }
    public Double4x4 storeCMUnsafe(Double4x4Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8, self.m10);
        U.putDouble(address + 16, self.m20);
        U.putDouble(address + 24, self.m30);
        U.putDouble(_p1, self.m01);
        U.putDouble(_p1 + 8, self.m11);
        U.putDouble(_p1 + 16, self.m21);
        U.putDouble(_p1 + 24, self.m31);
        U.putDouble(_p2, self.m02);
        U.putDouble(_p2 + 8, self.m12);
        U.putDouble(_p2 + 16, self.m22);
        U.putDouble(_p2 + 24, self.m32);
        U.putDouble(_p3, self.m03);
        U.putDouble(_p3 + 8, self.m13);
        U.putDouble(_p3 + 16, self.m23);
        U.putDouble(_p3 + 24, self.m33);
        return self;
    }
    public Double4x4 loadCMUnsafe(Double4x4Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.m00 = U.getDouble(address);
        self.m10 = U.getDouble(address + 8);
        self.m20 = U.getDouble(address + 16);
        self.m30 = U.getDouble(address + 24);
        self.m01 = U.getDouble(_p1);
        self.m11 = U.getDouble(_p1 + 8);
        self.m21 = U.getDouble(_p1 + 16);
        self.m31 = U.getDouble(_p1 + 24);
        self.m02 = U.getDouble(_p2);
        self.m12 = U.getDouble(_p2 + 8);
        self.m22 = U.getDouble(_p2 + 16);
        self.m32 = U.getDouble(_p2 + 24);
        self.m03 = U.getDouble(_p3);
        self.m13 = U.getDouble(_p3 + 8);
        self.m23 = U.getDouble(_p3 + 16);
        self.m33 = U.getDouble(_p3 + 24);
        self.properties = self.determineProperties();
        return self;
    }
    public Double4x4 storeCMFloatUnsafe(Double4x4Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        U.putFloat(address, (float) self.m00);
        U.putFloat(address + 4, (float) self.m10);
        U.putFloat(address + 8, (float) self.m20);
        U.putFloat(address + 12, (float) self.m30);
        U.putFloat(_p1, (float) self.m01);
        U.putFloat(_p1 + 4, (float) self.m11);
        U.putFloat(_p1 + 8, (float) self.m21);
        U.putFloat(_p1 + 12, (float) self.m31);
        U.putFloat(_p2, (float) self.m02);
        U.putFloat(_p2 + 4, (float) self.m12);
        U.putFloat(_p2 + 8, (float) self.m22);
        U.putFloat(_p2 + 12, (float) self.m32);
        U.putFloat(_p3, (float) self.m03);
        U.putFloat(_p3 + 4, (float) self.m13);
        U.putFloat(_p3 + 8, (float) self.m23);
        U.putFloat(_p3 + 12, (float) self.m33);
        return self;
    }
    public Double4x4 loadCMFloatUnsafe(Double4x4Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.m00 = U.getFloat(address);
        self.m10 = U.getFloat(address + 4);
        self.m20 = U.getFloat(address + 8);
        self.m30 = U.getFloat(address + 12);
        self.m01 = U.getFloat(_p1);
        self.m11 = U.getFloat(_p1 + 4);
        self.m21 = U.getFloat(_p1 + 8);
        self.m31 = U.getFloat(_p1 + 12);
        self.m02 = U.getFloat(_p2);
        self.m12 = U.getFloat(_p2 + 4);
        self.m22 = U.getFloat(_p2 + 8);
        self.m32 = U.getFloat(_p2 + 12);
        self.m03 = U.getFloat(_p3);
        self.m13 = U.getFloat(_p3 + 4);
        self.m23 = U.getFloat(_p3 + 8);
        self.m33 = U.getFloat(_p3 + 12);
        self.properties = self.determineProperties();
        return self;
    }
    public Double4x4 storeRMUnsafe(Double4x4Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
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
        U.putDouble(_p3, self.m30);
        U.putDouble(_p3 + 8, self.m31);
        U.putDouble(_p3 + 16, self.m32);
        U.putDouble(_p3 + 24, self.m33);
        return self;
    }
    public Double4x4 loadRMUnsafe(Double4x4Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
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
        self.m30 = U.getDouble(_p3);
        self.m31 = U.getDouble(_p3 + 8);
        self.m32 = U.getDouble(_p3 + 16);
        self.m33 = U.getDouble(_p3 + 24);
        self.properties = self.determineProperties();
        return self;
    }
    public Double4x4 storeRMFloatUnsafe(Double4x4Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
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
        U.putFloat(_p3, (float) self.m30);
        U.putFloat(_p3 + 4, (float) self.m31);
        U.putFloat(_p3 + 8, (float) self.m32);
        U.putFloat(_p3 + 12, (float) self.m33);
        return self;
    }
    public Double4x4 loadRMFloatUnsafe(Double4x4Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
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
        self.m30 = U.getFloat(_p3);
        self.m31 = U.getFloat(_p3 + 4);
        self.m32 = U.getFloat(_p3 + 8);
        self.m33 = U.getFloat(_p3 + 12);
        self.properties = self.determineProperties();
        return self;
    }
}
