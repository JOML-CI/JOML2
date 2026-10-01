// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface DoubleTransformRawOps {
    DoubleTransform storeUnsafe(DoubleTransform self, long address);
    DoubleTransform loadUnsafe(long address);
    DoubleTransform storeFloatUnsafe(DoubleTransform self, long address);
    DoubleTransform loadFloatUnsafe(long address);
}
