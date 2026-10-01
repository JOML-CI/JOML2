// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class Double2x4RawOps {
    public abstract Double2x4 storeCMUnsafe(Double2x4 self, long address);
    public abstract Double2x4 loadCMUnsafe(long address);
    public abstract Double2x4 storeCMFloatUnsafe(Double2x4 self, long address);
    public abstract Double2x4 loadCMFloatUnsafe(long address);
    public abstract Double2x4 storeRMUnsafe(Double2x4 self, long address);
    public abstract Double2x4 loadRMUnsafe(long address);
    public abstract Double2x4 storeRMFloatUnsafe(Double2x4 self, long address);
    public abstract Double2x4 loadRMFloatUnsafe(long address);
    public abstract Double2x4 storeCMUnsafe(Double2x4 self, long address, int stride);
    public abstract Double2x4 loadCMUnsafe(long address, int stride);
    public abstract Double2x4 storeCMFloatUnsafe(Double2x4 self, long address, int stride);
    public abstract Double2x4 loadCMFloatUnsafe(long address, int stride);
    public abstract Double2x4 storeRMUnsafe(Double2x4 self, long address, int stride);
    public abstract Double2x4 loadRMUnsafe(long address, int stride);
    public abstract Double2x4 storeRMFloatUnsafe(Double2x4 self, long address, int stride);
    public abstract Double2x4 loadRMFloatUnsafe(long address, int stride);
}
