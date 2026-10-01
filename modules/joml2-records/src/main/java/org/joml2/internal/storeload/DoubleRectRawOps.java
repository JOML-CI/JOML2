// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class DoubleRectRawOps {
    public abstract DoubleRect storeUnsafe(DoubleRect self, long address);
    public abstract DoubleRect loadUnsafe(long address);
    public abstract DoubleRect storeFloatUnsafe(DoubleRect self, long address);
    public abstract DoubleRect loadFloatUnsafe(long address);
}
