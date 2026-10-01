// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface Float2x3SegOps {
    MemorySegment storeCM(Float2x3Impl self, long offset, MemorySegment dest);
    Float2x3 loadCM(Float2x3Impl self, long offset, MemorySegment src);
    MemorySegment storeCMDouble(Float2x3Impl self, long offset, MemorySegment dest);
    Float2x3 loadCMDouble(Float2x3Impl self, long offset, MemorySegment src);
    MemorySegment storeRM(Float2x3Impl self, long offset, MemorySegment dest);
    Float2x3 loadRM(Float2x3Impl self, long offset, MemorySegment src);
    MemorySegment storeRMDouble(Float2x3Impl self, long offset, MemorySegment dest);
    Float2x3 loadRMDouble(Float2x3Impl self, long offset, MemorySegment src);
    MemorySegment storeCM(Float2x3Impl self, long offset, MemorySegment dest, int stride);
    Float2x3 loadCM(Float2x3Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeCMDouble(Float2x3Impl self, long offset, MemorySegment dest, int stride);
    Float2x3 loadCMDouble(Float2x3Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeRM(Float2x3Impl self, long offset, MemorySegment dest, int stride);
    Float2x3 loadRM(Float2x3Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeRMDouble(Float2x3Impl self, long offset, MemorySegment dest, int stride);
    Float2x3 loadRMDouble(Float2x3Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeCM3x3(Float2x3Impl self, long offset, MemorySegment dest);
    MemorySegment storeCM3x3Double(Float2x3Impl self, long offset, MemorySegment dest);
    MemorySegment storeRM3x3(Float2x3Impl self, long offset, MemorySegment dest);
    MemorySegment storeRM3x3Double(Float2x3Impl self, long offset, MemorySegment dest);
    MemorySegment storeCM4x4(Float2x3Impl self, long offset, MemorySegment dest);
    MemorySegment storeCM4x4Double(Float2x3Impl self, long offset, MemorySegment dest);
    MemorySegment storeRM4x4(Float2x3Impl self, long offset, MemorySegment dest);
    MemorySegment storeRM4x4Double(Float2x3Impl self, long offset, MemorySegment dest);
}
