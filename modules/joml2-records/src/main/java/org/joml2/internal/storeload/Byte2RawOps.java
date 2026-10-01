// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface Byte2RawOps {
    Byte2 storeUnsafe(Byte2 self, long address);
    Byte2 loadUnsafe(long address);
    Byte2 storeShortUnsafe(Byte2 self, long address);
    Byte2 loadShortUnsafe(long address);
}
