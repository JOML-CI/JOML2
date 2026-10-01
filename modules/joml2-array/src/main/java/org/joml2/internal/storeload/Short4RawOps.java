// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface Short4RawOps {
    Short4 storeUnsafe(Short4Impl self, long address);
    Short4 loadUnsafe(Short4Impl self, long address);
    Short4 storeByteUnsafe(Short4Impl self, long address);
    Short4 loadByteUnsafe(Short4Impl self, long address);
}
