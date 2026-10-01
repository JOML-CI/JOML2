// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public abstract class DoubleDualQuatSegOps {
    public abstract MemorySegment store(DoubleDualQuatImpl self, long offset, MemorySegment dest);
    public abstract DoubleDualQuat load(DoubleDualQuatImpl self, long offset, MemorySegment src);
    public abstract MemorySegment storeFloat(DoubleDualQuatImpl self, long offset, MemorySegment dest);
    public abstract DoubleDualQuat loadFloat(DoubleDualQuatImpl self, long offset, MemorySegment src);
}
