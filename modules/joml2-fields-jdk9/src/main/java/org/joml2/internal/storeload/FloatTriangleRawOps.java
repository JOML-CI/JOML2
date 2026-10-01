// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class FloatTriangleRawOps {
    public abstract FloatTriangle storeUnsafe(FloatTriangleImpl self, long address);
    public abstract FloatTriangle loadUnsafe(FloatTriangleImpl self, long address);
    public abstract FloatTriangle storeDoubleUnsafe(FloatTriangleImpl self, long address);
    public abstract FloatTriangle loadDoubleUnsafe(FloatTriangleImpl self, long address);
}
