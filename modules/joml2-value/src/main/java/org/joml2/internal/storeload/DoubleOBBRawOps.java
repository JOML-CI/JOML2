// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class DoubleOBBRawOps {
    public abstract DoubleOBB storeUnsafe(DoubleOBB self, long address);
    public abstract DoubleOBB loadUnsafe(long address);
    public abstract DoubleOBB storeFloatUnsafe(DoubleOBB self, long address);
    public abstract DoubleOBB loadFloatUnsafe(long address);
}
