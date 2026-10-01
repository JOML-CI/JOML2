// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface Int4RawOps {
    Int4 storeUnsafe(Int4Impl self, long address);
    Int4 loadUnsafe(Int4Impl self, long address);
    Int4 storeLongUnsafe(Int4Impl self, long address);
    Int4 loadLongUnsafe(Int4Impl self, long address);
}
