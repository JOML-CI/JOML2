// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface Double4RawOps {
    Double4 storeUnsafe(Double4Impl self, long address);
    Double4 loadUnsafe(Double4Impl self, long address);
    Double4 storeFloatUnsafe(Double4Impl self, long address);
    Double4 loadFloatUnsafe(Double4Impl self, long address);
}
