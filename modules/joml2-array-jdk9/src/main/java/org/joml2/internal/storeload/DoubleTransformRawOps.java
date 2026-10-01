// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface DoubleTransformRawOps {
    DoubleTransform storeUnsafe(DoubleTransformImpl self, long address);
    DoubleTransform loadUnsafe(DoubleTransformImpl self, long address);
    DoubleTransform storeFloatUnsafe(DoubleTransformImpl self, long address);
    DoubleTransform loadFloatUnsafe(DoubleTransformImpl self, long address);
}
