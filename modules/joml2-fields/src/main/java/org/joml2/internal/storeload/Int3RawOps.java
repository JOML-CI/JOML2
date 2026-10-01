// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface Int3RawOps {
    Int3 storeUnsafe(Int3Impl self, long address);
    Int3 loadUnsafe(Int3Impl self, long address);
    Int3 storeLongUnsafe(Int3Impl self, long address);
    Int3 loadLongUnsafe(Int3Impl self, long address);
}
