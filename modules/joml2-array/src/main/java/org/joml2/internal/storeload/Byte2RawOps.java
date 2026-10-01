// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface Byte2RawOps {
    Byte2 storeUnsafe(Byte2Impl self, long address);
    Byte2 loadUnsafe(Byte2Impl self, long address);
    Byte2 storeShortUnsafe(Byte2Impl self, long address);
    Byte2 loadShortUnsafe(Byte2Impl self, long address);
}
