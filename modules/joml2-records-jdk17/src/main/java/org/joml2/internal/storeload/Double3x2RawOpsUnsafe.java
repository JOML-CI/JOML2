// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double3x2RawOpsUnsafe implements Double3x2RawOps {
    public Double3x2 storeCMUnsafe(Double3x2 self, long address) {
        U.putDouble(address + 0L, self.m00());
        U.putDouble(address + 8L, self.m10());
        U.putDouble(address + 16L, self.m20());
        U.putDouble(address + 24L, self.m01());
        U.putDouble(address + 32L, self.m11());
        U.putDouble(address + 40L, self.m21());
        return self;
    }
    public Double3x2 loadCMUnsafe(long address) {
        double _c0 = U.getDouble(address + 0L);
        double _c2 = U.getDouble(address + 8L);
        double _c4 = U.getDouble(address + 16L);
        double _c1 = U.getDouble(address + 24L);
        double _c3 = U.getDouble(address + 32L);
        double _c5 = U.getDouble(address + 40L);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public Double3x2 storeCMFloatUnsafe(Double3x2 self, long address) {
        U.putFloat(address + 0L, (float) self.m00());
        U.putFloat(address + 4L, (float) self.m10());
        U.putFloat(address + 8L, (float) self.m20());
        U.putFloat(address + 12L, (float) self.m01());
        U.putFloat(address + 16L, (float) self.m11());
        U.putFloat(address + 20L, (float) self.m21());
        return self;
    }
    public Double3x2 loadCMFloatUnsafe(long address) {
        double _c0 = U.getFloat(address + 0L);
        double _c2 = U.getFloat(address + 4L);
        double _c4 = U.getFloat(address + 8L);
        double _c1 = U.getFloat(address + 12L);
        double _c3 = U.getFloat(address + 16L);
        double _c5 = U.getFloat(address + 20L);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public Double3x2 storeRMUnsafe(Double3x2 self, long address) {
        U.putDouble(address + 0L, self.m00());
        U.putDouble(address + 8L, self.m01());
        U.putDouble(address + 16L, self.m10());
        U.putDouble(address + 24L, self.m11());
        U.putDouble(address + 32L, self.m20());
        U.putDouble(address + 40L, self.m21());
        return self;
    }
    public Double3x2 loadRMUnsafe(long address) {
        double _c0 = U.getDouble(address + 0L);
        double _c1 = U.getDouble(address + 8L);
        double _c2 = U.getDouble(address + 16L);
        double _c3 = U.getDouble(address + 24L);
        double _c4 = U.getDouble(address + 32L);
        double _c5 = U.getDouble(address + 40L);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public Double3x2 storeRMFloatUnsafe(Double3x2 self, long address) {
        U.putFloat(address + 0L, (float) self.m00());
        U.putFloat(address + 4L, (float) self.m01());
        U.putFloat(address + 8L, (float) self.m10());
        U.putFloat(address + 12L, (float) self.m11());
        U.putFloat(address + 16L, (float) self.m20());
        U.putFloat(address + 20L, (float) self.m21());
        return self;
    }
    public Double3x2 loadRMFloatUnsafe(long address) {
        double _c0 = U.getFloat(address + 0L);
        double _c1 = U.getFloat(address + 4L);
        double _c2 = U.getFloat(address + 8L);
        double _c3 = U.getFloat(address + 12L);
        double _c4 = U.getFloat(address + 16L);
        double _c5 = U.getFloat(address + 20L);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public Double3x2 storeCMUnsafe(Double3x2 self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8, self.m10());
        U.putDouble(address + 16, self.m20());
        U.putDouble(_p1, self.m01());
        U.putDouble(_p1 + 8, self.m11());
        U.putDouble(_p1 + 16, self.m21());
        return self;
    }
    public Double3x2 loadCMUnsafe(long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        double _c0 = U.getDouble(address);
        double _c2 = U.getDouble(address + 8);
        double _c4 = U.getDouble(address + 16);
        double _c1 = U.getDouble(_p1);
        double _c3 = U.getDouble(_p1 + 8);
        double _c5 = U.getDouble(_p1 + 16);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public Double3x2 storeCMFloatUnsafe(Double3x2 self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        U.putFloat(address, (float) self.m00());
        U.putFloat(address + 4, (float) self.m10());
        U.putFloat(address + 8, (float) self.m20());
        U.putFloat(_p1, (float) self.m01());
        U.putFloat(_p1 + 4, (float) self.m11());
        U.putFloat(_p1 + 8, (float) self.m21());
        return self;
    }
    public Double3x2 loadCMFloatUnsafe(long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        double _c0 = U.getFloat(address);
        double _c2 = U.getFloat(address + 4);
        double _c4 = U.getFloat(address + 8);
        double _c1 = U.getFloat(_p1);
        double _c3 = U.getFloat(_p1 + 4);
        double _c5 = U.getFloat(_p1 + 8);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public Double3x2 storeRMUnsafe(Double3x2 self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.m00());
        U.putDouble(address + 8, self.m01());
        U.putDouble(_p1, self.m10());
        U.putDouble(_p1 + 8, self.m11());
        U.putDouble(_p2, self.m20());
        U.putDouble(_p2 + 8, self.m21());
        return self;
    }
    public Double3x2 loadRMUnsafe(long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        double _c0 = U.getDouble(address);
        double _c1 = U.getDouble(address + 8);
        double _c2 = U.getDouble(_p1);
        double _c3 = U.getDouble(_p1 + 8);
        double _c4 = U.getDouble(_p2);
        double _c5 = U.getDouble(_p2 + 8);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public Double3x2 storeRMFloatUnsafe(Double3x2 self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, (float) self.m00());
        U.putFloat(address + 4, (float) self.m01());
        U.putFloat(_p1, (float) self.m10());
        U.putFloat(_p1 + 4, (float) self.m11());
        U.putFloat(_p2, (float) self.m20());
        U.putFloat(_p2 + 4, (float) self.m21());
        return self;
    }
    public Double3x2 loadRMFloatUnsafe(long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        double _c0 = U.getFloat(address);
        double _c1 = U.getFloat(address + 4);
        double _c2 = U.getFloat(_p1);
        double _c3 = U.getFloat(_p1 + 4);
        double _c4 = U.getFloat(_p2);
        double _c5 = U.getFloat(_p2 + 4);
        return new Double3x2(_c0, _c1, _c2, _c3, _c4, _c5);
    }
}
