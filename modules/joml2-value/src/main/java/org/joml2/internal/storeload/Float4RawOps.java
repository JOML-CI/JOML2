// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface Float4RawOps {
    Float4 storeUnsafe(Float4 self, long address);
    Float4 loadUnsafe(long address);
    Float4 storeDoubleUnsafe(Float4 self, long address);
    Float4 loadDoubleUnsafe(long address);
}
