// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface Float3x4RawOps {
    Float3x4 storeCMUnsafe(Float3x4 self, long address);
    Float3x4 loadCMUnsafe(long address);
    Float3x4 storeCMDoubleUnsafe(Float3x4 self, long address);
    Float3x4 loadCMDoubleUnsafe(long address);
    Float3x4 storeRMUnsafe(Float3x4 self, long address);
    Float3x4 loadRMUnsafe(long address);
    Float3x4 storeRMDoubleUnsafe(Float3x4 self, long address);
    Float3x4 loadRMDoubleUnsafe(long address);
    Float3x4 storeCMUnsafe(Float3x4 self, long address, int stride);
    Float3x4 loadCMUnsafe(long address, int stride);
    Float3x4 storeCMDoubleUnsafe(Float3x4 self, long address, int stride);
    Float3x4 loadCMDoubleUnsafe(long address, int stride);
    Float3x4 storeRMUnsafe(Float3x4 self, long address, int stride);
    Float3x4 loadRMUnsafe(long address, int stride);
    Float3x4 storeRMDoubleUnsafe(Float3x4 self, long address, int stride);
    Float3x4 loadRMDoubleUnsafe(long address, int stride);
    Float3x4 storeCM4x4Unsafe(Float3x4 self, long address);
    Float3x4 storeCM4x4DoubleUnsafe(Float3x4 self, long address);
    Float3x4 storeRM4x4Unsafe(Float3x4 self, long address);
    Float3x4 storeRM4x4DoubleUnsafe(Float3x4 self, long address);
}
