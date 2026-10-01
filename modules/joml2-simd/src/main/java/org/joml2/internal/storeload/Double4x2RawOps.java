// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface Double4x2RawOps {
    Double4x2 storeCMUnsafe(Double4x2Impl self, long address);
    Double4x2 loadCMUnsafe(Double4x2Impl self, long address);
    Double4x2 storeCMFloatUnsafe(Double4x2Impl self, long address);
    Double4x2 loadCMFloatUnsafe(Double4x2Impl self, long address);
    Double4x2 storeRMUnsafe(Double4x2Impl self, long address);
    Double4x2 loadRMUnsafe(Double4x2Impl self, long address);
    Double4x2 storeRMFloatUnsafe(Double4x2Impl self, long address);
    Double4x2 loadRMFloatUnsafe(Double4x2Impl self, long address);
    Double4x2 storeCMUnsafe(Double4x2Impl self, long address, int stride);
    Double4x2 loadCMUnsafe(Double4x2Impl self, long address, int stride);
    Double4x2 storeCMFloatUnsafe(Double4x2Impl self, long address, int stride);
    Double4x2 loadCMFloatUnsafe(Double4x2Impl self, long address, int stride);
    Double4x2 storeRMUnsafe(Double4x2Impl self, long address, int stride);
    Double4x2 loadRMUnsafe(Double4x2Impl self, long address, int stride);
    Double4x2 storeRMFloatUnsafe(Double4x2Impl self, long address, int stride);
    Double4x2 loadRMFloatUnsafe(Double4x2Impl self, long address, int stride);
}
