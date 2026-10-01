// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class Long4RawOps {
    public abstract Long4 storeUnsafe(Long4 self, long address);
    public abstract Long4 loadUnsafe(long address);
    public abstract Long4 storeIntUnsafe(Long4 self, long address);
    public abstract Long4 loadIntUnsafe(long address);
}
