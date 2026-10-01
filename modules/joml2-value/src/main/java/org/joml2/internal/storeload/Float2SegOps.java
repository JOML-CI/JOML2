// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface Float2SegOps {
    MemorySegment store(Float2 self, long offset, MemorySegment dest);
    Float2 load(long offset, MemorySegment src);
    MemorySegment storeDouble(Float2 self, long offset, MemorySegment dest);
    Float2 loadDouble(long offset, MemorySegment src);
}
