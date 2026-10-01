// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface DoubleOBBSegOps {
    MemorySegment store(DoubleOBB self, long offset, MemorySegment dest);
    DoubleOBB load(long offset, MemorySegment src);
    MemorySegment storeFloat(DoubleOBB self, long offset, MemorySegment dest);
    DoubleOBB loadFloat(long offset, MemorySegment src);
}
