// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class DoubleDualQuatRawOps {
    public abstract DoubleDualQuat storeUnsafe(DoubleDualQuat self, long address);
    public abstract DoubleDualQuat loadUnsafe(long address);
    public abstract DoubleDualQuat storeFloatUnsafe(DoubleDualQuat self, long address);
    public abstract DoubleDualQuat loadFloatUnsafe(long address);
}
