// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class FloatRectSegOps {
    public abstract MemorySegment store(FloatRect self, long offset, MemorySegment dest);
    public abstract FloatRect load(long offset, MemorySegment src);
    public abstract MemorySegment storeDouble(FloatRect self, long offset, MemorySegment dest);
    public abstract FloatRect loadDouble(long offset, MemorySegment src);
}
