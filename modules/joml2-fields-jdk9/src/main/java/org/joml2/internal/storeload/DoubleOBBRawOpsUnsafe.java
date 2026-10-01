// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleOBBRawOpsUnsafe implements DoubleOBBRawOps {
    public DoubleOBB storeUnsafe(DoubleOBBImpl self, long address) {
        U.putDouble(address + 0L, self.cX);
        U.putDouble(address + 8L, self.cY);
        U.putDouble(address + 16L, self.cZ);
        U.putDouble(address + 24L, self.uXx);
        U.putDouble(address + 32L, self.uXy);
        U.putDouble(address + 40L, self.uXz);
        U.putDouble(address + 48L, self.uYx);
        U.putDouble(address + 56L, self.uYy);
        U.putDouble(address + 64L, self.uYz);
        U.putDouble(address + 72L, self.uZx);
        U.putDouble(address + 80L, self.uZy);
        U.putDouble(address + 88L, self.uZz);
        U.putDouble(address + 96L, self.hsX);
        U.putDouble(address + 104L, self.hsY);
        U.putDouble(address + 112L, self.hsZ);
        return self;
    }
    public DoubleOBB loadUnsafe(DoubleOBBImpl self, long address) {
        self.cX = U.getDouble(address + 0L);
        self.cY = U.getDouble(address + 8L);
        self.cZ = U.getDouble(address + 16L);
        self.uXx = U.getDouble(address + 24L);
        self.uXy = U.getDouble(address + 32L);
        self.uXz = U.getDouble(address + 40L);
        self.uYx = U.getDouble(address + 48L);
        self.uYy = U.getDouble(address + 56L);
        self.uYz = U.getDouble(address + 64L);
        self.uZx = U.getDouble(address + 72L);
        self.uZy = U.getDouble(address + 80L);
        self.uZz = U.getDouble(address + 88L);
        self.hsX = U.getDouble(address + 96L);
        self.hsY = U.getDouble(address + 104L);
        self.hsZ = U.getDouble(address + 112L);
        return self;
    }
    public DoubleOBB storeFloatUnsafe(DoubleOBBImpl self, long address) {
        U.putFloat(address + 0L, (float) self.cX);
        U.putFloat(address + 4L, (float) self.cY);
        U.putFloat(address + 8L, (float) self.cZ);
        U.putFloat(address + 12L, (float) self.uXx);
        U.putFloat(address + 16L, (float) self.uXy);
        U.putFloat(address + 20L, (float) self.uXz);
        U.putFloat(address + 24L, (float) self.uYx);
        U.putFloat(address + 28L, (float) self.uYy);
        U.putFloat(address + 32L, (float) self.uYz);
        U.putFloat(address + 36L, (float) self.uZx);
        U.putFloat(address + 40L, (float) self.uZy);
        U.putFloat(address + 44L, (float) self.uZz);
        U.putFloat(address + 48L, (float) self.hsX);
        U.putFloat(address + 52L, (float) self.hsY);
        U.putFloat(address + 56L, (float) self.hsZ);
        return self;
    }
    public DoubleOBB loadFloatUnsafe(DoubleOBBImpl self, long address) {
        self.cX = U.getFloat(address + 0L);
        self.cY = U.getFloat(address + 4L);
        self.cZ = U.getFloat(address + 8L);
        self.uXx = U.getFloat(address + 12L);
        self.uXy = U.getFloat(address + 16L);
        self.uXz = U.getFloat(address + 20L);
        self.uYx = U.getFloat(address + 24L);
        self.uYy = U.getFloat(address + 28L);
        self.uYz = U.getFloat(address + 32L);
        self.uZx = U.getFloat(address + 36L);
        self.uZy = U.getFloat(address + 40L);
        self.uZz = U.getFloat(address + 44L);
        self.hsX = U.getFloat(address + 48L);
        self.hsY = U.getFloat(address + 52L);
        self.hsZ = U.getFloat(address + 56L);
        return self;
    }
}
