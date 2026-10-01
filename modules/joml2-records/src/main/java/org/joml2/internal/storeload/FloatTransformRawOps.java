// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class FloatTransformRawOps {
    public abstract FloatTransform storeUnsafe(FloatTransform self, long address);
    public abstract FloatTransform loadUnsafe(long address);
    public abstract FloatTransform storeDoubleUnsafe(FloatTransform self, long address);
    public abstract FloatTransform loadDoubleUnsafe(long address);
}
