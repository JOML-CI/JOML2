// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface Float3x2RawOps {
    Float3x2 storeCMUnsafe(Float3x2Impl self, long address);
    Float3x2 loadCMUnsafe(Float3x2Impl self, long address);
    Float3x2 storeCMDoubleUnsafe(Float3x2Impl self, long address);
    Float3x2 loadCMDoubleUnsafe(Float3x2Impl self, long address);
    Float3x2 storeRMUnsafe(Float3x2Impl self, long address);
    Float3x2 loadRMUnsafe(Float3x2Impl self, long address);
    Float3x2 storeRMDoubleUnsafe(Float3x2Impl self, long address);
    Float3x2 loadRMDoubleUnsafe(Float3x2Impl self, long address);
    Float3x2 storeCMUnsafe(Float3x2Impl self, long address, int stride);
    Float3x2 loadCMUnsafe(Float3x2Impl self, long address, int stride);
    Float3x2 storeCMDoubleUnsafe(Float3x2Impl self, long address, int stride);
    Float3x2 loadCMDoubleUnsafe(Float3x2Impl self, long address, int stride);
    Float3x2 storeRMUnsafe(Float3x2Impl self, long address, int stride);
    Float3x2 loadRMUnsafe(Float3x2Impl self, long address, int stride);
    Float3x2 storeRMDoubleUnsafe(Float3x2Impl self, long address, int stride);
    Float3x2 loadRMDoubleUnsafe(Float3x2Impl self, long address, int stride);
}
