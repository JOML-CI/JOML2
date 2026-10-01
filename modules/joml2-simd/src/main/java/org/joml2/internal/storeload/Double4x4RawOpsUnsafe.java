// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double4x4RawOpsUnsafe extends Double4x4RawOps {
    public Double4x4 storeCMUnsafe(Double4x4Impl self, long address) {
        U.putDouble(address, self.data[0]);
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
        self.data[0] = U.getDouble(address);
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
        U.putFloat(address, (float) self.data[0]);
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
        self.data[0] = U.getFloat(address);
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
        U.putDouble(address, self.data[0]);
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
        self.data[0] = U.getDouble(address);
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
        U.putFloat(address, (float) self.data[0]);
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
        self.data[0] = U.getFloat(address);
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
        double[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            U.putDouble(_a, _d[_o]);
            U.putDouble(_a + 8L, _d[_o + 1]);
            U.putDouble(_a + 16L, _d[_o + 2]);
            U.putDouble(_a + 24L, _d[_o + 3]);
        }
        return self;
    }
    public Double4x4 loadCMUnsafe(Double4x4Impl self, long address, int stride) {
        double[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            _d[_o] = U.getDouble(_a);
            _d[_o + 1] = U.getDouble(_a + 8L);
            _d[_o + 2] = U.getDouble(_a + 16L);
            _d[_o + 3] = U.getDouble(_a + 24L);
        }
        self.properties = self.determineProperties();
        return self;
    }
    public Double4x4 storeCMFloatUnsafe(Double4x4Impl self, long address, int stride) {
        double[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            U.putFloat(_a, (float) _d[_o]);
            U.putFloat(_a + 4L, (float) _d[_o + 1]);
            U.putFloat(_a + 8L, (float) _d[_o + 2]);
            U.putFloat(_a + 12L, (float) _d[_o + 3]);
        }
        return self;
    }
    public Double4x4 loadCMFloatUnsafe(Double4x4Impl self, long address, int stride) {
        double[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            _d[_o] = U.getFloat(_a);
            _d[_o + 1] = U.getFloat(_a + 4L);
            _d[_o + 2] = U.getFloat(_a + 8L);
            _d[_o + 3] = U.getFloat(_a + 12L);
        }
        self.properties = self.determineProperties();
        return self;
    }
    public Double4x4 storeRMUnsafe(Double4x4Impl self, long address, int stride) {
        double[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            U.putDouble(_a, _d[_o]);
            U.putDouble(_a + 8L, _d[_o + 4]);
            U.putDouble(_a + 16L, _d[_o + 8]);
            U.putDouble(_a + 24L, _d[_o + 12]);
        }
        return self;
    }
    public Double4x4 loadRMUnsafe(Double4x4Impl self, long address, int stride) {
        double[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            _d[_o] = U.getDouble(_a);
            _d[_o + 4] = U.getDouble(_a + 8L);
            _d[_o + 8] = U.getDouble(_a + 16L);
            _d[_o + 12] = U.getDouble(_a + 24L);
        }
        self.properties = self.determineProperties();
        return self;
    }
    public Double4x4 storeRMFloatUnsafe(Double4x4Impl self, long address, int stride) {
        double[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            U.putFloat(_a, (float) _d[_o]);
            U.putFloat(_a + 4L, (float) _d[_o + 4]);
            U.putFloat(_a + 8L, (float) _d[_o + 8]);
            U.putFloat(_a + 12L, (float) _d[_o + 12]);
        }
        return self;
    }
    public Double4x4 loadRMFloatUnsafe(Double4x4Impl self, long address, int stride) {
        double[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            _d[_o] = U.getFloat(_a);
            _d[_o + 4] = U.getFloat(_a + 4L);
            _d[_o + 8] = U.getFloat(_a + 8L);
            _d[_o + 12] = U.getFloat(_a + 12L);
        }
        self.properties = self.determineProperties();
        return self;
    }
}
