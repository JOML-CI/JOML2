// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface Float3SegOps {
    MemorySegment store(Float3Impl self, long offset, MemorySegment dest);
    Float3 load(Float3Impl self, long offset, MemorySegment src);
    MemorySegment storeDouble(Float3Impl self, long offset, MemorySegment dest);
    Float3 loadDouble(Float3Impl self, long offset, MemorySegment src);
}
