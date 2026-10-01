// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Float3x4RawOpsUnsafe extends Float3x4RawOps {
    public Float3x4 storeCMUnsafe(Float3x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.data[0]);
        _U.putFloat(address + 4L, self.data[4]);
        _U.putFloat(address + 8L, self.data[8]);
        _U.putFloat(address + 12L, self.data[1]);
        _U.putFloat(address + 16L, self.data[5]);
        _U.putFloat(address + 20L, self.data[9]);
        _U.putFloat(address + 24L, self.data[2]);
        _U.putFloat(address + 28L, self.data[6]);
        _U.putFloat(address + 32L, self.data[10]);
        _U.putFloat(address + 36L, self.data[3]);
        _U.putFloat(address + 40L, self.data[7]);
        _U.putFloat(address + 44L, self.data[11]);
        return self;
    }

    public Float3x4 storeCMHeap(Float3x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.data[0]);
        _U.putFloat(bytes, address + 4L, self.data[4]);
        _U.putFloat(bytes, address + 8L, self.data[8]);
        _U.putFloat(bytes, address + 12L, self.data[1]);
        _U.putFloat(bytes, address + 16L, self.data[5]);
        _U.putFloat(bytes, address + 20L, self.data[9]);
        _U.putFloat(bytes, address + 24L, self.data[2]);
        _U.putFloat(bytes, address + 28L, self.data[6]);
        _U.putFloat(bytes, address + 32L, self.data[10]);
        _U.putFloat(bytes, address + 36L, self.data[3]);
        _U.putFloat(bytes, address + 40L, self.data[7]);
        _U.putFloat(bytes, address + 44L, self.data[11]);
        return self;
    }

    public Float3x4 loadCMUnsafe(Float3x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(address);
        self.data[4] = _U.getFloat(address + 4L);
        self.data[8] = _U.getFloat(address + 8L);
        self.data[1] = _U.getFloat(address + 12L);
        self.data[5] = _U.getFloat(address + 16L);
        self.data[9] = _U.getFloat(address + 20L);
        self.data[2] = _U.getFloat(address + 24L);
        self.data[6] = _U.getFloat(address + 28L);
        self.data[10] = _U.getFloat(address + 32L);
        self.data[3] = _U.getFloat(address + 36L);
        self.data[7] = _U.getFloat(address + 40L);
        self.data[11] = _U.getFloat(address + 44L);
        self.properties = self.determineProperties();
        return self;
    }

    public Float3x4 loadCMHeap(Float3x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(bytes, address);
        self.data[4] = _U.getFloat(bytes, address + 4L);
        self.data[8] = _U.getFloat(bytes, address + 8L);
        self.data[1] = _U.getFloat(bytes, address + 12L);
        self.data[5] = _U.getFloat(bytes, address + 16L);
        self.data[9] = _U.getFloat(bytes, address + 20L);
        self.data[2] = _U.getFloat(bytes, address + 24L);
        self.data[6] = _U.getFloat(bytes, address + 28L);
        self.data[10] = _U.getFloat(bytes, address + 32L);
        self.data[3] = _U.getFloat(bytes, address + 36L);
        self.data[7] = _U.getFloat(bytes, address + 40L);
        self.data[11] = _U.getFloat(bytes, address + 44L);
        self.properties = self.determineProperties();
        return self;
    }

    public Float3x4 storeCMDoubleUnsafe(Float3x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8L, self.data[4]);
        _U.putDouble(address + 16L, self.data[8]);
        _U.putDouble(address + 24L, self.data[1]);
        _U.putDouble(address + 32L, self.data[5]);
        _U.putDouble(address + 40L, self.data[9]);
        _U.putDouble(address + 48L, self.data[2]);
        _U.putDouble(address + 56L, self.data[6]);
        _U.putDouble(address + 64L, self.data[10]);
        _U.putDouble(address + 72L, self.data[3]);
        _U.putDouble(address + 80L, self.data[7]);
        _U.putDouble(address + 88L, self.data[11]);
        return self;
    }

    public Float3x4 storeCMDoubleHeap(Float3x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8L, self.data[4]);
        _U.putDouble(bytes, address + 16L, self.data[8]);
        _U.putDouble(bytes, address + 24L, self.data[1]);
        _U.putDouble(bytes, address + 32L, self.data[5]);
        _U.putDouble(bytes, address + 40L, self.data[9]);
        _U.putDouble(bytes, address + 48L, self.data[2]);
        _U.putDouble(bytes, address + 56L, self.data[6]);
        _U.putDouble(bytes, address + 64L, self.data[10]);
        _U.putDouble(bytes, address + 72L, self.data[3]);
        _U.putDouble(bytes, address + 80L, self.data[7]);
        _U.putDouble(bytes, address + 88L, self.data[11]);
        return self;
    }

    public Float3x4 loadCMDoubleUnsafe(Float3x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = (float) _U.getDouble(address);
        self.data[4] = (float) _U.getDouble(address + 8L);
        self.data[8] = (float) _U.getDouble(address + 16L);
        self.data[1] = (float) _U.getDouble(address + 24L);
        self.data[5] = (float) _U.getDouble(address + 32L);
        self.data[9] = (float) _U.getDouble(address + 40L);
        self.data[2] = (float) _U.getDouble(address + 48L);
        self.data[6] = (float) _U.getDouble(address + 56L);
        self.data[10] = (float) _U.getDouble(address + 64L);
        self.data[3] = (float) _U.getDouble(address + 72L);
        self.data[7] = (float) _U.getDouble(address + 80L);
        self.data[11] = (float) _U.getDouble(address + 88L);
        self.properties = self.determineProperties();
        return self;
    }

    public Float3x4 loadCMDoubleHeap(Float3x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = (float) _U.getDouble(bytes, address);
        self.data[4] = (float) _U.getDouble(bytes, address + 8L);
        self.data[8] = (float) _U.getDouble(bytes, address + 16L);
        self.data[1] = (float) _U.getDouble(bytes, address + 24L);
        self.data[5] = (float) _U.getDouble(bytes, address + 32L);
        self.data[9] = (float) _U.getDouble(bytes, address + 40L);
        self.data[2] = (float) _U.getDouble(bytes, address + 48L);
        self.data[6] = (float) _U.getDouble(bytes, address + 56L);
        self.data[10] = (float) _U.getDouble(bytes, address + 64L);
        self.data[3] = (float) _U.getDouble(bytes, address + 72L);
        self.data[7] = (float) _U.getDouble(bytes, address + 80L);
        self.data[11] = (float) _U.getDouble(bytes, address + 88L);
        self.properties = self.determineProperties();
        return self;
    }

    public Float3x4 storeRMUnsafe(Float3x4Impl self, long address) {
        float[] _d = self.data;
        if (org.joml2.internal.unsafe.UnsafeCopy.UNALIGNED_LONGS) {
            for (int _k = 0; _k < 48; _k += 8)
                U.putLong(address + _k, U.getLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k));
        } else {
            for (int _k = 0; _k < 12; _k++)
                U.putFloat(address + 4L * _k, _d[_k]);
        }
        return self;
    }

    public Float3x4 storeRMHeap(Float3x4Impl self, byte[] bytes, long address) {
        float[] _d = self.data;
        if (org.joml2.internal.unsafe.UnsafeCopy.UNALIGNED_LONGS) {
            for (int _k = 0; _k < 48; _k += 8)
                U.putLong(bytes, address + _k, U.getLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k));
        } else {
            for (int _k = 0; _k < 12; _k++)
                U.putFloat(bytes, address + 4L * _k, _d[_k]);
        }
        return self;
    }

    public Float3x4 loadRMUnsafe(Float3x4Impl self, long address) {
        float[] _d = self.data;
        if (org.joml2.internal.unsafe.UnsafeCopy.UNALIGNED_LONGS) {
            for (int _k = 0; _k < 48; _k += 8)
                U.putLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k, U.getLong(address + _k));
        } else {
            for (int _k = 0; _k < 12; _k++)
                _d[_k] = U.getFloat(address + 4L * _k);
        }
        self.properties = self.determineProperties();
        return self;
    }

    public Float3x4 loadRMHeap(Float3x4Impl self, byte[] bytes, long address) {
        float[] _d = self.data;
        if (org.joml2.internal.unsafe.UnsafeCopy.UNALIGNED_LONGS) {
            for (int _k = 0; _k < 48; _k += 8)
                U.putLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k, U.getLong(bytes, address + _k));
        } else {
            for (int _k = 0; _k < 12; _k++)
                _d[_k] = U.getFloat(bytes, address + 4L * _k);
        }
        self.properties = self.determineProperties();
        return self;
    }

    public Float3x4 storeRMDoubleUnsafe(Float3x4Impl self, long address) {
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
        return self;
    }

    public Float3x4 storeRMDoubleHeap(Float3x4Impl self, byte[] bytes, long address) {
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
        return self;
    }

    public Float3x4 loadRMDoubleUnsafe(Float3x4Impl self, long address) {
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
        self.properties = self.determineProperties();
        return self;
    }

    public Float3x4 loadRMDoubleHeap(Float3x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = (float) _U.getDouble(bytes, address);
        self.data[1] = (float) _U.getDouble(bytes, address + 8L);
        self.data[2] = (float) _U.getDouble(bytes, address + 16L);
        self.data[3] = (float) _U.getDouble(bytes, address + 24L);
        self.data[4] = (float) _U.getDouble(bytes, address + 32L);
        self.data[5] = (float) _U.getDouble(bytes, address + 40L);
        self.data[6] = (float) _U.getDouble(bytes, address + 48L);
        self.data[7] = (float) _U.getDouble(bytes, address + 56L);
        self.data[8] = (float) _U.getDouble(bytes, address + 64L);
        self.data[9] = (float) _U.getDouble(bytes, address + 72L);
        self.data[10] = (float) _U.getDouble(bytes, address + 80L);
        self.data[11] = (float) _U.getDouble(bytes, address + 88L);
        self.properties = self.determineProperties();
        return self;
    }

    public Float3x4 storeCMUnsafe(Float3x4Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.data[0]);
        _U.putFloat(address + 4, self.data[4]);
        _U.putFloat(address + 8, self.data[8]);
        _U.putFloat(_p1, self.data[1]);
        _U.putFloat(_p1 + 4, self.data[5]);
        _U.putFloat(_p1 + 8, self.data[9]);
        _U.putFloat(_p2, self.data[2]);
        _U.putFloat(_p2 + 4, self.data[6]);
        _U.putFloat(_p2 + 8, self.data[10]);
        _U.putFloat(_p3, self.data[3]);
        _U.putFloat(_p3 + 4, self.data[7]);
        _U.putFloat(_p3 + 8, self.data[11]);
        return self;
    }

    public Float3x4 storeCMHeap(Float3x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.data[0]);
        _U.putFloat(bytes, address + 4, self.data[4]);
        _U.putFloat(bytes, address + 8, self.data[8]);
        _U.putFloat(bytes, _p1, self.data[1]);
        _U.putFloat(bytes, _p1 + 4, self.data[5]);
        _U.putFloat(bytes, _p1 + 8, self.data[9]);
        _U.putFloat(bytes, _p2, self.data[2]);
        _U.putFloat(bytes, _p2 + 4, self.data[6]);
        _U.putFloat(bytes, _p2 + 8, self.data[10]);
        _U.putFloat(bytes, _p3, self.data[3]);
        _U.putFloat(bytes, _p3 + 4, self.data[7]);
        _U.putFloat(bytes, _p3 + 8, self.data[11]);
        return self;
    }

    public Float3x4 loadCMUnsafe(Float3x4Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(address);
        self.data[4] = _U.getFloat(address + 4);
        self.data[8] = _U.getFloat(address + 8);
        self.data[1] = _U.getFloat(_p1);
        self.data[5] = _U.getFloat(_p1 + 4);
        self.data[9] = _U.getFloat(_p1 + 8);
        self.data[2] = _U.getFloat(_p2);
        self.data[6] = _U.getFloat(_p2 + 4);
        self.data[10] = _U.getFloat(_p2 + 8);
        self.data[3] = _U.getFloat(_p3);
        self.data[7] = _U.getFloat(_p3 + 4);
        self.data[11] = _U.getFloat(_p3 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public Float3x4 loadCMHeap(Float3x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(bytes, address);
        self.data[4] = _U.getFloat(bytes, address + 4);
        self.data[8] = _U.getFloat(bytes, address + 8);
        self.data[1] = _U.getFloat(bytes, _p1);
        self.data[5] = _U.getFloat(bytes, _p1 + 4);
        self.data[9] = _U.getFloat(bytes, _p1 + 8);
        self.data[2] = _U.getFloat(bytes, _p2);
        self.data[6] = _U.getFloat(bytes, _p2 + 4);
        self.data[10] = _U.getFloat(bytes, _p2 + 8);
        self.data[3] = _U.getFloat(bytes, _p3);
        self.data[7] = _U.getFloat(bytes, _p3 + 4);
        self.data[11] = _U.getFloat(bytes, _p3 + 8);
        self.properties = self.determineProperties();
        return self;
    }

    public Float3x4 storeCMDoubleUnsafe(Float3x4Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8, self.data[4]);
        _U.putDouble(address + 16, self.data[8]);
        _U.putDouble(_p1, self.data[1]);
        _U.putDouble(_p1 + 8, self.data[5]);
        _U.putDouble(_p1 + 16, self.data[9]);
        _U.putDouble(_p2, self.data[2]);
        _U.putDouble(_p2 + 8, self.data[6]);
        _U.putDouble(_p2 + 16, self.data[10]);
        _U.putDouble(_p3, self.data[3]);
        _U.putDouble(_p3 + 8, self.data[7]);
        _U.putDouble(_p3 + 16, self.data[11]);
        return self;
    }

    public Float3x4 storeCMDoubleHeap(Float3x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8, self.data[4]);
        _U.putDouble(bytes, address + 16, self.data[8]);
        _U.putDouble(bytes, _p1, self.data[1]);
        _U.putDouble(bytes, _p1 + 8, self.data[5]);
        _U.putDouble(bytes, _p1 + 16, self.data[9]);
        _U.putDouble(bytes, _p2, self.data[2]);
        _U.putDouble(bytes, _p2 + 8, self.data[6]);
        _U.putDouble(bytes, _p2 + 16, self.data[10]);
        _U.putDouble(bytes, _p3, self.data[3]);
        _U.putDouble(bytes, _p3 + 8, self.data[7]);
        _U.putDouble(bytes, _p3 + 16, self.data[11]);
        return self;
    }

    public Float3x4 loadCMDoubleUnsafe(Float3x4Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = (float) _U.getDouble(address);
        self.data[4] = (float) _U.getDouble(address + 8);
        self.data[8] = (float) _U.getDouble(address + 16);
        self.data[1] = (float) _U.getDouble(_p1);
        self.data[5] = (float) _U.getDouble(_p1 + 8);
        self.data[9] = (float) _U.getDouble(_p1 + 16);
        self.data[2] = (float) _U.getDouble(_p2);
        self.data[6] = (float) _U.getDouble(_p2 + 8);
        self.data[10] = (float) _U.getDouble(_p2 + 16);
        self.data[3] = (float) _U.getDouble(_p3);
        self.data[7] = (float) _U.getDouble(_p3 + 8);
        self.data[11] = (float) _U.getDouble(_p3 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    public Float3x4 loadCMDoubleHeap(Float3x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        long _p3 = _p2 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = (float) _U.getDouble(bytes, address);
        self.data[4] = (float) _U.getDouble(bytes, address + 8);
        self.data[8] = (float) _U.getDouble(bytes, address + 16);
        self.data[1] = (float) _U.getDouble(bytes, _p1);
        self.data[5] = (float) _U.getDouble(bytes, _p1 + 8);
        self.data[9] = (float) _U.getDouble(bytes, _p1 + 16);
        self.data[2] = (float) _U.getDouble(bytes, _p2);
        self.data[6] = (float) _U.getDouble(bytes, _p2 + 8);
        self.data[10] = (float) _U.getDouble(bytes, _p2 + 16);
        self.data[3] = (float) _U.getDouble(bytes, _p3);
        self.data[7] = (float) _U.getDouble(bytes, _p3 + 8);
        self.data[11] = (float) _U.getDouble(bytes, _p3 + 16);
        self.properties = self.determineProperties();
        return self;
    }

    public Float3x4 storeRMUnsafe(Float3x4Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.data[0]);
        _U.putFloat(address + 4, self.data[1]);
        _U.putFloat(address + 8, self.data[2]);
        _U.putFloat(address + 12, self.data[3]);
        _U.putFloat(_p1, self.data[4]);
        _U.putFloat(_p1 + 4, self.data[5]);
        _U.putFloat(_p1 + 8, self.data[6]);
        _U.putFloat(_p1 + 12, self.data[7]);
        _U.putFloat(_p2, self.data[8]);
        _U.putFloat(_p2 + 4, self.data[9]);
        _U.putFloat(_p2 + 8, self.data[10]);
        _U.putFloat(_p2 + 12, self.data[11]);
        return self;
    }

    public Float3x4 storeRMHeap(Float3x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.data[0]);
        _U.putFloat(bytes, address + 4, self.data[1]);
        _U.putFloat(bytes, address + 8, self.data[2]);
        _U.putFloat(bytes, address + 12, self.data[3]);
        _U.putFloat(bytes, _p1, self.data[4]);
        _U.putFloat(bytes, _p1 + 4, self.data[5]);
        _U.putFloat(bytes, _p1 + 8, self.data[6]);
        _U.putFloat(bytes, _p1 + 12, self.data[7]);
        _U.putFloat(bytes, _p2, self.data[8]);
        _U.putFloat(bytes, _p2 + 4, self.data[9]);
        _U.putFloat(bytes, _p2 + 8, self.data[10]);
        _U.putFloat(bytes, _p2 + 12, self.data[11]);
        return self;
    }

    public Float3x4 loadRMUnsafe(Float3x4Impl self, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(address);
        self.data[1] = _U.getFloat(address + 4);
        self.data[2] = _U.getFloat(address + 8);
        self.data[3] = _U.getFloat(address + 12);
        self.data[4] = _U.getFloat(_p1);
        self.data[5] = _U.getFloat(_p1 + 4);
        self.data[6] = _U.getFloat(_p1 + 8);
        self.data[7] = _U.getFloat(_p1 + 12);
        self.data[8] = _U.getFloat(_p2);
        self.data[9] = _U.getFloat(_p2 + 4);
        self.data[10] = _U.getFloat(_p2 + 8);
        self.data[11] = _U.getFloat(_p2 + 12);
        self.properties = self.determineProperties();
        return self;
    }

    public Float3x4 loadRMHeap(Float3x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 4L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(bytes, address);
        self.data[1] = _U.getFloat(bytes, address + 4);
        self.data[2] = _U.getFloat(bytes, address + 8);
        self.data[3] = _U.getFloat(bytes, address + 12);
        self.data[4] = _U.getFloat(bytes, _p1);
        self.data[5] = _U.getFloat(bytes, _p1 + 4);
        self.data[6] = _U.getFloat(bytes, _p1 + 8);
        self.data[7] = _U.getFloat(bytes, _p1 + 12);
        self.data[8] = _U.getFloat(bytes, _p2);
        self.data[9] = _U.getFloat(bytes, _p2 + 4);
        self.data[10] = _U.getFloat(bytes, _p2 + 8);
        self.data[11] = _U.getFloat(bytes, _p2 + 12);
        self.properties = self.determineProperties();
        return self;
    }

    public Float3x4 storeRMDoubleUnsafe(Float3x4Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8, self.data[1]);
        _U.putDouble(address + 16, self.data[2]);
        _U.putDouble(address + 24, self.data[3]);
        _U.putDouble(_p1, self.data[4]);
        _U.putDouble(_p1 + 8, self.data[5]);
        _U.putDouble(_p1 + 16, self.data[6]);
        _U.putDouble(_p1 + 24, self.data[7]);
        _U.putDouble(_p2, self.data[8]);
        _U.putDouble(_p2 + 8, self.data[9]);
        _U.putDouble(_p2 + 16, self.data[10]);
        _U.putDouble(_p2 + 24, self.data[11]);
        return self;
    }

    public Float3x4 storeRMDoubleHeap(Float3x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8, self.data[1]);
        _U.putDouble(bytes, address + 16, self.data[2]);
        _U.putDouble(bytes, address + 24, self.data[3]);
        _U.putDouble(bytes, _p1, self.data[4]);
        _U.putDouble(bytes, _p1 + 8, self.data[5]);
        _U.putDouble(bytes, _p1 + 16, self.data[6]);
        _U.putDouble(bytes, _p1 + 24, self.data[7]);
        _U.putDouble(bytes, _p2, self.data[8]);
        _U.putDouble(bytes, _p2 + 8, self.data[9]);
        _U.putDouble(bytes, _p2 + 16, self.data[10]);
        _U.putDouble(bytes, _p2 + 24, self.data[11]);
        return self;
    }

    public Float3x4 loadRMDoubleUnsafe(Float3x4Impl self, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = (float) _U.getDouble(address);
        self.data[1] = (float) _U.getDouble(address + 8);
        self.data[2] = (float) _U.getDouble(address + 16);
        self.data[3] = (float) _U.getDouble(address + 24);
        self.data[4] = (float) _U.getDouble(_p1);
        self.data[5] = (float) _U.getDouble(_p1 + 8);
        self.data[6] = (float) _U.getDouble(_p1 + 16);
        self.data[7] = (float) _U.getDouble(_p1 + 24);
        self.data[8] = (float) _U.getDouble(_p2);
        self.data[9] = (float) _U.getDouble(_p2 + 8);
        self.data[10] = (float) _U.getDouble(_p2 + 16);
        self.data[11] = (float) _U.getDouble(_p2 + 24);
        self.properties = self.determineProperties();
        return self;
    }

    public Float3x4 loadRMDoubleHeap(Float3x4Impl self, byte[] bytes, long address, int stride) {
        long _ps = stride * 8L;
        long _p1 = address + _ps;
        long _p2 = _p1 + _ps;
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = (float) _U.getDouble(bytes, address);
        self.data[1] = (float) _U.getDouble(bytes, address + 8);
        self.data[2] = (float) _U.getDouble(bytes, address + 16);
        self.data[3] = (float) _U.getDouble(bytes, address + 24);
        self.data[4] = (float) _U.getDouble(bytes, _p1);
        self.data[5] = (float) _U.getDouble(bytes, _p1 + 8);
        self.data[6] = (float) _U.getDouble(bytes, _p1 + 16);
        self.data[7] = (float) _U.getDouble(bytes, _p1 + 24);
        self.data[8] = (float) _U.getDouble(bytes, _p2);
        self.data[9] = (float) _U.getDouble(bytes, _p2 + 8);
        self.data[10] = (float) _U.getDouble(bytes, _p2 + 16);
        self.data[11] = (float) _U.getDouble(bytes, _p2 + 24);
        self.properties = self.determineProperties();
        return self;
    }

    public Float3x4 storeCM4x4Unsafe(Float3x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.data[0]);
        _U.putFloat(address + 4L, self.data[4]);
        _U.putFloat(address + 8L, self.data[8]);
        _U.putFloat(address + 12L, 0.0f);
        _U.putFloat(address + 16L, self.data[1]);
        _U.putFloat(address + 20L, self.data[5]);
        _U.putFloat(address + 24L, self.data[9]);
        _U.putFloat(address + 28L, 0.0f);
        _U.putFloat(address + 32L, self.data[2]);
        _U.putFloat(address + 36L, self.data[6]);
        _U.putFloat(address + 40L, self.data[10]);
        _U.putFloat(address + 44L, 0.0f);
        _U.putFloat(address + 48L, self.data[3]);
        _U.putFloat(address + 52L, self.data[7]);
        _U.putFloat(address + 56L, self.data[11]);
        _U.putFloat(address + 60L, 1.0f);
        return self;
    }

    public Float3x4 storeCM4x4Heap(Float3x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.data[0]);
        _U.putFloat(bytes, address + 4L, self.data[4]);
        _U.putFloat(bytes, address + 8L, self.data[8]);
        _U.putFloat(bytes, address + 12L, 0.0f);
        _U.putFloat(bytes, address + 16L, self.data[1]);
        _U.putFloat(bytes, address + 20L, self.data[5]);
        _U.putFloat(bytes, address + 24L, self.data[9]);
        _U.putFloat(bytes, address + 28L, 0.0f);
        _U.putFloat(bytes, address + 32L, self.data[2]);
        _U.putFloat(bytes, address + 36L, self.data[6]);
        _U.putFloat(bytes, address + 40L, self.data[10]);
        _U.putFloat(bytes, address + 44L, 0.0f);
        _U.putFloat(bytes, address + 48L, self.data[3]);
        _U.putFloat(bytes, address + 52L, self.data[7]);
        _U.putFloat(bytes, address + 56L, self.data[11]);
        _U.putFloat(bytes, address + 60L, 1.0f);
        return self;
    }

    public Float3x4 storeCM4x4DoubleUnsafe(Float3x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.data[0]);
        _U.putDouble(address + 8L, self.data[4]);
        _U.putDouble(address + 16L, self.data[8]);
        _U.putDouble(address + 24L, 0.0);
        _U.putDouble(address + 32L, self.data[1]);
        _U.putDouble(address + 40L, self.data[5]);
        _U.putDouble(address + 48L, self.data[9]);
        _U.putDouble(address + 56L, 0.0);
        _U.putDouble(address + 64L, self.data[2]);
        _U.putDouble(address + 72L, self.data[6]);
        _U.putDouble(address + 80L, self.data[10]);
        _U.putDouble(address + 88L, 0.0);
        _U.putDouble(address + 96L, self.data[3]);
        _U.putDouble(address + 104L, self.data[7]);
        _U.putDouble(address + 112L, self.data[11]);
        _U.putDouble(address + 120L, 1.0);
        return self;
    }

    public Float3x4 storeCM4x4DoubleHeap(Float3x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.data[0]);
        _U.putDouble(bytes, address + 8L, self.data[4]);
        _U.putDouble(bytes, address + 16L, self.data[8]);
        _U.putDouble(bytes, address + 24L, 0.0);
        _U.putDouble(bytes, address + 32L, self.data[1]);
        _U.putDouble(bytes, address + 40L, self.data[5]);
        _U.putDouble(bytes, address + 48L, self.data[9]);
        _U.putDouble(bytes, address + 56L, 0.0);
        _U.putDouble(bytes, address + 64L, self.data[2]);
        _U.putDouble(bytes, address + 72L, self.data[6]);
        _U.putDouble(bytes, address + 80L, self.data[10]);
        _U.putDouble(bytes, address + 88L, 0.0);
        _U.putDouble(bytes, address + 96L, self.data[3]);
        _U.putDouble(bytes, address + 104L, self.data[7]);
        _U.putDouble(bytes, address + 112L, self.data[11]);
        _U.putDouble(bytes, address + 120L, 1.0);
        return self;
    }

    public Float3x4 storeRM4x4Unsafe(Float3x4Impl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.data[0]);
        _U.putFloat(address + 4L, self.data[1]);
        _U.putFloat(address + 8L, self.data[2]);
        _U.putFloat(address + 12L, self.data[3]);
        _U.putFloat(address + 16L, self.data[4]);
        _U.putFloat(address + 20L, self.data[5]);
        _U.putFloat(address + 24L, self.data[6]);
        _U.putFloat(address + 28L, self.data[7]);
        _U.putFloat(address + 32L, self.data[8]);
        _U.putFloat(address + 36L, self.data[9]);
        _U.putFloat(address + 40L, self.data[10]);
        _U.putFloat(address + 44L, self.data[11]);
        _U.putFloat(address + 48L, 0.0f);
        _U.putFloat(address + 52L, 0.0f);
        _U.putFloat(address + 56L, 0.0f);
        _U.putFloat(address + 60L, 1.0f);
        return self;
    }

    public Float3x4 storeRM4x4Heap(Float3x4Impl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.data[0]);
        _U.putFloat(bytes, address + 4L, self.data[1]);
        _U.putFloat(bytes, address + 8L, self.data[2]);
        _U.putFloat(bytes, address + 12L, self.data[3]);
        _U.putFloat(bytes, address + 16L, self.data[4]);
        _U.putFloat(bytes, address + 20L, self.data[5]);
        _U.putFloat(bytes, address + 24L, self.data[6]);
        _U.putFloat(bytes, address + 28L, self.data[7]);
        _U.putFloat(bytes, address + 32L, self.data[8]);
        _U.putFloat(bytes, address + 36L, self.data[9]);
        _U.putFloat(bytes, address + 40L, self.data[10]);
        _U.putFloat(bytes, address + 44L, self.data[11]);
        _U.putFloat(bytes, address + 48L, 0.0f);
        _U.putFloat(bytes, address + 52L, 0.0f);
        _U.putFloat(bytes, address + 56L, 0.0f);
        _U.putFloat(bytes, address + 60L, 1.0f);
        return self;
    }

    public Float3x4 storeRM4x4DoubleUnsafe(Float3x4Impl self, long address) {
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
        _U.putDouble(address + 96L, 0.0);
        _U.putDouble(address + 104L, 0.0);
        _U.putDouble(address + 112L, 0.0);
        _U.putDouble(address + 120L, 1.0);
        return self;
    }

    public Float3x4 storeRM4x4DoubleHeap(Float3x4Impl self, byte[] bytes, long address) {
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
        _U.putDouble(bytes, address + 96L, 0.0);
        _U.putDouble(bytes, address + 104L, 0.0);
        _U.putDouble(bytes, address + 112L, 0.0);
        _U.putDouble(bytes, address + 120L, 1.0);
        return self;
    }
}
