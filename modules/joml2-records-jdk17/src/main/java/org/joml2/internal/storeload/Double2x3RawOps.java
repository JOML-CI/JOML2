// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface Double2x3RawOps {
    Double2x3 storeCMUnsafe(Double2x3 self, long address);
    Double2x3 loadCMUnsafe(long address);
    Double2x3 storeCMFloatUnsafe(Double2x3 self, long address);
    Double2x3 loadCMFloatUnsafe(long address);
    Double2x3 storeRMUnsafe(Double2x3 self, long address);
    Double2x3 loadRMUnsafe(long address);
    Double2x3 storeRMFloatUnsafe(Double2x3 self, long address);
    Double2x3 loadRMFloatUnsafe(long address);
    Double2x3 storeCMUnsafe(Double2x3 self, long address, int stride);
    Double2x3 loadCMUnsafe(long address, int stride);
    Double2x3 storeCMFloatUnsafe(Double2x3 self, long address, int stride);
    Double2x3 loadCMFloatUnsafe(long address, int stride);
    Double2x3 storeRMUnsafe(Double2x3 self, long address, int stride);
    Double2x3 loadRMUnsafe(long address, int stride);
    Double2x3 storeRMFloatUnsafe(Double2x3 self, long address, int stride);
    Double2x3 loadRMFloatUnsafe(long address, int stride);
    Double2x3 storeCM3x3Unsafe(Double2x3 self, long address);
    Double2x3 storeCM3x3FloatUnsafe(Double2x3 self, long address);
    Double2x3 storeRM3x3Unsafe(Double2x3 self, long address);
    Double2x3 storeRM3x3FloatUnsafe(Double2x3 self, long address);
    Double2x3 storeCM4x4Unsafe(Double2x3 self, long address);
    Double2x3 storeCM4x4FloatUnsafe(Double2x3 self, long address);
    Double2x3 storeRM4x4Unsafe(Double2x3 self, long address);
    Double2x3 storeRM4x4FloatUnsafe(Double2x3 self, long address);
}
