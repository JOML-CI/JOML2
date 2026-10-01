// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class DoubleTriangleRawOps {
    public abstract DoubleTriangle storeUnsafe(DoubleTriangle self, long address);
    public abstract DoubleTriangle loadUnsafe(long address);
    public abstract DoubleTriangle storeFloatUnsafe(DoubleTriangle self, long address);
    public abstract DoubleTriangle loadFloatUnsafe(long address);
}
