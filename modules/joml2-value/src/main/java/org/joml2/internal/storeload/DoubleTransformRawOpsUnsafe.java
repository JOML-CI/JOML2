// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleTransformRawOpsUnsafe implements DoubleTransformRawOps {
    public DoubleTransform storeUnsafe(DoubleTransform self, long address) {
        U.putDouble(address + 0L, self.tX());
        U.putDouble(address + 8L, self.tY());
        U.putDouble(address + 16L, self.tZ());
        U.putDouble(address + 24L, self.rX());
        U.putDouble(address + 32L, self.rY());
        U.putDouble(address + 40L, self.rZ());
        U.putDouble(address + 48L, self.rW());
        U.putDouble(address + 56L, self.sX());
        U.putDouble(address + 64L, self.sY());
        U.putDouble(address + 72L, self.sZ());
        return self;
    }
    public DoubleTransform loadUnsafe(long address) {
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
        return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
    }
    public DoubleTransform storeFloatUnsafe(DoubleTransform self, long address) {
        U.putFloat(address + 0L, (float) self.tX());
        U.putFloat(address + 4L, (float) self.tY());
        U.putFloat(address + 8L, (float) self.tZ());
        U.putFloat(address + 12L, (float) self.rX());
        U.putFloat(address + 16L, (float) self.rY());
        U.putFloat(address + 20L, (float) self.rZ());
        U.putFloat(address + 24L, (float) self.rW());
        U.putFloat(address + 28L, (float) self.sX());
        U.putFloat(address + 32L, (float) self.sY());
        U.putFloat(address + 36L, (float) self.sZ());
        return self;
    }
    public DoubleTransform loadFloatUnsafe(long address) {
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
        return new DoubleTransform(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9);
    }
}
