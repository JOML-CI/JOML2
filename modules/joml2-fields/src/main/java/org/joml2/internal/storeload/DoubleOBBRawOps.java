// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class DoubleOBBRawOps {
    public abstract DoubleOBB storeUnsafe(DoubleOBBImpl self, long address);
    public abstract DoubleOBB loadUnsafe(DoubleOBBImpl self, long address);
    public abstract DoubleOBB storeFloatUnsafe(DoubleOBBImpl self, long address);
    public abstract DoubleOBB loadFloatUnsafe(DoubleOBBImpl self, long address);
}
