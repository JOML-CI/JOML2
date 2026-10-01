// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface Float2x4RawOps {
    Float2x4 storeCMUnsafe(Float2x4 self, long address);
    Float2x4 loadCMUnsafe(long address);
    Float2x4 storeCMDoubleUnsafe(Float2x4 self, long address);
    Float2x4 loadCMDoubleUnsafe(long address);
    Float2x4 storeRMUnsafe(Float2x4 self, long address);
    Float2x4 loadRMUnsafe(long address);
    Float2x4 storeRMDoubleUnsafe(Float2x4 self, long address);
    Float2x4 loadRMDoubleUnsafe(long address);
    Float2x4 storeCMUnsafe(Float2x4 self, long address, int stride);
    Float2x4 loadCMUnsafe(long address, int stride);
    Float2x4 storeCMDoubleUnsafe(Float2x4 self, long address, int stride);
    Float2x4 loadCMDoubleUnsafe(long address, int stride);
    Float2x4 storeRMUnsafe(Float2x4 self, long address, int stride);
    Float2x4 loadRMUnsafe(long address, int stride);
    Float2x4 storeRMDoubleUnsafe(Float2x4 self, long address, int stride);
    Float2x4 loadRMDoubleUnsafe(long address, int stride);
}
