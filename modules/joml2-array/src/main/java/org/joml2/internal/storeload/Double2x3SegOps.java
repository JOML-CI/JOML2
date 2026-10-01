// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public abstract class Double2x3SegOps {
    public abstract MemorySegment storeCM(Double2x3Impl self, long offset, MemorySegment dest);
    public abstract Double2x3 loadCM(Double2x3Impl self, long offset, MemorySegment src);
    public abstract MemorySegment storeCMFloat(Double2x3Impl self, long offset, MemorySegment dest);
    public abstract Double2x3 loadCMFloat(Double2x3Impl self, long offset, MemorySegment src);
    public abstract MemorySegment storeRM(Double2x3Impl self, long offset, MemorySegment dest);
    public abstract Double2x3 loadRM(Double2x3Impl self, long offset, MemorySegment src);
    public abstract MemorySegment storeRMFloat(Double2x3Impl self, long offset, MemorySegment dest);
    public abstract Double2x3 loadRMFloat(Double2x3Impl self, long offset, MemorySegment src);
    public abstract MemorySegment storeCM(Double2x3Impl self, long offset, MemorySegment dest, int stride);
    public abstract Double2x3 loadCM(Double2x3Impl self, long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeCMFloat(Double2x3Impl self, long offset, MemorySegment dest, int stride);
    public abstract Double2x3 loadCMFloat(Double2x3Impl self, long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeRM(Double2x3Impl self, long offset, MemorySegment dest, int stride);
    public abstract Double2x3 loadRM(Double2x3Impl self, long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeRMFloat(Double2x3Impl self, long offset, MemorySegment dest, int stride);
    public abstract Double2x3 loadRMFloat(Double2x3Impl self, long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeCM3x3(Double2x3Impl self, long offset, MemorySegment dest);
    public abstract MemorySegment storeCM3x3Float(Double2x3Impl self, long offset, MemorySegment dest);
    public abstract MemorySegment storeRM3x3(Double2x3Impl self, long offset, MemorySegment dest);
    public abstract MemorySegment storeRM3x3Float(Double2x3Impl self, long offset, MemorySegment dest);
    public abstract MemorySegment storeCM4x4(Double2x3Impl self, long offset, MemorySegment dest);
    public abstract MemorySegment storeCM4x4Float(Double2x3Impl self, long offset, MemorySegment dest);
    public abstract MemorySegment storeRM4x4(Double2x3Impl self, long offset, MemorySegment dest);
    public abstract MemorySegment storeRM4x4Float(Double2x3Impl self, long offset, MemorySegment dest);
}
