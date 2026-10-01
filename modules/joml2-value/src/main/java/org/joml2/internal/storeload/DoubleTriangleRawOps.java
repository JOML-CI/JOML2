// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface DoubleTriangleRawOps {
    DoubleTriangle storeUnsafe(DoubleTriangle self, long address);
    DoubleTriangle loadUnsafe(long address);
    DoubleTriangle storeFloatUnsafe(DoubleTriangle self, long address);
    DoubleTriangle loadFloatUnsafe(long address);
}
