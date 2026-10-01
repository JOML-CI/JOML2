// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public abstract class FloatTriangleSegOps {
    public abstract MemorySegment store(FloatTriangleImpl self, long offset, MemorySegment dest);
    public abstract FloatTriangle load(FloatTriangleImpl self, long offset, MemorySegment src);
    public abstract MemorySegment storeDouble(FloatTriangleImpl self, long offset, MemorySegment dest);
    public abstract FloatTriangle loadDouble(FloatTriangleImpl self, long offset, MemorySegment src);
}
