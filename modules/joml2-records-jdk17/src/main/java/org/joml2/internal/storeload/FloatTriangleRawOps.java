// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface FloatTriangleRawOps {
    FloatTriangle storeUnsafe(FloatTriangle self, long address);
    FloatTriangle loadUnsafe(long address);
    FloatTriangle storeDoubleUnsafe(FloatTriangle self, long address);
    FloatTriangle loadDoubleUnsafe(long address);
}
