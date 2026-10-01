// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class Double4x3SegOps {
    public abstract MemorySegment storeCM(Double4x3 self, long offset, MemorySegment dest);
    public abstract Double4x3 loadCM(long offset, MemorySegment src);
    public abstract MemorySegment storeCMFloat(Double4x3 self, long offset, MemorySegment dest);
    public abstract Double4x3 loadCMFloat(long offset, MemorySegment src);
    public abstract MemorySegment storeRM(Double4x3 self, long offset, MemorySegment dest);
    public abstract Double4x3 loadRM(long offset, MemorySegment src);
    public abstract MemorySegment storeRMFloat(Double4x3 self, long offset, MemorySegment dest);
    public abstract Double4x3 loadRMFloat(long offset, MemorySegment src);
    public abstract MemorySegment storeCM(Double4x3 self, long offset, MemorySegment dest, int stride);
    public abstract Double4x3 loadCM(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeCMFloat(Double4x3 self, long offset, MemorySegment dest, int stride);
    public abstract Double4x3 loadCMFloat(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeRM(Double4x3 self, long offset, MemorySegment dest, int stride);
    public abstract Double4x3 loadRM(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeRMFloat(Double4x3 self, long offset, MemorySegment dest, int stride);
    public abstract Double4x3 loadRMFloat(long offset, MemorySegment src, int stride);
}
