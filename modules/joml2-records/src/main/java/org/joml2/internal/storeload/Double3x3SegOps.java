// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class Double3x3SegOps {
    public abstract MemorySegment storeCM(Double3x3 self, long offset, MemorySegment dest);
    public abstract Double3x3 loadCM(long offset, MemorySegment src);
    public abstract MemorySegment storeCMFloat(Double3x3 self, long offset, MemorySegment dest);
    public abstract Double3x3 loadCMFloat(long offset, MemorySegment src);
    public abstract MemorySegment storeRM(Double3x3 self, long offset, MemorySegment dest);
    public abstract Double3x3 loadRM(long offset, MemorySegment src);
    public abstract MemorySegment storeRMFloat(Double3x3 self, long offset, MemorySegment dest);
    public abstract Double3x3 loadRMFloat(long offset, MemorySegment src);
    public abstract MemorySegment storeCM(Double3x3 self, long offset, MemorySegment dest, int stride);
    public abstract Double3x3 loadCM(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeCMFloat(Double3x3 self, long offset, MemorySegment dest, int stride);
    public abstract Double3x3 loadCMFloat(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeRM(Double3x3 self, long offset, MemorySegment dest, int stride);
    public abstract Double3x3 loadRM(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeRMFloat(Double3x3 self, long offset, MemorySegment dest, int stride);
    public abstract Double3x3 loadRMFloat(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeCM4x4(Double3x3 self, long offset, MemorySegment dest);
    public abstract MemorySegment storeCM4x4Float(Double3x3 self, long offset, MemorySegment dest);
    public abstract MemorySegment storeRM4x4(Double3x3 self, long offset, MemorySegment dest);
    public abstract MemorySegment storeRM4x4Float(Double3x3 self, long offset, MemorySegment dest);
}
