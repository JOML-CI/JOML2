// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Double4x4RawOpsUnsafe extends Double4x4RawOps {
    public Double4x4 storeCMUnsafe(Double4x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8L, self.data[1]);
        _U.putDouble(address + 16L, self.data[2]);
        _U.putDouble(address + 24L, self.data[3]);
        _U.putDouble(address + 32L, self.data[4]);
        _U.putDouble(address + 40L, self.data[5]);
        _U.putDouble(address + 48L, self.data[6]);
        _U.putDouble(address + 56L, self.data[7]);
        _U.putDouble(address + 64L, self.data[8]);
        _U.putDouble(address + 72L, self.data[9]);
        _U.putDouble(address + 80L, self.data[10]);
        _U.putDouble(address + 88L, self.data[11]);
        _U.putDouble(address + 96L, self.data[12]);
        _U.putDouble(address + 104L, self.data[13]);
        _U.putDouble(address + 112L, self.data[14]);
        _U.putDouble(address + 120L, self.data[15]);
        return self;
    }

    public Double4x4 storeCMHeap(Double4x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8L, self.data[1]);
        _U.putDouble(bytes, address + 16L, self.data[2]);
        _U.putDouble(bytes, address + 24L, self.data[3]);
        _U.putDouble(bytes, address + 32L, self.data[4]);
        _U.putDouble(bytes, address + 40L, self.data[5]);
        _U.putDouble(bytes, address + 48L, self.data[6]);
        _U.putDouble(bytes, address + 56L, self.data[7]);
        _U.putDouble(bytes, address + 64L, self.data[8]);
        _U.putDouble(bytes, address + 72L, self.data[9]);
        _U.putDouble(bytes, address + 80L, self.data[10]);
        _U.putDouble(bytes, address + 88L, self.data[11]);
        _U.putDouble(bytes, address + 96L, self.data[12]);
        _U.putDouble(bytes, address + 104L, self.data[13]);
        _U.putDouble(bytes, address + 112L, self.data[14]);
        _U.putDouble(bytes, address + 120L, self.data[15]);
        return self;
    }

    public Double4x4 loadCMUnsafe(Double4x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(address);
        self.data[1] = _U.getDouble(address + 8L);
        self.data[2] = _U.getDouble(address + 16L);
        self.data[3] = _U.getDouble(address + 24L);
        self.data[4] = _U.getDouble(address + 32L);
        self.data[5] = _U.getDouble(address + 40L);
        self.data[6] = _U.getDouble(address + 48L);
        self.data[7] = _U.getDouble(address + 56L);
        self.data[8] = _U.getDouble(address + 64L);
        self.data[9] = _U.getDouble(address + 72L);
        self.data[10] = _U.getDouble(address + 80L);
        self.data[11] = _U.getDouble(address + 88L);
        self.data[12] = _U.getDouble(address + 96L);
        self.data[13] = _U.getDouble(address + 104L);
        self.data[14] = _U.getDouble(address + 112L);
        self.data[15] = _U.getDouble(address + 120L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double4x4 loadCMHeap(Double4x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(bytes, address);
        self.data[1] = _U.getDouble(bytes, address + 8L);
        self.data[2] = _U.getDouble(bytes, address + 16L);
        self.data[3] = _U.getDouble(bytes, address + 24L);
        self.data[4] = _U.getDouble(bytes, address + 32L);
        self.data[5] = _U.getDouble(bytes, address + 40L);
        self.data[6] = _U.getDouble(bytes, address + 48L);
        self.data[7] = _U.getDouble(bytes, address + 56L);
        self.data[8] = _U.getDouble(bytes, address + 64L);
        self.data[9] = _U.getDouble(bytes, address + 72L);
        self.data[10] = _U.getDouble(bytes, address + 80L);
        self.data[11] = _U.getDouble(bytes, address + 88L);
        self.data[12] = _U.getDouble(bytes, address + 96L);
        self.data[13] = _U.getDouble(bytes, address + 104L);
        self.data[14] = _U.getDouble(bytes, address + 112L);
        self.data[15] = _U.getDouble(bytes, address + 120L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double4x4 storeCMFloatUnsafe(Double4x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4L, (float) self.data[1]);
        _U.putFloat(address + 8L, (float) self.data[2]);
        _U.putFloat(address + 12L, (float) self.data[3]);
        _U.putFloat(address + 16L, (float) self.data[4]);
        _U.putFloat(address + 20L, (float) self.data[5]);
        _U.putFloat(address + 24L, (float) self.data[6]);
        _U.putFloat(address + 28L, (float) self.data[7]);
        _U.putFloat(address + 32L, (float) self.data[8]);
        _U.putFloat(address + 36L, (float) self.data[9]);
        _U.putFloat(address + 40L, (float) self.data[10]);
        _U.putFloat(address + 44L, (float) self.data[11]);
        _U.putFloat(address + 48L, (float) self.data[12]);
        _U.putFloat(address + 52L, (float) self.data[13]);
        _U.putFloat(address + 56L, (float) self.data[14]);
        _U.putFloat(address + 60L, (float) self.data[15]);
        return self;
    }

    public Double4x4 storeCMFloatHeap(Double4x4Impl self, byte[] bytes, long address) {
        double[] _d = self.data;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += 16L) {
            U.putFloat(bytes, _a, (float) _d[_o]);
            U.putFloat(bytes, _a + 4L, (float) _d[_o + 1]);
            U.putFloat(bytes, _a + 8L, (float) _d[_o + 2]);
            U.putFloat(bytes, _a + 12L, (float) _d[_o + 3]);
        }
        return self;
    }

    public Double4x4 loadCMFloatUnsafe(Double4x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(address);
        self.data[1] = _U.getFloat(address + 4L);
        self.data[2] = _U.getFloat(address + 8L);
        self.data[3] = _U.getFloat(address + 12L);
        self.data[4] = _U.getFloat(address + 16L);
        self.data[5] = _U.getFloat(address + 20L);
        self.data[6] = _U.getFloat(address + 24L);
        self.data[7] = _U.getFloat(address + 28L);
        self.data[8] = _U.getFloat(address + 32L);
        self.data[9] = _U.getFloat(address + 36L);
        self.data[10] = _U.getFloat(address + 40L);
        self.data[11] = _U.getFloat(address + 44L);
        self.data[12] = _U.getFloat(address + 48L);
        self.data[13] = _U.getFloat(address + 52L);
        self.data[14] = _U.getFloat(address + 56L);
        self.data[15] = _U.getFloat(address + 60L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double4x4 loadCMFloatHeap(Double4x4Impl self, byte[] bytes, long address) {
        double[] _d = self.data;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += 16L) {
            _d[_o] = U.getFloat(bytes, _a);
            _d[_o + 1] = U.getFloat(bytes, _a + 4L);
            _d[_o + 2] = U.getFloat(bytes, _a + 8L);
            _d[_o + 3] = U.getFloat(bytes, _a + 12L);
        }
        self.properties = self.determineProperties();
        return self;
    }

    public Double4x4 storeRMUnsafe(Double4x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8L, self.data[4]);
        _U.putDouble(address + 16L, self.data[8]);
        _U.putDouble(address + 24L, self.data[12]);
        _U.putDouble(address + 32L, self.data[1]);
        _U.putDouble(address + 40L, self.data[5]);
        _U.putDouble(address + 48L, self.data[9]);
        _U.putDouble(address + 56L, self.data[13]);
        _U.putDouble(address + 64L, self.data[2]);
        _U.putDouble(address + 72L, self.data[6]);
        _U.putDouble(address + 80L, self.data[10]);
        _U.putDouble(address + 88L, self.data[14]);
        _U.putDouble(address + 96L, self.data[3]);
        _U.putDouble(address + 104L, self.data[7]);
        _U.putDouble(address + 112L, self.data[11]);
        _U.putDouble(address + 120L, self.data[15]);
        return self;
    }

    public Double4x4 storeRMHeap(Double4x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8L, self.data[4]);
        _U.putDouble(bytes, address + 16L, self.data[8]);
        _U.putDouble(bytes, address + 24L, self.data[12]);
        _U.putDouble(bytes, address + 32L, self.data[1]);
        _U.putDouble(bytes, address + 40L, self.data[5]);
        _U.putDouble(bytes, address + 48L, self.data[9]);
        _U.putDouble(bytes, address + 56L, self.data[13]);
        _U.putDouble(bytes, address + 64L, self.data[2]);
        _U.putDouble(bytes, address + 72L, self.data[6]);
        _U.putDouble(bytes, address + 80L, self.data[10]);
        _U.putDouble(bytes, address + 88L, self.data[14]);
        _U.putDouble(bytes, address + 96L, self.data[3]);
        _U.putDouble(bytes, address + 104L, self.data[7]);
        _U.putDouble(bytes, address + 112L, self.data[11]);
        _U.putDouble(bytes, address + 120L, self.data[15]);
        return self;
    }

    public Double4x4 loadRMUnsafe(Double4x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(address);
        self.data[4] = _U.getDouble(address + 8L);
        self.data[8] = _U.getDouble(address + 16L);
        self.data[12] = _U.getDouble(address + 24L);
        self.data[1] = _U.getDouble(address + 32L);
        self.data[5] = _U.getDouble(address + 40L);
        self.data[9] = _U.getDouble(address + 48L);
        self.data[13] = _U.getDouble(address + 56L);
        self.data[2] = _U.getDouble(address + 64L);
        self.data[6] = _U.getDouble(address + 72L);
        self.data[10] = _U.getDouble(address + 80L);
        self.data[14] = _U.getDouble(address + 88L);
        self.data[3] = _U.getDouble(address + 96L);
        self.data[7] = _U.getDouble(address + 104L);
        self.data[11] = _U.getDouble(address + 112L);
        self.data[15] = _U.getDouble(address + 120L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double4x4 loadRMHeap(Double4x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getDouble(bytes, address);
        self.data[4] = _U.getDouble(bytes, address + 8L);
        self.data[8] = _U.getDouble(bytes, address + 16L);
        self.data[12] = _U.getDouble(bytes, address + 24L);
        self.data[1] = _U.getDouble(bytes, address + 32L);
        self.data[5] = _U.getDouble(bytes, address + 40L);
        self.data[9] = _U.getDouble(bytes, address + 48L);
        self.data[13] = _U.getDouble(bytes, address + 56L);
        self.data[2] = _U.getDouble(bytes, address + 64L);
        self.data[6] = _U.getDouble(bytes, address + 72L);
        self.data[10] = _U.getDouble(bytes, address + 80L);
        self.data[14] = _U.getDouble(bytes, address + 88L);
        self.data[3] = _U.getDouble(bytes, address + 96L);
        self.data[7] = _U.getDouble(bytes, address + 104L);
        self.data[11] = _U.getDouble(bytes, address + 112L);
        self.data[15] = _U.getDouble(bytes, address + 120L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double4x4 storeRMFloatUnsafe(Double4x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.data[0]);
        _U.putFloat(address + 4L, (float) self.data[4]);
        _U.putFloat(address + 8L, (float) self.data[8]);
        _U.putFloat(address + 12L, (float) self.data[12]);
        _U.putFloat(address + 16L, (float) self.data[1]);
        _U.putFloat(address + 20L, (float) self.data[5]);
        _U.putFloat(address + 24L, (float) self.data[9]);
        _U.putFloat(address + 28L, (float) self.data[13]);
        _U.putFloat(address + 32L, (float) self.data[2]);
        _U.putFloat(address + 36L, (float) self.data[6]);
        _U.putFloat(address + 40L, (float) self.data[10]);
        _U.putFloat(address + 44L, (float) self.data[14]);
        _U.putFloat(address + 48L, (float) self.data[3]);
        _U.putFloat(address + 52L, (float) self.data[7]);
        _U.putFloat(address + 56L, (float) self.data[11]);
        _U.putFloat(address + 60L, (float) self.data[15]);
        return self;
    }

    public Double4x4 storeRMFloatHeap(Double4x4Impl self, byte[] bytes, long address) {
        double[] _d = self.data;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += 16L) {
            U.putFloat(bytes, _a, (float) _d[_o]);
            U.putFloat(bytes, _a + 4L, (float) _d[_o + 4]);
            U.putFloat(bytes, _a + 8L, (float) _d[_o + 8]);
            U.putFloat(bytes, _a + 12L, (float) _d[_o + 12]);
        }
        return self;
    }

    public Double4x4 loadRMFloatUnsafe(Double4x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(address);
        self.data[4] = _U.getFloat(address + 4L);
        self.data[8] = _U.getFloat(address + 8L);
        self.data[12] = _U.getFloat(address + 12L);
        self.data[1] = _U.getFloat(address + 16L);
        self.data[5] = _U.getFloat(address + 20L);
        self.data[9] = _U.getFloat(address + 24L);
        self.data[13] = _U.getFloat(address + 28L);
        self.data[2] = _U.getFloat(address + 32L);
        self.data[6] = _U.getFloat(address + 36L);
        self.data[10] = _U.getFloat(address + 40L);
        self.data[14] = _U.getFloat(address + 44L);
        self.data[3] = _U.getFloat(address + 48L);
        self.data[7] = _U.getFloat(address + 52L);
        self.data[11] = _U.getFloat(address + 56L);
        self.data[15] = _U.getFloat(address + 60L);
        self.properties = self.determineProperties();
        return self;
    }

    public Double4x4 loadRMFloatHeap(Double4x4Impl self, byte[] bytes, long address) {
        double[] _d = self.data;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += 16L) {
            _d[_o] = U.getFloat(bytes, _a);
            _d[_o + 4] = U.getFloat(bytes, _a + 4L);
            _d[_o + 8] = U.getFloat(bytes, _a + 8L);
            _d[_o + 12] = U.getFloat(bytes, _a + 12L);
        }
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

    public Double4x4 storeCMHeap(Double4x4Impl self, byte[] bytes, long address, int stride) {
        double[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            U.putDouble(bytes, _a, _d[_o]);
            U.putDouble(bytes, _a + 8L, _d[_o + 1]);
            U.putDouble(bytes, _a + 16L, _d[_o + 2]);
            U.putDouble(bytes, _a + 24L, _d[_o + 3]);
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

    public Double4x4 loadCMHeap(Double4x4Impl self, byte[] bytes, long address, int stride) {
        double[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            _d[_o] = U.getDouble(bytes, _a);
            _d[_o + 1] = U.getDouble(bytes, _a + 8L);
            _d[_o + 2] = U.getDouble(bytes, _a + 16L);
            _d[_o + 3] = U.getDouble(bytes, _a + 24L);
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

    public Double4x4 storeCMFloatHeap(Double4x4Impl self, byte[] bytes, long address, int stride) {
        double[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            U.putFloat(bytes, _a, (float) _d[_o]);
            U.putFloat(bytes, _a + 4L, (float) _d[_o + 1]);
            U.putFloat(bytes, _a + 8L, (float) _d[_o + 2]);
            U.putFloat(bytes, _a + 12L, (float) _d[_o + 3]);
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

    public Double4x4 loadCMFloatHeap(Double4x4Impl self, byte[] bytes, long address, int stride) {
        double[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            _d[_o] = U.getFloat(bytes, _a);
            _d[_o + 1] = U.getFloat(bytes, _a + 4L);
            _d[_o + 2] = U.getFloat(bytes, _a + 8L);
            _d[_o + 3] = U.getFloat(bytes, _a + 12L);
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

    public Double4x4 storeRMHeap(Double4x4Impl self, byte[] bytes, long address, int stride) {
        double[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            U.putDouble(bytes, _a, _d[_o]);
            U.putDouble(bytes, _a + 8L, _d[_o + 4]);
            U.putDouble(bytes, _a + 16L, _d[_o + 8]);
            U.putDouble(bytes, _a + 24L, _d[_o + 12]);
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

    public Double4x4 loadRMHeap(Double4x4Impl self, byte[] bytes, long address, int stride) {
        double[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            _d[_o] = U.getDouble(bytes, _a);
            _d[_o + 4] = U.getDouble(bytes, _a + 8L);
            _d[_o + 8] = U.getDouble(bytes, _a + 16L);
            _d[_o + 12] = U.getDouble(bytes, _a + 24L);
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

    public Double4x4 storeRMFloatHeap(Double4x4Impl self, byte[] bytes, long address, int stride) {
        double[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            U.putFloat(bytes, _a, (float) _d[_o]);
            U.putFloat(bytes, _a + 4L, (float) _d[_o + 4]);
            U.putFloat(bytes, _a + 8L, (float) _d[_o + 8]);
            U.putFloat(bytes, _a + 12L, (float) _d[_o + 12]);
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

    public Double4x4 loadRMFloatHeap(Double4x4Impl self, byte[] bytes, long address, int stride) {
        double[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            _d[_o] = U.getFloat(bytes, _a);
            _d[_o + 4] = U.getFloat(bytes, _a + 4L);
            _d[_o + 8] = U.getFloat(bytes, _a + 8L);
            _d[_o + 12] = U.getFloat(bytes, _a + 12L);
        }
        self.properties = self.determineProperties();
        return self;
    }
}
