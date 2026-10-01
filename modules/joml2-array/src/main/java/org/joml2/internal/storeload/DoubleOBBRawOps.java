// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface DoubleOBBRawOps {
    DoubleOBB storeUnsafe(DoubleOBBImpl self, long address);
    DoubleOBB loadUnsafe(DoubleOBBImpl self, long address);
    DoubleOBB storeFloatUnsafe(DoubleOBBImpl self, long address);
    DoubleOBB loadFloatUnsafe(DoubleOBBImpl self, long address);
}
