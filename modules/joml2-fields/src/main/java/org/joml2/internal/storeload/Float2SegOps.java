// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public abstract class Float2SegOps {
    public abstract MemorySegment store(Float2Impl self, long offset, MemorySegment dest);
    public abstract Float2 load(Float2Impl self, long offset, MemorySegment src);
    public abstract MemorySegment storeDouble(Float2Impl self, long offset, MemorySegment dest);
    public abstract Float2 loadDouble(Float2Impl self, long offset, MemorySegment src);
}
