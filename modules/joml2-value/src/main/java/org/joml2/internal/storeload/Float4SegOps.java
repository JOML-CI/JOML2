// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class Float4SegOps {
    public abstract MemorySegment store(Float4 self, long offset, MemorySegment dest);
    public abstract Float4 load(long offset, MemorySegment src);
    public abstract MemorySegment storeDouble(Float4 self, long offset, MemorySegment dest);
    public abstract Float4 loadDouble(long offset, MemorySegment src);
}
