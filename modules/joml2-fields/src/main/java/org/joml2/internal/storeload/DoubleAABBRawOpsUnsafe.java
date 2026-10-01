// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleAABBRawOpsUnsafe extends DoubleAABBRawOps {
    public DoubleAABB storeUnsafe(DoubleAABBImpl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.minX);
        _U.putDouble(address + 8L, self.minY);
        _U.putDouble(address + 16L, self.minZ);
        _U.putDouble(address + 24L, self.maxX);
        _U.putDouble(address + 32L, self.maxY);
        _U.putDouble(address + 40L, self.maxZ);
        return self;
    }

    public DoubleAABB storeHeap(DoubleAABBImpl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.minX);
        _U.putDouble(bytes, address + 8L, self.minY);
        _U.putDouble(bytes, address + 16L, self.minZ);
        _U.putDouble(bytes, address + 24L, self.maxX);
        _U.putDouble(bytes, address + 32L, self.maxY);
        _U.putDouble(bytes, address + 40L, self.maxZ);
        return self;
    }

    public DoubleAABB loadUnsafe(DoubleAABBImpl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.minX = _U.getDouble(address);
        self.minY = _U.getDouble(address + 8L);
        self.minZ = _U.getDouble(address + 16L);
        self.maxX = _U.getDouble(address + 24L);
        self.maxY = _U.getDouble(address + 32L);
        self.maxZ = _U.getDouble(address + 40L);
        return self;
    }

    public DoubleAABB loadHeap(DoubleAABBImpl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.minX = _U.getDouble(bytes, address);
        self.minY = _U.getDouble(bytes, address + 8L);
        self.minZ = _U.getDouble(bytes, address + 16L);
        self.maxX = _U.getDouble(bytes, address + 24L);
        self.maxY = _U.getDouble(bytes, address + 32L);
        self.maxZ = _U.getDouble(bytes, address + 40L);
        return self;
    }

    public DoubleAABB storeFloatUnsafe(DoubleAABBImpl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.minX);
        _U.putFloat(address + 4L, (float) self.minY);
        _U.putFloat(address + 8L, (float) self.minZ);
        _U.putFloat(address + 12L, (float) self.maxX);
        _U.putFloat(address + 16L, (float) self.maxY);
        _U.putFloat(address + 20L, (float) self.maxZ);
        return self;
    }

    public DoubleAABB storeFloatHeap(DoubleAABBImpl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.minX);
        _U.putFloat(bytes, address + 4L, (float) self.minY);
        _U.putFloat(bytes, address + 8L, (float) self.minZ);
        _U.putFloat(bytes, address + 12L, (float) self.maxX);
        _U.putFloat(bytes, address + 16L, (float) self.maxY);
        _U.putFloat(bytes, address + 20L, (float) self.maxZ);
        return self;
    }

    public DoubleAABB loadFloatUnsafe(DoubleAABBImpl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.minX = _U.getFloat(address);
        self.minY = _U.getFloat(address + 4L);
        self.minZ = _U.getFloat(address + 8L);
        self.maxX = _U.getFloat(address + 12L);
        self.maxY = _U.getFloat(address + 16L);
        self.maxZ = _U.getFloat(address + 20L);
        return self;
    }

    public DoubleAABB loadFloatHeap(DoubleAABBImpl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.minX = _U.getFloat(bytes, address);
        self.minY = _U.getFloat(bytes, address + 4L);
        self.minZ = _U.getFloat(bytes, address + 8L);
        self.maxX = _U.getFloat(bytes, address + 12L);
        self.maxY = _U.getFloat(bytes, address + 16L);
        self.maxZ = _U.getFloat(bytes, address + 20L);
        return self;
    }
}
