// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class FloatOBBRawOps {
    public abstract FloatOBB storeUnsafe(FloatOBBImpl self, long address);
    public abstract FloatOBB loadUnsafe(FloatOBBImpl self, long address);
    public abstract FloatOBB storeDoubleUnsafe(FloatOBBImpl self, long address);
    public abstract FloatOBB loadDoubleUnsafe(FloatOBBImpl self, long address);
}
