// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public final class Short4SegOpsUnsafe implements Short4SegOps {

    private static final Short4SegOpsMS MS = new Short4SegOpsMS();
    private static final Short4RawOpsUnsafe RAW = new Short4RawOpsUnsafe();

    public MemorySegment storeByte(Short4Impl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeByte(self, offset, dest);
        RAW.storeByteUnsafe(self, dest.address() + offset);
        return dest;
    }
    public Short4 loadByte(Short4Impl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadByte(self, offset, src);
        return RAW.loadByteUnsafe(self, src.address() + offset);
    }
}
