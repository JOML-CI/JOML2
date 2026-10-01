// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class IntRectRawOpsUnsafe extends IntRectRawOps {
    public IntRect storeUnsafe(IntRectImpl self, long address) {
        U.putInt(address, self.minX);
        U.putInt(address + 4L, self.minY);
        U.putInt(address + 8L, self.maxX);
        U.putInt(address + 12L, self.maxY);
        return self;
    }

    public IntRect storeHeap(IntRectImpl self, byte[] bytes, long address) {
        U.putInt(bytes, address, self.minX);
        U.putInt(bytes, address + 4L, self.minY);
        U.putInt(bytes, address + 8L, self.maxX);
        U.putInt(bytes, address + 12L, self.maxY);
        return self;
    }

    public IntRect loadUnsafe(IntRectImpl self, long address) {
        self.minX = U.getInt(address);
        self.minY = U.getInt(address + 4L);
        self.maxX = U.getInt(address + 8L);
        self.maxY = U.getInt(address + 12L);
        return self;
    }

    public IntRect loadHeap(IntRectImpl self, byte[] bytes, long address) {
        self.minX = U.getInt(bytes, address);
        self.minY = U.getInt(bytes, address + 4L);
        self.maxX = U.getInt(bytes, address + 8L);
        self.maxY = U.getInt(bytes, address + 12L);
        return self;
    }

    public IntRect storeLongUnsafe(IntRectImpl self, long address) {
        U.putLong(address, self.minX);
        U.putLong(address + 8L, self.minY);
        U.putLong(address + 16L, self.maxX);
        U.putLong(address + 24L, self.maxY);
        return self;
    }

    public IntRect storeLongHeap(IntRectImpl self, byte[] bytes, long address) {
        U.putLong(bytes, address, self.minX);
        U.putLong(bytes, address + 8L, self.minY);
        U.putLong(bytes, address + 16L, self.maxX);
        U.putLong(bytes, address + 24L, self.maxY);
        return self;
    }

    public IntRect loadLongUnsafe(IntRectImpl self, long address) {
        self.minX = (int) U.getLong(address);
        self.minY = (int) U.getLong(address + 8L);
        self.maxX = (int) U.getLong(address + 16L);
        self.maxY = (int) U.getLong(address + 24L);
        return self;
    }

    public IntRect loadLongHeap(IntRectImpl self, byte[] bytes, long address) {
        self.minX = (int) U.getLong(bytes, address);
        self.minY = (int) U.getLong(bytes, address + 8L);
        self.maxX = (int) U.getLong(bytes, address + 16L);
        self.maxY = (int) U.getLong(bytes, address + 24L);
        return self;
    }
}
