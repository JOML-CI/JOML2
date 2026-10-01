// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleOBBRawOpsUnsafe extends DoubleOBBRawOps {
    public DoubleOBB storeUnsafe(DoubleOBB self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(address, self.cX());
        _U.putDouble(address + 8L, self.cY());
        _U.putDouble(address + 16L, self.cZ());
        _U.putDouble(address + 24L, self.uXx());
        _U.putDouble(address + 32L, self.uXy());
        _U.putDouble(address + 40L, self.uXz());
        _U.putDouble(address + 48L, self.uYx());
        _U.putDouble(address + 56L, self.uYy());
        _U.putDouble(address + 64L, self.uYz());
        _U.putDouble(address + 72L, self.uZx());
        _U.putDouble(address + 80L, self.uZy());
        _U.putDouble(address + 88L, self.uZz());
        _U.putDouble(address + 96L, self.hsX());
        _U.putDouble(address + 104L, self.hsY());
        _U.putDouble(address + 112L, self.hsZ());
        return self;
    }

    public DoubleOBB storeHeap(DoubleOBB self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putDouble(bytes, address, self.cX());
        _U.putDouble(bytes, address + 8L, self.cY());
        _U.putDouble(bytes, address + 16L, self.cZ());
        _U.putDouble(bytes, address + 24L, self.uXx());
        _U.putDouble(bytes, address + 32L, self.uXy());
        _U.putDouble(bytes, address + 40L, self.uXz());
        _U.putDouble(bytes, address + 48L, self.uYx());
        _U.putDouble(bytes, address + 56L, self.uYy());
        _U.putDouble(bytes, address + 64L, self.uYz());
        _U.putDouble(bytes, address + 72L, self.uZx());
        _U.putDouble(bytes, address + 80L, self.uZy());
        _U.putDouble(bytes, address + 88L, self.uZz());
        _U.putDouble(bytes, address + 96L, self.hsX());
        _U.putDouble(bytes, address + 104L, self.hsY());
        _U.putDouble(bytes, address + 112L, self.hsZ());
        return self;
    }

    public DoubleOBB loadUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(address);
        double _c1 = _U.getDouble(address + 8L);
        double _c2 = _U.getDouble(address + 16L);
        double _c3 = _U.getDouble(address + 24L);
        double _c4 = _U.getDouble(address + 32L);
        double _c5 = _U.getDouble(address + 40L);
        double _c6 = _U.getDouble(address + 48L);
        double _c7 = _U.getDouble(address + 56L);
        double _c8 = _U.getDouble(address + 64L);
        double _c9 = _U.getDouble(address + 72L);
        double _c10 = _U.getDouble(address + 80L);
        double _c11 = _U.getDouble(address + 88L);
        double _c12 = _U.getDouble(address + 96L);
        double _c13 = _U.getDouble(address + 104L);
        double _c14 = _U.getDouble(address + 112L);
        return new DoubleOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }

    public DoubleOBB loadHeap(byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(bytes, address);
        double _c1 = _U.getDouble(bytes, address + 8L);
        double _c2 = _U.getDouble(bytes, address + 16L);
        double _c3 = _U.getDouble(bytes, address + 24L);
        double _c4 = _U.getDouble(bytes, address + 32L);
        double _c5 = _U.getDouble(bytes, address + 40L);
        double _c6 = _U.getDouble(bytes, address + 48L);
        double _c7 = _U.getDouble(bytes, address + 56L);
        double _c8 = _U.getDouble(bytes, address + 64L);
        double _c9 = _U.getDouble(bytes, address + 72L);
        double _c10 = _U.getDouble(bytes, address + 80L);
        double _c11 = _U.getDouble(bytes, address + 88L);
        double _c12 = _U.getDouble(bytes, address + 96L);
        double _c13 = _U.getDouble(bytes, address + 104L);
        double _c14 = _U.getDouble(bytes, address + 112L);
        return new DoubleOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }

    public DoubleOBB storeFloatUnsafe(DoubleOBB self, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(address, (float) self.cX());
        _U.putFloat(address + 4L, (float) self.cY());
        _U.putFloat(address + 8L, (float) self.cZ());
        _U.putFloat(address + 12L, (float) self.uXx());
        _U.putFloat(address + 16L, (float) self.uXy());
        _U.putFloat(address + 20L, (float) self.uXz());
        _U.putFloat(address + 24L, (float) self.uYx());
        _U.putFloat(address + 28L, (float) self.uYy());
        _U.putFloat(address + 32L, (float) self.uYz());
        _U.putFloat(address + 36L, (float) self.uZx());
        _U.putFloat(address + 40L, (float) self.uZy());
        _U.putFloat(address + 44L, (float) self.uZz());
        _U.putFloat(address + 48L, (float) self.hsX());
        _U.putFloat(address + 52L, (float) self.hsY());
        _U.putFloat(address + 56L, (float) self.hsZ());
        return self;
    }

    public DoubleOBB storeFloatHeap(DoubleOBB self, byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        _U.putFloat(bytes, address, (float) self.cX());
        _U.putFloat(bytes, address + 4L, (float) self.cY());
        _U.putFloat(bytes, address + 8L, (float) self.cZ());
        _U.putFloat(bytes, address + 12L, (float) self.uXx());
        _U.putFloat(bytes, address + 16L, (float) self.uXy());
        _U.putFloat(bytes, address + 20L, (float) self.uXz());
        _U.putFloat(bytes, address + 24L, (float) self.uYx());
        _U.putFloat(bytes, address + 28L, (float) self.uYy());
        _U.putFloat(bytes, address + 32L, (float) self.uYz());
        _U.putFloat(bytes, address + 36L, (float) self.uZx());
        _U.putFloat(bytes, address + 40L, (float) self.uZy());
        _U.putFloat(bytes, address + 44L, (float) self.uZz());
        _U.putFloat(bytes, address + 48L, (float) self.hsX());
        _U.putFloat(bytes, address + 52L, (float) self.hsY());
        _U.putFloat(bytes, address + 56L, (float) self.hsZ());
        return self;
    }

    public DoubleOBB loadFloatUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(address);
        double _c1 = _U.getFloat(address + 4L);
        double _c2 = _U.getFloat(address + 8L);
        double _c3 = _U.getFloat(address + 12L);
        double _c4 = _U.getFloat(address + 16L);
        double _c5 = _U.getFloat(address + 20L);
        double _c6 = _U.getFloat(address + 24L);
        double _c7 = _U.getFloat(address + 28L);
        double _c8 = _U.getFloat(address + 32L);
        double _c9 = _U.getFloat(address + 36L);
        double _c10 = _U.getFloat(address + 40L);
        double _c11 = _U.getFloat(address + 44L);
        double _c12 = _U.getFloat(address + 48L);
        double _c13 = _U.getFloat(address + 52L);
        double _c14 = _U.getFloat(address + 56L);
        return new DoubleOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }

    public DoubleOBB loadFloatHeap(byte[] bytes, long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(bytes, address);
        double _c1 = _U.getFloat(bytes, address + 4L);
        double _c2 = _U.getFloat(bytes, address + 8L);
        double _c3 = _U.getFloat(bytes, address + 12L);
        double _c4 = _U.getFloat(bytes, address + 16L);
        double _c5 = _U.getFloat(bytes, address + 20L);
        double _c6 = _U.getFloat(bytes, address + 24L);
        double _c7 = _U.getFloat(bytes, address + 28L);
        double _c8 = _U.getFloat(bytes, address + 32L);
        double _c9 = _U.getFloat(bytes, address + 36L);
        double _c10 = _U.getFloat(bytes, address + 40L);
        double _c11 = _U.getFloat(bytes, address + 44L);
        double _c12 = _U.getFloat(bytes, address + 48L);
        double _c13 = _U.getFloat(bytes, address + 52L);
        double _c14 = _U.getFloat(bytes, address + 56L);
        return new DoubleOBB(_c0, _c1, _c2, _c3, _c4, _c5, _c6, _c7, _c8, _c9, _c10, _c11, _c12, _c13, _c14);
    }
}
