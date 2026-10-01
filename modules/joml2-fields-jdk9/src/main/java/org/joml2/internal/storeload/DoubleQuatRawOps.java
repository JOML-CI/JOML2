// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface DoubleQuatRawOps {
    DoubleQuat storeUnsafe(DoubleQuatImpl self, long address);
    DoubleQuat loadUnsafe(DoubleQuatImpl self, long address);
    DoubleQuat storeFloatUnsafe(DoubleQuatImpl self, long address);
    DoubleQuat loadFloatUnsafe(DoubleQuatImpl self, long address);
}
