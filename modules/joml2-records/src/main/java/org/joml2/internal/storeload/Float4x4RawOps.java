// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class Float4x4RawOps {
    public abstract Float4x4 storeCMUnsafe(Float4x4 self, long address);
    public abstract Float4x4 loadCMUnsafe(long address);
    public abstract Float4x4 storeCMDoubleUnsafe(Float4x4 self, long address);
    public abstract Float4x4 loadCMDoubleUnsafe(long address);
    public abstract Float4x4 storeRMUnsafe(Float4x4 self, long address);
    public abstract Float4x4 loadRMUnsafe(long address);
    public abstract Float4x4 storeRMDoubleUnsafe(Float4x4 self, long address);
    public abstract Float4x4 loadRMDoubleUnsafe(long address);
    public abstract Float4x4 storeCMUnsafe(Float4x4 self, long address, int stride);
    public abstract Float4x4 loadCMUnsafe(long address, int stride);
    public abstract Float4x4 storeCMDoubleUnsafe(Float4x4 self, long address, int stride);
    public abstract Float4x4 loadCMDoubleUnsafe(long address, int stride);
    public abstract Float4x4 storeRMUnsafe(Float4x4 self, long address, int stride);
    public abstract Float4x4 loadRMUnsafe(long address, int stride);
    public abstract Float4x4 storeRMDoubleUnsafe(Float4x4 self, long address, int stride);
    public abstract Float4x4 loadRMDoubleUnsafe(long address, int stride);
}
