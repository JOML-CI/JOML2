// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public abstract class DoubleRectSegOps {
    public abstract MemorySegment store(DoubleRectImpl self, long offset, MemorySegment dest);
    public abstract DoubleRect load(DoubleRectImpl self, long offset, MemorySegment src);
    public abstract MemorySegment storeFloat(DoubleRectImpl self, long offset, MemorySegment dest);
    public abstract DoubleRect loadFloat(DoubleRectImpl self, long offset, MemorySegment src);
}
