// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public final class FloatSphereSegOpsUnsafe implements FloatSphereSegOps {

    private static final FloatSphereSegOpsMS MS = new FloatSphereSegOpsMS();
    private static final FloatSphereRawOpsUnsafe RAW = new FloatSphereRawOpsUnsafe();

    public MemorySegment storeDouble(FloatSphereImpl self, long offset, MemorySegment dest) {
        if (!dest.isNative() || dest.isReadOnly()) return MS.storeDouble(self, offset, dest);
        RAW.storeDoubleUnsafe(self, dest.address() + offset);
        return dest;
    }
    public FloatSphere loadDouble(FloatSphereImpl self, long offset, MemorySegment src) {
        if (!src.isNative()) return MS.loadDouble(self, offset, src);
        return RAW.loadDoubleUnsafe(self, src.address() + offset);
    }
}
