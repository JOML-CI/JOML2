// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface Int3SegOps {
    MemorySegment store(Int3 self, long offset, MemorySegment dest);
    Int3 load(long offset, MemorySegment src);
    MemorySegment storeLong(Int3 self, long offset, MemorySegment dest);
    Int3 loadLong(long offset, MemorySegment src);
}
