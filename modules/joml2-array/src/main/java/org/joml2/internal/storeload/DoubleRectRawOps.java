// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface DoubleRectRawOps {
    DoubleRect storeUnsafe(DoubleRectImpl self, long address);
    DoubleRect loadUnsafe(DoubleRectImpl self, long address);
    DoubleRect storeFloatUnsafe(DoubleRectImpl self, long address);
    DoubleRect loadFloatUnsafe(DoubleRectImpl self, long address);
}
