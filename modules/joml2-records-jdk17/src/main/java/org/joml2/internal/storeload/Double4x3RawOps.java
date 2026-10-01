// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class Double4x3RawOps {
    public abstract Double4x3 storeCMUnsafe(Double4x3 self, long address);
    public abstract Double4x3 loadCMUnsafe(long address);
    public abstract Double4x3 storeCMFloatUnsafe(Double4x3 self, long address);
    public abstract Double4x3 loadCMFloatUnsafe(long address);
    public abstract Double4x3 storeRMUnsafe(Double4x3 self, long address);
    public abstract Double4x3 loadRMUnsafe(long address);
    public abstract Double4x3 storeRMFloatUnsafe(Double4x3 self, long address);
    public abstract Double4x3 loadRMFloatUnsafe(long address);
    public abstract Double4x3 storeCMUnsafe(Double4x3 self, long address, int stride);
    public abstract Double4x3 loadCMUnsafe(long address, int stride);
    public abstract Double4x3 storeCMFloatUnsafe(Double4x3 self, long address, int stride);
    public abstract Double4x3 loadCMFloatUnsafe(long address, int stride);
    public abstract Double4x3 storeRMUnsafe(Double4x3 self, long address, int stride);
    public abstract Double4x3 loadRMUnsafe(long address, int stride);
    public abstract Double4x3 storeRMFloatUnsafe(Double4x3 self, long address, int stride);
    public abstract Double4x3 loadRMFloatUnsafe(long address, int stride);
}
