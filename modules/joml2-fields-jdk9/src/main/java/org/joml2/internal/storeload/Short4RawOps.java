// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class Short4RawOps {
    public abstract Short4 storeUnsafe(Short4Impl self, long address);
    public abstract Short4 loadUnsafe(Short4Impl self, long address);
    public abstract Short4 storeByteUnsafe(Short4Impl self, long address);
    public abstract Short4 loadByteUnsafe(Short4Impl self, long address);
}
