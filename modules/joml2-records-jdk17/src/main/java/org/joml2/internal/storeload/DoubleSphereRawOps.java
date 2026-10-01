// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class DoubleSphereRawOps {
    public abstract DoubleSphere storeUnsafe(DoubleSphere self, long address);
    public abstract DoubleSphere loadUnsafe(long address);
    public abstract DoubleSphere storeFloatUnsafe(DoubleSphere self, long address);
    public abstract DoubleSphere loadFloatUnsafe(long address);
}
