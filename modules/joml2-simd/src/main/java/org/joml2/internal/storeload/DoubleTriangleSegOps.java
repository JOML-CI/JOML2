// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface DoubleTriangleSegOps {
    MemorySegment store(DoubleTriangleImpl self, long offset, MemorySegment dest);
    DoubleTriangle load(DoubleTriangleImpl self, long offset, MemorySegment src);
    MemorySegment storeFloat(DoubleTriangleImpl self, long offset, MemorySegment dest);
    DoubleTriangle loadFloat(DoubleTriangleImpl self, long offset, MemorySegment src);
}
