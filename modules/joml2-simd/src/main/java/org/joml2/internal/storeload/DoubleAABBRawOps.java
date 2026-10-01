// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class DoubleAABBRawOps {
    public abstract DoubleAABB storeUnsafe(DoubleAABBImpl self, long address);
    public abstract DoubleAABB loadUnsafe(DoubleAABBImpl self, long address);
    public abstract DoubleAABB storeFloatUnsafe(DoubleAABBImpl self, long address);
    public abstract DoubleAABB loadFloatUnsafe(DoubleAABBImpl self, long address);
}
