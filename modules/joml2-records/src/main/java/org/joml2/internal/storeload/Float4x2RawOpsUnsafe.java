// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float4x2RawOpsUnsafe implements Float4x2RawOps {
    public Float4x2 storeCMUnsafe(Float4x2 self, long address) {
        U.putFloat(address + 0L, self.m00());
        U.putFloat(address + 4L, self.m10());
        U.putFloat(address + 8L, self.m20());
        U.putFloat(address + 12L, self.m30());
        U.putFloat(address + 16L, self.m01());
        U.putFloat(address + 20L, self.m11());
        U.putFloat(address + 24L, self.m21());
        U.putFloat(address + 28L, self.m31());
        return self;
    }
    public Float4x2 loadCMUnsafe(long address) {
        float _c0 = U.getFloat(address + 0L);
        float _c2 = U.getFloat(address + 4L);
        float _c4 = U.getFloat(address + 8L);
        float _c6 = U.getFloat(address + 12L);
        float _c1 = U.getFloat(address + 16L);
        float _c3 = U.getFloat(address + 20L);
        float _c5 = U.getFloat(address + 24L);
        float _c7 = U.getFloat(address + 28L);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public Float4x2 storeCMDoubleUnsafe(Float4x2 self, long address) {
        U.putDouble(address + 0L, self.m00());
        U.putDouble(address + 8L, self.m10());
        U.putDouble(address + 16L, self.m20());
        U.putDouble(address + 24L, self.m30());
        U.putDouble(address + 32L, self.m01());
        U.putDouble(address + 40L, self.m11());
        U.putDouble(address + 48L, self.m21());
        U.putDouble(address + 56L, self.m31());
        return self;
    }
    public Float4x2 loadCMDoubleUnsafe(long address) {
        float _c0 = (float) U.getDouble(address + 0L);
        float _c2 = (float) U.getDouble(address + 8L);
        float _c4 = (float) U.getDouble(address + 16L);
        float _c6 = (float) U.getDouble(address + 24L);
        float _c1 = (float) U.getDouble(address + 32L);
        float _c3 = (float) U.getDouble(address + 40L);
        float _c5 = (float) U.getDouble(address + 48L);
        float _c7 = (float) U.getDouble(address + 56L);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public Float4x2 storeRMUnsafe(Float4x2 self, long address) {
        U.putFloat(address + 0L, self.m00());
        U.putFloat(address + 4L, self.m01());
        U.putFloat(address + 8L, self.m10());
        U.putFloat(address + 12L, self.m11());
        U.putFloat(address + 16L, self.m20());
        U.putFloat(address + 20L, self.m21());
        U.putFloat(address + 24L, self.m30());
        U.putFloat(address + 28L, self.m31());
        return self;
    }
    public Float4x2 loadRMUnsafe(long address) {
        float _c0 = U.getFloat(address + 0L);
        float _c1 = U.getFloat(address + 4L);
        float _c2 = U.getFloat(address + 8L);
        float _c3 = U.getFloat(address + 12L);
        float _c4 = U.getFloat(address + 16L);
        float _c5 = U.getFloat(address + 20L);
        float _c6 = U.getFloat(address + 24L);
        float _c7 = U.getFloat(address + 28L);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public Float4x2 storeRMDoubleUnsafe(Float4x2 self, long address) {
        U.putDouble(address + 0L, self.m00());
        U.putDouble(address + 8L, self.m01());
        U.putDouble(address + 16L, self.m10());
        U.putDouble(address + 24L, self.m11());
        U.putDouble(address + 32L, self.m20());
        U.putDouble(address + 40L, self.m21());
        U.putDouble(address + 48L, self.m30());
        U.putDouble(address + 56L, self.m31());
        return self;
    }
    public Float4x2 loadRMDoubleUnsafe(long address) {
        float _c0 = (float) U.getDouble(address + 0L);
        float _c1 = (float) U.getDouble(address + 8L);
        float _c2 = (float) U.getDouble(address + 16L);
        float _c3 = (float) U.getDouble(address + 24L);
        float _c4 = (float) U.getDouble(address + 32L);
        float _c5 = (float) U.getDouble(address + 40L);
        float _c6 = (float) U.getDouble(address + 48L);
        float _c7 = (float) U.getDouble(address + 56L);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public Float4x2 storeCMUnsafe(Float4x2 self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        U.putFloat(address, self.m00());
        U.putFloat(address + 4, self.m10());
        U.putFloat(address + 8, self.m20());
        U.putFloat(address + 12, self.m30());
        U.putFloat(_p1, self.m01());
        U.putFloat(_p1 + 4, self.m11());
        U.putFloat(_p1 + 8, self.m21());
        U.putFloat(_p1 + 12, self.m31());
        return self;
    }
    public Float4x2 loadCMUnsafe(long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        float _c0 = U.getFloat(address);
        float _c2 = U.getFloat(address + 4);
        float _c4 = U.getFloat(address + 8);
        float _c6 = U.getFloat(address + 12);
        float _c1 = U.getFloat(_p1);
        float _c3 = U.getFloat(_p1 + 4);
        float _c5 = U.getFloat(_p1 + 8);
        float _c7 = U.getFloat(_p1 + 12);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public Float4x2 storeCMDoubleUnsafe(Float4x2 self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8, self.m10());
        U.putDouble(address + 16, self.m20());
        U.putDouble(address + 24, self.m30());
        U.putDouble(_p1, self.m01());
        U.putDouble(_p1 + 8, self.m11());
        U.putDouble(_p1 + 16, self.m21());
        U.putDouble(_p1 + 24, self.m31());
        return self;
    }
    public Float4x2 loadCMDoubleUnsafe(long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        float _c0 = (float) U.getDouble(address);
        float _c2 = (float) U.getDouble(address + 8);
        float _c4 = (float) U.getDouble(address + 16);
        float _c6 = (float) U.getDouble(address + 24);
        float _c1 = (float) U.getDouble(_p1);
        float _c3 = (float) U.getDouble(_p1 + 8);
        float _c5 = (float) U.getDouble(_p1 + 16);
        float _c7 = (float) U.getDouble(_p1 + 24);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public Float4x2 storeRMUnsafe(Float4x2 self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        U.putFloat(address, self.m00());
        U.putFloat(address + 4, self.m01());
        U.putFloat(_p1, self.m10());
        U.putFloat(_p1 + 4, self.m11());
        U.putFloat(_p2, self.m20());
        U.putFloat(_p2 + 4, self.m21());
        U.putFloat(_p3, self.m30());
        U.putFloat(_p3 + 4, self.m31());
        return self;
    }
    public Float4x2 loadRMUnsafe(long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        float _c0 = U.getFloat(address);
        float _c1 = U.getFloat(address + 4);
        float _c2 = U.getFloat(_p1);
        float _c3 = U.getFloat(_p1 + 4);
        float _c4 = U.getFloat(_p2);
        float _c5 = U.getFloat(_p2 + 4);
        float _c6 = U.getFloat(_p3);
        float _c7 = U.getFloat(_p3 + 4);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
    public Float4x2 storeRMDoubleUnsafe(Float4x2 self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8, self.m01());
        U.putDouble(_p1, self.m10());
        U.putDouble(_p1 + 8, self.m11());
        U.putDouble(_p2, self.m20());
        U.putDouble(_p2 + 8, self.m21());
        U.putDouble(_p3, self.m30());
        U.putDouble(_p3 + 8, self.m31());
        return self;
    }
    public Float4x2 loadRMDoubleUnsafe(long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        float _c0 = (float) U.getDouble(address);
        float _c1 = (float) U.getDouble(address + 8);
        float _c2 = (float) U.getDouble(_p1);
        float _c3 = (float) U.getDouble(_p1 + 8);
        float _c4 = (float) U.getDouble(_p2);
        float _c5 = (float) U.getDouble(_p2 + 8);
        float _c6 = (float) U.getDouble(_p3);
        float _c7 = (float) U.getDouble(_p3 + 8);
        return new Float4x2(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
}
