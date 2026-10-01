// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class Byte3SegOps {
    public abstract MemorySegment store(Byte3 self, long offset, MemorySegment dest);
    public abstract Byte3 load(long offset, MemorySegment src);
    public abstract MemorySegment storeShort(Byte3 self, long offset, MemorySegment dest);
    public abstract Byte3 loadShort(long offset, MemorySegment src);
}
