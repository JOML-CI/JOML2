// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class IntRectSegOpsUnsafe extends IntRectSegOps {

    private static final IntRectSegOpsMS MS = new IntRectSegOpsMS();

    public MemorySegment store(IntRectImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putInt(address, self.minX);
        U.putInt(address + 4L, self.minY);
        U.putInt(address + 8L, self.maxX);
        U.putInt(address + 12L, self.maxY);
        return dest;
    }
    public IntRect load(IntRectImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        long address = src.address() + offset;
        self.minX = U.getInt(address);
        self.minY = U.getInt(address + 4L);
        self.maxX = U.getInt(address + 8L);
        self.maxY = U.getInt(address + 12L);
        return self;
    }
    public MemorySegment storeLong(IntRectImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeLong(self, offset, dest);
        long address = dest.address() + offset;
        U.putLong(address, self.minX);
        U.putLong(address + 8L, self.minY);
        U.putLong(address + 16L, self.maxX);
        U.putLong(address + 24L, self.maxY);
        return dest;
    }
    public IntRect loadLong(IntRectImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadLong(self, offset, src);
        long address = src.address() + offset;
        self.minX = (int) U.getLong(address);
        self.minY = (int) U.getLong(address + 8L);
        self.maxX = (int) U.getLong(address + 16L);
        self.maxY = (int) U.getLong(address + 24L);
        return self;
    }
}
