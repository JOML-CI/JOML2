// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface Byte3RawOps {
    Byte3 storeUnsafe(Byte3Impl self, long address);
    Byte3 loadUnsafe(Byte3Impl self, long address);
    Byte3 storeShortUnsafe(Byte3Impl self, long address);
    Byte3 loadShortUnsafe(Byte3Impl self, long address);
}
