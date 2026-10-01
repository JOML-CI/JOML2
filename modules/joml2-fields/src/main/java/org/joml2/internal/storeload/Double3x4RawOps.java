// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface Double3x4RawOps {
    Double3x4 storeCMUnsafe(Double3x4Impl self, long address);
    Double3x4 loadCMUnsafe(Double3x4Impl self, long address);
    Double3x4 storeCMFloatUnsafe(Double3x4Impl self, long address);
    Double3x4 loadCMFloatUnsafe(Double3x4Impl self, long address);
    Double3x4 storeRMUnsafe(Double3x4Impl self, long address);
    Double3x4 loadRMUnsafe(Double3x4Impl self, long address);
    Double3x4 storeRMFloatUnsafe(Double3x4Impl self, long address);
    Double3x4 loadRMFloatUnsafe(Double3x4Impl self, long address);
    Double3x4 storeCMUnsafe(Double3x4Impl self, long address, int stride);
    Double3x4 loadCMUnsafe(Double3x4Impl self, long address, int stride);
    Double3x4 storeCMFloatUnsafe(Double3x4Impl self, long address, int stride);
    Double3x4 loadCMFloatUnsafe(Double3x4Impl self, long address, int stride);
    Double3x4 storeRMUnsafe(Double3x4Impl self, long address, int stride);
    Double3x4 loadRMUnsafe(Double3x4Impl self, long address, int stride);
    Double3x4 storeRMFloatUnsafe(Double3x4Impl self, long address, int stride);
    Double3x4 loadRMFloatUnsafe(Double3x4Impl self, long address, int stride);
    Double3x4 storeCM4x4Unsafe(Double3x4Impl self, long address);
    Double3x4 storeCM4x4FloatUnsafe(Double3x4Impl self, long address);
    Double3x4 storeRM4x4Unsafe(Double3x4Impl self, long address);
    Double3x4 storeRM4x4FloatUnsafe(Double3x4Impl self, long address);
}
