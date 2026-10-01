// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class FloatRigidRawOps {
    public abstract FloatRigid storeUnsafe(FloatRigid self, long address);
    public abstract FloatRigid loadUnsafe(long address);
    public abstract FloatRigid storeDoubleUnsafe(FloatRigid self, long address);
    public abstract FloatRigid loadDoubleUnsafe(long address);
}
