// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class FloatDualQuatRawOps {
    public abstract FloatDualQuat storeUnsafe(FloatDualQuat self, long address);
    public abstract FloatDualQuat loadUnsafe(long address);
    public abstract FloatDualQuat storeDoubleUnsafe(FloatDualQuat self, long address);
    public abstract FloatDualQuat loadDoubleUnsafe(long address);
}
