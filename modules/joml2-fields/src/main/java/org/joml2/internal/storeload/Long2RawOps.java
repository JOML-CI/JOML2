// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class Long2RawOps {
    public abstract Long2 storeUnsafe(Long2Impl self, long address);
    public abstract Long2 loadUnsafe(Long2Impl self, long address);
    public abstract Long2 storeIntUnsafe(Long2Impl self, long address);
    public abstract Long2 loadIntUnsafe(Long2Impl self, long address);
}
