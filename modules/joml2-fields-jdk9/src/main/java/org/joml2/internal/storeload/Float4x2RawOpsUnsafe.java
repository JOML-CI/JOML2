// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float4x2RawOpsUnsafe implements Float4x2RawOps {
    public Float4x2 storeCMUnsafe(Float4x2Impl self, long address) {
        U.putFloat(address + 0L, self.m00);
        U.putFloat(address + 4L, self.m10);
        U.putFloat(address + 8L, self.m20);
        U.putFloat(address + 12L, self.m30);
        U.putFloat(address + 16L, self.m01);
        U.putFloat(address + 20L, self.m11);
        U.putFloat(address + 24L, self.m21);
        U.putFloat(address + 28L, self.m31);
        return self;
    }
    public Float4x2 loadCMUnsafe(Float4x2Impl self, long address) {
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
    public Float4x2 storeCMDoubleUnsafe(Float4x2Impl self, long address) {
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
    public Float4x2 loadCMDoubleUnsafe(Float4x2Impl self, long address) {
        self.m00 = (float) U.getDouble(address + 0L);
        self.m10 = (float) U.getDouble(address + 8L);
        self.m20 = (float) U.getDouble(address + 16L);
        self.m30 = (float) U.getDouble(address + 24L);
        self.m01 = (float) U.getDouble(address + 32L);
        self.m11 = (float) U.getDouble(address + 40L);
        self.m21 = (float) U.getDouble(address + 48L);
        self.m31 = (float) U.getDouble(address + 56L);
        return self;
    }
    public Float4x2 storeRMUnsafe(Float4x2Impl self, long address) {
        U.putFloat(address + 0L, self.m00);
        U.putFloat(address + 4L, self.m01);
        U.putFloat(address + 8L, self.m10);
        U.putFloat(address + 12L, self.m11);
        U.putFloat(address + 16L, self.m20);
        U.putFloat(address + 20L, self.m21);
        U.putFloat(address + 24L, self.m30);
        U.putFloat(address + 28L, self.m31);
        return self;
    }
    public Float4x2 loadRMUnsafe(Float4x2Impl self, long address) {
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
    public Float4x2 storeRMDoubleUnsafe(Float4x2Impl self, long address) {
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
    public Float4x2 loadRMDoubleUnsafe(Float4x2Impl self, long address) {
        self.m00 = (float) U.getDouble(address + 0L);
        self.m01 = (float) U.getDouble(address + 8L);
        self.m10 = (float) U.getDouble(address + 16L);
        self.m11 = (float) U.getDouble(address + 24L);
        self.m20 = (float) U.getDouble(address + 32L);
        self.m21 = (float) U.getDouble(address + 40L);
        self.m30 = (float) U.getDouble(address + 48L);
        self.m31 = (float) U.getDouble(address + 56L);
        return self;
    }
    public Float4x2 storeCMUnsafe(Float4x2Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4, self.m10);
        U.putFloat(address + 8, self.m20);
        U.putFloat(address + 12, self.m30);
        U.putFloat(_p1, self.m01);
        U.putFloat(_p1 + 4, self.m11);
        U.putFloat(_p1 + 8, self.m21);
        U.putFloat(_p1 + 12, self.m31);
        return self;
    }
    public Float4x2 loadCMUnsafe(Float4x2Impl self, long address, int stride) {
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
    public Float4x2 storeCMDoubleUnsafe(Float4x2Impl self, long address, int stride) {
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
    public Float4x2 loadCMDoubleUnsafe(Float4x2Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        self.m00 = (float) U.getDouble(address);
        self.m10 = (float) U.getDouble(address + 8);
        self.m20 = (float) U.getDouble(address + 16);
        self.m30 = (float) U.getDouble(address + 24);
        self.m01 = (float) U.getDouble(_p1);
        self.m11 = (float) U.getDouble(_p1 + 8);
        self.m21 = (float) U.getDouble(_p1 + 16);
        self.m31 = (float) U.getDouble(_p1 + 24);
        return self;
    }
    public Float4x2 storeRMUnsafe(Float4x2Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        U.putFloat(address, self.m00);
        U.putFloat(address + 4, self.m01);
        U.putFloat(_p1, self.m10);
        U.putFloat(_p1 + 4, self.m11);
        U.putFloat(_p2, self.m20);
        U.putFloat(_p2 + 4, self.m21);
        U.putFloat(_p3, self.m30);
        U.putFloat(_p3 + 4, self.m31);
        return self;
    }
    public Float4x2 loadRMUnsafe(Float4x2Impl self, long address, int stride) {
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
    public Float4x2 storeRMDoubleUnsafe(Float4x2Impl self, long address, int stride) {
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
    public Float4x2 loadRMDoubleUnsafe(Float4x2Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.m00 = (float) U.getDouble(address);
        self.m01 = (float) U.getDouble(address + 8);
        self.m10 = (float) U.getDouble(_p1);
        self.m11 = (float) U.getDouble(_p1 + 8);
        self.m20 = (float) U.getDouble(_p2);
        self.m21 = (float) U.getDouble(_p2 + 8);
        self.m30 = (float) U.getDouble(_p3);
        self.m31 = (float) U.getDouble(_p3 + 8);
        return self;
    }
}
