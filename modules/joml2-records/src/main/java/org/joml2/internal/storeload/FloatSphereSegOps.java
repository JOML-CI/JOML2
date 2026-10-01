// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface FloatSphereSegOps {
    MemorySegment store(FloatSphere self, long offset, MemorySegment dest);
    FloatSphere load(long offset, MemorySegment src);
    MemorySegment storeDouble(FloatSphere self, long offset, MemorySegment dest);
    FloatSphere loadDouble(long offset, MemorySegment src);
}
