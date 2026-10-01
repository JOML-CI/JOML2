// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double4x2RawOpsUnsafe implements Double4x2RawOps {
    public Double4x2 storeCMUnsafe(Double4x2Impl self, long address) {
        U.putDouble(address + 0L, self.m00);
        U.putDouble(address + 8L, self.m10);
        U.putDouble(address + 16L, self.m20);
        U.putDouble(address + 24L, self.m30);
        U.putDouble(address + 32L, self.m01);
        U.putDouble(address + 40L, self.m11);
        U.putDouble(address + 48L, self.m21);
        U.putDouble(address + 56L, self.m31);
        return self;
    }
    public Double4x2 loadCMUnsafe(Double4x2Impl self, long address) {
        self.m00 = U.getDouble(address + 0L);
        self.m10 = U.getDouble(address + 8L);
        self.m20 = U.getDouble(address + 16L);
        self.m30 = U.getDouble(address + 24L);
        self.m01 = U.getDouble(address + 32L);
        self.m11 = U.getDouble(address + 40L);
        self.m21 = U.getDouble(address + 48L);
        self.m31 = U.getDouble(address + 56L);
        return self;
    }
    public Double4x2 storeCMFloatUnsafe(Double4x2Impl self, long address) {
        U.putFloat(address + 0L, (float) self.m00);
        U.putFloat(address + 4L, (float) self.m10);
        U.putFloat(address + 8L, (float) self.m20);
        U.putFloat(address + 12L, (float) self.m30);
        U.putFloat(address + 16L, (float) self.m01);
        U.putFloat(address + 20L, (float) self.m11);
        U.putFloat(address + 24L, (float) self.m21);
        U.putFloat(address + 28L, (float) self.m31);
        return self;
    }
    public Double4x2 loadCMFloatUnsafe(Double4x2Impl self, long address) {
        self.m00 = U.getFloat(address + 0L);
        self.m10 = U.getFloat(address + 4L);
        self.m20 = U.getFloat(address + 8L);
        self.m30 = U.getFloat(address + 12L);
        self.m01 = U.getFloat(address + 16L);
        self.m11 = U.getFloat(address + 20L);
        self.m21 = U.getFloat(address + 24L);
        self.m31 = U.getFloat(address + 28L);
        return self;
    }
    public Double4x2 storeRMUnsafe(Double4x2Impl self, long address) {
        U.putDouble(address + 0L, self.m00);
        U.putDouble(address + 8L, self.m01);
        U.putDouble(address + 16L, self.m10);
        U.putDouble(address + 24L, self.m11);
        U.putDouble(address + 32L, self.m20);
        U.putDouble(address + 40L, self.m21);
        U.putDouble(address + 48L, self.m30);
        U.putDouble(address + 56L, self.m31);
        return self;
    }
    public Double4x2 loadRMUnsafe(Double4x2Impl self, long address) {
        self.m00 = U.getDouble(address + 0L);
        self.m01 = U.getDouble(address + 8L);
        self.m10 = U.getDouble(address + 16L);
        self.m11 = U.getDouble(address + 24L);
        self.m20 = U.getDouble(address + 32L);
        self.m21 = U.getDouble(address + 40L);
        self.m30 = U.getDouble(address + 48L);
        self.m31 = U.getDouble(address + 56L);
        return self;
    }
    public Double4x2 storeRMFloatUnsafe(Double4x2Impl self, long address) {
        U.putFloat(address + 0L, (float) self.m00);
        U.putFloat(address + 4L, (float) self.m01);
        U.putFloat(address + 8L, (float) self.m10);
        U.putFloat(address + 12L, (float) self.m11);
        U.putFloat(address + 16L, (float) self.m20);
        U.putFloat(address + 20L, (float) self.m21);
        U.putFloat(address + 24L, (float) self.m30);
        U.putFloat(address + 28L, (float) self.m31);
        return self;
    }
    public Double4x2 loadRMFloatUnsafe(Double4x2Impl self, long address) {
        self.m00 = U.getFloat(address + 0L);
        self.m01 = U.getFloat(address + 4L);
        self.m10 = U.getFloat(address + 8L);
        self.m11 = U.getFloat(address + 12L);
        self.m20 = U.getFloat(address + 16L);
        self.m21 = U.getFloat(address + 20L);
        self.m30 = U.getFloat(address + 24L);
        self.m31 = U.getFloat(address + 28L);
        return self;
    }
    public Double4x2 storeCMUnsafe(Double4x2Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8, self.m10);
        U.putDouble(address + 16, self.m20);
        U.putDouble(address + 24, self.m30);
        U.putDouble(_p1, self.m01);
        U.putDouble(_p1 + 8, self.m11);
        U.putDouble(_p1 + 16, self.m21);
        U.putDouble(_p1 + 24, self.m31);
        return self;
    }
    public Double4x2 loadCMUnsafe(Double4x2Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        self.m00 = U.getDouble(address);
        self.m10 = U.getDouble(address + 8);
        self.m20 = U.getDouble(address + 16);
        self.m30 = U.getDouble(address + 24);
        self.m01 = U.getDouble(_p1);
        self.m11 = U.getDouble(_p1 + 8);
        self.m21 = U.getDouble(_p1 + 16);
        self.m31 = U.getDouble(_p1 + 24);
        return self;
    }
    public Double4x2 storeCMFloatUnsafe(Double4x2Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        U.putFloat(address, (float) self.m00);
        U.putFloat(address + 4, (float) self.m10);
        U.putFloat(address + 8, (float) self.m20);
        U.putFloat(address + 12, (float) self.m30);
        U.putFloat(_p1, (float) self.m01);
        U.putFloat(_p1 + 4, (float) self.m11);
        U.putFloat(_p1 + 8, (float) self.m21);
        U.putFloat(_p1 + 12, (float) self.m31);
        return self;
    }
    public Double4x2 loadCMFloatUnsafe(Double4x2Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        self.m00 = U.getFloat(address);
        self.m10 = U.getFloat(address + 4);
        self.m20 = U.getFloat(address + 8);
        self.m30 = U.getFloat(address + 12);
        self.m01 = U.getFloat(_p1);
        self.m11 = U.getFloat(_p1 + 4);
        self.m21 = U.getFloat(_p1 + 8);
        self.m31 = U.getFloat(_p1 + 12);
        return self;
    }
    public Double4x2 storeRMUnsafe(Double4x2Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        U.putDouble(address, self.m00);
        U.putDouble(address + 8, self.m01);
        U.putDouble(_p1, self.m10);
        U.putDouble(_p1 + 8, self.m11);
        U.putDouble(_p2, self.m20);
        U.putDouble(_p2 + 8, self.m21);
        U.putDouble(_p3, self.m30);
        U.putDouble(_p3 + 8, self.m31);
        return self;
    }
    public Double4x2 loadRMUnsafe(Double4x2Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.m00 = U.getDouble(address);
        self.m01 = U.getDouble(address + 8);
        self.m10 = U.getDouble(_p1);
        self.m11 = U.getDouble(_p1 + 8);
        self.m20 = U.getDouble(_p2);
        self.m21 = U.getDouble(_p2 + 8);
        self.m30 = U.getDouble(_p3);
        self.m31 = U.getDouble(_p3 + 8);
        return self;
    }
    public Double4x2 storeRMFloatUnsafe(Double4x2Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        U.putFloat(address, (float) self.m00);
        U.putFloat(address + 4, (float) self.m01);
        U.putFloat(_p1, (float) self.m10);
        U.putFloat(_p1 + 4, (float) self.m11);
        U.putFloat(_p2, (float) self.m20);
        U.putFloat(_p2 + 4, (float) self.m21);
        U.putFloat(_p3, (float) self.m30);
        U.putFloat(_p3 + 4, (float) self.m31);
        return self;
    }
    public Double4x2 loadRMFloatUnsafe(Double4x2Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.m00 = U.getFloat(address);
        self.m01 = U.getFloat(address + 4);
        self.m10 = U.getFloat(_p1);
        self.m11 = U.getFloat(_p1 + 4);
        self.m20 = U.getFloat(_p2);
        self.m21 = U.getFloat(_p2 + 4);
        self.m30 = U.getFloat(_p3);
        self.m31 = U.getFloat(_p3 + 4);
        return self;
    }
}
