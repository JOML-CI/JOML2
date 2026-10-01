// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class FloatRayRawOps {
    public abstract FloatRay storeUnsafe(FloatRayImpl self, long address);
    public abstract FloatRay loadUnsafe(FloatRayImpl self, long address);
    public abstract FloatRay storeDoubleUnsafe(FloatRayImpl self, long address);
    public abstract FloatRay loadDoubleUnsafe(FloatRayImpl self, long address);
}
