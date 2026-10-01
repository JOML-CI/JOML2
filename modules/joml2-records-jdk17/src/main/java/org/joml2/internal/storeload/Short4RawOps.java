// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class Short4RawOps {
    public abstract Short4 storeUnsafe(Short4 self, long address);
    public abstract Short4 loadUnsafe(long address);
    public abstract Short4 storeByteUnsafe(Short4 self, long address);
    public abstract Short4 loadByteUnsafe(long address);
}
