// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface FloatAABBSegOps {
    MemorySegment store(FloatAABB self, long offset, MemorySegment dest);
    FloatAABB load(long offset, MemorySegment src);
    MemorySegment storeDouble(FloatAABB self, long offset, MemorySegment dest);
    FloatAABB loadDouble(long offset, MemorySegment src);
}
