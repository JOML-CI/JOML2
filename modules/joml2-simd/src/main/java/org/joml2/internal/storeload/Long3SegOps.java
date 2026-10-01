// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public abstract class Long3SegOps {
    public abstract MemorySegment store(Long3Impl self, long offset, MemorySegment dest);
    public abstract Long3 load(Long3Impl self, long offset, MemorySegment src);
    public abstract MemorySegment storeInt(Long3Impl self, long offset, MemorySegment dest);
    public abstract Long3 loadInt(Long3Impl self, long offset, MemorySegment src);
}
