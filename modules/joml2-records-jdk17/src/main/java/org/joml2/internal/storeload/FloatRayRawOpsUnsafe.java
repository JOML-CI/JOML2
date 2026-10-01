// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatRayRawOpsUnsafe extends FloatRayRawOps {
    public FloatRay storeUnsafe(FloatRay self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.oX());
        _U.putFloat(address + 4L, self.oY());
        _U.putFloat(address + 8L, self.oZ());
        _U.putFloat(address + 12L, self.dX());
        _U.putFloat(address + 16L, self.dY());
        _U.putFloat(address + 20L, self.dZ());
        return self;
    }

    public FloatRay storeHeap(FloatRay self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.oX());
        _U.putFloat(bytes, address + 4L, self.oY());
        _U.putFloat(bytes, address + 8L, self.oZ());
        _U.putFloat(bytes, address + 12L, self.dX());
        _U.putFloat(bytes, address + 16L, self.dY());
        _U.putFloat(bytes, address + 20L, self.dZ());
        return self;
    }

    public FloatRay loadUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(address);
        float _c1 = _U.getFloat(address + 4L);
        float _c2 = _U.getFloat(address + 8L);
        float _c3 = _U.getFloat(address + 12L);
        float _c4 = _U.getFloat(address + 16L);
        float _c5 = _U.getFloat(address + 20L);
        return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public FloatRay loadHeap(byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(bytes, address);
        float _c1 = _U.getFloat(bytes, address + 4L);
        float _c2 = _U.getFloat(bytes, address + 8L);
        float _c3 = _U.getFloat(bytes, address + 12L);
        float _c4 = _U.getFloat(bytes, address + 16L);
        float _c5 = _U.getFloat(bytes, address + 20L);
        return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public FloatRay storeDoubleUnsafe(FloatRay self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.oX());
        _U.putDouble(address + 8L, self.oY());
        _U.putDouble(address + 16L, self.oZ());
        _U.putDouble(address + 24L, self.dX());
        _U.putDouble(address + 32L, self.dY());
        _U.putDouble(address + 40L, self.dZ());
        return self;
    }

    public FloatRay storeDoubleHeap(FloatRay self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.oX());
        _U.putDouble(bytes, address + 8L, self.oY());
        _U.putDouble(bytes, address + 16L, self.oZ());
        _U.putDouble(bytes, address + 24L, self.dX());
        _U.putDouble(bytes, address + 32L, self.dY());
        _U.putDouble(bytes, address + 40L, self.dZ());
        return self;
    }

    public FloatRay loadDoubleUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(address);
        float _c1 = (float) _U.getDouble(address + 8L);
        float _c2 = (float) _U.getDouble(address + 16L);
        float _c3 = (float) _U.getDouble(address + 24L);
        float _c4 = (float) _U.getDouble(address + 32L);
        float _c5 = (float) _U.getDouble(address + 40L);
        return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public FloatRay loadDoubleHeap(byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(bytes, address);
        float _c1 = (float) _U.getDouble(bytes, address + 8L);
        float _c2 = (float) _U.getDouble(bytes, address + 16L);
        float _c3 = (float) _U.getDouble(bytes, address + 24L);
        float _c4 = (float) _U.getDouble(bytes, address + 32L);
        float _c5 = (float) _U.getDouble(bytes, address + 40L);
        return new FloatRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }
}
