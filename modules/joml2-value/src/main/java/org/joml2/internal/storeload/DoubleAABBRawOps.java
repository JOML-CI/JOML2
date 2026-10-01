// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface DoubleAABBRawOps {
    DoubleAABB storeUnsafe(DoubleAABB self, long address);
    DoubleAABB loadUnsafe(long address);
    DoubleAABB storeFloatUnsafe(DoubleAABB self, long address);
    DoubleAABB loadFloatUnsafe(long address);
}
