// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class Int4RawOps {
    public abstract Int4 storeUnsafe(Int4 self, long address);
    public abstract Int4 loadUnsafe(long address);
    public abstract Int4 storeLongUnsafe(Int4 self, long address);
    public abstract Int4 loadLongUnsafe(long address);
}
