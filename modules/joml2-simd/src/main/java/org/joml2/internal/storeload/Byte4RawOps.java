// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class Byte4RawOps {
    public abstract Byte4 storeUnsafe(Byte4Impl self, long address);
    public abstract Byte4 loadUnsafe(Byte4Impl self, long address);
    public abstract Byte4 storeShortUnsafe(Byte4Impl self, long address);
    public abstract Byte4 loadShortUnsafe(Byte4Impl self, long address);
}
