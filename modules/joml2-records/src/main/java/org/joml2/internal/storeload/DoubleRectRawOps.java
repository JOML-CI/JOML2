// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface DoubleRectRawOps {
    DoubleRect storeUnsafe(DoubleRect self, long address);
    DoubleRect loadUnsafe(long address);
    DoubleRect storeFloatUnsafe(DoubleRect self, long address);
    DoubleRect loadFloatUnsafe(long address);
}
