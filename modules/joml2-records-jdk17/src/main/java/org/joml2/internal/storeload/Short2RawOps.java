// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class Short2RawOps {
    public abstract Short2 storeUnsafe(Short2 self, long address);
    public abstract Short2 loadUnsafe(long address);
    public abstract Short2 storeByteUnsafe(Short2 self, long address);
    public abstract Short2 loadByteUnsafe(long address);
}
