// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public abstract class FloatPlaneSegOps {
    public abstract MemorySegment storeDouble(FloatPlaneImpl self, long offset, MemorySegment dest);
    public abstract FloatPlane loadDouble(FloatPlaneImpl self, long offset, MemorySegment src);
}
