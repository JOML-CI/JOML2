// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface Double4x2SegOps {
    MemorySegment storeCM(Double4x2Impl self, long offset, MemorySegment dest);
    Double4x2 loadCM(Double4x2Impl self, long offset, MemorySegment src);
    MemorySegment storeCMFloat(Double4x2Impl self, long offset, MemorySegment dest);
    Double4x2 loadCMFloat(Double4x2Impl self, long offset, MemorySegment src);
    MemorySegment storeRM(Double4x2Impl self, long offset, MemorySegment dest);
    Double4x2 loadRM(Double4x2Impl self, long offset, MemorySegment src);
    MemorySegment storeRMFloat(Double4x2Impl self, long offset, MemorySegment dest);
    Double4x2 loadRMFloat(Double4x2Impl self, long offset, MemorySegment src);
    MemorySegment storeCM(Double4x2Impl self, long offset, MemorySegment dest, int stride);
    Double4x2 loadCM(Double4x2Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeCMFloat(Double4x2Impl self, long offset, MemorySegment dest, int stride);
    Double4x2 loadCMFloat(Double4x2Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeRM(Double4x2Impl self, long offset, MemorySegment dest, int stride);
    Double4x2 loadRM(Double4x2Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeRMFloat(Double4x2Impl self, long offset, MemorySegment dest, int stride);
    Double4x2 loadRMFloat(Double4x2Impl self, long offset, MemorySegment src, int stride);
}
