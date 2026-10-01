// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class Float3x3RawOps {
    public abstract Float3x3 storeCMUnsafe(Float3x3 self, long address);
    public abstract Float3x3 loadCMUnsafe(long address);
    public abstract Float3x3 storeCMDoubleUnsafe(Float3x3 self, long address);
    public abstract Float3x3 loadCMDoubleUnsafe(long address);
    public abstract Float3x3 storeRMUnsafe(Float3x3 self, long address);
    public abstract Float3x3 loadRMUnsafe(long address);
    public abstract Float3x3 storeRMDoubleUnsafe(Float3x3 self, long address);
    public abstract Float3x3 loadRMDoubleUnsafe(long address);
    public abstract Float3x3 storeCMUnsafe(Float3x3 self, long address, int stride);
    public abstract Float3x3 loadCMUnsafe(long address, int stride);
    public abstract Float3x3 storeCMDoubleUnsafe(Float3x3 self, long address, int stride);
    public abstract Float3x3 loadCMDoubleUnsafe(long address, int stride);
    public abstract Float3x3 storeRMUnsafe(Float3x3 self, long address, int stride);
    public abstract Float3x3 loadRMUnsafe(long address, int stride);
    public abstract Float3x3 storeRMDoubleUnsafe(Float3x3 self, long address, int stride);
    public abstract Float3x3 loadRMDoubleUnsafe(long address, int stride);
    public abstract Float3x3 storeCM4x4Unsafe(Float3x3 self, long address);
    public abstract Float3x3 storeCM4x4DoubleUnsafe(Float3x3 self, long address);
    public abstract Float3x3 storeRM4x4Unsafe(Float3x3 self, long address);
    public abstract Float3x3 storeRM4x4DoubleUnsafe(Float3x3 self, long address);
}
