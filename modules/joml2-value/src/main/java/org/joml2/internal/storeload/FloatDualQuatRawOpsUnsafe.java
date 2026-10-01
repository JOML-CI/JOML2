// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatDualQuatRawOpsUnsafe extends FloatDualQuatRawOps {
    public FloatDualQuat storeUnsafe(FloatDualQuat self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.rX());
        _U.putFloat(address + 4L, self.rY());
        _U.putFloat(address + 8L, self.rZ());
        _U.putFloat(address + 12L, self.rW());
        _U.putFloat(address + 16L, self.dX());
        _U.putFloat(address + 20L, self.dY());
        _U.putFloat(address + 24L, self.dZ());
        _U.putFloat(address + 28L, self.dW());
        return self;
    }

    public FloatDualQuat storeHeap(FloatDualQuat self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.rX());
        _U.putFloat(bytes, address + 4L, self.rY());
        _U.putFloat(bytes, address + 8L, self.rZ());
        _U.putFloat(bytes, address + 12L, self.rW());
        _U.putFloat(bytes, address + 16L, self.dX());
        _U.putFloat(bytes, address + 20L, self.dY());
        _U.putFloat(bytes, address + 24L, self.dZ());
        _U.putFloat(bytes, address + 28L, self.dW());
        return self;
    }

    public FloatDualQuat loadUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(address);
        float _c1 = _U.getFloat(address + 4L);
        float _c2 = _U.getFloat(address + 8L);
        float _c3 = _U.getFloat(address + 12L);
        float _c4 = _U.getFloat(address + 16L);
        float _c5 = _U.getFloat(address + 20L);
        float _c6 = _U.getFloat(address + 24L);
        float _c7 = _U.getFloat(address + 28L);
        return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public FloatDualQuat loadHeap(byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(bytes, address);
        float _c1 = _U.getFloat(bytes, address + 4L);
        float _c2 = _U.getFloat(bytes, address + 8L);
        float _c3 = _U.getFloat(bytes, address + 12L);
        float _c4 = _U.getFloat(bytes, address + 16L);
        float _c5 = _U.getFloat(bytes, address + 20L);
        float _c6 = _U.getFloat(bytes, address + 24L);
        float _c7 = _U.getFloat(bytes, address + 28L);
        return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public FloatDualQuat storeDoubleUnsafe(FloatDualQuat self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.rX());
        _U.putDouble(address + 8L, self.rY());
        _U.putDouble(address + 16L, self.rZ());
        _U.putDouble(address + 24L, self.rW());
        _U.putDouble(address + 32L, self.dX());
        _U.putDouble(address + 40L, self.dY());
        _U.putDouble(address + 48L, self.dZ());
        _U.putDouble(address + 56L, self.dW());
        return self;
    }

    public FloatDualQuat storeDoubleHeap(FloatDualQuat self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.rX());
        _U.putDouble(bytes, address + 8L, self.rY());
        _U.putDouble(bytes, address + 16L, self.rZ());
        _U.putDouble(bytes, address + 24L, self.rW());
        _U.putDouble(bytes, address + 32L, self.dX());
        _U.putDouble(bytes, address + 40L, self.dY());
        _U.putDouble(bytes, address + 48L, self.dZ());
        _U.putDouble(bytes, address + 56L, self.dW());
        return self;
    }

    public FloatDualQuat loadDoubleUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(address);
        float _c1 = (float) _U.getDouble(address + 8L);
        float _c2 = (float) _U.getDouble(address + 16L);
        float _c3 = (float) _U.getDouble(address + 24L);
        float _c4 = (float) _U.getDouble(address + 32L);
        float _c5 = (float) _U.getDouble(address + 40L);
        float _c6 = (float) _U.getDouble(address + 48L);
        float _c7 = (float) _U.getDouble(address + 56L);
        return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }

    public FloatDualQuat loadDoubleHeap(byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(bytes, address);
        float _c1 = (float) _U.getDouble(bytes, address + 8L);
        float _c2 = (float) _U.getDouble(bytes, address + 16L);
        float _c3 = (float) _U.getDouble(bytes, address + 24L);
        float _c4 = (float) _U.getDouble(bytes, address + 32L);
        float _c5 = (float) _U.getDouble(bytes, address + 40L);
        float _c6 = (float) _U.getDouble(bytes, address + 48L);
        float _c7 = (float) _U.getDouble(bytes, address + 56L);
        return new FloatDualQuat(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7);
    }
}
