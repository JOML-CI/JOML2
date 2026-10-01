// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public abstract class Double2SegOps {
    public abstract MemorySegment store(Double2Impl self, long offset, MemorySegment dest);
    public abstract Double2 load(Double2Impl self, long offset, MemorySegment src);
    public abstract MemorySegment storeFloat(Double2Impl self, long offset, MemorySegment dest);
    public abstract Double2 loadFloat(Double2Impl self, long offset, MemorySegment src);
}
