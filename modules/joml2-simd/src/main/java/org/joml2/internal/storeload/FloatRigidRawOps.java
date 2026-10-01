// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface FloatRigidRawOps {
    FloatRigid storeUnsafe(FloatRigidImpl self, long address);
    FloatRigid loadUnsafe(FloatRigidImpl self, long address);
    FloatRigid storeDoubleUnsafe(FloatRigidImpl self, long address);
    FloatRigid loadDoubleUnsafe(FloatRigidImpl self, long address);
}
