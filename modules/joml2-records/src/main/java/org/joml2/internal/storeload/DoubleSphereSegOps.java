// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface DoubleSphereSegOps {
    MemorySegment store(DoubleSphere self, long offset, MemorySegment dest);
    DoubleSphere load(long offset, MemorySegment src);
    MemorySegment storeFloat(DoubleSphere self, long offset, MemorySegment dest);
    DoubleSphere loadFloat(long offset, MemorySegment src);
}
