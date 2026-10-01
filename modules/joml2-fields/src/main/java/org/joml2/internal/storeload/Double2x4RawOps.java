// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public abstract class Double2x4RawOps {
    public abstract Double2x4 storeCMUnsafe(Double2x4Impl self, long address);
    public abstract Double2x4 loadCMUnsafe(Double2x4Impl self, long address);
    public abstract Double2x4 storeCMFloatUnsafe(Double2x4Impl self, long address);
    public abstract Double2x4 loadCMFloatUnsafe(Double2x4Impl self, long address);
    public abstract Double2x4 storeRMUnsafe(Double2x4Impl self, long address);
    public abstract Double2x4 loadRMUnsafe(Double2x4Impl self, long address);
    public abstract Double2x4 storeRMFloatUnsafe(Double2x4Impl self, long address);
    public abstract Double2x4 loadRMFloatUnsafe(Double2x4Impl self, long address);
    public abstract Double2x4 storeCMUnsafe(Double2x4Impl self, long address, int stride);
    public abstract Double2x4 loadCMUnsafe(Double2x4Impl self, long address, int stride);
    public abstract Double2x4 storeCMFloatUnsafe(Double2x4Impl self, long address, int stride);
    public abstract Double2x4 loadCMFloatUnsafe(Double2x4Impl self, long address, int stride);
    public abstract Double2x4 storeRMUnsafe(Double2x4Impl self, long address, int stride);
    public abstract Double2x4 loadRMUnsafe(Double2x4Impl self, long address, int stride);
    public abstract Double2x4 storeRMFloatUnsafe(Double2x4Impl self, long address, int stride);
    public abstract Double2x4 loadRMFloatUnsafe(Double2x4Impl self, long address, int stride);
}
