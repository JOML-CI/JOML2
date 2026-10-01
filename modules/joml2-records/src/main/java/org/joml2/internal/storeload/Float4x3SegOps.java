// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface Float4x3SegOps {
    MemorySegment storeCM(Float4x3 self, long offset, MemorySegment dest);
    Float4x3 loadCM(long offset, MemorySegment src);
    MemorySegment storeCMDouble(Float4x3 self, long offset, MemorySegment dest);
    Float4x3 loadCMDouble(long offset, MemorySegment src);
    MemorySegment storeRM(Float4x3 self, long offset, MemorySegment dest);
    Float4x3 loadRM(long offset, MemorySegment src);
    MemorySegment storeRMDouble(Float4x3 self, long offset, MemorySegment dest);
    Float4x3 loadRMDouble(long offset, MemorySegment src);
    MemorySegment storeCM(Float4x3 self, long offset, MemorySegment dest, int stride);
    Float4x3 loadCM(long offset, MemorySegment src, int stride);
    MemorySegment storeCMDouble(Float4x3 self, long offset, MemorySegment dest, int stride);
    Float4x3 loadCMDouble(long offset, MemorySegment src, int stride);
    MemorySegment storeRM(Float4x3 self, long offset, MemorySegment dest, int stride);
    Float4x3 loadRM(long offset, MemorySegment src, int stride);
    MemorySegment storeRMDouble(Float4x3 self, long offset, MemorySegment dest, int stride);
    Float4x3 loadRMDouble(long offset, MemorySegment src, int stride);
}
