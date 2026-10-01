// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class DoubleQuatRawOps {
    public abstract DoubleQuat storeUnsafe(DoubleQuatImpl self, long address);
    public abstract DoubleQuat loadUnsafe(DoubleQuatImpl self, long address);
    public abstract DoubleQuat storeFloatUnsafe(DoubleQuatImpl self, long address);
    public abstract DoubleQuat loadFloatUnsafe(DoubleQuatImpl self, long address);
}
