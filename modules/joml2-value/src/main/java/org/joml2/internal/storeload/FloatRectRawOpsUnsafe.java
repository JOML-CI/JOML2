// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class FloatRectRawOpsUnsafe extends FloatRectRawOps {
    public FloatRect storeUnsafe(FloatRect self, long address) {
        U.putFloat(address, self.minX());
        U.putFloat(address + 4L, self.minY());
        U.putFloat(address + 8L, self.maxX());
        U.putFloat(address + 12L, self.maxY());
        return self;
    }

    public FloatRect storeHeap(FloatRect self, byte[] bytes, long address) {
        U.putFloat(bytes, address, self.minX());
        U.putFloat(bytes, address + 4L, self.minY());
        U.putFloat(bytes, address + 8L, self.maxX());
        U.putFloat(bytes, address + 12L, self.maxY());
        return self;
    }

    public FloatRect loadUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = _U.getFloat(address);
        float _c1 = _U.getFloat(address + 4L);
        float _c2 = _U.getFloat(address + 8L);
        float _c3 = _U.getFloat(address + 12L);
        return new FloatRect(_c0, _c1, _c2, _c3);
    }

    public FloatRect loadHeap(byte[] bytes, long address) {
        float _c0 = U.getFloat(bytes, address);
        float _c1 = U.getFloat(bytes, address + 4L);
        float _c2 = U.getFloat(bytes, address + 8L);
        float _c3 = U.getFloat(bytes, address + 12L);
        return new FloatRect(_c0, _c1, _c2, _c3);
    }

    public FloatRect storeDoubleUnsafe(FloatRect self, long address) {
        U.putDouble(address, self.minX());
        U.putDouble(address + 8L, self.minY());
        U.putDouble(address + 16L, self.maxX());
        U.putDouble(address + 24L, self.maxY());
        return self;
    }

    public FloatRect storeDoubleHeap(FloatRect self, byte[] bytes, long address) {
        U.putDouble(bytes, address, self.minX());
        U.putDouble(bytes, address + 8L, self.minY());
        U.putDouble(bytes, address + 16L, self.maxX());
        U.putDouble(bytes, address + 24L, self.maxY());
        return self;
    }

    public FloatRect loadDoubleUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        float _c0 = (float) _U.getDouble(address);
        float _c1 = (float) _U.getDouble(address + 8L);
        float _c2 = (float) _U.getDouble(address + 16L);
        float _c3 = (float) _U.getDouble(address + 24L);
        return new FloatRect(_c0, _c1, _c2, _c3);
    }

    public FloatRect loadDoubleHeap(byte[] bytes, long address) {
        float _c0 = (float) U.getDouble(bytes, address);
        float _c1 = (float) U.getDouble(bytes, address + 8L);
        float _c2 = (float) U.getDouble(bytes, address + 16L);
        float _c3 = (float) U.getDouble(bytes, address + 24L);
        return new FloatRect(_c0, _c1, _c2, _c3);
    }
}
