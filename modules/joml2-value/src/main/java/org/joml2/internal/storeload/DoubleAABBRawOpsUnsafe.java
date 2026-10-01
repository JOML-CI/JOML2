// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleAABBRawOpsUnsafe implements DoubleAABBRawOps {
    public DoubleAABB storeUnsafe(DoubleAABB self, long address) {
        U.putDouble(address + 0L, self.minX());
        U.putDouble(address + 8L, self.minY());
        U.putDouble(address + 16L, self.minZ());
        U.putDouble(address + 24L, self.maxX());
        U.putDouble(address + 32L, self.maxY());
        U.putDouble(address + 40L, self.maxZ());
        return self;
    }
    public DoubleAABB loadUnsafe(long address) {
        double _c0 = U.getDouble(address + 0L);
        double _c1 = U.getDouble(address + 8L);
        double _c2 = U.getDouble(address + 16L);
        double _c3 = U.getDouble(address + 24L);
        double _c4 = U.getDouble(address + 32L);
        double _c5 = U.getDouble(address + 40L);
        return new DoubleAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public DoubleAABB storeFloatUnsafe(DoubleAABB self, long address) {
        U.putFloat(address + 0L, (float) self.minX());
        U.putFloat(address + 4L, (float) self.minY());
        U.putFloat(address + 8L, (float) self.minZ());
        U.putFloat(address + 12L, (float) self.maxX());
        U.putFloat(address + 16L, (float) self.maxY());
        U.putFloat(address + 20L, (float) self.maxZ());
        return self;
    }
    public DoubleAABB loadFloatUnsafe(long address) {
        double _c0 = U.getFloat(address + 0L);
        double _c1 = U.getFloat(address + 4L);
        double _c2 = U.getFloat(address + 8L);
        double _c3 = U.getFloat(address + 12L);
        double _c4 = U.getFloat(address + 16L);
        double _c5 = U.getFloat(address + 20L);
        return new DoubleAABB(_c0, _c1, _c2, _c3, _c4, _c5);
    }
}
