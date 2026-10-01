// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface FloatRectRawOps {
    FloatRect storeUnsafe(FloatRectImpl self, long address);
    FloatRect loadUnsafe(FloatRectImpl self, long address);
    FloatRect storeDoubleUnsafe(FloatRectImpl self, long address);
    FloatRect loadDoubleUnsafe(FloatRectImpl self, long address);
}
