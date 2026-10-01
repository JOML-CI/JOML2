// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface Double4x4SegOps {
    MemorySegment storeCM(Double4x4 self, long offset, MemorySegment dest);
    Double4x4 loadCM(long offset, MemorySegment src);
    MemorySegment storeCMFloat(Double4x4 self, long offset, MemorySegment dest);
    Double4x4 loadCMFloat(long offset, MemorySegment src);
    MemorySegment storeRM(Double4x4 self, long offset, MemorySegment dest);
    Double4x4 loadRM(long offset, MemorySegment src);
    MemorySegment storeRMFloat(Double4x4 self, long offset, MemorySegment dest);
    Double4x4 loadRMFloat(long offset, MemorySegment src);
    MemorySegment storeCM(Double4x4 self, long offset, MemorySegment dest, int stride);
    Double4x4 loadCM(long offset, MemorySegment src, int stride);
    MemorySegment storeCMFloat(Double4x4 self, long offset, MemorySegment dest, int stride);
    Double4x4 loadCMFloat(long offset, MemorySegment src, int stride);
    MemorySegment storeRM(Double4x4 self, long offset, MemorySegment dest, int stride);
    Double4x4 loadRM(long offset, MemorySegment src, int stride);
    MemorySegment storeRMFloat(Double4x4 self, long offset, MemorySegment dest, int stride);
    Double4x4 loadRMFloat(long offset, MemorySegment src, int stride);
}
