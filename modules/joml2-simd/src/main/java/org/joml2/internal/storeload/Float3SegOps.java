// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public abstract class Float3SegOps {
    public abstract MemorySegment store(Float3Impl self, long offset, MemorySegment dest);
    public abstract Float3 load(Float3Impl self, long offset, MemorySegment src);
    public abstract MemorySegment storeDouble(Float3Impl self, long offset, MemorySegment dest);
    public abstract Float3 loadDouble(Float3Impl self, long offset, MemorySegment src);
}
