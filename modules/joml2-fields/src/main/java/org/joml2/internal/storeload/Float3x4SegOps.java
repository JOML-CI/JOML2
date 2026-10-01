// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public abstract class Float3x4SegOps {
    public abstract MemorySegment storeCM(Float3x4Impl self, long offset, MemorySegment dest);
    public abstract Float3x4 loadCM(Float3x4Impl self, long offset, MemorySegment src);
    public abstract MemorySegment storeCMDouble(Float3x4Impl self, long offset, MemorySegment dest);
    public abstract Float3x4 loadCMDouble(Float3x4Impl self, long offset, MemorySegment src);
    public abstract MemorySegment storeRM(Float3x4Impl self, long offset, MemorySegment dest);
    public abstract Float3x4 loadRM(Float3x4Impl self, long offset, MemorySegment src);
    public abstract MemorySegment storeRMDouble(Float3x4Impl self, long offset, MemorySegment dest);
    public abstract Float3x4 loadRMDouble(Float3x4Impl self, long offset, MemorySegment src);
    public abstract MemorySegment storeCM(Float3x4Impl self, long offset, MemorySegment dest, int stride);
    public abstract Float3x4 loadCM(Float3x4Impl self, long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeCMDouble(Float3x4Impl self, long offset, MemorySegment dest, int stride);
    public abstract Float3x4 loadCMDouble(Float3x4Impl self, long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeRM(Float3x4Impl self, long offset, MemorySegment dest, int stride);
    public abstract Float3x4 loadRM(Float3x4Impl self, long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeRMDouble(Float3x4Impl self, long offset, MemorySegment dest, int stride);
    public abstract Float3x4 loadRMDouble(Float3x4Impl self, long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeCM4x4(Float3x4Impl self, long offset, MemorySegment dest);
    public abstract MemorySegment storeCM4x4Double(Float3x4Impl self, long offset, MemorySegment dest);
    public abstract MemorySegment storeRM4x4(Float3x4Impl self, long offset, MemorySegment dest);
    public abstract MemorySegment storeRM4x4Double(Float3x4Impl self, long offset, MemorySegment dest);
}
