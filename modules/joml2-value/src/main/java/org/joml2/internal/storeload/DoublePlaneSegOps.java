// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface DoublePlaneSegOps {
    MemorySegment store(DoublePlane self, long offset, MemorySegment dest);
    DoublePlane load(long offset, MemorySegment src);
    MemorySegment storeFloat(DoublePlane self, long offset, MemorySegment dest);
    DoublePlane loadFloat(long offset, MemorySegment src);
}
