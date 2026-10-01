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
        U.putLong(address, self.data[0]);
        U.putLong(address + 8L, self.data[1]);
        return dest;
    }
    public Long2 load(Long2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getLong(address);
        self.data[1] = U.getLong(address + 8L);
        return self;
    }
    public MemorySegment storeInt(Long2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeInt(self, offset, dest);
        long address = dest.address() + offset;
        U.putInt(address, (int) self.data[0]);
        U.putInt(address + 4L, (int) self.data[1]);
        return dest;
    }
    public Long2 loadInt(Long2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadInt(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getInt(address);
        self.data[1] = U.getInt(address + 4L);
        return self;
    }
}
