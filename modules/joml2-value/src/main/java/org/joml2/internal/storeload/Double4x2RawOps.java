// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface Double4x2RawOps {
    Double4x2 storeCMUnsafe(Double4x2 self, long address);
    Double4x2 loadCMUnsafe(long address);
    Double4x2 storeCMFloatUnsafe(Double4x2 self, long address);
    Double4x2 loadCMFloatUnsafe(long address);
    Double4x2 storeRMUnsafe(Double4x2 self, long address);
    Double4x2 loadRMUnsafe(long address);
    Double4x2 storeRMFloatUnsafe(Double4x2 self, long address);
    Double4x2 loadRMFloatUnsafe(long address);
    Double4x2 storeCMUnsafe(Double4x2 self, long address, int stride);
    Double4x2 loadCMUnsafe(long address, int stride);
    Double4x2 storeCMFloatUnsafe(Double4x2 self, long address, int stride);
    Double4x2 loadCMFloatUnsafe(long address, int stride);
    Double4x2 storeRMUnsafe(Double4x2 self, long address, int stride);
    Double4x2 loadRMUnsafe(long address, int stride);
    Double4x2 storeRMFloatUnsafe(Double4x2 self, long address, int stride);
    Double4x2 loadRMFloatUnsafe(long address, int stride);
}
