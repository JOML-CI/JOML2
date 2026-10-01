// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class Float4x2RawOps {
    public abstract Float4x2 storeCMUnsafe(Float4x2Impl self, long address);
    public abstract Float4x2 loadCMUnsafe(Float4x2Impl self, long address);
    public abstract Float4x2 storeCMDoubleUnsafe(Float4x2Impl self, long address);
    public abstract Float4x2 loadCMDoubleUnsafe(Float4x2Impl self, long address);
    public abstract Float4x2 storeRMUnsafe(Float4x2Impl self, long address);
    public abstract Float4x2 loadRMUnsafe(Float4x2Impl self, long address);
    public abstract Float4x2 storeRMDoubleUnsafe(Float4x2Impl self, long address);
    public abstract Float4x2 loadRMDoubleUnsafe(Float4x2Impl self, long address);
    public abstract Float4x2 storeCMUnsafe(Float4x2Impl self, long address, int stride);
    public abstract Float4x2 loadCMUnsafe(Float4x2Impl self, long address, int stride);
    public abstract Float4x2 storeCMDoubleUnsafe(Float4x2Impl self, long address, int stride);
    public abstract Float4x2 loadCMDoubleUnsafe(Float4x2Impl self, long address, int stride);
    public abstract Float4x2 storeRMUnsafe(Float4x2Impl self, long address, int stride);
    public abstract Float4x2 loadRMUnsafe(Float4x2Impl self, long address, int stride);
    public abstract Float4x2 storeRMDoubleUnsafe(Float4x2Impl self, long address, int stride);
    public abstract Float4x2 loadRMDoubleUnsafe(Float4x2Impl self, long address, int stride);
}
