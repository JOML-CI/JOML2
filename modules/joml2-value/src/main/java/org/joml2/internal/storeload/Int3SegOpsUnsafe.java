// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Int3SegOpsUnsafe extends Int3SegOps {

    private static final Int3SegOpsMS MS = new Int3SegOpsMS();

    public MemorySegment store(Int3 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putInt(address, self.x());
        U.putInt(address + 4L, self.y());
        U.putInt(address + 8L, self.z());
        return dest;
    }
    public Int3 load(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(offset, src);
        long address = src.address() + offset;
        int _c0 = U.getInt(address);
        int _c1 = U.getInt(address + 4L);
        int _c2 = U.getInt(address + 8L);
        return new Int3(_c0, _c1, _c2);
    }
    public MemorySegment storeLong(Int3 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeLong(self, offset, dest);
        long address = dest.address() + offset;
        U.putLong(address, self.x());
        U.putLong(address + 8L, self.y());
        U.putLong(address + 16L, self.z());
        return dest;
    }
    public Int3 loadLong(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadLong(offset, src);
        long address = src.address() + offset;
        int _c0 = (int) U.getLong(address);
        int _c1 = (int) U.getLong(address + 8L);
        int _c2 = (int) U.getLong(address + 16L);
        return new Int3(_c0, _c1, _c2);
    }
}
