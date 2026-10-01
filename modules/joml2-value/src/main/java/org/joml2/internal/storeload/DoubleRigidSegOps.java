// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public abstract class DoubleRigidSegOps {
    public abstract MemorySegment store(DoubleRigid self, long offset, MemorySegment dest);
    public abstract DoubleRigid load(long offset, MemorySegment src);
    public abstract MemorySegment storeFloat(DoubleRigid self, long offset, MemorySegment dest);
    public abstract DoubleRigid loadFloat(long offset, MemorySegment src);
}
