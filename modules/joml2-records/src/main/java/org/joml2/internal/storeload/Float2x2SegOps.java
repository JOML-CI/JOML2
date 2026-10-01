// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class Float2x2SegOps {
    public abstract MemorySegment storeCM(Float2x2 self, long offset, MemorySegment dest);
    public abstract Float2x2 loadCM(long offset, MemorySegment src);
    public abstract MemorySegment storeCMDouble(Float2x2 self, long offset, MemorySegment dest);
    public abstract Float2x2 loadCMDouble(long offset, MemorySegment src);
    public abstract MemorySegment storeRM(Float2x2 self, long offset, MemorySegment dest);
    public abstract Float2x2 loadRM(long offset, MemorySegment src);
    public abstract MemorySegment storeRMDouble(Float2x2 self, long offset, MemorySegment dest);
    public abstract Float2x2 loadRMDouble(long offset, MemorySegment src);
    public abstract MemorySegment storeCM(Float2x2 self, long offset, MemorySegment dest, int stride);
    public abstract Float2x2 loadCM(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeCMDouble(Float2x2 self, long offset, MemorySegment dest, int stride);
    public abstract Float2x2 loadCMDouble(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeRM(Float2x2 self, long offset, MemorySegment dest, int stride);
    public abstract Float2x2 loadRM(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeRMDouble(Float2x2 self, long offset, MemorySegment dest, int stride);
    public abstract Float2x2 loadRMDouble(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeCM3x3(Float2x2 self, long offset, MemorySegment dest);
    public abstract MemorySegment storeCM3x3Double(Float2x2 self, long offset, MemorySegment dest);
    public abstract MemorySegment storeRM3x3(Float2x2 self, long offset, MemorySegment dest);
    public abstract MemorySegment storeRM3x3Double(Float2x2 self, long offset, MemorySegment dest);
    public abstract MemorySegment storeCM4x4(Float2x2 self, long offset, MemorySegment dest);
    public abstract MemorySegment storeCM4x4Double(Float2x2 self, long offset, MemorySegment dest);
    public abstract MemorySegment storeRM4x4(Float2x2 self, long offset, MemorySegment dest);
    public abstract MemorySegment storeRM4x4Double(Float2x2 self, long offset, MemorySegment dest);
}
