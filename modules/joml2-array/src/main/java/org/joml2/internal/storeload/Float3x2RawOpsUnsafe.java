// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float3x2RawOpsUnsafe implements Float3x2RawOps {
    public Float3x2 storeCMUnsafe(Float3x2Impl self, long address) {
        U.putFloat(address + 0L, self.data[0]);
        U.putFloat(address + 4L, self.data[1]);
        U.putFloat(address + 8L, self.data[2]);
        U.putFloat(address + 12L, self.data[3]);
        U.putFloat(address + 16L, self.data[4]);
        U.putFloat(address + 20L, self.data[5]);
        return self;
    }
    public Float3x2 loadCMUnsafe(Float3x2Impl self, long address) {
        self.data[0] = U.getFloat(address + 0L);
        self.data[1] = U.getFloat(address + 4L);
        self.data[2] = U.getFloat(address + 8L);
        self.data[3] = U.getFloat(address + 12L);
        self.data[4] = U.getFloat(address + 16L);
        self.data[5] = U.getFloat(address + 20L);
        return self;
    }
    public Float3x2 storeCMDoubleUnsafe(Float3x2Impl self, long address) {
        U.putDouble(address + 0L, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, self.data[2]);
        U.putDouble(address + 24L, self.data[3]);
        U.putDouble(address + 32L, self.data[4]);
        U.putDouble(address + 40L, self.data[5]);
        return self;
    }
    public Float3x2 loadCMDoubleUnsafe(Float3x2Impl self, long address) {
        self.data[0] = (float) U.getDouble(address + 0L);
        self.data[1] = (float) U.getDouble(address + 8L);
        self.data[2] = (float) U.getDouble(address + 16L);
        self.data[3] = (float) U.getDouble(address + 24L);
        self.data[4] = (float) U.getDouble(address + 32L);
        self.data[5] = (float) U.getDouble(address + 40L);
        return self;
    }
    public Float3x2 storeRMUnsafe(Float3x2Impl self, long address) {
        U.putFloat(address + 0L, self.data[0]);
        U.putFloat(address + 4L, self.data[3]);
        U.putFloat(address + 8L, self.data[1]);
        U.putFloat(address + 12L, self.data[4]);
        U.putFloat(address + 16L, self.data[2]);
        U.putFloat(address + 20L, self.data[5]);
        return self;
    }
    public Float3x2 loadRMUnsafe(Float3x2Impl self, long address) {
        self.data[0] = U.getFloat(address + 0L);
        self.data[3] = U.getFloat(address + 4L);
        self.data[1] = U.getFloat(address + 8L);
        self.data[4] = U.getFloat(address + 12L);
        self.data[2] = U.getFloat(address + 16L);
        self.data[5] = U.getFloat(address + 20L);
        return self;
    }
    public Float3x2 storeRMDoubleUnsafe(Float3x2Impl self, long address) {
        U.putDouble(address + 0L, self.data[0]);
        U.putDouble(address + 8L, self.data[3]);
        U.putDouble(address + 16L, self.data[1]);
        U.putDouble(address + 24L, self.data[4]);
        U.putDouble(address + 32L, self.data[2]);
        U.putDouble(address + 40L, self.data[5]);
        return self;
    }
    public Float3x2 loadRMDoubleUnsafe(Float3x2Impl self, long address) {
        self.data[0] = (float) U.getDouble(address + 0L);
        self.data[3] = (float) U.getDouble(address + 8L);
        self.data[1] = (float) U.getDouble(address + 16L);
        self.data[4] = (float) U.getDouble(address + 24L);
        self.data[2] = (float) U.getDouble(address + 32L);
        self.data[5] = (float) U.getDouble(address + 40L);
        return self;
    }
    public Float3x2 storeCMUnsafe(Float3x2Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4, self.data[1]);
        U.putFloat(address + 8, self.data[2]);
        U.putFloat(_p1, self.data[3]);
        U.putFloat(_p1 + 4, self.data[4]);
        U.putFloat(_p1 + 8, self.data[5]);
        return self;
    }
    public Float3x2 loadCMUnsafe(Float3x2Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4);
        self.data[2] = U.getFloat(address + 8);
        self.data[3] = U.getFloat(_p1);
        self.data[4] = U.getFloat(_p1 + 4);
        self.data[5] = U.getFloat(_p1 + 8);
        return self;
    }
    public Float3x2 storeCMDoubleUnsafe(Float3x2Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[1]);
        U.putDouble(address + 16, self.data[2]);
        U.putDouble(_p1, self.data[3]);
        U.putDouble(_p1 + 8, self.data[4]);
        U.putDouble(_p1 + 16, self.data[5]);
        return self;
    }
    public Float3x2 loadCMDoubleUnsafe(Float3x2Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        self.data[0] = (float) U.getDouble(address);
        self.data[1] = (float) U.getDouble(address + 8);
        self.data[2] = (float) U.getDouble(address + 16);
        self.data[3] = (float) U.getDouble(_p1);
        self.data[4] = (float) U.getDouble(_p1 + 8);
        self.data[5] = (float) U.getDouble(_p1 + 16);
        return self;
    }
    public Float3x2 storeRMUnsafe(Float3x2Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4, self.data[3]);
        U.putFloat(_p1, self.data[1]);
        U.putFloat(_p1 + 4, self.data[4]);
        U.putFloat(_p2, self.data[2]);
        U.putFloat(_p2 + 4, self.data[5]);
        return self;
    }
    public Float3x2 loadRMUnsafe(Float3x2Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = U.getFloat(address);
        self.data[3] = U.getFloat(address + 4);
        self.data[1] = U.getFloat(_p1);
        self.data[4] = U.getFloat(_p1 + 4);
        self.data[2] = U.getFloat(_p2);
        self.data[5] = U.getFloat(_p2 + 4);
        return self;
    }
    public Float3x2 storeRMDoubleUnsafe(Float3x2Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[3]);
        U.putDouble(_p1, self.data[1]);
        U.putDouble(_p1 + 8, self.data[4]);
        U.putDouble(_p2, self.data[2]);
        U.putDouble(_p2 + 8, self.data[5]);
        return self;
    }
    public Float3x2 loadRMDoubleUnsafe(Float3x2Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = (float) U.getDouble(address);
        self.data[3] = (float) U.getDouble(address + 8);
        self.data[1] = (float) U.getDouble(_p1);
        self.data[4] = (float) U.getDouble(_p1 + 8);
        self.data[2] = (float) U.getDouble(_p2);
        self.data[5] = (float) U.getDouble(_p2 + 8);
        return self;
    }
}
