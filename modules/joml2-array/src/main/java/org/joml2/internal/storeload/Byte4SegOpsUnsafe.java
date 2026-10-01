// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public final class Byte4SegOpsUnsafe implements Byte4SegOps {

    private static final Byte4SegOpsMS MS = new Byte4SegOpsMS();
    private static final Byte4RawOpsUnsafe RAW = new Byte4RawOpsUnsafe();

    public MemorySegment store(Byte4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        RAW.storeUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Byte4 load(Byte4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        return RAW.loadUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeShort(Byte4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeShort(self, offset, dest);
        RAW.storeShortUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Byte4 loadShort(Byte4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadShort(self, offset, src);
        return RAW.loadShortUnsafe(self, src.address() + offset);
    }
}
