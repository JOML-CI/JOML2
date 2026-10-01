// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface Byte4RawOps {
    Byte4 storeUnsafe(Byte4 self, long address);
    Byte4 loadUnsafe(long address);
    Byte4 storeShortUnsafe(Byte4 self, long address);
    Byte4 loadShortUnsafe(long address);
}
