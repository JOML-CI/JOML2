// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface Float2x3RawOps {
    Float2x3 storeCMUnsafe(Float2x3 self, long address);
    Float2x3 loadCMUnsafe(long address);
    Float2x3 storeCMDoubleUnsafe(Float2x3 self, long address);
    Float2x3 loadCMDoubleUnsafe(long address);
    Float2x3 storeRMUnsafe(Float2x3 self, long address);
    Float2x3 loadRMUnsafe(long address);
    Float2x3 storeRMDoubleUnsafe(Float2x3 self, long address);
    Float2x3 loadRMDoubleUnsafe(long address);
    Float2x3 storeCMUnsafe(Float2x3 self, long address, int stride);
    Float2x3 loadCMUnsafe(long address, int stride);
    Float2x3 storeCMDoubleUnsafe(Float2x3 self, long address, int stride);
    Float2x3 loadCMDoubleUnsafe(long address, int stride);
    Float2x3 storeRMUnsafe(Float2x3 self, long address, int stride);
    Float2x3 loadRMUnsafe(long address, int stride);
    Float2x3 storeRMDoubleUnsafe(Float2x3 self, long address, int stride);
    Float2x3 loadRMDoubleUnsafe(long address, int stride);
    Float2x3 storeCM3x3Unsafe(Float2x3 self, long address);
    Float2x3 storeCM3x3DoubleUnsafe(Float2x3 self, long address);
    Float2x3 storeRM3x3Unsafe(Float2x3 self, long address);
    Float2x3 storeRM3x3DoubleUnsafe(Float2x3 self, long address);
    Float2x3 storeCM4x4Unsafe(Float2x3 self, long address);
    Float2x3 storeCM4x4DoubleUnsafe(Float2x3 self, long address);
    Float2x3 storeRM4x4Unsafe(Float2x3 self, long address);
    Float2x3 storeRM4x4DoubleUnsafe(Float2x3 self, long address);
}
