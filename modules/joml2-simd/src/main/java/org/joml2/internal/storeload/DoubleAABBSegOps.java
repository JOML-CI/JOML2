// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public abstract class DoubleAABBSegOps {
    public abstract MemorySegment store(DoubleAABBImpl self, long offset, MemorySegment dest);
    public abstract DoubleAABB load(DoubleAABBImpl self, long offset, MemorySegment src);
    public abstract MemorySegment storeFloat(DoubleAABBImpl self, long offset, MemorySegment dest);
    public abstract DoubleAABB loadFloat(DoubleAABBImpl self, long offset, MemorySegment src);
}
