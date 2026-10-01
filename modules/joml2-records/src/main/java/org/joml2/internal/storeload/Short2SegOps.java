// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class Short2SegOps {
    public abstract MemorySegment store(Short2 self, long offset, MemorySegment dest);
    public abstract Short2 load(long offset, MemorySegment src);
    public abstract MemorySegment storeByte(Short2 self, long offset, MemorySegment dest);
    public abstract Short2 loadByte(long offset, MemorySegment src);
}
