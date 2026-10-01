// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface Double2SegOps {
    MemorySegment store(Double2Impl self, long offset, MemorySegment dest);
    Double2 load(Double2Impl self, long offset, MemorySegment src);
    MemorySegment storeFloat(Double2Impl self, long offset, MemorySegment dest);
    Double2 loadFloat(Double2Impl self, long offset, MemorySegment src);
}
