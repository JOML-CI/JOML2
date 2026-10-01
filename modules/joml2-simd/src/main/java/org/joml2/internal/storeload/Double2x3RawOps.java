// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface Double2x3RawOps {
    Double2x3 storeCMUnsafe(Double2x3Impl self, long address);
    Double2x3 loadCMUnsafe(Double2x3Impl self, long address);
    Double2x3 storeCMFloatUnsafe(Double2x3Impl self, long address);
    Double2x3 loadCMFloatUnsafe(Double2x3Impl self, long address);
    Double2x3 storeRMUnsafe(Double2x3Impl self, long address);
    Double2x3 loadRMUnsafe(Double2x3Impl self, long address);
    Double2x3 storeRMFloatUnsafe(Double2x3Impl self, long address);
    Double2x3 loadRMFloatUnsafe(Double2x3Impl self, long address);
    Double2x3 storeCMUnsafe(Double2x3Impl self, long address, int stride);
    Double2x3 loadCMUnsafe(Double2x3Impl self, long address, int stride);
    Double2x3 storeCMFloatUnsafe(Double2x3Impl self, long address, int stride);
    Double2x3 loadCMFloatUnsafe(Double2x3Impl self, long address, int stride);
    Double2x3 storeRMUnsafe(Double2x3Impl self, long address, int stride);
    Double2x3 loadRMUnsafe(Double2x3Impl self, long address, int stride);
    Double2x3 storeRMFloatUnsafe(Double2x3Impl self, long address, int stride);
    Double2x3 loadRMFloatUnsafe(Double2x3Impl self, long address, int stride);
    Double2x3 storeCM3x3Unsafe(Double2x3Impl self, long address);
    Double2x3 storeCM3x3FloatUnsafe(Double2x3Impl self, long address);
    Double2x3 storeRM3x3Unsafe(Double2x3Impl self, long address);
    Double2x3 storeRM3x3FloatUnsafe(Double2x3Impl self, long address);
    Double2x3 storeCM4x4Unsafe(Double2x3Impl self, long address);
    Double2x3 storeCM4x4FloatUnsafe(Double2x3Impl self, long address);
    Double2x3 storeRM4x4Unsafe(Double2x3Impl self, long address);
    Double2x3 storeRM4x4FloatUnsafe(Double2x3Impl self, long address);
}
