// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public final class Byte3SegOpsUnsafe implements Byte3SegOps {

    private static final Byte3SegOpsMS MS = new Byte3SegOpsMS();
    private static final Byte3RawOpsUnsafe RAW = new Byte3RawOpsUnsafe();

    public MemorySegment store(Byte3 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        RAW.storeUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Byte3 load(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(offset, src);
        return RAW.loadUnsafe(src.address() + offset);
    }
    public MemorySegment storeShort(Byte3 self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeShort(self, offset, dest);
        RAW.storeShortUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Byte3 loadShort(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadShort(offset, src);
        return RAW.loadShortUnsafe(src.address() + offset);
    }
}
