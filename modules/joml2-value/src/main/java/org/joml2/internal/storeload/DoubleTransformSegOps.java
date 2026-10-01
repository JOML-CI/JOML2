// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class DoubleTransformSegOps {
    public abstract MemorySegment store(DoubleTransform self, long offset, MemorySegment dest);
    public abstract DoubleTransform load(long offset, MemorySegment src);
    public abstract MemorySegment storeFloat(DoubleTransform self, long offset, MemorySegment dest);
    public abstract DoubleTransform loadFloat(long offset, MemorySegment src);
}
