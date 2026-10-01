// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double4x4RawOpsUnsafe implements Double4x4RawOps {
    public Double4x4 storeCMUnsafe(Double4x4Impl self, long address) {
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
        U.putDouble(address + 96L, self.data[12]);
        U.putDouble(address + 104L, self.data[13]);
        U.putDouble(address + 112L, self.data[14]);
        U.putDouble(address + 120L, self.data[15]);
        return self;
    }
    public Double4x4 loadCMUnsafe(Double4x4Impl self, long address) {
        self.data[0] = U.getDouble(address + 0L);
        self.data[1] = U.getDouble(address + 8L);
        self.data[2] = U.getDouble(address + 16L);
        self.data[3] = U.getDouble(address + 24L);
        self.data[4] = U.getDouble(address + 32L);
        self.data[5] = U.getDouble(address + 40L);
        self.data[6] = U.getDouble(address + 48L);
        self.data[7] = U.getDouble(address + 56L);
        self.data[8] = U.getDouble(address + 64L);
        self.data[9] = U.getDouble(address + 72L);
        self.data[10] = U.getDouble(address + 80L);
        self.data[11] = U.getDouble(address + 88L);
        self.data[12] = U.getDouble(address + 96L);
        self.data[13] = U.getDouble(address + 104L);
        self.data[14] = U.getDouble(address + 112L);
        self.data[15] = U.getDouble(address + 120L);
        self.properties = self.determineProperties();
        return self;
    }
    public Double4x4 storeCMFloatUnsafe(Double4x4Impl self, long address) {
        U.putFloat(address + 0L, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[1]);
        U.putFloat(address + 8L, (float) self.data[2]);
        U.putFloat(address + 12L, (float) self.data[3]);
        U.putFloat(address + 16L, (float) self.data[4]);
        U.putFloat(address + 20L, (float) self.data[5]);
        U.putFloat(address + 24L, (float) self.data[6]);
        U.putFloat(address + 28L, (float) self.data[7]);
        U.putFloat(address + 32L, (float) self.data[8]);
        U.putFloat(address + 36L, (float) self.data[9]);
        U.putFloat(address + 40L, (float) self.data[10]);
        U.putFloat(address + 44L, (float) self.data[11]);
        U.putFloat(address + 48L, (float) self.data[12]);
        U.putFloat(address + 52L, (float) self.data[13]);
        U.putFloat(address + 56L, (float) self.data[14]);
        U.putFloat(address + 60L, (float) self.data[15]);
        return self;
    }
    public Double4x4 loadCMFloatUnsafe(Double4x4Impl self, long address) {
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
        self.data[12] = U.getFloat(address + 48L);
        self.data[13] = U.getFloat(address + 52L);
        self.data[14] = U.getFloat(address + 56L);
        self.data[15] = U.getFloat(address + 60L);
        self.properties = self.determineProperties();
        return self;
    }
    public Double4x4 storeRMUnsafe(Double4x4Impl self, long address) {
        U.putDouble(address + 0L, self.data[0]);
        U.putDouble(address + 8L, self.data[4]);
        U.putDouble(address + 16L, self.data[8]);
        U.putDouble(address + 24L, self.data[12]);
        U.putDouble(address + 32L, self.data[1]);
        U.putDouble(address + 40L, self.data[5]);
        U.putDouble(address + 48L, self.data[9]);
        U.putDouble(address + 56L, self.data[13]);
        U.putDouble(address + 64L, self.data[2]);
        U.putDouble(address + 72L, self.data[6]);
        U.putDouble(address + 80L, self.data[10]);
        U.putDouble(address + 88L, self.data[14]);
        U.putDouble(address + 96L, self.data[3]);
        U.putDouble(address + 104L, self.data[7]);
        U.putDouble(address + 112L, self.data[11]);
        U.putDouble(address + 120L, self.data[15]);
        return self;
    }
    public Double4x4 loadRMUnsafe(Double4x4Impl self, long address) {
        self.data[0] = U.getDouble(address + 0L);
        self.data[4] = U.getDouble(address + 8L);
        self.data[8] = U.getDouble(address + 16L);
        self.data[12] = U.getDouble(address + 24L);
        self.data[1] = U.getDouble(address + 32L);
        self.data[5] = U.getDouble(address + 40L);
        self.data[9] = U.getDouble(address + 48L);
        self.data[13] = U.getDouble(address + 56L);
        self.data[2] = U.getDouble(address + 64L);
        self.data[6] = U.getDouble(address + 72L);
        self.data[10] = U.getDouble(address + 80L);
        self.data[14] = U.getDouble(address + 88L);
        self.data[3] = U.getDouble(address + 96L);
        self.data[7] = U.getDouble(address + 104L);
        self.data[11] = U.getDouble(address + 112L);
        self.data[15] = U.getDouble(address + 120L);
        self.properties = self.determineProperties();
        return self;
    }
    public Double4x4 storeRMFloatUnsafe(Double4x4Impl self, long address) {
        U.putFloat(address + 0L, (float) self.data[0]);
        U.putFloat(address + 4L, (float) self.data[4]);
        U.putFloat(address + 8L, (float) self.data[8]);
        U.putFloat(address + 12L, (float) self.data[12]);
        U.putFloat(address + 16L, (float) self.data[1]);
        U.putFloat(address + 20L, (float) self.data[5]);
        U.putFloat(address + 24L, (float) self.data[9]);
        U.putFloat(address + 28L, (float) self.data[13]);
        U.putFloat(address + 32L, (float) self.data[2]);
        U.putFloat(address + 36L, (float) self.data[6]);
        U.putFloat(address + 40L, (float) self.data[10]);
        U.putFloat(address + 44L, (float) self.data[14]);
        U.putFloat(address + 48L, (float) self.data[3]);
        U.putFloat(address + 52L, (float) self.data[7]);
        U.putFloat(address + 56L, (float) self.data[11]);
        U.putFloat(address + 60L, (float) self.data[15]);
        return self;
    }
    public Double4x4 loadRMFloatUnsafe(Double4x4Impl self, long address) {
        self.data[0] = U.getFloat(address + 0L);
        self.data[4] = U.getFloat(address + 4L);
        self.data[8] = U.getFloat(address + 8L);
        self.data[12] = U.getFloat(address + 12L);
        self.data[1] = U.getFloat(address + 16L);
        self.data[5] = U.getFloat(address + 20L);
        self.data[9] = U.getFloat(address + 24L);
        self.data[13] = U.getFloat(address + 28L);
        self.data[2] = U.getFloat(address + 32L);
        self.data[6] = U.getFloat(address + 36L);
        self.data[10] = U.getFloat(address + 40L);
        self.data[14] = U.getFloat(address + 44L);
        self.data[3] = U.getFloat(address + 48L);
        self.data[7] = U.getFloat(address + 52L);
        self.data[11] = U.getFloat(address + 56L);
        self.data[15] = U.getFloat(address + 60L);
        self.properties = self.determineProperties();
        return self;
    }
    public Double4x4 storeCMUnsafe(Double4x4Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
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
        U.putDouble(_p3, self.data[12]);
        U.putDouble(_p3 + 8, self.data[13]);
        U.putDouble(_p3 + 16, self.data[14]);
        U.putDouble(_p3 + 24, self.data[15]);
        return self;
    }
    public Double4x4 loadCMUnsafe(Double4x4Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.data[0] = U.getDouble(address);
        self.data[1] = U.getDouble(address + 8);
        self.data[2] = U.getDouble(address + 16);
        self.data[3] = U.getDouble(address + 24);
        self.data[4] = U.getDouble(_p1);
        self.data[5] = U.getDouble(_p1 + 8);
        self.data[6] = U.getDouble(_p1 + 16);
        self.data[7] = U.getDouble(_p1 + 24);
        self.data[8] = U.getDouble(_p2);
        self.data[9] = U.getDouble(_p2 + 8);
        self.data[10] = U.getDouble(_p2 + 16);
        self.data[11] = U.getDouble(_p2 + 24);
        self.data[12] = U.getDouble(_p3);
        self.data[13] = U.getDouble(_p3 + 8);
        self.data[14] = U.getDouble(_p3 + 16);
        self.data[15] = U.getDouble(_p3 + 24);
        self.properties = self.determineProperties();
        return self;
    }
    public Double4x4 storeCMFloatUnsafe(Double4x4Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4, (float) self.data[1]);
        U.putFloat(address + 8, (float) self.data[2]);
        U.putFloat(address + 12, (float) self.data[3]);
        U.putFloat(_p1, (float) self.data[4]);
        U.putFloat(_p1 + 4, (float) self.data[5]);
        U.putFloat(_p1 + 8, (float) self.data[6]);
        U.putFloat(_p1 + 12, (float) self.data[7]);
        U.putFloat(_p2, (float) self.data[8]);
        U.putFloat(_p2 + 4, (float) self.data[9]);
        U.putFloat(_p2 + 8, (float) self.data[10]);
        U.putFloat(_p2 + 12, (float) self.data[11]);
        U.putFloat(_p3, (float) self.data[12]);
        U.putFloat(_p3 + 4, (float) self.data[13]);
        U.putFloat(_p3 + 8, (float) self.data[14]);
        U.putFloat(_p3 + 12, (float) self.data[15]);
        return self;
    }
    public Double4x4 loadCMFloatUnsafe(Double4x4Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
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
        self.data[12] = U.getFloat(_p3);
        self.data[13] = U.getFloat(_p3 + 4);
        self.data[14] = U.getFloat(_p3 + 8);
        self.data[15] = U.getFloat(_p3 + 12);
        self.properties = self.determineProperties();
        return self;
    }
    public Double4x4 storeRMUnsafe(Double4x4Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        U.putDouble(address, self.data[0]);
        U.putDouble(address + 8, self.data[4]);
        U.putDouble(address + 16, self.data[8]);
        U.putDouble(address + 24, self.data[12]);
        U.putDouble(_p1, self.data[1]);
        U.putDouble(_p1 + 8, self.data[5]);
        U.putDouble(_p1 + 16, self.data[9]);
        U.putDouble(_p1 + 24, self.data[13]);
        U.putDouble(_p2, self.data[2]);
        U.putDouble(_p2 + 8, self.data[6]);
        U.putDouble(_p2 + 16, self.data[10]);
        U.putDouble(_p2 + 24, self.data[14]);
        U.putDouble(_p3, self.data[3]);
        U.putDouble(_p3 + 8, self.data[7]);
        U.putDouble(_p3 + 16, self.data[11]);
        U.putDouble(_p3 + 24, self.data[15]);
        return self;
    }
    public Double4x4 loadRMUnsafe(Double4x4Impl self, long address, int stride) {
        long _ps = (long) stride * 8;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.data[0] = U.getDouble(address);
        self.data[4] = U.getDouble(address + 8);
        self.data[8] = U.getDouble(address + 16);
        self.data[12] = U.getDouble(address + 24);
        self.data[1] = U.getDouble(_p1);
        self.data[5] = U.getDouble(_p1 + 8);
        self.data[9] = U.getDouble(_p1 + 16);
        self.data[13] = U.getDouble(_p1 + 24);
        self.data[2] = U.getDouble(_p2);
        self.data[6] = U.getDouble(_p2 + 8);
        self.data[10] = U.getDouble(_p2 + 16);
        self.data[14] = U.getDouble(_p2 + 24);
        self.data[3] = U.getDouble(_p3);
        self.data[7] = U.getDouble(_p3 + 8);
        self.data[11] = U.getDouble(_p3 + 16);
        self.data[15] = U.getDouble(_p3 + 24);
        self.properties = self.determineProperties();
        return self;
    }
    public Double4x4 storeRMFloatUnsafe(Double4x4Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        U.putFloat(address, (float) self.data[0]);
        U.putFloat(address + 4, (float) self.data[4]);
        U.putFloat(address + 8, (float) self.data[8]);
        U.putFloat(address + 12, (float) self.data[12]);
        U.putFloat(_p1, (float) self.data[1]);
        U.putFloat(_p1 + 4, (float) self.data[5]);
        U.putFloat(_p1 + 8, (float) self.data[9]);
        U.putFloat(_p1 + 12, (float) self.data[13]);
        U.putFloat(_p2, (float) self.data[2]);
        U.putFloat(_p2 + 4, (float) self.data[6]);
        U.putFloat(_p2 + 8, (float) self.data[10]);
        U.putFloat(_p2 + 12, (float) self.data[14]);
        U.putFloat(_p3, (float) self.data[3]);
        U.putFloat(_p3 + 4, (float) self.data[7]);
        U.putFloat(_p3 + 8, (float) self.data[11]);
        U.putFloat(_p3 + 12, (float) self.data[15]);
        return self;
    }
    public Double4x4 loadRMFloatUnsafe(Double4x4Impl self, long address, int stride) {
        long _ps = (long) stride * 4;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        self.data[0] = U.getFloat(address);
        self.data[4] = U.getFloat(address + 4);
        self.data[8] = U.getFloat(address + 8);
        self.data[12] = U.getFloat(address + 12);
        self.data[1] = U.getFloat(_p1);
        self.data[5] = U.getFloat(_p1 + 4);
        self.data[9] = U.getFloat(_p1 + 8);
        self.data[13] = U.getFloat(_p1 + 12);
        self.data[2] = U.getFloat(_p2);
        self.data[6] = U.getFloat(_p2 + 4);
        self.data[10] = U.getFloat(_p2 + 8);
        self.data[14] = U.getFloat(_p2 + 12);
        self.data[3] = U.getFloat(_p3);
        self.data[7] = U.getFloat(_p3 + 4);
        self.data[11] = U.getFloat(_p3 + 8);
        self.data[15] = U.getFloat(_p3 + 12);
        self.properties = self.determineProperties();
        return self;
    }
}
