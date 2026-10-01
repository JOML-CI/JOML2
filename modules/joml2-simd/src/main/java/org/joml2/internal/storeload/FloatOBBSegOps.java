// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface FloatOBBSegOps {
    MemorySegment store(FloatOBBImpl self, long offset, MemorySegment dest);
    FloatOBB load(FloatOBBImpl self, long offset, MemorySegment src);
    MemorySegment storeDouble(FloatOBBImpl self, long offset, MemorySegment dest);
    FloatOBB loadDouble(FloatOBBImpl self, long offset, MemorySegment src);
}
