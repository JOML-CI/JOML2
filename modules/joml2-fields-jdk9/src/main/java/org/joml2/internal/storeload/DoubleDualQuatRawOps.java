// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface DoubleDualQuatRawOps {
    DoubleDualQuat storeUnsafe(DoubleDualQuatImpl self, long address);
    DoubleDualQuat loadUnsafe(DoubleDualQuatImpl self, long address);
    DoubleDualQuat storeFloatUnsafe(DoubleDualQuatImpl self, long address);
    DoubleDualQuat loadFloatUnsafe(DoubleDualQuatImpl self, long address);
}
