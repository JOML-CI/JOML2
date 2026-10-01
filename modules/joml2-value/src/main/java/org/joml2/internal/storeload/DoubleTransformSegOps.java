// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface DoubleTransformSegOps {
    MemorySegment store(DoubleTransform self, long offset, MemorySegment dest);
    DoubleTransform load(long offset, MemorySegment src);
    MemorySegment storeFloat(DoubleTransform self, long offset, MemorySegment dest);
    DoubleTransform loadFloat(long offset, MemorySegment src);
}
