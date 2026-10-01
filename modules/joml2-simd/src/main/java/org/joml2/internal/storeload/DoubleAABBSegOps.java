// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface DoubleAABBSegOps {
    MemorySegment store(DoubleAABBImpl self, long offset, MemorySegment dest);
    DoubleAABB load(DoubleAABBImpl self, long offset, MemorySegment src);
    MemorySegment storeFloat(DoubleAABBImpl self, long offset, MemorySegment dest);
    DoubleAABB loadFloat(DoubleAABBImpl self, long offset, MemorySegment src);
}
