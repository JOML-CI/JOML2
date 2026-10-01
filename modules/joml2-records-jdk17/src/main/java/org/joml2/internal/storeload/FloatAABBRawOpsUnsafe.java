// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatAABBRawOpsUnsafe extends FloatAABBRawOps {
    public FloatAABB storeUnsafe(FloatAABB self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.minX());
        _U.putFloat(address + 4L, self.minY());
        _U.putFloat(address + 8L, self.minZ());
        _U.putFloat(address + 12L, self.maxX());
        _U.putFloat(address + 16L, self.maxY());
        _U.putFloat(address + 20L, self.maxZ());
        return self;
    }

    public FloatAABB storeHeap(FloatAABB self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.minX());
        _U.putFloat(bytes, address + 4L, self.minY());
        _U.putFloat(bytes, address + 8L, self.minZ());
        _U.putFloat(bytes, address + 12L, self.maxX());
        _U.putFloat(bytes, address + 16L, self.maxY());
        _U.putFloat(bytes, address + 20L, self.maxZ());
        return self;
    }

    public FloatAABB loadUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(address);
        float _c1 = _U.getFloat(address + 4L);
        float _c2 = _U.getFloat(address + 8L);
        float _c3 = _U.getFloat(address + 12L);
        float _c4 = _U.getFloat(address + 16L);
        float _c5 = _U.getFloat(address + 20L);
        return new FloatAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public FloatAABB loadHeap(byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(bytes, address);
        float _c1 = _U.getFloat(bytes, address + 4L);
        float _c2 = _U.getFloat(bytes, address + 8L);
        float _c3 = _U.getFloat(bytes, address + 12L);
        float _c4 = _U.getFloat(bytes, address + 16L);
        float _c5 = _U.getFloat(bytes, address + 20L);
        return new FloatAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public FloatAABB storeDoubleUnsafe(FloatAABB self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.minX());
        _U.putDouble(address + 8L, self.minY());
        _U.putDouble(address + 16L, self.minZ());
        _U.putDouble(address + 24L, self.maxX());
        _U.putDouble(address + 32L, self.maxY());
        _U.putDouble(address + 40L, self.maxZ());
        return self;
    }

    public FloatAABB storeDoubleHeap(FloatAABB self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.minX());
        _U.putDouble(bytes, address + 8L, self.minY());
        _U.putDouble(bytes, address + 16L, self.minZ());
        _U.putDouble(bytes, address + 24L, self.maxX());
        _U.putDouble(bytes, address + 32L, self.maxY());
        _U.putDouble(bytes, address + 40L, self.maxZ());
        return self;
    }

    public FloatAABB loadDoubleUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(address);
        float _c1 = (float) _U.getDouble(address + 8L);
        float _c2 = (float) _U.getDouble(address + 16L);
        float _c3 = (float) _U.getDouble(address + 24L);
        float _c4 = (float) _U.getDouble(address + 32L);
        float _c5 = (float) _U.getDouble(address + 40L);
        return new FloatAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public FloatAABB loadDoubleHeap(byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(bytes, address);
        float _c1 = (float) _U.getDouble(bytes, address + 8L);
        float _c2 = (float) _U.getDouble(bytes, address + 16L);
        float _c3 = (float) _U.getDouble(bytes, address + 24L);
        float _c4 = (float) _U.getDouble(bytes, address + 32L);
        float _c5 = (float) _U.getDouble(bytes, address + 40L);
        return new FloatAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }
}
