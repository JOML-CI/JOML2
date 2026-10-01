// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class DoubleTransformRawOps {
    public abstract DoubleTransform storeUnsafe(DoubleTransform self, long address);
    public abstract DoubleTransform loadUnsafe(long address);
    public abstract DoubleTransform storeFloatUnsafe(DoubleTransform self, long address);
    public abstract DoubleTransform loadFloatUnsafe(long address);
}
