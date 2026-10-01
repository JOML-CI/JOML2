// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class Double3x2SegOps {
    public abstract MemorySegment storeCM(Double3x2 self, long offset, MemorySegment dest);
    public abstract Double3x2 loadCM(long offset, MemorySegment src);
    public abstract MemorySegment storeCMFloat(Double3x2 self, long offset, MemorySegment dest);
    public abstract Double3x2 loadCMFloat(long offset, MemorySegment src);
    public abstract MemorySegment storeRM(Double3x2 self, long offset, MemorySegment dest);
    public abstract Double3x2 loadRM(long offset, MemorySegment src);
    public abstract MemorySegment storeRMFloat(Double3x2 self, long offset, MemorySegment dest);
    public abstract Double3x2 loadRMFloat(long offset, MemorySegment src);
    public abstract MemorySegment storeCM(Double3x2 self, long offset, MemorySegment dest, int stride);
    public abstract Double3x2 loadCM(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeCMFloat(Double3x2 self, long offset, MemorySegment dest, int stride);
    public abstract Double3x2 loadCMFloat(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeRM(Double3x2 self, long offset, MemorySegment dest, int stride);
    public abstract Double3x2 loadRM(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeRMFloat(Double3x2 self, long offset, MemorySegment dest, int stride);
    public abstract Double3x2 loadRMFloat(long offset, MemorySegment src, int stride);
}
