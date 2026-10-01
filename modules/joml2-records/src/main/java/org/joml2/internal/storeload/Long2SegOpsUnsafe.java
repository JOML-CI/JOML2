// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Long2SegOpsUnsafe extends Long2SegOps {

    private static final Long2SegOpsMS MS = new Long2SegOpsMS();

    public MemorySegment store(Long2 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putLong(address, self.x());
        U.putLong(address + 8L, self.y());
        return dest;
    }
    public Long2 load(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(offset, src);
        long address = src.address() + offset;
        long _c0 = U.getLong(address);
        long _c1 = U.getLong(address + 8L);
        return new Long2(_c0, _c1);
    }
    public MemorySegment storeInt(Long2 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeInt(self, offset, dest);
        long address = dest.address() + offset;
        U.putInt(address, (int) self.x());
        U.putInt(address + 4L, (int) self.y());
        return dest;
    }
    public Long2 loadInt(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadInt(offset, src);
        long address = src.address() + offset;
        long _c0 = U.getInt(address);
        long _c1 = U.getInt(address + 4L);
        return new Long2(_c0, _c1);
    }
}
