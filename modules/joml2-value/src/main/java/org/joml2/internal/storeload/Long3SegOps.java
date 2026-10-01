// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface Long3SegOps {
    MemorySegment store(Long3 self, long offset, MemorySegment dest);
    Long3 load(long offset, MemorySegment src);
    MemorySegment storeInt(Long3 self, long offset, MemorySegment dest);
    Long3 loadInt(long offset, MemorySegment src);
}
