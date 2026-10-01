// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class Double3x4SegOps {
    public abstract MemorySegment storeCM(Double3x4 self, long offset, MemorySegment dest);
    public abstract Double3x4 loadCM(long offset, MemorySegment src);
    public abstract MemorySegment storeCMFloat(Double3x4 self, long offset, MemorySegment dest);
    public abstract Double3x4 loadCMFloat(long offset, MemorySegment src);
    public abstract MemorySegment storeRM(Double3x4 self, long offset, MemorySegment dest);
    public abstract Double3x4 loadRM(long offset, MemorySegment src);
    public abstract MemorySegment storeRMFloat(Double3x4 self, long offset, MemorySegment dest);
    public abstract Double3x4 loadRMFloat(long offset, MemorySegment src);
    public abstract MemorySegment storeCM(Double3x4 self, long offset, MemorySegment dest, int stride);
    public abstract Double3x4 loadCM(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeCMFloat(Double3x4 self, long offset, MemorySegment dest, int stride);
    public abstract Double3x4 loadCMFloat(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeRM(Double3x4 self, long offset, MemorySegment dest, int stride);
    public abstract Double3x4 loadRM(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeRMFloat(Double3x4 self, long offset, MemorySegment dest, int stride);
    public abstract Double3x4 loadRMFloat(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeCM4x4(Double3x4 self, long offset, MemorySegment dest);
    public abstract MemorySegment storeCM4x4Float(Double3x4 self, long offset, MemorySegment dest);
    public abstract MemorySegment storeRM4x4(Double3x4 self, long offset, MemorySegment dest);
    public abstract MemorySegment storeRM4x4Float(Double3x4 self, long offset, MemorySegment dest);
}
