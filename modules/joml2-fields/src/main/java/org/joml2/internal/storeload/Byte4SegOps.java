// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface Byte4SegOps {
    MemorySegment store(Byte4Impl self, long offset, MemorySegment dest);
    Byte4 load(Byte4Impl self, long offset, MemorySegment src);
    MemorySegment storeShort(Byte4Impl self, long offset, MemorySegment dest);
    Byte4 loadShort(Byte4Impl self, long offset, MemorySegment src);
}
