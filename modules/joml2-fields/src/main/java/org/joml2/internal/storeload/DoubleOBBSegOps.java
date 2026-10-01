// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public abstract class DoubleOBBSegOps {
    public abstract MemorySegment store(DoubleOBBImpl self, long offset, MemorySegment dest);
    public abstract DoubleOBB load(DoubleOBBImpl self, long offset, MemorySegment src);
    public abstract MemorySegment storeFloat(DoubleOBBImpl self, long offset, MemorySegment dest);
    public abstract DoubleOBB loadFloat(DoubleOBBImpl self, long offset, MemorySegment src);
}
