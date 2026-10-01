// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class Double2x2RawOps {
    public abstract Double2x2 storeCMUnsafe(Double2x2 self, long address);
    public abstract Double2x2 loadCMUnsafe(long address);
    public abstract Double2x2 storeCMFloatUnsafe(Double2x2 self, long address);
    public abstract Double2x2 loadCMFloatUnsafe(long address);
    public abstract Double2x2 storeRMUnsafe(Double2x2 self, long address);
    public abstract Double2x2 loadRMUnsafe(long address);
    public abstract Double2x2 storeRMFloatUnsafe(Double2x2 self, long address);
    public abstract Double2x2 loadRMFloatUnsafe(long address);
    public abstract Double2x2 storeCMUnsafe(Double2x2 self, long address, int stride);
    public abstract Double2x2 loadCMUnsafe(long address, int stride);
    public abstract Double2x2 storeCMFloatUnsafe(Double2x2 self, long address, int stride);
    public abstract Double2x2 loadCMFloatUnsafe(long address, int stride);
    public abstract Double2x2 storeRMUnsafe(Double2x2 self, long address, int stride);
    public abstract Double2x2 loadRMUnsafe(long address, int stride);
    public abstract Double2x2 storeRMFloatUnsafe(Double2x2 self, long address, int stride);
    public abstract Double2x2 loadRMFloatUnsafe(long address, int stride);
    public abstract Double2x2 storeCM3x3Unsafe(Double2x2 self, long address);
    public abstract Double2x2 storeCM3x3FloatUnsafe(Double2x2 self, long address);
    public abstract Double2x2 storeRM3x3Unsafe(Double2x2 self, long address);
    public abstract Double2x2 storeRM3x3FloatUnsafe(Double2x2 self, long address);
    public abstract Double2x2 storeCM4x4Unsafe(Double2x2 self, long address);
    public abstract Double2x2 storeCM4x4FloatUnsafe(Double2x2 self, long address);
    public abstract Double2x2 storeRM4x4Unsafe(Double2x2 self, long address);
    public abstract Double2x2 storeRM4x4FloatUnsafe(Double2x2 self, long address);
}
