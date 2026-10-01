// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface Double2RawOps {
    Double2 storeUnsafe(Double2Impl self, long address);
    Double2 loadUnsafe(Double2Impl self, long address);
    Double2 storeFloatUnsafe(Double2Impl self, long address);
    Double2 loadFloatUnsafe(Double2Impl self, long address);
}
