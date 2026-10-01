// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface FloatSphereRawOps {
    FloatSphere storeUnsafe(FloatSphereImpl self, long address);
    FloatSphere loadUnsafe(FloatSphereImpl self, long address);
    FloatSphere storeDoubleUnsafe(FloatSphereImpl self, long address);
    FloatSphere loadDoubleUnsafe(FloatSphereImpl self, long address);
}
