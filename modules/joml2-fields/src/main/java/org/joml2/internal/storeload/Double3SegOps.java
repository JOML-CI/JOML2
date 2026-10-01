// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface Double3SegOps {
    MemorySegment store(Double3Impl self, long offset, MemorySegment dest);
    Double3 load(Double3Impl self, long offset, MemorySegment src);
    MemorySegment storeFloat(Double3Impl self, long offset, MemorySegment dest);
    Double3 loadFloat(Double3Impl self, long offset, MemorySegment src);
}
