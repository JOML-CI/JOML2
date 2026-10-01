// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public abstract class Float4x4SegOps {
    public abstract MemorySegment storeCMDouble(Float4x4Impl self, long offset, MemorySegment dest);
    public abstract Float4x4 loadCMDouble(Float4x4Impl self, long offset, MemorySegment src);
    public abstract MemorySegment storeRM(Float4x4Impl self, long offset, MemorySegment dest);
    public abstract Float4x4 loadRM(Float4x4Impl self, long offset, MemorySegment src);
    public abstract MemorySegment storeRMDouble(Float4x4Impl self, long offset, MemorySegment dest);
    public abstract Float4x4 loadRMDouble(Float4x4Impl self, long offset, MemorySegment src);
    public abstract MemorySegment storeCM(Float4x4Impl self, long offset, MemorySegment dest, int stride);
    public abstract Float4x4 loadCM(Float4x4Impl self, long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeCMDouble(Float4x4Impl self, long offset, MemorySegment dest, int stride);
    public abstract Float4x4 loadCMDouble(Float4x4Impl self, long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeRM(Float4x4Impl self, long offset, MemorySegment dest, int stride);
    public abstract Float4x4 loadRM(Float4x4Impl self, long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeRMDouble(Float4x4Impl self, long offset, MemorySegment dest, int stride);
    public abstract Float4x4 loadRMDouble(Float4x4Impl self, long offset, MemorySegment src, int stride);
}
