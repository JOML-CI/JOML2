// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class FloatQuatRawOps {
    public abstract FloatQuat storeUnsafe(FloatQuat self, long address);
    public abstract FloatQuat loadUnsafe(long address);
    public abstract FloatQuat storeDoubleUnsafe(FloatQuat self, long address);
    public abstract FloatQuat loadDoubleUnsafe(long address);
}
