// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface Double3x2SegOps {
    MemorySegment storeCM(Double3x2Impl self, long offset, MemorySegment dest);
    Double3x2 loadCM(Double3x2Impl self, long offset, MemorySegment src);
    MemorySegment storeCMFloat(Double3x2Impl self, long offset, MemorySegment dest);
    Double3x2 loadCMFloat(Double3x2Impl self, long offset, MemorySegment src);
    MemorySegment storeRM(Double3x2Impl self, long offset, MemorySegment dest);
    Double3x2 loadRM(Double3x2Impl self, long offset, MemorySegment src);
    MemorySegment storeRMFloat(Double3x2Impl self, long offset, MemorySegment dest);
    Double3x2 loadRMFloat(Double3x2Impl self, long offset, MemorySegment src);
    MemorySegment storeCM(Double3x2Impl self, long offset, MemorySegment dest, int stride);
    Double3x2 loadCM(Double3x2Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeCMFloat(Double3x2Impl self, long offset, MemorySegment dest, int stride);
    Double3x2 loadCMFloat(Double3x2Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeRM(Double3x2Impl self, long offset, MemorySegment dest, int stride);
    Double3x2 loadRM(Double3x2Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeRMFloat(Double3x2Impl self, long offset, MemorySegment dest, int stride);
    Double3x2 loadRMFloat(Double3x2Impl self, long offset, MemorySegment src, int stride);
}
