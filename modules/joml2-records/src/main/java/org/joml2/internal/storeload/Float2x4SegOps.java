// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface Float2x4SegOps {
    MemorySegment storeCM(Float2x4 self, long offset, MemorySegment dest);
    Float2x4 loadCM(long offset, MemorySegment src);
    MemorySegment storeCMDouble(Float2x4 self, long offset, MemorySegment dest);
    Float2x4 loadCMDouble(long offset, MemorySegment src);
    MemorySegment storeRM(Float2x4 self, long offset, MemorySegment dest);
    Float2x4 loadRM(long offset, MemorySegment src);
    MemorySegment storeRMDouble(Float2x4 self, long offset, MemorySegment dest);
    Float2x4 loadRMDouble(long offset, MemorySegment src);
    MemorySegment storeCM(Float2x4 self, long offset, MemorySegment dest, int stride);
    Float2x4 loadCM(long offset, MemorySegment src, int stride);
    MemorySegment storeCMDouble(Float2x4 self, long offset, MemorySegment dest, int stride);
    Float2x4 loadCMDouble(long offset, MemorySegment src, int stride);
    MemorySegment storeRM(Float2x4 self, long offset, MemorySegment dest, int stride);
    Float2x4 loadRM(long offset, MemorySegment src, int stride);
    MemorySegment storeRMDouble(Float2x4 self, long offset, MemorySegment dest, int stride);
    Float2x4 loadRMDouble(long offset, MemorySegment src, int stride);
}
