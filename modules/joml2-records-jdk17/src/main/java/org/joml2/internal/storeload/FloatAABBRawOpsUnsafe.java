// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatAABBRawOpsUnsafe implements FloatAABBRawOps {
    public FloatAABB storeUnsafe(FloatAABB self, long address) {
        U.putFloat(address + 0L, self.minX());
        U.putFloat(address + 4L, self.minY());
        U.putFloat(address + 8L, self.minZ());
        U.putFloat(address + 12L, self.maxX());
        U.putFloat(address + 16L, self.maxY());
        U.putFloat(address + 20L, self.maxZ());
        return self;
    }
    public FloatAABB loadUnsafe(long address) {
        float _c0 = U.getFloat(address + 0L);
        float _c1 = U.getFloat(address + 4L);
        float _c2 = U.getFloat(address + 8L);
        float _c3 = U.getFloat(address + 12L);
        float _c4 = U.getFloat(address + 16L);
        float _c5 = U.getFloat(address + 20L);
        return new FloatAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public FloatAABB storeDoubleUnsafe(FloatAABB self, long address) {
        U.putDouble(address + 0L, self.minX());
        U.putDouble(address + 8L, self.minY());
        U.putDouble(address + 16L, self.minZ());
        U.putDouble(address + 24L, self.maxX());
        U.putDouble(address + 32L, self.maxY());
        U.putDouble(address + 40L, self.maxZ());
        return self;
    }
    public FloatAABB loadDoubleUnsafe(long address) {
        float _c0 = (float) U.getDouble(address + 0L);
        float _c1 = (float) U.getDouble(address + 8L);
        float _c2 = (float) U.getDouble(address + 16L);
        float _c3 = (float) U.getDouble(address + 24L);
        float _c4 = (float) U.getDouble(address + 32L);
        float _c5 = (float) U.getDouble(address + 40L);
        return new FloatAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }
}
