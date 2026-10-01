// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;
import static org.joml2.internal.unsafe.UnsafeOpsHolder.U;

public final class Long3SegOpsUnsafe extends Long3SegOps {

    private static final Long3SegOpsMS MS = new Long3SegOpsMS();

    public MemorySegment store(Long3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        long address = dest.address() + offset;
        U.putLong(address, self.x);
        U.putLong(address + 8L, self.y);
        U.putLong(address + 16L, self.z);
        return dest;
    }
    public Long3 load(Long3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        long address = src.address() + offset;
        self.x = U.getLong(address);
        self.y = U.getLong(address + 8L);
        self.z = U.getLong(address + 16L);
        return self;
    }
    public MemorySegment storeInt(Long3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeInt(self, offset, dest);
        long address = dest.address() + offset;
        U.putInt(address, (int) self.x);
        U.putInt(address + 4L, (int) self.y);
        U.putInt(address + 8L, (int) self.z);
        return dest;
    }
    public Long3 loadInt(Long3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadInt(self, offset, src);
        long address = src.address() + offset;
        self.x = U.getInt(address);
        self.y = U.getInt(address + 4L);
        self.z = U.getInt(address + 8L);
        return self;
    }
}
