// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface Int4SegOps {
    MemorySegment store(Int4 self, long offset, MemorySegment dest);
    Int4 load(long offset, MemorySegment src);
    MemorySegment storeLong(Int4 self, long offset, MemorySegment dest);
    Int4 loadLong(long offset, MemorySegment src);
}
