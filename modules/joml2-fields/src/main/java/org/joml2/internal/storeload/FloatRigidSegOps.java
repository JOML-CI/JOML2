// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.lang.foreign.MemorySegment;

public interface FloatRigidSegOps {
    MemorySegment store(FloatRigidImpl self, long offset, MemorySegment dest);
    FloatRigid load(FloatRigidImpl self, long offset, MemorySegment src);
    MemorySegment storeDouble(FloatRigidImpl self, long offset, MemorySegment dest);
    FloatRigid loadDouble(FloatRigidImpl self, long offset, MemorySegment src);
}
