// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class Int3RawOps {
    public abstract Int3 storeUnsafe(Int3 self, long address);
    public abstract Int3 loadUnsafe(long address);
    public abstract Int3 storeLongUnsafe(Int3 self, long address);
    public abstract Int3 loadLongUnsafe(long address);
}
