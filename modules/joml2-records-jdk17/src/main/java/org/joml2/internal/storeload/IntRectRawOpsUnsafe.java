// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class IntRectRawOpsUnsafe extends IntRectRawOps {
    public IntRect storeUnsafe(IntRect self, long address) {
        U.putInt(address, self.minX());
        U.putInt(address + 4L, self.minY());
        U.putInt(address + 8L, self.maxX());
        U.putInt(address + 12L, self.maxY());
        return self;
    }

    public IntRect storeHeap(IntRect self, byte[] bytes, long address) {
        U.putInt(bytes, address, self.minX());
        U.putInt(bytes, address + 4L, self.minY());
        U.putInt(bytes, address + 8L, self.maxX());
        U.putInt(bytes, address + 12L, self.maxY());
        return self;
    }

    public IntRect loadUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        int _c0 = _U.getInt(address);
        int _c1 = _U.getInt(address + 4L);
        int _c2 = _U.getInt(address + 8L);
        int _c3 = _U.getInt(address + 12L);
        return new IntRect(_c0, _c1, _c2, _c3);
    }

    public IntRect loadHeap(byte[] bytes, long address) {
        int _c0 = U.getInt(bytes, address);
        int _c1 = U.getInt(bytes, address + 4L);
        int _c2 = U.getInt(bytes, address + 8L);
        int _c3 = U.getInt(bytes, address + 12L);
        return new IntRect(_c0, _c1, _c2, _c3);
    }

    public IntRect storeLongUnsafe(IntRect self, long address) {
        U.putLong(address, self.minX());
        U.putLong(address + 8L, self.minY());
        U.putLong(address + 16L, self.maxX());
        U.putLong(address + 24L, self.maxY());
        return self;
    }

    public IntRect storeLongHeap(IntRect self, byte[] bytes, long address) {
        U.putLong(bytes, address, self.minX());
        U.putLong(bytes, address + 8L, self.minY());
        U.putLong(bytes, address + 16L, self.maxX());
        U.putLong(bytes, address + 24L, self.maxY());
        return self;
    }

    public IntRect loadLongUnsafe(long address) {
        org.joml2.internal.unsafe.UnsafeOps _U = U;
        int _c0 = (int) _U.getLong(address);
        int _c1 = (int) _U.getLong(address + 8L);
        int _c2 = (int) _U.getLong(address + 16L);
        int _c3 = (int) _U.getLong(address + 24L);
        return new IntRect(_c0, _c1, _c2, _c3);
    }

    public IntRect loadLongHeap(byte[] bytes, long address) {
        int _c0 = (int) U.getLong(bytes, address);
        int _c1 = (int) U.getLong(bytes, address + 8L);
        int _c2 = (int) U.getLong(bytes, address + 16L);
        int _c3 = (int) U.getLong(bytes, address + 24L);
        return new IntRect(_c0, _c1, _c2, _c3);
    }
}
