// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface Int2SegOps {
    MemorySegment store(Int2 self, long offset, MemorySegment dest);
    Int2 load(long offset, MemorySegment src);
    MemorySegment storeLong(Int2 self, long offset, MemorySegment dest);
    Int2 loadLong(long offset, MemorySegment src);
}
