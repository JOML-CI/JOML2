// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface FloatRectRawOps {
    FloatRect storeUnsafe(FloatRect self, long address);
    FloatRect loadUnsafe(long address);
    FloatRect storeDoubleUnsafe(FloatRect self, long address);
    FloatRect loadDoubleUnsafe(long address);
}
