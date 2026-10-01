// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class Float3x3SegOps {
    public abstract MemorySegment storeCM(Float3x3 self, long offset, MemorySegment dest);
    public abstract Float3x3 loadCM(long offset, MemorySegment src);
    public abstract MemorySegment storeCMDouble(Float3x3 self, long offset, MemorySegment dest);
    public abstract Float3x3 loadCMDouble(long offset, MemorySegment src);
    public abstract MemorySegment storeRM(Float3x3 self, long offset, MemorySegment dest);
    public abstract Float3x3 loadRM(long offset, MemorySegment src);
    public abstract MemorySegment storeRMDouble(Float3x3 self, long offset, MemorySegment dest);
    public abstract Float3x3 loadRMDouble(long offset, MemorySegment src);
    public abstract MemorySegment storeCM(Float3x3 self, long offset, MemorySegment dest, int stride);
    public abstract Float3x3 loadCM(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeCMDouble(Float3x3 self, long offset, MemorySegment dest, int stride);
    public abstract Float3x3 loadCMDouble(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeRM(Float3x3 self, long offset, MemorySegment dest, int stride);
    public abstract Float3x3 loadRM(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeRMDouble(Float3x3 self, long offset, MemorySegment dest, int stride);
    public abstract Float3x3 loadRMDouble(long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeCM4x4(Float3x3 self, long offset, MemorySegment dest);
    public abstract MemorySegment storeCM4x4Double(Float3x3 self, long offset, MemorySegment dest);
    public abstract MemorySegment storeRM4x4(Float3x3 self, long offset, MemorySegment dest);
    public abstract MemorySegment storeRM4x4Double(Float3x3 self, long offset, MemorySegment dest);
}
