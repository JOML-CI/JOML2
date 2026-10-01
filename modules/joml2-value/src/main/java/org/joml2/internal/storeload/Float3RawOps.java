// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface Float3RawOps {
    Float3 storeUnsafe(Float3 self, long address);
    Float3 loadUnsafe(long address);
    Float3 storeDoubleUnsafe(Float3 self, long address);
    Float3 loadDoubleUnsafe(long address);
}
