// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface FloatRectSegOps {
    MemorySegment store(FloatRectImpl self, long offset, MemorySegment dest);
    FloatRect load(FloatRectImpl self, long offset, MemorySegment src);
    MemorySegment storeDouble(FloatRectImpl self, long offset, MemorySegment dest);
    FloatRect loadDouble(FloatRectImpl self, long offset, MemorySegment src);
}
