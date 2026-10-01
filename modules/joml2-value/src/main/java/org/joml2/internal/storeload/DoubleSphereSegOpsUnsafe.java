// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public final class DoubleSphereSegOpsUnsafe implements DoubleSphereSegOps {

    private static final DoubleSphereSegOpsMS MS = new DoubleSphereSegOpsMS();
    private static final DoubleSphereRawOpsUnsafe RAW = new DoubleSphereRawOpsUnsafe();

    public MemorySegment store(DoubleSphere self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        RAW.storeUnsafe(self, dest.address() + offset);
        return dest;
    }
    public DoubleSphere load(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(offset, src);
        return RAW.loadUnsafe(src.address() + offset);
    }
    public MemorySegment storeFloat(DoubleSphere self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeFloat(self, offset, dest);
        RAW.storeFloatUnsafe(self, dest.address() + offset);
        return dest;
    }
    public DoubleSphere loadFloat(long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadFloat(offset, src);
        return RAW.loadFloatUnsafe(src.address() + offset);
    }
}
