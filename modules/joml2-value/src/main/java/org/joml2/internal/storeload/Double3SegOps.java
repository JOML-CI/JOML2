// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class Double3SegOps {
    public abstract MemorySegment store(Double3 self, long offset, MemorySegment dest);
    public abstract Double3 load(long offset, MemorySegment src);
    public abstract MemorySegment storeFloat(Double3 self, long offset, MemorySegment dest);
    public abstract Double3 loadFloat(long offset, MemorySegment src);
}
