// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public final class Long4SegOpsUnsafe implements Long4SegOps {

    private static final Long4SegOpsMS MS = new Long4SegOpsMS();
    private static final Long4RawOpsUnsafe RAW = new Long4RawOpsUnsafe();

    public MemorySegment store(Long4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        RAW.storeUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Long4 load(Long4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        return RAW.loadUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeInt(Long4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeInt(self, offset, dest);
        RAW.storeIntUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Long4 loadInt(Long4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadInt(self, offset, src);
        return RAW.loadIntUnsafe(self, src.address() + offset);
    }
}
