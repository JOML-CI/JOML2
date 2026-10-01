// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface Float4x4RawOps {
    Float4x4 storeCMUnsafe(Float4x4 self, long address);
    Float4x4 loadCMUnsafe(long address);
    Float4x4 storeCMDoubleUnsafe(Float4x4 self, long address);
    Float4x4 loadCMDoubleUnsafe(long address);
    Float4x4 storeRMUnsafe(Float4x4 self, long address);
    Float4x4 loadRMUnsafe(long address);
    Float4x4 storeRMDoubleUnsafe(Float4x4 self, long address);
    Float4x4 loadRMDoubleUnsafe(long address);
    Float4x4 storeCMUnsafe(Float4x4 self, long address, int stride);
    Float4x4 loadCMUnsafe(long address, int stride);
    Float4x4 storeCMDoubleUnsafe(Float4x4 self, long address, int stride);
    Float4x4 loadCMDoubleUnsafe(long address, int stride);
    Float4x4 storeRMUnsafe(Float4x4 self, long address, int stride);
    Float4x4 loadRMUnsafe(long address, int stride);
    Float4x4 storeRMDoubleUnsafe(Float4x4 self, long address, int stride);
    Float4x4 loadRMDoubleUnsafe(long address, int stride);
}
