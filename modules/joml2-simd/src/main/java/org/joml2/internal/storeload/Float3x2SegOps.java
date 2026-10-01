// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface Float3x2SegOps {
    MemorySegment storeCM(Float3x2Impl self, long offset, MemorySegment dest);
    Float3x2 loadCM(Float3x2Impl self, long offset, MemorySegment src);
    MemorySegment storeCMDouble(Float3x2Impl self, long offset, MemorySegment dest);
    Float3x2 loadCMDouble(Float3x2Impl self, long offset, MemorySegment src);
    MemorySegment storeRM(Float3x2Impl self, long offset, MemorySegment dest);
    Float3x2 loadRM(Float3x2Impl self, long offset, MemorySegment src);
    MemorySegment storeRMDouble(Float3x2Impl self, long offset, MemorySegment dest);
    Float3x2 loadRMDouble(Float3x2Impl self, long offset, MemorySegment src);
    MemorySegment storeCM(Float3x2Impl self, long offset, MemorySegment dest, int stride);
    Float3x2 loadCM(Float3x2Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeCMDouble(Float3x2Impl self, long offset, MemorySegment dest, int stride);
    Float3x2 loadCMDouble(Float3x2Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeRM(Float3x2Impl self, long offset, MemorySegment dest, int stride);
    Float3x2 loadRM(Float3x2Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeRMDouble(Float3x2Impl self, long offset, MemorySegment dest, int stride);
    Float3x2 loadRMDouble(Float3x2Impl self, long offset, MemorySegment src, int stride);
}
