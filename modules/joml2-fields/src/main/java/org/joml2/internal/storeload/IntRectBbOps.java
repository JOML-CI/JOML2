// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import org.joml2.internal.types.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public interface IntRectBbOps {
    IntBuffer storeAbsolute(IntRectImpl self, int index, IntBuffer buf);
    IntRect loadAbsolute(IntRectImpl self, int index, IntBuffer buf);
    ByteBuffer storeAbsolute(IntRectImpl self, int index, ByteBuffer buf);
    IntRect loadAbsolute(IntRectImpl self, int index, ByteBuffer buf);
    LongBuffer storeAbsolute(IntRectImpl self, int index, LongBuffer buf);
    IntRect loadAbsolute(IntRectImpl self, int index, LongBuffer buf);
    ByteBuffer storeLongAbsolute(IntRectImpl self, int index, ByteBuffer buf);
    IntRect loadLongAbsolute(IntRectImpl self, int index, ByteBuffer buf);
}
