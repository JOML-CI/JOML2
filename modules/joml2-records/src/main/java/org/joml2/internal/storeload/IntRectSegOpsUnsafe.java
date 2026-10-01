// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class IntRectSegOpsUnsafe extends IntRectSegOps {

    private static final IntRectSegOpsMS MS = new IntRectSegOpsMS();

    public MemorySegment store(IntRect self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putInt(address, self.minX());
        U.putInt(address + 4L, self.minY());
        U.putInt(address + 8L, self.maxX());
        U.putInt(address + 12L, self.maxY());
        return dest;
    }
    public IntRect load(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(offset, src);
        long address = src.address() + offset;
        int _c0 = U.getInt(address);
        int _c1 = U.getInt(address + 4L);
        int _c2 = U.getInt(address + 8L);
        int _c3 = U.getInt(address + 12L);
        return new IntRect(_c0, _c1, _c2, _c3);
    }
    public MemorySegment storeLong(IntRect self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeLong(self, offset, dest);
        long address = dest.address() + offset;
        U.putLong(address, self.minX());
        U.putLong(address + 8L, self.minY());
        U.putLong(address + 16L, self.maxX());
        U.putLong(address + 24L, self.maxY());
        return dest;
    }
    public IntRect loadLong(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadLong(offset, src);
        long address = src.address() + offset;
        int _c0 = (int) U.getLong(address);
        int _c1 = (int) U.getLong(address + 8L);
        int _c2 = (int) U.getLong(address + 16L);
        int _c3 = (int) U.getLong(address + 24L);
        return new IntRect(_c0, _c1, _c2, _c3);
    }
}
