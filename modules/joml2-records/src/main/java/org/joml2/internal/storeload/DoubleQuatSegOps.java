// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface DoubleQuatSegOps {
    MemorySegment store(DoubleQuat self, long offset, MemorySegment dest);
    DoubleQuat load(long offset, MemorySegment src);
    MemorySegment storeFloat(DoubleQuat self, long offset, MemorySegment dest);
    DoubleQuat loadFloat(long offset, MemorySegment src);
}
