// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface DoublePlaneRawOps {
    DoublePlane storeUnsafe(DoublePlane self, long address);
    DoublePlane loadUnsafe(long address);
    DoublePlane storeFloatUnsafe(DoublePlane self, long address);
    DoublePlane loadFloatUnsafe(long address);
}
