// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class FloatAABBRawOps {
    public abstract FloatAABB storeUnsafe(FloatAABB self, long address);
    public abstract FloatAABB loadUnsafe(long address);
    public abstract FloatAABB storeDoubleUnsafe(FloatAABB self, long address);
    public abstract FloatAABB loadDoubleUnsafe(long address);
}
