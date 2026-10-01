// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class FloatSphereSegOps {
    public abstract MemorySegment store(FloatSphere self, long offset, MemorySegment dest);
    public abstract FloatSphere load(long offset, MemorySegment src);
    public abstract MemorySegment storeDouble(FloatSphere self, long offset, MemorySegment dest);
    public abstract FloatSphere loadDouble(long offset, MemorySegment src);
}
