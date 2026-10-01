// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class Long3RawOps {
    public abstract Long3 storeUnsafe(Long3 self, long address);
    public abstract Long3 loadUnsafe(long address);
    public abstract Long3 storeIntUnsafe(Long3 self, long address);
    public abstract Long3 loadIntUnsafe(long address);
}
