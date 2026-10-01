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
            for (int _k = 0; _k < 64; _k += 8)
                U.putLong(address + _k, U.getLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k));
        } else {
            for (int _k = 0; _k < 16; _k++)
                U.putFloat(address + 4L * _k, _d[_k]);
        }
        return self;
    }

    public Float4x4 storeCMHeap(Float4x4Impl self, byte[] bytes, long address) {
        float[] _d = self.data;
        if (org.joml2.internal.unsafe.UnsafeCopy.UNALIGNED_LONGS) {
            for (int _k = 0; _k < 64; _k += 8)
                U.putLong(bytes, address + _k, U.getLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k));
        } else {
            for (int _k = 0; _k < 16; _k++)
                U.putFloat(bytes, address + 4L * _k, _d[_k]);
        }
        return self;
    }

    public Float4x4 loadCMUnsafe(Float4x4Impl self, long address) {
        float[] _d = self.data;
        if (org.joml2.internal.unsafe.UnsafeCopy.UNALIGNED_LONGS) {
            for (int _k = 0; _k < 64; _k += 8)
                U.putLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k, U.getLong(address + _k));
        } else {
            for (int _k = 0; _k < 16; _k++)
                _d[_k] = U.getFloat(address + 4L * _k);
        }
        self.properties = self.determineProperties();
        return self;
    }

    public Float4x4 loadCMHeap(Float4x4Impl self, byte[] bytes, long address) {
        float[] _d = self.data;
        if (org.joml2.internal.unsafe.UnsafeCopy.UNALIGNED_LONGS) {
            for (int _k = 0; _k < 64; _k += 8)
                U.putLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k, U.getLong(bytes, address + _k));
        } else {
            for (int _k = 0; _k < 16; _k++)
                _d[_k] = U.getFloat(bytes, address + 4L * _k);
        }
        self.properties = self.determineProperties();
        return self;
    }

    public Float4x4 storeCMDoubleUnsafe(Float4x4Impl self, long address) {
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

    public Float4x4 storeCMDoubleHeap(Float4x4Impl self, byte[] bytes, long address) {
        float[] _d = self.data;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += 32L) {
            U.putDouble(bytes, _a, _d[_o]);
            U.putDouble(bytes, _a + 8L, _d[_o + 1]);
            U.putDouble(bytes, _a + 16L, _d[_o + 2]);
            U.putDouble(bytes, _a + 24L, _d[_o + 3]);
        }
        return self;
    }

    public Float4x4 loadCMDoubleUnsafe(Float4x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = (float) _U.getDouble(address);
        self.data[1] = (float) _U.getDouble(address + 8L);
        self.data[2] = (float) _U.getDouble(address + 16L);
        self.data[3] = (float) _U.getDouble(address + 24L);
        self.data[4] = (float) _U.getDouble(address + 32L);
        self.data[5] = (float) _U.getDouble(address + 40L);
        self.data[6] = (float) _U.getDouble(address + 48L);
        self.data[7] = (float) _U.getDouble(address + 56L);
        self.data[8] = (float) _U.getDouble(address + 64L);
        self.data[9] = (float) _U.getDouble(address + 72L);
        self.data[10] = (float) _U.getDouble(address + 80L);
        self.data[11] = (float) _U.getDouble(address + 88L);
        self.data[12] = (float) _U.getDouble(address + 96L);
        self.data[13] = (float) _U.getDouble(address + 104L);
        self.data[14] = (float) _U.getDouble(address + 112L);
        self.data[15] = (float) _U.getDouble(address + 120L);
        self.properties = self.determineProperties();
        return self;
    }

    public Float4x4 loadCMDoubleHeap(Float4x4Impl self, byte[] bytes, long address) {
        float[] _d = self.data;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += 32L) {
            _d[_o] = (float) U.getDouble(bytes, _a);
            _d[_o + 1] = (float) U.getDouble(bytes, _a + 8L);
            _d[_o + 2] = (float) U.getDouble(bytes, _a + 16L);
            _d[_o + 3] = (float) U.getDouble(bytes, _a + 24L);
        }
        self.properties = self.determineProperties();
        return self;
    }

    public Float4x4 storeRMUnsafe(Float4x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.data[0]);
        _U.putFloat(address + 4L, self.data[4]);
        _U.putFloat(address + 8L, self.data[8]);
        _U.putFloat(address + 12L, self.data[12]);
        _U.putFloat(address + 16L, self.data[1]);
        _U.putFloat(address + 20L, self.data[5]);
        _U.putFloat(address + 24L, self.data[9]);
        _U.putFloat(address + 28L, self.data[13]);
        _U.putFloat(address + 32L, self.data[2]);
        _U.putFloat(address + 36L, self.data[6]);
        _U.putFloat(address + 40L, self.data[10]);
        _U.putFloat(address + 44L, self.data[14]);
        _U.putFloat(address + 48L, self.data[3]);
        _U.putFloat(address + 52L, self.data[7]);
        _U.putFloat(address + 56L, self.data[11]);
        _U.putFloat(address + 60L, self.data[15]);
        return self;
    }

    public Float4x4 storeRMHeap(Float4x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.data[0]);
        _U.putFloat(bytes, address + 4L, self.data[4]);
        _U.putFloat(bytes, address + 8L, self.data[8]);
        _U.putFloat(bytes, address + 12L, self.data[12]);
        _U.putFloat(bytes, address + 16L, self.data[1]);
        _U.putFloat(bytes, address + 20L, self.data[5]);
        _U.putFloat(bytes, address + 24L, self.data[9]);
        _U.putFloat(bytes, address + 28L, self.data[13]);
        _U.putFloat(bytes, address + 32L, self.data[2]);
        _U.putFloat(bytes, address + 36L, self.data[6]);
        _U.putFloat(bytes, address + 40L, self.data[10]);
        _U.putFloat(bytes, address + 44L, self.data[14]);
        _U.putFloat(bytes, address + 48L, self.data[3]);
        _U.putFloat(bytes, address + 52L, self.data[7]);
        _U.putFloat(bytes, address + 56L, self.data[11]);
        _U.putFloat(bytes, address + 60L, self.data[15]);
        return self;
    }

    public Float4x4 loadRMUnsafe(Float4x4Impl self, long address) {
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

    public Float4x4 loadRMHeap(Float4x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(bytes, address);
        self.data[4] = _U.getFloat(bytes, address + 4L);
        self.data[8] = _U.getFloat(bytes, address + 8L);
        self.data[12] = _U.getFloat(bytes, address + 12L);
        self.data[1] = _U.getFloat(bytes, address + 16L);
        self.data[5] = _U.getFloat(bytes, address + 20L);
        self.data[9] = _U.getFloat(bytes, address + 24L);
        self.data[13] = _U.getFloat(bytes, address + 28L);
        self.data[2] = _U.getFloat(bytes, address + 32L);
        self.data[6] = _U.getFloat(bytes, address + 36L);
        self.data[10] = _U.getFloat(bytes, address + 40L);
        self.data[14] = _U.getFloat(bytes, address + 44L);
        self.data[3] = _U.getFloat(bytes, address + 48L);
        self.data[7] = _U.getFloat(bytes, address + 52L);
        self.data[11] = _U.getFloat(bytes, address + 56L);
        self.data[15] = _U.getFloat(bytes, address + 60L);
        self.properties = self.determineProperties();
        return self;
    }

    public Float4x4 storeRMDoubleUnsafe(Float4x4Impl self, long address) {
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

    public Float4x4 storeRMDoubleHeap(Float4x4Impl self, byte[] bytes, long address) {
        float[] _d = self.data;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += 32L) {
            U.putDouble(bytes, _a, _d[_o]);
            U.putDouble(bytes, _a + 8L, _d[_o + 4]);
            U.putDouble(bytes, _a + 16L, _d[_o + 8]);
            U.putDouble(bytes, _a + 24L, _d[_o + 12]);
        }
        return self;
    }

    public Float4x4 loadRMDoubleUnsafe(Float4x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = (float) _U.getDouble(address);
        self.data[4] = (float) _U.getDouble(address + 8L);
        self.data[8] = (float) _U.getDouble(address + 16L);
        self.data[12] = (float) _U.getDouble(address + 24L);
        self.data[1] = (float) _U.getDouble(address + 32L);
        self.data[5] = (float) _U.getDouble(address + 40L);
        self.data[9] = (float) _U.getDouble(address + 48L);
        self.data[13] = (float) _U.getDouble(address + 56L);
        self.data[2] = (float) _U.getDouble(address + 64L);
        self.data[6] = (float) _U.getDouble(address + 72L);
        self.data[10] = (float) _U.getDouble(address + 80L);
        self.data[14] = (float) _U.getDouble(address + 88L);
        self.data[3] = (float) _U.getDouble(address + 96L);
        self.data[7] = (float) _U.getDouble(address + 104L);
        self.data[11] = (float) _U.getDouble(address + 112L);
        self.data[15] = (float) _U.getDouble(address + 120L);
        self.properties = self.determineProperties();
        return self;
    }

    public Float4x4 loadRMDoubleHeap(Float4x4Impl self, byte[] bytes, long address) {
        float[] _d = self.data;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += 32L) {
            _d[_o] = (float) U.getDouble(bytes, _a);
            _d[_o + 4] = (float) U.getDouble(bytes, _a + 8L);
            _d[_o + 8] = (float) U.getDouble(bytes, _a + 16L);
            _d[_o + 12] = (float) U.getDouble(bytes, _a + 24L);
        }
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

    public Float4x4 storeCMHeap(Float4x4Impl self, byte[] bytes, long address, int stride) {
        float[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            U.putFloat(bytes, _a, _d[_o]);
            U.putFloat(bytes, _a + 4L, _d[_o + 1]);
            U.putFloat(bytes, _a + 8L, _d[_o + 2]);
            U.putFloat(bytes, _a + 12L, _d[_o + 3]);
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

    public Float4x4 loadCMHeap(Float4x4Impl self, byte[] bytes, long address, int stride) {
        float[] _d = self.data;
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

    public Float4x4 storeCMDoubleHeap(Float4x4Impl self, byte[] bytes, long address, int stride) {
        float[] _d = self.data;
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

    public Float4x4 loadCMDoubleHeap(Float4x4Impl self, byte[] bytes, long address, int stride) {
        float[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 4, _a += _ps) {
            _d[_o] = (float) U.getDouble(bytes, _a);
            _d[_o + 1] = (float) U.getDouble(bytes, _a + 8L);
            _d[_o + 2] = (float) U.getDouble(bytes, _a + 16L);
            _d[_o + 3] = (float) U.getDouble(bytes, _a + 24L);
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

    public Float4x4 storeRMHeap(Float4x4Impl self, byte[] bytes, long address, int stride) {
        float[] _d = self.data;
        long _ps = stride * 4L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            U.putFloat(bytes, _a, _d[_o]);
            U.putFloat(bytes, _a + 4L, _d[_o + 4]);
            U.putFloat(bytes, _a + 8L, _d[_o + 8]);
            U.putFloat(bytes, _a + 12L, _d[_o + 12]);
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

    public Float4x4 loadRMHeap(Float4x4Impl self, byte[] bytes, long address, int stride) {
        float[] _d = self.data;
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

    public Float4x4 storeRMDoubleHeap(Float4x4Impl self, byte[] bytes, long address, int stride) {
        float[] _d = self.data;
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

    public Float4x4 loadRMDoubleHeap(Float4x4Impl self, byte[] bytes, long address, int stride) {
        float[] _d = self.data;
        long _ps = stride * 8L;
        long _a = address;
        for (int _j = 0, _o = 0; _j < 4; _j++, _o += 1, _a += _ps) {
            _d[_o] = (float) U.getDouble(bytes, _a);
            _d[_o + 4] = (float) U.getDouble(bytes, _a + 8L);
            _d[_o + 8] = (float) U.getDouble(bytes, _a + 16L);
            _d[_o + 12] = (float) U.getDouble(bytes, _a + 24L);
        }
        self.properties = self.determineProperties();
        return self;
    }
}
