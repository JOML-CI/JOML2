// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleRayRawOpsUnsafe implements DoubleRayRawOps {
    public DoubleRay storeUnsafe(DoubleRay self, long address) {
        U.putDouble(address + 0L, self.oX());
        U.putDouble(address + 8L, self.oY());
        U.putDouble(address + 16L, self.oZ());
        U.putDouble(address + 24L, self.dX());
        U.putDouble(address + 32L, self.dY());
        U.putDouble(address + 40L, self.dZ());
        return self;
    }
    public DoubleRay loadUnsafe(long address) {
        double _c0 = U.getDouble(address + 0L);
        double _c1 = U.getDouble(address + 8L);
        double _c2 = U.getDouble(address + 16L);
        double _c3 = U.getDouble(address + 24L);
        double _c4 = U.getDouble(address + 32L);
        double _c5 = U.getDouble(address + 40L);
        return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }
    public DoubleRay storeFloatUnsafe(DoubleRay self, long address) {
        U.putFloat(address + 0L, (float) self.oX());
        U.putFloat(address + 4L, (float) self.oY());
        U.putFloat(address + 8L, (float) self.oZ());
        U.putFloat(address + 12L, (float) self.dX());
        U.putFloat(address + 16L, (float) self.dY());
        U.putFloat(address + 20L, (float) self.dZ());
        return self;
    }
    public DoubleRay loadFloatUnsafe(long address) {
        double _c0 = U.getFloat(address + 0L);
        double _c1 = U.getFloat(address + 4L);
        double _c2 = U.getFloat(address + 8L);
        double _c3 = U.getFloat(address + 12L);
        double _c4 = U.getFloat(address + 16L);
        double _c5 = U.getFloat(address + 20L);
        return new DoubleRay(_c0, _c1, _c2, _c3, _c4, _c5);
    }
}
