// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface FloatDualQuatSegOps {
    MemorySegment store(FloatDualQuat self, long offset, MemorySegment dest);
    FloatDualQuat load(long offset, MemorySegment src);
    MemorySegment storeDouble(FloatDualQuat self, long offset, MemorySegment dest);
    FloatDualQuat loadDouble(long offset, MemorySegment src);
}
