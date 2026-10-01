// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Long4SegOpsUnsafe extends Long4SegOps {

    private static final Long4SegOpsMS MS = new Long4SegOpsMS();

    public MemorySegment store(Long4 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putLong(address, self.x());
        U.putLong(address + 8L, self.y());
        U.putLong(address + 16L, self.z());
        U.putLong(address + 24L, self.w());
        return dest;
    }
    public Long4 load(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(offset, src);
        long address = src.address() + offset;
        long _c0 = U.getLong(address);
        long _c1 = U.getLong(address + 8L);
        long _c2 = U.getLong(address + 16L);
        long _c3 = U.getLong(address + 24L);
        return new Long4(_c0, _c1, _c2, _c3);
    }
    public MemorySegment storeInt(Long4 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeInt(self, offset, dest);
        long address = dest.address() + offset;
        U.putInt(address, (int) self.x());
        U.putInt(address + 4L, (int) self.y());
        U.putInt(address + 8L, (int) self.z());
        U.putInt(address + 12L, (int) self.w());
        return dest;
    }
    public Long4 loadInt(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadInt(offset, src);
        long address = src.address() + offset;
        long _c0 = U.getInt(address);
        long _c1 = U.getInt(address + 4L);
        long _c2 = U.getInt(address + 8L);
        long _c3 = U.getInt(address + 12L);
        return new Long4(_c0, _c1, _c2, _c3);
    }
}
