// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public abstract class Long2SegOps {
    public abstract MemorySegment store(Long2Impl self, long offset, MemorySegment dest);
    public abstract Long2 load(Long2Impl self, long offset, MemorySegment src);
    public abstract MemorySegment storeInt(Long2Impl self, long offset, MemorySegment dest);
    public abstract Long2 loadInt(Long2Impl self, long offset, MemorySegment src);
}
