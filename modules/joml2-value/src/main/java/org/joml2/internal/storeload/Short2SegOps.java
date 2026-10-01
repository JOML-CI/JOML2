// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface Short2SegOps {
    MemorySegment store(Short2 self, long offset, MemorySegment dest);
    Short2 load(long offset, MemorySegment src);
    MemorySegment storeByte(Short2 self, long offset, MemorySegment dest);
    Short2 loadByte(long offset, MemorySegment src);
}
