// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface FloatPlaneRawOps {
    FloatPlane storeUnsafe(FloatPlane self, long address);
    FloatPlane loadUnsafe(long address);
    FloatPlane storeDoubleUnsafe(FloatPlane self, long address);
    FloatPlane loadDoubleUnsafe(long address);
}
