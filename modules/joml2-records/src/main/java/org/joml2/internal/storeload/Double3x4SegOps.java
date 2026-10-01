// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface Double3x4SegOps {
    MemorySegment storeCM(Double3x4 self, long offset, MemorySegment dest);
    Double3x4 loadCM(long offset, MemorySegment src);
    MemorySegment storeCMFloat(Double3x4 self, long offset, MemorySegment dest);
    Double3x4 loadCMFloat(long offset, MemorySegment src);
    MemorySegment storeRM(Double3x4 self, long offset, MemorySegment dest);
    Double3x4 loadRM(long offset, MemorySegment src);
    MemorySegment storeRMFloat(Double3x4 self, long offset, MemorySegment dest);
    Double3x4 loadRMFloat(long offset, MemorySegment src);
    MemorySegment storeCM(Double3x4 self, long offset, MemorySegment dest, int stride);
    Double3x4 loadCM(long offset, MemorySegment src, int stride);
    MemorySegment storeCMFloat(Double3x4 self, long offset, MemorySegment dest, int stride);
    Double3x4 loadCMFloat(long offset, MemorySegment src, int stride);
    MemorySegment storeRM(Double3x4 self, long offset, MemorySegment dest, int stride);
    Double3x4 loadRM(long offset, MemorySegment src, int stride);
    MemorySegment storeRMFloat(Double3x4 self, long offset, MemorySegment dest, int stride);
    Double3x4 loadRMFloat(long offset, MemorySegment src, int stride);
    MemorySegment storeCM4x4(Double3x4 self, long offset, MemorySegment dest);
    MemorySegment storeCM4x4Float(Double3x4 self, long offset, MemorySegment dest);
    MemorySegment storeRM4x4(Double3x4 self, long offset, MemorySegment dest);
    MemorySegment storeRM4x4Float(Double3x4 self, long offset, MemorySegment dest);
}
