// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class FloatPlaneRawOps {
    public abstract FloatPlane storeUnsafe(FloatPlaneImpl self, long address);
    public abstract FloatPlane loadUnsafe(FloatPlaneImpl self, long address);
    public abstract FloatPlane storeDoubleUnsafe(FloatPlaneImpl self, long address);
    public abstract FloatPlane loadDoubleUnsafe(FloatPlaneImpl self, long address);
}
