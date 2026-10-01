// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public final class DoubleQuatSegOpsUnsafe implements DoubleQuatSegOps {

    private static final DoubleQuatSegOpsMS MS = new DoubleQuatSegOpsMS();
    private static final DoubleQuatRawOpsUnsafe RAW = new DoubleQuatRawOpsUnsafe();

    public MemorySegment store(DoubleQuat self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        RAW.storeUnsafe(self, dest.address() + offset);
        return dest;
    }
    public DoubleQuat load(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(offset, src);
        return RAW.loadUnsafe(src.address() + offset);
    }
    public MemorySegment storeFloat(DoubleQuat self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeFloat(self, offset, dest);
        RAW.storeFloatUnsafe(self, dest.address() + offset);
        return dest;
    }
    public DoubleQuat loadFloat(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadFloat(offset, src);
        return RAW.loadFloatUnsafe(src.address() + offset);
    }
}
