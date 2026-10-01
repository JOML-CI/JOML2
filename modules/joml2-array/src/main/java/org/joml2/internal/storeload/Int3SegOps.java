// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface Int3SegOps {
    MemorySegment store(Int3Impl self, long offset, MemorySegment dest);
    Int3 load(Int3Impl self, long offset, MemorySegment src);
    MemorySegment storeLong(Int3Impl self, long offset, MemorySegment dest);
    Int3 loadLong(Int3Impl self, long offset, MemorySegment src);
}
