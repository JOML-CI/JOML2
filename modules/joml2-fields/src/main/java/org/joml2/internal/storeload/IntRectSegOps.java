// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public abstract class IntRectSegOps {
    public abstract MemorySegment store(IntRectImpl self, long offset, MemorySegment dest);
    public abstract IntRect load(IntRectImpl self, long offset, MemorySegment src);
    public abstract MemorySegment storeLong(IntRectImpl self, long offset, MemorySegment dest);
    public abstract IntRect loadLong(IntRectImpl self, long offset, MemorySegment src);
}
