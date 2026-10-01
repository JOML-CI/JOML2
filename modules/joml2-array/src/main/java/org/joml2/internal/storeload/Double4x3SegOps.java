// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface Double4x3SegOps {
    MemorySegment storeCM(Double4x3Impl self, long offset, MemorySegment dest);
    Double4x3 loadCM(Double4x3Impl self, long offset, MemorySegment src);
    MemorySegment storeCMFloat(Double4x3Impl self, long offset, MemorySegment dest);
    Double4x3 loadCMFloat(Double4x3Impl self, long offset, MemorySegment src);
    MemorySegment storeRM(Double4x3Impl self, long offset, MemorySegment dest);
    Double4x3 loadRM(Double4x3Impl self, long offset, MemorySegment src);
    MemorySegment storeRMFloat(Double4x3Impl self, long offset, MemorySegment dest);
    Double4x3 loadRMFloat(Double4x3Impl self, long offset, MemorySegment src);
    MemorySegment storeCM(Double4x3Impl self, long offset, MemorySegment dest, int stride);
    Double4x3 loadCM(Double4x3Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeCMFloat(Double4x3Impl self, long offset, MemorySegment dest, int stride);
    Double4x3 loadCMFloat(Double4x3Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeRM(Double4x3Impl self, long offset, MemorySegment dest, int stride);
    Double4x3 loadRM(Double4x3Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeRMFloat(Double4x3Impl self, long offset, MemorySegment dest, int stride);
    Double4x3 loadRMFloat(Double4x3Impl self, long offset, MemorySegment src, int stride);
}
