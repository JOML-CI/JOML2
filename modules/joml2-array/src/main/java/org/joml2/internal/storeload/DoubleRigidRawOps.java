// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class DoubleRigidRawOps {
    public abstract DoubleRigid storeUnsafe(DoubleRigidImpl self, long address);
    public abstract DoubleRigid loadUnsafe(DoubleRigidImpl self, long address);
    public abstract DoubleRigid storeFloatUnsafe(DoubleRigidImpl self, long address);
    public abstract DoubleRigid loadFloatUnsafe(DoubleRigidImpl self, long address);
}
