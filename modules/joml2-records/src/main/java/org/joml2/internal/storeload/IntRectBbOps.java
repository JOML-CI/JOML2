// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public interface IntRectBbOps {
    IntBuffer storeAbsolute(IntRect self, int index, IntBuffer buf);
    IntRect loadAbsolute(int index, IntBuffer buf);
    ByteBuffer storeAbsolute(IntRect self, int index, ByteBuffer buf);
    IntRect loadAbsolute(int index, ByteBuffer buf);
    LongBuffer storeAbsolute(IntRect self, int index, LongBuffer buf);
    IntRect loadAbsolute(int index, LongBuffer buf);
    ByteBuffer storeLongAbsolute(IntRect self, int index, ByteBuffer buf);
    IntRect loadLongAbsolute(int index, ByteBuffer buf);
}
