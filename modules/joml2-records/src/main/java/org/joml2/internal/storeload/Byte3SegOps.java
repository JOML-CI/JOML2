// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface Byte3SegOps {
    MemorySegment store(Byte3 self, long offset, MemorySegment dest);
    Byte3 load(long offset, MemorySegment src);
    MemorySegment storeShort(Byte3 self, long offset, MemorySegment dest);
    Byte3 loadShort(long offset, MemorySegment src);
}
