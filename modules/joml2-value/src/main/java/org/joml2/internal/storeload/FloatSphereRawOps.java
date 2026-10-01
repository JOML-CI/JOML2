// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class FloatSphereRawOps {
    public abstract FloatSphere storeUnsafe(FloatSphere self, long address);
    public abstract FloatSphere loadUnsafe(long address);
    public abstract FloatSphere storeDoubleUnsafe(FloatSphere self, long address);
    public abstract FloatSphere loadDoubleUnsafe(long address);
}
