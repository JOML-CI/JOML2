// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class DoubleQuatRawOps {
    public abstract DoubleQuat storeUnsafe(DoubleQuat self, long address);
    public abstract DoubleQuat loadUnsafe(long address);
    public abstract DoubleQuat storeFloatUnsafe(DoubleQuat self, long address);
    public abstract DoubleQuat loadFloatUnsafe(long address);
}
