// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface Double3x3SegOps {
    MemorySegment storeCM(Double3x3Impl self, long offset, MemorySegment dest);
    Double3x3 loadCM(Double3x3Impl self, long offset, MemorySegment src);
    MemorySegment storeCMFloat(Double3x3Impl self, long offset, MemorySegment dest);
    Double3x3 loadCMFloat(Double3x3Impl self, long offset, MemorySegment src);
    MemorySegment storeRM(Double3x3Impl self, long offset, MemorySegment dest);
    Double3x3 loadRM(Double3x3Impl self, long offset, MemorySegment src);
    MemorySegment storeRMFloat(Double3x3Impl self, long offset, MemorySegment dest);
    Double3x3 loadRMFloat(Double3x3Impl self, long offset, MemorySegment src);
    MemorySegment storeCM(Double3x3Impl self, long offset, MemorySegment dest, int stride);
    Double3x3 loadCM(Double3x3Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeCMFloat(Double3x3Impl self, long offset, MemorySegment dest, int stride);
    Double3x3 loadCMFloat(Double3x3Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeRM(Double3x3Impl self, long offset, MemorySegment dest, int stride);
    Double3x3 loadRM(Double3x3Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeRMFloat(Double3x3Impl self, long offset, MemorySegment dest, int stride);
    Double3x3 loadRMFloat(Double3x3Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeCM4x4(Double3x3Impl self, long offset, MemorySegment dest);
    MemorySegment storeCM4x4Float(Double3x3Impl self, long offset, MemorySegment dest);
    MemorySegment storeRM4x4(Double3x3Impl self, long offset, MemorySegment dest);
    MemorySegment storeRM4x4Float(Double3x3Impl self, long offset, MemorySegment dest);
}
