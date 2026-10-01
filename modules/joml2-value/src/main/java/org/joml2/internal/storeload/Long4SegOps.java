// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class Long4SegOps {
    public abstract MemorySegment store(Long4 self, long offset, MemorySegment dest);
    public abstract Long4 load(long offset, MemorySegment src);
    public abstract MemorySegment storeInt(Long4 self, long offset, MemorySegment dest);
    public abstract Long4 loadInt(long offset, MemorySegment src);
}
