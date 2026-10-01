// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class DoubleRigidRawOps {
    public abstract DoubleRigid storeUnsafe(DoubleRigid self, long address);
    public abstract DoubleRigid loadUnsafe(long address);
    public abstract DoubleRigid storeFloatUnsafe(DoubleRigid self, long address);
    public abstract DoubleRigid loadFloatUnsafe(long address);
}
