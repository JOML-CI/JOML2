// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class Float3RawOps {
    public abstract Float3 storeUnsafe(Float3 self, long address);
    public abstract Float3 loadUnsafe(long address);
    public abstract Float3 storeDoubleUnsafe(Float3 self, long address);
    public abstract Float3 loadDoubleUnsafe(long address);
}
