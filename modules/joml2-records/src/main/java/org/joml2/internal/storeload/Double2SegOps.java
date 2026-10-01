// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface Double2SegOps {
    MemorySegment store(Double2 self, long offset, MemorySegment dest);
    Double2 load(long offset, MemorySegment src);
    MemorySegment storeFloat(Double2 self, long offset, MemorySegment dest);
    Double2 loadFloat(long offset, MemorySegment src);
}
