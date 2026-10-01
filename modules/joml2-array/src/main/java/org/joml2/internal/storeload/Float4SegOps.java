// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface Float4SegOps {
    MemorySegment store(Float4Impl self, long offset, MemorySegment dest);
    Float4 load(Float4Impl self, long offset, MemorySegment src);
    MemorySegment storeDouble(Float4Impl self, long offset, MemorySegment dest);
    Float4 loadDouble(Float4Impl self, long offset, MemorySegment src);
}
