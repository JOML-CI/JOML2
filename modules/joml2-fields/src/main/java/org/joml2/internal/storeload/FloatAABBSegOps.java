// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public abstract class FloatAABBSegOps {
    public abstract MemorySegment store(FloatAABBImpl self, long offset, MemorySegment dest);
    public abstract FloatAABB load(FloatAABBImpl self, long offset, MemorySegment src);
    public abstract MemorySegment storeDouble(FloatAABBImpl self, long offset, MemorySegment dest);
    public abstract FloatAABB loadDouble(FloatAABBImpl self, long offset, MemorySegment src);
}
