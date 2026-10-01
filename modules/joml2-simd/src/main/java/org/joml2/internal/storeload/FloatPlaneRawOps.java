// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface FloatPlaneRawOps {
    FloatPlane storeUnsafe(FloatPlaneImpl self, long address);
    FloatPlane loadUnsafe(FloatPlaneImpl self, long address);
    FloatPlane storeDoubleUnsafe(FloatPlaneImpl self, long address);
    FloatPlane loadDoubleUnsafe(FloatPlaneImpl self, long address);
}
