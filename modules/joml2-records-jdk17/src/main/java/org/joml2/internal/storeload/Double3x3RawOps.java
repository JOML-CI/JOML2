// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface Double3x3RawOps {
    Double3x3 storeCMUnsafe(Double3x3 self, long address);
    Double3x3 loadCMUnsafe(long address);
    Double3x3 storeCMFloatUnsafe(Double3x3 self, long address);
    Double3x3 loadCMFloatUnsafe(long address);
    Double3x3 storeRMUnsafe(Double3x3 self, long address);
    Double3x3 loadRMUnsafe(long address);
    Double3x3 storeRMFloatUnsafe(Double3x3 self, long address);
    Double3x3 loadRMFloatUnsafe(long address);
    Double3x3 storeCMUnsafe(Double3x3 self, long address, int stride);
    Double3x3 loadCMUnsafe(long address, int stride);
    Double3x3 storeCMFloatUnsafe(Double3x3 self, long address, int stride);
    Double3x3 loadCMFloatUnsafe(long address, int stride);
    Double3x3 storeRMUnsafe(Double3x3 self, long address, int stride);
    Double3x3 loadRMUnsafe(long address, int stride);
    Double3x3 storeRMFloatUnsafe(Double3x3 self, long address, int stride);
    Double3x3 loadRMFloatUnsafe(long address, int stride);
    Double3x3 storeCM4x4Unsafe(Double3x3 self, long address);
    Double3x3 storeCM4x4FloatUnsafe(Double3x3 self, long address);
    Double3x3 storeRM4x4Unsafe(Double3x3 self, long address);
    Double3x3 storeRM4x4FloatUnsafe(Double3x3 self, long address);
}
