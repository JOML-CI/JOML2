// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double2x4RawOpsUnsafe implements Double2x4RawOps {
    public Double2x4 storeCMUnsafe(Double2x4Impl self, long address) {
        U.putDouble(address + 0L, self.data[0]);
        U.putDouble(address + 8L, self.data[4]);
        U.putDouble(address + 16L, self.data[1]);
        U.putDouble(address + 24L, self.data[5]);
        U.putDouble(address + 32L, self.data[2]);
        U.putDouble(address + 40L, self.data[6]);
        U.putDouble(address + 48L, self.data[3]);
        U.putDouble(address + 56L, self.data[7]);
        return self;
    }
    public Double2x4 loadCMUnsafe(Double2x4Impl self, long address) {
        self.data[0] = U.getDouble(address + 0L);
        self.data[4] = U.getDouble(address + 8L);
        self.data[1] = U.getDouble(address + 16L);
        self.data[5] = U.getDouble(address + 24L);
        self.data[2] = U.getDouble(address + 32L);
        self.data[6] = U.getDouble(address + 40L);
        self.data[3] = U.getDouble(address + 48L);
        self.data[7] = U.getDouble(address + 56L);
        return self;
    }
    public Double2x4 storeCMFloatUnsafe(Double2x4Impl self, long address) {
        U.putFloat(address + 0L, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[4]);
        U.putFloat(address + 8L, (float) self.data[1]);
        U.putFloat(address + 12L, (float) self.data[5]);
        U.putFloat(address + 16L, (float) self.data[2]);
        U.putFloat(address + 20L, (float) self.data[6]);
        U.putFloat(address + 24L, (float) self.data[3]);
        U.putFloat(address + 28L, (float) self.data[7]);
        return self;
    }
    public Double2x4 loadCMFloatUnsafe(Double2x4Impl self, long address) {
        self.data[0] = U.getFloat(address + 0L);
        self.data[4] = U.getFloat(address + 4L);
        self.data[1] = U.getFloat(address + 8L);
        self.data[5] = U.getFloat(address + 12L);
        self.data[2] = U.getFloat(address + 16L);
        self.data[6] = U.getFloat(address + 20L);
        self.data[3] = U.getFloat(address + 24L);
        self.data[7] = U.getFloat(address + 28L);
        return self;
    }
    public Double2x4 storeRMUnsafe(Double2x4Impl self, long address) {
        U.putDouble(address + 0L, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, self.data[2]);
        U.putDouble(address + 24L, self.data[3]);
        U.putDouble(address + 32L, self.data[4]);
        U.putDouble(address + 40L, self.data[5]);
        U.putDouble(address + 48L, self.data[6]);
        U.putDouble(address + 56L, self.data[7]);
        return self;
    }
    public Double2x4 loadRMUnsafe(Double2x4Impl self, long address) {
        self.data[0] = U.getDouble(address + 0L);
        self.data[1] = U.getDouble(address + 8L);
        self.data[2] = U.getDouble(address + 16L);
        self.data[3] = U.getDouble(address + 24L);
        self.data[4] = U.getDouble(address + 32L);
        self.data[5] = U.getDouble(address + 40L);
        self.data[6] = U.getDouble(address + 48L);
        self.data[7] = U.getDouble(address + 56L);
        return self;
    }
    public Double2x4 storeRMFloatUnsafe(Double2x4Impl self, long address) {
        U.putFloat(address + 0L, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[1]);
        U.putFloat(address + 8L, (float) self.data[2]);
        U.putFloat(address + 12L, (float) self.data[3]);
        U.putFloat(address + 16L, (float) self.data[4]);
        U.putFloat(address + 20L, (float) self.data[5]);
        U.putFloat(address + 24L, (float) self.data[6]);
        U.putFloat(address + 28L, (float) self.data[7]);
        return self;
    }
    public Double2x4 loadRMFloatUnsafe(Double2x4Impl self, long address) {
        self.data[0] = U.getFloat(address + 0L);
        self.data[1] = U.getFloat(address + 4L);
        self.data[2] = U.getFloat(address + 8L);
        self.data[3] = U.getFloat(address + 12L);
        self.data[4] = U.getFloat(address + 16L);
        self.data[5] = U.getFloat(address + 20L);
        self.data[6] = U.getFloat(address + 24L);
        self.data[7] = U.getFloat(address + 28L);
        return self;
    }
    public Double2x4 storeCMUnsafe(Double2x4Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[4]);
        U.putDouble(_p1, self.data[1]);
        U.putDouble(_p1 + 8, self.data[5]);
        U.putDouble(_p2, self.data[2]);
        U.putDouble(_p2 + 8, self.data[6]);
        U.putDouble(_p3, self.data[3]);
        U.putDouble(_p3 + 8, self.data[7]);
        return self;
    }
    public Double2x4 loadCMUnsafe(Double2x4Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.data[0] = U.getDouble(address);
        self.data[4] = U.getDouble(address + 8);
        self.data[1] = U.getDouble(_p1);
        self.data[5] = U.getDouble(_p1 + 8);
        self.data[2] = U.getDouble(_p2);
        self.data[6] = U.getDouble(_p2 + 8);
        self.data[3] = U.getDouble(_p3);
        self.data[7] = U.getDouble(_p3 + 8);
        return self;
    }
    public Double2x4 storeCMFloatUnsafe(Double2x4Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4, (float) self.data[4]);
        U.putFloat(_p1, (float) self.data[1]);
        U.putFloat(_p1 + 4, (float) self.data[5]);
        U.putFloat(_p2, (float) self.data[2]);
        U.putFloat(_p2 + 4, (float) self.data[6]);
        U.putFloat(_p3, (float) self.data[3]);
        U.putFloat(_p3 + 4, (float) self.data[7]);
        return self;
    }
    public Double2x4 loadCMFloatUnsafe(Double2x4Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.data[0] = U.getFloat(address);
        self.data[4] = U.getFloat(address + 4);
        self.data[1] = U.getFloat(_p1);
        self.data[5] = U.getFloat(_p1 + 4);
        self.data[2] = U.getFloat(_p2);
        self.data[6] = U.getFloat(_p2 + 4);
        self.data[3] = U.getFloat(_p3);
        self.data[7] = U.getFloat(_p3 + 4);
        return self;
    }
    public Double2x4 storeRMUnsafe(Double2x4Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[1]);
        U.putDouble(address + 16, self.data[2]);
        U.putDouble(address + 24, self.data[3]);
        U.putDouble(_p1, self.data[4]);
        U.putDouble(_p1 + 8, self.data[5]);
        U.putDouble(_p1 + 16, self.data[6]);
        U.putDouble(_p1 + 24, self.data[7]);
        return self;
    }
    public Double2x4 loadRMUnsafe(Double2x4Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        self.data[0] = U.getDouble(address);
        self.data[1] = U.getDouble(address + 8);
        self.data[2] = U.getDouble(address + 16);
        self.data[3] = U.getDouble(address + 24);
        self.data[4] = U.getDouble(_p1);
        self.data[5] = U.getDouble(_p1 + 8);
        self.data[6] = U.getDouble(_p1 + 16);
        self.data[7] = U.getDouble(_p1 + 24);
        return self;
    }
    public Double2x4 storeRMFloatUnsafe(Double2x4Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4, (float) self.data[1]);
        U.putFloat(address + 8, (float) self.data[2]);
        U.putFloat(address + 12, (float) self.data[3]);
        U.putFloat(_p1, (float) self.data[4]);
        U.putFloat(_p1 + 4, (float) self.data[5]);
        U.putFloat(_p1 + 8, (float) self.data[6]);
        U.putFloat(_p1 + 12, (float) self.data[7]);
        return self;
    }
    public Double2x4 loadRMFloatUnsafe(Double2x4Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4);
        self.data[2] = U.getFloat(address + 8);
        self.data[3] = U.getFloat(address + 12);
        self.data[4] = U.getFloat(_p1);
        self.data[5] = U.getFloat(_p1 + 4);
        self.data[6] = U.getFloat(_p1 + 8);
        self.data[7] = U.getFloat(_p1 + 12);
        return self;
    }
}
