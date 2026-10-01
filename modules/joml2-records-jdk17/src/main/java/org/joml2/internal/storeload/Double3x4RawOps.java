// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class Double3x4RawOps {
    public abstract Double3x4 storeCMUnsafe(Double3x4 self, long address);
    public abstract Double3x4 loadCMUnsafe(long address);
    public abstract Double3x4 storeCMFloatUnsafe(Double3x4 self, long address);
    public abstract Double3x4 loadCMFloatUnsafe(long address);
    public abstract Double3x4 storeRMUnsafe(Double3x4 self, long address);
    public abstract Double3x4 loadRMUnsafe(long address);
    public abstract Double3x4 storeRMFloatUnsafe(Double3x4 self, long address);
    public abstract Double3x4 loadRMFloatUnsafe(long address);
    public abstract Double3x4 storeCMUnsafe(Double3x4 self, long address, int stride);
    public abstract Double3x4 loadCMUnsafe(long address, int stride);
    public abstract Double3x4 storeCMFloatUnsafe(Double3x4 self, long address, int stride);
    public abstract Double3x4 loadCMFloatUnsafe(long address, int stride);
    public abstract Double3x4 storeRMUnsafe(Double3x4 self, long address, int stride);
    public abstract Double3x4 loadRMUnsafe(long address, int stride);
    public abstract Double3x4 storeRMFloatUnsafe(Double3x4 self, long address, int stride);
    public abstract Double3x4 loadRMFloatUnsafe(long address, int stride);
    public abstract Double3x4 storeCM4x4Unsafe(Double3x4 self, long address);
    public abstract Double3x4 storeCM4x4FloatUnsafe(Double3x4 self, long address);
    public abstract Double3x4 storeRM4x4Unsafe(Double3x4 self, long address);
    public abstract Double3x4 storeRM4x4FloatUnsafe(Double3x4 self, long address);
}
