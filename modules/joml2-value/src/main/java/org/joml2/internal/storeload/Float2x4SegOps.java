// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class Float2x4SegOps {
    public abstract MemorySegment storeCM(Float2x4 self, long offset, MemorySegment dest);
    public abstract Float2x4 loadCM(long offset, MemorySegment src);
    public abstract MemorySegment storeCMDouble(Float2x4 self, long offset, MemorySegment dest);
    public abstract Float2x4 loadCMDouble(long offset, MemorySegment src);
    public abstract MemorySegment storeRM(Float2x4 self, long offset, MemorySegment dest);
    public abstract Float2x4 loadRM(long offset, MemorySegment src);
    public abstract MemorySegment storeRMDouble(Float2x4 self, long offset, MemorySegment dest);
    public abstract Float2x4 loadRMDouble(long offset, MemorySegment src);
    public abstract MemorySegment storeCM(Float2x4 self, long offset, MemorySegment dest, int stride);
    public abstract Float2x4 loadCM(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeCMDouble(Float2x4 self, long offset, MemorySegment dest, int stride);
    public abstract Float2x4 loadCMDouble(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeRM(Float2x4 self, long offset, MemorySegment dest, int stride);
    public abstract Float2x4 loadRM(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeRMDouble(Float2x4 self, long offset, MemorySegment dest, int stride);
    public abstract Float2x4 loadRMDouble(long offset, MemorySegment src, int stride);
}
