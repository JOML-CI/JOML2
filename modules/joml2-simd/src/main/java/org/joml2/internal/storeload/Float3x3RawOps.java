// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface Float3x3RawOps {
    Float3x3 storeCMUnsafe(Float3x3Impl self, long address);
    Float3x3 loadCMUnsafe(Float3x3Impl self, long address);
    Float3x3 storeCMDoubleUnsafe(Float3x3Impl self, long address);
    Float3x3 loadCMDoubleUnsafe(Float3x3Impl self, long address);
    Float3x3 storeRMUnsafe(Float3x3Impl self, long address);
    Float3x3 loadRMUnsafe(Float3x3Impl self, long address);
    Float3x3 storeRMDoubleUnsafe(Float3x3Impl self, long address);
    Float3x3 loadRMDoubleUnsafe(Float3x3Impl self, long address);
    Float3x3 storeCMUnsafe(Float3x3Impl self, long address, int stride);
    Float3x3 loadCMUnsafe(Float3x3Impl self, long address, int stride);
    Float3x3 storeCMDoubleUnsafe(Float3x3Impl self, long address, int stride);
    Float3x3 loadCMDoubleUnsafe(Float3x3Impl self, long address, int stride);
    Float3x3 storeRMUnsafe(Float3x3Impl self, long address, int stride);
    Float3x3 loadRMUnsafe(Float3x3Impl self, long address, int stride);
    Float3x3 storeRMDoubleUnsafe(Float3x3Impl self, long address, int stride);
    Float3x3 loadRMDoubleUnsafe(Float3x3Impl self, long address, int stride);
    Float3x3 storeCM4x4Unsafe(Float3x3Impl self, long address);
    Float3x3 storeCM4x4DoubleUnsafe(Float3x3Impl self, long address);
    Float3x3 storeRM4x4Unsafe(Float3x3Impl self, long address);
    Float3x3 storeRM4x4DoubleUnsafe(Float3x3Impl self, long address);
}
