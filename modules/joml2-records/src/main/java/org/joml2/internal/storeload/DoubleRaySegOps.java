// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class DoubleRaySegOps {
    public abstract MemorySegment store(DoubleRay self, long offset, MemorySegment dest);
    public abstract DoubleRay load(long offset, MemorySegment src);
    public abstract MemorySegment storeFloat(DoubleRay self, long offset, MemorySegment dest);
    public abstract DoubleRay loadFloat(long offset, MemorySegment src);
}
