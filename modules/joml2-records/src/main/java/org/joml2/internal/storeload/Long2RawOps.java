// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface Long2RawOps {
    Long2 storeUnsafe(Long2 self, long address);
    Long2 loadUnsafe(long address);
    Long2 storeIntUnsafe(Long2 self, long address);
    Long2 loadIntUnsafe(long address);
}
