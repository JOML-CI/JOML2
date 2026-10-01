// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class DoubleDualQuatSegOps {
    public abstract MemorySegment store(DoubleDualQuat self, long offset, MemorySegment dest);
    public abstract DoubleDualQuat load(long offset, MemorySegment src);
    public abstract MemorySegment storeFloat(DoubleDualQuat self, long offset, MemorySegment dest);
    public abstract DoubleDualQuat loadFloat(long offset, MemorySegment src);
}
