// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public final class Short2SegOpsUnsafe implements Short2SegOps {

    private static final Short2SegOpsMS MS = new Short2SegOpsMS();
    private static final Short2RawOpsUnsafe RAW = new Short2RawOpsUnsafe();

    public MemorySegment store(Short2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        RAW.storeUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Short2 load(Short2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        return RAW.loadUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeByte(Short2Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeByte(self, offset, dest);
        RAW.storeByteUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Short2 loadByte(Short2Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadByte(self, offset, src);
        return RAW.loadByteUnsafe(self, src.address() + offset);
    }
}
