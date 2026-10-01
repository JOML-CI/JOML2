// Copyright (c) 2015-2026 JOML
// SPDX-License-Identifier: MIT
package org.joml2.internal.storeload;

import org.joml2.*;
import org.joml2.Math;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

public interface Int4BbOps {
    IntBuffer storeAbsolute(Int4 self, int index, IntBuffer buf);
    Int4 loadAbsolute(int index, IntBuffer buf);
    ByteBuffer storeAbsolute(Int4 self, int index, ByteBuffer buf);
    Int4 loadAbsolute(int index, ByteBuffer buf);
    LongBuffer storeAbsolute(Int4 self, int index, LongBuffer buf);
    Int4 loadAbsolute(int index, LongBuffer buf);
    ByteBuffer storeLongAbsolute(Int4 self, int index, ByteBuffer buf);
    Int4 loadLongAbsolute(int index, ByteBuffer buf);
}
