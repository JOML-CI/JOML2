// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface DoubleDualQuatSegOps {
    MemorySegment store(DoubleDualQuat self, long offset, MemorySegment dest);
    DoubleDualQuat load(long offset, MemorySegment src);
    MemorySegment storeFloat(DoubleDualQuat self, long offset, MemorySegment dest);
    DoubleDualQuat loadFloat(long offset, MemorySegment src);
}
