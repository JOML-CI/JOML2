// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;

public interface IntRectRawOps {
    IntRect storeUnsafe(IntRectImpl self, long address);
    IntRect loadUnsafe(IntRectImpl self, long address);
    IntRect storeLongUnsafe(IntRectImpl self, long address);
    IntRect loadLongUnsafe(IntRectImpl self, long address);
}
