// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface Byte3RawOps {
    Byte3 storeUnsafe(Byte3 self, long address);
    Byte3 loadUnsafe(long address);
    Byte3 storeShortUnsafe(Byte3 self, long address);
    Byte3 loadShortUnsafe(long address);
}
