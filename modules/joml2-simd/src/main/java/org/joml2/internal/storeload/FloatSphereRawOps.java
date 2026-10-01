// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class FloatSphereRawOps {
    public abstract FloatSphere storeUnsafe(FloatSphereImpl self, long address);
    public abstract FloatSphere loadUnsafe(FloatSphereImpl self, long address);
    public abstract FloatSphere storeDoubleUnsafe(FloatSphereImpl self, long address);
    public abstract FloatSphere loadDoubleUnsafe(FloatSphereImpl self, long address);
}
