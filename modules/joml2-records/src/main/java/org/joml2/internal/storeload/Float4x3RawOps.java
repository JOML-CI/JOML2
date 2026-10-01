// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface Float4x3RawOps {
    Float4x3 storeCMUnsafe(Float4x3 self, long address);
    Float4x3 loadCMUnsafe(long address);
    Float4x3 storeCMDoubleUnsafe(Float4x3 self, long address);
    Float4x3 loadCMDoubleUnsafe(long address);
    Float4x3 storeRMUnsafe(Float4x3 self, long address);
    Float4x3 loadRMUnsafe(long address);
    Float4x3 storeRMDoubleUnsafe(Float4x3 self, long address);
    Float4x3 loadRMDoubleUnsafe(long address);
    Float4x3 storeCMUnsafe(Float4x3 self, long address, int stride);
    Float4x3 loadCMUnsafe(long address, int stride);
    Float4x3 storeCMDoubleUnsafe(Float4x3 self, long address, int stride);
    Float4x3 loadCMDoubleUnsafe(long address, int stride);
    Float4x3 storeRMUnsafe(Float4x3 self, long address, int stride);
    Float4x3 loadRMUnsafe(long address, int stride);
    Float4x3 storeRMDoubleUnsafe(Float4x3 self, long address, int stride);
    Float4x3 loadRMDoubleUnsafe(long address, int stride);
}
