// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface Double4x3RawOps {
    Double4x3 storeCMUnsafe(Double4x3 self, long address);
    Double4x3 loadCMUnsafe(long address);
    Double4x3 storeCMFloatUnsafe(Double4x3 self, long address);
    Double4x3 loadCMFloatUnsafe(long address);
    Double4x3 storeRMUnsafe(Double4x3 self, long address);
    Double4x3 loadRMUnsafe(long address);
    Double4x3 storeRMFloatUnsafe(Double4x3 self, long address);
    Double4x3 loadRMFloatUnsafe(long address);
    Double4x3 storeCMUnsafe(Double4x3 self, long address, int stride);
    Double4x3 loadCMUnsafe(long address, int stride);
    Double4x3 storeCMFloatUnsafe(Double4x3 self, long address, int stride);
    Double4x3 loadCMFloatUnsafe(long address, int stride);
    Double4x3 storeRMUnsafe(Double4x3 self, long address, int stride);
    Double4x3 loadRMUnsafe(long address, int stride);
    Double4x3 storeRMFloatUnsafe(Double4x3 self, long address, int stride);
    Double4x3 loadRMFloatUnsafe(long address, int stride);
}
