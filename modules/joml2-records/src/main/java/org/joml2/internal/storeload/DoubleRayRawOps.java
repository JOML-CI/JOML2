// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface DoubleRayRawOps {
    DoubleRay storeUnsafe(DoubleRay self, long address);
    DoubleRay loadUnsafe(long address);
    DoubleRay storeFloatUnsafe(DoubleRay self, long address);
    DoubleRay loadFloatUnsafe(long address);
}
