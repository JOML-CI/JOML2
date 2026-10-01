// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface Int4RawOps {
    Int4 storeUnsafe(Int4 self, long address);
    Int4 loadUnsafe(long address);
    Int4 storeLongUnsafe(Int4 self, long address);
    Int4 loadLongUnsafe(long address);
}
