// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class IntRectSegOps {
    public abstract MemorySegment store(IntRect self, long offset, MemorySegment dest);
    public abstract IntRect load(long offset, MemorySegment src);
    public abstract MemorySegment storeLong(IntRect self, long offset, MemorySegment dest);
    public abstract IntRect loadLong(long offset, MemorySegment src);
}
