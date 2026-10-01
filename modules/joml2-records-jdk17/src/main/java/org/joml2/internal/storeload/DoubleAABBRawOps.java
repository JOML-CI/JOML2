// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class DoubleAABBRawOps {
    public abstract DoubleAABB storeUnsafe(DoubleAABB self, long address);
    public abstract DoubleAABB loadUnsafe(long address);
    public abstract DoubleAABB storeFloatUnsafe(DoubleAABB self, long address);
    public abstract DoubleAABB loadFloatUnsafe(long address);
}
