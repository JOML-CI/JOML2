// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class FloatTransformRawOps {
    public abstract FloatTransform storeUnsafe(FloatTransformImpl self, long address);
    public abstract FloatTransform loadUnsafe(FloatTransformImpl self, long address);
    public abstract FloatTransform storeDoubleUnsafe(FloatTransformImpl self, long address);
    public abstract FloatTransform loadDoubleUnsafe(FloatTransformImpl self, long address);
}
