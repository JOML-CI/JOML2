// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class Float4x3RawOps {
    public abstract Float4x3 storeCMUnsafe(Float4x3Impl self, long address);
    public abstract Float4x3 loadCMUnsafe(Float4x3Impl self, long address);
    public abstract Float4x3 storeCMDoubleUnsafe(Float4x3Impl self, long address);
    public abstract Float4x3 loadCMDoubleUnsafe(Float4x3Impl self, long address);
    public abstract Float4x3 storeRMUnsafe(Float4x3Impl self, long address);
    public abstract Float4x3 loadRMUnsafe(Float4x3Impl self, long address);
    public abstract Float4x3 storeRMDoubleUnsafe(Float4x3Impl self, long address);
    public abstract Float4x3 loadRMDoubleUnsafe(Float4x3Impl self, long address);
    public abstract Float4x3 storeCMUnsafe(Float4x3Impl self, long address, int stride);
    public abstract Float4x3 loadCMUnsafe(Float4x3Impl self, long address, int stride);
    public abstract Float4x3 storeCMDoubleUnsafe(Float4x3Impl self, long address, int stride);
    public abstract Float4x3 loadCMDoubleUnsafe(Float4x3Impl self, long address, int stride);
    public abstract Float4x3 storeRMUnsafe(Float4x3Impl self, long address, int stride);
    public abstract Float4x3 loadRMUnsafe(Float4x3Impl self, long address, int stride);
    public abstract Float4x3 storeRMDoubleUnsafe(Float4x3Impl self, long address, int stride);
    public abstract Float4x3 loadRMDoubleUnsafe(Float4x3Impl self, long address, int stride);
}
