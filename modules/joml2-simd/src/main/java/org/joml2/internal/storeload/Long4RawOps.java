// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class Long4RawOps {
    public abstract Long4 storeUnsafe(Long4Impl self, long address);
    public abstract Long4 loadUnsafe(Long4Impl self, long address);
    public abstract Long4 storeIntUnsafe(Long4Impl self, long address);
    public abstract Long4 loadIntUnsafe(Long4Impl self, long address);
}
