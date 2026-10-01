// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public final class Byte2SegOpsUnsafe implements Byte2SegOps {

    private static final Byte2SegOpsMS MS = new Byte2SegOpsMS();
    private static final Byte2RawOpsUnsafe RAW = new Byte2RawOpsUnsafe();

    public MemorySegment store(Byte2 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        RAW.storeUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Byte2 load(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(offset, src);
        return RAW.loadUnsafe(src.address() + offset);
    }
    public MemorySegment storeShort(Byte2 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeShort(self, offset, dest);
        RAW.storeShortUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Byte2 loadShort(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadShort(offset, src);
        return RAW.loadShortUnsafe(src.address() + offset);
    }
}
