// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class Short3RawOps {
    public abstract Short3 storeUnsafe(Short3Impl self, long address);
    public abstract Short3 loadUnsafe(Short3Impl self, long address);
    public abstract Short3 storeByteUnsafe(Short3Impl self, long address);
    public abstract Short3 loadByteUnsafe(Short3Impl self, long address);
}
