// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class Byte2SegOps {
    public abstract MemorySegment store(Byte2 self, long offset, MemorySegment dest);
    public abstract Byte2 load(long offset, MemorySegment src);
    public abstract MemorySegment storeShort(Byte2 self, long offset, MemorySegment dest);
    public abstract Byte2 loadShort(long offset, MemorySegment src);
}
