// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class Int2SegOps {
    public abstract MemorySegment store(Int2 self, long offset, MemorySegment dest);
    public abstract Int2 load(long offset, MemorySegment src);
    public abstract MemorySegment storeLong(Int2 self, long offset, MemorySegment dest);
    public abstract Int2 loadLong(long offset, MemorySegment src);
}
