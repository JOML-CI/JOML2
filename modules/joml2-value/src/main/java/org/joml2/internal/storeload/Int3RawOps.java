// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface Int3RawOps {
    Int3 storeUnsafe(Int3 self, long address);
    Int3 loadUnsafe(long address);
    Int3 storeLongUnsafe(Int3 self, long address);
    Int3 loadLongUnsafe(long address);
}
