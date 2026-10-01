// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class Int3RawOps {
    public abstract Int3 storeUnsafe(Int3Impl self, long address);
    public abstract Int3 loadUnsafe(Int3Impl self, long address);
    public abstract Int3 storeLongUnsafe(Int3Impl self, long address);
    public abstract Int3 loadLongUnsafe(Int3Impl self, long address);
}
