// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class Float2x2RawOps {
    public abstract Float2x2 storeCMUnsafe(Float2x2Impl self, long address);
    public abstract Float2x2 loadCMUnsafe(Float2x2Impl self, long address);
    public abstract Float2x2 storeCMDoubleUnsafe(Float2x2Impl self, long address);
    public abstract Float2x2 loadCMDoubleUnsafe(Float2x2Impl self, long address);
    public abstract Float2x2 storeRMUnsafe(Float2x2Impl self, long address);
    public abstract Float2x2 loadRMUnsafe(Float2x2Impl self, long address);
    public abstract Float2x2 storeRMDoubleUnsafe(Float2x2Impl self, long address);
    public abstract Float2x2 loadRMDoubleUnsafe(Float2x2Impl self, long address);
    public abstract Float2x2 storeCMUnsafe(Float2x2Impl self, long address, int stride);
    public abstract Float2x2 loadCMUnsafe(Float2x2Impl self, long address, int stride);
    public abstract Float2x2 storeCMDoubleUnsafe(Float2x2Impl self, long address, int stride);
    public abstract Float2x2 loadCMDoubleUnsafe(Float2x2Impl self, long address, int stride);
    public abstract Float2x2 storeRMUnsafe(Float2x2Impl self, long address, int stride);
    public abstract Float2x2 loadRMUnsafe(Float2x2Impl self, long address, int stride);
    public abstract Float2x2 storeRMDoubleUnsafe(Float2x2Impl self, long address, int stride);
    public abstract Float2x2 loadRMDoubleUnsafe(Float2x2Impl self, long address, int stride);
    public abstract Float2x2 storeCM3x3Unsafe(Float2x2Impl self, long address);
    public abstract Float2x2 storeCM3x3DoubleUnsafe(Float2x2Impl self, long address);
    public abstract Float2x2 storeRM3x3Unsafe(Float2x2Impl self, long address);
    public abstract Float2x2 storeRM3x3DoubleUnsafe(Float2x2Impl self, long address);
    public abstract Float2x2 storeCM4x4Unsafe(Float2x2Impl self, long address);
    public abstract Float2x2 storeCM4x4DoubleUnsafe(Float2x2Impl self, long address);
    public abstract Float2x2 storeRM4x4Unsafe(Float2x2Impl self, long address);
    public abstract Float2x2 storeRM4x4DoubleUnsafe(Float2x2Impl self, long address);
}
