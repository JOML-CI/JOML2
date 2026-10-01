// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public interface Short4RawOps {
    Short4 storeUnsafe(Short4 self, long address);
    Short4 loadUnsafe(long address);
    Short4 storeByteUnsafe(Short4 self, long address);
    Short4 loadByteUnsafe(long address);
}
