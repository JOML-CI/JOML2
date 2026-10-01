// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class Float3RawOps {
    public abstract Float3 storeUnsafe(Float3Impl self, long address);
    public abstract Float3 loadUnsafe(Float3Impl self, long address);
    public abstract Float3 storeDoubleUnsafe(Float3Impl self, long address);
    public abstract Float3 loadDoubleUnsafe(Float3Impl self, long address);
}
