// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public abstract class Int2SegOps {
    public abstract MemorySegment store(Int2Impl self, long offset, MemorySegment dest);
    public abstract Int2 load(Int2Impl self, long offset, MemorySegment src);
    public abstract MemorySegment storeLong(Int2Impl self, long offset, MemorySegment dest);
    public abstract Int2 loadLong(Int2Impl self, long offset, MemorySegment src);
}
