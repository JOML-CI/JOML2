// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public abstract class DoubleTriangleSegOps {
    public abstract MemorySegment store(DoubleTriangleImpl self, long offset, MemorySegment dest);
    public abstract DoubleTriangle load(DoubleTriangleImpl self, long offset, MemorySegment src);
    public abstract MemorySegment storeFloat(DoubleTriangleImpl self, long offset, MemorySegment dest);
    public abstract DoubleTriangle loadFloat(DoubleTriangleImpl self, long offset, MemorySegment src);
}
