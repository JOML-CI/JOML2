// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;

public abstract class IntRectRawOps {
    public abstract IntRect storeUnsafe(IntRect self, long address);
    public abstract IntRect loadUnsafe(long address);
    public abstract IntRect storeLongUnsafe(IntRect self, long address);
    public abstract IntRect loadLongUnsafe(long address);
}
