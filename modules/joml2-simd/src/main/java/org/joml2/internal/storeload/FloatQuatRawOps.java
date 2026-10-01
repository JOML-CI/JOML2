// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface FloatQuatRawOps {
    FloatQuat storeUnsafe(FloatQuatImpl self, long address);
    FloatQuat loadUnsafe(FloatQuatImpl self, long address);
    FloatQuat storeDoubleUnsafe(FloatQuatImpl self, long address);
    FloatQuat loadDoubleUnsafe(FloatQuatImpl self, long address);
}
