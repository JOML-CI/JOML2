// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatOBBRawOpsUnsafe implements FloatOBBRawOps {
    public FloatOBB storeUnsafe(FloatOBB self, long address) {
        U.putFloat(address + 0L, self.cX());
        U.putFloat(address + 4L, self.cY());
        U.putFloat(address + 8L, self.cZ());
        U.putFloat(address + 12L, self.uXx());
        U.putFloat(address + 16L, self.uXy());
        U.putFloat(address + 20L, self.uXz());
        U.putFloat(address + 24L, self.uYx());
        U.putFloat(address + 28L, self.uYy());
        U.putFloat(address + 32L, self.uYz());
        U.putFloat(address + 36L, self.uZx());
        U.putFloat(address + 40L, self.uZy());
        U.putFloat(address + 44L, self.uZz());
        U.putFloat(address + 48L, self.hsX());
        U.putFloat(address + 52L, self.hsY());
        U.putFloat(address + 56L, self.hsZ());
        return self;
    }
    public FloatOBB loadUnsafe(long address) {
        float _c0 = U.getFloat(address + 0L);
        float _c1 = U.getFloat(address + 4L);
        float _c2 = U.getFloat(address + 8L);
        float _c3 = U.getFloat(address + 12L);
        float _c4 = U.getFloat(address + 16L);
        float _c5 = U.getFloat(address + 20L);
        float _c6 = U.getFloat(address + 24L);
        float _c7 = U.getFloat(address + 28L);
        float _c8 = U.getFloat(address + 32L);
        float _c9 = U.getFloat(address + 36L);
        float _c10 = U.getFloat(address + 40L);
        float _c11 = U.getFloat(address + 44L);
        float _c12 = U.getFloat(address + 48L);
        float _c13 = U.getFloat(address + 52L);
        float _c14 = U.getFloat(address + 56L);
        return new FloatOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }
    public FloatOBB storeDoubleUnsafe(FloatOBB self, long address) {
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
    public FloatOBB loadDoubleUnsafe(long address) {
        float _c0 = (float) U.getDouble(address + 0L);
        float _c1 = (float) U.getDouble(address + 8L);
        float _c2 = (float) U.getDouble(address + 16L);
        float _c3 = (float) U.getDouble(address + 24L);
        float _c4 = (float) U.getDouble(address + 32L);
        float _c5 = (float) U.getDouble(address + 40L);
        float _c6 = (float) U.getDouble(address + 48L);
        float _c7 = (float) U.getDouble(address + 56L);
        float _c8 = (float) U.getDouble(address + 64L);
        float _c9 = (float) U.getDouble(address + 72L);
        float _c10 = (float) U.getDouble(address + 80L);
        float _c11 = (float) U.getDouble(address + 88L);
        float _c12 = (float) U.getDouble(address + 96L);
        float _c13 = (float) U.getDouble(address + 104L);
        float _c14 = (float) U.getDouble(address + 112L);
        return new FloatOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }
}
