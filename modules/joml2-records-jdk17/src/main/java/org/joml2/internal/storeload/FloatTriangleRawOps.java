// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class FloatTriangleRawOps {
    public abstract FloatTriangle storeUnsafe(FloatTriangle self, long address);
    public abstract FloatTriangle loadUnsafe(long address);
    public abstract FloatTriangle storeDoubleUnsafe(FloatTriangle self, long address);
    public abstract FloatTriangle loadDoubleUnsafe(long address);
}
