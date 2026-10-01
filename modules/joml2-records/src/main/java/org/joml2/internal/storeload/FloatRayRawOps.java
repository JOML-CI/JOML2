// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class FloatRayRawOps {
    public abstract FloatRay storeUnsafe(FloatRay self, long address);
    public abstract FloatRay loadUnsafe(long address);
    public abstract FloatRay storeDoubleUnsafe(FloatRay self, long address);
    public abstract FloatRay loadDoubleUnsafe(long address);
}
