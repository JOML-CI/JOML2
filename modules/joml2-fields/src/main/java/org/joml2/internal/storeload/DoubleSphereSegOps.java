// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public abstract class DoubleSphereSegOps {
    public abstract MemorySegment store(DoubleSphereImpl self, long offset, MemorySegment dest);
    public abstract DoubleSphere load(DoubleSphereImpl self, long offset, MemorySegment src);
    public abstract MemorySegment storeFloat(DoubleSphereImpl self, long offset, MemorySegment dest);
    public abstract DoubleSphere loadFloat(DoubleSphereImpl self, long offset, MemorySegment src);
}
