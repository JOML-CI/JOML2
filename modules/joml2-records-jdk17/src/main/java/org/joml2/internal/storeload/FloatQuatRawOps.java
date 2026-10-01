// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface FloatQuatRawOps {
    FloatQuat storeUnsafe(FloatQuat self, long address);
    FloatQuat loadUnsafe(long address);
    FloatQuat storeDoubleUnsafe(FloatQuat self, long address);
    FloatQuat loadDoubleUnsafe(long address);
}
