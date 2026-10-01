// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleOBBRawOpsUnsafe extends DoubleOBBRawOps {
    public DoubleOBB storeUnsafe(DoubleOBBImpl self, long address) {
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

    public DoubleOBB storeHeap(DoubleOBBImpl self, byte[] bytes, long address) {
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

    public DoubleOBB loadUnsafe(DoubleOBBImpl self, long address) {
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
        return self;
    }

    public DoubleOBB loadHeap(DoubleOBBImpl self, byte[] bytes, long address) {
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
        return self;
    }

    public DoubleOBB storeFloatUnsafe(DoubleOBBImpl self, long address) {
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
        return self;
    }

    public DoubleOBB storeFloatHeap(DoubleOBBImpl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.data[0]);
        _U.putFloat(bytes, address + 4L, (float) self.data[1]);
        _U.putFloat(bytes, address + 8L, (float) self.data[2]);
        _U.putFloat(bytes, address + 12L, (float) self.data[3]);
        _U.putFloat(bytes, address + 16L, (float) self.data[4]);
        _U.putFloat(bytes, address + 20L, (float) self.data[5]);
        _U.putFloat(bytes, address + 24L, (float) self.data[6]);
        _U.putFloat(bytes, address + 28L, (float) self.data[7]);
        _U.putFloat(bytes, address + 32L, (float) self.data[8]);
        _U.putFloat(bytes, address + 36L, (float) self.data[9]);
        _U.putFloat(bytes, address + 40L, (float) self.data[10]);
        _U.putFloat(bytes, address + 44L, (float) self.data[11]);
        _U.putFloat(bytes, address + 48L, (float) self.data[12]);
        _U.putFloat(bytes, address + 52L, (float) self.data[13]);
        _U.putFloat(bytes, address + 56L, (float) self.data[14]);
        return self;
    }

    public DoubleOBB loadFloatUnsafe(DoubleOBBImpl self, long address) {
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
        return self;
    }

    public DoubleOBB loadFloatHeap(DoubleOBBImpl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.data[0] = _U.getFloat(bytes, address);
        self.data[1] = _U.getFloat(bytes, address + 4L);
        self.data[2] = _U.getFloat(bytes, address + 8L);
        self.data[3] = _U.getFloat(bytes, address + 12L);
        self.data[4] = _U.getFloat(bytes, address + 16L);
        self.data[5] = _U.getFloat(bytes, address + 20L);
        self.data[6] = _U.getFloat(bytes, address + 24L);
        self.data[7] = _U.getFloat(bytes, address + 28L);
        self.data[8] = _U.getFloat(bytes, address + 32L);
        self.data[9] = _U.getFloat(bytes, address + 36L);
        self.data[10] = _U.getFloat(bytes, address + 40L);
        self.data[11] = _U.getFloat(bytes, address + 44L);
        self.data[12] = _U.getFloat(bytes, address + 48L);
        self.data[13] = _U.getFloat(bytes, address + 52L);
        self.data[14] = _U.getFloat(bytes, address + 56L);
        return self;
    }
}
