// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public abstract class Int4SegOps {
    public abstract MemorySegment storeLong(Int4Impl self, long offset, MemorySegment dest);
    public abstract Int4 loadLong(Int4Impl self, long offset, MemorySegment src);
}
