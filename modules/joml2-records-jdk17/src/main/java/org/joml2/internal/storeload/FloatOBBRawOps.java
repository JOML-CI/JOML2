// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class FloatOBBRawOps {
    public abstract FloatOBB storeUnsafe(FloatOBB self, long address);
    public abstract FloatOBB loadUnsafe(long address);
    public abstract FloatOBB storeDoubleUnsafe(FloatOBB self, long address);
    public abstract FloatOBB loadDoubleUnsafe(long address);
}
