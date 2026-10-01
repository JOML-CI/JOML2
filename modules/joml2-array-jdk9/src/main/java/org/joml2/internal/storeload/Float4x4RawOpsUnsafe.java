// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float4x4RawOpsUnsafe extends Float4x4RawOps {
    public Float4x4 storeCMUnsafe(Float4x4Impl self, long address) {
        float[] _d = self.data;
        if (org.joml2.internal.unsafe.UnsafeCopy.UNALIGNED_LONGS) {
            for (int _k = 0; _k < 64; _k += 8) U.putLong(address + _k, U.getLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k));
        } else {
            for (int _k = 0; _k < 16; _k++) U.putFloat(address + 4L * _k, _d[_k]);
        }
        return self;
    }
    public Float4x4 loadCMUnsafe(Float4x4Impl self, long address) {
        float[] _d = self.data;
        if (org.joml2.internal.unsafe.UnsafeCopy.UNALIGNED_LONGS) {
            for (int _k = 0; _k < 64; _k += 8) U.putLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k, U.getLong(address + _k));
        } else {
            for (int _k = 0; _k < 16; _k++) _d[_k] = U.getFloat(address + 4L * _k);
        }
        self.properties = self.determineProperties();
        return self;
    }
    public Float4x4 storeCMDoubleUnsafe(Float4x4Impl self, long address) {
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
    public Float4x4 loadCMDoubleUnsafe(Float4x4Impl self, long address) {
        self.data[0] = (float) U.getDouble(address);
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
        self.data[12] = (float) U.getDouble(address + 96L);
        self.data[13] = (float) U.getDouble(address + 104L);
        self.data[14] = (float) U.getDouble(address + 112L);
        self.data[15] = (float) U.getDouble(address + 120L);
        self.properties = self.determineProperties();
        return self;
    }
    public Float4x4 storeRMUnsafe(Float4x4Impl self, long address) {
        U.putFloat(address, self.data[0]);
        U.putFloat(address + 4L, self.data[4]);
        U.putFloat(address + 8L, self.data[8]);
        U.putFloat(address + 12L, self.data[12]);
        U.putFloat(address + 16L, self.data[1]);
        U.putFloat(address + 20L, self.data[5]);
        U.putFloat(address + 24L, self.data[9]);
        U.putFloat(address + 28L, self.data[13]);
        U.putFloat(address + 32L, self.data[2]);
        U.putFloat(address + 36L, self.data[6]);
        U.putFloat(address + 40L, self.data[10]);
        U.putFloat(address + 44L, self.data[14]);
        U.putFloat(address + 48L, self.data[3]);
        U.putFloat(address + 52L, self.data[7]);
        U.putFloat(address + 56L, self.data[11]);
        U.putFloat(address + 60L, self.data[15]);
        return self;
    }
    public Float4x4 loadRMUnsafe(Float4x4Impl self, long address) {
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
    public Float4x4 storeRMDoubleUnsafe(Float4x4Impl self, long address) {
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
    public Float4x4 loadRMDoubleUnsafe(Float4x4Impl self, long address) {
        self.data[0] = (float) U.getDouble(address);
        self.data[4] = (float) U.getDouble(address + 8L);
        self.data[8] = (float) U.getDouble(address + 16L);
        self.data[12] = (float) U.getDouble(address + 24L);
        self.data[1] = (float) U.getDouble(address + 32L);
        self.data[5] = (float) U.getDouble(address + 40L);
        self.data[9] = (float) U.getDouble(address + 48L);
        self.data[13] = (float) U.getDouble(address + 56L);
        self.data[2] = (float) U.getDouble(address + 64L);
        self.data[6] = (float) U.getDouble(address + 72L);
        self.data[10] = (float) U.getDouble(address + 80L);
        self.data[14] = (float) U.getDouble(address + 88L);
        self.data[3] = (float) U.getDouble(address + 96L);
        self.data[7] = (float) U.getDouble(address + 104L);
        self.data[11] = (float) U.getDouble(address + 112L);
        self.data[15] = (float) U.getDouble(address + 120L);
        self.properties = self.determineProperties();
        return self;
    }
    public Float4x4 storeCMUnsafe(Float4x4Impl self, long address, int stride) {
        float[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            U.putFloat(_a, _d[_o]);
            U.putFloat(_a + 4L, _d[_o + 1]);
            U.putFloat(_a + 8L, _d[_o + 2]);
            U.putFloat(_a + 12L, _d[_o + 3]);
        }
        return self;
    }
    public Float4x4 loadCMUnsafe(Float4x4Impl self, long address, int stride) {
        float[] _d = self.data;
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
    public Float4x4 storeCMDoubleUnsafe(Float4x4Impl self, long address, int stride) {
        float[] _d = self.data;
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
    public Float4x4 loadCMDoubleUnsafe(Float4x4Impl self, long address, int stride) {
        float[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            _d[_o] = (float) U.getDouble(_a);
            _d[_o + 1] = (float) U.getDouble(_a + 8L);
            _d[_o + 2] = (float) U.getDouble(_a + 16L);
            _d[_o + 3] = (float) U.getDouble(_a + 24L);
        }
        self.properties = self.determineProperties();
        return self;
    }
    public Float4x4 storeRMUnsafe(Float4x4Impl self, long address, int stride) {
        float[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            U.putFloat(_a, _d[_o]);
            U.putFloat(_a + 4L, _d[_o + 4]);
            U.putFloat(_a + 8L, _d[_o + 8]);
            U.putFloat(_a + 12L, _d[_o + 12]);
        }
        return self;
    }
    public Float4x4 loadRMUnsafe(Float4x4Impl self, long address, int stride) {
        float[] _d = self.data;
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
    public Float4x4 storeRMDoubleUnsafe(Float4x4Impl self, long address, int stride) {
        float[] _d = self.data;
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
    public Float4x4 loadRMDoubleUnsafe(Float4x4Impl self, long address, int stride) {
        float[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            _d[_o] = (float) U.getDouble(_a);
            _d[_o + 4] = (float) U.getDouble(_a + 8L);
            _d[_o + 8] = (float) U.getDouble(_a + 16L);
            _d[_o + 12] = (float) U.getDouble(_a + 24L);
        }
        self.properties = self.determineProperties();
        return self;
    }
}
