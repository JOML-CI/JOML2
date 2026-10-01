// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface Double3x3RawOps {
    Double3x3 storeCMUnsafe(Double3x3Impl self, long address);
    Double3x3 loadCMUnsafe(Double3x3Impl self, long address);
    Double3x3 storeCMFloatUnsafe(Double3x3Impl self, long address);
    Double3x3 loadCMFloatUnsafe(Double3x3Impl self, long address);
    Double3x3 storeRMUnsafe(Double3x3Impl self, long address);
    Double3x3 loadRMUnsafe(Double3x3Impl self, long address);
    Double3x3 storeRMFloatUnsafe(Double3x3Impl self, long address);
    Double3x3 loadRMFloatUnsafe(Double3x3Impl self, long address);
    Double3x3 storeCMUnsafe(Double3x3Impl self, long address, int stride);
    Double3x3 loadCMUnsafe(Double3x3Impl self, long address, int stride);
    Double3x3 storeCMFloatUnsafe(Double3x3Impl self, long address, int stride);
    Double3x3 loadCMFloatUnsafe(Double3x3Impl self, long address, int stride);
    Double3x3 storeRMUnsafe(Double3x3Impl self, long address, int stride);
    Double3x3 loadRMUnsafe(Double3x3Impl self, long address, int stride);
    Double3x3 storeRMFloatUnsafe(Double3x3Impl self, long address, int stride);
    Double3x3 loadRMFloatUnsafe(Double3x3Impl self, long address, int stride);
    Double3x3 storeCM4x4Unsafe(Double3x3Impl self, long address);
    Double3x3 storeCM4x4FloatUnsafe(Double3x3Impl self, long address);
    Double3x3 storeRM4x4Unsafe(Double3x3Impl self, long address);
    Double3x3 storeRM4x4FloatUnsafe(Double3x3Impl self, long address);
}
