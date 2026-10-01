// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface FloatTriangleRawOps {
    FloatTriangle storeUnsafe(FloatTriangleImpl self, long address);
    FloatTriangle loadUnsafe(FloatTriangleImpl self, long address);
    FloatTriangle storeDoubleUnsafe(FloatTriangleImpl self, long address);
    FloatTriangle loadDoubleUnsafe(FloatTriangleImpl self, long address);
}
