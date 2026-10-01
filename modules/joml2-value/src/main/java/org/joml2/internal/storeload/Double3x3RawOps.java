// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class Double3x3RawOps {
    public abstract Double3x3 storeCMUnsafe(Double3x3 self, long address);
    public abstract Double3x3 loadCMUnsafe(long address);
    public abstract Double3x3 storeCMFloatUnsafe(Double3x3 self, long address);
    public abstract Double3x3 loadCMFloatUnsafe(long address);
    public abstract Double3x3 storeRMUnsafe(Double3x3 self, long address);
    public abstract Double3x3 loadRMUnsafe(long address);
    public abstract Double3x3 storeRMFloatUnsafe(Double3x3 self, long address);
    public abstract Double3x3 loadRMFloatUnsafe(long address);
    public abstract Double3x3 storeCMUnsafe(Double3x3 self, long address, int stride);
    public abstract Double3x3 loadCMUnsafe(long address, int stride);
    public abstract Double3x3 storeCMFloatUnsafe(Double3x3 self, long address, int stride);
    public abstract Double3x3 loadCMFloatUnsafe(long address, int stride);
    public abstract Double3x3 storeRMUnsafe(Double3x3 self, long address, int stride);
    public abstract Double3x3 loadRMUnsafe(long address, int stride);
    public abstract Double3x3 storeRMFloatUnsafe(Double3x3 self, long address, int stride);
    public abstract Double3x3 loadRMFloatUnsafe(long address, int stride);
    public abstract Double3x3 storeCM4x4Unsafe(Double3x3 self, long address);
    public abstract Double3x3 storeCM4x4FloatUnsafe(Double3x3 self, long address);
    public abstract Double3x3 storeRM4x4Unsafe(Double3x3 self, long address);
    public abstract Double3x3 storeRM4x4FloatUnsafe(Double3x3 self, long address);
}
