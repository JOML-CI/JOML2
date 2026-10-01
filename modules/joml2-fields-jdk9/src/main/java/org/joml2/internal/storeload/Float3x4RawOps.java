// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface Float3x4RawOps {
    Float3x4 storeCMUnsafe(Float3x4Impl self, long address);
    Float3x4 loadCMUnsafe(Float3x4Impl self, long address);
    Float3x4 storeCMDoubleUnsafe(Float3x4Impl self, long address);
    Float3x4 loadCMDoubleUnsafe(Float3x4Impl self, long address);
    Float3x4 storeRMUnsafe(Float3x4Impl self, long address);
    Float3x4 loadRMUnsafe(Float3x4Impl self, long address);
    Float3x4 storeRMDoubleUnsafe(Float3x4Impl self, long address);
    Float3x4 loadRMDoubleUnsafe(Float3x4Impl self, long address);
    Float3x4 storeCMUnsafe(Float3x4Impl self, long address, int stride);
    Float3x4 loadCMUnsafe(Float3x4Impl self, long address, int stride);
    Float3x4 storeCMDoubleUnsafe(Float3x4Impl self, long address, int stride);
    Float3x4 loadCMDoubleUnsafe(Float3x4Impl self, long address, int stride);
    Float3x4 storeRMUnsafe(Float3x4Impl self, long address, int stride);
    Float3x4 loadRMUnsafe(Float3x4Impl self, long address, int stride);
    Float3x4 storeRMDoubleUnsafe(Float3x4Impl self, long address, int stride);
    Float3x4 loadRMDoubleUnsafe(Float3x4Impl self, long address, int stride);
    Float3x4 storeCM4x4Unsafe(Float3x4Impl self, long address);
    Float3x4 storeCM4x4DoubleUnsafe(Float3x4Impl self, long address);
    Float3x4 storeRM4x4Unsafe(Float3x4Impl self, long address);
    Float3x4 storeRM4x4DoubleUnsafe(Float3x4Impl self, long address);
}
