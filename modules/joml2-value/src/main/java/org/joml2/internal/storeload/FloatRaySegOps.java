// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class FloatRaySegOps {
    public abstract MemorySegment store(FloatRay self, long offset, MemorySegment dest);
    public abstract FloatRay load(long offset, MemorySegment src);
    public abstract MemorySegment storeDouble(FloatRay self, long offset, MemorySegment dest);
    public abstract FloatRay loadDouble(long offset, MemorySegment src);
}
