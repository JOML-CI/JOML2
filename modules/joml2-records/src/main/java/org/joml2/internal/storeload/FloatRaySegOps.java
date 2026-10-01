// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface FloatRaySegOps {
    MemorySegment store(FloatRay self, long offset, MemorySegment dest);
    FloatRay load(long offset, MemorySegment src);
    MemorySegment storeDouble(FloatRay self, long offset, MemorySegment dest);
    FloatRay loadDouble(long offset, MemorySegment src);
}
