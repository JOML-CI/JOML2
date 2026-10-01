// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class Short4SegOps {
    public abstract MemorySegment store(Short4 self, long offset, MemorySegment dest);
    public abstract Short4 load(long offset, MemorySegment src);
    public abstract MemorySegment storeByte(Short4 self, long offset, MemorySegment dest);
    public abstract Short4 loadByte(long offset, MemorySegment src);
}
