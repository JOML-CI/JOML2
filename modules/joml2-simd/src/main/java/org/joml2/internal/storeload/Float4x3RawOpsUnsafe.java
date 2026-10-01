// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float4x3RawOpsUnsafe implements Float4x3RawOps {
    public Float4x3 storeCMUnsafe(Float4x3Impl self, long address) {
        U.putFloat(address + 0L, self.data[0]);
        U.putFloat(address + 4L, self.data[1]);
        U.putFloat(address + 8L, self.data[2]);
        U.putFloat(address + 12L, self.data[3]);
        U.putFloat(address + 16L, self.data[4]);
        U.putFloat(address + 20L, self.data[5]);
        U.putFloat(address + 24L, self.data[6]);
        U.putFloat(address + 28L, self.data[7]);
        U.putFloat(address + 32L, self.data[8]);
        U.putFloat(address + 36L, self.data[9]);
        U.putFloat(address + 40L, self.data[10]);
        U.putFloat(address + 44L, self.data[11]);
        return self;
    }
    public Float4x3 loadCMUnsafe(Float4x3Impl self, long address) {
        self.data[0] = U.getFloat(address + 0L);
        self.data[1] = U.getFloat(address + 4L);
        self.data[2] = U.getFloat(address + 8L);
        self.data[3] = U.getFloat(address + 12L);
        self.data[4] = U.getFloat(address + 16L);
        self.data[5] = U.getFloat(address + 20L);
        self.data[6] = U.getFloat(address + 24L);
        self.data[7] = U.getFloat(address + 28L);
        self.data[8] = U.getFloat(address + 32L);
        self.data[9] = U.getFloat(address + 36L);
        self.data[10] = U.getFloat(address + 40L);
        self.data[11] = U.getFloat(address + 44L);
        return self;
    }
    public Float4x3 storeCMDoubleUnsafe(Float4x3Impl self, long address) {
        U.putDouble(address + 0L, self.data[0]);
        U.putDouble(address + 8L, self.data[1]);
        U.putDouble(address + 16L, self.data[2]);
        U.putDouble(address + 24L, self.data[3]);
        U.putDouble(address + 32L, self.data[4]);
        U.putDouble(address + 40L, self.data[5]);
        U.putDouble(address + 48L, self.data[6]);
        U.putDouble(address + 56L, self.data[7]);
        U.putDouble(address + 64L, self.data[8]);
        U.putDouble(address + 72L, self.data[9]);
        U.putDouble(address + 80L, self.data[10]);
        U.putDouble(address + 88L, self.data[11]);
        return self;
    }
    public Float4x3 loadCMDoubleUnsafe(Float4x3Impl self, long address) {
        self.data[0] = (float) U.getDouble(address + 0L);
        self.data[1] = (float) U.getDouble(address + 8L);
        self.data[2] = (float) U.getDouble(address + 16L);
        self.data[3] = (float) U.getDouble(address + 24L);
        self.data[4] = (float) U.getDouble(address + 32L);
        self.data[5] = (float) U.getDouble(address + 40L);
        self.data[6] = (float) U.getDouble(address + 48L);
        self.data[7] = (float) U.getDouble(address + 56L);
        self.data[8] = (float) U.getDouble(address + 64L);
        self.data[9] = (float) U.getDouble(address + 72L);
        self.data[10] = (float) U.getDouble(address + 80L);
        self.data[11] = (float) U.getDouble(address + 88L);
        return self;
    }
    public Float4x3 storeRMUnsafe(Float4x3Impl self, long address) {
        U.putFloat(address + 0L, self.data[0]);
        U.putFloat(address + 4L, self.data[4]);
        U.putFloat(address + 8L, self.data[8]);
        U.putFloat(address + 12L, self.data[1]);
        U.putFloat(address + 16L, self.data[5]);
        U.putFloat(address + 20L, self.data[9]);
        U.putFloat(address + 24L, self.data[2]);
        U.putFloat(address + 28L, self.data[6]);
        U.putFloat(address + 32L, self.data[10]);
        U.putFloat(address + 36L, self.data[3]);
        U.putFloat(address + 40L, self.data[7]);
        U.putFloat(address + 44L, self.data[11]);
        return self;
    }
    public Float4x3 loadRMUnsafe(Float4x3Impl self, long address) {
        self.data[0] = U.getFloat(address + 0L);
        self.data[4] = U.getFloat(address + 4L);
        self.data[8] = U.getFloat(address + 8L);
        self.data[1] = U.getFloat(address + 12L);
        self.data[5] = U.getFloat(address + 16L);
        self.data[9] = U.getFloat(address + 20L);
        self.data[2] = U.getFloat(address + 24L);
        self.data[6] = U.getFloat(address + 28L);
        self.data[10] = U.getFloat(address + 32L);
        self.data[3] = U.getFloat(address + 36L);
        self.data[7] = U.getFloat(address + 40L);
        self.data[11] = U.getFloat(address + 44L);
        return self;
    }
    public Float4x3 storeRMDoubleUnsafe(Float4x3Impl self, long address) {
        U.putDouble(address + 0L, self.data[0]);
        U.putDouble(address + 8L, self.data[4]);
        U.putDouble(address + 16L, self.data[8]);
        U.putDouble(address + 24L, self.data[1]);
        U.putDouble(address + 32L, self.data[5]);
        U.putDouble(address + 40L, self.data[9]);
        U.putDouble(address + 48L, self.data[2]);
        U.putDouble(address + 56L, self.data[6]);
        U.putDouble(address + 64L, self.data[10]);
        U.putDouble(address + 72L, self.data[3]);
        U.putDouble(address + 80L, self.data[7]);
        U.putDouble(address + 88L, self.data[11]);
        return self;
    }
    public Float4x3 loadRMDoubleUnsafe(Float4x3Impl self, long address) {
        self.data[0] = (float) U.getDouble(address + 0L);
        self.data[4] = (float) U.getDouble(address + 8L);
        self.data[8] = (float) U.getDouble(address + 16L);
        self.data[1] = (float) U.getDouble(address + 24L);
        self.data[5] = (float) U.getDouble(address + 32L);
        self.data[9] = (float) U.getDouble(address + 40L);
        self.data[2] = (float) U.getDouble(address + 48L);
        self.data[6] = (float) U.getDouble(address + 56L);
        self.data[10] = (float) U.getDouble(address + 64L);
        self.data[3] = (float) U.getDouble(address + 72L);
        self.data[7] = (float) U.getDouble(address + 80L);
        self.data[11] = (float) U.getDouble(address + 88L);
        return self;
    }
    public Float4x3 storeCMUnsafe(Float4x3Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4, self.data[1]);
        U.putFloat(address + 8, self.data[2]);
        U.putFloat(address + 12, self.data[3]);
        U.putFloat(_p1, self.data[4]);
        U.putFloat(_p1 + 4, self.data[5]);
        U.putFloat(_p1 + 8, self.data[6]);
        U.putFloat(_p1 + 12, self.data[7]);
        U.putFloat(_p2, self.data[8]);
        U.putFloat(_p2 + 4, self.data[9]);
        U.putFloat(_p2 + 8, self.data[10]);
        U.putFloat(_p2 + 12, self.data[11]);
        return self;
    }
    public Float4x3 loadCMUnsafe(Float4x3Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = U.getFloat(address);
        self.data[1] = U.getFloat(address + 4);
        self.data[2] = U.getFloat(address + 8);
        self.data[3] = U.getFloat(address + 12);
        self.data[4] = U.getFloat(_p1);
        self.data[5] = U.getFloat(_p1 + 4);
        self.data[6] = U.getFloat(_p1 + 8);
        self.data[7] = U.getFloat(_p1 + 12);
        self.data[8] = U.getFloat(_p2);
        self.data[9] = U.getFloat(_p2 + 4);
        self.data[10] = U.getFloat(_p2 + 8);
        self.data[11] = U.getFloat(_p2 + 12);
        return self;
    }
    public Float4x3 storeCMDoubleUnsafe(Float4x3Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[1]);
        U.putDouble(address + 16, self.data[2]);
        U.putDouble(address + 24, self.data[3]);
        U.putDouble(_p1, self.data[4]);
        U.putDouble(_p1 + 8, self.data[5]);
        U.putDouble(_p1 + 16, self.data[6]);
        U.putDouble(_p1 + 24, self.data[7]);
        U.putDouble(_p2, self.data[8]);
        U.putDouble(_p2 + 8, self.data[9]);
        U.putDouble(_p2 + 16, self.data[10]);
        U.putDouble(_p2 + 24, self.data[11]);
        return self;
    }
    public Float4x3 loadCMDoubleUnsafe(Float4x3Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        self.data[0] = (float) U.getDouble(address);
        self.data[1] = (float) U.getDouble(address + 8);
        self.data[2] = (float) U.getDouble(address + 16);
        self.data[3] = (float) U.getDouble(address + 24);
        self.data[4] = (float) U.getDouble(_p1);
        self.data[5] = (float) U.getDouble(_p1 + 8);
        self.data[6] = (float) U.getDouble(_p1 + 16);
        self.data[7] = (float) U.getDouble(_p1 + 24);
        self.data[8] = (float) U.getDouble(_p2);
        self.data[9] = (float) U.getDouble(_p2 + 8);
        self.data[10] = (float) U.getDouble(_p2 + 16);
        self.data[11] = (float) U.getDouble(_p2 + 24);
        return self;
    }
    public Float4x3 storeRMUnsafe(Float4x3Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4, self.data[4]);
        U.putFloat(address + 8, self.data[8]);
        U.putFloat(_p1, self.data[1]);
        U.putFloat(_p1 + 4, self.data[5]);
        U.putFloat(_p1 + 8, self.data[9]);
        U.putFloat(_p2, self.data[2]);
        U.putFloat(_p2 + 4, self.data[6]);
        U.putFloat(_p2 + 8, self.data[10]);
        U.putFloat(_p3, self.data[3]);
        U.putFloat(_p3 + 4, self.data[7]);
        U.putFloat(_p3 + 8, self.data[11]);
        return self;
    }
    public Float4x3 loadRMUnsafe(Float4x3Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.data[0] = U.getFloat(address);
        self.data[4] = U.getFloat(address + 4);
        self.data[8] = U.getFloat(address + 8);
        self.data[1] = U.getFloat(_p1);
        self.data[5] = U.getFloat(_p1 + 4);
        self.data[9] = U.getFloat(_p1 + 8);
        self.data[2] = U.getFloat(_p2);
        self.data[6] = U.getFloat(_p2 + 4);
        self.data[10] = U.getFloat(_p2 + 8);
        self.data[3] = U.getFloat(_p3);
        self.data[7] = U.getFloat(_p3 + 4);
        self.data[11] = U.getFloat(_p3 + 8);
        return self;
    }
    public Float4x3 storeRMDoubleUnsafe(Float4x3Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[4]);
        U.putDouble(address + 16, self.data[8]);
        U.putDouble(_p1, self.data[1]);
        U.putDouble(_p1 + 8, self.data[5]);
        U.putDouble(_p1 + 16, self.data[9]);
        U.putDouble(_p2, self.data[2]);
        U.putDouble(_p2 + 8, self.data[6]);
        U.putDouble(_p2 + 16, self.data[10]);
        U.putDouble(_p3, self.data[3]);
        U.putDouble(_p3 + 8, self.data[7]);
        U.putDouble(_p3 + 16, self.data[11]);
        return self;
    }
    public Float4x3 loadRMDoubleUnsafe(Float4x3Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.data[0] = (float) U.getDouble(address);
        self.data[4] = (float) U.getDouble(address + 8);
        self.data[8] = (float) U.getDouble(address + 16);
        self.data[1] = (float) U.getDouble(_p1);
        self.data[5] = (float) U.getDouble(_p1 + 8);
        self.data[9] = (float) U.getDouble(_p1 + 16);
        self.data[2] = (float) U.getDouble(_p2);
        self.data[6] = (float) U.getDouble(_p2 + 8);
        self.data[10] = (float) U.getDouble(_p2 + 16);
        self.data[3] = (float) U.getDouble(_p3);
        self.data[7] = (float) U.getDouble(_p3 + 8);
        self.data[11] = (float) U.getDouble(_p3 + 16);
        return self;
    }
}
