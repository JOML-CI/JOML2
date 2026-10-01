// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public abstract class Short3SegOps {
    public abstract MemorySegment store(Short3Impl self, long offset, MemorySegment dest);
    public abstract Short3 load(Short3Impl self, long offset, MemorySegment src);
    public abstract MemorySegment storeByte(Short3Impl self, long offset, MemorySegment dest);
    public abstract Short3 loadByte(Short3Impl self, long offset, MemorySegment src);
}
