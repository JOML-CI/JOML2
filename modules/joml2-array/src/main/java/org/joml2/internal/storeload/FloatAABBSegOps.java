// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface FloatAABBSegOps {
    MemorySegment store(FloatAABBImpl self, long offset, MemorySegment dest);
    FloatAABB load(FloatAABBImpl self, long offset, MemorySegment src);
    MemorySegment storeDouble(FloatAABBImpl self, long offset, MemorySegment dest);
    FloatAABB loadDouble(FloatAABBImpl self, long offset, MemorySegment src);
}
