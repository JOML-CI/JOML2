// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class FloatPlaneRawOps {
    public abstract FloatPlane storeUnsafe(FloatPlane self, long address);
    public abstract FloatPlane loadUnsafe(long address);
    public abstract FloatPlane storeDoubleUnsafe(FloatPlane self, long address);
    public abstract FloatPlane loadDoubleUnsafe(long address);
}
