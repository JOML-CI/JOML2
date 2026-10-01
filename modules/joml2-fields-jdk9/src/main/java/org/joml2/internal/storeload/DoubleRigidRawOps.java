// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface DoubleRigidRawOps {
    DoubleRigid storeUnsafe(DoubleRigidImpl self, long address);
    DoubleRigid loadUnsafe(DoubleRigidImpl self, long address);
    DoubleRigid storeFloatUnsafe(DoubleRigidImpl self, long address);
    DoubleRigid loadFloatUnsafe(DoubleRigidImpl self, long address);
}
