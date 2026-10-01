// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface Double2RawOps {
    Double2 storeUnsafe(Double2 self, long address);
    Double2 loadUnsafe(long address);
    Double2 storeFloatUnsafe(Double2 self, long address);
    Double2 loadFloatUnsafe(long address);
}
