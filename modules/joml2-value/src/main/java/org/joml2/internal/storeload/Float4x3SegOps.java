// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class Float4x3SegOps {
    public abstract MemorySegment storeCM(Float4x3 self, long offset, MemorySegment dest);
    public abstract Float4x3 loadCM(long offset, MemorySegment src);
    public abstract MemorySegment storeCMDouble(Float4x3 self, long offset, MemorySegment dest);
    public abstract Float4x3 loadCMDouble(long offset, MemorySegment src);
    public abstract MemorySegment storeRM(Float4x3 self, long offset, MemorySegment dest);
    public abstract Float4x3 loadRM(long offset, MemorySegment src);
    public abstract MemorySegment storeRMDouble(Float4x3 self, long offset, MemorySegment dest);
    public abstract Float4x3 loadRMDouble(long offset, MemorySegment src);
    public abstract MemorySegment storeCM(Float4x3 self, long offset, MemorySegment dest, int stride);
    public abstract Float4x3 loadCM(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeCMDouble(Float4x3 self, long offset, MemorySegment dest, int stride);
    public abstract Float4x3 loadCMDouble(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeRM(Float4x3 self, long offset, MemorySegment dest, int stride);
    public abstract Float4x3 loadRM(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeRMDouble(Float4x3 self, long offset, MemorySegment dest, int stride);
    public abstract Float4x3 loadRMDouble(long offset, MemorySegment src, int stride);
}
