// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Long4SegOpsUnsafe extends Long4SegOps {

    private static final Long4SegOpsMS MS = new Long4SegOpsMS();

    public MemorySegment store(Long4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putLong(address, self.data[0]);
        U.putLong(address + 8L, self.data[1]);
        U.putLong(address + 16L, self.data[2]);
        U.putLong(address + 24L, self.data[3]);
        return dest;
    }
    public Long4 load(Long4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getLong(address);
        self.data[1] = U.getLong(address + 8L);
        self.data[2] = U.getLong(address + 16L);
        self.data[3] = U.getLong(address + 24L);
        return self;
    }
    public MemorySegment storeInt(Long4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeInt(self, offset, dest);
        long address = dest.address() + offset;
        U.putInt(address, (int) self.data[0]);
        U.putInt(address + 4L, (int) self.data[1]);
        U.putInt(address + 8L, (int) self.data[2]);
        U.putInt(address + 12L, (int) self.data[3]);
        return dest;
    }
    public Long4 loadInt(Long4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadInt(self, offset, src);
        long address = src.address() + offset;
        self.data[0] = U.getInt(address);
        self.data[1] = U.getInt(address + 4L);
        self.data[2] = U.getInt(address + 8L);
        self.data[3] = U.getInt(address + 12L);
        return self;
    }
}
