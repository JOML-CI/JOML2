// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class Int2RawOps {
    public abstract Int2 storeUnsafe(Int2Impl self, long address);
    public abstract Int2 loadUnsafe(Int2Impl self, long address);
    public abstract Int2 storeLongUnsafe(Int2Impl self, long address);
    public abstract Int2 loadLongUnsafe(Int2Impl self, long address);
}
