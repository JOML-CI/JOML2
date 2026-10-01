// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface Short2RawOps {
    Short2 storeUnsafe(Short2Impl self, long address);
    Short2 loadUnsafe(Short2Impl self, long address);
    Short2 storeByteUnsafe(Short2Impl self, long address);
    Short2 loadByteUnsafe(Short2Impl self, long address);
}
