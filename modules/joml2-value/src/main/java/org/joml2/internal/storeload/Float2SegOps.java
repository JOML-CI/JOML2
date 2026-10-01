// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class Float2SegOps {
    public abstract MemorySegment store(Float2 self, long offset, MemorySegment dest);
    public abstract Float2 load(long offset, MemorySegment src);
    public abstract MemorySegment storeDouble(Float2 self, long offset, MemorySegment dest);
    public abstract Float2 loadDouble(long offset, MemorySegment src);
}
