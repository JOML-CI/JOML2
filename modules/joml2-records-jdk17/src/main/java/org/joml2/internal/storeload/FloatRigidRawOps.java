// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface FloatRigidRawOps {
    FloatRigid storeUnsafe(FloatRigid self, long address);
    FloatRigid loadUnsafe(long address);
    FloatRigid storeDoubleUnsafe(FloatRigid self, long address);
    FloatRigid loadDoubleUnsafe(long address);
}
