// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public abstract class Double4SegOps {
    public abstract MemorySegment store(Double4Impl self, long offset, MemorySegment dest);
    public abstract Double4 load(Double4Impl self, long offset, MemorySegment src);
    public abstract MemorySegment storeFloat(Double4Impl self, long offset, MemorySegment dest);
    public abstract Double4 loadFloat(Double4Impl self, long offset, MemorySegment src);
}
