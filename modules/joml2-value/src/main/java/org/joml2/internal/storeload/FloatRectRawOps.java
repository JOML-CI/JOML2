// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class FloatRectRawOps {
    public abstract FloatRect storeUnsafe(FloatRect self, long address);
    public abstract FloatRect loadUnsafe(long address);
    public abstract FloatRect storeDoubleUnsafe(FloatRect self, long address);
    public abstract FloatRect loadDoubleUnsafe(long address);
}
