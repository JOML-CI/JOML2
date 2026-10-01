// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class FloatTransformSegOps {
    public abstract MemorySegment store(FloatTransform self, long offset, MemorySegment dest);
    public abstract FloatTransform load(long offset, MemorySegment src);
    public abstract MemorySegment storeDouble(FloatTransform self, long offset, MemorySegment dest);
    public abstract FloatTransform loadDouble(long offset, MemorySegment src);
}
