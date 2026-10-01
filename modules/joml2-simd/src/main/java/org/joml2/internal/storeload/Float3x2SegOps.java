// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public abstract class Float3x2SegOps {
    public abstract MemorySegment storeCM(Float3x2Impl self, long offset, MemorySegment dest);
    public abstract Float3x2 loadCM(Float3x2Impl self, long offset, MemorySegment src);
    public abstract MemorySegment storeCMDouble(Float3x2Impl self, long offset, MemorySegment dest);
    public abstract Float3x2 loadCMDouble(Float3x2Impl self, long offset, MemorySegment src);
    public abstract MemorySegment storeRM(Float3x2Impl self, long offset, MemorySegment dest);
    public abstract Float3x2 loadRM(Float3x2Impl self, long offset, MemorySegment src);
    public abstract MemorySegment storeRMDouble(Float3x2Impl self, long offset, MemorySegment dest);
    public abstract Float3x2 loadRMDouble(Float3x2Impl self, long offset, MemorySegment src);
    public abstract MemorySegment storeCM(Float3x2Impl self, long offset, MemorySegment dest, int stride);
    public abstract Float3x2 loadCM(Float3x2Impl self, long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeCMDouble(Float3x2Impl self, long offset, MemorySegment dest, int stride);
    public abstract Float3x2 loadCMDouble(Float3x2Impl self, long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeRM(Float3x2Impl self, long offset, MemorySegment dest, int stride);
    public abstract Float3x2 loadRM(Float3x2Impl self, long offset, MemorySegment src, int stride);
    public abstract MemorySegment storeRMDouble(Float3x2Impl self, long offset, MemorySegment dest, int stride);
    public abstract Float3x2 loadRMDouble(Float3x2Impl self, long offset, MemorySegment src, int stride);
}
