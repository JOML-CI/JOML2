// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class DoublePlaneRawOps {
    public abstract DoublePlane storeUnsafe(DoublePlaneImpl self, long address);
    public abstract DoublePlane loadUnsafe(DoublePlaneImpl self, long address);
    public abstract DoublePlane storeFloatUnsafe(DoublePlaneImpl self, long address);
    public abstract DoublePlane loadFloatUnsafe(DoublePlaneImpl self, long address);
}
