// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public final class DoubleQuatSegOpsUnsafe implements DoubleQuatSegOps {

    private static final DoubleQuatSegOpsMS MS = new DoubleQuatSegOpsMS();
    private static final DoubleQuatRawOpsUnsafe RAW = new DoubleQuatRawOpsUnsafe();

    public MemorySegment storeFloat(DoubleQuatImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeFloat(self, offset, dest);
        RAW.storeFloatUnsafe(self, dest.address() + offset);
        return dest;
    }
    public DoubleQuat loadFloat(DoubleQuatImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadFloat(self, offset, src);
        return RAW.loadFloatUnsafe(self, src.address() + offset);
    }
}
