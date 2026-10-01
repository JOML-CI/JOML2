// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface Short4SegOps {
    MemorySegment store(Short4 self, long offset, MemorySegment dest);
    Short4 load(long offset, MemorySegment src);
    MemorySegment storeByte(Short4 self, long offset, MemorySegment dest);
    Short4 loadByte(long offset, MemorySegment src);
}
