// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class DoubleRayRawOps {
    public abstract DoubleRay storeUnsafe(DoubleRayImpl self, long address);
    public abstract DoubleRay loadUnsafe(DoubleRayImpl self, long address);
    public abstract DoubleRay storeFloatUnsafe(DoubleRayImpl self, long address);
    public abstract DoubleRay loadFloatUnsafe(DoubleRayImpl self, long address);
}
