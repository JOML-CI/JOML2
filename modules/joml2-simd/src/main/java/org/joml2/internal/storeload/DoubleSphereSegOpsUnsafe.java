// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public final class DoubleSphereSegOpsUnsafe implements DoubleSphereSegOps {

    private static final DoubleSphereSegOpsMS MS = new DoubleSphereSegOpsMS();
    private static final DoubleSphereRawOpsUnsafe RAW = new DoubleSphereRawOpsUnsafe();

    public MemorySegment storeFloat(DoubleSphereImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeFloat(self, offset, dest);
        RAW.storeFloatUnsafe(self, dest.address() + offset);
        return dest;
    }
    public DoubleSphere loadFloat(DoubleSphereImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadFloat(self, offset, src);
        return RAW.loadFloatUnsafe(self, src.address() + offset);
    }
}
