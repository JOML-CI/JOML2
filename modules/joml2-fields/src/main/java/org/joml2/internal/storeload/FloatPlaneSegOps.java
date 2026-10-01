// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface FloatPlaneSegOps {
    MemorySegment store(FloatPlaneImpl self, long offset, MemorySegment dest);
    FloatPlane load(FloatPlaneImpl self, long offset, MemorySegment src);
    MemorySegment storeDouble(FloatPlaneImpl self, long offset, MemorySegment dest);
    FloatPlane loadDouble(FloatPlaneImpl self, long offset, MemorySegment src);
}
