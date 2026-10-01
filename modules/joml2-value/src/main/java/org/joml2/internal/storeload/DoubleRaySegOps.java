// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface DoubleRaySegOps {
    MemorySegment store(DoubleRay self, long offset, MemorySegment dest);
    DoubleRay load(long offset, MemorySegment src);
    MemorySegment storeFloat(DoubleRay self, long offset, MemorySegment dest);
    DoubleRay loadFloat(long offset, MemorySegment src);
}
