// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class FloatRigidRawOps {
    public abstract FloatRigid storeUnsafe(FloatRigidImpl self, long address);
    public abstract FloatRigid loadUnsafe(FloatRigidImpl self, long address);
    public abstract FloatRigid storeDoubleUnsafe(FloatRigidImpl self, long address);
    public abstract FloatRigid loadDoubleUnsafe(FloatRigidImpl self, long address);
}
