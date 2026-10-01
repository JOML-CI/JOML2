// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface Double2x4SegOps {
    MemorySegment storeCM(Double2x4Impl self, long offset, MemorySegment dest);
    Double2x4 loadCM(Double2x4Impl self, long offset, MemorySegment src);
    MemorySegment storeCMFloat(Double2x4Impl self, long offset, MemorySegment dest);
    Double2x4 loadCMFloat(Double2x4Impl self, long offset, MemorySegment src);
    MemorySegment storeRM(Double2x4Impl self, long offset, MemorySegment dest);
    Double2x4 loadRM(Double2x4Impl self, long offset, MemorySegment src);
    MemorySegment storeRMFloat(Double2x4Impl self, long offset, MemorySegment dest);
    Double2x4 loadRMFloat(Double2x4Impl self, long offset, MemorySegment src);
    MemorySegment storeCM(Double2x4Impl self, long offset, MemorySegment dest, int stride);
    Double2x4 loadCM(Double2x4Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeCMFloat(Double2x4Impl self, long offset, MemorySegment dest, int stride);
    Double2x4 loadCMFloat(Double2x4Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeRM(Double2x4Impl self, long offset, MemorySegment dest, int stride);
    Double2x4 loadRM(Double2x4Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeRMFloat(Double2x4Impl self, long offset, MemorySegment dest, int stride);
    Double2x4 loadRMFloat(Double2x4Impl self, long offset, MemorySegment src, int stride);
}
