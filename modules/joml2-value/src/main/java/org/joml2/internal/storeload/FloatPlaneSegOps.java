// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class FloatPlaneSegOps {
    public abstract MemorySegment store(FloatPlane self, long offset, MemorySegment dest);
    public abstract FloatPlane load(long offset, MemorySegment src);
    public abstract MemorySegment storeDouble(FloatPlane self, long offset, MemorySegment dest);
    public abstract FloatPlane loadDouble(long offset, MemorySegment src);
}
