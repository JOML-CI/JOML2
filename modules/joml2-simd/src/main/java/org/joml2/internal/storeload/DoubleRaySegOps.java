// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface DoubleRaySegOps {
    MemorySegment store(DoubleRayImpl self, long offset, MemorySegment dest);
    DoubleRay load(DoubleRayImpl self, long offset, MemorySegment src);
    MemorySegment storeFloat(DoubleRayImpl self, long offset, MemorySegment dest);
    DoubleRay loadFloat(DoubleRayImpl self, long offset, MemorySegment src);
}
