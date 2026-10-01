// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public abstract class FloatTransformSegOps {
    public abstract MemorySegment store(FloatTransformImpl self, long offset, MemorySegment dest);
    public abstract FloatTransform load(FloatTransformImpl self, long offset, MemorySegment src);
    public abstract MemorySegment storeDouble(FloatTransformImpl self, long offset, MemorySegment dest);
    public abstract FloatTransform loadDouble(FloatTransformImpl self, long offset, MemorySegment src);
}
