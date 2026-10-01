// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface Float3x2SegOps {
    MemorySegment storeCM(Float3x2 self, long offset, MemorySegment dest);
    Float3x2 loadCM(long offset, MemorySegment src);
    MemorySegment storeCMDouble(Float3x2 self, long offset, MemorySegment dest);
    Float3x2 loadCMDouble(long offset, MemorySegment src);
    MemorySegment storeRM(Float3x2 self, long offset, MemorySegment dest);
    Float3x2 loadRM(long offset, MemorySegment src);
    MemorySegment storeRMDouble(Float3x2 self, long offset, MemorySegment dest);
    Float3x2 loadRMDouble(long offset, MemorySegment src);
    MemorySegment storeCM(Float3x2 self, long offset, MemorySegment dest, int stride);
    Float3x2 loadCM(long offset, MemorySegment src, int stride);
    MemorySegment storeCMDouble(Float3x2 self, long offset, MemorySegment dest, int stride);
    Float3x2 loadCMDouble(long offset, MemorySegment src, int stride);
    MemorySegment storeRM(Float3x2 self, long offset, MemorySegment dest, int stride);
    Float3x2 loadRM(long offset, MemorySegment src, int stride);
    MemorySegment storeRMDouble(Float3x2 self, long offset, MemorySegment dest, int stride);
    Float3x2 loadRMDouble(long offset, MemorySegment src, int stride);
}
