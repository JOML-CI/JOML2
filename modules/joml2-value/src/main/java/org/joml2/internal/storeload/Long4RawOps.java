// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface Long4RawOps {
    Long4 storeUnsafe(Long4 self, long address);
    Long4 loadUnsafe(long address);
    Long4 storeIntUnsafe(Long4 self, long address);
    Long4 loadIntUnsafe(long address);
}
