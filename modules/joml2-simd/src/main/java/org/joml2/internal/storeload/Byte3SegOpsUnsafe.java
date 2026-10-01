// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public final class Byte3SegOpsUnsafe implements Byte3SegOps {

    private static final Byte3SegOpsMS MS = new Byte3SegOpsMS();
    private static final Byte3RawOpsUnsafe RAW = new Byte3RawOpsUnsafe();

    public MemorySegment store(Byte3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        RAW.storeUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Byte3 load(Byte3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        return RAW.loadUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeShort(Byte3Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeShort(self, offset, dest);
        RAW.storeShortUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Byte3 loadShort(Byte3Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadShort(self, offset, src);
        return RAW.loadShortUnsafe(self, src.address() + offset);
    }
}
