// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface Long4SegOps {
    MemorySegment store(Long4 self, long offset, MemorySegment dest);
    Long4 load(long offset, MemorySegment src);
    MemorySegment storeInt(Long4 self, long offset, MemorySegment dest);
    Long4 loadInt(long offset, MemorySegment src);
}
