// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface DoubleSphereRawOps {
    DoubleSphere storeUnsafe(DoubleSphereImpl self, long address);
    DoubleSphere loadUnsafe(DoubleSphereImpl self, long address);
    DoubleSphere storeFloatUnsafe(DoubleSphereImpl self, long address);
    DoubleSphere loadFloatUnsafe(DoubleSphereImpl self, long address);
}
