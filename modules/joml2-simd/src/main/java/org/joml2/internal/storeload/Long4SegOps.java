// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public abstract class Long4SegOps {
    public abstract MemorySegment storeInt(Long4Impl self, long offset, MemorySegment dest);
    public abstract Long4 loadInt(Long4Impl self, long offset, MemorySegment src);
    public abstract MemorySegment store(Long4Impl self, long offset, MemorySegment dest);
    public abstract Long4 load(Long4Impl self, long offset, MemorySegment src);
}
