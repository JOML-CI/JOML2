// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class Byte3RawOps {
    public abstract Byte3 storeUnsafe(Byte3 self, long address);
    public abstract Byte3 loadUnsafe(long address);
    public abstract Byte3 storeShortUnsafe(Byte3 self, long address);
    public abstract Byte3 loadShortUnsafe(long address);
}
