// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface Byte4SegOps {
    MemorySegment store(Byte4 self, long offset, MemorySegment dest);
    Byte4 load(long offset, MemorySegment src);
    MemorySegment storeShort(Byte4 self, long offset, MemorySegment dest);
    Byte4 loadShort(long offset, MemorySegment src);
}
