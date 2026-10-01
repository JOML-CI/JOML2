// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface FloatDualQuatRawOps {
    FloatDualQuat storeUnsafe(FloatDualQuat self, long address);
    FloatDualQuat loadUnsafe(long address);
    FloatDualQuat storeDoubleUnsafe(FloatDualQuat self, long address);
    FloatDualQuat loadDoubleUnsafe(long address);
}
