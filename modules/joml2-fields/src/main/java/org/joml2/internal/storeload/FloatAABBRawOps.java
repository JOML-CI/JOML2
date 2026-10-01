// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface FloatAABBRawOps {
    FloatAABB storeUnsafe(FloatAABBImpl self, long address);
    FloatAABB loadUnsafe(FloatAABBImpl self, long address);
    FloatAABB storeDoubleUnsafe(FloatAABBImpl self, long address);
    FloatAABB loadDoubleUnsafe(FloatAABBImpl self, long address);
}
