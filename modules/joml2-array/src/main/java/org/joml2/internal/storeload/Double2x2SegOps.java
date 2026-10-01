// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface Double2x2SegOps {
    MemorySegment storeCM(Double2x2Impl self, long offset, MemorySegment dest);
    Double2x2 loadCM(Double2x2Impl self, long offset, MemorySegment src);
    MemorySegment storeCMFloat(Double2x2Impl self, long offset, MemorySegment dest);
    Double2x2 loadCMFloat(Double2x2Impl self, long offset, MemorySegment src);
    MemorySegment storeRM(Double2x2Impl self, long offset, MemorySegment dest);
    Double2x2 loadRM(Double2x2Impl self, long offset, MemorySegment src);
    MemorySegment storeRMFloat(Double2x2Impl self, long offset, MemorySegment dest);
    Double2x2 loadRMFloat(Double2x2Impl self, long offset, MemorySegment src);
    MemorySegment storeCM(Double2x2Impl self, long offset, MemorySegment dest, int stride);
    Double2x2 loadCM(Double2x2Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeCMFloat(Double2x2Impl self, long offset, MemorySegment dest, int stride);
    Double2x2 loadCMFloat(Double2x2Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeRM(Double2x2Impl self, long offset, MemorySegment dest, int stride);
    Double2x2 loadRM(Double2x2Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeRMFloat(Double2x2Impl self, long offset, MemorySegment dest, int stride);
    Double2x2 loadRMFloat(Double2x2Impl self, long offset, MemorySegment src, int stride);
    MemorySegment storeCM3x3(Double2x2Impl self, long offset, MemorySegment dest);
    MemorySegment storeCM3x3Float(Double2x2Impl self, long offset, MemorySegment dest);
    MemorySegment storeRM3x3(Double2x2Impl self, long offset, MemorySegment dest);
    MemorySegment storeRM3x3Float(Double2x2Impl self, long offset, MemorySegment dest);
    MemorySegment storeCM4x4(Double2x2Impl self, long offset, MemorySegment dest);
    MemorySegment storeCM4x4Float(Double2x2Impl self, long offset, MemorySegment dest);
    MemorySegment storeRM4x4(Double2x2Impl self, long offset, MemorySegment dest);
    MemorySegment storeRM4x4Float(Double2x2Impl self, long offset, MemorySegment dest);
}
