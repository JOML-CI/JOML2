// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface Double2x4RawOps {
    Double2x4 storeCMUnsafe(Double2x4 self, long address);
    Double2x4 loadCMUnsafe(long address);
    Double2x4 storeCMFloatUnsafe(Double2x4 self, long address);
    Double2x4 loadCMFloatUnsafe(long address);
    Double2x4 storeRMUnsafe(Double2x4 self, long address);
    Double2x4 loadRMUnsafe(long address);
    Double2x4 storeRMFloatUnsafe(Double2x4 self, long address);
    Double2x4 loadRMFloatUnsafe(long address);
    Double2x4 storeCMUnsafe(Double2x4 self, long address, int stride);
    Double2x4 loadCMUnsafe(long address, int stride);
    Double2x4 storeCMFloatUnsafe(Double2x4 self, long address, int stride);
    Double2x4 loadCMFloatUnsafe(long address, int stride);
    Double2x4 storeRMUnsafe(Double2x4 self, long address, int stride);
    Double2x4 loadRMUnsafe(long address, int stride);
    Double2x4 storeRMFloatUnsafe(Double2x4 self, long address, int stride);
    Double2x4 loadRMFloatUnsafe(long address, int stride);
}
