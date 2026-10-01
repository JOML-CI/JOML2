// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface Byte2SegOps {
    MemorySegment store(Byte2Impl self, long offset, MemorySegment dest);
    Byte2 load(Byte2Impl self, long offset, MemorySegment src);
    MemorySegment storeShort(Byte2Impl self, long offset, MemorySegment dest);
    Byte2 loadShort(Byte2Impl self, long offset, MemorySegment src);
}
