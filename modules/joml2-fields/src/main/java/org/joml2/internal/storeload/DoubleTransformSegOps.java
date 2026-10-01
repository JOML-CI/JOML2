// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public abstract class DoubleTransformSegOps {
    public abstract MemorySegment store(DoubleTransformImpl self, long offset, MemorySegment dest);
    public abstract DoubleTransform load(DoubleTransformImpl self, long offset, MemorySegment src);
    public abstract MemorySegment storeFloat(DoubleTransformImpl self, long offset, MemorySegment dest);
    public abstract DoubleTransform loadFloat(DoubleTransformImpl self, long offset, MemorySegment src);
}
