// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class Double2x4SegOps {
    public abstract MemorySegment storeCM(Double2x4 self, long offset, MemorySegment dest);
    public abstract Double2x4 loadCM(long offset, MemorySegment src);
    public abstract MemorySegment storeCMFloat(Double2x4 self, long offset, MemorySegment dest);
    public abstract Double2x4 loadCMFloat(long offset, MemorySegment src);
    public abstract MemorySegment storeRM(Double2x4 self, long offset, MemorySegment dest);
    public abstract Double2x4 loadRM(long offset, MemorySegment src);
    public abstract MemorySegment storeRMFloat(Double2x4 self, long offset, MemorySegment dest);
    public abstract Double2x4 loadRMFloat(long offset, MemorySegment src);
    public abstract MemorySegment storeCM(Double2x4 self, long offset, MemorySegment dest, int stride);
    public abstract Double2x4 loadCM(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeCMFloat(Double2x4 self, long offset, MemorySegment dest, int stride);
    public abstract Double2x4 loadCMFloat(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeRM(Double2x4 self, long offset, MemorySegment dest, int stride);
    public abstract Double2x4 loadRM(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeRMFloat(Double2x4 self, long offset, MemorySegment dest, int stride);
    public abstract Double2x4 loadRMFloat(long offset, MemorySegment src, int stride);
}
