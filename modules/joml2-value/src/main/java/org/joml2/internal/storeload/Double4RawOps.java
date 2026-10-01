// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface Double4RawOps {
    Double4 storeUnsafe(Double4 self, long address);
    Double4 loadUnsafe(long address);
    Double4 storeFloatUnsafe(Double4 self, long address);
    Double4 loadFloatUnsafe(long address);
}
