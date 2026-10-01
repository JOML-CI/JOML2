// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public final class Int4SegOpsUnsafe implements Int4SegOps {

    private static final Int4SegOpsMS MS = new Int4SegOpsMS();
    private static final Int4RawOpsUnsafe RAW = new Int4RawOpsUnsafe();

    public MemorySegment storeLong(Int4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeLong(self, offset, dest);
        RAW.storeLongUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Int4 loadLong(Int4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadLong(self, offset, src);
        return RAW.loadLongUnsafe(self, src.address() + offset);
    }
}
