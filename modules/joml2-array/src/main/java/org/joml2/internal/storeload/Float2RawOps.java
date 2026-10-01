// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface Float2RawOps {
    Float2 storeUnsafe(Float2Impl self, long address);
    Float2 loadUnsafe(Float2Impl self, long address);
    Float2 storeDoubleUnsafe(Float2Impl self, long address);
    Float2 loadDoubleUnsafe(Float2Impl self, long address);
}
