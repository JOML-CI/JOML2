// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface Double4x4RawOps {
    Double4x4 storeCMUnsafe(Double4x4 self, long address);
    Double4x4 loadCMUnsafe(long address);
    Double4x4 storeCMFloatUnsafe(Double4x4 self, long address);
    Double4x4 loadCMFloatUnsafe(long address);
    Double4x4 storeRMUnsafe(Double4x4 self, long address);
    Double4x4 loadRMUnsafe(long address);
    Double4x4 storeRMFloatUnsafe(Double4x4 self, long address);
    Double4x4 loadRMFloatUnsafe(long address);
    Double4x4 storeCMUnsafe(Double4x4 self, long address, int stride);
    Double4x4 loadCMUnsafe(long address, int stride);
    Double4x4 storeCMFloatUnsafe(Double4x4 self, long address, int stride);
    Double4x4 loadCMFloatUnsafe(long address, int stride);
    Double4x4 storeRMUnsafe(Double4x4 self, long address, int stride);
    Double4x4 loadRMUnsafe(long address, int stride);
    Double4x4 storeRMFloatUnsafe(Double4x4 self, long address, int stride);
    Double4x4 loadRMFloatUnsafe(long address, int stride);
}
