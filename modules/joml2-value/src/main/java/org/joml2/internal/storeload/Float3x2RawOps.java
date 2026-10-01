// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class Float3x2RawOps {
    public abstract Float3x2 storeCMUnsafe(Float3x2 self, long address);
    public abstract Float3x2 loadCMUnsafe(long address);
    public abstract Float3x2 storeCMDoubleUnsafe(Float3x2 self, long address);
    public abstract Float3x2 loadCMDoubleUnsafe(long address);
    public abstract Float3x2 storeRMUnsafe(Float3x2 self, long address);
    public abstract Float3x2 loadRMUnsafe(long address);
    public abstract Float3x2 storeRMDoubleUnsafe(Float3x2 self, long address);
    public abstract Float3x2 loadRMDoubleUnsafe(long address);
    public abstract Float3x2 storeCMUnsafe(Float3x2 self, long address, int stride);
    public abstract Float3x2 loadCMUnsafe(long address, int stride);
    public abstract Float3x2 storeCMDoubleUnsafe(Float3x2 self, long address, int stride);
    public abstract Float3x2 loadCMDoubleUnsafe(long address, int stride);
    public abstract Float3x2 storeRMUnsafe(Float3x2 self, long address, int stride);
    public abstract Float3x2 loadRMUnsafe(long address, int stride);
    public abstract Float3x2 storeRMDoubleUnsafe(Float3x2 self, long address, int stride);
    public abstract Float3x2 loadRMDoubleUnsafe(long address, int stride);
}
