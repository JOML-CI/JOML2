// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.lang.foreign.MemorySegment;

public interface FloatRigidSegOps {
    MemorySegment store(FloatRigid self, long offset, MemorySegment dest);
    FloatRigid load(long offset, MemorySegment src);
    MemorySegment storeDouble(FloatRigid self, long offset, MemorySegment dest);
    FloatRigid loadDouble(long offset, MemorySegment src);
}
