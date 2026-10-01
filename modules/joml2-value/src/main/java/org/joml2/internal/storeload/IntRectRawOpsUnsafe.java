// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class IntRectRawOpsUnsafe implements IntRectRawOps {
    public IntRect storeUnsafe(IntRect self, long address) {
        U.putInt(address + 0L, self.minX());
        U.putInt(address + 4L, self.minY());
        U.putInt(address + 8L, self.maxX());
        U.putInt(address + 12L, self.maxY());
        return self;
    }
    public IntRect loadUnsafe(long address) {
        int _c0 = U.getInt(address + 0L);
        int _c1 = U.getInt(address + 4L);
        int _c2 = U.getInt(address + 8L);
        int _c3 = U.getInt(address + 12L);
        return new IntRect(_c0, _c1, _c2, _c3);
    }
    public IntRect storeLongUnsafe(IntRect self, long address) {
        U.putLong(address + 0L, self.minX());
        U.putLong(address + 8L, self.minY());
        U.putLong(address + 16L, self.maxX());
        U.putLong(address + 24L, self.maxY());
        return self;
    }
    public IntRect loadLongUnsafe(long address) {
        int _c0 = (int) U.getLong(address + 0L);
        int _c1 = (int) U.getLong(address + 8L);
        int _c2 = (int) U.getLong(address + 16L);
        int _c3 = (int) U.getLong(address + 24L);
        return new IntRect(_c0, _c1, _c2, _c3);
    }
}
