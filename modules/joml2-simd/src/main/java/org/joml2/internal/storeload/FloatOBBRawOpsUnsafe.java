// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatOBBRawOpsUnsafe extends FloatOBBRawOps {
    public FloatOBB storeUnsafe(FloatOBBImpl self, long address) {
        float[] _d = self.data;
        if (org.joml2.internal.unsafe.UnsafeCopy.UNALIGNED_LONGS) {
            for (int _k = 0; _k < 56; _k += 8)
                U.putLong(address + _k, U.getLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k));
            U.putFloat(address + 56L, _d[14]);
        } else {
            for (int _k = 0; _k < 15; _k++)
                U.putFloat(address + 4L * _k, _d[_k]);
        }
        return self;
    }

    public FloatOBB storeHeap(FloatOBBImpl self, byte[] bytes, long address) {
        float[] _d = self.data;
        if (org.joml2.internal.unsafe.UnsafeCopy.UNALIGNED_LONGS) {
            for (int _k = 0; _k < 56; _k += 8)
                U.putLong(bytes, address + _k, U.getLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k));
            U.putFloat(bytes, address + 56L, _d[14]);
        } else {
            for (int _k = 0; _k < 15; _k++)
                U.putFloat(bytes, address + 4L * _k, _d[_k]);
        }
        return self;
    }

    public FloatOBB loadUnsafe(FloatOBBImpl self, long address) {
        float[] _d = self.data;
        if (org.joml2.internal.unsafe.UnsafeCopy.UNALIGNED_LONGS) {
            for (int _k = 0; _k < 56; _k += 8)
                U.putLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k, U.getLong(address + _k));
            _d[14] = U.getFloat(address + 56L);
        } else {
            for (int _k = 0; _k < 15; _k++)
                _d[_k] = U.getFloat(address + 4L * _k);
        }
        return self;
    }

    public FloatOBB loadHeap(FloatOBBImpl self, byte[] bytes, long address) {
        float[] _d = self.data;
        if (org.joml2.internal.unsafe.UnsafeCopy.UNALIGNED_LONGS) {
            for (int _k = 0; _k < 56; _k += 8)
                U.putLong(_d, org.joml2.internal.unsafe.UnsafeCopy.FLOAT_ARRAY_BASE + _k, U.getLong(bytes, address + _k));
            _d[14] = U.getFloat(bytes, address + 56L);
        } else {
            for (int _k = 0; _k < 15; _k++)
                _d[_k] = U.getFloat(bytes, address + 4L * _k);
        }
        return self;
    }

    public FloatOBB storeDoubleUnsafe(FloatOBBImpl self, long address) {
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
        return self;
    }

    public FloatOBB storeDoubleHeap(FloatOBBImpl self, byte[] bytes, long address) {
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
        return self;
    }

    public FloatOBB loadDoubleUnsafe(FloatOBBImpl self, long address) {
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
        return self;
    }

    public FloatOBB loadDoubleHeap(FloatOBBImpl self, byte[] bytes, long address) {
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
        self.data[12] = (float) _U.getDouble(bytes, address + 96L);
        self.data[13] = (float) _U.getDouble(bytes, address + 104L);
        self.data[14] = (float) _U.getDouble(bytes, address + 112L);
        return self;
    }
}
