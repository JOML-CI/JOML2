// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleAABBRawOpsUnsafe extends DoubleAABBRawOps {
    public DoubleAABB storeUnsafe(DoubleAABB self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.minX());
        _U.putDouble(address + 8L, self.minY());
        _U.putDouble(address + 16L, self.minZ());
        _U.putDouble(address + 24L, self.maxX());
        _U.putDouble(address + 32L, self.maxY());
        _U.putDouble(address + 40L, self.maxZ());
        return self;
    }

    public DoubleAABB storeHeap(DoubleAABB self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.minX());
        _U.putDouble(bytes, address + 8L, self.minY());
        _U.putDouble(bytes, address + 16L, self.minZ());
        _U.putDouble(bytes, address + 24L, self.maxX());
        _U.putDouble(bytes, address + 32L, self.maxY());
        _U.putDouble(bytes, address + 40L, self.maxZ());
        return self;
    }

    public DoubleAABB loadUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(address);
        double _c1 = _U.getDouble(address + 8L);
        double _c2 = _U.getDouble(address + 16L);
        double _c3 = _U.getDouble(address + 24L);
        double _c4 = _U.getDouble(address + 32L);
        double _c5 = _U.getDouble(address + 40L);
        return new DoubleAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public DoubleAABB loadHeap(byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(bytes, address);
        double _c1 = _U.getDouble(bytes, address + 8L);
        double _c2 = _U.getDouble(bytes, address + 16L);
        double _c3 = _U.getDouble(bytes, address + 24L);
        double _c4 = _U.getDouble(bytes, address + 32L);
        double _c5 = _U.getDouble(bytes, address + 40L);
        return new DoubleAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public DoubleAABB storeFloatUnsafe(DoubleAABB self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.minX());
        _U.putFloat(address + 4L, (float) self.minY());
        _U.putFloat(address + 8L, (float) self.minZ());
        _U.putFloat(address + 12L, (float) self.maxX());
        _U.putFloat(address + 16L, (float) self.maxY());
        _U.putFloat(address + 20L, (float) self.maxZ());
        return self;
    }

    public DoubleAABB storeFloatHeap(DoubleAABB self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.minX());
        _U.putFloat(bytes, address + 4L, (float) self.minY());
        _U.putFloat(bytes, address + 8L, (float) self.minZ());
        _U.putFloat(bytes, address + 12L, (float) self.maxX());
        _U.putFloat(bytes, address + 16L, (float) self.maxY());
        _U.putFloat(bytes, address + 20L, (float) self.maxZ());
        return self;
    }

    public DoubleAABB loadFloatUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(address);
        double _c1 = _U.getFloat(address + 4L);
        double _c2 = _U.getFloat(address + 8L);
        double _c3 = _U.getFloat(address + 12L);
        double _c4 = _U.getFloat(address + 16L);
        double _c5 = _U.getFloat(address + 20L);
        return new DoubleAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }

    public DoubleAABB loadFloatHeap(byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(bytes, address);
        double _c1 = _U.getFloat(bytes, address + 4L);
        double _c2 = _U.getFloat(bytes, address + 8L);
        double _c3 = _U.getFloat(bytes, address + 12L);
        double _c4 = _U.getFloat(bytes, address + 16L);
        double _c5 = _U.getFloat(bytes, address + 20L);
        return new DoubleAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }
}
