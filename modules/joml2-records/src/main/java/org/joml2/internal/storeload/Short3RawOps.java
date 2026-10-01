// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface Short3RawOps {
    Short3 storeUnsafe(Short3 self, long address);
    Short3 loadUnsafe(long address);
    Short3 storeByteUnsafe(Short3 self, long address);
    Short3 loadByteUnsafe(long address);
}
