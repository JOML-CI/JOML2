// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class DoublePlaneRawOps {
    public abstract DoublePlane storeUnsafe(DoublePlane self, long address);
    public abstract DoublePlane loadUnsafe(long address);
    public abstract DoublePlane storeFloatUnsafe(DoublePlane self, long address);
    public abstract DoublePlane loadFloatUnsafe(long address);
}
