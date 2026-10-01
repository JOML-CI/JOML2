// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class FloatQuatRawOps {
    public abstract FloatQuat storeUnsafe(FloatQuatImpl self, long address);
    public abstract FloatQuat loadUnsafe(FloatQuatImpl self, long address);
    public abstract FloatQuat storeDoubleUnsafe(FloatQuatImpl self, long address);
    public abstract FloatQuat loadDoubleUnsafe(FloatQuatImpl self, long address);
}
