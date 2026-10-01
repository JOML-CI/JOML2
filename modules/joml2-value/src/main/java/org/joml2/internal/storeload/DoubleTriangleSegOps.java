// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface DoubleTriangleSegOps {
    MemorySegment store(DoubleTriangle self, long offset, MemorySegment dest);
    DoubleTriangle load(long offset, MemorySegment src);
    MemorySegment storeFloat(DoubleTriangle self, long offset, MemorySegment dest);
    DoubleTriangle loadFloat(long offset, MemorySegment src);
}
