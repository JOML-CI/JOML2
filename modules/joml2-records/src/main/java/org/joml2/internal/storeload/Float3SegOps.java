// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class Float3SegOps {
    public abstract MemorySegment store(Float3 self, long offset, MemorySegment dest);
    public abstract Float3 load(long offset, MemorySegment src);
    public abstract MemorySegment storeDouble(Float3 self, long offset, MemorySegment dest);
    public abstract Float3 loadDouble(long offset, MemorySegment src);
}
