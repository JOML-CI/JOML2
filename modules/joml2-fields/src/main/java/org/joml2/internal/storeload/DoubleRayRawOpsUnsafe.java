// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleRayRawOpsUnsafe implements DoubleRayRawOps {
    public DoubleRay storeUnsafe(DoubleRayImpl self, long address) {
        U.putDouble(address + 0L, self.oX);
        U.putDouble(address + 8L, self.oY);
        U.putDouble(address + 16L, self.oZ);
        U.putDouble(address + 24L, self.dX);
        U.putDouble(address + 32L, self.dY);
        U.putDouble(address + 40L, self.dZ);
        return self;
    }
    public DoubleRay loadUnsafe(DoubleRayImpl self, long address) {
        self.oX = U.getDouble(address + 0L);
        self.oY = U.getDouble(address + 8L);
        self.oZ = U.getDouble(address + 16L);
        self.dX = U.getDouble(address + 24L);
        self.dY = U.getDouble(address + 32L);
        self.dZ = U.getDouble(address + 40L);
        return self;
    }
    public DoubleRay storeFloatUnsafe(DoubleRayImpl self, long address) {
        U.putFloat(address + 0L, (float) self.oX);
        U.putFloat(address + 4L, (float) self.oY);
        U.putFloat(address + 8L, (float) self.oZ);
        U.putFloat(address + 12L, (float) self.dX);
        U.putFloat(address + 16L, (float) self.dY);
        U.putFloat(address + 20L, (float) self.dZ);
        return self;
    }
    public DoubleRay loadFloatUnsafe(DoubleRayImpl self, long address) {
        self.oX = U.getFloat(address + 0L);
        self.oY = U.getFloat(address + 4L);
        self.oZ = U.getFloat(address + 8L);
        self.dX = U.getFloat(address + 12L);
        self.dY = U.getFloat(address + 16L);
        self.dZ = U.getFloat(address + 20L);
        return self;
    }
}
