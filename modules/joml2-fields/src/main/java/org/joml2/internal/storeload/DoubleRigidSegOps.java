// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public abstract class DoubleRigidSegOps {
    public abstract MemorySegment store(DoubleRigidImpl self, long offset, MemorySegment dest);
    public abstract DoubleRigid load(DoubleRigidImpl self, long offset, MemorySegment src);
    public abstract MemorySegment storeFloat(DoubleRigidImpl self, long offset, MemorySegment dest);
    public abstract DoubleRigid loadFloat(DoubleRigidImpl self, long offset, MemorySegment src);
}
