// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class Float4x2RawOps {
    public abstract Float4x2 storeCMUnsafe(Float4x2 self, long address);
    public abstract Float4x2 loadCMUnsafe(long address);
    public abstract Float4x2 storeCMDoubleUnsafe(Float4x2 self, long address);
    public abstract Float4x2 loadCMDoubleUnsafe(long address);
    public abstract Float4x2 storeRMUnsafe(Float4x2 self, long address);
    public abstract Float4x2 loadRMUnsafe(long address);
    public abstract Float4x2 storeRMDoubleUnsafe(Float4x2 self, long address);
    public abstract Float4x2 loadRMDoubleUnsafe(long address);
    public abstract Float4x2 storeCMUnsafe(Float4x2 self, long address, int stride);
    public abstract Float4x2 loadCMUnsafe(long address, int stride);
    public abstract Float4x2 storeCMDoubleUnsafe(Float4x2 self, long address, int stride);
    public abstract Float4x2 loadCMDoubleUnsafe(long address, int stride);
    public abstract Float4x2 storeRMUnsafe(Float4x2 self, long address, int stride);
    public abstract Float4x2 loadRMUnsafe(long address, int stride);
    public abstract Float4x2 storeRMDoubleUnsafe(Float4x2 self, long address, int stride);
    public abstract Float4x2 loadRMDoubleUnsafe(long address, int stride);
}
