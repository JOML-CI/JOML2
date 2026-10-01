// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleOBBRawOpsUnsafe implements DoubleOBBRawOps {
    public DoubleOBB storeUnsafe(DoubleOBB self, long address) {
        U.putDouble(address + 0L, self.cX());
        U.putDouble(address + 8L, self.cY());
        U.putDouble(address + 16L, self.cZ());
        U.putDouble(address + 24L, self.uXx());
        U.putDouble(address + 32L, self.uXy());
        U.putDouble(address + 40L, self.uXz());
        U.putDouble(address + 48L, self.uYx());
        U.putDouble(address + 56L, self.uYy());
        U.putDouble(address + 64L, self.uYz());
        U.putDouble(address + 72L, self.uZx());
        U.putDouble(address + 80L, self.uZy());
        U.putDouble(address + 88L, self.uZz());
        U.putDouble(address + 96L, self.hsX());
        U.putDouble(address + 104L, self.hsY());
        U.putDouble(address + 112L, self.hsZ());
        return self;
    }
    public DoubleOBB loadUnsafe(long address) {
        double _c0 = U.getDouble(address + 0L);
        double _c1 = U.getDouble(address + 8L);
        double _c2 = U.getDouble(address + 16L);
        double _c3 = U.getDouble(address + 24L);
        double _c4 = U.getDouble(address + 32L);
        double _c5 = U.getDouble(address + 40L);
        double _c6 = U.getDouble(address + 48L);
        double _c7 = U.getDouble(address + 56L);
        double _c8 = U.getDouble(address + 64L);
        double _c9 = U.getDouble(address + 72L);
        double _c10 = U.getDouble(address + 80L);
        double _c11 = U.getDouble(address + 88L);
        double _c12 = U.getDouble(address + 96L);
        double _c13 = U.getDouble(address + 104L);
        double _c14 = U.getDouble(address + 112L);
        return new DoubleOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }
    public DoubleOBB storeFloatUnsafe(DoubleOBB self, long address) {
        U.putFloat(address + 0L, (float) self.cX());
        U.putFloat(address + 4L, (float) self.cY());
        U.putFloat(address + 8L, (float) self.cZ());
        U.putFloat(address + 12L, (float) self.uXx());
        U.putFloat(address + 16L, (float) self.uXy());
        U.putFloat(address + 20L, (float) self.uXz());
        U.putFloat(address + 24L, (float) self.uYx());
        U.putFloat(address + 28L, (float) self.uYy());
        U.putFloat(address + 32L, (float) self.uYz());
        U.putFloat(address + 36L, (float) self.uZx());
        U.putFloat(address + 40L, (float) self.uZy());
        U.putFloat(address + 44L, (float) self.uZz());
        U.putFloat(address + 48L, (float) self.hsX());
        U.putFloat(address + 52L, (float) self.hsY());
        U.putFloat(address + 56L, (float) self.hsZ());
        return self;
    }
    public DoubleOBB loadFloatUnsafe(long address) {
        double _c0 = U.getFloat(address + 0L);
        double _c1 = U.getFloat(address + 4L);
        double _c2 = U.getFloat(address + 8L);
        double _c3 = U.getFloat(address + 12L);
        double _c4 = U.getFloat(address + 16L);
        double _c5 = U.getFloat(address + 20L);
        double _c6 = U.getFloat(address + 24L);
        double _c7 = U.getFloat(address + 28L);
        double _c8 = U.getFloat(address + 32L);
        double _c9 = U.getFloat(address + 36L);
        double _c10 = U.getFloat(address + 40L);
        double _c11 = U.getFloat(address + 44L);
        double _c12 = U.getFloat(address + 48L);
        double _c13 = U.getFloat(address + 52L);
        double _c14 = U.getFloat(address + 56L);
        return new DoubleOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }
}
