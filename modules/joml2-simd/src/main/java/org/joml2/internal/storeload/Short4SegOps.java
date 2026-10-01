// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public abstract class Short4SegOps {
    public abstract MemorySegment storeByte(Short4Impl self, long offset, MemorySegment dest);
    public abstract Short4 loadByte(Short4Impl self, long offset, MemorySegment src);
}
