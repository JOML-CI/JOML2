// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatRayRawOpsUnsafe extends FloatRayRawOps {
    public FloatRay storeUnsafe(FloatRayImpl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.oX);
        _U.putFloat(address + 4L, self.oY);
        _U.putFloat(address + 8L, self.oZ);
        _U.putFloat(address + 12L, self.dX);
        _U.putFloat(address + 16L, self.dY);
        _U.putFloat(address + 20L, self.dZ);
        return self;
    }

    public FloatRay storeHeap(FloatRayImpl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.oX);
        _U.putFloat(bytes, address + 4L, self.oY);
        _U.putFloat(bytes, address + 8L, self.oZ);
        _U.putFloat(bytes, address + 12L, self.dX);
        _U.putFloat(bytes, address + 16L, self.dY);
        _U.putFloat(bytes, address + 20L, self.dZ);
        return self;
    }

    public FloatRay loadUnsafe(FloatRayImpl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.oX = _U.getFloat(address);
        self.oY = _U.getFloat(address + 4L);
        self.oZ = _U.getFloat(address + 8L);
        self.dX = _U.getFloat(address + 12L);
        self.dY = _U.getFloat(address + 16L);
        self.dZ = _U.getFloat(address + 20L);
        return self;
    }

    public FloatRay loadHeap(FloatRayImpl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.oX = _U.getFloat(bytes, address);
        self.oY = _U.getFloat(bytes, address + 4L);
        self.oZ = _U.getFloat(bytes, address + 8L);
        self.dX = _U.getFloat(bytes, address + 12L);
        self.dY = _U.getFloat(bytes, address + 16L);
        self.dZ = _U.getFloat(bytes, address + 20L);
        return self;
    }

    public FloatRay storeDoubleUnsafe(FloatRayImpl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.oX);
        _U.putDouble(address + 8L, self.oY);
        _U.putDouble(address + 16L, self.oZ);
        _U.putDouble(address + 24L, self.dX);
        _U.putDouble(address + 32L, self.dY);
        _U.putDouble(address + 40L, self.dZ);
        return self;
    }

    public FloatRay storeDoubleHeap(FloatRayImpl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.oX);
        _U.putDouble(bytes, address + 8L, self.oY);
        _U.putDouble(bytes, address + 16L, self.oZ);
        _U.putDouble(bytes, address + 24L, self.dX);
        _U.putDouble(bytes, address + 32L, self.dY);
        _U.putDouble(bytes, address + 40L, self.dZ);
        return self;
    }

    public FloatRay loadDoubleUnsafe(FloatRayImpl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.oX = (float) _U.getDouble(address);
        self.oY = (float) _U.getDouble(address + 8L);
        self.oZ = (float) _U.getDouble(address + 16L);
        self.dX = (float) _U.getDouble(address + 24L);
        self.dY = (float) _U.getDouble(address + 32L);
        self.dZ = (float) _U.getDouble(address + 40L);
        return self;
    }

    public FloatRay loadDoubleHeap(FloatRayImpl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.oX = (float) _U.getDouble(bytes, address);
        self.oY = (float) _U.getDouble(bytes, address + 8L);
        self.oZ = (float) _U.getDouble(bytes, address + 16L);
        self.dX = (float) _U.getDouble(bytes, address + 24L);
        self.dY = (float) _U.getDouble(bytes, address + 32L);
        self.dZ = (float) _U.getDouble(bytes, address + 40L);
        return self;
    }
}
