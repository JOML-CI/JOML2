// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class Long2RawOps {
    public abstract Long2 storeUnsafe(Long2 self, long address);
    public abstract Long2 loadUnsafe(long address);
    public abstract Long2 storeIntUnsafe(Long2 self, long address);
    public abstract Long2 loadIntUnsafe(long address);
}
