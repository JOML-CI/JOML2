// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Long2SegOpsUnsafe extends Long2SegOps {

    private static final Long2SegOpsMS MS = new Long2SegOpsMS();

    public MemorySegment store(Long2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putLong(address, self.x);
        U.putLong(address + 8L, self.y);
        return dest;
    }
    public Long2 load(Long2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        long address = src.address() + offset;
        self.x = U.getLong(address);
        self.y = U.getLong(address + 8L);
        return self;
    }
    public MemorySegment storeInt(Long2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeInt(self, offset, dest);
        long address = dest.address() + offset;
        U.putInt(address, (int) self.x);
        U.putInt(address + 4L, (int) self.y);
        return dest;
    }
    public Long2 loadInt(Long2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadInt(self, offset, src);
        long address = src.address() + offset;
        self.x = U.getInt(address);
        self.y = U.getInt(address + 4L);
        return self;
    }
}
