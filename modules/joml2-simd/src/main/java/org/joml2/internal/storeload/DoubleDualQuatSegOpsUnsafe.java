// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public final class DoubleDualQuatSegOpsUnsafe implements DoubleDualQuatSegOps {

    private static final DoubleDualQuatSegOpsMS MS = new DoubleDualQuatSegOpsMS();
    private static final DoubleDualQuatRawOpsUnsafe RAW = new DoubleDualQuatRawOpsUnsafe();

    public MemorySegment store(DoubleDualQuatImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.store(self, offset, dest);
        RAW.storeUnsafe(self, dest.address() + offset);
        return dest;
    }
    public DoubleDualQuat load(DoubleDualQuatImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.load(self, offset, src);
        return RAW.loadUnsafe(self, src.address() + offset);
    }
    public MemorySegment storeFloat(DoubleDualQuatImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeFloat(self, offset, dest);
        RAW.storeFloatUnsafe(self, dest.address() + offset);
        return dest;
    }
    public DoubleDualQuat loadFloat(DoubleDualQuatImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadFloat(self, offset, src);
        return RAW.loadFloatUnsafe(self, src.address() + offset);
    }
}
