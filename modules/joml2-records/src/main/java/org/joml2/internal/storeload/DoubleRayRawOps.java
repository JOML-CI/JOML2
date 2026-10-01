// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class DoubleRayRawOps {
    public abstract DoubleRay storeUnsafe(DoubleRay self, long address);
    public abstract DoubleRay loadUnsafe(long address);
    public abstract DoubleRay storeFloatUnsafe(DoubleRay self, long address);
    public abstract DoubleRay loadFloatUnsafe(long address);
}
