// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface Double3x2RawOps {
    Double3x2 storeCMUnsafe(Double3x2Impl self, long address);
    Double3x2 loadCMUnsafe(Double3x2Impl self, long address);
    Double3x2 storeCMFloatUnsafe(Double3x2Impl self, long address);
    Double3x2 loadCMFloatUnsafe(Double3x2Impl self, long address);
    Double3x2 storeRMUnsafe(Double3x2Impl self, long address);
    Double3x2 loadRMUnsafe(Double3x2Impl self, long address);
    Double3x2 storeRMFloatUnsafe(Double3x2Impl self, long address);
    Double3x2 loadRMFloatUnsafe(Double3x2Impl self, long address);
    Double3x2 storeCMUnsafe(Double3x2Impl self, long address, int stride);
    Double3x2 loadCMUnsafe(Double3x2Impl self, long address, int stride);
    Double3x2 storeCMFloatUnsafe(Double3x2Impl self, long address, int stride);
    Double3x2 loadCMFloatUnsafe(Double3x2Impl self, long address, int stride);
    Double3x2 storeRMUnsafe(Double3x2Impl self, long address, int stride);
    Double3x2 loadRMUnsafe(Double3x2Impl self, long address, int stride);
    Double3x2 storeRMFloatUnsafe(Double3x2Impl self, long address, int stride);
    Double3x2 loadRMFloatUnsafe(Double3x2Impl self, long address, int stride);
}
