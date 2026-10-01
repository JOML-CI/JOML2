// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface FloatOBBRawOps {
    FloatOBB storeUnsafe(FloatOBB self, long address);
    FloatOBB loadUnsafe(long address);
    FloatOBB storeDoubleUnsafe(FloatOBB self, long address);
    FloatOBB loadDoubleUnsafe(long address);
}
