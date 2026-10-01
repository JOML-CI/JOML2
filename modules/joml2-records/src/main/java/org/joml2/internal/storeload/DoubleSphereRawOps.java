// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface DoubleSphereRawOps {
    DoubleSphere storeUnsafe(DoubleSphere self, long address);
    DoubleSphere loadUnsafe(long address);
    DoubleSphere storeFloatUnsafe(DoubleSphere self, long address);
    DoubleSphere loadFloatUnsafe(long address);
}
