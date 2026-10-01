// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface FloatDualQuatSegOps {
    MemorySegment store(FloatDualQuatImpl self, long offset, MemorySegment dest);
    FloatDualQuat load(FloatDualQuatImpl self, long offset, MemorySegment src);
    MemorySegment storeDouble(FloatDualQuatImpl self, long offset, MemorySegment dest);
    FloatDualQuat loadDouble(FloatDualQuatImpl self, long offset, MemorySegment src);
}
