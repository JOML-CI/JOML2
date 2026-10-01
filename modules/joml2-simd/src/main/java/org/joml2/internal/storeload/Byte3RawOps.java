// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class Byte3RawOps {
    public abstract Byte3 storeUnsafe(Byte3Impl self, long address);
    public abstract Byte3 loadUnsafe(Byte3Impl self, long address);
    public abstract Byte3 storeShortUnsafe(Byte3Impl self, long address);
    public abstract Byte3 loadShortUnsafe(Byte3Impl self, long address);
}
