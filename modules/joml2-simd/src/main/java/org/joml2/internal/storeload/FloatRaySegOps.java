// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public abstract class FloatRaySegOps {
    public abstract MemorySegment store(FloatRayImpl self, long offset, MemorySegment dest);
    public abstract FloatRay load(FloatRayImpl self, long offset, MemorySegment src);
    public abstract MemorySegment storeDouble(FloatRayImpl self, long offset, MemorySegment dest);
    public abstract FloatRay loadDouble(FloatRayImpl self, long offset, MemorySegment src);
}
