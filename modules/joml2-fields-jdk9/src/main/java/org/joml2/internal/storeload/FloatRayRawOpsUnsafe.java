// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatRayRawOpsUnsafe implements FloatRayRawOps {
    public FloatRay storeUnsafe(FloatRayImpl self, long address) {
        U.putFloat(address + 0L, self.oX);
        U.putFloat(address + 4L, self.oY);
        U.putFloat(address + 8L, self.oZ);
        U.putFloat(address + 12L, self.dX);
        U.putFloat(address + 16L, self.dY);
        U.putFloat(address + 20L, self.dZ);
        return self;
    }
    public FloatRay loadUnsafe(FloatRayImpl self, long address) {
        self.oX = U.getFloat(address + 0L);
        self.oY = U.getFloat(address + 4L);
        self.oZ = U.getFloat(address + 8L);
        self.dX = U.getFloat(address + 12L);
        self.dY = U.getFloat(address + 16L);
        self.dZ = U.getFloat(address + 20L);
        return self;
    }
    public FloatRay storeDoubleUnsafe(FloatRayImpl self, long address) {
        U.putDouble(address + 0L, self.oX);
        U.putDouble(address + 8L, self.oY);
        U.putDouble(address + 16L, self.oZ);
        U.putDouble(address + 24L, self.dX);
        U.putDouble(address + 32L, self.dY);
        U.putDouble(address + 40L, self.dZ);
        return self;
    }
    public FloatRay loadDoubleUnsafe(FloatRayImpl self, long address) {
        self.oX = (float) U.getDouble(address + 0L);
        self.oY = (float) U.getDouble(address + 8L);
        self.oZ = (float) U.getDouble(address + 16L);
        self.dX = (float) U.getDouble(address + 24L);
        self.dY = (float) U.getDouble(address + 32L);
        self.dZ = (float) U.getDouble(address + 40L);
        return self;
    }
}
