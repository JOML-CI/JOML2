// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface Double4x3RawOps {
    Double4x3 storeCMUnsafe(Double4x3Impl self, long address);
    Double4x3 loadCMUnsafe(Double4x3Impl self, long address);
    Double4x3 storeCMFloatUnsafe(Double4x3Impl self, long address);
    Double4x3 loadCMFloatUnsafe(Double4x3Impl self, long address);
    Double4x3 storeRMUnsafe(Double4x3Impl self, long address);
    Double4x3 loadRMUnsafe(Double4x3Impl self, long address);
    Double4x3 storeRMFloatUnsafe(Double4x3Impl self, long address);
    Double4x3 loadRMFloatUnsafe(Double4x3Impl self, long address);
    Double4x3 storeCMUnsafe(Double4x3Impl self, long address, int stride);
    Double4x3 loadCMUnsafe(Double4x3Impl self, long address, int stride);
    Double4x3 storeCMFloatUnsafe(Double4x3Impl self, long address, int stride);
    Double4x3 loadCMFloatUnsafe(Double4x3Impl self, long address, int stride);
    Double4x3 storeRMUnsafe(Double4x3Impl self, long address, int stride);
    Double4x3 loadRMUnsafe(Double4x3Impl self, long address, int stride);
    Double4x3 storeRMFloatUnsafe(Double4x3Impl self, long address, int stride);
    Double4x3 loadRMFloatUnsafe(Double4x3Impl self, long address, int stride);
}
