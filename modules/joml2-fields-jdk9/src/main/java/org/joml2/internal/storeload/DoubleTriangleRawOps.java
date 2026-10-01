// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface DoubleTriangleRawOps {
    DoubleTriangle storeUnsafe(DoubleTriangleImpl self, long address);
    DoubleTriangle loadUnsafe(DoubleTriangleImpl self, long address);
    DoubleTriangle storeFloatUnsafe(DoubleTriangleImpl self, long address);
    DoubleTriangle loadFloatUnsafe(DoubleTriangleImpl self, long address);
}
