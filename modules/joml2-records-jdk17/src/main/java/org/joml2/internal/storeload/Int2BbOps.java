// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public interface Int2BbOps {
    IntBuffer storeAbsolute(Int2 self, int index, IntBuffer buf);
    Int2 loadAbsolute(int index, IntBuffer buf);
    ByteBuffer storeAbsolute(Int2 self, int index, ByteBuffer buf);
    Int2 loadAbsolute(int index, ByteBuffer buf);
    LongBuffer storeAbsolute(Int2 self, int index, LongBuffer buf);
    Int2 loadAbsolute(int index, LongBuffer buf);
    ByteBuffer storeLongAbsolute(Int2 self, int index, ByteBuffer buf);
    Int2 loadLongAbsolute(int index, ByteBuffer buf);
}
