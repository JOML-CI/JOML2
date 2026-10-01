// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public final class Long3SegOpsUnsafe implements Long3SegOps {

    private static final Long3SegOpsMS MS = new Long3SegOpsMS();
    private static final Long3RawOpsUnsafe RAW = new Long3RawOpsUnsafe();

    public MemorySegment store(Long3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        RAW.storeUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Long3 load(Long3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        return RAW.loadUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeInt(Long3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeInt(self, offset, dest);
        RAW.storeIntUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Long3 loadInt(Long3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadInt(self, offset, src);
        return RAW.loadIntUnsafe(self, src.address() + offset);
    }
}
