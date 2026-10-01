// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface Double4SegOps {
    MemorySegment store(Double4 self, long offset, MemorySegment dest);
    Double4 load(long offset, MemorySegment src);
    MemorySegment storeFloat(Double4 self, long offset, MemorySegment dest);
    Double4 loadFloat(long offset, MemorySegment src);
}
