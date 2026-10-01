// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class DoubleQuatSegOps {
    public abstract MemorySegment store(DoubleQuat self, long offset, MemorySegment dest);
    public abstract DoubleQuat load(long offset, MemorySegment src);
    public abstract MemorySegment storeFloat(DoubleQuat self, long offset, MemorySegment dest);
    public abstract DoubleQuat loadFloat(long offset, MemorySegment src);
}
