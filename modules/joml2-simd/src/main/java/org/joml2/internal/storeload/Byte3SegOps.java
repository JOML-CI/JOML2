// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface Byte3SegOps {
    MemorySegment store(Byte3Impl self, long offset, MemorySegment dest);
    Byte3 load(Byte3Impl self, long offset, MemorySegment src);
    MemorySegment storeShort(Byte3Impl self, long offset, MemorySegment dest);
    Byte3 loadShort(Byte3Impl self, long offset, MemorySegment src);
}
