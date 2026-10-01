// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface IntRectSegOps {
    MemorySegment store(IntRect self, long offset, MemorySegment dest);
    IntRect load(long offset, MemorySegment src);
    MemorySegment storeLong(IntRect self, long offset, MemorySegment dest);
    IntRect loadLong(long offset, MemorySegment src);
}
