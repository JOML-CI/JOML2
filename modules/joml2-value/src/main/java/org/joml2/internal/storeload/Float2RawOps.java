// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class Float2RawOps {
    public abstract Float2 storeUnsafe(Float2 self, long address);
    public abstract Float2 loadUnsafe(long address);
    public abstract Float2 storeDoubleUnsafe(Float2 self, long address);
    public abstract Float2 loadDoubleUnsafe(long address);
}
