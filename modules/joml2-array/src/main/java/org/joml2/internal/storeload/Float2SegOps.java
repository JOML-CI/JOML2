// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface Float2SegOps {
    MemorySegment store(Float2Impl self, long offset, MemorySegment dest);
    Float2 load(Float2Impl self, long offset, MemorySegment src);
    MemorySegment storeDouble(Float2Impl self, long offset, MemorySegment dest);
    Float2 loadDouble(Float2Impl self, long offset, MemorySegment src);
}
