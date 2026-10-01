// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class FloatDualQuatRawOps {
    public abstract FloatDualQuat storeUnsafe(FloatDualQuatImpl self, long address);
    public abstract FloatDualQuat loadUnsafe(FloatDualQuatImpl self, long address);
    public abstract FloatDualQuat storeDoubleUnsafe(FloatDualQuatImpl self, long address);
    public abstract FloatDualQuat loadDoubleUnsafe(FloatDualQuatImpl self, long address);
}
