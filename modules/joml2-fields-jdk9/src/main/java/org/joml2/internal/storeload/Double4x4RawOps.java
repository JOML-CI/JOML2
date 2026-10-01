// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface Double4x4RawOps {
    Double4x4 storeCMUnsafe(Double4x4Impl self, long address);
    Double4x4 loadCMUnsafe(Double4x4Impl self, long address);
    Double4x4 storeCMFloatUnsafe(Double4x4Impl self, long address);
    Double4x4 loadCMFloatUnsafe(Double4x4Impl self, long address);
    Double4x4 storeRMUnsafe(Double4x4Impl self, long address);
    Double4x4 loadRMUnsafe(Double4x4Impl self, long address);
    Double4x4 storeRMFloatUnsafe(Double4x4Impl self, long address);
    Double4x4 loadRMFloatUnsafe(Double4x4Impl self, long address);
    Double4x4 storeCMUnsafe(Double4x4Impl self, long address, int stride);
    Double4x4 loadCMUnsafe(Double4x4Impl self, long address, int stride);
    Double4x4 storeCMFloatUnsafe(Double4x4Impl self, long address, int stride);
    Double4x4 loadCMFloatUnsafe(Double4x4Impl self, long address, int stride);
    Double4x4 storeRMUnsafe(Double4x4Impl self, long address, int stride);
    Double4x4 loadRMUnsafe(Double4x4Impl self, long address, int stride);
    Double4x4 storeRMFloatUnsafe(Double4x4Impl self, long address, int stride);
    Double4x4 loadRMFloatUnsafe(Double4x4Impl self, long address, int stride);
}
