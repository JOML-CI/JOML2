// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleDualQuatRawOpsUnsafe extends DoubleDualQuatRawOps {
    public DoubleDualQuat storeUnsafe(DoubleDualQuatImpl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.rX);
        _U.putDouble(address + 8L, self.rY);
        _U.putDouble(address + 16L, self.rZ);
        _U.putDouble(address + 24L, self.rW);
        _U.putDouble(address + 32L, self.dX);
        _U.putDouble(address + 40L, self.dY);
        _U.putDouble(address + 48L, self.dZ);
        _U.putDouble(address + 56L, self.dW);
        return self;
    }

    public DoubleDualQuat storeHeap(DoubleDualQuatImpl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.rX);
        _U.putDouble(bytes, address + 8L, self.rY);
        _U.putDouble(bytes, address + 16L, self.rZ);
        _U.putDouble(bytes, address + 24L, self.rW);
        _U.putDouble(bytes, address + 32L, self.dX);
        _U.putDouble(bytes, address + 40L, self.dY);
        _U.putDouble(bytes, address + 48L, self.dZ);
        _U.putDouble(bytes, address + 56L, self.dW);
        return self;
    }

    public DoubleDualQuat loadUnsafe(DoubleDualQuatImpl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.rX = _U.getDouble(address);
        self.rY = _U.getDouble(address + 8L);
        self.rZ = _U.getDouble(address + 16L);
        self.rW = _U.getDouble(address + 24L);
        self.dX = _U.getDouble(address + 32L);
        self.dY = _U.getDouble(address + 40L);
        self.dZ = _U.getDouble(address + 48L);
        self.dW = _U.getDouble(address + 56L);
        return self;
    }

    public DoubleDualQuat loadHeap(DoubleDualQuatImpl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.rX = _U.getDouble(bytes, address);
        self.rY = _U.getDouble(bytes, address + 8L);
        self.rZ = _U.getDouble(bytes, address + 16L);
        self.rW = _U.getDouble(bytes, address + 24L);
        self.dX = _U.getDouble(bytes, address + 32L);
        self.dY = _U.getDouble(bytes, address + 40L);
        self.dZ = _U.getDouble(bytes, address + 48L);
        self.dW = _U.getDouble(bytes, address + 56L);
        return self;
    }

    public DoubleDualQuat storeFloatUnsafe(DoubleDualQuatImpl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.rX);
        _U.putFloat(address + 4L, (float) self.rY);
        _U.putFloat(address + 8L, (float) self.rZ);
        _U.putFloat(address + 12L, (float) self.rW);
        _U.putFloat(address + 16L, (float) self.dX);
        _U.putFloat(address + 20L, (float) self.dY);
        _U.putFloat(address + 24L, (float) self.dZ);
        _U.putFloat(address + 28L, (float) self.dW);
        return self;
    }

    public DoubleDualQuat storeFloatHeap(DoubleDualQuatImpl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.rX);
        _U.putFloat(bytes, address + 4L, (float) self.rY);
        _U.putFloat(bytes, address + 8L, (float) self.rZ);
        _U.putFloat(bytes, address + 12L, (float) self.rW);
        _U.putFloat(bytes, address + 16L, (float) self.dX);
        _U.putFloat(bytes, address + 20L, (float) self.dY);
        _U.putFloat(bytes, address + 24L, (float) self.dZ);
        _U.putFloat(bytes, address + 28L, (float) self.dW);
        return self;
    }

    public DoubleDualQuat loadFloatUnsafe(DoubleDualQuatImpl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.rX = _U.getFloat(address);
        self.rY = _U.getFloat(address + 4L);
        self.rZ = _U.getFloat(address + 8L);
        self.rW = _U.getFloat(address + 12L);
        self.dX = _U.getFloat(address + 16L);
        self.dY = _U.getFloat(address + 20L);
        self.dZ = _U.getFloat(address + 24L);
        self.dW = _U.getFloat(address + 28L);
        return self;
    }

    public DoubleDualQuat loadFloatHeap(DoubleDualQuatImpl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.rX = _U.getFloat(bytes, address);
        self.rY = _U.getFloat(bytes, address + 4L);
        self.rZ = _U.getFloat(bytes, address + 8L);
        self.rW = _U.getFloat(bytes, address + 12L);
        self.dX = _U.getFloat(bytes, address + 16L);
        self.dY = _U.getFloat(bytes, address + 20L);
        self.dZ = _U.getFloat(bytes, address + 24L);
        self.dW = _U.getFloat(bytes, address + 28L);
        return self;
    }
}
