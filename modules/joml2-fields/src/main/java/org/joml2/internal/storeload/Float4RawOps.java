// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface Float4RawOps {
    Float4 storeUnsafe(Float4Impl self, long address);
    Float4 loadUnsafe(Float4Impl self, long address);
    Float4 storeDoubleUnsafe(Float4Impl self, long address);
    Float4 loadDoubleUnsafe(Float4Impl self, long address);
}
