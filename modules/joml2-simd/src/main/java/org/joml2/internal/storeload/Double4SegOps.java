// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface Double4SegOps {
    MemorySegment storeFloat(Double4Impl self, long offset, MemorySegment dest);
    Double4 loadFloat(Double4Impl self, long offset, MemorySegment src);
}
