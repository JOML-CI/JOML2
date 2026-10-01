// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface Double3RawOps {
    Double3 storeUnsafe(Double3Impl self, long address);
    Double3 loadUnsafe(Double3Impl self, long address);
    Double3 storeFloatUnsafe(Double3Impl self, long address);
    Double3 loadFloatUnsafe(Double3Impl self, long address);
}
