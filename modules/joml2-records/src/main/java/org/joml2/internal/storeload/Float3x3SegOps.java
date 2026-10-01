// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface Float3x3SegOps {
    MemorySegment storeCM(Float3x3 self, long offset, MemorySegment dest);
    Float3x3 loadCM(long offset, MemorySegment src);
    MemorySegment storeCMDouble(Float3x3 self, long offset, MemorySegment dest);
    Float3x3 loadCMDouble(long offset, MemorySegment src);
    MemorySegment storeRM(Float3x3 self, long offset, MemorySegment dest);
    Float3x3 loadRM(long offset, MemorySegment src);
    MemorySegment storeRMDouble(Float3x3 self, long offset, MemorySegment dest);
    Float3x3 loadRMDouble(long offset, MemorySegment src);
    MemorySegment storeCM(Float3x3 self, long offset, MemorySegment dest, int stride);
    Float3x3 loadCM(long offset, MemorySegment src, int stride);
    MemorySegment storeCMDouble(Float3x3 self, long offset, MemorySegment dest, int stride);
    Float3x3 loadCMDouble(long offset, MemorySegment src, int stride);
    MemorySegment storeRM(Float3x3 self, long offset, MemorySegment dest, int stride);
    Float3x3 loadRM(long offset, MemorySegment src, int stride);
    MemorySegment storeRMDouble(Float3x3 self, long offset, MemorySegment dest, int stride);
    Float3x3 loadRMDouble(long offset, MemorySegment src, int stride);
    MemorySegment storeCM4x4(Float3x3 self, long offset, MemorySegment dest);
    MemorySegment storeCM4x4Double(Float3x3 self, long offset, MemorySegment dest);
    MemorySegment storeRM4x4(Float3x3 self, long offset, MemorySegment dest);
    MemorySegment storeRM4x4Double(Float3x3 self, long offset, MemorySegment dest);
}
