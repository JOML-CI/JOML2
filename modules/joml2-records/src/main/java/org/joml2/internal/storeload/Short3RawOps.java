// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class Short3RawOps {
    public abstract Short3 storeUnsafe(Short3 self, long address);
    public abstract Short3 loadUnsafe(long address);
    public abstract Short3 storeByteUnsafe(Short3 self, long address);
    public abstract Short3 loadByteUnsafe(long address);
}
