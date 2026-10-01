// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface Float2x4RawOps {
    Float2x4 storeCMUnsafe(Float2x4Impl self, long address);
    Float2x4 loadCMUnsafe(Float2x4Impl self, long address);
    Float2x4 storeCMDoubleUnsafe(Float2x4Impl self, long address);
    Float2x4 loadCMDoubleUnsafe(Float2x4Impl self, long address);
    Float2x4 storeRMUnsafe(Float2x4Impl self, long address);
    Float2x4 loadRMUnsafe(Float2x4Impl self, long address);
    Float2x4 storeRMDoubleUnsafe(Float2x4Impl self, long address);
    Float2x4 loadRMDoubleUnsafe(Float2x4Impl self, long address);
    Float2x4 storeCMUnsafe(Float2x4Impl self, long address, int stride);
    Float2x4 loadCMUnsafe(Float2x4Impl self, long address, int stride);
    Float2x4 storeCMDoubleUnsafe(Float2x4Impl self, long address, int stride);
    Float2x4 loadCMDoubleUnsafe(Float2x4Impl self, long address, int stride);
    Float2x4 storeRMUnsafe(Float2x4Impl self, long address, int stride);
    Float2x4 loadRMUnsafe(Float2x4Impl self, long address, int stride);
    Float2x4 storeRMDoubleUnsafe(Float2x4Impl self, long address, int stride);
    Float2x4 loadRMDoubleUnsafe(Float2x4Impl self, long address, int stride);
}
