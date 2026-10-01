// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface DoubleQuatSegOps {
    MemorySegment store(DoubleQuatImpl self, long offset, MemorySegment dest);
    DoubleQuat load(DoubleQuatImpl self, long offset, MemorySegment src);
    MemorySegment storeFloat(DoubleQuatImpl self, long offset, MemorySegment dest);
    DoubleQuat loadFloat(DoubleQuatImpl self, long offset, MemorySegment src);
}
