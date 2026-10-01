// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface Long2SegOps {
    MemorySegment store(Long2 self, long offset, MemorySegment dest);
    Long2 load(long offset, MemorySegment src);
    MemorySegment storeInt(Long2 self, long offset, MemorySegment dest);
    Long2 loadInt(long offset, MemorySegment src);
}
