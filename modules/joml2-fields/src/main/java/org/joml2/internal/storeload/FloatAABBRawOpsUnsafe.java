// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatAABBRawOpsUnsafe extends FloatAABBRawOps {
    public FloatAABB storeUnsafe(FloatAABBImpl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, self.minX);
        _U.putFloat(address + 4L, self.minY);
        _U.putFloat(address + 8L, self.minZ);
        _U.putFloat(address + 12L, self.maxX);
        _U.putFloat(address + 16L, self.maxY);
        _U.putFloat(address + 20L, self.maxZ);
        return self;
    }

    public FloatAABB storeHeap(FloatAABBImpl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, self.minX);
        _U.putFloat(bytes, address + 4L, self.minY);
        _U.putFloat(bytes, address + 8L, self.minZ);
        _U.putFloat(bytes, address + 12L, self.maxX);
        _U.putFloat(bytes, address + 16L, self.maxY);
        _U.putFloat(bytes, address + 20L, self.maxZ);
        return self;
    }

    public FloatAABB loadUnsafe(FloatAABBImpl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.minX = _U.getFloat(address);
        self.minY = _U.getFloat(address + 4L);
        self.minZ = _U.getFloat(address + 8L);
        self.maxX = _U.getFloat(address + 12L);
        self.maxY = _U.getFloat(address + 16L);
        self.maxZ = _U.getFloat(address + 20L);
        return self;
    }

    public FloatAABB loadHeap(FloatAABBImpl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.minX = _U.getFloat(bytes, address);
        self.minY = _U.getFloat(bytes, address + 4L);
        self.minZ = _U.getFloat(bytes, address + 8L);
        self.maxX = _U.getFloat(bytes, address + 12L);
        self.maxY = _U.getFloat(bytes, address + 16L);
        self.maxZ = _U.getFloat(bytes, address + 20L);
        return self;
    }

    public FloatAABB storeDoubleUnsafe(FloatAABBImpl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.minX);
        _U.putDouble(address + 8L, self.minY);
        _U.putDouble(address + 16L, self.minZ);
        _U.putDouble(address + 24L, self.maxX);
        _U.putDouble(address + 32L, self.maxY);
        _U.putDouble(address + 40L, self.maxZ);
        return self;
    }

    public FloatAABB storeDoubleHeap(FloatAABBImpl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.minX);
        _U.putDouble(bytes, address + 8L, self.minY);
        _U.putDouble(bytes, address + 16L, self.minZ);
        _U.putDouble(bytes, address + 24L, self.maxX);
        _U.putDouble(bytes, address + 32L, self.maxY);
        _U.putDouble(bytes, address + 40L, self.maxZ);
        return self;
    }

    public FloatAABB loadDoubleUnsafe(FloatAABBImpl self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.minX = (float) _U.getDouble(address);
        self.minY = (float) _U.getDouble(address + 8L);
        self.minZ = (float) _U.getDouble(address + 16L);
        self.maxX = (float) _U.getDouble(address + 24L);
        self.maxY = (float) _U.getDouble(address + 32L);
        self.maxZ = (float) _U.getDouble(address + 40L);
        return self;
    }

    public FloatAABB loadDoubleHeap(FloatAABBImpl self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        self.minX = (float) _U.getDouble(bytes, address);
        self.minY = (float) _U.getDouble(bytes, address + 8L);
        self.minZ = (float) _U.getDouble(bytes, address + 16L);
        self.maxX = (float) _U.getDouble(bytes, address + 24L);
        self.maxY = (float) _U.getDouble(bytes, address + 32L);
        self.maxZ = (float) _U.getDouble(bytes, address + 40L);
        return self;
    }
}
