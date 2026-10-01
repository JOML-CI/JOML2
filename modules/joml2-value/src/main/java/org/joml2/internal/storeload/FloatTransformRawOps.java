// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface FloatTransformRawOps {
    FloatTransform storeUnsafe(FloatTransform self, long address);
    FloatTransform loadUnsafe(long address);
    FloatTransform storeDoubleUnsafe(FloatTransform self, long address);
    FloatTransform loadDoubleUnsafe(long address);
}
