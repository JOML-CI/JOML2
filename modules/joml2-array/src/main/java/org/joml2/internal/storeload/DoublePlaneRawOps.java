// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface DoublePlaneRawOps {
    DoublePlane storeUnsafe(DoublePlaneImpl self, long address);
    DoublePlane loadUnsafe(DoublePlaneImpl self, long address);
    DoublePlane storeFloatUnsafe(DoublePlaneImpl self, long address);
    DoublePlane loadFloatUnsafe(DoublePlaneImpl self, long address);
}
