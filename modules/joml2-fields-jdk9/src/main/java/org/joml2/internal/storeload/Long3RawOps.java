// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class Long3RawOps {
    public abstract Long3 storeUnsafe(Long3Impl self, long address);
    public abstract Long3 loadUnsafe(Long3Impl self, long address);
    public abstract Long3 storeIntUnsafe(Long3Impl self, long address);
    public abstract Long3 loadIntUnsafe(Long3Impl self, long address);
}
