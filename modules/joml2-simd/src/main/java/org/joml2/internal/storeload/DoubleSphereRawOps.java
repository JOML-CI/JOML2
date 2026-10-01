// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class DoubleSphereRawOps {
    public abstract DoubleSphere storeUnsafe(DoubleSphereImpl self, long address);
    public abstract DoubleSphere loadUnsafe(DoubleSphereImpl self, long address);
    public abstract DoubleSphere storeFloatUnsafe(DoubleSphereImpl self, long address);
    public abstract DoubleSphere loadFloatUnsafe(DoubleSphereImpl self, long address);
}
