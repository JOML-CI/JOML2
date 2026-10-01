// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface Double3x2RawOps {
    Double3x2 storeCMUnsafe(Double3x2 self, long address);
    Double3x2 loadCMUnsafe(long address);
    Double3x2 storeCMFloatUnsafe(Double3x2 self, long address);
    Double3x2 loadCMFloatUnsafe(long address);
    Double3x2 storeRMUnsafe(Double3x2 self, long address);
    Double3x2 loadRMUnsafe(long address);
    Double3x2 storeRMFloatUnsafe(Double3x2 self, long address);
    Double3x2 loadRMFloatUnsafe(long address);
    Double3x2 storeCMUnsafe(Double3x2 self, long address, int stride);
    Double3x2 loadCMUnsafe(long address, int stride);
    Double3x2 storeCMFloatUnsafe(Double3x2 self, long address, int stride);
    Double3x2 loadCMFloatUnsafe(long address, int stride);
    Double3x2 storeRMUnsafe(Double3x2 self, long address, int stride);
    Double3x2 loadRMUnsafe(long address, int stride);
    Double3x2 storeRMFloatUnsafe(Double3x2 self, long address, int stride);
    Double3x2 loadRMFloatUnsafe(long address, int stride);
}
