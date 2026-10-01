// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface Int2RawOps {
    Int2 storeUnsafe(Int2 self, long address);
    Int2 loadUnsafe(long address);
    Int2 storeLongUnsafe(Int2 self, long address);
    Int2 loadLongUnsafe(long address);
}
