// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface Float3x4SegOps {
    MemorySegment storeCM(Float3x4Impl self, long offset, MemorySegment dest);
    Float3x4 loadCM(Float3x4Impl self, long offset, MemorySegment src);
    MemorySegment storeCMDouble(Float3x4Impl self, long offset, MemorySegment dest);
    Float3x4 loadCMDouble(Float3x4Impl self, long offset, MemorySegment src);
    MemorySegment storeRM(Float3x4Impl self, long offset, MemorySegment dest);
    Float3x4 loadRM(Float3x4Impl self, long offset, MemorySegment src);
    MemorySegment storeRMDouble(Float3x4Impl self, long offset, MemorySegment dest);
    Float3x4 loadRMDouble(Float3x4Impl self, long offset, MemorySegment src);
    MemorySegment storeCM(Float3x4Impl self, long offset, MemorySegment dest, int stride);
    Float3x4 loadCM(Float3x4Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeCMDouble(Float3x4Impl self, long offset, MemorySegment dest, int stride);
    Float3x4 loadCMDouble(Float3x4Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeRM(Float3x4Impl self, long offset, MemorySegment dest, int stride);
    Float3x4 loadRM(Float3x4Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeRMDouble(Float3x4Impl self, long offset, MemorySegment dest, int stride);
    Float3x4 loadRMDouble(Float3x4Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeCM4x4(Float3x4Impl self, long offset, MemorySegment dest);
    MemorySegment storeCM4x4Double(Float3x4Impl self, long offset, MemorySegment dest);
    MemorySegment storeRM4x4(Float3x4Impl self, long offset, MemorySegment dest);
    MemorySegment storeRM4x4Double(Float3x4Impl self, long offset, MemorySegment dest);
}
