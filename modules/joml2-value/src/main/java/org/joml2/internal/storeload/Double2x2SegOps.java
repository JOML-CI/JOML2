// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class Double2x2SegOps {
    public abstract MemorySegment storeCM(Double2x2 self, long offset, MemorySegment dest);
    public abstract Double2x2 loadCM(long offset, MemorySegment src);
    public abstract MemorySegment storeCMFloat(Double2x2 self, long offset, MemorySegment dest);
    public abstract Double2x2 loadCMFloat(long offset, MemorySegment src);
    public abstract MemorySegment storeRM(Double2x2 self, long offset, MemorySegment dest);
    public abstract Double2x2 loadRM(long offset, MemorySegment src);
    public abstract MemorySegment storeRMFloat(Double2x2 self, long offset, MemorySegment dest);
    public abstract Double2x2 loadRMFloat(long offset, MemorySegment src);
    public abstract MemorySegment storeCM(Double2x2 self, long offset, MemorySegment dest, int stride);
    public abstract Double2x2 loadCM(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeCMFloat(Double2x2 self, long offset, MemorySegment dest, int stride);
    public abstract Double2x2 loadCMFloat(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeRM(Double2x2 self, long offset, MemorySegment dest, int stride);
    public abstract Double2x2 loadRM(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeRMFloat(Double2x2 self, long offset, MemorySegment dest, int stride);
    public abstract Double2x2 loadRMFloat(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeCM3x3(Double2x2 self, long offset, MemorySegment dest);
    public abstract MemorySegment storeCM3x3Float(Double2x2 self, long offset, MemorySegment dest);
    public abstract MemorySegment storeRM3x3(Double2x2 self, long offset, MemorySegment dest);
    public abstract MemorySegment storeRM3x3Float(Double2x2 self, long offset, MemorySegment dest);
    public abstract MemorySegment storeCM4x4(Double2x2 self, long offset, MemorySegment dest);
    public abstract MemorySegment storeCM4x4Float(Double2x2 self, long offset, MemorySegment dest);
    public abstract MemorySegment storeRM4x4(Double2x2 self, long offset, MemorySegment dest);
    public abstract MemorySegment storeRM4x4Float(Double2x2 self, long offset, MemorySegment dest);
}
