// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface Double2x2RawOps {
    Double2x2 storeCMUnsafe(Double2x2Impl self, long address);
    Double2x2 loadCMUnsafe(Double2x2Impl self, long address);
    Double2x2 storeCMFloatUnsafe(Double2x2Impl self, long address);
    Double2x2 loadCMFloatUnsafe(Double2x2Impl self, long address);
    Double2x2 storeRMUnsafe(Double2x2Impl self, long address);
    Double2x2 loadRMUnsafe(Double2x2Impl self, long address);
    Double2x2 storeRMFloatUnsafe(Double2x2Impl self, long address);
    Double2x2 loadRMFloatUnsafe(Double2x2Impl self, long address);
    Double2x2 storeCMUnsafe(Double2x2Impl self, long address, int stride);
    Double2x2 loadCMUnsafe(Double2x2Impl self, long address, int stride);
    Double2x2 storeCMFloatUnsafe(Double2x2Impl self, long address, int stride);
    Double2x2 loadCMFloatUnsafe(Double2x2Impl self, long address, int stride);
    Double2x2 storeRMUnsafe(Double2x2Impl self, long address, int stride);
    Double2x2 loadRMUnsafe(Double2x2Impl self, long address, int stride);
    Double2x2 storeRMFloatUnsafe(Double2x2Impl self, long address, int stride);
    Double2x2 loadRMFloatUnsafe(Double2x2Impl self, long address, int stride);
    Double2x2 storeCM3x3Unsafe(Double2x2Impl self, long address);
    Double2x2 storeCM3x3FloatUnsafe(Double2x2Impl self, long address);
    Double2x2 storeRM3x3Unsafe(Double2x2Impl self, long address);
    Double2x2 storeRM3x3FloatUnsafe(Double2x2Impl self, long address);
    Double2x2 storeCM4x4Unsafe(Double2x2Impl self, long address);
    Double2x2 storeCM4x4FloatUnsafe(Double2x2Impl self, long address);
    Double2x2 storeRM4x4Unsafe(Double2x2Impl self, long address);
    Double2x2 storeRM4x4FloatUnsafe(Double2x2Impl self, long address);
}
