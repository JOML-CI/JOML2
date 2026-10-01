// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface FloatTransformSegOps {
    MemorySegment store(FloatTransform self, long offset, MemorySegment dest);
    FloatTransform load(long offset, MemorySegment src);
    MemorySegment storeDouble(FloatTransform self, long offset, MemorySegment dest);
    FloatTransform loadDouble(long offset, MemorySegment src);
}
