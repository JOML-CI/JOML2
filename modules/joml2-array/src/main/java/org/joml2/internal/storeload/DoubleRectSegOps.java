// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface DoubleRectSegOps {
    MemorySegment store(DoubleRectImpl self, long offset, MemorySegment dest);
    DoubleRect load(DoubleRectImpl self, long offset, MemorySegment src);
    MemorySegment storeFloat(DoubleRectImpl self, long offset, MemorySegment dest);
    DoubleRect loadFloat(DoubleRectImpl self, long offset, MemorySegment src);
}
