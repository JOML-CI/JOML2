// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface FloatSphereSegOps {
    MemorySegment store(FloatSphereImpl self, long offset, MemorySegment dest);
    FloatSphere load(FloatSphereImpl self, long offset, MemorySegment src);
    MemorySegment storeDouble(FloatSphereImpl self, long offset, MemorySegment dest);
    FloatSphere loadDouble(FloatSphereImpl self, long offset, MemorySegment src);
}
