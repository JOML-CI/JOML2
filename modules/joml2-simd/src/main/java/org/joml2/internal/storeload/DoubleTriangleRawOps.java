// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class DoubleTriangleRawOps {
    public abstract DoubleTriangle storeUnsafe(DoubleTriangleImpl self, long address);
    public abstract DoubleTriangle loadUnsafe(DoubleTriangleImpl self, long address);
    public abstract DoubleTriangle storeFloatUnsafe(DoubleTriangleImpl self, long address);
    public abstract DoubleTriangle loadFloatUnsafe(DoubleTriangleImpl self, long address);
}
