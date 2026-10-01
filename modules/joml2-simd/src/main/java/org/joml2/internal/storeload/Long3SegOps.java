// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface Long3SegOps {
    MemorySegment store(Long3Impl self, long offset, MemorySegment dest);
    Long3 load(Long3Impl self, long offset, MemorySegment src);
    MemorySegment storeInt(Long3Impl self, long offset, MemorySegment dest);
    Long3 loadInt(Long3Impl self, long offset, MemorySegment src);
}
