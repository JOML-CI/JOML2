// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleTriangleRawOpsUnsafe extends DoubleTriangleRawOps {
    public DoubleTriangle storeUnsafe(DoubleTriangleImpl self, long address) {
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
        return self;
    }

    public DoubleTriangle storeHeap(DoubleTriangleImpl self, byte[] bytes, long address) {
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
        return self;
    }

    public DoubleTriangle loadUnsafe(DoubleTriangleImpl self, long address) {
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
        return self;
    }

    public DoubleTriangle loadHeap(DoubleTriangleImpl self, byte[] bytes, long address) {
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
        return self;
    }

    public DoubleTriangle storeFloatUnsafe(DoubleTriangleImpl self, long address) {
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
        return self;
    }

    public DoubleTriangle storeFloatHeap(DoubleTriangleImpl self, byte[] bytes, long address) {
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
        return self;
    }

    public DoubleTriangle loadFloatUnsafe(DoubleTriangleImpl self, long address) {
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
        return self;
    }

    public DoubleTriangle loadFloatHeap(DoubleTriangleImpl self, byte[] bytes, long address) {
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
        return self;
    }
}
