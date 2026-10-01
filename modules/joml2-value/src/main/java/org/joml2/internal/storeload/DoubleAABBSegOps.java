// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface DoubleAABBSegOps {
    MemorySegment store(DoubleAABB self, long offset, MemorySegment dest);
    DoubleAABB load(long offset, MemorySegment src);
    MemorySegment storeFloat(DoubleAABB self, long offset, MemorySegment dest);
    DoubleAABB loadFloat(long offset, MemorySegment src);
}
