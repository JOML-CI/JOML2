// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface Float4x4SegOps {
    MemorySegment storeCMDouble(Float4x4Impl self, long offset, MemorySegment dest);
    Float4x4 loadCMDouble(Float4x4Impl self, long offset, MemorySegment src);
    MemorySegment storeRM(Float4x4Impl self, long offset, MemorySegment dest);
    Float4x4 loadRM(Float4x4Impl self, long offset, MemorySegment src);
    MemorySegment storeRMDouble(Float4x4Impl self, long offset, MemorySegment dest);
    Float4x4 loadRMDouble(Float4x4Impl self, long offset, MemorySegment src);
    MemorySegment storeCM(Float4x4Impl self, long offset, MemorySegment dest, int stride);
    Float4x4 loadCM(Float4x4Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeCMDouble(Float4x4Impl self, long offset, MemorySegment dest, int stride);
    Float4x4 loadCMDouble(Float4x4Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeRM(Float4x4Impl self, long offset, MemorySegment dest, int stride);
    Float4x4 loadRM(Float4x4Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeRMDouble(Float4x4Impl self, long offset, MemorySegment dest, int stride);
    Float4x4 loadRMDouble(Float4x4Impl self, long offset, MemorySegment src, int stride);
}
