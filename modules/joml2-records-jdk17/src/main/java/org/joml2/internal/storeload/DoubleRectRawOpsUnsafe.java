// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class DoubleRectRawOpsUnsafe extends DoubleRectRawOps {
    public DoubleRect storeUnsafe(DoubleRect self, long address) {
        U.putDouble(address, self.minX());
        U.putDouble(address + 8L, self.minY());
        U.putDouble(address + 16L, self.maxX());
        U.putDouble(address + 24L, self.maxY());
        return self;
    }

    public DoubleRect storeHeap(DoubleRect self, byte[] bytes, long address) {
        U.putDouble(bytes, address, self.minX());
        U.putDouble(bytes, address + 8L, self.minY());
        U.putDouble(bytes, address + 16L, self.maxX());
        U.putDouble(bytes, address + 24L, self.maxY());
        return self;
    }

    public DoubleRect loadUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getDouble(address);
        double _c1 = _U.getDouble(address + 8L);
        double _c2 = _U.getDouble(address + 16L);
        double _c3 = _U.getDouble(address + 24L);
        return new DoubleRect(_c0, _c1, _c2, _c3);
    }

    public DoubleRect loadHeap(byte[] bytes, long address) {
        double _c0 = U.getDouble(bytes, address);
        double _c1 = U.getDouble(bytes, address + 8L);
        double _c2 = U.getDouble(bytes, address + 16L);
        double _c3 = U.getDouble(bytes, address + 24L);
        return new DoubleRect(_c0, _c1, _c2, _c3);
    }

    public DoubleRect storeFloatUnsafe(DoubleRect self, long address) {
        U.putFloat(address, (float) self.minX());
        U.putFloat(address + 4L, (float) self.minY());
        U.putFloat(address + 8L, (float) self.maxX());
        U.putFloat(address + 12L, (float) self.maxY());
        return self;
    }

    public DoubleRect storeFloatHeap(DoubleRect self, byte[] bytes, long address) {
        U.putFloat(bytes, address, (float) self.minX());
        U.putFloat(bytes, address + 4L, (float) self.minY());
        U.putFloat(bytes, address + 8L, (float) self.maxX());
        U.putFloat(bytes, address + 12L, (float) self.maxY());
        return self;
    }

    public DoubleRect loadFloatUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        double _c0 = _U.getFloat(address);
        double _c1 = _U.getFloat(address + 4L);
        double _c2 = _U.getFloat(address + 8L);
        double _c3 = _U.getFloat(address + 12L);
        return new DoubleRect(_c0, _c1, _c2, _c3);
    }

    public DoubleRect loadFloatHeap(byte[] bytes, long address) {
        double _c0 = U.getFloat(bytes, address);
        double _c1 = U.getFloat(bytes, address + 4L);
        double _c2 = U.getFloat(bytes, address + 8L);
        double _c3 = U.getFloat(bytes, address + 12L);
        return new DoubleRect(_c0, _c1, _c2, _c3);
    }
}
